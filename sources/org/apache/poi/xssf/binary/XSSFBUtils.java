package org.apache.poi.xssf.binary;

import java.nio.charset.StandardCharsets;
import kotlin.UByte;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;

@Internal
public class XSSFBUtils {
    static int readXLNullableWideString(byte[] bArr, int i, StringBuilder sb) throws XSSFBParseException {
        long uInt = LittleEndian.getUInt(bArr, i);
        if (uInt >= 0) {
            int i2 = (uInt > 4294967295L ? 1 : (uInt == 4294967295L ? 0 : -1));
            if (i2 == 0) {
                return 0;
            }
            if (i2 <= 0) {
                int i3 = ((int) uInt) * 2;
                int i4 = i + 4;
                if (i4 + i3 <= bArr.length) {
                    sb.append(new String(bArr, i4, i3, StandardCharsets.UTF_16LE));
                    return i3 + 4;
                }
                throw new XSSFBParseException("trying to read beyond data length: offset=" + i4 + ", numBytes=" + i3 + ", data.length=" + bArr.length);
            }
            throw new XSSFBParseException("too many chars to read");
        }
        throw new XSSFBParseException("too few chars to read");
    }

    public static int readXLWideString(byte[] bArr, int i, StringBuilder sb) throws XSSFBParseException {
        long uInt = LittleEndian.getUInt(bArr, i);
        if (uInt < 0) {
            throw new XSSFBParseException("too few chars to read");
        } else if (uInt <= 4294967295L) {
            int i2 = ((int) uInt) * 2;
            int i3 = i + 4;
            if (i3 + i2 <= bArr.length) {
                sb.append(new String(bArr, i3, i2, StandardCharsets.UTF_16LE));
                return i2 + 4;
            }
            throw new XSSFBParseException("trying to read beyond data length");
        } else {
            throw new XSSFBParseException("too many chars to read");
        }
    }

    static int castToInt(long j) {
        if (j < 2147483647L && j > -2147483648L) {
            return (int) j;
        }
        throw new POIXMLException("val (" + j + ") can't be cast to int");
    }

    static short castToShort(int i) {
        if (i < 32767 && i > -32768) {
            return (short) i;
        }
        throw new POIXMLException("val (" + i + ") can't be cast to short");
    }

    static int get24BitInt(byte[] bArr, int i) {
        int i2 = i + 1;
        return ((bArr[i2 + 1] & UByte.MAX_VALUE) << UnionPtg.sid) + ((bArr[i2] & UByte.MAX_VALUE) << 8) + (bArr[i] & UByte.MAX_VALUE);
    }
}
