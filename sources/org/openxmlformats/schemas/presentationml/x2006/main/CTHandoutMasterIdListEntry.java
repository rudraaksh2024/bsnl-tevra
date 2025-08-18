package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface CTHandoutMasterIdListEntry extends XmlObject {
    public static final DocumentFactory<CTHandoutMasterIdListEntry> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTExtensionList getExtLst();

    String getId();

    boolean isSetExtLst();

    void setExtLst(CTExtensionList cTExtensionList);

    void setId(String str);

    void unsetExtLst();

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);

    static {
        DocumentFactory<CTHandoutMasterIdListEntry> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cthandoutmasteridlistentryad8dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
