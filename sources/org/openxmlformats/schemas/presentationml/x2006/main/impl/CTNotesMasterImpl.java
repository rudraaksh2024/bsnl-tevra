package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster;

public class CTNotesMasterImpl extends XmlComplexContentImpl implements CTNotesMaster {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cSld"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "clrMap"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "hf"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "notesStyle"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst")};
    private static final long serialVersionUID = 1;

    public CTNotesMasterImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTCommonSlideData getCSld() {
        CTCommonSlideData cTCommonSlideData;
        synchronized (monitor()) {
            check_orphaned();
            cTCommonSlideData = (CTCommonSlideData) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTCommonSlideData == null) {
                cTCommonSlideData = null;
            }
        }
        return cTCommonSlideData;
    }

    public void setCSld(CTCommonSlideData cTCommonSlideData) {
        generatedSetterHelperImpl(cTCommonSlideData, PROPERTY_QNAME[0], 0, 1);
    }

    public CTCommonSlideData addNewCSld() {
        CTCommonSlideData cTCommonSlideData;
        synchronized (monitor()) {
            check_orphaned();
            cTCommonSlideData = (CTCommonSlideData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCommonSlideData;
    }

    public CTColorMapping getClrMap() {
        CTColorMapping cTColorMapping;
        synchronized (monitor()) {
            check_orphaned();
            cTColorMapping = (CTColorMapping) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTColorMapping == null) {
                cTColorMapping = null;
            }
        }
        return cTColorMapping;
    }

    public void setClrMap(CTColorMapping cTColorMapping) {
        generatedSetterHelperImpl(cTColorMapping, PROPERTY_QNAME[1], 0, 1);
    }

    public CTColorMapping addNewClrMap() {
        CTColorMapping cTColorMapping;
        synchronized (monitor()) {
            check_orphaned();
            cTColorMapping = (CTColorMapping) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTColorMapping;
    }

    public CTHeaderFooter getHf() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            cTHeaderFooter = (CTHeaderFooter) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTHeaderFooter == null) {
                cTHeaderFooter = null;
            }
        }
        return cTHeaderFooter;
    }

    public boolean isSetHf() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setHf(CTHeaderFooter cTHeaderFooter) {
        generatedSetterHelperImpl(cTHeaderFooter, PROPERTY_QNAME[2], 0, 1);
    }

    public CTHeaderFooter addNewHf() {
        CTHeaderFooter cTHeaderFooter;
        synchronized (monitor()) {
            check_orphaned();
            cTHeaderFooter = (CTHeaderFooter) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTHeaderFooter;
    }

    public void unsetHf() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTTextListStyle getNotesStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTextListStyle = (CTTextListStyle) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTTextListStyle == null) {
                cTTextListStyle = null;
            }
        }
        return cTTextListStyle;
    }

    public boolean isSetNotesStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setNotesStyle(CTTextListStyle cTTextListStyle) {
        generatedSetterHelperImpl(cTTextListStyle, PROPERTY_QNAME[3], 0, 1);
    }

    public CTTextListStyle addNewNotesStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTextListStyle = (CTTextListStyle) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTTextListStyle;
    }

    public void unsetNotesStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTExtensionListModify getExtLst() {
        CTExtensionListModify find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionListModify cTExtensionListModify) {
        generatedSetterHelperImpl(cTExtensionListModify, PROPERTY_QNAME[4], 0, 1);
    }

    public CTExtensionListModify addNewExtLst() {
        CTExtensionListModify add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return add_element_user;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
