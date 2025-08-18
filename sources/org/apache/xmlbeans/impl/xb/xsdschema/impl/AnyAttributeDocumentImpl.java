package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyAttributeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;

public class AnyAttributeDocumentImpl extends XmlComplexContentImpl implements AnyAttributeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute")};
    private static final long serialVersionUID = 1;

    public AnyAttributeDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public Wildcard getAnyAttribute() {
        Wildcard wildcard;
        synchronized (monitor()) {
            check_orphaned();
            wildcard = (Wildcard) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (wildcard == null) {
                wildcard = null;
            }
        }
        return wildcard;
    }

    public void setAnyAttribute(Wildcard wildcard) {
        generatedSetterHelperImpl(wildcard, PROPERTY_QNAME[0], 0, 1);
    }

    public Wildcard addNewAnyAttribute() {
        Wildcard wildcard;
        synchronized (monitor()) {
            check_orphaned();
            wildcard = (Wildcard) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return wildcard;
    }
}
