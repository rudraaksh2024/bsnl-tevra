package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTSerAx extends XmlObject {
    public static final DocumentFactory<CTSerAx> Factory;
    public static final SchemaType type;

    CTUnsignedInt addNewAxId();

    CTAxPos addNewAxPos();

    CTUnsignedInt addNewCrossAx();

    CTCrosses addNewCrosses();

    CTDouble addNewCrossesAt();

    CTBoolean addNewDelete();

    CTExtensionList addNewExtLst();

    CTChartLines addNewMajorGridlines();

    CTTickMark addNewMajorTickMark();

    CTChartLines addNewMinorGridlines();

    CTTickMark addNewMinorTickMark();

    CTNumFmt addNewNumFmt();

    CTScaling addNewScaling();

    CTShapeProperties addNewSpPr();

    CTTickLblPos addNewTickLblPos();

    CTSkip addNewTickLblSkip();

    CTSkip addNewTickMarkSkip();

    CTTitle addNewTitle();

    CTTextBody addNewTxPr();

    CTUnsignedInt getAxId();

    CTAxPos getAxPos();

    CTUnsignedInt getCrossAx();

    CTCrosses getCrosses();

    CTDouble getCrossesAt();

    CTBoolean getDelete();

    CTExtensionList getExtLst();

    CTChartLines getMajorGridlines();

    CTTickMark getMajorTickMark();

    CTChartLines getMinorGridlines();

    CTTickMark getMinorTickMark();

    CTNumFmt getNumFmt();

    CTScaling getScaling();

    CTShapeProperties getSpPr();

    CTTickLblPos getTickLblPos();

    CTSkip getTickLblSkip();

    CTSkip getTickMarkSkip();

    CTTitle getTitle();

    CTTextBody getTxPr();

    boolean isSetCrosses();

    boolean isSetCrossesAt();

    boolean isSetDelete();

    boolean isSetExtLst();

    boolean isSetMajorGridlines();

    boolean isSetMajorTickMark();

    boolean isSetMinorGridlines();

    boolean isSetMinorTickMark();

    boolean isSetNumFmt();

    boolean isSetSpPr();

    boolean isSetTickLblPos();

    boolean isSetTickLblSkip();

    boolean isSetTickMarkSkip();

    boolean isSetTitle();

    boolean isSetTxPr();

    void setAxId(CTUnsignedInt cTUnsignedInt);

    void setAxPos(CTAxPos cTAxPos);

    void setCrossAx(CTUnsignedInt cTUnsignedInt);

    void setCrosses(CTCrosses cTCrosses);

    void setCrossesAt(CTDouble cTDouble);

    void setDelete(CTBoolean cTBoolean);

    void setExtLst(CTExtensionList cTExtensionList);

    void setMajorGridlines(CTChartLines cTChartLines);

    void setMajorTickMark(CTTickMark cTTickMark);

    void setMinorGridlines(CTChartLines cTChartLines);

    void setMinorTickMark(CTTickMark cTTickMark);

    void setNumFmt(CTNumFmt cTNumFmt);

    void setScaling(CTScaling cTScaling);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTickLblPos(CTTickLblPos cTTickLblPos);

    void setTickLblSkip(CTSkip cTSkip);

    void setTickMarkSkip(CTSkip cTSkip);

    void setTitle(CTTitle cTTitle);

    void setTxPr(CTTextBody cTTextBody);

    void unsetCrosses();

    void unsetCrossesAt();

    void unsetDelete();

    void unsetExtLst();

    void unsetMajorGridlines();

    void unsetMajorTickMark();

    void unsetMinorGridlines();

    void unsetMinorTickMark();

    void unsetNumFmt();

    void unsetSpPr();

    void unsetTickLblPos();

    void unsetTickLblSkip();

    void unsetTickMarkSkip();

    void unsetTitle();

    void unsetTxPr();

    static {
        DocumentFactory<CTSerAx> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctserax2c0ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
