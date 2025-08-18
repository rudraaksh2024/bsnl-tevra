package com.microsoft.schemas.vml;

import com.microsoft.schemas.office.office.STConnectType;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

public interface CTPath extends XmlObject {
    public static final DocumentFactory<CTPath> Factory;
    public static final SchemaType type;

    STTrueFalse.Enum getArrowok();

    String getConnectangles();

    String getConnectlocs();

    STConnectType.Enum getConnecttype();

    STTrueFalse.Enum getExtrusionok();

    STTrueFalse.Enum getFillok();

    STTrueFalse.Enum getGradientshapeok();

    String getId();

    STTrueFalse.Enum getInsetpenok();

    String getLimo();

    STTrueFalse.Enum getShadowok();

    STTrueFalse.Enum getStrokeok();

    String getTextboxrect();

    STTrueFalse.Enum getTextpathok();

    String getV();

    boolean isSetArrowok();

    boolean isSetConnectangles();

    boolean isSetConnectlocs();

    boolean isSetConnecttype();

    boolean isSetExtrusionok();

    boolean isSetFillok();

    boolean isSetGradientshapeok();

    boolean isSetId();

    boolean isSetInsetpenok();

    boolean isSetLimo();

    boolean isSetShadowok();

    boolean isSetStrokeok();

    boolean isSetTextboxrect();

    boolean isSetTextpathok();

    boolean isSetV();

    void setArrowok(STTrueFalse.Enum enumR);

    void setConnectangles(String str);

    void setConnectlocs(String str);

    void setConnecttype(STConnectType.Enum enumR);

    void setExtrusionok(STTrueFalse.Enum enumR);

    void setFillok(STTrueFalse.Enum enumR);

    void setGradientshapeok(STTrueFalse.Enum enumR);

    void setId(String str);

    void setInsetpenok(STTrueFalse.Enum enumR);

    void setLimo(String str);

    void setShadowok(STTrueFalse.Enum enumR);

    void setStrokeok(STTrueFalse.Enum enumR);

    void setTextboxrect(String str);

    void setTextpathok(STTrueFalse.Enum enumR);

    void setV(String str);

    void unsetArrowok();

    void unsetConnectangles();

    void unsetConnectlocs();

    void unsetConnecttype();

    void unsetExtrusionok();

    void unsetFillok();

    void unsetGradientshapeok();

    void unsetId();

    void unsetInsetpenok();

    void unsetLimo();

    void unsetShadowok();

    void unsetStrokeok();

    void unsetTextboxrect();

    void unsetTextpathok();

    void unsetV();

    STTrueFalse xgetArrowok();

    XmlString xgetConnectangles();

    XmlString xgetConnectlocs();

    STConnectType xgetConnecttype();

    STTrueFalse xgetExtrusionok();

    STTrueFalse xgetFillok();

    STTrueFalse xgetGradientshapeok();

    XmlString xgetId();

    STTrueFalse xgetInsetpenok();

    XmlString xgetLimo();

    STTrueFalse xgetShadowok();

    STTrueFalse xgetStrokeok();

    XmlString xgetTextboxrect();

    STTrueFalse xgetTextpathok();

    XmlString xgetV();

    void xsetArrowok(STTrueFalse sTTrueFalse);

    void xsetConnectangles(XmlString xmlString);

    void xsetConnectlocs(XmlString xmlString);

    void xsetConnecttype(STConnectType sTConnectType);

    void xsetExtrusionok(STTrueFalse sTTrueFalse);

    void xsetFillok(STTrueFalse sTTrueFalse);

    void xsetGradientshapeok(STTrueFalse sTTrueFalse);

    void xsetId(XmlString xmlString);

    void xsetInsetpenok(STTrueFalse sTTrueFalse);

    void xsetLimo(XmlString xmlString);

    void xsetShadowok(STTrueFalse sTTrueFalse);

    void xsetStrokeok(STTrueFalse sTTrueFalse);

    void xsetTextboxrect(XmlString xmlString);

    void xsetTextpathok(STTrueFalse sTTrueFalse);

    void xsetV(XmlString xmlString);

    static {
        DocumentFactory<CTPath> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpath5963type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
