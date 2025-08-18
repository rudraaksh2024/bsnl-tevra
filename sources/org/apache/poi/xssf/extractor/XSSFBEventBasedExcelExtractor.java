package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.binary.XSSFBCommentsTable;
import org.apache.poi.xssf.binary.XSSFBSheetHandler;
import org.apache.poi.xssf.binary.XSSFBStylesTable;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlException;

public class XSSFBEventBasedExcelExtractor extends XSSFEventBasedExcelExtractor {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) XSSFBEventBasedExcelExtractor.class);
    public static final List<XSSFRelation> SUPPORTED_TYPES = Collections.singletonList(XSSFRelation.XLSB_BINARY_WORKBOOK);
    private boolean handleHyperlinksInCells;

    public XSSFBEventBasedExcelExtractor(String str) throws XmlException, OpenXML4JException, IOException {
        super(str);
    }

    public XSSFBEventBasedExcelExtractor(OPCPackage oPCPackage) throws XmlException, OpenXML4JException, IOException {
        super(oPCPackage);
    }

    public void setHandleHyperlinksInCells(boolean z) {
        this.handleHyperlinksInCells = z;
    }

    public void setFormulasNotResults(boolean z) {
        throw new IllegalArgumentException("Not currently supported");
    }

    public void processSheet(XSSFSheetXMLHandler.SheetContentsHandler sheetContentsHandler, XSSFBStylesTable xSSFBStylesTable, XSSFBCommentsTable xSSFBCommentsTable, SharedStrings sharedStrings, InputStream inputStream) throws IOException {
        DataFormatter dataFormatter;
        if (getLocale() == null) {
            dataFormatter = new DataFormatter();
        } else {
            dataFormatter = new DataFormatter(getLocale());
        }
        new XSSFBSheetHandler(inputStream, xSSFBStylesTable, xSSFBCommentsTable, sharedStrings, sheetContentsHandler, dataFormatter, getFormulasNotResults()).parse();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0094, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0095, code lost:
        if (r11 != null) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getText() {
        /*
            r12 = this;
            org.apache.poi.xssf.binary.XSSFBSharedStringsTable r6 = new org.apache.poi.xssf.binary.XSSFBSharedStringsTable     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            org.apache.poi.openxml4j.opc.OPCPackage r0 = r12.getPackage()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            r6.<init>((org.apache.poi.openxml4j.opc.OPCPackage) r0)     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            org.apache.poi.xssf.eventusermodel.XSSFBReader r0 = new org.apache.poi.xssf.eventusermodel.XSSFBReader     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            org.apache.poi.openxml4j.opc.OPCPackage r1 = r12.getPackage()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            r0.<init>(r1)     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            org.apache.poi.xssf.binary.XSSFBStylesTable r7 = r0.getXSSFBStylesTable()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            java.util.Iterator r0 = r0.getSheetsData()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            r8 = r0
            org.apache.poi.xssf.eventusermodel.XSSFBReader$SheetIterator r8 = (org.apache.poi.xssf.eventusermodel.XSSFBReader.SheetIterator) r8     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            r0 = 64
            r9.<init>(r0)     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor$SheetTextExtractor r10 = new org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor$SheetTextExtractor     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            r10.<init>()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
        L_0x0029:
            boolean r0 = r8.hasNext()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            if (r0 == 0) goto L_0x00a0
            java.io.InputStream r11 = r8.next()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            boolean r0 = r12.getIncludeSheetNames()     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = r8.getSheetName()     // Catch:{ all -> 0x0092 }
            r9.append(r0)     // Catch:{ all -> 0x0092 }
            r0 = 10
            r9.append(r0)     // Catch:{ all -> 0x0092 }
        L_0x0045:
            boolean r0 = r12.handleHyperlinksInCells     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x0052
            org.apache.poi.xssf.binary.XSSFBHyperlinksTable r0 = new org.apache.poi.xssf.binary.XSSFBHyperlinksTable     // Catch:{ all -> 0x0092 }
            org.apache.poi.openxml4j.opc.PackagePart r1 = r8.getSheetPart()     // Catch:{ all -> 0x0092 }
            r0.<init>(r1)     // Catch:{ all -> 0x0092 }
        L_0x0052:
            boolean r0 = r12.getIncludeCellComments()     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x005d
            org.apache.poi.xssf.binary.XSSFBCommentsTable r0 = r8.getXSSFBSheetComments()     // Catch:{ all -> 0x0092 }
            goto L_0x005e
        L_0x005d:
            r0 = 0
        L_0x005e:
            r3 = r0
            r0 = r12
            r1 = r10
            r2 = r7
            r4 = r6
            r5 = r11
            r0.processSheet(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x0092 }
            boolean r0 = r12.getIncludeHeadersFooters()     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x0070
            r10.appendHeaderText(r9)     // Catch:{ all -> 0x0092 }
        L_0x0070:
            r10.appendCellText(r9)     // Catch:{ all -> 0x0092 }
            boolean r0 = r12.getIncludeTextBoxes()     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x0080
            java.util.List r0 = r8.getShapes()     // Catch:{ all -> 0x0092 }
            r12.processShapes(r0, r9)     // Catch:{ all -> 0x0092 }
        L_0x0080:
            boolean r0 = r12.getIncludeHeadersFooters()     // Catch:{ all -> 0x0092 }
            if (r0 == 0) goto L_0x0089
            r10.appendFooterText(r9)     // Catch:{ all -> 0x0092 }
        L_0x0089:
            r10.reset()     // Catch:{ all -> 0x0092 }
            if (r11 == 0) goto L_0x0029
            r11.close()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            goto L_0x0029
        L_0x0092:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x0094 }
        L_0x0094:
            r0 = move-exception
            if (r11 == 0) goto L_0x009f
            r11.close()     // Catch:{ all -> 0x009b }
            goto L_0x009f
        L_0x009b:
            r1 = move-exception
            r12.addSuppressed(r1)     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
        L_0x009f:
            throw r0     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
        L_0x00a0:
            java.lang.String r12 = r9.toString()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x00a5 }
            return r12
        L_0x00a5:
            r12 = move-exception
            org.apache.logging.log4j.Logger r0 = LOGGER
            org.apache.logging.log4j.LogBuilder r0 = r0.atWarn()
            org.apache.logging.log4j.LogBuilder r12 = r0.withThrowable(r12)
            java.lang.String r0 = "Failed to load text"
            r12.log((java.lang.String) r0)
            java.lang.String r12 = ""
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.extractor.XSSFBEventBasedExcelExtractor.getText():java.lang.String");
    }
}
