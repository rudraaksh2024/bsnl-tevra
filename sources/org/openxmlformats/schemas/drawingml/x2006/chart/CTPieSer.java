package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public interface CTPieSer extends XmlObject {
    public static final DocumentFactory<CTPieSer> Factory;
    public static final SchemaType type;

    CTAxDataSource addNewCat();

    CTDLbls addNewDLbls();

    CTDPt addNewDPt();

    CTUnsignedInt addNewExplosion();

    CTExtensionList addNewExtLst();

    CTUnsignedInt addNewIdx();

    CTUnsignedInt addNewOrder();

    CTShapeProperties addNewSpPr();

    CTSerTx addNewTx();

    CTNumDataSource addNewVal();

    CTAxDataSource getCat();

    CTDLbls getDLbls();

    CTDPt getDPtArray(int i);

    CTDPt[] getDPtArray();

    List<CTDPt> getDPtList();

    CTUnsignedInt getExplosion();

    CTExtensionList getExtLst();

    CTUnsignedInt getIdx();

    CTUnsignedInt getOrder();

    CTShapeProperties getSpPr();

    CTSerTx getTx();

    CTNumDataSource getVal();

    CTDPt insertNewDPt(int i);

    boolean isSetCat();

    boolean isSetDLbls();

    boolean isSetExplosion();

    boolean isSetExtLst();

    boolean isSetSpPr();

    boolean isSetTx();

    boolean isSetVal();

    void removeDPt(int i);

    void setCat(CTAxDataSource cTAxDataSource);

    void setDLbls(CTDLbls cTDLbls);

    void setDPtArray(int i, CTDPt cTDPt);

    void setDPtArray(CTDPt[] cTDPtArr);

    void setExplosion(CTUnsignedInt cTUnsignedInt);

    void setExtLst(CTExtensionList cTExtensionList);

    void setIdx(CTUnsignedInt cTUnsignedInt);

    void setOrder(CTUnsignedInt cTUnsignedInt);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTx(CTSerTx cTSerTx);

    void setVal(CTNumDataSource cTNumDataSource);

    int sizeOfDPtArray();

    void unsetCat();

    void unsetDLbls();

    void unsetExplosion();

    void unsetExtLst();

    void unsetSpPr();

    void unsetTx();

    void unsetVal();

    static {
        DocumentFactory<CTPieSer> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpieser5248type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
