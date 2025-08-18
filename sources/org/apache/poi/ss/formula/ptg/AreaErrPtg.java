package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class AreaErrPtg extends OperandPtg {
    public static final byte sid = 43;
    private final int unused1;
    private final int unused2;

    public AreaErrPtg copy() {
        return this;
    }

    public byte getDefaultOperandClass() {
        return 0;
    }

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 9;
    }

    public AreaErrPtg() {
        this.unused1 = 0;
        this.unused2 = 0;
    }

    public AreaErrPtg(LittleEndianInput littleEndianInput) {
        this.unused1 = littleEndianInput.readInt();
        this.unused2 = littleEndianInput.readInt();
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeInt(this.unused1);
        littleEndianOutput.writeInt(this.unused2);
    }

    public String toFormulaString() {
        return FormulaError.REF.getString();
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("unused1", new AreaErrPtg$$ExternalSyntheticLambda0(this), "unused2", new AreaErrPtg$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-AreaErrPtg  reason: not valid java name */
    public /* synthetic */ Object m2265lambda$getGenericProperties$0$orgapachepoissformulaptgAreaErrPtg() {
        return Integer.valueOf(this.unused1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-AreaErrPtg  reason: not valid java name */
    public /* synthetic */ Object m2266lambda$getGenericProperties$1$orgapachepoissformulaptgAreaErrPtg() {
        return Integer.valueOf(this.unused2);
    }
}
