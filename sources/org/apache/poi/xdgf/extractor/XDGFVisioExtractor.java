package org.apache.poi.xdgf.extractor;

import java.io.IOException;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xdgf.usermodel.XDGFPage;
import org.apache.poi.xdgf.usermodel.XmlVisioDocument;
import org.apache.poi.xdgf.usermodel.shape.ShapeTextVisitor;

public class XDGFVisioExtractor implements POIXMLTextExtractor {
    private boolean doCloseFilesystem;
    protected final XmlVisioDocument document;

    public XDGFVisioExtractor(XmlVisioDocument xmlVisioDocument) {
        this.doCloseFilesystem = true;
        this.document = xmlVisioDocument;
    }

    public XDGFVisioExtractor(OPCPackage oPCPackage) throws IOException {
        this(new XmlVisioDocument(oPCPackage));
    }

    public String getText() {
        ShapeTextVisitor shapeTextVisitor = new ShapeTextVisitor();
        for (XDGFPage content : this.document.getPages()) {
            content.getContent().visitShapes(shapeTextVisitor);
        }
        return shapeTextVisitor.getText();
    }

    public XmlVisioDocument getDocument() {
        return this.document;
    }

    public void setCloseFilesystem(boolean z) {
        this.doCloseFilesystem = z;
    }

    public boolean isCloseFilesystem() {
        return this.doCloseFilesystem;
    }

    public XmlVisioDocument getFilesystem() {
        return this.document;
    }
}
