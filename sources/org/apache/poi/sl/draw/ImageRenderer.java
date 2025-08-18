package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.Dimension2DDouble;

public interface ImageRenderer {
    boolean canRender(String str);

    boolean drawImage(Graphics2D graphics2D, Rectangle2D rectangle2D);

    boolean drawImage(Graphics2D graphics2D, Rectangle2D rectangle2D, Insets insets);

    Rectangle2D getBounds();

    String getCachedContentType() {
        return null;
    }

    byte[] getCachedImage() {
        return null;
    }

    GenericRecord getGenericRecord() {
        return null;
    }

    BufferedImage getImage();

    BufferedImage getImage(Dimension2D dimension2D);

    Rectangle2D getNativeBounds();

    void loadImage(InputStream inputStream, String str) throws IOException;

    void loadImage(byte[] bArr, String str) throws IOException;

    void setAlpha(double d);

    void setCacheInput(boolean z) {
    }

    void setDefaultCharset(Charset charset) {
    }

    Dimension2D getDimension() {
        Rectangle2D bounds = getBounds();
        return new Dimension2DDouble(Math.abs(bounds.getWidth()), Math.abs(bounds.getHeight()));
    }
}
