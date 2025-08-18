package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTCatAx extends XmlObject {
    public static final DocumentFactory<CTCatAx> Factory;
    public static final SchemaType type;

    CTBoolean addNewAuto();

    CTUnsignedInt addNewAxId();

    CTAxPos addNewAxPos();

    CTUnsignedInt addNewCrossAx();

    CTCrosses addNewCrosses();

    CTDouble addNewCrossesAt();

    CTBoolean addNewDelete();

    CTExtensionList addNewExtLst();

    CTLblAlgn addNewLblAlgn();

    CTLblOffset addNewLblOffset();

    CTChartLines addNewMajorGridlines();

    CTTickMark addNewMajorTickMark();

    CTChartLines addNewMinorGridlines();

    CTTickMark addNewMinorTickMark();

    CTBoolean addNewNoMultiLvlLbl();

    CTNumFmt addNewNumFmt();

    CTScaling addNewScaling();

    CTShapeProperties addNewSpPr();

    CTTickLblPos addNewTickLblPos();

    CTSkip addNewTickLblSkip();

    CTSkip addNewTickMarkSkip();

    CTTitle addNewTitle();

    CTTextBody addNewTxPr();

    CTBoolean getAuto();

    CTUnsignedInt getAxId();

    CTAxPos getAxPos();

    CTUnsignedInt getCrossAx();

    CTCrosses getCrosses();

    CTDouble getCrossesAt();

    CTBoolean getDelete();

    CTExtensionList getExtLst();

    CTLblAlgn getLblAlgn();

    CTLblOffset getLblOffset();

    CTChartLines getMajorGridlines();

    CTTickMark getMajorTickMark();

    CTChartLines getMinorGridlines();

    CTTickMark getMinorTickMark();

    CTBoolean getNoMultiLvlLbl();

    CTNumFmt getNumFmt();

    CTScaling getScaling();

    CTShapeProperties getSpPr();

    CTTickLblPos getTickLblPos();

    CTSkip getTickLblSkip();

    CTSkip getTickMarkSkip();

    CTTitle getTitle();

    CTTextBody getTxPr();

    boolean isSetAuto();

    boolean isSetCrosses();

    boolean isSetCrossesAt();

    boolean isSetDelete();

    boolean isSetExtLst();

    boolean isSetLblAlgn();

    boolean isSetLblOffset();

    boolean isSetMajorGridlines();

    boolean isSetMajorTickMark();

    boolean isSetMinorGridlines();

    boolean isSetMinorTickMark();

    boolean isSetNoMultiLvlLbl();

    boolean isSetNumFmt();

    boolean isSetSpPr();

    boolean isSetTickLblPos();

    boolean isSetTickLblSkip();

    boolean isSetTickMarkSkip();

    boolean isSetTitle();

    boolean isSetTxPr();

    void setAuto(CTBoolean cTBoolean);

    void setAxId(CTUnsignedInt cTUnsignedInt);

    void setAxPos(CTAxPos cTAxPos);

    void setCrossAx(CTUnsignedInt cTUnsignedInt);

    void setCrosses(CTCrosses cTCrosses);

    void setCrossesAt(CTDouble cTDouble);

    void setDelete(CTBoolean cTBoolean);

    void setExtLst(CTExtensionList cTExtensionList);

    void setLblAlgn(CTLblAlgn cTLblAlgn);

    void setLblOffset(CTLblOffset cTLblOffset);

    void setMajorGridlines(CTChartLines cTChartLines);

    void setMajorTickMark(CTTickMark cTTickMark);

    void setMinorGridlines(CTChartLines cTChartLines);

    void setMinorTickMark(CTTickMark cTTickMark);

    void setNoMultiLvlLbl(CTBoolean cTBoolean);

    void setNumFmt(CTNumFmt cTNumFmt);

    void setScaling(CTScaling cTScaling);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTickLblPos(CTTickLblPos cTTickLblPos);

    void setTickLblSkip(CTSkip cTSkip);

    void setTickMarkSkip(CTSkip cTSkip);

    void setTitle(CTTitle cTTitle);

    void setTxPr(CTTextBody cTTextBody);

    void unsetAuto();

    void unsetCrosses();

    void unsetCrossesAt();

    void unsetDelete();

    void unsetExtLst();

    void unsetLblAlgn();

    void unsetLblOffset();

    void unsetMajorGridlines();

    void unsetMajorTickMark();

    void unsetMinorGridlines();

    void unsetMinorTickMark();

    void unsetNoMultiLvlLbl();

    void unsetNumFmt();

    void unsetSpPr();

    void unsetTickLblPos();

    void unsetTickLblSkip();

    void unsetTickMarkSkip();

    void unsetTitle();

    void unsetTxPr();

    static {
        DocumentFactory<CTCatAx> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcatax7159type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
