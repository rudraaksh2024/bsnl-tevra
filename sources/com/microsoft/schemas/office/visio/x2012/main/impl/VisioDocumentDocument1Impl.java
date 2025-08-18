package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class VisioDocumentDocument1Impl extends XmlComplexContentImpl implements VisioDocumentDocument1 {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "VisioDocument")};
    private static final long serialVersionUID = 1;

    public VisioDocumentDocument1Impl(SchemaType schemaType) {
        super(schemaType);
    }

    public VisioDocumentType getVisioDocument() {
        VisioDocumentType visioDocumentType;
        synchronized (monitor()) {
            check_orphaned();
            visioDocumentType = (VisioDocumentType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (visioDocumentType == null) {
                visioDocumentType = null;
            }
        }
        return visioDocumentType;
    }

    public void setVisioDocument(VisioDocumentType visioDocumentType) {
        generatedSetterHelperImpl(visioDocumentType, PROPERTY_QNAME[0], 0, 1);
    }

    public VisioDocumentType addNewVisioDocument() {
        VisioDocumentType visioDocumentType;
        synchronized (monitor()) {
            check_orphaned();
            visioDocumentType = (VisioDocumentType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return visioDocumentType;
    }
}
