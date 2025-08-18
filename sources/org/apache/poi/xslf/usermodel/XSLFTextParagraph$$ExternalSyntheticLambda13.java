package org.apache.poi.xslf.usermodel;

import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.poi.xslf.model.ParagraphPropertyFetcher;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSLFTextParagraph$$ExternalSyntheticLambda13 implements ParagraphPropertyFetcher.ParaPropFetcher {
    public final /* synthetic */ Function f$0;

    public /* synthetic */ XSLFTextParagraph$$ExternalSyntheticLambda13(Function function) {
        this.f$0 = function;
    }

    public final void fetch(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        XSLFTextParagraph.fetchSpacing(this.f$0, cTTextParagraphProperties, consumer);
    }
}
