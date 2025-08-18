package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SeriesChartGroupIndexRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ SeriesChartGroupIndexRecord f$0;

    public /* synthetic */ SeriesChartGroupIndexRecord$$ExternalSyntheticLambda0(SeriesChartGroupIndexRecord seriesChartGroupIndexRecord) {
        this.f$0 = seriesChartGroupIndexRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getChartGroupIndex());
    }
}
