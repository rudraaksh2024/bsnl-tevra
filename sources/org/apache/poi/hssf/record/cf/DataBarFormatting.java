package org.apache.poi.hssf.record.cf;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class DataBarFormatting implements Duplicatable, GenericRecord {
    private static final BitField ICON_ONLY = BitFieldFactory.getInstance(1);
    private static final Logger LOG = LogManager.getLogger((Class<?>) DataBarFormatting.class);
    private static final BitField REVERSED = BitFieldFactory.getInstance(4);
    private ExtendedColor color;
    private byte options;
    private byte percentMax;
    private byte percentMin;
    private DataBarThreshold thresholdMax;
    private DataBarThreshold thresholdMin;

    public DataBarFormatting() {
        this.options = 2;
    }

    public DataBarFormatting(DataBarFormatting dataBarFormatting) {
        this.options = dataBarFormatting.options;
        this.percentMin = dataBarFormatting.percentMin;
        this.percentMax = dataBarFormatting.percentMax;
        ExtendedColor extendedColor = dataBarFormatting.color;
        DataBarThreshold dataBarThreshold = null;
        this.color = extendedColor == null ? null : extendedColor.copy();
        DataBarThreshold dataBarThreshold2 = dataBarFormatting.thresholdMin;
        this.thresholdMin = dataBarThreshold2 == null ? null : dataBarThreshold2.copy();
        DataBarThreshold dataBarThreshold3 = dataBarFormatting.thresholdMax;
        this.thresholdMax = dataBarThreshold3 != null ? dataBarThreshold3.copy() : dataBarThreshold;
    }

    public DataBarFormatting(LittleEndianInput littleEndianInput) {
        littleEndianInput.readShort();
        littleEndianInput.readByte();
        this.options = littleEndianInput.readByte();
        this.percentMin = littleEndianInput.readByte();
        this.percentMax = littleEndianInput.readByte();
        byte b = this.percentMin;
        if (b < 0 || b > 100) {
            LOG.atWarn().log("Inconsistent Minimum Percentage found {}", (Object) Unbox.box(this.percentMin));
        }
        byte b2 = this.percentMax;
        if (b2 < 0 || b2 > 100) {
            LOG.atWarn().log("Inconsistent Maximum Percentage found {}", (Object) Unbox.box(this.percentMax));
        }
        this.color = new ExtendedColor(littleEndianInput);
        this.thresholdMin = new DataBarThreshold(littleEndianInput);
        this.thresholdMax = new DataBarThreshold(littleEndianInput);
    }

    public boolean isIconOnly() {
        return getOptionFlag(ICON_ONLY);
    }

    public void setIconOnly(boolean z) {
        setOptionFlag(z, ICON_ONLY);
    }

    public boolean isReversed() {
        return getOptionFlag(REVERSED);
    }

    public void setReversed(boolean z) {
        setOptionFlag(z, REVERSED);
    }

    private boolean getOptionFlag(BitField bitField) {
        return bitField.getValue(this.options) != 0;
    }

    private void setOptionFlag(boolean z, BitField bitField) {
        this.options = bitField.setByteBoolean(this.options, z);
    }

    public byte getPercentMin() {
        return this.percentMin;
    }

    public void setPercentMin(byte b) {
        this.percentMin = b;
    }

    public byte getPercentMax() {
        return this.percentMax;
    }

    public void setPercentMax(byte b) {
        this.percentMax = b;
    }

    public ExtendedColor getColor() {
        return this.color;
    }

    public void setColor(ExtendedColor extendedColor) {
        this.color = extendedColor;
    }

    public DataBarThreshold getThresholdMin() {
        return this.thresholdMin;
    }

    public void setThresholdMin(DataBarThreshold dataBarThreshold) {
        this.thresholdMin = dataBarThreshold;
    }

    public DataBarThreshold getThresholdMax() {
        return this.thresholdMax;
    }

    public void setThresholdMax(DataBarThreshold dataBarThreshold) {
        this.thresholdMax = dataBarThreshold;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("options", GenericRecordUtil.getBitsAsString((Supplier<Number>) new DataBarFormatting$$ExternalSyntheticLambda0(this), new BitField[]{ICON_ONLY, REVERSED}, new String[]{"ICON_ONLY", "REVERSED"}), TypedValues.Custom.S_COLOR, new DataBarFormatting$$ExternalSyntheticLambda1(this), "percentMin", new DataBarFormatting$$ExternalSyntheticLambda2(this), "percentMax", new DataBarFormatting$$ExternalSyntheticLambda3(this), "thresholdMin", new DataBarFormatting$$ExternalSyntheticLambda4(this), "thresholdMax", new DataBarFormatting$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-cf-DataBarFormatting  reason: not valid java name */
    public /* synthetic */ Number m2119lambda$getGenericProperties$0$orgapachepoihssfrecordcfDataBarFormatting() {
        return Byte.valueOf(this.options);
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public DataBarFormatting copy() {
        return new DataBarFormatting(this);
    }

    public int getDataLength() {
        return this.color.getDataLength() + 6 + this.thresholdMin.getDataLength() + this.thresholdMax.getDataLength();
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(0);
        littleEndianOutput.writeByte(0);
        littleEndianOutput.writeByte(this.options);
        littleEndianOutput.writeByte(this.percentMin);
        littleEndianOutput.writeByte(this.percentMax);
        this.color.serialize(littleEndianOutput);
        this.thresholdMin.serialize(littleEndianOutput);
        this.thresholdMax.serialize(littleEndianOutput);
    }
}
