package org.apache.poi;

import java.io.File;
import java.io.OutputStream;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public abstract class POIReadOnlyDocument extends POIDocument {
    protected POIReadOnlyDocument(DirectoryNode directoryNode) {
        super(directoryNode);
    }

    protected POIReadOnlyDocument(POIFSFileSystem pOIFSFileSystem) {
        super(pOIFSFileSystem);
    }

    public void write() {
        notImplemented();
    }

    public void write(File file) {
        notImplemented();
    }

    public void write(OutputStream outputStream) {
        notImplemented();
    }

    private static void notImplemented() {
        throw new IllegalStateException("Writing is not yet implemented for this Document Format");
    }
}
