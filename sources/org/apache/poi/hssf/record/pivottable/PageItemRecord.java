package org.apache.poi.hssf.record.pivottable;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

public final class PageItemRecord extends StandardRecord {
    public static final short sid = 182;
    private final FieldInfo[] _fieldInfos;

    public short getSid() {
        return 182;
    }

    private static final class FieldInfo implements GenericRecord {
        public static final int ENCODED_SIZE = 6;
        private int _idObj;
        private int _isxvd;
        private int _isxvi;

        public FieldInfo(FieldInfo fieldInfo) {
            this._isxvi = fieldInfo._isxvi;
            this._isxvd = fieldInfo._isxvd;
            this._idObj = fieldInfo._idObj;
        }

        public FieldInfo(RecordInputStream recordInputStream) {
            this._isxvi = recordInputStream.readShort();
            this._isxvd = recordInputStream.readShort();
            this._idObj = recordInputStream.readShort();
        }

        /* access modifiers changed from: private */
        public void serialize(LittleEndianOutput littleEndianOutput) {
            littleEndianOutput.writeShort(this._isxvi);
            littleEndianOutput.writeShort(this._isxvd);
            littleEndianOutput.writeShort(this._idObj);
        }

        public Map<String, Supplier<?>> getGenericProperties() {
            return GenericRecordUtil.getGenericProperties("isxvi", new PageItemRecord$FieldInfo$$ExternalSyntheticLambda0(this), "isxvd", new PageItemRecord$FieldInfo$$ExternalSyntheticLambda1(this), "idObj", new PageItemRecord$FieldInfo$$ExternalSyntheticLambda2(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-pivottable-PageItemRecord$FieldInfo  reason: not valid java name */
        public /* synthetic */ Object m2179lambda$getGenericProperties$0$orgapachepoihssfrecordpivottablePageItemRecord$FieldInfo() {
            return Integer.valueOf(this._isxvi);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-PageItemRecord$FieldInfo  reason: not valid java name */
        public /* synthetic */ Object m2180lambda$getGenericProperties$1$orgapachepoihssfrecordpivottablePageItemRecord$FieldInfo() {
            return Integer.valueOf(this._isxvd);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-pivottable-PageItemRecord$FieldInfo  reason: not valid java name */
        public /* synthetic */ Object m2181lambda$getGenericProperties$2$orgapachepoihssfrecordpivottablePageItemRecord$FieldInfo() {
            return Integer.valueOf(this._idObj);
        }
    }

    public PageItemRecord(PageItemRecord pageItemRecord) {
        super(pageItemRecord);
        this._fieldInfos = (FieldInfo[]) Stream.of(pageItemRecord._fieldInfos).map(new PageItemRecord$$ExternalSyntheticLambda0()).toArray(new PageItemRecord$$ExternalSyntheticLambda1());
    }

    static /* synthetic */ FieldInfo[] lambda$new$0(int i) {
        return new FieldInfo[i];
    }

    public PageItemRecord(RecordInputStream recordInputStream) {
        int remaining = recordInputStream.remaining();
        if (remaining % 6 == 0) {
            int i = remaining / 6;
            FieldInfo[] fieldInfoArr = new FieldInfo[i];
            for (int i2 = 0; i2 < i; i2++) {
                fieldInfoArr[i2] = new FieldInfo(recordInputStream);
            }
            this._fieldInfos = fieldInfoArr;
            return;
        }
        throw new RecordFormatException("Bad data size " + remaining);
    }

    /* access modifiers changed from: protected */
    public void serialize(LittleEndianOutput littleEndianOutput) {
        for (FieldInfo access$000 : this._fieldInfos) {
            access$000.serialize(littleEndianOutput);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this._fieldInfos.length * 6;
    }

    public PageItemRecord copy() {
        return new PageItemRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PAGE_ITEM;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("fieldInfos", new PageItemRecord$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-pivottable-PageItemRecord  reason: not valid java name */
    public /* synthetic */ Object m2178lambda$getGenericProperties$1$orgapachepoihssfrecordpivottablePageItemRecord() {
        return this._fieldInfos;
    }
}
