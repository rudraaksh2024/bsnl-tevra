package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface CTPivotCache extends XmlObject {
    public static final DocumentFactory<CTPivotCache> Factory;
    public static final SchemaType type;

    long getCacheId();

    String getId();

    void setCacheId(long j);

    void setId(String str);

    XmlUnsignedInt xgetCacheId();

    STRelationshipId xgetId();

    void xsetCacheId(XmlUnsignedInt xmlUnsignedInt);

    void xsetId(STRelationshipId sTRelationshipId);

    static {
        DocumentFactory<CTPivotCache> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpivotcache4de9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
