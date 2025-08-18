package org.apache.poi.hssf.record.aggregates;

import java.util.function.Consumer;
import org.apache.poi.hssf.record.DVRecord;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DataValidityTable$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ RecordAggregate.RecordVisitor f$0;

    public /* synthetic */ DataValidityTable$$ExternalSyntheticLambda0(RecordAggregate.RecordVisitor recordVisitor) {
        this.f$0 = recordVisitor;
    }

    public final void accept(Object obj) {
        this.f$0.visitRecord((DVRecord) obj);
    }
}
