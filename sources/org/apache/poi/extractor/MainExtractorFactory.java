package org.apache.poi.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.extractor.EventBasedExcelExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.extractor.OldExcelExtractor;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class MainExtractorFactory implements ExtractorProvider {
    public boolean accepts(FileMagic fileMagic) {
        return FileMagic.OLE2 == fileMagic;
    }

    public POITextExtractor create(File file, String str) throws IOException {
        return create(new POIFSFileSystem(file, true).getRoot(), str);
    }

    public POITextExtractor create(InputStream inputStream, String str) throws IOException {
        return create(new POIFSFileSystem(inputStream).getRoot(), str);
    }

    /* JADX INFO: finally extract failed */
    public POITextExtractor create(DirectoryNode directoryNode, String str) throws IOException {
        String currentUserPassword = Biff8EncryptionKey.getCurrentUserPassword();
        try {
            Biff8EncryptionKey.setCurrentUserPassword(str);
            for (String hasEntry : InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES) {
                if (directoryNode.hasEntry(hasEntry)) {
                    POITextExtractor eventBasedExcelExtractor = ExtractorFactory.getPreferEventExtractor() ? new EventBasedExcelExtractor(directoryNode) : new ExcelExtractor(directoryNode);
                    Biff8EncryptionKey.setCurrentUserPassword(currentUserPassword);
                    return eventBasedExcelExtractor;
                }
            }
            if (directoryNode.hasEntry(InternalWorkbook.OLD_WORKBOOK_DIR_ENTRY_NAME)) {
                OldExcelExtractor oldExcelExtractor = new OldExcelExtractor(directoryNode);
                Biff8EncryptionKey.setCurrentUserPassword(currentUserPassword);
                return oldExcelExtractor;
            }
            Biff8EncryptionKey.setCurrentUserPassword(currentUserPassword);
            return null;
        } catch (Throwable th) {
            Biff8EncryptionKey.setCurrentUserPassword(currentUserPassword);
            throw th;
        }
    }
}
