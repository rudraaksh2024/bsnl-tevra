package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface TableDocument extends XmlObject {
    public static final DocumentFactory<TableDocument> Factory;
    public static final SchemaType type;

    CTTable addNewTable();

    CTTable getTable();

    void setTable(CTTable cTTable);

    static {
        DocumentFactory<TableDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "table0b99doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
