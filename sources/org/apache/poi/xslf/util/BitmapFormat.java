package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import javax.imageio.ImageIO;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.util.Internal;

@Internal
public class BitmapFormat implements OutputFormat {
    private final String format;
    private Graphics2D graphics;
    private BufferedImage img;

    public BitmapFormat(String str) {
        this.format = str;
    }

    public Graphics2D addSlide(double d, double d2) {
        String str = this.format;
        str.hashCode();
        BufferedImage bufferedImage = new BufferedImage((int) d, (int) d2, (str.equals(ContentTypes.EXTENSION_GIF) || str.equals(ContentTypes.EXTENSION_PNG)) ? 2 : 1);
        this.img = bufferedImage;
        Graphics2D createGraphics = bufferedImage.createGraphics();
        this.graphics = createGraphics;
        createGraphics.setRenderingHint(Drawable.BUFFERED_IMAGE, new WeakReference(this.img));
        return this.graphics;
    }

    public void writeSlide(MFProxy mFProxy, File file) throws IOException {
        if (!"null".equals(this.format)) {
            ImageIO.write(this.img, this.format, file);
        }
    }

    public void close() throws IOException {
        Graphics2D graphics2D = this.graphics;
        if (graphics2D != null) {
            graphics2D.dispose();
            this.img.flush();
        }
    }
}
