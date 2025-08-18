package org.apache.poi.poifs.filesystem;

import org.apache.poi.UnsupportedFileFormatException;

public class OfficeXmlFileException extends UnsupportedFileFormatException {
    public OfficeXmlFileException(String str) {
        super(str);
    }
}
