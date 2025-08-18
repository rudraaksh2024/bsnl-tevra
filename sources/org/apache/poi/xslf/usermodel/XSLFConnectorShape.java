package org.apache.poi.xslf.usermodel;

import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.sl.usermodel.ConnectorShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual;

public class XSLFConnectorShape extends XSLFSimpleShape implements ConnectorShape<XSLFShape, XSLFTextParagraph> {
    public XSLFShadow getShadow() {
        return null;
    }

    XSLFConnectorShape(CTConnector cTConnector, XSLFSheet xSLFSheet) {
        super(cTConnector, xSLFSheet);
    }

    static CTConnector prototype(int i) {
        CTConnector newInstance = CTConnector.Factory.newInstance();
        CTConnectorNonVisual addNewNvCxnSpPr = newInstance.addNewNvCxnSpPr();
        CTNonVisualDrawingProps addNewCNvPr = addNewNvCxnSpPr.addNewCNvPr();
        addNewCNvPr.setName("Connector " + i);
        addNewCNvPr.setId((long) i);
        addNewNvCxnSpPr.addNewCNvCxnSpPr();
        addNewNvCxnSpPr.addNewNvPr();
        CTShapeProperties addNewSpPr = newInstance.addNewSpPr();
        CTPresetGeometry2D addNewPrstGeom = addNewSpPr.addNewPrstGeom();
        addNewPrstGeom.setPrst(STShapeType.LINE);
        addNewPrstGeom.addNewAvLst();
        addNewSpPr.addNewLn();
        return newInstance;
    }

    public void setPlaceholder(Placeholder placeholder) {
        throw new POIXMLException("A connector shape can't be a placeholder.");
    }
}
