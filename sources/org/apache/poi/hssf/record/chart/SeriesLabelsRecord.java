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

public final class SeriesLabelsRecord extends StandardRecord {
    private static final BitField labelAsPercentage = BitFieldFactory.getInstance(4);
    private static final BitField showActual = BitFieldFactory.getInstance(1);
    private static final BitField showBubbleSizes = BitFieldFactory.getInstance(32);
    private static final BitField showLabel = BitFieldFactory.getInstance(16);
    private static final BitField showPercent = BitFieldFactory.getInstance(2);
    public static final short sid = 4108;
    private static final BitField smoothedLine = BitFieldFactory.getInstance(8);
    private short field_1_formatFlags;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return sid;
    }

    public SeriesLabelsRecord() {
    }

    public SeriesLabelsRecord(SeriesLabelsRecord seriesLabelsRecord) {
        super(seriesLabelsRecord);
        this.field_1_formatFlags = seriesLabelsRecord.field_1_formatFlags;
    }

    public SeriesLabelsRecord(RecordInputStream recordInputStream) {
        this.field_1_formatFlags = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_formatFlags);
    }

    public SeriesLabelsRecord copy() {
        return new SeriesLabelsRecord(this);
    }

    public short getFormatFlags() {
        return this.field_1_formatFlags;
    }

    public void setFormatFlags(short s) {
        this.field_1_formatFlags = s;
    }

    public void setShowActual(boolean z) {
        this.field_1_formatFlags = showActual.setShortBoolean(this.field_1_formatFlags, z);
    }

    public boolean isShowActual() {
        return showActual.isSet(this.field_1_formatFlags);
    }

    public void setShowPercent(boolean z) {
        this.field_1_formatFlags = showPercent.setShortBoolean(this.field_1_formatFlags, z);
    }

    public boolean isShowPercent() {
        return showPercent.isSet(this.field_1_formatFlags);
    }

    public void setLabelAsPercentage(boolean z) {
        this.field_1_formatFlags = labelAsPercentage.setShortBoolean(this.field_1_formatFlags, z);
    }

    public boolean isLabelAsPercentage() {
        return labelAsPercentage.isSet(this.field_1_formatFlags);
    }

    public void setSmoothedLine(boolean z) {
        this.field_1_formatFlags = smoothedLine.setShortBoolean(this.field_1_formatFlags, z);
    }

    public boolean isSmoothedLine() {
        return smoothedLine.isSet(this.field_1_formatFlags);
    }

    public void setShowLabel(boolean z) {
        this.field_1_formatFlags = showLabel.setShortBoolean(this.field_1_formatFlags, z);
    }

    public boolean isShowLabel() {
        return showLabel.isSet(this.field_1_formatFlags);
    }

    public void setShowBubbleSizes(boolean z) {
        this.field_1_formatFlags = showBubbleSizes.setShortBoolean(this.field_1_formatFlags, z);
    }

    public boolean isShowBubbleSizes() {
        return showBubbleSizes.isSet(this.field_1_formatFlags);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SERIES_LABELS;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("formatFlags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new SeriesLabelsRecord$$ExternalSyntheticLambda0(this), new BitField[]{showActual, showPercent, labelAsPercentage, smoothedLine, showLabel, showBubbleSizes}, new String[]{"SHOW_ACTUAL", "SHOW_PERCENT", "LABEL_AS_PERCENTAGE", "SMOOTHED_LINE", "SHOW_LABEL", "SHOW_BUBBLE_SIZES"}));
    }
}
