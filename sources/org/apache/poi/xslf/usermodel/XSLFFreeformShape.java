package org.apache.poi.xslf.usermodel;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.sl.usermodel.FreeformShape;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.draw.geom.XSLFCustomGeometry;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DClose;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShapeNonVisual;

public class XSLFFreeformShape extends XSLFAutoShape implements FreeformShape<XSLFShape, XSLFTextParagraph> {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSLFFreeformShape.class);

    XSLFFreeformShape(CTShape cTShape, XSLFSheet xSLFSheet) {
        super(cTShape, xSLFSheet);
    }

    public int setPath(Path2D path2D) {
        CTAdjPoint2D[] cTAdjPoint2DArr;
        CTPath2D newInstance = CTPath2D.Factory.newInstance();
        Rectangle2D bounds2D = path2D.getBounds2D();
        int emu = Units.toEMU(bounds2D.getX());
        int emu2 = Units.toEMU(bounds2D.getY());
        PathIterator pathIterator = path2D.getPathIterator(new AffineTransform());
        newInstance.setH((long) Units.toEMU(bounds2D.getHeight()));
        newInstance.setW((long) Units.toEMU(bounds2D.getWidth()));
        double[] dArr = new double[6];
        int i = 0;
        while (!pathIterator.isDone()) {
            int currentSegment = pathIterator.currentSegment(dArr);
            if (currentSegment == 0) {
                cTAdjPoint2DArr = addMoveTo(newInstance);
            } else if (currentSegment == 1) {
                cTAdjPoint2DArr = addLineTo(newInstance);
            } else if (currentSegment == 2) {
                cTAdjPoint2DArr = addQuadBezierTo(newInstance);
            } else if (currentSegment == 3) {
                cTAdjPoint2DArr = addCubicBezierTo(newInstance);
            } else if (currentSegment == 4) {
                cTAdjPoint2DArr = addClosePath(newInstance);
            } else {
                throw new IllegalStateException("Unrecognized path segment type: " + currentSegment);
            }
            int i2 = 0;
            for (CTAdjPoint2D cTAdjPoint2D : cTAdjPoint2DArr) {
                int i3 = i2 + 1;
                cTAdjPoint2D.setX(Integer.valueOf(Units.toEMU(dArr[i2]) - emu));
                i2 = i3 + 1;
                cTAdjPoint2D.setY(Integer.valueOf(Units.toEMU(dArr[i3]) - emu2));
            }
            i += Math.max(cTAdjPoint2DArr.length, 1);
            pathIterator.next();
        }
        XmlObject shapeProperties = getShapeProperties();
        if (!(shapeProperties instanceof CTShapeProperties)) {
            return -1;
        }
        ((CTShapeProperties) shapeProperties).getCustGeom().getPathLst().setPathArray(new CTPath2D[]{newInstance});
        setAnchor(bounds2D);
        return i;
    }

    public CustomGeometry getGeometry() {
        XmlObject shapeProperties = getShapeProperties();
        if (!(shapeProperties instanceof CTShapeProperties)) {
            return null;
        }
        return XSLFCustomGeometry.convertCustomGeometry(((CTShapeProperties) shapeProperties).getCustGeom());
    }

    /* JADX INFO: finally extract failed */
    public Path2D.Double getPath() {
        Path2D.Double doubleR = new Path2D.Double();
        XmlObject shapeProperties = getShapeProperties();
        if (!(shapeProperties instanceof CTShapeProperties)) {
            return null;
        }
        CTPath2D[] pathArray = ((CTShapeProperties) shapeProperties).getCustGeom().getPathLst().getPathArray();
        int length = pathArray.length;
        int i = 0;
        while (i < length) {
            XmlCursor newCursor = pathArray[i].newCursor();
            try {
                if (newCursor.toFirstChild()) {
                    do {
                        XmlObject object = newCursor.getObject();
                        if (object instanceof CTPath2DMoveTo) {
                            addMoveTo(doubleR, (CTPath2DMoveTo) object);
                        } else if (object instanceof CTPath2DLineTo) {
                            addLineTo(doubleR, (CTPath2DLineTo) object);
                        } else if (object instanceof CTPath2DQuadBezierTo) {
                            addQuadBezierTo(doubleR, (CTPath2DQuadBezierTo) object);
                        } else if (object instanceof CTPath2DCubicBezierTo) {
                            addCubicBezierTo(doubleR, (CTPath2DCubicBezierTo) object);
                        } else if (object instanceof CTPath2DClose) {
                            addClosePath((Path2D) doubleR);
                        } else {
                            LOG.atWarn().log("can't handle path of type {}", (Object) shapeProperties.getClass());
                        }
                    } while (newCursor.toNextSibling());
                }
                newCursor.dispose();
                i++;
            } catch (Throwable th) {
                newCursor.dispose();
                throw th;
            }
        }
        AffineTransform affineTransform = new AffineTransform();
        CTTransform2D xfrm = getXfrm(false);
        Rectangle2D.Double doubleR2 = new Rectangle2D.Double((double) POIXMLUnits.parseLength(xfrm.getOff().xgetX()), (double) POIXMLUnits.parseLength(xfrm.getOff().xgetY()), (double) xfrm.getExt().getCx(), (double) xfrm.getExt().getCy());
        Rectangle2D anchor = getAnchor();
        affineTransform.translate(anchor.getX() + anchor.getCenterX(), anchor.getY() + anchor.getCenterY());
        affineTransform.scale(7.874015748031496E-5d, 7.874015748031496E-5d);
        affineTransform.translate(-doubleR2.getCenterX(), -doubleR2.getCenterY());
        return new Path2D.Double(affineTransform.createTransformedShape(doubleR));
    }

    private static CTAdjPoint2D[] addMoveTo(CTPath2D cTPath2D) {
        return new CTAdjPoint2D[]{cTPath2D.addNewMoveTo().addNewPt()};
    }

    private static void addMoveTo(Path2D path2D, CTPath2DMoveTo cTPath2DMoveTo) {
        CTAdjPoint2D pt = cTPath2DMoveTo.getPt();
        path2D.moveTo((double) ((Long) pt.getX()).longValue(), (double) ((Long) pt.getY()).longValue());
    }

    private static CTAdjPoint2D[] addLineTo(CTPath2D cTPath2D) {
        return new CTAdjPoint2D[]{cTPath2D.addNewLnTo().addNewPt()};
    }

    private static void addLineTo(Path2D path2D, CTPath2DLineTo cTPath2DLineTo) {
        CTAdjPoint2D pt = cTPath2DLineTo.getPt();
        path2D.lineTo((double) ((Long) pt.getX()).longValue(), (double) ((Long) pt.getY()).longValue());
    }

    private static CTAdjPoint2D[] addQuadBezierTo(CTPath2D cTPath2D) {
        CTPath2DQuadBezierTo addNewQuadBezTo = cTPath2D.addNewQuadBezTo();
        return new CTAdjPoint2D[]{addNewQuadBezTo.addNewPt(), addNewQuadBezTo.addNewPt()};
    }

    private static void addQuadBezierTo(Path2D path2D, CTPath2DQuadBezierTo cTPath2DQuadBezierTo) {
        CTAdjPoint2D ptArray = cTPath2DQuadBezierTo.getPtArray(0);
        CTAdjPoint2D ptArray2 = cTPath2DQuadBezierTo.getPtArray(1);
        path2D.quadTo((double) ((Long) ptArray.getX()).longValue(), (double) ((Long) ptArray.getY()).longValue(), (double) ((Long) ptArray2.getX()).longValue(), (double) ((Long) ptArray2.getY()).longValue());
    }

    private static CTAdjPoint2D[] addCubicBezierTo(CTPath2D cTPath2D) {
        CTPath2DCubicBezierTo addNewCubicBezTo = cTPath2D.addNewCubicBezTo();
        return new CTAdjPoint2D[]{addNewCubicBezTo.addNewPt(), addNewCubicBezTo.addNewPt(), addNewCubicBezTo.addNewPt()};
    }

    private static void addCubicBezierTo(Path2D path2D, CTPath2DCubicBezierTo cTPath2DCubicBezierTo) {
        CTPath2DCubicBezierTo cTPath2DCubicBezierTo2 = cTPath2DCubicBezierTo;
        CTAdjPoint2D ptArray = cTPath2DCubicBezierTo2.getPtArray(0);
        CTAdjPoint2D ptArray2 = cTPath2DCubicBezierTo2.getPtArray(1);
        CTAdjPoint2D ptArray3 = cTPath2DCubicBezierTo2.getPtArray(2);
        path2D.curveTo((double) ((Long) ptArray.getX()).longValue(), (double) ((Long) ptArray.getY()).longValue(), (double) ((Long) ptArray2.getX()).longValue(), (double) ((Long) ptArray2.getY()).longValue(), (double) ((Long) ptArray3.getX()).longValue(), (double) ((Long) ptArray3.getY()).longValue());
    }

    private static CTAdjPoint2D[] addClosePath(CTPath2D cTPath2D) {
        cTPath2D.addNewClose();
        return new CTAdjPoint2D[0];
    }

    private static void addClosePath(Path2D path2D) {
        path2D.closePath();
    }

    static CTShape prototype(int i) {
        CTShape newInstance = CTShape.Factory.newInstance();
        CTShapeNonVisual addNewNvSpPr = newInstance.addNewNvSpPr();
        CTNonVisualDrawingProps addNewCNvPr = addNewNvSpPr.addNewCNvPr();
        addNewCNvPr.setName("Freeform " + i);
        addNewCNvPr.setId((long) i);
        addNewNvSpPr.addNewCNvSpPr();
        addNewNvSpPr.addNewNvPr();
        CTCustomGeometry2D addNewCustGeom = newInstance.addNewSpPr().addNewCustGeom();
        addNewCustGeom.addNewAvLst();
        addNewCustGeom.addNewGdLst();
        addNewCustGeom.addNewAhLst();
        addNewCustGeom.addNewCxnLst();
        CTGeomRect addNewRect = addNewCustGeom.addNewRect();
        addNewRect.setR("r");
        addNewRect.setB("b");
        addNewRect.setT("t");
        addNewRect.setL("l");
        addNewCustGeom.addNewPathLst();
        return newInstance;
    }
}
