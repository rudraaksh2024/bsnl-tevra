package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextBulletColorFollowText extends XmlObject {
    public static final DocumentFactory<CTTextBulletColorFollowText> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTTextBulletColorFollowText> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextbulletcolorfollowtext2ca3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
