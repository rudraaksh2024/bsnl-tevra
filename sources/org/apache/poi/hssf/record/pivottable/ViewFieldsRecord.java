package org.apache.poi.hssf.record.pivottable;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class ViewFieldsRecord extends StandardRecord {
    private static final int BASE_SIZE = 10;
    private static final int STRING_NOT_PRESENT_LEN = 65535;
    public static final short sid = 177;
    private final int _cItm;
    private final int _cSub;
    private final int _grbitSub;
    private String _name;
    private final int _sxaxis;

    public short getSid() {
        return 177;
    }

    private enum Axis {
        NO_AXIS(0),
        ROW(1),
        COLUMN(2),
        PAGE(4),
        DATA(8);
        
        final int id;

        private Axis(int i) {
            this.id = i;
        }
    }

    public ViewFieldsRecord(ViewFieldsRecord viewFieldsRecord) {
        super(viewFieldsRecord);
        this._sxaxis = viewFieldsRecord._sxaxis;
        this._cSub = viewFieldsRecord._cSub;
        this._grbitSub = viewFieldsRecord._grbitSub;
        this._cItm = viewFieldsRecord._cItm;
        this._name = viewFieldsRecord._name;
    }

    public ViewFieldsRecord(RecordInputStream recordInputStream) {
        this._sxaxis = recordInputStream.readShort();
        this._cSub = recordInputStream.readShort();
        this._grbitSub = recordInputStream.readShort();
        this._cItm = recordInputStream.readShort();
        int readUShort = recordInputStream.readUShort();
        if (readUShort == 65535) {
            return;
        }
        if ((recordInputStream.readByte() & 1) != 0) {
            this._name = recordInputStream.readUnicodeLEString(readUShort);
        } else {
            this._name = recordInputStream.readCompressedUnicode(readUShort);
        }
    }

    /* access modifiers changed from: protected */
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._sxaxis);
        littleEndianOutput.writeShort(this._cSub);
        littleEndianOutput.writeShort(this._grbitSub);
        littleEndianOutput.writeShort(this._cItm);
        String str = this._name;
        if (str != null) {
            StringUtil.writeUnicodeString(littleEndianOutput, str);
        } else {
            littleEndianOutput.writeShort(65535);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        String str = this._name;
        if (str == null) {
            return 10;
        }
        return (str.length() * (StringUtil.hasMultibyte(this._name) ? 2 : 1)) + 11;
    }

    public ViewFieldsRecord copy() {
        return new ViewFieldsRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.VIEW_FIELDS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("sxaxis", new ViewFieldsRecord$$ExternalSyntheticLambda0(this), "cSub", new ViewFieldsRecord$$ExternalSyntheticLambda1(this), "grbitSub", new ViewFieldsRecord$$ExternalSyntheticLambda2(this), "cItm", new ViewFieldsRecord$$ExternalSyntheticLambda3(this), "name", new ViewFieldsRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-ViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2205lambda$getGenericProperties$0$orgapachepoihssfrecordpivottableViewFieldsRecord() {
        return Integer.valueOf(this._sxaxis);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-ViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2206lambda$getGenericProperties$1$orgapachepoihssfrecordpivottableViewFieldsRecord() {
        return Integer.valueOf(this._cSub);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-pivottable-ViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2207lambda$getGenericProperties$2$orgapachepoihssfrecordpivottableViewFieldsRecord() {
        return Integer.valueOf(this._grbitSub);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-pivottable-ViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2208lambda$getGenericProperties$3$orgapachepoihssfrecordpivottableViewFieldsRecord() {
        return Integer.valueOf(this._cItm);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-pivottable-ViewFieldsRecord  reason: not valid java name */
    public /* synthetic */ Object m2209lambda$getGenericProperties$4$orgapachepoihssfrecordpivottableViewFieldsRecord() {
        return this._name;
    }
}
