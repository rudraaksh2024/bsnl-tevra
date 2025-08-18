package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTBar3DChart extends XmlObject {
    public static final DocumentFactory<CTBar3DChart> Factory;
    public static final SchemaType type;

    CTUnsignedInt addNewAxId();

    CTBarDir addNewBarDir();

    CTDLbls addNewDLbls();

    CTExtensionList addNewExtLst();

    CTGapAmount addNewGapDepth();

    CTGapAmount addNewGapWidth();

    CTBarGrouping addNewGrouping();

    CTBarSer addNewSer();

    CTShape addNewShape();

    CTBoolean addNewVaryColors();

    CTUnsignedInt getAxIdArray(int i);

    CTUnsignedInt[] getAxIdArray();

    List<CTUnsignedInt> getAxIdList();

    CTBarDir getBarDir();

    CTDLbls getDLbls();

    CTExtensionList getExtLst();

    CTGapAmount getGapDepth();

    CTGapAmount getGapWidth();

    CTBarGrouping getGrouping();

    CTBarSer getSerArray(int i);

    CTBarSer[] getSerArray();

    List<CTBarSer> getSerList();

    CTShape getShape();

    CTBoolean getVaryColors();

    CTUnsignedInt insertNewAxId(int i);

    CTBarSer insertNewSer(int i);

    boolean isSetDLbls();

    boolean isSetExtLst();

    boolean isSetGapDepth();

    boolean isSetGapWidth();

    boolean isSetGrouping();

    boolean isSetShape();

    boolean isSetVaryColors();

    void removeAxId(int i);

    void removeSer(int i);

    void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt);

    void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr);

    void setBarDir(CTBarDir cTBarDir);

    void setDLbls(CTDLbls cTDLbls);

    void setExtLst(CTExtensionList cTExtensionList);

    void setGapDepth(CTGapAmount cTGapAmount);

    void setGapWidth(CTGapAmount cTGapAmount);

    void setGrouping(CTBarGrouping cTBarGrouping);

    void setSerArray(int i, CTBarSer cTBarSer);

    void setSerArray(CTBarSer[] cTBarSerArr);

    void setShape(CTShape cTShape);

    void setVaryColors(CTBoolean cTBoolean);

    int sizeOfAxIdArray();

    int sizeOfSerArray();

    void unsetDLbls();

    void unsetExtLst();

    void unsetGapDepth();

    void unsetGapWidth();

    void unsetGrouping();

    void unsetShape();

    void unsetVaryColors();

    static {
        DocumentFactory<CTBar3DChart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbar3dcharte4c2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
