package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class RefErrorPtg extends OperandPtg {
    private static final int SIZE = 5;
    public static final byte sid = 42;
    private final int field_1_reserved;

    public byte getDefaultOperandClass() {
        return 0;
    }

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 5;
    }

    public RefErrorPtg() {
        this.field_1_reserved = 0;
    }

    public RefErrorPtg(RefErrorPtg refErrorPtg) {
        super(refErrorPtg);
        this.field_1_reserved = refErrorPtg.field_1_reserved;
    }

    public RefErrorPtg(LittleEndianInput littleEndianInput) {
        this.field_1_reserved = littleEndianInput.readInt();
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeInt(this.field_1_reserved);
    }

    public String toFormulaString() {
        return FormulaError.REF.getString();
    }

    public RefErrorPtg copy() {
        return new RefErrorPtg(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved", new RefErrorPtg$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-RefErrorPtg  reason: not valid java name */
    public /* synthetic */ Object m2285lambda$getGenericProperties$0$orgapachepoissformulaptgRefErrorPtg() {
        return Integer.valueOf(this.field_1_reserved);
    }
}
