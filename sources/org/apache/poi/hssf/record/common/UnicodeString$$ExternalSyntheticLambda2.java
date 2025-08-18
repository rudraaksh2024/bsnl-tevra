package org.apache.poi.hssf.record.common;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UnicodeString$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ UnicodeString f$0;

    public /* synthetic */ UnicodeString$$ExternalSyntheticLambda2(UnicodeString unicodeString) {
        this.f$0 = unicodeString;
    }

    public final Object get() {
        return Byte.valueOf(this.f$0.getOptionFlags());
    }
}
