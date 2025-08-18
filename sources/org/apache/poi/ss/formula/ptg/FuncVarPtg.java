package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class FuncVarPtg extends AbstractFunctionPtg {
    private static final int SIZE = 4;
    public static final OperationPtg SUM = create("SUM", 1);
    private static final BitField ceFunc = BitFieldFactory.getInstance(61440);
    public static final byte sid = 34;
    private final boolean _isCetab;

    public FuncVarPtg copy() {
        return this;
    }

    public byte getSid() {
        return 34;
    }

    public int getSize() {
        return 4;
    }

    private FuncVarPtg(int i, int i2, byte[] bArr, int i3, boolean z) {
        super(i, i2, bArr, i3);
        this._isCetab = z;
    }

    public static FuncVarPtg create(LittleEndianInput littleEndianInput) {
        return create((int) littleEndianInput.readByte(), littleEndianInput.readUShort());
    }

    public static FuncVarPtg create(String str, int i) {
        return create(i, (int) lookupIndex(str));
    }

    private static FuncVarPtg create(int i, int i2) {
        FunctionMetadata functionMetadata;
        BitField bitField = ceFunc;
        boolean isSet = bitField.isSet(i2);
        if (isSet) {
            i2 = bitField.clear(i2);
            functionMetadata = FunctionMetadataRegistry.getCetabFunctionByIndex(i2);
        } else {
            functionMetadata = FunctionMetadataRegistry.getFunctionByIndex(i2);
        }
        int i3 = i2;
        if (functionMetadata != null) {
            return new FuncVarPtg(i3, functionMetadata.getReturnClassCode(), functionMetadata.getParameterClassCodes(), i, isSet);
        }
        return new FuncVarPtg(i3, 32, new byte[]{32}, i, isSet);
    }

    /* access modifiers changed from: protected */
    public String lookupName(short s) {
        return lookupName(s, this._isCetab);
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + 34);
        littleEndianOutput.writeByte(getNumberOfOperands());
        littleEndianOutput.writeShort(getFunctionIndex());
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new FuncVarPtg$$ExternalSyntheticLambda0(this), "cetab", new FuncVarPtg$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-FuncVarPtg  reason: not valid java name */
    public /* synthetic */ Object m2281lambda$getGenericProperties$0$orgapachepoissformulaptgFuncVarPtg() {
        return super.getGenericProperties();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-FuncVarPtg  reason: not valid java name */
    public /* synthetic */ Object m2282lambda$getGenericProperties$1$orgapachepoissformulaptgFuncVarPtg() {
        return Boolean.valueOf(this._isCetab);
    }
}
