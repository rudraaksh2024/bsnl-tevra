package org.apache.poi.ss.usermodel;

import java.io.IOException;
import org.apache.poi.poifs.filesystem.DirectoryEntry;

public interface ObjectData extends SimpleShape {
    String getContentType() {
        return "binary/octet-stream";
    }

    DirectoryEntry getDirectory() throws IOException;

    String getFileName();

    String getOLE2ClassName();

    byte[] getObjectData() throws IOException;

    PictureData getPictureData();

    boolean hasDirectoryEntry();
}
