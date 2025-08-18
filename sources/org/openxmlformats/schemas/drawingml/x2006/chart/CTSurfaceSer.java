package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public interface CTSurfaceSer extends XmlObject {
    public static final DocumentFactory<CTSurfaceSer> Factory;
    public static final SchemaType type;

    CTAxDataSource addNewCat();

    CTExtensionList addNewExtLst();

    CTUnsignedInt addNewIdx();

    CTUnsignedInt addNewOrder();

    CTShapeProperties addNewSpPr();

    CTSerTx addNewTx();

    CTNumDataSource addNewVal();

    CTAxDataSource getCat();

    CTExtensionList getExtLst();

    CTUnsignedInt getIdx();

    CTUnsignedInt getOrder();

    CTShapeProperties getSpPr();

    CTSerTx getTx();

    CTNumDataSource getVal();

    boolean isSetCat();

    boolean isSetExtLst();

    boolean isSetSpPr();

    boolean isSetTx();

    boolean isSetVal();

    void setCat(CTAxDataSource cTAxDataSource);

    void setExtLst(CTExtensionList cTExtensionList);

    void setIdx(CTUnsignedInt cTUnsignedInt);

    void setOrder(CTUnsignedInt cTUnsignedInt);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTx(CTSerTx cTSerTx);

    void setVal(CTNumDataSource cTNumDataSource);

    void unsetCat();

    void unsetExtLst();

    void unsetSpPr();

    void unsetTx();

    void unsetVal();

    static {
        DocumentFactory<CTSurfaceSer> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsurfaceser3ca9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
