package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTRegularTextRun extends XmlObject {
    public static final DocumentFactory<CTRegularTextRun> Factory;
    public static final SchemaType type;

    CTTextCharacterProperties addNewRPr();

    CTTextCharacterProperties getRPr();

    String getT();

    boolean isSetRPr();

    void setRPr(CTTextCharacterProperties cTTextCharacterProperties);

    void setT(String str);

    void unsetRPr();

    XmlString xgetT();

    void xsetT(XmlString xmlString);

    static {
        DocumentFactory<CTRegularTextRun> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctregulartextrun7e3dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
