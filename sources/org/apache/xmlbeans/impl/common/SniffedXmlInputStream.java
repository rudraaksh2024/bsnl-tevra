package org.apache.xmlbeans.impl.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import kotlin.UByte;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ss.formula.ptg.UnionPtg;

public class SniffedXmlInputStream extends BufferedInputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MAX_SNIFFED_BYTES = 192;
    private static char[] NOTNAME = {Chars.EQ, Chars.SPACE, Chars.CR, 9, 10, '?', Typography.greater, Typography.less, Chars.QUOTE, '\"'};
    private static char[] WHITESPACE = {Chars.SPACE, Chars.CR, 9, 10};
    private static Charset dummy1 = Charset.forName("UTF-8");
    private static Charset dummy2 = Charset.forName("UTF-16");
    private static Charset dummy3 = Charset.forName("UTF-16BE");
    private static Charset dummy4 = Charset.forName("UTF-16LE");
    private static Charset dummy5 = Charset.forName("ISO-8859-1");
    private static Charset dummy6 = Charset.forName("US-ASCII");
    private static Charset dummy7 = Charset.forName("Cp1252");
    private String _encoding;

    public SniffedXmlInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
        String sniffForXmlDecl;
        String sniffFourBytes = sniffFourBytes();
        this._encoding = sniffFourBytes;
        if (!(sniffFourBytes == null || !sniffFourBytes.equals("IBM037") || (sniffForXmlDecl = sniffForXmlDecl(this._encoding)) == null)) {
            this._encoding = sniffForXmlDecl;
        }
        if (this._encoding == null) {
            this._encoding = sniffForXmlDecl("UTF-8");
        }
        if (this._encoding == null) {
            this._encoding = "UTF-8";
        }
    }

    private int readAsMuchAsPossible(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int read = read(bArr, i + i3, i2 - i3);
            if (read < 0) {
                break;
            }
            i3 += read;
        }
        return i3;
    }

    private String sniffFourBytes() throws IOException {
        mark(4);
        try {
            byte[] bArr = new byte[4];
            if (readAsMuchAsPossible(bArr, 0, 4) < 4) {
                return null;
            }
            long j = (long) (((bArr[0] << 24) & -16777216) | ((bArr[1] << UnionPtg.sid) & 16711680) | ((bArr[2] << 8) & 65280) | (bArr[3] & UByte.MAX_VALUE));
            if (j == 65279) {
                reset();
                return "UCS-4";
            } else if (j == -131072) {
                reset();
                return "UCS-4";
            } else if (j == 60) {
                reset();
                return "UCS-4BE";
            } else if (j == 1006632960) {
                reset();
                return "UCS-4LE";
            } else if (j == 3932223) {
                reset();
                return "UTF-16BE";
            } else if (j == 1006649088) {
                reset();
                return "UTF-16LE";
            } else if (j == 1010792557) {
                reset();
                return null;
            } else if (j == 1282385812) {
                reset();
                return "IBM037";
            } else {
                long j2 = -65536 & j;
                if (j2 == -16842752) {
                    reset();
                    return "UTF-16";
                } else if (j2 == -131072) {
                    reset();
                    return "UTF-16";
                } else if ((j & -256) == -272908544) {
                    reset();
                    return "UTF-8";
                } else {
                    reset();
                    return null;
                }
            }
        } finally {
            reset();
        }
    }

    private String sniffForXmlDecl(String str) throws IOException {
        mark(192);
        try {
            byte[] bArr = new byte[192];
            int readAsMuchAsPossible = readAsMuchAsPossible(bArr, 0, 192);
            InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(bArr, 0, readAsMuchAsPossible), Charset.forName(str));
            char[] cArr = new char[readAsMuchAsPossible];
            int i = 0;
            while (true) {
                if (i >= readAsMuchAsPossible) {
                    break;
                }
                int read = inputStreamReader.read(cArr, i, readAsMuchAsPossible - i);
                if (read < 0) {
                    break;
                }
                i += read;
            }
            return extractXmlDeclEncoding(cArr, 0, i);
        } finally {
            reset();
        }
    }

    public String getXmlEncoding() {
        return this._encoding;
    }

    static String extractXmlDeclEncoding(char[] cArr, int i, int i2) {
        int i3 = i2 + i;
        int firstIndexOf = firstIndexOf("<?xml", cArr, i, i3);
        if (firstIndexOf >= 0) {
            int i4 = firstIndexOf + 5;
            ScannedAttribute scannedAttribute = new ScannedAttribute();
            while (i4 < i3) {
                i4 = scanAttribute(cArr, i4, i3, scannedAttribute);
                if (i4 < 0) {
                    return null;
                }
                if (scannedAttribute.name.equals("encoding")) {
                    return scannedAttribute.value;
                }
            }
        }
        return null;
    }

    private static int firstIndexOf(String str, char[] cArr, int i, int i2) {
        char[] charArray = str.toCharArray();
        char c = charArray[0];
        int length = i2 - charArray.length;
        while (i < length) {
            if (cArr[i] == c) {
                int i3 = 1;
                while (i3 < charArray.length) {
                    if (cArr[i + i3] == charArray[i3]) {
                        i3++;
                    }
                }
                return i;
            }
            i++;
        }
        return -1;
    }

    private static int nextNonmatchingByte(char[] cArr, char[] cArr2, int i, int i2) {
        while (i < i2) {
            char c = cArr2[i];
            int i3 = 0;
            while (i3 < cArr.length) {
                if (c == cArr[i3]) {
                    i++;
                } else {
                    i3++;
                }
            }
            return i;
        }
        return -1;
    }

    private static int nextMatchingByte(char[] cArr, char[] cArr2, int i, int i2) {
        while (i < i2) {
            char c = cArr2[i];
            for (char c2 : cArr) {
                if (c == c2) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    private static int nextMatchingByte(char c, char[] cArr, int i, int i2) {
        while (i < i2) {
            if (cArr[i] == c) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static class ScannedAttribute {
        public String name;
        public String value;

        private ScannedAttribute() {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        r4 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int scanAttribute(char[] r5, int r6, int r7, org.apache.xmlbeans.impl.common.SniffedXmlInputStream.ScannedAttribute r8) {
        /*
            char[] r0 = WHITESPACE
            int r6 = nextNonmatchingByte(r0, r5, r6, r7)
            r0 = -1
            if (r6 >= 0) goto L_0x000a
            return r0
        L_0x000a:
            char[] r1 = NOTNAME
            int r1 = nextMatchingByte((char[]) r1, (char[]) r5, (int) r6, (int) r7)
            if (r1 >= 0) goto L_0x0013
            return r0
        L_0x0013:
            char[] r2 = WHITESPACE
            int r2 = nextNonmatchingByte(r2, r5, r1, r7)
            if (r2 >= 0) goto L_0x001c
            return r0
        L_0x001c:
            char r3 = r5[r2]
            r4 = 61
            if (r3 == r4) goto L_0x0023
            return r0
        L_0x0023:
            char[] r3 = WHITESPACE
            int r2 = r2 + 1
            int r2 = nextNonmatchingByte(r3, r5, r2, r7)
            char r3 = r5[r2]
            r4 = 39
            if (r3 == r4) goto L_0x0036
            r4 = 34
            if (r3 == r4) goto L_0x0036
            return r0
        L_0x0036:
            int r4 = r2 + 1
            int r7 = nextMatchingByte((char) r3, (char[]) r5, (int) r4, (int) r7)
            if (r7 >= 0) goto L_0x003f
            return r0
        L_0x003f:
            java.lang.String r0 = new java.lang.String
            int r1 = r1 - r6
            r0.<init>(r5, r6, r1)
            r8.name = r0
            java.lang.String r6 = new java.lang.String
            int r0 = r7 - r2
            int r0 = r0 + -1
            r6.<init>(r5, r4, r0)
            r8.value = r6
            int r7 = r7 + 1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.common.SniffedXmlInputStream.scanAttribute(char[], int, int, org.apache.xmlbeans.impl.common.SniffedXmlInputStream$ScannedAttribute):int");
    }
}
