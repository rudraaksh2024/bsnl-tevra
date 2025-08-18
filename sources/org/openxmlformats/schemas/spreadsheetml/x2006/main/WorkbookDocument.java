package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface WorkbookDocument extends XmlObject {
    public static final DocumentFactory<WorkbookDocument> Factory;
    public static final SchemaType type;

    CTWorkbook addNewWorkbook();

    CTWorkbook getWorkbook();

    void setWorkbook(CTWorkbook cTWorkbook);

    static {
        DocumentFactory<WorkbookDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "workbookec17doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
