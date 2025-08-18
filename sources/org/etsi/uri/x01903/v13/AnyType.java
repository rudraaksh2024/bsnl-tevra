package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface AnyType extends XmlObject {
    public static final DocumentFactory<AnyType> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<AnyType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "anytype96c8type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
