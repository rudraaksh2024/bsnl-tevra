package org.apache.poi.xwpf.usermodel;

import java.util.function.Predicate;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XWPFParagraph$$ExternalSyntheticLambda4 implements Predicate {
    public final /* synthetic */ CTHyperlink f$0;

    public /* synthetic */ XWPFParagraph$$ExternalSyntheticLambda4(CTHyperlink cTHyperlink) {
        this.f$0 = cTHyperlink;
    }

    public final boolean test(Object obj) {
        return XWPFParagraph.lambda$isTheOnlyCTHyperlinkInRuns$3(this.f$0, (XWPFRun) obj);
    }
}
