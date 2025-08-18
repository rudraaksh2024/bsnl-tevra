package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;

public interface CTLineProperties extends XmlObject {
    public static final DocumentFactory<CTLineProperties> Factory;
    public static final SchemaType type;

    CTLineJoinBevel addNewBevel();

    CTDashStopList addNewCustDash();

    CTOfficeArtExtensionList addNewExtLst();

    CTGradientFillProperties addNewGradFill();

    CTLineEndProperties addNewHeadEnd();

    CTLineJoinMiterProperties addNewMiter();

    CTNoFillProperties addNewNoFill();

    CTPatternFillProperties addNewPattFill();

    CTPresetLineDashProperties addNewPrstDash();

    CTLineJoinRound addNewRound();

    CTSolidColorFillProperties addNewSolidFill();

    CTLineEndProperties addNewTailEnd();

    STPenAlignment.Enum getAlgn();

    CTLineJoinBevel getBevel();

    STLineCap.Enum getCap();

    STCompoundLine.Enum getCmpd();

    CTDashStopList getCustDash();

    CTOfficeArtExtensionList getExtLst();

    CTGradientFillProperties getGradFill();

    CTLineEndProperties getHeadEnd();

    CTLineJoinMiterProperties getMiter();

    CTNoFillProperties getNoFill();

    CTPatternFillProperties getPattFill();

    CTPresetLineDashProperties getPrstDash();

    CTLineJoinRound getRound();

    CTSolidColorFillProperties getSolidFill();

    CTLineEndProperties getTailEnd();

    int getW();

    boolean isSetAlgn();

    boolean isSetBevel();

    boolean isSetCap();

    boolean isSetCmpd();

    boolean isSetCustDash();

    boolean isSetExtLst();

    boolean isSetGradFill();

    boolean isSetHeadEnd();

    boolean isSetMiter();

    boolean isSetNoFill();

    boolean isSetPattFill();

    boolean isSetPrstDash();

    boolean isSetRound();

    boolean isSetSolidFill();

    boolean isSetTailEnd();

    boolean isSetW();

    void setAlgn(STPenAlignment.Enum enumR);

    void setBevel(CTLineJoinBevel cTLineJoinBevel);

    void setCap(STLineCap.Enum enumR);

    void setCmpd(STCompoundLine.Enum enumR);

    void setCustDash(CTDashStopList cTDashStopList);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setGradFill(CTGradientFillProperties cTGradientFillProperties);

    void setHeadEnd(CTLineEndProperties cTLineEndProperties);

    void setMiter(CTLineJoinMiterProperties cTLineJoinMiterProperties);

    void setNoFill(CTNoFillProperties cTNoFillProperties);

    void setPattFill(CTPatternFillProperties cTPatternFillProperties);

    void setPrstDash(CTPresetLineDashProperties cTPresetLineDashProperties);

    void setRound(CTLineJoinRound cTLineJoinRound);

    void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties);

    void setTailEnd(CTLineEndProperties cTLineEndProperties);

    void setW(int i);

    void unsetAlgn();

    void unsetBevel();

    void unsetCap();

    void unsetCmpd();

    void unsetCustDash();

    void unsetExtLst();

    void unsetGradFill();

    void unsetHeadEnd();

    void unsetMiter();

    void unsetNoFill();

    void unsetPattFill();

    void unsetPrstDash();

    void unsetRound();

    void unsetSolidFill();

    void unsetTailEnd();

    void unsetW();

    STPenAlignment xgetAlgn();

    STLineCap xgetCap();

    STCompoundLine xgetCmpd();

    STLineWidth xgetW();

    void xsetAlgn(STPenAlignment sTPenAlignment);

    void xsetCap(STLineCap sTLineCap);

    void xsetCmpd(STCompoundLine sTCompoundLine);

    void xsetW(STLineWidth sTLineWidth);

    static {
        DocumentFactory<CTLineProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlinepropertiesd5e2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
