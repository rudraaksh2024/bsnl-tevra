package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public interface CTDPt extends XmlObject {
    public static final DocumentFactory<CTDPt> Factory;
    public static final SchemaType type;

    CTBoolean addNewBubble3D();

    CTUnsignedInt addNewExplosion();

    CTExtensionList addNewExtLst();

    CTUnsignedInt addNewIdx();

    CTBoolean addNewInvertIfNegative();

    CTMarker addNewMarker();

    CTPictureOptions addNewPictureOptions();

    CTShapeProperties addNewSpPr();

    CTBoolean getBubble3D();

    CTUnsignedInt getExplosion();

    CTExtensionList getExtLst();

    CTUnsignedInt getIdx();

    CTBoolean getInvertIfNegative();

    CTMarker getMarker();

    CTPictureOptions getPictureOptions();

    CTShapeProperties getSpPr();

    boolean isSetBubble3D();

    boolean isSetExplosion();

    boolean isSetExtLst();

    boolean isSetInvertIfNegative();

    boolean isSetMarker();

    boolean isSetPictureOptions();

    boolean isSetSpPr();

    void setBubble3D(CTBoolean cTBoolean);

    void setExplosion(CTUnsignedInt cTUnsignedInt);

    void setExtLst(CTExtensionList cTExtensionList);

    void setIdx(CTUnsignedInt cTUnsignedInt);

    void setInvertIfNegative(CTBoolean cTBoolean);

    void setMarker(CTMarker cTMarker);

    void setPictureOptions(CTPictureOptions cTPictureOptions);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void unsetBubble3D();

    void unsetExplosion();

    void unsetExtLst();

    void unsetInvertIfNegative();

    void unsetMarker();

    void unsetPictureOptions();

    void unsetSpPr();

    static {
        DocumentFactory<CTDPt> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdpt255etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
