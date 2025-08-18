package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrDefault;

public class CTDocDefaultsImpl extends XmlComplexContentImpl implements CTDocDefaults {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPrDefault"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pPrDefault")};
    private static final long serialVersionUID = 1;

    public CTDocDefaultsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTRPrDefault getRPrDefault() {
        CTRPrDefault cTRPrDefault;
        synchronized (monitor()) {
            check_orphaned();
            cTRPrDefault = (CTRPrDefault) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTRPrDefault == null) {
                cTRPrDefault = null;
            }
        }
        return cTRPrDefault;
    }

    public boolean isSetRPrDefault() {
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

    public void setRPrDefault(CTRPrDefault cTRPrDefault) {
        generatedSetterHelperImpl(cTRPrDefault, PROPERTY_QNAME[0], 0, 1);
    }

    public CTRPrDefault addNewRPrDefault() {
        CTRPrDefault cTRPrDefault;
        synchronized (monitor()) {
            check_orphaned();
            cTRPrDefault = (CTRPrDefault) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRPrDefault;
    }

    public void unsetRPrDefault() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTPPrDefault getPPrDefault() {
        CTPPrDefault cTPPrDefault;
        synchronized (monitor()) {
            check_orphaned();
            cTPPrDefault = (CTPPrDefault) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTPPrDefault == null) {
                cTPPrDefault = null;
            }
        }
        return cTPPrDefault;
    }

    public boolean isSetPPrDefault() {
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

    public void setPPrDefault(CTPPrDefault cTPPrDefault) {
        generatedSetterHelperImpl(cTPPrDefault, PROPERTY_QNAME[1], 0, 1);
    }

    public CTPPrDefault addNewPPrDefault() {
        CTPPrDefault cTPPrDefault;
        synchronized (monitor()) {
            check_orphaned();
            cTPPrDefault = (CTPPrDefault) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTPPrDefault;
    }

    public void unsetPPrDefault() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
