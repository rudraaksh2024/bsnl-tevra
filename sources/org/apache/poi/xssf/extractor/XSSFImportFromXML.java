package org.apache.poi.xssf.extractor;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.ooxml.util.XPathHelper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFMap;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.apache.poi.xssf.usermodel.helpers.XSSFSingleXmlCell;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XSSFImportFromXML {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSSFImportFromXML.class);
    private final XSSFMap _map;

    public XSSFImportFromXML(XSSFMap xSSFMap) {
        this._map = xSSFMap;
    }

    public void importFromXML(String str) throws SAXException, XPathExpressionException, IOException {
        Document parse = DocumentHelper.newDocumentBuilder().parse(new InputSource(new StringReader(str.trim())));
        List<XSSFSingleXmlCell> relatedSingleXMLCell = this._map.getRelatedSingleXMLCell();
        List<XSSFTable> relatedTables = this._map.getRelatedTables();
        XPath newXPath = XPathHelper.getFactory().newXPath();
        newXPath.setNamespaceContext(new DefaultNamespaceContext(parse));
        for (XSSFSingleXmlCell next : relatedSingleXMLCell) {
            String xmlDataType = next.getXmlDataType();
            String xpath = next.getXpath();
            Node node = (Node) newXPath.evaluate(xpath, parse, XPathConstants.NODE);
            if (node != null) {
                String textContent = node.getTextContent();
                Logger logger = LOG;
                logger.atDebug().log("Extracting with xpath {} : value is '{}'", xpath, textContent);
                XSSFCell referencedCell = next.getReferencedCell();
                logger.atDebug().log("Setting '{}' to cell {}-{} in sheet {}", textContent, Unbox.box(referencedCell.getColumnIndex()), Unbox.box(referencedCell.getRowIndex()), referencedCell.getSheet().getSheetName());
                setCellValue(textContent, referencedCell, xmlDataType);
            }
        }
        Iterator<XSSFTable> it = relatedTables.iterator();
        while (it.hasNext()) {
            XSSFTable next2 = it.next();
            NodeList nodeList = (NodeList) newXPath.evaluate(next2.getCommonXpath(), parse, XPathConstants.NODESET);
            int row = next2.getStartCellReference().getRow() + next2.getHeaderRowCount();
            short col = next2.getStartCellReference().getCol();
            next2.setDataRowCount(nodeList.getLength());
            for (int i = 0; i < nodeList.getLength(); i++) {
                int i2 = 1;
                Node cloneNode = nodeList.item(i).cloneNode(true);
                for (XSSFTableColumn next3 : next2.getColumns()) {
                    XSSFXmlColumnPr xmlColumnPr = next3.getXmlColumnPr();
                    if (xmlColumnPr != null) {
                        int i3 = row + i;
                        int columnIndex = next3.getColumnIndex() + col;
                        Document document = parse;
                        String localXPath = xmlColumnPr.getLocalXPath();
                        Iterator<XSSFTable> it2 = it;
                        String substring = localXPath.substring(localXPath.indexOf(47, i2) + i2);
                        String str2 = (String) newXPath.evaluate(substring, cloneNode, XPathConstants.STRING);
                        Logger logger2 = LOG;
                        logger2.atDebug().log("Extracting with xpath {} : value is '{}'", substring, str2);
                        XSSFRow row2 = next2.getXSSFSheet().getRow(i3);
                        if (row2 == null) {
                            row2 = next2.getXSSFSheet().createRow(i3);
                        }
                        XSSFCell cell = row2.getCell(columnIndex);
                        if (cell == null) {
                            cell = row2.createCell(columnIndex);
                        }
                        logger2.atDebug().log("Setting '{}' to cell {}-{} in sheet {}", str2, Unbox.box(cell.getColumnIndex()), Unbox.box(cell.getRowIndex()), next2.getXSSFSheet().getSheetName());
                        setCellValue(str2, cell, xmlColumnPr.getXmlDataType());
                        parse = document;
                        it = it2;
                        i2 = 1;
                    }
                }
                Document document2 = parse;
                Iterator<XSSFTable> it3 = it;
            }
        }
    }

    private enum DataType {
        BOOLEAN("boolean"),
        DOUBLE(XmlErrorCodes.DOUBLE),
        INTEGER(XmlErrorCodes.INT, "unsignedInt", "integer"),
        STRING(TypedValues.Custom.S_STRING),
        DATE(XmlErrorCodes.DATE);
        
        private Set<String> xmlDataTypes;

        private DataType(String... strArr) {
            this.xmlDataTypes = new HashSet(Arrays.asList(strArr));
        }

        public static DataType getDataType(String str) {
            for (DataType dataType : values()) {
                if (dataType.xmlDataTypes.contains(str)) {
                    return dataType;
                }
            }
            return null;
        }
    }

    private void setCellValue(String str, XSSFCell xSSFCell, String str2) {
        DataType dataType = DataType.getDataType(str2);
        try {
            if (!str.isEmpty()) {
                if (dataType != null) {
                    int i = AnonymousClass1.$SwitchMap$org$apache$poi$xssf$extractor$XSSFImportFromXML$DataType[dataType.ordinal()];
                    if (i == 1) {
                        xSSFCell.setCellValue(Boolean.parseBoolean(str));
                        return;
                    } else if (i == 2) {
                        xSSFCell.setCellValue(Double.parseDouble(str));
                        return;
                    } else if (i == 3) {
                        xSSFCell.setCellValue((double) Integer.parseInt(str));
                        return;
                    } else if (i != 4) {
                        xSSFCell.setCellValue(str.trim());
                        return;
                    } else {
                        xSSFCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd", LocaleUtil.getUserLocale()).parse(str));
                        if (!DateUtil.isValidExcelDate(xSSFCell.getNumericCellValue())) {
                            xSSFCell.setCellValue(str);
                            return;
                        }
                        return;
                    }
                }
            }
            String str3 = null;
            xSSFCell.setCellValue((String) null);
        } catch (IllegalArgumentException | ParseException unused) {
            throw new IllegalArgumentException(String.format(LocaleUtil.getUserLocale(), "Unable to format value '%s' as %s for cell %s", new Object[]{str, dataType, new CellReference((Cell) xSSFCell).formatAsString()}));
        }
    }

    /* renamed from: org.apache.poi.xssf.extractor.XSSFImportFromXML$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$xssf$extractor$XSSFImportFromXML$DataType;

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
                org.apache.poi.xssf.extractor.XSSFImportFromXML$DataType[] r0 = org.apache.poi.xssf.extractor.XSSFImportFromXML.DataType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$xssf$extractor$XSSFImportFromXML$DataType = r0
                org.apache.poi.xssf.extractor.XSSFImportFromXML$DataType r1 = org.apache.poi.xssf.extractor.XSSFImportFromXML.DataType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$extractor$XSSFImportFromXML$DataType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.xssf.extractor.XSSFImportFromXML$DataType r1 = org.apache.poi.xssf.extractor.XSSFImportFromXML.DataType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$extractor$XSSFImportFromXML$DataType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.xssf.extractor.XSSFImportFromXML$DataType r1 = org.apache.poi.xssf.extractor.XSSFImportFromXML.DataType.INTEGER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$extractor$XSSFImportFromXML$DataType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.xssf.extractor.XSSFImportFromXML$DataType r1 = org.apache.poi.xssf.extractor.XSSFImportFromXML.DataType.DATE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$xssf$extractor$XSSFImportFromXML$DataType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.xssf.extractor.XSSFImportFromXML$DataType r1 = org.apache.poi.xssf.extractor.XSSFImportFromXML.DataType.STRING     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.extractor.XSSFImportFromXML.AnonymousClass1.<clinit>():void");
        }
    }

    private static final class DefaultNamespaceContext implements NamespaceContext {
        private final Element _docElem;

        public String getPrefix(String str) {
            return null;
        }

        public Iterator<String> getPrefixes(String str) {
            return null;
        }

        public DefaultNamespaceContext(Document document) {
            this._docElem = document.getDocumentElement();
        }

        public String getNamespaceURI(String str) {
            return getNamespaceForPrefix(str);
        }

        private String getNamespaceForPrefix(String str) {
            if (str.equals("xml")) {
                return "http://www.w3.org/XML/1998/namespace";
            }
            Node node = this._docElem;
            while (node != null) {
                short nodeType = node.getNodeType();
                if (nodeType == 1) {
                    if (node.getNodeName().startsWith(str + ParameterizedMessage.ERROR_MSG_SEPARATOR)) {
                        return node.getNamespaceURI();
                    }
                    NamedNodeMap attributes = node.getAttributes();
                    for (int i = 0; i < attributes.getLength(); i++) {
                        Node item = attributes.item(i);
                        String nodeName = item.getNodeName();
                        boolean startsWith = nodeName.startsWith(Sax2Dom.XMLNS_STRING);
                        if (startsWith || nodeName.equals(Sax2Dom.XMLNS_PREFIX)) {
                            if ((startsWith ? nodeName.substring(nodeName.indexOf(58) + 1) : "").equals(str)) {
                                return item.getNodeValue();
                            }
                        }
                    }
                    node = node.getParentNode();
                } else if (nodeType != 5) {
                    return null;
                }
            }
            return null;
        }
    }
}
