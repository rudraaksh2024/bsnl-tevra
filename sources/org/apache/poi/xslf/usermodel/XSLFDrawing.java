package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;

public class XSLFDrawing {
    private XSLFSheet _sheet;
    private CTGroupShape _spTree;

    XSLFDrawing(XSLFSheet xSLFSheet, CTGroupShape cTGroupShape) {
        this._sheet = xSLFSheet;
        this._spTree = cTGroupShape;
        for (XmlObject xmlObject : xSLFSheet.getSpTree().selectPath("declare namespace p='http://schemas.openxmlformats.org/presentationml/2006/main' .//*/p:cNvPr")) {
            if (xmlObject instanceof CTNonVisualDrawingProps) {
                xSLFSheet.registerShapeId((int) ((CTNonVisualDrawingProps) xmlObject).getId());
            }
        }
    }

    public XSLFAutoShape createAutoShape() {
        CTShape addNewSp = this._spTree.addNewSp();
        addNewSp.set(XSLFAutoShape.prototype(this._sheet.allocateShapeId()));
        XSLFAutoShape xSLFAutoShape = new XSLFAutoShape(addNewSp, this._sheet);
        xSLFAutoShape.setAnchor(new Rectangle2D.Double());
        return xSLFAutoShape;
    }

    public XSLFFreeformShape createFreeform() {
        CTShape addNewSp = this._spTree.addNewSp();
        addNewSp.set(XSLFFreeformShape.prototype(this._sheet.allocateShapeId()));
        XSLFFreeformShape xSLFFreeformShape = new XSLFFreeformShape(addNewSp, this._sheet);
        xSLFFreeformShape.setAnchor(new Rectangle2D.Double());
        return xSLFFreeformShape;
    }

    public XSLFTextBox createTextBox() {
        CTShape addNewSp = this._spTree.addNewSp();
        addNewSp.set(XSLFTextBox.prototype(this._sheet.allocateShapeId()));
        XSLFTextBox xSLFTextBox = new XSLFTextBox(addNewSp, this._sheet);
        xSLFTextBox.setAnchor(new Rectangle2D.Double());
        return xSLFTextBox;
    }

    public XSLFConnectorShape createConnector() {
        CTConnector addNewCxnSp = this._spTree.addNewCxnSp();
        addNewCxnSp.set(XSLFConnectorShape.prototype(this._sheet.allocateShapeId()));
        XSLFConnectorShape xSLFConnectorShape = new XSLFConnectorShape(addNewCxnSp, this._sheet);
        xSLFConnectorShape.setAnchor(new Rectangle2D.Double());
        xSLFConnectorShape.setLineColor(Color.black);
        xSLFConnectorShape.setLineWidth(0.75d);
        return xSLFConnectorShape;
    }

    public XSLFGroupShape createGroup() {
        CTGroupShape addNewGrpSp = this._spTree.addNewGrpSp();
        addNewGrpSp.set(XSLFGroupShape.prototype(this._sheet.allocateShapeId()));
        XSLFGroupShape xSLFGroupShape = new XSLFGroupShape(addNewGrpSp, this._sheet);
        xSLFGroupShape.setAnchor(new Rectangle2D.Double());
        return xSLFGroupShape;
    }

    public XSLFPictureShape createPicture(String str) {
        CTPicture addNewPic = this._spTree.addNewPic();
        addNewPic.set(XSLFPictureShape.prototype(this._sheet.allocateShapeId(), str));
        XSLFPictureShape xSLFPictureShape = new XSLFPictureShape(addNewPic, this._sheet);
        xSLFPictureShape.setAnchor(new Rectangle2D.Double());
        return xSLFPictureShape;
    }

    public XSLFTable createTable() {
        CTGraphicalObjectFrame addNewGraphicFrame = this._spTree.addNewGraphicFrame();
        addNewGraphicFrame.set(XSLFTable.prototype(this._sheet.allocateShapeId()));
        XSLFTable xSLFTable = new XSLFTable(addNewGraphicFrame, this._sheet);
        xSLFTable.setAnchor(new Rectangle2D.Double());
        return xSLFTable;
    }

    public void addChart(String str, Rectangle2D rectangle2D) {
        this._spTree.addNewGraphicFrame().set(XSLFChart.prototype(this._sheet.allocateShapeId(), str, rectangle2D));
    }

    public XSLFObjectShape createOleShape(String str) {
        CTGraphicalObjectFrame addNewGraphicFrame = this._spTree.addNewGraphicFrame();
        addNewGraphicFrame.set(XSLFObjectShape.prototype(this._sheet.allocateShapeId(), str));
        XSLFObjectShape xSLFObjectShape = new XSLFObjectShape(addNewGraphicFrame, this._sheet);
        xSLFObjectShape.setAnchor(new Rectangle2D.Double());
        return xSLFObjectShape;
    }
}
