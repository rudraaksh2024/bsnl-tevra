package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import org.apache.poi.util.Internal;

@Internal
interface OutputFormat extends Closeable {
    Graphics2D addSlide(double d, double d2) throws IOException;

    void writeDocument(MFProxy mFProxy, File file) throws IOException {
    }

    void writeSlide(MFProxy mFProxy, File file) throws IOException;
}
