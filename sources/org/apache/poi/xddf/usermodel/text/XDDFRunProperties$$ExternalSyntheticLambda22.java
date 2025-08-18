package org.apache.poi.xddf.usermodel.text;

import java.util.function.Consumer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XDDFRunProperties$$ExternalSyntheticLambda22 implements Consumer {
    public final /* synthetic */ CTTextCharacterProperties f$0;

    public /* synthetic */ XDDFRunProperties$$ExternalSyntheticLambda22(CTTextCharacterProperties cTTextCharacterProperties) {
        this.f$0 = cTTextCharacterProperties;
    }

    public final void accept(Object obj) {
        this.f$0.setHighlight((CTColor) obj);
    }
}
