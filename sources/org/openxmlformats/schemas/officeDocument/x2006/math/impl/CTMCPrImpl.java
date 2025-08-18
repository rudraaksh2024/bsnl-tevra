package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTInteger255;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTXAlign;

public class CTMCPrImpl extends XmlComplexContentImpl implements CTMCPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "count"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mcJc")};
    private static final long serialVersionUID = 1;

    public CTMCPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTInteger255 getCount() {
        CTInteger255 cTInteger255;
        synchronized (monitor()) {
            check_orphaned();
            cTInteger255 = (CTInteger255) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTInteger255 == null) {
                cTInteger255 = null;
            }
        }
        return cTInteger255;
    }

    public boolean isSetCount() {
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

    public void setCount(CTInteger255 cTInteger255) {
        generatedSetterHelperImpl(cTInteger255, PROPERTY_QNAME[0], 0, 1);
    }

    public CTInteger255 addNewCount() {
        CTInteger255 cTInteger255;
        synchronized (monitor()) {
            check_orphaned();
            cTInteger255 = (CTInteger255) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTInteger255;
    }

    public void unsetCount() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTXAlign getMcJc() {
        CTXAlign cTXAlign;
        synchronized (monitor()) {
            check_orphaned();
            cTXAlign = (CTXAlign) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTXAlign == null) {
                cTXAlign = null;
            }
        }
        return cTXAlign;
    }

    public boolean isSetMcJc() {
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

    public void setMcJc(CTXAlign cTXAlign) {
        generatedSetterHelperImpl(cTXAlign, PROPERTY_QNAME[1], 0, 1);
    }

    public CTXAlign addNewMcJc() {
        CTXAlign cTXAlign;
        synchronized (monitor()) {
            check_orphaned();
            cTXAlign = (CTXAlign) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTXAlign;
    }

    public void unsetMcJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
