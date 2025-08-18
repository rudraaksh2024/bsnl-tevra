package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTFFData extends XmlObject {
    public static final DocumentFactory<CTFFData> Factory;
    public static final SchemaType type;

    CTOnOff addNewCalcOnExit();

    CTFFCheckBox addNewCheckBox();

    CTFFDDList addNewDdList();

    CTOnOff addNewEnabled();

    CTMacroName addNewEntryMacro();

    CTMacroName addNewExitMacro();

    CTFFHelpText addNewHelpText();

    CTDecimalNumber addNewLabel();

    CTFFName addNewName();

    CTFFStatusText addNewStatusText();

    CTUnsignedDecimalNumber addNewTabIndex();

    CTFFTextInput addNewTextInput();

    CTOnOff getCalcOnExitArray(int i);

    CTOnOff[] getCalcOnExitArray();

    List<CTOnOff> getCalcOnExitList();

    CTFFCheckBox getCheckBoxArray(int i);

    CTFFCheckBox[] getCheckBoxArray();

    List<CTFFCheckBox> getCheckBoxList();

    CTFFDDList getDdListArray(int i);

    CTFFDDList[] getDdListArray();

    List<CTFFDDList> getDdListList();

    CTOnOff getEnabledArray(int i);

    CTOnOff[] getEnabledArray();

    List<CTOnOff> getEnabledList();

    CTMacroName getEntryMacroArray(int i);

    CTMacroName[] getEntryMacroArray();

    List<CTMacroName> getEntryMacroList();

    CTMacroName getExitMacroArray(int i);

    CTMacroName[] getExitMacroArray();

    List<CTMacroName> getExitMacroList();

    CTFFHelpText getHelpTextArray(int i);

    CTFFHelpText[] getHelpTextArray();

    List<CTFFHelpText> getHelpTextList();

    CTDecimalNumber getLabelArray(int i);

    CTDecimalNumber[] getLabelArray();

    List<CTDecimalNumber> getLabelList();

    CTFFName getNameArray(int i);

    CTFFName[] getNameArray();

    List<CTFFName> getNameList();

    CTFFStatusText getStatusTextArray(int i);

    CTFFStatusText[] getStatusTextArray();

    List<CTFFStatusText> getStatusTextList();

    CTUnsignedDecimalNumber getTabIndexArray(int i);

    CTUnsignedDecimalNumber[] getTabIndexArray();

    List<CTUnsignedDecimalNumber> getTabIndexList();

    CTFFTextInput getTextInputArray(int i);

    CTFFTextInput[] getTextInputArray();

    List<CTFFTextInput> getTextInputList();

    CTOnOff insertNewCalcOnExit(int i);

    CTFFCheckBox insertNewCheckBox(int i);

    CTFFDDList insertNewDdList(int i);

    CTOnOff insertNewEnabled(int i);

    CTMacroName insertNewEntryMacro(int i);

    CTMacroName insertNewExitMacro(int i);

    CTFFHelpText insertNewHelpText(int i);

    CTDecimalNumber insertNewLabel(int i);

    CTFFName insertNewName(int i);

    CTFFStatusText insertNewStatusText(int i);

    CTUnsignedDecimalNumber insertNewTabIndex(int i);

    CTFFTextInput insertNewTextInput(int i);

    void removeCalcOnExit(int i);

    void removeCheckBox(int i);

    void removeDdList(int i);

    void removeEnabled(int i);

    void removeEntryMacro(int i);

    void removeExitMacro(int i);

    void removeHelpText(int i);

    void removeLabel(int i);

    void removeName(int i);

    void removeStatusText(int i);

    void removeTabIndex(int i);

    void removeTextInput(int i);

    void setCalcOnExitArray(int i, CTOnOff cTOnOff);

    void setCalcOnExitArray(CTOnOff[] cTOnOffArr);

    void setCheckBoxArray(int i, CTFFCheckBox cTFFCheckBox);

    void setCheckBoxArray(CTFFCheckBox[] cTFFCheckBoxArr);

    void setDdListArray(int i, CTFFDDList cTFFDDList);

    void setDdListArray(CTFFDDList[] cTFFDDListArr);

    void setEnabledArray(int i, CTOnOff cTOnOff);

    void setEnabledArray(CTOnOff[] cTOnOffArr);

    void setEntryMacroArray(int i, CTMacroName cTMacroName);

    void setEntryMacroArray(CTMacroName[] cTMacroNameArr);

    void setExitMacroArray(int i, CTMacroName cTMacroName);

    void setExitMacroArray(CTMacroName[] cTMacroNameArr);

    void setHelpTextArray(int i, CTFFHelpText cTFFHelpText);

    void setHelpTextArray(CTFFHelpText[] cTFFHelpTextArr);

    void setLabelArray(int i, CTDecimalNumber cTDecimalNumber);

    void setLabelArray(CTDecimalNumber[] cTDecimalNumberArr);

    void setNameArray(int i, CTFFName cTFFName);

    void setNameArray(CTFFName[] cTFFNameArr);

    void setStatusTextArray(int i, CTFFStatusText cTFFStatusText);

    void setStatusTextArray(CTFFStatusText[] cTFFStatusTextArr);

    void setTabIndexArray(int i, CTUnsignedDecimalNumber cTUnsignedDecimalNumber);

    void setTabIndexArray(CTUnsignedDecimalNumber[] cTUnsignedDecimalNumberArr);

    void setTextInputArray(int i, CTFFTextInput cTFFTextInput);

    void setTextInputArray(CTFFTextInput[] cTFFTextInputArr);

    int sizeOfCalcOnExitArray();

    int sizeOfCheckBoxArray();

    int sizeOfDdListArray();

    int sizeOfEnabledArray();

    int sizeOfEntryMacroArray();

    int sizeOfExitMacroArray();

    int sizeOfHelpTextArray();

    int sizeOfLabelArray();

    int sizeOfNameArray();

    int sizeOfStatusTextArray();

    int sizeOfTabIndexArray();

    int sizeOfTextInputArray();

    static {
        DocumentFactory<CTFFData> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctffdataaa7etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
