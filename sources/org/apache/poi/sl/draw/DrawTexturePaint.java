package org.apache.poi.sl.draw;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.Internal;

@Internal
public class DrawTexturePaint extends TexturePaint {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Insets2D INSETS_EMPTY = new Insets2D(0.0d, 0.0d, 0.0d, 0.0d);
    private final PaintStyle.TexturePaint fill;
    private final double flipX;
    private final double flipY;
    private final ImageRenderer imgRdr;
    private final boolean isBitmapSrc;
    private final Shape shape;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DrawTexturePaint(ImageRenderer imageRenderer, BufferedImage bufferedImage, Shape shape2, PaintStyle.TexturePaint texturePaint, double d, double d2, boolean z) {
        super(bufferedImage, new Rectangle2D.Double(0.0d, 0.0d, (double) bufferedImage.getWidth(), (double) bufferedImage.getHeight()));
        BufferedImage bufferedImage2 = bufferedImage;
        this.imgRdr = imageRenderer;
        this.fill = texturePaint;
        this.shape = shape2;
        this.flipX = d;
        this.flipY = d2;
        this.isBitmapSrc = z;
    }

    public PaintContext createContext(ColorModel colorModel, Rectangle rectangle, Rectangle2D rectangle2D, AffineTransform affineTransform, RenderingHints renderingHints) {
        Rectangle2D rectangle2D2;
        AffineTransform affineTransform2 = affineTransform;
        Dimension2DDouble dimension2DDouble = new Dimension2DDouble();
        if (this.fill.isRotatedWithShape() || this.shape == null) {
            rectangle2D2 = rectangle2D;
        } else {
            AffineTransform affineTransform3 = new AffineTransform(affineTransform2);
            affineTransform3.preConcatenate(AffineTransform.getTranslateInstance(-affineTransform3.getTranslateX(), -affineTransform3.getTranslateY()));
            Point2D.Double doubleR = new Point2D.Double(1.0d, 0.0d);
            Point2D transform = affineTransform3.transform(doubleR, doubleR);
            double atan2 = Math.atan2(transform.getY(), transform.getX());
            if (atan2 != 0.0d) {
                affineTransform.rotate(-atan2, rectangle2D.getCenterX(), rectangle2D.getCenterY());
            }
            rectangle2D2 = AffineTransform.getRotateInstance(atan2, rectangle2D.getCenterX(), rectangle2D.getCenterY()).createTransformedShape(this.shape).getBounds2D();
        }
        dimension2DDouble.setSize(rectangle2D2.getWidth(), rectangle2D2.getHeight());
        affineTransform2.translate(rectangle2D2.getX(), rectangle2D2.getY());
        BufferedImage image = getImage(rectangle2D2);
        if (this.fill.getStretch() != null) {
            return new TexturePaint(image, new Rectangle2D.Double(0.0d, 0.0d, (double) image.getWidth(), (double) image.getHeight())).createContext(colorModel, rectangle, rectangle2D2, affineTransform, renderingHints);
        }
        if (this.fill.getScale() == null) {
            return DrawTexturePaint.super.createContext(colorModel, rectangle, rectangle2D, affineTransform, renderingHints);
        }
        return new TexturePaint(image, new Rectangle2D.Double(0.0d, 0.0d, (double) image.getWidth(), (double) image.getHeight())).createContext(colorModel, rectangle, rectangle2D, getTiledInstance(rectangle2D2, (AffineTransform) affineTransform.clone()), renderingHints);
    }

