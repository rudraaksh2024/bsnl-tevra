package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;

public interface CTStyle extends XmlObject {
    public static final DocumentFactory<CTStyle> Factory;
    public static final SchemaType type;

    CTString addNewAliases();

    CTOnOff addNewAutoRedefine();

    CTString addNewBasedOn();

    CTOnOff addNewHidden();

    CTString addNewLink();

    CTOnOff addNewLocked();

    CTString addNewName();

    CTString addNewNext();

    CTPPrGeneral addNewPPr();

    CTOnOff addNewPersonal();

    CTOnOff addNewPersonalCompose();

    CTOnOff addNewPersonalReply();

    CTOnOff addNewQFormat();

    CTRPr addNewRPr();

    CTLongHexNumber addNewRsid();

    CTOnOff addNewSemiHidden();

    CTTblPrBase addNewTblPr();

    CTTblStylePr addNewTblStylePr();

    CTTcPr addNewTcPr();

    CTTrPr addNewTrPr();

    CTDecimalNumber addNewUiPriority();

    CTOnOff addNewUnhideWhenUsed();

    CTString getAliases();

    CTOnOff getAutoRedefine();

    CTString getBasedOn();

    Object getCustomStyle();

    Object getDefault();

    CTOnOff getHidden();

    CTString getLink();

    CTOnOff getLocked();

    CTString getName();

    CTString getNext();

    CTPPrGeneral getPPr();

    CTOnOff getPersonal();

    CTOnOff getPersonalCompose();

    CTOnOff getPersonalReply();

    CTOnOff getQFormat();

    CTRPr getRPr();

    CTLongHexNumber getRsid();

    CTOnOff getSemiHidden();

    String getStyleId();

    CTTblPrBase getTblPr();

    CTTblStylePr getTblStylePrArray(int i);

    CTTblStylePr[] getTblStylePrArray();

    List<CTTblStylePr> getTblStylePrList();

    CTTcPr getTcPr();

    CTTrPr getTrPr();

    STStyleType.Enum getType();

    CTDecimalNumber getUiPriority();

    CTOnOff getUnhideWhenUsed();

    CTTblStylePr insertNewTblStylePr(int i);

    boolean isSetAliases();

    boolean isSetAutoRedefine();

    boolean isSetBasedOn();

    boolean isSetCustomStyle();

    boolean isSetDefault();

    boolean isSetHidden();

    boolean isSetLink();

    boolean isSetLocked();

    boolean isSetName();

    boolean isSetNext();

    boolean isSetPPr();

    boolean isSetPersonal();

    boolean isSetPersonalCompose();

    boolean isSetPersonalReply();

    boolean isSetQFormat();

    boolean isSetRPr();

    boolean isSetRsid();

    boolean isSetSemiHidden();

    boolean isSetStyleId();

    boolean isSetTblPr();

    boolean isSetTcPr();

    boolean isSetTrPr();

    boolean isSetType();

    boolean isSetUiPriority();

    boolean isSetUnhideWhenUsed();

    void removeTblStylePr(int i);

    void setAliases(CTString cTString);

    void setAutoRedefine(CTOnOff cTOnOff);

    void setBasedOn(CTString cTString);

    void setCustomStyle(Object obj);

    void setDefault(Object obj);

    void setHidden(CTOnOff cTOnOff);

    void setLink(CTString cTString);

    void setLocked(CTOnOff cTOnOff);

    void setName(CTString cTString);

    void setNext(CTString cTString);

    void setPPr(CTPPrGeneral cTPPrGeneral);

    void setPersonal(CTOnOff cTOnOff);

    void setPersonalCompose(CTOnOff cTOnOff);

    void setPersonalReply(CTOnOff cTOnOff);

    void setQFormat(CTOnOff cTOnOff);

    void setRPr(CTRPr cTRPr);

    void setRsid(CTLongHexNumber cTLongHexNumber);

    void setSemiHidden(CTOnOff cTOnOff);

    void setStyleId(String str);

    void setTblPr(CTTblPrBase cTTblPrBase);

    void setTblStylePrArray(int i, CTTblStylePr cTTblStylePr);

    void setTblStylePrArray(CTTblStylePr[] cTTblStylePrArr);

    void setTcPr(CTTcPr cTTcPr);

    void setTrPr(CTTrPr cTTrPr);

    void setType(STStyleType.Enum enumR);

    void setUiPriority(CTDecimalNumber cTDecimalNumber);

    void setUnhideWhenUsed(CTOnOff cTOnOff);

    int sizeOfTblStylePrArray();

    void unsetAliases();

    void unsetAutoRedefine();

    void unsetBasedOn();

    void unsetCustomStyle();

    void unsetDefault();

    void unsetHidden();

    void unsetLink();

    void unsetLocked();

    void unsetName();

    void unsetNext();

    void unsetPPr();

    void unsetPersonal();

    void unsetPersonalCompose();

    void unsetPersonalReply();

    void unsetQFormat();

    void unsetRPr();

    void unsetRsid();

    void unsetSemiHidden();

    void unsetStyleId();

    void unsetTblPr();

    void unsetTcPr();

    void unsetTrPr();

    void unsetType();

    void unsetUiPriority();

    void unsetUnhideWhenUsed();

    STOnOff xgetCustomStyle();

    STOnOff xgetDefault();

    STString xgetStyleId();

    STStyleType xgetType();

    void xsetCustomStyle(STOnOff sTOnOff);

    void xsetDefault(STOnOff sTOnOff);

    void xsetStyleId(STString sTString);

    void xsetType(STStyleType sTStyleType);

    static {
        DocumentFactory<CTStyle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstyle41c1type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
