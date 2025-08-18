package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface CTExternalReference extends XmlObject {
    public static final DocumentFactory<CTExternalReference> Factory;
    public static final SchemaType type;

    String getId();

    void setId(String str);

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);

    static {
        DocumentFactory<CTExternalReference> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternalreference945ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
