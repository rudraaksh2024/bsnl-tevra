package org.apache.commons.compress.compressors.z;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.compressors.lzw.LZWInputStream;

public class ZCompressorInputStream extends LZWInputStream {
    private static final int BLOCK_MODE_MASK = 128;
    private static final int MAGIC_1 = 31;
    private static final int MAGIC_2 = 157;
    private static final int MAX_CODE_SIZE_MASK = 31;
    private final boolean blockMode;
    private final int maxCodeSize;
    private long totalCodesRead;

    public ZCompressorInputStream(InputStream inputStream, int i) throws IOException {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
        int readBits = (int) this.in.readBits(8);
        int readBits2 = (int) this.in.readBits(8);
        int readBits3 = (int) this.in.readBits(8);
        if (readBits == 31 && readBits2 == 157 && readBits3 >= 0) {
            boolean z = (readBits3 & 128) != 0;
            this.blockMode = z;
            int i2 = readBits3 & 31;
            this.maxCodeSize = i2;
            if (z) {
                setClearCode(9);
            }
            initializeTables(i2, i);
            clearEntries();
            return;
        }
        throw new IOException("Input is not in .Z format");
    }

    public ZCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, -1);
    }

    private void clearEntries() {
        setTableSize((this.blockMode ? 1 : 0) + true);
    }

    /* access modifiers changed from: protected */
    public int readNextCode() throws IOException {
        int readNextCode = super.readNextCode();
        if (readNextCode >= 0) {
            this.totalCodesRead++;
        }
        return readNextCode;
    }

    private void reAlignReading() throws IOException {
        long j = 8 - (this.totalCodesRead % 8);
        if (j == 8) {
            j = 0;
        }
        for (long j2 = 0; j2 < j; j2++) {
            readNextCode();
        }
        this.in.clearBitCache();
    }

    /* access modifiers changed from: protected */
    public int addEntry(int i, byte b) throws IOException {
        int codeSize = 1 << getCodeSize();
        int addEntry = addEntry(i, b, codeSize);
        if (getTableSize() == codeSize && getCodeSize() < this.maxCodeSize) {
            reAlignReading();
            incrementCodeSize();
        }
        return addEntry;
    }

    /* access modifiers changed from: protected */
    public int decompressNextSymbol() throws IOException {
        int readNextCode = readNextCode();
        if (readNextCode < 0) {
            return -1;
        }
        boolean z = false;
        if (!this.blockMode || readNextCode != getClearCode()) {
            if (readNextCode == getTableSize()) {
                addRepeatOfPreviousCode();
                z = true;
            } else if (readNextCode > getTableSize()) {
                throw new IOException(String.format("Invalid %d bit code 0x%x", new Object[]{Integer.valueOf(getCodeSize()), Integer.valueOf(readNextCode)}));
            }
            return expandCodeToOutputStack(readNextCode, z);
        }
        clearEntries();
        reAlignReading();
        resetCodeSize();
        resetPreviousCode();
        return 0;
    }

    public static boolean matches(byte[] bArr, int i) {
        return i > 3 && bArr[0] == 31 && bArr[1] == -99;
    }
}
