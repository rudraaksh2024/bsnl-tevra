package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;

public interface CTOneCellAnchor extends XmlObject {
    public static final DocumentFactory<CTOneCellAnchor> Factory;
    public static final SchemaType type;

    CTAnchorClientData addNewClientData();

    CTRel addNewContentPart();

    CTConnector addNewCxnSp();

    CTPositiveSize2D addNewExt();

    CTMarker addNewFrom();

    CTGraphicalObjectFrame addNewGraphicFrame();

    CTGroupShape addNewGrpSp();

    CTPicture addNewPic();

    CTShape addNewSp();

    CTAnchorClientData getClientData();

    CTRel getContentPart();

    CTConnector getCxnSp();

    CTPositiveSize2D getExt();

    CTMarker getFrom();

    CTGraphicalObjectFrame getGraphicFrame();

    CTGroupShape getGrpSp();

    CTPicture getPic();

    CTShape getSp();

    boolean isSetContentPart();

    boolean isSetCxnSp();

    boolean isSetGraphicFrame();

    boolean isSetGrpSp();

    boolean isSetPic();

    boolean isSetSp();

    void setClientData(CTAnchorClientData cTAnchorClientData);

    void setContentPart(CTRel cTRel);

    void setCxnSp(CTConnector cTConnector);

    void setExt(CTPositiveSize2D cTPositiveSize2D);

    void setFrom(CTMarker cTMarker);

    void setGraphicFrame(CTGraphicalObjectFrame cTGraphicalObjectFrame);

    void setGrpSp(CTGroupShape cTGroupShape);

    void setPic(CTPicture cTPicture);

    void setSp(CTShape cTShape);

    void unsetContentPart();

    void unsetCxnSp();

    void unsetGraphicFrame();

    void unsetGrpSp();

    void unsetPic();

    void unsetSp();

    static {
        DocumentFactory<CTOneCellAnchor> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctonecellanchor0527type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
