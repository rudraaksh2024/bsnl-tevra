package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.util.LittleEndianInput;

public final class AreaPtg extends Area2DPtgBase {
    public static final short sid = 37;

    public byte getSid() {
        return 37;
    }

    public AreaPtg(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4) {
        super(i, i2, i3, i4, z, z2, z3, z4);
    }

    public AreaPtg(AreaPtg areaPtg) {
        super((Area2DPtgBase) areaPtg);
    }

    public AreaPtg(LittleEndianInput littleEndianInput) {
        super(littleEndianInput);
    }

    public AreaPtg(AreaReference areaReference) {
        super(areaReference);
    }

    public AreaPtg copy() {
        return new AreaPtg(this);
    }
}
