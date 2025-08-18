package org.apache.poi.xslf.usermodel;

import java.util.function.Consumer;
import org.apache.poi.xslf.model.CharacterPropertyFetcher;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSLFTextRun$$ExternalSyntheticLambda5 implements CharacterPropertyFetcher.CharPropFetcher {
    public final /* synthetic */ XSLFShape f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ XSLFTextRun$$ExternalSyntheticLambda5(XSLFShape xSLFShape, boolean z) {
        this.f$0 = xSLFShape;
        this.f$1 = z;
    }

    public final void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        XSLFTextRun.fetchFontColor(cTTextCharacterProperties, consumer, this.f$0, this.f$1);
    }
}
