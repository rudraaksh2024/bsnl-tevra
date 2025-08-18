package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

public class XWPFComment implements IBody {
    private List<IBodyElement> bodyElements = new ArrayList();
    protected XWPFComments comments;
    protected CTComment ctComment;
    protected XWPFDocument document;
    private List<XWPFParagraph> paragraphs = new ArrayList();
    private List<XWPFTable> tables = new ArrayList();

    public XWPFComment(CTComment cTComment, XWPFComments xWPFComments) {
        this.comments = xWPFComments;
        this.ctComment = cTComment;
        this.document = xWPFComments.getXWPFDocument();
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        XmlCursor newCursor = this.ctComment.newCursor();
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
        newCursor.dispose();
    }

    public POIXMLDocumentPart getPart() {
        return this.comments;
    }

    public BodyType getPartType() {
        return BodyType.COMMENT;
    }

    public List<IBodyElement> getBodyElements() {
        return Collections.unmodifiableList(this.bodyElements);
    }

    public List<XWPFParagraph> getParagraphs() {
        return Collections.unmodifiableList(this.paragraphs);
    }

    public List<XWPFTable> getTables() {
        return Collections.unmodifiableList(this.tables);
    }

    public XWPFParagraph getParagraph(CTP ctp) {
        for (XWPFParagraph next : this.paragraphs) {
            if (next.getCTP().equals(ctp)) {
                return next;
            }
        }
        return null;
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

    public XWPFParagraph getParagraphArray(int i) {
        if (i < 0 || i >= this.paragraphs.size()) {
            return null;
        }
        return this.paragraphs.get(i);
    }

    public XWPFTable getTableArray(int i) {
        if (i < 0 || i >= this.tables.size()) {
            return null;
        }
        return this.tables.get(i);
    }

    public XWPFParagraph insertNewParagraph(XmlCursor xmlCursor) {
        boolean z;
        XmlObject xmlObject = null;
        if (!isCursorInCmt(xmlCursor)) {
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
        xmlCursor.toCursor(newCursor);
        newCursor.dispose();
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
    }

    private boolean isCursorInCmt(XmlCursor xmlCursor) {
        XmlCursor newCursor = xmlCursor.newCursor();
        newCursor.toParent();
        boolean z = newCursor.getObject() == this.ctComment;
        newCursor.dispose();
        return z;
    }

    public XWPFTable insertNewTbl(XmlCursor xmlCursor) {
        boolean z;
        XmlObject xmlObject = null;
        if (!isCursorInCmt(xmlCursor)) {
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
        newCursor.dispose();
        this.bodyElements.add(i2, xWPFTable);
        XmlCursor newCursor2 = cTTbl.newCursor();
        xmlCursor.toCursor(newCursor2);
        xmlCursor.toEndToken();
        newCursor2.dispose();
        return xWPFTable;
    }

    public void insertTable(int i, XWPFTable xWPFTable) {
        this.bodyElements.add(i, xWPFTable);
        Iterator<CTTbl> it = this.ctComment.getTblList().iterator();
        int i2 = 0;
        while (it.hasNext() && it.next() != xWPFTable.getCTTbl()) {
            i2++;
        }
        this.tables.add(i2, xWPFTable);
    }

    public XWPFTableCell getTableCell(CTTc cTTc) {
        XWPFTable table;
        XmlCursor newCursor = cTTc.newCursor();
        try {
            newCursor.toParent();
            XmlObject object = newCursor.getObject();
            if (!(object instanceof CTRow)) {
                newCursor.dispose();
                return null;
            }
            CTRow cTRow = (CTRow) object;
            newCursor.toParent();
            XmlObject object2 = newCursor.getObject();
            newCursor.dispose();
            if ((object2 instanceof CTTbl) && (table = getTable((CTTbl) object2)) != null) {
                return table.getRow(cTRow).getTableCell(cTTc);
            }
            return null;
        } finally {
            newCursor.dispose();
        }
    }

    public XWPFDocument getXWPFDocument() {
        return this.document;
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (XWPFParagraph next : this.paragraphs) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(next.getText());
        }
        return sb.toString();
    }

    public XWPFParagraph createParagraph() {
        XWPFParagraph xWPFParagraph = new XWPFParagraph(this.ctComment.addNewP(), this);
        this.paragraphs.add(xWPFParagraph);
        this.bodyElements.add(xWPFParagraph);
        return xWPFParagraph;
    }

    public void removeParagraph(XWPFParagraph xWPFParagraph) {
        if (this.paragraphs.contains(xWPFParagraph)) {
            XmlCursor newCursor = xWPFParagraph.getCTP().newCursor();
            newCursor.removeXml();
            newCursor.dispose();
            this.paragraphs.remove(xWPFParagraph);
            this.bodyElements.remove(xWPFParagraph);
        }
    }

    public void removeTable(XWPFTable xWPFTable) {
        if (this.tables.contains(xWPFTable)) {
            XmlCursor newCursor = xWPFTable.getCTTbl().newCursor();
            newCursor.removeXml();
            newCursor.dispose();
            this.tables.remove(xWPFTable);
            this.bodyElements.remove(xWPFTable);
        }
    }

    public XWPFTable createTable(int i, int i2) {
        XWPFTable xWPFTable = new XWPFTable(this.ctComment.addNewTbl(), this, i, i2);
        this.tables.add(xWPFTable);
        this.bodyElements.add(xWPFTable);
        return xWPFTable;
    }

    public CTComment getCtComment() {
        return this.ctComment;
    }

    public XWPFComments getComments() {
        return this.comments;
    }

    public String getId() {
        return this.ctComment.getId().toString();
    }

    public String getAuthor() {
        return this.ctComment.getAuthor();
    }

    public void setAuthor(String str) {
        this.ctComment.setAuthor(str);
    }

    public String getInitials() {
        return this.ctComment.getInitials();
    }

    public void setInitials(String str) {
        this.ctComment.setInitials(str);
    }

    public Calendar getDate() {
        return this.ctComment.getDate();
    }

    public void setDate(Calendar calendar) {
        this.ctComment.setDate(calendar);
    }
}
