package org.apache.poi.xwpf.usermodel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

public abstract class XWPFAbstractFootnoteEndnote implements Iterable<XWPFParagraph>, IBody {
    private final List<IBodyElement> bodyElements = new ArrayList();
    protected CTFtnEdn ctFtnEdn;
    protected XWPFDocument document;
    protected XWPFAbstractFootnotesEndnotes footnotes;
    private final List<XWPFParagraph> paragraphs = new ArrayList();
    private final List<XWPFPictureData> pictures = new ArrayList();
    private final List<XWPFTable> tables = new ArrayList();

    public abstract void ensureFootnoteRef(XWPFParagraph xWPFParagraph);

    public XWPFAbstractFootnoteEndnote() {
    }

    @Internal
    protected XWPFAbstractFootnoteEndnote(XWPFDocument xWPFDocument, CTFtnEdn cTFtnEdn) {
        this.ctFtnEdn = cTFtnEdn;
        this.document = xWPFDocument;
        init();
    }

    @Internal
    protected XWPFAbstractFootnoteEndnote(CTFtnEdn cTFtnEdn, XWPFAbstractFootnotesEndnotes xWPFAbstractFootnotesEndnotes) {
        this.footnotes = xWPFAbstractFootnotesEndnotes;
        this.ctFtnEdn = cTFtnEdn;
        this.document = xWPFAbstractFootnotesEndnotes.getXWPFDocument();
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        XmlCursor newCursor = this.ctFtnEdn.newCursor();
        try {
            newCursor.selectPath("./*");
            while (newCursor.toNextSelection()) {
                XmlObject object = newCursor.getObject();
                if (object instanceof CTP) {
                    XWPFParagraph xWPFParagraph = new XWPFParagraph((CTP) object, this);
                    this.bodyElements.add(xWPFParagraph);
                    this.paragraphs.add(xWPFParagraph);
                } else if (object instanceof CTTbl) {
                    XWPFTable xWPFTable = new XWPFTable((CTTbl) object, this);
                    this.bodyElements.add(xWPFTable);
                    this.tables.add(xWPFTable);
                } else if (object instanceof CTSdtBlock) {
                    this.bodyElements.add(new XWPFSDT((CTSdtBlock) object, (IBody) this));
                }
            }
        } finally {
            newCursor.dispose();
        }
    }

    public List<XWPFParagraph> getParagraphs() {
        return this.paragraphs;
    }

    public Iterator<XWPFParagraph> iterator() {
        return this.paragraphs.iterator();
    }

    public Spliterator<XWPFParagraph> spliterator() {
        return this.paragraphs.spliterator();
    }

    public List<XWPFTable> getTables() {
        return this.tables;
    }

    public List<XWPFPictureData> getPictures() {
        return this.pictures;
    }

    public List<IBodyElement> getBodyElements() {
        return this.bodyElements;
    }

    public CTFtnEdn getCTFtnEdn() {
        return this.ctFtnEdn;
    }

    public void setCTFtnEdn(CTFtnEdn cTFtnEdn) {
        this.ctFtnEdn = cTFtnEdn;
    }

    public XWPFTable getTableArray(int i) {
        if (i < 0 || i >= this.tables.size()) {
            return null;
        }
        return this.tables.get(i);
    }

    public void insertTable(int i, XWPFTable xWPFTable) {
        this.bodyElements.add(i, xWPFTable);
        Iterator<CTTbl> it = this.ctFtnEdn.getTblList().iterator();
        int i2 = 0;
        while (it.hasNext() && it.next() != xWPFTable.getCTTbl()) {
            i2++;
        }
        this.tables.add(i2, xWPFTable);
    }

