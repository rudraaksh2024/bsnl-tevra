package org.apache.poi.ss.util;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CellReference$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ CellReference f$0;

    public /* synthetic */ CellReference$$ExternalSyntheticLambda2(CellReference cellReference) {
        this.f$0 = cellReference;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getCol());
    }
}
