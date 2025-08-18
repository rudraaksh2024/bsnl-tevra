package org.apache.poi.hssf.record;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import kotlin.UByte;
import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianInputStream;
import org.apache.poi.util.RecordFormatException;

public final class RecordInputStream implements LittleEndianInput {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int DATA_LEN_NEEDS_TO_BE_READ = -1;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final int INVALID_SID_VALUE = -1;
    public static final short MAX_RECORD_DATA_SIZE = 8224;
    private final BiffHeaderInput _bhi;
    private int _currentDataLength;
    private int _currentDataOffset;
    private int _currentSid;
    private final LittleEndianInput _dataInput;
    private int _markedDataOffset;
    private int _nextSid;

    public static final class LeftoverDataException extends RuntimeException {
        public LeftoverDataException(int i, int i2) {
            super("Initialisation of record 0x" + Integer.toHexString(i).toUpperCase(Locale.ROOT) + "(" + getRecordName(i) + ") left " + i2 + " bytes remaining still to be read.");
        }

        private static String getRecordName(int i) {
            Class<? extends Record> recordClass = RecordFactory.getRecordClass(i);
            if (recordClass == null) {
                return null;
            }
            return recordClass.getSimpleName();
        }
    }

    private static final class SimpleHeaderInput implements BiffHeaderInput {
        private final LittleEndianInput _lei;

        private SimpleHeaderInput(LittleEndianInput littleEndianInput) {
            this._lei = littleEndianInput;
        }

        public int available() {
            return this._lei.available();
        }

        public int readDataSize() {
            return this._lei.readUShort();
        }

        public int readRecordSID() {
            return this._lei.readUShort();
        }
    }

    public RecordInputStream(InputStream inputStream) throws RecordFormatException {
        this(inputStream, (EncryptionInfo) null, 0);
    }

    public RecordInputStream(InputStream inputStream, EncryptionInfo encryptionInfo, int i) throws RecordFormatException {
        if (encryptionInfo == null) {
            LittleEndianInput littleEndianInputStream = inputStream instanceof LittleEndianInput ? (LittleEndianInput) inputStream : new LittleEndianInputStream(inputStream);
            this._dataInput = littleEndianInputStream;
            this._bhi = new SimpleHeaderInput(littleEndianInputStream);
        } else {
            Biff8DecryptingStream biff8DecryptingStream = new Biff8DecryptingStream(inputStream, i, encryptionInfo);
            this._dataInput = biff8DecryptingStream;
            this._bhi = biff8DecryptingStream;
        }
        this._nextSid = readNextSid();
    }

    public int available() {
        return remaining();
    }

