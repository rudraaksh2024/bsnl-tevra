package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTBarChart extends XmlObject {
    public static final DocumentFactory<CTBarChart> Factory;
    public static final SchemaType type;

    CTUnsignedInt addNewAxId();

    CTBarDir addNewBarDir();

    CTDLbls addNewDLbls();

    CTExtensionList addNewExtLst();

    CTGapAmount addNewGapWidth();

    CTBarGrouping addNewGrouping();

    CTOverlap addNewOverlap();

    CTBarSer addNewSer();

    CTChartLines addNewSerLines();

    CTBoolean addNewVaryColors();

    CTUnsignedInt getAxIdArray(int i);

    CTUnsignedInt[] getAxIdArray();

    List<CTUnsignedInt> getAxIdList();

    CTBarDir getBarDir();

    CTDLbls getDLbls();

    CTExtensionList getExtLst();

    CTGapAmount getGapWidth();

    CTBarGrouping getGrouping();

    CTOverlap getOverlap();

    CTBarSer getSerArray(int i);

    CTBarSer[] getSerArray();

    CTChartLines getSerLinesArray(int i);

    CTChartLines[] getSerLinesArray();

    List<CTChartLines> getSerLinesList();

    List<CTBarSer> getSerList();

    CTBoolean getVaryColors();

    CTUnsignedInt insertNewAxId(int i);

    CTBarSer insertNewSer(int i);

    CTChartLines insertNewSerLines(int i);

    boolean isSetDLbls();

    boolean isSetExtLst();

    boolean isSetGapWidth();

    boolean isSetGrouping();

    boolean isSetOverlap();

    boolean isSetVaryColors();

    void removeAxId(int i);

    void removeSer(int i);

    void removeSerLines(int i);

    void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt);

    void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr);

    void setBarDir(CTBarDir cTBarDir);

    void setDLbls(CTDLbls cTDLbls);

    void setExtLst(CTExtensionList cTExtensionList);

    void setGapWidth(CTGapAmount cTGapAmount);

    void setGrouping(CTBarGrouping cTBarGrouping);

    void setOverlap(CTOverlap cTOverlap);

    void setSerArray(int i, CTBarSer cTBarSer);

    void setSerArray(CTBarSer[] cTBarSerArr);

    void setSerLinesArray(int i, CTChartLines cTChartLines);

    void setSerLinesArray(CTChartLines[] cTChartLinesArr);

    void setVaryColors(CTBoolean cTBoolean);

    int sizeOfAxIdArray();

    int sizeOfSerArray();

    int sizeOfSerLinesArray();

    void unsetDLbls();

    void unsetExtLst();

    void unsetGapWidth();

    void unsetGrouping();

    void unsetOverlap();

    void unsetVaryColors();

    static {
        DocumentFactory<CTBarChart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbarchart4151type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
