package org.apache.poi.ss.formula.ptg;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;
import org.apache.poi.util.GenericRecordUtil;

public abstract class AbstractFunctionPtg extends OperationPtg {
    private static final short FUNCTION_INDEX_EXTERNAL = 255;
    public static final String FUNCTION_NAME_IF = "IF";
    private final short _functionIndex;
    private final int _numberOfArgs;
    private final byte[] paramClass;
    private final byte returnClass;

    public abstract int getSize();

    public final boolean isBaseToken() {
        return false;
    }

    protected AbstractFunctionPtg(int i, int i2, byte[] bArr, int i3) {
        this._numberOfArgs = i3;
        if (i < -32768 || i > 32767) {
            throw new RuntimeException("functionIndex " + i + " cannot be cast to short");
        }
        this._functionIndex = (short) i;
        if (i2 < -128 || i2 > 127) {
            throw new RuntimeException("pReturnClass " + i2 + " cannot be cast to byte");
        }
        this.returnClass = (byte) i2;
        this.paramClass = bArr;
    }

    public final short getFunctionIndex() {
        return this._functionIndex;
    }

    public final int getNumberOfOperands() {
        return this._numberOfArgs;
    }

    public final String getName() {
        return lookupName(this._functionIndex);
    }

    public final boolean isExternalFunction() {
        return this._functionIndex == 255;
    }

    public final String toFormulaString() {
        return getName();
    }

    public String toFormulaString(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        if (isExternalFunction()) {
            sb.append(strArr[0]);
            appendArgs(sb, 1, strArr);
        } else {
            sb.append(getName());
            appendArgs(sb, 0, strArr);
        }
        return sb.toString();
    }

    private static void appendArgs(StringBuilder sb, int i, String[] strArr) {
        sb.append('(');
        for (int i2 = i; i2 < strArr.length; i2++) {
            if (i2 > i) {
                sb.append(',');
            }
            sb.append(strArr[i2]);
        }
        sb.append(")");
    }

    public static boolean isBuiltInFunctionName(String str) {
        return FunctionMetadataRegistry.lookupIndexByName(str.toUpperCase(Locale.ROOT)) >= 0;
    }

    /* access modifiers changed from: protected */
    public String lookupName(short s) {
        return lookupName(s, false);
    }

    /* access modifiers changed from: protected */
    public final String lookupName(short s, boolean z) {
        FunctionMetadata functionMetadata;
        if (s == 255) {
            return "#external#";
        }
        if (z) {
            functionMetadata = FunctionMetadataRegistry.getCetabFunctionByIndex(s);
        } else {
            functionMetadata = FunctionMetadataRegistry.getFunctionByIndex(s);
        }
        if (functionMetadata != null) {
            return functionMetadata.getName();
        }
        throw new RuntimeException("bad function index (" + s + ", " + z + ")");
    }

    protected static short lookupIndex(String str) {
        short lookupIndexByName = FunctionMetadataRegistry.lookupIndexByName(str.toUpperCase(Locale.ROOT));
        if (lookupIndexByName < 0) {
            return 255;
        }
        return lookupIndexByName;
    }

    public byte getDefaultOperandClass() {
        return this.returnClass;
    }

    public final byte getParameterClass(int i) {
        byte[] bArr = this.paramClass;
        if (i >= bArr.length) {
            return bArr[bArr.length - 1];
        }
        return bArr[i];
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("functionIndex", new AbstractFunctionPtg$$ExternalSyntheticLambda0(this), "functionName", new AbstractFunctionPtg$$ExternalSyntheticLambda1(this), "numberOfOperands", new AbstractFunctionPtg$$ExternalSyntheticLambda2(this), "externalFunction", new AbstractFunctionPtg$$ExternalSyntheticLambda3(this), "defaultOperandClass", new AbstractFunctionPtg$$ExternalSyntheticLambda4(this));
    }
}
