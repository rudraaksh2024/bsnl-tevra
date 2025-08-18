package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface CTEmbeddedFontDataId extends XmlObject {
    public static final DocumentFactory<CTEmbeddedFontDataId> Factory;
    public static final SchemaType type;

    String getId();

    void setId(String str);

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);

    static {
        DocumentFactory<CTEmbeddedFontDataId> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctembeddedfontdataid7d67type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
