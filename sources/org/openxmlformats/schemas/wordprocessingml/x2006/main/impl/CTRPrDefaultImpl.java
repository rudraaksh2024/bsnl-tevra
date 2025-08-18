package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrDefault;

public class CTRPrDefaultImpl extends XmlComplexContentImpl implements CTRPrDefault {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr")};
    private static final long serialVersionUID = 1;

    public CTRPrDefaultImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTRPr getRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRPr = (CTRPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTRPr == null) {
                cTRPr = null;
            }
        }
        return cTRPr;
    }

    public boolean isSetRPr() {
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

    public void setRPr(CTRPr cTRPr) {
        generatedSetterHelperImpl(cTRPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTRPr addNewRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRPr = (CTRPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRPr;
    }

    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
