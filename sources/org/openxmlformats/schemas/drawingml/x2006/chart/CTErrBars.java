package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public interface CTErrBars extends XmlObject {
    public static final DocumentFactory<CTErrBars> Factory;
    public static final SchemaType type;

    CTErrBarType addNewErrBarType();

    CTErrDir addNewErrDir();

    CTErrValType addNewErrValType();

    CTExtensionList addNewExtLst();

    CTNumDataSource addNewMinus();

    CTBoolean addNewNoEndCap();

    CTNumDataSource addNewPlus();

    CTShapeProperties addNewSpPr();

    CTDouble addNewVal();

    CTErrBarType getErrBarType();

    CTErrDir getErrDir();

    CTErrValType getErrValType();

    CTExtensionList getExtLst();

    CTNumDataSource getMinus();

    CTBoolean getNoEndCap();

    CTNumDataSource getPlus();

    CTShapeProperties getSpPr();

    CTDouble getVal();

    boolean isSetErrDir();

    boolean isSetExtLst();

    boolean isSetMinus();

    boolean isSetNoEndCap();

    boolean isSetPlus();

    boolean isSetSpPr();

    boolean isSetVal();

    void setErrBarType(CTErrBarType cTErrBarType);

    void setErrDir(CTErrDir cTErrDir);

    void setErrValType(CTErrValType cTErrValType);

    void setExtLst(CTExtensionList cTExtensionList);

    void setMinus(CTNumDataSource cTNumDataSource);

    void setNoEndCap(CTBoolean cTBoolean);

    void setPlus(CTNumDataSource cTNumDataSource);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setVal(CTDouble cTDouble);

    void unsetErrDir();

    void unsetExtLst();

    void unsetMinus();

    void unsetNoEndCap();

    void unsetPlus();

    void unsetSpPr();

    void unsetVal();

    static {
        DocumentFactory<CTErrBars> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cterrbarsa201type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
