package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.ImageInputStream;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.sl.usermodel.PictureData;

public class BitmapImageRenderer implements ImageRenderer {
    private static final PictureData.PictureType[] ALLOWED_TYPES = {PictureData.PictureType.JPEG, PictureData.PictureType.PNG, PictureData.PictureType.BMP, PictureData.PictureType.GIF};
    private static final ImageLoader[] IMAGE_LOADERS = {new BitmapImageRenderer$$ExternalSyntheticLambda3(), new BitmapImageRenderer$$ExternalSyntheticLambda4(), new BitmapImageRenderer$$ExternalSyntheticLambda5()};
    private static final Logger LOG = LogManager.getLogger((Class<?>) BitmapImageRenderer.class);
    private static final String UNSUPPORTED_IMAGE_TYPE = "Unsupported Image Type";
    private String cachedContentType;
    private byte[] cachedImage;
    private boolean doCache;
    protected BufferedImage img;

    private interface ImageLoader {
        BufferedImage load(ImageReader imageReader, ImageInputStream imageInputStream, ImageReadParam imageReadParam) throws IOException;
    }

    public boolean canRender(String str) {
        return Stream.of(ALLOWED_TYPES).anyMatch(new BitmapImageRenderer$$ExternalSyntheticLambda0(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        r1.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadImage(java.io.InputStream r2, java.lang.String r3) throws java.io.IOException {
        /*
            r1 = this;
            boolean r0 = r1.doCache
            if (r0 == 0) goto L_0x0028
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r0.<init>()
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r2, (java.io.OutputStream) r0)     // Catch:{ all -> 0x001c }
            byte[] r2 = r0.toByteArray()     // Catch:{ all -> 0x001c }
            r1.cachedImage = r2     // Catch:{ all -> 0x001c }
            r1.cachedContentType = r3     // Catch:{ all -> 0x001c }
            java.io.InputStream r2 = r0.toInputStream()     // Catch:{ all -> 0x001c }
            r0.close()
            goto L_0x0028
        L_0x001c:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001e }
        L_0x001e:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r3 = move-exception
            r1.addSuppressed(r3)
        L_0x0027:
            throw r2
        L_0x0028:
            java.awt.image.BufferedImage r2 = readImage(r2, r3)
            r1.img = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.BitmapImageRenderer.loadImage(java.io.InputStream, java.lang.String):void");
    }

    public void loadImage(byte[] bArr, String str) throws IOException {
        if (bArr != null) {
            if (this.doCache) {
                this.cachedImage = (byte[]) bArr.clone();
                this.cachedContentType = str;
            }
            this.img = readImage(new UnsynchronizedByteArrayInputStream(bArr), str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008e, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0093, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0094, code lost:
        r11.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0097, code lost:
        throw r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.awt.image.BufferedImage readImage(java.io.InputStream r11, java.lang.String r12) throws java.io.IOException {
        /*
            javax.imageio.stream.MemoryCacheImageInputStream r0 = new javax.imageio.stream.MemoryCacheImageInputStream
            r0.<init>(r11)
            java.util.Iterator r11 = javax.imageio.ImageIO.getImageReaders(r0)     // Catch:{ all -> 0x008c }
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x000c:
            r4 = 0
            if (r2 != 0) goto L_0x0054
            boolean r5 = r11.hasNext()     // Catch:{ all -> 0x008c }
            if (r5 == 0) goto L_0x0054
            java.lang.Object r3 = r11.next()     // Catch:{ all -> 0x008c }
            javax.imageio.ImageReader r3 = (javax.imageio.ImageReader) r3     // Catch:{ all -> 0x008c }
            javax.imageio.ImageReadParam r5 = r3.getDefaultReadParam()     // Catch:{ all -> 0x008c }
            org.apache.poi.sl.draw.BitmapImageRenderer$ImageLoader[] r6 = IMAGE_LOADERS     // Catch:{ all -> 0x008c }
            int r7 = r6.length     // Catch:{ all -> 0x008c }
            r8 = r1
        L_0x0023:
            if (r4 >= r7) goto L_0x004f
            r9 = r6[r4]     // Catch:{ all -> 0x008c }
            r0.reset()     // Catch:{ all -> 0x008c }
            r0.mark()     // Catch:{ all -> 0x008c }
            java.awt.image.BufferedImage r2 = r9.load(r3, r0, r5)     // Catch:{ IOException -> 0x003e, RuntimeException -> 0x0034 }
            if (r2 == 0) goto L_0x004c
            goto L_0x004f
        L_0x0034:
            r8 = move-exception
            java.io.IOException r9 = new java.io.IOException     // Catch:{ all -> 0x008c }
            java.lang.String r10 = "ImageIO runtime exception"
            r9.<init>(r10, r8)     // Catch:{ all -> 0x008c }
            r8 = r9
            goto L_0x004c
        L_0x003e:
            r8 = move-exception
            java.lang.String r9 = "Unsupported Image Type"
            java.lang.String r10 = r8.getMessage()     // Catch:{ all -> 0x008c }
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x008c }
            if (r9 == 0) goto L_0x004c
            goto L_0x004f
        L_0x004c:
            int r4 = r4 + 1
            goto L_0x0023
        L_0x004f:
            r3.dispose()     // Catch:{ all -> 0x008c }
            r3 = r8
            goto L_0x000c
        L_0x0054:
            r0.close()
            if (r2 != 0) goto L_0x0068
            if (r3 != 0) goto L_0x0067
            org.apache.logging.log4j.Logger r11 = LOG
            org.apache.logging.log4j.LogBuilder r11 = r11.atWarn()
            java.lang.String r0 = "Content-type: {} is not supported. Image ignored."
            r11.log((java.lang.String) r0, (java.lang.Object) r12)
            return r1
        L_0x0067:
            throw r3
        L_0x0068:
            java.awt.image.ColorModel r11 = r2.getColorModel()
            boolean r11 = r11.hasAlpha()
            if (r11 == 0) goto L_0x0073
            return r2
        L_0x0073:
            java.awt.image.BufferedImage r11 = new java.awt.image.BufferedImage
            int r12 = r2.getWidth()
            int r0 = r2.getHeight()
            r3 = 2
            r11.<init>(r12, r0, r3)
            java.awt.Graphics r12 = r11.getGraphics()
            r12.drawImage(r2, r4, r4, r1)
            r12.dispose()
            return r11
        L_0x008c:
            r11 = move-exception
            throw r11     // Catch:{ all -> 0x008e }
        L_0x008e:
            r12 = move-exception
            r0.close()     // Catch:{ all -> 0x0093 }
            goto L_0x0097
        L_0x0093:
            r0 = move-exception
            r11.addSuppressed(r0)
        L_0x0097:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.BitmapImageRenderer.readImage(java.io.InputStream, java.lang.String):java.awt.image.BufferedImage");
    }

    /* access modifiers changed from: private */
    public static BufferedImage loadColored(ImageReader imageReader, ImageInputStream imageInputStream, ImageReadParam imageReadParam) throws IOException {
        imageReader.setInput(imageInputStream, false, true);
        return imageReader.read(0, imageReadParam);
    }

    /* access modifiers changed from: private */
    public static BufferedImage loadGrayScaled(ImageReader imageReader, ImageInputStream imageInputStream, ImageReadParam imageReadParam) throws IOException {
        Optional findFirst = StreamSupport.stream(new IteratorIterable(imageReader.getImageTypes(0)).spliterator(), false).filter(new BitmapImageRenderer$$ExternalSyntheticLambda1()).findFirst();
        imageReadParam.getClass();
        findFirst.ifPresent(new BitmapImageRenderer$$ExternalSyntheticLambda2(imageReadParam));
        imageReader.setInput(imageInputStream, false, true);
        return imageReader.read(0, imageReadParam);
    }

    static /* synthetic */ boolean lambda$loadGrayScaled$1(ImageTypeSpecifier imageTypeSpecifier) {
        return imageTypeSpecifier.getBufferedImageType() == 10;
    }

    /* access modifiers changed from: private */
    public static BufferedImage loadTruncated(ImageReader imageReader, ImageInputStream imageInputStream, ImageReadParam imageReadParam) throws IOException {
        int findTruncatedBlackBox;
        imageReader.setInput(imageInputStream, false, true);
        int height = imageReader.getHeight(0);
        int width = imageReader.getWidth(0);
        Iterator imageTypes = imageReader.getImageTypes(0);
        if (!imageTypes.hasNext()) {
            return null;
        }
        BufferedImage createBufferedImage = ((ImageTypeSpecifier) imageTypes.next()).createBufferedImage(width, height);
        imageReadParam.setDestination(createBufferedImage);
        try {
            imageReader.read(0, imageReadParam);
        } catch (IOException unused) {
        }
        if (createBufferedImage.getColorModel().hasAlpha() || (findTruncatedBlackBox = findTruncatedBlackBox(createBufferedImage, width, height)) >= height) {
            return createBufferedImage;
        }
        BufferedImage bufferedImage = new BufferedImage(width, height, 2);
        Graphics2D createGraphics = bufferedImage.createGraphics();
        createGraphics.clipRect(0, 0, width, findTruncatedBlackBox);
        createGraphics.drawImage(createBufferedImage, 0, 0, (ImageObserver) null);
        createGraphics.dispose();
        createBufferedImage.flush();
        return bufferedImage;
    }

    private static int findTruncatedBlackBox(BufferedImage bufferedImage, int i, int i2) {
        for (int i3 = i2 - 1; i3 > 0; i3--) {
            int i4 = i - 1;
            while (i4 > 0) {
                if (bufferedImage.getRGB(i4, i3) != -16777216) {
                    return i3 + 1;
                }
                i4 -= i / 10;
            }
        }
        return 0;
    }

    public BufferedImage getImage() {
        return this.img;
    }

    public BufferedImage getImage(Dimension2D dimension2D) {
        BufferedImage bufferedImage = this.img;
        if (bufferedImage == null) {
            return null;
        }
        double width = (double) bufferedImage.getWidth();
        double height = (double) this.img.getHeight();
        double width2 = dimension2D.getWidth();
        double height2 = dimension2D.getHeight();
        if (width == width2 && height == height2) {
            return this.img;
        }
        BufferedImage bufferedImage2 = new BufferedImage((int) width2, (int) height2, 2);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.scale(width2 / width, height2 / height);
        new AffineTransformOp(affineTransform, 2).filter(this.img, bufferedImage2);
        return bufferedImage2;
    }

    public Rectangle2D getBounds() {
        if (this.img == null) {
            return new Rectangle2D.Double();
        }
        return new Rectangle2D.Double(0.0d, 0.0d, (double) this.img.getWidth(), (double) this.img.getHeight());
    }

    public void setAlpha(double d) {
        this.img = setAlpha(this.img, d);
    }

    public static BufferedImage setAlpha(BufferedImage bufferedImage, double d) {
        if (bufferedImage == null) {
            return new BufferedImage(1, 1, 2);
        }
        if (d == 0.0d) {
            return bufferedImage;
        }
        return new RescaleOp(new float[]{1.0f, 1.0f, 1.0f, (float) d}, new float[]{0.0f, 0.0f, 0.0f, 0.0f}, (RenderingHints) null).filter(bufferedImage, (BufferedImage) null);
    }

    public boolean drawImage(Graphics2D graphics2D, Rectangle2D rectangle2D) {
        return drawImage(graphics2D, rectangle2D, (Insets) null);
    }

    public boolean drawImage(Graphics2D graphics2D, Rectangle2D rectangle2D, Insets insets) {
        Insets insets2;
        Graphics2D graphics2D2 = graphics2D;
        boolean z = false;
        if (this.img == null) {
            return false;
        }
        if (insets == null) {
            insets2 = new Insets(0, 0, 0, 0);
        } else {
            insets2 = insets;
            z = true;
        }
        int width = this.img.getWidth();
        int height = this.img.getHeight();
        boolean z2 = z;
        double d = (double) width;
        double width2 = rectangle2D.getWidth() / ((((double) ((BZip2Constants.BASEBLOCKSIZE - insets2.left) - insets2.right)) / 100000.0d) * d);
        double d2 = (double) height;
        double height2 = rectangle2D.getHeight() / ((((double) ((BZip2Constants.BASEBLOCKSIZE - insets2.top) - insets2.bottom)) / 100000.0d) * d2);
        AffineTransform affineTransform = new AffineTransform(width2, 0.0d, 0.0d, height2, rectangle2D.getX() - (((d * width2) * ((double) insets2.left)) / 100000.0d), rectangle2D.getY() - (((d2 * height2) * ((double) insets2.top)) / 100000.0d));
        Shape clip = graphics2D.getClip();
        if (z2) {
            graphics2D2.clip(rectangle2D.getBounds2D());
        }
        graphics2D2.drawRenderedImage(this.img, affineTransform);
        graphics2D2.setClip(clip);
        return true;
    }

    public Rectangle2D getNativeBounds() {
        return new Rectangle2D.Double(0.0d, 0.0d, (double) this.img.getWidth(), (double) this.img.getHeight());
    }

    public void setCacheInput(boolean z) {
        this.doCache = z;
        if (!z) {
            this.cachedContentType = null;
            this.cachedImage = null;
        }
    }

    public byte[] getCachedImage() {
        return this.cachedImage;
    }

    public String getCachedContentType() {
        return this.cachedContentType;
    }
}
