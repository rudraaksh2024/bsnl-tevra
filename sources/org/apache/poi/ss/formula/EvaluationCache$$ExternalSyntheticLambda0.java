package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.FormulaCellCache;
import org.apache.poi.ss.formula.FormulaUsedBlankCellSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EvaluationCache$$ExternalSyntheticLambda0 implements FormulaCellCache.IEntryOperation {
    public final /* synthetic */ EvaluationCache f$0;
    public final /* synthetic */ FormulaUsedBlankCellSet.BookSheetKey f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ EvaluationCache$$ExternalSyntheticLambda0(EvaluationCache evaluationCache, FormulaUsedBlankCellSet.BookSheetKey bookSheetKey, int i, int i2) {
        this.f$0 = evaluationCache;
        this.f$1 = bookSheetKey;
        this.f$2 = i;
        this.f$3 = i2;
    }

    public final void processEntry(FormulaCellCacheEntry formulaCellCacheEntry) {
        this.f$0.m2241lambda$updateAnyBlankReferencingFormulas$0$orgapachepoissformulaEvaluationCache(this.f$1, this.f$2, this.f$3, formulaCellCacheEntry);
    }
}