    public BufferedImage getImage(Rectangle2D rectangle2D) {
        BufferedImage image = DrawTexturePaint.super.getImage();
        Insets2D insets = this.fill.getInsets();
        Insets2D stretch = this.fill.getStretch();
        if (((insets == null || INSETS_EMPTY.equals(insets)) && stretch == null) || rectangle2D == null || rectangle2D.isEmpty()) {
            return image;
        }
        if (insets != null && !INSETS_EMPTY.equals(insets)) {
            int width = image.getWidth();
            double d = (double) width;
            double height = (double) image.getHeight();
            double d2 = d;
            image = image.getSubimage((int) ((Math.max(insets.left, 0.0d) / 100000.0d) * d), (int) ((Math.max(insets.top, 0.0d) / 100000.0d) * height), (int) ((((100000.0d - Math.max(insets.left, 0.0d)) - Math.max(insets.right, 0.0d)) / 100000.0d) * d), (int) ((((100000.0d - Math.max(insets.top, 0.0d)) - Math.max(insets.bottom, 0.0d)) / 100000.0d) * height));
            int max = (int) ((Math.max(-insets.top, 0.0d) / 100000.0d) * height);
            int max2 = (int) ((Math.max(-insets.left, 0.0d) / 100000.0d) * d2);
            int max3 = (int) ((Math.max(-insets.bottom, 0.0d) / 100000.0d) * height);
            int max4 = (int) ((Math.max(-insets.right, 0.0d) / 100000.0d) * d2);
            if (max > 0 || max2 > 0 || max3 > 0 || max4 > 0) {
                int[] iArr = new int[(image.getWidth() * image.getHeight())];
                image.getRGB(0, 0, image.getWidth(), image.getHeight(), iArr, 0, image.getWidth());
                BufferedImage bufferedImage = new BufferedImage(image.getWidth() + max2 + max4, image.getHeight() + max + max3, image.getType());
                bufferedImage.setRGB(max2, max, image.getWidth(), image.getHeight(), iArr, 0, image.getWidth());
                image = bufferedImage;
            }
        }
        if (stretch == null) {
            return image;
        }
        Rectangle2D.Double doubleR = new Rectangle2D.Double(0.0d, 0.0d, (double) image.getWidth(), (double) image.getHeight());
        Rectangle2D.Double doubleR2 = new Rectangle2D.Double((stretch.left / 100000.0d) * rectangle2D.getWidth(), (stretch.top / 100000.0d) * rectangle2D.getHeight(), (((100000.0d - stretch.left) - stretch.right) / 100000.0d) * rectangle2D.getWidth(), (((100000.0d - stretch.top) - stretch.bottom) / 100000.0d) * rectangle2D.getHeight());
        BufferedImage bufferedImage2 = new BufferedImage((int) rectangle2D.getWidth(), (int) rectangle2D.getHeight(), 2);
        Graphics2D createGraphics = bufferedImage2.createGraphics();
        createGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        createGraphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        createGraphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        createGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        createGraphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        createGraphics.setComposite(AlphaComposite.Clear);
        createGraphics.fillRect(0, 0, bufferedImage2.getWidth(), bufferedImage2.getHeight());
        createGraphics.setComposite(AlphaComposite.SrcOver);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(doubleR2.getCenterX(), doubleR2.getCenterY());
        affineTransform.scale(doubleR2.getWidth() / doubleR.getWidth(), doubleR2.getHeight() / doubleR.getHeight());
        affineTransform.translate(-doubleR.getCenterX(), -doubleR.getCenterY());
        createGraphics.drawRenderedImage(image, affineTransform);
        createGraphics.dispose();
        return bufferedImage2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0066, code lost:
        r4 = 0.0d;
        r8 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        r4 = (r14 - r6) / 2.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007b, code lost:
        r4 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0080, code lost:
        r4 = r14 - r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0082, code lost:
        r1.translate(r8, r4);
        r2 = r0.fill.getOffset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008b, code lost:
        if (r2 == null) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008d, code lost:
        r1.translate(r2.getX(), r2.getY());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0098, code lost:
        r4 = r3.getWidth();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009e, code lost:
        if (r0.isBitmapSrc == false) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a0, code lost:
        r6 = r0.flipX;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a3, code lost:
        r6 = 1.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a4, code lost:
        r4 = r4 / r6;
        r2 = r3.getHeight();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ab, code lost:
        if (r0.isBitmapSrc == false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ad, code lost:
        r10 = r0.flipY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00af, code lost:
        r1.scale(r4, r2 / r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b3, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.awt.geom.AffineTransform getTiledInstance(java.awt.geom.Rectangle2D r19, java.awt.geom.AffineTransform r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r20
            java.awt.image.BufferedImage r2 = r18.getImage()
            org.apache.poi.sl.usermodel.PaintStyle$TexturePaint r3 = r0.fill
            java.awt.geom.Dimension2D r3 = r3.getScale()
            int r4 = r2.getWidth()
            double r4 = (double) r4
            double r6 = r3.getWidth()
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r6 != 0) goto L_0x0021
            r6 = r10
            goto L_0x0025
        L_0x0021:
            double r6 = r3.getWidth()
        L_0x0025:
            double r4 = r4 * r6
            double r6 = r0.flipX
            double r4 = r4 / r6
            int r2 = r2.getHeight()
            double r6 = (double) r2
            double r12 = r3.getHeight()
            int r2 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x0038
            r12 = r10
            goto L_0x003c
        L_0x0038:
            double r12 = r3.getHeight()
        L_0x003c:
            double r6 = r6 * r12
            double r12 = r0.flipY
            double r6 = r6 / r12
            org.apache.poi.sl.usermodel.PaintStyle$TexturePaint r2 = r0.fill
            org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r2 = r2.getAlignment()
            double r12 = r19.getWidth()
            double r14 = r19.getHeight()
            int[] r16 = org.apache.poi.sl.draw.DrawTexturePaint.AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment
            if (r2 != 0) goto L_0x0054
            org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r2 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.TOP_LEFT
        L_0x0054:
            int r2 = r2.ordinal()
            r2 = r16[r2]
            r16 = 4611686018427387904(0x4000000000000000, double:2.0)
            switch(r2) {
                case 1: goto L_0x007d;
                case 2: goto L_0x007a;
                case 3: goto L_0x0077;
                case 4: goto L_0x0070;
                case 5: goto L_0x006c;
                case 6: goto L_0x0069;
                case 7: goto L_0x0063;
                case 8: goto L_0x005f;
                case 9: goto L_0x0061;
                default: goto L_0x005f;
            }
        L_0x005f:
            r4 = r8
            goto L_0x0082
        L_0x0061:
            double r12 = r12 - r4
            goto L_0x0066
        L_0x0063:
            double r12 = r12 - r4
            double r12 = r12 / r16
        L_0x0066:
            r4 = r8
            r8 = r12
            goto L_0x0082
        L_0x0069:
            double r8 = r12 - r4
            goto L_0x0073
        L_0x006c:
            double r14 = r14 - r6
            double r14 = r14 / r16
            goto L_0x007b
        L_0x0070:
            double r12 = r12 - r4
            double r8 = r12 / r16
        L_0x0073:
            double r14 = r14 - r6
            double r4 = r14 / r16
            goto L_0x0082
        L_0x0077:
            double r8 = r12 - r4
            goto L_0x0080
        L_0x007a:
            double r14 = r14 - r6
        L_0x007b:
            r4 = r14
            goto L_0x0082
        L_0x007d:
            double r12 = r12 - r4
            double r8 = r12 / r16
        L_0x0080:
            double r4 = r14 - r6
        L_0x0082:
            r1.translate(r8, r4)
            org.apache.poi.sl.usermodel.PaintStyle$TexturePaint r2 = r0.fill
            java.awt.geom.Point2D r2 = r2.getOffset()
            if (r2 == 0) goto L_0x0098
            double r4 = r2.getX()
            double r6 = r2.getY()
            r1.translate(r4, r6)
        L_0x0098:
            double r4 = r3.getWidth()
            boolean r2 = r0.isBitmapSrc
            if (r2 == 0) goto L_0x00a3
            double r6 = r0.flipX
            goto L_0x00a4
        L_0x00a3:
            r6 = r10
        L_0x00a4:
            double r4 = r4 / r6
            double r2 = r3.getHeight()
            boolean r6 = r0.isBitmapSrc
            if (r6 == 0) goto L_0x00af
            double r10 = r0.flipY
        L_0x00af:
            double r2 = r2 / r10
            r1.scale(r4, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawTexturePaint.getTiledInstance(java.awt.geom.Rectangle2D, java.awt.geom.AffineTransform):java.awt.geom.AffineTransform");
    }

    /* renamed from: org.apache.poi.sl.draw.DrawTexturePaint$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment[] r0 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment = r0
                org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r1 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r1 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.BOTTOM_LEFT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r1 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.BOTTOM_RIGHT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r1 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.CENTER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r1 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.LEFT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r1 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.RIGHT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r1 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.TOP     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r1 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.TOP_LEFT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$TextureAlignment     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.poi.sl.usermodel.PaintStyle$TextureAlignment r1 = org.apache.poi.sl.usermodel.PaintStyle.TextureAlignment.TOP_RIGHT     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawTexturePaint.AnonymousClass1.<clinit>():void");
        }
    }

    public ImageRenderer getImageRenderer() {
        return this.imgRdr;
    }

    public PaintStyle.TexturePaint getFill() {
        return this.fill;
    }

    public Shape getAwtShape() {
        return this.shape;
    }
}
