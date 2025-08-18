package org.apache.poi.ss.util;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CellRangeAddressBase$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ CellRangeAddressBase f$0;

    public /* synthetic */ CellRangeAddressBase$$ExternalSyntheticLambda3(CellRangeAddressBase cellRangeAddressBase) {
        this.f$0 = cellRangeAddressBase;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getLastColumn());
    }
}
