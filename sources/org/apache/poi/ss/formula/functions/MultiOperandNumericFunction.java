package org.apache.poi.ss.formula.functions;

import java.util.Arrays;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public abstract class MultiOperandNumericFunction implements Function {
    private static final int DEFAULT_MAX_NUM_OPERANDS = SpreadsheetVersion.EXCEL2007.getMaxFunctionArgs();
    static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    private EvalConsumer<BlankEval, DoubleList> blankConsumer;
    private EvalConsumer<BoolEval, DoubleList> boolByRefConsumer;
    private EvalConsumer<BoolEval, DoubleList> boolByValueConsumer;
    private EvalConsumer<MissingArgEval, DoubleList> missingArgConsumer = ConsumerFactory.createForMissingArg(Policy.SKIP);

    private interface EvalConsumer<T, R> {
        void accept(T t, R r) throws EvaluationException;
    }

    public enum Policy {
        COERCE,
        SKIP,
        ERROR
    }

    /* access modifiers changed from: protected */
    public abstract double evaluate(double[] dArr) throws EvaluationException;

    public boolean isHiddenRowCounted() {
        return true;
    }

    public boolean isSubtotalCounted() {
        return true;
    }

    protected MultiOperandNumericFunction(boolean z, boolean z2) {
        this.boolByRefConsumer = ConsumerFactory.createForBoolEval(z ? Policy.COERCE : Policy.SKIP);
        this.boolByValueConsumer = ConsumerFactory.createForBoolEval(Policy.COERCE);
        this.blankConsumer = ConsumerFactory.createForBlank(z2 ? Policy.COERCE : Policy.SKIP);
    }

    private static class DoubleList {
        private double[] _array = new double[8];
        private int _count = 0;

        public double[] toArray() {
            int i = this._count;
            return i < 1 ? MultiOperandNumericFunction.EMPTY_DOUBLE_ARRAY : Arrays.copyOf(this._array, i);
        }

        private void ensureCapacity(int i) {
            double[] dArr = this._array;
            if (i > dArr.length) {
                this._array = Arrays.copyOf(dArr, (i * 3) / 2);
            }
        }

        public void add(double d) {
            ensureCapacity(this._count + 1);
            double[] dArr = this._array;
            int i = this._count;
            dArr[i] = d;
            this._count = i + 1;
        }
    }

    public void setMissingArgPolicy(Policy policy) {
        this.missingArgConsumer = ConsumerFactory.createForMissingArg(policy);
    }

    public void setBlankEvalPolicy(Policy policy) {
        this.blankConsumer = ConsumerFactory.createForBlank(policy);
    }

    public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        try {
            double evaluate = evaluate(getNumberArray(valueEvalArr));
            if (!Double.isNaN(evaluate)) {
                if (!Double.isInfinite(evaluate)) {
                    return new NumberEval(evaluate);
                }
            }
            return ErrorEval.NUM_ERROR;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxNumOperands() {
        return DEFAULT_MAX_NUM_OPERANDS;
    }

    /* access modifiers changed from: protected */
    public final double[] getNumberArray(ValueEval[] valueEvalArr) throws EvaluationException {
        if (valueEvalArr.length <= getMaxNumOperands()) {
            DoubleList doubleList = new DoubleList();
            for (ValueEval collectValues : valueEvalArr) {
                collectValues(collectValues, doubleList);
            }
            return doubleList.toArray();
        }
        throw EvaluationException.invalidValue();
    }

    private void collectValues(ValueEval valueEval, DoubleList doubleList) throws EvaluationException {
        if (valueEval instanceof ThreeDEval) {
            ThreeDEval threeDEval = (ThreeDEval) valueEval;
            for (int firstSheetIndex = threeDEval.getFirstSheetIndex(); firstSheetIndex <= threeDEval.getLastSheetIndex(); firstSheetIndex++) {
                int width = threeDEval.getWidth();
                int height = threeDEval.getHeight();
                for (int i = 0; i < height; i++) {
                    for (int i2 = 0; i2 < width; i2++) {
                        ValueEval value = threeDEval.getValue(firstSheetIndex, i, i2);
                        if ((isSubtotalCounted() || !threeDEval.isSubTotal(i, i2)) && (isHiddenRowCounted() || !threeDEval.isRowHidden(i))) {
                            collectValue(value, true, doubleList);
                        }
                    }
                }
            }
        } else if (valueEval instanceof TwoDEval) {
            TwoDEval twoDEval = (TwoDEval) valueEval;
            int width2 = twoDEval.getWidth();
            int height2 = twoDEval.getHeight();
            for (int i3 = 0; i3 < height2; i3++) {
                for (int i4 = 0; i4 < width2; i4++) {
                    ValueEval value2 = twoDEval.getValue(i3, i4);
                    if (isSubtotalCounted() || !twoDEval.isSubTotal(i3, i4)) {
                        collectValue(value2, true, doubleList);
                    }
                }
            }
        } else if (valueEval instanceof RefEval) {
            RefEval refEval = (RefEval) valueEval;
            for (int firstSheetIndex2 = refEval.getFirstSheetIndex(); firstSheetIndex2 <= refEval.getLastSheetIndex(); firstSheetIndex2++) {
                collectValue(refEval.getInnerValueEval(firstSheetIndex2), true, doubleList);
            }
        } else {
            collectValue(valueEval, false, doubleList);
        }
    }

    private void collectValue(ValueEval valueEval, boolean z, DoubleList doubleList) throws EvaluationException {
        if (valueEval == null) {
            throw new IllegalArgumentException("ve must not be null");
        } else if (valueEval instanceof BoolEval) {
            BoolEval boolEval = (BoolEval) valueEval;
            if (z) {
                this.boolByRefConsumer.accept(boolEval, doubleList);
            } else {
                this.boolByValueConsumer.accept(boolEval, doubleList);
            }
        } else if (valueEval instanceof NumericValueEval) {
            doubleList.add(((NumericValueEval) valueEval).getNumberValue());
        } else if (valueEval instanceof StringValueEval) {
            if (!z) {
                Double parseDouble = OperandResolver.parseDouble(((StringValueEval) valueEval).getStringValue());
                if (parseDouble != null) {
                    doubleList.add(parseDouble.doubleValue());
                    return;
                }
                throw new EvaluationException(ErrorEval.VALUE_INVALID);
            }
        } else if (valueEval instanceof ErrorEval) {
            throw new EvaluationException((ErrorEval) valueEval);
        } else if (valueEval == BlankEval.instance) {
            this.blankConsumer.accept((BlankEval) valueEval, doubleList);
        } else if (valueEval == MissingArgEval.instance) {
            this.missingArgConsumer.accept((MissingArgEval) valueEval, doubleList);
        } else {
            throw new RuntimeException("Invalid ValueEval type passed for conversion: (" + valueEval.getClass() + ")");
        }
    }

    private static class ConsumerFactory {
        static /* synthetic */ void lambda$doNothing$3(Object obj, DoubleList doubleList) throws EvaluationException {
        }

        private ConsumerFactory() {
        }

        static EvalConsumer<MissingArgEval, DoubleList> createForMissingArg(Policy policy) {
            return createAny(new MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda1(), policy);
        }

        static EvalConsumer<BoolEval, DoubleList> createForBoolEval(Policy policy) {
            return createAny(new MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda2(), policy);
        }

        static EvalConsumer<BlankEval, DoubleList> createForBlank(Policy policy) {
            return createAny(new MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda3(), policy);
        }

        private static <T> EvalConsumer<T, DoubleList> createAny(EvalConsumer<T, DoubleList> evalConsumer, Policy policy) {
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$formula$functions$MultiOperandNumericFunction$Policy[policy.ordinal()];
            if (i == 1) {
                return evalConsumer;
            }
            if (i == 2) {
                return doNothing();
            }
            if (i == 3) {
                return throwValueInvalid();
            }
            throw new AssertionError();
        }

        private static <T> EvalConsumer<T, DoubleList> doNothing() {
            return new MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda4();
        }

        private static <T> EvalConsumer<T, DoubleList> throwValueInvalid() {
            return new MultiOperandNumericFunction$ConsumerFactory$$ExternalSyntheticLambda0();
        }

        static /* synthetic */ void lambda$throwValueInvalid$4(Object obj, DoubleList doubleList) throws EvaluationException {
            throw new EvaluationException(ErrorEval.VALUE_INVALID);
        }
    }

    /* renamed from: org.apache.poi.ss.formula.functions.MultiOperandNumericFunction$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$formula$functions$MultiOperandNumericFunction$Policy;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.ss.formula.functions.MultiOperandNumericFunction$Policy[] r0 = org.apache.poi.ss.formula.functions.MultiOperandNumericFunction.Policy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$formula$functions$MultiOperandNumericFunction$Policy = r0
                org.apache.poi.ss.formula.functions.MultiOperandNumericFunction$Policy r1 = org.apache.poi.ss.formula.functions.MultiOperandNumericFunction.Policy.COERCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$functions$MultiOperandNumericFunction$Policy     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.formula.functions.MultiOperandNumericFunction$Policy r1 = org.apache.poi.ss.formula.functions.MultiOperandNumericFunction.Policy.SKIP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$functions$MultiOperandNumericFunction$Policy     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.formula.functions.MultiOperandNumericFunction$Policy r1 = org.apache.poi.ss.formula.functions.MultiOperandNumericFunction.Policy.ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.MultiOperandNumericFunction.AnonymousClass1.<clinit>():void");
        }
    }
}
