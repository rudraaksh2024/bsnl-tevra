package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.MaxLengthDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;

public class MaxLengthDocumentImpl extends XmlComplexContentImpl implements MaxLengthDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "maxLength")};
    private static final long serialVersionUID = 1;

    public MaxLengthDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public NumFacet getMaxLength() {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (numFacet == null) {
                numFacet = null;
            }
        }
        return numFacet;
    }

    public void setMaxLength(NumFacet numFacet) {
        generatedSetterHelperImpl(numFacet, PROPERTY_QNAME[0], 0, 1);
    }

    public NumFacet addNewMaxLength() {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return numFacet;
    }
}
