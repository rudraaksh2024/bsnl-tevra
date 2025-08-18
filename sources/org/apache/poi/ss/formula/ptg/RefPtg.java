package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class RefPtg extends Ref2DPtgBase {
    public static final byte sid = 36;

    public byte getSid() {
        return sid;
    }

    public /* bridge */ /* synthetic */ void write(LittleEndianOutput littleEndianOutput) {
        super.write(littleEndianOutput);
    }

    public RefPtg(String str) {
        super(new CellReference(str));
    }

    public RefPtg(RefPtg refPtg) {
        super((Ref2DPtgBase) refPtg);
    }

    public RefPtg(int i, int i2, boolean z, boolean z2) {
        super(i, i2, z, z2);
    }

    public RefPtg(LittleEndianInput littleEndianInput) {
        super(littleEndianInput);
    }

    public RefPtg(CellReference cellReference) {
        super(cellReference);
    }

    public RefPtg copy() {
        return new RefPtg(this);
    }
}
