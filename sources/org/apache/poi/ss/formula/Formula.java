package org.apache.poi.ss.formula;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.record.ExternalNameRecord$$ExternalSyntheticLambda4;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public class Formula implements GenericRecord {
    private static final Formula EMPTY = new Formula(new byte[0], 0);
    private static final int MAX_ENCODED_LEN = 100000;
    private final byte[] _byteEncoding;
    private final int _encodedTokenLen;

    public Formula copy() {
        return this;
    }

    public Formula(Formula formula) {
        byte[] bArr = formula._byteEncoding;
        this._byteEncoding = bArr == null ? null : (byte[]) bArr.clone();
        this._encodedTokenLen = formula._encodedTokenLen;
    }

    private Formula(byte[] bArr, int i) {
        this._byteEncoding = (byte[]) bArr.clone();
        this._encodedTokenLen = i;
    }

    public static Formula read(int i, LittleEndianInput littleEndianInput) {
        return read(i, littleEndianInput, i);
    }

    public static Formula read(int i, LittleEndianInput littleEndianInput, int i2) {
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) i2, 100000);
        littleEndianInput.readFully(safelyAllocate);
        return new Formula(safelyAllocate, i);
    }

    public Ptg[] getTokens() {
        return Ptg.readTokens(this._encodedTokenLen, new LittleEndianByteArrayInputStream(this._byteEncoding));
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this._encodedTokenLen);
        littleEndianOutput.write(this._byteEncoding);
    }

    public void serializeTokens(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.write(this._byteEncoding, 0, this._encodedTokenLen);
    }

    public void serializeArrayConstantData(LittleEndianOutput littleEndianOutput) {
        byte[] bArr = this._byteEncoding;
        int length = bArr.length;
        int i = this._encodedTokenLen;
        littleEndianOutput.write(bArr, i, length - i);
    }

    public int getEncodedSize() {
        return this._byteEncoding.length + 2;
    }

    public int getEncodedTokenSize() {
        return this._encodedTokenLen;
    }

    public static Formula create(Ptg[] ptgArr) {
        if (ptgArr == null || ptgArr.length < 1) {
            return EMPTY;
        }
        byte[] bArr = new byte[Ptg.getEncodedSize(ptgArr)];
        Ptg.serializePtgs(ptgArr, bArr, 0);
        return new Formula(bArr, Ptg.getEncodedSizeWithoutArrayData(ptgArr));
    }

    public static Ptg[] getTokens(Formula formula) {
        if (formula == null) {
            return null;
        }
        return formula.getTokens();
    }

    public CellReference getExpReference() {
        byte[] bArr = this._byteEncoding;
        if (bArr.length != 5) {
            return null;
        }
        byte b = bArr[0];
        if (b == 1 || b == 2) {
            return new CellReference(LittleEndian.getUShort(bArr, 1), LittleEndian.getUShort(bArr, 3));
        }
        return null;
    }

    public boolean isSame(Formula formula) {
        return Arrays.equals(this._byteEncoding, formula._byteEncoding);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("tokens", new ExternalNameRecord$$ExternalSyntheticLambda4(this), "expReference", new Formula$$ExternalSyntheticLambda0(this));
    }
}
