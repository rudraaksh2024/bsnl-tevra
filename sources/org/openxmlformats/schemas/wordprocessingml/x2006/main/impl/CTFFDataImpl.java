package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import com.google.firebase.messaging.Constants;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFCheckBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFDDList;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFHelpText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFName;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFStatusText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFTextInput;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMacroName;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnsignedDecimalNumber;

public class CTFFDataImpl extends XmlComplexContentImpl implements CTFFData {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "name"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", Constants.ScionAnalytics.PARAM_LABEL), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tabIndex"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "enabled"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "calcOnExit"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "entryMacro"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "exitMacro"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "helpText"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "statusText"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "checkBox"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ddList"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textInput")};
    private static final long serialVersionUID = 1;

    public CTFFDataImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTFFName> getNameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda50(this), new CTFFDataImpl$$ExternalSyntheticLambda51(this), new CTFFDataImpl$$ExternalSyntheticLambda52(this), new CTFFDataImpl$$ExternalSyntheticLambda53(this), new CTFFDataImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public CTFFName[] getNameArray() {
        return (CTFFName[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTFFName[0]);
    }

    public CTFFName getNameArray(int i) {
        CTFFName cTFFName;
        synchronized (monitor()) {
            check_orphaned();
            cTFFName = (CTFFName) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTFFName == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFFName;
    }

    public int sizeOfNameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setNameArray(CTFFName[] cTFFNameArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFFNameArr, PROPERTY_QNAME[0]);
    }

    public void setNameArray(int i, CTFFName cTFFName) {
        generatedSetterHelperImpl(cTFFName, PROPERTY_QNAME[0], i, 2);
    }

    public CTFFName insertNewName(int i) {
        CTFFName cTFFName;
        synchronized (monitor()) {
            check_orphaned();
            cTFFName = (CTFFName) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTFFName;
    }

    public CTFFName addNewName() {
        CTFFName cTFFName;
        synchronized (monitor()) {
            check_orphaned();
            cTFFName = (CTFFName) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTFFName;
    }

    public void removeName(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTDecimalNumber> getLabelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda39(this), new CTFFDataImpl$$ExternalSyntheticLambda40(this), new CTFFDataImpl$$ExternalSyntheticLambda41(this), new CTFFDataImpl$$ExternalSyntheticLambda42(this), new CTFFDataImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTDecimalNumber[] getLabelArray() {
        return (CTDecimalNumber[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTDecimalNumber[0]);
    }

    public CTDecimalNumber getLabelArray(int i) {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTDecimalNumber == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDecimalNumber;
    }

    public int sizeOfLabelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setLabelArray(CTDecimalNumber[] cTDecimalNumberArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDecimalNumberArr, PROPERTY_QNAME[1]);
    }

    public void setLabelArray(int i, CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[1], i, 2);
    }

    public CTDecimalNumber insertNewLabel(int i) {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTDecimalNumber;
    }

    public CTDecimalNumber addNewLabel() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTDecimalNumber;
    }

    public void removeLabel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTUnsignedDecimalNumber> getTabIndexList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda17(this), new CTFFDataImpl$$ExternalSyntheticLambda18(this), new CTFFDataImpl$$ExternalSyntheticLambda19(this), new CTFFDataImpl$$ExternalSyntheticLambda20(this), new CTFFDataImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTUnsignedDecimalNumber[] getTabIndexArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTUnsignedDecimalNumber[0]);
    }

    public CTUnsignedDecimalNumber getTabIndexArray(int i) {
        CTUnsignedDecimalNumber find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfTabIndexArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setTabIndexArray(CTUnsignedDecimalNumber[] cTUnsignedDecimalNumberArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTUnsignedDecimalNumberArr, PROPERTY_QNAME[2]);
    }

    public void setTabIndexArray(int i, CTUnsignedDecimalNumber cTUnsignedDecimalNumber) {
        generatedSetterHelperImpl(cTUnsignedDecimalNumber, PROPERTY_QNAME[2], i, 2);
    }

    public CTUnsignedDecimalNumber insertNewTabIndex(int i) {
        CTUnsignedDecimalNumber insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return insert_element_user;
    }

    public CTUnsignedDecimalNumber addNewTabIndex() {
        CTUnsignedDecimalNumber add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void removeTabIndex(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTOnOff> getEnabledList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda0(this), new CTFFDataImpl$$ExternalSyntheticLambda11(this), new CTFFDataImpl$$ExternalSyntheticLambda22(this), new CTFFDataImpl$$ExternalSyntheticLambda33(this), new CTFFDataImpl$$ExternalSyntheticLambda44(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getEnabledArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getEnabledArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfEnabledArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setEnabledArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[3]);
    }

    public void setEnabledArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[3], i, 2);
    }

    public CTOnOff insertNewEnabled(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewEnabled() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTOnOff;
    }

    public void removeEnabled(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTOnOff> getCalcOnExitList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda6(this), new CTFFDataImpl$$ExternalSyntheticLambda7(this), new CTFFDataImpl$$ExternalSyntheticLambda8(this), new CTFFDataImpl$$ExternalSyntheticLambda9(this), new CTFFDataImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getCalcOnExitArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getCalcOnExitArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfCalcOnExitArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setCalcOnExitArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[4]);
    }

    public void setCalcOnExitArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[4], i, 2);
    }

    public CTOnOff insertNewCalcOnExit(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewCalcOnExit() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTOnOff;
    }

    public void removeCalcOnExit(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTMacroName> getEntryMacroList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda1(this), new CTFFDataImpl$$ExternalSyntheticLambda2(this), new CTFFDataImpl$$ExternalSyntheticLambda3(this), new CTFFDataImpl$$ExternalSyntheticLambda4(this), new CTFFDataImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTMacroName[] getEntryMacroArray() {
        return getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTMacroName[0]);
    }

    public CTMacroName getEntryMacroArray(int i) {
        CTMacroName find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfEntryMacroArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setEntryMacroArray(CTMacroName[] cTMacroNameArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMacroNameArr, PROPERTY_QNAME[5]);
    }

    public void setEntryMacroArray(int i, CTMacroName cTMacroName) {
        generatedSetterHelperImpl(cTMacroName, PROPERTY_QNAME[5], i, 2);
    }

    public CTMacroName insertNewEntryMacro(int i) {
        CTMacroName insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return insert_element_user;
    }

    public CTMacroName addNewEntryMacro() {
        CTMacroName add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return add_element_user;
    }

    public void removeEntryMacro(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTMacroName> getExitMacroList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda55(this), new CTFFDataImpl$$ExternalSyntheticLambda56(this), new CTFFDataImpl$$ExternalSyntheticLambda57(this), new CTFFDataImpl$$ExternalSyntheticLambda58(this), new CTFFDataImpl$$ExternalSyntheticLambda59(this));
        }
        return javaListXmlObject;
    }

    public CTMacroName[] getExitMacroArray() {
        return getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTMacroName[0]);
    }

    public CTMacroName getExitMacroArray(int i) {
        CTMacroName find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfExitMacroArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setExitMacroArray(CTMacroName[] cTMacroNameArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMacroNameArr, PROPERTY_QNAME[6]);
    }

    public void setExitMacroArray(int i, CTMacroName cTMacroName) {
        generatedSetterHelperImpl(cTMacroName, PROPERTY_QNAME[6], i, 2);
    }

    public CTMacroName insertNewExitMacro(int i) {
        CTMacroName insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return insert_element_user;
    }

    public CTMacroName addNewExitMacro() {
        CTMacroName add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return add_element_user;
    }

    public void removeExitMacro(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTFFHelpText> getHelpTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda34(this), new CTFFDataImpl$$ExternalSyntheticLambda35(this), new CTFFDataImpl$$ExternalSyntheticLambda36(this), new CTFFDataImpl$$ExternalSyntheticLambda37(this), new CTFFDataImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTFFHelpText[] getHelpTextArray() {
        return getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTFFHelpText[0]);
    }

    public CTFFHelpText getHelpTextArray(int i) {
        CTFFHelpText find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfHelpTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setHelpTextArray(CTFFHelpText[] cTFFHelpTextArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFFHelpTextArr, PROPERTY_QNAME[7]);
    }

    public void setHelpTextArray(int i, CTFFHelpText cTFFHelpText) {
        generatedSetterHelperImpl(cTFFHelpText, PROPERTY_QNAME[7], i, 2);
    }

    public CTFFHelpText insertNewHelpText(int i) {
        CTFFHelpText insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return insert_element_user;
    }

    public CTFFHelpText addNewHelpText() {
        CTFFHelpText add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return add_element_user;
    }

    public void removeHelpText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTFFStatusText> getStatusTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda45(this), new CTFFDataImpl$$ExternalSyntheticLambda46(this), new CTFFDataImpl$$ExternalSyntheticLambda47(this), new CTFFDataImpl$$ExternalSyntheticLambda48(this), new CTFFDataImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CTFFStatusText[] getStatusTextArray() {
        return getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTFFStatusText[0]);
    }

    public CTFFStatusText getStatusTextArray(int i) {
        CTFFStatusText find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfStatusTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setStatusTextArray(CTFFStatusText[] cTFFStatusTextArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFFStatusTextArr, PROPERTY_QNAME[8]);
    }

    public void setStatusTextArray(int i, CTFFStatusText cTFFStatusText) {
        generatedSetterHelperImpl(cTFFStatusText, PROPERTY_QNAME[8], i, 2);
    }

    public CTFFStatusText insertNewStatusText(int i) {
        CTFFStatusText insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return insert_element_user;
    }

    public CTFFStatusText addNewStatusText() {
        CTFFStatusText add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return add_element_user;
    }

    public void removeStatusText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTFFCheckBox> getCheckBoxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda28(this), new CTFFDataImpl$$ExternalSyntheticLambda29(this), new CTFFDataImpl$$ExternalSyntheticLambda30(this), new CTFFDataImpl$$ExternalSyntheticLambda31(this), new CTFFDataImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTFFCheckBox[] getCheckBoxArray() {
        return (CTFFCheckBox[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTFFCheckBox[0]);
    }

    public CTFFCheckBox getCheckBoxArray(int i) {
        CTFFCheckBox cTFFCheckBox;
        synchronized (monitor()) {
            check_orphaned();
            cTFFCheckBox = (CTFFCheckBox) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (cTFFCheckBox == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFFCheckBox;
    }

    public int sizeOfCheckBoxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setCheckBoxArray(CTFFCheckBox[] cTFFCheckBoxArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFFCheckBoxArr, PROPERTY_QNAME[9]);
    }

    public void setCheckBoxArray(int i, CTFFCheckBox cTFFCheckBox) {
        generatedSetterHelperImpl(cTFFCheckBox, PROPERTY_QNAME[9], i, 2);
    }

    public CTFFCheckBox insertNewCheckBox(int i) {
        CTFFCheckBox cTFFCheckBox;
        synchronized (monitor()) {
            check_orphaned();
            cTFFCheckBox = (CTFFCheckBox) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return cTFFCheckBox;
    }

    public CTFFCheckBox addNewCheckBox() {
        CTFFCheckBox cTFFCheckBox;
        synchronized (monitor()) {
            check_orphaned();
            cTFFCheckBox = (CTFFCheckBox) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTFFCheckBox;
    }

    public void removeCheckBox(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTFFDDList> getDdListList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda12(this), new CTFFDataImpl$$ExternalSyntheticLambda13(this), new CTFFDataImpl$$ExternalSyntheticLambda14(this), new CTFFDataImpl$$ExternalSyntheticLambda15(this), new CTFFDataImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTFFDDList[] getDdListArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTFFDDList[0]);
    }

    public CTFFDDList getDdListArray(int i) {
        CTFFDDList find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfDdListArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setDdListArray(CTFFDDList[] cTFFDDListArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFFDDListArr, PROPERTY_QNAME[10]);
    }

    public void setDdListArray(int i, CTFFDDList cTFFDDList) {
        generatedSetterHelperImpl(cTFFDDList, PROPERTY_QNAME[10], i, 2);
    }

    public CTFFDDList insertNewDdList(int i) {
        CTFFDDList insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return insert_element_user;
    }

    public CTFFDDList addNewDdList() {
        CTFFDDList add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return add_element_user;
    }

    public void removeDdList(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTFFTextInput> getTextInputList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFFDataImpl$$ExternalSyntheticLambda23(this), new CTFFDataImpl$$ExternalSyntheticLambda24(this), new CTFFDataImpl$$ExternalSyntheticLambda25(this), new CTFFDataImpl$$ExternalSyntheticLambda26(this), new CTFFDataImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTFFTextInput[] getTextInputArray() {
        return (CTFFTextInput[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTFFTextInput[0]);
    }

    public CTFFTextInput getTextInputArray(int i) {
        CTFFTextInput cTFFTextInput;
        synchronized (monitor()) {
            check_orphaned();
            cTFFTextInput = (CTFFTextInput) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (cTFFTextInput == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFFTextInput;
    }

    public int sizeOfTextInputArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setTextInputArray(CTFFTextInput[] cTFFTextInputArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFFTextInputArr, PROPERTY_QNAME[11]);
    }

    public void setTextInputArray(int i, CTFFTextInput cTFFTextInput) {
        generatedSetterHelperImpl(cTFFTextInput, PROPERTY_QNAME[11], i, 2);
    }

    public CTFFTextInput insertNewTextInput(int i) {
        CTFFTextInput cTFFTextInput;
        synchronized (monitor()) {
            check_orphaned();
            cTFFTextInput = (CTFFTextInput) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTFFTextInput;
    }

    public CTFFTextInput addNewTextInput() {
        CTFFTextInput cTFFTextInput;
        synchronized (monitor()) {
            check_orphaned();
            cTFFTextInput = (CTFFTextInput) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTFFTextInput;
    }

    public void removeTextInput(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }
}
