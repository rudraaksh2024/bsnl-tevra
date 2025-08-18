package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class WindowTwoRecord extends StandardRecord {
    private static final BitField active = BitFieldFactory.getInstance(1024);
    private static final BitField arabic = BitFieldFactory.getInstance(64);
    private static final BitField defaultHeader = BitFieldFactory.getInstance(32);
    private static final BitField displayFormulas = BitFieldFactory.getInstance(1);
    private static final BitField displayGridlines = BitFieldFactory.getInstance(2);
    private static final BitField displayGuts = BitFieldFactory.getInstance(128);
    private static final BitField displayRowColHeadings = BitFieldFactory.getInstance(4);
    private static final BitField displayZeros = BitFieldFactory.getInstance(16);
    private static final BitField freezePanes = BitFieldFactory.getInstance(8);
    private static final BitField freezePanesNoSplit = BitFieldFactory.getInstance(256);
    private static final BitField savedInPageBreakPreview = BitFieldFactory.getInstance(2048);
    private static final BitField selected = BitFieldFactory.getInstance(512);
    public static final short sid = 574;
    private short field_1_options;
    private short field_2_top_row;
    private short field_3_left_col;
    private int field_4_header_color;
    private short field_5_page_break_zoom;
    private short field_6_normal_zoom;
    private int field_7_reserved;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 18;
    }

    public short getSid() {
        return sid;
    }

    public WindowTwoRecord() {
    }

    public WindowTwoRecord(WindowTwoRecord windowTwoRecord) {
        super(windowTwoRecord);
        this.field_1_options = windowTwoRecord.field_1_options;
        this.field_2_top_row = windowTwoRecord.field_2_top_row;
        this.field_3_left_col = windowTwoRecord.field_3_left_col;
        this.field_4_header_color = windowTwoRecord.field_4_header_color;
        this.field_5_page_break_zoom = windowTwoRecord.field_5_page_break_zoom;
        this.field_6_normal_zoom = windowTwoRecord.field_6_normal_zoom;
        this.field_7_reserved = windowTwoRecord.field_7_reserved;
    }

    public WindowTwoRecord(RecordInputStream recordInputStream) {
        int remaining = recordInputStream.remaining();
        this.field_1_options = recordInputStream.readShort();
        this.field_2_top_row = recordInputStream.readShort();
        this.field_3_left_col = recordInputStream.readShort();
        this.field_4_header_color = recordInputStream.readInt();
        if (remaining > 10) {
            this.field_5_page_break_zoom = recordInputStream.readShort();
            this.field_6_normal_zoom = recordInputStream.readShort();
        }
        if (remaining > 14) {
            this.field_7_reserved = recordInputStream.readInt();
        }
    }

    public void setOptions(short s) {
        this.field_1_options = s;
    }

    public void setDisplayFormulas(boolean z) {
        this.field_1_options = displayFormulas.setShortBoolean(this.field_1_options, z);
    }

    public void setDisplayGridlines(boolean z) {
        this.field_1_options = displayGridlines.setShortBoolean(this.field_1_options, z);
    }

    public void setDisplayRowColHeadings(boolean z) {
        this.field_1_options = displayRowColHeadings.setShortBoolean(this.field_1_options, z);
    }

    public void setFreezePanes(boolean z) {
        this.field_1_options = freezePanes.setShortBoolean(this.field_1_options, z);
    }

    public void setDisplayZeros(boolean z) {
        this.field_1_options = displayZeros.setShortBoolean(this.field_1_options, z);
    }

    public void setDefaultHeader(boolean z) {
        this.field_1_options = defaultHeader.setShortBoolean(this.field_1_options, z);
    }

    public void setArabic(boolean z) {
        this.field_1_options = arabic.setShortBoolean(this.field_1_options, z);
    }

    public void setDisplayGuts(boolean z) {
        this.field_1_options = displayGuts.setShortBoolean(this.field_1_options, z);
    }

    public void setFreezePanesNoSplit(boolean z) {
        this.field_1_options = freezePanesNoSplit.setShortBoolean(this.field_1_options, z);
    }

    public void setSelected(boolean z) {
        this.field_1_options = selected.setShortBoolean(this.field_1_options, z);
    }

    public void setActive(boolean z) {
        this.field_1_options = active.setShortBoolean(this.field_1_options, z);
    }

    public void setSavedInPageBreakPreview(boolean z) {
        this.field_1_options = savedInPageBreakPreview.setShortBoolean(this.field_1_options, z);
    }

    public void setTopRow(short s) {
        this.field_2_top_row = s;
    }

    public void setLeftCol(short s) {
        this.field_3_left_col = s;
    }

    public void setHeaderColor(int i) {
        this.field_4_header_color = i;
    }

    public void setPageBreakZoom(short s) {
        this.field_5_page_break_zoom = s;
    }

    public void setNormalZoom(short s) {
        this.field_6_normal_zoom = s;
    }

    public void setReserved(int i) {
        this.field_7_reserved = i;
    }

    public short getOptions() {
        return this.field_1_options;
    }

    public boolean getDisplayFormulas() {
        return displayFormulas.isSet(this.field_1_options);
    }

    public boolean getDisplayGridlines() {
        return displayGridlines.isSet(this.field_1_options);
    }

    public boolean getDisplayRowColHeadings() {
        return displayRowColHeadings.isSet(this.field_1_options);
    }

    public boolean getFreezePanes() {
        return freezePanes.isSet(this.field_1_options);
    }

    public boolean getDisplayZeros() {
        return displayZeros.isSet(this.field_1_options);
    }

    public boolean getDefaultHeader() {
        return defaultHeader.isSet(this.field_1_options);
    }

    public boolean getArabic() {
        return arabic.isSet(this.field_1_options);
    }

    public boolean getDisplayGuts() {
        return displayGuts.isSet(this.field_1_options);
    }

    public boolean getFreezePanesNoSplit() {
        return freezePanesNoSplit.isSet(this.field_1_options);
    }

    public boolean getSelected() {
        return selected.isSet(this.field_1_options);
    }

    public boolean isActive() {
        return active.isSet(this.field_1_options);
    }

    public boolean getSavedInPageBreakPreview() {
        return savedInPageBreakPreview.isSet(this.field_1_options);
    }

    public short getTopRow() {
        return this.field_2_top_row;
    }

    public short getLeftCol() {
        return this.field_3_left_col;
    }

    public int getHeaderColor() {
        return this.field_4_header_color;
    }

    public short getPageBreakZoom() {
        return this.field_5_page_break_zoom;
    }

    public short getNormalZoom() {
        return this.field_6_normal_zoom;
    }

    public int getReserved() {
        return this.field_7_reserved;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getOptions());
        littleEndianOutput.writeShort(getTopRow());
        littleEndianOutput.writeShort(getLeftCol());
        littleEndianOutput.writeInt(getHeaderColor());
        littleEndianOutput.writeShort(getPageBreakZoom());
        littleEndianOutput.writeShort(getNormalZoom());
        littleEndianOutput.writeInt(getReserved());
    }

    public WindowTwoRecord copy() {
        return new WindowTwoRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.WINDOW_TWO;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", GenericRecordUtil.getBitsAsString((Supplier<Number>) new WindowTwoRecord$$ExternalSyntheticLambda0(this), new BitField[]{displayFormulas, displayGridlines, displayRowColHeadings, freezePanes, displayZeros, defaultHeader, arabic, displayGuts, freezePanesNoSplit, selected, active, savedInPageBreakPreview}, new String[]{"DISPLAY_FORMULAS", "DISPLAY_GRIDLINES", "DISPLAY_ROW_COL_HEADINGS", "FREEZE_PANES", "DISPLAY_ZEROS", "DEFAULT_HEADER", "ARABIC", "DISPLAY_GUTS", "FREEZE_PANES_NO_SPLIT", "SELECTED", "ACTIVE", "SAVED_IN_PAGE_BREAK_PREVIEW"}), "topRow", new WindowTwoRecord$$ExternalSyntheticLambda1(this), "leftCol", new WindowTwoRecord$$ExternalSyntheticLambda2(this), "headerColor", new WindowTwoRecord$$ExternalSyntheticLambda3(this), "pageBreakZoom", new WindowTwoRecord$$ExternalSyntheticLambda4(this), "normalZoom", new WindowTwoRecord$$ExternalSyntheticLambda5(this), "reserved", new WindowTwoRecord$$ExternalSyntheticLambda6(this));
    }
}
