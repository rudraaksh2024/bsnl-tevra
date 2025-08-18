package org.apache.poi.xssf.streaming;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.poi.util.Removal;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.model.SharedStringsTable;

public class GZIPSheetDataWriter extends SheetDataWriter {
    public GZIPSheetDataWriter() throws IOException {
    }

    public GZIPSheetDataWriter(SharedStringsTable sharedStringsTable) throws IOException {
        super(sharedStringsTable);
    }

    @Deprecated
    @Removal(version = "6.0.0")
    public File createTempFile() throws IOException {
        return TempFile.createTempFile("poi-sxssf-sheet-xml", ".gz");
    }

    /* access modifiers changed from: protected */
    public InputStream decorateInputStream(FileInputStream fileInputStream) throws IOException {
        return new GZIPInputStream(fileInputStream);
    }

    /* access modifiers changed from: protected */
    public OutputStream decorateOutputStream(FileOutputStream fileOutputStream) throws IOException {
        return new GZIPOutputStream(fileOutputStream);
    }
}
