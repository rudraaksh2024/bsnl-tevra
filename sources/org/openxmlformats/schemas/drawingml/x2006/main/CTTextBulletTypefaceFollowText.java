package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextBulletTypefaceFollowText extends XmlObject {
    public static final DocumentFactory<CTTextBulletTypefaceFollowText> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTTextBulletTypefaceFollowText> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextbullettypefacefollowtextd07ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
