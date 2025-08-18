package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPrintOptions extends XmlObject {
    public static final DocumentFactory<CTPrintOptions> Factory;
    public static final SchemaType type;

    boolean getGridLines();

    boolean getGridLinesSet();

    boolean getHeadings();

    boolean getHorizontalCentered();

    boolean getVerticalCentered();

    boolean isSetGridLines();

    boolean isSetGridLinesSet();

    boolean isSetHeadings();

    boolean isSetHorizontalCentered();

    boolean isSetVerticalCentered();

    void setGridLines(boolean z);

    void setGridLinesSet(boolean z);

    void setHeadings(boolean z);

    void setHorizontalCentered(boolean z);

    void setVerticalCentered(boolean z);

    void unsetGridLines();

    void unsetGridLinesSet();

    void unsetHeadings();

    void unsetHorizontalCentered();

    void unsetVerticalCentered();

    XmlBoolean xgetGridLines();

    XmlBoolean xgetGridLinesSet();

    XmlBoolean xgetHeadings();

    XmlBoolean xgetHorizontalCentered();

    XmlBoolean xgetVerticalCentered();

    void xsetGridLines(XmlBoolean xmlBoolean);

    void xsetGridLinesSet(XmlBoolean xmlBoolean);

    void xsetHeadings(XmlBoolean xmlBoolean);

    void xsetHorizontalCentered(XmlBoolean xmlBoolean);

    void xsetVerticalCentered(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTPrintOptions> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctprintoptions943atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
