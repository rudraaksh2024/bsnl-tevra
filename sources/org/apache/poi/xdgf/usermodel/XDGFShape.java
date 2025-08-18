package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType;
import com.microsoft.schemas.office.visio.x2012.main.TextType;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.poi.xdgf.usermodel.section.GeometrySection;
import org.apache.poi.xdgf.usermodel.section.XDGFSection;
import org.apache.poi.xdgf.usermodel.shape.ShapeVisitor;
import org.apache.poi.xdgf.usermodel.shape.exceptions.StopVisitingThisBranch;

public class XDGFShape extends XDGFSheet {
    Double _angle;
    Double _beginX;
    Double _beginY;
    Double _endX;
    Double _endY;
    Boolean _flipX;
    Boolean _flipY;
    Double _height;
    Double _locPinX;
    Double _locPinY;
    XDGFMaster _master;
    XDGFShape _masterShape;
    XDGFShape _parent;
    XDGFBaseContents _parentPage;
    Double _pinX;
    Double _pinY;
    Double _rotationXAngle;
    Double _rotationYAngle;
    Double _rotationZAngle;
    List<XDGFShape> _shapes;
    XDGFText _text;
    Double _txtAngle;
    Double _txtHeight;
    Double _txtLocPinX;
    Double _txtLocPinY;
    Double _txtPinX;
    Double _txtPinY;
    Double _txtWidth;
    Double _width;

    public XDGFShape(ShapeSheetType shapeSheetType, XDGFBaseContents xDGFBaseContents, XDGFDocument xDGFDocument) {
        this((XDGFShape) null, shapeSheetType, xDGFBaseContents, xDGFDocument);
    }

    public XDGFShape(XDGFShape xDGFShape, ShapeSheetType shapeSheetType, XDGFBaseContents xDGFBaseContents, XDGFDocument xDGFDocument) {
        super(shapeSheetType, xDGFDocument);
        this._parent = xDGFShape;
        this._parentPage = xDGFBaseContents;
        TextType text = shapeSheetType.getText();
        if (text != null) {
            this._text = new XDGFText(text, this);
        }
        if (shapeSheetType.isSetShapes()) {
            this._shapes = new ArrayList();
            for (ShapeSheetType xDGFShape2 : shapeSheetType.getShapes().getShapeArray()) {
                this._shapes.add(new XDGFShape(this, xDGFShape2, xDGFBaseContents, xDGFDocument));
            }
        }
        readProperties();
    }

    public String toString() {
        if (this._parentPage instanceof XDGFMasterContents) {
            return this._parentPage + ": <Shape ID=\"" + getID() + "\">";
        }
        return "<Shape ID=\"" + getID() + "\">";
    }

    /* access modifiers changed from: protected */
    public void readProperties() {
        this._pinX = XDGFCell.maybeGetDouble(this._cells, "PinX");
        this._pinY = XDGFCell.maybeGetDouble(this._cells, "PinY");
        this._width = XDGFCell.maybeGetDouble(this._cells, "Width");
        this._height = XDGFCell.maybeGetDouble(this._cells, "Height");
        this._locPinX = XDGFCell.maybeGetDouble(this._cells, "LocPinX");
        this._locPinY = XDGFCell.maybeGetDouble(this._cells, "LocPinY");
        this._beginX = XDGFCell.maybeGetDouble(this._cells, "BeginX");
        this._beginY = XDGFCell.maybeGetDouble(this._cells, "BeginY");
        this._endX = XDGFCell.maybeGetDouble(this._cells, "EndX");
        this._endY = XDGFCell.maybeGetDouble(this._cells, "EndY");
        this._angle = XDGFCell.maybeGetDouble(this._cells, "Angle");
        this._rotationXAngle = XDGFCell.maybeGetDouble(this._cells, "RotationXAngle");
        this._rotationYAngle = XDGFCell.maybeGetDouble(this._cells, "RotationYAngle");
        this._rotationZAngle = XDGFCell.maybeGetDouble(this._cells, "RotationZAngle");
        this._flipX = XDGFCell.maybeGetBoolean(this._cells, "FlipX");
        this._flipY = XDGFCell.maybeGetBoolean(this._cells, "FlipY");
        this._txtPinX = XDGFCell.maybeGetDouble(this._cells, "TxtPinX");
        this._txtPinY = XDGFCell.maybeGetDouble(this._cells, "TxtPinY");
        this._txtLocPinX = XDGFCell.maybeGetDouble(this._cells, "TxtLocPinX");
        this._txtLocPinY = XDGFCell.maybeGetDouble(this._cells, "TxtLocPinY");
        this._txtWidth = XDGFCell.maybeGetDouble(this._cells, "TxtWidth");
        this._txtHeight = XDGFCell.maybeGetDouble(this._cells, "TxtHeight");
        this._txtAngle = XDGFCell.maybeGetDouble(this._cells, "TxtAngle");
    }

