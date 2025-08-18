package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTScatterChart extends XmlObject {
    public static final DocumentFactory<CTScatterChart> Factory;
    public static final SchemaType type;

    CTUnsignedInt addNewAxId();

    CTDLbls addNewDLbls();

    CTExtensionList addNewExtLst();

    CTScatterStyle addNewScatterStyle();

    CTScatterSer addNewSer();

    CTBoolean addNewVaryColors();

    CTUnsignedInt getAxIdArray(int i);

    CTUnsignedInt[] getAxIdArray();

    List<CTUnsignedInt> getAxIdList();

    CTDLbls getDLbls();

    CTExtensionList getExtLst();

    CTScatterStyle getScatterStyle();

    CTScatterSer getSerArray(int i);

    CTScatterSer[] getSerArray();

    List<CTScatterSer> getSerList();

    CTBoolean getVaryColors();

    CTUnsignedInt insertNewAxId(int i);

    CTScatterSer insertNewSer(int i);

    boolean isSetDLbls();

    boolean isSetExtLst();

    boolean isSetVaryColors();

    void removeAxId(int i);

    void removeSer(int i);

    void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt);

    void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr);

    void setDLbls(CTDLbls cTDLbls);

    void setExtLst(CTExtensionList cTExtensionList);

    void setScatterStyle(CTScatterStyle cTScatterStyle);

    void setSerArray(int i, CTScatterSer cTScatterSer);

    void setSerArray(CTScatterSer[] cTScatterSerArr);

    void setVaryColors(CTBoolean cTBoolean);

    int sizeOfAxIdArray();

    int sizeOfSerArray();

    void unsetDLbls();

    void unsetExtLst();

    void unsetVaryColors();

    static {
        DocumentFactory<CTScatterChart> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctscatterchart2bfctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
