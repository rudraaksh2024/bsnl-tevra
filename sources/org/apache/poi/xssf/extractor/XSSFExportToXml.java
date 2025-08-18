package org.apache.poi.xssf.extractor;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFMap;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.apache.poi.xssf.usermodel.helpers.XSSFSingleXmlCell;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XSSFExportToXml implements Comparator<String> {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSSFExportToXml.class);
    private final HashMap<String, Integer> indexMap = new HashMap<>();
    private XSSFMap map;

    @FunctionalInterface
    private interface SecurityFeature {
        void accept(String str) throws SAXException;
    }

    public XSSFExportToXml(XSSFMap xSSFMap) {
        this.map = xSSFMap;
    }

    public void exportToXML(OutputStream outputStream, boolean z) throws SAXException, TransformerException {
        exportToXML(outputStream, "UTF-8", z);
    }

    public void exportToXML(OutputStream outputStream, String str, boolean z) throws SAXException, TransformerException {
        Element element;
        Iterator it;
        XSSFXmlColumnPr xmlColumnPr;
        XSSFCell referencedCell;
        List<XSSFSingleXmlCell> relatedSingleXMLCell = this.map.getRelatedSingleXMLCell();
        List<XSSFTable> relatedTables = this.map.getRelatedTables();
        String rootElement = this.map.getCtMap().getRootElement();
        Document createDocument = DocumentHelper.createDocument();
        if (isNamespaceDeclared()) {
            element = createDocument.createElementNS(getNamespace(), rootElement);
        } else {
            element = createDocument.createElementNS("", rootElement);
        }
        createDocument.appendChild(element);
        Vector vector = new Vector();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (XSSFSingleXmlCell next : relatedSingleXMLCell) {
            vector.add(next.getXpath());
            hashMap.put(next.getXpath(), next);
        }
        for (XSSFTable next2 : relatedTables) {
            String commonXpath = next2.getCommonXpath();
            vector.add(commonXpath);
            hashMap2.put(commonXpath, next2);
        }
        this.indexMap.clear();
        vector.sort(this);
        this.indexMap.clear();
        Iterator it2 = vector.iterator();
        while (true) {
            boolean z2 = true;
            if (!it2.hasNext()) {
                break;
            }
            String str2 = (String) it2.next();
            XSSFSingleXmlCell xSSFSingleXmlCell = (XSSFSingleXmlCell) hashMap.get(str2);
            XSSFTable xSSFTable = (XSSFTable) hashMap2.get(str2);
            if (!str2.matches(".*\\[.*")) {
                if (!(xSSFSingleXmlCell == null || (referencedCell = xSSFSingleXmlCell.getReferencedCell()) == null)) {
                    Node nodeByXPath = getNodeByXPath(str2, createDocument.getFirstChild(), createDocument, false);
                    mapCellOnNode(referencedCell, nodeByXPath);
                    if ("".equals(nodeByXPath.getTextContent()) && nodeByXPath.getParentNode() != null) {
                        nodeByXPath.getParentNode().removeChild(nodeByXPath);
                    }
                }
                if (xSSFTable != null) {
                    List<XSSFTableColumn> columns = xSSFTable.getColumns();
                    XSSFSheet xSSFSheet = xSSFTable.getXSSFSheet();
                    int row = xSSFTable.getStartCellReference().getRow() + xSSFTable.getHeaderRowCount();
                    int row2 = xSSFTable.getEndCellReference().getRow();
                    while (row <= row2) {
                        XSSFRow row3 = xSSFSheet.getRow(row);
                        Node nodeByXPath2 = getNodeByXPath(xSSFTable.getCommonXpath(), createDocument.getFirstChild(), createDocument, z2);
                        short col = xSSFTable.getStartCellReference().getCol();
                        for (XSSFTableColumn next3 : columns) {
                            XSSFCell cell = row3.getCell(col + next3.getColumnIndex());
                            if (cell == null || (xmlColumnPr = next3.getXmlColumnPr()) == null) {
                                it = it2;
                            } else {
                                it = it2;
                                mapCellOnNode(cell, getNodeByXPath(xmlColumnPr.getLocalXPath(), nodeByXPath2, createDocument, false));
                            }
                            it2 = it;
                        }
                        Iterator it3 = it2;
                        row++;
                        z2 = true;
                    }
                }
            }
            it2 = it2;
        }
        if (z ? isValid(createDocument) : true) {
            Transformer newTransformer = XMLHelper.newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", "yes");
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("encoding", str);
            newTransformer.transform(new DOMSource(createDocument), new StreamResult(outputStream));
        }
    }

    private boolean isValid(Document document) throws SAXException {
        try {
            XMLHelper.getSchemaFactory().newSchema(new DOMSource(this.map.getSchema())).newValidator().validate(new DOMSource(document));
            return true;
        } catch (IOException e) {
            LOG.atError().withThrowable(e).log("document is not valid");
            return false;
        }
    }

    /* renamed from: org.apache.poi.xssf.extractor.XSSFExportToXml$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.ss.usermodel.CellType[] r0 = org.apache.poi.ss.usermodel.CellType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$CellType = r0
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.FORMULA     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.extractor.XSSFExportToXml.AnonymousClass1.<clinit>():void");
        }
    }

    private void mapCellOnNode(XSSFCell xSSFCell, Node node) {
        String str;
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[xSSFCell.getCellType().ordinal()];
        if (i != 1) {
            str = "";
            if (i == 2) {
                str = str + xSSFCell.getBooleanCellValue();
            } else if (i == 3) {
                str = xSSFCell.getErrorCellString();
            } else if (i != 4) {
                if (i == 5) {
                    str = DateUtil.isCellDateFormatted(xSSFCell) ? getFormattedDate(xSSFCell) : str + xSSFCell.getRawValue();
                }
            } else if (xSSFCell.getCachedFormulaResultType() == CellType.STRING) {
                str = xSSFCell.getStringCellValue();
            } else if (xSSFCell.getCachedFormulaResultType() == CellType.BOOLEAN) {
                str = str + xSSFCell.getBooleanCellValue();
            } else if (xSSFCell.getCachedFormulaResultType() == CellType.ERROR) {
                str = xSSFCell.getErrorCellString();
            } else if (xSSFCell.getCachedFormulaResultType() == CellType.NUMERIC) {
                str = DateUtil.isCellDateFormatted(xSSFCell) ? getFormattedDate(xSSFCell) : str + xSSFCell.getNumericCellValue();
            }
        } else {
            str = xSSFCell.getStringCellValue();
        }
        if (node instanceof Element) {
            ((Element) node).setTextContent(str);
        } else {
            node.setNodeValue(str);
        }
    }

    private String removeNamespace(String str) {
        return str.matches(".*:.*") ? str.split(ParameterizedMessage.ERROR_MSG_SEPARATOR)[1] : str;
    }

    private String getFormattedDate(XSSFCell xSSFCell) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
        simpleDateFormat.setTimeZone(LocaleUtil.getUserTimeZone());
        return simpleDateFormat.format(xSSFCell.getDateCellValue());
    }

    private Node getNodeByXPath(String str, Node node, Document document, boolean z) {
        Node node2;
        String[] split = str.split(PackagingURIHelper.FORWARD_SLASH_STRING);
        for (int i = 2; i < split.length; i++) {
            String removeNamespace = removeNamespace(split[i]);
            if (!removeNamespace.startsWith("@")) {
                NodeList childNodes = node.getChildNodes();
                if (!z || i != split.length - 1) {
                    node2 = selectNode(removeNamespace, childNodes);
                } else {
                    node2 = null;
                }
                if (node2 == null) {
                    node2 = createElement(document, node, removeNamespace);
                }
                node = node2;
            } else {
                node = createAttribute(document, node, removeNamespace);
            }
        }
        return node;
    }

    private Node createAttribute(Document document, Node node, String str) {
        String substring = str.substring(1);
        NamedNodeMap attributes = node.getAttributes();
        Node namedItem = attributes.getNamedItem(substring);
        if (namedItem != null) {
            return namedItem;
        }
        Attr createAttributeNS = document.createAttributeNS("", substring);
        attributes.setNamedItem(createAttributeNS);
        return createAttributeNS;
    }

    private Node createElement(Document document, Node node, String str) {
        Element element;
        if (isNamespaceDeclared()) {
            element = document.createElementNS(getNamespace(), str);
        } else {
            element = document.createElementNS("", str);
        }
        node.appendChild(element);
        return element;
    }

    private Node selectNode(String str, NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if (item.getNodeName().equals(str)) {
                return item;
            }
        }
        return null;
    }

    private boolean isNamespaceDeclared() {
        String namespace = getNamespace();
        return namespace != null && !namespace.isEmpty();
    }

    private String getNamespace() {
        return this.map.getCTSchema().getNamespace();
    }

    public int compare(String str, String str2) {
        Node schema = this.map.getSchema();
        String[] split = str.split(PackagingURIHelper.FORWARD_SLASH_STRING);
        String[] split2 = str2.split(PackagingURIHelper.FORWARD_SLASH_STRING);
        int min = Math.min(split.length, split2.length);
        String str3 = "";
        Node node = schema;
        for (int i = 1; i < min; i++) {
            String str4 = split[i];
            String str5 = split2[i];
            if (!str4.equals(str5)) {
                return indexOfElementInComplexType(str3, str4, str5, node);
            }
            str3 = str3 + PackagingURIHelper.FORWARD_SLASH_STRING + str4;
            node = getComplexTypeForElement(str4, schema, node);
        }
        return 0;
    }

    private int indexOfElementInComplexType(String str, String str2, String str3, Node node) {
        if (node == null) {
            return 0;
        }
        String removeNamespace = removeNamespace(str2);
        int andStoreIndex = getAndStoreIndex(str, removeNamespace);
        String removeNamespace2 = removeNamespace(str3);
        int andStoreIndex2 = getAndStoreIndex(str, removeNamespace2);
        int i = 0;
        for (Node firstChild = node.getFirstChild(); firstChild != null && (andStoreIndex2 == -1 || andStoreIndex == -1); firstChild = firstChild.getNextSibling()) {
            if ((firstChild instanceof Element) && "element".equals(firstChild.getLocalName())) {
                String nodeValue = getNameOrRefElement(firstChild).getNodeValue();
                if (nodeValue.equals(removeNamespace)) {
                    this.indexMap.put(str + PackagingURIHelper.FORWARD_SLASH_STRING + removeNamespace, Integer.valueOf(i));
                    andStoreIndex = i;
                }
                if (nodeValue.equals(removeNamespace2)) {
                    this.indexMap.put(str + PackagingURIHelper.FORWARD_SLASH_STRING + removeNamespace2, Integer.valueOf(i));
                    andStoreIndex2 = i;
                }
            }
            i++;
        }
        if (andStoreIndex == -1 || andStoreIndex2 == -1) {
            return 0;
        }
        return Integer.compare(andStoreIndex, andStoreIndex2);
    }

    private int getAndStoreIndex(String str, String str2) {
        return this.indexMap.getOrDefault(str + PackagingURIHelper.FORWARD_SLASH_STRING + str2, -1).intValue();
    }

    private Node getNameOrRefElement(Node node) {
        Node namedItem = node.getAttributes().getNamedItem("ref");
        if (namedItem != null) {
            return namedItem;
        }
        return node.getAttributes().getNamedItem("name");
    }

    private Node getComplexTypeForElement(String str, Node node, Node node2) {
        String complexTypeNameFromChildren = getComplexTypeNameFromChildren(node2, removeNamespace(str));
        if (!"".equals(complexTypeNameFromChildren)) {
            return getComplexTypeNodeFromSchemaChildren(node, (Node) null, complexTypeNameFromChildren);
        }
        return null;
    }

    private String getComplexTypeNameFromChildren(Node node, String str) {
        Node namedItem;
        if (node == null) {
            return "";
        }
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if ((firstChild instanceof Element) && "element".equals(firstChild.getLocalName()) && getNameOrRefElement(firstChild).getNodeValue().equals(str) && (namedItem = firstChild.getAttributes().getNamedItem("type")) != null) {
                return namedItem.getNodeValue();
            }
        }
        return "";
    }

    private Node getComplexTypeNodeFromSchemaChildren(Node node, Node node2, String str) {
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if ((firstChild instanceof Element) && "complexType".equals(firstChild.getLocalName()) && getNameOrRefElement(firstChild).getNodeValue().equals(str)) {
                Node firstChild2 = firstChild.getFirstChild();
                while (true) {
                    if (firstChild2 == null) {
                        break;
                    }
                    if (firstChild2 instanceof Element) {
                        String localName = firstChild2.getLocalName();
                        if ("sequence".equals(localName) || "all".equals(localName)) {
                            node2 = firstChild2;
                        }
                    }
                    firstChild2 = firstChild2.getNextSibling();
                }
                node2 = firstChild2;
                if (node2 != null) {
                    break;
                }
            }
        }
        return node2;
    }

    private static void trySet(String str, SecurityFeature securityFeature) {
        try {
            securityFeature.accept(str);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("SchemaFactory feature ({}) unsupported", (Object) str);
        } catch (AbstractMethodError e2) {
            LOG.atWarn().withThrowable(e2).log("Cannot set SchemaFactory feature ({}) because outdated XML parser in classpath", (Object) str);
        }
    }
}
