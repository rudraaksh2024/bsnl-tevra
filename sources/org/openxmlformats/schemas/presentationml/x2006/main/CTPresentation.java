package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum;

public interface CTPresentation extends XmlObject {
    public static final DocumentFactory<CTPresentation> Factory;
    public static final SchemaType type;

    CTCustomerDataList addNewCustDataLst();

    CTCustomShowList addNewCustShowLst();

    CTTextListStyle addNewDefaultTextStyle();

    CTEmbeddedFontList addNewEmbeddedFontLst();

    CTExtensionList addNewExtLst();

    CTHandoutMasterIdList addNewHandoutMasterIdLst();

    CTKinsoku addNewKinsoku();

    CTModifyVerifier addNewModifyVerifier();

    CTNotesMasterIdList addNewNotesMasterIdLst();

    CTPositiveSize2D addNewNotesSz();

    CTPhotoAlbum addNewPhotoAlbum();

    CTSlideIdList addNewSldIdLst();

    CTSlideMasterIdList addNewSldMasterIdLst();

    CTSlideSize addNewSldSz();

    CTSmartTags addNewSmartTags();

    boolean getAutoCompressPictures();

    long getBookmarkIdSeed();

    boolean getCompatMode();

    STConformanceClass$Enum getConformance();

    CTCustomerDataList getCustDataLst();

    CTCustomShowList getCustShowLst();

    CTTextListStyle getDefaultTextStyle();

    boolean getEmbedTrueTypeFonts();

    CTEmbeddedFontList getEmbeddedFontLst();

    CTExtensionList getExtLst();

    int getFirstSlideNum();

    CTHandoutMasterIdList getHandoutMasterIdLst();

    CTKinsoku getKinsoku();

    CTModifyVerifier getModifyVerifier();

    CTNotesMasterIdList getNotesMasterIdLst();

    CTPositiveSize2D getNotesSz();

    CTPhotoAlbum getPhotoAlbum();

    boolean getRemovePersonalInfoOnSave();

    boolean getRtl();

    boolean getSaveSubsetFonts();

    Object getServerZoom();

    boolean getShowSpecialPlsOnTitleSld();

    CTSlideIdList getSldIdLst();

    CTSlideMasterIdList getSldMasterIdLst();

    CTSlideSize getSldSz();

    CTSmartTags getSmartTags();

    boolean getStrictFirstAndLastChars();

    boolean isSetAutoCompressPictures();

    boolean isSetBookmarkIdSeed();

    boolean isSetCompatMode();

    boolean isSetConformance();

    boolean isSetCustDataLst();

    boolean isSetCustShowLst();

    boolean isSetDefaultTextStyle();

    boolean isSetEmbedTrueTypeFonts();

    boolean isSetEmbeddedFontLst();

    boolean isSetExtLst();

    boolean isSetFirstSlideNum();

    boolean isSetHandoutMasterIdLst();

    boolean isSetKinsoku();

    boolean isSetModifyVerifier();

    boolean isSetNotesMasterIdLst();

    boolean isSetPhotoAlbum();

    boolean isSetRemovePersonalInfoOnSave();

    boolean isSetRtl();

    boolean isSetSaveSubsetFonts();

    boolean isSetServerZoom();

    boolean isSetShowSpecialPlsOnTitleSld();

    boolean isSetSldIdLst();

    boolean isSetSldMasterIdLst();

    boolean isSetSldSz();

    boolean isSetSmartTags();

    boolean isSetStrictFirstAndLastChars();

    void setAutoCompressPictures(boolean z);

    void setBookmarkIdSeed(long j);

    void setCompatMode(boolean z);

    void setConformance(STConformanceClass$Enum sTConformanceClass$Enum);

    void setCustDataLst(CTCustomerDataList cTCustomerDataList);

    void setCustShowLst(CTCustomShowList cTCustomShowList);

    void setDefaultTextStyle(CTTextListStyle cTTextListStyle);

