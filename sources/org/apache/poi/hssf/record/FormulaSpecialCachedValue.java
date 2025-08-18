package org.apache.poi.hssf.record;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

@Internal
public final class FormulaSpecialCachedValue implements GenericRecord {
    private static final long BIT_MARKER = -281474976710656L;
    public static final int BOOLEAN = 1;
    private static final int DATA_INDEX = 2;
    public static final int EMPTY = 3;
    public static final int ERROR_CODE = 2;
    public static final int STRING = 0;
    private static final int VARIABLE_DATA_LENGTH = 6;
    private final byte[] _variableData;

    FormulaSpecialCachedValue(FormulaSpecialCachedValue formulaSpecialCachedValue) {
        byte[] bArr = formulaSpecialCachedValue._variableData;
        this._variableData = bArr == null ? null : (byte[]) bArr.clone();
    }

    private FormulaSpecialCachedValue(byte[] bArr) {
        this._variableData = bArr;
    }

    public int getTypeCode() {
        return this._variableData[0];
    }

    public static FormulaSpecialCachedValue create(long j) {
        if ((j & BIT_MARKER) != BIT_MARKER) {
            return null;
        }
        byte[] bArr = new byte[6];
        for (int i = 0; i < 6; i++) {
            bArr[i] = (byte) ((int) j);
            j >>= 8;
        }
        byte b = bArr[0];
        if (b == 0 || b == 1 || b == 2 || b == 3) {
            return new FormulaSpecialCachedValue(bArr);
        }
        throw new RecordFormatException("Bad special value code (" + bArr[0] + ")");
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.write(this._variableData);
        littleEndianOutput.writeShort(65535);
    }

    public String formatDebugString() {
        return formatValue() + Chars.SPACE + HexDump.toHex(this._variableData);
    }

    private String formatValue() {
        int typeCode = getTypeCode();
        if (typeCode == 0) {
            return "<string>";
        }
        if (typeCode == 1) {
            return getDataValue() == 0 ? "FALSE" : "TRUE";
        }
        if (typeCode != 2) {
            return typeCode != 3 ? "#error(type=" + typeCode + ")#" : "<empty>";
        }
        return ErrorEval.getText(getDataValue());
    }

    private int getDataValue() {
        return this._variableData[2];
    }

    public static FormulaSpecialCachedValue createCachedEmptyValue() {
        return create(3, 0);
    }

    public static FormulaSpecialCachedValue createForString() {
        return create(0, 0);
    }

    public static FormulaSpecialCachedValue createCachedBoolean(boolean z) {
        return create(1, z ? 1 : 0);
    }

    public static FormulaSpecialCachedValue createCachedErrorCode(int i) {
        return create(2, i);
    }

    private static FormulaSpecialCachedValue create(int i, int i2) {
        return new FormulaSpecialCachedValue(new byte[]{(byte) i, 0, (byte) i2, 0, 0, 0});
    }

    public String toString() {
        return getClass().getName() + '[' + formatValue() + ']';
    }

    @Deprecated
    public int getValueType() {
        int typeCode = getTypeCode();
        if (typeCode != 0) {
            if (typeCode == 1) {
                return CellType.BOOLEAN.getCode();
            }
            if (typeCode == 2) {
                return CellType.ERROR.getCode();
            }
            if (typeCode != 3) {
                throw new IllegalStateException("Unexpected type id (" + typeCode + ")");
            }
        }
        return CellType.STRING.getCode();
    }

    public CellType getValueTypeEnum() {
        int typeCode = getTypeCode();
        if (typeCode != 0) {
            if (typeCode == 1) {
                return CellType.BOOLEAN;
            }
            if (typeCode == 2) {
                return CellType.ERROR;
            }
            if (typeCode != 3) {
                throw new IllegalStateException("Unexpected type id (" + typeCode + ")");
            }
        }
        return CellType.STRING;
    }

    public boolean getBooleanValue() {
        if (getTypeCode() != 1) {
            throw new IllegalStateException("Not a boolean cached value - " + formatValue());
        } else if (getDataValue() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getErrorValue() {
        if (getTypeCode() == 2) {
            return getDataValue();
        }
        throw new IllegalStateException("Not an error cached value - " + formatValue());
    }

    /* access modifiers changed from: private */
    public Object getGenericValue() {
        int typeCode = getTypeCode();
        if (typeCode == 0) {
            return TypedValues.Custom.S_STRING;
        }
        if (typeCode == 1) {
            return Boolean.valueOf(getBooleanValue());
        }
        if (typeCode == 2) {
            return Integer.valueOf(getErrorValue());
        }
        if (typeCode == 3) {
            return null;
        }
        throw new IllegalStateException("Unexpected type id (" + typeCode + ")");
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("value", new FormulaSpecialCachedValue$$ExternalSyntheticLambda0(this), "typeCode", GenericRecordUtil.getEnumBitsAsString(new FormulaSpecialCachedValue$$ExternalSyntheticLambda1(this), new int[]{0, 1, 2, 3}, new String[]{"STRING", "BOOLEAN", "ERROR_CODE", "EMPTY"}));
    }
}
