package org.apache.poi.hssf.record;

import java.util.function.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BackupRecord$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ BackupRecord f$0;

    public /* synthetic */ BackupRecord$$ExternalSyntheticLambda0(BackupRecord backupRecord) {
        this.f$0 = backupRecord;
    }

    public final Object get() {
        return Short.valueOf(this.f$0.getBackup());
    }
}
