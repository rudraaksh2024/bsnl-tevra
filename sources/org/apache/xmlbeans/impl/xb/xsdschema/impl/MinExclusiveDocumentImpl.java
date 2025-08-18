package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.MinExclusiveDocument;

public class MinExclusiveDocumentImpl extends XmlComplexContentImpl implements MinExclusiveDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "minExclusive")};
    private static final long serialVersionUID = 1;

    public MinExclusiveDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public Facet getMinExclusive() {
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

    public void setMinExclusive(Facet facet) {
        generatedSetterHelperImpl(facet, PROPERTY_QNAME[0], 0, 1);
    }

    public Facet addNewMinExclusive() {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            facet = (Facet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return facet;
    }
}
