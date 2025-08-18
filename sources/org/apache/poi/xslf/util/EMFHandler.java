package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.sl.draw.BitmapImageRenderer;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.EmbeddedExtractor;
import org.apache.poi.sl.draw.ImageRenderer;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.util.PPTX2PNG;

@Internal
class EMFHandler extends MFProxy {
    private ImageRenderer imgr = null;
    private InputStream is;

    public String getTitle() {
        return "";
    }

    EMFHandler() {
    }

    public void parse(File file) throws IOException {
        InputStream openStream = file.toURI().toURL().openStream();
        this.is = openStream;
        parse(openStream);
    }

    public void parse(InputStream inputStream) throws IOException {
        ImageRenderer imageRenderer = DrawPictureShape.getImageRenderer((Graphics2D) null, getContentType());
        this.imgr = imageRenderer;
        if (!(imageRenderer instanceof BitmapImageRenderer)) {
            imageRenderer.loadImage(inputStream, getContentType());
            if (this.ignoreParse) {
                try {
                    this.imgr.getDimension();
                } catch (Exception unused) {
                }
            }
        } else {
            throw new PPTX2PNG.NoScratchpadException();
        }
    }

    /* access modifiers changed from: protected */
    public String getContentType() {
        return PictureData.PictureType.EMF.contentType;
    }

    public Dimension2D getSize() {
        return this.imgr.getDimension();
    }

    public void draw(Graphics2D graphics2D) {
        Dimension2D size = getSize();
        this.imgr.drawImage(graphics2D, new Rectangle2D.Double(0.0d, 0.0d, size.getWidth(), size.getHeight()));
    }

    public void close() throws IOException {
        InputStream inputStream = this.is;
        if (inputStream != null) {
            try {
                inputStream.close();
            } finally {
                this.is = null;
            }
        }
    }

    public GenericRecord getRoot() {
        return this.imgr.getGenericRecord();
    }

    public Iterable<EmbeddedExtractor.EmbeddedPart> getEmbeddings(int i) {
        ImageRenderer imageRenderer = this.imgr;
        if (imageRenderer instanceof EmbeddedExtractor) {
            return ((EmbeddedExtractor) imageRenderer).getEmbeddings();
        }
        return Collections.emptyList();
    }

    /* access modifiers changed from: package-private */
    public void setDefaultCharset(Charset charset) {
        this.imgr.setDefaultCharset(charset);
    }
}
