package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class WSBoolRecord extends StandardRecord {
    private static final BitField alternateexpression = BitFieldFactory.getInstance(64);
    private static final BitField alternateformula = BitFieldFactory.getInstance(128);
    private static final BitField applystyles = BitFieldFactory.getInstance(32);
    private static final BitField autobreaks = BitFieldFactory.getInstance(1);
    private static final BitField dialog = BitFieldFactory.getInstance(16);
    private static final BitField displayguts = BitFieldFactory.getInstance(6);
    private static final BitField fittopage = BitFieldFactory.getInstance(1);
    private static final BitField rowsumsbelow = BitFieldFactory.getInstance(64);
    private static final BitField rowsumsright = BitFieldFactory.getInstance(128);
    public static final short sid = 129;
    private byte field_1_wsbool;
    private byte field_2_wsbool;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 2;
    }

    public short getSid() {
        return 129;
    }

    public WSBoolRecord() {
    }

    public WSBoolRecord(WSBoolRecord wSBoolRecord) {
        super(wSBoolRecord);
        this.field_1_wsbool = wSBoolRecord.field_1_wsbool;
        this.field_2_wsbool = wSBoolRecord.field_2_wsbool;
    }

    public WSBoolRecord(RecordInputStream recordInputStream) {
        byte[] readRemainder = recordInputStream.readRemainder();
        this.field_1_wsbool = readRemainder[1];
        this.field_2_wsbool = readRemainder[0];
    }

    public void setWSBool1(byte b) {
        this.field_1_wsbool = b;
    }

    public void setAutobreaks(boolean z) {
        this.field_1_wsbool = autobreaks.setByteBoolean(this.field_1_wsbool, z);
    }

    public void setDialog(boolean z) {
        this.field_1_wsbool = dialog.setByteBoolean(this.field_1_wsbool, z);
    }

    public void setRowSumsBelow(boolean z) {
        this.field_1_wsbool = rowsumsbelow.setByteBoolean(this.field_1_wsbool, z);
    }

    public void setRowSumsRight(boolean z) {
        this.field_1_wsbool = rowsumsright.setByteBoolean(this.field_1_wsbool, z);
    }

    public void setWSBool2(byte b) {
        this.field_2_wsbool = b;
    }

    public void setFitToPage(boolean z) {
        this.field_2_wsbool = fittopage.setByteBoolean(this.field_2_wsbool, z);
    }

    public void setDisplayGuts(boolean z) {
        this.field_2_wsbool = displayguts.setByteBoolean(this.field_2_wsbool, z);
    }

    public void setAlternateExpression(boolean z) {
        this.field_2_wsbool = alternateexpression.setByteBoolean(this.field_2_wsbool, z);
    }

    public void setAlternateFormula(boolean z) {
        this.field_2_wsbool = alternateformula.setByteBoolean(this.field_2_wsbool, z);
    }

    public byte getWSBool1() {
        return this.field_1_wsbool;
    }

    public boolean getAutobreaks() {
        return autobreaks.isSet(this.field_1_wsbool);
    }

    public boolean getDialog() {
        return dialog.isSet(this.field_1_wsbool);
    }

    public boolean getRowSumsBelow() {
        return rowsumsbelow.isSet(this.field_1_wsbool);
    }

    public boolean getRowSumsRight() {
        return rowsumsright.isSet(this.field_1_wsbool);
    }

    public byte getWSBool2() {
        return this.field_2_wsbool;
    }

    public boolean getFitToPage() {
        return fittopage.isSet(this.field_2_wsbool);
    }

    public boolean getDisplayGuts() {
        return displayguts.isSet(this.field_2_wsbool);
    }

    public boolean getAlternateExpression() {
        return alternateexpression.isSet(this.field_2_wsbool);
    }

    public boolean getAlternateFormula() {
        return alternateformula.isSet(this.field_2_wsbool);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getWSBool2());
        littleEndianOutput.writeByte(getWSBool1());
    }

    public WSBoolRecord copy() {
        return new WSBoolRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.WS_BOOL;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("wsbool1", GenericRecordUtil.getBitsAsString((Supplier<Number>) new WSBoolRecord$$ExternalSyntheticLambda0(this), new BitField[]{autobreaks, dialog, applystyles, rowsumsbelow, rowsumsright}, new String[]{"AUTO_BREAKS", "DIALOG", "APPLY_STYLES", "ROW_SUMS_BELOW", "ROW_SUMS_RIGHT"}), "wsbool2", GenericRecordUtil.getBitsAsString((Supplier<Number>) new WSBoolRecord$$ExternalSyntheticLambda1(this), new BitField[]{fittopage, displayguts, alternateexpression, alternateformula}, new String[]{"FIT_TO_PAGE", "DISPLAY_GUTS", "ALTERNATE_EXPRESSION", "ALTERNATE_FORMULA"}));
    }
}
