package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class StringPtg extends ScalarConstantPtg {
    private static final char FORMULA_DELIMITER = '\"';
    public static final byte sid = 23;
    private final boolean _is16bitUnicode;
    private final String field_3_string;

    public StringPtg copy() {
        return this;
    }

    public byte getSid() {
        return sid;
    }

    public StringPtg(LittleEndianInput littleEndianInput) {
        int readUByte = littleEndianInput.readUByte();
        boolean z = (littleEndianInput.readByte() & 1) == 0 ? false : true;
        this._is16bitUnicode = z;
        if (z) {
            this.field_3_string = StringUtil.readUnicodeLE(littleEndianInput, readUByte);
        } else {
            this.field_3_string = StringUtil.readCompressedUnicode(littleEndianInput, readUByte);
        }
    }

    public StringPtg(String str) {
        if (str.length() <= 255) {
            this._is16bitUnicode = StringUtil.hasMultibyte(str);
            this.field_3_string = str;
            return;
        }
        throw new IllegalArgumentException("String literals in formulas can't be bigger than 255 characters ASCII");
    }

    public String getValue() {
        return this.field_3_string;
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeByte(this.field_3_string.length());
        littleEndianOutput.writeByte(this._is16bitUnicode ? 1 : 0);
        if (this._is16bitUnicode) {
            StringUtil.putUnicodeLE(this.field_3_string, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(this.field_3_string, littleEndianOutput);
        }
    }

    public int getSize() {
        return (this.field_3_string.length() * (this._is16bitUnicode ? 2 : 1)) + 3;
    }

    public String toFormulaString() {
        String str = this.field_3_string;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 4);
        sb.append('\"');
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                sb.append('\"');
            }
            sb.append(charAt);
        }
        sb.append('\"');
        return sb.toString();
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("value", new StringPtg$$ExternalSyntheticLambda0(this));
    }
}
