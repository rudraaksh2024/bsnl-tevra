package org.apache.poi.poifs.filesystem;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.poifs.nio.FileBackedDataSource;
import org.apache.poi.util.TempFile;

public class TempFilePOIFSFileSystem extends POIFSFileSystem {
    private static Logger LOG = LogManager.getLogger((Class<?>) TempFilePOIFSFileSystem.class);
    File tempFile;

    /* access modifiers changed from: protected */
    public void createNewDataSource() {
        try {
            this.tempFile = TempFile.createTempFile("poifs", ".tmp");
            this._data = new FileBackedDataSource(this.tempFile, false);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create data source", e);
        }
    }

    public void close() throws IOException {
        File file = this.tempFile;
        if (file != null && file.exists() && !this.tempFile.delete()) {
            LOG.atDebug().log("temp file was already deleted (probably due to previous call to close this resource)");
        }
        super.close();
    }
}
