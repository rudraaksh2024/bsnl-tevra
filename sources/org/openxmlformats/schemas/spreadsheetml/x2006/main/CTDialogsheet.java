package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDialogsheet extends XmlObject {
    public static final DocumentFactory<CTDialogsheet> Factory;
    public static final SchemaType type;

    CTControls addNewControls();

    CTCustomSheetViews addNewCustomSheetViews();

    CTDrawing addNewDrawing();

    CTDrawingHF addNewDrawingHF();

    CTExtensionList addNewExtLst();

    CTHeaderFooter addNewHeaderFooter();

    CTLegacyDrawing addNewLegacyDrawing();

    CTLegacyDrawing addNewLegacyDrawingHF();

    CTOleObjects addNewOleObjects();

    CTPageMargins addNewPageMargins();

    CTPageSetup addNewPageSetup();

    CTPrintOptions addNewPrintOptions();

    CTSheetFormatPr addNewSheetFormatPr();

    CTSheetPr addNewSheetPr();

    CTSheetProtection addNewSheetProtection();

    CTSheetViews addNewSheetViews();

    CTControls getControls();

    CTCustomSheetViews getCustomSheetViews();

    CTDrawing getDrawing();

    CTDrawingHF getDrawingHF();

    CTExtensionList getExtLst();

    CTHeaderFooter getHeaderFooter();

    CTLegacyDrawing getLegacyDrawing();

    CTLegacyDrawing getLegacyDrawingHF();

    CTOleObjects getOleObjects();

    CTPageMargins getPageMargins();

    CTPageSetup getPageSetup();

    CTPrintOptions getPrintOptions();

    CTSheetFormatPr getSheetFormatPr();

    CTSheetPr getSheetPr();

    CTSheetProtection getSheetProtection();

    CTSheetViews getSheetViews();

    boolean isSetControls();

    boolean isSetCustomSheetViews();

    boolean isSetDrawing();

    boolean isSetDrawingHF();

    boolean isSetExtLst();

    boolean isSetHeaderFooter();

    boolean isSetLegacyDrawing();

    boolean isSetLegacyDrawingHF();

    boolean isSetOleObjects();

    boolean isSetPageMargins();

    boolean isSetPageSetup();

    boolean isSetPrintOptions();

    boolean isSetSheetFormatPr();

    boolean isSetSheetPr();

    boolean isSetSheetProtection();

    boolean isSetSheetViews();

    void setControls(CTControls cTControls);

    void setCustomSheetViews(CTCustomSheetViews cTCustomSheetViews);

    void setDrawing(CTDrawing cTDrawing);

    void setDrawingHF(CTDrawingHF cTDrawingHF);

    void setExtLst(CTExtensionList cTExtensionList);

    void setHeaderFooter(CTHeaderFooter cTHeaderFooter);

    void setLegacyDrawing(CTLegacyDrawing cTLegacyDrawing);

    void setLegacyDrawingHF(CTLegacyDrawing cTLegacyDrawing);

    void setOleObjects(CTOleObjects cTOleObjects);

    void setPageMargins(CTPageMargins cTPageMargins);

    void setPageSetup(CTPageSetup cTPageSetup);

    void setPrintOptions(CTPrintOptions cTPrintOptions);

    void setSheetFormatPr(CTSheetFormatPr cTSheetFormatPr);

    void setSheetPr(CTSheetPr cTSheetPr);

    void setSheetProtection(CTSheetProtection cTSheetProtection);

    void setSheetViews(CTSheetViews cTSheetViews);

    void unsetControls();

    void unsetCustomSheetViews();

    void unsetDrawing();

    void unsetDrawingHF();

    void unsetExtLst();

    void unsetHeaderFooter();

    void unsetLegacyDrawing();

    void unsetLegacyDrawingHF();

    void unsetOleObjects();

    void unsetPageMargins();

    void unsetPageSetup();

    void unsetPrintOptions();

    void unsetSheetFormatPr();

    void unsetSheetPr();

    void unsetSheetProtection();

    void unsetSheetViews();

    static {
        DocumentFactory<CTDialogsheet> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdialogsheet6f36type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