    public XWPFTable getTable(CTTbl cTTbl) {
        XWPFTable next;
        Iterator<XWPFTable> it = this.tables.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            if (next.getCTTbl().equals(cTTbl)) {
                return next;
            }
        }
        return null;
    }

    public XWPFParagraph getParagraph(CTP ctp) {
        for (XWPFParagraph next : this.paragraphs) {
            if (next.getCTP().equals(ctp)) {
                return next;
            }
        }
        return null;
    }

    public XWPFParagraph getParagraphArray(int i) {
        if (i < 0 || i >= this.paragraphs.size()) {
            return null;
        }
        return this.paragraphs.get(i);
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

    private boolean isCursorInFtn(XmlCursor xmlCursor) {
        XmlCursor newCursor = xmlCursor.newCursor();
        try {
            newCursor.toParent();
            return newCursor.getObject() == this.ctFtnEdn;
        } finally {
            newCursor.dispose();
        }
    }

    public POIXMLDocumentPart getOwner() {
        return this.footnotes;
    }

    public XWPFTable insertNewTbl(XmlCursor xmlCursor) {
        boolean z;
        XmlObject xmlObject = null;
        if (!isCursorInFtn(xmlCursor)) {
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
            XmlObject object = newCursor.getObject();
            if ((object instanceof CTP) || (object instanceof CTTbl)) {
                i2++;
            }
        }
        this.bodyElements.add(i2, xWPFTable);
        XmlCursor newCursor2 = cTTbl.newCursor();
        try {
            newCursor.toCursor(newCursor2);
            newCursor.toEndToken();
            return xWPFTable;
        } finally {
            newCursor2.dispose();
        }
    }

    public XWPFParagraph insertNewParagraph(XmlCursor xmlCursor) {
        boolean z;
        XmlObject xmlObject = null;
        if (!isCursorInFtn(xmlCursor)) {
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
            xmlCursor.toCursor(newCursor2);
            xmlCursor.toEndToken();
            newCursor2.dispose();
            return xWPFParagraph;
        } finally {
            newCursor.dispose();
        }
    }

    public XWPFTable addNewTbl(CTTbl cTTbl) {
        CTTbl addNewTbl = this.ctFtnEdn.addNewTbl();
        addNewTbl.set(cTTbl);
        XWPFTable xWPFTable = new XWPFTable(addNewTbl, this);
        this.tables.add(xWPFTable);
        return xWPFTable;
    }

    public XWPFParagraph addNewParagraph(CTP ctp) {
        CTP addNewP = this.ctFtnEdn.addNewP();
        addNewP.set(ctp);
        XWPFParagraph xWPFParagraph = new XWPFParagraph(addNewP, this);
        this.paragraphs.add(xWPFParagraph);
        return xWPFParagraph;
    }

    public XWPFDocument getXWPFDocument() {
        return this.document;
    }

    public POIXMLDocumentPart getPart() {
        return this.footnotes;
    }

    public BodyType getPartType() {
        return BodyType.FOOTNOTE;
    }

    public BigInteger getId() {
        return this.ctFtnEdn.getId();
    }

    public XWPFParagraph createParagraph() {
        XWPFParagraph xWPFParagraph = new XWPFParagraph(this.ctFtnEdn.addNewP(), this);
        this.paragraphs.add(xWPFParagraph);
        this.bodyElements.add(xWPFParagraph);
        if (xWPFParagraph.equals(getParagraphs().get(0))) {
            ensureFootnoteRef(xWPFParagraph);
        }
        return xWPFParagraph;
    }

    public XWPFTable createTable() {
        XWPFTable xWPFTable = new XWPFTable(this.ctFtnEdn.addNewTbl(), this);
        if (this.bodyElements.isEmpty()) {
            ensureFootnoteRef(createParagraph());
        }
        this.bodyElements.add(xWPFTable);
        this.tables.add(xWPFTable);
        return xWPFTable;
    }

    public XWPFTable createTable(int i, int i2) {
        XWPFTable xWPFTable = new XWPFTable(this.ctFtnEdn.addNewTbl(), this, i, i2);
        this.bodyElements.add(xWPFTable);
        this.tables.add(xWPFTable);
        return xWPFTable;
    }
}
