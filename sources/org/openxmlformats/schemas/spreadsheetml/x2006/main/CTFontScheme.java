package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontScheme;

public interface CTFontScheme extends XmlObject {
    public static final DocumentFactory<CTFontScheme> Factory;
    public static final SchemaType type;

    STFontScheme.Enum getVal();

    void setVal(STFontScheme.Enum enumR);

    STFontScheme xgetVal();

    void xsetVal(STFontScheme sTFontScheme);

    static {
        DocumentFactory<CTFontScheme> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfontschemebf5dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
