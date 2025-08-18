package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public abstract class Area2DPtgBase extends AreaPtgBase {
    private static final int SIZE = 9;

    public final int getSize() {
        return 9;
    }

    protected Area2DPtgBase(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4) {
        super(i, i2, i3, i4, z, z2, z3, z4);
    }

    protected Area2DPtgBase(Area2DPtgBase area2DPtgBase) {
        super((AreaPtgBase) area2DPtgBase);
    }

    protected Area2DPtgBase(AreaReference areaReference) {
        super(areaReference);
    }

    protected Area2DPtgBase(LittleEndianInput littleEndianInput) {
        readCoordinates(littleEndianInput);
    }

    public final void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getSid() + getPtgClass());
        writeCoordinates(littleEndianOutput);
    }

    public final String toFormulaString() {
        return formatReferenceAsString();
    }
}
