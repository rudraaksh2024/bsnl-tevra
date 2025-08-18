package org.apache.poi.hssf.record.cf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class ColorGradientThreshold extends Threshold implements Duplicatable, GenericRecord {
    private double position;

    public ColorGradientThreshold() {
        this.position = 0.0d;
    }

    public ColorGradientThreshold(ColorGradientThreshold colorGradientThreshold) {
        super((Threshold) colorGradientThreshold);
        this.position = colorGradientThreshold.position;
    }

    public ColorGradientThreshold(LittleEndianInput littleEndianInput) {
        super(littleEndianInput);
        this.position = littleEndianInput.readDouble();
    }

    public double getPosition() {
        return this.position;
    }

    public void setPosition(double d) {
        this.position = d;
    }

    public int getDataLength() {
        return super.getDataLength() + 8;
    }

    public ColorGradientThreshold copy() {
        return new ColorGradientThreshold(this);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        super.serialize(littleEndianOutput);
        littleEndianOutput.writeDouble(this.position);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("position", new ColorGradientThreshold$$ExternalSyntheticLambda0(this));
    }
}
