package org.apache.poi.extractor;

import java.io.Closeable;
import java.io.IOException;

public interface POITextExtractor extends Closeable {
    Object getDocument();

    Closeable getFilesystem();

    POITextExtractor getMetadataTextExtractor();

    String getText();

    boolean isCloseFilesystem();

    void setCloseFilesystem(boolean z);

    void close() throws IOException {
        Closeable filesystem = getFilesystem();
        if (isCloseFilesystem() && filesystem != null) {
            filesystem.close();
        }
    }
}
