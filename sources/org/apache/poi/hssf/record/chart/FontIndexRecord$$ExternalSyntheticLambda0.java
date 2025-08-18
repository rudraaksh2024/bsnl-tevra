package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FontIndexRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ FontIndexRecord f$0;

    public /* synthetic */ FontIndexRecord$$ExternalSyntheticLambda0(FontIndexRecord fontIndexRecord) {
        this.f$0 = fontIndexRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getFontIndex());
    }
}
