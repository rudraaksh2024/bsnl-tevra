package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public interface CTBarSer extends XmlObject {
    public static final DocumentFactory<CTBarSer> Factory;
    public static final SchemaType type;

    CTAxDataSource addNewCat();

    CTDLbls addNewDLbls();

    CTDPt addNewDPt();

    CTErrBars addNewErrBars();

    CTExtensionList addNewExtLst();

    CTUnsignedInt addNewIdx();

    CTBoolean addNewInvertIfNegative();

    CTUnsignedInt addNewOrder();

    CTPictureOptions addNewPictureOptions();

    CTShape addNewShape();

    CTShapeProperties addNewSpPr();

    CTTrendline addNewTrendline();

    CTSerTx addNewTx();

    CTNumDataSource addNewVal();

    CTAxDataSource getCat();

    CTDLbls getDLbls();

    CTDPt getDPtArray(int i);

    CTDPt[] getDPtArray();

    List<CTDPt> getDPtList();

    CTErrBars getErrBars();

    CTExtensionList getExtLst();

    CTUnsignedInt getIdx();

    CTBoolean getInvertIfNegative();

    CTUnsignedInt getOrder();

    CTPictureOptions getPictureOptions();

    CTShape getShape();

    CTShapeProperties getSpPr();

    CTTrendline getTrendlineArray(int i);

    CTTrendline[] getTrendlineArray();

    List<CTTrendline> getTrendlineList();

    CTSerTx getTx();

    CTNumDataSource getVal();

    CTDPt insertNewDPt(int i);

    CTTrendline insertNewTrendline(int i);

    boolean isSetCat();

    boolean isSetDLbls();

    boolean isSetErrBars();

    boolean isSetExtLst();

    boolean isSetInvertIfNegative();

    boolean isSetPictureOptions();

    boolean isSetShape();

    boolean isSetSpPr();

    boolean isSetTx();

    boolean isSetVal();

    void removeDPt(int i);

    void removeTrendline(int i);

    void setCat(CTAxDataSource cTAxDataSource);

    void setDLbls(CTDLbls cTDLbls);

    void setDPtArray(int i, CTDPt cTDPt);

    void setDPtArray(CTDPt[] cTDPtArr);

    void setErrBars(CTErrBars cTErrBars);

    void setExtLst(CTExtensionList cTExtensionList);

    void setIdx(CTUnsignedInt cTUnsignedInt);

    void setInvertIfNegative(CTBoolean cTBoolean);

    void setOrder(CTUnsignedInt cTUnsignedInt);

    void setPictureOptions(CTPictureOptions cTPictureOptions);

    void setShape(CTShape cTShape);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTrendlineArray(int i, CTTrendline cTTrendline);

    void setTrendlineArray(CTTrendline[] cTTrendlineArr);

    void setTx(CTSerTx cTSerTx);

    void setVal(CTNumDataSource cTNumDataSource);

    int sizeOfDPtArray();

    int sizeOfTrendlineArray();

    void unsetCat();

    void unsetDLbls();

    void unsetErrBars();

    void unsetExtLst();

    void unsetInvertIfNegative();

    void unsetPictureOptions();

    void unsetShape();

    void unsetSpPr();

    void unsetTx();

    void unsetVal();

    static {
        DocumentFactory<CTBarSer> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbarser960ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
