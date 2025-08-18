package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface CTPageBorder extends CTBorder {
    public static final DocumentFactory<CTPageBorder> Factory;
    public static final SchemaType type;

    String getId();

    boolean isSetId();

    void setId(String str);

    void unsetId();

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);

    static {
        DocumentFactory<CTPageBorder> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpageborderd76dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
