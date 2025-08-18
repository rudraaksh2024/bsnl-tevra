package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.sl.draw.EmbeddedExtractor;
import org.apache.poi.util.Internal;

@Internal
abstract class MFProxy implements Closeable {
    boolean ignoreParse;
    boolean quiet;

    /* access modifiers changed from: package-private */
    public abstract void draw(Graphics2D graphics2D);

    /* access modifiers changed from: package-private */
    public abstract Iterable<EmbeddedExtractor.EmbeddedPart> getEmbeddings(int i);

    /* access modifiers changed from: package-private */
    public abstract GenericRecord getRoot();

    /* access modifiers changed from: package-private */
    public abstract Dimension2D getSize();

    /* access modifiers changed from: package-private */
    public int getSlideCount() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public abstract String getTitle();

    /* access modifiers changed from: package-private */
    public abstract void parse(File file) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void parse(InputStream inputStream) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void setDefaultCharset(Charset charset);

    /* access modifiers changed from: package-private */
    public void setSlideNo(int i) {
    }

    MFProxy() {
    }

    /* access modifiers changed from: package-private */
    public void setIgnoreParse(boolean z) {
        this.ignoreParse = z;
    }

    /* access modifiers changed from: package-private */
    public void setQuiet(boolean z) {
        this.quiet = z;
    }

    /* access modifiers changed from: package-private */
    public Set<Integer> slideIndexes(String str) {
        return Collections.singleton(1);
    }
}
