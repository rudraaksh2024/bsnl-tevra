package com.microsoft.schemas.office.x2006.digsig.impl;

import com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1;
import com.microsoft.schemas.office.x2006.digsig.SignatureInfoV1Document;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class SignatureInfoV1DocumentImpl extends XmlComplexContentImpl implements SignatureInfoV1Document {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureInfoV1")};
    private static final long serialVersionUID = 1;

    public SignatureInfoV1DocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSignatureInfoV1 getSignatureInfoV1() {
        CTSignatureInfoV1 cTSignatureInfoV1;
        synchronized (monitor()) {
            check_orphaned();
            cTSignatureInfoV1 = (CTSignatureInfoV1) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSignatureInfoV1 == null) {
                cTSignatureInfoV1 = null;
            }
        }
        return cTSignatureInfoV1;
    }

    public void setSignatureInfoV1(CTSignatureInfoV1 cTSignatureInfoV1) {
        generatedSetterHelperImpl(cTSignatureInfoV1, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSignatureInfoV1 addNewSignatureInfoV1() {
        CTSignatureInfoV1 cTSignatureInfoV1;
        synchronized (monitor()) {
            check_orphaned();
            cTSignatureInfoV1 = (CTSignatureInfoV1) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSignatureInfoV1;
    }
}
