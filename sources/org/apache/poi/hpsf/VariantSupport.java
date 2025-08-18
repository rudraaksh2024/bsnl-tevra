package org.apache.poi.hpsf;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

public class VariantSupport extends Variant {
    private static final Logger LOG = LogManager.getLogger((Class<?>) VariantSupport.class);
    public static final int[] SUPPORTED_TYPES = {0, 2, 3, 20, 5, 64, 30, 31, 71, 11};
    private static boolean logUnsupportedTypes;
    private static final byte[] paddingBytes = new byte[3];
    private static List<Long> unsupportedMessage;

    public static void setLogUnsupportedTypes(boolean z) {
        logUnsupportedTypes = z;
    }

    public static boolean isLogUnsupportedTypes() {
        return logUnsupportedTypes;
    }

    protected static void writeUnsupportedTypeMessage(UnsupportedVariantTypeException unsupportedVariantTypeException) {
        if (isLogUnsupportedTypes()) {
            if (unsupportedMessage == null) {
                unsupportedMessage = new LinkedList();
            }
            Long valueOf = Long.valueOf(unsupportedVariantTypeException.getVariantType());
            if (!unsupportedMessage.contains(valueOf)) {
                LOG.atError().withThrowable(unsupportedVariantTypeException).log("Unsupported type");
                unsupportedMessage.add(valueOf);
            }
        }
    }

