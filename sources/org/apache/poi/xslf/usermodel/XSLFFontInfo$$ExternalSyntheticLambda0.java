package org.apache.poi.xslf.usermodel;

import java.util.function.Function;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSLFFontInfo$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ XMLSlideShow f$0;

    public /* synthetic */ XSLFFontInfo$$ExternalSyntheticLambda0(XMLSlideShow xMLSlideShow) {
        this.f$0 = xMLSlideShow;
    }

    public final Object apply(Object obj) {
        return XSLFFontInfo.lambda$getFonts$0(this.f$0, (CTEmbeddedFontListEntry) obj);
    }
}
