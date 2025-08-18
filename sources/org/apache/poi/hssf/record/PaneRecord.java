package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class PaneRecord extends StandardRecord {
    public static final short ACTIVE_PANE_LOWER_LEFT = 2;
    public static final short ACTIVE_PANE_LOWER_RIGHT = 0;
    public static final short ACTIVE_PANE_UPPER_LEFT = 3;
    public static final short ACTIVE_PANE_UPPER_RIGHT = 1;
    public static final short sid = 65;
    private short field_1_x;
    private short field_2_y;
    private short field_3_topRow;
    private short field_4_leftColumn;
    private short field_5_activePane;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 10;
    }

    public short getSid() {
        return 65;
    }

    public PaneRecord() {
    }

    public PaneRecord(PaneRecord paneRecord) {
        super(paneRecord);
        this.field_1_x = paneRecord.field_1_x;
        this.field_2_y = paneRecord.field_2_y;
        this.field_3_topRow = paneRecord.field_3_topRow;
        this.field_4_leftColumn = paneRecord.field_4_leftColumn;
        this.field_5_activePane = paneRecord.field_5_activePane;
    }

    public PaneRecord(RecordInputStream recordInputStream) {
        this.field_1_x = recordInputStream.readShort();
        this.field_2_y = recordInputStream.readShort();
        this.field_3_topRow = recordInputStream.readShort();
        this.field_4_leftColumn = recordInputStream.readShort();
        this.field_5_activePane = recordInputStream.readShort();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_x);
        littleEndianOutput.writeShort(this.field_2_y);
        littleEndianOutput.writeShort(this.field_3_topRow);
        littleEndianOutput.writeShort(this.field_4_leftColumn);
        littleEndianOutput.writeShort(this.field_5_activePane);
    }

    public PaneRecord copy() {
        return new PaneRecord(this);
    }

    public short getX() {
        return this.field_1_x;
    }

    public void setX(short s) {
        this.field_1_x = s;
    }

    public short getY() {
        return this.field_2_y;
    }

    public void setY(short s) {
        this.field_2_y = s;
    }

    public short getTopRow() {
        return this.field_3_topRow;
    }

    public void setTopRow(short s) {
        this.field_3_topRow = s;
    }

    public short getLeftColumn() {
        return this.field_4_leftColumn;
    }

    public void setLeftColumn(short s) {
        this.field_4_leftColumn = s;
    }

    public short getActivePane() {
        return this.field_5_activePane;
    }

    public void setActivePane(short s) {
        this.field_5_activePane = s;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PANE;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("x", new PaneRecord$$ExternalSyntheticLambda0(this), "y", new PaneRecord$$ExternalSyntheticLambda1(this), "topRow", new PaneRecord$$ExternalSyntheticLambda2(this), "leftColumn", new PaneRecord$$ExternalSyntheticLambda3(this), "activePane", GenericRecordUtil.getEnumBitsAsString(new PaneRecord$$ExternalSyntheticLambda4(this), new int[]{0, 1, 2, 3}, new String[]{"LOWER_RIGHT", "UPPER_RIGHT", "LOWER_LEFT", "UPPER_LEFT"}));
    }
}
