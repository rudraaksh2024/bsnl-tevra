package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTSheetPr extends XmlObject {
    public static final DocumentFactory<CTSheetPr> Factory;
    public static final SchemaType type;

    CTOutlinePr addNewOutlinePr();

    CTPageSetUpPr addNewPageSetUpPr();

    CTColor addNewTabColor();

    String getCodeName();

    boolean getEnableFormatConditionsCalculation();

    boolean getFilterMode();

    CTOutlinePr getOutlinePr();

    CTPageSetUpPr getPageSetUpPr();

    boolean getPublished();

    boolean getSyncHorizontal();

    String getSyncRef();

    boolean getSyncVertical();

    CTColor getTabColor();

    boolean getTransitionEntry();

    boolean getTransitionEvaluation();

    boolean isSetCodeName();

    boolean isSetEnableFormatConditionsCalculation();

    boolean isSetFilterMode();

    boolean isSetOutlinePr();

    boolean isSetPageSetUpPr();

    boolean isSetPublished();

    boolean isSetSyncHorizontal();

    boolean isSetSyncRef();

    boolean isSetSyncVertical();

    boolean isSetTabColor();

    boolean isSetTransitionEntry();

    boolean isSetTransitionEvaluation();

    void setCodeName(String str);

    void setEnableFormatConditionsCalculation(boolean z);

    void setFilterMode(boolean z);

    void setOutlinePr(CTOutlinePr cTOutlinePr);

    void setPageSetUpPr(CTPageSetUpPr cTPageSetUpPr);

    void setPublished(boolean z);

    void setSyncHorizontal(boolean z);

    void setSyncRef(String str);

    void setSyncVertical(boolean z);

    void setTabColor(CTColor cTColor);

    void setTransitionEntry(boolean z);

    void setTransitionEvaluation(boolean z);

    void unsetCodeName();

    void unsetEnableFormatConditionsCalculation();

    void unsetFilterMode();

    void unsetOutlinePr();

    void unsetPageSetUpPr();

    void unsetPublished();

    void unsetSyncHorizontal();

    void unsetSyncRef();

    void unsetSyncVertical();

    void unsetTabColor();

    void unsetTransitionEntry();

    void unsetTransitionEvaluation();

    XmlString xgetCodeName();

    XmlBoolean xgetEnableFormatConditionsCalculation();

    XmlBoolean xgetFilterMode();

    XmlBoolean xgetPublished();

    XmlBoolean xgetSyncHorizontal();

    STRef xgetSyncRef();

    XmlBoolean xgetSyncVertical();

    XmlBoolean xgetTransitionEntry();

    XmlBoolean xgetTransitionEvaluation();

    void xsetCodeName(XmlString xmlString);

    void xsetEnableFormatConditionsCalculation(XmlBoolean xmlBoolean);

    void xsetFilterMode(XmlBoolean xmlBoolean);

    void xsetPublished(XmlBoolean xmlBoolean);

    void xsetSyncHorizontal(XmlBoolean xmlBoolean);

    void xsetSyncRef(STRef sTRef);

    void xsetSyncVertical(XmlBoolean xmlBoolean);

    void xsetTransitionEntry(XmlBoolean xmlBoolean);

    void xsetTransitionEvaluation(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTSheetPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsheetpr3ae0type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
