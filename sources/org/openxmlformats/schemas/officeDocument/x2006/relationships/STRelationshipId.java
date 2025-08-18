package org.openxmlformats.schemas.officeDocument.x2006.relationships;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STRelationshipId extends XmlString {
    public static final SimpleTypeFactory<STRelationshipId> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STRelationshipId> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "strelationshipid1e94type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
