package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class FontBasisRecord extends StandardRecord {
    public static final short sid = 4192;
    private short field_1_xBasis;
    private short field_2_yBasis;
    private short field_3_heightBasis;
    private short field_4_scale;
    private short field_5_indexToFontTable;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 10;
    }

    public short getSid() {
        return sid;
    }

    public FontBasisRecord() {
    }

    public FontBasisRecord(FontBasisRecord fontBasisRecord) {
        super(fontBasisRecord);
        this.field_1_xBasis = fontBasisRecord.field_1_xBasis;
        this.field_2_yBasis = fontBasisRecord.field_2_yBasis;
        this.field_3_heightBasis = fontBasisRecord.field_3_heightBasis;
        this.field_4_scale = fontBasisRecord.field_4_scale;
        this.field_5_indexToFontTable = fontBasisRecord.field_5_indexToFontTable;
    }

    public FontBasisRecord(RecordInputStream recordInputStream) {
        this.field_1_xBasis = recordInputStream.readShort();
        this.field_2_yBasis = recordInputStream.readShort();
        this.field_3_heightBasis = recordInputStream.readShort();
        this.field_4_scale = recordInputStream.readShort();
        this.field_5_indexToFontTable = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_xBasis);
        littleEndianOutput.writeShort(this.field_2_yBasis);
        littleEndianOutput.writeShort(this.field_3_heightBasis);
        littleEndianOutput.writeShort(this.field_4_scale);
        littleEndianOutput.writeShort(this.field_5_indexToFontTable);
    }

    public FontBasisRecord copy() {
        return new FontBasisRecord(this);
    }

    public short getXBasis() {
        return this.field_1_xBasis;
    }

    public void setXBasis(short s) {
        this.field_1_xBasis = s;
    }

    public short getYBasis() {
        return this.field_2_yBasis;
    }

    public void setYBasis(short s) {
        this.field_2_yBasis = s;
    }

    public short getHeightBasis() {
        return this.field_3_heightBasis;
    }

    public void setHeightBasis(short s) {
        this.field_3_heightBasis = s;
    }

    public short getScale() {
        return this.field_4_scale;
    }

    public void setScale(short s) {
        this.field_4_scale = s;
    }

    public short getIndexToFontTable() {
        return this.field_5_indexToFontTable;
    }

    public void setIndexToFontTable(short s) {
        this.field_5_indexToFontTable = s;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FONT_BASIS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("xBasis", new FontBasisRecord$$ExternalSyntheticLambda0(this), "yBasis", new FontBasisRecord$$ExternalSyntheticLambda1(this), "heightBasis", new FontBasisRecord$$ExternalSyntheticLambda2(this), "scale", new FontBasisRecord$$ExternalSyntheticLambda3(this), "indexToFontTable", new FontBasisRecord$$ExternalSyntheticLambda4(this));
    }
}
