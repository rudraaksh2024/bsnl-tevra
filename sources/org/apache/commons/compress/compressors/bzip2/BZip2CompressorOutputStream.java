package org.apache.commons.compress.compressors.bzip2;

import androidx.core.view.InputDeviceCompat;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import kotlin.UByte;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;

public class BZip2CompressorOutputStream extends CompressorOutputStream implements BZip2Constants {
    private static final int GREATER_ICOST = 15;
    private static final int LESSER_ICOST = 0;
    public static final int MAX_BLOCKSIZE = 9;
    public static final int MIN_BLOCKSIZE = 1;
    private final int allowableBlockSize;
    private int blockCRC;
    private final int blockSize100k;
    private BlockSort blockSorter;
    private int bsBuff;
    private int bsLive;
    private volatile boolean closed;
    private int combinedCRC;
    private final CRC crc;
    private int currentChar;
    private Data data;
    private int last;
    private int nInUse;
    private int nMTF;
    private OutputStream out;
    private int runLength;

    private static void hbMakeCodeLengths(byte[] bArr, int[] iArr, Data data2, int i, int i2) {
        Data data3 = data2;
        int i3 = i;
        int[] iArr2 = data3.heap;
        int[] iArr3 = data3.weight;
        int[] iArr4 = data3.parent;
        int i4 = i3;
        while (true) {
            i4--;
            int i5 = 1;
            if (i4 < 0) {
                break;
            }
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            if (i7 != 0) {
                i5 = i7;
            }
            iArr3[i6] = i5 << 8;
        }
        boolean z = true;
        while (z) {
            iArr2[0] = 0;
            iArr3[0] = 0;
            iArr4[0] = -2;
            int i8 = 0;
            for (int i9 = 1; i9 <= i3; i9++) {
                iArr4[i9] = -1;
                i8++;
                iArr2[i8] = i9;
                int i10 = i8;
                while (true) {
                    int i11 = iArr3[i9];
                    int i12 = i10 >> 1;
                    int i13 = iArr2[i12];
                    if (i11 >= iArr3[i13]) {
                        break;
                    }
                    iArr2[i10] = i13;
                    i10 = i12;
                }
                iArr2[i10] = i9;
            }
            int i14 = i3;
            while (i8 > 1) {
                int i15 = iArr2[1];
                int i16 = iArr2[i8];
                iArr2[1] = i16;
                int i17 = i8 - 1;
                int i18 = 1;
                while (true) {
                    int i19 = i18 << 1;
                    if (i19 > i17) {
                        break;
                    }
                    if (i19 < i17) {
                        int i20 = i19 + 1;
                        if (iArr3[iArr2[i20]] < iArr3[iArr2[i19]]) {
                            i19 = i20;
                        }
                    }
                    int i21 = iArr3[i16];
                    int i22 = iArr2[i19];
                    if (i21 < iArr3[i22]) {
                        break;
                    }
                    iArr2[i18] = i22;
                    i18 = i19;
                }
                iArr2[i18] = i16;
                int i23 = iArr2[1];
                int i24 = iArr2[i17];
                iArr2[1] = i24;
                int i25 = i17 - 1;
                int i26 = 1;
                while (true) {
                    int i27 = i26 << 1;
                    if (i27 > i25) {
                        break;
                    }
                    if (i27 < i25) {
                        int i28 = i27 + 1;
                        if (iArr3[iArr2[i28]] < iArr3[iArr2[i27]]) {
                            i27 = i28;
                        }
                    }
                    int i29 = iArr3[i24];
                    int i30 = iArr2[i27];
                    if (i29 < iArr3[i30]) {
                        break;
                    }
                    iArr2[i26] = i30;
                    i26 = i27;
                }
                iArr2[i26] = i24;
                i14++;
                iArr4[i23] = i14;
                iArr4[i15] = i14;
                int i31 = iArr3[i15];
                int i32 = iArr3[i23];
                int i33 = (i31 & InputDeviceCompat.SOURCE_ANY) + (i32 & InputDeviceCompat.SOURCE_ANY);
                int i34 = i31 & 255;
                int i35 = i32 & 255;
                if (i34 <= i35) {
                    i34 = i35;
                }
                iArr3[i14] = (i34 + 1) | i33;
                iArr4[i14] = -1;
                i8 = i25 + 1;
                iArr2[i8] = i14;
                int i36 = iArr3[i14];
                int i37 = i8;
                while (true) {
                    int i38 = i37 >> 1;
                    int i39 = iArr2[i38];
                    if (i36 >= iArr3[i39]) {
                        break;
                    }
                    iArr2[i37] = i39;
                    i37 = i38;
                }
                iArr2[i37] = i14;
            }
            z = false;
            for (int i40 = 1; i40 <= i3; i40++) {
                int i41 = i40;
                int i42 = 0;
                while (true) {
                    i41 = iArr4[i41];
                    if (i41 < 0) {
                        break;
                    }
                    i42++;
                }
                bArr[i40 - 1] = (byte) i42;
                if (i42 > i2) {
                    z = true;
                }
            }
            int i43 = i2;
            if (z) {
                for (int i44 = 1; i44 < i3; i44++) {
                    iArr3[i44] = (((iArr3[i44] >> 8) >> 1) + 1) << 8;
                }
            }
        }
    }

