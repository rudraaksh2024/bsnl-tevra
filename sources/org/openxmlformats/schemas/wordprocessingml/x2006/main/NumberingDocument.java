package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface NumberingDocument extends XmlObject {
    public static final DocumentFactory<NumberingDocument> Factory;
    public static final SchemaType type;

    CTNumbering addNewNumbering();

    CTNumbering getNumbering();

    void setNumbering(CTNumbering cTNumbering);

    static {
        DocumentFactory<NumberingDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "numbering1c4ddoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
