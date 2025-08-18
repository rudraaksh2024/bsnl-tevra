package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.RecordFormatException;

public final class LabelRecord extends Record implements CellValueRecordInterface {
    private static final Logger LOG = LogManager.getLogger((Class<?>) LabelRecord.class);
    public static final short sid = 516;
    private int field_1_row;
    private short field_2_column;
    private short field_3_xf_index;
    private short field_4_string_len;
    private byte field_5_unicode_flag;
    private String field_6_value;

    public short getSid() {
        return 516;
    }

    public void setColumn(short s) {
    }

    public void setRow(int i) {
    }

    public void setXFIndex(short s) {
    }

    public LabelRecord() {
    }

    public LabelRecord(LabelRecord labelRecord) {
        super(labelRecord);
        this.field_1_row = labelRecord.field_1_row;
        this.field_2_column = labelRecord.field_2_column;
        this.field_3_xf_index = labelRecord.field_3_xf_index;
        this.field_4_string_len = labelRecord.field_4_string_len;
        this.field_5_unicode_flag = labelRecord.field_5_unicode_flag;
        this.field_6_value = labelRecord.field_6_value;
    }

    public LabelRecord(RecordInputStream recordInputStream) {
        this.field_1_row = recordInputStream.readUShort();
        this.field_2_column = recordInputStream.readShort();
        this.field_3_xf_index = recordInputStream.readShort();
        this.field_4_string_len = recordInputStream.readShort();
        this.field_5_unicode_flag = recordInputStream.readByte();
        if (this.field_4_string_len <= 0) {
            this.field_6_value = "";
        } else if (isUnCompressedUnicode()) {
            this.field_6_value = recordInputStream.readUnicodeLEString(this.field_4_string_len);
        } else {
            this.field_6_value = recordInputStream.readCompressedUnicode(this.field_4_string_len);
        }
        if (recordInputStream.remaining() > 0) {
            LOG.atInfo().log("LabelRecord data remains: {} : {}", Unbox.box(recordInputStream.remaining()), HexDump.toHex(recordInputStream.readRemainder()));
        }
    }

    public int getRow() {
        return this.field_1_row;
    }

    public short getColumn() {
        return this.field_2_column;
    }

    public short getXFIndex() {
        return this.field_3_xf_index;
    }

    public short getStringLength() {
        return this.field_4_string_len;
    }

    public boolean isUnCompressedUnicode() {
        return (this.field_5_unicode_flag & 1) != 0;
    }

    public String getValue() {
        return this.field_6_value;
    }

    public int serialize(int i, byte[] bArr) {
        throw new RecordFormatException("Label Records are supported READ ONLY...convert to LabelSST");
    }

    public int getRecordSize() {
        throw new RecordFormatException("Label Records are supported READ ONLY...convert to LabelSST");
    }

    public LabelRecord copy() {
        return new LabelRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.LABEL;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("row", new LabelRecord$$ExternalSyntheticLambda0(this), "column", new LabelRecord$$ExternalSyntheticLambda1(this), "xfIndex", new LabelRecord$$ExternalSyntheticLambda2(this), "stringLen", new LabelRecord$$ExternalSyntheticLambda3(this), "unCompressedUnicode", new LabelRecord$$ExternalSyntheticLambda4(this), "value", new LabelRecord$$ExternalSyntheticLambda5(this));
    }
}
