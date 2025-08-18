package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument;

public class PropertiesDocumentImpl extends XmlComplexContentImpl implements PropertiesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Properties")};
    private static final long serialVersionUID = 1;

    public PropertiesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTProperties getProperties() {
        CTProperties cTProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTProperties = (CTProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTProperties == null) {
                cTProperties = null;
            }
        }
        return cTProperties;
    }

    public void setProperties(CTProperties cTProperties) {
        generatedSetterHelperImpl(cTProperties, PROPERTY_QNAME[0], 0, 1);
    }

    public CTProperties addNewProperties() {
        CTProperties cTProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTProperties = (CTProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTProperties;
    }
}
