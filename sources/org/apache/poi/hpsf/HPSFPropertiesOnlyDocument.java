package org.apache.poi.hpsf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.POIDocument;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.EntryUtils;
import org.apache.poi.poifs.filesystem.FilteringDirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class HPSFPropertiesOnlyDocument extends POIDocument {
    public HPSFPropertiesOnlyDocument(POIFSFileSystem pOIFSFileSystem) {
        super(pOIFSFileSystem);
    }

    public void write() throws IOException {
        POIFSFileSystem fileSystem = getDirectory().getFileSystem();
        validateInPlaceWritePossible();
        writeProperties(fileSystem, (List<String>) null);
        fileSystem.writeFilesystem();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0019, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        if (r2 != null) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(java.io.File r2) throws java.io.IOException {
        /*
            r1 = this;
            org.apache.poi.poifs.filesystem.POIFSFileSystem r2 = org.apache.poi.poifs.filesystem.POIFSFileSystem.create(r2)
            r1.write((org.apache.poi.poifs.filesystem.POIFSFileSystem) r2)     // Catch:{ all -> 0x0010 }
            r2.writeFilesystem()     // Catch:{ all -> 0x0010 }
            if (r2 == 0) goto L_0x000f
            r2.close()
        L_0x000f:
            return
        L_0x0010:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r0 = move-exception
            if (r2 == 0) goto L_0x001d
            r2.close()     // Catch:{ all -> 0x0019 }
            goto L_0x001d
        L_0x0019:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x001d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hpsf.HPSFPropertiesOnlyDocument.write(java.io.File):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(java.io.OutputStream r2) throws java.io.IOException {
        /*
            r1 = this;
            org.apache.poi.poifs.filesystem.POIFSFileSystem r0 = new org.apache.poi.poifs.filesystem.POIFSFileSystem
            r0.<init>()
            r1.write((org.apache.poi.poifs.filesystem.POIFSFileSystem) r0)     // Catch:{ all -> 0x000f }
            r0.writeFilesystem(r2)     // Catch:{ all -> 0x000f }
            r0.close()
            return
        L_0x000f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0011 }
        L_0x0011:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0016 }
            goto L_0x001a
        L_0x0016:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x001a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hpsf.HPSFPropertiesOnlyDocument.write(java.io.OutputStream):void");
    }

    private void write(POIFSFileSystem pOIFSFileSystem) throws IOException {
        ArrayList arrayList = new ArrayList(2);
        writeProperties(pOIFSFileSystem, arrayList);
        EntryUtils.copyNodes((DirectoryEntry) new FilteringDirectoryNode(getDirectory(), arrayList), (DirectoryEntry) new FilteringDirectoryNode(pOIFSFileSystem.getRoot(), arrayList));
    }
}
