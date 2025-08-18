package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface RelType extends XmlObject {
    public static final DocumentFactory<RelType> Factory;
    public static final SchemaType type;

    String getId();

    void setId(String str);

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);

    static {
        DocumentFactory<RelType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "reltype05f2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
