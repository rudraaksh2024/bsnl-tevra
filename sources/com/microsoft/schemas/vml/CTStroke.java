package com.microsoft.schemas.vml;

import com.microsoft.schemas.office.office.CTStrokeChild;
import com.microsoft.schemas.vml.STFillType;
import com.microsoft.schemas.vml.STStrokeArrowType;
import com.microsoft.schemas.vml.STStrokeJoinStyle;
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

public interface CTStroke extends XmlObject {
    public static final DocumentFactory<CTStroke> Factory;
    public static final SchemaType type;

    CTStrokeChild addNewBottom();

    CTStrokeChild addNewColumn();

    CTStrokeChild addNewLeft();

    CTStrokeChild addNewRight();

    CTStrokeChild addNewTop();

    String getAlthref();

    CTStrokeChild getBottom();

    String getColor();

    String getColor2();

    CTStrokeChild getColumn();

    String getDashstyle();

    STStrokeArrowType.Enum getEndarrow();

    STStrokeArrowLength$Enum getEndarrowlength();

    STStrokeArrowWidth$Enum getEndarrowwidth();

    STStrokeEndCap$Enum getEndcap();

    STFillType.Enum getFilltype();

    STTrueFalse.Enum getForcedash();

    String getHref();

    String getId();

    String getId2();

    STTrueFalse.Enum getImagealignshape();

    STImageAspect$Enum getImageaspect();

    String getImagesize();

    STTrueFalse.Enum getInsetpen();

    STStrokeJoinStyle.Enum getJoinstyle();

    CTStrokeChild getLeft();

    STStrokeLineStyle$Enum getLinestyle();

    BigDecimal getMiterlimit();

    STTrueFalse.Enum getOn();

    String getOpacity();

    String getRelid();

    CTStrokeChild getRight();

    String getSrc();

    STStrokeArrowType.Enum getStartarrow();

    STStrokeArrowLength$Enum getStartarrowlength();

    STStrokeArrowWidth$Enum getStartarrowwidth();

    String getTitle();

    CTStrokeChild getTop();

    String getWeight();

    boolean isSetAlthref();

    boolean isSetBottom();

    boolean isSetColor();

    boolean isSetColor2();

    boolean isSetColumn();

    boolean isSetDashstyle();

    boolean isSetEndarrow();

    boolean isSetEndarrowlength();

    boolean isSetEndarrowwidth();

    boolean isSetEndcap();

    boolean isSetFilltype();

    boolean isSetForcedash();

    boolean isSetHref();

    boolean isSetId();

    boolean isSetId2();

    boolean isSetImagealignshape();

    boolean isSetImageaspect();

    boolean isSetImagesize();

    boolean isSetInsetpen();

    boolean isSetJoinstyle();

    boolean isSetLeft();

    boolean isSetLinestyle();

    boolean isSetMiterlimit();

    boolean isSetOn();

    boolean isSetOpacity();

    boolean isSetRelid();

    boolean isSetRight();

    boolean isSetSrc();

    boolean isSetStartarrow();

    boolean isSetStartarrowlength();

    boolean isSetStartarrowwidth();

    boolean isSetTitle();

    boolean isSetTop();

    boolean isSetWeight();

    void setAlthref(String str);

    void setBottom(CTStrokeChild cTStrokeChild);

    void setColor(String str);

    void setColor2(String str);

    void setColumn(CTStrokeChild cTStrokeChild);

    void setDashstyle(String str);

    void setEndarrow(STStrokeArrowType.Enum enumR);

    void setEndarrowlength(STStrokeArrowLength$Enum sTStrokeArrowLength$Enum);

    void setEndarrowwidth(STStrokeArrowWidth$Enum sTStrokeArrowWidth$Enum);

    void setEndcap(STStrokeEndCap$Enum sTStrokeEndCap$Enum);

    void setFilltype(STFillType.Enum enumR);

    void setForcedash(STTrueFalse.Enum enumR);

    void setHref(String str);

    void setId(String str);

    void setId2(String str);

    void setImagealignshape(STTrueFalse.Enum enumR);

    void setImageaspect(STImageAspect$Enum sTImageAspect$Enum);

    void setImagesize(String str);

    void setInsetpen(STTrueFalse.Enum enumR);

    void setJoinstyle(STStrokeJoinStyle.Enum enumR);

    void setLeft(CTStrokeChild cTStrokeChild);

    void setLinestyle(STStrokeLineStyle$Enum sTStrokeLineStyle$Enum);

    void setMiterlimit(BigDecimal bigDecimal);

    void setOn(STTrueFalse.Enum enumR);

    void setOpacity(String str);

    void setRelid(String str);

