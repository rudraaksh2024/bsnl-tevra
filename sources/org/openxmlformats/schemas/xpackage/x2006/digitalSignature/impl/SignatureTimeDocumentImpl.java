package org.openxmlformats.schemas.xpackage.x2006.digitalSignature.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.SignatureTimeDocument;

public class SignatureTimeDocumentImpl extends XmlComplexContentImpl implements SignatureTimeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/package/2006/digital-signature", "SignatureTime")};
    private static final long serialVersionUID = 1;

    public SignatureTimeDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSignatureTime getSignatureTime() {
        CTSignatureTime cTSignatureTime;
        synchronized (monitor()) {
            check_orphaned();
            cTSignatureTime = (CTSignatureTime) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSignatureTime == null) {
                cTSignatureTime = null;
            }
        }
        return cTSignatureTime;
    }

    public void setSignatureTime(CTSignatureTime cTSignatureTime) {
        generatedSetterHelperImpl(cTSignatureTime, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSignatureTime addNewSignatureTime() {
        CTSignatureTime cTSignatureTime;
        synchronized (monitor()) {
            check_orphaned();
            cTSignatureTime = (CTSignatureTime) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSignatureTime;
    }
}
