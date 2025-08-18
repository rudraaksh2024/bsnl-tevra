package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTValAx extends XmlObject {
    public static final DocumentFactory<CTValAx> Factory;
    public static final SchemaType type;

    CTUnsignedInt addNewAxId();

    CTAxPos addNewAxPos();

    CTUnsignedInt addNewCrossAx();

    CTCrossBetween addNewCrossBetween();

    CTCrosses addNewCrosses();

    CTDouble addNewCrossesAt();

    CTBoolean addNewDelete();

    CTDispUnits addNewDispUnits();

    CTExtensionList addNewExtLst();

    CTChartLines addNewMajorGridlines();

    CTTickMark addNewMajorTickMark();

    CTAxisUnit addNewMajorUnit();

    CTChartLines addNewMinorGridlines();

    CTTickMark addNewMinorTickMark();

    CTAxisUnit addNewMinorUnit();

    CTNumFmt addNewNumFmt();

    CTScaling addNewScaling();

    CTShapeProperties addNewSpPr();

    CTTickLblPos addNewTickLblPos();

    CTTitle addNewTitle();

    CTTextBody addNewTxPr();

    CTUnsignedInt getAxId();

    CTAxPos getAxPos();

    CTUnsignedInt getCrossAx();

    CTCrossBetween getCrossBetween();

    CTCrosses getCrosses();

    CTDouble getCrossesAt();

    CTBoolean getDelete();

    CTDispUnits getDispUnits();

    CTExtensionList getExtLst();

    CTChartLines getMajorGridlines();

    CTTickMark getMajorTickMark();

    CTAxisUnit getMajorUnit();

    CTChartLines getMinorGridlines();

    CTTickMark getMinorTickMark();

    CTAxisUnit getMinorUnit();

    CTNumFmt getNumFmt();

    CTScaling getScaling();

    CTShapeProperties getSpPr();

    CTTickLblPos getTickLblPos();

    CTTitle getTitle();

    CTTextBody getTxPr();

    boolean isSetCrossBetween();

    boolean isSetCrosses();

    boolean isSetCrossesAt();

    boolean isSetDelete();

    boolean isSetDispUnits();

    boolean isSetExtLst();

    boolean isSetMajorGridlines();

    boolean isSetMajorTickMark();

    boolean isSetMajorUnit();

    boolean isSetMinorGridlines();

    boolean isSetMinorTickMark();

    boolean isSetMinorUnit();

    boolean isSetNumFmt();

    boolean isSetSpPr();

    boolean isSetTickLblPos();

    boolean isSetTitle();

    boolean isSetTxPr();

    void setAxId(CTUnsignedInt cTUnsignedInt);

    void setAxPos(CTAxPos cTAxPos);

    void setCrossAx(CTUnsignedInt cTUnsignedInt);

    void setCrossBetween(CTCrossBetween cTCrossBetween);

    void setCrosses(CTCrosses cTCrosses);

    void setCrossesAt(CTDouble cTDouble);

    void setDelete(CTBoolean cTBoolean);

    void setDispUnits(CTDispUnits cTDispUnits);

    void setExtLst(CTExtensionList cTExtensionList);

    void setMajorGridlines(CTChartLines cTChartLines);

    void setMajorTickMark(CTTickMark cTTickMark);

    void setMajorUnit(CTAxisUnit cTAxisUnit);

    void setMinorGridlines(CTChartLines cTChartLines);

    void setMinorTickMark(CTTickMark cTTickMark);

    void setMinorUnit(CTAxisUnit cTAxisUnit);

    void setNumFmt(CTNumFmt cTNumFmt);

    void setScaling(CTScaling cTScaling);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTickLblPos(CTTickLblPos cTTickLblPos);

    void setTitle(CTTitle cTTitle);

    void setTxPr(CTTextBody cTTextBody);

    void unsetCrossBetween();

    void unsetCrosses();

    void unsetCrossesAt();

    void unsetDelete();

    void unsetDispUnits();

    void unsetExtLst();

    void unsetMajorGridlines();

    void unsetMajorTickMark();

    void unsetMajorUnit();

    void unsetMinorGridlines();

    void unsetMinorTickMark();

    void unsetMinorUnit();

    void unsetNumFmt();

    void unsetSpPr();

    void unsetTickLblPos();

    void unsetTitle();

    void unsetTxPr();

    static {
        DocumentFactory<CTValAx> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctvalaxd06etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
