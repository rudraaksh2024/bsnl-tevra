package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface XAdESTimeStampType extends GenericTimeStampType {
    public static final DocumentFactory<XAdESTimeStampType> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<XAdESTimeStampType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "xadestimestamptypeaedbtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
