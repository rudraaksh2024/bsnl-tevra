package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument;

public class NotesDocumentImpl extends XmlComplexContentImpl implements NotesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "notes")};
    private static final long serialVersionUID = 1;

    public NotesDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTNotesSlide getNotes() {
        CTNotesSlide cTNotesSlide;
        synchronized (monitor()) {
            check_orphaned();
            cTNotesSlide = (CTNotesSlide) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTNotesSlide == null) {
                cTNotesSlide = null;
            }
        }
        return cTNotesSlide;
    }

    public void setNotes(CTNotesSlide cTNotesSlide) {
        generatedSetterHelperImpl(cTNotesSlide, PROPERTY_QNAME[0], 0, 1);
    }

    public CTNotesSlide addNewNotes() {
        CTNotesSlide cTNotesSlide;
        synchronized (monitor()) {
            check_orphaned();
            cTNotesSlide = (CTNotesSlide) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTNotesSlide;
    }
}
