package org.apache.poi.ooxml.extractor;

import java.io.IOException;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;

public interface POIXMLTextExtractor extends POITextExtractor {
    POIXMLDocument getDocument();

    POIXMLProperties.CoreProperties getCoreProperties() {
        return getDocument().getProperties().getCoreProperties();
    }

    POIXMLProperties.ExtendedProperties getExtendedProperties() {
        return getDocument().getProperties().getExtendedProperties();
    }

    POIXMLProperties.CustomProperties getCustomProperties() {
        return getDocument().getProperties().getCustomProperties();
    }

    OPCPackage getPackage() {
        POIXMLDocument document = getDocument();
        if (document != null) {
            return document.getPackage();
        }
        return null;
    }

    POIXMLPropertiesTextExtractor getMetadataTextExtractor() {
        return new POIXMLPropertiesTextExtractor(getDocument());
    }

    void close() throws IOException {
        OPCPackage oPCPackage;
        if (isCloseFilesystem() && (oPCPackage = getPackage()) != null) {
            oPCPackage.revert();
        }
    }

    void checkMaxTextSize(CharSequence charSequence, String str) {
        if (str != null) {
            int length = charSequence.length() + str.length();
            if (((long) length) > ZipSecureFile.getMaxTextSize()) {
                throw new IllegalStateException("The text would exceed the max allowed overall size of extracted text. By default this is prevented as some documents may exhaust available memory and it may indicate that the file is used to inflate memory usage and thus could pose a security risk. You can adjust this limit via ZipSecureFile.setMaxTextSize() if you need to work with files which have a lot of text. Size: " + length + ", limit: MAX_TEXT_SIZE: " + ZipSecureFile.getMaxTextSize());
            }
        }
    }
}
