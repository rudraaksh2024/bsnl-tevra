package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextListStyle extends XmlObject {
    public static final DocumentFactory<CTTextListStyle> Factory;
    public static final SchemaType type;

    CTTextParagraphProperties addNewDefPPr();

    CTOfficeArtExtensionList addNewExtLst();

    CTTextParagraphProperties addNewLvl1PPr();

    CTTextParagraphProperties addNewLvl2PPr();

    CTTextParagraphProperties addNewLvl3PPr();

    CTTextParagraphProperties addNewLvl4PPr();

    CTTextParagraphProperties addNewLvl5PPr();

    CTTextParagraphProperties addNewLvl6PPr();

    CTTextParagraphProperties addNewLvl7PPr();

    CTTextParagraphProperties addNewLvl8PPr();

    CTTextParagraphProperties addNewLvl9PPr();

    CTTextParagraphProperties getDefPPr();

    CTOfficeArtExtensionList getExtLst();

    CTTextParagraphProperties getLvl1PPr();

    CTTextParagraphProperties getLvl2PPr();

    CTTextParagraphProperties getLvl3PPr();

    CTTextParagraphProperties getLvl4PPr();

    CTTextParagraphProperties getLvl5PPr();

    CTTextParagraphProperties getLvl6PPr();

    CTTextParagraphProperties getLvl7PPr();

    CTTextParagraphProperties getLvl8PPr();

    CTTextParagraphProperties getLvl9PPr();

    boolean isSetDefPPr();

    boolean isSetExtLst();

    boolean isSetLvl1PPr();

    boolean isSetLvl2PPr();

    boolean isSetLvl3PPr();

    boolean isSetLvl4PPr();

    boolean isSetLvl5PPr();

    boolean isSetLvl6PPr();

    boolean isSetLvl7PPr();

    boolean isSetLvl8PPr();

    boolean isSetLvl9PPr();

    void setDefPPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setLvl1PPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setLvl2PPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setLvl3PPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setLvl4PPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setLvl5PPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setLvl6PPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setLvl7PPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setLvl8PPr(CTTextParagraphProperties cTTextParagraphProperties);

    void setLvl9PPr(CTTextParagraphProperties cTTextParagraphProperties);

    void unsetDefPPr();

    void unsetExtLst();

    void unsetLvl1PPr();

    void unsetLvl2PPr();

    void unsetLvl3PPr();

    void unsetLvl4PPr();

    void unsetLvl5PPr();

    void unsetLvl6PPr();

    void unsetLvl7PPr();

    void unsetLvl8PPr();

    void unsetLvl9PPr();

    static {
        DocumentFactory<CTTextListStyle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextliststyleab77type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
