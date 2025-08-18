package org.apache.poi.xwpf.usermodel;

import java.util.function.Predicate;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XWPFParagraph$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ CTSimpleField f$0;

    public /* synthetic */ XWPFParagraph$$ExternalSyntheticLambda1(CTSimpleField cTSimpleField) {
        this.f$0 = cTSimpleField;
    }

    public final boolean test(Object obj) {
        return XWPFParagraph.lambda$isTheOnlyCTFieldInRuns$4(this.f$0, (XWPFRun) obj);
    }
}
