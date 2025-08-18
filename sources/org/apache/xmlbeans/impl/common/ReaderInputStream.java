package org.apache.xmlbeans.impl.common;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class ReaderInputStream extends PushedInputStream {
    public static final int defaultBufferSize = 2048;
    private final char[] buf;
    private final Reader reader;
    private final Writer writer;

    public ReaderInputStream(Reader reader2, String str) throws UnsupportedEncodingException {
        this(reader2, str, 2048);
    }

    public ReaderInputStream(Reader reader2, String str, int i) throws UnsupportedEncodingException {
        if (i > 0) {
            this.reader = reader2;
            this.writer = new OutputStreamWriter(getOutputStream(), str);
            this.buf = new char[i];
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }

    public void fill(int i) throws IOException {
        do {
            int read = this.reader.read(this.buf);
            if (read >= 0) {
                this.writer.write(this.buf, 0, read);
                this.writer.flush();
            } else {
                return;
            }
        } while (available() <= 0);
    }
}
