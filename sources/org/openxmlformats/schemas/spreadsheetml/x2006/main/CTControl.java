package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface CTControl extends XmlObject {
    public static final DocumentFactory<CTControl> Factory;
    public static final SchemaType type;

    CTControlPr addNewControlPr();

    CTControlPr getControlPr();

    String getId();

    String getName();

    long getShapeId();

    boolean isSetControlPr();

    boolean isSetName();

    void setControlPr(CTControlPr cTControlPr);

    void setId(String str);

    void setName(String str);

    void setShapeId(long j);

    void unsetControlPr();

    void unsetName();

    STRelationshipId xgetId();

    XmlString xgetName();

    XmlUnsignedInt xgetShapeId();

    void xsetId(STRelationshipId sTRelationshipId);

    void xsetName(XmlString xmlString);

    void xsetShapeId(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTControl> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcontrol997ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
