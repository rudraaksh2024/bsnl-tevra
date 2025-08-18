package org.apache.poi.poifs.filesystem;

import com.google.mlkit.common.MlKitException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.hssf.usermodel.HSSFShapeTypes;
import org.apache.poi.poifs.storage.HeaderBlockConstants;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LocaleUtil;

public enum FileMagic {
    OLE2((String) HeaderBlockConstants._signature),
    OOXML((String) new int[]{80, 75, 3, 4}),
    XML((String) new int[]{60, 63, 120, 109, 108}),
    BIFF2((String) new int[]{9, 0, 4, 0, 0, 0, 63, 0}),
    BIFF3((String) new int[]{9, 2, 6, 0, 0, 0, 63, 0}),
    BIFF4((String) new byte[][]{new byte[]{9, 4, 6, 0, 0, 0, 63, 0}, new byte[]{9, 4, 6, 0, 0, 0, 0, 1}}),
    MSWRITE((String) new byte[][]{new byte[]{TarConstants.LF_LINK, -66, 0, 0}, new byte[]{TarConstants.LF_SYMLINK, -66, 0, 0}}),
    RTF((String) new String[]{"{\\rtf"}),
    PDF((String) new String[]{"%PDF"}),
    HTML((String) new String[]{"<!DOCTYP", "<html", "\n\r<html", "\r\n<html", "\r<html", "\n<html", "<HTML", "\r\n<HTML", "\n\r<HTML", "\r<HTML", "\n<HTML"}),
    WORD2((String) new int[]{219, 165, 45, 0}),
    JPEG((String) new byte[][]{new byte[]{-1, -40, -1, -37}, new byte[]{-1, -40, -1, -32, 63, 63, 74, 70, 73, 70, 0, 1}, new byte[]{-1, -40, -1, -18}, new byte[]{-1, -40, -1, -31, 63, 63, 69, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 105, 102, 0, 0}}),
    GIF((String) new String[]{"GIF87a", "GIF89a"}),
    PNG((String) new int[]{137, 80, 78, 71, 13, 10, 26, 10}),
    TIFF((String) new String[]{"II*\u0000", "MM\u0000*"}),
    WMF((String) new int[]{215, MlKitException.CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR, HSSFShapeTypes.ActionButtonDocument, 154}),
    EMF((String) new int[]{1, 0, 0, 0, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 32, 69, 77, 70}),
    BMP((String) new int[]{66, 77}),
    UNKNOWN((String) new byte[][]{new byte[0]});
    
    static final int MAX_PATTERN_LENGTH = 44;
    final byte[][] magic;

    private FileMagic(long j) {
        byte[][] bArr = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{1, 8});
        this.magic = bArr;
        LittleEndian.putLong(bArr[0], 0, j);
    }

    private FileMagic(int... iArr) {
        byte[] bArr = new byte[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            bArr[i] = (byte) (iArr[i] & 255);
        }
        this.magic = new byte[][]{bArr};
    }

    private FileMagic(byte[]... bArr) {
        this.magic = bArr;
    }

    private FileMagic(String... strArr) {
        this.magic = new byte[strArr.length][];
        int length = strArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            this.magic[i2] = strArr[i].getBytes(LocaleUtil.CHARSET_1252);
            i++;
            i2++;
        }
    }

    public static FileMagic valueOf(byte[] bArr) {
        for (FileMagic fileMagic : values()) {
            for (byte[] bArr2 : fileMagic.magic) {
                if (bArr.length >= bArr2.length && findMagic(bArr2, bArr)) {
                    return fileMagic;
                }
            }
        }
        return UNKNOWN;
    }

    private static boolean findMagic(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            if (bArr2[i2] != b && b != 63) {
                return false;
            }
            i++;
            i2 = i3;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.poifs.filesystem.FileMagic valueOf(java.io.File r3) throws java.io.IOException {
        /*
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r3)
            r3 = 44
            byte[] r1 = new byte[r3]     // Catch:{ all -> 0x0023 }
            r2 = 0
            int r3 = org.apache.poi.util.IOUtils.readFully(r0, r1, r2, r3)     // Catch:{ all -> 0x0023 }
            r2 = -1
            if (r3 != r2) goto L_0x0017
            org.apache.poi.poifs.filesystem.FileMagic r3 = UNKNOWN     // Catch:{ all -> 0x0023 }
            r0.close()
            return r3
        L_0x0017:
            byte[] r3 = java.util.Arrays.copyOf(r1, r3)     // Catch:{ all -> 0x0023 }
            org.apache.poi.poifs.filesystem.FileMagic r3 = valueOf((byte[]) r3)     // Catch:{ all -> 0x0023 }
            r0.close()
            return r3
        L_0x0023:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r0 = move-exception
            r3.addSuppressed(r0)
        L_0x002e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.FileMagic.valueOf(java.io.File):org.apache.poi.poifs.filesystem.FileMagic");
    }

    public static FileMagic valueOf(InputStream inputStream) throws IOException {
        if (inputStream.markSupported()) {
            return valueOf(IOUtils.peekFirstNBytes(inputStream, 44));
        }
        throw new IOException("getFileMagic() only operates on streams which support mark(int)");
    }

    public static InputStream prepareToCheckMagic(InputStream inputStream) {
        if (inputStream.markSupported()) {
            return inputStream;
        }
        return new BufferedInputStream(inputStream);
    }
}
