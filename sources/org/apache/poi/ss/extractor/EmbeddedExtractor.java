package org.apache.poi.ss.extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.Ole10Native;
import org.apache.poi.poifs.filesystem.Ole10NativeException;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.ObjectData;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.ShapeContainer;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.StringUtil;

public class EmbeddedExtractor implements Iterable<EmbeddedExtractor> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CONTENT_TYPE_BYTES = "binary/octet-stream";
    private static final String CONTENT_TYPE_DOC = "application/msword";
    private static final String CONTENT_TYPE_PDF = "application/pdf";
    private static final String CONTENT_TYPE_XLS = "application/vnd.ms-excel";
    private static final int DEFAULT_MAX_RECORD_LENGTH = 1000000;
    private static final Logger LOG = LogManager.getLogger((Class<?>) EmbeddedExtractor.class);
    /* access modifiers changed from: private */
    public static int MAX_RECORD_LENGTH = 1000000;

    public boolean canExtract(DirectoryNode directoryNode) {
        return false;
    }

    public boolean canExtract(Picture picture) {
        return false;
    }

    /* access modifiers changed from: protected */
    public EmbeddedData extract(Picture picture) throws IOException {
        return null;
    }

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public Iterator<EmbeddedExtractor> iterator() {
        return Arrays.asList(new EmbeddedExtractor[]{new Ole10Extractor(), new PdfExtractor(), new BiffExtractor(), new OOXMLExtractor(), new FsExtractor()}).iterator();
    }

    public EmbeddedData extractOne(DirectoryNode directoryNode) throws IOException {
        Iterator<EmbeddedExtractor> it = iterator();
        while (it.hasNext()) {
            EmbeddedExtractor next = it.next();
            if (next.canExtract(directoryNode)) {
                return next.extract(directoryNode);
            }
        }
        return null;
    }

    public EmbeddedData extractOne(Picture picture) throws IOException {
        Iterator<EmbeddedExtractor> it = iterator();
        while (it.hasNext()) {
            EmbeddedExtractor next = it.next();
            if (next.canExtract(picture)) {
                return next.extract(picture);
            }
        }
        return null;
    }

    public List<EmbeddedData> extractAll(Sheet sheet) throws IOException {
        Drawing<?> drawingPatriarch = sheet.getDrawingPatriarch();
        if (drawingPatriarch == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        extractAll(drawingPatriarch, arrayList);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void extractAll(ShapeContainer<?> shapeContainer, List<EmbeddedData> list) throws IOException {
        EmbeddedData embeddedData;
        Iterator it = shapeContainer.iterator();
        while (it.hasNext()) {
            Shape shape = (Shape) it.next();
            EmbeddedData embeddedData2 = null;
            if (shape instanceof ObjectData) {
                ObjectData objectData = (ObjectData) shape;
                try {
                    if (objectData.hasDirectoryEntry()) {
                        embeddedData = extractOne((DirectoryNode) objectData.getDirectory());
                    } else {
                        embeddedData = new EmbeddedData(objectData.getFileName(), objectData.getObjectData(), objectData.getContentType());
                    }
                    embeddedData2 = embeddedData;
                } catch (Exception e) {
                    LOG.atWarn().withThrowable(e).log("Entry not found / readable - ignoring OLE embedding");
                }
            } else if (shape instanceof Picture) {
                embeddedData2 = extractOne((Picture) shape);
            } else if (shape instanceof ShapeContainer) {
                extractAll((ShapeContainer) shape, list);
            }
            if (embeddedData2 != null) {
                embeddedData2.setShape(shape);
                String filename = embeddedData2.getFilename();
                String substring = (filename == null || filename.lastIndexOf(46) == -1) ? ".bin" : filename.substring(filename.lastIndexOf(46));
                if ((filename == null || filename.isEmpty() || filename.startsWith("MBD") || filename.startsWith("Root Entry")) && (filename = shape.getShapeName()) != null) {
                    filename = filename + substring;
                }
                if (filename == null || filename.isEmpty()) {
                    filename = "picture_" + list.size() + substring;
                }
                embeddedData2.setFilename(filename.trim());
                list.add(embeddedData2);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r5.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0040, code lost:
        r5.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0043, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.ss.extractor.EmbeddedData extract(org.apache.poi.poifs.filesystem.DirectoryNode r5) throws java.io.IOException {
        /*
            r4 = this;
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r4 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r0 = 20000(0x4e20, float:2.8026E-41)
            r4.<init>(r0)
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch:{ all -> 0x0038 }
            r0.<init>()     // Catch:{ all -> 0x0038 }
            org.apache.poi.poifs.filesystem.DirectoryNode r1 = r0.getRoot()     // Catch:{ all -> 0x002c }
            copyNodes(r5, r1)     // Catch:{ all -> 0x002c }
            r0.writeFilesystem(r4)     // Catch:{ all -> 0x002c }
            org.apache.poi.ss.extractor.EmbeddedData r1 = new org.apache.poi.ss.extractor.EmbeddedData     // Catch:{ all -> 0x002c }
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x002c }
            byte[] r2 = r4.toByteArray()     // Catch:{ all -> 0x002c }
            java.lang.String r3 = "binary/octet-stream"
            r1.<init>(r5, r2, r3)     // Catch:{ all -> 0x002c }
            r0.close()     // Catch:{ all -> 0x0038 }
            r4.close()
            return r1
        L_0x002c:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x002e }
        L_0x002e:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r0 = move-exception
            r5.addSuppressed(r0)     // Catch:{ all -> 0x0038 }
        L_0x0037:
            throw r1     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x003a }
        L_0x003a:
            r0 = move-exception
            r4.close()     // Catch:{ all -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r4 = move-exception
            r5.addSuppressed(r4)
        L_0x0043:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.extractor.EmbeddedExtractor.extract(org.apache.poi.poifs.filesystem.DirectoryNode):org.apache.poi.ss.extractor.EmbeddedData");
    }

    public static class Ole10Extractor extends EmbeddedExtractor {
        public boolean canExtract(DirectoryNode directoryNode) {
            return ClassIDPredefined.lookup(directoryNode.getStorageClsid()) == ClassIDPredefined.OLE_V1_PACKAGE;
        }

        public EmbeddedData extract(DirectoryNode directoryNode) throws IOException {
            try {
                Ole10Native createFromEmbeddedOleObject = Ole10Native.createFromEmbeddedOleObject(directoryNode);
                return new EmbeddedData(createFromEmbeddedOleObject.getFileName(), createFromEmbeddedOleObject.getDataBuffer(), EmbeddedExtractor.CONTENT_TYPE_BYTES);
            } catch (Ole10NativeException e) {
                throw new IOException(e);
            }
        }
    }

    static class PdfExtractor extends EmbeddedExtractor {
        PdfExtractor() {
        }

        public boolean canExtract(DirectoryNode directoryNode) {
            return ClassIDPredefined.PDF.equals(directoryNode.getStorageClsid()) || directoryNode.hasEntry("CONTENTS");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x003b, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
            if (r0 != null) goto L_0x003e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r5.addSuppressed(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0049, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            r4.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x004f, code lost:
            r5.addSuppressed(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0052, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.poi.ss.extractor.EmbeddedData extract(org.apache.poi.poifs.filesystem.DirectoryNode r5) throws java.io.IOException {
            /*
                r4 = this;
                org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r4 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
                r4.<init>()
                java.lang.String r0 = "CONTENTS"
                org.apache.poi.poifs.filesystem.DocumentInputStream r0 = r5.createDocumentInputStream((java.lang.String) r0)     // Catch:{ all -> 0x0047 }
                org.apache.poi.util.IOUtils.copy((java.io.InputStream) r0, (java.io.OutputStream) r4)     // Catch:{ all -> 0x0039 }
                org.apache.poi.ss.extractor.EmbeddedData r1 = new org.apache.poi.ss.extractor.EmbeddedData     // Catch:{ all -> 0x0039 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0039 }
                r2.<init>()     // Catch:{ all -> 0x0039 }
                java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x0039 }
                java.lang.StringBuilder r5 = r2.append(r5)     // Catch:{ all -> 0x0039 }
                java.lang.String r2 = ".pdf"
                java.lang.StringBuilder r5 = r5.append(r2)     // Catch:{ all -> 0x0039 }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0039 }
                byte[] r2 = r4.toByteArray()     // Catch:{ all -> 0x0039 }
                java.lang.String r3 = "application/pdf"
                r1.<init>(r5, r2, r3)     // Catch:{ all -> 0x0039 }
                if (r0 == 0) goto L_0x0035
                r0.close()     // Catch:{ all -> 0x0047 }
            L_0x0035:
                r4.close()
                return r1
            L_0x0039:
                r5 = move-exception
                throw r5     // Catch:{ all -> 0x003b }
            L_0x003b:
                r1 = move-exception
                if (r0 == 0) goto L_0x0046
                r0.close()     // Catch:{ all -> 0x0042 }
                goto L_0x0046
            L_0x0042:
                r0 = move-exception
                r5.addSuppressed(r0)     // Catch:{ all -> 0x0047 }
            L_0x0046:
                throw r1     // Catch:{ all -> 0x0047 }
            L_0x0047:
                r5 = move-exception
                throw r5     // Catch:{ all -> 0x0049 }
            L_0x0049:
                r0 = move-exception
                r4.close()     // Catch:{ all -> 0x004e }
                goto L_0x0052
            L_0x004e:
                r4 = move-exception
                r5.addSuppressed(r4)
            L_0x0052:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.extractor.EmbeddedExtractor.PdfExtractor.extract(org.apache.poi.poifs.filesystem.DirectoryNode):org.apache.poi.ss.extractor.EmbeddedData");
        }

        public boolean canExtract(Picture picture) {
            PictureData pictureData = picture.getPictureData();
            return pictureData != null && pictureData.getPictureType() == 2;
        }

        /* access modifiers changed from: protected */
        public EmbeddedData extract(Picture picture) throws IOException {
            byte[] data;
            int access$000;
            int access$0002;
            PictureData pictureData = picture.getPictureData();
            if (pictureData == null || pictureData.getPictureType() != 2 || (access$000 = EmbeddedExtractor.indexOf(data, 0, "%PDF-".getBytes(LocaleUtil.CHARSET_1252))) == -1 || (access$0002 = EmbeddedExtractor.indexOf(data, access$000, "%%EOF".getBytes(LocaleUtil.CHARSET_1252))) == -1) {
                return null;
            }
            byte[] safelyClone = IOUtils.safelyClone((data = pictureData.getData()), access$000, (access$0002 - access$000) + 6, EmbeddedExtractor.MAX_RECORD_LENGTH);
            String trim = picture.getShapeName().trim();
            if (!StringUtil.endsWithIgnoreCase(trim, ".pdf")) {
                trim = trim + ".pdf";
            }
            return new EmbeddedData(trim, safelyClone, EmbeddedExtractor.CONTENT_TYPE_PDF);
        }
    }

    static class OOXMLExtractor extends EmbeddedExtractor {
        OOXMLExtractor() {
        }

        public boolean canExtract(DirectoryNode directoryNode) {
            return directoryNode.hasEntry("package");
        }

        public EmbeddedData extract(DirectoryNode directoryNode) throws IOException {
            String str;
            String str2;
            ClassIDPredefined lookup = ClassIDPredefined.lookup(directoryNode.getStorageClsid());
            if (lookup != null) {
                str2 = lookup.getContentType();
                str = lookup.getFileExtension();
            } else {
                str2 = null;
                str = null;
            }
            if (str2 == null || str == null) {
                str2 = "application/zip";
                str = ".zip";
            }
            DocumentInputStream createDocumentInputStream = directoryNode.createDocumentInputStream("package");
            byte[] byteArray = IOUtils.toByteArray(createDocumentInputStream);
            createDocumentInputStream.close();
            return new EmbeddedData(directoryNode.getName() + str, byteArray, str2);
        }
    }

    static class BiffExtractor extends EmbeddedExtractor {
        BiffExtractor() {
        }

        public boolean canExtract(DirectoryNode directoryNode) {
            return canExtractExcel(directoryNode) || canExtractWord(directoryNode);
        }

        /* access modifiers changed from: protected */
        public boolean canExtractExcel(DirectoryNode directoryNode) {
            ClassIDPredefined lookup = ClassIDPredefined.lookup(directoryNode.getStorageClsid());
            return ClassIDPredefined.EXCEL_V7 == lookup || ClassIDPredefined.EXCEL_V8 == lookup || directoryNode.hasEntry("Workbook");
        }

        /* access modifiers changed from: protected */
        public boolean canExtractWord(DirectoryNode directoryNode) {
            ClassIDPredefined lookup = ClassIDPredefined.lookup(directoryNode.getStorageClsid());
            return ClassIDPredefined.WORD_V7 == lookup || ClassIDPredefined.WORD_V8 == lookup || directoryNode.hasEntry("WordDocument");
        }

        public EmbeddedData extract(DirectoryNode directoryNode) throws IOException {
            EmbeddedData extract = EmbeddedExtractor.super.extract(directoryNode);
            if (canExtractExcel(directoryNode)) {
                extract.setFilename(directoryNode.getName() + ".xls");
                extract.setContentType(EmbeddedExtractor.CONTENT_TYPE_XLS);
            } else if (canExtractWord(directoryNode)) {
                extract.setFilename(directoryNode.getName() + ".doc");
                extract.setContentType(EmbeddedExtractor.CONTENT_TYPE_DOC);
            }
            return extract;
        }
    }

    static class FsExtractor extends EmbeddedExtractor {
        public boolean canExtract(DirectoryNode directoryNode) {
            return true;
        }

        FsExtractor() {
        }

        public EmbeddedData extract(DirectoryNode directoryNode) throws IOException {
            EmbeddedData extract = EmbeddedExtractor.super.extract(directoryNode);
            extract.setFilename(directoryNode.getName() + ".ole");
            return extract;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        if (r2 != null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0045, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        r4.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void copyNodes(org.apache.poi.poifs.filesystem.DirectoryNode r4, org.apache.poi.poifs.filesystem.DirectoryNode r5) throws java.io.IOException {
        /*
            java.util.Iterator r0 = r4.iterator()
        L_0x0004:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x004a
            java.lang.Object r1 = r0.next()
            org.apache.poi.poifs.filesystem.Entry r1 = (org.apache.poi.poifs.filesystem.Entry) r1
            boolean r2 = r1 instanceof org.apache.poi.poifs.filesystem.DirectoryNode
            if (r2 == 0) goto L_0x002b
            org.apache.poi.poifs.filesystem.DirectoryNode r1 = (org.apache.poi.poifs.filesystem.DirectoryNode) r1
            java.lang.String r2 = r1.getName()
            org.apache.poi.poifs.filesystem.DirectoryEntry r2 = r5.createDirectory(r2)
            org.apache.poi.poifs.filesystem.DirectoryNode r2 = (org.apache.poi.poifs.filesystem.DirectoryNode) r2
            org.apache.poi.hpsf.ClassID r3 = r1.getStorageClsid()
            r2.setStorageClsid(r3)
            copyNodes(r1, r2)
            goto L_0x0004
        L_0x002b:
            org.apache.poi.poifs.filesystem.DocumentInputStream r2 = r4.createDocumentInputStream((org.apache.poi.poifs.filesystem.Entry) r1)
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x003c }
            r5.createDocument(r1, r2)     // Catch:{ all -> 0x003c }
            if (r2 == 0) goto L_0x0004
            r2.close()
            goto L_0x0004
        L_0x003c:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x003e }
        L_0x003e:
            r5 = move-exception
            if (r2 == 0) goto L_0x0049
            r2.close()     // Catch:{ all -> 0x0045 }
            goto L_0x0049
        L_0x0045:
            r0 = move-exception
            r4.addSuppressed(r0)
        L_0x0049:
            throw r5
        L_0x004a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.extractor.EmbeddedExtractor.copyNodes(org.apache.poi.poifs.filesystem.DirectoryNode, org.apache.poi.poifs.filesystem.DirectoryNode):void");
    }

    /* access modifiers changed from: private */
    public static int indexOf(byte[] bArr, int i, byte[] bArr2) {
        int[] computeFailure = computeFailure(bArr2);
        if (bArr.length == 0) {
            return -1;
        }
        int i2 = 0;
        while (i < bArr.length) {
            while (i2 > 0 && bArr2[i2] != bArr[i]) {
                i2 = computeFailure[i2 - 1];
            }
            if (bArr2[i2] == bArr[i]) {
                i2++;
            }
            if (i2 == bArr2.length) {
                return (i - bArr2.length) + 1;
            }
            i++;
        }
        return -1;
    }

    private static int[] computeFailure(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        int i = 0;
        int i2 = 1;
        while (i2 < bArr.length) {
            while (i > 0 && bArr[i] != bArr[i2]) {
                i = iArr[i - 1];
            }
            if (bArr[i] == bArr[i2]) {
                i++;
            }
            iArr[i2] = i;
            i2++;
        }
        return iArr;
    }
}
