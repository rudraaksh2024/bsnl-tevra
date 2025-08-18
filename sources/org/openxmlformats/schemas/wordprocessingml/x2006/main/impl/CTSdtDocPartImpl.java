package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDocPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;

public class CTSdtDocPartImpl extends XmlComplexContentImpl implements CTSdtDocPart {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docPartGallery"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docPartCategory"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docPartUnique")};
    private static final long serialVersionUID = 1;

    public CTSdtDocPartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTString getDocPartGallery() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTString == null) {
                cTString = null;
            }
        }
        return cTString;
    }

    public boolean isSetDocPartGallery() {
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

    public void setDocPartGallery(CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[0], 0, 1);
    }

    public CTString addNewDocPartGallery() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTString;
    }

    public void unsetDocPartGallery() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTString getDocPartCategory() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTString == null) {
                cTString = null;
            }
        }
        return cTString;
    }

    public boolean isSetDocPartCategory() {
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

    public void setDocPartCategory(CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[1], 0, 1);
    }

    public CTString addNewDocPartCategory() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTString;
    }

    public void unsetDocPartCategory() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTOnOff getDocPartUnique() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetDocPartUnique() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setDocPartUnique(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[2], 0, 1);
    }

    public CTOnOff addNewDocPartUnique() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTOnOff;
    }

    public void unsetDocPartUnique() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
