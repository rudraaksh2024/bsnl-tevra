package com.microsoft.schemas.vml;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STColorType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

public interface CTImageData extends XmlObject {
    public static final DocumentFactory<CTImageData> Factory;
    public static final SchemaType type;

    String getAlthref();

    STTrueFalse.Enum getBilevel();

    String getBlacklevel();

    String getChromakey();

    String getCropbottom();

    String getCropleft();

    String getCropright();

    String getCroptop();

    STTrueFalse.Enum getDetectmouseclick();

    String getEmbosscolor();

    String getGain();

    String getGamma();

    STTrueFalse.Enum getGrayscale();

    String getHref();

    String getHref2();

    String getId();

    String getId2();

    float getMovie();

    float getOleid();

    String getPict();

    String getRecolortarget();

    String getRelid();

    String getSrc();

    String getTitle();

    boolean isSetAlthref();

    boolean isSetBilevel();

    boolean isSetBlacklevel();

    boolean isSetChromakey();

    boolean isSetCropbottom();

    boolean isSetCropleft();

    boolean isSetCropright();

    boolean isSetCroptop();

    boolean isSetDetectmouseclick();

    boolean isSetEmbosscolor();

    boolean isSetGain();

    boolean isSetGamma();

    boolean isSetGrayscale();

    boolean isSetHref();

    boolean isSetHref2();

    boolean isSetId();

    boolean isSetId2();

    boolean isSetMovie();

    boolean isSetOleid();

    boolean isSetPict();

    boolean isSetRecolortarget();

    boolean isSetRelid();

    boolean isSetSrc();

    boolean isSetTitle();

    void setAlthref(String str);

    void setBilevel(STTrueFalse.Enum enumR);

    void setBlacklevel(String str);

    void setChromakey(String str);

    void setCropbottom(String str);

    void setCropleft(String str);

    void setCropright(String str);

    void setCroptop(String str);

    void setDetectmouseclick(STTrueFalse.Enum enumR);

    void setEmbosscolor(String str);

    void setGain(String str);

    void setGamma(String str);

    void setGrayscale(STTrueFalse.Enum enumR);

    void setHref(String str);

    void setHref2(String str);

    void setId(String str);

    void setId2(String str);

    void setMovie(float f);

    void setOleid(float f);

    void setPict(String str);

    void setRecolortarget(String str);

    void setRelid(String str);

    void setSrc(String str);

    void setTitle(String str);

    void unsetAlthref();

    void unsetBilevel();

    void unsetBlacklevel();

    void unsetChromakey();

    void unsetCropbottom();

    void unsetCropleft();

    void unsetCropright();

    void unsetCroptop();

    void unsetDetectmouseclick();

    void unsetEmbosscolor();

    void unsetGain();

    void unsetGamma();

    void unsetGrayscale();

    void unsetHref();

    void unsetHref2();

    void unsetId();

    void unsetId2();

    void unsetMovie();

    void unsetOleid();

    void unsetPict();

    void unsetRecolortarget();

    void unsetRelid();

    void unsetSrc();

    void unsetTitle();

    XmlString xgetAlthref();

    STTrueFalse xgetBilevel();

    XmlString xgetBlacklevel();

    STColorType xgetChromakey();

    XmlString xgetCropbottom();

    XmlString xgetCropleft();

    XmlString xgetCropright();

    XmlString xgetCroptop();

    STTrueFalse xgetDetectmouseclick();

    STColorType xgetEmbosscolor();

    XmlString xgetGain();

    XmlString xgetGamma();

    STTrueFalse xgetGrayscale();

    XmlString xgetHref();

    STRelationshipId xgetHref2();

    XmlString xgetId();

    STRelationshipId xgetId2();

    XmlFloat xgetMovie();

    XmlFloat xgetOleid();

    STRelationshipId xgetPict();

    STColorType xgetRecolortarget();

    STRelationshipId xgetRelid();

    XmlString xgetSrc();

    XmlString xgetTitle();

    void xsetAlthref(XmlString xmlString);

    void xsetBilevel(STTrueFalse sTTrueFalse);

    void xsetBlacklevel(XmlString xmlString);

    void xsetChromakey(STColorType sTColorType);

    void xsetCropbottom(XmlString xmlString);

    void xsetCropleft(XmlString xmlString);

    void xsetCropright(XmlString xmlString);

    void xsetCroptop(XmlString xmlString);

    void xsetDetectmouseclick(STTrueFalse sTTrueFalse);

    void xsetEmbosscolor(STColorType sTColorType);

    void xsetGain(XmlString xmlString);

    void xsetGamma(XmlString xmlString);

    void xsetGrayscale(STTrueFalse sTTrueFalse);

    void xsetHref(XmlString xmlString);

    void xsetHref2(STRelationshipId sTRelationshipId);

    void xsetId(XmlString xmlString);

    void xsetId2(STRelationshipId sTRelationshipId);

    void xsetMovie(XmlFloat xmlFloat);

    void xsetOleid(XmlFloat xmlFloat);

    void xsetPict(STRelationshipId sTRelationshipId);

    void xsetRecolortarget(STColorType sTColorType);

    void xsetRelid(STRelationshipId sTRelationshipId);

    void xsetSrc(XmlString xmlString);

    void xsetTitle(XmlString xmlString);

    static {
        DocumentFactory<CTImageData> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctimagedata4039type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
