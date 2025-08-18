package org.apache.poi.xssf.eventusermodel;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.xssf.binary.XSSFBParseException;
import org.apache.poi.xssf.binary.XSSFBParser;
import org.apache.poi.xssf.binary.XSSFBRecordType;
import org.apache.poi.xssf.binary.XSSFBUtils;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.usermodel.XSSFRelation;

public class XSSFBReader extends XSSFReader {
    /* access modifiers changed from: private */
    public static final Logger LOGGER = LogManager.getLogger((Class<?>) XSSFBReader.class);
    /* access modifiers changed from: private */
    public static final Set<String> WORKSHEET_RELS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{XSSFRelation.WORKSHEET.getRelation(), XSSFRelation.CHARTSHEET.getRelation(), XSSFRelation.MACRO_SHEET_BIN.getRelation(), XSSFRelation.INTL_MACRO_SHEET_BIN.getRelation(), XSSFRelation.DIALOG_SHEET_BIN.getRelation()})));

    public XSSFBReader(OPCPackage oPCPackage) throws IOException, OpenXML4JException {
        super(oPCPackage);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        if (r2 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAbsPathMetadata() throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.PackagePart r2 = r2.workbookPart
            java.io.InputStream r2 = r2.getInputStream()
            org.apache.poi.xssf.eventusermodel.XSSFBReader$PathExtractor r0 = new org.apache.poi.xssf.eventusermodel.XSSFBReader$PathExtractor     // Catch:{ all -> 0x0018 }
            r0.<init>(r2)     // Catch:{ all -> 0x0018 }
            r0.parse()     // Catch:{ all -> 0x0018 }
            java.lang.String r0 = r0.getPath()     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x0017
            r2.close()
        L_0x0017:
            return r0
        L_0x0018:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x001a }
        L_0x001a:
            r1 = move-exception
            if (r2 == 0) goto L_0x0025
            r2.close()     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x0025:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.eventusermodel.XSSFBReader.getAbsPathMetadata():java.lang.String");
    }

    public Iterator<InputStream> getSheetsData() throws IOException, InvalidFormatException {
        return new SheetIterator(this.workbookPart);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r2 != null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0034, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xssf.binary.XSSFBStylesTable getXSSFBStylesTable() throws java.io.IOException {
        /*
            r2 = this;
            org.apache.poi.openxml4j.opc.OPCPackage r2 = r2.pkg
            org.apache.poi.xssf.binary.XSSFBRelation r0 = org.apache.poi.xssf.binary.XSSFBRelation.STYLES_BINARY
            java.lang.String r0 = r0.getContentType()
            java.util.ArrayList r2 = r2.getPartsByContentType(r0)
            boolean r0 = r2.isEmpty()
            if (r0 == 0) goto L_0x0014
            r2 = 0
            return r2
        L_0x0014:
            r0 = 0
            java.lang.Object r2 = r2.get(r0)
            org.apache.poi.openxml4j.opc.PackagePart r2 = (org.apache.poi.openxml4j.opc.PackagePart) r2
            java.io.InputStream r2 = r2.getInputStream()
            org.apache.poi.xssf.binary.XSSFBStylesTable r0 = new org.apache.poi.xssf.binary.XSSFBStylesTable     // Catch:{ all -> 0x002a }
            r0.<init>(r2)     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x0029
            r2.close()
        L_0x0029:
            return r0
        L_0x002a:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002c }
        L_0x002c:
            r1 = move-exception
            if (r2 == 0) goto L_0x0037
            r2.close()     // Catch:{ all -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x0037:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.eventusermodel.XSSFBReader.getXSSFBStylesTable():org.apache.poi.xssf.binary.XSSFBStylesTable");
    }

    public static class SheetIterator extends XSSFReader.SheetIterator {
        private SheetIterator(PackagePart packagePart) throws IOException {
            super(packagePart);
        }

        /* access modifiers changed from: protected */
        public Set<String> getSheetRelationships() {
            return XSSFBReader.WORKSHEET_RELS;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
            if (r1 != null) goto L_0x0020;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
            r1.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
            r2.addSuppressed(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0028, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
            r0 = move-exception;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.Iterator<org.apache.poi.xssf.eventusermodel.XSSFReader.XSSFSheetRef> createSheetIteratorFromWB(org.apache.poi.openxml4j.opc.PackagePart r2) throws java.io.IOException {
            /*
                r1 = this;
                java.io.InputStream r1 = r2.getInputStream()
                org.apache.poi.xssf.eventusermodel.XSSFBReader$SheetRefLoader r2 = new org.apache.poi.xssf.eventusermodel.XSSFBReader$SheetRefLoader     // Catch:{ all -> 0x001b }
                r0 = 0
                r2.<init>(r1)     // Catch:{ all -> 0x001b }
                r2.parse()     // Catch:{ all -> 0x001b }
                java.util.List r2 = r2.getSheets()     // Catch:{ all -> 0x001b }
                java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x001b }
                if (r1 == 0) goto L_0x001a
                r1.close()
            L_0x001a:
                return r2
            L_0x001b:
                r2 = move-exception
                throw r2     // Catch:{ all -> 0x001d }
            L_0x001d:
                r0 = move-exception
                if (r1 == 0) goto L_0x0028
                r1.close()     // Catch:{ all -> 0x0024 }
                goto L_0x0028
            L_0x0024:
                r1 = move-exception
                r2.addSuppressed(r1)
            L_0x0028:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.eventusermodel.XSSFBReader.SheetIterator.createSheetIteratorFromWB(org.apache.poi.openxml4j.opc.PackagePart):java.util.Iterator");
        }

        public CommentsTable getSheetComments() {
            throw new IllegalArgumentException("Please use getXSSFBSheetComments");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
            if (r3 != null) goto L_0x0047;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r3.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x004f, code lost:
            throw r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.poi.xssf.binary.XSSFBCommentsTable getXSSFBSheetComments() {
            /*
                r3 = this;
                org.apache.poi.openxml4j.opc.PackagePart r3 = r3.getSheetPart()
                r0 = 0
                org.apache.poi.xssf.usermodel.XSSFRelation r1 = org.apache.poi.xssf.usermodel.XSSFRelation.SHEET_COMMENTS     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                java.lang.String r1 = r1.getRelation()     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                org.apache.poi.openxml4j.opc.PackageRelationshipCollection r1 = r3.getRelationshipsByType(r1)     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                boolean r2 = r1.isEmpty()     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                if (r2 != 0) goto L_0x0050
                r2 = 0
                org.apache.poi.openxml4j.opc.PackageRelationship r1 = r1.getRelationship(r2)     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                if (r1 == 0) goto L_0x0050
                java.net.URI r2 = r1.getTargetURI()     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                if (r2 != 0) goto L_0x0023
                goto L_0x0050
            L_0x0023:
                java.net.URI r1 = r1.getTargetURI()     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                org.apache.poi.openxml4j.opc.PackagePartName r1 = org.apache.poi.openxml4j.opc.PackagingURIHelper.createPartName((java.net.URI) r1)     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                org.apache.poi.openxml4j.opc.OPCPackage r3 = r3.getPackage()     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                org.apache.poi.openxml4j.opc.PackagePart r3 = r3.getPart((org.apache.poi.openxml4j.opc.PackagePartName) r1)     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                java.io.InputStream r3 = r3.getInputStream()     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
                org.apache.poi.xssf.binary.XSSFBCommentsTable r1 = new org.apache.poi.xssf.binary.XSSFBCommentsTable     // Catch:{ all -> 0x0042 }
                r1.<init>(r3)     // Catch:{ all -> 0x0042 }
                if (r3 == 0) goto L_0x0041
                r3.close()     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
            L_0x0041:
                return r1
            L_0x0042:
                r1 = move-exception
                throw r1     // Catch:{ all -> 0x0044 }
            L_0x0044:
                r2 = move-exception
                if (r3 == 0) goto L_0x004f
                r3.close()     // Catch:{ all -> 0x004b }
                goto L_0x004f
            L_0x004b:
                r3 = move-exception
                r1.addSuppressed(r3)     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
            L_0x004f:
                throw r2     // Catch:{ IOException | InvalidFormatException -> 0x0050 }
            L_0x0050:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.eventusermodel.XSSFBReader.SheetIterator.getXSSFBSheetComments():org.apache.poi.xssf.binary.XSSFBCommentsTable");
        }
    }

    private static class PathExtractor extends XSSFBParser {
        private static SparseBitSet RECORDS;
        private String path;

        static {
            SparseBitSet sparseBitSet = new SparseBitSet();
            RECORDS = sparseBitSet;
            sparseBitSet.set(XSSFBRecordType.BrtAbsPath15.getId());
        }

        public PathExtractor(InputStream inputStream) {
            super(inputStream, RECORDS);
        }

        public void handleRecord(int i, byte[] bArr) throws XSSFBParseException {
            if (i == XSSFBRecordType.BrtAbsPath15.getId()) {
                StringBuilder sb = new StringBuilder();
                XSSFBUtils.readXLWideString(bArr, 0, sb);
                this.path = sb.toString();
            }
        }

        /* access modifiers changed from: package-private */
        public String getPath() {
            return this.path;
        }
    }

    private static class SheetRefLoader extends XSSFBParser {
        List<XSSFReader.XSSFSheetRef> sheets;

        private SheetRefLoader(InputStream inputStream) {
            super(inputStream);
            this.sheets = new LinkedList();
        }

        public void handleRecord(int i, byte[] bArr) throws XSSFBParseException {
            if (i == XSSFBRecordType.BrtBundleSh.getId()) {
                addWorksheet(bArr);
            }
        }

        private void addWorksheet(byte[] bArr) {
            try {
                tryToAddWorksheet(bArr);
            } catch (XSSFBParseException e) {
                if (tryOldFormat(bArr)) {
                    XSSFBReader.LOGGER.atWarn().log("This file was written with a beta version of Excel. POI will try to parse the file as a regular xlsb.");
                    return;
                }
                throw e;
            }
        }

        private void tryToAddWorksheet(byte[] bArr) throws XSSFBParseException {
            LittleEndian.getUInt(bArr, 0);
            long uInt = LittleEndian.getUInt(bArr, 4);
            if (uInt < 1 || uInt > 65535) {
                throw new XSSFBParseException("table id out of range: " + uInt);
            }
            StringBuilder sb = new StringBuilder();
            int readXLWideString = 8 + XSSFBUtils.readXLWideString(bArr, 8, sb);
            String sb2 = sb.toString();
            sb.setLength(0);
            XSSFBUtils.readXLWideString(bArr, readXLWideString, sb);
            String sb3 = sb.toString();
            if (sb2.trim().length() > 0) {
                this.sheets.add(new XSSFReader.XSSFSheetRef(sb2, sb3));
            }
        }

        private boolean tryOldFormat(byte[] bArr) throws XSSFBParseException {
            long uInt = LittleEndian.getUInt(bArr, 8);
            if (uInt < 1 || uInt > 65535) {
                throw new XSSFBParseException("table id out of range: " + uInt);
            }
            StringBuilder sb = new StringBuilder();
            int readXLWideString = 12 + XSSFBUtils.readXLWideString(bArr, 12, sb);
            String sb2 = sb.toString();
            sb.setLength(0);
            int readXLWideString2 = readXLWideString + XSSFBUtils.readXLWideString(bArr, readXLWideString, sb);
            String sb3 = sb.toString();
            if (sb2.trim().length() > 0) {
                this.sheets.add(new XSSFReader.XSSFSheetRef(sb2, sb3));
            }
            if (readXLWideString2 == bArr.length) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public List<XSSFReader.XSSFSheetRef> getSheets() {
            return this.sheets;
        }
    }
}
