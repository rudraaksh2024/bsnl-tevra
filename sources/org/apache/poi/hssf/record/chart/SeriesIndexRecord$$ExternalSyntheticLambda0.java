package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SeriesIndexRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ SeriesIndexRecord f$0;

    public /* synthetic */ SeriesIndexRecord$$ExternalSyntheticLambda0(SeriesIndexRecord seriesIndexRecord) {
        this.f$0 = seriesIndexRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getIndex());
    }
}
