package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;

public abstract class XWPFAbstractFootnotesEndnotes extends POIXMLDocumentPart {
    protected XWPFDocument document;
    private FootnoteEndnoteIdManager idManager;
    protected List<XWPFAbstractFootnoteEndnote> listFootnote = new ArrayList();

    public XWPFAbstractFootnotesEndnotes(OPCPackage oPCPackage) {
        super(oPCPackage);
    }

    public XWPFAbstractFootnotesEndnotes(OPCPackage oPCPackage, String str) {
        super(oPCPackage, str);
    }

    public XWPFAbstractFootnotesEndnotes() {
    }

    public XWPFAbstractFootnotesEndnotes(PackagePart packagePart) {
        super(packagePart);
    }

    public XWPFAbstractFootnotesEndnotes(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) {
        super(pOIXMLDocumentPart, packagePart);
    }

    public XWPFAbstractFootnoteEndnote getFootnoteById(int i) {
        for (XWPFAbstractFootnoteEndnote next : this.listFootnote) {
            if (next.getCTFtnEdn().getId().intValue() == i) {
                return next;
            }
        }
        return null;
    }

    public XWPFDocument getXWPFDocument() {
        XWPFDocument xWPFDocument = this.document;
        if (xWPFDocument != null) {
            return xWPFDocument;
        }
        return (XWPFDocument) getParent();
    }

    public void setXWPFDocument(XWPFDocument xWPFDocument) {
        this.document = xWPFDocument;
    }

    public void setIdManager(FootnoteEndnoteIdManager footnoteEndnoteIdManager) {
        this.idManager = footnoteEndnoteIdManager;
    }

    public FootnoteEndnoteIdManager getIdManager() {
        return this.idManager;
    }
}
