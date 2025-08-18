package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextLineBreak extends XmlObject {
    public static final DocumentFactory<CTTextLineBreak> Factory;
    public static final SchemaType type;

    CTTextCharacterProperties addNewRPr();

    CTTextCharacterProperties getRPr();

    boolean isSetRPr();

    void setRPr(CTTextCharacterProperties cTTextCharacterProperties);

    void unsetRPr();

    static {
        DocumentFactory<CTTextLineBreak> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextlinebreak932ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
