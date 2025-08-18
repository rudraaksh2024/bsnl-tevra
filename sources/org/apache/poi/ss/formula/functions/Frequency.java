package org.apache.poi.ss.formula.functions;

import java.util.Arrays;
import org.apache.poi.ss.formula.CacheAreaEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.MatrixFunction;

public class Frequency extends Fixed2ArgFunction {
    public static final Function instance = new Frequency();

    private Frequency() {
    }

    public ValueEval evaluate(int i, int i2, ValueEval valueEval, ValueEval valueEval2) {
        MatrixFunction.MutableValueCollector mutableValueCollector = new MatrixFunction.MutableValueCollector(false, false);
        try {
            NumberEval[] numberEvalArr = (NumberEval[]) Arrays.stream(histogram(mutableValueCollector.collectValues(valueEval), mutableValueCollector.collectValues(valueEval2))).boxed().map(new Frequency$$ExternalSyntheticLambda0()).toArray(new Frequency$$ExternalSyntheticLambda1());
            return new CacheAreaEval(i, i2, (numberEvalArr.length + i) - 1, i2, numberEvalArr);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    static /* synthetic */ NumberEval[] lambda$evaluate$0(int i) {
        return new NumberEval[i];
    }

    static int findBin(double d, double[] dArr) {
        int binarySearch = Arrays.binarySearch(dArr, d);
        return binarySearch >= 0 ? binarySearch + 1 : -binarySearch;
    }

    static int[] histogram(double[] dArr, double[] dArr2) {
        int[] iArr = new int[(dArr2.length + 1)];
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            int findBin = findBin(dArr[i], dArr2) - 1;
            iArr[findBin] = iArr[findBin] + 1;
        }
        return iArr;
    }
}
