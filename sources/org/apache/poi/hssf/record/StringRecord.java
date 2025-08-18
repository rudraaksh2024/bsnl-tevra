package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.StringUtil;

public final class StringRecord extends ContinuableRecord {
    public static final short sid = 519;
    private boolean _is16bitUnicode;
    private String _text;

    public short getSid() {
        return 519;
    }

    public StringRecord() {
    }

    public StringRecord(StringRecord stringRecord) {
        this._is16bitUnicode = stringRecord._is16bitUnicode;
        this._text = stringRecord._text;
    }

    public StringRecord(RecordInputStream recordInputStream) {
        int readUShort = recordInputStream.readUShort();
        boolean z = recordInputStream.readByte() != 0;
        this._is16bitUnicode = z;
        if (z) {
            this._text = recordInputStream.readUnicodeLEString(readUShort);
        } else {
            this._text = recordInputStream.readCompressedUnicode(readUShort);
        }
    }

    /* access modifiers changed from: protected */
    public void serialize(ContinuableRecordOutput continuableRecordOutput) {
        continuableRecordOutput.writeShort(this._text.length());
        continuableRecordOutput.writeStringData(this._text);
    }

    public String getString() {
        return this._text;
    }

    public void setString(String str) {
        this._text = str;
        this._is16bitUnicode = StringUtil.hasMultibyte(str);
    }

    public StringRecord copy() {
        return new StringRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.STRING;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("is16bitUnicode", new StringRecord$$ExternalSyntheticLambda0(this), "text", new StringRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-StringRecord  reason: not valid java name */
    public /* synthetic */ Object m2089lambda$getGenericProperties$0$orgapachepoihssfrecordStringRecord() {
        return Boolean.valueOf(this._is16bitUnicode);
    }
}
