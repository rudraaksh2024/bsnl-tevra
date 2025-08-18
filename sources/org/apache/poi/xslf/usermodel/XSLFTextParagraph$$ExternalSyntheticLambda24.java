package org.apache.poi.xslf.usermodel;

import java.util.function.Consumer;
import org.apache.poi.xslf.model.ParagraphPropertyFetcher;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSLFTextParagraph$$ExternalSyntheticLambda24 implements ParagraphPropertyFetcher.ParaPropFetcher {
    public final /* synthetic */ XSLFTextParagraph f$0;

    public /* synthetic */ XSLFTextParagraph$$ExternalSyntheticLambda24(XSLFTextParagraph xSLFTextParagraph) {
        this.f$0 = xSLFTextParagraph;
    }

    public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        this.f$0.fetchBulletFontColor(cTTextParagraphProperties, consumer);
    }
}
