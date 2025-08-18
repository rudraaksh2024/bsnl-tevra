package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class NameCommentRecord extends StandardRecord {
    public static final short sid = 2196;
    private final short field_1_record_type;
    private final short field_2_frt_cell_ref_flag;
    private final long field_3_reserved;
    private String field_6_name_text;
    private String field_7_comment_text;

    public short getSid() {
        return sid;
    }

    public NameCommentRecord(NameCommentRecord nameCommentRecord) {
        this.field_1_record_type = nameCommentRecord.field_1_record_type;
        this.field_2_frt_cell_ref_flag = nameCommentRecord.field_2_frt_cell_ref_flag;
        this.field_3_reserved = nameCommentRecord.field_3_reserved;
        this.field_6_name_text = nameCommentRecord.field_6_name_text;
        this.field_7_comment_text = nameCommentRecord.field_7_comment_text;
    }

    public NameCommentRecord(String str, String str2) {
        this.field_1_record_type = 0;
        this.field_2_frt_cell_ref_flag = 0;
        this.field_3_reserved = 0;
        this.field_6_name_text = str;
        this.field_7_comment_text = str2;
    }

    public NameCommentRecord(RecordInputStream recordInputStream) {
        this.field_1_record_type = recordInputStream.readShort();
        this.field_2_frt_cell_ref_flag = recordInputStream.readShort();
        this.field_3_reserved = recordInputStream.readLong();
        short readShort = recordInputStream.readShort();
        short readShort2 = recordInputStream.readShort();
        if (recordInputStream.readByte() == 0) {
            this.field_6_name_text = StringUtil.readCompressedUnicode(recordInputStream, readShort);
        } else {
            this.field_6_name_text = StringUtil.readUnicodeLE(recordInputStream, readShort);
        }
        if (recordInputStream.readByte() == 0) {
            this.field_7_comment_text = StringUtil.readCompressedUnicode(recordInputStream, readShort2);
        } else {
            this.field_7_comment_text = StringUtil.readUnicodeLE(recordInputStream, readShort2);
        }
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        int length = this.field_6_name_text.length();
        int length2 = this.field_7_comment_text.length();
        littleEndianOutput.writeShort(this.field_1_record_type);
        littleEndianOutput.writeShort(this.field_2_frt_cell_ref_flag);
        littleEndianOutput.writeLong(this.field_3_reserved);
        littleEndianOutput.writeShort(length);
        littleEndianOutput.writeShort(length2);
        boolean hasMultibyte = StringUtil.hasMultibyte(this.field_6_name_text);
        littleEndianOutput.writeByte(hasMultibyte ? 1 : 0);
        if (hasMultibyte) {
            StringUtil.putUnicodeLE(this.field_6_name_text, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(this.field_6_name_text, littleEndianOutput);
        }
        boolean hasMultibyte2 = StringUtil.hasMultibyte(this.field_7_comment_text);
        littleEndianOutput.writeByte(hasMultibyte2 ? 1 : 0);
        if (hasMultibyte2) {
            StringUtil.putUnicodeLE(this.field_7_comment_text, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(this.field_7_comment_text, littleEndianOutput);
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        int length = (StringUtil.hasMultibyte(this.field_6_name_text) ? this.field_6_name_text.length() * 2 : this.field_6_name_text.length()) + 18;
        boolean hasMultibyte = StringUtil.hasMultibyte(this.field_7_comment_text);
        int length2 = this.field_7_comment_text.length();
        if (hasMultibyte) {
            length2 *= 2;
        }
        return length + length2;
    }

    public String getNameText() {
        return this.field_6_name_text;
    }

    public void setNameText(String str) {
        this.field_6_name_text = str;
    }

    public String getCommentText() {
        return this.field_7_comment_text;
    }

    public void setCommentText(String str) {
        this.field_7_comment_text = str;
    }

    public short getRecordType() {
        return this.field_1_record_type;
    }

    public NameCommentRecord copy() {
        return new NameCommentRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.NAME_COMMENT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("recordType", new NameCommentRecord$$ExternalSyntheticLambda0(this), "frtCellRefFlag", new NameCommentRecord$$ExternalSyntheticLambda1(this), "reserved", new NameCommentRecord$$ExternalSyntheticLambda2(this), "name", new NameCommentRecord$$ExternalSyntheticLambda3(this), "comment", new NameCommentRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-NameCommentRecord  reason: not valid java name */
    public /* synthetic */ Object m2055lambda$getGenericProperties$0$orgapachepoihssfrecordNameCommentRecord() {
        return Short.valueOf(this.field_2_frt_cell_ref_flag);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-NameCommentRecord  reason: not valid java name */
    public /* synthetic */ Object m2056lambda$getGenericProperties$1$orgapachepoihssfrecordNameCommentRecord() {
        return Long.valueOf(this.field_3_reserved);
    }
}
