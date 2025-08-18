package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTChartsheet extends XmlObject {
    public static final DocumentFactory<CTChartsheet> Factory;
    public static final SchemaType type;

    CTCustomChartsheetViews addNewCustomSheetViews();

    CTDrawing addNewDrawing();

    CTDrawingHF addNewDrawingHF();

    CTExtensionList addNewExtLst();

    CTHeaderFooter addNewHeaderFooter();

    CTLegacyDrawing addNewLegacyDrawing();

    CTLegacyDrawing addNewLegacyDrawingHF();

    CTPageMargins addNewPageMargins();

    CTCsPageSetup addNewPageSetup();

    CTSheetBackgroundPicture addNewPicture();

    CTChartsheetPr addNewSheetPr();

    CTChartsheetProtection addNewSheetProtection();

    CTChartsheetViews addNewSheetViews();

    CTWebPublishItems addNewWebPublishItems();

    CTCustomChartsheetViews getCustomSheetViews();

    CTDrawing getDrawing();

    CTDrawingHF getDrawingHF();

    CTExtensionList getExtLst();

    CTHeaderFooter getHeaderFooter();

    CTLegacyDrawing getLegacyDrawing();

    CTLegacyDrawing getLegacyDrawingHF();

    CTPageMargins getPageMargins();

    CTCsPageSetup getPageSetup();

    CTSheetBackgroundPicture getPicture();

    CTChartsheetPr getSheetPr();

    CTChartsheetProtection getSheetProtection();

    CTChartsheetViews getSheetViews();

    CTWebPublishItems getWebPublishItems();

    boolean isSetCustomSheetViews();

    boolean isSetDrawingHF();

    boolean isSetExtLst();

    boolean isSetHeaderFooter();

    boolean isSetLegacyDrawing();

    boolean isSetLegacyDrawingHF();

    boolean isSetPageMargins();

    boolean isSetPageSetup();

    boolean isSetPicture();

    boolean isSetSheetPr();

    boolean isSetSheetProtection();

    boolean isSetWebPublishItems();

    void setCustomSheetViews(CTCustomChartsheetViews cTCustomChartsheetViews);

    void setDrawing(CTDrawing cTDrawing);

    void setDrawingHF(CTDrawingHF cTDrawingHF);

    void setExtLst(CTExtensionList cTExtensionList);

    void setHeaderFooter(CTHeaderFooter cTHeaderFooter);

    void setLegacyDrawing(CTLegacyDrawing cTLegacyDrawing);

    void setLegacyDrawingHF(CTLegacyDrawing cTLegacyDrawing);

    void setPageMargins(CTPageMargins cTPageMargins);

    void setPageSetup(CTCsPageSetup cTCsPageSetup);

    void setPicture(CTSheetBackgroundPicture cTSheetBackgroundPicture);

    void setSheetPr(CTChartsheetPr cTChartsheetPr);

    void setSheetProtection(CTChartsheetProtection cTChartsheetProtection);

    void setSheetViews(CTChartsheetViews cTChartsheetViews);

    void setWebPublishItems(CTWebPublishItems cTWebPublishItems);

    void unsetCustomSheetViews();

    void unsetDrawingHF();

    void unsetExtLst();

    void unsetHeaderFooter();

    void unsetLegacyDrawing();

    void unsetLegacyDrawingHF();

    void unsetPageMargins();

    void unsetPageSetup();

    void unsetPicture();

    void unsetSheetPr();

    void unsetSheetProtection();

    void unsetWebPublishItems();

    static {
        DocumentFactory<CTChartsheet> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctchartsheetf68atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
