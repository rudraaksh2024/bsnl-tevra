package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.eval.FunctionNameEval;
import org.apache.poi.ss.formula.eval.NotImplementedFunctionException;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

final class UserDefinedFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new UserDefinedFunction();

    private UserDefinedFunction() {
    }

    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        ValueEval evaluateArrayFunction;
        int length = valueEvalArr.length;
        if (length >= 1) {
            FunctionNameEval functionNameEval = valueEvalArr[0];
            if (functionNameEval instanceof FunctionNameEval) {
                String functionName = functionNameEval.getFunctionName();
                FreeRefFunction findUserDefinedFunction = operationEvaluationContext.findUserDefinedFunction(functionName);
                if (findUserDefinedFunction != null) {
                    int i = length - 1;
                    ValueEval[] valueEvalArr2 = new ValueEval[i];
                    System.arraycopy(valueEvalArr, 1, valueEvalArr2, 0, i);
                    if (!(findUserDefinedFunction instanceof ArrayFunction) || (evaluateArrayFunction = OperationEvaluatorFactory.evaluateArrayFunction((ArrayFunction) findUserDefinedFunction, valueEvalArr2, operationEvaluationContext)) == null) {
                        return findUserDefinedFunction.evaluate(valueEvalArr2, operationEvaluationContext);
                    }
                    return evaluateArrayFunction;
                }
                throw new NotImplementedFunctionException(functionName);
            }
            throw new RuntimeException("First argument should be a NameEval, but got (" + functionNameEval.getClass().getName() + ")");
        }
        throw new RuntimeException("function name argument missing");
    }
}
