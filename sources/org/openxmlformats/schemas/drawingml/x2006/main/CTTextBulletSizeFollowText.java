package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextBulletSizeFollowText extends XmlObject {
    public static final DocumentFactory<CTTextBulletSizeFollowText> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTTextBulletSizeFollowText> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextbulletsizefollowtext11e9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
