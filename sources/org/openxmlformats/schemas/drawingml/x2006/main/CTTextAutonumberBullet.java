package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAutonumberScheme;

public interface CTTextAutonumberBullet extends XmlObject {
    public static final DocumentFactory<CTTextAutonumberBullet> Factory;
    public static final SchemaType type;

    int getStartAt();

    STTextAutonumberScheme.Enum getType();

    boolean isSetStartAt();

    void setStartAt(int i);

    void setType(STTextAutonumberScheme.Enum enumR);

    void unsetStartAt();

    STTextBulletStartAtNum xgetStartAt();

    STTextAutonumberScheme xgetType();

    void xsetStartAt(STTextBulletStartAtNum sTTextBulletStartAtNum);

    void xsetType(STTextAutonumberScheme sTTextAutonumberScheme);

    static {
        DocumentFactory<CTTextAutonumberBullet> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextautonumberbulletd602type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
