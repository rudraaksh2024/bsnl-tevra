package org.apache.poi.sl.draw;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.RectAlign;

public class DrawPictureShape extends DrawSimpleShape {
    private static final Logger LOG = LogManager.getLogger((Class<?>) DrawPictureShape.class);

    /* access modifiers changed from: protected */
    public Paint getFillPaint(Graphics2D graphics2D) {
        return null;
    }

    public DrawPictureShape(PictureShape<?, ?> pictureShape) {
        super(pictureShape);
    }

    public void drawContent(Graphics2D graphics2D) {
        PictureShape<?, ?> shape = getShape();
        Rectangle2D anchor = getAnchor(graphics2D, (PlaceableShape<?, ?>) shape);
        Insets clipping = shape.getClipping();
        PictureData[] pictureDataArr = {shape.getAlternativePictureData(), shape.getPictureData()};
        for (int i = 0; i < 2; i++) {
            PictureData pictureData = pictureDataArr[i];
            if (pictureData != null) {
                try {
                    byte[] data = pictureData.getData();
                    PictureType valueOf = PictureType.valueOf(FileMagic.valueOf(data));
                    String contentType = valueOf == PictureType.UNKNOWN ? pictureData.getContentType() : valueOf.getContentType();
                    ImageRenderer imageRenderer = getImageRenderer(graphics2D, contentType);
                    if (imageRenderer.canRender(contentType)) {
                        imageRenderer.loadImage(data, contentType);
                        imageRenderer.drawImage(graphics2D, anchor, clipping);
                        return;
                    }
                } catch (IOException e) {
                    LOG.atError().withThrowable(e).log("image can't be loaded/rendered.");
                }
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        LOG.atWarn().log("No suitable image renderer found for content-type '{}' - include poi-scratchpad (for wmf/emf) or poi-ooxml (for svg) jars - svgs/batik doesn't work on the module-path!", (java.lang.Object) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        return r3;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0031 */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031 A[LOOP:0: B:11:0x0031->B:13:0x003b, LOOP_START, SYNTHETIC, Splitter:B:11:0x0031] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.sl.draw.ImageRenderer getImageRenderer(java.awt.Graphics2D r3, java.lang.String r4) {
        /*
            if (r3 == 0) goto L_0x000b
            org.apache.poi.sl.draw.Drawable$DrawableHint r0 = org.apache.poi.sl.draw.Drawable.IMAGE_RENDERER
            java.lang.Object r3 = r3.getRenderingHint(r0)
            org.apache.poi.sl.draw.ImageRenderer r3 = (org.apache.poi.sl.draw.ImageRenderer) r3
            goto L_0x000c
        L_0x000b:
            r3 = 0
        L_0x000c:
            if (r3 == 0) goto L_0x0015
            boolean r0 = r3.canRender(r4)
            if (r0 == 0) goto L_0x0015
            return r3
        L_0x0015:
            org.apache.poi.sl.draw.BitmapImageRenderer r3 = new org.apache.poi.sl.draw.BitmapImageRenderer
            r3.<init>()
            boolean r0 = r3.canRender(r4)
            if (r0 == 0) goto L_0x0021
            return r3
        L_0x0021:
            java.lang.Class<org.apache.poi.sl.draw.DrawPictureShape> r0 = org.apache.poi.sl.draw.DrawPictureShape.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            java.lang.Class<org.apache.poi.sl.draw.ImageRenderer> r1 = org.apache.poi.sl.draw.ImageRenderer.class
            java.util.ServiceLoader r0 = java.util.ServiceLoader.load(r1, r0)
            java.util.Iterator r0 = r0.iterator()
        L_0x0031:
            java.lang.Object r1 = r0.next()     // Catch:{ NoSuchElementException -> 0x003e, Exception | ServiceConfigurationError -> 0x0031 }
            org.apache.poi.sl.draw.ImageRenderer r1 = (org.apache.poi.sl.draw.ImageRenderer) r1     // Catch:{ NoSuchElementException -> 0x003e, Exception | ServiceConfigurationError -> 0x0031 }
            boolean r2 = r1.canRender(r4)     // Catch:{ NoSuchElementException -> 0x003e, Exception | ServiceConfigurationError -> 0x0031 }
            if (r2 == 0) goto L_0x0031
            return r1
        L_0x003e:
            org.apache.logging.log4j.Logger r0 = LOG
            org.apache.logging.log4j.LogBuilder r0 = r0.atWarn()
            java.lang.String r1 = "No suitable image renderer found for content-type '{}' - include poi-scratchpad (for wmf/emf) or poi-ooxml (for svg) jars - svgs/batik doesn't work on the module-path!"
            r0.log((java.lang.String) r1, (java.lang.Object) r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawPictureShape.getImageRenderer(java.awt.Graphics2D, java.lang.String):org.apache.poi.sl.draw.ImageRenderer");
    }

    /* access modifiers changed from: protected */
    public PictureShape<?, ?> getShape() {
        return (PictureShape) this.shape;
    }

    public void resize() {
        PictureShape<?, ?> shape = getShape();
        Dimension imageDimension = shape.getPictureData().getImageDimension();
        Rectangle2D anchor = shape.getAnchor();
        shape.setAnchor(new Rectangle2D.Double(anchor.getX(), anchor.getY(), imageDimension.getWidth(), imageDimension.getHeight()));
    }

    public void resize(Rectangle2D rectangle2D) {
        resize(rectangle2D, RectAlign.CENTER);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006d, code lost:
        r2 = r2 + r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006f, code lost:
        r2 = r2 + r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0070, code lost:
        r7 = r7 / 2.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0071, code lost:
        r4 = r4 + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0074, code lost:
        r2 = r2 + r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0075, code lost:
        r1.setAnchor(new java.awt.geom.Rectangle2D.Double(r2, r4, r19, r21));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0081, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void resize(java.awt.geom.Rectangle2D r26, org.apache.poi.sl.usermodel.RectAlign r27) {
        /*
            r25 = this;
            r0 = r26
            org.apache.poi.sl.usermodel.PictureShape r1 = r25.getShape()
            org.apache.poi.sl.usermodel.PictureData r2 = r1.getPictureData()
            java.awt.Dimension r2 = r2.getImageDimension()
            int r3 = r2.width
            if (r3 <= 0) goto L_0x0086
            int r3 = r2.height
            if (r3 > 0) goto L_0x0018
            goto L_0x0086
        L_0x0018:
            double r3 = r26.getWidth()
            double r5 = r26.getHeight()
            int r7 = r2.width
            double r7 = (double) r7
            double r7 = r3 / r7
            int r9 = r2.height
            double r9 = (double) r9
            double r9 = r5 / r9
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            r12 = 0
            if (r11 <= 0) goto L_0x0044
            int r2 = r2.width
            double r2 = (double) r2
            double r3 = r9 * r2
            double r7 = r26.getWidth()
            double r7 = r7 - r3
            r19 = r3
            r21 = r5
            r23 = r7
            r7 = r12
            r12 = r23
            goto L_0x0055
        L_0x0044:
            int r5 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r5 <= 0) goto L_0x0082
            int r2 = r2.height
            double r5 = (double) r2
            double r5 = r5 * r7
            double r7 = r26.getHeight()
            double r7 = r7 - r5
            r19 = r3
            r21 = r5
        L_0x0055:
            double r2 = r26.getX()
            double r4 = r26.getY()
            int[] r0 = org.apache.poi.sl.draw.DrawPictureShape.AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$RectAlign
            int r6 = r27.ordinal()
            r0 = r0[r6]
            r9 = 4611686018427387904(0x4000000000000000, double:2.0)
            switch(r0) {
                case 1: goto L_0x0073;
                case 2: goto L_0x0074;
                case 3: goto L_0x006f;
                case 4: goto L_0x006d;
                case 5: goto L_0x006c;
                case 6: goto L_0x0071;
                case 7: goto L_0x0070;
                case 8: goto L_0x0075;
                default: goto L_0x006a;
            }
        L_0x006a:
            double r12 = r12 / r9
            goto L_0x006f
        L_0x006c:
            double r12 = r12 / r9
        L_0x006d:
            double r2 = r2 + r12
            goto L_0x0071
        L_0x006f:
            double r2 = r2 + r12
        L_0x0070:
            double r7 = r7 / r9
        L_0x0071:
            double r4 = r4 + r7
            goto L_0x0075
        L_0x0073:
            double r12 = r12 / r9
        L_0x0074:
            double r2 = r2 + r12
        L_0x0075:
            r15 = r2
            r17 = r4
            java.awt.geom.Rectangle2D$Double r0 = new java.awt.geom.Rectangle2D$Double
            r14 = r0
            r14.<init>(r15, r17, r19, r21)
            r1.setAnchor(r0)
            return
        L_0x0082:
            r1.setAnchor(r0)
            return
        L_0x0086:
            r1.setAnchor(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawPictureShape.resize(java.awt.geom.Rectangle2D, org.apache.poi.sl.usermodel.RectAlign):void");
    }

    /* renamed from: org.apache.poi.sl.draw.DrawPictureShape$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$RectAlign;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.sl.usermodel.RectAlign[] r0 = org.apache.poi.sl.usermodel.RectAlign.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$RectAlign = r0
                org.apache.poi.sl.usermodel.RectAlign r1 = org.apache.poi.sl.usermodel.RectAlign.TOP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$RectAlign     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.RectAlign r1 = org.apache.poi.sl.usermodel.RectAlign.TOP_RIGHT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$RectAlign     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.RectAlign r1 = org.apache.poi.sl.usermodel.RectAlign.RIGHT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$RectAlign     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.RectAlign r1 = org.apache.poi.sl.usermodel.RectAlign.BOTTOM_RIGHT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$RectAlign     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.sl.usermodel.RectAlign r1 = org.apache.poi.sl.usermodel.RectAlign.BOTTOM     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$RectAlign     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.sl.usermodel.RectAlign r1 = org.apache.poi.sl.usermodel.RectAlign.BOTTOM_LEFT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$RectAlign     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.sl.usermodel.RectAlign r1 = org.apache.poi.sl.usermodel.RectAlign.LEFT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$RectAlign     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.sl.usermodel.RectAlign r1 = org.apache.poi.sl.usermodel.RectAlign.TOP_LEFT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawPictureShape.AnonymousClass1.<clinit>():void");
        }
    }
}
