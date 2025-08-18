package com.microsoft.schemas.vml;

import com.microsoft.schemas.office.office.STInsetMode;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent;

public interface CTTextbox extends XmlObject {
    public static final DocumentFactory<CTTextbox> Factory;
    public static final SchemaType type;

    CTTxbxContent addNewTxbxContent();

    String getId();

    String getInset();

    STInsetMode.Enum getInsetmode();

    STTrueFalse.Enum getSingleclick();

    String getStyle();

    CTTxbxContent getTxbxContent();

    boolean isSetId();

    boolean isSetInset();

    boolean isSetInsetmode();

    boolean isSetSingleclick();

    boolean isSetStyle();

    boolean isSetTxbxContent();

    void setId(String str);

    void setInset(String str);

    void setInsetmode(STInsetMode.Enum enumR);

    void setSingleclick(STTrueFalse.Enum enumR);

    void setStyle(String str);

    void setTxbxContent(CTTxbxContent cTTxbxContent);

    void unsetId();

    void unsetInset();

    void unsetInsetmode();

    void unsetSingleclick();

    void unsetStyle();

    void unsetTxbxContent();

    XmlString xgetId();

    XmlString xgetInset();

    STInsetMode xgetInsetmode();

    STTrueFalse xgetSingleclick();

    XmlString xgetStyle();

    void xsetId(XmlString xmlString);

    void xsetInset(XmlString xmlString);

    void xsetInsetmode(STInsetMode sTInsetMode);

    void xsetSingleclick(STTrueFalse sTTrueFalse);

    void xsetStyle(XmlString xmlString);

    static {
        DocumentFactory<CTTextbox> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextboxf712type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
