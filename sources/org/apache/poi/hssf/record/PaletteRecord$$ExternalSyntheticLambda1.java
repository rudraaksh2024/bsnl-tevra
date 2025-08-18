package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.function.Consumer;
import org.apache.poi.hssf.record.PaletteRecord;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PaletteRecord$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ ArrayList f$0;

    public /* synthetic */ PaletteRecord$$ExternalSyntheticLambda1(ArrayList arrayList) {
        this.f$0 = arrayList;
    }

    public final void accept(Object obj) {
        boolean unused = this.f$0.add((PaletteRecord.PColor) obj);
    }
}
