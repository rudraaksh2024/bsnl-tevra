package org.apache.poi.hssf.record.cf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public abstract class Threshold implements GenericRecord {
    private Formula formula;
    private byte type;
    private Double value;

    public abstract Threshold copy();

    protected Threshold() {
        this.type = (byte) ConditionalFormattingThreshold.RangeType.NUMBER.id;
        this.formula = Formula.create((Ptg[]) null);
        this.value = Double.valueOf(0.0d);
    }

    protected Threshold(Threshold threshold) {
        this.type = threshold.type;
        this.formula = threshold.formula.copy();
        this.value = threshold.value;
    }

    protected Threshold(LittleEndianInput littleEndianInput) {
        this.type = littleEndianInput.readByte();
        short readShort = littleEndianInput.readShort();
        if (readShort > 0) {
            this.formula = Formula.read(readShort, littleEndianInput);
        } else {
            this.formula = Formula.create((Ptg[]) null);
        }
        if (readShort == 0 && this.type != ConditionalFormattingThreshold.RangeType.MIN.id && this.type != ConditionalFormattingThreshold.RangeType.MAX.id) {
            this.value = Double.valueOf(littleEndianInput.readDouble());
        }
    }

    public byte getType() {
        return this.type;
    }

    public void setType(byte b) {
        this.type = b;
        if (b == ConditionalFormattingThreshold.RangeType.MIN.id || b == ConditionalFormattingThreshold.RangeType.MAX.id || b == ConditionalFormattingThreshold.RangeType.FORMULA.id) {
            this.value = null;
        } else if (this.value == null) {
            this.value = Double.valueOf(0.0d);
        }
    }

    public void setType(int i) {
        this.type = (byte) i;
    }

    /* access modifiers changed from: protected */
    public Formula getFormula() {
        return this.formula;
    }

    public Ptg[] getParsedExpression() {
        return this.formula.getTokens();
    }

    public void setParsedExpression(Ptg[] ptgArr) {
        this.formula = Formula.create(ptgArr);
        if (ptgArr.length > 0) {
            this.value = null;
        }
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double d) {
        this.value = d;
    }

    public int getDataLength() {
        int encodedSize = this.formula.getEncodedSize() + 1;
        return this.value != null ? encodedSize + 8 : encodedSize;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("type", new Threshold$$ExternalSyntheticLambda0(this), "formula", new Threshold$$ExternalSyntheticLambda1(this), "value", new Threshold$$ExternalSyntheticLambda2(this));
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(this.type);
        if (this.formula.getTokens().length == 0) {
            littleEndianOutput.writeShort(0);
        } else {
            this.formula.serialize(littleEndianOutput);
        }
        Double d = this.value;
        if (d != null) {
            littleEndianOutput.writeDouble(d.doubleValue());
        }
    }
}
