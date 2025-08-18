package org.apache.poi.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.FileMagic;

public interface ExtractorProvider {
    boolean accepts(FileMagic fileMagic);

    POITextExtractor create(File file, String str) throws IOException;

    POITextExtractor create(InputStream inputStream, String str) throws IOException;

    POITextExtractor create(DirectoryNode directoryNode, String str) throws IOException;

    void identifyEmbeddedResources(POIOLE2TextExtractor pOIOLE2TextExtractor, List<Entry> list, List<InputStream> list2) throws IOException {
        throw new IllegalArgumentException("Error checking for Scratchpad embedded resources");
    }
}
