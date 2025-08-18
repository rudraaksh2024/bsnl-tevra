package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;

public class CTEffectStyleItemImpl extends XmlComplexContentImpl implements CTEffectStyleItem {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "effectLst"), new QName(XSSFRelation.NS_DRAWINGML, "effectDag"), new QName(XSSFRelation.NS_DRAWINGML, "scene3d"), new QName(XSSFRelation.NS_DRAWINGML, "sp3d")};
    private static final long serialVersionUID = 1;

    public CTEffectStyleItemImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTEffectList getEffectLst() {
        CTEffectList cTEffectList;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectList = (CTEffectList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTEffectList == null) {
                cTEffectList = null;
            }
        }
        return cTEffectList;
    }

    public boolean isSetEffectLst() {
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

    public void setEffectLst(CTEffectList cTEffectList) {
        generatedSetterHelperImpl(cTEffectList, PROPERTY_QNAME[0], 0, 1);
    }

    public CTEffectList addNewEffectLst() {
        CTEffectList cTEffectList;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectList = (CTEffectList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTEffectList;
    }

    public void unsetEffectLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTEffectContainer getEffectDag() {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectContainer = (CTEffectContainer) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTEffectContainer == null) {
                cTEffectContainer = null;
            }
        }
        return cTEffectContainer;
    }

    public boolean isSetEffectDag() {
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

    public void setEffectDag(CTEffectContainer cTEffectContainer) {
        generatedSetterHelperImpl(cTEffectContainer, PROPERTY_QNAME[1], 0, 1);
    }

    public CTEffectContainer addNewEffectDag() {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectContainer = (CTEffectContainer) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTEffectContainer;
    }

    public void unsetEffectDag() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTScene3D getScene3D() {
        CTScene3D cTScene3D;
        synchronized (monitor()) {
            check_orphaned();
            cTScene3D = (CTScene3D) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTScene3D == null) {
                cTScene3D = null;
            }
        }
        return cTScene3D;
    }

    public boolean isSetScene3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setScene3D(CTScene3D cTScene3D) {
        generatedSetterHelperImpl(cTScene3D, PROPERTY_QNAME[2], 0, 1);
    }

    public CTScene3D addNewScene3D() {
        CTScene3D cTScene3D;
        synchronized (monitor()) {
            check_orphaned();
            cTScene3D = (CTScene3D) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTScene3D;
    }

    public void unsetScene3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTShape3D getSp3D() {
        CTShape3D cTShape3D;
        synchronized (monitor()) {
            check_orphaned();
            cTShape3D = (CTShape3D) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTShape3D == null) {
                cTShape3D = null;
            }
        }
        return cTShape3D;
    }

    public boolean isSetSp3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setSp3D(CTShape3D cTShape3D) {
        generatedSetterHelperImpl(cTShape3D, PROPERTY_QNAME[3], 0, 1);
    }

    public CTShape3D addNewSp3D() {
        CTShape3D cTShape3D;
        synchronized (monitor()) {
            check_orphaned();
            cTShape3D = (CTShape3D) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTShape3D;
    }

    public void unsetSp3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
