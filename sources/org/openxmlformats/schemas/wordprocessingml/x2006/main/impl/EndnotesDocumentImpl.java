package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument;

public class EndnotesDocumentImpl extends XmlComplexContentImpl implements EndnotesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "endnotes")};
    private static final long serialVersionUID = 1;

    public EndnotesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTEndnotes getEndnotes() {
        CTEndnotes cTEndnotes;
        synchronized (monitor()) {
            check_orphaned();
            cTEndnotes = (CTEndnotes) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTEndnotes == null) {
                cTEndnotes = null;
            }
        }
        return cTEndnotes;
    }

    public void setEndnotes(CTEndnotes cTEndnotes) {
        generatedSetterHelperImpl(cTEndnotes, PROPERTY_QNAME[0], 0, 1);
    }

    public CTEndnotes addNewEndnotes() {
        CTEndnotes cTEndnotes;
        synchronized (monitor()) {
            check_orphaned();
            cTEndnotes = (CTEndnotes) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTEndnotes;
    }
}
