package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPageMargins extends XmlObject {
    public static final DocumentFactory<CTPageMargins> Factory;
    public static final SchemaType type;

    double getBottom();

    double getFooter();

    double getHeader();

    double getLeft();

    double getRight();

    double getTop();

    void setBottom(double d);

    void setFooter(double d);

    void setHeader(double d);

    void setLeft(double d);

    void setRight(double d);

    void setTop(double d);

    XmlDouble xgetBottom();

    XmlDouble xgetFooter();

    XmlDouble xgetHeader();

    XmlDouble xgetLeft();

    XmlDouble xgetRight();

    XmlDouble xgetTop();

    void xsetBottom(XmlDouble xmlDouble);

    void xsetFooter(XmlDouble xmlDouble);

    void xsetHeader(XmlDouble xmlDouble);

    void xsetLeft(XmlDouble xmlDouble);

    void xsetRight(XmlDouble xmlDouble);

    void xsetTop(XmlDouble xmlDouble);

    static {
        DocumentFactory<CTPageMargins> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpagemargins5455type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
