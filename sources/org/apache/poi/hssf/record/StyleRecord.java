package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

public final class StyleRecord extends StandardRecord {
    private static final BitField isBuiltinFlag = BitFieldFactory.getInstance(32768);
    public static final short sid = 659;
    private static final BitField styleIndexMask = BitFieldFactory.getInstance(4095);
    private int field_1_xf_index;
    private int field_2_builtin_style;
    private int field_3_outline_style_level;
    private boolean field_3_stringHasMultibyte;
    private String field_4_name;

    public short getSid() {
        return sid;
    }

    public StyleRecord() {
        this.field_1_xf_index = isBuiltinFlag.set(0);
    }

    public StyleRecord(StyleRecord styleRecord) {
        super(styleRecord);
        this.field_1_xf_index = styleRecord.field_1_xf_index;
        this.field_2_builtin_style = styleRecord.field_2_builtin_style;
        this.field_3_outline_style_level = styleRecord.field_3_outline_style_level;
        this.field_3_stringHasMultibyte = styleRecord.field_3_stringHasMultibyte;
        this.field_4_name = styleRecord.field_4_name;
    }

    public StyleRecord(RecordInputStream recordInputStream) {
        this.field_1_xf_index = recordInputStream.readShort();
        if (isBuiltin()) {
            this.field_2_builtin_style = recordInputStream.readByte();
            this.field_3_outline_style_level = recordInputStream.readByte();
            return;
        }
        short readShort = recordInputStream.readShort();
        boolean z = true;
        if (recordInputStream.remaining() >= 1) {
            z = recordInputStream.readByte() == 0 ? false : z;
            this.field_3_stringHasMultibyte = z;
            if (z) {
                this.field_4_name = StringUtil.readUnicodeLE(recordInputStream, readShort);
            } else {
                this.field_4_name = StringUtil.readCompressedUnicode(recordInputStream, readShort);
            }
        } else if (readShort == 0) {
            this.field_4_name = "";
        } else {
            throw new RecordFormatException("Ran out of data reading style record");
        }
    }

    public void setXFIndex(int i) {
        this.field_1_xf_index = styleIndexMask.setValue(this.field_1_xf_index, i);
    }

    public int getXFIndex() {
        return styleIndexMask.getValue(this.field_1_xf_index);
    }

    public void setName(String str) {
        this.field_4_name = str;
        this.field_3_stringHasMultibyte = StringUtil.hasMultibyte(str);
        this.field_1_xf_index = isBuiltinFlag.clear(this.field_1_xf_index);
    }

    public void setBuiltinStyle(int i) {
        this.field_1_xf_index = isBuiltinFlag.set(this.field_1_xf_index);
        this.field_2_builtin_style = i;
    }

    public void setOutlineStyleLevel(int i) {
        this.field_3_outline_style_level = i & 255;
    }

    public boolean isBuiltin() {
        return isBuiltinFlag.isSet(this.field_1_xf_index);
    }

    public String getName() {
        return this.field_4_name;
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        if (isBuiltin()) {
            return 4;
        }
        return (this.field_4_name.length() * (this.field_3_stringHasMultibyte ? 2 : 1)) + 5;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_xf_index);
        if (isBuiltin()) {
            littleEndianOutput.writeByte(this.field_2_builtin_style);
            littleEndianOutput.writeByte(this.field_3_outline_style_level);
            return;
        }
        littleEndianOutput.writeShort(this.field_4_name.length());
        littleEndianOutput.writeByte(this.field_3_stringHasMultibyte ? 1 : 0);
        if (this.field_3_stringHasMultibyte) {
            StringUtil.putUnicodeLE(getName(), littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(getName(), littleEndianOutput);
        }
    }

    public StyleRecord copy() {
        return new StyleRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.STYLE;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("xfIndex", new StyleRecord$$ExternalSyntheticLambda0(this), "type", new StyleRecord$$ExternalSyntheticLambda1(this), "builtin_style", new StyleRecord$$ExternalSyntheticLambda2(this), "outline_level", new StyleRecord$$ExternalSyntheticLambda3(this), "name", new StyleRecord$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-StyleRecord  reason: not valid java name */
    public /* synthetic */ Object m2090lambda$getGenericProperties$0$orgapachepoihssfrecordStyleRecord() {
        return isBuiltin() ? "built-in" : "user-defined";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-StyleRecord  reason: not valid java name */
    public /* synthetic */ Object m2091lambda$getGenericProperties$1$orgapachepoihssfrecordStyleRecord() {
        return Integer.valueOf(this.field_2_builtin_style);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-StyleRecord  reason: not valid java name */
    public /* synthetic */ Object m2092lambda$getGenericProperties$2$orgapachepoihssfrecordStyleRecord() {
        return Integer.valueOf(this.field_3_outline_style_level);
    }
}
