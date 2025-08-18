package org.apache.poi.ss.formula.ptg;

import org.apache.poi.util.LittleEndianInput;

public final class AreaNPtg extends Area2DPtgBase {
    public static final short sid = 45;

    public byte getSid() {
        return 45;
    }

    public AreaNPtg(AreaNPtg areaNPtg) {
        super((Area2DPtgBase) areaNPtg);
    }

    public AreaNPtg(LittleEndianInput littleEndianInput) {
        super(littleEndianInput);
    }

    public AreaNPtg copy() {
        return new AreaNPtg(this);
    }
}
