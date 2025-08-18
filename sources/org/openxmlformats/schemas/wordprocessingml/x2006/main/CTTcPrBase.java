package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTcPrBase extends XmlObject {
    public static final DocumentFactory<CTTcPrBase> Factory;
    public static final SchemaType type;

    CTCnf addNewCnfStyle();

    CTDecimalNumber addNewGridSpan();

    CTHMerge addNewHMerge();

    CTHeaders addNewHeaders();

    CTOnOff addNewHideMark();

    CTOnOff addNewNoWrap();

    CTShd addNewShd();

    CTTcBorders addNewTcBorders();

    CTOnOff addNewTcFitText();

    CTTcMar addNewTcMar();

    CTTblWidth addNewTcW();

    CTTextDirection addNewTextDirection();

    CTVerticalJc addNewVAlign();

    CTVMerge addNewVMerge();

    CTCnf getCnfStyle();

    CTDecimalNumber getGridSpan();

    CTHMerge getHMerge();

    CTHeaders getHeaders();

    CTOnOff getHideMark();

    CTOnOff getNoWrap();

    CTShd getShd();

    CTTcBorders getTcBorders();

    CTOnOff getTcFitText();

    CTTcMar getTcMar();

    CTTblWidth getTcW();

    CTTextDirection getTextDirection();

    CTVerticalJc getVAlign();

    CTVMerge getVMerge();

    boolean isSetCnfStyle();

    boolean isSetGridSpan();

    boolean isSetHMerge();

    boolean isSetHeaders();

    boolean isSetHideMark();

    boolean isSetNoWrap();

    boolean isSetShd();

    boolean isSetTcBorders();

    boolean isSetTcFitText();

    boolean isSetTcMar();

    boolean isSetTcW();

    boolean isSetTextDirection();

    boolean isSetVAlign();

    boolean isSetVMerge();

    void setCnfStyle(CTCnf cTCnf);

    void setGridSpan(CTDecimalNumber cTDecimalNumber);

    void setHMerge(CTHMerge cTHMerge);

    void setHeaders(CTHeaders cTHeaders);

    void setHideMark(CTOnOff cTOnOff);

    void setNoWrap(CTOnOff cTOnOff);

    void setShd(CTShd cTShd);

    void setTcBorders(CTTcBorders cTTcBorders);

    void setTcFitText(CTOnOff cTOnOff);

    void setTcMar(CTTcMar cTTcMar);

    void setTcW(CTTblWidth cTTblWidth);

    void setTextDirection(CTTextDirection cTTextDirection);

    void setVAlign(CTVerticalJc cTVerticalJc);

    void setVMerge(CTVMerge cTVMerge);

    void unsetCnfStyle();

    void unsetGridSpan();

    void unsetHMerge();

    void unsetHeaders();

    void unsetHideMark();

    void unsetNoWrap();

    void unsetShd();

    void unsetTcBorders();

    void unsetTcFitText();

    void unsetTcMar();

    void unsetTcW();

    void unsetTextDirection();

    void unsetVAlign();

    void unsetVMerge();

    static {
        DocumentFactory<CTTcPrBase> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttcprbase93e6type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
