package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;

public class AttributeGroupDocumentImpl extends XmlComplexContentImpl implements AttributeGroupDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup")};
    private static final long serialVersionUID = 1;

    public AttributeGroupDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public NamedAttributeGroup getAttributeGroup() {
        NamedAttributeGroup namedAttributeGroup;
        synchronized (monitor()) {
            check_orphaned();
            namedAttributeGroup = (NamedAttributeGroup) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (namedAttributeGroup == null) {
                namedAttributeGroup = null;
            }
        }
        return namedAttributeGroup;
    }

    public void setAttributeGroup(NamedAttributeGroup namedAttributeGroup) {
        generatedSetterHelperImpl(namedAttributeGroup, PROPERTY_QNAME[0], 0, 1);
    }

    public NamedAttributeGroup addNewAttributeGroup() {
        NamedAttributeGroup namedAttributeGroup;
        synchronized (monitor()) {
            check_orphaned();
            namedAttributeGroup = (NamedAttributeGroup) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return namedAttributeGroup;
    }
}
