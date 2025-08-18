package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

abstract class Ref2DPtgBase extends RefPtgBase {
    private static final int SIZE = 5;

    public final int getSize() {
        return 5;
    }

    protected Ref2DPtgBase(int i, int i2, boolean z, boolean z2) {
        setRow(i);
        setColumn(i2);
        setRowRelative(z);
        setColRelative(z2);
    }

    protected Ref2DPtgBase(Ref2DPtgBase ref2DPtgBase) {
        super((RefPtgBase) ref2DPtgBase);
    }

    protected Ref2DPtgBase(LittleEndianInput littleEndianInput) {
        readCoordinates(littleEndianInput);
    }

    protected Ref2DPtgBase(CellReference cellReference) {
        super(cellReference);
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getSid() + getPtgClass());
        writeCoordinates(littleEndianOutput);
    }

    public final String toFormulaString() {
        return formatReferenceAsString();
    }
}
