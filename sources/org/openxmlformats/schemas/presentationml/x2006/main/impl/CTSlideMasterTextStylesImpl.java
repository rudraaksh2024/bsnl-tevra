package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterTextStyles;

public class CTSlideMasterTextStylesImpl extends XmlComplexContentImpl implements CTSlideMasterTextStyles {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "titleStyle"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "bodyStyle"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "otherStyle"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst")};
    private static final long serialVersionUID = 1;

    public CTSlideMasterTextStylesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTextListStyle getTitleStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTextListStyle = (CTTextListStyle) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTextListStyle == null) {
                cTTextListStyle = null;
            }
        }
        return cTTextListStyle;
    }

    public boolean isSetTitleStyle() {
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

    public void setTitleStyle(CTTextListStyle cTTextListStyle) {
        generatedSetterHelperImpl(cTTextListStyle, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTextListStyle addNewTitleStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTextListStyle = (CTTextListStyle) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTextListStyle;
    }

    public void unsetTitleStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTextListStyle getBodyStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTextListStyle = (CTTextListStyle) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTextListStyle == null) {
                cTTextListStyle = null;
            }
        }
        return cTTextListStyle;
    }

    public boolean isSetBodyStyle() {
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

    public void setBodyStyle(CTTextListStyle cTTextListStyle) {
        generatedSetterHelperImpl(cTTextListStyle, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTextListStyle addNewBodyStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTextListStyle = (CTTextListStyle) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTextListStyle;
    }

    public void unsetBodyStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTTextListStyle getOtherStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTextListStyle = (CTTextListStyle) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTTextListStyle == null) {
                cTTextListStyle = null;
            }
        }
        return cTTextListStyle;
    }

    public boolean isSetOtherStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setOtherStyle(CTTextListStyle cTTextListStyle) {
        generatedSetterHelperImpl(cTTextListStyle, PROPERTY_QNAME[2], 0, 1);
    }

    public CTTextListStyle addNewOtherStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTextListStyle = (CTTextListStyle) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTTextListStyle;
    }

    public void unsetOtherStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTExtensionList == null) {
                cTExtensionList = null;
            }
        }
        return cTExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[3], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
