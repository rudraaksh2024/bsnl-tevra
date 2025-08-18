package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument;

public class DocumentDocumentImpl extends XmlComplexContentImpl implements DocumentDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "document")};
    private static final long serialVersionUID = 1;

    public DocumentDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTDocument1 getDocument() {
        CTDocument1 cTDocument1;
        synchronized (monitor()) {
            check_orphaned();
            cTDocument1 = (CTDocument1) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTDocument1 == null) {
                cTDocument1 = null;
            }
        }
        return cTDocument1;
    }

    public void setDocument(CTDocument1 cTDocument1) {
        generatedSetterHelperImpl(cTDocument1, PROPERTY_QNAME[0], 0, 1);
    }

    public CTDocument1 addNewDocument() {
        CTDocument1 cTDocument1;
        synchronized (monitor()) {
            check_orphaned();
            cTDocument1 = (CTDocument1) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTDocument1;
    }
}
