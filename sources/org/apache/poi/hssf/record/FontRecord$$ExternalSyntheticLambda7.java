package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FontRecord$$ExternalSyntheticLambda7 implements Supplier {
    public final /* synthetic */ FontRecord f$0;

    public /* synthetic */ FontRecord$$ExternalSyntheticLambda7(FontRecord fontRecord) {
        this.f$0 = fontRecord;
    }

    public final Object get() {
        return Byte.valueOf(this.f$0.getCharset());
    }
}
