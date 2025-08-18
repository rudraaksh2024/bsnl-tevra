package org.apache.poi.xddf.usermodel.text;

import java.util.function.Consumer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XDDFRunProperties$$ExternalSyntheticLambda76 implements Consumer {
    public final /* synthetic */ CTTextCharacterProperties f$0;

    public /* synthetic */ XDDFRunProperties$$ExternalSyntheticLambda76(CTTextCharacterProperties cTTextCharacterProperties) {
        this.f$0 = cTTextCharacterProperties;
    }

    public final void accept(Object obj) {
        this.f$0.setEa((CTTextFont) obj);
    }
}
