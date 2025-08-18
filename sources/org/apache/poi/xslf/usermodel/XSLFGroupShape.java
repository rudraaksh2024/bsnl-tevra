package org.apache.poi.xslf.usermodel;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.Units;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnector;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPicture;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;

public class XSLFGroupShape extends XSLFShape implements XSLFShapeContainer, GroupShape<XSLFShape, XSLFTextParagraph> {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSLFGroupShape.class);
    private XSLFDrawing _drawing;
    private final CTGroupShapeProperties _grpSpPr;
    private final List<XSLFShape> _shapes;

    protected XSLFGroupShape(CTGroupShape cTGroupShape, XSLFSheet xSLFSheet) {
        super(cTGroupShape, xSLFSheet);
        this._shapes = XSLFSheet.buildShapes(cTGroupShape, this);
        this._grpSpPr = cTGroupShape.getGrpSpPr();
    }

    /* access modifiers changed from: protected */
    public CTGroupShapeProperties getGrpSpPr() {
        return this._grpSpPr;
    }

    private CTGroupTransform2D getSafeXfrm() {
        CTGroupTransform2D xfrm = getXfrm();
        return xfrm == null ? getGrpSpPr().addNewXfrm() : xfrm;
    }

    /* access modifiers changed from: protected */
    public CTGroupTransform2D getXfrm() {
        return getGrpSpPr().getXfrm();
    }

    public Rectangle2D getAnchor() {
        CTGroupTransform2D xfrm = getXfrm();
        CTPoint2D off = xfrm.getOff();
        double points = Units.toPoints(POIXMLUnits.parseLength(off.xgetX()));
        double points2 = Units.toPoints(POIXMLUnits.parseLength(off.xgetY()));
        CTPositiveSize2D ext = xfrm.getExt();
        return new Rectangle2D.Double(points, points2, Units.toPoints(ext.getCx()), Units.toPoints(ext.getCy()));
    }

    public void setAnchor(Rectangle2D rectangle2D) {
        CTGroupTransform2D safeXfrm = getSafeXfrm();
        CTPoint2D off = safeXfrm.isSetOff() ? safeXfrm.getOff() : safeXfrm.addNewOff();
        off.setX(Long.valueOf((long) Units.toEMU(rectangle2D.getX())));
        off.setY(Long.valueOf((long) Units.toEMU(rectangle2D.getY())));
        CTPositiveSize2D ext = safeXfrm.isSetExt() ? safeXfrm.getExt() : safeXfrm.addNewExt();
        ext.setCx((long) Units.toEMU(rectangle2D.getWidth()));
        ext.setCy((long) Units.toEMU(rectangle2D.getHeight()));
    }

    public Rectangle2D getInteriorAnchor() {
        CTGroupTransform2D xfrm = getXfrm();
        CTPoint2D chOff = xfrm.getChOff();
        double points = Units.toPoints(POIXMLUnits.parseLength(chOff.xgetX()));
        double points2 = Units.toPoints(POIXMLUnits.parseLength(chOff.xgetY()));
        CTPositiveSize2D chExt = xfrm.getChExt();
        return new Rectangle2D.Double(points, points2, Units.toPoints(chExt.getCx()), Units.toPoints(chExt.getCy()));
    }

    public void setInteriorAnchor(Rectangle2D rectangle2D) {
        CTGroupTransform2D safeXfrm = getSafeXfrm();
        CTPoint2D chOff = safeXfrm.isSetChOff() ? safeXfrm.getChOff() : safeXfrm.addNewChOff();
        chOff.setX(Long.valueOf((long) Units.toEMU(rectangle2D.getX())));
        chOff.setY(Long.valueOf((long) Units.toEMU(rectangle2D.getY())));
        CTPositiveSize2D chExt = safeXfrm.isSetChExt() ? safeXfrm.getChExt() : safeXfrm.addNewChExt();
        chExt.setCx((long) Units.toEMU(rectangle2D.getWidth()));
        chExt.setCy((long) Units.toEMU(rectangle2D.getHeight()));
    }

    public List<XSLFShape> getShapes() {
        return this._shapes;
    }

    public Iterator<XSLFShape> iterator() {
        return this._shapes.iterator();
    }

    public boolean removeShape(XSLFShape xSLFShape) {
        XmlObject xmlObject = xSLFShape.getXmlObject();
        CTGroupShape cTGroupShape = (CTGroupShape) getXmlObject();
        getSheet().deregisterShapeId(xSLFShape.getShapeId());
        if (xmlObject instanceof CTShape) {
            cTGroupShape.getSpList().remove(xmlObject);
        } else if (xmlObject instanceof CTGroupShape) {
            XSLFGroupShape xSLFGroupShape = (XSLFGroupShape) xSLFShape;
            ArrayList arrayList = new ArrayList(xSLFGroupShape.getShapes());
            xSLFGroupShape.getClass();
            arrayList.forEach(new XSLFGroupShape$$ExternalSyntheticLambda0(xSLFGroupShape));
            cTGroupShape.getGrpSpList().remove(xmlObject);
        } else if (xmlObject instanceof CTConnector) {
            cTGroupShape.getCxnSpList().remove(xmlObject);
        } else if (xmlObject instanceof CTGraphicalObjectFrame) {
            cTGroupShape.getGraphicFrameList().remove(xmlObject);
        } else if (xmlObject instanceof CTPicture) {
            XSLFPictureShape xSLFPictureShape = (XSLFPictureShape) xSLFShape;
            XSLFSheet sheet = getSheet();
            if (sheet != null) {
                sheet.removePictureRelation(xSLFPictureShape);
            }
            cTGroupShape.getPicList().remove(xmlObject);
        } else {
            throw new IllegalArgumentException("Unsupported shape: " + xSLFShape);
        }
        return this._shapes.remove(xSLFShape);
    }

    static CTGroupShape prototype(int i) {
        CTGroupShape newInstance = CTGroupShape.Factory.newInstance();
        CTGroupShapeNonVisual addNewNvGrpSpPr = newInstance.addNewNvGrpSpPr();
        CTNonVisualDrawingProps addNewCNvPr = addNewNvGrpSpPr.addNewCNvPr();
        addNewCNvPr.setName("Group " + i);
        addNewCNvPr.setId((long) i);
        addNewNvGrpSpPr.addNewCNvGrpSpPr();
        addNewNvGrpSpPr.addNewNvPr();
        newInstance.addNewGrpSpPr();
        return newInstance;
    }

    private XSLFDrawing getDrawing() {
        if (this._drawing == null) {
            this._drawing = new XSLFDrawing(getSheet(), (CTGroupShape) getXmlObject());
        }
        return this._drawing;
    }

    public XSLFAutoShape createAutoShape() {
        XSLFAutoShape createAutoShape = getDrawing().createAutoShape();
        this._shapes.add(createAutoShape);
        createAutoShape.setParent(this);
        return createAutoShape;
    }

    public XSLFFreeformShape createFreeform() {
        XSLFFreeformShape createFreeform = getDrawing().createFreeform();
        this._shapes.add(createFreeform);
        createFreeform.setParent(this);
        return createFreeform;
    }

    public XSLFTextBox createTextBox() {
        XSLFTextBox createTextBox = getDrawing().createTextBox();
        this._shapes.add(createTextBox);
        createTextBox.setParent(this);
        return createTextBox;
    }

    public XSLFConnectorShape createConnector() {
        XSLFConnectorShape createConnector = getDrawing().createConnector();
        this._shapes.add(createConnector);
        createConnector.setParent(this);
        return createConnector;
    }

    public XSLFGroupShape createGroup() {
        XSLFGroupShape createGroup = getDrawing().createGroup();
        this._shapes.add(createGroup);
        createGroup.setParent(this);
        return createGroup;
    }

    public XSLFPictureShape createPicture(PictureData pictureData) {
        if (pictureData instanceof XSLFPictureData) {
            XSLFPictureShape createPicture = getDrawing().createPicture(getSheet().addRelation((String) null, XSLFRelation.IMAGES, (XSLFPictureData) pictureData).getRelationship().getId());
            new DrawPictureShape(createPicture).resize();
            this._shapes.add(createPicture);
            createPicture.setParent(this);
            return createPicture;
        }
        throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
    }

    public XSLFObjectShape createOleShape(PictureData pictureData) {
        if (pictureData instanceof XSLFPictureData) {
            XSLFObjectShape createOleShape = getDrawing().createOleShape(getSheet().addRelation((String) null, XSLFRelation.IMAGES, (XSLFPictureData) pictureData).getRelationship().getId());
            CTOleObject cTOleObject = createOleShape.getCTOleObject();
            Dimension imageDimension = pictureData.getImageDimension();
            cTOleObject.setImgW(Units.toEMU(imageDimension.getWidth()));
            cTOleObject.setImgH(Units.toEMU(imageDimension.getHeight()));
            getShapes().add(createOleShape);
            createOleShape.setParent(this);
            return createOleShape;
        }
        throw new IllegalArgumentException("pictureData needs to be of type XSLFPictureData");
    }

    public XSLFTable createTable() {
        XSLFTable createTable = getDrawing().createTable();
        this._shapes.add(createTable);
        createTable.setParent(this);
        return createTable;
    }

    public XSLFTable createTable(int i, int i2) {
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("numRows and numCols must be greater than 0");
        }
        XSLFTable createTable = getDrawing().createTable();
        this._shapes.add(createTable);
        createTable.setParent(this);
        for (int i3 = 0; i3 < i; i3++) {
            XSLFTableRow addRow = createTable.addRow();
            for (int i4 = 0; i4 < i2; i4++) {
                addRow.addCell();
            }
        }
        return createTable;
    }

    public void setFlipHorizontal(boolean z) {
        getSafeXfrm().setFlipH(z);
    }

    public void setFlipVertical(boolean z) {
        getSafeXfrm().setFlipV(z);
    }

    public boolean getFlipHorizontal() {
        CTGroupTransform2D xfrm = getXfrm();
        return xfrm != null && xfrm.isSetFlipH() && xfrm.getFlipH();
    }

    public boolean getFlipVertical() {
        CTGroupTransform2D xfrm = getXfrm();
        return xfrm != null && xfrm.isSetFlipV() && xfrm.getFlipV();
    }

    public void setRotation(double d) {
        getSafeXfrm().setRot((int) (d * 60000.0d));
    }

    public double getRotation() {
        CTGroupTransform2D xfrm = getXfrm();
        if (xfrm == null || !xfrm.isSetRot()) {
            return 0.0d;
        }
        return ((double) xfrm.getRot()) / 60000.0d;
    }

    /* access modifiers changed from: package-private */
    public void copy(XSLFShape xSLFShape) {
        XSLFShape xSLFShape2;
        List<XSLFShape> shapes = getShapes();
        List<XSLFShape> shapes2 = ((XSLFGroupShape) xSLFShape).getShapes();
        if (shapes.size() == shapes2.size()) {
            for (int i = 0; i < shapes.size(); i++) {
                shapes.get(i).copy(shapes2.get(i));
            }
            return;
        }
        clear();
        for (XSLFShape next : shapes2) {
            if (next instanceof XSLFTextBox) {
                xSLFShape2 = createTextBox();
            } else if (next instanceof XSLFFreeformShape) {
                xSLFShape2 = createFreeform();
            } else if (next instanceof XSLFAutoShape) {
                xSLFShape2 = createAutoShape();
            } else if (next instanceof XSLFConnectorShape) {
                xSLFShape2 = createConnector();
            } else if (next instanceof XSLFPictureShape) {
                XSLFPictureData pictureData = ((XSLFPictureShape) next).getPictureData();
                xSLFShape2 = createPicture((PictureData) getSheet().getSlideShow().addPicture(pictureData.getData(), pictureData.getType()));
            } else if (next instanceof XSLFGroupShape) {
                xSLFShape2 = createGroup();
            } else if (next instanceof XSLFTable) {
                xSLFShape2 = createTable();
            } else {
                LOG.atWarn().log("copying of class {} not supported.", (Object) next.getClass());
            }
            xSLFShape2.copy(next);
        }
    }

    public void clear() {
        for (XSLFShape removeShape : new ArrayList(getShapes())) {
            removeShape(removeShape);
        }
    }

    public void addShape(XSLFShape xSLFShape) {
        throw new UnsupportedOperationException("Adding a shape from a different container is not supported - create it from scratch with XSLFGroupShape.create* methods");
    }
}
