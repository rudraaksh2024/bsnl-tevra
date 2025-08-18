package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;
import org.apache.poi.hssf.record.chart.ChartTitleFormatRecord;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ChartTitleFormatRecord$CTFormat$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ ChartTitleFormatRecord.CTFormat f$0;

    public /* synthetic */ ChartTitleFormatRecord$CTFormat$$ExternalSyntheticLambda1(ChartTitleFormatRecord.CTFormat cTFormat) {
        this.f$0 = cTFormat;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getFontIndex());
    }
}
