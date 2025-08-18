package org.apache.poi.xssf.usermodel;

import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class XSSFGraphicFrame extends XSSFShape {
    private static CTGraphicalObjectFrame prototype;
    private final CTGraphicalObjectFrame graphicFrame;

    /* access modifiers changed from: protected */
    public CTShapeProperties getShapeProperties() {
        return null;
    }

    protected XSSFGraphicFrame(XSSFDrawing xSSFDrawing, CTGraphicalObjectFrame cTGraphicalObjectFrame) {
        this.drawing = xSSFDrawing;
        this.graphicFrame = cTGraphicalObjectFrame;
        CTGraphicalObjectData graphicData = cTGraphicalObjectFrame.getGraphic().getGraphicData();
        if (graphicData != null) {
            NodeList childNodes = graphicData.getDomNode().getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item.getNodeName().equals("c:chart")) {
                    POIXMLDocumentPart relationById = xSSFDrawing.getRelationById(item.getAttributes().getNamedItem("r:id").getNodeValue());
                    if (relationById instanceof XSSFChart) {
                        ((XSSFChart) relationById).setGraphicFrame(this);
                    }
                }
            }
        }
    }

    @Internal
    public CTGraphicalObjectFrame getCTGraphicalObjectFrame() {
        return this.graphicFrame;
    }

    protected static CTGraphicalObjectFrame prototype() {
        if (prototype == null) {
            CTGraphicalObjectFrame newInstance = CTGraphicalObjectFrame.Factory.newInstance();
            CTGraphicalObjectFrameNonVisual addNewNvGraphicFramePr = newInstance.addNewNvGraphicFramePr();
            CTNonVisualDrawingProps addNewCNvPr = addNewNvGraphicFramePr.addNewCNvPr();
            addNewCNvPr.setId(0);
            addNewCNvPr.setName("Diagramm 1");
            addNewNvGraphicFramePr.addNewCNvGraphicFramePr();
            CTTransform2D addNewXfrm = newInstance.addNewXfrm();
            CTPositiveSize2D addNewExt = addNewXfrm.addNewExt();
            CTPoint2D addNewOff = addNewXfrm.addNewOff();
            addNewExt.setCx(0);
            addNewExt.setCy(0);
            addNewOff.setX(0);
            addNewOff.setY(0);
            newInstance.addNewGraphic();
            prototype = newInstance;
        }
        return prototype;
    }

    public void setMacro(String str) {
        this.graphicFrame.setMacro(str);
    }

    public void setName(String str) {
        getNonVisualProperties().setName(str);
    }

    public String getName() {
        return getNonVisualProperties().getName();
    }

    private CTNonVisualDrawingProps getNonVisualProperties() {
        return this.graphicFrame.getNvGraphicFramePr().getCNvPr();
    }

    /* access modifiers changed from: protected */
    public void setAnchor(XSSFClientAnchor xSSFClientAnchor) {
        this.anchor = xSSFClientAnchor;
    }

    public XSSFClientAnchor getAnchor() {
        return (XSSFClientAnchor) this.anchor;
    }

    /* access modifiers changed from: protected */
    public void setChart(XSSFChart xSSFChart, String str) {
        appendChartElement(this.graphicFrame.getGraphic().addNewGraphicData(), str);
        xSSFChart.setGraphicFrame(this);
    }

    public long getId() {
        return this.graphicFrame.getNvGraphicFramePr().getCNvPr().getId();
    }

    /* access modifiers changed from: protected */
    public void setId(long j) {
        this.graphicFrame.getNvGraphicFramePr().getCNvPr().setId(j);
    }

    /* JADX INFO: finally extract failed */
    private void appendChartElement(CTGraphicalObjectData cTGraphicalObjectData, String str) {
        String namespaceURI = STRelationshipId.type.getName().getNamespaceURI();
        XmlCursor newCursor = cTGraphicalObjectData.newCursor();
        try {
            newCursor.toNextToken();
            newCursor.beginElement(new QName(XSSFRelation.NS_CHART, "chart", "c"));
            newCursor.insertAttributeWithValue(new QName(namespaceURI, "id", "r"), str);
            newCursor.dispose();
            cTGraphicalObjectData.setUri(XSSFRelation.NS_CHART);
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }

    public String getShapeName() {
        return this.graphicFrame.getNvGraphicFramePr().getCNvPr().getName();
    }
}