    public int read(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, remaining());
        if (min == 0) {
            return 0;
        }
        readFully(bArr, i, min);
        return min;
    }

    public short getSid() {
        return (short) this._currentSid;
    }

    public boolean hasNextRecord() throws LeftoverDataException {
        int i = this._currentDataLength;
        if (i == -1 || i == this._currentDataOffset) {
            if (i != -1) {
                this._nextSid = readNextSid();
            }
            return this._nextSid != -1;
        }
        throw new LeftoverDataException(this._currentSid, remaining());
    }

    private int readNextSid() {
        if (this._bhi.available() < 4) {
            return -1;
        }
        int readRecordSID = this._bhi.readRecordSID();
        if (readRecordSID != -1) {
            this._currentDataLength = -1;
            return readRecordSID;
        }
        throw new RecordFormatException("Found invalid sid (" + readRecordSID + ")");
    }

    public void nextRecord() throws RecordFormatException {
        int i = this._nextSid;
        if (i == -1) {
            throw new IllegalStateException("EOF - next record not available");
        } else if (this._currentDataLength == -1) {
            this._currentSid = i;
            this._currentDataOffset = 0;
            int readDataSize = this._bhi.readDataSize();
            this._currentDataLength = readDataSize;
            if (readDataSize > 8224) {
                throw new RecordFormatException("The content of an excel record cannot exceed 8224 bytes");
            }
        } else {
            throw new IllegalStateException("Cannot call nextRecord() without checking hasNextRecord() first");
        }
    }

    private void checkRecordPosition(int i) {
        int remaining = remaining();
        if (remaining < i) {
            if (remaining != 0 || !isContinueNext()) {
                throw new RecordFormatException("Not enough data (" + remaining + ") to read requested (" + i + ") bytes");
            }
            nextRecord();
        }
    }

    public byte readByte() {
        checkRecordPosition(1);
        this._currentDataOffset++;
        return this._dataInput.readByte();
    }

    public short readShort() {
        checkRecordPosition(2);
        this._currentDataOffset += 2;
        return this._dataInput.readShort();
    }

    public int readInt() {
        checkRecordPosition(4);
        this._currentDataOffset += 4;
        return this._dataInput.readInt();
    }

    public long readLong() {
        checkRecordPosition(8);
        this._currentDataOffset += 8;
        return this._dataInput.readLong();
    }

    public int readUByte() {
        return readByte() & UByte.MAX_VALUE;
    }

    public int readUShort() {
        checkRecordPosition(2);
        this._currentDataOffset += 2;
        return this._dataInput.readUShort();
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public void readPlain(byte[] bArr, int i, int i2) {
        readFully(bArr, 0, bArr.length, true);
    }

    public void readFully(byte[] bArr) {
        readFully(bArr, 0, bArr.length, false);
    }

    public void readFully(byte[] bArr, int i, int i2) {
        readFully(bArr, i, i2, false);
    }

    private void readFully(byte[] bArr, int i, int i2, boolean z) {
        bArr.getClass();
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = i2;
        while (i3 > 0) {
            int min = Math.min(available(), i3);
            if (min == 0) {
                if (hasNextRecord()) {
                    nextRecord();
                    min = Math.min(available(), i3);
                    if (min <= 0) {
                        throw new RecordFormatException("Need to have a valid next chunk, but had: " + min + " with len: " + i3 + " and available: " + available());
                    }
                } else {
                    throw new RecordFormatException("Can't read the remaining " + i3 + " bytes of the requested " + i2 + " bytes. No further record exists.");
                }
            }
            checkRecordPosition(min);
            if (z) {
                this._dataInput.readPlain(bArr, i, min);
            } else {
                this._dataInput.readFully(bArr, i, min);
            }
            this._currentDataOffset += min;
            i += min;
            i3 -= min;
        }
    }

    public String readString() {
        return readStringCommon(readUShort(), readByte() == 0);
    }

    public String readUnicodeLEString(int i) {
        return readStringCommon(i, false);
    }

    public String readCompressedUnicode(int i) {
        return readStringCommon(i, true);
    }

    private String readStringCommon(int i, boolean z) {
        int i2;
        int i3;
        if (i < 0 || i > 1048576) {
            throw new IllegalArgumentException("Bad requested string length (" + i + ")");
        }
        char[] cArr = new char[i];
        int i4 = 0;
        while (true) {
            int remaining = remaining();
            if (!z) {
                remaining /= 2;
            }
            if (i - i4 <= remaining) {
                while (i4 < i) {
                    if (z) {
                        i2 = readUByte();
                    } else {
                        i2 = readShort();
                    }
                    cArr[i4] = (char) i2;
                    i4++;
                }
                return new String(cArr);
            }
            while (remaining > 0) {
                if (z) {
                    i3 = readUByte();
                } else {
                    i3 = readShort();
                }
                cArr[i4] = (char) i3;
                i4++;
                remaining--;
            }
            if (!isContinueNext()) {
                throw new RecordFormatException("Expected to find a ContinueRecord in order to read remaining " + (i - i4) + " of " + i + " chars");
            } else if (remaining() == 0) {
                nextRecord();
                z = readByte() == 0;
            } else {
                throw new RecordFormatException("Odd number of bytes(" + remaining() + ") left behind");
            }
        }
    }

    public byte[] readRemainder() {
        int remaining = remaining();
        if (remaining == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) remaining, HSSFWorkbook.getMaxRecordLength());
        readFully(safelyAllocate);
        return safelyAllocate;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002d, code lost:
        throw r1;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] readAllContinuedRemainder() {
        /*
            r4 = this;
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream     // Catch:{ IOException -> 0x002e }
            r1 = 16448(0x4040, float:2.3049E-41)
            r0.<init>(r1)     // Catch:{ IOException -> 0x002e }
        L_0x0007:
            byte[] r1 = r4.readRemainder()     // Catch:{ all -> 0x0022 }
            int r2 = r1.length     // Catch:{ all -> 0x0022 }
            r3 = 0
            r0.write(r1, r3, r2)     // Catch:{ all -> 0x0022 }
            boolean r1 = r4.isContinueNext()     // Catch:{ all -> 0x0022 }
            if (r1 != 0) goto L_0x001e
            byte[] r4 = r0.toByteArray()     // Catch:{ all -> 0x0022 }
            r0.close()     // Catch:{ IOException -> 0x002e }
            return r4
        L_0x001e:
            r4.nextRecord()     // Catch:{ all -> 0x0022 }
            goto L_0x0007
        L_0x0022:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r0 = move-exception
            r4.addSuppressed(r0)     // Catch:{ IOException -> 0x002e }
        L_0x002d:
            throw r1     // Catch:{ IOException -> 0x002e }
        L_0x002e:
            r4 = move-exception
            org.apache.poi.util.RecordFormatException r0 = new org.apache.poi.util.RecordFormatException
            r0.<init>((java.lang.Throwable) r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.RecordInputStream.readAllContinuedRemainder():byte[]");
    }

    public int remaining() {
        int i = this._currentDataLength;
        if (i == -1) {
            return 0;
        }
        return i - this._currentDataOffset;
    }

    private boolean isContinueNext() {
        int i = this._currentDataLength;
        if (i != -1 && this._currentDataOffset != i) {
            throw new IllegalStateException("Should never be called before end of current record");
        } else if (hasNextRecord() && this._nextSid == 60) {
            return true;
        } else {
            return false;
        }
    }

    public int getNextSid() {
        return this._nextSid;
    }

    @Internal
    public void mark(int i) {
        ((InputStream) this._dataInput).mark(i);
        this._markedDataOffset = this._currentDataOffset;
    }

    @Internal
    public void reset() throws IOException {
        ((InputStream) this._dataInput).reset();
        this._currentDataOffset = this._markedDataOffset;
    }
}
