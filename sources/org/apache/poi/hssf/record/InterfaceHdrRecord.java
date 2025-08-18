package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class InterfaceHdrRecord extends StandardRecord {
    public static final int CODEPAGE = 1200;
    public static final short sid = 225;
    private final int _codepage;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public InterfaceHdrRecord(InterfaceHdrRecord interfaceHdrRecord) {
        super(interfaceHdrRecord);
        this._codepage = interfaceHdrRecord._codepage;
    }

    public InterfaceHdrRecord(int i) {
        this._codepage = i;
    }

    public InterfaceHdrRecord(RecordInputStream recordInputStream) {
        this._codepage = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._codepage);
    }

    public InterfaceHdrRecord copy() {
        return new InterfaceHdrRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.INTERFACE_HDR;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("codePage", new InterfaceHdrRecord$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-InterfaceHdrRecord  reason: not valid java name */
    public /* synthetic */ Object m2033lambda$getGenericProperties$0$orgapachepoihssfrecordInterfaceHdrRecord() {
        return Integer.valueOf(this._codepage);
    }
}
