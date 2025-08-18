package org.apache.xmlbeans.impl.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LongUTFDataOutputStream extends DataOutputStream {
    static final int MAX_UNSIGNED_SHORT = 65534;

    public LongUTFDataOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void writeShortOrInt(int i) throws IOException {
        writeShortOrInt(this, i);
    }

    public static void writeShortOrInt(DataOutputStream dataOutputStream, int i) throws IOException {
        if (i < MAX_UNSIGNED_SHORT) {
            dataOutputStream.writeShort(i);
            return;
        }
        dataOutputStream.writeShort(MAX_UNSIGNED_SHORT);
        dataOutputStream.writeInt(i);
    }

    public void writeLongUTF(String str) throws IOException {
        int i;
        int i2;
        writeShortOrInt(countUTF(str));
        byte[] bArr = new byte[4096];
        int length = str.length();
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (i3 >= 4093) {
                write(bArr, 0, i3);
                i3 = 0;
            }
            char charAt = str.charAt(i4);
            if (charAt < 1 || charAt > 127) {
                if (charAt > 2047) {
                    int i5 = i3 + 1;
                    bArr[i3] = (byte) (((charAt >> 12) & 15) | 224);
                    i = i5 + 1;
                    bArr[i5] = (byte) (((charAt >> 6) & 63) | 128);
                } else {
                    bArr[i3] = (byte) (((charAt >> 6) & 31) | 192);
                    i = i3 + 1;
                }
                i2 = i + 1;
                bArr[i] = (byte) ((charAt & '?') | 128);
            } else {
                i2 = i3 + 1;
                bArr[i3] = (byte) charAt;
            }
            i3 = i2;
        }
        write(bArr, 0, i3);
    }

    public static int countUTF(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            i = (charAt < 1 || charAt > 127) ? charAt > 2047 ? i + 3 : i + 2 : i + 1;
        }
        return i;
    }
}
