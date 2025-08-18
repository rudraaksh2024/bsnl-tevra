package org.apache.poi.xdgf.xml;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.usermodel.XDGFDocument;

public class XDGFXMLDocumentPart extends POIXMLDocumentPart {
    protected XDGFDocument _document;

    public XDGFXMLDocumentPart(PackagePart packagePart) {
        super(packagePart);
    }

    @Internal
    public void setDocument(XDGFDocument xDGFDocument) {
        this._document = xDGFDocument;
    }
}
