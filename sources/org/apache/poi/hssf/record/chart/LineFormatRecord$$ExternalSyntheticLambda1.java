package org.apache.poi.hssf.record.chart;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LineFormatRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ LineFormatRecord f$0;

    public /* synthetic */ LineFormatRecord$$ExternalSyntheticLambda1(LineFormatRecord lineFormatRecord) {
        this.f$0 = lineFormatRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getLinePattern());
    }
}
