package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class GroupMarkerSubRecord extends SubRecord {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final short sid = 6;
    private byte[] reserved;

    public short getSid() {
        return 6;
    }

    public GroupMarkerSubRecord() {
        this.reserved = EMPTY_BYTE_ARRAY;
    }

    public GroupMarkerSubRecord(GroupMarkerSubRecord groupMarkerSubRecord) {
        super(groupMarkerSubRecord);
        this.reserved = (byte[]) groupMarkerSubRecord.reserved.clone();
    }

    public GroupMarkerSubRecord(LittleEndianInput littleEndianInput, int i) {
        this(littleEndianInput, i, -1);
    }

    GroupMarkerSubRecord(LittleEndianInput littleEndianInput, int i, int i2) {
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) i, HSSFWorkbook.getMaxRecordLength());
        littleEndianInput.readFully(safelyAllocate);
        this.reserved = safelyAllocate;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(6);
        littleEndianOutput.writeShort(this.reserved.length);
        littleEndianOutput.write(this.reserved);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this.reserved.length;
    }

    public GroupMarkerSubRecord copy() {
        return new GroupMarkerSubRecord(this);
    }

    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.GROUP_MARKER;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new GroupMarkerSubRecord$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-GroupMarkerSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2029lambda$getGenericProperties$0$orgapachepoihssfrecordGroupMarkerSubRecord() {
        return this.reserved;
    }
}
