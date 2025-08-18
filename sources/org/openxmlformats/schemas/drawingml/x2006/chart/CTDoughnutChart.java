package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDoughnutChart extends XmlObject {
    public static final DocumentFactory<CTDoughnutChart> Factory;
    public static final SchemaType type;

    CTDLbls addNewDLbls();

    CTExtensionList addNewExtLst();

    CTFirstSliceAng addNewFirstSliceAng();

    CTHoleSize addNewHoleSize();

    CTPieSer addNewSer();

    CTBoolean addNewVaryColors();

    CTDLbls getDLbls();

    CTExtensionList getExtLst();

    CTFirstSliceAng getFirstSliceAng();

    CTHoleSize getHoleSize();

    CTPieSer getSerArray(int i);

    CTPieSer[] getSerArray();

    List<CTPieSer> getSerList();

    CTBoolean getVaryColors();

    CTPieSer insertNewSer(int i);

    boolean isSetDLbls();

    boolean isSetExtLst();

    boolean isSetFirstSliceAng();

    boolean isSetHoleSize();

    boolean isSetVaryColors();

    void removeSer(int i);

    void setDLbls(CTDLbls cTDLbls);

    void setExtLst(CTExtensionList cTExtensionList);

    void setFirstSliceAng(CTFirstSliceAng cTFirstSliceAng);

    void setHoleSize(CTHoleSize cTHoleSize);

    void setSerArray(int i, CTPieSer cTPieSer);

    void setSerArray(CTPieSer[] cTPieSerArr);

    void setVaryColors(CTBoolean cTBoolean);

    int sizeOfSerArray();

    void unsetDLbls();

    void unsetExtLst();

    void unsetFirstSliceAng();

    void unsetHoleSize();

    void unsetVaryColors();

    static {
        DocumentFactory<CTDoughnutChart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdoughnutchartc12atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
