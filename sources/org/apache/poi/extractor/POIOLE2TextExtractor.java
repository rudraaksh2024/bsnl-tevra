package org.apache.poi.extractor;

import org.apache.poi.POIDocument;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hpsf.extractor.HPSFPropertiesExtractor;
import org.apache.poi.poifs.filesystem.DirectoryEntry;

public interface POIOLE2TextExtractor extends POITextExtractor {
    POIDocument getDocument();

    DocumentSummaryInformation getDocSummaryInformation() {
        return getDocument().getDocumentSummaryInformation();
    }

    SummaryInformation getSummaryInformation() {
        return getDocument().getSummaryInformation();
    }

    POITextExtractor getMetadataTextExtractor() {
        return new HPSFPropertiesExtractor(this);
    }

    DirectoryEntry getRoot() {
        return getDocument().getDirectory();
    }
}
