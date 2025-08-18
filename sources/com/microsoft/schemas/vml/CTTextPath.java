package com.microsoft.schemas.vml;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

public interface CTTextPath extends XmlObject {
    public static final DocumentFactory<CTTextPath> Factory;
    public static final SchemaType type;

    STTrueFalse.Enum getFitpath();

    STTrueFalse.Enum getFitshape();

    String getId();

    STTrueFalse.Enum getOn();

    String getString();

    String getStyle();

    STTrueFalse.Enum getTrim();

    STTrueFalse.Enum getXscale();

    boolean isSetFitpath();

    boolean isSetFitshape();

    boolean isSetId();

    boolean isSetOn();

    boolean isSetString();

    boolean isSetStyle();

    boolean isSetTrim();

    boolean isSetXscale();

    void setFitpath(STTrueFalse.Enum enumR);

    void setFitshape(STTrueFalse.Enum enumR);

    void setId(String str);

    void setOn(STTrueFalse.Enum enumR);

    void setString(String str);

    void setStyle(String str);

    void setTrim(STTrueFalse.Enum enumR);

    void setXscale(STTrueFalse.Enum enumR);

    void unsetFitpath();

    void unsetFitshape();

    void unsetId();

    void unsetOn();

    void unsetString();

    void unsetStyle();

    void unsetTrim();

    void unsetXscale();

    STTrueFalse xgetFitpath();

    STTrueFalse xgetFitshape();

    XmlString xgetId();

    STTrueFalse xgetOn();

    XmlString xgetString();

    XmlString xgetStyle();

    STTrueFalse xgetTrim();

    STTrueFalse xgetXscale();

    void xsetFitpath(STTrueFalse sTTrueFalse);

    void xsetFitshape(STTrueFalse sTTrueFalse);

    void xsetId(XmlString xmlString);

    void xsetOn(STTrueFalse sTTrueFalse);

    void xsetString(XmlString xmlString);

    void xsetStyle(XmlString xmlString);

    void xsetTrim(STTrueFalse sTTrueFalse);

    void xsetXscale(STTrueFalse sTTrueFalse);

    static {
        DocumentFactory<CTTextPath> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextpath14f0type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
