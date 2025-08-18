package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;

public interface CTTableStyle extends XmlObject {
    public static final DocumentFactory<CTTableStyle> Factory;
    public static final SchemaType type;

    CTTablePartStyle addNewBand1H();

    CTTablePartStyle addNewBand1V();

    CTTablePartStyle addNewBand2H();

    CTTablePartStyle addNewBand2V();

    CTOfficeArtExtensionList addNewExtLst();

    CTTablePartStyle addNewFirstCol();

    CTTablePartStyle addNewFirstRow();

    CTTablePartStyle addNewLastCol();

    CTTablePartStyle addNewLastRow();

    CTTablePartStyle addNewNeCell();

    CTTablePartStyle addNewNwCell();

    CTTablePartStyle addNewSeCell();

    CTTablePartStyle addNewSwCell();

    CTTableBackgroundStyle addNewTblBg();

    CTTablePartStyle addNewWholeTbl();

    CTTablePartStyle getBand1H();

    CTTablePartStyle getBand1V();

    CTTablePartStyle getBand2H();

    CTTablePartStyle getBand2V();

    CTOfficeArtExtensionList getExtLst();

    CTTablePartStyle getFirstCol();

    CTTablePartStyle getFirstRow();

    CTTablePartStyle getLastCol();

    CTTablePartStyle getLastRow();

    CTTablePartStyle getNeCell();

    CTTablePartStyle getNwCell();

    CTTablePartStyle getSeCell();

    String getStyleId();

    String getStyleName();

    CTTablePartStyle getSwCell();

    CTTableBackgroundStyle getTblBg();

    CTTablePartStyle getWholeTbl();

    boolean isSetBand1H();

    boolean isSetBand1V();

    boolean isSetBand2H();

    boolean isSetBand2V();

    boolean isSetExtLst();

    boolean isSetFirstCol();

    boolean isSetFirstRow();

    boolean isSetLastCol();

    boolean isSetLastRow();

    boolean isSetNeCell();

    boolean isSetNwCell();

    boolean isSetSeCell();

    boolean isSetSwCell();

    boolean isSetTblBg();

    boolean isSetWholeTbl();

    void setBand1H(CTTablePartStyle cTTablePartStyle);

    void setBand1V(CTTablePartStyle cTTablePartStyle);

    void setBand2H(CTTablePartStyle cTTablePartStyle);

    void setBand2V(CTTablePartStyle cTTablePartStyle);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setFirstCol(CTTablePartStyle cTTablePartStyle);

    void setFirstRow(CTTablePartStyle cTTablePartStyle);

    void setLastCol(CTTablePartStyle cTTablePartStyle);

    void setLastRow(CTTablePartStyle cTTablePartStyle);

    void setNeCell(CTTablePartStyle cTTablePartStyle);

    void setNwCell(CTTablePartStyle cTTablePartStyle);

    void setSeCell(CTTablePartStyle cTTablePartStyle);

    void setStyleId(String str);

    void setStyleName(String str);

    void setSwCell(CTTablePartStyle cTTablePartStyle);

    void setTblBg(CTTableBackgroundStyle cTTableBackgroundStyle);

    void setWholeTbl(CTTablePartStyle cTTablePartStyle);

    void unsetBand1H();

    void unsetBand1V();

    void unsetBand2H();

    void unsetBand2V();

    void unsetExtLst();

    void unsetFirstCol();

    void unsetFirstRow();

    void unsetLastCol();

    void unsetLastRow();

    void unsetNeCell();

    void unsetNwCell();

    void unsetSeCell();

    void unsetSwCell();

    void unsetTblBg();

    void unsetWholeTbl();

    STGuid xgetStyleId();

    XmlString xgetStyleName();

    void xsetStyleId(STGuid sTGuid);

    void xsetStyleName(XmlString xmlString);

    static {
        DocumentFactory<CTTableStyle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablestyled59etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