    public boolean isSupportedType(int i) {
        for (int i2 : SUPPORTED_TYPES) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    public static Object read(byte[] bArr, int i, int i2, long j, int i3) throws ReadingNotSupportedException, UnsupportedEncodingException {
        return read(new LittleEndianByteArrayInputStream(bArr, i), i2, j, i3);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:39|40|41) */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00aa, code lost:
        throw new org.apache.poi.hpsf.ReadingNotSupportedException(r6, org.apache.poi.util.IOUtils.toByteArray(r4, r5, org.apache.poi.hpsf.CodePageString.getMaxRecordLength()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ab, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b1, code lost:
        throw new java.lang.RuntimeException(r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x009d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object read(org.apache.poi.util.LittleEndianByteArrayInputStream r4, int r5, long r6, int r8) throws org.apache.poi.hpsf.ReadingNotSupportedException, java.io.UnsupportedEncodingException {
        /*
            int r0 = r4.getReadIndex()
            org.apache.poi.hpsf.TypedPropertyValue r1 = new org.apache.poi.hpsf.TypedPropertyValue
            int r2 = (int) r6
            r3 = 0
            r1.<init>(r2, r3)
            r1.readValue(r4)     // Catch:{ UnsupportedOperationException -> 0x009d }
            if (r2 == 0) goto L_0x0098
            r5 = 11
            if (r2 == r5) goto L_0x0089
            r5 = 64
            if (r2 == r5) goto L_0x007e
            r5 = 71
            if (r2 == r5) goto L_0x0073
            r5 = 2
            if (r2 == r5) goto L_0x0064
            r5 = 3
            if (r2 == r5) goto L_0x0098
            r5 = 4
            if (r2 == r5) goto L_0x0098
            r5 = 5
            if (r2 == r5) goto L_0x0098
            r5 = 30
            if (r2 == r5) goto L_0x0059
            r5 = 31
            if (r2 == r5) goto L_0x004e
            switch(r2) {
                case 16: goto L_0x0098;
                case 17: goto L_0x0098;
                case 18: goto L_0x0098;
                case 19: goto L_0x0098;
                case 20: goto L_0x0098;
                case 21: goto L_0x0098;
                default: goto L_0x0033;
            }
        L_0x0033:
            int r5 = r4.getReadIndex()
            int r5 = r5 - r0
            r4.setReadIndex(r0)
            long r0 = (long) r5
            int r8 = org.apache.poi.hpsf.CodePageString.getMaxRecordLength()
            byte[] r8 = org.apache.poi.util.IOUtils.safelyAllocate(r0, r8)
            r0 = 0
            r4.readFully(r8, r0, r5)
            org.apache.poi.hpsf.ReadingNotSupportedException r4 = new org.apache.poi.hpsf.ReadingNotSupportedException
            r4.<init>(r6, r8)
            throw r4
        L_0x004e:
            java.lang.Object r4 = r1.getValue()
            org.apache.poi.hpsf.UnicodeString r4 = (org.apache.poi.hpsf.UnicodeString) r4
            java.lang.String r4 = r4.toJavaString()
            return r4
        L_0x0059:
            java.lang.Object r4 = r1.getValue()
            org.apache.poi.hpsf.CodePageString r4 = (org.apache.poi.hpsf.CodePageString) r4
            java.lang.String r4 = r4.getJavaValue(r8)
            return r4
        L_0x0064:
            java.lang.Object r4 = r1.getValue()
            java.lang.Short r4 = (java.lang.Short) r4
            int r4 = r4.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            return r4
        L_0x0073:
            java.lang.Object r4 = r1.getValue()
            org.apache.poi.hpsf.ClipboardData r4 = (org.apache.poi.hpsf.ClipboardData) r4
            byte[] r4 = r4.toByteArray()
            return r4
        L_0x007e:
            java.lang.Object r4 = r1.getValue()
            org.apache.poi.hpsf.Filetime r4 = (org.apache.poi.hpsf.Filetime) r4
            java.util.Date r4 = r4.getJavaValue()
            return r4
        L_0x0089:
            java.lang.Object r4 = r1.getValue()
            org.apache.poi.hpsf.VariantBool r4 = (org.apache.poi.hpsf.VariantBool) r4
            boolean r4 = r4.getValue()
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            return r4
        L_0x0098:
            java.lang.Object r4 = r1.getValue()
            return r4
        L_0x009d:
            int r8 = org.apache.poi.hpsf.CodePageString.getMaxRecordLength()     // Catch:{ IOException -> 0x00ab }
            byte[] r4 = org.apache.poi.util.IOUtils.toByteArray(r4, r5, r8)     // Catch:{ IOException -> 0x00ab }
            org.apache.poi.hpsf.ReadingNotSupportedException r5 = new org.apache.poi.hpsf.ReadingNotSupportedException     // Catch:{ IOException -> 0x00ab }
            r5.<init>(r6, r4)     // Catch:{ IOException -> 0x00ab }
            throw r5     // Catch:{ IOException -> 0x00ab }
        L_0x00ab:
            r4 = move-exception
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hpsf.VariantSupport.read(org.apache.poi.util.LittleEndianByteArrayInputStream, int, long, int):java.lang.Object");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x014d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int write(java.io.OutputStream r11, long r12, java.lang.Object r14, int r15) throws java.io.IOException, org.apache.poi.hpsf.WritingNotSupportedException {
        /*
            int r0 = (int) r12
            r1 = 0
            r2 = 3
            r3 = 4
            r4 = -1
            if (r0 == 0) goto L_0x0145
            r5 = 11
            r6 = 2
            if (r0 == r5) goto L_0x012b
            r5 = 64
            if (r0 == r5) goto L_0x0114
            r7 = 71
            if (r0 == r7) goto L_0x0106
            if (r0 == r6) goto L_0x00f7
            if (r0 == r2) goto L_0x00e8
            if (r0 == r3) goto L_0x00d5
            r7 = 5
            r8 = 8
            if (r0 == r7) goto L_0x00c4
            r7 = 30
            if (r0 == r7) goto L_0x00af
            r15 = 31
            if (r0 == r15) goto L_0x009a
            switch(r0) {
                case 18: goto L_0x008a;
                case 19: goto L_0x007a;
                case 20: goto L_0x006b;
                case 21: goto L_0x002c;
                default: goto L_0x002a;
            }
        L_0x002a:
            goto L_0x0143
        L_0x002c:
            boolean r15 = r14 instanceof java.lang.Number
            if (r15 == 0) goto L_0x0143
            boolean r15 = r14 instanceof java.math.BigInteger
            if (r15 == 0) goto L_0x0038
            r15 = r14
            java.math.BigInteger r15 = (java.math.BigInteger) r15
            goto L_0x0043
        L_0x0038:
            r15 = r14
            java.lang.Number r15 = (java.lang.Number) r15
            long r6 = r15.longValue()
            java.math.BigInteger r15 = java.math.BigInteger.valueOf(r6)
        L_0x0043:
            int r0 = r15.bitLength()
            if (r0 > r5) goto L_0x0065
            byte[] r15 = r15.toByteArray()
            byte[] r0 = new byte[r8]
            int r5 = r15.length
            int r6 = r15.length
            r7 = r1
        L_0x0052:
            if (r7 >= r6) goto L_0x0061
            byte r9 = r15[r7]
            if (r5 > r8) goto L_0x005c
            int r10 = r5 + -1
            r0[r10] = r9
        L_0x005c:
            int r5 = r5 + -1
            int r7 = r7 + 1
            goto L_0x0052
        L_0x0061:
            r11.write(r0)
            goto L_0x00d2
        L_0x0065:
            org.apache.poi.hpsf.WritingNotSupportedException r11 = new org.apache.poi.hpsf.WritingNotSupportedException
            r11.<init>(r12, r14)
            throw r11
        L_0x006b:
            boolean r15 = r14 instanceof java.lang.Number
            if (r15 == 0) goto L_0x0143
            r15 = r14
            java.lang.Number r15 = (java.lang.Number) r15
            long r5 = r15.longValue()
            org.apache.poi.util.LittleEndian.putLong(r5, r11)
            goto L_0x00d2
        L_0x007a:
            boolean r15 = r14 instanceof java.lang.Number
            if (r15 == 0) goto L_0x0143
            r15 = r14
            java.lang.Number r15 = (java.lang.Number) r15
            long r5 = r15.longValue()
            org.apache.poi.util.LittleEndian.putUInt(r5, r11)
            goto L_0x014a
        L_0x008a:
            boolean r15 = r14 instanceof java.lang.Number
            if (r15 == 0) goto L_0x0143
            r15 = r14
            java.lang.Number r15 = (java.lang.Number) r15
            int r15 = r15.intValue()
            org.apache.poi.util.LittleEndian.putUShort(r15, r11)
            goto L_0x014b
        L_0x009a:
            boolean r15 = r14 instanceof java.lang.String
            if (r15 == 0) goto L_0x0143
            org.apache.poi.hpsf.UnicodeString r15 = new org.apache.poi.hpsf.UnicodeString
            r15.<init>()
            r0 = r14
            java.lang.String r0 = (java.lang.String) r0
            r15.setJavaValue(r0)
            int r6 = r15.write(r11)
            goto L_0x014b
        L_0x00af:
            boolean r0 = r14 instanceof java.lang.String
            if (r0 == 0) goto L_0x0143
            org.apache.poi.hpsf.CodePageString r0 = new org.apache.poi.hpsf.CodePageString
            r0.<init>()
            r5 = r14
            java.lang.String r5 = (java.lang.String) r5
            r0.setJavaValue(r5, r15)
            int r6 = r0.write(r11)
            goto L_0x014b
        L_0x00c4:
            boolean r15 = r14 instanceof java.lang.Number
            if (r15 == 0) goto L_0x0143
            r15 = r14
            java.lang.Number r15 = (java.lang.Number) r15
            double r5 = r15.doubleValue()
            org.apache.poi.util.LittleEndian.putDouble(r5, r11)
        L_0x00d2:
            r6 = r8
            goto L_0x014b
        L_0x00d5:
            boolean r15 = r14 instanceof java.lang.Number
            if (r15 == 0) goto L_0x0143
            r15 = r14
            java.lang.Number r15 = (java.lang.Number) r15
            float r15 = r15.floatValue()
            int r15 = java.lang.Float.floatToIntBits(r15)
            org.apache.poi.util.LittleEndian.putInt(r15, r11)
            goto L_0x014a
        L_0x00e8:
            boolean r15 = r14 instanceof java.lang.Number
            if (r15 == 0) goto L_0x0143
            r15 = r14
            java.lang.Number r15 = (java.lang.Number) r15
            int r15 = r15.intValue()
            org.apache.poi.util.LittleEndian.putInt(r15, r11)
            goto L_0x014a
        L_0x00f7:
            boolean r15 = r14 instanceof java.lang.Number
            if (r15 == 0) goto L_0x0143
            r15 = r14
            java.lang.Number r15 = (java.lang.Number) r15
            short r15 = r15.shortValue()
            org.apache.poi.util.LittleEndian.putShort(r11, r15)
            goto L_0x014b
        L_0x0106:
            boolean r15 = r14 instanceof byte[]
            if (r15 == 0) goto L_0x0143
            r15 = r14
            byte[] r15 = (byte[]) r15
            byte[] r15 = (byte[]) r15
            r11.write(r15)
            int r6 = r15.length
            goto L_0x014b
        L_0x0114:
            boolean r15 = r14 instanceof java.util.Date
            if (r15 == 0) goto L_0x0121
            org.apache.poi.hpsf.Filetime r15 = new org.apache.poi.hpsf.Filetime
            r0 = r14
            java.util.Date r0 = (java.util.Date) r0
            r15.<init>(r0)
            goto L_0x0126
        L_0x0121:
            org.apache.poi.hpsf.Filetime r15 = new org.apache.poi.hpsf.Filetime
            r15.<init>()
        L_0x0126:
            int r6 = r15.write(r11)
            goto L_0x014b
        L_0x012b:
            boolean r15 = r14 instanceof java.lang.Boolean
            if (r15 == 0) goto L_0x0143
            r15 = r14
            java.lang.Boolean r15 = (java.lang.Boolean) r15
            boolean r15 = r15.booleanValue()
            if (r15 == 0) goto L_0x013b
            r15 = 255(0xff, float:3.57E-43)
            goto L_0x013c
        L_0x013b:
            r15 = r1
        L_0x013c:
            r11.write(r15)
            r11.write(r15)
            goto L_0x014b
        L_0x0143:
            r6 = r4
            goto L_0x014b
        L_0x0145:
            r5 = 0
            org.apache.poi.util.LittleEndian.putUInt(r5, r11)
        L_0x014a:
            r6 = r3
        L_0x014b:
            if (r6 != r4) goto L_0x0169
            boolean r15 = r14 instanceof byte[]
            if (r15 == 0) goto L_0x0163
            r15 = r14
            byte[] r15 = (byte[]) r15
            byte[] r15 = (byte[]) r15
            r11.write(r15)
            int r6 = r15.length
            org.apache.poi.hpsf.WritingNotSupportedException r15 = new org.apache.poi.hpsf.WritingNotSupportedException
            r15.<init>(r12, r14)
            writeUnsupportedTypeMessage(r15)
            goto L_0x0169
        L_0x0163:
            org.apache.poi.hpsf.WritingNotSupportedException r11 = new org.apache.poi.hpsf.WritingNotSupportedException
            r11.<init>(r12, r14)
            throw r11
        L_0x0169:
            r12 = r6 & 3
            int r3 = r3 - r12
            r12 = r3 & 3
            byte[] r13 = paddingBytes
            r11.write(r13, r1, r12)
            int r6 = r6 + r12
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hpsf.VariantSupport.write(java.io.OutputStream, long, java.lang.Object, int):int");
    }
}
