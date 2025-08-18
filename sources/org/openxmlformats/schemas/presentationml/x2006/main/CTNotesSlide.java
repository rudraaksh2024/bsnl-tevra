package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;

public interface CTNotesSlide extends XmlObject {
    public static final DocumentFactory<CTNotesSlide> Factory;
    public static final SchemaType type;

    CTCommonSlideData addNewCSld();

    CTColorMappingOverride addNewClrMapOvr();

    CTExtensionListModify addNewExtLst();

    CTCommonSlideData getCSld();

    CTColorMappingOverride getClrMapOvr();

    CTExtensionListModify getExtLst();

    boolean getShowMasterPhAnim();

    boolean getShowMasterSp();

    boolean isSetClrMapOvr();

    boolean isSetExtLst();

    boolean isSetShowMasterPhAnim();

    boolean isSetShowMasterSp();

    void setCSld(CTCommonSlideData cTCommonSlideData);

    void setClrMapOvr(CTColorMappingOverride cTColorMappingOverride);

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setShowMasterPhAnim(boolean z);

    void setShowMasterSp(boolean z);

    void unsetClrMapOvr();

    void unsetExtLst();

    void unsetShowMasterPhAnim();

    void unsetShowMasterSp();

    XmlBoolean xgetShowMasterPhAnim();

    XmlBoolean xgetShowMasterSp();

    void xsetShowMasterPhAnim(XmlBoolean xmlBoolean);

    void xsetShowMasterSp(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTNotesSlide> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnotesslideab75type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
