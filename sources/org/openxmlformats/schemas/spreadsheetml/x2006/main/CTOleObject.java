package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDvAspect;

public interface CTOleObject extends XmlObject {
    public static final DocumentFactory<CTOleObject> Factory;
    public static final SchemaType type;

    CTObjectPr addNewObjectPr();

    boolean getAutoLoad();

    STDvAspect.Enum getDvAspect();

    String getId();

    String getLink();

    CTObjectPr getObjectPr();

    STOleUpdate$Enum getOleUpdate();

    String getProgId();

    long getShapeId();

    boolean isSetAutoLoad();

    boolean isSetDvAspect();

    boolean isSetId();

    boolean isSetLink();

    boolean isSetObjectPr();

    boolean isSetOleUpdate();

    boolean isSetProgId();

    void setAutoLoad(boolean z);

    void setDvAspect(STDvAspect.Enum enumR);

    void setId(String str);

    void setLink(String str);

    void setObjectPr(CTObjectPr cTObjectPr);

    void setOleUpdate(STOleUpdate$Enum sTOleUpdate$Enum);

    void setProgId(String str);

    void setShapeId(long j);

    void unsetAutoLoad();

    void unsetDvAspect();

    void unsetId();

    void unsetLink();

    void unsetObjectPr();

    void unsetOleUpdate();

    void unsetProgId();

    XmlBoolean xgetAutoLoad();

    STDvAspect xgetDvAspect();

    STRelationshipId xgetId();

    STXstring xgetLink();

    STOleUpdate xgetOleUpdate();

    XmlString xgetProgId();

    XmlUnsignedInt xgetShapeId();

    void xsetAutoLoad(XmlBoolean xmlBoolean);

    void xsetDvAspect(STDvAspect sTDvAspect);

    void xsetId(STRelationshipId sTRelationshipId);

    void xsetLink(STXstring sTXstring);

    void xsetOleUpdate(STOleUpdate sTOleUpdate);

    void xsetProgId(XmlString xmlString);

    void xsetShapeId(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTOleObject> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctoleobjectd866type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
