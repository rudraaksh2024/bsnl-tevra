package org.apache.poi.poifs.filesystem;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;

@Internal
public final class DocumentFactoryHelper {
    private DocumentFactoryHelper() {
    }

    public static InputStream getDecryptedStream(final POIFSFileSystem pOIFSFileSystem, String str) throws IOException {
        return new FilterInputStream(getDecryptedStream(pOIFSFileSystem.getRoot(), str)) {
            public void close() throws IOException {
                pOIFSFileSystem.close();
                super.close();
            }
        };
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002e A[Catch:{ GeneralSecurityException -> 0x0021 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002f A[Catch:{ GeneralSecurityException -> 0x0021 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032 A[Catch:{ GeneralSecurityException -> 0x0021 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0037 A[Catch:{ GeneralSecurityException -> 0x0021 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.InputStream getDecryptedStream(org.apache.poi.poifs.filesystem.DirectoryNode r4, java.lang.String r5) throws java.io.IOException {
        /*
            java.lang.String r0 = "Package"
            boolean r1 = r4.hasEntry(r0)
            if (r1 == 0) goto L_0x000d
            org.apache.poi.poifs.filesystem.DocumentInputStream r4 = r4.createDocumentInputStream((java.lang.String) r0)
            return r4
        L_0x000d:
            org.apache.poi.poifs.crypt.EncryptionInfo r0 = new org.apache.poi.poifs.crypt.EncryptionInfo
            r0.<init>((org.apache.poi.poifs.filesystem.DirectoryNode) r4)
            org.apache.poi.poifs.crypt.Decryptor r0 = org.apache.poi.poifs.crypt.Decryptor.getInstance(r0)
            r1 = 1
            if (r5 == 0) goto L_0x0023
            boolean r2 = r0.verifyPassword(r5)     // Catch:{ GeneralSecurityException -> 0x0021 }
            if (r2 == 0) goto L_0x0023
            r2 = r1
            goto L_0x0024
        L_0x0021:
            r4 = move-exception
            goto L_0x0049
        L_0x0023:
            r2 = 0
        L_0x0024:
            if (r2 != 0) goto L_0x002f
            java.lang.String r3 = "VelvetSweatshop"
            boolean r3 = r0.verifyPassword(r3)     // Catch:{ GeneralSecurityException -> 0x0021 }
            if (r3 == 0) goto L_0x002f
            goto L_0x0030
        L_0x002f:
            r1 = r2
        L_0x0030:
            if (r1 == 0) goto L_0x0037
            java.io.InputStream r4 = r0.getDataStream((org.apache.poi.poifs.filesystem.DirectoryNode) r4)     // Catch:{ GeneralSecurityException -> 0x0021 }
            return r4
        L_0x0037:
            if (r5 == 0) goto L_0x0041
            org.apache.poi.EncryptedDocumentException r4 = new org.apache.poi.EncryptedDocumentException     // Catch:{ GeneralSecurityException -> 0x0021 }
            java.lang.String r5 = "Password incorrect"
            r4.<init>((java.lang.String) r5)     // Catch:{ GeneralSecurityException -> 0x0021 }
            throw r4     // Catch:{ GeneralSecurityException -> 0x0021 }
        L_0x0041:
            org.apache.poi.EncryptedDocumentException r4 = new org.apache.poi.EncryptedDocumentException     // Catch:{ GeneralSecurityException -> 0x0021 }
            java.lang.String r5 = "The supplied spreadsheet is protected, but no password was supplied"
            r4.<init>((java.lang.String) r5)     // Catch:{ GeneralSecurityException -> 0x0021 }
            throw r4     // Catch:{ GeneralSecurityException -> 0x0021 }
        L_0x0049:
            java.io.IOException r5 = new java.io.IOException
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.filesystem.DocumentFactoryHelper.getDecryptedStream(org.apache.poi.poifs.filesystem.DirectoryNode, java.lang.String):java.io.InputStream");
    }

    @Deprecated
    @Removal(version = "4.0")
    public static boolean hasOOXMLHeader(InputStream inputStream) throws IOException {
        return FileMagic.valueOf(inputStream) == FileMagic.OOXML;
    }
}
