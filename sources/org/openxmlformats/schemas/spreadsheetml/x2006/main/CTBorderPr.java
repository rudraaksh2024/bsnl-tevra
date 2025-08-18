package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STBorderStyle;

public interface CTBorderPr extends XmlObject {
    public static final DocumentFactory<CTBorderPr> Factory;
    public static final SchemaType type;

    CTColor addNewColor();

    CTColor getColor();

    STBorderStyle.Enum getStyle();

    boolean isSetColor();

    boolean isSetStyle();

    void setColor(CTColor cTColor);

    void setStyle(STBorderStyle.Enum enumR);

    void unsetColor();

    void unsetStyle();

    STBorderStyle xgetStyle();

    void xsetStyle(STBorderStyle sTBorderStyle);

    static {
        DocumentFactory<CTBorderPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctborderpre497type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
