package org.apache.poi.sl.usermodel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface SlideShowProvider<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> {
    boolean accepts(FileMagic fileMagic);

    SlideShow<S, P> create();

    SlideShow<S, P> create(File file, String str, boolean z) throws IOException;

    SlideShow<S, P> create(InputStream inputStream) throws IOException;

    SlideShow<S, P> create(InputStream inputStream, String str) throws IOException;

    SlideShow<S, P> create(DirectoryNode directoryNode, String str) throws IOException;
}
