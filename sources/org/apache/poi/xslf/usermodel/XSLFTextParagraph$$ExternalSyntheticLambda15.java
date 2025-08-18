package org.apache.poi.xslf.usermodel;

import java.util.function.Consumer;
import org.apache.poi.xslf.model.ParagraphPropertyFetcher;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSLFTextParagraph$$ExternalSyntheticLambda15 implements ParagraphPropertyFetcher.ParaPropFetcher {
    public final /* synthetic */ int f$0;

    public /* synthetic */ XSLFTextParagraph$$ExternalSyntheticLambda15(int i) {
        this.f$0 = i;
    }

    public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        XSLFTextParagraph.fetchTabStop(this.f$0, cTTextParagraphProperties, consumer);
    }
}
