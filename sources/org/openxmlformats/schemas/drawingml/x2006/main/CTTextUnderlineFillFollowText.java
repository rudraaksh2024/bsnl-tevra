package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTextUnderlineFillFollowText extends XmlObject {
    public static final DocumentFactory<CTTextUnderlineFillFollowText> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTTextUnderlineFillFollowText> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextunderlinefillfollowtext8011type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
