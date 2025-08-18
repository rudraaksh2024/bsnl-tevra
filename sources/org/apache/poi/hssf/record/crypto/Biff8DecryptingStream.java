package org.apache.poi.hssf.record.crypto;

import java.io.InputStream;
import java.io.PushbackInputStream;
import kotlin.UByte;
import org.apache.poi.hssf.record.BiffHeaderInput;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.crypt.ChunkedCipherInputStream;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.RecordFormatException;

public final class Biff8DecryptingStream implements BiffHeaderInput, LittleEndianInput {
    public static final int RC4_REKEYING_INTERVAL = 1024;
    private final byte[] buffer = new byte[8];
    private final ChunkedCipherInputStream ccis;
    private boolean shouldSkipEncryptionOnCurrentRecord;

    public static boolean isNeverEncryptedRecord(int i) {
        return i == 47 || i == 225 || i == 2057;
    }

    public Biff8DecryptingStream(InputStream inputStream, int i, EncryptionInfo encryptionInfo) throws RecordFormatException {
        try {
            byte[] safelyAllocate = IOUtils.safelyAllocate((long) i, HSSFWorkbook.getMaxRecordLength());
            if (i != 0) {
                PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, i);
                PushbackInputStream pushbackInputStream2 = pushbackInputStream;
                pushbackInputStream.unread(safelyAllocate);
                inputStream = pushbackInputStream;
            }
            Decryptor decryptor = encryptionInfo.getDecryptor();
            decryptor.setChunkSize(1024);
            ChunkedCipherInputStream chunkedCipherInputStream = (ChunkedCipherInputStream) decryptor.getDataStream(inputStream, Integer.MAX_VALUE, 0);
            this.ccis = chunkedCipherInputStream;
            if (i > 0) {
                chunkedCipherInputStream.readFully(safelyAllocate);
            }
        } catch (Exception e) {
            throw new RecordFormatException((Throwable) e);
        }
    }

    public int available() {
        return this.ccis.available();
    }

    public int readRecordSID() {
        readPlain(this.buffer, 0, 2);
        int uShort = LittleEndian.getUShort(this.buffer, 0);
        this.shouldSkipEncryptionOnCurrentRecord = isNeverEncryptedRecord(uShort);
        return uShort;
    }

    public int readDataSize() {
        readPlain(this.buffer, 0, 2);
        int uShort = LittleEndian.getUShort(this.buffer, 0);
        this.ccis.setNextRecordSize(uShort);
        return uShort;
    }

    public double readDouble() {
        double longBitsToDouble = Double.longBitsToDouble(readLong());
        if (!Double.isNaN(longBitsToDouble)) {
            return longBitsToDouble;
        }
        throw new RuntimeException("Did not expect to read NaN");
    }

    public void readFully(byte[] bArr) {
        readFully(bArr, 0, bArr.length);
    }

    public void readFully(byte[] bArr, int i, int i2) {
        if (this.shouldSkipEncryptionOnCurrentRecord) {
            readPlain(bArr, i, bArr.length);
        } else {
            this.ccis.readFully(bArr, i, i2);
        }
    }

    public int readUByte() {
        return readByte() & UByte.MAX_VALUE;
    }

    public byte readByte() {
        if (!this.shouldSkipEncryptionOnCurrentRecord) {
            return this.ccis.readByte();
        }
        readPlain(this.buffer, 0, 1);
        return this.buffer[0];
    }

    public int readUShort() {
        return readShort() & 65535;
    }

    public short readShort() {
        if (!this.shouldSkipEncryptionOnCurrentRecord) {
            return this.ccis.readShort();
        }
        readPlain(this.buffer, 0, 2);
        return LittleEndian.getShort(this.buffer);
    }

    public int readInt() {
        if (!this.shouldSkipEncryptionOnCurrentRecord) {
            return this.ccis.readInt();
        }
        readPlain(this.buffer, 0, 4);
        return LittleEndian.getInt(this.buffer);
    }

    public long readLong() {
        if (!this.shouldSkipEncryptionOnCurrentRecord) {
            return this.ccis.readLong();
        }
        readPlain(this.buffer, 0, 8);
        return LittleEndian.getLong(this.buffer);
    }

    public long getPosition() {
        return this.ccis.getPos();
    }

    public void readPlain(byte[] bArr, int i, int i2) {
        this.ccis.readPlain(bArr, i, i2);
    }
}
