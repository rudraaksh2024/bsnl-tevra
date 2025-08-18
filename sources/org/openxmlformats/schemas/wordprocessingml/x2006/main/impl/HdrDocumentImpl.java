package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument;

public class HdrDocumentImpl extends XmlComplexContentImpl implements HdrDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hdr")};
    private static final long serialVersionUID = 1;

    public HdrDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTHdrFtr getHdr() {
        CTHdrFtr cTHdrFtr;
        synchronized (monitor()) {
            check_orphaned();
            cTHdrFtr = (CTHdrFtr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTHdrFtr == null) {
                cTHdrFtr = null;
            }
        }
        return cTHdrFtr;
    }

    public void setHdr(CTHdrFtr cTHdrFtr) {
        generatedSetterHelperImpl(cTHdrFtr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTHdrFtr addNewHdr() {
        CTHdrFtr cTHdrFtr;
        synchronized (monitor()) {
            check_orphaned();
            cTHdrFtr = (CTHdrFtr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTHdrFtr;
    }
}
