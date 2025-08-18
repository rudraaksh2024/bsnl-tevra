package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrOriginal;

public class CTParaRPrChangeImpl extends CTTrackChangeImpl implements CTParaRPrChange {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr")};
    private static final long serialVersionUID = 1;

    public CTParaRPrChangeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTParaRPrOriginal getRPr() {
        CTParaRPrOriginal cTParaRPrOriginal;
        synchronized (monitor()) {
            check_orphaned();
            cTParaRPrOriginal = (CTParaRPrOriginal) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTParaRPrOriginal == null) {
                cTParaRPrOriginal = null;
            }
        }
        return cTParaRPrOriginal;
    }

    public void setRPr(CTParaRPrOriginal cTParaRPrOriginal) {
        generatedSetterHelperImpl(cTParaRPrOriginal, PROPERTY_QNAME[0], 0, 1);
    }

    public CTParaRPrOriginal addNewRPr() {
        CTParaRPrOriginal cTParaRPrOriginal;
        synchronized (monitor()) {
            check_orphaned();
            cTParaRPrOriginal = (CTParaRPrOriginal) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTParaRPrOriginal;
    }
}
