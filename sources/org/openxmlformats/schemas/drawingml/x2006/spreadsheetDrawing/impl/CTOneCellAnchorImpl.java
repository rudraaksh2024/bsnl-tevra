package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTOneCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTRel;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;

public class CTOneCellAnchorImpl extends XmlComplexContentImpl implements CTOneCellAnchor {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "from"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "ext"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "sp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "grpSp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "graphicFrame"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cxnSp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "pic"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "contentPart"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "clientData")};
    private static final long serialVersionUID = 1;

    public CTOneCellAnchorImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTMarker getFrom() {
        CTMarker cTMarker;
        synchronized (monitor()) {
            check_orphaned();
            cTMarker = (CTMarker) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTMarker == null) {
                cTMarker = null;
            }
        }
        return cTMarker;
    }

    public void setFrom(CTMarker cTMarker) {
        generatedSetterHelperImpl(cTMarker, PROPERTY_QNAME[0], 0, 1);
    }

    public CTMarker addNewFrom() {
        CTMarker cTMarker;
        synchronized (monitor()) {
            check_orphaned();
            cTMarker = (CTMarker) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTMarker;
    }

    public CTPositiveSize2D getExt() {
        CTPositiveSize2D cTPositiveSize2D;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveSize2D = (CTPositiveSize2D) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTPositiveSize2D == null) {
                cTPositiveSize2D = null;
            }
        }
        return cTPositiveSize2D;
    }

    public void setExt(CTPositiveSize2D cTPositiveSize2D) {
        generatedSetterHelperImpl(cTPositiveSize2D, PROPERTY_QNAME[1], 0, 1);
    }

    public CTPositiveSize2D addNewExt() {
        CTPositiveSize2D cTPositiveSize2D;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveSize2D = (CTPositiveSize2D) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTPositiveSize2D;
    }

    public CTShape getSp() {
        CTShape cTShape;
        synchronized (monitor()) {
            check_orphaned();
            cTShape = (CTShape) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTShape == null) {
                cTShape = null;
            }
        }
        return cTShape;
    }

    public boolean isSetSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setSp(CTShape cTShape) {
        generatedSetterHelperImpl(cTShape, PROPERTY_QNAME[2], 0, 1);
    }

    public CTShape addNewSp() {
        CTShape cTShape;
        synchronized (monitor()) {
            check_orphaned();
            cTShape = (CTShape) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTShape;
    }

    public void unsetSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTGroupShape getGrpSp() {
        CTGroupShape cTGroupShape;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupShape = (CTGroupShape) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTGroupShape == null) {
                cTGroupShape = null;
            }
        }
        return cTGroupShape;
    }

    public boolean isSetGrpSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setGrpSp(CTGroupShape cTGroupShape) {
        generatedSetterHelperImpl(cTGroupShape, PROPERTY_QNAME[3], 0, 1);
    }

    public CTGroupShape addNewGrpSp() {
        CTGroupShape cTGroupShape;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupShape = (CTGroupShape) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTGroupShape;
    }

    public void unsetGrpSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTGraphicalObjectFrame getGraphicFrame() {
        CTGraphicalObjectFrame cTGraphicalObjectFrame;
        synchronized (monitor()) {
            check_orphaned();
            cTGraphicalObjectFrame = (CTGraphicalObjectFrame) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTGraphicalObjectFrame == null) {
                cTGraphicalObjectFrame = null;
            }
        }
        return cTGraphicalObjectFrame;
    }

    public boolean isSetGraphicFrame() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setGraphicFrame(CTGraphicalObjectFrame cTGraphicalObjectFrame) {
        generatedSetterHelperImpl(cTGraphicalObjectFrame, PROPERTY_QNAME[4], 0, 1);
    }

    public CTGraphicalObjectFrame addNewGraphicFrame() {
        CTGraphicalObjectFrame cTGraphicalObjectFrame;
        synchronized (monitor()) {
            check_orphaned();
            cTGraphicalObjectFrame = (CTGraphicalObjectFrame) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTGraphicalObjectFrame;
    }

    public void unsetGraphicFrame() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTConnector getCxnSp() {
        CTConnector cTConnector;
        synchronized (monitor()) {
            check_orphaned();
            cTConnector = (CTConnector) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTConnector == null) {
                cTConnector = null;
            }
        }
        return cTConnector;
    }

    public boolean isSetCxnSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setCxnSp(CTConnector cTConnector) {
        generatedSetterHelperImpl(cTConnector, PROPERTY_QNAME[5], 0, 1);
    }

    public CTConnector addNewCxnSp() {
        CTConnector cTConnector;
        synchronized (monitor()) {
            check_orphaned();
            cTConnector = (CTConnector) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTConnector;
    }

    public void unsetCxnSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTPicture getPic() {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTPicture = (CTPicture) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTPicture == null) {
                cTPicture = null;
            }
        }
        return cTPicture;
    }

    public boolean isSetPic() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setPic(CTPicture cTPicture) {
        generatedSetterHelperImpl(cTPicture, PROPERTY_QNAME[6], 0, 1);
    }

    public CTPicture addNewPic() {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTPicture = (CTPicture) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTPicture;
    }

    public void unsetPic() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTRel getContentPart() {
        CTRel find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetContentPart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setContentPart(CTRel cTRel) {
        generatedSetterHelperImpl(cTRel, PROPERTY_QNAME[7], 0, 1);
    }

    public CTRel addNewContentPart() {
        CTRel add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return add_element_user;
    }

    public void unsetContentPart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTAnchorClientData getClientData() {
        CTAnchorClientData cTAnchorClientData;
        synchronized (monitor()) {
            check_orphaned();
            cTAnchorClientData = (CTAnchorClientData) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTAnchorClientData == null) {
                cTAnchorClientData = null;
            }
        }
        return cTAnchorClientData;
    }

    public void setClientData(CTAnchorClientData cTAnchorClientData) {
        generatedSetterHelperImpl(cTAnchorClientData, PROPERTY_QNAME[8], 0, 1);
    }

    public CTAnchorClientData addNewClientData() {
        CTAnchorClientData cTAnchorClientData;
        synchronized (monitor()) {
            check_orphaned();
            cTAnchorClientData = (CTAnchorClientData) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTAnchorClientData;
    }
}
