package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeID;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

public interface CTOleObject extends XmlObject {
    public static final DocumentFactory<CTOleObject> Factory;
    public static final SchemaType type;

    CTOleObjectEmbed addNewEmbed();

    CTOleObjectLink addNewLink();

    CTPicture addNewPic();

    CTOleObjectEmbed getEmbed();

    String getId();

    int getImgH();

    int getImgW();

    CTOleObjectLink getLink();

    String getName();

    CTPicture getPic();

    String getProgId();

    boolean getShowAsIcon();

    String getSpid();

    boolean isSetEmbed();

    boolean isSetId();

    boolean isSetImgH();

    boolean isSetImgW();

    boolean isSetLink();

    boolean isSetName();

    boolean isSetPic();

    boolean isSetProgId();

    boolean isSetShowAsIcon();

    boolean isSetSpid();

    void setEmbed(CTOleObjectEmbed cTOleObjectEmbed);

    void setId(String str);

    void setImgH(int i);

    void setImgW(int i);

    void setLink(CTOleObjectLink cTOleObjectLink);

    void setName(String str);

    void setPic(CTPicture cTPicture);

    void setProgId(String str);

    void setShowAsIcon(boolean z);

    void setSpid(String str);

    void unsetEmbed();

    void unsetId();

    void unsetImgH();

    void unsetImgW();

    void unsetLink();

    void unsetName();

    void unsetPic();

    void unsetProgId();

    void unsetShowAsIcon();

    void unsetSpid();

    STRelationshipId xgetId();

    STPositiveCoordinate32 xgetImgH();

    STPositiveCoordinate32 xgetImgW();

    XmlString xgetName();

    XmlString xgetProgId();

    XmlBoolean xgetShowAsIcon();

    STShapeID xgetSpid();

    void xsetId(STRelationshipId sTRelationshipId);

    void xsetImgH(STPositiveCoordinate32 sTPositiveCoordinate32);

    void xsetImgW(STPositiveCoordinate32 sTPositiveCoordinate32);

    void xsetName(XmlString xmlString);

    void xsetProgId(XmlString xmlString);

    void xsetShowAsIcon(XmlBoolean xmlBoolean);

    void xsetSpid(STShapeID sTShapeID);

    static {
        DocumentFactory<CTOleObject> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctoleobject5da8type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
