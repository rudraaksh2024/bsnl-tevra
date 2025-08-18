package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import com.google.firebase.messaging.Constants;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPlaceholder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtComboBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDocPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDropDownList;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnsignedDecimalNumber;

public class CTSdtPrImpl extends XmlComplexContentImpl implements CTSdtPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "alias"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tag"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "id"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lock"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "placeholder"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "temporary"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "showingPlcHdr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dataBinding"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", Constants.ScionAnalytics.PARAM_LABEL), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tabIndex"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "equation"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "comboBox"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", XmlErrorCodes.DATE), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docPartObj"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docPartList"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dropDownList"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "picture"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "richText"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "text"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "citation"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "group"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bibliography")};
    private static final long serialVersionUID = 1;

    public CTSdtPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTRPr getRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRPr = (CTRPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTRPr == null) {
                cTRPr = null;
            }
        }
        return cTRPr;
    }

    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setRPr(CTRPr cTRPr) {
        generatedSetterHelperImpl(cTRPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTRPr addNewRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRPr = (CTRPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRPr;
    }

    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTString getAlias() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTString == null) {
                cTString = null;
            }
        }
        return cTString;
    }

    public boolean isSetAlias() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setAlias(CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[1], 0, 1);
    }

    public CTString addNewAlias() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTString;
    }

    public void unsetAlias() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTString getTag() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTString == null) {
                cTString = null;
            }
        }
        return cTString;
    }

    public boolean isSetTag() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setTag(CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[2], 0, 1);
    }

    public CTString addNewTag() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTString;
    }

    public void unsetTag() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTDecimalNumber getId() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTDecimalNumber == null) {
                cTDecimalNumber = null;
            }
        }
        return cTDecimalNumber;
    }

    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setId(CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[3], 0, 1);
    }

    public CTDecimalNumber addNewId() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTDecimalNumber;
    }

    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTLock getLock() {
        CTLock cTLock;
        synchronized (monitor()) {
            check_orphaned();
            cTLock = (CTLock) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTLock == null) {
                cTLock = null;
            }
        }
        return cTLock;
    }

    public boolean isSetLock() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setLock(CTLock cTLock) {
        generatedSetterHelperImpl(cTLock, PROPERTY_QNAME[4], 0, 1);
    }

    public CTLock addNewLock() {
        CTLock cTLock;
        synchronized (monitor()) {
            check_orphaned();
            cTLock = (CTLock) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTLock;
    }

    public void unsetLock() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTPlaceholder getPlaceholder() {
        CTPlaceholder cTPlaceholder;
        synchronized (monitor()) {
            check_orphaned();
            cTPlaceholder = (CTPlaceholder) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTPlaceholder == null) {
                cTPlaceholder = null;
            }
        }
        return cTPlaceholder;
    }

    public boolean isSetPlaceholder() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setPlaceholder(CTPlaceholder cTPlaceholder) {
        generatedSetterHelperImpl(cTPlaceholder, PROPERTY_QNAME[5], 0, 1);
    }

    public CTPlaceholder addNewPlaceholder() {
        CTPlaceholder cTPlaceholder;
        synchronized (monitor()) {
            check_orphaned();
            cTPlaceholder = (CTPlaceholder) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTPlaceholder;
    }

    public void unsetPlaceholder() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTOnOff getTemporary() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetTemporary() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setTemporary(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[6], 0, 1);
    }

    public CTOnOff addNewTemporary() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTOnOff;
    }

    public void unsetTemporary() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTOnOff getShowingPlcHdr() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetShowingPlcHdr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setShowingPlcHdr(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[7], 0, 1);
    }

    public CTOnOff addNewShowingPlcHdr() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTOnOff;
    }

    public void unsetShowingPlcHdr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTDataBinding getDataBinding() {
        CTDataBinding cTDataBinding;
        synchronized (monitor()) {
            check_orphaned();
            cTDataBinding = (CTDataBinding) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTDataBinding == null) {
                cTDataBinding = null;
            }
        }
        return cTDataBinding;
    }

    public boolean isSetDataBinding() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setDataBinding(CTDataBinding cTDataBinding) {
        generatedSetterHelperImpl(cTDataBinding, PROPERTY_QNAME[8], 0, 1);
    }

    public CTDataBinding addNewDataBinding() {
        CTDataBinding cTDataBinding;
        synchronized (monitor()) {
            check_orphaned();
            cTDataBinding = (CTDataBinding) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTDataBinding;
    }

    public void unsetDataBinding() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTDecimalNumber getLabel() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTDecimalNumber == null) {
                cTDecimalNumber = null;
            }
        }
        return cTDecimalNumber;
    }

    public boolean isSetLabel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setLabel(CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[9], 0, 1);
    }

    public CTDecimalNumber addNewLabel() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTDecimalNumber;
    }

    public void unsetLabel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTUnsignedDecimalNumber getTabIndex() {
        CTUnsignedDecimalNumber find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetTabIndex() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setTabIndex(CTUnsignedDecimalNumber cTUnsignedDecimalNumber) {
        generatedSetterHelperImpl(cTUnsignedDecimalNumber, PROPERTY_QNAME[10], 0, 1);
    }

    public CTUnsignedDecimalNumber addNewTabIndex() {
        CTUnsignedDecimalNumber add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return add_element_user;
    }

    public void unsetTabIndex() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTEmpty getEquation() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTEmpty == null) {
                cTEmpty = null;
            }
        }
        return cTEmpty;
    }

    public boolean isSetEquation() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setEquation(CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[11], 0, 1);
    }

    public CTEmpty addNewEquation() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTEmpty;
    }

    public void unsetEquation() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTSdtComboBox getComboBox() {
        CTSdtComboBox cTSdtComboBox;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtComboBox = (CTSdtComboBox) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTSdtComboBox == null) {
                cTSdtComboBox = null;
            }
        }
        return cTSdtComboBox;
    }

    public boolean isSetComboBox() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setComboBox(CTSdtComboBox cTSdtComboBox) {
        generatedSetterHelperImpl(cTSdtComboBox, PROPERTY_QNAME[12], 0, 1);
    }

    public CTSdtComboBox addNewComboBox() {
        CTSdtComboBox cTSdtComboBox;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtComboBox = (CTSdtComboBox) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTSdtComboBox;
    }

    public void unsetComboBox() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTSdtDate getDate() {
        CTSdtDate cTSdtDate;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtDate = (CTSdtDate) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (cTSdtDate == null) {
                cTSdtDate = null;
            }
        }
        return cTSdtDate;
    }

    public boolean isSetDate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setDate(CTSdtDate cTSdtDate) {
        generatedSetterHelperImpl(cTSdtDate, PROPERTY_QNAME[13], 0, 1);
    }

    public CTSdtDate addNewDate() {
        CTSdtDate cTSdtDate;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtDate = (CTSdtDate) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTSdtDate;
    }

    public void unsetDate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    public CTSdtDocPart getDocPartObj() {
        CTSdtDocPart cTSdtDocPart;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtDocPart = (CTSdtDocPart) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (cTSdtDocPart == null) {
                cTSdtDocPart = null;
            }
        }
        return cTSdtDocPart;
    }

    public boolean isSetDocPartObj() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    public void setDocPartObj(CTSdtDocPart cTSdtDocPart) {
        generatedSetterHelperImpl(cTSdtDocPart, PROPERTY_QNAME[14], 0, 1);
    }

    public CTSdtDocPart addNewDocPartObj() {
        CTSdtDocPart cTSdtDocPart;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtDocPart = (CTSdtDocPart) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTSdtDocPart;
    }

    public void unsetDocPartObj() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    public CTSdtDocPart getDocPartList() {
        CTSdtDocPart cTSdtDocPart;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtDocPart = (CTSdtDocPart) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (cTSdtDocPart == null) {
                cTSdtDocPart = null;
            }
        }
        return cTSdtDocPart;
    }

    public boolean isSetDocPartList() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    public void setDocPartList(CTSdtDocPart cTSdtDocPart) {
        generatedSetterHelperImpl(cTSdtDocPart, PROPERTY_QNAME[15], 0, 1);
    }

    public CTSdtDocPart addNewDocPartList() {
        CTSdtDocPart cTSdtDocPart;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtDocPart = (CTSdtDocPart) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTSdtDocPart;
    }

    public void unsetDocPartList() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    public CTSdtDropDownList getDropDownList() {
        CTSdtDropDownList cTSdtDropDownList;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtDropDownList = (CTSdtDropDownList) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            if (cTSdtDropDownList == null) {
                cTSdtDropDownList = null;
            }
        }
        return cTSdtDropDownList;
    }

    public boolean isSetDropDownList() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    public void setDropDownList(CTSdtDropDownList cTSdtDropDownList) {
        generatedSetterHelperImpl(cTSdtDropDownList, PROPERTY_QNAME[16], 0, 1);
    }

    public CTSdtDropDownList addNewDropDownList() {
        CTSdtDropDownList cTSdtDropDownList;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtDropDownList = (CTSdtDropDownList) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTSdtDropDownList;
    }

    public void unsetDropDownList() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    public CTEmpty getPicture() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (cTEmpty == null) {
                cTEmpty = null;
            }
        }
        return cTEmpty;
    }

    public boolean isSetPicture() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    public void setPicture(CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[17], 0, 1);
    }

    public CTEmpty addNewPicture() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTEmpty;
    }

    public void unsetPicture() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    public CTEmpty getRichText() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (cTEmpty == null) {
                cTEmpty = null;
            }
        }
        return cTEmpty;
    }

    public boolean isSetRichText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    public void setRichText(CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[18], 0, 1);
    }

    public CTEmpty addNewRichText() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTEmpty;
    }

    public void unsetRichText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    public CTSdtText getText() {
        CTSdtText cTSdtText;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtText = (CTSdtText) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            if (cTSdtText == null) {
                cTSdtText = null;
            }
        }
        return cTSdtText;
    }

    public boolean isSetText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    public void setText(CTSdtText cTSdtText) {
        generatedSetterHelperImpl(cTSdtText, PROPERTY_QNAME[19], 0, 1);
    }

    public CTSdtText addNewText() {
        CTSdtText cTSdtText;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtText = (CTSdtText) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTSdtText;
    }

    public void unsetText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    public CTEmpty getCitation() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            if (cTEmpty == null) {
                cTEmpty = null;
            }
        }
        return cTEmpty;
    }

    public boolean isSetCitation() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    public void setCitation(CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[20], 0, 1);
    }

    public CTEmpty addNewCitation() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTEmpty;
    }

    public void unsetCitation() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    public CTEmpty getGroup() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            if (cTEmpty == null) {
                cTEmpty = null;
            }
        }
        return cTEmpty;
    }

    public boolean isSetGroup() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    public void setGroup(CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[21], 0, 1);
    }

    public CTEmpty addNewGroup() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return cTEmpty;
    }

    public void unsetGroup() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    public CTEmpty getBibliography() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            if (cTEmpty == null) {
                cTEmpty = null;
            }
        }
        return cTEmpty;
    }

    public boolean isSetBibliography() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    public void setBibliography(CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[22], 0, 1);
    }

    public CTEmpty addNewBibliography() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return cTEmpty;
    }

    public void unsetBibliography() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }
}
