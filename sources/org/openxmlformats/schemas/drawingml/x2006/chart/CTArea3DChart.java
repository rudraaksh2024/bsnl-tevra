package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTArea3DChart extends XmlObject {
    public static final DocumentFactory<CTArea3DChart> Factory;
    public static final SchemaType type;

    CTUnsignedInt addNewAxId();

    CTDLbls addNewDLbls();

    CTChartLines addNewDropLines();

    CTExtensionList addNewExtLst();

    CTGapAmount addNewGapDepth();

    CTGrouping addNewGrouping();

    CTAreaSer addNewSer();

    CTBoolean addNewVaryColors();

    CTUnsignedInt getAxIdArray(int i);

    CTUnsignedInt[] getAxIdArray();

    List<CTUnsignedInt> getAxIdList();

    CTDLbls getDLbls();

    CTChartLines getDropLines();

    CTExtensionList getExtLst();

    CTGapAmount getGapDepth();

    CTGrouping getGrouping();

    CTAreaSer getSerArray(int i);

    CTAreaSer[] getSerArray();

    List<CTAreaSer> getSerList();

    CTBoolean getVaryColors();

    CTUnsignedInt insertNewAxId(int i);

    CTAreaSer insertNewSer(int i);

    boolean isSetDLbls();

    boolean isSetDropLines();

    boolean isSetExtLst();

    boolean isSetGapDepth();

    boolean isSetGrouping();

    boolean isSetVaryColors();

    void removeAxId(int i);

    void removeSer(int i);

    void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt);

    void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr);

    void setDLbls(CTDLbls cTDLbls);

    void setDropLines(CTChartLines cTChartLines);

    void setExtLst(CTExtensionList cTExtensionList);

    void setGapDepth(CTGapAmount cTGapAmount);

    void setGrouping(CTGrouping cTGrouping);

    void setSerArray(int i, CTAreaSer cTAreaSer);

    void setSerArray(CTAreaSer[] cTAreaSerArr);

    void setVaryColors(CTBoolean cTBoolean);

    int sizeOfAxIdArray();

    int sizeOfSerArray();

    void unsetDLbls();

    void unsetDropLines();

    void unsetExtLst();

    void unsetGapDepth();

    void unsetGrouping();

    void unsetVaryColors();

    static {
        DocumentFactory<CTArea3DChart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctarea3dchart4c26type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
