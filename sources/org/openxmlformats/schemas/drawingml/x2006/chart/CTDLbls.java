package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTDLbls extends XmlObject {
    public static final DocumentFactory<CTDLbls> Factory;
    public static final SchemaType type;

    CTDLbl addNewDLbl();

    CTDLblPos addNewDLblPos();

    CTBoolean addNewDelete();

    CTExtensionList addNewExtLst();

    CTChartLines addNewLeaderLines();

    CTNumFmt addNewNumFmt();

    CTBoolean addNewShowBubbleSize();

    CTBoolean addNewShowCatName();

    CTBoolean addNewShowLeaderLines();

    CTBoolean addNewShowLegendKey();

    CTBoolean addNewShowPercent();

    CTBoolean addNewShowSerName();

    CTBoolean addNewShowVal();

    CTShapeProperties addNewSpPr();

    CTTextBody addNewTxPr();

    CTDLbl getDLblArray(int i);

    CTDLbl[] getDLblArray();

    List<CTDLbl> getDLblList();

    CTDLblPos getDLblPos();

    CTBoolean getDelete();

    CTExtensionList getExtLst();

    CTChartLines getLeaderLines();

    CTNumFmt getNumFmt();

    String getSeparator();

    CTBoolean getShowBubbleSize();

    CTBoolean getShowCatName();

    CTBoolean getShowLeaderLines();

    CTBoolean getShowLegendKey();

    CTBoolean getShowPercent();

    CTBoolean getShowSerName();

    CTBoolean getShowVal();

    CTShapeProperties getSpPr();

    CTTextBody getTxPr();

    CTDLbl insertNewDLbl(int i);

    boolean isSetDLblPos();

    boolean isSetDelete();

    boolean isSetExtLst();

    boolean isSetLeaderLines();

    boolean isSetNumFmt();

    boolean isSetSeparator();

    boolean isSetShowBubbleSize();

    boolean isSetShowCatName();

    boolean isSetShowLeaderLines();

    boolean isSetShowLegendKey();

    boolean isSetShowPercent();

    boolean isSetShowSerName();

    boolean isSetShowVal();

    boolean isSetSpPr();

    boolean isSetTxPr();

    void removeDLbl(int i);

    void setDLblArray(int i, CTDLbl cTDLbl);

    void setDLblArray(CTDLbl[] cTDLblArr);

    void setDLblPos(CTDLblPos cTDLblPos);

    void setDelete(CTBoolean cTBoolean);

    void setExtLst(CTExtensionList cTExtensionList);

    void setLeaderLines(CTChartLines cTChartLines);

    void setNumFmt(CTNumFmt cTNumFmt);

    void setSeparator(String str);

    void setShowBubbleSize(CTBoolean cTBoolean);

    void setShowCatName(CTBoolean cTBoolean);

    void setShowLeaderLines(CTBoolean cTBoolean);

    void setShowLegendKey(CTBoolean cTBoolean);

    void setShowPercent(CTBoolean cTBoolean);

    void setShowSerName(CTBoolean cTBoolean);

    void setShowVal(CTBoolean cTBoolean);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTxPr(CTTextBody cTTextBody);

    int sizeOfDLblArray();

    void unsetDLblPos();

    void unsetDelete();

    void unsetExtLst();

    void unsetLeaderLines();

    void unsetNumFmt();

    void unsetSeparator();

    void unsetShowBubbleSize();

    void unsetShowCatName();

    void unsetShowLeaderLines();

    void unsetShowLegendKey();

    void unsetShowPercent();

    void unsetShowSerName();

    void unsetShowVal();

    void unsetSpPr();

    void unsetTxPr();

    XmlString xgetSeparator();

    void xsetSeparator(XmlString xmlString);

    static {
        DocumentFactory<CTDLbls> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdlblsb585type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
