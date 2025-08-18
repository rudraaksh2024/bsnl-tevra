package org.apache.poi.sl.draw;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.IllegalPathStateException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Hashtable;
import org.apache.poi.util.Internal;

@Internal
public class PathGradientPaint implements Paint {
    /* access modifiers changed from: private */
    public final int capStyle;
    /* access modifiers changed from: private */
    public final Color[] colors;
    /* access modifiers changed from: private */
    public final float[] fractions;
    /* access modifiers changed from: private */
    public final int joinStyle;
    private final int transparency;

    PathGradientPaint(float[] fArr, Color[] colorArr) {
        this(fArr, colorArr, 1, 1);
    }

    private PathGradientPaint(float[] fArr, Color[] colorArr, int i, int i2) {
        this.colors = (Color[]) colorArr.clone();
        this.fractions = (float[]) fArr.clone();
        this.capStyle = i;
        this.joinStyle = i2;
        int i3 = 1;
        boolean z = true;
        for (Color color : colorArr) {
            if (color != null) {
                z = z && color.getAlpha() == 255;
            }
        }
        this.transparency = !z ? 3 : i3;
    }

    public PathGradientContext createContext(ColorModel colorModel, Rectangle rectangle, Rectangle2D rectangle2D, AffineTransform affineTransform, RenderingHints renderingHints) {
        return new PathGradientContext(this, colorModel, rectangle, rectangle2D, affineTransform, renderingHints);
    }

    public int getTransparency() {
        return this.transparency;
    }

    public class PathGradientContext implements PaintContext {
        final Rectangle deviceBounds;
        final int gradientSteps;
        final RenderingHints hints;
        final PaintContext pCtx;
        WritableRaster raster;
        protected final Shape shape;
        final /* synthetic */ PathGradientPaint this$0;
        final Rectangle2D userBounds;
        protected final AffineTransform xform;

        public void dispose() {
        }

        PathGradientContext(PathGradientPaint pathGradientPaint, ColorModel colorModel, Rectangle rectangle, Rectangle2D rectangle2D, AffineTransform affineTransform, RenderingHints renderingHints) {
            RenderingHints renderingHints2 = renderingHints;
            this.this$0 = pathGradientPaint;
            Shape shape2 = (Shape) renderingHints2.get(Drawable.GRADIENT_SHAPE);
            this.shape = shape2;
            if (shape2 != null) {
                this.deviceBounds = rectangle;
                this.userBounds = rectangle2D;
                this.xform = affineTransform;
                this.hints = renderingHints2;
                int gradientSteps2 = getGradientSteps(shape2);
                this.gradientSteps = gradientSteps2;
                LinearGradientPaint linearGradientPaint = new LinearGradientPaint(new Point2D.Double(0.0d, 0.0d), new Point2D.Double((double) gradientSteps2, 0.0d), pathGradientPaint.fractions, pathGradientPaint.colors, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform());
                Rectangle rectangle2 = new Rectangle(0, 0, gradientSteps2, 1);
                this.pCtx = linearGradientPaint.createContext(colorModel, rectangle2, rectangle2, new AffineTransform(), renderingHints);
                return;
            }
            throw new IllegalPathStateException("PathGradientPaint needs a shape to be set via the rendering hint Drawable.GRADIANT_SHAPE.");
        }

        public ColorModel getColorModel() {
            return this.pCtx.getColorModel();
        }

        public Raster getRaster(int i, int i2, int i3, int i4) {
            ColorModel colorModel = getColorModel();
            this.raster = createRaster();
            WritableRaster createCompatibleWritableRaster = colorModel.createCompatibleWritableRaster(i3, i4);
            Rectangle2D.Double doubleR = new Rectangle2D.Double((double) i, (double) i2, (double) i3, (double) i4);
            if (!doubleR.intersects(this.deviceBounds)) {
                return createCompatibleWritableRaster;
            }
            Rectangle2D.Double doubleR2 = new Rectangle2D.Double();
            Rectangle2D.intersect(doubleR, this.deviceBounds, doubleR2);
            int x = (int) (doubleR2.getX() - this.deviceBounds.getX());
            int y = (int) (doubleR2.getY() - this.deviceBounds.getY());
            int width = (int) doubleR2.getWidth();
            int height = (int) doubleR2.getHeight();
            Object dataElements = this.raster.getDataElements(x, y, width, height, (Object) null);
            createCompatibleWritableRaster.setDataElements((int) (doubleR2.getX() - doubleR.getX()), (int) (doubleR2.getY() - doubleR.getY()), width, height, dataElements);
            return createCompatibleWritableRaster;
        }

        /* access modifiers changed from: package-private */
        public int getGradientSteps(Shape shape2) {
            Rectangle bounds = shape2.getBounds();
            int max = (int) (Math.max(bounds.getWidth(), bounds.getHeight()) / 2.0d);
            int i = 1;
            while (i < max - 1) {
                int i2 = ((max - i) / 2) + i;
                if (new Area(new BasicStroke((float) i2, this.this$0.capStyle, this.this$0.joinStyle).createStrokedShape(shape2)).isSingular()) {
                    max = i2;
                } else {
                    i = i2;
                }
            }
            return Math.max(max, 1);
        }

        public WritableRaster createRaster() {
            WritableRaster writableRaster = this.raster;
            if (writableRaster != null) {
                return writableRaster;
            }
            ColorModel colorModel = getColorModel();
            this.raster = colorModel.createCompatibleWritableRaster((int) this.deviceBounds.getWidth(), (int) this.deviceBounds.getHeight());
            Graphics2D createGraphics = new BufferedImage(colorModel, this.raster, false, (Hashtable) null).createGraphics();
            createGraphics.setRenderingHints(this.hints);
            createGraphics.translate(-this.deviceBounds.getX(), -this.deviceBounds.getY());
            createGraphics.transform(this.xform);
            Raster raster2 = this.pCtx.getRaster(0, 0, this.gradientSteps, 1);
            int numComponents = colorModel.getNumComponents();
            int[] iArr = new int[numComponents];
            for (int i = this.gradientSteps - 1; i >= 0; i--) {
                raster2.getPixel((this.gradientSteps - i) - 1, 0, iArr);
                Color color = new Color(iArr[0], iArr[1], iArr[2]);
                if (numComponents == 4) {
                    createGraphics.setComposite(AlphaComposite.getInstance(2, ((float) iArr[3]) / 255.0f));
                }
                createGraphics.setStroke(new BasicStroke(((float) i) + 1.0f, this.this$0.capStyle, this.this$0.joinStyle));
                createGraphics.setColor(color);
                if (i == this.gradientSteps - 1) {
                    createGraphics.fill(this.shape);
                }
                createGraphics.draw(this.shape);
            }
            createGraphics.dispose();
            return this.raster;
        }
    }
}