    void setRight(CTStrokeChild cTStrokeChild);

    void setSrc(String str);

    void setStartarrow(STStrokeArrowType.Enum enumR);

    void setStartarrowlength(STStrokeArrowLength$Enum sTStrokeArrowLength$Enum);

    void setStartarrowwidth(STStrokeArrowWidth$Enum sTStrokeArrowWidth$Enum);

    void setTitle(String str);

    void setTop(CTStrokeChild cTStrokeChild);

    void setWeight(String str);

    void unsetAlthref();

    void unsetBottom();

    void unsetColor();

    void unsetColor2();

    void unsetColumn();

    void unsetDashstyle();

    void unsetEndarrow();

    void unsetEndarrowlength();

    void unsetEndarrowwidth();

    void unsetEndcap();

    void unsetFilltype();

    void unsetForcedash();

    void unsetHref();

    void unsetId();

    void unsetId2();

    void unsetImagealignshape();

    void unsetImageaspect();

    void unsetImagesize();

    void unsetInsetpen();

    void unsetJoinstyle();

    void unsetLeft();

    void unsetLinestyle();

    void unsetMiterlimit();

    void unsetOn();

    void unsetOpacity();

    void unsetRelid();

    void unsetRight();

    void unsetSrc();

    void unsetStartarrow();

    void unsetStartarrowlength();

    void unsetStartarrowwidth();

    void unsetTitle();

    void unsetTop();

    void unsetWeight();

    XmlString xgetAlthref();

    STColorType xgetColor();

    STColorType xgetColor2();

    XmlString xgetDashstyle();

    STStrokeArrowType xgetEndarrow();

    STStrokeArrowLength xgetEndarrowlength();

    STStrokeArrowWidth xgetEndarrowwidth();

    STStrokeEndCap xgetEndcap();

    STFillType xgetFilltype();

    STTrueFalse xgetForcedash();

    XmlString xgetHref();

    XmlString xgetId();

    STRelationshipId xgetId2();

    STTrueFalse xgetImagealignshape();

    STImageAspect xgetImageaspect();

    XmlString xgetImagesize();

    STTrueFalse xgetInsetpen();

    STStrokeJoinStyle xgetJoinstyle();

    STStrokeLineStyle xgetLinestyle();

    XmlDecimal xgetMiterlimit();

    STTrueFalse xgetOn();

    XmlString xgetOpacity();

    STRelationshipId xgetRelid();

    XmlString xgetSrc();

    STStrokeArrowType xgetStartarrow();

    STStrokeArrowLength xgetStartarrowlength();

    STStrokeArrowWidth xgetStartarrowwidth();

    XmlString xgetTitle();

    XmlString xgetWeight();

    void xsetAlthref(XmlString xmlString);

    void xsetColor(STColorType sTColorType);

    void xsetColor2(STColorType sTColorType);

    void xsetDashstyle(XmlString xmlString);

    void xsetEndarrow(STStrokeArrowType sTStrokeArrowType);

    void xsetEndarrowlength(STStrokeArrowLength sTStrokeArrowLength);

    void xsetEndarrowwidth(STStrokeArrowWidth sTStrokeArrowWidth);

    void xsetEndcap(STStrokeEndCap sTStrokeEndCap);

    void xsetFilltype(STFillType sTFillType);

    void xsetForcedash(STTrueFalse sTTrueFalse);

    void xsetHref(XmlString xmlString);

    void xsetId(XmlString xmlString);

    void xsetId2(STRelationshipId sTRelationshipId);

    void xsetImagealignshape(STTrueFalse sTTrueFalse);

    void xsetImageaspect(STImageAspect sTImageAspect);

    void xsetImagesize(XmlString xmlString);

    void xsetInsetpen(STTrueFalse sTTrueFalse);

    void xsetJoinstyle(STStrokeJoinStyle sTStrokeJoinStyle);

    void xsetLinestyle(STStrokeLineStyle sTStrokeLineStyle);

    void xsetMiterlimit(XmlDecimal xmlDecimal);

    void xsetOn(STTrueFalse sTTrueFalse);

    void xsetOpacity(XmlString xmlString);

    void xsetRelid(STRelationshipId sTRelationshipId);

    void xsetSrc(XmlString xmlString);

    void xsetStartarrow(STStrokeArrowType sTStrokeArrowType);

    void xsetStartarrowlength(STStrokeArrowLength sTStrokeArrowLength);

    void xsetStartarrowwidth(STStrokeArrowWidth sTStrokeArrowWidth);

    void xsetTitle(XmlString xmlString);

    void xsetWeight(XmlString xmlString);

    static {
        DocumentFactory<CTStroke> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstrokee2f6type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
