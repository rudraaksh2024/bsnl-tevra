package org.openxmlformats.schemas.presentationml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;

public interface CTGroupShape extends XmlObject {
    public static final DocumentFactory<CTGroupShape> Factory;
    public static final SchemaType type;

    CTRel addNewContentPart();

    CTConnector addNewCxnSp();

    CTExtensionListModify addNewExtLst();

    CTGraphicalObjectFrame addNewGraphicFrame();

    CTGroupShape addNewGrpSp();

    CTGroupShapeProperties addNewGrpSpPr();

    CTGroupShapeNonVisual addNewNvGrpSpPr();

    CTPicture addNewPic();

    CTShape addNewSp();

    CTRel getContentPartArray(int i);

    CTRel[] getContentPartArray();

    List<CTRel> getContentPartList();

    CTConnector getCxnSpArray(int i);

    CTConnector[] getCxnSpArray();

    List<CTConnector> getCxnSpList();

    CTExtensionListModify getExtLst();

    CTGraphicalObjectFrame getGraphicFrameArray(int i);

    CTGraphicalObjectFrame[] getGraphicFrameArray();

    List<CTGraphicalObjectFrame> getGraphicFrameList();

    CTGroupShape getGrpSpArray(int i);

    CTGroupShape[] getGrpSpArray();

    List<CTGroupShape> getGrpSpList();

    CTGroupShapeProperties getGrpSpPr();

    CTGroupShapeNonVisual getNvGrpSpPr();

    CTPicture getPicArray(int i);

    CTPicture[] getPicArray();

    List<CTPicture> getPicList();

    CTShape getSpArray(int i);

    CTShape[] getSpArray();

    List<CTShape> getSpList();

    CTRel insertNewContentPart(int i);

    CTConnector insertNewCxnSp(int i);

    CTGraphicalObjectFrame insertNewGraphicFrame(int i);

    CTGroupShape insertNewGrpSp(int i);

    CTPicture insertNewPic(int i);

    CTShape insertNewSp(int i);

    boolean isSetExtLst();

    void removeContentPart(int i);

    void removeCxnSp(int i);

    void removeGraphicFrame(int i);

    void removeGrpSp(int i);

    void removePic(int i);

    void removeSp(int i);

    void setContentPartArray(int i, CTRel cTRel);

    void setContentPartArray(CTRel[] cTRelArr);

    void setCxnSpArray(int i, CTConnector cTConnector);

    void setCxnSpArray(CTConnector[] cTConnectorArr);

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setGraphicFrameArray(int i, CTGraphicalObjectFrame cTGraphicalObjectFrame);

    void setGraphicFrameArray(CTGraphicalObjectFrame[] cTGraphicalObjectFrameArr);

    void setGrpSpArray(int i, CTGroupShape cTGroupShape);

    void setGrpSpArray(CTGroupShape[] cTGroupShapeArr);

    void setGrpSpPr(CTGroupShapeProperties cTGroupShapeProperties);

    void setNvGrpSpPr(CTGroupShapeNonVisual cTGroupShapeNonVisual);

    void setPicArray(int i, CTPicture cTPicture);

    void setPicArray(CTPicture[] cTPictureArr);

    void setSpArray(int i, CTShape cTShape);

    void setSpArray(CTShape[] cTShapeArr);

    int sizeOfContentPartArray();

    int sizeOfCxnSpArray();

    int sizeOfGraphicFrameArray();

    int sizeOfGrpSpArray();

    int sizeOfPicArray();

    int sizeOfSpArray();

    void unsetExtLst();

    static {
        DocumentFactory<CTGroupShape> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgroupshape5b43type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
