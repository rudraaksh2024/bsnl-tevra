package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FontRecord$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ FontRecord f$0;

    public /* synthetic */ FontRecord$$ExternalSyntheticLambda2(FontRecord fontRecord) {
        this.f$0 = fontRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getColorPaletteIndex());
    }
}
