package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.FootnotesDocument;

public class FootnotesDocumentImpl extends XmlComplexContentImpl implements FootnotesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnotes")};
    private static final long serialVersionUID = 1;

    public FootnotesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTFootnotes getFootnotes() {
        CTFootnotes cTFootnotes;
        synchronized (monitor()) {
            check_orphaned();
            cTFootnotes = (CTFootnotes) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTFootnotes == null) {
                cTFootnotes = null;
            }
        }
        return cTFootnotes;
    }

    public void setFootnotes(CTFootnotes cTFootnotes) {
        generatedSetterHelperImpl(cTFootnotes, PROPERTY_QNAME[0], 0, 1);
    }

    public CTFootnotes addNewFootnotes() {
        CTFootnotes cTFootnotes;
        synchronized (monitor()) {
            check_orphaned();
            cTFootnotes = (CTFootnotes) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTFootnotes;
    }
}
