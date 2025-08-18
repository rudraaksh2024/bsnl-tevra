package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTDefinedName extends STFormula {
    public static final DocumentFactory<CTDefinedName> Factory;
    public static final SchemaType type;

    String getComment();

    String getCustomMenu();

    String getDescription();

    boolean getFunction();

    long getFunctionGroupId();

    String getHelp();

    boolean getHidden();

    long getLocalSheetId();

    String getName();

    boolean getPublishToServer();

    String getShortcutKey();

    String getStatusBar();

    boolean getVbProcedure();

    boolean getWorkbookParameter();

    boolean getXlm();

    boolean isSetComment();

    boolean isSetCustomMenu();

    boolean isSetDescription();

    boolean isSetFunction();

    boolean isSetFunctionGroupId();

    boolean isSetHelp();

    boolean isSetHidden();

    boolean isSetLocalSheetId();

    boolean isSetPublishToServer();

    boolean isSetShortcutKey();

    boolean isSetStatusBar();

    boolean isSetVbProcedure();

    boolean isSetWorkbookParameter();

    boolean isSetXlm();

    void setComment(String str);

    void setCustomMenu(String str);

    void setDescription(String str);

    void setFunction(boolean z);

    void setFunctionGroupId(long j);

    void setHelp(String str);

    void setHidden(boolean z);

    void setLocalSheetId(long j);

    void setName(String str);

    void setPublishToServer(boolean z);

    void setShortcutKey(String str);

    void setStatusBar(String str);

    void setVbProcedure(boolean z);

    void setWorkbookParameter(boolean z);

    void setXlm(boolean z);

    void unsetComment();

    void unsetCustomMenu();

    void unsetDescription();

    void unsetFunction();

    void unsetFunctionGroupId();

    void unsetHelp();

    void unsetHidden();

    void unsetLocalSheetId();

    void unsetPublishToServer();

    void unsetShortcutKey();

    void unsetStatusBar();

    void unsetVbProcedure();

    void unsetWorkbookParameter();

    void unsetXlm();

    STXstring xgetComment();

    STXstring xgetCustomMenu();

    STXstring xgetDescription();

    XmlBoolean xgetFunction();

    XmlUnsignedInt xgetFunctionGroupId();

    STXstring xgetHelp();

    XmlBoolean xgetHidden();

    XmlUnsignedInt xgetLocalSheetId();

    STXstring xgetName();

    XmlBoolean xgetPublishToServer();

    STXstring xgetShortcutKey();

    STXstring xgetStatusBar();

    XmlBoolean xgetVbProcedure();

    XmlBoolean xgetWorkbookParameter();

    XmlBoolean xgetXlm();

    void xsetComment(STXstring sTXstring);

    void xsetCustomMenu(STXstring sTXstring);

    void xsetDescription(STXstring sTXstring);

    void xsetFunction(XmlBoolean xmlBoolean);

    void xsetFunctionGroupId(XmlUnsignedInt xmlUnsignedInt);

    void xsetHelp(STXstring sTXstring);

    void xsetHidden(XmlBoolean xmlBoolean);

    void xsetLocalSheetId(XmlUnsignedInt xmlUnsignedInt);

    void xsetName(STXstring sTXstring);

    void xsetPublishToServer(XmlBoolean xmlBoolean);

    void xsetShortcutKey(STXstring sTXstring);

    void xsetStatusBar(STXstring sTXstring);

    void xsetVbProcedure(XmlBoolean xmlBoolean);

    void xsetWorkbookParameter(XmlBoolean xmlBoolean);

    void xsetXlm(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTDefinedName> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdefinedname9413type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
