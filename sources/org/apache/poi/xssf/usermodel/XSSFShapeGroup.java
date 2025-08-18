package org.apache.poi.xssf.usermodel;

import java.util.Iterator;
import java.util.Spliterator;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.usermodel.ShapeContainer;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;

public final class XSSFShapeGroup extends XSSFShape implements ShapeContainer<XSSFShape> {
    private static CTGroupShape prototype;
    private CTGroupShape ctGroup;

    protected XSSFShapeGroup(XSSFDrawing xSSFDrawing, CTGroupShape cTGroupShape) {
        this.drawing = xSSFDrawing;
        this.ctGroup = cTGroupShape;
    }

    protected static CTGroupShape prototype() {
        if (prototype == null) {
            CTGroupShape newInstance = CTGroupShape.Factory.newInstance();
            CTGroupShapeNonVisual addNewNvGrpSpPr = newInstance.addNewNvGrpSpPr();
            CTNonVisualDrawingProps addNewCNvPr = addNewNvGrpSpPr.addNewCNvPr();
            addNewCNvPr.setId(0);
            addNewCNvPr.setName("Group 0");
            addNewNvGrpSpPr.addNewCNvGrpSpPr();
            CTGroupTransform2D addNewXfrm = newInstance.addNewGrpSpPr().addNewXfrm();
            CTPositiveSize2D addNewExt = addNewXfrm.addNewExt();
            addNewExt.setCx(0);
            addNewExt.setCy(0);
            CTPoint2D addNewOff = addNewXfrm.addNewOff();
            addNewOff.setX(0);
            addNewOff.setY(0);
            CTPositiveSize2D addNewChExt = addNewXfrm.addNewChExt();
            addNewChExt.setCx(0);
            addNewChExt.setCy(0);
            CTPoint2D addNewChOff = addNewXfrm.addNewChOff();
            addNewChOff.setX(0);
            addNewChOff.setY(0);
            prototype = newInstance;
        }
        return prototype;
    }

    public XSSFTextBox createTextbox(XSSFChildAnchor xSSFChildAnchor) {
        CTShape addNewSp = this.ctGroup.addNewSp();
        addNewSp.set(XSSFSimpleShape.prototype());
        XSSFTextBox xSSFTextBox = new XSSFTextBox(getDrawing(), addNewSp);
        xSSFTextBox.parent = this;
        xSSFTextBox.anchor = xSSFChildAnchor;
        xSSFTextBox.setXfrm(xSSFChildAnchor.getCTTransform2D());
        return xSSFTextBox;
    }

    public XSSFSimpleShape createSimpleShape(XSSFChildAnchor xSSFChildAnchor) {
        CTShape addNewSp = this.ctGroup.addNewSp();
        addNewSp.set(XSSFSimpleShape.prototype());
        XSSFSimpleShape xSSFSimpleShape = new XSSFSimpleShape(getDrawing(), addNewSp);
        xSSFSimpleShape.parent = this;
        xSSFSimpleShape.anchor = xSSFChildAnchor;
        xSSFSimpleShape.setXfrm(xSSFChildAnchor.getCTTransform2D());
        return xSSFSimpleShape;
    }

    public XSSFConnector createConnector(XSSFChildAnchor xSSFChildAnchor) {
        CTConnector addNewCxnSp = this.ctGroup.addNewCxnSp();
        addNewCxnSp.set(XSSFConnector.prototype());
        XSSFConnector xSSFConnector = new XSSFConnector(getDrawing(), addNewCxnSp);
        xSSFConnector.parent = this;
        xSSFConnector.anchor = xSSFChildAnchor;
        xSSFConnector.getCTConnector().getSpPr().setXfrm(xSSFChildAnchor.getCTTransform2D());
        return xSSFConnector;
    }

    public XSSFPicture createPicture(XSSFClientAnchor xSSFClientAnchor, int i) {
        PackageRelationship addPictureReference = getDrawing().addPictureReference(i);
        CTPicture addNewPic = this.ctGroup.addNewPic();
        addNewPic.set(XSSFPicture.prototype());
        XSSFPicture xSSFPicture = new XSSFPicture(getDrawing(), addNewPic);
        xSSFPicture.parent = this;
        xSSFPicture.anchor = xSSFClientAnchor;
        xSSFPicture.setPictureReference(addPictureReference);
        return xSSFPicture;
    }

    public XSSFShapeGroup createGroup(XSSFChildAnchor xSSFChildAnchor) {
        CTGroupShape addNewGrpSp = this.ctGroup.addNewGrpSp();
        addNewGrpSp.set(prototype());
        XSSFShapeGroup xSSFShapeGroup = new XSSFShapeGroup(getDrawing(), addNewGrpSp);
        xSSFShapeGroup.parent = this;
        xSSFShapeGroup.anchor = xSSFChildAnchor;
        CTGroupTransform2D xfrm = xSSFShapeGroup.getCTGroupShape().getGrpSpPr().getXfrm();
        CTTransform2D cTTransform2D = xSSFChildAnchor.getCTTransform2D();
        xfrm.setOff(cTTransform2D.getOff());
        xfrm.setExt(cTTransform2D.getExt());
        xfrm.setChExt(cTTransform2D.getExt());
        xfrm.setFlipH(cTTransform2D.getFlipH());
        xfrm.setFlipV(cTTransform2D.getFlipV());
        return xSSFShapeGroup;
    }

    @Internal
    public CTGroupShape getCTGroupShape() {
        return this.ctGroup;
    }

    public void setCoordinates(int i, int i2, int i3, int i4) {
        CTGroupTransform2D xfrm = this.ctGroup.getGrpSpPr().getXfrm();
        CTPoint2D off = xfrm.getOff();
        off.setX(Integer.valueOf(i));
        off.setY(Integer.valueOf(i2));
        CTPositiveSize2D ext = xfrm.getExt();
        long j = (long) i3;
        ext.setCx(j);
        long j2 = (long) i4;
        ext.setCy(j2);
        CTPoint2D chOff = xfrm.getChOff();
        chOff.setX(Integer.valueOf(i));
        chOff.setY(Integer.valueOf(i2));
        CTPositiveSize2D chExt = xfrm.getChExt();
        chExt.setCx(j);
        chExt.setCy(j2);
    }

    /* access modifiers changed from: protected */
    public CTShapeProperties getShapeProperties() {
        throw new IllegalStateException("Not supported for shape group");
    }

    public Iterator<XSSFShape> iterator() {
        return getDrawing().getShapes(this).iterator();
    }

    public Spliterator<XSSFShape> spliterator() {
        return getDrawing().getShapes(this).spliterator();
    }

    public String getShapeName() {
        return this.ctGroup.getNvGrpSpPr().getCNvPr().getName();
    }
}
