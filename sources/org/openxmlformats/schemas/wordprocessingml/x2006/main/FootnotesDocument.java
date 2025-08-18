package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface FootnotesDocument extends XmlObject {
    public static final DocumentFactory<FootnotesDocument> Factory;
    public static final SchemaType type;

    CTFootnotes addNewFootnotes();

    CTFootnotes getFootnotes();

    void setFootnotes(CTFootnotes cTFootnotes);

    static {
        DocumentFactory<FootnotesDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "footnotes8773doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
