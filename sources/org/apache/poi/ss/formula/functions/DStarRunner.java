package org.apache.poi.ss.formula.functions;

import java.util.function.Supplier;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;

public final class DStarRunner implements Function3Arg {
    private final DStarAlgorithmEnum algoType;

    private enum operator {
        largerThan,
        largerEqualThan,
        smallerThan,
        smallerEqualThan,
        equal
    }

    public enum DStarAlgorithmEnum {
        DGET(new DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda0()),
        DMIN(new DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda1()),
        DMAX(new DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda2()),
        DSUM(new DStarRunner$DStarAlgorithmEnum$$ExternalSyntheticLambda3());
        
        private final Supplier<IDStarAlgorithm> implSupplier;

        private DStarAlgorithmEnum(Supplier<IDStarAlgorithm> supplier) {
            this.implSupplier = supplier;
        }

        public IDStarAlgorithm newInstance() {
            return this.implSupplier.get();
        }
    }

    public DStarRunner(DStarAlgorithmEnum dStarAlgorithmEnum) {
        this.algoType = dStarAlgorithmEnum;
    }

    public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
        if (valueEvalArr.length != 3) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluate(i, i2, valueEvalArr[0], valueEvalArr[1], valueEvalArr[2]);
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2, ValueEval valueEval3) {
        if (!(valueEval instanceof AreaEval) || !(valueEval3 instanceof AreaEval)) {
            return ErrorEval.VALUE_INVALID;
        }
        AreaEval areaEval = (AreaEval) valueEval;
        AreaEval areaEval2 = (AreaEval) valueEval3;
        try {
            try {
                int columnForName = getColumnForName(OperandResolver.getSingleValue(valueEval2, i, i2), areaEval);
                if (columnForName == -1) {
                    return ErrorEval.VALUE_INVALID;
                }
                IDStarAlgorithm newInstance = this.algoType.newInstance();
                int height = areaEval.getHeight();
                int i3 = 1;
                while (i3 < height) {
                    try {
                        if (fullfillsConditions(areaEval, i3, areaEval2) && !newInstance.processMatch(resolveReference(areaEval, i3, columnForName))) {
                            break;
                        }
                        i3++;
                    } catch (EvaluationException unused) {
                        return ErrorEval.VALUE_INVALID;
                    }
                }
                return newInstance.getResult();
            } catch (EvaluationException unused2) {
                return ErrorEval.VALUE_INVALID;
            }
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static int getColumnForName(ValueEval valueEval, AreaEval areaEval) throws EvaluationException {
        if (!(valueEval instanceof NumericValueEval)) {
            return getColumnForString(areaEval, OperandResolver.coerceValueToString(valueEval));
        }
        int coerceValueToInt = OperandResolver.coerceValueToInt(valueEval) - 1;
        if (coerceValueToInt < 0 || coerceValueToInt >= areaEval.getWidth()) {
            return -1;
        }
        return coerceValueToInt;
    }

    private static int getColumnForString(AreaEval areaEval, String str) {
        int width = areaEval.getWidth();
        for (int i = 0; i < width; i++) {
            ValueEval resolveReference = resolveReference(areaEval, 0, i);
            if (!(resolveReference instanceof BlankEval) && !(resolveReference instanceof ErrorEval) && str.equalsIgnoreCase(OperandResolver.coerceValueToString(resolveReference))) {
                return i;
            }
        }
        return -1;
    }

    private static boolean fullfillsConditions(AreaEval areaEval, int i, AreaEval areaEval2) throws EvaluationException {
        int height = areaEval2.getHeight();
        int i2 = 1;
        while (true) {
            boolean z = false;
            if (i2 >= height) {
                return false;
            }
            int width = areaEval2.getWidth();
            int i3 = 0;
            while (true) {
                if (i3 >= width) {
                    z = true;
                    break;
                }
                ValueEval resolveReference = resolveReference(areaEval2, i2, i3);
                if (!(resolveReference instanceof BlankEval)) {
                    ValueEval resolveReference2 = resolveReference(areaEval2, 0, i3);
                    if (resolveReference2 instanceof StringValueEval) {
                        if (getColumnForName(resolveReference2, areaEval) != -1) {
                            if (!testNormalCondition(resolveReference(areaEval, i, getColumnForName(resolveReference2, areaEval)), resolveReference)) {
                                break;
                            }
                        } else if (OperandResolver.coerceValueToString(resolveReference).isEmpty()) {
                            throw new EvaluationException(ErrorEval.VALUE_INVALID);
                        } else {
                            throw new NotImplementedException("D* function with formula conditions");
                        }
                    } else {
                        throw new EvaluationException(ErrorEval.VALUE_INVALID);
                    }
                }
                i3++;
            }
            if (z) {
                return true;
            }
            i2++;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:27|28) */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        java.lang.Double.parseDouble(r6);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0070 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean testNormalCondition(org.apache.poi.ss.formula.eval.ValueEval r5, org.apache.poi.ss.formula.eval.ValueEval r6) throws org.apache.poi.ss.formula.eval.EvaluationException {
        /*
            boolean r0 = r6 instanceof org.apache.poi.ss.formula.eval.StringEval
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x00a2
            org.apache.poi.ss.formula.eval.StringEval r6 = (org.apache.poi.ss.formula.eval.StringEval) r6
            java.lang.String r6 = r6.getStringValue()
            java.lang.String r0 = "<"
            boolean r0 = r6.startsWith(r0)
            java.lang.String r3 = "="
            if (r0 == 0) goto L_0x0032
            java.lang.String r6 = r6.substring(r2)
            boolean r0 = r6.startsWith(r3)
            if (r0 == 0) goto L_0x002b
            java.lang.String r6 = r6.substring(r2)
            org.apache.poi.ss.formula.functions.DStarRunner$operator r0 = org.apache.poi.ss.formula.functions.DStarRunner.operator.smallerEqualThan
            boolean r5 = testNumericCondition(r5, r0, r6)
            return r5
        L_0x002b:
            org.apache.poi.ss.formula.functions.DStarRunner$operator r0 = org.apache.poi.ss.formula.functions.DStarRunner.operator.smallerThan
            boolean r5 = testNumericCondition(r5, r0, r6)
            return r5
        L_0x0032:
            java.lang.String r0 = ">"
            boolean r0 = r6.startsWith(r0)
            if (r0 == 0) goto L_0x0056
            java.lang.String r6 = r6.substring(r2)
            boolean r0 = r6.startsWith(r3)
            if (r0 == 0) goto L_0x004f
            java.lang.String r6 = r6.substring(r2)
            org.apache.poi.ss.formula.functions.DStarRunner$operator r0 = org.apache.poi.ss.formula.functions.DStarRunner.operator.largerEqualThan
            boolean r5 = testNumericCondition(r5, r0, r6)
            return r5
        L_0x004f:
            org.apache.poi.ss.formula.functions.DStarRunner$operator r0 = org.apache.poi.ss.formula.functions.DStarRunner.operator.largerThan
            boolean r5 = testNumericCondition(r5, r0, r6)
            return r5
        L_0x0056:
            boolean r0 = r6.startsWith(r3)
            java.lang.String r3 = ""
            if (r0 == 0) goto L_0x008b
            java.lang.String r6 = r6.substring(r2)
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L_0x006b
            boolean r5 = r5 instanceof org.apache.poi.ss.formula.eval.BlankEval
            return r5
        L_0x006b:
            java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x0070 }
        L_0x006e:
            r1 = r2
            goto L_0x0074
        L_0x0070:
            java.lang.Double.parseDouble(r6)     // Catch:{ NumberFormatException -> 0x0074 }
            goto L_0x006e
        L_0x0074:
            if (r1 == 0) goto L_0x007d
            org.apache.poi.ss.formula.functions.DStarRunner$operator r0 = org.apache.poi.ss.formula.functions.DStarRunner.operator.equal
            boolean r5 = testNumericCondition(r5, r0, r6)
            return r5
        L_0x007d:
            boolean r0 = r5 instanceof org.apache.poi.ss.formula.eval.BlankEval
            if (r0 == 0) goto L_0x0082
            goto L_0x0086
        L_0x0082:
            java.lang.String r3 = org.apache.poi.ss.formula.eval.OperandResolver.coerceValueToString(r5)
        L_0x0086:
            boolean r5 = r6.equals(r3)
            return r5
        L_0x008b:
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L_0x0094
            boolean r5 = r5 instanceof org.apache.poi.ss.formula.eval.StringEval
            return r5
        L_0x0094:
            boolean r0 = r5 instanceof org.apache.poi.ss.formula.eval.BlankEval
            if (r0 == 0) goto L_0x0099
            goto L_0x009d
        L_0x0099:
            java.lang.String r3 = org.apache.poi.ss.formula.eval.OperandResolver.coerceValueToString(r5)
        L_0x009d:
            boolean r5 = r3.startsWith(r6)
            return r5
        L_0x00a2:
            boolean r0 = r6 instanceof org.apache.poi.ss.formula.eval.NumericValueEval
            if (r0 == 0) goto L_0x00bc
            org.apache.poi.ss.formula.eval.NumericValueEval r6 = (org.apache.poi.ss.formula.eval.NumericValueEval) r6
            double r3 = r6.getNumberValue()
            java.lang.Double r5 = getNumberFromValueEval(r5)
            if (r5 == 0) goto L_0x00bb
            double r5 = r5.doubleValue()
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x00bb
            r1 = r2
        L_0x00bb:
            return r1
        L_0x00bc:
            boolean r0 = r6 instanceof org.apache.poi.ss.formula.eval.ErrorEval
            if (r0 == 0) goto L_0x00d3
            boolean r0 = r5 instanceof org.apache.poi.ss.formula.eval.ErrorEval
            if (r0 == 0) goto L_0x00d3
            org.apache.poi.ss.formula.eval.ErrorEval r6 = (org.apache.poi.ss.formula.eval.ErrorEval) r6
            int r6 = r6.getErrorCode()
            org.apache.poi.ss.formula.eval.ErrorEval r5 = (org.apache.poi.ss.formula.eval.ErrorEval) r5
            int r5 = r5.getErrorCode()
            if (r6 != r5) goto L_0x00d3
            r1 = r2
        L_0x00d3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.DStarRunner.testNormalCondition(org.apache.poi.ss.formula.eval.ValueEval, org.apache.poi.ss.formula.eval.ValueEval):boolean");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:7|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004d, code lost:
        throw new org.apache.poi.ss.formula.eval.EvaluationException(org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r4 = java.lang.Double.parseDouble(r8);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean testNumericCondition(org.apache.poi.ss.formula.eval.ValueEval r6, org.apache.poi.ss.formula.functions.DStarRunner.operator r7, java.lang.String r8) throws org.apache.poi.ss.formula.eval.EvaluationException {
        /*
            boolean r0 = r6 instanceof org.apache.poi.ss.formula.eval.NumericValueEval
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            org.apache.poi.ss.formula.eval.NumericValueEval r6 = (org.apache.poi.ss.formula.eval.NumericValueEval) r6
            double r2 = r6.getNumberValue()
            int r6 = java.lang.Integer.parseInt(r8)     // Catch:{ NumberFormatException -> 0x0012 }
            double r4 = (double) r6
            goto L_0x0016
        L_0x0012:
            double r4 = java.lang.Double.parseDouble(r8)     // Catch:{ NumberFormatException -> 0x0046 }
        L_0x0016:
            int r6 = org.apache.poi.ss.util.NumberComparer.compare(r2, r4)
            int[] r8 = org.apache.poi.ss.formula.functions.DStarRunner.AnonymousClass1.$SwitchMap$org$apache$poi$ss$formula$functions$DStarRunner$operator
            int r7 = r7.ordinal()
            r7 = r8[r7]
            r8 = 1
            if (r7 == r8) goto L_0x0042
            r0 = 2
            if (r7 == r0) goto L_0x003e
            r0 = 3
            if (r7 == r0) goto L_0x003a
            r0 = 4
            if (r7 == r0) goto L_0x0036
            r0 = 5
            if (r7 == r0) goto L_0x0032
            return r1
        L_0x0032:
            if (r6 != 0) goto L_0x0035
            r1 = r8
        L_0x0035:
            return r1
        L_0x0036:
            if (r6 > 0) goto L_0x0039
            r1 = r8
        L_0x0039:
            return r1
        L_0x003a:
            if (r6 >= 0) goto L_0x003d
            r1 = r8
        L_0x003d:
            return r1
        L_0x003e:
            if (r6 < 0) goto L_0x0041
            r1 = r8
        L_0x0041:
            return r1
        L_0x0042:
            if (r6 <= 0) goto L_0x0045
            r1 = r8
        L_0x0045:
            return r1
        L_0x0046:
            org.apache.poi.ss.formula.eval.EvaluationException r6 = new org.apache.poi.ss.formula.eval.EvaluationException
            org.apache.poi.ss.formula.eval.ErrorEval r7 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.DStarRunner.testNumericCondition(org.apache.poi.ss.formula.eval.ValueEval, org.apache.poi.ss.formula.functions.DStarRunner$operator, java.lang.String):boolean");
    }

    /* renamed from: org.apache.poi.ss.formula.functions.DStarRunner$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$formula$functions$DStarRunner$operator;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ss.formula.functions.DStarRunner$operator[] r0 = org.apache.poi.ss.formula.functions.DStarRunner.operator.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$formula$functions$DStarRunner$operator = r0
                org.apache.poi.ss.formula.functions.DStarRunner$operator r1 = org.apache.poi.ss.formula.functions.DStarRunner.operator.largerThan     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$functions$DStarRunner$operator     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.formula.functions.DStarRunner$operator r1 = org.apache.poi.ss.formula.functions.DStarRunner.operator.largerEqualThan     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$functions$DStarRunner$operator     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.formula.functions.DStarRunner$operator r1 = org.apache.poi.ss.formula.functions.DStarRunner.operator.smallerThan     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$functions$DStarRunner$operator     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.formula.functions.DStarRunner$operator r1 = org.apache.poi.ss.formula.functions.DStarRunner.operator.smallerEqualThan     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$formula$functions$DStarRunner$operator     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.formula.functions.DStarRunner$operator r1 = org.apache.poi.ss.formula.functions.DStarRunner.operator.equal     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.DStarRunner.AnonymousClass1.<clinit>():void");
        }
    }

    private static Double getNumberFromValueEval(ValueEval valueEval) {
        if (valueEval instanceof NumericValueEval) {
            return Double.valueOf(((NumericValueEval) valueEval).getNumberValue());
        }
        if (valueEval instanceof StringValueEval) {
            try {
                return Double.valueOf(Double.parseDouble(((StringValueEval) valueEval).getStringValue()));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    private static ValueEval resolveReference(AreaEval areaEval, int i, int i2) {
        try {
            return OperandResolver.getSingleValue(areaEval.getValue(i, i2), areaEval.getFirstRow() + i, areaEval.getFirstColumn() + i2);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
