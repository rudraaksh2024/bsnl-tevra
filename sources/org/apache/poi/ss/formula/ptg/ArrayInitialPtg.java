package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.constant.ConstantValueParser;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

final class ArrayInitialPtg extends Ptg {
    private final int _reserved0;
    private final int _reserved1;
    private final int _reserved2;

    public ArrayInitialPtg copy() {
        return this;
    }

    public byte getSid() {
        return -1;
    }

    public int getSize() {
        return 8;
    }

    public boolean isBaseToken() {
        return false;
    }

    public ArrayInitialPtg(LittleEndianInput littleEndianInput) {
        this._reserved0 = littleEndianInput.readInt();
        this._reserved1 = littleEndianInput.readUShort();
        this._reserved2 = littleEndianInput.readUByte();
    }

    private static RuntimeException invalid() {
        throw new IllegalStateException("This object is a partially initialised tArray, and cannot be used as a Ptg");
    }

    public byte getDefaultOperandClass() {
        throw invalid();
    }

    public String toFormulaString() {
        throw invalid();
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        throw invalid();
    }

    public ArrayPtg finishReading(LittleEndianInput littleEndianInput) {
        int readUByte = littleEndianInput.readUByte() + 1;
        short readShort = (short) (littleEndianInput.readShort() + 1);
        ArrayPtg arrayPtg = new ArrayPtg(this._reserved0, this._reserved1, this._reserved2, readUByte, readShort, ConstantValueParser.parse(littleEndianInput, readShort * readUByte));
        arrayPtg.setClass(getPtgClass());
        return arrayPtg;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved0", new ArrayInitialPtg$$ExternalSyntheticLambda0(this), "reserved1", new ArrayInitialPtg$$ExternalSyntheticLambda1(this), "reserved2", new ArrayInitialPtg$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-ArrayInitialPtg  reason: not valid java name */
    public /* synthetic */ Object m2267lambda$getGenericProperties$0$orgapachepoissformulaptgArrayInitialPtg() {
        return Integer.valueOf(this._reserved0);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-ArrayInitialPtg  reason: not valid java name */
    public /* synthetic */ Object m2268lambda$getGenericProperties$1$orgapachepoissformulaptgArrayInitialPtg() {
        return Integer.valueOf(this._reserved1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-ss-formula-ptg-ArrayInitialPtg  reason: not valid java name */
    public /* synthetic */ Object m2269lambda$getGenericProperties$2$orgapachepoissformulaptgArrayInitialPtg() {
        return Integer.valueOf(this._reserved2);
    }
}
