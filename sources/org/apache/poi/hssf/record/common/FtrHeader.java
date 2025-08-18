package org.apache.poi.hssf.record.common;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class FtrHeader implements Duplicatable, GenericRecord {
    private CellRangeAddress associatedRange;
    private short grbitFrt;
    private short recordType;

    public static int getDataSize() {
        return 12;
    }

    public FtrHeader() {
        this.associatedRange = new CellRangeAddress(0, 0, 0, 0);
    }

    public FtrHeader(FtrHeader ftrHeader) {
        this.recordType = ftrHeader.recordType;
        this.grbitFrt = ftrHeader.grbitFrt;
        this.associatedRange = ftrHeader.associatedRange.copy();
    }

    public FtrHeader(RecordInputStream recordInputStream) {
        this.recordType = recordInputStream.readShort();
        this.grbitFrt = recordInputStream.readShort();
        this.associatedRange = new CellRangeAddress(recordInputStream);
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.recordType);
        littleEndianOutput.writeShort(this.grbitFrt);
        this.associatedRange.serialize(littleEndianOutput);
    }

    public short getRecordType() {
        return this.recordType;
    }

    public void setRecordType(short s) {
        this.recordType = s;
    }

    public short getGrbitFrt() {
        return this.grbitFrt;
    }

    public void setGrbitFrt(short s) {
        this.grbitFrt = s;
    }

    public CellRangeAddress getAssociatedRange() {
        return this.associatedRange;
    }

    public void setAssociatedRange(CellRangeAddress cellRangeAddress) {
        this.associatedRange = cellRangeAddress;
    }

    public FtrHeader copy() {
        return new FtrHeader(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("recordType", new FtrHeader$$ExternalSyntheticLambda0(this), "grbitFrt", new FtrHeader$$ExternalSyntheticLambda1(this), "associatedRange", new FtrHeader$$ExternalSyntheticLambda2(this));
    }
}
