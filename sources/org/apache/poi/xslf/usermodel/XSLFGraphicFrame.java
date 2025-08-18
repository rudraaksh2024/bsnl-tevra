package org.apache.poi.xslf.usermodel;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.sl.usermodel.GraphicalFrame;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShape;

public class XSLFGraphicFrame extends XSLFShape implements GraphicalFrame<XSLFShape, XSLFTextParagraph> {
    private static final String DRAWINGML_CHART_URI = "http://schemas.openxmlformats.org/drawingml/2006/chart";
    private static final String DRAWINGML_DIAGRAM_URI = "http://schemas.openxmlformats.org/drawingml/2006/diagram";
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSLFGraphicFrame.class);

    public boolean getFlipHorizontal() {
        return false;
    }

    public boolean getFlipVertical() {
        return false;
    }

    public double getRotation() {
        return 0.0d;
    }

    XSLFGraphicFrame(CTGraphicalObjectFrame cTGraphicalObjectFrame, XSLFSheet xSLFSheet) {
        super(cTGraphicalObjectFrame, xSLFSheet);
    }

    public ShapeType getShapeType() {
        throw new UnsupportedOperationException();
    }

    public Rectangle2D getAnchor() {
        CTTransform2D xfrm = ((CTGraphicalObjectFrame) getXmlObject()).getXfrm();
        CTPoint2D off = xfrm.getOff();
        double points = Units.toPoints(POIXMLUnits.parseLength(off.xgetX()));
        double points2 = Units.toPoints(POIXMLUnits.parseLength(off.xgetY()));
        CTPositiveSize2D ext = xfrm.getExt();
        return new Rectangle2D.Double(points, points2, Units.toPoints(ext.getCx()), Units.toPoints(ext.getCy()));
    }

    public void setAnchor(Rectangle2D rectangle2D) {
        CTPositiveSize2D cTPositiveSize2D;
        CTTransform2D xfrm = ((CTGraphicalObjectFrame) getXmlObject()).getXfrm();
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

    static XSLFGraphicFrame create(CTGraphicalObjectFrame cTGraphicalObjectFrame, XSLFSheet xSLFSheet) {
        String uri = getUri(cTGraphicalObjectFrame);
        if (uri == null) {
            uri = "";
        }
        uri.hashCode();
        if (uri.equals("http://schemas.openxmlformats.org/presentationml/2006/ole")) {
            return new XSLFObjectShape(cTGraphicalObjectFrame, xSLFSheet);
        }
        if (!uri.equals("http://schemas.openxmlformats.org/drawingml/2006/table")) {
            return new XSLFGraphicFrame(cTGraphicalObjectFrame, xSLFSheet);
        }
        return new XSLFTable(cTGraphicalObjectFrame, xSLFSheet);
    }

    private static String getUri(CTGraphicalObjectFrame cTGraphicalObjectFrame) {
        CTGraphicalObjectData graphicData;
        CTGraphicalObject graphic = cTGraphicalObjectFrame.getGraphic();
        if (graphic == null || (graphicData = graphic.getGraphicData()) == null) {
            return null;
        }
        return graphicData.getUri();
    }

    public void setRotation(double d) {
        throw new IllegalArgumentException("Operation not supported");
    }

    public void setFlipHorizontal(boolean z) {
        throw new IllegalArgumentException("Operation not supported");
    }

    public void setFlipVertical(boolean z) {
        throw new IllegalArgumentException("Operation not supported");
    }

    public boolean hasChart() {
        return getGraphicalData().getUri().equals("http://schemas.openxmlformats.org/drawingml/2006/chart");
    }

    public boolean hasDiagram() {
        return getGraphicalData().getUri().equals(DRAWINGML_DIAGRAM_URI);
    }

    private CTGraphicalObjectData getGraphicalData() {
        return ((CTGraphicalObjectFrame) getXmlObject()).getGraphic().getGraphicData();
    }

    public XSLFChart getChart() {
        String str;
        if (!hasChart()) {
            return null;
        }
        XmlObject[] selectPath = getGraphicalData().selectPath("declare namespace c='http://schemas.openxmlformats.org/drawingml/2006/chart' c:chart");
        if (selectPath == null || selectPath.length != 1) {
            str = null;
        } else {
            XmlCursor newCursor = selectPath[0].newCursor();
            try {
                str = newCursor.getAttributeText(new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id"));
            } finally {
                newCursor.dispose();
            }
        }
        if (str == null) {
            return null;
        }
        return (XSLFChart) getSheet().getRelationById(str);
    }

    /* access modifiers changed from: package-private */
    public void copy(XSLFShape xSLFShape) {
        super.copy(xSLFShape);
        CTGraphicalObjectData graphicalData = getGraphicalData();
        String uri = graphicalData.getUri();
        if (uri.equals(DRAWINGML_DIAGRAM_URI)) {
            copyDiagram(graphicalData, (XSLFGraphicFrame) xSLFShape);
        }
        if (uri.equals("http://schemas.openxmlformats.org/drawingml/2006/chart")) {
            copyChart(graphicalData, (XSLFGraphicFrame) xSLFShape);
        }
    }

    private void copyChart(CTGraphicalObjectData cTGraphicalObjectData, XSLFGraphicFrame xSLFGraphicFrame) {
        XSLFPropertiesDelegate.XSLFFillProperties fillDelegate;
        XSLFSlide xSLFSlide = (XSLFSlide) getSheet();
        XSLFSheet sheet = xSLFGraphicFrame.getSheet();
        XmlObject[] selectPath = cTGraphicalObjectData.selectPath("declare namespace c='http://schemas.openxmlformats.org/drawingml/2006/chart' c:chart");
        if (selectPath != null && selectPath.length == 1) {
            XmlCursor newCursor = selectPath[0].newCursor();
            try {
                QName qName = new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id");
                XSLFChart xSLFChart = (XSLFChart) sheet.getRelationById(newCursor.getAttributeText(qName));
                XSLFChart createChart = xSLFSlide.getSlideShow().createChart(xSLFSlide);
                createChart.importContent(xSLFChart);
                createChart.setWorkbook(xSLFChart.getWorkbook());
                newCursor.setAttributeText(qName, xSLFSlide.getRelationId(createChart));
                CTChartSpace cTChartSpace = createChart.getCTChartSpace();
                if (!(cTChartSpace == null || (fillDelegate = XSLFPropertiesDelegate.getFillDelegate(cTChartSpace.getSpPr())) == null || !fillDelegate.isSetBlipFill())) {
                    CTBlip blip = fillDelegate.getBlipFill().getBlip();
                    blip.setEmbed(xSLFSlide.getSlideShow().importBlip(blip.getEmbed(), xSLFChart, createChart));
                }
                newCursor.dispose();
            } catch (IOException | InvalidFormatException e) {
                throw new POIXMLException(e);
            } catch (Throwable th) {
                newCursor.dispose();
                throw th;
            }
        }
    }

    private void copyDiagram(CTGraphicalObjectData cTGraphicalObjectData, XSLFGraphicFrame xSLFGraphicFrame) {
        XmlObject[] selectPath = cTGraphicalObjectData.selectPath("declare namespace dgm='http://schemas.openxmlformats.org/drawingml/2006/diagram' $this//dgm:relIds");
        if (selectPath != null && selectPath.length == 1) {
            XmlCursor newCursor = selectPath[0].newCursor();
            XSLFSheet sheet = xSLFGraphicFrame.getSheet();
            try {
                PackageRelationship relationship = sheet.getPackagePart().getRelationship(newCursor.getAttributeText(new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "dm")));
                getSheet().importPart(relationship, sheet.getPackagePart().getRelatedPart(relationship));
                PackageRelationship relationship2 = sheet.getPackagePart().getRelationship(newCursor.getAttributeText(new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "lo")));
                getSheet().importPart(relationship2, sheet.getPackagePart().getRelatedPart(relationship2));
                PackageRelationship relationship3 = sheet.getPackagePart().getRelationship(newCursor.getAttributeText(new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "qs")));
                getSheet().importPart(relationship3, sheet.getPackagePart().getRelatedPart(relationship3));
                PackageRelationship relationship4 = sheet.getPackagePart().getRelationship(newCursor.getAttributeText(new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "cs")));
                getSheet().importPart(relationship4, sheet.getPackagePart().getRelatedPart(relationship4));
                newCursor.dispose();
            } catch (InvalidFormatException e) {
                throw new POIXMLException((Throwable) e);
            } catch (Throwable th) {
                newCursor.dispose();
                throw th;
            }
        }
    }

    public XSLFPictureShape getFallbackPicture() {
        XmlObject selectProperty = selectProperty(XmlObject.class, "declare namespace p='http://schemas.openxmlformats.org/presentationml/2006/main'; declare namespace mc='http://schemas.openxmlformats.org/markup-compatibility/2006' .//mc:Fallback/*/p:pic");
        if (selectProperty == null) {
            return null;
        }
        try {
            CTGroupShape parse = CTGroupShape.Factory.parse(selectProperty.newDomNode());
            if (parse.sizeOfPicArray() == 0) {
                return null;
            }
            return new XSLFPictureShape(parse.getPicArray(0), getSheet());
        } catch (XmlException e) {
            LOG.atWarn().withThrowable(e).log("Can't parse fallback picture stream of graphical frame");
            return null;
        }
    }
}
