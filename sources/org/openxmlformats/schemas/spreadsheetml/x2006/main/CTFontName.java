package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTFontName extends XmlObject {
    public static final DocumentFactory<CTFontName> Factory;
    public static final SchemaType type;

    String getVal();

    void setVal(String str);

    STXstring xgetVal();

    void xsetVal(STXstring sTXstring);

    static {
        DocumentFactory<CTFontName> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfontname2dc3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
