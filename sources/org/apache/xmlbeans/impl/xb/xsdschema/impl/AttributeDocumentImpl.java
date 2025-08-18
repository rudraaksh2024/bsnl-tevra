package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;

public class AttributeDocumentImpl extends XmlComplexContentImpl implements AttributeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "attribute")};
    private static final long serialVersionUID = 1;

    public AttributeDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public TopLevelAttribute getAttribute() {
        TopLevelAttribute topLevelAttribute;
        synchronized (monitor()) {
            check_orphaned();
            topLevelAttribute = (TopLevelAttribute) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (topLevelAttribute == null) {
                topLevelAttribute = null;
            }
        }
        return topLevelAttribute;
    }

    public void setAttribute(TopLevelAttribute topLevelAttribute) {
        generatedSetterHelperImpl(topLevelAttribute, PROPERTY_QNAME[0], 0, 1);
    }

    public TopLevelAttribute addNewAttribute() {
        TopLevelAttribute topLevelAttribute;
        synchronized (monitor()) {
            check_orphaned();
            topLevelAttribute = (TopLevelAttribute) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return topLevelAttribute;
    }
}
