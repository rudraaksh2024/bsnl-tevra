package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFonts extends XmlObject {
    public static final DocumentFactory<CTFonts> Factory;
    public static final SchemaType type;

    CTFont addNewFont();

    long getCount();

    CTFont getFontArray(int i);

    CTFont[] getFontArray();

    List<CTFont> getFontList();

    CTFont insertNewFont(int i);

    boolean isSetCount();

    void removeFont(int i);

    void setCount(long j);

    void setFontArray(int i, CTFont cTFont);

    void setFontArray(CTFont[] cTFontArr);

    int sizeOfFontArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTFonts> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfonts6623type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
