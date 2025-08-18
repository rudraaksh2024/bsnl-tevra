package org.apache.poi.xddf.usermodel.text;

import java.util.function.Consumer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XDDFRunProperties$$ExternalSyntheticLambda38 implements Consumer {
    public final /* synthetic */ CTTextCharacterProperties f$0;

    public /* synthetic */ XDDFRunProperties$$ExternalSyntheticLambda38(CTTextCharacterProperties cTTextCharacterProperties) {
        this.f$0 = cTTextCharacterProperties;
    }

    public final void accept(Object obj) {
        this.f$0.setU((STTextUnderlineType.Enum) obj);
    }
}
