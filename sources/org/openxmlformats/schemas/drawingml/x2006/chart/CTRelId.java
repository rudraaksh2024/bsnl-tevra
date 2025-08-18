package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface CTRelId extends XmlObject {
    public static final DocumentFactory<CTRelId> Factory;
    public static final SchemaType type;

    String getId();

    void setId(String str);

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);

    static {
        DocumentFactory<CTRelId> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrelida492type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
