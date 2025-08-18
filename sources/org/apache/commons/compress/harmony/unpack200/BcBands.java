package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionTableEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.NewAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class BcBands extends BandSet {
    private int[] bcByte;
    private int[] bcCaseCount;
    private int[] bcCaseValue;
    private int[] bcClassRef;
    private int[] bcDoubleRef;
    private int[][] bcEscByte;
    private int[] bcEscRef;
    private int[] bcEscRefSize;
    private int[] bcEscSize;
    private int[] bcFieldRef;
    private int[] bcFloatRef;
    private int[] bcIMethodRef;
    private int[] bcInitRef;
    private int[] bcIntRef;
    private int[] bcLabel;
    private int[] bcLocal;
    private int[] bcLongRef;
    private int[] bcMethodRef;
    private int[] bcShort;
    private int[] bcStringRef;
    private int[] bcSuperField;
    private int[] bcSuperMethod;
    private int[] bcThisField;
    private int[] bcThisMethod;
    private byte[][][] methodByteCodePacked;
    private List wideByteCodes;

    private boolean endsWithLoad(int i) {
        return i >= 21 && i <= 25;
    }

    private boolean endsWithStore(int i) {
        return i >= 54 && i <= 58;
    }

    private boolean startsWithIf(int i) {
        return (i >= 153 && i <= 166) || i == 198 || i == 199;
    }

    public BcBands(Segment segment) {
        super(segment);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0117, code lost:
        if (startsWithIf(r4) != false) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0150, code lost:
        r10 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0154, code lost:
        r13 = r13 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0157, code lost:
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0166, code lost:
        r11 = r11 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void read(java.io.InputStream r38) throws java.io.IOException, org.apache.commons.compress.harmony.pack200.Pack200Exception {
        /*
            r37 = this;
            r0 = r37
            r1 = r38
            org.apache.commons.compress.harmony.unpack200.Segment r2 = r0.segment
            org.apache.commons.compress.harmony.unpack200.AttrDefinitionBands r2 = r2.getAttrDefinitionBands()
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r2 = r2.getAttributeDefinitionMap()
            org.apache.commons.compress.harmony.unpack200.SegmentHeader r3 = r0.header
            int r3 = r3.getClassCount()
            org.apache.commons.compress.harmony.unpack200.Segment r4 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r4 = r4.getClassBands()
            long[][] r4 = r4.getMethodFlags()
            java.lang.String r5 = "ACC_ABSTRACT"
            r6 = 2
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r5 = r2.getAttributeLayout((java.lang.String) r5, (int) r6)
            java.lang.String r7 = "ACC_NATIVE"
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r2 = r2.getAttributeLayout((java.lang.String) r7, (int) r6)
            byte[][][] r7 = new byte[r3][][]
            r0.methodByteCodePacked = r7
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r0.wideByteCodes = r8
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 0
        L_0x0060:
            if (r9 >= r3) goto L_0x0206
            r6 = r4[r9]
            int r6 = r6.length
            byte[][][] r8 = r0.methodByteCodePacked
            r31 = r3
            byte[][] r3 = new byte[r6][]
            r8[r9] = r3
            r3 = 0
        L_0x006e:
            if (r3 >= r6) goto L_0x01f2
            r8 = r4[r9]
            r32 = r10
            r33 = r11
            r10 = r8[r3]
            boolean r8 = r5.matches(r10)
            if (r8 != 0) goto L_0x01dd
            boolean r8 = r2.matches(r10)
            if (r8 != 0) goto L_0x01dd
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream
            r8.<init>()
        L_0x0089:
            int r10 = r38.read()
            r10 = r10 & 255(0xff, float:3.57E-43)
            byte r10 = (byte) r10
            r11 = -1
            if (r10 == r11) goto L_0x0097
            r8.write(r10)
            goto L_0x0089
        L_0x0097:
            byte[][][] r10 = r0.methodByteCodePacked
            r10 = r10[r9]
            byte[] r8 = r8.toByteArray()
            r10[r3] = r8
            byte[][][] r8 = r0.methodByteCodePacked
            r8 = r8[r9]
            r8 = r8[r3]
            int r10 = r8.length
            int r8 = r8.length
            int[] r10 = new int[r8]
            r11 = 0
        L_0x00ac:
            if (r11 >= r8) goto L_0x00c1
            r34 = r2
            byte[][][] r2 = r0.methodByteCodePacked
            r2 = r2[r9]
            r2 = r2[r3]
            byte r2 = r2[r11]
            r2 = r2 & 255(0xff, float:3.57E-43)
            r10[r11] = r2
            int r11 = r11 + 1
            r2 = r34
            goto L_0x00ac
        L_0x00c1:
            r34 = r2
            r10 = r32
            r11 = r33
            r2 = 0
        L_0x00c8:
            byte[][][] r8 = r0.methodByteCodePacked
            r8 = r8[r9]
            r8 = r8[r3]
            r35 = r4
            int r4 = r8.length
            if (r2 >= r4) goto L_0x01d9
            byte r4 = r8[r2]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r36 = r5
            r5 = 132(0x84, float:1.85E-43)
            r32 = 1
            if (r4 == r5) goto L_0x01cc
            r5 = 192(0xc0, float:2.69E-43)
            if (r4 == r5) goto L_0x0176
            r5 = 193(0xc1, float:2.7E-43)
            if (r4 == r5) goto L_0x0176
            r5 = 196(0xc4, float:2.75E-43)
            if (r4 == r5) goto L_0x0178
            r5 = 197(0xc5, float:2.76E-43)
            if (r4 == r5) goto L_0x0173
            r5 = 253(0xfd, float:3.55E-43)
            if (r4 == r5) goto L_0x016f
            r5 = 254(0xfe, float:3.56E-43)
            if (r4 == r5) goto L_0x016b
            switch(r4) {
                case 16: goto L_0x0134;
                case 17: goto L_0x0162;
                case 18: goto L_0x015e;
                case 19: goto L_0x015e;
                case 20: goto L_0x015a;
                default: goto L_0x00fa;
            }
        L_0x00fa:
            switch(r4) {
                case 167: goto L_0x0132;
                case 168: goto L_0x0132;
                case 169: goto L_0x0153;
                case 170: goto L_0x0148;
                case 171: goto L_0x013f;
                default: goto L_0x00fd;
            }
        L_0x00fd:
            switch(r4) {
                case 178: goto L_0x013c;
                case 179: goto L_0x013c;
                case 180: goto L_0x013c;
                case 181: goto L_0x013c;
                case 182: goto L_0x0139;
                case 183: goto L_0x0139;
                case 184: goto L_0x0139;
                case 185: goto L_0x0136;
                default: goto L_0x0100;
            }
        L_0x0100:
            switch(r4) {
                case 187: goto L_0x0176;
                case 188: goto L_0x0134;
                case 189: goto L_0x0176;
                default: goto L_0x0103;
            }
        L_0x0103:
            switch(r4) {
                case 200: goto L_0x0132;
                case 201: goto L_0x0132;
                case 202: goto L_0x012f;
                case 203: goto L_0x012f;
                case 204: goto L_0x012f;
                case 205: goto L_0x012f;
                case 206: goto L_0x012c;
                case 207: goto L_0x012c;
                case 208: goto L_0x012c;
                case 209: goto L_0x012f;
                case 210: goto L_0x012f;
                case 211: goto L_0x012f;
                case 212: goto L_0x012f;
                case 213: goto L_0x012c;
                case 214: goto L_0x012c;
                case 215: goto L_0x012c;
                case 216: goto L_0x0129;
                case 217: goto L_0x0129;
                case 218: goto L_0x0129;
                case 219: goto L_0x0129;
                case 220: goto L_0x0126;
                case 221: goto L_0x0126;
                case 222: goto L_0x0126;
                case 223: goto L_0x0129;
                case 224: goto L_0x0129;
                case 225: goto L_0x0129;
                case 226: goto L_0x0129;
                case 227: goto L_0x0126;
                case 228: goto L_0x0126;
                case 229: goto L_0x0126;
                case 230: goto L_0x0123;
                case 231: goto L_0x0123;
                case 232: goto L_0x0123;
                case 233: goto L_0x0176;
                case 234: goto L_0x0120;
                case 235: goto L_0x011d;
                case 236: goto L_0x0176;
                case 237: goto L_0x0120;
                case 238: goto L_0x011d;
                case 239: goto L_0x011a;
                default: goto L_0x0106;
            }
        L_0x0106:
            boolean r5 = r0.endsWithLoad(r4)
            if (r5 != 0) goto L_0x0154
            boolean r5 = r0.endsWithStore(r4)
            if (r5 == 0) goto L_0x0113
            goto L_0x0154
        L_0x0113:
            boolean r4 = r0.startsWithIf(r4)
            if (r4 == 0) goto L_0x0168
            goto L_0x0157
        L_0x011a:
            int r18 = r18 + 1
            goto L_0x0168
        L_0x011d:
            int r16 = r16 + 1
            goto L_0x0168
        L_0x0120:
            int r15 = r15 + 1
            goto L_0x0168
        L_0x0123:
            int r28 = r28 + 1
            goto L_0x0168
        L_0x0126:
            int r27 = r27 + 1
            goto L_0x0168
        L_0x0129:
            int r25 = r25 + 1
            goto L_0x0168
        L_0x012c:
            int r26 = r26 + 1
            goto L_0x0168
        L_0x012f:
            int r24 = r24 + 1
            goto L_0x0168
        L_0x0132:
            r4 = 0
            goto L_0x0157
        L_0x0134:
            r4 = 0
            goto L_0x0166
        L_0x0136:
            int r23 = r23 + 1
            goto L_0x0168
        L_0x0139:
            int r22 = r22 + 1
            goto L_0x0168
        L_0x013c:
            int r21 = r21 + 1
            goto L_0x0168
        L_0x013f:
            r4 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
            r7.add(r5)
            goto L_0x0150
        L_0x0148:
            r4 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r32)
            r7.add(r5)
        L_0x0150:
            int r10 = r10 + 1
            goto L_0x0157
        L_0x0153:
            r4 = 0
        L_0x0154:
            int r13 = r13 + 1
            goto L_0x0168
        L_0x0157:
            int r14 = r14 + 1
            goto L_0x0168
        L_0x015a:
            r4 = 0
            int r17 = r17 + 1
            goto L_0x0168
        L_0x015e:
            r4 = 0
            int r19 = r19 + 1
            goto L_0x0168
        L_0x0162:
            r4 = 0
            int r12 = r12 + 1
            goto L_0x0168
        L_0x0166:
            int r11 = r11 + 1
        L_0x0168:
            r5 = 2
            goto L_0x01d1
        L_0x016b:
            r4 = 0
            int r29 = r29 + 1
            goto L_0x0168
        L_0x016f:
            r4 = 0
            int r30 = r30 + 1
            goto L_0x0168
        L_0x0173:
            r4 = 0
            int r11 = r11 + 1
        L_0x0176:
            r5 = 2
            goto L_0x01c9
        L_0x0178:
            r4 = 0
            int r2 = r2 + 1
            byte r5 = r8[r2]
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.util.List r8 = r0.wideByteCodes
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            r8.add(r4)
            r4 = 132(0x84, float:1.85E-43)
            if (r5 != r4) goto L_0x0194
            int r13 = r13 + 1
            int r12 = r12 + 1
            r33 = r2
            r5 = 2
            goto L_0x01c6
        L_0x0194:
            boolean r4 = r0.endsWithLoad(r5)
            if (r4 != 0) goto L_0x01c1
            boolean r4 = r0.endsWithStore(r5)
            if (r4 != 0) goto L_0x01c1
            r4 = 169(0xa9, float:2.37E-43)
            if (r5 != r4) goto L_0x01a5
            goto L_0x01c1
        L_0x01a5:
            org.apache.commons.compress.harmony.unpack200.Segment r4 = r0.segment
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r33 = r2
            java.lang.String r2 = "Found unhandled "
            r8.<init>(r2)
            org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode r2 = org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode.getByteCode(r5)
            java.lang.StringBuilder r2 = r8.append(r2)
            java.lang.String r2 = r2.toString()
            r5 = 2
            r4.log(r5, r2)
            goto L_0x01c6
        L_0x01c1:
            r33 = r2
            r5 = 2
            int r13 = r13 + 1
        L_0x01c6:
            r2 = r33
            goto L_0x01d1
        L_0x01c9:
            int r20 = r20 + 1
            goto L_0x01d1
        L_0x01cc:
            r5 = 2
            int r13 = r13 + 1
            int r11 = r11 + 1
        L_0x01d1:
            int r2 = r2 + 1
            r4 = r35
            r5 = r36
            goto L_0x00c8
        L_0x01d9:
            r36 = r5
            r5 = 2
            goto L_0x01e8
        L_0x01dd:
            r34 = r2
            r35 = r4
            r36 = r5
            r5 = 2
            r10 = r32
            r11 = r33
        L_0x01e8:
            int r3 = r3 + 1
            r2 = r34
            r4 = r35
            r5 = r36
            goto L_0x006e
        L_0x01f2:
            r34 = r2
            r35 = r4
            r36 = r5
            r32 = r10
            r33 = r11
            r5 = 2
            int r9 = r9 + 1
            r6 = r5
            r3 = r31
            r5 = r36
            goto L_0x0060
        L_0x0206:
            java.lang.String r2 = "bc_case_count"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r10)
            r0.bcCaseCount = r2
            r2 = 0
            r3 = 0
        L_0x0212:
            int[] r4 = r0.bcCaseCount
            int r4 = r4.length
            if (r2 >= r4) goto L_0x022e
            java.lang.Object r4 = r7.get(r2)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0226
            int r3 = r3 + 1
            goto L_0x022b
        L_0x0226:
            int[] r4 = r0.bcCaseCount
            r4 = r4[r2]
            int r3 = r3 + r4
        L_0x022b:
            int r2 = r2 + 1
            goto L_0x0212
        L_0x022e:
            java.lang.String r2 = "bc_case_value"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r4 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r4, (int) r3)
            r0.bcCaseValue = r2
            r8 = 0
        L_0x0239:
            if (r8 >= r10) goto L_0x0243
            int[] r2 = r0.bcCaseCount
            r2 = r2[r8]
            int r14 = r14 + r2
            int r8 = r8 + 1
            goto L_0x0239
        L_0x0243:
            java.lang.String r2 = "bc_byte"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.BYTE1
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r11)
            r0.bcByte = r2
            java.lang.String r2 = "bc_short"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r12)
            r0.bcShort = r2
            java.lang.String r2 = "bc_local"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r13)
            r0.bcLocal = r2
            java.lang.String r2 = "bc_label"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.BRANCH5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r14)
            r0.bcLabel = r2
            java.lang.String r2 = "bc_intref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r15)
            r0.bcIntRef = r2
            java.lang.String r2 = "bc_floatref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r16
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcFloatRef = r2
            java.lang.String r2 = "bc_longref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r17
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcLongRef = r2
            java.lang.String r2 = "bc_doubleref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r18
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcDoubleRef = r2
            java.lang.String r2 = "bc_stringref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r19
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcStringRef = r2
            java.lang.String r2 = "bc_classref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r20
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcClassRef = r2
            java.lang.String r2 = "bc_fieldref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r21
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcFieldRef = r2
            java.lang.String r2 = "bc_methodref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r22
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcMethodRef = r2
            java.lang.String r2 = "bc_imethodref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r23
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcIMethodRef = r2
            java.lang.String r2 = "bc_thisfield"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r24
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcThisField = r2
            java.lang.String r2 = "bc_superfield"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r25
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcSuperField = r2
            java.lang.String r2 = "bc_thismethod"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r26
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcThisMethod = r2
            java.lang.String r2 = "bc_supermethod"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r27
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcSuperMethod = r2
            java.lang.String r2 = "bc_initref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r28
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcInitRef = r2
            java.lang.String r2 = "bc_escref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r30
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcEscRef = r2
            java.lang.String r2 = "bc_escrefsize"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcEscRefSize = r2
            java.lang.String r2 = "bc_escsize"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r29
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcEscSize = r2
            org.apache.commons.compress.harmony.pack200.BHSDCodec r2 = org.apache.commons.compress.harmony.pack200.Codec.BYTE1
            int[] r3 = r0.bcEscSize
            java.lang.String r4 = "bc_escbyte"
            int[][] r1 = r0.decodeBandInt((java.lang.String) r4, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r2, (int[]) r3)
            r0.bcEscByte = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.BcBands.read(java.io.InputStream):void");
    }

    public void unpack() throws Pack200Exception {
        AttributeLayout attributeLayout;
        AttributeLayout attributeLayout2;
        String[][] strArr;
        int[] iArr;
        int[] iArr2;
        ArrayList arrayList;
        List list;
        List list2;
        int classCount = this.header.getClassCount();
        long[][] methodFlags = this.segment.getClassBands().getMethodFlags();
        int[] codeMaxNALocals = this.segment.getClassBands().getCodeMaxNALocals();
        int[] codeMaxStack = this.segment.getClassBands().getCodeMaxStack();
        ArrayList[][] methodAttributes = this.segment.getClassBands().getMethodAttributes();
        String[][] methodDescr = this.segment.getClassBands().getMethodDescr();
        AttributeLayoutMap attributeDefinitionMap = this.segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        AttributeLayout attributeLayout3 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_ABSTRACT, 2);
        AttributeLayout attributeLayout4 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_NATIVE, 2);
        AttributeLayout attributeLayout5 = attributeDefinitionMap.getAttributeLayout(AttributeLayout.ACC_STATIC, 2);
        int size = this.wideByteCodes.size();
        int[] iArr3 = new int[size];
        for (int i = 0; i < size; i++) {
            iArr3[i] = ((Integer) this.wideByteCodes.get(i)).intValue();
        }
        ArrayList[][] arrayListArr = methodAttributes;
        OperandManager operandManager = new OperandManager(this.bcCaseCount, this.bcCaseValue, this.bcByte, this.bcShort, this.bcLocal, this.bcLabel, this.bcIntRef, this.bcFloatRef, this.bcLongRef, this.bcDoubleRef, this.bcStringRef, this.bcClassRef, this.bcFieldRef, this.bcMethodRef, this.bcIMethodRef, this.bcThisField, this.bcSuperField, this.bcThisMethod, this.bcSuperMethod, this.bcInitRef, iArr3);
        operandManager.setSegment(this.segment);
        ArrayList orderedCodeAttributes = this.segment.getClassBands().getOrderedCodeAttributes();
        int[] codeHandlerCount = this.segment.getClassBands().getCodeHandlerCount();
        int[][] codeHandlerStartP = this.segment.getClassBands().getCodeHandlerStartP();
        int[][] codeHandlerEndPO = this.segment.getClassBands().getCodeHandlerEndPO();
        int[][] codeHandlerCatchPO = this.segment.getClassBands().getCodeHandlerCatchPO();
        int[][] codeHandlerClassRCN = this.segment.getClassBands().getCodeHandlerClassRCN();
        ArrayList arrayList2 = orderedCodeAttributes;
        boolean hasAllCodeFlags = this.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
        boolean[] codeHasAttributes = this.segment.getClassBands().getCodeHasAttributes();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < classCount) {
            int i5 = classCount;
            int length = methodFlags[i2].length;
            int[][] iArr4 = codeHandlerEndPO;
            int[][] iArr5 = codeHandlerCatchPO;
            int i6 = i3;
            int i7 = i4;
            int i8 = 0;
            while (i8 < length) {
                int i9 = length;
                long[][] jArr = methodFlags;
                long j = methodFlags[i2][i8];
                if (attributeLayout3.matches(j) || attributeLayout4.matches(j)) {
                    iArr2 = codeMaxNALocals;
                    iArr = codeMaxStack;
                    strArr = methodDescr;
                    attributeLayout2 = attributeLayout5;
                    attributeLayout = attributeLayout3;
                    arrayList = arrayList2;
                    i7 = i7;
                } else {
                    int i10 = codeMaxStack[i6];
                    int i11 = codeMaxNALocals[i6];
                    if (!attributeLayout5.matches(j)) {
                        i11++;
                    }
                    int countInvokeInterfaceArgs = i11 + SegmentUtils.countInvokeInterfaceArgs(methodDescr[i2][i8]);
                    String[] cpClass = this.segment.getCpBands().getCpClass();
                    operandManager.setCurrentClass(cpClass[this.segment.getClassBands().getClassThisInts()[i2]]);
                    operandManager.setSuperClass(cpClass[this.segment.getClassBands().getClassSuperInts()[i2]]);
                    ArrayList arrayList3 = new ArrayList();
                    iArr2 = codeMaxNALocals;
                    if (codeHandlerCount != null) {
                        int i12 = 0;
                        while (i12 < codeHandlerCount[i6]) {
                            int i13 = codeHandlerClassRCN[i6][i12] - 1;
                            int[] iArr6 = codeMaxStack;
                            arrayList3.add(new ExceptionTableEntry(codeHandlerStartP[i6][i12], iArr4[i6][i12], iArr5[i6][i12], i13 != -1 ? this.segment.getCpBands().cpClassValue(i13) : null));
                            i12++;
                            codeMaxStack = iArr6;
                            methodDescr = methodDescr;
                            attributeLayout5 = attributeLayout5;
                            attributeLayout3 = attributeLayout3;
                        }
                    }
                    iArr = codeMaxStack;
                    strArr = methodDescr;
                    attributeLayout2 = attributeLayout5;
                    attributeLayout = attributeLayout3;
                    CodeAttribute codeAttribute = new CodeAttribute(i10, countInvokeInterfaceArgs, this.methodByteCodePacked[i2][i8], this.segment, operandManager, arrayList3);
                    ArrayList arrayList4 = arrayListArr[i2][i8];
                    int i14 = 0;
                    for (int i15 = 0; i15 < arrayList4.size(); i15++) {
                        Attribute attribute = (Attribute) arrayList4.get(i15);
                        if (!(attribute instanceof NewAttribute) || ((NewAttribute) attribute).getLayoutIndex() >= 15) {
                            break;
                        }
                        i14++;
                    }
                    arrayList4.add(i14, codeAttribute);
                    codeAttribute.renumber(codeAttribute.byteCodeOffsets);
                    if (hasAllCodeFlags) {
                        arrayList = arrayList2;
                        list = (List) arrayList.get(i6);
                    } else {
                        arrayList = arrayList2;
                        if (codeHasAttributes[i6]) {
                            int i16 = i7;
                            list2 = (List) arrayList.get(i16);
                            i7 = i16 + 1;
                        } else {
                            int i17 = i7;
                            list2 = Collections.EMPTY_LIST;
                        }
                        list = list2;
                    }
                    for (int i18 = 0; i18 < list.size(); i18++) {
                        Attribute attribute2 = (Attribute) list.get(i18);
                        codeAttribute.addAttribute(attribute2);
                        if (attribute2.hasBCIRenumbering()) {
                            ((BCIRenumberedAttribute) attribute2).renumber(codeAttribute.byteCodeOffsets);
                        }
                    }
                    i6++;
                }
                i8++;
                arrayList2 = arrayList;
                methodFlags = jArr;
                length = i9;
                codeMaxNALocals = iArr2;
                codeMaxStack = iArr;
                methodDescr = strArr;
                attributeLayout5 = attributeLayout2;
                attributeLayout3 = attributeLayout;
            }
            long[][] jArr2 = methodFlags;
            int[] iArr7 = codeMaxStack;
            String[][] strArr2 = methodDescr;
            AttributeLayout attributeLayout6 = attributeLayout5;
            AttributeLayout attributeLayout7 = attributeLayout3;
            ArrayList arrayList5 = arrayList2;
            i2++;
            i4 = i7;
            i3 = i6;
            classCount = i5;
            codeHandlerCatchPO = iArr5;
            codeHandlerEndPO = iArr4;
            codeMaxNALocals = codeMaxNALocals;
        }
    }

    public byte[][][] getMethodByteCodePacked() {
        return this.methodByteCodePacked;
    }

    public int[] getBcCaseCount() {
        return this.bcCaseCount;
    }

    public int[] getBcCaseValue() {
        return this.bcCaseValue;
    }

    public int[] getBcByte() {
        return this.bcByte;
    }

    public int[] getBcClassRef() {
        return this.bcClassRef;
    }

    public int[] getBcDoubleRef() {
        return this.bcDoubleRef;
    }

    public int[] getBcFieldRef() {
        return this.bcFieldRef;
    }

    public int[] getBcFloatRef() {
        return this.bcFloatRef;
    }

    public int[] getBcIMethodRef() {
        return this.bcIMethodRef;
    }

    public int[] getBcInitRef() {
        return this.bcInitRef;
    }

    public int[] getBcIntRef() {
        return this.bcIntRef;
    }

    public int[] getBcLabel() {
        return this.bcLabel;
    }

    public int[] getBcLocal() {
        return this.bcLocal;
    }

    public int[] getBcLongRef() {
        return this.bcLongRef;
    }

    public int[] getBcMethodRef() {
        return this.bcMethodRef;
    }

    public int[] getBcShort() {
        return this.bcShort;
    }

    public int[] getBcStringRef() {
        return this.bcStringRef;
    }

    public int[] getBcSuperField() {
        return this.bcSuperField;
    }

    public int[] getBcSuperMethod() {
        return this.bcSuperMethod;
    }

    public int[] getBcThisField() {
        return this.bcThisField;
    }

    public int[] getBcThisMethod() {
        return this.bcThisMethod;
    }
}
