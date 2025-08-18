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

public final class SheetPropertiesRecord extends StandardRecord {
    public static final byte EMPTY_INTERPOLATED = 2;
    public static final byte EMPTY_NOT_PLOTTED = 0;
    public static final byte EMPTY_ZERO = 1;
    private static final BitField autoPlotArea = BitFieldFactory.getInstance(16);
    private static final BitField chartTypeManuallyFormatted = BitFieldFactory.getInstance(1);
    private static final BitField defaultPlotDimensions = BitFieldFactory.getInstance(8);
    private static final BitField doNotSizeWithWindow = BitFieldFactory.getInstance(4);
    private static final BitField plotVisibleOnly = BitFieldFactory.getInstance(2);
    public static final short sid = 4164;
    private int field_1_flags;
    private int field_2_empty;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 4;
    }

    public short getSid() {
        return sid;
    }

    public SheetPropertiesRecord() {
    }

    public SheetPropertiesRecord(SheetPropertiesRecord sheetPropertiesRecord) {
        super(sheetPropertiesRecord);
        this.field_1_flags = sheetPropertiesRecord.field_1_flags;
        this.field_2_empty = sheetPropertiesRecord.field_2_empty;
    }

    public SheetPropertiesRecord(RecordInputStream recordInputStream) {
        this.field_1_flags = recordInputStream.readUShort();
        this.field_2_empty = recordInputStream.readUShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_flags);
        littleEndianOutput.writeShort(this.field_2_empty);
    }

    public SheetPropertiesRecord copy() {
        return new SheetPropertiesRecord(this);
    }

    public int getFlags() {
        return this.field_1_flags;
    }

    public int getEmpty() {
        return this.field_2_empty;
    }

    public void setEmpty(byte b) {
        this.field_2_empty = b;
    }

    public void setChartTypeManuallyFormatted(boolean z) {
        this.field_1_flags = chartTypeManuallyFormatted.setBoolean(this.field_1_flags, z);
    }

    public boolean isChartTypeManuallyFormatted() {
        return chartTypeManuallyFormatted.isSet(this.field_1_flags);
    }

    public void setPlotVisibleOnly(boolean z) {
        this.field_1_flags = plotVisibleOnly.setBoolean(this.field_1_flags, z);
    }

    public boolean isPlotVisibleOnly() {
        return plotVisibleOnly.isSet(this.field_1_flags);
    }

    public void setDoNotSizeWithWindow(boolean z) {
        this.field_1_flags = doNotSizeWithWindow.setBoolean(this.field_1_flags, z);
    }

    public boolean isDoNotSizeWithWindow() {
        return doNotSizeWithWindow.isSet(this.field_1_flags);
    }

    public void setDefaultPlotDimensions(boolean z) {
        this.field_1_flags = defaultPlotDimensions.setBoolean(this.field_1_flags, z);
    }

    public boolean isDefaultPlotDimensions() {
        return defaultPlotDimensions.isSet(this.field_1_flags);
    }

    public void setAutoPlotArea(boolean z) {
        this.field_1_flags = autoPlotArea.setBoolean(this.field_1_flags, z);
    }

    public boolean isAutoPlotArea() {
        return autoPlotArea.isSet(this.field_1_flags);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SHEET_PROPERTIES;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("flags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new SheetPropertiesRecord$$ExternalSyntheticLambda0(this), new BitField[]{chartTypeManuallyFormatted, plotVisibleOnly, doNotSizeWithWindow, defaultPlotDimensions, autoPlotArea}, new String[]{"CHART_TYPE_MANUALLY_FORMATTED", "PLOT_VISIBLE_ONLY", "DO_NOT_SIZE_WITH_WINDOW", "DEFAULT_PLOT_DIMENSIONS", "AUTO_PLOT_AREA"}), "empty", GenericRecordUtil.getEnumBitsAsString(new SheetPropertiesRecord$$ExternalSyntheticLambda1(this), new int[]{0, 1, 2}, new String[]{"EMPTY_NOT_PLOTTED", "EMPTY_ZERO", "EMPTY_INTERPOLATED"}));
    }
}
