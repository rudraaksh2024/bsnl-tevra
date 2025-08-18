package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument;

public class NumberingDocumentImpl extends XmlComplexContentImpl implements NumberingDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numbering")};
    private static final long serialVersionUID = 1;

    public NumberingDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTNumbering getNumbering() {
        CTNumbering cTNumbering;
        synchronized (monitor()) {
            check_orphaned();
            cTNumbering = (CTNumbering) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTNumbering == null) {
                cTNumbering = null;
            }
        }
        return cTNumbering;
    }

    public void setNumbering(CTNumbering cTNumbering) {
        generatedSetterHelperImpl(cTNumbering, PROPERTY_QNAME[0], 0, 1);
    }

    public CTNumbering addNewNumbering() {
        CTNumbering cTNumbering;
        synchronized (monitor()) {
            check_orphaned();
            cTNumbering = (CTNumbering) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTNumbering;
    }
}
