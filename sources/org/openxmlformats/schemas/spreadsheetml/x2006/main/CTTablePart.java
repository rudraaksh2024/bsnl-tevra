package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface CTTablePart extends XmlObject {
    public static final DocumentFactory<CTTablePart> Factory;
    public static final SchemaType type;

    String getId();

    void setId(String str);

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);

    static {
        DocumentFactory<CTTablePart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablepart1140type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
