package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STEditAs;

public interface CTTwoCellAnchor extends XmlObject {
    public static final DocumentFactory<CTTwoCellAnchor> Factory;
    public static final SchemaType type;

    CTAnchorClientData addNewClientData();

    CTRel addNewContentPart();

    CTConnector addNewCxnSp();

    CTMarker addNewFrom();

    CTGraphicalObjectFrame addNewGraphicFrame();

    CTGroupShape addNewGrpSp();

    CTPicture addNewPic();

    CTShape addNewSp();

    CTMarker addNewTo();

    CTAnchorClientData getClientData();

    CTRel getContentPart();

    CTConnector getCxnSp();

    STEditAs.Enum getEditAs();

    CTMarker getFrom();

    CTGraphicalObjectFrame getGraphicFrame();

    CTGroupShape getGrpSp();

    CTPicture getPic();

    CTShape getSp();

    CTMarker getTo();

    boolean isSetContentPart();

    boolean isSetCxnSp();

    boolean isSetEditAs();

    boolean isSetGraphicFrame();

    boolean isSetGrpSp();

    boolean isSetPic();

    boolean isSetSp();

    void setClientData(CTAnchorClientData cTAnchorClientData);

    void setContentPart(CTRel cTRel);

    void setCxnSp(CTConnector cTConnector);

    void setEditAs(STEditAs.Enum enumR);

    void setFrom(CTMarker cTMarker);

    void setGraphicFrame(CTGraphicalObjectFrame cTGraphicalObjectFrame);

    void setGrpSp(CTGroupShape cTGroupShape);

    void setPic(CTPicture cTPicture);

    void setSp(CTShape cTShape);

    void setTo(CTMarker cTMarker);

    void unsetContentPart();

    void unsetCxnSp();

    void unsetEditAs();

    void unsetGraphicFrame();

    void unsetGrpSp();

    void unsetPic();

    void unsetSp();

    STEditAs xgetEditAs();

    void xsetEditAs(STEditAs sTEditAs);

    static {
        DocumentFactory<CTTwoCellAnchor> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttwocellanchor1e8dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
