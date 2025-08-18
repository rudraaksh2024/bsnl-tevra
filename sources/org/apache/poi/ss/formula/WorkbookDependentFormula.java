package org.apache.poi.ss.formula;

import org.apache.poi.util.Internal;

@Internal
public interface WorkbookDependentFormula {
    String toFormulaString(FormulaRenderingWorkbook formulaRenderingWorkbook);
}
