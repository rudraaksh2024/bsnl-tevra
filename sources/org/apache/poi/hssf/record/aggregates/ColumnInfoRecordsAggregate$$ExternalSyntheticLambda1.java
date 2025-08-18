package org.apache.poi.hssf.record.aggregates;

import java.util.List;
import java.util.function.Consumer;
import org.apache.poi.hssf.record.ColumnInfoRecord;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ColumnInfoRecordsAggregate$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ List f$0;

    public /* synthetic */ ColumnInfoRecordsAggregate$$ExternalSyntheticLambda1(List list) {
        this.f$0 = list;
    }

    public final void accept(Object obj) {
        this.f$0.add((ColumnInfoRecord) obj);
    }
}
