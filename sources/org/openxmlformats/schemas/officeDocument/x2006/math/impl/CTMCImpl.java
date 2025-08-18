package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMC;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMCPr;

public class CTMCImpl extends XmlComplexContentImpl implements CTMC {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mcPr")};
    private static final long serialVersionUID = 1;

    public CTMCImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTMCPr getMcPr() {
        CTMCPr cTMCPr;
        synchronized (monitor()) {
            check_orphaned();
            cTMCPr = (CTMCPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTMCPr == null) {
                cTMCPr = null;
            }
        }
        return cTMCPr;
    }

    public boolean isSetMcPr() {
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

    public void setMcPr(CTMCPr cTMCPr) {
        generatedSetterHelperImpl(cTMCPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTMCPr addNewMcPr() {
        CTMCPr cTMCPr;
        synchronized (monitor()) {
            check_orphaned();
            cTMCPr = (CTMCPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTMCPr;
    }

    public void unsetMcPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
