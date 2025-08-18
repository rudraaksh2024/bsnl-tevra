package org.apache.poi.hpsf;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public class ClassID implements Duplicatable, GenericRecord {
    public static final int LENGTH = 16;
    private final byte[] bytes;

    public int length() {
        return 16;
    }

    public ClassID(byte[] bArr, int i) {
        this.bytes = new byte[16];
        read(bArr, i);
    }

    public ClassID() {
        byte[] bArr = new byte[16];
        this.bytes = bArr;
        Arrays.fill(bArr, (byte) 0);
    }

    public ClassID(ClassID classID) {
        byte[] bArr = new byte[16];
        this.bytes = bArr;
        System.arraycopy(classID.bytes, 0, bArr, 0, bArr.length);
    }

    public ClassID(String str) {
        this.bytes = new byte[16];
        String replaceAll = str.replaceAll("[{}-]", "");
        int i = 0;
        while (i < replaceAll.length()) {
            int i2 = i + 2;
            this.bytes[i / 2] = (byte) Integer.parseInt(replaceAll.substring(i, i2), 16);
            i = i2;
        }
    }

    public ClassID(LittleEndianInput littleEndianInput) {
        byte[] bArr = new byte[16];
        this.bytes = bArr;
        byte[] bArr2 = (byte[]) bArr.clone();
        littleEndianInput.readFully(bArr2);
        read(bArr2, 0);
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] bArr) {
        System.arraycopy(bArr, 0, this.bytes, 0, 16);
    }

    public byte[] read(byte[] bArr, int i) {
        byte[] bArr2 = this.bytes;
        bArr2[0] = bArr[i + 3];
        bArr2[1] = bArr[i + 2];
        bArr2[2] = bArr[i + 1];
        bArr2[3] = bArr[i + 0];
        bArr2[4] = bArr[i + 5];
        bArr2[5] = bArr[i + 4];
        bArr2[6] = bArr[i + 7];
        bArr2[7] = bArr[i + 6];
        System.arraycopy(bArr, i + 8, bArr2, 8, 8);
        return this.bytes;
    }

    public void write(byte[] bArr, int i) throws ArrayStoreException {
        if (bArr.length >= 16) {
            byte[] bArr2 = this.bytes;
            bArr[i + 0] = bArr2[3];
            bArr[i + 1] = bArr2[2];
            bArr[i + 2] = bArr2[1];
            bArr[i + 3] = bArr2[0];
            bArr[i + 4] = bArr2[5];
            bArr[i + 5] = bArr2[4];
            bArr[i + 6] = bArr2[7];
            bArr[i + 7] = bArr2[6];
            System.arraycopy(bArr2, 8, bArr, i + 8, 8);
            return;
        }
        throw new ArrayStoreException("Destination byte[] must have room for at least 16 bytes, but has a length of only " + bArr.length + ".");
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        byte[] bArr = (byte[]) this.bytes.clone();
        write(bArr, 0);
        littleEndianOutput.write(bArr);
    }

    public boolean equals(Object obj) {
        return (obj instanceof ClassID) && Arrays.equals(this.bytes, ((ClassID) obj).bytes);
    }

    public boolean equalsInverted(ClassID classID) {
        byte[] bArr = classID.bytes;
        byte b = bArr[0];
        byte[] bArr2 = this.bytes;
        return b == bArr2[3] && bArr[1] == bArr2[2] && bArr[2] == bArr2[1] && bArr[3] == bArr2[0] && bArr[4] == bArr2[5] && bArr[5] == bArr2[4] && bArr[6] == bArr2[7] && bArr[7] == bArr2[6] && bArr[8] == bArr2[8] && bArr[9] == bArr2[9] && bArr[10] == bArr2[10] && bArr[11] == bArr2[11] && bArr[12] == bArr2[12] && bArr[13] == bArr2[13] && bArr[14] == bArr2[14] && bArr[15] == bArr2[15];
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        return VectorFormat.DEFAULT_PREFIX + toUUIDString() + VectorFormat.DEFAULT_SUFFIX;
    }

    public String toUUIDString() {
        return toUUID().toString().toUpperCase(Locale.ROOT);
    }

    public UUID toUUID() {
        return new UUID(ByteBuffer.wrap(this.bytes, 0, 8).getLong(), ByteBuffer.wrap(this.bytes, 8, 8).getLong());
    }

    public ClassID copy() {
        return new ClassID(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("uuid", new ClassID$$ExternalSyntheticLambda0(this));
    }
}
