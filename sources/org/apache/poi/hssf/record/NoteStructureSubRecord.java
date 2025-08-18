package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class NoteStructureSubRecord extends SubRecord {
    private static final int ENCODED_SIZE = 22;
    public static final short sid = 13;
    private final byte[] reserved;

    public short getSid() {
        return 13;
    }

    public NoteStructureSubRecord() {
        this.reserved = new byte[22];
    }

    public NoteStructureSubRecord(NoteStructureSubRecord noteStructureSubRecord) {
        super(noteStructureSubRecord);
        this.reserved = (byte[]) noteStructureSubRecord.reserved.clone();
    }

    public NoteStructureSubRecord(LittleEndianInput littleEndianInput, int i) {
        this(littleEndianInput, i, -1);
    }

    public NoteStructureSubRecord(LittleEndianInput littleEndianInput, int i, int i2) {
        if (i == 22) {
            byte[] safelyAllocate = IOUtils.safelyAllocate((long) i, 22);
            littleEndianInput.readFully(safelyAllocate);
            this.reserved = safelyAllocate;
            return;
        }
        throw new RecordFormatException("Unexpected size (" + i + ")");
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(13);
        littleEndianOutput.writeShort(this.reserved.length);
        littleEndianOutput.write(this.reserved);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this.reserved.length;
    }

    public NoteStructureSubRecord copy() {
        return new NoteStructureSubRecord(this);
    }

    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.NOTE_STRUCTURE;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new NoteStructureSubRecord$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-NoteStructureSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2060lambda$getGenericProperties$0$orgapachepoihssfrecordNoteStructureSubRecord() {
        return this.reserved;
    }
}
