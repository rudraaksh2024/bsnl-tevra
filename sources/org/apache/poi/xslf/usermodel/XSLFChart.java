package org.apache.poi.xslf.usermodel;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;

public final class XSLFChart extends XDDFChart {
    private static String CHART_URI = "http://schemas.openxmlformats.org/drawingml/2006/chart";

    protected XSLFChart() {
    }

    protected XSLFChart(PackagePart packagePart) throws IOException, XmlException {
        super(packagePart);
    }

    /* access modifiers changed from: protected */
    public POIXMLRelation getChartRelation() {
        return XSLFRelation.CHART;
    }

    /* access modifiers changed from: protected */
    public POIXMLRelation getChartWorkbookRelation() {
        return XSLFRelation.WORKBOOK;
    }

    /* access modifiers changed from: protected */
    public POIXMLFactory getChartFactory() {
        return XSLFFactory.getInstance();
    }

    public XSLFTextShape getTitleShape() {
        if (!getCTChart().isSetTitle()) {
            getCTChart().addNewTitle();
        }
        final CTTitle title = getCTChart().getTitle();
        if (title.getTx() == null || !title.getTx().isSetRich()) {
            return new XSLFTextShape((XSLFSheet) null, title) {
                /* access modifiers changed from: protected */
                public CTTextBody getTextBody(boolean z) {
                    return title.getTxPr();
                }
            };
        }
        return new XSLFTextShape((XSLFSheet) null, title) {
            /* access modifiers changed from: protected */
            public CTTextBody getTextBody(boolean z) {
                return title.getTx().getRich();
            }
        };
    }

    /* JADX INFO: finally extract failed */
    static CTGraphicalObjectFrame prototype(int i, String str, Rectangle2D rectangle2D) {
        CTGraphicalObjectFrame newInstance = CTGraphicalObjectFrame.Factory.newInstance();
        CTGraphicalObjectFrameNonVisual addNewNvGraphicFramePr = newInstance.addNewNvGraphicFramePr();
        CTNonVisualDrawingProps addNewCNvPr = addNewNvGraphicFramePr.addNewCNvPr();
        addNewCNvPr.setName("Chart " + i);
        addNewCNvPr.setId((long) i);
        addNewNvGraphicFramePr.addNewCNvGraphicFramePr().addNewGraphicFrameLocks().setNoGrp(true);
        addNewNvGraphicFramePr.addNewNvPr();
        CTTransform2D addNewXfrm = newInstance.addNewXfrm();
        CTPoint2D addNewOff = addNewXfrm.addNewOff();
        addNewOff.setX(Integer.valueOf((int) rectangle2D.getX()));
        addNewOff.setY(Integer.valueOf((int) rectangle2D.getY()));
        CTPositiveSize2D addNewExt = addNewXfrm.addNewExt();
        addNewExt.setCx((long) ((int) rectangle2D.getWidth()));
        addNewExt.setCy((long) ((int) rectangle2D.getHeight()));
        addNewXfrm.setExt(addNewExt);
        addNewXfrm.setOff(addNewOff);
        CTGraphicalObjectData addNewGraphicData = newInstance.addNewGraphic().addNewGraphicData();
        XmlCursor newCursor = addNewGraphicData.newCursor();
        try {
            newCursor.toNextToken();
            newCursor.beginElement(new QName(CHART_URI, "chart"));
            newCursor.insertAttributeWithValue("id", PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, str);
            newCursor.dispose();
            addNewGraphicData.setUri(CHART_URI);
            return newInstance;
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }
}
