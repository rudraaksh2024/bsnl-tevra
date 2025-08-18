package org.apache.poi.ss.formula;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.formula.eval.ConcatEval;
import org.apache.poi.ss.formula.eval.FunctionEval;
import org.apache.poi.ss.formula.eval.IntersectionEval;
import org.apache.poi.ss.formula.eval.PercentEval;
import org.apache.poi.ss.formula.eval.RangeEval;
import org.apache.poi.ss.formula.eval.RelationalOperationEval;
import org.apache.poi.ss.formula.eval.TwoOperandNumericOperation;
import org.apache.poi.ss.formula.eval.UnaryMinusEval;
import org.apache.poi.ss.formula.eval.UnaryPlusEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.functions.Indirect;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.AddPtg;
import org.apache.poi.ss.formula.ptg.ConcatPtg;
import org.apache.poi.ss.formula.ptg.DividePtg;
import org.apache.poi.ss.formula.ptg.EqualPtg;
import org.apache.poi.ss.formula.ptg.GreaterEqualPtg;
import org.apache.poi.ss.formula.ptg.GreaterThanPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.LessEqualPtg;
import org.apache.poi.ss.formula.ptg.LessThanPtg;
import org.apache.poi.ss.formula.ptg.MultiplyPtg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.PowerPtg;
import org.apache.poi.ss.formula.ptg.RangePtg;
import org.apache.poi.ss.formula.ptg.SubtractPtg;
import org.apache.poi.ss.formula.ptg.UnaryMinusPtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.util.CellRangeAddress;

final class OperationEvaluatorFactory {
    private static final Map<Byte, Function> _instancesByPtgClass = initialiseInstancesMap();

    private OperationEvaluatorFactory() {
    }

    private static Map<Byte, Function> initialiseInstancesMap() {
        HashMap hashMap = new HashMap(32);
        hashMap.put(Byte.valueOf(AddPtg.instance.getSid()), TwoOperandNumericOperation.AddEval);
        hashMap.put(Byte.valueOf(SubtractPtg.instance.getSid()), TwoOperandNumericOperation.SubtractEval);
        hashMap.put(Byte.valueOf(MultiplyPtg.instance.getSid()), TwoOperandNumericOperation.MultiplyEval);
        hashMap.put(Byte.valueOf(DividePtg.instance.getSid()), TwoOperandNumericOperation.DivideEval);
        hashMap.put(Byte.valueOf(PowerPtg.instance.getSid()), TwoOperandNumericOperation.PowerEval);
        hashMap.put(Byte.valueOf(ConcatPtg.instance.getSid()), ConcatEval.instance);
        hashMap.put(Byte.valueOf(LessThanPtg.instance.getSid()), RelationalOperationEval.LessThanEval);
        hashMap.put(Byte.valueOf(LessEqualPtg.instance.getSid()), RelationalOperationEval.LessEqualEval);
        hashMap.put(Byte.valueOf(EqualPtg.instance.getSid()), RelationalOperationEval.EqualEval);
        hashMap.put(Byte.valueOf(GreaterEqualPtg.instance.getSid()), RelationalOperationEval.GreaterEqualEval);
        hashMap.put(Byte.valueOf(GreaterThanPtg.instance.getSid()), RelationalOperationEval.GreaterThanEval);
        hashMap.put(Byte.valueOf(NotEqualPtg.instance.getSid()), RelationalOperationEval.NotEqualEval);
        hashMap.put(Byte.valueOf(IntersectionPtg.instance.getSid()), IntersectionEval.instance);
        hashMap.put(Byte.valueOf(RangePtg.instance.getSid()), RangeEval.instance);
        hashMap.put(Byte.valueOf(UnaryPlusPtg.instance.getSid()), UnaryPlusEval.instance);
        hashMap.put(Byte.valueOf(UnaryMinusPtg.instance.getSid()), UnaryMinusEval.instance);
        hashMap.put(Byte.valueOf(PercentPtg.instance.getSid()), PercentEval.instance);
        return hashMap;
    }

    public static ValueEval evaluate(OperationPtg operationPtg, ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        ValueEval evaluateArrayFunction;
        if (operationPtg != null) {
            Function function = _instancesByPtgClass.get(Byte.valueOf(operationPtg.getSid()));
            FreeRefFunction freeRefFunction = null;
            if (function == null && (operationPtg instanceof AbstractFunctionPtg)) {
                short functionIndex = ((AbstractFunctionPtg) operationPtg).getFunctionIndex();
                if (functionIndex == 148) {
                    freeRefFunction = Indirect.instance;
                } else if (functionIndex != 255) {
                    function = FunctionEval.getBasicFunction(functionIndex);
                } else {
                    freeRefFunction = UserDefinedFunction.instance;
                }
            }
            if (function != null) {
                if (!(function instanceof ArrayFunction) || (evaluateArrayFunction = evaluateArrayFunction((ArrayFunction) function, valueEvalArr, operationEvaluationContext)) == null) {
                    return function.evaluate(valueEvalArr, operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
                }
                return evaluateArrayFunction;
            } else if (freeRefFunction != null) {
                return freeRefFunction.evaluate(valueEvalArr, operationEvaluationContext);
            } else {
                throw new RuntimeException("Unexpected operation ptg class (" + operationPtg.getClass().getName() + ")");
            }
        } else {
            throw new IllegalArgumentException("ptg must not be null");
        }
    }

    static ValueEval evaluateArrayFunction(ArrayFunction arrayFunction, ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        EvaluationCell cell = operationEvaluationContext.getWorkbook().getSheet(operationEvaluationContext.getSheetIndex()).getCell(operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
        if (cell == null) {
            return null;
        }
        if (cell.isPartOfArrayFormulaGroup()) {
            CellRangeAddress arrayFormulaRange = cell.getArrayFormulaRange();
            return arrayFunction.evaluateArray(valueEvalArr, arrayFormulaRange.getFirstRow(), arrayFormulaRange.getFirstColumn());
        } else if (operationEvaluationContext.isArraymode()) {
            return arrayFunction.evaluateArray(valueEvalArr, operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
        } else {
            return null;
        }
    }
}
