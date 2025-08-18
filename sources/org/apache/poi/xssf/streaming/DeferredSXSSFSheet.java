package org.apache.poi.xssf.streaming;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class DeferredSXSSFSheet extends SXSSFSheet {
    private RowGeneratorFunction rowGenerator;

    public DeferredSXSSFSheet(DeferredSXSSFWorkbook deferredSXSSFWorkbook, XSSFSheet xSSFSheet) throws IOException {
        super(deferredSXSSFWorkbook, xSSFSheet, deferredSXSSFWorkbook.getRandomAccessWindowSize());
    }

    public InputStream getWorksheetXMLInputStream() throws IOException {
        throw new RuntimeException("Not supported by DeferredSXSSFSheet");
    }

    public void setRowGenerator(RowGeneratorFunction rowGeneratorFunction) {
        this.rowGenerator = rowGeneratorFunction;
    }

    public void writeRows(OutputStream outputStream) throws IOException {
        this._writer = ((DeferredSXSSFWorkbook) this._workbook).createSheetDataWriter(outputStream);
        try {
            RowGeneratorFunction rowGeneratorFunction = this.rowGenerator;
            if (rowGeneratorFunction != null) {
                rowGeneratorFunction.generateRows(this);
            }
            flushRows(0);
            this._writer.close();
            outputStream.flush();
        } catch (Exception e) {
            throw new IOException("Error generating Excel rows", e);
        } catch (Throwable th) {
            flushRows(0);
            this._writer.close();
            outputStream.flush();
            throw th;
        }
    }
}
