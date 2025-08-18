package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

public interface CTText extends STString {
    public static final DocumentFactory<CTText> Factory;
    public static final SchemaType type;

    SpaceAttribute.Space.Enum getSpace();

    boolean isSetSpace();

    void setSpace(SpaceAttribute.Space.Enum enumR);

    void unsetSpace();

    SpaceAttribute.Space xgetSpace();

    void xsetSpace(SpaceAttribute.Space space);

    static {
        DocumentFactory<CTText> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttext8965type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
