package org.apache.poi.hssf.record.chart;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.HSSFRecordTypes;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class DatRecord extends StandardRecord {
    private static final BitField border = BitFieldFactory.getInstance(4);
    private static final BitField horizontalBorder = BitFieldFactory.getInstance(1);
    private static final BitField showSeriesKey = BitFieldFactory.getInstance(8);
    public static final short sid = 4195;
    private static final BitField verticalBorder = BitFieldFactory.getInstance(2);
    private short field_1_options;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public DatRecord() {
    }

    public DatRecord(DatRecord datRecord) {
        super(datRecord);
        this.field_1_options = datRecord.field_1_options;
    }

    public DatRecord(RecordInputStream recordInputStream) {
        this.field_1_options = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_options);
    }

    public DatRecord copy() {
        return new DatRecord(this);
    }

    public short getOptions() {
        return this.field_1_options;
    }

    public void setOptions(short s) {
        this.field_1_options = s;
    }

    public void setHorizontalBorder(boolean z) {
        this.field_1_options = horizontalBorder.setShortBoolean(this.field_1_options, z);
    }

    public boolean isHorizontalBorder() {
        return horizontalBorder.isSet(this.field_1_options);
    }

    public void setVerticalBorder(boolean z) {
        this.field_1_options = verticalBorder.setShortBoolean(this.field_1_options, z);
    }

    public boolean isVerticalBorder() {
        return verticalBorder.isSet(this.field_1_options);
    }

    public void setBorder(boolean z) {
        this.field_1_options = border.setShortBoolean(this.field_1_options, z);
    }

    public boolean isBorder() {
        return border.isSet(this.field_1_options);
    }

    public void setShowSeriesKey(boolean z) {
        this.field_1_options = showSeriesKey.setShortBoolean(this.field_1_options, z);
    }

    public boolean isShowSeriesKey() {
        return showSeriesKey.isSet(this.field_1_options);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DAT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", new DatRecord$$ExternalSyntheticLambda0(this), "horizontalBorder", new DatRecord$$ExternalSyntheticLambda1(this), "verticalBorder", new DatRecord$$ExternalSyntheticLambda2(this), "border", new DatRecord$$ExternalSyntheticLambda3(this), "showSeriesKey", new DatRecord$$ExternalSyntheticLambda4(this));
    }
}
