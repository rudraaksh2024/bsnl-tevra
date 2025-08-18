package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;

public interface CTSlideMaster extends XmlObject {
    public static final DocumentFactory<CTSlideMaster> Factory;
    public static final SchemaType type;

    CTCommonSlideData addNewCSld();

    CTColorMapping addNewClrMap();

    CTExtensionListModify addNewExtLst();

    CTHeaderFooter addNewHf();

    CTSlideLayoutIdList addNewSldLayoutIdLst();

    CTSlideTiming addNewTiming();

    CTSlideTransition addNewTransition();

    CTSlideMasterTextStyles addNewTxStyles();

    CTCommonSlideData getCSld();

    CTColorMapping getClrMap();

    CTExtensionListModify getExtLst();

    CTHeaderFooter getHf();

    boolean getPreserve();

    CTSlideLayoutIdList getSldLayoutIdLst();

    CTSlideTiming getTiming();

    CTSlideTransition getTransition();

    CTSlideMasterTextStyles getTxStyles();

    boolean isSetExtLst();

    boolean isSetHf();

    boolean isSetPreserve();

    boolean isSetSldLayoutIdLst();

    boolean isSetTiming();

    boolean isSetTransition();

    boolean isSetTxStyles();

    void setCSld(CTCommonSlideData cTCommonSlideData);

    void setClrMap(CTColorMapping cTColorMapping);

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setHf(CTHeaderFooter cTHeaderFooter);

    void setPreserve(boolean z);

    void setSldLayoutIdLst(CTSlideLayoutIdList cTSlideLayoutIdList);

    void setTiming(CTSlideTiming cTSlideTiming);

    void setTransition(CTSlideTransition cTSlideTransition);

    void setTxStyles(CTSlideMasterTextStyles cTSlideMasterTextStyles);

    void unsetExtLst();

    void unsetHf();

    void unsetPreserve();

    void unsetSldLayoutIdLst();

    void unsetTiming();

    void unsetTransition();

    void unsetTxStyles();

    XmlBoolean xgetPreserve();

    void xsetPreserve(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTSlideMaster> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctslidemasterd8fctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
