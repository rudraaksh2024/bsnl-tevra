package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FontBasisRecord$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ FontBasisRecord f$0;

    public /* synthetic */ FontBasisRecord$$ExternalSyntheticLambda3(FontBasisRecord fontBasisRecord) {
        this.f$0 = fontBasisRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getScale());
    }
}
