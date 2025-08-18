package org.apache.poi.hssf.record.cf;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

public final class FontFormatting implements Duplicatable, GenericRecord {
    private static final BitField CANCELLATION = BitFieldFactory.getInstance(128);
    public static final int FONT_CELL_HEIGHT_PRESERVED = -1;
    private static final short FONT_WEIGHT_BOLD = 700;
    private static final short FONT_WEIGHT_NORMAL = 400;
    private static final int OFFSET_ESCAPEMENT_TYPE = 74;
    private static final int OFFSET_ESCAPEMENT_TYPE_MODIFIED = 92;
    private static final int OFFSET_FONT_COLOR_INDEX = 80;
    private static final int OFFSET_FONT_FORMATING_END = 116;
    private static final int OFFSET_FONT_HEIGHT = 64;
    private static final int OFFSET_FONT_NAME = 0;
    private static final int OFFSET_FONT_OPTIONS = 68;
    private static final int OFFSET_FONT_WEIGHT = 72;
    private static final int OFFSET_FONT_WEIGHT_MODIFIED = 100;
    private static final int OFFSET_NOT_USED1 = 104;
    private static final int OFFSET_NOT_USED2 = 108;
    private static final int OFFSET_NOT_USED3 = 112;
    private static final int OFFSET_OPTION_FLAGS = 88;
    private static final int OFFSET_UNDERLINE_TYPE = 76;
    private static final int OFFSET_UNDERLINE_TYPE_MODIFIED = 96;
    private static final BitField OUTLINE = BitFieldFactory.getInstance(8);
    private static final BitField POSTURE = BitFieldFactory.getInstance(2);
    private static final int RAW_DATA_SIZE = 118;
    private static final BitField SHADOW = BitFieldFactory.getInstance(16);
    private final byte[] _rawData;

    public int getDataLength() {
        return 118;
    }

    public FontFormatting() {
        this._rawData = new byte[118];
        setFontHeight(-1);
        setItalic(false);
        setFontWieghtModified(false);
        setOutline(false);
        setShadow(false);
        setStrikeout(false);
        setEscapementType(0);
        setUnderlineType(0);
        setFontColorIndex(-1);
        setFontStyleModified(false);
        setFontOutlineModified(false);
        setFontShadowModified(false);
        setFontCancellationModified(false);
        setEscapementTypeModified(false);
        setUnderlineTypeModified(false);
        setShort(0, 0);
        setInt(104, 1);
        setInt(108, 0);
        setInt(112, Integer.MAX_VALUE);
        setShort(116, 1);
    }

    public FontFormatting(FontFormatting fontFormatting) {
        byte[] bArr = new byte[118];
        this._rawData = bArr;
        System.arraycopy(fontFormatting._rawData, 0, bArr, 0, 118);
    }

    public FontFormatting(RecordInputStream recordInputStream) {
        byte[] bArr = new byte[118];
        this._rawData = bArr;
        recordInputStream.readFully(bArr);
    }

    private short getShort(int i) {
        return LittleEndian.getShort(this._rawData, i);
    }

    private void setShort(int i, int i2) {
        LittleEndian.putShort(this._rawData, i, (short) i2);
    }

    private int getInt(int i) {
        return LittleEndian.getInt(this._rawData, i);
    }

    private void setInt(int i, int i2) {
        LittleEndian.putInt(this._rawData, i, i2);
    }

    public byte[] getRawRecord() {
        return this._rawData;
    }

    public void setFontHeight(int i) {
        setInt(64, i);
    }

    public int getFontHeight() {
        return getInt(64);
    }

    private void setFontOption(boolean z, BitField bitField) {
        setInt(68, bitField.setBoolean(getInt(68), z));
    }

    private boolean getFontOption(BitField bitField) {
        return bitField.isSet(getInt(68));
    }

    public void setItalic(boolean z) {
        setFontOption(z, POSTURE);
    }

    public boolean isItalic() {
        return getFontOption(POSTURE);
    }

    public void setOutline(boolean z) {
        setFontOption(z, OUTLINE);
    }

    public boolean isOutlineOn() {
        return getFontOption(OUTLINE);
    }

    public void setShadow(boolean z) {
        setFontOption(z, SHADOW);
    }

    public boolean isShadowOn() {
        return getFontOption(SHADOW);
    }

    public void setStrikeout(boolean z) {
        setFontOption(z, CANCELLATION);
    }

    public boolean isStruckout() {
        return getFontOption(CANCELLATION);
    }

    private void setFontWeight(short s) {
        setShort(72, Math.max(100, Math.min(1000, s)));
    }

    public void setBold(boolean z) {
        setFontWeight(z ? FONT_WEIGHT_BOLD : FONT_WEIGHT_NORMAL);
    }

