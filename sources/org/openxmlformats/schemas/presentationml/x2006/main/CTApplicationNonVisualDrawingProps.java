package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAudioCD;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAudioFile;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile;
import org.openxmlformats.schemas.drawingml.x2006.main.CTQuickTimeFile;
import org.openxmlformats.schemas.drawingml.x2006.main.CTVideoFile;

public interface CTApplicationNonVisualDrawingProps extends XmlObject {
    public static final DocumentFactory<CTApplicationNonVisualDrawingProps> Factory;
    public static final SchemaType type;

    CTAudioCD addNewAudioCd();

    CTAudioFile addNewAudioFile();

    CTCustomerDataList addNewCustDataLst();

    CTExtensionList addNewExtLst();

    CTPlaceholder addNewPh();

    CTQuickTimeFile addNewQuickTimeFile();

    CTVideoFile addNewVideoFile();

    CTEmbeddedWAVAudioFile addNewWavAudioFile();

    CTAudioCD getAudioCd();

    CTAudioFile getAudioFile();

    CTCustomerDataList getCustDataLst();

    CTExtensionList getExtLst();

    boolean getIsPhoto();

    CTPlaceholder getPh();

    CTQuickTimeFile getQuickTimeFile();

    boolean getUserDrawn();

    CTVideoFile getVideoFile();

    CTEmbeddedWAVAudioFile getWavAudioFile();

    boolean isSetAudioCd();

    boolean isSetAudioFile();

    boolean isSetCustDataLst();

    boolean isSetExtLst();

    boolean isSetIsPhoto();

    boolean isSetPh();

    boolean isSetQuickTimeFile();

    boolean isSetUserDrawn();

    boolean isSetVideoFile();

    boolean isSetWavAudioFile();

    void setAudioCd(CTAudioCD cTAudioCD);

    void setAudioFile(CTAudioFile cTAudioFile);

    void setCustDataLst(CTCustomerDataList cTCustomerDataList);

    void setExtLst(CTExtensionList cTExtensionList);

    void setIsPhoto(boolean z);

    void setPh(CTPlaceholder cTPlaceholder);

    void setQuickTimeFile(CTQuickTimeFile cTQuickTimeFile);

    void setUserDrawn(boolean z);

    void setVideoFile(CTVideoFile cTVideoFile);

    void setWavAudioFile(CTEmbeddedWAVAudioFile cTEmbeddedWAVAudioFile);

    void unsetAudioCd();

    void unsetAudioFile();

    void unsetCustDataLst();

    void unsetExtLst();

    void unsetIsPhoto();

    void unsetPh();

    void unsetQuickTimeFile();

    void unsetUserDrawn();

    void unsetVideoFile();

    void unsetWavAudioFile();

    XmlBoolean xgetIsPhoto();

    XmlBoolean xgetUserDrawn();

    void xsetIsPhoto(XmlBoolean xmlBoolean);

    void xsetUserDrawn(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTApplicationNonVisualDrawingProps> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctapplicationnonvisualdrawingprops2fb6type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
