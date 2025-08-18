package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.DebugKt;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

public class XWPFTableCell implements IBody, ICell {
    private static final EnumMap<XWPFVertAlign, STVerticalJc.Enum> alignMap;
    private static final HashMap<Integer, XWPFVertAlign> stVertAlignTypeMap;
    protected List<IBodyElement> bodyElements = new ArrayList();
    private final CTTc ctTc;
    protected List<XWPFParagraph> paragraphs = new ArrayList();
    protected IBody part;
    private final XWPFTableRow tableRow;
    protected List<XWPFTable> tables = new ArrayList();

    public enum XWPFVertAlign {
        TOP,
        CENTER,
        BOTH,
        BOTTOM
    }

    static {
        EnumMap<XWPFVertAlign, STVerticalJc.Enum> enumMap = new EnumMap<>(XWPFVertAlign.class);
        alignMap = enumMap;
        enumMap.put(XWPFVertAlign.TOP, STVerticalJc.Enum.forInt(1));
        enumMap.put(XWPFVertAlign.CENTER, STVerticalJc.Enum.forInt(2));
        enumMap.put(XWPFVertAlign.BOTH, STVerticalJc.Enum.forInt(3));
        enumMap.put(XWPFVertAlign.BOTTOM, STVerticalJc.Enum.forInt(4));
        HashMap<Integer, XWPFVertAlign> hashMap = new HashMap<>();
        stVertAlignTypeMap = hashMap;
        hashMap.put(1, XWPFVertAlign.TOP);
        hashMap.put(2, XWPFVertAlign.CENTER);
        hashMap.put(3, XWPFVertAlign.BOTH);
        hashMap.put(4, XWPFVertAlign.BOTTOM);
    }

