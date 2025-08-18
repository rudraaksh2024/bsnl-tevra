package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSimpleShape;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XSSFEventBasedExcelExtractor implements POIXMLTextExtractor, ExcelExtractor {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) XSSFEventBasedExcelExtractor.class);
    protected boolean concatenatePhoneticRuns;
    protected final OPCPackage container;
    private boolean doCloseFilesystem;
    protected boolean formulasNotResults;
    protected boolean includeCellComments;
    protected boolean includeHeadersFooters;
    protected boolean includeSheetNames;
    protected boolean includeTextBoxes;
    protected Locale locale;
    protected final POIXMLProperties properties;

    public POIXMLDocument getDocument() {
        return null;
    }

    public XSSFEventBasedExcelExtractor(String str) throws XmlException, OpenXML4JException, IOException {
        this(OPCPackage.open(str));
    }

    public XSSFEventBasedExcelExtractor(OPCPackage oPCPackage) throws XmlException, OpenXML4JException, IOException {
        this.includeTextBoxes = true;
        this.includeSheetNames = true;
        this.includeHeadersFooters = true;
        this.concatenatePhoneticRuns = true;
        this.doCloseFilesystem = true;
        this.container = oPCPackage;
        this.properties = new POIXMLProperties(oPCPackage);
    }

    public void setIncludeSheetNames(boolean z) {
        this.includeSheetNames = z;
    }

    public boolean getIncludeSheetNames() {
        return this.includeSheetNames;
    }

    public void setFormulasNotResults(boolean z) {
        this.formulasNotResults = z;
    }

    public boolean getFormulasNotResults() {
        return this.formulasNotResults;
    }

    public void setIncludeHeadersFooters(boolean z) {
        this.includeHeadersFooters = z;
    }

    public boolean getIncludeHeadersFooters() {
        return this.includeHeadersFooters;
    }

    public void setIncludeTextBoxes(boolean z) {
        this.includeTextBoxes = z;
    }

    public boolean getIncludeTextBoxes() {
        return this.includeTextBoxes;
    }

    public void setIncludeCellComments(boolean z) {
        this.includeCellComments = z;
    }

    public boolean getIncludeCellComments() {
        return this.includeCellComments;
    }

    public void setConcatenatePhoneticRuns(boolean z) {
        this.concatenatePhoneticRuns = z;
    }

    public void setLocale(Locale locale2) {
        this.locale = locale2;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public OPCPackage getPackage() {
        return this.container;
    }

    public POIXMLProperties.CoreProperties getCoreProperties() {
        return this.properties.getCoreProperties();
    }

    public POIXMLProperties.ExtendedProperties getExtendedProperties() {
        return this.properties.getExtendedProperties();
    }

    public POIXMLProperties.CustomProperties getCustomProperties() {
        return this.properties.getCustomProperties();
    }

    public void processSheet(XSSFSheetXMLHandler.SheetContentsHandler sheetContentsHandler, Styles styles, Comments comments, SharedStrings sharedStrings, InputStream inputStream) throws IOException, SAXException {
        DataFormatter dataFormatter;
        if (this.locale == null) {
            dataFormatter = new DataFormatter();
        } else {
            dataFormatter = new DataFormatter(this.locale);
        }
        DataFormatter dataFormatter2 = dataFormatter;
        InputSource inputSource = new InputSource(inputStream);
        try {
            XMLReader newXMLReader = XMLHelper.newXMLReader();
            newXMLReader.setContentHandler(new XSSFSheetXMLHandler(styles, comments, sharedStrings, sheetContentsHandler, dataFormatter2, this.formulasNotResults));
            newXMLReader.parse(inputSource);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public SharedStrings createSharedStringsTable(XSSFReader xSSFReader, OPCPackage oPCPackage) throws IOException, SAXException {
        return new ReadOnlySharedStringsTable(oPCPackage, this.concatenatePhoneticRuns);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0077, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0078, code lost:
        if (r11 != null) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0082, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getText() {
        /*
            r12 = this;
            org.apache.poi.xssf.eventusermodel.XSSFReader r0 = new org.apache.poi.xssf.eventusermodel.XSSFReader     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            org.apache.poi.openxml4j.opc.OPCPackage r1 = r12.container     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            r0.<init>(r1)     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            org.apache.poi.openxml4j.opc.OPCPackage r1 = r12.container     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            org.apache.poi.xssf.model.SharedStrings r1 = r12.createSharedStringsTable(r0, r1)     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            org.apache.poi.xssf.model.StylesTable r8 = r0.getStylesTable()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            java.util.Iterator r0 = r0.getSheetsData()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            org.apache.poi.xssf.eventusermodel.XSSFReader$SheetIterator r0 = (org.apache.poi.xssf.eventusermodel.XSSFReader.SheetIterator) r0     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            r2 = 64
            r9.<init>(r2)     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor$SheetTextExtractor r10 = new org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor$SheetTextExtractor     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            r10.<init>()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
        L_0x0023:
            boolean r2 = r0.hasNext()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            if (r2 == 0) goto L_0x0083
            java.io.InputStream r11 = r0.next()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            boolean r2 = r12.includeSheetNames     // Catch:{ all -> 0x0075 }
            if (r2 == 0) goto L_0x003d
            java.lang.String r2 = r0.getSheetName()     // Catch:{ all -> 0x0075 }
            r9.append(r2)     // Catch:{ all -> 0x0075 }
            r2 = 10
            r9.append(r2)     // Catch:{ all -> 0x0075 }
        L_0x003d:
            boolean r2 = r12.includeCellComments     // Catch:{ all -> 0x0075 }
            if (r2 == 0) goto L_0x0046
            org.apache.poi.xssf.model.Comments r2 = r0.getSheetComments()     // Catch:{ all -> 0x0075 }
            goto L_0x0047
        L_0x0046:
            r2 = 0
        L_0x0047:
            r5 = r2
            r2 = r12
            r3 = r10
            r4 = r8
            r6 = r1
            r7 = r11
            r2.processSheet(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0075 }
            boolean r2 = r12.includeHeadersFooters     // Catch:{ all -> 0x0075 }
            if (r2 == 0) goto L_0x0057
            r10.appendHeaderText(r9)     // Catch:{ all -> 0x0075 }
        L_0x0057:
            r10.appendCellText(r9)     // Catch:{ all -> 0x0075 }
            boolean r2 = r12.includeTextBoxes     // Catch:{ all -> 0x0075 }
            if (r2 == 0) goto L_0x0065
            java.util.List r2 = r0.getShapes()     // Catch:{ all -> 0x0075 }
            r12.processShapes(r2, r9)     // Catch:{ all -> 0x0075 }
        L_0x0065:
            boolean r2 = r12.includeHeadersFooters     // Catch:{ all -> 0x0075 }
            if (r2 == 0) goto L_0x006c
            r10.appendFooterText(r9)     // Catch:{ all -> 0x0075 }
        L_0x006c:
            r10.reset()     // Catch:{ all -> 0x0075 }
            if (r11 == 0) goto L_0x0023
            r11.close()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            goto L_0x0023
        L_0x0075:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r0 = move-exception
            if (r11 == 0) goto L_0x0082
            r11.close()     // Catch:{ all -> 0x007e }
            goto L_0x0082
        L_0x007e:
            r1 = move-exception
            r12.addSuppressed(r1)     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
        L_0x0082:
            throw r0     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
        L_0x0083:
            java.lang.String r12 = r9.toString()     // Catch:{ IOException | OpenXML4JException | SAXException -> 0x0088 }
            return r12
        L_0x0088:
            r12 = move-exception
            org.apache.logging.log4j.Logger r0 = LOGGER
            org.apache.logging.log4j.LogBuilder r0 = r0.atWarn()
            org.apache.logging.log4j.LogBuilder r12 = r0.withThrowable(r12)
            java.lang.String r0 = "Failed to load text"
            r12.log((java.lang.String) r0)
            java.lang.String r12 = ""
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.extractor.XSSFEventBasedExcelExtractor.getText():java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public void processShapes(List<XSSFShape> list, StringBuilder sb) {
        String text;
        if (list != null) {
            for (XSSFShape next : list) {
                if ((next instanceof XSSFSimpleShape) && (text = ((XSSFSimpleShape) next).getText()) != null && text.length() > 0) {
                    sb.append(text).append(10);
                }
            }
        }
    }

    public void setCloseFilesystem(boolean z) {
        this.doCloseFilesystem = z;
    }

    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    public OPCPackage getFilesystem() {
        return this.container;
    }

    protected class SheetTextExtractor implements XSSFSheetXMLHandler.SheetContentsHandler {
        private boolean firstCellOfRow = true;
        private final Map<String, String> headerFooterMap;
        private final StringBuilder output = new StringBuilder(64);

        protected SheetTextExtractor() {
            this.headerFooterMap = XSSFEventBasedExcelExtractor.this.includeHeadersFooters ? new HashMap() : null;
        }

        public void startRow(int i) {
            this.firstCellOfRow = true;
        }

        public void endRow(int i) {
            this.output.append(10);
        }

        public void cell(String str, String str2, XSSFComment xSSFComment) {
            if (this.firstCellOfRow) {
                this.firstCellOfRow = false;
            } else {
                this.output.append(9);
            }
            if (str2 != null) {
                XSSFEventBasedExcelExtractor.this.checkMaxTextSize(this.output, str2);
                this.output.append(str2);
            }
            if (XSSFEventBasedExcelExtractor.this.includeCellComments && xSSFComment != null) {
                String replace = xSSFComment.getString().getString().replace(10, Chars.SPACE);
                this.output.append(str2 != null ? " Comment by " : "Comment by ");
                XSSFEventBasedExcelExtractor.this.checkMaxTextSize(this.output, replace);
                if (replace.startsWith(xSSFComment.getAuthor() + ": ")) {
                    this.output.append(replace);
                } else {
                    this.output.append(xSSFComment.getAuthor()).append(": ").append(replace);
                }
            }
        }

        public void headerFooter(String str, boolean z, String str2) {
            Map<String, String> map = this.headerFooterMap;
            if (map != null) {
                map.put(str2, str);
            }
        }

        private void appendHeaderFooterText(StringBuilder sb, String str) {
            String str2 = this.headerFooterMap.get(str);
            if (str2 != null && str2.length() > 0) {
                sb.append(handleHeaderFooterDelimiter(handleHeaderFooterDelimiter(handleHeaderFooterDelimiter(str2, "&L"), "&C"), "&R")).append(10);
            }
        }

        private String handleHeaderFooterDelimiter(String str, String str2) {
            int indexOf = str.indexOf(str2);
            if (indexOf == 0) {
                return str.substring(2);
            }
            return indexOf > 0 ? str.substring(0, indexOf) + "\t" + str.substring(indexOf + 2) : str;
        }

        /* access modifiers changed from: package-private */
        public void appendHeaderText(StringBuilder sb) {
            appendHeaderFooterText(sb, "firstHeader");
            appendHeaderFooterText(sb, "oddHeader");
            appendHeaderFooterText(sb, "evenHeader");
        }

        /* access modifiers changed from: package-private */
        public void appendFooterText(StringBuilder sb) {
            appendHeaderFooterText(sb, "firstFooter");
            appendHeaderFooterText(sb, "oddFooter");
            appendHeaderFooterText(sb, "evenFooter");
        }

        /* access modifiers changed from: package-private */
        public void appendCellText(StringBuilder sb) {
            XSSFEventBasedExcelExtractor.this.checkMaxTextSize(sb, this.output.toString());
            sb.append(this.output);
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.output.setLength(0);
            this.firstCellOfRow = true;
            Map<String, String> map = this.headerFooterMap;
            if (map != null) {
                map.clear();
            }
        }
    }
}
