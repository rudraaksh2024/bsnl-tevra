package org.apache.poi.hssf.record;

import java.util.function.Supplier;
import org.apache.poi.hssf.record.ExtSSTRecord;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtSSTRecord$InfoSubRecord$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ ExtSSTRecord.InfoSubRecord f$0;

    public /* synthetic */ ExtSSTRecord$InfoSubRecord$$ExternalSyntheticLambda1(ExtSSTRecord.InfoSubRecord infoSubRecord) {
        this.f$0 = infoSubRecord;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getBucketSSTOffset());
    }
}
