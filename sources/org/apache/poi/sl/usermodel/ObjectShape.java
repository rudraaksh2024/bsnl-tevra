package org.apache.poi.sl.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface ObjectShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends Shape<S, P>, PlaceableShape<S, P> {
    String getFullName();

    ObjectData getObjectData();

    PictureData getPictureData();

    String getProgId();

    OutputStream updateObjectData(ObjectMetaData.Application application, ObjectMetaData objectMetaData) throws IOException;

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0076, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0077, code lost:
        if (r0 != null) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0081, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0084, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0089, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x008d, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00a0, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00a1, code lost:
        if (r9 != null) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00a7, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        r0.addSuppressed(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00ab, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00ae, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00b3, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00b4, code lost:
        r9.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00b7, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    java.io.InputStream readObjectData() throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r0 = r9.getProgId()
            if (r0 == 0) goto L_0x00b8
            org.apache.poi.sl.usermodel.ObjectMetaData$Application r0 = org.apache.poi.sl.usermodel.ObjectMetaData.Application.lookup(r0)
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r1 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r1.<init>()
            java.io.InputStream r9 = r9.readObjectDataRaw()     // Catch:{ all -> 0x00ac }
            java.io.InputStream r9 = org.apache.poi.poifs.filesystem.FileMagic.prepareToCheckMagic(r9)     // Catch:{ all -> 0x00ac }
            org.apache.poi.poifs.filesystem.FileMagic r2 = org.apache.poi.poifs.filesystem.FileMagic.valueOf((java.io.InputStream) r9)     // Catch:{ all -> 0x009e }
            org.apache.poi.poifs.filesystem.FileMagic r3 = org.apache.poi.poifs.filesystem.FileMagic.OLE2     // Catch:{ all -> 0x009e }
            if (r2 != r3) goto L_0x008e
            org.apache.poi.poifs.filesystem.POIFSFileSystem r2 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x009e }
            r2.<init>((java.io.InputStream) r9)     // Catch:{ all -> 0x009e }
            r3 = 5
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ all -> 0x0082 }
            r5 = 0
            if (r0 != 0) goto L_0x002c
            r0 = r5
            goto L_0x0034
        L_0x002c:
            org.apache.poi.sl.usermodel.ObjectMetaData r0 = r0.getMetaData()     // Catch:{ all -> 0x0082 }
            java.lang.String r0 = r0.getOleEntry()     // Catch:{ all -> 0x0082 }
        L_0x0034:
            r6 = 0
            r4[r6] = r0     // Catch:{ all -> 0x0082 }
            java.lang.String r0 = "Package"
            r7 = 1
            r4[r7] = r0     // Catch:{ all -> 0x0082 }
            java.lang.String r0 = "Contents"
            r7 = 2
            r4[r7] = r0     // Catch:{ all -> 0x0082 }
            java.lang.String r0 = "CONTENTS"
            r7 = 3
            r4[r7] = r0     // Catch:{ all -> 0x0082 }
            java.lang.String r0 = "CONTENTSV30"
            r7 = 4
            r4[r7] = r0     // Catch:{ all -> 0x0082 }
            org.apache.poi.poifs.filesystem.DirectoryNode r0 = r2.getRoot()     // Catch:{ all -> 0x0082 }
        L_0x004f:
            if (r6 >= r3) goto L_0x005e
            r7 = r4[r6]     // Catch:{ all -> 0x0082 }
            boolean r8 = r0.hasEntry(r7)     // Catch:{ all -> 0x0082 }
            if (r8 == 0) goto L_0x005b
            r5 = r7
            goto L_0x005e
        L_0x005b:
            int r6 = r6 + 1
            goto L_0x004f
        L_0x005e:
            if (r5 != 0) goto L_0x0064
            r2.writeFilesystem(r1)     // Catch:{ all -> 0x0082 }
            goto L_0x0070
        L_0x0064:
            org.apache.poi.poifs.filesystem.DocumentInputStream r0 = r2.createDocumentInputStream(r5)     // Catch:{ all -> 0x0082 }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r0, (java.io.OutputStream) r1)     // Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x0070
            r0.close()     // Catch:{ all -> 0x0082 }
        L_0x0070:
            r2.close()     // Catch:{ all -> 0x009e }
            goto L_0x0091
        L_0x0074:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0076 }
        L_0x0076:
            r4 = move-exception
            if (r0 == 0) goto L_0x0081
            r0.close()     // Catch:{ all -> 0x007d }
            goto L_0x0081
        L_0x007d:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ all -> 0x0082 }
        L_0x0081:
            throw r4     // Catch:{ all -> 0x0082 }
        L_0x0082:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0084 }
        L_0x0084:
            r3 = move-exception
            r2.close()     // Catch:{ all -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r2 = move-exception
            r0.addSuppressed(r2)     // Catch:{ all -> 0x009e }
        L_0x008d:
            throw r3     // Catch:{ all -> 0x009e }
        L_0x008e:
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r9, (java.io.OutputStream) r1)     // Catch:{ all -> 0x009e }
        L_0x0091:
            java.io.InputStream r0 = r1.toInputStream()     // Catch:{ all -> 0x009e }
            if (r9 == 0) goto L_0x009a
            r9.close()     // Catch:{ all -> 0x00ac }
        L_0x009a:
            r1.close()
            return r0
        L_0x009e:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00a0 }
        L_0x00a0:
            r2 = move-exception
            if (r9 == 0) goto L_0x00ab
            r9.close()     // Catch:{ all -> 0x00a7 }
            goto L_0x00ab
        L_0x00a7:
            r9 = move-exception
            r0.addSuppressed(r9)     // Catch:{ all -> 0x00ac }
        L_0x00ab:
            throw r2     // Catch:{ all -> 0x00ac }
        L_0x00ac:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x00ae }
        L_0x00ae:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x00b3 }
            goto L_0x00b7
        L_0x00b3:
            r1 = move-exception
            r9.addSuppressed(r1)
        L_0x00b7:
            throw r0
        L_0x00b8:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "Ole object hasn't been initialized or provided in the source xml. use updateObjectData() first or check the corresponding slideXXX.xml"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.usermodel.ObjectShape.readObjectData():java.io.InputStream");
    }

    InputStream readObjectDataRaw() throws IOException {
        return getObjectData().getInputStream();
    }
}
