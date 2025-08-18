package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTAreaChart extends XmlObject {
    public static final DocumentFactory<CTAreaChart> Factory;
    public static final SchemaType type;

    CTUnsignedInt addNewAxId();

    CTDLbls addNewDLbls();

    CTChartLines addNewDropLines();

    CTExtensionList addNewExtLst();

    CTGrouping addNewGrouping();

    CTAreaSer addNewSer();

    CTBoolean addNewVaryColors();

    CTUnsignedInt getAxIdArray(int i);

    CTUnsignedInt[] getAxIdArray();

    List<CTUnsignedInt> getAxIdList();

    CTDLbls getDLbls();

    CTChartLines getDropLines();

    CTExtensionList getExtLst();

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

    boolean isSetGrouping();

    boolean isSetVaryColors();

    void removeAxId(int i);

    void removeSer(int i);

    void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt);

    void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr);

    void setDLbls(CTDLbls cTDLbls);

    void setDropLines(CTChartLines cTChartLines);

    void setExtLst(CTExtensionList cTExtensionList);

    void setGrouping(CTGrouping cTGrouping);

    void setSerArray(int i, CTAreaSer cTAreaSer);

    void setSerArray(CTAreaSer[] cTAreaSerArr);

    void setVaryColors(CTBoolean cTBoolean);

    int sizeOfAxIdArray();

    int sizeOfSerArray();

    void unsetDLbls();

    void unsetDropLines();

    void unsetExtLst();

    void unsetGrouping();

    void unsetVaryColors();

    static {
        DocumentFactory<CTAreaChart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctareachart31b5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
