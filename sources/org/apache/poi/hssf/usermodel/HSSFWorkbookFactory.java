package org.apache.poi.hssf.usermodel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookProvider;
import org.apache.poi.util.Internal;

@Internal
public class HSSFWorkbookFactory implements WorkbookProvider {
    public boolean accepts(FileMagic fileMagic) {
        return FileMagic.OLE2 == fileMagic;
    }

    public HSSFWorkbook create() {
        return new HSSFWorkbook();
    }

    public static HSSFWorkbook createWorkbook(POIFSFileSystem pOIFSFileSystem) throws IOException {
        return new HSSFWorkbook(pOIFSFileSystem);
    }

    public HSSFWorkbook create(DirectoryNode directoryNode, String str) throws IOException {
        boolean z;
        if (str != null) {
            Biff8EncryptionKey.setCurrentUserPassword(str);
            z = true;
        } else {
            z = false;
        }
        try {
            return new HSSFWorkbook(directoryNode, true);
        } finally {
            if (z) {
                Biff8EncryptionKey.setCurrentUserPassword((String) null);
            }
        }
    }

    public Workbook create(InputStream inputStream) throws IOException {
        return create(inputStream, (String) null);
    }

    public Workbook create(InputStream inputStream, String str) throws IOException {
        return create(new POIFSFileSystem(inputStream).getRoot(), str);
    }

    public Workbook create(File file, String str, boolean z) throws IOException {
        boolean z2;
        POIFSFileSystem pOIFSFileSystem;
        if (str != null) {
            Biff8EncryptionKey.setCurrentUserPassword(str);
            z2 = true;
        } else {
            z2 = false;
        }
        try {
            pOIFSFileSystem = new POIFSFileSystem(file, z);
            HSSFWorkbook hSSFWorkbook = new HSSFWorkbook(pOIFSFileSystem, true);
            if (z2) {
                Biff8EncryptionKey.setCurrentUserPassword((String) null);
            }
            return hSSFWorkbook;
        } catch (RuntimeException e) {
            pOIFSFileSystem.close();
            throw e;
        } catch (Throwable th) {
            if (z2) {
                Biff8EncryptionKey.setCurrentUserPassword((String) null);
            }
            throw th;
        }
    }
}
