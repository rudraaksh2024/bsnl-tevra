package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BandSet {
    private static final int[] effortThresholds = {0, 0, 1000, 500, 100, 100, 100, 100, 100, 0};
    private long[] canonicalLargest;
    private long[] canonicalSmallest;
    final int effort;
    protected final SegmentHeader segmentHeader;

    public abstract void pack(OutputStream outputStream) throws IOException, Pack200Exception;

    public BandSet(int i, SegmentHeader segmentHeader2) {
        this.effort = i;
        this.segmentHeader = segmentHeader2;
    }

    public byte[] encodeScalar(int[] iArr, BHSDCodec bHSDCodec) throws Pack200Exception {
        return bHSDCodec.encode(iArr);
    }

    public byte[] encodeScalar(int i, BHSDCodec bHSDCodec) throws Pack200Exception {
        return bHSDCodec.encode(i);
    }

    public byte[] encodeBandInt(String str, int[] iArr, BHSDCodec bHSDCodec) throws Pack200Exception {
        byte[] bArr;
        int i;
        int i2 = this.effort;
        if (i2 <= 1 || iArr.length < effortThresholds[i2]) {
            bArr = null;
        } else {
            BandAnalysisResults analyseBand = analyseBand(str, iArr, bHSDCodec);
            Codec access$000 = analyseBand.betterCodec;
            bArr = analyseBand.encodedBand;
            if (access$000 != null) {
                if (access$000 instanceof BHSDCodec) {
                    int[] specifier = CodecEncoding.getSpecifier(access$000, bHSDCodec);
                    int i3 = specifier[0];
                    if (specifier.length > 1) {
                        for (int i4 = 1; i4 < specifier.length; i4++) {
                            this.segmentHeader.appendBandCodingSpecifier(specifier[i4]);
                        }
                    }
                    if (bHSDCodec.isSigned()) {
                        i = -1 - i3;
                    } else {
                        i = i3 + bHSDCodec.getL();
                    }
                    byte[] encode = bHSDCodec.encode(new int[]{i});
                    byte[] bArr2 = new byte[(encode.length + bArr.length)];
                    System.arraycopy(encode, 0, bArr2, 0, encode.length);
                    System.arraycopy(bArr, 0, bArr2, encode.length, bArr.length);
                    return bArr2;
                } else if (access$000 instanceof PopulationCodec) {
                    int[] access$200 = analyseBand.extraMetadata;
                    for (int appendBandCodingSpecifier : access$200) {
                        this.segmentHeader.appendBandCodingSpecifier(appendBandCodingSpecifier);
                    }
                    return bArr;
                } else {
                    boolean z = access$000 instanceof RunCodec;
                }
            }
        }
        if (iArr.length <= 0) {
            return new byte[0];
        }
        if (bArr == null) {
            bArr = bHSDCodec.encode(iArr);
        }
        int i5 = iArr[0];
        if (bHSDCodec.getB() != 1) {
            if (bHSDCodec.isSigned() && i5 >= -256 && i5 <= -1) {
                byte[] encode2 = bHSDCodec.encode(new int[]{-1 - CodecEncoding.getSpecifierForDefaultCodec(bHSDCodec)});
                byte[] bArr3 = new byte[(encode2.length + bArr.length)];
                System.arraycopy(encode2, 0, bArr3, 0, encode2.length);
                System.arraycopy(bArr, 0, bArr3, encode2.length, bArr.length);
                return bArr3;
            } else if (!bHSDCodec.isSigned() && i5 >= bHSDCodec.getL() && i5 <= bHSDCodec.getL() + 255) {
                byte[] encode3 = bHSDCodec.encode(new int[]{CodecEncoding.getSpecifierForDefaultCodec(bHSDCodec) + bHSDCodec.getL()});
                byte[] bArr4 = new byte[(encode3.length + bArr.length)];
                System.arraycopy(encode3, 0, bArr4, 0, encode3.length);
                System.arraycopy(bArr, 0, bArr4, encode3.length, bArr.length);
                return bArr4;
            }
        }
        return bArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009d, code lost:
        if (r0 >= 0.04d) goto L_0x00af;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.compress.harmony.pack200.BandSet.BandAnalysisResults analyseBand(java.lang.String r13, int[] r14, org.apache.commons.compress.harmony.pack200.BHSDCodec r15) throws org.apache.commons.compress.harmony.pack200.Pack200Exception {
        /*
            r12 = this;
            org.apache.commons.compress.harmony.pack200.BandSet$BandAnalysisResults r8 = new org.apache.commons.compress.harmony.pack200.BandSet$BandAnalysisResults
            r8.<init>()
            long[] r0 = r12.canonicalLargest
            if (r0 != 0) goto L_0x0032
            r0 = 116(0x74, float:1.63E-43)
            long[] r1 = new long[r0]
            r12.canonicalLargest = r1
            long[] r0 = new long[r0]
            r12.canonicalSmallest = r0
            r0 = 1
        L_0x0014:
            long[] r1 = r12.canonicalLargest
            int r2 = r1.length
            if (r0 >= r2) goto L_0x0032
            org.apache.commons.compress.harmony.pack200.BHSDCodec r2 = org.apache.commons.compress.harmony.pack200.CodecEncoding.getCanonicalCodec(r0)
            long r2 = r2.largest()
            r1[r0] = r2
            long[] r1 = r12.canonicalSmallest
            org.apache.commons.compress.harmony.pack200.BHSDCodec r2 = org.apache.commons.compress.harmony.pack200.CodecEncoding.getCanonicalCodec(r0)
            long r2 = r2.smallest()
            r1[r0] = r2
            int r0 = r0 + 1
            goto L_0x0014
        L_0x0032:
            org.apache.commons.compress.harmony.pack200.BandSet$BandData r9 = new org.apache.commons.compress.harmony.pack200.BandSet$BandData
            r9.<init>(r14)
            byte[] r10 = r15.encode((int[]) r14)
            byte[] unused = r8.encodedBand = r10
            int r0 = r10.length
            int r1 = r14.length
            int r1 = r1 + 23
            int r2 = r12.effort
            int r2 = r2 * 2
            int r1 = r1 - r2
            if (r0 > r1) goto L_0x004a
            return r8
        L_0x004a:
            boolean r0 = r9.anyNegatives()
            if (r0 != 0) goto L_0x006e
            int r0 = r9.largest
            long r0 = (long) r0
            org.apache.commons.compress.harmony.pack200.BHSDCodec r2 = org.apache.commons.compress.harmony.pack200.Codec.BYTE1
            long r2 = r2.largest()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x006e
            org.apache.commons.compress.harmony.pack200.BHSDCodec r12 = org.apache.commons.compress.harmony.pack200.Codec.BYTE1
            byte[] r12 = r12.encode((int[]) r14)
            byte[] unused = r8.encodedBand = r12
            org.apache.commons.compress.harmony.pack200.BHSDCodec r12 = org.apache.commons.compress.harmony.pack200.Codec.BYTE1
            org.apache.commons.compress.harmony.pack200.Codec unused = r8.betterCodec = r12
            return r8
        L_0x006e:
            int r0 = r12.effort
            r1 = 3
            if (r0 <= r1) goto L_0x00af
            java.lang.String r0 = "POPULATION"
            boolean r0 = r13.equals(r0)
            if (r0 != 0) goto L_0x00af
            int r0 = r9.numDistinctValues()
            float r1 = (float) r0
            int r2 = r14.length
            float r2 = (float) r2
            float r1 = r1 / r2
            r2 = 100
            if (r0 < r2) goto L_0x009f
            double r0 = (double) r1
            r2 = 4581421828931458171(0x3f947ae147ae147b, double:0.02)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x009f
            int r2 = r12.effort
            r3 = 6
            if (r2 <= r3) goto L_0x00af
            r2 = 4585925428558828667(0x3fa47ae147ae147b, double:0.04)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x00af
        L_0x009f:
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r9
            r5 = r8
            r0.encodeWithPopulationCodec(r1, r2, r3, r4, r5)
            boolean r0 = r12.timeToStop(r8)
            if (r0 == 0) goto L_0x00af
            return r8
        L_0x00af:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            boolean r1 = r9.mainlyPositiveDeltas()
            if (r1 == 0) goto L_0x00c5
            boolean r1 = r9.mainlySmallDeltas()
            if (r1 == 0) goto L_0x00c5
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaUnsignedCodecs2
            r0.add(r1)
        L_0x00c5:
            boolean r1 = r9.wellCorrelated()
            if (r1 == 0) goto L_0x0124
            boolean r1 = r9.mainlyPositiveDeltas()
            if (r1 == 0) goto L_0x0100
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaUnsignedCodecs1
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaUnsignedCodecs3
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaUnsignedCodecs4
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaUnsignedCodecs5
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaUnsignedCodecs1
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaUnsignedCodecs3
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaUnsignedCodecs4
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaUnsignedCodecs5
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaUnsignedCodecs2
            r0.add(r1)
            goto L_0x017b
        L_0x0100:
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaSignedCodecs1
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaSignedCodecs3
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaSignedCodecs2
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaSignedCodecs4
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaSignedCodecs5
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaSignedCodecs1
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaSignedCodecs2
            r0.add(r1)
            goto L_0x017b
        L_0x0124:
            boolean r1 = r9.anyNegatives()
            if (r1 == 0) goto L_0x014e
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaSignedCodecs1
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaSignedCodecs2
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaSignedCodecs1
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaSignedCodecs2
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaSignedCodecs3
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaSignedCodecs4
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaSignedCodecs5
            r0.add(r1)
            goto L_0x017b
        L_0x014e:
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaUnsignedCodecs1
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaUnsignedCodecs3
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaUnsignedCodecs4
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaUnsignedCodecs5
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.nonDeltaUnsignedCodecs2
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaUnsignedCodecs1
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaUnsignedCodecs3
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaUnsignedCodecs4
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r1 = org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies.deltaUnsignedCodecs5
            r0.add(r1)
        L_0x017b:
            java.lang.String r1 = "cpint"
            boolean r1 = r13.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x018a
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.String r2 = ""
            r1.print(r2)
        L_0x018a:
            java.util.Iterator r11 = r0.iterator()
        L_0x018e:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x01ad
            java.lang.Object r0 = r11.next()
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r0 = (org.apache.commons.compress.harmony.pack200.BHSDCodec[]) r0
            r7 = r0
            org.apache.commons.compress.harmony.pack200.BHSDCodec[] r7 = (org.apache.commons.compress.harmony.pack200.BHSDCodec[]) r7
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r9
            r5 = r8
            r6 = r10
            r0.tryCodecs(r1, r2, r3, r4, r5, r6, r7)
            boolean r0 = r12.timeToStop(r8)
            if (r0 == 0) goto L_0x018e
        L_0x01ad:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BandSet.analyseBand(java.lang.String, int[], org.apache.commons.compress.harmony.pack200.BHSDCodec):org.apache.commons.compress.harmony.pack200.BandSet$BandAnalysisResults");
    }

    private boolean timeToStop(BandAnalysisResults bandAnalysisResults) {
        if (this.effort > 6) {
            if (bandAnalysisResults.numCodecsTried >= this.effort * 2) {
                return true;
            }
            return false;
        } else if (bandAnalysisResults.numCodecsTried >= this.effort) {
            return true;
        } else {
            return false;
        }
    }

    private void tryCodecs(String str, int[] iArr, BHSDCodec bHSDCodec, BandData bandData, BandAnalysisResults bandAnalysisResults, byte[] bArr, BHSDCodec[] bHSDCodecArr) throws Pack200Exception {
        int i = 0;
        while (i < bHSDCodecArr.length) {
            BHSDCodec bHSDCodec2 = bHSDCodecArr[i];
            if (!bHSDCodec2.equals(bHSDCodec)) {
                if (bHSDCodec2.isDelta()) {
                    if (bHSDCodec2.largest() >= ((long) bandData.largestDelta) && bHSDCodec2.smallest() <= ((long) bandData.smallestDelta) && bHSDCodec2.largest() >= ((long) bandData.largest) && bHSDCodec2.smallest() <= ((long) bandData.smallest)) {
                        byte[] encode = bHSDCodec2.encode(iArr);
                        BandAnalysisResults.access$408(bandAnalysisResults);
                        int length = (bArr.length - encode.length) - bHSDCodec.encode(CodecEncoding.getSpecifier(bHSDCodec2, (Codec) null)).length;
                        if (length > bandAnalysisResults.saved) {
                            Codec unused = bandAnalysisResults.betterCodec = bHSDCodec2;
                            byte[] unused2 = bandAnalysisResults.encodedBand = encode;
                            int unused3 = bandAnalysisResults.saved = length;
                        }
                    }
                } else if (bHSDCodec2.largest() >= ((long) bandData.largest) && bHSDCodec2.smallest() <= ((long) bandData.smallest)) {
                    byte[] encode2 = bHSDCodec2.encode(iArr);
                    BandAnalysisResults.access$408(bandAnalysisResults);
                    int length2 = (bArr.length - encode2.length) - bHSDCodec.encode(CodecEncoding.getSpecifier(bHSDCodec2, (Codec) null)).length;
                    if (length2 > bandAnalysisResults.saved) {
                        Codec unused4 = bandAnalysisResults.betterCodec = bHSDCodec2;
                        byte[] unused5 = bandAnalysisResults.encodedBand = encode2;
                        int unused6 = bandAnalysisResults.saved = length2;
                    }
                }
                if (!timeToStop(bandAnalysisResults)) {
                    i++;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private void encodeWithPopulationCodec(String str, int[] iArr, BHSDCodec bHSDCodec, BandData bandData, BandAnalysisResults bandAnalysisResults) throws Pack200Exception {
        int i;
        Codec codec;
        int i2;
        byte[] bArr;
        int i3;
        int[] iArr2 = iArr;
        BHSDCodec bHSDCodec2 = bHSDCodec;
        BandAnalysisResults bandAnalysisResults2 = bandAnalysisResults;
        int unused = bandAnalysisResults2.numCodecsTried = bandAnalysisResults.numCodecsTried + 3;
        Map access$900 = bandData.distinctValues;
        ArrayList arrayList = new ArrayList();
        for (Integer num : access$900.keySet()) {
            if (((Integer) access$900.get(num)).intValue() > 2 || access$900.size() < 256) {
                arrayList.add(num);
            }
        }
        if (access$900.size() > 255) {
            Collections.sort(arrayList, new BandSet$$ExternalSyntheticLambda0(access$900));
        }
        IntList intList = new IntList();
        HashMap hashMap = new HashMap();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            hashMap.put((Integer) arrayList.get(i4), Integer.valueOf(i4));
        }
        int[] iArr3 = new int[iArr2.length];
        for (int i5 = 0; i5 < iArr2.length; i5++) {
            Integer num2 = (Integer) hashMap.get(Integer.valueOf(iArr2[i5]));
            if (num2 == null) {
                iArr3[i5] = 0;
                intList.add(iArr2[i5]);
            } else {
                iArr3[i5] = num2.intValue() + 1;
            }
        }
        arrayList.add(arrayList.get(arrayList.size() - 1));
        int[] integerListToArray = integerListToArray(arrayList);
        int[] array = intList.toArray();
        BandAnalysisResults analyseBand = analyseBand("POPULATION", integerListToArray, bHSDCodec2);
        BandAnalysisResults analyseBand2 = analyseBand("POPULATION", array, bHSDCodec2);
        int size = arrayList.size() - 1;
        if (size < 256) {
            bArr = Codec.BYTE1.encode(iArr3);
            i = 0;
            codec = null;
            i2 = 1;
        } else {
            BandAnalysisResults analyseBand3 = analyseBand("POPULATION", iArr3, bHSDCodec2);
            codec = analyseBand3.betterCodec;
            bArr = analyseBand3.encodedBand;
            if (codec == null) {
                codec = bHSDCodec2;
            }
            BHSDCodec bHSDCodec3 = (BHSDCodec) codec;
            i = bHSDCodec3.getL();
            int h = bHSDCodec3.getH();
            int s = bHSDCodec3.getS();
            int b = bHSDCodec3.getB();
            boolean isDelta = bHSDCodec3.isDelta();
            if (s == 0 && !isDelta) {
                if (b <= 1 || new BHSDCodec(b - 1, h).largest() < ((long) size)) {
                    switch (i) {
                        case 4:
                            i2 = 1;
                            break;
                        case 8:
                            i2 = 2;
                            break;
                        case 16:
                            i2 = 3;
                            break;
                        case 32:
                            i2 = 4;
                            break;
                        case 64:
                            i2 = 5;
                            break;
                        case 128:
                            i2 = 6;
                            break;
                        case 192:
                            i2 = 7;
                            break;
                        case 224:
                            i2 = 8;
                            break;
                        case 240:
                            i2 = 9;
                            break;
                        case 248:
                            i2 = 10;
                            break;
                        case 252:
                            i2 = 11;
                            break;
                    }
                }
            }
            i2 = 0;
        }
        byte[] access$100 = analyseBand.encodedBand;
        byte[] access$1002 = analyseBand2.encodedBand;
        Codec access$000 = analyseBand.betterCodec;
        Codec access$0002 = analyseBand2.betterCodec;
        int i6 = (access$000 == null ? 1 : 0) + 141 + (i2 * 4) + (access$0002 == null ? 2 : 0);
        IntList intList2 = new IntList(3);
        if (access$000 != null) {
            int[] specifier = CodecEncoding.getSpecifier(access$000, (Codec) null);
            for (int add : specifier) {
                intList2.add(add);
            }
        }
        if (i2 == 0) {
            int[] specifier2 = CodecEncoding.getSpecifier(codec, (Codec) null);
            for (int add2 : specifier2) {
                intList2.add(add2);
            }
        }
        if (access$0002 != null) {
            int[] specifier3 = CodecEncoding.getSpecifier(access$0002, (Codec) null);
            for (int add3 : specifier3) {
                intList2.add(add3);
            }
        }
        int[] array2 = intList2.toArray();
        byte[] encode = Codec.UNSIGNED5.encode(array2);
        if (bHSDCodec.isSigned()) {
            i3 = -1 - i6;
        } else {
            i3 = bHSDCodec.getL() + i6;
        }
        byte[] encode2 = bHSDCodec2.encode(new int[]{i3});
        int length = encode2.length + access$100.length + bArr.length + access$1002.length;
        if (encode.length + length < bandAnalysisResults.encodedBand.length) {
            int unused2 = bandAnalysisResults2.saved = bandAnalysisResults.saved + (bandAnalysisResults.encodedBand.length - (encode.length + length));
            byte[] bArr2 = new byte[length];
            System.arraycopy(encode2, 0, bArr2, 0, encode2.length);
            System.arraycopy(access$100, 0, bArr2, encode2.length, access$100.length);
            System.arraycopy(bArr, 0, bArr2, encode2.length + access$100.length, bArr.length);
            System.arraycopy(access$1002, 0, bArr2, encode2.length + access$100.length + bArr.length, access$1002.length);
            byte[] unused3 = bandAnalysisResults2.encodedBand = bArr2;
            int[] unused4 = bandAnalysisResults2.extraMetadata = array2;
            if (i != 0) {
                Codec unused5 = bandAnalysisResults2.betterCodec = new PopulationCodec(access$000, i, access$0002);
            } else {
                Codec unused6 = bandAnalysisResults2.betterCodec = new PopulationCodec(access$000, codec, access$0002);
            }
        }
    }

    /* access modifiers changed from: protected */
    public byte[] encodeFlags(String str, long[] jArr, BHSDCodec bHSDCodec, BHSDCodec bHSDCodec2, boolean z) throws Pack200Exception {
        if (!z) {
            int[] iArr = new int[jArr.length];
            for (int i = 0; i < jArr.length; i++) {
                iArr[i] = (int) jArr[i];
            }
            return encodeBandInt(str, iArr, bHSDCodec);
        }
        int[] iArr2 = new int[jArr.length];
        int[] iArr3 = new int[jArr.length];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            long j = jArr[i2];
            iArr2[i2] = (int) (j >> 32);
            iArr3[i2] = (int) j;
        }
        byte[] encodeBandInt = encodeBandInt(str, iArr2, bHSDCodec2);
        byte[] encodeBandInt2 = encodeBandInt(str, iArr3, bHSDCodec);
        byte[] bArr = new byte[(encodeBandInt.length + encodeBandInt2.length)];
        System.arraycopy(encodeBandInt, 0, bArr, 0, encodeBandInt.length);
        System.arraycopy(encodeBandInt2, 0, bArr, encodeBandInt.length + 1, encodeBandInt2.length);
        return bArr;
    }

    /* access modifiers changed from: protected */
    public int[] integerListToArray(List list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((Integer) list.get(i)).intValue();
        }
        return iArr;
    }

    /* access modifiers changed from: protected */
    public long[] longListToArray(List list) {
        int size = list.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = ((Long) list.get(i)).longValue();
        }
        return jArr;
    }

    /* access modifiers changed from: protected */
    public int[] cpEntryListToArray(List list) {
        int size = list.size();
        int[] iArr = new int[size];
        int i = 0;
        while (i < size) {
            int index = ((ConstantPoolEntry) list.get(i)).getIndex();
            iArr[i] = index;
            if (index >= 0) {
                i++;
            } else {
                throw new RuntimeException("Index should be > 0");
            }
        }
        return iArr;
    }

    /* access modifiers changed from: protected */
    public int[] cpEntryOrNullListToArray(List list) {
        int i;
        int size = list.size();
        int[] iArr = new int[size];
        int i2 = 0;
        while (i2 < size) {
            ConstantPoolEntry constantPoolEntry = (ConstantPoolEntry) list.get(i2);
            if (constantPoolEntry == null) {
                i = 0;
            } else {
                i = constantPoolEntry.getIndex() + 1;
            }
            iArr[i2] = i;
            if (constantPoolEntry == null || constantPoolEntry.getIndex() >= 0) {
                i2++;
            } else {
                throw new RuntimeException("Index should be > 0");
            }
        }
        return iArr;
    }

    /* access modifiers changed from: protected */
    public byte[] encodeFlags(String str, long[][] jArr, BHSDCodec bHSDCodec, BHSDCodec bHSDCodec2, boolean z) throws Pack200Exception {
        return encodeFlags(str, flatten(jArr), bHSDCodec, bHSDCodec2, z);
    }

    private long[] flatten(long[][] jArr) {
        int i = 0;
        for (long[] length : jArr) {
            i += length.length;
        }
        long[] jArr2 = new long[i];
        int i2 = 0;
        for (long[] jArr3 : jArr) {
            int i3 = 0;
            while (true) {
                if (i3 >= jArr3.length) {
                    break;
                }
                jArr2[i2] = jArr3[i3];
                i2++;
                i3++;
            }
        }
        return jArr2;
    }

    public class BandData {
        private double averageAbsoluteDelta = 0.0d;
        private double averageAbsoluteValue = 0.0d;
        private final int[] band;
        private int deltaIsAscending = 0;
        /* access modifiers changed from: private */
        public Map distinctValues;
        /* access modifiers changed from: private */
        public int largest = Integer.MIN_VALUE;
        /* access modifiers changed from: private */
        public int largestDelta;
        private int smallDeltaCount = 0;
        /* access modifiers changed from: private */
        public int smallest = Integer.MAX_VALUE;
        /* access modifiers changed from: private */
        public int smallestDelta;

        public BandData(int[] iArr) {
            Integer num;
            this.band = iArr;
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                if (i2 < this.smallest) {
                    this.smallest = i2;
                }
                if (i2 > this.largest) {
                    this.largest = i2;
                }
                if (i != 0) {
                    int i3 = i2 - iArr[i - 1];
                    if (i3 < this.smallestDelta) {
                        this.smallestDelta = i3;
                    }
                    if (i3 > this.largestDelta) {
                        this.largestDelta = i3;
                    }
                    if (i3 >= 0) {
                        this.deltaIsAscending++;
                    }
                    this.averageAbsoluteDelta += ((double) Math.abs(i3)) / ((double) (iArr.length - 1));
                    if (Math.abs(i3) < 256) {
                        this.smallDeltaCount++;
                    }
                } else {
                    int i4 = iArr[0];
                    this.smallestDelta = i4;
                    this.largestDelta = i4;
                }
                this.averageAbsoluteValue += ((double) Math.abs(iArr[i])) / ((double) iArr.length);
                if (BandSet.this.effort > 3) {
                    if (this.distinctValues == null) {
                        this.distinctValues = new HashMap();
                    }
                    Integer valueOf = Integer.valueOf(iArr[i]);
                    Integer num2 = (Integer) this.distinctValues.get(valueOf);
                    if (num2 == null) {
                        num = 1;
                    } else {
                        num = Integer.valueOf(num2.intValue() + 1);
                    }
                    this.distinctValues.put(valueOf, num);
                }
            }
        }

        public boolean mainlySmallDeltas() {
            return ((float) this.smallDeltaCount) / ((float) this.band.length) > 0.7f;
        }

        public boolean wellCorrelated() {
            return this.averageAbsoluteDelta * 3.1d < this.averageAbsoluteValue;
        }

        public boolean mainlyPositiveDeltas() {
            return ((float) this.deltaIsAscending) / ((float) this.band.length) > 0.95f;
        }

        public boolean anyNegatives() {
            return this.smallest < 0;
        }

        public int numDistinctValues() {
            Map map = this.distinctValues;
            if (map == null) {
                return this.band.length;
            }
            return map.size();
        }
    }

    public class BandAnalysisResults {
        /* access modifiers changed from: private */
        public Codec betterCodec;
        /* access modifiers changed from: private */
        public byte[] encodedBand;
        /* access modifiers changed from: private */
        public int[] extraMetadata;
        /* access modifiers changed from: private */
        public int numCodecsTried = 0;
        /* access modifiers changed from: private */
        public int saved = 0;

        public BandAnalysisResults() {
        }

        static /* synthetic */ int access$408(BandAnalysisResults bandAnalysisResults) {
            int i = bandAnalysisResults.numCodecsTried;
            bandAnalysisResults.numCodecsTried = i + 1;
            return i;
        }
    }
}
