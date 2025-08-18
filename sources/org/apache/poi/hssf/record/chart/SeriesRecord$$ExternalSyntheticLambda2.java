package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SeriesRecord$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ SeriesRecord f$0;

    public /* synthetic */ SeriesRecord$$ExternalSyntheticLambda2(SeriesRecord seriesRecord) {
        this.f$0 = seriesRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getNumCategories());
    }
}
