package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.sl.draw.geom.Guide;
import org.apache.poi.sl.draw.geom.PresetGeometries;
import org.apache.poi.sl.usermodel.FillStyle;
import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.draw.geom.XSLFCustomGeometry;
import org.apache.poi.xslf.model.PropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetLineDashVal;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;

public abstract class XSLFSimpleShape extends XSLFShape implements SimpleShape<XSLFShape, XSLFTextParagraph> {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSLFSimpleShape.class);
    /* access modifiers changed from: private */
    public static final CTOuterShadowEffect NO_SHADOW = CTOuterShadowEffect.Factory.newInstance();

    public /* bridge */ /* synthetic */ PlaceholderDetails getPlaceholderDetails() {
        return super.getPlaceholderDetails();
    }

    XSLFSimpleShape(XmlObject xmlObject, XSLFSheet xSLFSheet) {
        super(xmlObject, xSLFSheet);
    }

    public void setShapeType(ShapeType shapeType) {
        XSLFPropertiesDelegate.XSLFGeometryProperties geometryDelegate = XSLFPropertiesDelegate.getGeometryDelegate(getShapeProperties());
        if (geometryDelegate != null) {
            if (geometryDelegate.isSetCustGeom()) {
                geometryDelegate.unsetCustGeom();
            }
            (geometryDelegate.isSetPrstGeom() ? geometryDelegate.getPrstGeom() : geometryDelegate.addNewPrstGeom()).setPrst(STShapeType.Enum.forInt(shapeType.ooxmlId));
        }
    }

    public ShapeType getShapeType() {
        STShapeType.Enum prst;
        XSLFPropertiesDelegate.XSLFGeometryProperties geometryDelegate = XSLFPropertiesDelegate.getGeometryDelegate(getShapeProperties());
        if (geometryDelegate == null || !geometryDelegate.isSetPrstGeom() || (prst = geometryDelegate.getPrstGeom().getPrst()) == null) {
            return null;
        }
        return ShapeType.forId(prst.intValue(), true);
    }

    /* access modifiers changed from: protected */
    public CTTransform2D getXfrm(boolean z) {
        AnonymousClass1 r0 = new PropertyFetcher<CTTransform2D>() {
            public boolean fetch(XSLFShape xSLFShape) {
                XmlObject shapeProperties = xSLFShape.getShapeProperties();
                if (!(shapeProperties instanceof CTShapeProperties)) {
                    return false;
                }
                CTShapeProperties cTShapeProperties = (CTShapeProperties) shapeProperties;
                if (!cTShapeProperties.isSetXfrm()) {
                    return false;
                }
                setValue(cTShapeProperties.getXfrm());
                return true;
            }
        };
        fetchShapeProperty(r0);
        CTTransform2D cTTransform2D = (CTTransform2D) r0.getValue();
        if (!z || cTTransform2D != null) {
            return cTTransform2D;
        }
        XmlObject shapeProperties = getShapeProperties();
        if (shapeProperties instanceof CTShapeProperties) {
            return ((CTShapeProperties) shapeProperties).addNewXfrm();
        }
        LOG.atWarn().log("{} doesn't have xfrm element.", (Object) getClass());
        return null;
    }

    public Rectangle2D getAnchor() {
        CTTransform2D xfrm = getXfrm(false);
        if (xfrm == null || !xfrm.isSetOff()) {
            return null;
        }
        CTPoint2D off = xfrm.getOff();
        double points = Units.toPoints(POIXMLUnits.parseLength(off.xgetX()));
        double points2 = Units.toPoints(POIXMLUnits.parseLength(off.xgetY()));
        CTPositiveSize2D ext = xfrm.getExt();
        return new Rectangle2D.Double(points, points2, Units.toPoints(ext.getCx()), Units.toPoints(ext.getCy()));
    }

    public void setAnchor(Rectangle2D rectangle2D) {
        CTPositiveSize2D cTPositiveSize2D;
        CTTransform2D xfrm = getXfrm(true);
        if (xfrm != null) {
            CTPoint2D off = xfrm.isSetOff() ? xfrm.getOff() : xfrm.addNewOff();
            off.setX(Long.valueOf((long) Units.toEMU(rectangle2D.getX())));
            off.setY(Long.valueOf((long) Units.toEMU(rectangle2D.getY())));
            if (xfrm.isSetExt()) {
                cTPositiveSize2D = xfrm.getExt();
            } else {
                cTPositiveSize2D = xfrm.addNewExt();
            }
            cTPositiveSize2D.setCx((long) Units.toEMU(rectangle2D.getWidth()));
            cTPositiveSize2D.setCy((long) Units.toEMU(rectangle2D.getHeight()));
        }
    }

    public void setRotation(double d) {
        CTTransform2D xfrm = getXfrm(true);
        if (xfrm != null) {
            xfrm.setRot((int) (d * 60000.0d));
        }
    }

    public double getRotation() {
        CTTransform2D xfrm = getXfrm(false);
        if (xfrm == null || !xfrm.isSetRot()) {
            return 0.0d;
        }
        return ((double) xfrm.getRot()) / 60000.0d;
    }

    public void setFlipHorizontal(boolean z) {
        CTTransform2D xfrm = getXfrm(true);
        if (xfrm != null) {
            xfrm.setFlipH(z);
        }
    }

    public void setFlipVertical(boolean z) {
        CTTransform2D xfrm = getXfrm(true);
        if (xfrm != null) {
            xfrm.setFlipV(z);
        }
    }

    public boolean getFlipHorizontal() {
        CTTransform2D xfrm = getXfrm(false);
        if (xfrm == null || !xfrm.isSetFlipH() || !xfrm.getFlipH()) {
            return false;
        }
        return true;
    }

    public boolean getFlipVertical() {
        CTTransform2D xfrm = getXfrm(false);
        if (xfrm == null || !xfrm.isSetFlipV() || !xfrm.getFlipV()) {
            return false;
        }
        return true;
    }

    private CTLineProperties getDefaultLineProperties() {
        CTStyleMatrixReference lnRef;
        CTBaseStyles themeElements;
        CTStyleMatrix fmtScheme;
        CTLineStyleList lnStyleLst;
        CTShapeStyle spStyle = getSpStyle();
        if (spStyle == null || (lnRef = spStyle.getLnRef()) == null) {
            return null;
        }
        int intExact = Math.toIntExact(lnRef.getIdx());
        XSLFTheme theme = getSheet().getTheme();
        if (theme == null || (themeElements = theme.getXmlObject().getThemeElements()) == null || (fmtScheme = themeElements.getFmtScheme()) == null || (lnStyleLst = fmtScheme.getLnStyleLst()) == null || lnStyleLst.sizeOfLnArray() < intExact) {
            return null;
        }
        return lnStyleLst.getLnArray(intExact - 1);
    }

    public void setLineColor(Color color) {
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            if (ln.isSetSolidFill()) {
                ln.unsetSolidFill();
            }
            if (ln.isSetGradFill()) {
                ln.unsetGradFill();
            }
            if (ln.isSetPattFill()) {
                ln.unsetPattFill();
            }
            if (ln.isSetNoFill()) {
                ln.unsetNoFill();
            }
            if (color == null) {
                ln.addNewNoFill();
                return;
            }
            CTSolidColorFillProperties addNewSolidFill = ln.addNewSolidFill();
            new XSLFColor(addNewSolidFill, getSheet().getTheme(), addNewSolidFill.getSchemeClr(), getSheet()).setColor(color);
        }
    }

    public Color getLineColor() {
        PaintStyle linePaint = getLinePaint();
        if (linePaint instanceof PaintStyle.SolidPaint) {
            return ((PaintStyle.SolidPaint) linePaint).getSolidColor().getColor();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public PaintStyle getLinePaint() {
        final XSLFTheme theme = getSheet().getTheme();
        final boolean z = getPlaceholder() != null;
        AnonymousClass2 r2 = new PropertyFetcher<PaintStyle>() {
            public boolean fetch(XSLFShape xSLFShape) {
                XSLFPropertiesDelegate.XSLFFillProperties fillDelegate = XSLFPropertiesDelegate.getFillDelegate(XSLFSimpleShape.getLn(xSLFShape, false));
                if (fillDelegate == null || !fillDelegate.isSetNoFill()) {
                    PackagePart packagePart = xSLFShape.getSheet().getPackagePart();
                    PaintStyle selectPaint = XSLFSimpleShape.this.selectPaint(fillDelegate, (CTSchemeColor) null, packagePart, theme, z);
                    if (selectPaint != null) {
                        setValue(selectPaint);
                        return true;
                    }
                    CTShapeStyle spStyle = xSLFShape.getSpStyle();
                    if (spStyle != null && (selectPaint = XSLFSimpleShape.this.selectPaint(XSLFPropertiesDelegate.getFillDelegate(spStyle.getLnRef()), (CTSchemeColor) null, packagePart, theme, z)) == null) {
                        selectPaint = getThemePaint(spStyle, packagePart);
                    }
                    if (selectPaint == null) {
                        return false;
                    }
                    setValue(selectPaint);
                    return true;
                }
                setValue(null);
                return true;
            }

            /* access modifiers changed from: package-private */
            public PaintStyle getThemePaint(CTShapeStyle cTShapeStyle, PackagePart packagePart) {
                CTStyleMatrixReference lnRef = cTShapeStyle.getLnRef();
                if (lnRef == null) {
                    return null;
                }
                int intExact = Math.toIntExact(lnRef.getIdx());
                CTSchemeColor schemeClr = lnRef.getSchemeClr();
                if (intExact <= 0) {
                    return null;
                }
                return XSLFSimpleShape.this.selectPaint(XSLFPropertiesDelegate.getFillDelegate(theme.getXmlObject().getThemeElements().getFmtScheme().getLnStyleLst().getLnArray(intExact - 1)), schemeClr, packagePart, theme, z);
            }
        };
        fetchShapeProperty(r2);
        return (PaintStyle) r2.getValue();
    }

    public void setLineWidth(double d) {
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            if (d == 0.0d) {
                if (ln.isSetW()) {
                    ln.unsetW();
                }
                if (!ln.isSetNoFill()) {
                    ln.addNewNoFill();
                }
                if (ln.isSetSolidFill()) {
                    ln.unsetSolidFill();
                }
                if (ln.isSetGradFill()) {
                    ln.unsetGradFill();
                }
                if (ln.isSetPattFill()) {
                    ln.unsetPattFill();
                    return;
                }
                return;
            }
            if (ln.isSetNoFill()) {
                ln.unsetNoFill();
            }
            ln.setW(Units.toEMU(d));
        }
    }

    public double getLineWidth() {
        AnonymousClass3 r0 = new PropertyFetcher<Double>() {
            public boolean fetch(XSLFShape xSLFShape) {
                CTLineProperties access$000 = XSLFSimpleShape.getLn(xSLFShape, false);
                if (access$000 != null) {
                    if (access$000.isSetNoFill()) {
                        setValue(Double.valueOf(0.0d));
                        return true;
                    } else if (access$000.isSetW()) {
                        setValue(Double.valueOf(Units.toPoints((long) access$000.getW())));
                        return true;
                    }
                }
                return false;
            }
        };
        fetchShapeProperty(r0);
        if (r0.getValue() != null) {
            return ((Double) r0.getValue()).doubleValue();
        }
        CTLineProperties defaultLineProperties = getDefaultLineProperties();
        if (defaultLineProperties == null || !defaultLineProperties.isSetW()) {
            return 0.0d;
        }
        return Units.toPoints((long) defaultLineProperties.getW());
    }

    public void setLineCompound(StrokeStyle.LineCompound lineCompound) {
        STCompoundLine.Enum enumR;
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            if (lineCompound != null) {
                int i = AnonymousClass10.$SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCompound[lineCompound.ordinal()];
                if (i == 2) {
                    enumR = STCompoundLine.DBL;
                } else if (i == 3) {
                    enumR = STCompoundLine.THICK_THIN;
                } else if (i == 4) {
                    enumR = STCompoundLine.THIN_THICK;
                } else if (i != 5) {
                    enumR = STCompoundLine.SNG;
                } else {
                    enumR = STCompoundLine.TRI;
                }
                ln.setCmpd(enumR);
            } else if (ln.isSetCmpd()) {
                ln.unsetCmpd();
            }
        }
    }

    /* renamed from: org.apache.poi.xslf.usermodel.XSLFSimpleShape$10  reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCompound;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.sl.usermodel.StrokeStyle$LineCompound[] r0 = org.apache.poi.sl.usermodel.StrokeStyle.LineCompound.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCompound = r0
                org.apache.poi.sl.usermodel.StrokeStyle$LineCompound r1 = org.apache.poi.sl.usermodel.StrokeStyle.LineCompound.SINGLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCompound     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.StrokeStyle$LineCompound r1 = org.apache.poi.sl.usermodel.StrokeStyle.LineCompound.DOUBLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCompound     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.StrokeStyle$LineCompound r1 = org.apache.poi.sl.usermodel.StrokeStyle.LineCompound.THICK_THIN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCompound     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.StrokeStyle$LineCompound r1 = org.apache.poi.sl.usermodel.StrokeStyle.LineCompound.THIN_THICK     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCompound     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.sl.usermodel.StrokeStyle$LineCompound r1 = org.apache.poi.sl.usermodel.StrokeStyle.LineCompound.TRIPLE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSimpleShape.AnonymousClass10.<clinit>():void");
        }
    }

    public StrokeStyle.LineCompound getLineCompound() {
        CTLineProperties defaultLineProperties;
        AnonymousClass4 r0 = new PropertyFetcher<Integer>() {
            public boolean fetch(XSLFShape xSLFShape) {
                STCompoundLine.Enum cmpd;
                CTLineProperties access$000 = XSLFSimpleShape.getLn(xSLFShape, false);
                if (access$000 == null || (cmpd = access$000.getCmpd()) == null) {
                    return false;
                }
                setValue(Integer.valueOf(cmpd.intValue()));
                return true;
            }
        };
        fetchShapeProperty(r0);
        if (((Integer) r0.getValue()) != null || (defaultLineProperties = getDefaultLineProperties()) == null || !defaultLineProperties.isSetCmpd()) {
            return null;
        }
        int intValue = defaultLineProperties.getCmpd().intValue();
        if (intValue == 2) {
            return StrokeStyle.LineCompound.DOUBLE;
        }
        if (intValue == 3) {
            return StrokeStyle.LineCompound.THICK_THIN;
        }
        if (intValue == 4) {
            return StrokeStyle.LineCompound.THIN_THICK;
        }
        if (intValue != 5) {
            return StrokeStyle.LineCompound.SINGLE;
        }
        return StrokeStyle.LineCompound.TRIPLE;
    }

    public void setLineDash(StrokeStyle.LineDash lineDash) {
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            if (lineDash != null) {
                (ln.isSetPrstDash() ? ln.getPrstDash() : ln.addNewPrstDash()).setVal(STPresetLineDashVal.Enum.forInt(lineDash.ooxmlId));
            } else if (ln.isSetPrstDash()) {
                ln.unsetPrstDash();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0010, code lost:
        r2 = getDefaultLineProperties();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.sl.usermodel.StrokeStyle.LineDash getLineDash() {
        /*
            r2 = this;
            org.apache.poi.xslf.usermodel.XSLFSimpleShape$5 r0 = new org.apache.poi.xslf.usermodel.XSLFSimpleShape$5
            r0.<init>()
            r2.fetchShapeProperty(r0)
            java.lang.Object r0 = r0.getValue()
            org.apache.poi.sl.usermodel.StrokeStyle$LineDash r0 = (org.apache.poi.sl.usermodel.StrokeStyle.LineDash) r0
            if (r0 != 0) goto L_0x002c
            org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties r2 = r2.getDefaultLineProperties()
            if (r2 == 0) goto L_0x002c
            boolean r1 = r2.isSetPrstDash()
            if (r1 == 0) goto L_0x002c
            org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties r2 = r2.getPrstDash()
            org.openxmlformats.schemas.drawingml.x2006.main.STPresetLineDashVal$Enum r2 = r2.getVal()
            int r2 = r2.intValue()
            org.apache.poi.sl.usermodel.StrokeStyle$LineDash r0 = org.apache.poi.sl.usermodel.StrokeStyle.LineDash.fromOoxmlId(r2)
        L_0x002c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSimpleShape.getLineDash():org.apache.poi.sl.usermodel.StrokeStyle$LineDash");
    }

    public void setLineCap(StrokeStyle.LineCap lineCap) {
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            if (lineCap != null) {
                ln.setCap(STLineCap.Enum.forInt(lineCap.ooxmlId));
            } else if (ln.isSetCap()) {
                ln.unsetCap();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0010, code lost:
        r2 = getDefaultLineProperties();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.sl.usermodel.StrokeStyle.LineCap getLineCap() {
        /*
            r2 = this;
            org.apache.poi.xslf.usermodel.XSLFSimpleShape$6 r0 = new org.apache.poi.xslf.usermodel.XSLFSimpleShape$6
            r0.<init>()
            r2.fetchShapeProperty(r0)
            java.lang.Object r0 = r0.getValue()
            org.apache.poi.sl.usermodel.StrokeStyle$LineCap r0 = (org.apache.poi.sl.usermodel.StrokeStyle.LineCap) r0
            if (r0 != 0) goto L_0x0028
            org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties r2 = r2.getDefaultLineProperties()
            if (r2 == 0) goto L_0x0028
            boolean r1 = r2.isSetCap()
            if (r1 == 0) goto L_0x0028
            org.openxmlformats.schemas.drawingml.x2006.main.STLineCap$Enum r2 = r2.getCap()
            int r2 = r2.intValue()
            org.apache.poi.sl.usermodel.StrokeStyle$LineCap r0 = org.apache.poi.sl.usermodel.StrokeStyle.LineCap.fromOoxmlId(r2)
        L_0x0028:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFSimpleShape.getLineCap():org.apache.poi.sl.usermodel.StrokeStyle$LineCap");
    }

    public void setFillColor(Color color) {
        XSLFPropertiesDelegate.XSLFFillProperties fillDelegate = XSLFPropertiesDelegate.getFillDelegate(getShapeProperties());
        if (fillDelegate != null) {
            if (color == null) {
                if (fillDelegate.isSetSolidFill()) {
                    fillDelegate.unsetSolidFill();
                }
                if (fillDelegate.isSetGradFill()) {
                    fillDelegate.unsetGradFill();
                }
                if (fillDelegate.isSetPattFill()) {
                    fillDelegate.unsetGradFill();
                }
                if (fillDelegate.isSetBlipFill()) {
                    fillDelegate.unsetBlipFill();
                }
                if (!fillDelegate.isSetNoFill()) {
                    fillDelegate.addNewNoFill();
                    return;
                }
                return;
            }
            if (fillDelegate.isSetNoFill()) {
                fillDelegate.unsetNoFill();
            }
            CTSolidColorFillProperties solidFill = fillDelegate.isSetSolidFill() ? fillDelegate.getSolidFill() : fillDelegate.addNewSolidFill();
            new XSLFColor(solidFill, getSheet().getTheme(), solidFill.getSchemeClr(), getSheet()).setColor(color);
        }
    }

    public Color getFillColor() {
        PaintStyle fillPaint = getFillPaint();
        if (fillPaint instanceof PaintStyle.SolidPaint) {
            return DrawPaint.applyColorTransform(((PaintStyle.SolidPaint) fillPaint).getSolidColor());
        }
        return null;
    }

    public XSLFShadow getShadow() {
        CTShapeStyle spStyle;
        int idx;
        AnonymousClass7 r0 = new PropertyFetcher<CTOuterShadowEffect>() {
            public boolean fetch(XSLFShape xSLFShape) {
                XSLFPropertiesDelegate.XSLFEffectProperties effectDelegate = XSLFPropertiesDelegate.getEffectDelegate(xSLFShape.getShapeProperties());
                if (effectDelegate == null || !effectDelegate.isSetEffectLst()) {
                    return false;
                }
                CTOuterShadowEffect outerShdw = effectDelegate.getEffectLst().getOuterShdw();
                if (outerShdw == null) {
                    outerShdw = XSLFSimpleShape.NO_SHADOW;
                }
                setValue(outerShdw);
                return true;
            }
        };
        fetchShapeProperty(r0);
        CTOuterShadowEffect cTOuterShadowEffect = (CTOuterShadowEffect) r0.getValue();
        if (!(cTOuterShadowEffect != null || (spStyle = getSpStyle()) == null || spStyle.getEffectRef() == null || (idx = (int) spStyle.getEffectRef().getIdx()) == 0)) {
            cTOuterShadowEffect = getSheet().getTheme().getXmlObject().getThemeElements().getFmtScheme().getEffectStyleLst().getEffectStyleArray(idx - 1).getEffectLst().getOuterShdw();
        }
        if (cTOuterShadowEffect == null || cTOuterShadowEffect == NO_SHADOW) {
            return null;
        }
        return new XSLFShadow(cTOuterShadowEffect, this);
    }

    public CustomGeometry getGeometry() {
        XSLFPropertiesDelegate.XSLFGeometryProperties geometryDelegate = XSLFPropertiesDelegate.getGeometryDelegate(getShapeProperties());
        if (geometryDelegate == null) {
            return null;
        }
        PresetGeometries instance = PresetGeometries.getInstance();
        if (geometryDelegate.isSetPrstGeom()) {
            String str = geometryDelegate.getPrstGeom().getPrst().toString();
            CustomGeometry customGeometry = instance.get(str);
            if (customGeometry != null) {
                return customGeometry;
            }
            throw new IllegalStateException("Unknown shape geometry: " + str + ", available geometries are: " + instance.keySet());
        } else if (geometryDelegate.isSetCustGeom()) {
            return XSLFCustomGeometry.convertCustomGeometry(geometryDelegate.getCustGeom());
        } else {
            return instance.get("rect");
        }
    }

    /* access modifiers changed from: package-private */
    public void copy(XSLFShape xSLFShape) {
        super.copy(xSLFShape);
        XSLFSimpleShape xSLFSimpleShape = (XSLFSimpleShape) xSLFShape;
        Color fillColor = xSLFSimpleShape.getFillColor();
        Color fillColor2 = getFillColor();
        if (fillColor != null && !fillColor.equals(fillColor2)) {
            setFillColor(fillColor);
        }
        XSLFPropertiesDelegate.XSLFFillProperties fillDelegate = XSLFPropertiesDelegate.getFillDelegate(getShapeProperties());
        if (fillDelegate != null && fillDelegate.isSetBlipFill()) {
            CTBlip blip = fillDelegate.getBlipFill().getBlip();
            blip.setEmbed(getSheet().importBlip(blip.getEmbed(), xSLFSimpleShape.getSheet()));
        }
        Color lineColor = xSLFSimpleShape.getLineColor();
        Color lineColor2 = getLineColor();
        if (lineColor != null && !lineColor.equals(lineColor2)) {
            setLineColor(lineColor);
        }
        double lineWidth = xSLFSimpleShape.getLineWidth();
        if (lineWidth != getLineWidth()) {
            setLineWidth(lineWidth);
        }
        StrokeStyle.LineDash lineDash = xSLFSimpleShape.getLineDash();
        StrokeStyle.LineDash lineDash2 = getLineDash();
        if (!(lineDash == null || lineDash == lineDash2)) {
            setLineDash(lineDash);
        }
        StrokeStyle.LineCap lineCap = xSLFSimpleShape.getLineCap();
        StrokeStyle.LineCap lineCap2 = getLineCap();
        if (lineCap != null && lineCap != lineCap2) {
            setLineCap(lineCap);
        }
    }

    public void setLineHeadDecoration(LineDecoration.DecorationShape decorationShape) {
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            CTLineEndProperties headEnd = ln.isSetHeadEnd() ? ln.getHeadEnd() : ln.addNewHeadEnd();
            if (decorationShape != null) {
                headEnd.setType(STLineEndType.Enum.forInt(decorationShape.ooxmlId));
            } else if (headEnd.isSetType()) {
                headEnd.unsetType();
            }
        }
    }

    public LineDecoration.DecorationShape getLineHeadDecoration() {
        CTLineProperties ln = getLn(this, false);
        LineDecoration.DecorationShape decorationShape = LineDecoration.DecorationShape.NONE;
        return (ln == null || !ln.isSetHeadEnd() || !ln.getHeadEnd().isSetType()) ? decorationShape : LineDecoration.DecorationShape.fromOoxmlId(ln.getHeadEnd().getType().intValue());
    }

    public void setLineHeadWidth(LineDecoration.DecorationSize decorationSize) {
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            CTLineEndProperties headEnd = ln.isSetHeadEnd() ? ln.getHeadEnd() : ln.addNewHeadEnd();
            if (decorationSize != null) {
                headEnd.setW(STLineEndWidth.Enum.forInt(decorationSize.ooxmlId));
            } else if (headEnd.isSetW()) {
                headEnd.unsetW();
            }
        }
    }

    public LineDecoration.DecorationSize getLineHeadWidth() {
        CTLineProperties ln = getLn(this, false);
        LineDecoration.DecorationSize decorationSize = LineDecoration.DecorationSize.MEDIUM;
        return (ln == null || !ln.isSetHeadEnd() || !ln.getHeadEnd().isSetW()) ? decorationSize : LineDecoration.DecorationSize.fromOoxmlId(ln.getHeadEnd().getW().intValue());
    }

    public void setLineHeadLength(LineDecoration.DecorationSize decorationSize) {
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            CTLineEndProperties headEnd = ln.isSetHeadEnd() ? ln.getHeadEnd() : ln.addNewHeadEnd();
            if (decorationSize != null) {
                headEnd.setLen(STLineEndLength.Enum.forInt(decorationSize.ooxmlId));
            } else if (headEnd.isSetLen()) {
                headEnd.unsetLen();
            }
        }
    }

    public LineDecoration.DecorationSize getLineHeadLength() {
        CTLineProperties ln = getLn(this, false);
        LineDecoration.DecorationSize decorationSize = LineDecoration.DecorationSize.MEDIUM;
        return (ln == null || !ln.isSetHeadEnd() || !ln.getHeadEnd().isSetLen()) ? decorationSize : LineDecoration.DecorationSize.fromOoxmlId(ln.getHeadEnd().getLen().intValue());
    }

    public void setLineTailDecoration(LineDecoration.DecorationShape decorationShape) {
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            CTLineEndProperties tailEnd = ln.isSetTailEnd() ? ln.getTailEnd() : ln.addNewTailEnd();
            if (decorationShape != null) {
                tailEnd.setType(STLineEndType.Enum.forInt(decorationShape.ooxmlId));
            } else if (tailEnd.isSetType()) {
                tailEnd.unsetType();
            }
        }
    }

    public LineDecoration.DecorationShape getLineTailDecoration() {
        CTLineProperties ln = getLn(this, false);
        LineDecoration.DecorationShape decorationShape = LineDecoration.DecorationShape.NONE;
        return (ln == null || !ln.isSetTailEnd() || !ln.getTailEnd().isSetType()) ? decorationShape : LineDecoration.DecorationShape.fromOoxmlId(ln.getTailEnd().getType().intValue());
    }

    public void setLineTailWidth(LineDecoration.DecorationSize decorationSize) {
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            CTLineEndProperties tailEnd = ln.isSetTailEnd() ? ln.getTailEnd() : ln.addNewTailEnd();
            if (decorationSize != null) {
                tailEnd.setW(STLineEndWidth.Enum.forInt(decorationSize.ooxmlId));
            } else if (tailEnd.isSetW()) {
                tailEnd.unsetW();
            }
        }
    }

    public LineDecoration.DecorationSize getLineTailWidth() {
        CTLineProperties ln = getLn(this, false);
        LineDecoration.DecorationSize decorationSize = LineDecoration.DecorationSize.MEDIUM;
        return (ln == null || !ln.isSetTailEnd() || !ln.getTailEnd().isSetW()) ? decorationSize : LineDecoration.DecorationSize.fromOoxmlId(ln.getTailEnd().getW().intValue());
    }

    public void setLineTailLength(LineDecoration.DecorationSize decorationSize) {
        CTLineProperties ln = getLn(this, true);
        if (ln != null) {
            CTLineEndProperties tailEnd = ln.isSetTailEnd() ? ln.getTailEnd() : ln.addNewTailEnd();
            if (decorationSize != null) {
                tailEnd.setLen(STLineEndLength.Enum.forInt(decorationSize.ooxmlId));
            } else if (tailEnd.isSetLen()) {
                tailEnd.unsetLen();
            }
        }
    }

    public LineDecoration.DecorationSize getLineTailLength() {
        CTLineProperties ln = getLn(this, false);
        LineDecoration.DecorationSize decorationSize = LineDecoration.DecorationSize.MEDIUM;
        return (ln == null || !ln.isSetTailEnd() || !ln.getTailEnd().isSetLen()) ? decorationSize : LineDecoration.DecorationSize.fromOoxmlId(ln.getTailEnd().getLen().intValue());
    }

    public Guide getAdjustValue(String str) {
        XSLFPropertiesDelegate.XSLFGeometryProperties geometryDelegate = XSLFPropertiesDelegate.getGeometryDelegate(getShapeProperties());
        if (geometryDelegate == null || !geometryDelegate.isSetPrstGeom() || !geometryDelegate.getPrstGeom().isSetAvLst()) {
            return null;
        }
        for (CTGeomGuide cTGeomGuide : geometryDelegate.getPrstGeom().getAvLst().getGdArray()) {
            if (cTGeomGuide.getName().equals(str)) {
                Guide guide = new Guide();
                guide.setName(cTGeomGuide.getName());
                guide.setFmla(cTGeomGuide.getFmla());
                return guide;
            }
        }
        return null;
    }

    public LineDecoration getLineDecoration() {
        return new LineDecoration() {
            public LineDecoration.DecorationShape getHeadShape() {
                return XSLFSimpleShape.this.getLineHeadDecoration();
            }

            public LineDecoration.DecorationSize getHeadWidth() {
                return XSLFSimpleShape.this.getLineHeadWidth();
            }

            public LineDecoration.DecorationSize getHeadLength() {
                return XSLFSimpleShape.this.getLineHeadLength();
            }

            public LineDecoration.DecorationShape getTailShape() {
                return XSLFSimpleShape.this.getLineTailDecoration();
            }

            public LineDecoration.DecorationSize getTailWidth() {
                return XSLFSimpleShape.this.getLineTailWidth();
            }

            public LineDecoration.DecorationSize getTailLength() {
                return XSLFSimpleShape.this.getLineTailLength();
            }
        };
    }

    public FillStyle getFillStyle() {
        return new XSLFSimpleShape$$ExternalSyntheticLambda0(this);
    }

    public StrokeStyle getStrokeStyle() {
        return new StrokeStyle() {
            public PaintStyle getPaint() {
                return XSLFSimpleShape.this.getLinePaint();
            }

            public StrokeStyle.LineCap getLineCap() {
                return XSLFSimpleShape.this.getLineCap();
            }

            public StrokeStyle.LineDash getLineDash() {
                return XSLFSimpleShape.this.getLineDash();
            }

            public double getLineWidth() {
                return XSLFSimpleShape.this.getLineWidth();
            }

            public StrokeStyle.LineCompound getLineCompound() {
                return XSLFSimpleShape.this.getLineCompound();
            }
        };
    }

    public void setStrokeStyle(Object... objArr) {
        if (objArr.length == 0) {
            setLineColor((Color) null);
            return;
        }
        for (Number number : objArr) {
            if (number instanceof Number) {
                setLineWidth(number.doubleValue());
            } else if (number instanceof StrokeStyle.LineCap) {
                setLineCap((StrokeStyle.LineCap) number);
            } else if (number instanceof StrokeStyle.LineDash) {
                setLineDash((StrokeStyle.LineDash) number);
            } else if (number instanceof StrokeStyle.LineCompound) {
                setLineCompound((StrokeStyle.LineCompound) number);
            } else if (number instanceof Color) {
                setLineColor((Color) number);
            }
        }
    }

    public XSLFHyperlink getHyperlink() {
        CTNonVisualDrawingProps cNvPr = getCNvPr();
        if (!cNvPr.isSetHlinkClick()) {
            return null;
        }
        return new XSLFHyperlink(cNvPr.getHlinkClick(), getSheet());
    }

    public XSLFHyperlink createHyperlink() {
        XSLFHyperlink hyperlink = getHyperlink();
        return hyperlink == null ? new XSLFHyperlink(getCNvPr().addNewHlinkClick(), getSheet()) : hyperlink;
    }

    /* access modifiers changed from: private */
    public static CTLineProperties getLn(XSLFShape xSLFShape, boolean z) {
        XmlObject shapeProperties = xSLFShape.getShapeProperties();
        if (!(shapeProperties instanceof CTShapeProperties)) {
            LOG.atWarn().log("{} doesn't have line properties", (Object) xSLFShape.getClass());
            return null;
        }
        CTShapeProperties cTShapeProperties = (CTShapeProperties) shapeProperties;
        return (cTShapeProperties.isSetLn() || !z) ? cTShapeProperties.getLn() : cTShapeProperties.addNewLn();
    }
}
