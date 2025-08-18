package com.microsoft.schemas.vml;

import com.microsoft.schemas.vml.STFillMethod;
import com.microsoft.schemas.vml.STFillType;
import java.math.BigDecimal;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STColorType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

public interface CTFill extends XmlObject {
    public static final DocumentFactory<CTFill> Factory;
    public static final SchemaType type;

    com.microsoft.schemas.office.office.CTFill addNewFill();

    STTrueFalse.Enum getAlignshape();

    String getAlthref();

    BigDecimal getAngle();

    STImageAspect$Enum getAspect();

    String getColor();

    String getColor2();

    String getColors();

    STTrueFalse.Enum getDetectmouseclick();

    com.microsoft.schemas.office.office.CTFill getFill();

    String getFocus();

    String getFocusposition();

    String getFocussize();

    String getHref();

    String getId();

    String getId2();

    STFillMethod.Enum getMethod();

    STTrueFalse.Enum getOn();

    String getOpacity();

    String getOpacity2();

    String getOrigin();

    String getPosition();

    STTrueFalse.Enum getRecolor();

    String getRelid();

    STTrueFalse.Enum getRotate();

    String getSize();

    String getSrc();

    String getTitle();

    STFillType.Enum getType();

    boolean isSetAlignshape();

    boolean isSetAlthref();

    boolean isSetAngle();

    boolean isSetAspect();

    boolean isSetColor();

    boolean isSetColor2();

    boolean isSetColors();

    boolean isSetDetectmouseclick();

    boolean isSetFill();

    boolean isSetFocus();

    boolean isSetFocusposition();

    boolean isSetFocussize();

    boolean isSetHref();

    boolean isSetId();

    boolean isSetId2();

    boolean isSetMethod();

    boolean isSetOn();

    boolean isSetOpacity();

    boolean isSetOpacity2();

    boolean isSetOrigin();

    boolean isSetPosition();

    boolean isSetRecolor();

    boolean isSetRelid();

    boolean isSetRotate();

    boolean isSetSize();

    boolean isSetSrc();

    boolean isSetTitle();

    boolean isSetType();

    void setAlignshape(STTrueFalse.Enum enumR);

    void setAlthref(String str);

    void setAngle(BigDecimal bigDecimal);

    void setAspect(STImageAspect$Enum sTImageAspect$Enum);

    void setColor(String str);

    void setColor2(String str);

    void setColors(String str);

    void setDetectmouseclick(STTrueFalse.Enum enumR);

    void setFill(com.microsoft.schemas.office.office.CTFill cTFill);

    void setFocus(String str);

    void setFocusposition(String str);

    void setFocussize(String str);

    void setHref(String str);

    void setId(String str);

    void setId2(String str);

    void setMethod(STFillMethod.Enum enumR);

    void setOn(STTrueFalse.Enum enumR);

    void setOpacity(String str);

    void setOpacity2(String str);

    void setOrigin(String str);

    void setPosition(String str);

    void setRecolor(STTrueFalse.Enum enumR);

    void setRelid(String str);

    void setRotate(STTrueFalse.Enum enumR);

    void setSize(String str);

    void setSrc(String str);

    void setTitle(String str);

    void setType(STFillType.Enum enumR);

    void unsetAlignshape();

    void unsetAlthref();

    void unsetAngle();

    void unsetAspect();

    void unsetColor();

    void unsetColor2();

    void unsetColors();

    void unsetDetectmouseclick();

    void unsetFill();

    void unsetFocus();

    void unsetFocusposition();

    void unsetFocussize();

    void unsetHref();

    void unsetId();

    void unsetId2();

    void unsetMethod();

    void unsetOn();

    void unsetOpacity();

    void unsetOpacity2();

    void unsetOrigin();

    void unsetPosition();

    void unsetRecolor();

    void unsetRelid();

    void unsetRotate();

    void unsetSize();

    void unsetSrc();

    void unsetTitle();

    void unsetType();

    STTrueFalse xgetAlignshape();

    XmlString xgetAlthref();

    XmlDecimal xgetAngle();

    STImageAspect xgetAspect();

    STColorType xgetColor();

    STColorType xgetColor2();

    XmlString xgetColors();

    STTrueFalse xgetDetectmouseclick();

    XmlString xgetFocus();

    XmlString xgetFocusposition();

    XmlString xgetFocussize();

    XmlString xgetHref();

    XmlString xgetId();

    STRelationshipId xgetId2();

    STFillMethod xgetMethod();

    STTrueFalse xgetOn();

    XmlString xgetOpacity();

    XmlString xgetOpacity2();

    XmlString xgetOrigin();

    XmlString xgetPosition();

    STTrueFalse xgetRecolor();

    STRelationshipId xgetRelid();

    STTrueFalse xgetRotate();

    XmlString xgetSize();

    XmlString xgetSrc();

    XmlString xgetTitle();

    STFillType xgetType();

    void xsetAlignshape(STTrueFalse sTTrueFalse);

    void xsetAlthref(XmlString xmlString);

    void xsetAngle(XmlDecimal xmlDecimal);

    void xsetAspect(STImageAspect sTImageAspect);

    void xsetColor(STColorType sTColorType);

    void xsetColor2(STColorType sTColorType);

    void xsetColors(XmlString xmlString);

    void xsetDetectmouseclick(STTrueFalse sTTrueFalse);

    void xsetFocus(XmlString xmlString);

    void xsetFocusposition(XmlString xmlString);

    void xsetFocussize(XmlString xmlString);

    void xsetHref(XmlString xmlString);

    void xsetId(XmlString xmlString);

    void xsetId2(STRelationshipId sTRelationshipId);

    void xsetMethod(STFillMethod sTFillMethod);

    void xsetOn(STTrueFalse sTTrueFalse);

    void xsetOpacity(XmlString xmlString);

    void xsetOpacity2(XmlString xmlString);

    void xsetOrigin(XmlString xmlString);

    void xsetPosition(XmlString xmlString);

    void xsetRecolor(STTrueFalse sTTrueFalse);

    void xsetRelid(STRelationshipId sTRelationshipId);

    void xsetRotate(STTrueFalse sTTrueFalse);

    void xsetSize(XmlString xmlString);

    void xsetSrc(XmlString xmlString);

    void xsetTitle(XmlString xmlString);

    void xsetType(STFillType sTFillType);

    static {
        DocumentFactory<CTFill> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfillb241type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