    public static int chooseBlockSize(long j) {
        if (j > 0) {
            return (int) Math.min((j / 132000) + 1, 9);
        }
        return 9;
    }

    public BZip2CompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, 9);
    }

    public BZip2CompressorOutputStream(OutputStream outputStream, int i) throws IOException {
        this.crc = new CRC();
        this.currentChar = -1;
        if (i < 1) {
            throw new IllegalArgumentException("blockSize(" + i + ") < 1");
        } else if (i <= 9) {
            this.blockSize100k = i;
            this.out = outputStream;
            this.allowableBlockSize = (i * BZip2Constants.BASEBLOCKSIZE) - 20;
            init();
        } else {
            throw new IllegalArgumentException("blockSize(" + i + ") > 9");
        }
    }

    public void write(int i) throws IOException {
        if (!this.closed) {
            write0(i);
            return;
        }
        throw new IOException("Closed");
    }

    private void writeRun() throws IOException {
        int i = this.last;
        if (i < this.allowableBlockSize) {
            int i2 = this.currentChar;
            Data data2 = this.data;
            data2.inUse[i2] = true;
            byte b = (byte) i2;
            int i3 = this.runLength;
            this.crc.updateCRC(i2, i3);
            if (i3 == 1) {
                data2.block[i + 2] = b;
                this.last = i + 1;
            } else if (i3 == 2) {
                int i4 = i + 2;
                data2.block[i4] = b;
                data2.block[i + 3] = b;
                this.last = i4;
            } else if (i3 != 3) {
                int i5 = i3 - 4;
                data2.inUse[i5] = true;
                byte[] bArr = data2.block;
                bArr[i + 2] = b;
                bArr[i + 3] = b;
                bArr[i + 4] = b;
                int i6 = i + 5;
                bArr[i6] = b;
                bArr[i + 6] = (byte) i5;
                this.last = i6;
            } else {
                byte[] bArr2 = data2.block;
                bArr2[i + 2] = b;
                int i7 = i + 3;
                bArr2[i7] = b;
                bArr2[i + 4] = b;
                this.last = i7;
            }
        } else {
            endBlock();
            initBlock();
            writeRun();
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        if (!this.closed) {
            System.err.println("Unclosed BZip2CompressorOutputStream detected, will *not* close it");
        }
        super.finalize();
    }

    public void finish() throws IOException {
        if (!this.closed) {
            this.closed = true;
            try {
                if (this.runLength > 0) {
                    writeRun();
                }
                this.currentChar = -1;
                endBlock();
                endCompression();
            } finally {
                this.out = null;
                this.blockSorter = null;
                this.data = null;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        if (r0 != null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0019, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001c, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
            r2 = this;
            boolean r0 = r2.closed
            if (r0 != 0) goto L_0x001d
            java.io.OutputStream r0 = r2.out
            r2.finish()     // Catch:{ all -> 0x000f }
            if (r0 == 0) goto L_0x001d
            r0.close()
            goto L_0x001d
        L_0x000f:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0011 }
        L_0x0011:
            r1 = move-exception
            if (r0 == 0) goto L_0x001c
            r0.close()     // Catch:{ all -> 0x0018 }
            goto L_0x001c
        L_0x0018:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x001c:
            throw r1
        L_0x001d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream.close():void");
    }

    public void flush() throws IOException {
        OutputStream outputStream = this.out;
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    private void init() throws IOException {
        bsPutUByte(66);
        bsPutUByte(90);
        this.data = new Data(this.blockSize100k);
        this.blockSorter = new BlockSort(this.data);
        bsPutUByte(104);
        bsPutUByte(this.blockSize100k + 48);
        this.combinedCRC = 0;
        initBlock();
    }

    private void initBlock() {
        this.crc.initializeCRC();
        this.last = -1;
        boolean[] zArr = this.data.inUse;
        int i = 256;
        while (true) {
            i--;
            if (i >= 0) {
                zArr[i] = false;
            } else {
                return;
            }
        }
    }

    private void endBlock() throws IOException {
        int finalCRC = this.crc.getFinalCRC();
        this.blockCRC = finalCRC;
        int i = this.combinedCRC;
        this.combinedCRC = finalCRC ^ ((i >>> 31) | (i << 1));
        if (this.last != -1) {
            blockSort();
            bsPutUByte(49);
            bsPutUByte(65);
            bsPutUByte(89);
            bsPutUByte(38);
            bsPutUByte(83);
            bsPutUByte(89);
            bsPutInt(this.blockCRC);
            bsW(1, 0);
            moveToFrontCodeAndSend();
        }
    }

    private void endCompression() throws IOException {
        bsPutUByte(23);
        bsPutUByte(114);
        bsPutUByte(69);
        bsPutUByte(56);
        bsPutUByte(80);
        bsPutUByte(144);
        bsPutInt(this.combinedCRC);
        bsFinishedWithStream();
    }

    public final int getBlockSize() {
        return this.blockSize100k;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("offs(" + i + ") < 0.");
        } else if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 > bArr.length) {
                throw new IndexOutOfBoundsException("offs(" + i + ") + len(" + i2 + ") > buf.length(" + bArr.length + ").");
            } else if (!this.closed) {
                while (i < i3) {
                    write0(bArr[i]);
                    i++;
                }
            } else {
                throw new IOException("Stream closed");
            }
        } else {
            throw new IndexOutOfBoundsException("len(" + i2 + ") < 0.");
        }
    }

    private void write0(int i) throws IOException {
        int i2 = this.currentChar;
        if (i2 != -1) {
            int i3 = i & 255;
            if (i2 == i3) {
                int i4 = this.runLength + 1;
                this.runLength = i4;
                if (i4 > 254) {
                    writeRun();
                    this.currentChar = -1;
                    this.runLength = 0;
                    return;
                }
                return;
            }
            writeRun();
            this.runLength = 1;
            this.currentChar = i3;
            return;
        }
        this.currentChar = i & 255;
        this.runLength++;
    }

    private static void hbAssignCodes(int[] iArr, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i <= i2) {
            for (int i5 = 0; i5 < i3; i5++) {
                if ((bArr[i5] & UByte.MAX_VALUE) == i) {
                    iArr[i5] = i4;
                    i4++;
                }
            }
            i4 <<= 1;
            i++;
        }
    }

    private void bsFinishedWithStream() throws IOException {
        while (this.bsLive > 0) {
            this.out.write(this.bsBuff >> 24);
            this.bsBuff <<= 8;
            this.bsLive -= 8;
        }
    }

    private void bsW(int i, int i2) throws IOException {
        OutputStream outputStream = this.out;
        int i3 = this.bsLive;
        int i4 = this.bsBuff;
        while (i3 >= 8) {
            outputStream.write(i4 >> 24);
            i4 <<= 8;
            i3 -= 8;
        }
        this.bsBuff = (i2 << ((32 - i3) - i)) | i4;
        this.bsLive = i3 + i;
    }

    private void bsPutUByte(int i) throws IOException {
        bsW(8, i);
    }

    private void bsPutInt(int i) throws IOException {
        bsW(8, (i >> 24) & 255);
        bsW(8, (i >> 16) & 255);
        bsW(8, (i >> 8) & 255);
        bsW(8, i & 255);
    }

    private void sendMTFValues() throws IOException {
        byte[][] bArr = this.data.sendMTFValues_len;
        int i = 2;
        int i2 = this.nInUse + 2;
        int i3 = 6;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            byte[] bArr2 = bArr[i3];
            int i4 = i2;
            while (true) {
                i4--;
                if (i4 >= 0) {
                    bArr2[i4] = IntersectionPtg.sid;
                }
            }
        }
        int i5 = this.nMTF;
        if (i5 >= 200) {
            i = i5 < 600 ? 3 : i5 < 1200 ? 4 : i5 < 2400 ? 5 : 6;
        }
        sendMTFValues0(i, i2);
        int sendMTFValues1 = sendMTFValues1(i, i2);
        sendMTFValues2(i, sendMTFValues1);
        sendMTFValues3(i, i2);
        sendMTFValues4();
        sendMTFValues5(i, sendMTFValues1);
        sendMTFValues6(i, i2);
        sendMTFValues7();
    }

    private void sendMTFValues0(int i, int i2) {
        byte[][] bArr = this.data.sendMTFValues_len;
        int[] iArr = this.data.mtfFreq;
        int i3 = this.nMTF;
        int i4 = 0;
        for (int i5 = i; i5 > 0; i5--) {
            int i6 = i3 / i5;
            int i7 = i4 - 1;
            int i8 = i2 - 1;
            int i9 = 0;
            while (i9 < i6 && i7 < i8) {
                i7++;
                i9 += iArr[i7];
            }
            if (!(i7 <= i4 || i5 == i || i5 == 1 || (1 & (i - i5)) == 0)) {
                i9 -= iArr[i7];
                i7--;
            }
            byte[] bArr2 = bArr[i5 - 1];
            int i10 = i2;
            while (true) {
                i10--;
                if (i10 < 0) {
                    break;
                } else if (i10 < i4 || i10 > i7) {
                    bArr2[i10] = IntersectionPtg.sid;
                } else {
                    bArr2[i10] = 0;
                }
            }
            i4 = i7 + 1;
            i3 -= i9;
        }
    }

    private int sendMTFValues1(int i, int i2) {
        byte[] bArr;
        int i3;
        BZip2CompressorOutputStream bZip2CompressorOutputStream = this;
        int i4 = i;
        Data data2 = bZip2CompressorOutputStream.data;
        int[][] iArr = data2.sendMTFValues_rfreq;
        int[] iArr2 = data2.sendMTFValues_fave;
        short[] sArr = data2.sendMTFValues_cost;
        char[] cArr = data2.sfmap;
        byte[] bArr2 = data2.selector;
        byte[][] bArr3 = data2.sendMTFValues_len;
        int i5 = 0;
        byte[] bArr4 = bArr3[0];
        byte[] bArr5 = bArr3[1];
        byte[] bArr6 = bArr3[2];
        byte[] bArr7 = bArr3[3];
        int i6 = 4;
        byte[] bArr8 = bArr3[4];
        byte[] bArr9 = bArr3[5];
        int i7 = bZip2CompressorOutputStream.nMTF;
        int i8 = 0;
        int i9 = 0;
        while (i8 < i6) {
            int i10 = i4;
            while (true) {
                i10--;
                if (i10 < 0) {
                    break;
                }
                iArr2[i10] = i5;
                int[] iArr3 = iArr[i10];
                int i11 = i2;
                while (true) {
                    i11--;
                    if (i11 >= 0) {
                        iArr3[i11] = i5;
                    }
                }
            }
            int i12 = i5;
            i9 = i12;
            while (i12 < bZip2CompressorOutputStream.nMTF) {
                int i13 = i12;
                int min = Math.min((i12 + 50) - 1, i7 - 1);
                if (i4 == 6) {
                    int i14 = i13;
                    short s = 0;
                    short s2 = 0;
                    short s3 = 0;
                    short s4 = 0;
                    short s5 = 0;
                    short s6 = 0;
                    while (i14 <= min) {
                        char c = cArr[i14];
                        short s7 = (short) (s2 + (bArr5[c] & UByte.MAX_VALUE));
                        short s8 = (short) (s3 + (bArr6[c] & UByte.MAX_VALUE));
                        short s9 = (short) (s4 + (bArr7[c] & UByte.MAX_VALUE));
                        short s10 = (short) (s5 + (bArr8[c] & UByte.MAX_VALUE));
                        i14++;
                        s6 = (short) (s6 + (bArr9[c] & UByte.MAX_VALUE));
                        s5 = s10;
                        bArr4 = bArr4;
                        s4 = s9;
                        s3 = s8;
                        s2 = s7;
                        s = (short) (s + (bArr4[c] & UByte.MAX_VALUE));
                        i7 = i7;
                    }
                    bArr = bArr4;
                    i3 = i7;
                    sArr[0] = s;
                    sArr[1] = s2;
                    sArr[2] = s3;
                    sArr[3] = s4;
                    sArr[4] = s5;
                    sArr[5] = s6;
                } else {
                    bArr = bArr4;
                    i3 = i7;
                    int i15 = i4;
                    while (true) {
                        i15--;
                        if (i15 < 0) {
                            break;
                        }
                        sArr[i15] = 0;
                    }
                    for (int i16 = i13; i16 <= min; i16++) {
                        char c2 = cArr[i16];
                        int i17 = i4;
                        while (true) {
                            i17--;
                            if (i17 < 0) {
                                break;
                            }
                            sArr[i17] = (short) (sArr[i17] + (bArr3[i17][c2] & UByte.MAX_VALUE));
                        }
                    }
                }
                short s11 = 999999999;
                int i18 = i4;
                int i19 = -1;
                while (true) {
                    i18--;
                    if (i18 < 0) {
                        break;
                    }
                    byte[] bArr10 = bArr5;
                    short s12 = sArr[i18];
                    if (s12 < s11) {
                        s11 = s12;
                        i19 = i18;
                    }
                    bArr5 = bArr10;
                }
                byte[] bArr11 = bArr5;
                iArr2[i19] = iArr2[i19] + 1;
                bArr2[i9] = (byte) i19;
                i9++;
                int[] iArr4 = iArr[i19];
                for (int i20 = i13; i20 <= min; i20++) {
                    char c3 = cArr[i20];
                    iArr4[c3] = iArr4[c3] + 1;
                }
                i12 = min + 1;
                bArr5 = bArr11;
                i7 = i3;
                bArr4 = bArr;
            }
            byte[] bArr12 = bArr4;
            byte[] bArr13 = bArr5;
            int i21 = i7;
            int i22 = 0;
            while (i22 < i4) {
                hbMakeCodeLengths(bArr3[i22], iArr[i22], bZip2CompressorOutputStream.data, i2, 20);
                i22++;
                bZip2CompressorOutputStream = this;
            }
            int i23 = i2;
            i8++;
            i5 = 0;
            bZip2CompressorOutputStream = this;
            i6 = 4;
            bArr5 = bArr13;
            i7 = i21;
            bArr4 = bArr12;
        }
        return i9;
    }

    private void sendMTFValues2(int i, int i2) {
        Data data2 = this.data;
        byte[] bArr = data2.sendMTFValues2_pos;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            bArr[i] = (byte) i;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = data2.selector[i3];
            byte b2 = bArr[0];
            int i4 = 0;
            while (b != b2) {
                i4++;
                byte b3 = bArr[i4];
                bArr[i4] = b2;
                b2 = b3;
            }
            bArr[0] = b2;
            data2.selectorMtf[i3] = (byte) i4;
        }
    }

    private void sendMTFValues3(int i, int i2) {
        int[][] iArr = this.data.sendMTFValues_code;
        byte[][] bArr = this.data.sendMTFValues_len;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            byte b = 32;
            int i4 = i2;
            byte b2 = 0;
            while (true) {
                i4--;
                if (i4 < 0) {
                    break;
                }
                byte b3 = bArr2[i4] & UByte.MAX_VALUE;
                if (b3 > b2) {
                    b2 = b3;
                }
                if (b3 < b) {
                    b = b3;
                }
            }
            hbAssignCodes(iArr[i3], bArr[i3], b, b2, i2);
        }
    }

    private void sendMTFValues4() throws IOException {
        boolean[] zArr = this.data.inUse;
        boolean[] zArr2 = this.data.sentMTFValues4_inUse16;
        int i = 16;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            zArr2[i] = false;
            int i2 = i * 16;
            int i3 = 16;
            while (true) {
                i3--;
                if (i3 >= 0) {
                    if (zArr[i2 + i3]) {
                        zArr2[i] = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        for (int i4 = 0; i4 < 16; i4++) {
            bsW(1, zArr2[i4] ? 1 : 0);
        }
        OutputStream outputStream = this.out;
        int i5 = this.bsLive;
        int i6 = this.bsBuff;
        for (int i7 = 0; i7 < 16; i7++) {
            if (zArr2[i7]) {
                int i8 = i7 * 16;
                for (int i9 = 0; i9 < 16; i9++) {
                    while (i5 >= 8) {
                        outputStream.write(i6 >> 24);
                        i6 <<= 8;
                        i5 -= 8;
                    }
                    if (zArr[i8 + i9]) {
                        i6 |= 1 << ((32 - i5) - 1);
                    }
                    i5++;
                }
            }
        }
        this.bsBuff = i6;
        this.bsLive = i5;
    }

    private void sendMTFValues5(int i, int i2) throws IOException {
        bsW(3, i);
        bsW(15, i2);
        OutputStream outputStream = this.out;
        byte[] bArr = this.data.selectorMtf;
        int i3 = this.bsLive;
        int i4 = this.bsBuff;
        for (int i5 = 0; i5 < i2; i5++) {
            byte b = bArr[i5] & UByte.MAX_VALUE;
            for (int i6 = 0; i6 < b; i6++) {
                while (i3 >= 8) {
                    outputStream.write(i4 >> 24);
                    i4 <<= 8;
                    i3 -= 8;
                }
                i4 |= 1 << ((32 - i3) - 1);
                i3++;
            }
            while (i3 >= 8) {
                outputStream.write(i4 >> 24);
                i4 <<= 8;
                i3 -= 8;
            }
            i3++;
        }
        this.bsBuff = i4;
        this.bsLive = i3;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sendMTFValues6(int r18, int r19) throws java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream$Data r1 = r0.data
            byte[][] r1 = r1.sendMTFValues_len
            java.io.OutputStream r2 = r0.out
            int r3 = r0.bsLive
            int r4 = r0.bsBuff
            r5 = 0
            r6 = r18
            r7 = r5
        L_0x0010:
            if (r7 >= r6) goto L_0x0080
            r8 = r1[r7]
            byte r9 = r8[r5]
            r9 = r9 & 255(0xff, float:3.57E-43)
        L_0x0018:
            r10 = 8
            if (r3 < r10) goto L_0x0026
            int r10 = r4 >> 24
            r2.write(r10)
            int r4 = r4 << 8
            int r3 = r3 + -8
            goto L_0x0018
        L_0x0026:
            int r11 = 32 - r3
            int r11 = r11 + -5
            int r11 = r9 << r11
            r4 = r4 | r11
            int r3 = r3 + 5
            r11 = r19
            r12 = r5
        L_0x0032:
            if (r12 >= r11) goto L_0x007d
            byte r13 = r8[r12]
            r13 = r13 & 255(0xff, float:3.57E-43)
        L_0x0038:
            r14 = 2
            if (r9 >= r13) goto L_0x0051
        L_0x003b:
            if (r3 < r10) goto L_0x0047
            int r15 = r4 >> 24
            r2.write(r15)
            int r4 = r4 << 8
            int r3 = r3 + -8
            goto L_0x003b
        L_0x0047:
            int r15 = 32 - r3
            int r15 = r15 - r14
            int r14 = r14 << r15
            r4 = r4 | r14
            int r3 = r3 + 2
            int r9 = r9 + 1
            goto L_0x0038
        L_0x0051:
            if (r9 <= r13) goto L_0x006c
        L_0x0053:
            if (r3 < r10) goto L_0x005f
            int r15 = r4 >> 24
            r2.write(r15)
            int r4 = r4 << 8
            int r3 = r3 + -8
            goto L_0x0053
        L_0x005f:
            int r15 = 32 - r3
            int r15 = r15 - r14
            r16 = 3
            int r15 = r16 << r15
            r4 = r4 | r15
            int r3 = r3 + 2
            int r9 = r9 + -1
            goto L_0x0051
        L_0x006c:
            if (r3 < r10) goto L_0x0078
            int r13 = r4 >> 24
            r2.write(r13)
            int r4 = r4 << 8
            int r3 = r3 + -8
            goto L_0x006c
        L_0x0078:
            int r3 = r3 + 1
            int r12 = r12 + 1
            goto L_0x0032
        L_0x007d:
            int r7 = r7 + 1
            goto L_0x0010
        L_0x0080:
            r0.bsBuff = r4
            r0.bsLive = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream.sendMTFValues6(int, int):void");
    }

    private void sendMTFValues7() throws IOException {
        Data data2 = this.data;
        byte[][] bArr = data2.sendMTFValues_len;
        int[][] iArr = data2.sendMTFValues_code;
        OutputStream outputStream = this.out;
        byte[] bArr2 = data2.selector;
        char[] cArr = data2.sfmap;
        int i = this.nMTF;
        int i2 = this.bsLive;
        int i3 = this.bsBuff;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int min = Math.min((i4 + 50) - 1, i - 1);
            byte b = bArr2[i5] & UByte.MAX_VALUE;
            int[] iArr2 = iArr[b];
            byte[] bArr3 = bArr[b];
            while (i4 <= min) {
                char c = cArr[i4];
                while (i2 >= 8) {
                    outputStream.write(i3 >> 24);
                    i3 <<= 8;
                    i2 -= 8;
                }
                byte b2 = bArr3[c] & UByte.MAX_VALUE;
                i3 |= iArr2[c] << ((32 - i2) - b2);
                i2 += b2;
                i4++;
            }
            i4 = min + 1;
            i5++;
        }
        this.bsBuff = i3;
        this.bsLive = i2;
    }

    private void moveToFrontCodeAndSend() throws IOException {
        bsW(24, this.data.origPtr);
        generateMTFValues();
        sendMTFValues();
    }

    private void blockSort() {
        this.blockSorter.blockSort(this.data, this.last);
    }

    private void generateMTFValues() {
        int i;
        int i2 = this.last;
        Data data2 = this.data;
        boolean[] zArr = data2.inUse;
        byte[] bArr = data2.block;
        int[] iArr = data2.fmap;
        char[] cArr = data2.sfmap;
        int[] iArr2 = data2.mtfFreq;
        byte[] bArr2 = data2.unseqToSeq;
        byte[] bArr3 = data2.generateMTFValues_yy;
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            if (zArr[i4]) {
                bArr2[i4] = (byte) i3;
                i3++;
            }
        }
        this.nInUse = i3;
        int i5 = i3 + 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            iArr2[i6] = 0;
        }
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            bArr3[i3] = (byte) i3;
        }
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 <= i2; i9++) {
            byte b = bArr2[bArr[iArr[i9]] & UByte.MAX_VALUE];
            byte b2 = bArr3[0];
            int i10 = 0;
            while (b != b2) {
                i10++;
                byte b3 = bArr3[i10];
                bArr3[i10] = b2;
                b2 = b3;
            }
            bArr3[0] = b2;
            if (i10 == 0) {
                i7++;
            } else {
                if (i7 > 0) {
                    int i11 = i7 - 1;
                    while (true) {
                        if ((i11 & 1) == 0) {
                            cArr[i8] = 0;
                            i8++;
                            iArr2[0] = iArr2[0] + 1;
                        } else {
                            cArr[i8] = 1;
                            i8++;
                            iArr2[1] = iArr2[1] + 1;
                        }
                        if (i11 < 2) {
                            break;
                        }
                        i11 = (i11 - 2) >> 1;
                    }
                    i7 = 0;
                }
                int i12 = i10 + 1;
                cArr[i8] = (char) i12;
                i8++;
                iArr2[i12] = iArr2[i12] + 1;
            }
        }
        if (i7 > 0) {
            int i13 = i7 - 1;
            while (true) {
                if ((i13 & 1) == 0) {
                    cArr[i8] = 0;
                    i = i8 + 1;
                    iArr2[0] = iArr2[0] + 1;
                } else {
                    cArr[i8] = 1;
                    i = i8 + 1;
                    iArr2[1] = iArr2[1] + 1;
                }
                if (i13 < 2) {
                    break;
                }
                i13 = (i13 - 2) >> 1;
            }
        }
        cArr[i8] = (char) i5;
        iArr2[i5] = iArr2[i5] + 1;
        this.nMTF = i8 + 1;
    }

    static final class Data {
        final byte[] block;
        final int[] fmap;
        final byte[] generateMTFValues_yy = new byte[256];
        final int[] heap = new int[260];
        final boolean[] inUse = new boolean[256];
        final int[] mtfFreq = new int[BZip2Constants.MAX_ALPHA_SIZE];
        int origPtr;
        final int[] parent = new int[516];
        final byte[] selector = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] selectorMtf = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] sendMTFValues2_pos = new byte[6];
        final int[][] sendMTFValues_code = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{6, BZip2Constants.MAX_ALPHA_SIZE}));
        final short[] sendMTFValues_cost = new short[6];
        final int[] sendMTFValues_fave = new int[6];
        final byte[][] sendMTFValues_len = ((byte[][]) Array.newInstance(Byte.TYPE, new int[]{6, BZip2Constants.MAX_ALPHA_SIZE}));
        final int[][] sendMTFValues_rfreq = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{6, BZip2Constants.MAX_ALPHA_SIZE}));
        final boolean[] sentMTFValues4_inUse16 = new boolean[16];
        final char[] sfmap;
        final byte[] unseqToSeq = new byte[256];
        final int[] weight = new int[516];

        Data(int i) {
            int i2 = i * BZip2Constants.BASEBLOCKSIZE;
            this.block = new byte[(i2 + 1 + 20)];
            this.fmap = new int[i2];
            this.sfmap = new char[(i2 * 2)];
        }
    }
}
