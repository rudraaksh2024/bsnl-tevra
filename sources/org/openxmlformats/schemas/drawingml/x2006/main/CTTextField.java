package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;

public interface CTTextField extends XmlObject {
    public static final DocumentFactory<CTTextField> Factory;
    public static final SchemaType type;

    CTTextParagraphProperties addNewPPr();

    CTTextCharacterProperties addNewRPr();

    String getId();

    CTTextParagraphProperties getPPr();

    CTTextCharacterProperties getRPr();

    String getT();

    String getType();

    boolean isSetPPr();

    boolean isSetRPr();

    boolean isSetT();

    boolean isSetType();

    void setId(String str);

    void setPPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setRPr(CTTextCharacterProperties cTTextCharacterProperties);

    void setT(String str);

    void setType(String str);

    void unsetPPr();

    void unsetRPr();

    void unsetT();

    void unsetType();

    STGuid xgetId();

    XmlString xgetT();

    XmlString xgetType();

    void xsetId(STGuid sTGuid);

    void xsetT(XmlString xmlString);

    void xsetType(XmlString xmlString);

    static {
        DocumentFactory<CTTextField> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextfield187etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
