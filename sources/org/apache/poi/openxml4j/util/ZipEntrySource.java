package org.apache.poi.openxml4j.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

public interface ZipEntrySource extends Closeable {
    void close() throws IOException;

    Enumeration<? extends ZipArchiveEntry> getEntries();

    ZipArchiveEntry getEntry(String str);

    InputStream getInputStream(ZipArchiveEntry zipArchiveEntry) throws IOException;

    boolean isClosed();
}
