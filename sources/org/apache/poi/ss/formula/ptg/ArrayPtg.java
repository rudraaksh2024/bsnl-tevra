package org.apache.poi.ss.formula.ptg;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.ss.formula.constant.ConstantValueParser;
import org.apache.poi.ss.formula.constant.ErrorConstant;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class ArrayPtg extends Ptg {
    public static final int PLAIN_TOKEN_SIZE = 8;
    private static final int RESERVED_FIELD_LEN = 7;
    public static final byte sid = 32;
    private final Object[] _arrayValues;
    private final int _nColumns;
    private final int _nRows;
    private final int _reserved0Int;
    private final int _reserved1Short;
    private final int _reserved2Byte;

    public byte getDefaultOperandClass() {
        return Ptg.CLASS_ARRAY;
    }

    public byte getSid() {
        return 32;
    }

    public boolean isBaseToken() {
        return false;
    }

    ArrayPtg(int i, int i2, int i3, int i4, int i5, Object[] objArr) {
        this._reserved0Int = i;
        this._reserved1Short = i2;
        this._reserved2Byte = i3;
        this._nColumns = i4;
        this._nRows = i5;
        this._arrayValues = (Object[]) objArr.clone();
    }

    public ArrayPtg(ArrayPtg arrayPtg) {
        this._reserved0Int = arrayPtg._reserved0Int;
        this._reserved1Short = arrayPtg._reserved1Short;
        this._reserved2Byte = arrayPtg._reserved2Byte;
        this._nColumns = arrayPtg._nColumns;
        this._nRows = arrayPtg._nRows;
        Object[] objArr = arrayPtg._arrayValues;
        this._arrayValues = objArr == null ? null : (Object[]) objArr.clone();
    }

    public ArrayPtg(Object[][] objArr) {
        int length = objArr[0].length;
        int length2 = objArr.length;
        short s = (short) length;
        this._nColumns = s;
        short s2 = (short) length2;
        this._nRows = s2;
        Object[] objArr2 = new Object[(s * s2)];
        for (int i = 0; i < length2; i++) {
            Object[] objArr3 = objArr[i];
            for (int i2 = 0; i2 < length; i2++) {
                objArr2[getValueIndex(i2, i)] = objArr3[i2];
            }
        }
        this._arrayValues = objArr2;
        this._reserved0Int = 0;
        this._reserved1Short = 0;
        this._reserved2Byte = 0;
    }

    public Object[][] getTokenArrayValues() {
        if (this._arrayValues != null) {
            int i = this._nRows;
            int[] iArr = new int[2];
            iArr[1] = this._nColumns;
            iArr[0] = i;
            Object[][] objArr = (Object[][]) Array.newInstance(Object.class, iArr);
            for (int i2 = 0; i2 < this._nRows; i2++) {
                Object[] objArr2 = objArr[i2];
                for (int i3 = 0; i3 < this._nColumns; i3++) {
                    objArr2[i3] = this._arrayValues[getValueIndex(i3, i2)];
                }
            }
            return objArr;
        }
        throw new IllegalStateException("array values not read yet");
    }

    /* access modifiers changed from: package-private */
    public int getValueIndex(int i, int i2) {
        int i3;
        if (i < 0 || i >= (i3 = this._nColumns)) {
            throw new IllegalArgumentException("Specified colIx (" + i + ") is outside the allowed range (0.." + (this._nColumns - 1) + ")");
        } else if (i2 >= 0 && i2 < this._nRows) {
            return (i2 * i3) + i;
        } else {
            throw new IllegalArgumentException("Specified rowIx (" + i2 + ") is outside the allowed range (0.." + (this._nRows - 1) + ")");
        }
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + 32);
        littleEndianOutput.writeInt(this._reserved0Int);
        littleEndianOutput.writeShort(this._reserved1Short);
        littleEndianOutput.writeByte(this._reserved2Byte);
    }

    public int writeTokenValueBytes(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(this._nColumns - 1);
        littleEndianOutput.writeShort(this._nRows - 1);
        ConstantValueParser.encode(littleEndianOutput, this._arrayValues);
        return ConstantValueParser.getEncodedSize(this._arrayValues) + 3;
    }

    public int getRowCount() {
        return this._nRows;
    }

    public int getColumnCount() {
        return this._nColumns;
    }

    public int getSize() {
        return ConstantValueParser.getEncodedSize(this._arrayValues) + 11;
    }

    public String toFormulaString() {
        StringBuilder sb = new StringBuilder(VectorFormat.DEFAULT_PREFIX);
        for (int i = 0; i < this._nRows; i++) {
            if (i > 0) {
                sb.append(";");
            }
            for (int i2 = 0; i2 < this._nColumns; i2++) {
                if (i2 > 0) {
                    sb.append(",");
                }
                sb.append(getConstantText(this._arrayValues[getValueIndex(i2, i)]));
            }
        }
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    private static String getConstantText(Object obj) {
        if (obj == null) {
            throw new RuntimeException("Array item cannot be null");
        } else if (obj instanceof String) {
            return "\"" + obj + "\"";
        } else {
            if (obj instanceof Double) {
                return NumberToTextConverter.toText(((Double) obj).doubleValue());
            }
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue() ? "TRUE" : "FALSE";
            }
            if (obj instanceof ErrorConstant) {
                return ((ErrorConstant) obj).getText();
            }
            throw new IllegalArgumentException("Unexpected constant class (" + obj.getClass().getName() + ")");
        }
    }

    public ArrayPtg copy() {
        return new ArrayPtg(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("reserved0", new ArrayPtg$$ExternalSyntheticLambda0(this), "reserved1", new ArrayPtg$$ExternalSyntheticLambda1(this), "reserved2", new ArrayPtg$$ExternalSyntheticLambda2(this), "columnCount", new ArrayPtg$$ExternalSyntheticLambda3(this), "rowCount", new ArrayPtg$$ExternalSyntheticLambda4(this), "arrayValues", new ArrayPtg$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-ArrayPtg  reason: not valid java name */
    public /* synthetic */ Object m2270lambda$getGenericProperties$0$orgapachepoissformulaptgArrayPtg() {
        return Integer.valueOf(this._reserved0Int);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-ArrayPtg  reason: not valid java name */
    public /* synthetic */ Object m2271lambda$getGenericProperties$1$orgapachepoissformulaptgArrayPtg() {
        return Integer.valueOf(this._reserved1Short);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-ss-formula-ptg-ArrayPtg  reason: not valid java name */
    public /* synthetic */ Object m2272lambda$getGenericProperties$2$orgapachepoissformulaptgArrayPtg() {
        return Integer.valueOf(this._reserved2Byte);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-ss-formula-ptg-ArrayPtg  reason: not valid java name */
    public /* synthetic */ Object m2273lambda$getGenericProperties$3$orgapachepoissformulaptgArrayPtg() {
        return this._arrayValues == null ? "#values#uninitialised#" : toFormulaString();
    }
}
