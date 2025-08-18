package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface StyleSheetsType extends XmlObject {
    public static final DocumentFactory<StyleSheetsType> Factory;
    public static final SchemaType type;

    StyleSheetType addNewStyleSheet();

    StyleSheetType getStyleSheetArray(int i);

    StyleSheetType[] getStyleSheetArray();

    List<StyleSheetType> getStyleSheetList();

    StyleSheetType insertNewStyleSheet(int i);

    void removeStyleSheet(int i);

    void setStyleSheetArray(int i, StyleSheetType styleSheetType);

    void setStyleSheetArray(StyleSheetType[] styleSheetTypeArr);

    int sizeOfStyleSheetArray();

    static {
        DocumentFactory<StyleSheetsType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "stylesheetstypeb706type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
