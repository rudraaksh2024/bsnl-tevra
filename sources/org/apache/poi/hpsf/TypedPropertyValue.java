package org.apache.poi.hpsf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class TypedPropertyValue {
    private static final Logger LOG = LogManager.getLogger((Class<?>) TypedPropertyValue.class);
    private int _type;
    private Object _value;

    public TypedPropertyValue(int i, Object obj) {
        this._type = i;
        this._value = obj;
    }

    public Object getValue() {
        return this._value;
    }

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        this._type = littleEndianByteArrayInputStream.readShort();
        short readShort = littleEndianByteArrayInputStream.readShort();
        if (readShort != 0) {
            LOG.atWarn().log("TypedPropertyValue padding at offset {} MUST be 0, but it's value is {}", Unbox.box(littleEndianByteArrayInputStream.getReadIndex()), Unbox.box(readShort));
        }
        readValue(littleEndianByteArrayInputStream);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0130, code lost:
        r5._value = java.lang.Integer.valueOf(r6.readInt());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readValue(org.apache.poi.util.LittleEndianByteArrayInputStream r6) {
        /*
            r5 = this;
            int r0 = r5._type
            r1 = 10
            if (r0 == r1) goto L_0x017b
            r1 = 11
            if (r0 == r1) goto L_0x0170
            r1 = 30
            if (r0 == r1) goto L_0x0165
            r1 = 31
            if (r0 == r1) goto L_0x015a
            r1 = 4126(0x101e, float:5.782E-42)
            if (r0 == r1) goto L_0x014a
            r1 = 4127(0x101f, float:5.783E-42)
            if (r0 == r1) goto L_0x014a
            r1 = 4167(0x1047, float:5.839E-42)
            if (r0 == r1) goto L_0x014a
            r1 = 4168(0x1048, float:5.84E-42)
            if (r0 == r1) goto L_0x014a
            switch(r0) {
                case 0: goto L_0x0146;
                case 1: goto L_0x0146;
                case 2: goto L_0x013b;
                case 3: goto L_0x0130;
                case 4: goto L_0x0121;
                case 5: goto L_0x0116;
                case 6: goto L_0x010a;
                case 7: goto L_0x00fe;
                case 8: goto L_0x0165;
                default: goto L_0x0025;
            }
        L_0x0025:
            switch(r0) {
                case 14: goto L_0x00f2;
                case 64: goto L_0x00e6;
                case 65: goto L_0x00da;
                case 66: goto L_0x00ce;
                case 67: goto L_0x00ce;
                case 68: goto L_0x00ce;
                case 69: goto L_0x00ce;
                case 70: goto L_0x00da;
                case 71: goto L_0x00c2;
                case 72: goto L_0x00b6;
                case 73: goto L_0x00aa;
                case 4160: goto L_0x014a;
                case 8194: goto L_0x009e;
                case 8195: goto L_0x009e;
                case 8196: goto L_0x009e;
                case 8197: goto L_0x009e;
                case 8198: goto L_0x009e;
                case 8199: goto L_0x009e;
                case 8200: goto L_0x009e;
                case 8202: goto L_0x009e;
                case 8203: goto L_0x009e;
                case 8204: goto L_0x009e;
                case 8206: goto L_0x009e;
                case 8208: goto L_0x009e;
                case 8209: goto L_0x009e;
                case 8210: goto L_0x009e;
                case 8211: goto L_0x009e;
                case 8214: goto L_0x009e;
                case 8215: goto L_0x009e;
                default: goto L_0x0028;
            }
        L_0x0028:
            switch(r0) {
                case 16: goto L_0x0092;
                case 17: goto L_0x0086;
                case 18: goto L_0x007a;
                case 19: goto L_0x017b;
                case 20: goto L_0x006e;
                case 21: goto L_0x004b;
                case 22: goto L_0x0130;
                case 23: goto L_0x017b;
                default: goto L_0x002b;
            }
        L_0x002b:
            switch(r0) {
                case 4098: goto L_0x014a;
                case 4099: goto L_0x014a;
                case 4100: goto L_0x014a;
                case 4101: goto L_0x014a;
                case 4102: goto L_0x014a;
                case 4103: goto L_0x014a;
                case 4104: goto L_0x014a;
                default: goto L_0x002e;
            }
        L_0x002e:
            switch(r0) {
                case 4106: goto L_0x014a;
                case 4107: goto L_0x014a;
                case 4108: goto L_0x014a;
                default: goto L_0x0031;
            }
        L_0x0031:
            switch(r0) {
                case 4112: goto L_0x014a;
                case 4113: goto L_0x014a;
                case 4114: goto L_0x014a;
                case 4115: goto L_0x014a;
                case 4116: goto L_0x014a;
                case 4117: goto L_0x014a;
                default: goto L_0x0034;
            }
        L_0x0034:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "Unknown (possibly, incorrect) TypedPropertyValue type: "
            r6.<init>(r0)
            int r5 = r5._type
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.String r5 = r5.toString()
            java.lang.UnsupportedOperationException r6 = new java.lang.UnsupportedOperationException
            r6.<init>(r5)
            throw r6
        L_0x004b:
            r0 = 8
            byte[] r1 = new byte[r0]
            r6.readFully(r1)
            r6 = 9
            byte[] r6 = new byte[r6]
            r2 = 0
            r3 = r0
        L_0x0058:
            if (r2 >= r0) goto L_0x0065
            byte r4 = r1[r2]
            if (r3 > r0) goto L_0x0060
            r6[r3] = r4
        L_0x0060:
            int r3 = r3 + -1
            int r2 = r2 + 1
            goto L_0x0058
        L_0x0065:
            java.math.BigInteger r0 = new java.math.BigInteger
            r0.<init>(r6)
            r5._value = r0
            goto L_0x0185
        L_0x006e:
            long r0 = r6.readLong()
            java.lang.Long r6 = java.lang.Long.valueOf(r0)
            r5._value = r6
            goto L_0x0185
        L_0x007a:
            int r6 = r6.readUShort()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5._value = r6
            goto L_0x0185
        L_0x0086:
            int r6 = r6.readUByte()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5._value = r6
            goto L_0x0185
        L_0x0092:
            byte r6 = r6.readByte()
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)
            r5._value = r6
            goto L_0x0185
        L_0x009e:
            org.apache.poi.hpsf.Array r0 = new org.apache.poi.hpsf.Array
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x00aa:
            org.apache.poi.hpsf.VersionedStream r0 = new org.apache.poi.hpsf.VersionedStream
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x00b6:
            org.apache.poi.hpsf.GUID r0 = new org.apache.poi.hpsf.GUID
            r0.<init>()
            r0.read(r6)
            r5._value = r6
            goto L_0x0185
        L_0x00c2:
            org.apache.poi.hpsf.ClipboardData r0 = new org.apache.poi.hpsf.ClipboardData
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x00ce:
            org.apache.poi.hpsf.IndirectPropertyName r0 = new org.apache.poi.hpsf.IndirectPropertyName
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x00da:
            org.apache.poi.hpsf.Blob r0 = new org.apache.poi.hpsf.Blob
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x00e6:
            org.apache.poi.hpsf.Filetime r0 = new org.apache.poi.hpsf.Filetime
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x00f2:
            org.apache.poi.hpsf.Decimal r0 = new org.apache.poi.hpsf.Decimal
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x00fe:
            org.apache.poi.hpsf.Date r0 = new org.apache.poi.hpsf.Date
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x010a:
            org.apache.poi.hpsf.Currency r0 = new org.apache.poi.hpsf.Currency
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x0116:
            double r0 = r6.readDouble()
            java.lang.Double r6 = java.lang.Double.valueOf(r0)
            r5._value = r6
            goto L_0x0185
        L_0x0121:
            int r6 = r6.readInt()
            float r6 = java.lang.Float.intBitsToFloat(r6)
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            r5._value = r6
            goto L_0x0185
        L_0x0130:
            int r6 = r6.readInt()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5._value = r6
            goto L_0x0185
        L_0x013b:
            short r6 = r6.readShort()
            java.lang.Short r6 = java.lang.Short.valueOf(r6)
            r5._value = r6
            goto L_0x0185
        L_0x0146:
            r6 = 0
            r5._value = r6
            goto L_0x0185
        L_0x014a:
            org.apache.poi.hpsf.Vector r0 = new org.apache.poi.hpsf.Vector
            int r1 = r5._type
            r1 = r1 & 4095(0xfff, float:5.738E-42)
            short r1 = (short) r1
            r0.<init>(r1)
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x015a:
            org.apache.poi.hpsf.UnicodeString r0 = new org.apache.poi.hpsf.UnicodeString
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x0165:
            org.apache.poi.hpsf.CodePageString r0 = new org.apache.poi.hpsf.CodePageString
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x0170:
            org.apache.poi.hpsf.VariantBool r0 = new org.apache.poi.hpsf.VariantBool
            r0.<init>()
            r0.read(r6)
            r5._value = r0
            goto L_0x0185
        L_0x017b:
            long r0 = r6.readUInt()
            java.lang.Long r6 = java.lang.Long.valueOf(r0)
            r5._value = r6
        L_0x0185:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hpsf.TypedPropertyValue.readValue(org.apache.poi.util.LittleEndianByteArrayInputStream):void");
    }

    static void skipPadding(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        int readIndex = (4 - (littleEndianByteArrayInputStream.getReadIndex() & 3)) & 3;
        for (int i = 0; i < readIndex; i++) {
            littleEndianByteArrayInputStream.mark(1);
            int read = littleEndianByteArrayInputStream.read();
            if (read == -1 || read != 0) {
                littleEndianByteArrayInputStream.reset();
                return;
            }
        }
    }
}
