package org.apache.poi.xssf.streaming;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StreamingSheetWriter extends SheetDataWriter {
    private static final Logger LOG = LogManager.getLogger((Class<?>) StreamingSheetWriter.class);
    private boolean closed = false;

    public StreamingSheetWriter() throws IOException {
        throw new RuntimeException("StreamingSheetWriter requires OutputStream");
    }

    public StreamingSheetWriter(OutputStream outputStream) throws IOException {
        super(createWriter(outputStream));
        LOG.atDebug().log("Preparing SXSSF sheet writer");
    }

    public File createTempFile() throws IOException {
        throw new RuntimeException("Not supported with StreamingSheetWriter");
    }

    public Writer createWriter(File file) throws IOException {
        throw new RuntimeException("Not supported with StreamingSheetWriter");
    }

    protected static Writer createWriter(OutputStream outputStream) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
    }

    public void close() throws IOException {
        if (!this.closed) {
            this._out.flush();
        }
    }

    public InputStream getWorksheetXMLInputStream() throws IOException {
        throw new RuntimeException("Not supported with StreamingSheetWriter");
    }

    /* access modifiers changed from: package-private */
    public boolean dispose() throws IOException {
        if (!this.closed) {
            this._out.close();
        }
        this.closed = true;
        return true;
    }
}
