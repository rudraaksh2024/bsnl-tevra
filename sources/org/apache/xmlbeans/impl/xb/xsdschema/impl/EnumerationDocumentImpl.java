package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.EnumerationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;

public class EnumerationDocumentImpl extends XmlComplexContentImpl implements EnumerationDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "enumeration")};
    private static final long serialVersionUID = 1;

    public EnumerationDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public NoFixedFacet getEnumeration() {
        NoFixedFacet noFixedFacet;
        synchronized (monitor()) {
            check_orphaned();
            noFixedFacet = (NoFixedFacet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (noFixedFacet == null) {
                noFixedFacet = null;
            }
        }
        return noFixedFacet;
    }

    public void setEnumeration(NoFixedFacet noFixedFacet) {
        generatedSetterHelperImpl(noFixedFacet, PROPERTY_QNAME[0], 0, 1);
    }

    public NoFixedFacet addNewEnumeration() {
        NoFixedFacet noFixedFacet;
        synchronized (monitor()) {
            check_orphaned();
            noFixedFacet = (NoFixedFacet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return noFixedFacet;
    }
}
