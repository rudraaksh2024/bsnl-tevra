package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class BackupRecord extends StandardRecord {
    public static final short sid = 64;
    private short field_1_backup;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 64;
    }

    public BackupRecord() {
    }

    public BackupRecord(BackupRecord backupRecord) {
        super(backupRecord);
        this.field_1_backup = backupRecord.field_1_backup;
    }

    public BackupRecord(RecordInputStream recordInputStream) {
        this.field_1_backup = recordInputStream.readShort();
    }

    public void setBackup(short s) {
        this.field_1_backup = s;
    }

    public short getBackup() {
        return this.field_1_backup;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getBackup());
    }

    public BackupRecord copy() {
        return new BackupRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BACKUP;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("backup", new BackupRecord$$ExternalSyntheticLambda0(this));
    }
}
