package org.apache.poi.hssf.record;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.NullEscherSerializationListener;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

public final class DrawingGroupRecord extends AbstractEscherHolderRecord {
    private static final int DEFAULT_MAX_RECORD_SIZE = 8228;
    private static int MAX_RECORD_SIZE = 8228;
    public static final short sid = 235;

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getRecordName() {
        return "MSODRAWINGGROUP";
    }

    public short getSid() {
        return sid;
    }

    public static void setMaxRecordSize(int i) {
        MAX_RECORD_SIZE = i;
    }

    public static int getMaxRecordSize() {
        return MAX_RECORD_SIZE;
    }

    private static int getMaxDataSize() {
        return MAX_RECORD_SIZE - 4;
    }

    public DrawingGroupRecord() {
    }

    public DrawingGroupRecord(DrawingGroupRecord drawingGroupRecord) {
        super((AbstractEscherHolderRecord) drawingGroupRecord);
    }

    public DrawingGroupRecord(RecordInputStream recordInputStream) {
        super(recordInputStream);
    }

    public int serialize(int i, byte[] bArr) {
        byte[] rawData = getRawData();
        if (getEscherRecords().isEmpty() && rawData != null) {
            return writeData(i, bArr, rawData);
        }
        byte[] bArr2 = new byte[getRawDataSize()];
        int i2 = 0;
        for (EscherRecord serialize : getEscherRecords()) {
            i2 += serialize.serialize(i2, bArr2, new NullEscherSerializationListener());
        }
        return writeData(i, bArr, bArr2);
    }

    @Deprecated
    @Removal(version = "5.3")
    public void processChildRecords() {
        decode();
    }

    public int getRecordSize() {
        return grossSizeFromDataSize(getRawDataSize());
    }

    private int getRawDataSize() {
        List<EscherRecord> escherRecords = getEscherRecords();
        byte[] rawData = getRawData();
        if (escherRecords.isEmpty() && rawData != null) {
            return rawData.length;
        }
        int i = 0;
        for (EscherRecord recordSize : escherRecords) {
            i += recordSize.getRecordSize();
        }
        return i;
    }

    static int grossSizeFromDataSize(int i) {
        return i + ((((i - 1) / getMaxDataSize()) + 1) * 4);
    }

    private int writeData(int i, byte[] bArr, byte[] bArr2) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr2.length) {
            int maxDataSize = getMaxDataSize();
            int min = Math.min(bArr2.length - i2, maxDataSize);
            if (i2 / maxDataSize >= 2) {
                writeContinueHeader(bArr, i, min);
            } else {
                writeHeader(bArr, i, min);
            }
            int i4 = i + 4;
            System.arraycopy(bArr2, i2, bArr, i4, min);
            i = i4 + min;
            i2 += min;
            i3 = i3 + 4 + min;
        }
        return i3;
    }

    private void writeHeader(byte[] bArr, int i, int i2) {
        LittleEndian.putShort(bArr, i, getSid());
        LittleEndian.putShort(bArr, i + 2, (short) i2);
    }

    private void writeContinueHeader(byte[] bArr, int i, int i2) {
        LittleEndian.putShort(bArr, i, 60);
        LittleEndian.putShort(bArr, i + 2, (short) i2);
    }

    public DrawingGroupRecord copy() {
        return new DrawingGroupRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DRAWING_GROUP;
    }
}