    /* access modifiers changed from: protected */
    public void setupMaster(XDGFPageContents xDGFPageContents, XDGFMasterContents xDGFMasterContents) {
        XDGFMasterContents xDGFMasterContents2;
        XDGFShape xDGFShape;
        ShapeSheetType xmlObject = getXmlObject();
        if (xmlObject.isSetMaster()) {
            XDGFMaster masterById = xDGFPageContents.getMasterById(xmlObject.getMaster());
            this._master = masterById;
            if (masterById != null) {
                List<XDGFShape> topLevelShapes = masterById.getContent().getTopLevelShapes();
                int size = topLevelShapes.size();
                if (size == 0) {
                    throw XDGFException.error("Could not retrieve master shape from " + this._master, this);
                } else if (size == 1) {
                    this._masterShape = topLevelShapes.iterator().next();
                }
            } else {
                throw XDGFException.error("refers to non-existant master " + xmlObject.getMaster(), this);
            }
        } else if (xmlObject.isSetMasterShape()) {
            if (xDGFMasterContents == null) {
                xDGFShape = null;
            } else {
                xDGFShape = xDGFMasterContents.getShapeById(xmlObject.getMasterShape());
            }
            this._masterShape = xDGFShape;
            if (xDGFShape == null) {
                throw XDGFException.error("refers to non-existant master shape " + xmlObject.getMasterShape(), this);
            }
        }
        setupSectionMasters();
        List<XDGFShape> list = this._shapes;
        if (list != null) {
            for (XDGFShape next : list) {
                XDGFMaster xDGFMaster = this._master;
                if (xDGFMaster == null) {
                    xDGFMasterContents2 = xDGFMasterContents;
                } else {
                    xDGFMasterContents2 = xDGFMaster.getContent();
                }
                next.setupMaster(xDGFPageContents, xDGFMasterContents2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setupSectionMasters() {
        if (this._masterShape != null) {
            try {
                for (Map.Entry entry : this._sections.entrySet()) {
                    XDGFSection section = this._masterShape.getSection((String) entry.getKey());
                    if (section != null) {
                        ((XDGFSection) entry.getValue()).setupMaster(section);
                    }
                }
                for (Map.Entry entry2 : this._geometry.entrySet()) {
                    GeometrySection geometryByIdx = this._masterShape.getGeometryByIdx(((Long) entry2.getKey()).longValue());
                    if (geometryByIdx != null) {
                        ((GeometrySection) entry2.getValue()).setupMaster(geometryByIdx);
                    }
                }
            } catch (POIXMLException e) {
                throw XDGFException.wrap(toString(), e);
            }
        }
    }

    @Internal
    public ShapeSheetType getXmlObject() {
        return (ShapeSheetType) this._sheet;
    }

    public long getID() {
        return getXmlObject().getID();
    }

    public String getType() {
        return getXmlObject().getType();
    }

    public String getTextAsString() {
        XDGFText text = getText();
        if (text == null) {
            return "";
        }
        return text.getTextContent();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasText() {
        /*
            r1 = this;
            org.apache.poi.xdgf.usermodel.XDGFText r0 = r1._text
            if (r0 != 0) goto L_0x000f
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFText r1 = r1._text
            if (r1 == 0) goto L_0x000d
            goto L_0x000f
        L_0x000d:
            r1 = 0
            goto L_0x0010
        L_0x000f:
            r1 = 1
        L_0x0010:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.hasText():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xdgf.usermodel.XDGFCell getCell(java.lang.String r2) {
        /*
            r1 = this;
            org.apache.poi.xdgf.usermodel.XDGFCell r0 = super.getCell(r2)
            if (r0 != 0) goto L_0x000e
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000e
            org.apache.poi.xdgf.usermodel.XDGFCell r0 = r1.getCell(r2)
        L_0x000e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.getCell(java.lang.String):org.apache.poi.xdgf.usermodel.XDGFCell");
    }

    public GeometrySection getGeometryByIdx(long j) {
        return (GeometrySection) this._geometry.get(Long.valueOf(j));
    }

    public List<XDGFShape> getShapes() {
        return this._shapes;
    }

    public String getName() {
        String name = getXmlObject().getName();
        return name == null ? "" : name;
    }

    public String getShapeType() {
        String type = getXmlObject().getType();
        return type == null ? "" : type;
    }

    public String getSymbolName() {
        String name;
        XDGFMaster xDGFMaster = this._master;
        if (xDGFMaster == null || (name = xDGFMaster.getName()) == null) {
            return "";
        }
        return name;
    }

    public XDGFShape getMasterShape() {
        return this._masterShape;
    }

    public XDGFShape getParentShape() {
        return this._parent;
    }

    public XDGFShape getTopmostParentShape() {
        XDGFShape xDGFShape = this._parent;
        if (xDGFShape == null) {
            return null;
        }
        XDGFShape topmostParentShape = xDGFShape.getTopmostParentShape();
        return topmostParentShape == null ? this._parent : topmostParentShape;
    }

    public boolean hasMaster() {
        return this._master != null;
    }

    public boolean hasMasterShape() {
        return this._masterShape != null;
    }

    public boolean hasParent() {
        return this._parent != null;
    }

    public boolean hasShapes() {
        return this._shapes != null;
    }

    public boolean isTopmost() {
        return this._parent == null;
    }

    public boolean isShape1D() {
        return getBeginX() != null;
    }

    public boolean isDeleted() {
        if (getXmlObject().isSetDel()) {
            return getXmlObject().getDel();
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.xdgf.usermodel.XDGFText getText() {
        /*
            r1 = this;
            org.apache.poi.xdgf.usermodel.XDGFText r0 = r1._text
            if (r0 != 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFText r1 = r1.getText()
            return r1
        L_0x000d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.getText():org.apache.poi.xdgf.usermodel.XDGFText");
    }

    public Double getPinX() {
        XDGFShape xDGFShape;
        Double d = this._pinX;
        if (d == null && (xDGFShape = this._masterShape) != null) {
            return xDGFShape.getPinX();
        }
        if (d != null) {
            return d;
        }
        throw XDGFException.error("PinX not set!", this);
    }

    public Double getPinY() {
        XDGFShape xDGFShape;
        Double d = this._pinY;
        if (d == null && (xDGFShape = this._masterShape) != null) {
            return xDGFShape.getPinY();
        }
        if (d != null) {
            return d;
        }
        throw XDGFException.error("PinY not specified!", this);
    }

    public Double getWidth() {
        XDGFShape xDGFShape;
        Double d = this._width;
        if (d == null && (xDGFShape = this._masterShape) != null) {
            return xDGFShape.getWidth();
        }
        if (d != null) {
            return d;
        }
        throw XDGFException.error("Width not specified!", this);
    }

    public Double getHeight() {
        XDGFShape xDGFShape;
        Double d = this._height;
        if (d == null && (xDGFShape = this._masterShape) != null) {
            return xDGFShape.getHeight();
        }
        if (d != null) {
            return d;
        }
        throw XDGFException.error("Height not specified!", this);
    }

    public Double getLocPinX() {
        XDGFShape xDGFShape;
        Double d = this._locPinX;
        if (d == null && (xDGFShape = this._masterShape) != null) {
            return xDGFShape.getLocPinX();
        }
        if (d != null) {
            return d;
        }
        throw XDGFException.error("LocPinX not specified!", this);
    }

    public Double getLocPinY() {
        XDGFShape xDGFShape;
        Double d = this._locPinY;
        if (d == null && (xDGFShape = this._masterShape) != null) {
            return xDGFShape.getLocPinY();
        }
        if (d != null) {
            return d;
        }
        throw XDGFException.error("LocPinY not specified!", this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Double getBeginX() {
        /*
            r1 = this;
            java.lang.Double r0 = r1._beginX
            if (r0 != 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000d
            java.lang.Double r1 = r1.getBeginX()
            return r1
        L_0x000d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.getBeginX():java.lang.Double");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Double getBeginY() {
        /*
            r1 = this;
            java.lang.Double r0 = r1._beginY
            if (r0 != 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000d
            java.lang.Double r1 = r1.getBeginY()
            return r1
        L_0x000d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.getBeginY():java.lang.Double");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Double getEndX() {
        /*
            r1 = this;
            java.lang.Double r0 = r1._endX
            if (r0 != 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000d
            java.lang.Double r1 = r1.getEndX()
            return r1
        L_0x000d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.getEndX():java.lang.Double");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Double getEndY() {
        /*
            r1 = this;
            java.lang.Double r0 = r1._endY
            if (r0 != 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000d
            java.lang.Double r1 = r1.getEndY()
            return r1
        L_0x000d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.getEndY():java.lang.Double");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Double getAngle() {
        /*
            r1 = this;
            java.lang.Double r0 = r1._angle
            if (r0 != 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000d
            java.lang.Double r1 = r1.getAngle()
            return r1
        L_0x000d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.getAngle():java.lang.Double");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Boolean getFlipX() {
        /*
            r1 = this;
            java.lang.Boolean r0 = r1._flipX
            if (r0 != 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000d
            java.lang.Boolean r1 = r1.getFlipX()
            return r1
        L_0x000d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.getFlipX():java.lang.Boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Boolean getFlipY() {
        /*
            r1 = this;
            java.lang.Boolean r0 = r1._flipY
            if (r0 != 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000d
            java.lang.Boolean r1 = r1.getFlipY()
            return r1
        L_0x000d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.getFlipY():java.lang.Boolean");
    }

    public Double getTxtPinX() {
        XDGFShape xDGFShape;
        Double d;
        Double d2 = this._txtPinX;
        if (d2 != null || (xDGFShape = this._masterShape) == null || (d = xDGFShape._txtPinX) == null) {
            return d2 == null ? Double.valueOf(getWidth().doubleValue() * 0.5d) : d2;
        }
        return d;
    }

    public Double getTxtPinY() {
        XDGFShape xDGFShape;
        Double d;
        Double d2 = this._txtPinY;
        if (d2 != null || (xDGFShape = this._masterShape) == null || (d = xDGFShape._txtPinY) == null) {
            return d2 == null ? Double.valueOf(getHeight().doubleValue() * 0.5d) : d2;
        }
        return d;
    }

    public Double getTxtLocPinX() {
        XDGFShape xDGFShape;
        Double d;
        Double d2 = this._txtLocPinX;
        if (d2 != null || (xDGFShape = this._masterShape) == null || (d = xDGFShape._txtLocPinX) == null) {
            return d2 == null ? Double.valueOf(getTxtWidth().doubleValue() * 0.5d) : d2;
        }
        return d;
    }

    public Double getTxtLocPinY() {
        XDGFShape xDGFShape;
        Double d;
        Double d2 = this._txtLocPinY;
        if (d2 != null || (xDGFShape = this._masterShape) == null || (d = xDGFShape._txtLocPinY) == null) {
            return d2 == null ? Double.valueOf(getTxtHeight().doubleValue() * 0.5d) : d2;
        }
        return d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1._masterShape;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Double getTxtAngle() {
        /*
            r1 = this;
            java.lang.Double r0 = r1._txtAngle
            if (r0 != 0) goto L_0x000d
            org.apache.poi.xdgf.usermodel.XDGFShape r1 = r1._masterShape
            if (r1 == 0) goto L_0x000d
            java.lang.Double r1 = r1.getTxtAngle()
            return r1
        L_0x000d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.usermodel.XDGFShape.getTxtAngle():java.lang.Double");
    }

    public Double getTxtWidth() {
        XDGFShape xDGFShape;
        Double d;
        Double d2 = this._txtWidth;
        if (d2 != null || (xDGFShape = this._masterShape) == null || (d = xDGFShape._txtWidth) == null) {
            return d2 == null ? getWidth() : d2;
        }
        return d;
    }

    public Double getTxtHeight() {
        XDGFShape xDGFShape;
        Double d;
        Double d2 = this._txtHeight;
        if (d2 != null || (xDGFShape = this._masterShape) == null || (d = xDGFShape._txtHeight) == null) {
            return d2 == null ? getHeight() : d2;
        }
        return d;
    }

    public Integer getLineCap() {
        Integer lineCap = super.getLineCap();
        if (lineCap != null) {
            return lineCap;
        }
        XDGFShape xDGFShape = this._masterShape;
        if (xDGFShape != null) {
            return xDGFShape.getLineCap();
        }
        return this._document.getDefaultLineStyle().getLineCap();
    }

    public Color getLineColor() {
        Color lineColor = super.getLineColor();
        if (lineColor != null) {
            return lineColor;
        }
        XDGFShape xDGFShape = this._masterShape;
        if (xDGFShape != null) {
            return xDGFShape.getLineColor();
        }
        return this._document.getDefaultLineStyle().getLineColor();
    }

    public Integer getLinePattern() {
        Integer linePattern = super.getLinePattern();
        if (linePattern != null) {
            return linePattern;
        }
        XDGFShape xDGFShape = this._masterShape;
        if (xDGFShape != null) {
            return xDGFShape.getLinePattern();
        }
        return this._document.getDefaultLineStyle().getLinePattern();
    }

    public Double getLineWeight() {
        Double lineWeight = super.getLineWeight();
        if (lineWeight != null) {
            return lineWeight;
        }
        XDGFShape xDGFShape = this._masterShape;
        if (xDGFShape != null) {
            return xDGFShape.getLineWeight();
        }
        return this._document.getDefaultLineStyle().getLineWeight();
    }

    public Color getFontColor() {
        Color fontColor = super.getFontColor();
        if (fontColor != null) {
            return fontColor;
        }
        XDGFShape xDGFShape = this._masterShape;
        if (xDGFShape != null) {
            return xDGFShape.getFontColor();
        }
        return this._document.getDefaultTextStyle().getFontColor();
    }

    public Double getFontSize() {
        Double fontSize = super.getFontSize();
        if (fontSize != null) {
            return fontSize;
        }
        XDGFShape xDGFShape = this._masterShape;
        if (xDGFShape != null) {
            return xDGFShape.getFontSize();
        }
        return this._document.getDefaultTextStyle().getFontSize();
    }

    public Stroke getStroke() {
        int i;
        float[] fArr;
        float floatValue = getLineWeight().floatValue();
        int intValue = getLineCap().intValue();
        if (intValue == 0) {
            i = 1;
        } else if (intValue == 1) {
            i = 2;
        } else if (intValue == 2) {
            i = 0;
        } else {
            throw new POIXMLException("Invalid line cap specified");
        }
        int intValue2 = getLinePattern().intValue();
        if (intValue2 != 254) {
            switch (intValue2) {
                case 0:
                case 1:
                    fArr = null;
                    break;
                case 2:
                    fArr = new float[]{5.0f, 3.0f};
                    break;
                case 3:
                    fArr = new float[]{1.0f, 4.0f};
                    break;
                case 4:
                    fArr = new float[]{6.0f, 3.0f, 1.0f, 3.0f};
                    break;
                case 5:
                    fArr = new float[]{6.0f, 3.0f, 1.0f, 3.0f, 1.0f, 3.0f};
                    break;
                case 6:
                    fArr = new float[]{1.0f, 3.0f, 6.0f, 3.0f, 6.0f, 3.0f};
                    break;
                case 7:
                    fArr = new float[]{15.0f, 3.0f, 6.0f, 3.0f};
                    break;
                case 8:
                    fArr = new float[]{6.0f, 3.0f, 6.0f, 3.0f};
                    break;
                case 9:
                    fArr = new float[]{3.0f, 2.0f};
                    break;
                case 10:
                    fArr = new float[]{1.0f, 2.0f};
                    break;
                case 11:
                    fArr = new float[]{3.0f, 2.0f, 1.0f, 2.0f};
                    break;
                case 12:
                    fArr = new float[]{3.0f, 2.0f, 1.0f, 2.0f, 1.0f};
                    break;
                case 13:
                    fArr = new float[]{1.0f, 2.0f, 3.0f, 2.0f, 3.0f, 2.0f};
                    break;
                case 14:
                    fArr = new float[]{3.0f, 2.0f, 7.0f, 2.0f};
                    break;
                case 15:
                    fArr = new float[]{7.0f, 2.0f, 3.0f, 2.0f, 3.0f, 2.0f};
                    break;
                case 16:
                    fArr = new float[]{12.0f, 6.0f};
                    break;
                case 17:
                    fArr = new float[]{1.0f, 6.0f};
                    break;
                case 18:
                    fArr = new float[]{1.0f, 6.0f, 12.0f, 6.0f};
                    break;
                case 19:
                    fArr = new float[]{1.0f, 6.0f, 1.0f, 6.0f, 12.0f, 6.0f};
                    break;
                case 20:
                    fArr = new float[]{1.0f, 6.0f, 12.0f, 6.0f, 12.0f, 6.0f};
                    break;
                case 21:
                    fArr = new float[]{30.0f, 6.0f, 12.0f, 6.0f};
                    break;
                case 22:
                    fArr = new float[]{30.0f, 6.0f, 12.0f, 6.0f, 12.0f, 6.0f};
                    break;
                case 23:
                    fArr = new float[]{1.0f};
                    break;
                default:
                    throw new POIXMLException("Invalid line pattern value");
            }
            float[] fArr2 = fArr;
            if (fArr2 != null) {
                for (int i2 = 0; i2 < fArr2.length; i2++) {
                    fArr2[i2] = fArr2[i2] * floatValue;
                }
            }
            return new BasicStroke(floatValue, i, 0, 10.0f, fArr2, 0.0f);
        }
        throw new POIXMLException("Unsupported line pattern value");
    }

    public Iterable<GeometrySection> getGeometrySections() {
        SortedMap sortedMap = this._geometry;
        XDGFShape xDGFShape = this._masterShape;
        return GeometrySection.combineGeometries(sortedMap, xDGFShape != null ? xDGFShape._geometry : null);
    }

    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(0.0d, 0.0d, getWidth().doubleValue(), getHeight().doubleValue());
    }

    public Path2D.Double getBoundsAsPath() {
        Double width = getWidth();
        Double height = getHeight();
        Path2D.Double doubleR = new Path2D.Double();
        doubleR.moveTo(0.0d, 0.0d);
        doubleR.lineTo(width.doubleValue(), 0.0d);
        doubleR.lineTo(width.doubleValue(), height.doubleValue());
        doubleR.lineTo(0.0d, height.doubleValue());
        doubleR.lineTo(0.0d, 0.0d);
        return doubleR;
    }

    public Path2D.Double getPath() {
        for (GeometrySection next : getGeometrySections()) {
            if (!next.getNoShow().booleanValue()) {
                return next.getPath(this);
            }
        }
        return null;
    }

    public boolean hasGeometry() {
        for (GeometrySection noShow : getGeometrySections()) {
            if (!noShow.getNoShow().booleanValue()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public AffineTransform getParentTransform() {
        AffineTransform affineTransform = new AffineTransform();
        Double locPinX = getLocPinX();
        Double locPinY = getLocPinY();
        Boolean flipX = getFlipX();
        Boolean flipY = getFlipY();
        Double angle = getAngle();
        affineTransform.translate(-locPinX.doubleValue(), -locPinY.doubleValue());
        affineTransform.translate(getPinX().doubleValue(), getPinY().doubleValue());
        if (angle != null && Math.abs(angle.doubleValue()) > 0.001d) {
            affineTransform.rotate(angle.doubleValue(), locPinX.doubleValue(), locPinY.doubleValue());
        }
        if (flipX != null && flipX.booleanValue()) {
            affineTransform.scale(-1.0d, 1.0d);
            affineTransform.translate(-getWidth().doubleValue(), 0.0d);
        }
        if (flipY != null && flipY.booleanValue()) {
            affineTransform.scale(1.0d, -1.0d);
            affineTransform.translate(0.0d, -getHeight().doubleValue());
        }
        return affineTransform;
    }

    public void visitShapes(ShapeVisitor shapeVisitor, AffineTransform affineTransform, int i) {
        AffineTransform affineTransform2 = (AffineTransform) affineTransform.clone();
        affineTransform2.concatenate(getParentTransform());
        try {
            if (shapeVisitor.accept(this)) {
                shapeVisitor.visit(this, affineTransform2, i);
            }
            List<XDGFShape> list = this._shapes;
            if (list != null) {
                for (XDGFShape visitShapes : list) {
                    visitShapes.visitShapes(shapeVisitor, affineTransform2, i + 1);
                }
            }
        } catch (StopVisitingThisBranch unused) {
        } catch (POIXMLException e) {
            throw XDGFException.wrap(toString(), e);
        }
    }

    public void visitShapes(ShapeVisitor shapeVisitor, int i) {
        try {
            if (shapeVisitor.accept(this)) {
                shapeVisitor.visit(this, (AffineTransform) null, i);
            }
            List<XDGFShape> list = this._shapes;
            if (list != null) {
                for (XDGFShape visitShapes : list) {
                    visitShapes.visitShapes(shapeVisitor, i + 1);
                }
            }
        } catch (StopVisitingThisBranch unused) {
        } catch (POIXMLException e) {
            throw XDGFException.wrap(toString(), e);
        }
    }
}
