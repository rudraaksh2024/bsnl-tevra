package org.apache.poi.hssf.record;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class PrintSetupRecord extends StandardRecord {
    private static final BitField draft = BitFieldFactory.getInstance(16);
    private static final BitField landscape = BitFieldFactory.getInstance(2);
    private static final BitField lefttoright = BitFieldFactory.getInstance(1);
    private static final BitField noOrientation = BitFieldFactory.getInstance(64);
    private static final BitField nocolor = BitFieldFactory.getInstance(8);
    private static final BitField notes = BitFieldFactory.getInstance(32);
    public static final short sid = 161;
    private static final BitField usepage = BitFieldFactory.getInstance(128);
    private static final BitField validsettings = BitFieldFactory.getInstance(4);
    private double field_10_footermargin;
    private short field_11_copies;
    private short field_1_paper_size;
    private short field_2_scale;
    private short field_3_page_start;
    private short field_4_fit_width;
    private short field_5_fit_height;
    private short field_6_options;
    private short field_7_hresolution;
    private short field_8_vresolution;
    private double field_9_headermargin;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 34;
    }

    public short getSid() {
        return 161;
    }

    public PrintSetupRecord() {
    }

    public PrintSetupRecord(PrintSetupRecord printSetupRecord) {
        super(printSetupRecord);
        this.field_1_paper_size = printSetupRecord.field_1_paper_size;
        this.field_2_scale = printSetupRecord.field_2_scale;
        this.field_3_page_start = printSetupRecord.field_3_page_start;
        this.field_4_fit_width = printSetupRecord.field_4_fit_width;
        this.field_5_fit_height = printSetupRecord.field_5_fit_height;
        this.field_6_options = printSetupRecord.field_6_options;
        this.field_7_hresolution = printSetupRecord.field_7_hresolution;
        this.field_8_vresolution = printSetupRecord.field_8_vresolution;
        this.field_9_headermargin = printSetupRecord.field_9_headermargin;
        this.field_10_footermargin = printSetupRecord.field_10_footermargin;
        this.field_11_copies = printSetupRecord.field_11_copies;
    }

    public PrintSetupRecord(RecordInputStream recordInputStream) {
        this.field_1_paper_size = recordInputStream.readShort();
        this.field_2_scale = recordInputStream.readShort();
        this.field_3_page_start = recordInputStream.readShort();
        this.field_4_fit_width = recordInputStream.readShort();
        this.field_5_fit_height = recordInputStream.readShort();
        this.field_6_options = recordInputStream.readShort();
        this.field_7_hresolution = recordInputStream.readShort();
        this.field_8_vresolution = recordInputStream.readShort();
        this.field_9_headermargin = recordInputStream.readDouble();
        this.field_10_footermargin = recordInputStream.readDouble();
        this.field_11_copies = recordInputStream.readShort();
    }

    public void setPaperSize(short s) {
        this.field_1_paper_size = s;
    }

    public void setScale(short s) {
        this.field_2_scale = s;
    }

    public void setPageStart(short s) {
        this.field_3_page_start = s;
    }

    public void setFitWidth(short s) {
        this.field_4_fit_width = s;
    }

    public void setFitHeight(short s) {
        this.field_5_fit_height = s;
    }

    public void setOptions(short s) {
        this.field_6_options = s;
    }

    public void setLeftToRight(boolean z) {
        this.field_6_options = lefttoright.setShortBoolean(this.field_6_options, z);
    }

    public void setLandscape(boolean z) {
        this.field_6_options = landscape.setShortBoolean(this.field_6_options, z);
    }

    public void setValidSettings(boolean z) {
        this.field_6_options = validsettings.setShortBoolean(this.field_6_options, z);
    }

    public void setNoColor(boolean z) {
        this.field_6_options = nocolor.setShortBoolean(this.field_6_options, z);
    }

    public void setDraft(boolean z) {
        this.field_6_options = draft.setShortBoolean(this.field_6_options, z);
    }

    public void setNotes(boolean z) {
        this.field_6_options = notes.setShortBoolean(this.field_6_options, z);
    }

    public void setNoOrientation(boolean z) {
        this.field_6_options = noOrientation.setShortBoolean(this.field_6_options, z);
    }

    public void setUsePage(boolean z) {
        this.field_6_options = usepage.setShortBoolean(this.field_6_options, z);
    }

    public void setHResolution(short s) {
        this.field_7_hresolution = s;
    }

    public void setVResolution(short s) {
        this.field_8_vresolution = s;
    }

    public void setHeaderMargin(double d) {
        this.field_9_headermargin = d;
    }

    public void setFooterMargin(double d) {
        this.field_10_footermargin = d;
    }

    public void setCopies(short s) {
        this.field_11_copies = s;
    }

    public short getPaperSize() {
        return this.field_1_paper_size;
    }

    public short getScale() {
        return this.field_2_scale;
    }

    public short getPageStart() {
        return this.field_3_page_start;
    }

    public short getFitWidth() {
        return this.field_4_fit_width;
    }

    public short getFitHeight() {
        return this.field_5_fit_height;
    }

    public short getOptions() {
        return this.field_6_options;
    }

    public boolean getLeftToRight() {
        return lefttoright.isSet(this.field_6_options);
    }

    public boolean getLandscape() {
        return landscape.isSet(this.field_6_options);
    }

    public boolean getValidSettings() {
        return validsettings.isSet(this.field_6_options);
    }

    public boolean getNoColor() {
        return nocolor.isSet(this.field_6_options);
    }

    public boolean getDraft() {
        return draft.isSet(this.field_6_options);
    }

    public boolean getNotes() {
        return notes.isSet(this.field_6_options);
    }

    public boolean getNoOrientation() {
        return noOrientation.isSet(this.field_6_options);
    }

    public boolean getUsePage() {
        return usepage.isSet(this.field_6_options);
    }

    public short getHResolution() {
        return this.field_7_hresolution;
    }

    public short getVResolution() {
        return this.field_8_vresolution;
    }

    public double getHeaderMargin() {
        return this.field_9_headermargin;
    }

    public double getFooterMargin() {
        return this.field_10_footermargin;
    }

    public short getCopies() {
        return this.field_11_copies;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getPaperSize());
        littleEndianOutput.writeShort(getScale());
        littleEndianOutput.writeShort(getPageStart());
        littleEndianOutput.writeShort(getFitWidth());
        littleEndianOutput.writeShort(getFitHeight());
        littleEndianOutput.writeShort(getOptions());
        littleEndianOutput.writeShort(getHResolution());
        littleEndianOutput.writeShort(getVResolution());
        littleEndianOutput.writeDouble(getHeaderMargin());
        littleEndianOutput.writeDouble(getFooterMargin());
        littleEndianOutput.writeShort(getCopies());
    }

    public PrintSetupRecord copy() {
        return new PrintSetupRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.PRINT_SETUP;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("paperSize", new PrintSetupRecord$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("scale", new PrintSetupRecord$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("pageStart", new PrintSetupRecord$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("fitWidth", new PrintSetupRecord$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("fitHeight", new PrintSetupRecord$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("options", GenericRecordUtil.getBitsAsString((Supplier<Number>) new PrintSetupRecord$$ExternalSyntheticLambda7(this), new BitField[]{lefttoright, landscape, validsettings, nocolor, draft, notes, noOrientation, usepage}, new String[]{"lefttoright", "landscape", "validsettings", "nocolor", "draft", "notes", "noOrientation", "usepage"}));
        linkedHashMap.put("hResolution", new PrintSetupRecord$$ExternalSyntheticLambda8(this));
        linkedHashMap.put("vResolution", new PrintSetupRecord$$ExternalSyntheticLambda9(this));
        linkedHashMap.put("headerMargin", new PrintSetupRecord$$ExternalSyntheticLambda10(this));
        linkedHashMap.put("footerMargin", new PrintSetupRecord$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("copies", new PrintSetupRecord$$ExternalSyntheticLambda2(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
