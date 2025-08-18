package org.apache.poi.util;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class GenericRecordUtil$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ Supplier f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ String[] f$2;

    public /* synthetic */ GenericRecordUtil$$ExternalSyntheticLambda4(Supplier supplier, int[] iArr, String[] strArr) {
        this.f$0 = supplier;
        this.f$1 = iArr;
        this.f$2 = strArr;
    }

    public final Object get() {
        return GenericRecordUtil.lambda$getBitsAsString$2(this.f$0, this.f$1, this.f$2);
    }
}
