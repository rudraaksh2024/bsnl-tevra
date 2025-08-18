package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class FtCblsSubRecord extends SubRecord {
    private static final int ENCODED_SIZE = 20;
    public static final short sid = 12;
    private final byte[] reserved;

    public short getSid() {
        return 12;
    }

    public FtCblsSubRecord() {
        this.reserved = new byte[20];
    }

    public FtCblsSubRecord(FtCblsSubRecord ftCblsSubRecord) {
        super(ftCblsSubRecord);
        this.reserved = (byte[]) ftCblsSubRecord.reserved.clone();
    }

    public FtCblsSubRecord(LittleEndianInput littleEndianInput, int i) {
        this(littleEndianInput, i, -1);
    }

    FtCblsSubRecord(LittleEndianInput littleEndianInput, int i, int i2) {
        if (i == 20) {
            byte[] safelyAllocate = IOUtils.safelyAllocate((long) i, 20);
            littleEndianInput.readFully(safelyAllocate);
            this.reserved = safelyAllocate;
            return;
        }
        throw new RecordFormatException("Unexpected size (" + i + ")");
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(12);
        littleEndianOutput.writeShort(this.reserved.length);
        littleEndianOutput.write(this.reserved);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this.reserved.length;
    }

    public FtCblsSubRecord copy() {
        return new FtCblsSubRecord(this);
    }

    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.FT_CBLS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new FtCblsSubRecord$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-FtCblsSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2028lambda$getGenericProperties$0$orgapachepoihssfrecordFtCblsSubRecord() {
        return this.reserved;
    }
}
