package org.apache.poi.hssf.record.aggregates;

import java.util.Map;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PageSettingsBlock$$ExternalSyntheticLambda0 implements RecordAggregate.RecordVisitor {
    public final /* synthetic */ PageSettingsBlock f$0;
    public final /* synthetic */ Map f$1;
    public final /* synthetic */ CustomViewSettingsRecordAggregate f$2;

    public /* synthetic */ PageSettingsBlock$$ExternalSyntheticLambda0(PageSettingsBlock pageSettingsBlock, Map map, CustomViewSettingsRecordAggregate customViewSettingsRecordAggregate) {
        this.f$0 = pageSettingsBlock;
        this.f$1 = map;
        this.f$2 = customViewSettingsRecordAggregate;
    }

    public final void visitRecord(Record record) {
        this.f$0.m2118lambda$positionRecords$0$orgapachepoihssfrecordaggregatesPageSettingsBlock(this.f$1, this.f$2, record);
    }
}
