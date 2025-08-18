package org.apache.poi.xdgf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;

public class XDGFFactory extends POIXMLFactory {
    private final XDGFDocument document;

    public XDGFFactory(XDGFDocument xDGFDocument) {
        this.document = xDGFDocument;
    }

    /* access modifiers changed from: protected */
    public POIXMLRelation getDescriptor(String str) {
        return XDGFRelation.getInstance(str);
    }

    public POIXMLDocumentPart createDocumentPart(POIXMLDocumentPart pOIXMLDocumentPart, PackagePart packagePart) {
        POIXMLDocumentPart createDocumentPart = super.createDocumentPart(pOIXMLDocumentPart, packagePart);
        if (createDocumentPart instanceof XDGFXMLDocumentPart) {
            ((XDGFXMLDocumentPart) createDocumentPart).setDocument(this.document);
        }
        return createDocumentPart;
    }

    public POIXMLDocumentPart newDocumentPart(POIXMLRelation pOIXMLRelation) {
        POIXMLDocumentPart newDocumentPart = super.newDocumentPart(pOIXMLRelation);
        if (newDocumentPart instanceof XDGFXMLDocumentPart) {
            ((XDGFXMLDocumentPart) newDocumentPart).setDocument(this.document);
        }
        return newDocumentPart;
    }
}