    public short getFontWeight() {
        return getShort(72);
    }

    public boolean isBold() {
        return getFontWeight() == 700;
    }

    public short getEscapementType() {
        return getShort(74);
    }

    public void setEscapementType(short s) {
        setShort(74, s);
    }

    public short getUnderlineType() {
        return getShort(76);
    }

    public void setUnderlineType(short s) {
        setShort(76, s);
    }

    public short getFontColorIndex() {
        return (short) getInt(80);
    }

    public void setFontColorIndex(short s) {
        setInt(80, s);
    }

    private boolean getOptionFlag(BitField bitField) {
        return bitField.getValue(getInt(88)) == 0;
    }

    private void setOptionFlag(boolean z, BitField bitField) {
        setInt(88, bitField.setValue(getInt(88), z ^ true ? 1 : 0));
    }

    public boolean isFontStyleModified() {
        return getOptionFlag(POSTURE);
    }

    public void setFontStyleModified(boolean z) {
        setOptionFlag(z, POSTURE);
    }

    public boolean isFontOutlineModified() {
        return getOptionFlag(OUTLINE);
    }

    public void setFontOutlineModified(boolean z) {
        setOptionFlag(z, OUTLINE);
    }

    public boolean isFontShadowModified() {
        return getOptionFlag(SHADOW);
    }

    public void setFontShadowModified(boolean z) {
        setOptionFlag(z, SHADOW);
    }

    public void setFontCancellationModified(boolean z) {
        setOptionFlag(z, CANCELLATION);
    }

    public boolean isFontCancellationModified() {
        return getOptionFlag(CANCELLATION);
    }

    public void setEscapementTypeModified(boolean z) {
        setInt(92, z ^ true ? 1 : 0);
    }

    public boolean isEscapementTypeModified() {
        return getInt(92) == 0;
    }

    public void setUnderlineTypeModified(boolean z) {
        setInt(96, z ^ true ? 1 : 0);
    }

    public boolean isUnderlineTypeModified() {
        return getInt(96) == 0;
    }

    public void setFontWieghtModified(boolean z) {
        setInt(100, z ^ true ? 1 : 0);
    }

    public boolean isFontWeightModified() {
        return getInt(100) == 0;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("fontHeight", new FontFormatting$$ExternalSyntheticLambda0(this));
        FontFormatting$$ExternalSyntheticLambda1 fontFormatting$$ExternalSyntheticLambda1 = new FontFormatting$$ExternalSyntheticLambda1(this);
        BitField bitField = POSTURE;
        BitField bitField2 = OUTLINE;
        BitField bitField3 = SHADOW;
        BitField bitField4 = CANCELLATION;
        linkedHashMap.put("options", GenericRecordUtil.getBitsAsString((Supplier<Number>) fontFormatting$$ExternalSyntheticLambda1, new BitField[]{bitField, bitField2, bitField3, bitField4}, new String[]{"POSTURE_MODIFIED", "OUTLINE_MODIFIED", "SHADOW_MODIFIED", "STRUCKOUT_MODIFIED"}));
        linkedHashMap.put("fontOptions", GenericRecordUtil.getBitsAsString((Supplier<Number>) new FontFormatting$$ExternalSyntheticLambda2(this), new BitField[]{bitField, bitField2, bitField3, bitField4}, new String[]{"ITALIC", "OUTLINE", "SHADOW", "STRUCKOUT"}));
        linkedHashMap.put("fontWEightModified", new FontFormatting$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("fontWeight", GenericRecordUtil.getEnumBitsAsString(new FontFormatting$$ExternalSyntheticLambda4(this), new int[]{FontHeader.REGULAR_WEIGHT, TypedValues.TransitionType.TYPE_DURATION}, new String[]{"NORMAL", "BOLD"}));
        linkedHashMap.put("escapementTypeModified", new FontFormatting$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("escapementType", new FontFormatting$$ExternalSyntheticLambda6(this));
        linkedHashMap.put("underlineTypeModified", new FontFormatting$$ExternalSyntheticLambda7(this));
        linkedHashMap.put("underlineType", new FontFormatting$$ExternalSyntheticLambda8(this));
        linkedHashMap.put("colorIndex", new FontFormatting$$ExternalSyntheticLambda9(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-cf-FontFormatting  reason: not valid java name */
    public /* synthetic */ Number m2120lambda$getGenericProperties$0$orgapachepoihssfrecordcfFontFormatting() {
        return Integer.valueOf(getInt(88));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-cf-FontFormatting  reason: not valid java name */
    public /* synthetic */ Number m2121lambda$getGenericProperties$1$orgapachepoihssfrecordcfFontFormatting() {
        return Integer.valueOf(getInt(68));
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public FontFormatting copy() {
        return new FontFormatting(this);
    }
}
