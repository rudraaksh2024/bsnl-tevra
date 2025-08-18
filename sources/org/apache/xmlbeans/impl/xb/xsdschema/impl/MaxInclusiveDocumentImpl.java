package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.MaxInclusiveDocument;

public class MaxInclusiveDocumentImpl extends XmlComplexContentImpl implements MaxInclusiveDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "maxInclusive")};
    private static final long serialVersionUID = 1;

    public MaxInclusiveDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public Facet getMaxInclusive() {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (facet == null) {
                facet = null;
            }
        }
        return facet;
    }

    public void setMaxInclusive(Facet facet) {
        generatedSetterHelperImpl(facet, PROPERTY_QNAME[0], 0, 1);
    }

    public Facet addNewMaxInclusive() {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return facet;
    }
}
