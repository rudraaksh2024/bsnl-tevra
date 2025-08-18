package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DataFormatRecord$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ DataFormatRecord f$0;

    public /* synthetic */ DataFormatRecord$$ExternalSyntheticLambda3(DataFormatRecord dataFormatRecord) {
        this.f$0 = dataFormatRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getFormatFlags());
    }
}
