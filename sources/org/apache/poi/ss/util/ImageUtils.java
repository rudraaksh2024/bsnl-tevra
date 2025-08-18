package org.apache.poi.ss.util;

import java.awt.Dimension;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.imageio.ImageReader;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.Units;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class ImageUtils {
    private static final int HEIGHT_UNITS = 256;
    private static final Logger LOG = LogManager.getLogger((Class<?>) ImageUtils.class);
    private static final int WIDTH_UNITS = 1024;

    private ImageUtils() {
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x007b, code lost:
        if (r7 != null) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0085, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.awt.Dimension getImageDimension(java.io.InputStream r7, int r8) {
        /*
            java.awt.Dimension r0 = new java.awt.Dimension
            r0.<init>()
            r1 = 5
            if (r8 == r1) goto L_0x001b
            r1 = 6
            if (r8 == r1) goto L_0x001b
            r1 = 7
            if (r8 == r1) goto L_0x001b
            org.apache.logging.log4j.Logger r7 = LOG
            org.apache.logging.log4j.LogBuilder r7 = r7.atWarn()
            java.lang.String r8 = "Only JPEG, PNG and DIB pictures can be automatically sized"
            r7.log((java.lang.String) r8)
            goto L_0x0096
        L_0x001b:
            javax.imageio.stream.ImageInputStream r7 = javax.imageio.ImageIO.createImageInputStream(r7)     // Catch:{ IOException -> 0x0086 }
            java.util.Iterator r8 = javax.imageio.ImageIO.getImageReaders(r7)     // Catch:{ all -> 0x0078 }
            boolean r1 = r8.hasNext()     // Catch:{ all -> 0x0078 }
            if (r1 == 0) goto L_0x0067
            java.lang.Object r8 = r8.next()     // Catch:{ all -> 0x0078 }
            javax.imageio.ImageReader r8 = (javax.imageio.ImageReader) r8     // Catch:{ all -> 0x0078 }
            r8.setInput(r7)     // Catch:{ all -> 0x0062 }
            r1 = 0
            java.awt.image.BufferedImage r2 = r8.read(r1)     // Catch:{ all -> 0x0062 }
            int[] r3 = getResolution(r8)     // Catch:{ all -> 0x0062 }
            r4 = r3[r1]     // Catch:{ all -> 0x0062 }
            r5 = 96
            if (r4 != 0) goto L_0x0043
            r3[r1] = r5     // Catch:{ all -> 0x0062 }
        L_0x0043:
            r4 = 1
            r6 = r3[r4]     // Catch:{ all -> 0x0062 }
            if (r6 != 0) goto L_0x004a
            r3[r4] = r5     // Catch:{ all -> 0x0062 }
        L_0x004a:
            int r6 = r2.getWidth()     // Catch:{ all -> 0x0062 }
            int r6 = r6 * r5
            r1 = r3[r1]     // Catch:{ all -> 0x0062 }
            int r6 = r6 / r1
            r0.width = r6     // Catch:{ all -> 0x0062 }
            int r1 = r2.getHeight()     // Catch:{ all -> 0x0062 }
            int r1 = r1 * r5
            r2 = r3[r4]     // Catch:{ all -> 0x0062 }
            int r1 = r1 / r2
            r0.height = r1     // Catch:{ all -> 0x0062 }
            r8.dispose()     // Catch:{ all -> 0x0078 }
            goto L_0x0072
        L_0x0062:
            r1 = move-exception
            r8.dispose()     // Catch:{ all -> 0x0078 }
            throw r1     // Catch:{ all -> 0x0078 }
        L_0x0067:
            org.apache.logging.log4j.Logger r8 = LOG     // Catch:{ all -> 0x0078 }
            org.apache.logging.log4j.LogBuilder r8 = r8.atWarn()     // Catch:{ all -> 0x0078 }
            java.lang.String r1 = "ImageIO found no images"
            r8.log((java.lang.String) r1)     // Catch:{ all -> 0x0078 }
        L_0x0072:
            if (r7 == 0) goto L_0x0096
            r7.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x0096
        L_0x0078:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x007a }
        L_0x007a:
            r1 = move-exception
            if (r7 == 0) goto L_0x0085
            r7.close()     // Catch:{ all -> 0x0081 }
            goto L_0x0085
        L_0x0081:
            r7 = move-exception
            r8.addSuppressed(r7)     // Catch:{ IOException -> 0x0086 }
        L_0x0085:
            throw r1     // Catch:{ IOException -> 0x0086 }
        L_0x0086:
            r7 = move-exception
            org.apache.logging.log4j.Logger r8 = LOG
            org.apache.logging.log4j.LogBuilder r8 = r8.atWarn()
            org.apache.logging.log4j.LogBuilder r7 = r8.withThrowable(r7)
            java.lang.String r8 = "Failed to determine image dimensions"
            r7.log((java.lang.String) r8)
        L_0x0096:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.util.ImageUtils.getImageDimension(java.io.InputStream, int):java.awt.Dimension");
    }

    public static int[] getResolution(ImageReader imageReader) throws IOException {
        Element element = (Element) imageReader.getImageMetadata(0).getAsTree("javax_imageio_1.0");
        NodeList elementsByTagName = element.getElementsByTagName("HorizontalPixelSize");
        int i = 96;
        int parseFloat = (elementsByTagName == null || elementsByTagName.getLength() != 1) ? 96 : (int) (25.4d / ((double) Float.parseFloat(((Element) elementsByTagName.item(0)).getAttribute("value"))));
        NodeList elementsByTagName2 = element.getElementsByTagName("VerticalPixelSize");
        if (elementsByTagName2 != null && elementsByTagName2.getLength() == 1) {
            i = (int) (25.4d / ((double) Float.parseFloat(((Element) elementsByTagName2.item(0)).getAttribute("value"))));
        }
        return new int[]{parseFloat, i};
    }

    public static Dimension setPreferredSize(Picture picture, double d, double d2) {
        ClientAnchor clientAnchor = picture.getClientAnchor();
        boolean z = clientAnchor instanceof HSSFClientAnchor;
        PictureData pictureData = picture.getPictureData();
        Sheet sheet = picture.getSheet();
        int i = (d > Double.MAX_VALUE ? 1 : (d == Double.MAX_VALUE ? 0 : -1));
        Dimension imageDimension = (i == 0 || d2 == Double.MAX_VALUE) ? getImageDimension(new UnsynchronizedByteArrayInputStream(pictureData.getData()), pictureData.getPictureType()) : new Dimension();
        Dimension dimension = (i == 0 && d2 == Double.MAX_VALUE) ? new Dimension() : getDimensionFromAnchor(picture);
        double width = i == 0 ? imageDimension.getWidth() : (dimension.getWidth() / 9525.0d) * d;
        double height = d2 == Double.MAX_VALUE ? imageDimension.getHeight() : (dimension.getHeight() / 9525.0d) * d2;
        short col1 = clientAnchor.getCol1();
        int dx1 = clientAnchor.getDx1();
        clientAnchor.getClass();
        ImageUtils$$ExternalSyntheticLambda2 imageUtils$$ExternalSyntheticLambda2 = new ImageUtils$$ExternalSyntheticLambda2(clientAnchor);
        clientAnchor.getClass();
        ImageUtils$$ExternalSyntheticLambda3 imageUtils$$ExternalSyntheticLambda3 = new ImageUtils$$ExternalSyntheticLambda3(clientAnchor);
        int i2 = z ? 1024 : 0;
        sheet.getClass();
        scaleCell(width, col1, dx1, imageUtils$$ExternalSyntheticLambda2, imageUtils$$ExternalSyntheticLambda3, i2, new ImageUtils$$ExternalSyntheticLambda0(sheet));
        int row1 = clientAnchor.getRow1();
        int dy1 = clientAnchor.getDy1();
        clientAnchor.getClass();
        ImageUtils$$ExternalSyntheticLambda4 imageUtils$$ExternalSyntheticLambda4 = new ImageUtils$$ExternalSyntheticLambda4(clientAnchor);
        clientAnchor.getClass();
        scaleCell(height, row1, dy1, imageUtils$$ExternalSyntheticLambda4, new ImageUtils$$ExternalSyntheticLambda5(clientAnchor), z ? 256 : 0, new ImageUtils$$ExternalSyntheticLambda6(sheet));
        return new Dimension((int) Math.round(width * 9525.0d), (int) Math.round(height * 9525.0d));
    }

    public static Dimension getDimensionFromAnchor(Picture picture) {
        Dimension dimension;
        double d;
        ClientAnchor clientAnchor = picture.getClientAnchor();
        boolean z = clientAnchor instanceof HSSFClientAnchor;
        Sheet sheet = picture.getSheet();
        if (clientAnchor.getCol2() < clientAnchor.getCol1() || clientAnchor.getRow2() < clientAnchor.getRow1()) {
            PictureData pictureData = picture.getPictureData();
            dimension = getImageDimension(new UnsynchronizedByteArrayInputStream(pictureData.getData()), pictureData.getPictureType());
        } else {
            dimension = null;
        }
        double d2 = 0.0d;
        if (dimension == null) {
            d = 0.0d;
        } else {
            d = dimension.getWidth();
        }
        short col1 = clientAnchor.getCol1();
        int dx1 = clientAnchor.getDx1();
        short col2 = clientAnchor.getCol2();
        int dx2 = clientAnchor.getDx2();
        int i = 0;
        int i2 = z ? 1024 : 0;
        sheet.getClass();
        int dimFromCell = getDimFromCell(d, col1, dx1, col2, dx2, i2, new ImageUtils$$ExternalSyntheticLambda0(sheet));
        if (dimension != null) {
            d2 = dimension.getHeight();
        }
        double d3 = d2;
        int row1 = clientAnchor.getRow1();
        int dy1 = clientAnchor.getDy1();
        int row2 = clientAnchor.getRow2();
        int dy2 = clientAnchor.getDy2();
        if (z) {
            i = 256;
        }
        return new Dimension(dimFromCell, getDimFromCell(d3, row1, dy1, row2, dy2, i, new ImageUtils$$ExternalSyntheticLambda1(sheet)));
    }

    public static double getRowHeightInPixels(Sheet sheet, int i) {
        Row row = sheet.getRow(i);
        return ((double) Units.toEMU((double) (row == null ? sheet.getDefaultRowHeightInPoints() : row.getHeightInPoints()))) / 9525.0d;
    }

    private static void scaleCell(double d, int i, int i2, Consumer<Integer> consumer, Consumer<Integer> consumer2, int i3, Function<Integer, Number> function) {
        double doubleValue;
        double d2;
        double d3;
        double d4 = d;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        double d5 = 0.0d;
        if (d4 < 0.0d) {
            throw new IllegalArgumentException("target size < 0");
        } else if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new IllegalArgumentException("target size " + d4 + " is not supported");
        } else {
            int i7 = i4;
            while (true) {
                doubleValue = function.apply(Integer.valueOf(i7)).doubleValue();
                double d6 = i7 == i4 ? i6 > 0 ? (1.0d - (((double) i5) / ((double) i6))) * doubleValue : doubleValue - (((double) i5) / 9525.0d) : doubleValue;
                d2 = d4 - d5;
                if (d2 < d6) {
                    break;
                }
                Consumer<Integer> consumer3 = consumer;
                Consumer<Integer> consumer4 = consumer2;
                i7++;
                d5 += d6;
            }
            if (i6 > 0) {
                d2 /= doubleValue;
                d3 = (double) i6;
            } else {
                d3 = 9525.0d;
            }
            double d7 = d2 * d3;
            if (i7 == i4) {
                d7 += (double) i5;
            }
            consumer.accept(Integer.valueOf(i7));
            consumer2.accept(Integer.valueOf((int) Math.rint(d7)));
        }
    }

    private static int getDimFromCell(double d, int i, int i2, int i3, int i4, int i5, Function<Integer, Number> function) {
        double d2;
        double d3;
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        int i10 = i5;
        double d4 = 9525.0d;
        if (i8 < i6) {
            d2 = d * 9525.0d;
        } else {
            int i11 = i6;
            double d5 = 0.0d;
            while (i11 <= i8) {
                double doubleValue = function.apply(Integer.valueOf(i11)).doubleValue() * d4;
                if (i11 == i6) {
                    d3 = (double) i7;
                    if (i10 > 0) {
                        d3 = (d3 * doubleValue) / ((double) i10);
                    }
                } else {
                    d3 = 0.0d;
                }
                d5 += (doubleValue - d3) - (i11 == i8 ? i10 > 0 ? (((double) (i10 - i9)) * doubleValue) / ((double) i10) : doubleValue - ((double) i9) : 0.0d);
                i11++;
                i6 = i;
                i7 = i2;
                d4 = 9525.0d;
            }
            d2 = d5;
        }
        return (int) Math.rint(d2);
    }
}
