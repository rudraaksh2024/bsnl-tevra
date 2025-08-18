package org.apache.poi.xssf.binary;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.internal.ByteCompanionObject;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInputStream;

@Internal
public abstract class XSSFBParser {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 1000000;
    private static int MAX_RECORD_LENGTH = 1000000;
    private final LittleEndianInputStream is;
    private final SparseBitSet records;

    public abstract void handleRecord(int i, byte[] bArr) throws XSSFBParseException;

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public XSSFBParser(InputStream inputStream) {
        this.is = new LittleEndianInputStream(inputStream);
        this.records = null;
    }

    protected XSSFBParser(InputStream inputStream, SparseBitSet sparseBitSet) {
        this.is = new LittleEndianInputStream(inputStream);
        this.records = sparseBitSet;
    }

    public void parse() throws IOException {
        while (true) {
            int read = this.is.read();
            if (read != -1) {
                readNext((byte) read);
            } else {
                return;
            }
        }
    }

    private void readNext(byte b) throws IOException {
        int i = (b >> 7) & 1;
        int i2 = b;
        if (i == 1) {
            i2 = ((byte) (b & ByteCompanionObject.MAX_VALUE)) + (((byte) (this.is.readByte() & ByteCompanionObject.MAX_VALUE)) << 7);
        }
        long j = 0;
        int i3 = 0;
        boolean z = false;
        while (i3 < 4 && !z) {
            byte readByte = this.is.readByte();
            boolean z2 = ((readByte >> 7) & 1) == 0;
            j += (long) (((byte) (readByte & ByteCompanionObject.MAX_VALUE)) << (i3 * 7));
            i3++;
            z = z2;
        }
        SparseBitSet sparseBitSet = this.records;
        if (sparseBitSet == null || sparseBitSet.get(i2)) {
            byte[] safelyAllocate = IOUtils.safelyAllocate(j, MAX_RECORD_LENGTH);
            this.is.readFully(safelyAllocate);
            handleRecord(i2, safelyAllocate);
            return;
        }
        long skipFully = IOUtils.skipFully(this.is, j);
        if (skipFully != j) {
            throw new XSSFBParseException("End of file reached before expected.\tTried to skip " + j + ", but only skipped " + skipFully);
        }
    }
}
