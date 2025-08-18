package org.apache.poi.ss.usermodel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;

public interface WorkbookProvider {
    boolean accepts(FileMagic fileMagic);

    Workbook create();

    Workbook create(File file, String str, boolean z) throws IOException;

    Workbook create(InputStream inputStream) throws IOException;

    Workbook create(InputStream inputStream, String str) throws IOException;

    Workbook create(DirectoryNode directoryNode, String str) throws IOException;
}
