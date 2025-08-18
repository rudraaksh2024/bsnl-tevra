package org.apache.poi.ss.formula.function;

public final class FunctionMetadata {
    private static final short FUNCTION_MAX_PARAMS = 30;
    private final int _index;
    private final int _maxParams;
    private final int _minParams;
    private final String _name;
    private final byte[] _parameterClassCodes;
    private final byte _returnClassCode;

    FunctionMetadata(int i, String str, int i2, int i3, byte b, byte[] bArr) {
        byte[] bArr2;
        this._index = i;
        this._name = str;
        this._minParams = i2;
        this._maxParams = i3;
        this._returnClassCode = b;
        if (bArr == null) {
            bArr2 = null;
        } else {
            bArr2 = (byte[]) bArr.clone();
        }
        this._parameterClassCodes = bArr2;
    }

    public int getIndex() {
        return this._index;
    }

    public String getName() {
        return this._name;
    }

    public int getMinParams() {
        return this._minParams;
    }

    public int getMaxParams() {
        return this._maxParams;
    }

    public boolean hasFixedArgsLength() {
        return this._minParams == this._maxParams;
    }

    public byte getReturnClassCode() {
        return this._returnClassCode;
    }

    public byte[] getParameterClassCodes() {
        return (byte[]) this._parameterClassCodes.clone();
    }

    public boolean hasUnlimitedVarags() {
        return 30 == this._maxParams;
    }

    public String toString() {
        return getClass().getName() + " [" + this._index + " " + this._name + "]";
    }
}
