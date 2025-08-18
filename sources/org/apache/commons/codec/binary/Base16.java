package org.apache.commons.codec.binary;

import kotlin.UByte;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodec;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.NotEqualPtg;

public class Base16 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 4;
    private static final int BYTES_PER_ENCODED_BLOCK = 2;
    private static final int BYTES_PER_UNENCODED_BLOCK = 1;
    private static final byte[] LOWER_CASE_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, NotEqualPtg.sid, IntersectionPtg.sid};
    private static final byte[] LOWER_CASE_ENCODE_TABLE = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, PaletteRecord.STANDARD_PALETTE_SIZE, 57, 97, 98, 99, 100, 101, 102};
    private static final int MASK_4BITS = 15;
    private static final byte[] UPPER_CASE_DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, NotEqualPtg.sid, IntersectionPtg.sid};
    private static final byte[] UPPER_CASE_ENCODE_TABLE = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, PaletteRecord.STANDARD_PALETTE_SIZE, 57, 65, 66, 67, 68, 69, 70};
    private final byte[] decodeTable;
    private final byte[] encodeTable;

    public Base16() {
        this(false);
    }

    public Base16(boolean z) {
        this(z, DECODING_POLICY_DEFAULT);
    }

    public Base16(boolean z, CodecPolicy codecPolicy) {
        super(1, 2, 0, 0, (byte) 61, codecPolicy);
        if (z) {
            this.encodeTable = LOWER_CASE_ENCODE_TABLE;
            this.decodeTable = LOWER_CASE_DECODE_TABLE;
            return;
        }
        this.encodeTable = UPPER_CASE_ENCODE_TABLE;
        this.decodeTable = UPPER_CASE_DECODE_TABLE;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decode(byte[] r8, int r9, int r10, org.apache.commons.codec.binary.BaseNCodec.Context r11) {
        /*
            r7 = this;
            boolean r0 = r11.eof
            r1 = 1
            if (r0 != 0) goto L_0x007e
            if (r10 >= 0) goto L_0x0009
            goto L_0x007e
        L_0x0009:
            int r0 = r8.length
            int r0 = r0 - r9
            int r10 = java.lang.Math.min(r0, r10)
            int r0 = r11.ibitWorkArea
            r2 = 0
            if (r0 == 0) goto L_0x0016
            r0 = r1
            goto L_0x0017
        L_0x0016:
            r0 = r2
        L_0x0017:
            int r0 = r0 + r10
            if (r0 != r1) goto L_0x0026
            if (r0 != r10) goto L_0x0026
            byte r8 = r8[r9]
            int r7 = r7.decodeOctet(r8)
            int r7 = r7 + r1
            r11.ibitWorkArea = r7
            return
        L_0x0026:
            int r3 = r0 % 2
            if (r3 != 0) goto L_0x002c
            r3 = r0
            goto L_0x002e
        L_0x002c:
            int r3 = r0 + -1
        L_0x002e:
            int r4 = r3 / 2
            byte[] r4 = r7.ensureBufferSize(r4, r11)
            if (r10 >= r0) goto L_0x0051
            int r0 = r11.ibitWorkArea
            int r0 = r0 - r1
            int r0 = r0 << 4
            int r5 = r9 + 1
            byte r9 = r8[r9]
            int r9 = r7.decodeOctet(r9)
            r9 = r9 | r0
            int r0 = r11.pos
            int r6 = r0 + 1
            r11.pos = r6
            byte r9 = (byte) r9
            r4[r0] = r9
            r11.ibitWorkArea = r2
            r2 = 2
        L_0x0050:
            r9 = r5
        L_0x0051:
            if (r2 >= r3) goto L_0x0072
            int r0 = r9 + 1
            byte r9 = r8[r9]
            int r9 = r7.decodeOctet(r9)
            int r9 = r9 << 4
            int r5 = r0 + 1
            byte r0 = r8[r0]
            int r0 = r7.decodeOctet(r0)
            r9 = r9 | r0
            int r2 = r2 + 2
            int r0 = r11.pos
            int r6 = r0 + 1
            r11.pos = r6
            byte r9 = (byte) r9
            r4[r0] = r9
            goto L_0x0050
        L_0x0072:
            if (r2 >= r10) goto L_0x007d
            byte r8 = r8[r2]
            int r7 = r7.decodeOctet(r8)
            int r7 = r7 + r1
            r11.ibitWorkArea = r7
        L_0x007d:
            return
        L_0x007e:
            r11.eof = r1
            int r8 = r11.ibitWorkArea
            if (r8 == 0) goto L_0x0087
            r7.validateTrailingCharacter()
        L_0x0087:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.binary.Base16.decode(byte[], int, int, org.apache.commons.codec.binary.BaseNCodec$Context):void");
    }

    private int decodeOctet(byte b) {
        byte b2 = b & UByte.MAX_VALUE;
        byte[] bArr = this.decodeTable;
        byte b3 = b2 < bArr.length ? bArr[b] : -1;
        if (b3 != -1) {
            return b3;
        }
        throw new IllegalArgumentException("Invalid octet in encoded value: " + b);
    }

    /* access modifiers changed from: package-private */
    public void encode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        if (!context.eof) {
            if (i2 < 0) {
                context.eof = true;
                return;
            }
            int i3 = i2 * 2;
            if (i3 >= 0) {
                byte[] ensureBufferSize = ensureBufferSize(i3, context);
                int i4 = i2 + i;
                while (i < i4) {
                    byte b = bArr[i];
                    byte b2 = b & IntersectionPtg.sid;
                    int i5 = context.pos;
                    context.pos = i5 + 1;
                    ensureBufferSize[i5] = this.encodeTable[(b >> 4) & 15];
                    int i6 = context.pos;
                    context.pos = i6 + 1;
                    ensureBufferSize[i6] = this.encodeTable[b2];
                    i++;
                }
                return;
            }
            throw new IllegalArgumentException("Input length exceeds maximum size for encoded data: " + i2);
        }
    }

    public boolean isInAlphabet(byte b) {
        byte b2 = b & UByte.MAX_VALUE;
        byte[] bArr = this.decodeTable;
        return b2 < bArr.length && bArr[b] != -1;
    }

    private void validateTrailingCharacter() {
        if (isStrictDecoding()) {
            throw new IllegalArgumentException("Strict decoding: Last encoded character is a valid base 16 alphabetcharacter but not a possible encoding. Decoding requires at least two characters to create one byte.");
        }
    }
}
