package org.apache.poi.xssf.eventusermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XSSFReader {
    /* access modifiers changed from: private */
    public static final Logger LOGGER = LogManager.getLogger((Class<?>) XSSFReader.class);
    /* access modifiers changed from: private */
    public static final Set<String> WORKSHEET_RELS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{XSSFRelation.WORKSHEET.getRelation(), XSSFRelation.CHARTSHEET.getRelation(), XSSFRelation.MACRO_SHEET_BIN.getRelation()})));
    protected OPCPackage pkg;
    protected boolean useReadOnlySharedStringsTable;
    protected PackagePart workbookPart;

    public XSSFReader(OPCPackage oPCPackage) throws IOException, OpenXML4JException {
        this(oPCPackage, false);
    }

    public XSSFReader(OPCPackage oPCPackage, boolean z) throws IOException, OpenXML4JException {
        this.pkg = oPCPackage;
        PackageRelationship relationship = oPCPackage.getRelationshipsByType(PackageRelationshipTypes.CORE_DOCUMENT).getRelationship(0);
        if (relationship == null) {
            if (z) {
                relationship = this.pkg.getRelationshipsByType(PackageRelationshipTypes.STRICT_CORE_DOCUMENT).getRelationship(0);
            } else if (this.pkg.getRelationshipsByType(PackageRelationshipTypes.STRICT_CORE_DOCUMENT).getRelationship(0) != null) {
                throw new POIXMLException("Strict OOXML isn't currently supported, please see bug #57699");
            }
            if (relationship == null) {
                throw new POIXMLException("OOXML file structure broken/invalid - no core document found!");
            }
        }
        this.workbookPart = this.pkg.getPart(relationship);
    }

    public void setUseReadOnlySharedStringsTable(boolean z) {
        this.useReadOnlySharedStringsTable = z;
    }

    public boolean useReadOnlySharedStringsTable() {
        return this.useReadOnlySharedStringsTable;
    }

    public SharedStrings getSharedStringsTable() throws IOException, InvalidFormatException {
        ArrayList<PackagePart> partsByContentType = this.pkg.getPartsByContentType(XSSFRelation.SHARED_STRINGS.getContentType());
        try {
            if (partsByContentType.isEmpty()) {
                return null;
            }
            if (this.useReadOnlySharedStringsTable) {
                return new ReadOnlySharedStringsTable(partsByContentType.get(0));
            }
            return new SharedStringsTable(partsByContentType.get(0));
        } catch (SAXException e) {
            throw new InvalidFormatException("Failed to parse SharedStringsTable", e);
        }
    }

    public StylesTable getStylesTable() throws IOException, InvalidFormatException {
        ArrayList<PackagePart> partsByContentType = this.pkg.getPartsByContentType(XSSFRelation.STYLES.getContentType());
        if (partsByContentType.isEmpty()) {
            return null;
        }
        StylesTable stylesTable = new StylesTable(partsByContentType.get(0));
        ArrayList<PackagePart> partsByContentType2 = this.pkg.getPartsByContentType(XSSFRelation.THEME.getContentType());
        if (partsByContentType2.size() != 0) {
            stylesTable.setTheme(new ThemesTable(partsByContentType2.get(0)));
        }
        return stylesTable;
    }

    public InputStream getSharedStringsData() throws IOException, InvalidFormatException {
        return XSSFRelation.SHARED_STRINGS.getContents(this.workbookPart);
    }

    public InputStream getStylesData() throws IOException, InvalidFormatException {
        return XSSFRelation.STYLES.getContents(this.workbookPart);
    }

    public InputStream getThemesData() throws IOException, InvalidFormatException {
        return XSSFRelation.THEME.getContents(this.workbookPart);
    }

    public InputStream getWorkbookData() throws IOException, InvalidFormatException {
        return this.workbookPart.getInputStream();
    }

    public InputStream getSheet(String str) throws IOException, InvalidFormatException {
        PackageRelationship relationship = this.workbookPart.getRelationship(str);
        if (relationship != null) {
            PackagePart part = this.pkg.getPart(PackagingURIHelper.createPartName(relationship.getTargetURI()));
            if (part != null) {
                return part.getInputStream();
            }
            throw new IllegalArgumentException("No data found for Sheet with r:id " + str);
        }
        throw new IllegalArgumentException("No Sheet found with r:id " + str);
    }

    public Iterator<InputStream> getSheetsData() throws IOException, InvalidFormatException {
        return new SheetIterator(this.workbookPart);
    }

    public static class SheetIterator implements Iterator<InputStream> {
        protected final Iterator<XSSFSheetRef> sheetIterator;
        protected final Map<String, PackagePart> sheetMap;
        protected XSSFSheetRef xssfSheetRef;

        protected SheetIterator(PackagePart packagePart) throws IOException {
            try {
                this.sheetMap = new HashMap();
                OPCPackage oPCPackage = packagePart.getPackage();
                Set<String> sheetRelationships = getSheetRelationships();
                Iterator<PackageRelationship> it = packagePart.getRelationships().iterator();
                while (it.hasNext()) {
                    PackageRelationship next = it.next();
                    if (sheetRelationships.contains(next.getRelationshipType())) {
                        this.sheetMap.put(next.getId(), oPCPackage.getPart(PackagingURIHelper.createPartName(next.getTargetURI())));
                    }
                }
                this.sheetIterator = createSheetIteratorFromWB(packagePart);
            } catch (InvalidFormatException e) {
                throw new POIXMLException((Throwable) e);
            }
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
            if (r3 != null) goto L_0x0050;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            r3.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0058, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.util.Iterator<org.apache.poi.xssf.eventusermodel.XSSFReader.XSSFSheetRef> createSheetIteratorFromWB(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException {
            /*
                r2 = this;
                org.apache.poi.xssf.eventusermodel.XSSFReader$XMLSheetRefReader r2 = new org.apache.poi.xssf.eventusermodel.XSSFReader$XMLSheetRefReader
                r2.<init>()
                org.xml.sax.XMLReader r0 = org.apache.poi.util.XMLHelper.newXMLReader()     // Catch:{ ParserConfigurationException | SAXException -> 0x0060 }
                r0.setContentHandler(r2)
                java.io.InputStream r3 = r3.getInputStream()     // Catch:{ SAXException -> 0x0059 }
                org.xml.sax.InputSource r1 = new org.xml.sax.InputSource     // Catch:{ all -> 0x004b }
                r1.<init>(r3)     // Catch:{ all -> 0x004b }
                r0.parse(r1)     // Catch:{ all -> 0x004b }
                if (r3 == 0) goto L_0x001d
                r3.close()     // Catch:{ SAXException -> 0x0059 }
            L_0x001d:
                java.util.ArrayList r3 = new java.util.ArrayList
                r3.<init>()
                java.util.List r2 = r2.getSheetRefs()
                java.util.Iterator r2 = r2.iterator()
            L_0x002a:
                boolean r0 = r2.hasNext()
                if (r0 == 0) goto L_0x0046
                java.lang.Object r0 = r2.next()
                org.apache.poi.xssf.eventusermodel.XSSFReader$XSSFSheetRef r0 = (org.apache.poi.xssf.eventusermodel.XSSFReader.XSSFSheetRef) r0
                java.lang.String r1 = r0.getId()
                if (r1 == 0) goto L_0x002a
                int r1 = r1.length()
                if (r1 <= 0) goto L_0x002a
                r3.add(r0)
                goto L_0x002a
            L_0x0046:
                java.util.Iterator r2 = r3.iterator()
                return r2
            L_0x004b:
                r2 = move-exception
                throw r2     // Catch:{ all -> 0x004d }
            L_0x004d:
                r0 = move-exception
                if (r3 == 0) goto L_0x0058
                r3.close()     // Catch:{ all -> 0x0054 }
                goto L_0x0058
            L_0x0054:
                r3 = move-exception
                r2.addSuppressed(r3)     // Catch:{ SAXException -> 0x0059 }
            L_0x0058:
                throw r0     // Catch:{ SAXException -> 0x0059 }
            L_0x0059:
                r2 = move-exception
                org.apache.poi.ooxml.POIXMLException r3 = new org.apache.poi.ooxml.POIXMLException
                r3.<init>((java.lang.Throwable) r2)
                throw r3
            L_0x0060:
                r2 = move-exception
                org.apache.poi.ooxml.POIXMLException r3 = new org.apache.poi.ooxml.POIXMLException
                r3.<init>((java.lang.Throwable) r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.eventusermodel.XSSFReader.SheetIterator.createSheetIteratorFromWB(org.apache.poi.openxml4j.opc.PackagePart):java.util.Iterator");
        }

        /* access modifiers changed from: protected */
        public Set<String> getSheetRelationships() {
            return XSSFReader.WORKSHEET_RELS;
        }

        public boolean hasNext() {
            return this.sheetIterator.hasNext();
        }

        public InputStream next() {
            XSSFSheetRef next = this.sheetIterator.next();
            this.xssfSheetRef = next;
            try {
                return this.sheetMap.get(next.getId()).getInputStream();
            } catch (IOException e) {
                throw new POIXMLException((Throwable) e);
            }
        }

        public String getSheetName() {
            return this.xssfSheetRef.getName();
        }

        public Comments getSheetComments() {
            PackagePart sheetPart = getSheetPart();
            try {
                PackageRelationshipCollection relationshipsByType = sheetPart.getRelationshipsByType(XSSFRelation.SHEET_COMMENTS.getRelation());
                if (relationshipsByType.isEmpty()) {
                    return null;
                }
                return parseComments(sheetPart.getPackage().getPart(PackagingURIHelper.createPartName(relationshipsByType.getRelationship(0).getTargetURI())));
            } catch (IOException | InvalidFormatException e) {
                XSSFReader.LOGGER.atWarn().withThrowable(e).log("Failed to load sheet comments");
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public Comments parseComments(PackagePart packagePart) throws IOException {
            return new CommentsTable(packagePart);
        }

        public List<XSSFShape> getShapes() {
            PackagePart sheetPart = getSheetPart();
            LinkedList linkedList = new LinkedList();
            try {
                PackageRelationshipCollection relationshipsByType = sheetPart.getRelationshipsByType(XSSFRelation.DRAWINGS.getRelation());
                int size = relationshipsByType.size();
                for (int i = 0; i < size; i++) {
                    PackagePartName createPartName = PackagingURIHelper.createPartName(relationshipsByType.getRelationship(i).getTargetURI());
                    PackagePart part = sheetPart.getPackage().getPart(createPartName);
                    if (part == null) {
                        XSSFReader.LOGGER.atWarn().log("Missing drawing: {}. Skipping it.", (Object) createPartName);
                    } else {
                        linkedList.addAll(new XSSFDrawing(part).getShapes());
                    }
                }
                return linkedList;
            } catch (IOException | InvalidFormatException | XmlException e) {
                XSSFReader.LOGGER.atWarn().withThrowable(e).log("Failed to load shapes");
                return null;
            }
        }

        public PackagePart getSheetPart() {
            return this.sheetMap.get(this.xssfSheetRef.getId());
        }

        public void remove() {
            throw new IllegalStateException("Not supported");
        }
    }

    public static final class XSSFSheetRef {
        private final String id;
        private final String name;

        public XSSFSheetRef(String str, String str2) {
            this.id = str;
            this.name = str2;
        }

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }
    }

    public static class XMLSheetRefReader extends DefaultHandler {
        private static final String ID = "id";
        private static final String NAME = "name";
        private static final String SHEET = "sheet";
        private final List<XSSFSheetRef> sheetRefs = new LinkedList();

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equalsIgnoreCase(SHEET)) {
                String str4 = null;
                int i = 0;
                String str5 = null;
                while (i < attributes.getLength()) {
                    String localName = attributes.getLocalName(i);
                    if (localName.equalsIgnoreCase(NAME)) {
                        str4 = attributes.getValue(i);
                    } else if (localName.equalsIgnoreCase(ID)) {
                        str5 = attributes.getValue(i);
                    }
                    if (str4 == null || str5 == null) {
                        i++;
                    } else {
                        this.sheetRefs.add(new XSSFSheetRef(str5, str4));
                        return;
                    }
                }
            }
        }

        public List<XSSFSheetRef> getSheetRefs() {
            return Collections.unmodifiableList(this.sheetRefs);
        }
    }
}
