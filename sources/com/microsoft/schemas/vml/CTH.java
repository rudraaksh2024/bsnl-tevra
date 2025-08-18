package com.microsoft.schemas.vml;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;

public interface CTH extends XmlObject {
    public static final DocumentFactory<CTH> Factory;
    public static final SchemaType type;

    STTrueFalse.Enum getInvx();

    STTrueFalse.Enum getInvy();

    String getMap();

    String getPolar();

    String getPosition();

    String getRadiusrange();

    STTrueFalseBlank.Enum getSwitch();

    String getXrange();

    String getYrange();

    boolean isSetInvx();

    boolean isSetInvy();

    boolean isSetMap();

    boolean isSetPolar();

    boolean isSetPosition();

    boolean isSetRadiusrange();

    boolean isSetSwitch();

    boolean isSetXrange();

    boolean isSetYrange();

    void setInvx(STTrueFalse.Enum enumR);

    void setInvy(STTrueFalse.Enum enumR);

    void setMap(String str);

    void setPolar(String str);

    void setPosition(String str);

    void setRadiusrange(String str);

    void setSwitch(STTrueFalseBlank.Enum enumR);

    void setXrange(String str);

    void setYrange(String str);

    void unsetInvx();

    void unsetInvy();

    void unsetMap();

    void unsetPolar();

    void unsetPosition();

    void unsetRadiusrange();

    void unsetSwitch();

    void unsetXrange();

    void unsetYrange();

    STTrueFalse xgetInvx();

    STTrueFalse xgetInvy();

    XmlString xgetMap();

    XmlString xgetPolar();

    XmlString xgetPosition();

    XmlString xgetRadiusrange();

    STTrueFalseBlank xgetSwitch();

    XmlString xgetXrange();

    XmlString xgetYrange();

    void xsetInvx(STTrueFalse sTTrueFalse);

    void xsetInvy(STTrueFalse sTTrueFalse);

    void xsetMap(XmlString xmlString);

    void xsetPolar(XmlString xmlString);

    void xsetPosition(XmlString xmlString);

    void xsetRadiusrange(XmlString xmlString);

    void xsetSwitch(STTrueFalseBlank sTTrueFalseBlank);

    void xsetXrange(XmlString xmlString);

    void xsetYrange(XmlString xmlString);

    static {
        DocumentFactory<CTH> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cth4cbctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
