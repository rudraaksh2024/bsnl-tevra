package org.apache.commons.compress.harmony.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class CodecEncoding {
    private static final BHSDCodec[] canonicalCodec = {null, new BHSDCodec(1, 256), new BHSDCodec(1, 256, 1), new BHSDCodec(1, 256, 0, 1), new BHSDCodec(1, 256, 1, 1), new BHSDCodec(2, 256), new BHSDCodec(2, 256, 1), new BHSDCodec(2, 256, 0, 1), new BHSDCodec(2, 256, 1, 1), new BHSDCodec(3, 256), new BHSDCodec(3, 256, 1), new BHSDCodec(3, 256, 0, 1), new BHSDCodec(3, 256, 1, 1), new BHSDCodec(4, 256), new BHSDCodec(4, 256, 1), new BHSDCodec(4, 256, 0, 1), new BHSDCodec(4, 256, 1, 1), new BHSDCodec(5, 4), new BHSDCodec(5, 4, 1), new BHSDCodec(5, 4, 2), new BHSDCodec(5, 16), new BHSDCodec(5, 16, 1), new BHSDCodec(5, 16, 2), new BHSDCodec(5, 32), new BHSDCodec(5, 32, 1), new BHSDCodec(5, 32, 2), new BHSDCodec(5, 64), new BHSDCodec(5, 64, 1), new BHSDCodec(5, 64, 2), new BHSDCodec(5, 128), new BHSDCodec(5, 128, 1), new BHSDCodec(5, 128, 2), new BHSDCodec(5, 4, 0, 1), new BHSDCodec(5, 4, 1, 1), new BHSDCodec(5, 4, 2, 1), new BHSDCodec(5, 16, 0, 1), new BHSDCodec(5, 16, 1, 1), new BHSDCodec(5, 16, 2, 1), new BHSDCodec(5, 32, 0, 1), new BHSDCodec(5, 32, 1, 1), new BHSDCodec(5, 32, 2, 1), new BHSDCodec(5, 64, 0, 1), new BHSDCodec(5, 64, 1, 1), new BHSDCodec(5, 64, 2, 1), new BHSDCodec(5, 128, 0, 1), new BHSDCodec(5, 128, 1, 1), new BHSDCodec(5, 128, 2, 1), new BHSDCodec(2, 192), new BHSDCodec(2, 224), new BHSDCodec(2, 240), new BHSDCodec(2, 248), new BHSDCodec(2, 252), new BHSDCodec(2, 8, 0, 1), new BHSDCodec(2, 8, 1, 1), new BHSDCodec(2, 16, 0, 1), new BHSDCodec(2, 16, 1, 1), new BHSDCodec(2, 32, 0, 1), new BHSDCodec(2, 32, 1, 1), new BHSDCodec(2, 64, 0, 1), new BHSDCodec(2, 64, 1, 1), new BHSDCodec(2, 128, 0, 1), new BHSDCodec(2, 128, 1, 1), new BHSDCodec(2, 192, 0, 1), new BHSDCodec(2, 192, 1, 1), new BHSDCodec(2, 224, 0, 1), new BHSDCodec(2, 224, 1, 1), new BHSDCodec(2, 240, 0, 1), new BHSDCodec(2, 240, 1, 1), new BHSDCodec(2, 248, 0, 1), new BHSDCodec(2, 248, 1, 1), new BHSDCodec(3, 192), new BHSDCodec(3, 224), new BHSDCodec(3, 240), new BHSDCodec(3, 248), new BHSDCodec(3, 252), new BHSDCodec(3, 8, 0, 1), new BHSDCodec(3, 8, 1, 1), new BHSDCodec(3, 16, 0, 1), new BHSDCodec(3, 16, 1, 1), new BHSDCodec(3, 32, 0, 1), new BHSDCodec(3, 32, 1, 1), new BHSDCodec(3, 64, 0, 1), new BHSDCodec(3, 64, 1, 1), new BHSDCodec(3, 128, 0, 1), new BHSDCodec(3, 128, 1, 1), new BHSDCodec(3, 192, 0, 1), new BHSDCodec(3, 192, 1, 1), new BHSDCodec(3, 224, 0, 1), new BHSDCodec(3, 224, 1, 1), new BHSDCodec(3, 240, 0, 1), new BHSDCodec(3, 240, 1, 1), new BHSDCodec(3, 248, 0, 1), new BHSDCodec(3, 248, 1, 1), new BHSDCodec(4, 192), new BHSDCodec(4, 224), new BHSDCodec(4, 240), new BHSDCodec(4, 248), new BHSDCodec(4, 252), new BHSDCodec(4, 8, 0, 1), new BHSDCodec(4, 8, 1, 1), new BHSDCodec(4, 16, 0, 1), new BHSDCodec(4, 16, 1, 1), new BHSDCodec(4, 32, 0, 1), new BHSDCodec(4, 32, 1, 1), new BHSDCodec(4, 64, 0, 1), new BHSDCodec(4, 64, 1, 1), new BHSDCodec(4, 128, 0, 1), new BHSDCodec(4, 128, 1, 1), new BHSDCodec(4, 192, 0, 1), new BHSDCodec(4, 192, 1, 1), new BHSDCodec(4, 224, 0, 1), new BHSDCodec(4, 224, 1, 1), new BHSDCodec(4, 240, 0, 1), new BHSDCodec(4, 240, 1, 1), new BHSDCodec(4, 248, 0, 1), new BHSDCodec(4, 248, 1, 1)};
    private static Map canonicalCodecsToSpecifiers;

    public static Codec getCodec(int i, InputStream inputStream, Codec codec) throws IOException, Pack200Exception {
        Codec codec2;
        Codec codec3;
        Codec codec4;
        BHSDCodec[] bHSDCodecArr = canonicalCodec;
        if (bHSDCodecArr.length != 116) {
            throw new Error("Canonical encodings have been incorrectly modified");
        } else if (i < 0) {
            throw new IllegalArgumentException("Encoding cannot be less than zero");
        } else if (i == 0) {
            return codec;
        } else {
            if (i <= 115) {
                return bHSDCodecArr[i];
            }
            int i2 = 3;
            boolean z = true;
            if (i == 116) {
                int read = inputStream.read();
                if (read != -1) {
                    int i3 = read & 1;
                    int i4 = (read >> 1) & 3;
                    int i5 = ((read >> 3) & 7) + 1;
                    int read2 = inputStream.read();
                    if (read2 != -1) {
                        return new BHSDCodec(i5, read2 + 1, i4, i3);
                    }
                    throw new EOFException("End of buffer read whilst trying to decode codec");
                }
                throw new EOFException("End of buffer read whilst trying to decode codec");
            }
            boolean z2 = false;
            if (i >= 117 && i <= 140) {
                int i6 = i - 117;
                int i7 = i6 & 3;
                boolean z3 = ((i6 >> 2) & 1) == 1;
                boolean z4 = ((i6 >> 3) & 1) == 1;
                if (((i6 >> 4) & 1) == 1) {
                    z2 = true;
                }
                if (!z4 || !z2) {
                    if (z3) {
                        i2 = inputStream.read();
                    }
                    int pow = (i2 + 1) * ((int) Math.pow(16.0d, (double) i7));
                    if (z4) {
                        codec4 = codec;
                    } else {
                        codec4 = getCodec(inputStream.read(), inputStream, codec);
                    }
                    if (!z2) {
                        codec = getCodec(inputStream.read(), inputStream, codec);
                    }
                    return new RunCodec(pow, codec4, codec);
                }
                throw new Pack200Exception("ADef and BDef should never both be true");
            } else if (i < 141 || i > 188) {
                throw new Pack200Exception("Invalid codec encoding byte (" + i + ") found");
            } else {
                int i8 = i - 141;
                boolean z5 = (i8 & 1) == 1;
                boolean z6 = ((i8 >> 1) & 1) == 1;
                int i9 = i8 >> 2;
                if (i9 == 0) {
                    z = false;
                }
                int i10 = new int[]{0, 4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252}[i9];
                if (z) {
                    if (z5) {
                        codec3 = codec;
                    } else {
                        codec3 = getCodec(inputStream.read(), inputStream, codec);
                    }
                    if (!z6) {
                        codec = getCodec(inputStream.read(), inputStream, codec);
                    }
                    return new PopulationCodec(codec3, i10, codec);
                }
                if (z5) {
                    codec2 = codec;
                } else {
                    codec2 = getCodec(inputStream.read(), inputStream, codec);
                }
                Codec codec5 = getCodec(inputStream.read(), inputStream, codec);
                if (!z6) {
                    codec = getCodec(inputStream.read(), inputStream, codec);
                }
                return new PopulationCodec(codec2, codec5, codec);
            }
        }
    }

    public static int getSpecifierForDefaultCodec(BHSDCodec bHSDCodec) {
        return getSpecifier(bHSDCodec, (Codec) null)[0];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0132, code lost:
        if (r9 != -1) goto L_0x0136;
     */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0168 A[LOOP:3: B:85:0x0165->B:87:0x0168, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0175 A[LOOP:4: B:89:0x0172->B:91:0x0175, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0181 A[LOOP:5: B:92:0x017e->B:94:0x0181, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int[] getSpecifier(org.apache.commons.compress.harmony.pack200.Codec r9, org.apache.commons.compress.harmony.pack200.Codec r10) {
        /*
            java.util.Map r0 = canonicalCodecsToSpecifiers
            r1 = 0
            if (r0 != 0) goto L_0x0021
            java.util.HashMap r0 = new java.util.HashMap
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r2 = canonicalCodec
            int r2 = r2.length
            r0.<init>(r2)
            r2 = r1
        L_0x000e:
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r3 = canonicalCodec
            int r4 = r3.length
            if (r2 >= r4) goto L_0x001f
            r3 = r3[r2]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            r0.put(r3, r4)
            int r2 = r2 + 1
            goto L_0x000e
        L_0x001f:
            canonicalCodecsToSpecifiers = r0
        L_0x0021:
            java.util.Map r0 = canonicalCodecsToSpecifiers
            boolean r0 = r0.containsKey(r9)
            r2 = 1
            if (r0 == 0) goto L_0x003b
            int[] r10 = new int[r2]
            java.util.Map r0 = canonicalCodecsToSpecifiers
            java.lang.Object r9 = r0.get(r9)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            r10[r1] = r9
            return r10
        L_0x003b:
            boolean r0 = r9 instanceof org.apache.commons.compress.harmony.pack200.BHSDCodec
            r3 = 3
            r4 = 2
            if (r0 == 0) goto L_0x0065
            org.apache.commons.compress.harmony.pack200.BHSDCodec r9 = (org.apache.commons.compress.harmony.pack200.BHSDCodec) r9
            int[] r10 = new int[r3]
            r0 = 116(0x74, float:1.63E-43)
            r10[r1] = r0
            boolean r0 = r9.isDelta()
            int r1 = r9.getS()
            int r1 = r1 * r4
            int r0 = r0 + r1
            int r1 = r9.getB()
            int r1 = r1 - r2
            int r1 = r1 * 8
            int r0 = r0 + r1
            r10[r2] = r0
            int r9 = r9.getH()
            int r9 = r9 - r2
            r10[r4] = r9
            return r10
        L_0x0065:
            boolean r0 = r9 instanceof org.apache.commons.compress.harmony.pack200.RunCodec
            r5 = 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x00ed
            org.apache.commons.compress.harmony.pack200.RunCodec r9 = (org.apache.commons.compress.harmony.pack200.RunCodec) r9
            int r0 = r9.getK()
            if (r0 > r5) goto L_0x0076
            int r0 = r0 - r2
            r5 = r1
            goto L_0x008a
        L_0x0076:
            r6 = 4096(0x1000, float:5.74E-42)
            if (r0 > r6) goto L_0x007f
            int r0 = r0 / 16
            int r0 = r0 - r2
            r5 = r2
            goto L_0x008a
        L_0x007f:
            r7 = 65536(0x10000, float:9.18355E-41)
            if (r0 > r7) goto L_0x0087
            int r0 = r0 / r5
            int r0 = r0 - r2
            r5 = r4
            goto L_0x008a
        L_0x0087:
            int r0 = r0 / r6
            int r0 = r0 - r2
            r5 = r3
        L_0x008a:
            org.apache.commons.compress.harmony.pack200.Codec r6 = r9.getACodec()
            org.apache.commons.compress.harmony.pack200.Codec r9 = r9.getBCodec()
            boolean r7 = r6.equals(r10)
            if (r7 == 0) goto L_0x009a
            r7 = r2
            goto L_0x00a3
        L_0x009a:
            boolean r7 = r9.equals(r10)
            if (r7 == 0) goto L_0x00a2
            r7 = r4
            goto L_0x00a3
        L_0x00a2:
            r7 = r1
        L_0x00a3:
            int r5 = r5 + 117
            if (r0 != r3) goto L_0x00a9
            r8 = r1
            goto L_0x00aa
        L_0x00a9:
            r8 = 4
        L_0x00aa:
            int r5 = r5 + r8
            int r8 = r7 * 8
            int r5 = r5 + r8
            if (r7 != r2) goto L_0x00b3
            int[] r6 = new int[r1]
            goto L_0x00b7
        L_0x00b3:
            int[] r6 = getSpecifier(r6, r10)
        L_0x00b7:
            if (r7 != r4) goto L_0x00bc
            int[] r9 = new int[r1]
            goto L_0x00c0
        L_0x00bc:
            int[] r9 = getSpecifier(r9, r10)
        L_0x00c0:
            if (r0 != r3) goto L_0x00c4
            r10 = r1
            goto L_0x00c5
        L_0x00c4:
            r10 = r2
        L_0x00c5:
            int r10 = r10 + r2
            int r7 = r6.length
            int r10 = r10 + r7
            int r7 = r9.length
            int r10 = r10 + r7
            int[] r10 = new int[r10]
            r10[r1] = r5
            if (r0 == r3) goto L_0x00d3
            r10[r2] = r0
            goto L_0x00d4
        L_0x00d3:
            r4 = r2
        L_0x00d4:
            r0 = r1
        L_0x00d5:
            int r3 = r6.length
            if (r0 >= r3) goto L_0x00e1
            r3 = r6[r0]
            r10[r4] = r3
            int r4 = r4 + 1
            int r0 = r0 + 1
            goto L_0x00d5
        L_0x00e1:
            int r0 = r9.length
            if (r1 >= r0) goto L_0x00ec
            r0 = r9[r1]
            r10[r4] = r0
            int r4 = r4 + r2
            int r1 = r1 + 1
            goto L_0x00e1
        L_0x00ec:
            return r10
        L_0x00ed:
            boolean r0 = r9 instanceof org.apache.commons.compress.harmony.pack200.PopulationCodec
            if (r0 == 0) goto L_0x018a
            org.apache.commons.compress.harmony.pack200.PopulationCodec r9 = (org.apache.commons.compress.harmony.pack200.PopulationCodec) r9
            org.apache.commons.compress.harmony.pack200.Codec r0 = r9.getTokenCodec()
            org.apache.commons.compress.harmony.pack200.Codec r3 = r9.getFavouredCodec()
            org.apache.commons.compress.harmony.pack200.Codec r4 = r9.getUnfavouredCodec()
            boolean r6 = r3.equals(r10)
            boolean r7 = r4.equals(r10)
            int[] r9 = r9.getFavoured()
            if (r9 == 0) goto L_0x0135
            int r9 = r9.length
            org.apache.commons.compress.harmony.pack200.BHSDCodec r9 = org.apache.commons.compress.harmony.pack200.Codec.BYTE1
            if (r0 != r9) goto L_0x0114
            r9 = r2
            goto L_0x0136
        L_0x0114:
            boolean r9 = r0 instanceof org.apache.commons.compress.harmony.pack200.BHSDCodec
            if (r9 == 0) goto L_0x0135
            r9 = r0
            org.apache.commons.compress.harmony.pack200.BHSDCodec r9 = (org.apache.commons.compress.harmony.pack200.BHSDCodec) r9
            int r8 = r9.getS()
            if (r8 != 0) goto L_0x0135
            r8 = 11
            int[] r8 = new int[r8]
            r8 = {4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252} // fill-array
            int r9 = r9.getH()
            int r5 = r5 - r9
            int r9 = java.util.Arrays.binarySearch(r8, r5)
            r5 = -1
            if (r9 == r5) goto L_0x0135
            goto L_0x0136
        L_0x0135:
            r9 = r1
        L_0x0136:
            int r5 = r6 + 141
            int r8 = r7 * 2
            int r5 = r5 + r8
            int r8 = r9 * 4
            int r5 = r5 + r8
            if (r6 != r2) goto L_0x0143
            int[] r3 = new int[r1]
            goto L_0x0147
        L_0x0143:
            int[] r3 = getSpecifier(r3, r10)
        L_0x0147:
            if (r9 == 0) goto L_0x014c
            int[] r9 = new int[r1]
            goto L_0x0150
        L_0x014c:
            int[] r9 = getSpecifier(r0, r10)
        L_0x0150:
            if (r7 != r2) goto L_0x0155
            int[] r10 = new int[r1]
            goto L_0x0159
        L_0x0155:
            int[] r10 = getSpecifier(r4, r10)
        L_0x0159:
            int r0 = r3.length
            int r0 = r0 + r2
            int r4 = r10.length
            int r0 = r0 + r4
            int r4 = r9.length
            int r0 = r0 + r4
            int[] r0 = new int[r0]
            r0[r1] = r5
            r4 = r1
            r5 = r2
        L_0x0165:
            int r6 = r3.length
            if (r4 >= r6) goto L_0x0171
            r6 = r3[r4]
            r0[r5] = r6
            int r5 = r5 + 1
            int r4 = r4 + 1
            goto L_0x0165
        L_0x0171:
            r3 = r1
        L_0x0172:
            int r4 = r9.length
            if (r3 >= r4) goto L_0x017e
            r4 = r9[r3]
            r0[r5] = r4
            int r5 = r5 + 1
            int r3 = r3 + 1
            goto L_0x0172
        L_0x017e:
            int r9 = r10.length
            if (r1 >= r9) goto L_0x0189
            r9 = r10[r1]
            r0[r5] = r9
            int r5 = r5 + r2
            int r1 = r1 + 1
            goto L_0x017e
        L_0x0189:
            return r0
        L_0x018a:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.CodecEncoding.getSpecifier(org.apache.commons.compress.harmony.pack200.Codec, org.apache.commons.compress.harmony.pack200.Codec):int[]");
    }

    public static BHSDCodec getCanonicalCodec(int i) {
        return canonicalCodec[i];
    }
}
