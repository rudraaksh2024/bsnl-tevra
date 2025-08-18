package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSdtPr extends XmlObject {
    public static final DocumentFactory<CTSdtPr> Factory;
    public static final SchemaType type;

    CTString addNewAlias();

    CTEmpty addNewBibliography();

    CTEmpty addNewCitation();

    CTSdtComboBox addNewComboBox();

    CTDataBinding addNewDataBinding();

    CTSdtDate addNewDate();

    CTSdtDocPart addNewDocPartList();

    CTSdtDocPart addNewDocPartObj();

    CTSdtDropDownList addNewDropDownList();

    CTEmpty addNewEquation();

    CTEmpty addNewGroup();

    CTDecimalNumber addNewId();

    CTDecimalNumber addNewLabel();

    CTLock addNewLock();

    CTEmpty addNewPicture();

    CTPlaceholder addNewPlaceholder();

    CTRPr addNewRPr();

    CTEmpty addNewRichText();

    CTOnOff addNewShowingPlcHdr();

    CTUnsignedDecimalNumber addNewTabIndex();

    CTString addNewTag();

    CTOnOff addNewTemporary();

    CTSdtText addNewText();

    CTString getAlias();

    CTEmpty getBibliography();

    CTEmpty getCitation();

    CTSdtComboBox getComboBox();

    CTDataBinding getDataBinding();

    CTSdtDate getDate();

    CTSdtDocPart getDocPartList();

    CTSdtDocPart getDocPartObj();

    CTSdtDropDownList getDropDownList();

    CTEmpty getEquation();

    CTEmpty getGroup();

    CTDecimalNumber getId();

    CTDecimalNumber getLabel();

    CTLock getLock();

    CTEmpty getPicture();

    CTPlaceholder getPlaceholder();

    CTRPr getRPr();

    CTEmpty getRichText();

    CTOnOff getShowingPlcHdr();

    CTUnsignedDecimalNumber getTabIndex();

    CTString getTag();

    CTOnOff getTemporary();

    CTSdtText getText();

    boolean isSetAlias();

    boolean isSetBibliography();

    boolean isSetCitation();

    boolean isSetComboBox();

    boolean isSetDataBinding();

    boolean isSetDate();

    boolean isSetDocPartList();

    boolean isSetDocPartObj();

    boolean isSetDropDownList();

    boolean isSetEquation();

    boolean isSetGroup();

    boolean isSetId();

    boolean isSetLabel();

    boolean isSetLock();

    boolean isSetPicture();

    boolean isSetPlaceholder();

    boolean isSetRPr();

    boolean isSetRichText();

    boolean isSetShowingPlcHdr();

    boolean isSetTabIndex();

    boolean isSetTag();

    boolean isSetTemporary();

    boolean isSetText();

    void setAlias(CTString cTString);

    void setBibliography(CTEmpty cTEmpty);

    void setCitation(CTEmpty cTEmpty);

    void setComboBox(CTSdtComboBox cTSdtComboBox);

    void setDataBinding(CTDataBinding cTDataBinding);

    void setDate(CTSdtDate cTSdtDate);

    void setDocPartList(CTSdtDocPart cTSdtDocPart);

    void setDocPartObj(CTSdtDocPart cTSdtDocPart);

    void setDropDownList(CTSdtDropDownList cTSdtDropDownList);

    void setEquation(CTEmpty cTEmpty);

    void setGroup(CTEmpty cTEmpty);

    void setId(CTDecimalNumber cTDecimalNumber);

    void setLabel(CTDecimalNumber cTDecimalNumber);

    void setLock(CTLock cTLock);

    void setPicture(CTEmpty cTEmpty);

    void setPlaceholder(CTPlaceholder cTPlaceholder);

    void setRPr(CTRPr cTRPr);

    void setRichText(CTEmpty cTEmpty);

    void setShowingPlcHdr(CTOnOff cTOnOff);

    void setTabIndex(CTUnsignedDecimalNumber cTUnsignedDecimalNumber);

    void setTag(CTString cTString);

    void setTemporary(CTOnOff cTOnOff);

    void setText(CTSdtText cTSdtText);

    void unsetAlias();

    void unsetBibliography();

    void unsetCitation();

    void unsetComboBox();

    void unsetDataBinding();

    void unsetDate();

    void unsetDocPartList();

    void unsetDocPartObj();

    void unsetDropDownList();

    void unsetEquation();

    void unsetGroup();

    void unsetId();

    void unsetLabel();

    void unsetLock();

    void unsetPicture();

    void unsetPlaceholder();

    void unsetRPr();

    void unsetRichText();

    void unsetShowingPlcHdr();

    void unsetTabIndex();

    void unsetTag();

    void unsetTemporary();

    void unsetText();

    static {
        DocumentFactory<CTSdtPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsdtpre24dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
