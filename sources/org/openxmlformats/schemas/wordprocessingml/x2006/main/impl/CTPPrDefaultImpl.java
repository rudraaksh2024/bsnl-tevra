package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrGeneral;

public class CTPPrDefaultImpl extends XmlComplexContentImpl implements CTPPrDefault {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pPr")};
    private static final long serialVersionUID = 1;

    public CTPPrDefaultImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTPPrGeneral getPPr() {
        CTPPrGeneral cTPPrGeneral;
        synchronized (monitor()) {
            check_orphaned();
            cTPPrGeneral = (CTPPrGeneral) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTPPrGeneral == null) {
                cTPPrGeneral = null;
            }
        }
        return cTPPrGeneral;
    }

    public boolean isSetPPr() {
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

    public void setPPr(CTPPrGeneral cTPPrGeneral) {
        generatedSetterHelperImpl(cTPPrGeneral, PROPERTY_QNAME[0], 0, 1);
    }

    public CTPPrGeneral addNewPPr() {
        CTPPrGeneral cTPPrGeneral;
        synchronized (monitor()) {
            check_orphaned();
            cTPPrGeneral = (CTPPrGeneral) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTPPrGeneral;
    }

    public void unsetPPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
