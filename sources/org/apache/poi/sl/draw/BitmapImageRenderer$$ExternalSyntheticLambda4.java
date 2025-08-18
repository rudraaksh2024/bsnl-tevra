package org.apache.poi.sl.draw;

import java.awt.image.BufferedImage;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.apache.poi.sl.draw.BitmapImageRenderer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BitmapImageRenderer$$ExternalSyntheticLambda4 implements BitmapImageRenderer.ImageLoader {
    public final BufferedImage load(ImageReader imageReader, ImageInputStream imageInputStream, ImageReadParam imageReadParam) {
        return BitmapImageRenderer.loadGrayScaled(imageReader, imageInputStream, imageReadParam);
    }
}
