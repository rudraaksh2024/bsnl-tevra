package org.apache.poi.hssf.record.pivottable;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

public final class ExtendedPivotTableViewFieldsRecord extends StandardRecord {
    private static final int STRING_NOT_PRESENT_LEN = 65535;
    public static final short sid = 256;
    private int _citmShow;
    private int _grbit1;
    private int _grbit2;
    private int _isxdiShow;
    private int _isxdiSort;
    private int _reserved1;
    private int _reserved2;
    private String _subtotalName;

    public short getSid() {
        return sid;
    }

    public ExtendedPivotTableViewFieldsRecord(ExtendedPivotTableViewFieldsRecord extendedPivotTableViewFieldsRecord) {
        super(extendedPivotTableViewFieldsRecord);
        this._grbit1 = extendedPivotTableViewFieldsRecord._grbit1;
        this._grbit2 = extendedPivotTableViewFieldsRecord._grbit2;
        this._citmShow = extendedPivotTableViewFieldsRecord._citmShow;
        this._isxdiSort = extendedPivotTableViewFieldsRecord._isxdiSort;
        this._isxdiShow = extendedPivotTableViewFieldsRecord._isxdiShow;
        this._reserved1 = extendedPivotTableViewFieldsRecord._reserved1;
        this._reserved2 = extendedPivotTableViewFieldsRecord._reserved2;
        this._subtotalName = extendedPivotTableViewFieldsRecord._subtotalName;
    }

    public ExtendedPivotTableViewFieldsRecord(RecordInputStream recordInputStream) {
        this._grbit1 = recordInputStream.readInt();
        this._grbit2 = recordInputStream.readUByte();
        this._citmShow = recordInputStream.readUByte();
        this._isxdiSort = recordInputStream.readUShort();
        this._isxdiShow = recordInputStream.readUShort();
        int remaining = recordInputStream.remaining();
        if (remaining == 0) {
            this._reserved1 = 0;
            this._reserved2 = 0;
            this._subtotalName = null;
        } else if (remaining == 10) {
            int readUShort = recordInputStream.readUShort();
            this._reserved1 = recordInputStream.readInt();
            this._reserved2 = recordInputStream.readInt();
            if (readUShort != 65535) {
                this._subtotalName = recordInputStream.readUnicodeLEString(readUShort);
            }
        } else {
            throw new RecordFormatException("Unexpected remaining size (" + recordInputStream.remaining() + ")");
        }
    }

    /* access modifiers changed from: protected */
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this._grbit1);
        littleEndianOutput.writeByte(this._grbit2);
        littleEndianOutput.writeByte(this._citmShow);
        littleEndianOutput.writeShort(this._isxdiSort);
        littleEndianOutput.writeShort(this._isxdiShow);
        String str = this._subtotalName;
        if (str == null) {
            littleEndianOutput.writeShort(65535);
        } else {
            littleEndianOutput.writeShort(str.length());
        }
        littleEndianOutput.writeInt(this._reserved1);
        littleEndianOutput.writeInt(this._reserved2);
        String str2 = this._subtotalName;
        if (str2 != null) {
            StringUtil.putUnicodeLE(str2, littleEndianOutput);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        int i;
        String str = this._subtotalName;
        if (str == null) {
            i = 0;
        } else {
            i = str.length() * 2;
        }
        return i + 20;
    }

    public ExtendedPivotTableViewFieldsRecord copy() {
        return new ExtendedPivotTableViewFieldsRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.EXTENDED_PIVOT_TABLE_VIEW_FIELDS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("grbit1", new ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda0(this), "grbit2", new ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda1(this), "citmShow", new ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda2(this), "isxdiSort", new ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda3(this), "isxdiShow", new ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda4(this), "subtotalName", new ExtendedPivotTableViewFieldsRecord$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2172lambda$getGenericProperties$0$orgapachepoihssfrecordpivottableExtendedPivotTableViewFieldsRecord() {
        return Integer.valueOf(this._grbit1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2173lambda$getGenericProperties$1$orgapachepoihssfrecordpivottableExtendedPivotTableViewFieldsRecord() {
        return Integer.valueOf(this._grbit2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2174lambda$getGenericProperties$2$orgapachepoihssfrecordpivottableExtendedPivotTableViewFieldsRecord() {
        return Integer.valueOf(this._citmShow);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2175lambda$getGenericProperties$3$orgapachepoihssfrecordpivottableExtendedPivotTableViewFieldsRecord() {
        return Integer.valueOf(this._isxdiSort);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2176lambda$getGenericProperties$4$orgapachepoihssfrecordpivottableExtendedPivotTableViewFieldsRecord() {
        return Integer.valueOf(this._isxdiShow);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-pivottable-ExtendedPivotTableViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2177lambda$getGenericProperties$5$orgapachepoihssfrecordpivottableExtendedPivotTableViewFieldsRecord() {
        return this._subtotalName;
    }
}
