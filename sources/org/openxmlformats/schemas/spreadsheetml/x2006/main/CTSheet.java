package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetState;

public interface CTSheet extends XmlObject {
    public static final DocumentFactory<CTSheet> Factory;
    public static final SchemaType type;

    String getId();

    String getName();

    long getSheetId();

    STSheetState.Enum getState();

    boolean isSetState();

    void setId(String str);

    void setName(String str);

    void setSheetId(long j);

    void setState(STSheetState.Enum enumR);

    void unsetState();

    STRelationshipId xgetId();

    STXstring xgetName();

    XmlUnsignedInt xgetSheetId();

    STSheetState xgetState();

    void xsetId(STRelationshipId sTRelationshipId);

    void xsetName(STXstring sTXstring);

    void xsetSheetId(XmlUnsignedInt xmlUnsignedInt);

    void xsetState(STSheetState sTSheetState);

    static {
        DocumentFactory<CTSheet> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsheet4dbetype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
