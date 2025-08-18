package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArg;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSub;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSSubPr;

public class CTSSubImpl extends XmlComplexContentImpl implements CTSSub {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sSubPr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "e"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sub")};
    private static final long serialVersionUID = 1;

    public CTSSubImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSSubPr getSSubPr() {
        CTSSubPr cTSSubPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSSubPr = (CTSSubPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSSubPr == null) {
                cTSSubPr = null;
            }
        }
        return cTSSubPr;
    }

    public boolean isSetSSubPr() {
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

    public void setSSubPr(CTSSubPr cTSSubPr) {
        generatedSetterHelperImpl(cTSSubPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSSubPr addNewSSubPr() {
        CTSSubPr cTSSubPr;
        synchronized (monitor()) {
            check_orphaned();
            cTSSubPr = (CTSSubPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSSubPr;
    }

    public void unsetSSubPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTOMathArg getE() {
        CTOMathArg cTOMathArg;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathArg = (CTOMathArg) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTOMathArg == null) {
                cTOMathArg = null;
            }
        }
        return cTOMathArg;
    }

    public void setE(CTOMathArg cTOMathArg) {
        generatedSetterHelperImpl(cTOMathArg, PROPERTY_QNAME[1], 0, 1);
    }

    public CTOMathArg addNewE() {
        CTOMathArg cTOMathArg;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathArg = (CTOMathArg) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTOMathArg;
    }

    public CTOMathArg getSub() {
        CTOMathArg cTOMathArg;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathArg = (CTOMathArg) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTOMathArg == null) {
                cTOMathArg = null;
            }
        }
        return cTOMathArg;
    }

    public void setSub(CTOMathArg cTOMathArg) {
        generatedSetterHelperImpl(cTOMathArg, PROPERTY_QNAME[2], 0, 1);
    }

    public CTOMathArg addNewSub() {
        CTOMathArg cTOMathArg;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathArg = (CTOMathArg) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTOMathArg;
    }
}
