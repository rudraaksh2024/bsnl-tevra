package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSurfaceChart extends XmlObject {
    public static final DocumentFactory<CTSurfaceChart> Factory;
    public static final SchemaType type;

    CTUnsignedInt addNewAxId();

    CTBandFmts addNewBandFmts();

    CTExtensionList addNewExtLst();

    CTSurfaceSer addNewSer();

    CTBoolean addNewWireframe();

    CTUnsignedInt getAxIdArray(int i);

    CTUnsignedInt[] getAxIdArray();

    List<CTUnsignedInt> getAxIdList();

    CTBandFmts getBandFmts();

    CTExtensionList getExtLst();

    CTSurfaceSer getSerArray(int i);

    CTSurfaceSer[] getSerArray();

    List<CTSurfaceSer> getSerList();

    CTBoolean getWireframe();

    CTUnsignedInt insertNewAxId(int i);

    CTSurfaceSer insertNewSer(int i);

    boolean isSetBandFmts();

    boolean isSetExtLst();

    boolean isSetWireframe();

    void removeAxId(int i);

    void removeSer(int i);

    void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt);

    void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr);

    void setBandFmts(CTBandFmts cTBandFmts);

    void setExtLst(CTExtensionList cTExtensionList);

    void setSerArray(int i, CTSurfaceSer cTSurfaceSer);

    void setSerArray(CTSurfaceSer[] cTSurfaceSerArr);

    void setWireframe(CTBoolean cTBoolean);

    int sizeOfAxIdArray();

    int sizeOfSerArray();

    void unsetBandFmts();

    void unsetExtLst();

    void unsetWireframe();

    static {
        DocumentFactory<CTSurfaceChart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsurfacecharta96btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
