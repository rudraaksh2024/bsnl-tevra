package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexTypeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;

public class ComplexTypeDocumentImpl extends XmlComplexContentImpl implements ComplexTypeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "complexType")};
    private static final long serialVersionUID = 1;

    public ComplexTypeDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public TopLevelComplexType getComplexType() {
        TopLevelComplexType topLevelComplexType;
        synchronized (monitor()) {
            check_orphaned();
            topLevelComplexType = (TopLevelComplexType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (topLevelComplexType == null) {
                topLevelComplexType = null;
            }
        }
        return topLevelComplexType;
    }

    public void setComplexType(TopLevelComplexType topLevelComplexType) {
        generatedSetterHelperImpl(topLevelComplexType, PROPERTY_QNAME[0], 0, 1);
    }

    public TopLevelComplexType addNewComplexType() {
        TopLevelComplexType topLevelComplexType;
        synchronized (monitor()) {
            check_orphaned();
            topLevelComplexType = (TopLevelComplexType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return topLevelComplexType;
    }
}
