package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.MinLengthDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;

public class MinLengthDocumentImpl extends XmlComplexContentImpl implements MinLengthDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "minLength")};
    private static final long serialVersionUID = 1;

    public MinLengthDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public NumFacet getMinLength() {
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

    public void setMinLength(NumFacet numFacet) {
        generatedSetterHelperImpl(numFacet, PROPERTY_QNAME[0], 0, 1);
    }

    public NumFacet addNewMinLength() {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            numFacet = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return numFacet;
    }
}
