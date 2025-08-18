package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTLineJoinRound extends XmlObject {
    public static final DocumentFactory<CTLineJoinRound> Factory;
    public static final SchemaType type;

    static {
        DocumentFactory<CTLineJoinRound> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlinejoinround7be1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