    void setEmbedTrueTypeFonts(boolean z);

    void setEmbeddedFontLst(CTEmbeddedFontList cTEmbeddedFontList);

    void setExtLst(CTExtensionList cTExtensionList);

    void setFirstSlideNum(int i);

    void setHandoutMasterIdLst(CTHandoutMasterIdList cTHandoutMasterIdList);

    void setKinsoku(CTKinsoku cTKinsoku);

    void setModifyVerifier(CTModifyVerifier cTModifyVerifier);

    void setNotesMasterIdLst(CTNotesMasterIdList cTNotesMasterIdList);

    void setNotesSz(CTPositiveSize2D cTPositiveSize2D);

    void setPhotoAlbum(CTPhotoAlbum cTPhotoAlbum);

    void setRemovePersonalInfoOnSave(boolean z);

    void setRtl(boolean z);

    void setSaveSubsetFonts(boolean z);

    void setServerZoom(Object obj);

    void setShowSpecialPlsOnTitleSld(boolean z);

    void setSldIdLst(CTSlideIdList cTSlideIdList);

    void setSldMasterIdLst(CTSlideMasterIdList cTSlideMasterIdList);

    void setSldSz(CTSlideSize cTSlideSize);

    void setSmartTags(CTSmartTags cTSmartTags);

    void setStrictFirstAndLastChars(boolean z);

    void unsetAutoCompressPictures();

    void unsetBookmarkIdSeed();

    void unsetCompatMode();

    void unsetConformance();

    void unsetCustDataLst();

    void unsetCustShowLst();

    void unsetDefaultTextStyle();

    void unsetEmbedTrueTypeFonts();

    void unsetEmbeddedFontLst();

    void unsetExtLst();

    void unsetFirstSlideNum();

    void unsetHandoutMasterIdLst();

    void unsetKinsoku();

    void unsetModifyVerifier();

    void unsetNotesMasterIdLst();

    void unsetPhotoAlbum();

    void unsetRemovePersonalInfoOnSave();

    void unsetRtl();

    void unsetSaveSubsetFonts();

    void unsetServerZoom();

    void unsetShowSpecialPlsOnTitleSld();

    void unsetSldIdLst();

    void unsetSldMasterIdLst();

    void unsetSldSz();

    void unsetSmartTags();

    void unsetStrictFirstAndLastChars();

    XmlBoolean xgetAutoCompressPictures();

    STBookmarkIdSeed xgetBookmarkIdSeed();

    XmlBoolean xgetCompatMode();

    STConformanceClass xgetConformance();

    XmlBoolean xgetEmbedTrueTypeFonts();

    XmlInt xgetFirstSlideNum();

    XmlBoolean xgetRemovePersonalInfoOnSave();

    XmlBoolean xgetRtl();

    XmlBoolean xgetSaveSubsetFonts();

    STPercentage xgetServerZoom();

    XmlBoolean xgetShowSpecialPlsOnTitleSld();

    XmlBoolean xgetStrictFirstAndLastChars();

    void xsetAutoCompressPictures(XmlBoolean xmlBoolean);

    void xsetBookmarkIdSeed(STBookmarkIdSeed sTBookmarkIdSeed);

    void xsetCompatMode(XmlBoolean xmlBoolean);

    void xsetConformance(STConformanceClass sTConformanceClass);

    void xsetEmbedTrueTypeFonts(XmlBoolean xmlBoolean);

    void xsetFirstSlideNum(XmlInt xmlInt);

    void xsetRemovePersonalInfoOnSave(XmlBoolean xmlBoolean);

    void xsetRtl(XmlBoolean xmlBoolean);

    void xsetSaveSubsetFonts(XmlBoolean xmlBoolean);

    void xsetServerZoom(STPercentage sTPercentage);

    void xsetShowSpecialPlsOnTitleSld(XmlBoolean xmlBoolean);

    void xsetStrictFirstAndLastChars(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTPresentation> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpresentation56cbtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
