package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTRadarChart extends XmlObject {
    public static final DocumentFactory<CTRadarChart> Factory;
    public static final SchemaType type;

    CTUnsignedInt addNewAxId();

    CTDLbls addNewDLbls();

    CTExtensionList addNewExtLst();

    CTRadarStyle addNewRadarStyle();

    CTRadarSer addNewSer();

    CTBoolean addNewVaryColors();

    CTUnsignedInt getAxIdArray(int i);

    CTUnsignedInt[] getAxIdArray();

    List<CTUnsignedInt> getAxIdList();

    CTDLbls getDLbls();

    CTExtensionList getExtLst();

    CTRadarStyle getRadarStyle();

    CTRadarSer getSerArray(int i);

    CTRadarSer[] getSerArray();

    List<CTRadarSer> getSerList();

    CTBoolean getVaryColors();

    CTUnsignedInt insertNewAxId(int i);

    CTRadarSer insertNewSer(int i);

    boolean isSetDLbls();

    boolean isSetExtLst();

    boolean isSetVaryColors();

    void removeAxId(int i);

    void removeSer(int i);

    void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt);

    void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr);

    void setDLbls(CTDLbls cTDLbls);

    void setExtLst(CTExtensionList cTExtensionList);

    void setRadarStyle(CTRadarStyle cTRadarStyle);

    void setSerArray(int i, CTRadarSer cTRadarSer);

    void setSerArray(CTRadarSer[] cTRadarSerArr);

    void setVaryColors(CTBoolean cTBoolean);

    int sizeOfAxIdArray();

    int sizeOfSerArray();

    void unsetDLbls();

    void unsetExtLst();

    void unsetVaryColors();

    static {
        DocumentFactory<CTRadarChart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctradarchart0f04type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
