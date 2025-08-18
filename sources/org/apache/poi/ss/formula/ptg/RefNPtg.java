package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class RefNPtg extends Ref2DPtgBase {
    public static final byte sid = 44;

    public byte getSid() {
        return sid;
    }

    public /* bridge */ /* synthetic */ void write(LittleEndianOutput littleEndianOutput) {
        super.write(littleEndianOutput);
    }

    public RefNPtg(LittleEndianInput littleEndianInput) {
        super(littleEndianInput);
    }

    public RefNPtg(RefNPtg refNPtg) {
        super((Ref2DPtgBase) refNPtg);
    }

    /* access modifiers changed from: protected */
    public final String formatReferenceAsString() {
        StringBuilder sb = new StringBuilder();
        if (isRowRelative()) {
            sb.append("RowOffset: ").append(getRow()).append(" ");
        } else {
            sb.append(getRow() + 1);
        }
        if (isColRelative()) {
            sb.append(" ColOffset: ").append(getColumn());
        } else {
            sb.append(CellReference.convertNumToColString(getColumn()));
        }
        return sb.toString();
    }

    public RefNPtg copy() {
        return new RefNPtg(this);
    }
}
