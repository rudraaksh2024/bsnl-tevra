package org.apache.poi.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HexRead {
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] readData(java.lang.String r2) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r2)
            java.io.FileInputStream r2 = new java.io.FileInputStream
            r2.<init>(r0)
            r0 = -1
            byte[] r0 = readData((java.io.InputStream) r2, (int) r0)     // Catch:{ all -> 0x0013 }
            r2.close()
            return r0
        L_0x0013:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0015 }
        L_0x0015:
            r1 = move-exception
            r2.close()     // Catch:{ all -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x001e:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.HexRead.readData(java.lang.String):byte[]");
    }

    public static byte[] readData(InputStream inputStream, String str) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            int read = inputStream.read();
            boolean z = false;
            while (read != -1) {
                if (read == 10 || read == 13) {
                    sb = new StringBuilder();
                } else {
                    if (read == 91) {
                        z = true;
                    } else if (read != 93) {
                        if (z) {
                            sb.append((char) read);
                        }
                    } else if (sb.toString().equals(str)) {
                        return readData(inputStream, 91);
                    } else {
                        sb = new StringBuilder();
                    }
                    read = inputStream.read();
                }
                z = false;
                read = inputStream.read();
            }
            inputStream.close();
            throw new IOException("Section '" + str + "' not found");
        } finally {
            inputStream.close();
        }
    }

    public static byte[] readData(String str, String str2) throws IOException {
        return readData((InputStream) new FileInputStream(str), str2);
    }

    public static byte[] readData(InputStream inputStream, int i) throws IOException {
        int i2;
        ArrayList arrayList = new ArrayList();
        loop0:
        while (true) {
            int i3 = 0;
            byte b = 0;
            while (true) {
                int read = inputStream.read();
                if (48 <= read && read <= 57) {
                    i2 = read - 48;
                } else if (65 <= read && read <= 70) {
                    i2 = read - 55;
                } else if (97 > read || read > 102) {
                    if (35 == read) {
                        readToEOL(inputStream);
                    } else if (-1 == read || i == read) {
                        Byte[] bArr = (Byte[]) arrayList.toArray(new Byte[0]);
                        byte[] bArr2 = new byte[bArr.length];
                    }
                    i2 = -1;
                } else {
                    i2 = read - 87;
                }
                if (i2 != -1) {
                    b = (byte) (((byte) (b << 4)) + ((byte) i2));
                    i3++;
                    if (i3 == 2) {
                        break;
                    }
                }
            }
            arrayList.add(Byte.valueOf(b));
        }
        Byte[] bArr3 = (Byte[]) arrayList.toArray(new Byte[0]);
        byte[] bArr22 = new byte[bArr3.length];
        for (int i4 = 0; i4 < bArr3.length; i4++) {
            bArr22[i4] = bArr3[i4].byteValue();
        }
        return bArr22;
    }

    public static byte[] readFromString(String str) {
        try {
            return readData((InputStream) new ByteArrayInputStream(str.getBytes(StringUtil.UTF8)), -1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readToEOL(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        while (read != -1 && read != 10 && read != 13) {
            read = inputStream.read();
        }
    }
}
