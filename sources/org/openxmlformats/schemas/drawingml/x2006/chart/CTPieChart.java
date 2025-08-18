package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPieChart extends XmlObject {
    public static final DocumentFactory<CTPieChart> Factory;
    public static final SchemaType type;

    CTDLbls addNewDLbls();

    CTExtensionList addNewExtLst();

    CTFirstSliceAng addNewFirstSliceAng();

    CTPieSer addNewSer();

    CTBoolean addNewVaryColors();

    CTDLbls getDLbls();

    CTExtensionList getExtLst();

    CTFirstSliceAng getFirstSliceAng();

    CTPieSer getSerArray(int i);

    CTPieSer[] getSerArray();

    List<CTPieSer> getSerList();

    CTBoolean getVaryColors();

    CTPieSer insertNewSer(int i);

    boolean isSetDLbls();

    boolean isSetExtLst();

    boolean isSetFirstSliceAng();

    boolean isSetVaryColors();

    void removeSer(int i);

    void setDLbls(CTDLbls cTDLbls);

    void setExtLst(CTExtensionList cTExtensionList);

    void setFirstSliceAng(CTFirstSliceAng cTFirstSliceAng);

    void setSerArray(int i, CTPieSer cTPieSer);

    void setSerArray(CTPieSer[] cTPieSerArr);

    void setVaryColors(CTBoolean cTBoolean);

    int sizeOfSerArray();

    void unsetDLbls();

    void unsetExtLst();

    void unsetFirstSliceAng();

    void unsetVaryColors();

    static {
        DocumentFactory<CTPieChart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpiechartd34atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
