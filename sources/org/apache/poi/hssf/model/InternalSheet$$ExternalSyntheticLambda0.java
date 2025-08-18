package org.apache.poi.hssf.model;

import java.util.List;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class InternalSheet$$ExternalSyntheticLambda0 implements RecordAggregate.RecordVisitor {
    public final /* synthetic */ List f$0;

    public /* synthetic */ InternalSheet$$ExternalSyntheticLambda0(List list) {
        this.f$0 = list;
    }

    public final void visitRecord(Record record) {
        this.f$0.add(record);
    }
}
