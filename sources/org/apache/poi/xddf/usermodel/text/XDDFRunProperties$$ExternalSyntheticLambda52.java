package org.apache.poi.xddf.usermodel.text;

import java.util.function.Supplier;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XDDFRunProperties$$ExternalSyntheticLambda52 implements Supplier {
    public final /* synthetic */ CTTextCharacterProperties f$0;

    public /* synthetic */ XDDFRunProperties$$ExternalSyntheticLambda52(CTTextCharacterProperties cTTextCharacterProperties) {
        this.f$0 = cTTextCharacterProperties;
    }

    public final Object get() {
        return Boolean.valueOf(this.f$0.isSetBaseline());
    }
}
