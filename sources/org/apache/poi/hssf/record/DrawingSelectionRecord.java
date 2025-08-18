package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class DrawingSelectionRecord extends StandardRecord {
    public static final short sid = 237;
    private int _cpsp;
    private int _dgslk;
    private OfficeArtRecordHeader _header;
    private int[] _shapeIds;
    private int _spidFocus;

    public DrawingSelectionRecord copy() {
        return this;
    }

    public short getSid() {
        return sid;
    }

    private static final class OfficeArtRecordHeader implements GenericRecord {
        public static final int ENCODED_SIZE = 8;
        private final int _length;
        private final int _type;
        private final int _verAndInstance;

        public OfficeArtRecordHeader(LittleEndianInput littleEndianInput) {
            this._verAndInstance = littleEndianInput.readUShort();
            this._type = littleEndianInput.readUShort();
            this._length = littleEndianInput.readInt();
        }

        public void serialize(LittleEndianOutput littleEndianOutput) {
            littleEndianOutput.writeShort(this._verAndInstance);
            littleEndianOutput.writeShort(this._type);
            littleEndianOutput.writeInt(this._length);
        }

        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("verAndInstance", new DrawingSelectionRecord$OfficeArtRecordHeader$$ExternalSyntheticLambda0(this), "type", new DrawingSelectionRecord$OfficeArtRecordHeader$$ExternalSyntheticLambda1(this), "length", new DrawingSelectionRecord$OfficeArtRecordHeader$$ExternalSyntheticLambda2(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DrawingSelectionRecord$OfficeArtRecordHeader  reason: not valid java name */
        public /* synthetic */ Object m2001lambda$getGenericProperties$0$orgapachepoihssfrecordDrawingSelectionRecord$OfficeArtRecordHeader() {
            return Integer.valueOf(this._verAndInstance);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-DrawingSelectionRecord$OfficeArtRecordHeader  reason: not valid java name */
        public /* synthetic */ Object m2002lambda$getGenericProperties$1$orgapachepoihssfrecordDrawingSelectionRecord$OfficeArtRecordHeader() {
            return Integer.valueOf(this._type);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-DrawingSelectionRecord$OfficeArtRecordHeader  reason: not valid java name */
        public /* synthetic */ Object m2003lambda$getGenericProperties$2$orgapachepoihssfrecordDrawingSelectionRecord$OfficeArtRecordHeader() {
            return Integer.valueOf(this._length);
        }
    }

    public DrawingSelectionRecord(RecordInputStream recordInputStream) {
        this._header = new OfficeArtRecordHeader(recordInputStream);
        this._cpsp = recordInputStream.readInt();
        this._dgslk = recordInputStream.readInt();
        this._spidFocus = recordInputStream.readInt();
        int available = recordInputStream.available() / 4;
        int[] iArr = new int[available];
        for (int i = 0; i < available; i++) {
            iArr[i] = recordInputStream.readInt();
        }
        this._shapeIds = iArr;
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this._shapeIds.length * 4) + 20;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        this._header.serialize(littleEndianOutput);
        littleEndianOutput.writeInt(this._cpsp);
        littleEndianOutput.writeInt(this._dgslk);
        littleEndianOutput.writeInt(this._spidFocus);
        for (int writeInt : this._shapeIds) {
            littleEndianOutput.writeInt(writeInt);
        }
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DRAWING_SELECTION;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("rh", new DrawingSelectionRecord$$ExternalSyntheticLambda0(this), "cpsp", new DrawingSelectionRecord$$ExternalSyntheticLambda1(this), "dgslk", new DrawingSelectionRecord$$ExternalSyntheticLambda2(this), "spidFocus", new DrawingSelectionRecord$$ExternalSyntheticLambda3(this), "shapeIds", new DrawingSelectionRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DrawingSelectionRecord  reason: not valid java name */
    public /* synthetic */ Object m1996lambda$getGenericProperties$0$orgapachepoihssfrecordDrawingSelectionRecord() {
        return this._header;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-DrawingSelectionRecord  reason: not valid java name */
    public /* synthetic */ Object m1997lambda$getGenericProperties$1$orgapachepoihssfrecordDrawingSelectionRecord() {
        return Integer.valueOf(this._cpsp);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-DrawingSelectionRecord  reason: not valid java name */
    public /* synthetic */ Object m1998lambda$getGenericProperties$2$orgapachepoihssfrecordDrawingSelectionRecord() {
        return Integer.valueOf(this._dgslk);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-DrawingSelectionRecord  reason: not valid java name */
    public /* synthetic */ Object m1999lambda$getGenericProperties$3$orgapachepoihssfrecordDrawingSelectionRecord() {
        return Integer.valueOf(this._spidFocus);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-DrawingSelectionRecord  reason: not valid java name */
    public /* synthetic */ Object m2000lambda$getGenericProperties$4$orgapachepoihssfrecordDrawingSelectionRecord() {
        return this._shapeIds;
    }
}
