package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.math.BigInteger;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;

public interface CTLvl extends XmlObject {
    public static final DocumentFactory<CTLvl> Factory;
    public static final SchemaType type;

    CTOnOff addNewIsLgl();

    CTLvlLegacy addNewLegacy();

    CTJc addNewLvlJc();

    CTDecimalNumber addNewLvlPicBulletId();

    CTDecimalNumber addNewLvlRestart();

    CTLevelText addNewLvlText();

    CTNumFmt addNewNumFmt();

    CTPPrGeneral addNewPPr();

    CTString addNewPStyle();

    CTRPr addNewRPr();

    CTDecimalNumber addNewStart();

    CTLevelSuffix addNewSuff();

    BigInteger getIlvl();

    CTOnOff getIsLgl();

    CTLvlLegacy getLegacy();

    CTJc getLvlJc();

    CTDecimalNumber getLvlPicBulletId();

    CTDecimalNumber getLvlRestart();

    CTLevelText getLvlText();

    CTNumFmt getNumFmt();

    CTPPrGeneral getPPr();

    CTString getPStyle();

    CTRPr getRPr();

    CTDecimalNumber getStart();

    CTLevelSuffix getSuff();

    Object getTentative();

    byte[] getTplc();

    boolean isSetIsLgl();

    boolean isSetLegacy();

    boolean isSetLvlJc();

    boolean isSetLvlPicBulletId();

    boolean isSetLvlRestart();

    boolean isSetLvlText();

    boolean isSetNumFmt();

    boolean isSetPPr();

    boolean isSetPStyle();

    boolean isSetRPr();

    boolean isSetStart();

    boolean isSetSuff();

    boolean isSetTentative();

    boolean isSetTplc();

    void setIlvl(BigInteger bigInteger);

    void setIsLgl(CTOnOff cTOnOff);

    void setLegacy(CTLvlLegacy cTLvlLegacy);

    void setLvlJc(CTJc cTJc);

    void setLvlPicBulletId(CTDecimalNumber cTDecimalNumber);

    void setLvlRestart(CTDecimalNumber cTDecimalNumber);

    void setLvlText(CTLevelText cTLevelText);

    void setNumFmt(CTNumFmt cTNumFmt);

    void setPPr(CTPPrGeneral cTPPrGeneral);

    void setPStyle(CTString cTString);

    void setRPr(CTRPr cTRPr);

    void setStart(CTDecimalNumber cTDecimalNumber);

    void setSuff(CTLevelSuffix cTLevelSuffix);

    void setTentative(Object obj);

    void setTplc(byte[] bArr);

    void unsetIsLgl();

    void unsetLegacy();

    void unsetLvlJc();

    void unsetLvlPicBulletId();

    void unsetLvlRestart();

    void unsetLvlText();

    void unsetNumFmt();

    void unsetPPr();

    void unsetPStyle();

    void unsetRPr();

    void unsetStart();

    void unsetSuff();

    void unsetTentative();

    void unsetTplc();

    STDecimalNumber xgetIlvl();

    STOnOff xgetTentative();

    STLongHexNumber xgetTplc();

    void xsetIlvl(STDecimalNumber sTDecimalNumber);

    void xsetTentative(STOnOff sTOnOff);

    void xsetTplc(STLongHexNumber sTLongHexNumber);

    static {
        DocumentFactory<CTLvl> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlvlf630type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
