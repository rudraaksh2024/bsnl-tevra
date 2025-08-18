package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTConditionalFormatting extends XmlObject {
    public static final DocumentFactory<CTConditionalFormatting> Factory;
    public static final SchemaType type;

    CTCfRule addNewCfRule();

    CTExtensionList addNewExtLst();

    CTCfRule getCfRuleArray(int i);

    CTCfRule[] getCfRuleArray();

    List<CTCfRule> getCfRuleList();

    CTExtensionList getExtLst();

    boolean getPivot();

    List getSqref();

    CTCfRule insertNewCfRule(int i);

    boolean isSetExtLst();

    boolean isSetPivot();

    boolean isSetSqref();

    void removeCfRule(int i);

    void setCfRuleArray(int i, CTCfRule cTCfRule);

    void setCfRuleArray(CTCfRule[] cTCfRuleArr);

    void setExtLst(CTExtensionList cTExtensionList);

    void setPivot(boolean z);

    void setSqref(List list);

    int sizeOfCfRuleArray();

    void unsetExtLst();

    void unsetPivot();

    void unsetSqref();

    XmlBoolean xgetPivot();

    STSqref xgetSqref();

    void xsetPivot(XmlBoolean xmlBoolean);

    void xsetSqref(STSqref sTSqref);

    static {
        DocumentFactory<CTConditionalFormatting> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctconditionalformatting0deatype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
