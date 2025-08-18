package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectFrameLocking;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

public class CTNonVisualGraphicFramePropertiesImpl extends XmlComplexContentImpl implements CTNonVisualGraphicFrameProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "graphicFrameLocks"), new QName(XSSFRelation.NS_DRAWINGML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTNonVisualGraphicFramePropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTGraphicalObjectFrameLocking getGraphicFrameLocks() {
        CTGraphicalObjectFrameLocking cTGraphicalObjectFrameLocking;
        synchronized (monitor()) {
            check_orphaned();
            cTGraphicalObjectFrameLocking = (CTGraphicalObjectFrameLocking) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTGraphicalObjectFrameLocking == null) {
                cTGraphicalObjectFrameLocking = null;
            }
        }
        return cTGraphicalObjectFrameLocking;
    }

    public boolean isSetGraphicFrameLocks() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setGraphicFrameLocks(CTGraphicalObjectFrameLocking cTGraphicalObjectFrameLocking) {
        generatedSetterHelperImpl(cTGraphicalObjectFrameLocking, PROPERTY_QNAME[0], 0, 1);
    }

    public CTGraphicalObjectFrameLocking addNewGraphicFrameLocks() {
        CTGraphicalObjectFrameLocking cTGraphicalObjectFrameLocking;
        synchronized (monitor()) {
            check_orphaned();
            cTGraphicalObjectFrameLocking = (CTGraphicalObjectFrameLocking) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTGraphicalObjectFrameLocking;
    }

    public void unsetGraphicFrameLocks() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTOfficeArtExtensionList == null) {
                cTOfficeArtExtensionList = null;
            }
        }
        return cTOfficeArtExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        generatedSetterHelperImpl(cTOfficeArtExtensionList, PROPERTY_QNAME[1], 0, 1);
    }

    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTOfficeArtExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
