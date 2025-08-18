package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.impl.xb.xsdschema.UniqueDocument;

public class UniqueDocumentImpl extends XmlComplexContentImpl implements UniqueDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "unique")};
    private static final long serialVersionUID = 1;

    public UniqueDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public Keybase getUnique() {
        Keybase keybase;
        synchronized (monitor()) {
            check_orphaned();
            keybase = (Keybase) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (keybase == null) {
                keybase = null;
            }
        }
        return keybase;
    }

    public void setUnique(Keybase keybase) {
        generatedSetterHelperImpl(keybase, PROPERTY_QNAME[0], 0, 1);
    }

    public Keybase addNewUnique() {
        Keybase keybase;
        synchronized (monitor()) {
            check_orphaned();
            keybase = (Keybase) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return keybase;
    }
}