    public XWPFTableCell(CTTc cTTc, XWPFTableRow xWPFTableRow, IBody iBody) {
        this.ctTc = cTTc;
        this.part = iBody;
        this.tableRow = xWPFTableRow;
        XmlCursor newCursor = cTTc.newCursor();
        try {
            newCursor.selectPath("./*");
            while (newCursor.toNextSelection()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTP) {
                    XWPFParagraph xWPFParagraph = new XWPFParagraph((CTP) object, this);
                    this.paragraphs.add(xWPFParagraph);
                    this.bodyElements.add(xWPFParagraph);
                }
                if (object instanceof CTTbl) {
                    XWPFTable xWPFTable = new XWPFTable((CTTbl) object, this);
                    this.tables.add(xWPFTable);
                    this.bodyElements.add(xWPFTable);
                }
                if (object instanceof CTSdtBlock) {
                    this.bodyElements.add(new XWPFSDT((CTSdtBlock) object, (IBody) this));
                }
                if (object instanceof CTSdtRun) {
                    this.bodyElements.add(new XWPFSDT((CTSdtRun) object, (IBody) this));
                }
            }
        } finally {
            newCursor.dispose();
        }
    }

    @Internal
    public CTTc getCTTc() {
        return this.ctTc;
    }

    public List<IBodyElement> getBodyElements() {
        return Collections.unmodifiableList(this.bodyElements);
    }

    public void setParagraph(XWPFParagraph xWPFParagraph) {
        if (this.ctTc.sizeOfPArray() == 0) {
            this.ctTc.addNewP();
        }
        this.ctTc.setPArray(0, xWPFParagraph.getCTP());
    }

    public List<XWPFParagraph> getParagraphs() {
        return Collections.unmodifiableList(this.paragraphs);
    }

    public XWPFParagraph addParagraph() {
        XWPFParagraph xWPFParagraph = new XWPFParagraph(this.ctTc.addNewP(), this);
        addParagraph(xWPFParagraph);
        return xWPFParagraph;
    }

    public void addParagraph(XWPFParagraph xWPFParagraph) {
        this.paragraphs.add(xWPFParagraph);
        this.bodyElements.add(xWPFParagraph);
    }

    public void removeParagraph(int i) {
        this.paragraphs.remove(i);
        this.ctTc.removeP(i);
        this.bodyElements.remove(this.paragraphs.get(i));
    }

    public XWPFParagraph getParagraph(CTP ctp) {
        for (XWPFParagraph next : this.paragraphs) {
            if (ctp.equals(next.getCTP())) {
                return next;
            }
        }
        return null;
    }

    public XWPFTableRow getTableRow() {
        return this.tableRow;
    }

    public String getColor() {
        CTShd shd;
        CTTcPr tcPr = this.ctTc.getTcPr();
        if (tcPr == null || (shd = tcPr.getShd()) == null) {
            return null;
        }
        return shd.xgetFill().getStringValue();
    }

    public void setColor(String str) {
        CTTcPr tcPr = getTcPr();
        CTShd shd = tcPr.isSetShd() ? tcPr.getShd() : tcPr.addNewShd();
        shd.setColor(DebugKt.DEBUG_PROPERTY_VALUE_AUTO);
        shd.setVal(STShd.CLEAR);
        shd.setFill(str);
    }

    public XWPFVertAlign getVerticalAlignment() {
        CTVerticalJc vAlign;
        CTTcPr tcPr = this.ctTc.getTcPr();
        if (tcPr == null || (vAlign = tcPr.getVAlign()) == null) {
            return null;
        }
        return stVertAlignTypeMap.get(Integer.valueOf(vAlign.getVal().intValue()));
    }

    public void setVerticalAlignment(XWPFVertAlign xWPFVertAlign) {
        getTcPr().addNewVAlign().setVal(alignMap.get(xWPFVertAlign));
    }

    /* JADX INFO: finally extract failed */
    public XWPFParagraph insertNewParagraph(XmlCursor xmlCursor) {
        boolean z;
        XmlObject xmlObject = null;
        if (!isCursorInTableCell(xmlCursor)) {
            return null;
        }
        xmlCursor.beginElement("p", CTP.type.getName().getNamespaceURI());
        xmlCursor.toParent();
        CTP ctp = (CTP) xmlCursor.getObject();
        XWPFParagraph xWPFParagraph = new XWPFParagraph(ctp, this);
        while (true) {
            z = xmlObject instanceof CTP;
            if (z || !xmlCursor.toPrevSibling()) {
                int i = 0;
            } else {
                xmlObject = xmlCursor.getObject();
            }
        }
        int i2 = 0;
        if (!z || xmlObject == ctp) {
            this.paragraphs.add(0, xWPFParagraph);
        } else {
            this.paragraphs.add(this.paragraphs.indexOf(getParagraph((CTP) xmlObject)) + 1, xWPFParagraph);
        }
        XmlCursor newCursor = ctp.newCursor();
        try {
            xmlCursor.toCursor(newCursor);
            while (xmlCursor.toPrevSibling()) {
                XmlObject object = xmlCursor.getObject();
                if ((object instanceof CTP) || (object instanceof CTTbl)) {
                    i2++;
                }
            }
            this.bodyElements.add(i2, xWPFParagraph);
            XmlCursor newCursor2 = ctp.newCursor();
            try {
                xmlCursor.toCursor(newCursor2);
                newCursor2.dispose();
                xmlCursor.toEndToken();
                return xWPFParagraph;
            } catch (Throwable th) {
                newCursor2.dispose();
                throw th;
            }
        } finally {
            newCursor.dispose();
        }
    }

    /* JADX INFO: finally extract failed */
    public XWPFTable insertNewTbl(XmlCursor xmlCursor) {
        boolean z;
        XmlObject xmlObject = null;
        if (!isCursorInTableCell(xmlCursor)) {
            return null;
        }
        xmlCursor.beginElement("tbl", CTTbl.type.getName().getNamespaceURI());
        xmlCursor.toParent();
        CTTbl cTTbl = (CTTbl) xmlCursor.getObject();
        XWPFTable xWPFTable = new XWPFTable(cTTbl, this);
        xmlCursor.removeXmlContents();
        while (true) {
            z = xmlObject instanceof CTTbl;
            if (z || !xmlCursor.toPrevSibling()) {
                int i = 0;
            } else {
                xmlObject = xmlCursor.getObject();
            }
        }
        int i2 = 0;
        if (!z) {
            this.tables.add(0, xWPFTable);
        } else {
            this.tables.add(this.tables.indexOf(getTable((CTTbl) xmlObject)) + 1, xWPFTable);
        }
        XmlCursor newCursor = cTTbl.newCursor();
        while (newCursor.toPrevSibling()) {
            try {
                XmlObject object = newCursor.getObject();
                if ((object instanceof CTP) || (object instanceof CTTbl)) {
                    i2++;
                }
            } catch (Throwable th) {
                newCursor.dispose();
                throw th;
            }
        }
        newCursor.dispose();
        this.bodyElements.add(i2, xWPFTable);
        XmlCursor newCursor2 = cTTbl.newCursor();
        try {
            xmlCursor.toCursor(newCursor2);
            xmlCursor.toEndToken();
            return xWPFTable;
        } finally {
            newCursor2.dispose();
        }
    }

    private boolean isCursorInTableCell(XmlCursor xmlCursor) {
        XmlCursor newCursor = xmlCursor.newCursor();
        try {
            newCursor.toParent();
            return newCursor.getObject() == this.ctTc;
        } finally {
            newCursor.dispose();
        }
    }

    public XWPFParagraph getParagraphArray(int i) {
        if (i < 0 || i >= this.paragraphs.size()) {
            return null;
        }
        return this.paragraphs.get(i);
    }

    public POIXMLDocumentPart getPart() {
        return this.tableRow.getTable().getPart();
    }

    public BodyType getPartType() {
        return BodyType.TABLECELL;
    }

    public XWPFTable getTable(CTTbl cTTbl) {
        for (int i = 0; i < this.tables.size(); i++) {
            if (getTables().get(i).getCTTbl() == cTTbl) {
                return getTables().get(i);
            }
        }
        return null;
    }

    public XWPFTable getTableArray(int i) {
        if (i < 0 || i >= this.tables.size()) {
            return null;
        }
        return this.tables.get(i);
    }

    public List<XWPFTable> getTables() {
        return Collections.unmodifiableList(this.tables);
    }

    public void insertTable(int i, XWPFTable xWPFTable) {
        this.bodyElements.add(i, xWPFTable);
        Iterator<CTTbl> it = this.ctTc.getTblList().iterator();
        int i2 = 0;
        while (it.hasNext() && it.next() != xWPFTable.getCTTbl()) {
            i2++;
        }
        this.tables.add(i2, xWPFTable);
    }

    public void removeTable(int i) {
        this.tables.remove(i);
        this.ctTc.removeTbl(i);
        this.bodyElements.remove(this.tables.get(i));
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (XWPFParagraph text : this.paragraphs) {
            sb.append(text.getText());
        }
        return sb.toString();
    }

    public void setText(String str) {
        (this.paragraphs.isEmpty() ? addParagraph() : this.paragraphs.get(0)).createRun().setText(str);
    }

    public String getTextRecursively() {
        StringBuilder sb = new StringBuilder(64);
        for (int i = 0; i < this.bodyElements.size(); i++) {
            boolean z = true;
            if (i != this.bodyElements.size() - 1) {
                z = false;
            }
            appendBodyElementText(sb, this.bodyElements.get(i), z);
        }
        return sb.toString();
    }

    private void appendBodyElementText(StringBuilder sb, IBodyElement iBodyElement, boolean z) {
        if (iBodyElement instanceof XWPFParagraph) {
            sb.append(((XWPFParagraph) iBodyElement).getText());
            if (!z) {
                sb.append(9);
            }
        } else if (iBodyElement instanceof XWPFTable) {
            for (XWPFTableRow tableCells : ((XWPFTable) iBodyElement).getRows()) {
                for (XWPFTableCell bodyElements2 : tableCells.getTableCells()) {
                    List<IBodyElement> bodyElements3 = bodyElements2.getBodyElements();
                    for (int i = 0; i < bodyElements3.size(); i++) {
                        boolean z2 = true;
                        if (i != bodyElements3.size() - 1) {
                            z2 = false;
                        }
                        appendBodyElementText(sb, bodyElements3.get(i), z2);
                    }
                }
            }
            if (!z) {
                sb.append(10);
            }
        } else if (iBodyElement instanceof XWPFSDT) {
            sb.append(((XWPFSDT) iBodyElement).getContent().getText());
            if (!z) {
                sb.append(9);
            }
        }
    }

    public XWPFTableCell getTableCell(CTTc cTTc) {
        XWPFTable table;
        XWPFTableRow row;
        XmlCursor newCursor = cTTc.newCursor();
        try {
            newCursor.toParent();
            XmlObject object = newCursor.getObject();
            if (!(object instanceof CTRow)) {
                return null;
            }
            CTRow cTRow = (CTRow) object;
            newCursor.toParent();
            XmlObject object2 = newCursor.getObject();
            newCursor.dispose();
            if (!(object2 instanceof CTTbl) || (table = getTable((CTTbl) object2)) == null || (row = table.getRow(cTRow)) == null) {
                return null;
            }
            return row.getTableCell(cTTc);
        } finally {
            newCursor.dispose();
        }
    }

    public XWPFDocument getXWPFDocument() {
        return this.part.getXWPFDocument();
    }

    public double getWidthDecimal() {
        return XWPFTable.getWidthDecimal(getTcWidth());
    }

    public TableWidthType getWidthType() {
        return XWPFTable.getWidthType(getTcWidth());
    }

    public void setWidth(String str) {
        XWPFTable.setWidthValue(str, getTcWidth());
    }

    private CTTblWidth getTcWidth() {
        CTTcPr tcPr = getTcPr();
        return tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
    }

    /* access modifiers changed from: protected */
    public CTTcPr getTcPr() {
        boolean isSetTcPr = this.ctTc.isSetTcPr();
        CTTc cTTc = this.ctTc;
        return isSetTcPr ? cTTc.getTcPr() : cTTc.addNewTcPr();
    }

    public void setWidthType(TableWidthType tableWidthType) {
        XWPFTable.setWidthType(tableWidthType, getTcWidth());
    }

    public int getWidth() {
        return (int) Units.toDXA(POIXMLUnits.parseLength(getTcWidth().xgetW()));
    }
}
