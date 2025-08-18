package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;

public class DummyFormat implements OutputFormat {
    private final UnsynchronizedByteArrayOutputStream bos;
    private final DummyGraphics2d dummy2d;

    public void writeDocument(MFProxy mFProxy, File file) {
    }

    public DummyFormat() {
        try {
            UnsynchronizedByteArrayOutputStream unsynchronizedByteArrayOutputStream = new UnsynchronizedByteArrayOutputStream();
            this.bos = unsynchronizedByteArrayOutputStream;
            this.dummy2d = new DummyGraphics2d(new PrintStream(unsynchronizedByteArrayOutputStream, true, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Graphics2D addSlide(double d, double d2) {
        this.bos.reset();
        return this.dummy2d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeSlide(org.apache.poi.xslf.util.MFProxy r1, java.io.File r2) throws java.io.IOException {
        /*
            r0 = this;
            java.io.FileOutputStream r1 = new java.io.FileOutputStream
            r1.<init>(r2)
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r2 = r0.bos     // Catch:{ all -> 0x0013 }
            r2.writeTo(r1)     // Catch:{ all -> 0x0013 }
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = r0.bos     // Catch:{ all -> 0x0013 }
            r0.reset()     // Catch:{ all -> 0x0013 }
            r1.close()
            return
        L_0x0013:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0015 }
        L_0x0015:
            r2 = move-exception
            r1.close()     // Catch:{ all -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r1 = move-exception
            r0.addSuppressed(r1)
        L_0x001e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.util.DummyFormat.writeSlide(org.apache.poi.xslf.util.MFProxy, java.io.File):void");
    }

    public void close() throws IOException {
        this.bos.reset();
    }
}
