package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.PageOrder;
import org.apache.poi.ss.usermodel.PaperSize;
import org.apache.poi.ss.usermodel.PrintCellComments;
import org.apache.poi.ss.usermodel.PrintOrientation;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STOrientation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPageOrder;

public class XSSFPrintSetup implements PrintSetup {
    private CTWorksheet ctWorksheet;
    private CTPageMargins pageMargins;
    private CTPageSetup pageSetup;

    protected XSSFPrintSetup(CTWorksheet cTWorksheet) {
        this.ctWorksheet = cTWorksheet;
        if (cTWorksheet.isSetPageSetup()) {
            this.pageSetup = this.ctWorksheet.getPageSetup();
        } else {
            this.pageSetup = this.ctWorksheet.addNewPageSetup();
        }
        if (this.ctWorksheet.isSetPageMargins()) {
            this.pageMargins = this.ctWorksheet.getPageMargins();
        } else {
            this.pageMargins = this.ctWorksheet.addNewPageMargins();
        }
    }

    public void setPaperSize(short s) {
        this.pageSetup.setPaperSize((long) s);
    }

    public void setPaperSize(PaperSize paperSize) {
        setPaperSize((short) (paperSize.ordinal() + 1));
    }

    public void setScale(short s) {
        if (s < 10 || s > 400) {
            throw new POIXMLException("Scale value not accepted: you must choose a value between 10 and 400.");
        }
        this.pageSetup.setScale((long) s);
    }

    public void setPageStart(short s) {
        this.pageSetup.setFirstPageNumber((long) s);
    }

    public void setFitWidth(short s) {
        this.pageSetup.setFitToWidth((long) s);
    }

    public void setFitHeight(short s) {
        this.pageSetup.setFitToHeight((long) s);
    }

    public void setLeftToRight(boolean z) {
        if (z) {
            setPageOrder(PageOrder.OVER_THEN_DOWN);
        } else {
            setPageOrder(PageOrder.DOWN_THEN_OVER);
        }
    }

    public void setLandscape(boolean z) {
        if (z) {
            setOrientation(PrintOrientation.LANDSCAPE);
        } else {
            setOrientation(PrintOrientation.PORTRAIT);
        }
    }

    public void setValidSettings(boolean z) {
        this.pageSetup.setUsePrinterDefaults(z);
    }

    public void setNoColor(boolean z) {
        this.pageSetup.setBlackAndWhite(z);
    }

    public void setDraft(boolean z) {
        this.pageSetup.setDraft(z);
    }

    public void setNotes(boolean z) {
        if (z) {
            this.pageSetup.setCellComments(STCellComments.AS_DISPLAYED);
        }
    }

    public void setNoOrientation(boolean z) {
        if (z) {
            setOrientation(PrintOrientation.DEFAULT);
        }
    }

    public void setUsePage(boolean z) {
        this.pageSetup.setUseFirstPageNumber(z);
    }

    public void setHResolution(short s) {
        this.pageSetup.setHorizontalDpi((long) s);
    }

    public void setVResolution(short s) {
        this.pageSetup.setVerticalDpi((long) s);
    }

    public void setHeaderMargin(double d) {
        this.pageMargins.setHeader(d);
    }

    public void setFooterMargin(double d) {
        this.pageMargins.setFooter(d);
    }

    public void setCopies(short s) {
        this.pageSetup.setCopies((long) s);
    }

    public void setOrientation(PrintOrientation printOrientation) {
        this.pageSetup.setOrientation(STOrientation.Enum.forInt(printOrientation.getValue()));
    }

    public PrintOrientation getOrientation() {
        STOrientation.Enum orientation = this.pageSetup.getOrientation();
        return orientation == null ? PrintOrientation.DEFAULT : PrintOrientation.valueOf(orientation.intValue());
    }

    public PrintCellComments getCellComment() {
        STCellComments.Enum cellComments = this.pageSetup.getCellComments();
        return cellComments == null ? PrintCellComments.NONE : PrintCellComments.valueOf(cellComments.intValue());
    }

    public void setPageOrder(PageOrder pageOrder) {
        this.pageSetup.setPageOrder(STPageOrder.Enum.forInt(pageOrder.getValue()));
    }

    public PageOrder getPageOrder() {
        if (this.pageSetup.getPageOrder() == null) {
            return null;
        }
        return PageOrder.valueOf(this.pageSetup.getPageOrder().intValue());
    }

    public short getPaperSize() {
        return (short) ((int) this.pageSetup.getPaperSize());
    }

    public PaperSize getPaperSizeEnum() {
        return PaperSize.values()[getPaperSize() - 1];
    }

    public short getScale() {
        return (short) ((int) this.pageSetup.getScale());
    }

    public short getPageStart() {
        return (short) ((int) this.pageSetup.getFirstPageNumber());
    }

    public short getFitWidth() {
        return (short) ((int) this.pageSetup.getFitToWidth());
    }

    public short getFitHeight() {
        return (short) ((int) this.pageSetup.getFitToHeight());
    }

    public boolean getLeftToRight() {
        return getPageOrder() == PageOrder.OVER_THEN_DOWN;
    }

    public boolean getLandscape() {
        return getOrientation() == PrintOrientation.LANDSCAPE;
    }

    public boolean getValidSettings() {
        return this.pageSetup.getUsePrinterDefaults();
    }

    public boolean getNoColor() {
        return this.pageSetup.getBlackAndWhite();
    }

    public boolean getDraft() {
        return this.pageSetup.getDraft();
    }

    public boolean getNotes() {
        return getCellComment() == PrintCellComments.AS_DISPLAYED;
    }

    public boolean getNoOrientation() {
        return getOrientation() == PrintOrientation.DEFAULT;
    }

    public boolean getUsePage() {
        return this.pageSetup.getUseFirstPageNumber();
    }

    public short getHResolution() {
        return (short) ((int) this.pageSetup.getHorizontalDpi());
    }

    public short getVResolution() {
        return (short) ((int) this.pageSetup.getVerticalDpi());
    }

    public double getHeaderMargin() {
        return this.pageMargins.getHeader();
    }

    public double getFooterMargin() {
        return this.pageMargins.getFooter();
    }

    public short getCopies() {
        return (short) ((int) this.pageSetup.getCopies());
    }

    public void setTopMargin(double d) {
        this.pageMargins.setTop(d);
    }

    public double getTopMargin() {
        return this.pageMargins.getTop();
    }

    public void setBottomMargin(double d) {
        this.pageMargins.setBottom(d);
    }

    public double getBottomMargin() {
        return this.pageMargins.getBottom();
    }

    public void setLeftMargin(double d) {
        this.pageMargins.setLeft(d);
    }

    public double getLeftMargin() {
        return this.pageMargins.getLeft();
    }

    public void setRightMargin(double d) {
        this.pageMargins.setRight(d);
    }

    public double getRightMargin() {
        return this.pageMargins.getRight();
    }
}
