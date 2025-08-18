package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTDateAx extends XmlObject {
    public static final DocumentFactory<CTDateAx> Factory;
    public static final SchemaType type;

    CTBoolean addNewAuto();

    CTUnsignedInt addNewAxId();

    CTAxPos addNewAxPos();

    CTTimeUnit addNewBaseTimeUnit();

    CTUnsignedInt addNewCrossAx();

    CTCrosses addNewCrosses();

    CTDouble addNewCrossesAt();

    CTBoolean addNewDelete();

    CTExtensionList addNewExtLst();

    CTLblOffset addNewLblOffset();

    CTChartLines addNewMajorGridlines();

    CTTickMark addNewMajorTickMark();

    CTTimeUnit addNewMajorTimeUnit();

    CTAxisUnit addNewMajorUnit();

    CTChartLines addNewMinorGridlines();

    CTTickMark addNewMinorTickMark();

    CTTimeUnit addNewMinorTimeUnit();

    CTAxisUnit addNewMinorUnit();

    CTNumFmt addNewNumFmt();

    CTScaling addNewScaling();

    CTShapeProperties addNewSpPr();

    CTTickLblPos addNewTickLblPos();

    CTTitle addNewTitle();

    CTTextBody addNewTxPr();

    CTBoolean getAuto();

    CTUnsignedInt getAxId();

    CTAxPos getAxPos();

    CTTimeUnit getBaseTimeUnit();

    CTUnsignedInt getCrossAx();

    CTCrosses getCrosses();

    CTDouble getCrossesAt();

    CTBoolean getDelete();

    CTExtensionList getExtLst();

    CTLblOffset getLblOffset();

    CTChartLines getMajorGridlines();

    CTTickMark getMajorTickMark();

    CTTimeUnit getMajorTimeUnit();

    CTAxisUnit getMajorUnit();

    CTChartLines getMinorGridlines();

    CTTickMark getMinorTickMark();

    CTTimeUnit getMinorTimeUnit();

    CTAxisUnit getMinorUnit();

    CTNumFmt getNumFmt();

    CTScaling getScaling();

    CTShapeProperties getSpPr();

    CTTickLblPos getTickLblPos();

    CTTitle getTitle();

    CTTextBody getTxPr();

    boolean isSetAuto();

    boolean isSetBaseTimeUnit();

    boolean isSetCrosses();

    boolean isSetCrossesAt();

    boolean isSetDelete();

    boolean isSetExtLst();

    boolean isSetLblOffset();

    boolean isSetMajorGridlines();

    boolean isSetMajorTickMark();

    boolean isSetMajorTimeUnit();

    boolean isSetMajorUnit();

    boolean isSetMinorGridlines();

    boolean isSetMinorTickMark();

    boolean isSetMinorTimeUnit();

    boolean isSetMinorUnit();

    boolean isSetNumFmt();

    boolean isSetSpPr();

    boolean isSetTickLblPos();

    boolean isSetTitle();

    boolean isSetTxPr();

    void setAuto(CTBoolean cTBoolean);

    void setAxId(CTUnsignedInt cTUnsignedInt);

    void setAxPos(CTAxPos cTAxPos);

    void setBaseTimeUnit(CTTimeUnit cTTimeUnit);

    void setCrossAx(CTUnsignedInt cTUnsignedInt);

    void setCrosses(CTCrosses cTCrosses);

    void setCrossesAt(CTDouble cTDouble);

    void setDelete(CTBoolean cTBoolean);

    void setExtLst(CTExtensionList cTExtensionList);

    void setLblOffset(CTLblOffset cTLblOffset);

    void setMajorGridlines(CTChartLines cTChartLines);

    void setMajorTickMark(CTTickMark cTTickMark);

    void setMajorTimeUnit(CTTimeUnit cTTimeUnit);

    void setMajorUnit(CTAxisUnit cTAxisUnit);

    void setMinorGridlines(CTChartLines cTChartLines);

    void setMinorTickMark(CTTickMark cTTickMark);

    void setMinorTimeUnit(CTTimeUnit cTTimeUnit);

    void setMinorUnit(CTAxisUnit cTAxisUnit);

    void setNumFmt(CTNumFmt cTNumFmt);

    void setScaling(CTScaling cTScaling);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTickLblPos(CTTickLblPos cTTickLblPos);

    void setTitle(CTTitle cTTitle);

    void setTxPr(CTTextBody cTTextBody);

    void unsetAuto();

    void unsetBaseTimeUnit();

    void unsetCrosses();

    void unsetCrossesAt();

    void unsetDelete();

    void unsetExtLst();

    void unsetLblOffset();

    void unsetMajorGridlines();

    void unsetMajorTickMark();

    void unsetMajorTimeUnit();

    void unsetMajorUnit();

    void unsetMinorGridlines();

    void unsetMinorTickMark();

    void unsetMinorTimeUnit();

    void unsetMinorUnit();

    void unsetNumFmt();

    void unsetSpPr();

    void unsetTickLblPos();

    void unsetTitle();

    void unsetTxPr();

    static {
        DocumentFactory<CTDateAx> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdateaxbdd7type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
