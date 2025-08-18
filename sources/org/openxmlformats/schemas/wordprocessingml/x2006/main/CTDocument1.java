package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum;

public interface CTDocument1 extends CTDocumentBase {
    public static final DocumentFactory<CTDocument1> Factory;
    public static final SchemaType type;

    CTBody addNewBody();

    CTBody getBody();

    STConformanceClass$Enum getConformance();

    boolean isSetBody();

    boolean isSetConformance();

    void setBody(CTBody cTBody);

    void setConformance(STConformanceClass$Enum sTConformanceClass$Enum);

    void unsetBody();

    void unsetConformance();

    STConformanceClass xgetConformance();

    void xsetConformance(STConformanceClass sTConformanceClass);

    static {
        DocumentFactory<CTDocument1> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdocument64adtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
