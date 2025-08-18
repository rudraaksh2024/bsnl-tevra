package org.apache.xmlbeans.impl.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UTFDataFormatException;
import kotlin.UByte;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.NumberPtg;

public class LongUTFDataInputStream extends DataInputStream {

    private interface IOCall {
        byte onebyte(int[] iArr, int[] iArr2, int[] iArr3) throws IOException;
    }

    public LongUTFDataInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public int readUnsignedShortOrInt() throws IOException {
        return readUnsignedShortOrInt(this);
    }

    public static int readUnsignedShortOrInt(DataInputStream dataInputStream) throws IOException {
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        return readUnsignedShort == 65534 ? dataInputStream.readInt() : readUnsignedShort;
    }

    public String readLongUTF() throws IOException {
        int readUnsignedShortOrInt = readUnsignedShortOrInt();
        StringBuilder sb = new StringBuilder(readUnsignedShortOrInt / 2);
        LongUTFDataInputStream$$ExternalSyntheticLambda0 longUTFDataInputStream$$ExternalSyntheticLambda0 = new LongUTFDataInputStream$$ExternalSyntheticLambda0(this, readUnsignedShortOrInt, new byte[4096]);
        int[] iArr = {0};
        int[] iArr2 = {0};
        int[] iArr3 = {0};
        while (iArr[0] < readUnsignedShortOrInt) {
            byte onebyte = longUTFDataInputStream$$ExternalSyntheticLambda0.onebyte(iArr2, iArr3, iArr) & UByte.MAX_VALUE;
            switch (onebyte >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    sb.append((char) onebyte);
                    break;
                case 12:
                case 13:
                    byte onebyte2 = longUTFDataInputStream$$ExternalSyntheticLambda0.onebyte(iArr2, iArr3, iArr);
                    if ((onebyte2 & 192) == 128) {
                        sb.append((char) (((onebyte & NumberPtg.sid) << 6) | (onebyte2 & 63)));
                        break;
                    } else {
                        throw new UTFDataFormatException("malformed input around byte " + iArr[0]);
                    }
                case 14:
                    byte onebyte3 = longUTFDataInputStream$$ExternalSyntheticLambda0.onebyte(iArr2, iArr3, iArr);
                    byte onebyte4 = longUTFDataInputStream$$ExternalSyntheticLambda0.onebyte(iArr2, iArr3, iArr);
                    if ((onebyte3 & 192) == 128 && (onebyte4 & 192) == 128) {
                        sb.append((char) (((onebyte & IntersectionPtg.sid) << 12) | ((onebyte3 & 63) << 6) | (onebyte4 & 63)));
                        break;
                    } else {
                        throw new UTFDataFormatException("malformed input around byte " + (iArr[0] - 1));
                    }
                default:
                    throw new UTFDataFormatException("malformed input around byte " + iArr[0]);
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$readLongUTF$0$org-apache-xmlbeans-impl-util-LongUTFDataInputStream  reason: not valid java name */
    public /* synthetic */ byte m2408lambda$readLongUTF$0$orgapachexmlbeansimplutilLongUTFDataInputStream(int i, byte[] bArr, int[] iArr, int[] iArr2, int[] iArr3) throws IOException {
        int i2 = iArr3[0];
        if (i2 + 1 <= i) {
            if (iArr[0] >= iArr2[0]) {
                int min = Math.min(bArr.length, i - i2);
                iArr2[0] = min;
                readFully(bArr, 0, min);
                iArr[0] = 0;
            }
            iArr3[0] = iArr3[0] + 1;
            int i3 = iArr[0];
            iArr[0] = i3 + 1;
            return bArr[i3];
        }
        throw new UTFDataFormatException("malformed input: partial character at end");
    }
}
