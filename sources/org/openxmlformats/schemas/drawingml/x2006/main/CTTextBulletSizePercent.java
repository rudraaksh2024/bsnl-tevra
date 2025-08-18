package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextBulletSizePercent extends XmlObject {
    public static final DocumentFactory<CTTextBulletSizePercent> Factory;
    public static final SchemaType type;

    String getVal();

    void setVal(String str);

    STTextBulletSizePercent xgetVal();

    void xsetVal(STTextBulletSizePercent sTTextBulletSizePercent);

    static {
        DocumentFactory<CTTextBulletSizePercent> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextbulletsizepercent9b26type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
