package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;

public final class DrawingRecordForBiffViewer extends AbstractEscherHolderRecord {
    public static final short sid = 236;

    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getRecordName() {
        return "MSODRAWING";
    }

    public short getSid() {
        return 236;
    }

    public DrawingRecordForBiffViewer() {
    }

    public DrawingRecordForBiffViewer(DrawingRecordForBiffViewer drawingRecordForBiffViewer) {
        super((AbstractEscherHolderRecord) drawingRecordForBiffViewer);
    }

    public DrawingRecordForBiffViewer(RecordInputStream recordInputStream) {
        super(recordInputStream);
    }

    public DrawingRecordForBiffViewer(DrawingRecord drawingRecord) {
        super(convertToInputStream(drawingRecord));
        decode();
    }

    private static RecordInputStream convertToInputStream(DrawingRecord drawingRecord) {
        RecordInputStream recordInputStream = new RecordInputStream(new UnsynchronizedByteArrayInputStream(drawingRecord.serialize()));
        recordInputStream.nextRecord();
        return recordInputStream;
    }

    public DrawingRecordForBiffViewer copy() {
        return new DrawingRecordForBiffViewer(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DRAWING;
    }
}
