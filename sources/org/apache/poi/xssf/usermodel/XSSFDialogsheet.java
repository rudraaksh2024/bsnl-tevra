package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Sheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public class XSSFDialogsheet extends XSSFSheet implements Sheet {
    protected CTDialogsheet dialogsheet = CTDialogsheet.Factory.newInstance();

    public XSSFRow createRow(int i) {
        return null;
    }

    public boolean getDialog() {
        return true;
    }

    /* access modifiers changed from: protected */
    public CTPageBreak getDialogColumnBreaks() {
        return null;
    }

    /* access modifiers changed from: protected */
    public CTPageBreak getDialogRowBreaks() {
        return null;
    }

    protected XSSFDialogsheet(XSSFSheet xSSFSheet) {
        super(xSSFSheet.getPackagePart());
        this.worksheet = CTWorksheet.Factory.newInstance();
    }

    /* access modifiers changed from: protected */
    public CTHeaderFooter getDialogHeaderFooter() {
        if (this.dialogsheet.getHeaderFooter() == null) {
            this.dialogsheet.setHeaderFooter(CTHeaderFooter.Factory.newInstance());
        }
        return this.dialogsheet.getHeaderFooter();
    }

    /* access modifiers changed from: protected */
    public CTSheetPr getDialogSheetPr() {
        if (this.dialogsheet.getSheetPr() == null) {
            this.dialogsheet.setSheetPr(CTSheetPr.Factory.newInstance());
        }
        return this.dialogsheet.getSheetPr();
    }

    /* access modifiers changed from: protected */
    public CTSheetFormatPr getDialogSheetFormatPr() {
        if (this.dialogsheet.getSheetFormatPr() == null) {
            this.dialogsheet.setSheetFormatPr(CTSheetFormatPr.Factory.newInstance());
        }
        return this.dialogsheet.getSheetFormatPr();
    }

    /* access modifiers changed from: protected */
    public CTPageMargins getDialogPageMargins() {
        if (this.dialogsheet.getPageMargins() == null) {
            this.dialogsheet.setPageMargins(CTPageMargins.Factory.newInstance());
        }
        return this.dialogsheet.getPageMargins();
    }

    /* access modifiers changed from: protected */
    public CTSheetViews getDialogSheetViews() {
        if (this.dialogsheet.getSheetViews() == null) {
            this.dialogsheet.setSheetViews(CTSheetViews.Factory.newInstance());
            this.dialogsheet.getSheetViews().addNewSheetView();
        }
        return this.dialogsheet.getSheetViews();
    }

    /* access modifiers changed from: protected */
    public CTPrintOptions getDialogPrintOptions() {
        if (this.dialogsheet.getPrintOptions() == null) {
            this.dialogsheet.setPrintOptions(CTPrintOptions.Factory.newInstance());
        }
        return this.dialogsheet.getPrintOptions();
    }

    /* access modifiers changed from: protected */
    public CTSheetProtection getDialogProtection() {
        if (this.dialogsheet.getSheetProtection() == null) {
            this.dialogsheet.setSheetProtection(CTSheetProtection.Factory.newInstance());
        }
        return this.dialogsheet.getSheetProtection();
    }
}
