package org.apache.poi.schemas.vmldrawing.impl;

import javax.xml.namespace.QName;
import org.apache.poi.schemas.vmldrawing.CTXML;
import org.apache.poi.schemas.vmldrawing.XmlDocument;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class XmlDocumentImpl extends XmlComplexContentImpl implements XmlDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-poi-apache-org:vmldrawing", "xml")};
    private static final long serialVersionUID = 1;

    public XmlDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTXML getXml() {
        CTXML ctxml;
        synchronized (monitor()) {
            check_orphaned();
            ctxml = (CTXML) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (ctxml == null) {
                ctxml = null;
            }
        }
        return ctxml;
    }

    public void setXml(CTXML ctxml) {
        generatedSetterHelperImpl(ctxml, PROPERTY_QNAME[0], 0, 1);
    }

    public CTXML addNewXml() {
        CTXML ctxml;
        synchronized (monitor()) {
            check_orphaned();
            ctxml = (CTXML) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return ctxml;
    }
}
