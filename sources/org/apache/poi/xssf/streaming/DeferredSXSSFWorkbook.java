package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DeferredSXSSFWorkbook extends SXSSFWorkbook {
    private static final Logger LOG = LogManager.getLogger((Class<?>) DeferredSXSSFWorkbook.class);

    public DeferredSXSSFWorkbook() {
        this((XSSFWorkbook) null);
    }

    public DeferredSXSSFWorkbook(int i) {
        this((XSSFWorkbook) null, i);
    }

    public DeferredSXSSFWorkbook(XSSFWorkbook xSSFWorkbook) {
        this(xSSFWorkbook, 100);
    }

    public DeferredSXSSFWorkbook(XSSFWorkbook xSSFWorkbook, int i) {
        super(xSSFWorkbook, i, false, false);
    }

    /* access modifiers changed from: protected */
    @NotImplemented
    public SheetDataWriter createSheetDataWriter() throws IOException {
        throw new RuntimeException("Not supported by DeferredSXSSFWorkbook");
    }

    /* access modifiers changed from: protected */
    public StreamingSheetWriter createSheetDataWriter(OutputStream outputStream) throws IOException {
        return new StreamingSheetWriter(outputStream);
    }

    /* access modifiers changed from: protected */
    public SXSSFWorkbook.ISheetInjector createSheetInjector(SXSSFSheet sXSSFSheet) throws IOException {
        return new DeferredSXSSFWorkbook$$ExternalSyntheticLambda0((DeferredSXSSFSheet) sXSSFSheet);
    }

    /* access modifiers changed from: package-private */
    public SXSSFSheet createAndRegisterSXSSFSheet(XSSFSheet xSSFSheet) {
        try {
            DeferredSXSSFSheet deferredSXSSFSheet = new DeferredSXSSFSheet(this, xSSFSheet);
            registerSheetMapping(deferredSXSSFSheet, xSSFSheet);
            return deferredSXSSFSheet;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DeferredSXSSFSheet createSheet() {
        return (DeferredSXSSFSheet) super.createSheet();
    }

    public DeferredSXSSFSheet createSheet(String str) {
        return (DeferredSXSSFSheet) super.createSheet(str);
    }

    public Iterator<Sheet> sheetIterator() {
        return new SXSSFWorkbook.SheetIterator();
    }

    public DeferredSXSSFSheet getStreamingSheetAt(int i) {
        XSSFSheet sheetAt = this._wb.getSheetAt(i);
        SXSSFSheet sXSSFSheet = getSXSSFSheet(sheetAt);
        if (sXSSFSheet != null || sheetAt == null) {
            return (DeferredSXSSFSheet) sXSSFSheet;
        }
        return (DeferredSXSSFSheet) createAndRegisterSXSSFSheet(sheetAt);
    }

    public XSSFSheet getXSSFSheet(String str) {
        return this._wb.getSheet(str);
    }

    public DeferredSXSSFSheet getStreamingSheet(String str) {
        XSSFSheet sheet = this._wb.getSheet(str);
        DeferredSXSSFSheet deferredSXSSFSheet = (DeferredSXSSFSheet) getSXSSFSheet(sheet);
        return (deferredSXSSFSheet != null || sheet == null) ? deferredSXSSFSheet : (DeferredSXSSFSheet) createAndRegisterSXSSFSheet(sheet);
    }

    public void removeSheetAt(int i) {
        XSSFSheet sheetAt = this._wb.getSheetAt(i);
        SXSSFSheet sXSSFSheet = getSXSSFSheet(sheetAt);
        this._wb.removeSheetAt(i);
        if (sXSSFSheet != null) {
            deregisterSheetMapping(sheetAt);
            try {
                sXSSFSheet.dispose();
            } catch (IOException e) {
                LOG.atWarn().withThrowable(e).log("Failed to cleanup old sheet");
            }
        }
    }
}
