package org.apache.poi.hssf.record.cf;

import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;
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

public final class ColorGradientFormatting implements Duplicatable, GenericRecord {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) ColorGradientFormatting.class);
    private static final BitField background = BitFieldFactory.getInstance(2);
    private static final BitField clamp = BitFieldFactory.getInstance(1);
    private ExtendedColor[] colors;
    private final byte options;
    private ColorGradientThreshold[] thresholds;

    public ColorGradientFormatting() {
        this.options = 3;
        this.thresholds = new ColorGradientThreshold[3];
        this.colors = new ExtendedColor[3];
    }

    public ColorGradientFormatting(ColorGradientFormatting colorGradientFormatting) {
        this.options = colorGradientFormatting.options;
        ColorGradientThreshold[] colorGradientThresholdArr = colorGradientFormatting.thresholds;
        if (colorGradientThresholdArr != null) {
            this.thresholds = (ColorGradientThreshold[]) Stream.of(colorGradientThresholdArr).map(new ColorGradientFormatting$$ExternalSyntheticLambda4()).toArray(new ColorGradientFormatting$$ExternalSyntheticLambda5());
        }
        ExtendedColor[] extendedColorArr = colorGradientFormatting.colors;
        if (extendedColorArr != null) {
            this.colors = (ExtendedColor[]) Stream.of(extendedColorArr).map(new ColorGradientFormatting$$ExternalSyntheticLambda6()).toArray(new ColorGradientFormatting$$ExternalSyntheticLambda7());
        }
    }

    static /* synthetic */ ColorGradientThreshold[] lambda$new$0(int i) {
        return new ColorGradientThreshold[i];
    }

    static /* synthetic */ ExtendedColor[] lambda$new$1(int i) {
        return new ExtendedColor[i];
    }

    public ColorGradientFormatting(LittleEndianInput littleEndianInput) {
        littleEndianInput.readShort();
        littleEndianInput.readByte();
        int readByte = littleEndianInput.readByte();
        int readByte2 = littleEndianInput.readByte();
        if (readByte != readByte2) {
            LOGGER.atWarn().log("Inconsistent Color Gradient definition, found {} vs {} entries", Unbox.box(readByte), Unbox.box(readByte2));
        }
        this.options = littleEndianInput.readByte();
        this.thresholds = new ColorGradientThreshold[readByte];
        int i = 0;
        while (true) {
            ColorGradientThreshold[] colorGradientThresholdArr = this.thresholds;
            if (i >= colorGradientThresholdArr.length) {
                break;
            }
            colorGradientThresholdArr[i] = new ColorGradientThreshold(littleEndianInput);
            i++;
        }
        this.colors = new ExtendedColor[readByte2];
        for (int i2 = 0; i2 < this.colors.length; i2++) {
            littleEndianInput.readDouble();
            this.colors[i2] = new ExtendedColor(littleEndianInput);
        }
    }

    public int getNumControlPoints() {
        return this.thresholds.length;
    }

    public void setNumControlPoints(int i) {
        ColorGradientThreshold[] colorGradientThresholdArr = this.thresholds;
        if (i != colorGradientThresholdArr.length) {
            ColorGradientThreshold[] colorGradientThresholdArr2 = new ColorGradientThreshold[i];
            ExtendedColor[] extendedColorArr = new ExtendedColor[i];
            int min = Math.min(colorGradientThresholdArr.length, i);
            System.arraycopy(this.thresholds, 0, colorGradientThresholdArr2, 0, min);
            System.arraycopy(this.colors, 0, extendedColorArr, 0, min);
            this.thresholds = colorGradientThresholdArr2;
            this.colors = extendedColorArr;
            updateThresholdPositions();
        }
    }

    public ColorGradientThreshold[] getThresholds() {
        return this.thresholds;
    }

    public void setThresholds(ColorGradientThreshold[] colorGradientThresholdArr) {
        this.thresholds = colorGradientThresholdArr == null ? null : (ColorGradientThreshold[]) colorGradientThresholdArr.clone();
        updateThresholdPositions();
    }

    public ExtendedColor[] getColors() {
        return this.colors;
    }

    public void setColors(ExtendedColor[] extendedColorArr) {
        this.colors = extendedColorArr == null ? null : (ExtendedColor[]) extendedColorArr.clone();
    }

    public boolean isClampToCurve() {
        return getOptionFlag(clamp);
    }

    public boolean isAppliesToBackground() {
        return getOptionFlag(background);
    }

    private boolean getOptionFlag(BitField bitField) {
        return bitField.isSet(this.options);
    }

    private void updateThresholdPositions() {
        double length = 1.0d / ((double) (this.thresholds.length - 1));
        int i = 0;
        while (true) {
            ColorGradientThreshold[] colorGradientThresholdArr = this.thresholds;
            if (i < colorGradientThresholdArr.length) {
                colorGradientThresholdArr[i].setPosition(((double) i) * length);
                i++;
            } else {
                return;
            }
        }
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("clampToCurve", new ColorGradientFormatting$$ExternalSyntheticLambda0(this), "background", new ColorGradientFormatting$$ExternalSyntheticLambda1(this), "thresholds", new ColorGradientFormatting$$ExternalSyntheticLambda2(this), "colors", new ColorGradientFormatting$$ExternalSyntheticLambda3(this));
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public ColorGradientFormatting copy() {
        return new ColorGradientFormatting(this);
    }

    public int getDataLength() {
        int i = 6;
        for (ColorGradientThreshold dataLength : this.thresholds) {
            i += dataLength.getDataLength();
        }
        for (ExtendedColor dataLength2 : this.colors) {
            i = i + dataLength2.getDataLength() + 8;
        }
        return i;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(0);
        littleEndianOutput.writeByte(0);
        littleEndianOutput.writeByte(this.thresholds.length);
        littleEndianOutput.writeByte(this.thresholds.length);
        littleEndianOutput.writeByte(this.options);
        for (ColorGradientThreshold serialize : this.thresholds) {
            serialize.serialize(littleEndianOutput);
        }
        double length = 1.0d / ((double) (this.colors.length - 1));
        for (int i = 0; i < this.colors.length; i++) {
            littleEndianOutput.writeDouble(((double) i) * length);
            this.colors[i].serialize(littleEndianOutput);
        }
    }
}
