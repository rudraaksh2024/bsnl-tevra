package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.KeyDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;

public class KeyDocumentImpl extends XmlComplexContentImpl implements KeyDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "key")};
    private static final long serialVersionUID = 1;

    public KeyDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public Keybase getKey() {
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

    public void setKey(Keybase keybase) {
        generatedSetterHelperImpl(keybase, PROPERTY_QNAME[0], 0, 1);
    }

    public Keybase addNewKey() {
        Keybase keybase;
        synchronized (monitor()) {
            check_orphaned();
            keybase = (Keybase) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return keybase;
    }
}
