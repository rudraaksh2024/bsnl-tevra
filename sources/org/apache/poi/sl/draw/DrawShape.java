package org.apache.poi.sl.draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Locale;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.StrokeStyle;

public class DrawShape implements Drawable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected final Shape<?, ?> shape;

    private static double safeScale(double d, double d2) {
        if (d == 0.0d || d2 == 0.0d) {
            return 1.0d;
        }
        return d / d2;
    }

    public void draw(Graphics2D graphics2D) {
    }

    public void drawContent(Graphics2D graphics2D) {
    }

    public DrawShape(Shape<?, ?> shape2) {
        this.shape = shape2;
    }

    static boolean isHSLF(Object obj) {
        return obj.getClass().getName().toLowerCase(Locale.ROOT).contains("hslf");
    }

    public void applyTransform(Graphics2D graphics2D) {
        Rectangle2D anchor;
        Shape<?, ?> shape2 = this.shape;
        if ((shape2 instanceof PlaceableShape) && graphics2D != null && (anchor = getAnchor(graphics2D, (PlaceableShape<?, ?>) (PlaceableShape) shape2)) != null) {
            if (isHSLF(this.shape)) {
                flipHorizontal(graphics2D, anchor);
                flipVertical(graphics2D, anchor);
                rotate(graphics2D, anchor);
                return;
            }
            rotate(graphics2D, anchor);
            flipHorizontal(graphics2D, anchor);
            flipVertical(graphics2D, anchor);
        }
    }

    private void flipHorizontal(Graphics2D graphics2D, Rectangle2D rectangle2D) {
        if (((PlaceableShape) this.shape).getFlipHorizontal()) {
            graphics2D.translate(rectangle2D.getX() + rectangle2D.getWidth(), rectangle2D.getY());
            graphics2D.scale(-1.0d, 1.0d);
            graphics2D.translate(-rectangle2D.getX(), -rectangle2D.getY());
        }
    }

    private void flipVertical(Graphics2D graphics2D, Rectangle2D rectangle2D) {
        if (((PlaceableShape) this.shape).getFlipVertical()) {
            graphics2D.translate(rectangle2D.getX(), rectangle2D.getY() + rectangle2D.getHeight());
            graphics2D.scale(1.0d, -1.0d);
            graphics2D.translate(-rectangle2D.getX(), -rectangle2D.getY());
        }
    }

    private void rotate(Graphics2D graphics2D, Rectangle2D rectangle2D) {
        double rotation = ((PlaceableShape) this.shape).getRotation();
        if (rotation != 0.0d) {
            graphics2D.rotate(Math.toRadians(rotation), rectangle2D.getCenterX(), rectangle2D.getCenterY());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.awt.geom.AffineTransform} */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ac, code lost:
        r13 = r1.createTransformedShape(r0);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.awt.geom.Rectangle2D getAnchor(java.awt.Graphics2D r13, org.apache.poi.sl.usermodel.PlaceableShape<?, ?> r14) {
        /*
            java.awt.geom.Rectangle2D r0 = r14.getAnchor()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            boolean r2 = isHSLF(r14)
            if (r13 != 0) goto L_0x000f
            goto L_0x0018
        L_0x000f:
            org.apache.poi.sl.draw.Drawable$DrawableHint r1 = org.apache.poi.sl.draw.Drawable.GROUP_TRANSFORM
            java.lang.Object r13 = r13.getRenderingHint(r1)
            r1 = r13
            java.awt.geom.AffineTransform r1 = (java.awt.geom.AffineTransform) r1
        L_0x0018:
            if (r1 != 0) goto L_0x001f
            java.awt.geom.AffineTransform r1 = new java.awt.geom.AffineTransform
            r1.<init>()
        L_0x001f:
            double r13 = r14.getRotation()
            r3 = 4645040803167600640(0x4076800000000000, double:360.0)
            double r13 = r13 % r3
            double r13 = r13 + r3
            double r13 = r13 % r3
            int r13 = (int) r13
            int r13 = r13 + 45
            int r13 = r13 / 90
            int r13 = r13 % 4
            r14 = 1
            if (r13 == r14) goto L_0x0038
            r14 = 3
            if (r13 != r14) goto L_0x00a5
        L_0x0038:
            java.awt.Shape r13 = r1.createTransformedShape(r0)
            java.awt.geom.Rectangle2D r13 = r13.getBounds2D()
            double r9 = r13.getCenterX()
            double r11 = r13.getCenterY()
            java.awt.geom.AffineTransform r14 = new java.awt.geom.AffineTransform
            r14.<init>()
            if (r2 != 0) goto L_0x0059
            r4 = 1
            r3 = r14
            r5 = r9
            r7 = r11
            r3.quadrantRotate(r4, r5, r7)
            r14.concatenate(r1)
        L_0x0059:
            r4 = 3
            r3 = r14
            r5 = r9
            r7 = r11
            r3.quadrantRotate(r4, r5, r7)
            if (r2 == 0) goto L_0x0065
            r14.concatenate(r1)
        L_0x0065:
            java.awt.Shape r14 = r14.createTransformedShape(r0)
            java.awt.geom.Rectangle2D r14 = r14.getBounds2D()
            double r2 = r13.getWidth()
            double r4 = r14.getWidth()
            double r2 = safeScale(r2, r4)
            double r4 = r13.getHeight()
            double r13 = r14.getHeight()
            double r13 = safeScale(r4, r13)
            double r4 = r0.getCenterX()
            double r6 = r0.getCenterY()
            java.awt.geom.AffineTransform r8 = new java.awt.geom.AffineTransform
            r8.<init>()
            r8.translate(r4, r6)
            r8.scale(r13, r2)
            double r13 = -r4
            double r2 = -r6
            r8.translate(r13, r2)
            java.awt.Shape r13 = r8.createTransformedShape(r0)
            java.awt.geom.Rectangle2D r0 = r13.getBounds2D()
        L_0x00a5:
            boolean r13 = r1.isIdentity()
            if (r13 == 0) goto L_0x00ac
            return r0
        L_0x00ac:
            java.awt.Shape r13 = r1.createTransformedShape(r0)
            if (r13 == 0) goto L_0x00b6
            java.awt.geom.Rectangle2D r0 = r13.getBounds2D()
        L_0x00b6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawShape.getAnchor(java.awt.Graphics2D, org.apache.poi.sl.usermodel.PlaceableShape):java.awt.geom.Rectangle2D");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r1 = (java.awt.geom.AffineTransform) r1.getRenderingHint(org.apache.poi.sl.draw.Drawable.GROUP_TRANSFORM);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.awt.geom.Rectangle2D getAnchor(java.awt.Graphics2D r1, java.awt.geom.Rectangle2D r2) {
        /*
            if (r1 != 0) goto L_0x0003
            return r2
        L_0x0003:
            org.apache.poi.sl.draw.Drawable$DrawableHint r0 = org.apache.poi.sl.draw.Drawable.GROUP_TRANSFORM
            java.lang.Object r1 = r1.getRenderingHint(r0)
            java.awt.geom.AffineTransform r1 = (java.awt.geom.AffineTransform) r1
            if (r1 == 0) goto L_0x0021
            boolean r0 = r1.isIdentity()
            if (r0 != 0) goto L_0x0021
            java.awt.Shape r0 = r1.createTransformedShape(r2)
            if (r0 == 0) goto L_0x0021
            java.awt.Shape r1 = r1.createTransformedShape(r2)
            java.awt.geom.Rectangle2D r2 = r1.getBounds2D()
        L_0x0021:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawShape.getAnchor(java.awt.Graphics2D, java.awt.geom.Rectangle2D):java.awt.geom.Rectangle2D");
    }

    /* access modifiers changed from: protected */
    public Shape<?, ?> getShape() {
        return this.shape;
    }

    protected static BasicStroke getStroke(StrokeStyle strokeStyle) {
        float[] fArr;
        int i;
        float lineWidth = (float) strokeStyle.getLineWidth();
        if (lineWidth == 0.0f) {
            lineWidth = 0.25f;
        }
        float f = lineWidth;
        StrokeStyle.LineDash lineDash = strokeStyle.getLineDash();
        if (lineDash == null) {
            lineDash = StrokeStyle.LineDash.SOLID;
        }
        int[] iArr = lineDash.pattern;
        if (iArr != null) {
            float[] fArr2 = new float[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                fArr2[i2] = ((float) iArr[i2]) * Math.max(1.0f, f);
            }
            fArr = fArr2;
        } else {
            fArr = null;
        }
        StrokeStyle.LineCap lineCap = strokeStyle.getLineCap();
        if (lineCap == null) {
            lineCap = StrokeStyle.LineCap.FLAT;
        }
        int i3 = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCap[lineCap.ordinal()];
        int i4 = 1;
        if (i3 != 1) {
            i4 = 2;
            if (i3 != 2) {
                i = 0;
                return new BasicStroke(f, i, 1, 10.0f, fArr, 0.0f);
            }
        }
        i = i4;
        return new BasicStroke(f, i, 1, 10.0f, fArr, 0.0f);
    }

    /* renamed from: org.apache.poi.sl.draw.DrawShape$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCap;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.sl.usermodel.StrokeStyle$LineCap[] r0 = org.apache.poi.sl.usermodel.StrokeStyle.LineCap.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCap = r0
                org.apache.poi.sl.usermodel.StrokeStyle$LineCap r1 = org.apache.poi.sl.usermodel.StrokeStyle.LineCap.ROUND     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCap     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.StrokeStyle$LineCap r1 = org.apache.poi.sl.usermodel.StrokeStyle.LineCap.SQUARE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$StrokeStyle$LineCap     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.StrokeStyle$LineCap r1 = org.apache.poi.sl.usermodel.StrokeStyle.LineCap.FLAT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawShape.AnonymousClass1.<clinit>():void");
        }
    }
}
