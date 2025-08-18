package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface NotesDocument extends XmlObject {
    public static final DocumentFactory<NotesDocument> Factory;
    public static final SchemaType type;

    CTNotesSlide addNewNotes();

    CTNotesSlide getNotes();

    void setNotes(CTNotesSlide cTNotesSlide);

    static {
        DocumentFactory<NotesDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "notes4a02doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
