package org.apache.poi.ss.usermodel.helpers;

import java.util.List;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;

@Internal
public abstract class BaseRowColShifter {
    public abstract List<CellRangeAddress> shiftMergedRegions(int i, int i2, int i3);

    public abstract void updateConditionalFormatting(FormulaShifter formulaShifter);

    public abstract void updateFormulas(FormulaShifter formulaShifter);

    public abstract void updateHyperlinks(FormulaShifter formulaShifter);

    public abstract void updateNamedRanges(FormulaShifter formulaShifter);

    public static CellRangeAddress shiftRange(FormulaShifter formulaShifter, CellRangeAddress cellRangeAddress, int i) {
        Ptg[] ptgArr = {new AreaPtg(cellRangeAddress.getFirstRow(), cellRangeAddress.getLastRow(), cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn(), false, false, false, false)};
        if (!formulaShifter.adjustFormula(ptgArr, i)) {
            return cellRangeAddress;
        }
        Ptg ptg = ptgArr[0];
        if (ptg instanceof AreaPtg) {
            AreaPtg areaPtg = (AreaPtg) ptg;
            return new CellRangeAddress(areaPtg.getFirstRow(), areaPtg.getLastRow(), areaPtg.getFirstColumn(), areaPtg.getLastColumn());
        } else if (ptg instanceof AreaErrPtg) {
            return null;
        } else {
            throw new IllegalStateException("Unexpected shifted ptg class (" + ptg.getClass().getName() + ")");
        }
    }
}
