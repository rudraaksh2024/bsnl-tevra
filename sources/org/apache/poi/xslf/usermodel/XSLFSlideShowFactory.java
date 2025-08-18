package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.sl.usermodel.SlideShowProvider;
import org.apache.poi.util.Internal;

@Internal
public class XSLFSlideShowFactory implements SlideShowProvider<XSLFShape, XSLFTextParagraph> {
    public boolean accepts(FileMagic fileMagic) {
        return fileMagic == FileMagic.OOXML;
    }

    public XMLSlideShow create() {
        return new XMLSlideShow();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
        if (r3 != null) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0022, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xslf.usermodel.XMLSlideShow create(org.apache.poi.poifs.filesystem.DirectoryNode r2, java.lang.String r3) throws java.io.IOException {
        /*
            r1 = this;
            java.io.InputStream r3 = org.apache.poi.poifs.filesystem.DocumentFactoryHelper.getDecryptedStream((org.apache.poi.poifs.filesystem.DirectoryNode) r2, (java.lang.String) r3)     // Catch:{ all -> 0x0023 }
            org.apache.poi.xslf.usermodel.XMLSlideShow r1 = r1.create((java.io.InputStream) r3)     // Catch:{ all -> 0x0015 }
            if (r3 == 0) goto L_0x000d
            r3.close()     // Catch:{ all -> 0x0023 }
        L_0x000d:
            org.apache.poi.poifs.filesystem.POIFSFileSystem r2 = r2.getFileSystem()
            r2.close()
            return r1
        L_0x0015:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0017 }
        L_0x0017:
            r0 = move-exception
            if (r3 == 0) goto L_0x0022
            r3.close()     // Catch:{ all -> 0x001e }
            goto L_0x0022
        L_0x001e:
            r3 = move-exception
            r1.addSuppressed(r3)     // Catch:{ all -> 0x0023 }
        L_0x0022:
            throw r0     // Catch:{ all -> 0x0023 }
        L_0x0023:
            r1 = move-exception
            org.apache.poi.poifs.filesystem.POIFSFileSystem r2 = r2.getFileSystem()
            r2.close()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideShowFactory.create(org.apache.poi.poifs.filesystem.DirectoryNode, java.lang.String):org.apache.poi.xslf.usermodel.XMLSlideShow");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        if (r3 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0033, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0036, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x003b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x003c, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x003f, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xslf.usermodel.XMLSlideShow create(java.io.InputStream r3, java.lang.String r4) throws java.io.IOException {
        /*
            r2 = this;
            java.io.InputStream r3 = org.apache.poi.poifs.filesystem.FileMagic.prepareToCheckMagic(r3)
            org.apache.poi.poifs.filesystem.FileMagic r0 = org.apache.poi.poifs.filesystem.FileMagic.valueOf((java.io.InputStream) r3)
            org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.OLE2
            if (r0 != r1) goto L_0x0040
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            r0.<init>((java.io.InputStream) r3)
            org.apache.poi.poifs.filesystem.DirectoryNode r3 = r0.getRoot()     // Catch:{ all -> 0x0034 }
            java.io.InputStream r3 = org.apache.poi.poifs.filesystem.DocumentFactoryHelper.getDecryptedStream((org.apache.poi.poifs.filesystem.DirectoryNode) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0034 }
            org.apache.poi.xslf.usermodel.XMLSlideShow r2 = r2.create((java.io.InputStream) r3)     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x0022
            r3.close()     // Catch:{ all -> 0x0034 }
        L_0x0022:
            r0.close()
            return r2
        L_0x0026:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r4 = move-exception
            if (r3 == 0) goto L_0x0033
            r3.close()     // Catch:{ all -> 0x002f }
            goto L_0x0033
        L_0x002f:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch:{ all -> 0x0034 }
        L_0x0033:
            throw r4     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x003f:
            throw r3
        L_0x0040:
            org.apache.poi.poifs.filesystem.FileMagic r4 = org.apache.poi.poifs.filesystem.FileMagic.OOXML
            if (r0 != r4) goto L_0x0049
            org.apache.poi.xslf.usermodel.XMLSlideShow r2 = r2.create((java.io.InputStream) r3)
            return r2
        L_0x0049:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideShowFactory.create(java.io.InputStream, java.lang.String):org.apache.poi.xslf.usermodel.XMLSlideShow");
    }

    public XMLSlideShow create(InputStream inputStream) throws IOException {
        try {
            return createSlideShow(OPCPackage.open(inputStream));
        } catch (InvalidFormatException e) {
            throw new IOException(e);
        }
    }

    public static XMLSlideShow createSlideShow(OPCPackage oPCPackage) throws IOException {
        try {
            return new XMLSlideShow(oPCPackage);
        } catch (RuntimeException e) {
            oPCPackage.revert();
            throw e;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        if (r3 != null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0033, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0038, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0039, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x003c, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xslf.usermodel.XMLSlideShow create(java.io.File r3, java.lang.String r4, boolean r5) throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.poifs.filesystem.FileMagic r0 = org.apache.poi.poifs.filesystem.FileMagic.valueOf((java.io.File) r3)
            org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.OLE2
            if (r0 != r1) goto L_0x003d
            org.apache.poi.poifs.filesystem.POIFSFileSystem r5 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            r0 = 1
            r5.<init>((java.io.File) r3, (boolean) r0)
            org.apache.poi.poifs.filesystem.DirectoryNode r3 = r5.getRoot()     // Catch:{ all -> 0x0031 }
            java.io.InputStream r3 = org.apache.poi.poifs.filesystem.DocumentFactoryHelper.getDecryptedStream((org.apache.poi.poifs.filesystem.DirectoryNode) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0031 }
            org.apache.poi.xslf.usermodel.XMLSlideShow r2 = r2.create((java.io.InputStream) r3)     // Catch:{ all -> 0x0023 }
            if (r3 == 0) goto L_0x001f
            r3.close()     // Catch:{ all -> 0x0031 }
        L_0x001f:
            r5.close()
            return r2
        L_0x0023:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r4 = move-exception
            if (r3 == 0) goto L_0x0030
            r3.close()     // Catch:{ all -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r3 = move-exception
            r2.addSuppressed(r3)     // Catch:{ all -> 0x0031 }
        L_0x0030:
            throw r4     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r3 = move-exception
            r5.close()     // Catch:{ all -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x003c:
            throw r3
        L_0x003d:
            if (r5 == 0) goto L_0x0042
            org.apache.poi.openxml4j.opc.PackageAccess r2 = org.apache.poi.openxml4j.opc.PackageAccess.READ     // Catch:{ InvalidFormatException -> 0x004d }
            goto L_0x0044
        L_0x0042:
            org.apache.poi.openxml4j.opc.PackageAccess r2 = org.apache.poi.openxml4j.opc.PackageAccess.READ_WRITE     // Catch:{ InvalidFormatException -> 0x004d }
        L_0x0044:
            org.apache.poi.openxml4j.opc.OPCPackage r2 = org.apache.poi.openxml4j.opc.OPCPackage.open((java.io.File) r3, (org.apache.poi.openxml4j.opc.PackageAccess) r2)     // Catch:{ InvalidFormatException -> 0x004d }
            org.apache.poi.xslf.usermodel.XMLSlideShow r2 = createSlideShow(r2)     // Catch:{ InvalidFormatException -> 0x004d }
            return r2
        L_0x004d:
            r2 = move-exception
            java.io.IOException r3 = new java.io.IOException
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSlideShowFactory.create(java.io.File, java.lang.String, boolean):org.apache.poi.xslf.usermodel.XMLSlideShow");
    }
}
