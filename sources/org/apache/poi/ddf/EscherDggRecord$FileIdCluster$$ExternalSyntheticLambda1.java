package org.apache.poi.ddf;

import java.util.function.Supplier;
import org.apache.poi.ddf.EscherDggRecord;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EscherDggRecord$FileIdCluster$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ EscherDggRecord.FileIdCluster f$0;

    public /* synthetic */ EscherDggRecord$FileIdCluster$$ExternalSyntheticLambda1(EscherDggRecord.FileIdCluster fileIdCluster) {
        this.f$0 = fileIdCluster;
    }

    public final Object get() {
        return Integer.valueOf(this.f$0.getNumShapeIdsUsed());
    }
}
