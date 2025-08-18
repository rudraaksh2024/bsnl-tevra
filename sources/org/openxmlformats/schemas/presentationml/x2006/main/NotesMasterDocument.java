package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface NotesMasterDocument extends XmlObject {
    public static final DocumentFactory<NotesMasterDocument> Factory;
    public static final SchemaType type;

    CTNotesMaster addNewNotesMaster();

    CTNotesMaster getNotesMaster();

    void setNotesMaster(CTNotesMaster cTNotesMaster);

    static {
        DocumentFactory<NotesMasterDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "notesmaster8840doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
