package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class CTTextListStyleImpl extends XmlComplexContentImpl implements CTTextListStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "defPPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl1pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl2pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl3pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl4pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl5pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl6pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl7pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl8pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl9pPr"), new QName(XSSFRelation.NS_DRAWINGML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTTextListStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTextParagraphProperties getDefPPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetDefPPr() {
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

    public void setDefPPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTextParagraphProperties addNewDefPPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetDefPPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTextParagraphProperties getLvl1PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetLvl1PPr() {
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

    public void setLvl1PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTextParagraphProperties addNewLvl1PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetLvl1PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTTextParagraphProperties getLvl2PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetLvl2PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setLvl2PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[2], 0, 1);
    }

    public CTTextParagraphProperties addNewLvl2PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetLvl2PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTTextParagraphProperties getLvl3PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetLvl3PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setLvl3PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[3], 0, 1);
    }

    public CTTextParagraphProperties addNewLvl3PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetLvl3PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTTextParagraphProperties getLvl4PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetLvl4PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setLvl4PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[4], 0, 1);
    }

    public CTTextParagraphProperties addNewLvl4PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetLvl4PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTTextParagraphProperties getLvl5PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetLvl5PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setLvl5PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[5], 0, 1);
    }

    public CTTextParagraphProperties addNewLvl5PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetLvl5PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTTextParagraphProperties getLvl6PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetLvl6PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setLvl6PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[6], 0, 1);
    }

    public CTTextParagraphProperties addNewLvl6PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetLvl6PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTTextParagraphProperties getLvl7PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetLvl7PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setLvl7PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[7], 0, 1);
    }

    public CTTextParagraphProperties addNewLvl7PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetLvl7PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTTextParagraphProperties getLvl8PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetLvl8PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setLvl8PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[8], 0, 1);
    }

    public CTTextParagraphProperties addNewLvl8PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetLvl8PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTTextParagraphProperties getLvl9PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetLvl9PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setLvl9PPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[9], 0, 1);
    }

    public CTTextParagraphProperties addNewLvl9PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetLvl9PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[10], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        generatedSetterHelperImpl(cTOfficeArtExtensionList, PROPERTY_QNAME[10], 0, 1);
    }

    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTOfficeArtExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }
}
