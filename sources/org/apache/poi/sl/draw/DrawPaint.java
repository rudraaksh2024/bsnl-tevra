package org.apache.poi.sl.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.sl.draw.geom.ArcToCommand;
import org.apache.poi.sl.usermodel.AbstractColorStyle;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.util.Dimension2DDouble;

public class DrawPaint {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = LogManager.getLogger((Class<?>) DrawPaint.class);
    private static final Color TRANSPARENT = new Color(1.0f, 1.0f, 1.0f, 0.0f);
    protected PlaceableShape<?, ?> shape;

    private static double HUE2RGB(double d, double d2, double d3) {
        double d4;
        if (d3 < 0.0d) {
            d3 += 1.0d;
        }
        if (d3 > 1.0d) {
            d3 -= 1.0d;
        }
        if (d3 * 6.0d < 1.0d) {
            d4 = (d2 - d) * 6.0d * d3;
        } else if (d3 * 2.0d < 1.0d) {
            return d2;
        } else {
            if (3.0d * d3 >= 2.0d) {
                return d;
            }
            d4 = (d2 - d) * 6.0d * (0.6666666666666666d - d3);
        }
        return d + d4;
    }

    private static double getCenterVal(double d, double d2, double d3, double d4) {
        double d5 = d2 - d;
        double d6 = d + (d5 * d3);
        double d7 = d5 * d4;
        return d6 + (((d3 + d4 <= 1.0d ? d2 - d7 : d2 + d7) - d6) / 2.0d);
    }

    private static double getScale(double d, double d2, double d3, double d4) {
        double d5 = d2 - d;
        double d6 = d + (d5 * d3);
        int i = ((d3 + d4) > 1.0d ? 1 : ((d3 + d4) == 1.0d ? 0 : -1));
        double d7 = d4 * d5;
        double d8 = i <= 0 ? d2 - d7 : d2 + d7;
        if (d5 == 0.0d) {
            return 1.0d;
        }
        return (d8 - d6) / d5;
    }

    public DrawPaint(PlaceableShape<?, ?> placeableShape) {
        this.shape = placeableShape;
    }

    private static class SimpleSolidPaint implements PaintStyle.SolidPaint {
        private final ColorStyle solidColor;

        SimpleSolidPaint(final Color color) {
            if (color != null) {
                this.solidColor = new AbstractColorStyle() {
                    public int getHueMod() {
                        return -1;
                    }

                    public int getHueOff() {
                        return -1;
                    }

                    public int getLumMod() {
                        return -1;
                    }

                    public int getLumOff() {
                        return -1;
                    }

                    public int getSatMod() {
                        return -1;
                    }

                    public int getSatOff() {
                        return -1;
                    }

                    public int getShade() {
                        return -1;
                    }

                    public int getTint() {
                        return -1;
                    }

                    public Color getColor() {
                        return new Color(color.getRed(), color.getGreen(), color.getBlue());
                    }

                    public int getAlpha() {
                        return (int) Math.round((((double) color.getAlpha()) * 100000.0d) / 255.0d);
                    }
                };
                return;
            }
            throw new NullPointerException("Color needs to be specified");
        }

        SimpleSolidPaint(ColorStyle colorStyle) {
            if (colorStyle != null) {
                this.solidColor = colorStyle;
                return;
            }
            throw new NullPointerException("Color needs to be specified");
        }

        public ColorStyle getSolidColor() {
            return this.solidColor;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PaintStyle.SolidPaint)) {
                return false;
            }
            return Objects.equals(getSolidColor(), ((PaintStyle.SolidPaint) obj).getSolidColor());
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.solidColor});
        }
    }

    public static PaintStyle.SolidPaint createSolidPaint(Color color) {
        if (color == null) {
            return null;
        }
        return new SimpleSolidPaint(color);
    }

    public static PaintStyle.SolidPaint createSolidPaint(ColorStyle colorStyle) {
        if (colorStyle == null) {
            return null;
        }
        return new SimpleSolidPaint(colorStyle);
    }

    public Paint getPaint(Graphics2D graphics2D, PaintStyle paintStyle) {
        return getPaint(graphics2D, paintStyle, PaintStyle.PaintModifier.NORM);
    }

    public Paint getPaint(Graphics2D graphics2D, PaintStyle paintStyle, PaintStyle.PaintModifier paintModifier) {
        if (paintModifier == PaintStyle.PaintModifier.NONE) {
            return TRANSPARENT;
        }
        if (paintStyle instanceof PaintStyle.SolidPaint) {
            return getSolidPaint((PaintStyle.SolidPaint) paintStyle, graphics2D, paintModifier);
        }
        if (paintStyle instanceof PaintStyle.GradientPaint) {
            return getGradientPaint((PaintStyle.GradientPaint) paintStyle, graphics2D);
        }
        if (paintStyle instanceof PaintStyle.TexturePaint) {
            return getTexturePaint((PaintStyle.TexturePaint) paintStyle, graphics2D);
        }
        return TRANSPARENT;
    }

    /* access modifiers changed from: protected */
    public Paint getSolidPaint(PaintStyle.SolidPaint solidPaint, Graphics2D graphics2D, final PaintStyle.PaintModifier paintModifier) {
        final ColorStyle solidColor = solidPaint.getSolidColor();
        return applyColorTransform(new AbstractColorStyle() {
            public Color getColor() {
                return solidColor.getColor();
            }

            public int getAlpha() {
                return solidColor.getAlpha();
            }

            public int getHueOff() {
                return solidColor.getHueOff();
            }

            public int getHueMod() {
                return solidColor.getHueMod();
            }

            public int getSatOff() {
                return solidColor.getSatOff();
            }

            public int getSatMod() {
                return solidColor.getSatMod();
            }

            public int getLumOff() {
                return solidColor.getLumOff();
            }

            public int getLumMod() {
                return solidColor.getLumMod();
            }

            public int getShade() {
                return scale(solidColor.getShade(), PaintStyle.PaintModifier.DARKEN_LESS, PaintStyle.PaintModifier.DARKEN);
            }

            public int getTint() {
                return scale(solidColor.getTint(), PaintStyle.PaintModifier.LIGHTEN_LESS, PaintStyle.PaintModifier.LIGHTEN);
            }

            private int scale(int i, PaintStyle.PaintModifier paintModifier, PaintStyle.PaintModifier paintModifier2) {
                if (i == -1) {
                    return -1;
                }
                PaintStyle.PaintModifier paintModifier3 = paintModifier;
                return Math.min(BZip2Constants.BASEBLOCKSIZE, Math.max(0, i) + (paintModifier3 == paintModifier ? 20000 : paintModifier3 == paintModifier2 ? 40000 : 0));
            }
        });
    }

    /* access modifiers changed from: protected */
    public Paint getGradientPaint(PaintStyle.GradientPaint gradientPaint, Graphics2D graphics2D) {
        int i = AnonymousClass2.$SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$GradientPaint$GradientType[gradientPaint.getGradientType().ordinal()];
        if (i == 1) {
            return createLinearGradientPaint(gradientPaint, graphics2D);
        }
        if (i == 2 || i == 3) {
            return createRadialGradientPaint(gradientPaint, graphics2D);
        }
        if (i == 4) {
            return createPathGradientPaint(gradientPaint, graphics2D);
        }
        throw new UnsupportedOperationException("gradient fill of type " + gradientPaint + " not supported.");
    }

    /* access modifiers changed from: protected */
    public Paint getTexturePaint(PaintStyle.TexturePaint texturePaint, Graphics2D graphics2D) {
        Throwable th;
        double d;
        double d2;
        PaintStyle.TexturePaint texturePaint2;
        double d3;
        Graphics2D graphics2D2 = graphics2D;
        String contentType = texturePaint.getContentType();
        if (contentType == null || contentType.isEmpty()) {
            return TRANSPARENT;
        }
        ImageRenderer imageRenderer = DrawPictureShape.getImageRenderer(graphics2D2, contentType);
        Rectangle2D anchor = this.shape.getAnchor();
        try {
            InputStream imageData = texturePaint.getImageData();
            if (imageData == null) {
                try {
                    Color color = TRANSPARENT;
                    if (imageData != null) {
                        imageData.close();
                    }
                    return color;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    if (imageData != null) {
                        imageData.close();
                    }
                    throw th3;
                }
            } else {
                Boolean bool = (Boolean) graphics2D2.getRenderingHint(Drawable.CACHE_IMAGE_SOURCE);
                imageRenderer.setCacheInput(bool != null && bool.booleanValue());
                imageRenderer.loadImage(imageData, contentType);
                int alpha = texturePaint.getAlpha();
                if (alpha >= 0 && alpha < 100000) {
                    imageRenderer.setAlpha((double) (((float) alpha) / 100000.0f));
                }
                Dimension2DDouble dimension = imageRenderer.getDimension();
                if ("image/x-wmf".contains(contentType)) {
                    dimension = new Dimension2DDouble(anchor.getWidth(), anchor.getHeight());
                }
                BufferedImage image = imageRenderer.getImage(dimension);
                if (image == null) {
                    LOG.atError().log("Can't load image data");
                    Color color2 = TRANSPARENT;
                    if (imageData != null) {
                        imageData.close();
                    }
                    return color2;
                }
                PaintStyle.FlipMode flipMode = texturePaint.getFlipMode();
                double d4 = 1.0d;
                if (flipMode == null || flipMode == PaintStyle.FlipMode.NONE) {
                    texturePaint2 = texturePaint;
                    d = 1.0d;
                    d2 = 1.0d;
                } else {
                    int width = image.getWidth();
                    int height = image.getHeight();
                    int i = AnonymousClass2.$SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$FlipMode[flipMode.ordinal()];
                    if (i == 1) {
                        d3 = 1.0d;
                        d4 = 2.0d;
                    } else if (i != 2) {
                        if (i == 3) {
                            d4 = 2.0d;
                        }
                        d3 = d4;
                    } else {
                        d3 = 2.0d;
                    }
                    BufferedImage bufferedImage = new BufferedImage((int) (((double) width) * d4), (int) (((double) height) * d3), 2);
                    Graphics2D createGraphics = bufferedImage.createGraphics();
                    createGraphics.drawImage(image, 0, 0, (ImageObserver) null);
                    int i2 = AnonymousClass2.$SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$FlipMode[flipMode.ordinal()];
                    if (i2 == 1) {
                        createGraphics.drawImage(image, width * 2, 0, -width, height, (ImageObserver) null);
                    } else if (i2 == 2) {
                        createGraphics.drawImage(image, 0, height * 2, width, -height, (ImageObserver) null);
                    } else if (i2 == 3) {
                        int i3 = width * 2;
                        int i4 = -width;
                        createGraphics.drawImage(image, i3, 0, i4, height, (ImageObserver) null);
                        int i5 = height * 2;
                        int i6 = -height;
                        createGraphics.drawImage(image, 0, i5, width, i6, (ImageObserver) null);
                        createGraphics.drawImage(image, i3, i5, i4, i6, (ImageObserver) null);
                    }
                    createGraphics.dispose();
                    texturePaint2 = texturePaint;
                    d2 = d4;
                    image = bufferedImage;
                    d = d3;
                }
                DrawTexturePaint drawTexturePaint = new DrawTexturePaint(imageRenderer, colorizePattern(texturePaint2, image), (Shape) graphics2D2.getRenderingHint(Drawable.GRADIENT_SHAPE), texturePaint, d2, d, imageRenderer instanceof BitmapImageRenderer);
                if (imageData != null) {
                    imageData.close();
                }
                return drawTexturePaint;
            }
        } catch (IOException e) {
            LOG.atError().withThrowable(e).log("Can't load image data - using transparent color");
            return TRANSPARENT;
        } catch (Throwable th4) {
            th.addSuppressed(th4);
        }
    }

    /* renamed from: org.apache.poi.sl.draw.DrawPaint$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$FlipMode;
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$GradientPaint$GradientType;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        static {
            /*
                org.apache.poi.sl.usermodel.PaintStyle$FlipMode[] r0 = org.apache.poi.sl.usermodel.PaintStyle.FlipMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$FlipMode = r0
                r1 = 1
                org.apache.poi.sl.usermodel.PaintStyle$FlipMode r2 = org.apache.poi.sl.usermodel.PaintStyle.FlipMode.X     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$FlipMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.PaintStyle$FlipMode r3 = org.apache.poi.sl.usermodel.PaintStyle.FlipMode.Y     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$FlipMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.PaintStyle$FlipMode r4 = org.apache.poi.sl.usermodel.PaintStyle.FlipMode.XY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                org.apache.poi.sl.usermodel.PaintStyle$GradientPaint$GradientType[] r3 = org.apache.poi.sl.usermodel.PaintStyle.GradientPaint.GradientType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$GradientPaint$GradientType = r3
                org.apache.poi.sl.usermodel.PaintStyle$GradientPaint$GradientType r4 = org.apache.poi.sl.usermodel.PaintStyle.GradientPaint.GradientType.linear     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$GradientPaint$GradientType     // Catch:{ NoSuchFieldError -> 0x0043 }
                org.apache.poi.sl.usermodel.PaintStyle$GradientPaint$GradientType r3 = org.apache.poi.sl.usermodel.PaintStyle.GradientPaint.GradientType.rectangular     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$GradientPaint$GradientType     // Catch:{ NoSuchFieldError -> 0x004d }
                org.apache.poi.sl.usermodel.PaintStyle$GradientPaint$GradientType r1 = org.apache.poi.sl.usermodel.PaintStyle.GradientPaint.GradientType.circular     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$PaintStyle$GradientPaint$GradientType     // Catch:{ NoSuchFieldError -> 0x0058 }
                org.apache.poi.sl.usermodel.PaintStyle$GradientPaint$GradientType r1 = org.apache.poi.sl.usermodel.PaintStyle.GradientPaint.GradientType.shape     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawPaint.AnonymousClass2.<clinit>():void");
        }
    }

    private static BufferedImage colorizePattern(PaintStyle.TexturePaint texturePaint, BufferedImage bufferedImage) {
        List<ColorStyle> duoTone = texturePaint.getDuoTone();
        if (duoTone == null || duoTone.size() != 2) {
            return bufferedImage;
        }
        int i = 0;
        int sampleSize = bufferedImage.getSampleModel().getSampleSize(0);
        int max = Math.max(Math.min(sampleSize, 8), 1);
        int i2 = 1 << max;
        double max2 = ((double) i2) / ((double) (1 << Math.max(sampleSize, 1)));
        BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 13, new IndexColorModel(max, i2, linearBlendedColors(duoTone, i2), 0, true, -1, 0));
        WritableRaster raster = bufferedImage.getRaster();
        WritableRaster raster2 = bufferedImage2.getRaster();
        int width = bufferedImage.getWidth();
        int[] iArr = new int[width];
        while (i < bufferedImage.getHeight()) {
            raster.getSamples(0, i, width, 1, 0, iArr);
            scaleShades(iArr, max2);
            int[] iArr2 = iArr;
            int i3 = width;
            raster2.setSamples(0, i, i3, 1, 0, iArr2);
            i++;
            iArr = iArr2;
            width = i3;
        }
        return bufferedImage2;
    }

    private static void scaleShades(int[] iArr, double d) {
        if (d != 1.0d) {
            for (int i = 0; i < iArr.length; i++) {
                iArr[i] = (int) Math.rint(((double) iArr[i]) * d);
            }
        }
    }

    static /* synthetic */ Color[] lambda$linearBlendedColors$0(int i) {
        return new Color[i];
    }

    private static int[] linearBlendedColors(List<ColorStyle> list, int i) {
        int i2 = i;
        BufferedImage bufferedImage = new BufferedImage(i2, 1, 2);
        Graphics2D createGraphics = bufferedImage.createGraphics();
        float f = (float) i2;
        createGraphics.setPaint(new LinearGradientPaint(0.0f, 0.0f, f, 0.0f, new float[]{0.0f, 1.0f}, (Color[]) list.stream().map(new DrawPaint$$ExternalSyntheticLambda1()).toArray(new DrawPaint$$ExternalSyntheticLambda2())));
        createGraphics.fillRect(0, 0, i2, 1);
        createGraphics.dispose();
        return bufferedImage.getRGB(0, 0, i, 1, (int[]) null, 0, i2);
    }

    public static Color applyColorTransform(ColorStyle colorStyle) {
        if (colorStyle == null || colorStyle.getColor() == null) {
            return TRANSPARENT;
        }
        Color color = colorStyle.getColor();
        double alpha = getAlpha(color, colorStyle);
        double[] RGB2SCRGB = RGB2SCRGB(color);
        applyShade(RGB2SCRGB, colorStyle);
        applyTint(RGB2SCRGB, colorStyle);
        double[] RGB2HSL = RGB2HSL(SCRGB2RGB(RGB2SCRGB));
        applyHslModOff(RGB2HSL, 0, colorStyle.getHueMod(), colorStyle.getHueOff());
        applyHslModOff(RGB2HSL, 1, colorStyle.getSatMod(), colorStyle.getSatOff());
        applyHslModOff(RGB2HSL, 2, colorStyle.getLumMod(), colorStyle.getLumOff());
        return HSL2RGB(RGB2HSL[0], RGB2HSL[1], RGB2HSL[2], alpha);
    }

    private static double getAlpha(Color color, ColorStyle colorStyle) {
        double alpha = ((double) color.getAlpha()) / 255.0d;
        int alpha2 = colorStyle.getAlpha();
        if (alpha2 != -1) {
            alpha *= ((double) alpha2) / 100000.0d;
        }
        return Math.min(1.0d, Math.max(0.0d, alpha));
    }

    private static void applyHslModOff(double[] dArr, int i, int i2, int i3) {
        if (i2 != -1) {
            dArr[i] = dArr[i] * (((double) i2) / 100000.0d);
        }
        if (i3 != -1) {
            dArr[i] = dArr[i] + (((double) i3) / 1000.0d);
        }
    }

    private static void applyShade(double[] dArr, ColorStyle colorStyle) {
        int shade = colorStyle.getShade();
        if (shade != -1) {
            double d = ((double) shade) / 100000.0d;
            for (int i = 0; i < 3; i++) {
                dArr[i] = Math.max(0.0d, Math.min(1.0d, dArr[i] * d));
            }
        }
    }

    private static void applyTint(double[] dArr, ColorStyle colorStyle) {
        int tint = colorStyle.getTint();
        if (tint != -1 && tint != 0) {
            double d = ((double) tint) / 100000.0d;
            for (int i = 0; i < 3; i++) {
                dArr[i] = 1.0d - ((1.0d - dArr[i]) * d);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Paint createLinearGradientPaint(PaintStyle.GradientPaint gradientPaint, Graphics2D graphics2D) {
        double gradientAngle = gradientPaint.getGradientAngle();
        if (!gradientPaint.isRotatedWithShape()) {
            gradientAngle -= this.shape.getRotation();
        }
        Rectangle2D anchor = DrawShape.getAnchor(graphics2D, this.shape);
        if (anchor == null) {
            return TRANSPARENT;
        }
        AffineTransform rotateInstance = AffineTransform.getRotateInstance(Math.toRadians(ArcToCommand.convertOoxml2AwtAngle(-gradientAngle, anchor.getWidth(), anchor.getHeight())), anchor.getCenterX(), anchor.getCenterY());
        Point2D transform = rotateInstance.transform(new Point2D.Double(anchor.getCenterX() - (Math.sqrt(Math.pow(anchor.getWidth(), 2.0d) + Math.pow(anchor.getHeight(), 2.0d)) / 2.0d), anchor.getCenterY()), (Point2D) null);
        Point2D transform2 = rotateInstance.transform(new Point2D.Double(anchor.getMaxX(), anchor.getCenterY()), (Point2D) null);
        if (transform.equals(transform2) || gradientPaint.getGradientFractions().length < 2) {
            return null;
        }
        return safeFractions(new DrawPaint$$ExternalSyntheticLambda0(transform, transform2), gradientPaint);
    }

    static /* synthetic */ Paint lambda$createLinearGradientPaint$1(Point2D point2D, Point2D point2D2, float[] fArr, Color[] colorArr) {
        return new LinearGradientPaint(point2D, point2D2, fArr, colorArr);
    }

    /* access modifiers changed from: protected */
    public Paint createRadialGradientPaint(PaintStyle.GradientPaint gradientPaint, Graphics2D graphics2D) {
        Rectangle2D anchor = DrawShape.getAnchor(graphics2D, this.shape);
        if (anchor == null) {
            return TRANSPARENT;
        }
        Insets2D fillToInsets = gradientPaint.getFillToInsets();
        if (fillToInsets == null) {
            fillToInsets = new Insets2D(0.0d, 0.0d, 0.0d, 0.0d);
        }
        Point2D.Double doubleR = new Point2D.Double(anchor.getCenterX(), anchor.getCenterY());
        Point2D.Double doubleR2 = new Point2D.Double(getCenterVal(anchor.getMinX(), anchor.getMaxX(), fillToInsets.left, fillToInsets.right), getCenterVal(anchor.getMinY(), anchor.getMaxY(), fillToInsets.top, fillToInsets.bottom));
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(doubleR2.getX(), doubleR2.getY());
        affineTransform.scale(getScale(anchor.getMinX(), anchor.getMaxX(), fillToInsets.left, fillToInsets.right), getScale(anchor.getMinY(), anchor.getMaxY(), fillToInsets.top, fillToInsets.bottom));
        affineTransform.translate(-doubleR2.getX(), -doubleR2.getY());
        return safeFractions(new DrawPaint$$ExternalSyntheticLambda6(doubleR, (float) Math.max(anchor.getWidth(), anchor.getHeight()), doubleR2, affineTransform), gradientPaint);
    }

    static /* synthetic */ Paint lambda$createRadialGradientPaint$2(Point2D point2D, float f, Point2D point2D2, AffineTransform affineTransform, float[] fArr, Color[] colorArr) {
        return new RadialGradientPaint(point2D, f, point2D2, fArr, colorArr, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, affineTransform);
    }

    /* access modifiers changed from: protected */
    public Paint createPathGradientPaint(PaintStyle.GradientPaint gradientPaint, Graphics2D graphics2D) {
        return safeFractions(new DrawPaint$$ExternalSyntheticLambda4(), gradientPaint);
    }

    private Paint safeFractions(BiFunction<float[], Color[], Paint> biFunction, PaintStyle.GradientPaint gradientPaint) {
        Iterator it = Stream.of(gradientPaint.getGradientColors()).map(new DrawPaint$$ExternalSyntheticLambda5()).iterator();
        TreeMap treeMap = new TreeMap();
        for (float valueOf : gradientPaint.getGradientFractions()) {
            treeMap.put(Float.valueOf(valueOf), it.next());
        }
        return biFunction.apply(toArray(treeMap.keySet()), treeMap.values().toArray(new Color[0]));
    }

    static /* synthetic */ Color lambda$safeFractions$3(ColorStyle colorStyle) {
        return colorStyle == null ? TRANSPARENT : applyColorTransform(colorStyle);
    }

    private static float[] toArray(Collection<Float> collection) {
        float[] fArr = new float[collection.size()];
        collection.forEach(new DrawPaint$$ExternalSyntheticLambda3(fArr, new int[]{0}));
        return fArr;
    }

    static /* synthetic */ void lambda$toArray$4(float[] fArr, int[] iArr, Float f) {
        int i = iArr[0];
        iArr[0] = i + 1;
        fArr[i] = f.floatValue();
    }

    public static Color HSL2RGB(double d, double d2, double d3, double d4) {
        double d5 = d4;
        double max = Math.max(0.0d, Math.min(100.0d, d2));
        double max2 = Math.max(0.0d, Math.min(100.0d, d3));
        if (d5 < 0.0d || d5 > 1.0d) {
            throw new IllegalArgumentException("Color parameter outside of expected range - Alpha: " + d5);
        }
        double d6 = (d % 360.0d) / 360.0d;
        double d7 = max / 100.0d;
        double d8 = max2 / 100.0d;
        double d9 = d8 < 0.5d ? (d7 + 1.0d) * d8 : (d8 + d7) - (d7 * d8);
        double d10 = (d8 * 2.0d) - d9;
        double d11 = d9;
        return new Color((float) Math.min(Math.max(0.0d, HUE2RGB(d10, d11, d6 + 0.3333333333333333d)), 1.0d), (float) Math.min(Math.max(0.0d, HUE2RGB(d10, d11, d6)), 1.0d), (float) Math.min(Math.max(0.0d, HUE2RGB(d10, d11, d6 - 0.3333333333333333d)), 1.0d), (float) d5);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static double[] RGB2HSL(java.awt.Color r19) {
        /*
            r0 = 0
            r1 = r19
            float[] r0 = r1.getRGBColorComponents(r0)
            r1 = 0
            r2 = r0[r1]
            double r2 = (double) r2
            r4 = 1
            r5 = r0[r4]
            double r5 = (double) r5
            r7 = 2
            r0 = r0[r7]
            double r8 = (double) r0
            double r10 = java.lang.Math.min(r5, r8)
            double r10 = java.lang.Math.min(r2, r10)
            double r12 = java.lang.Math.max(r5, r8)
            double r12 = java.lang.Math.max(r2, r12)
            int r0 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            r14 = 0
            if (r0 != 0) goto L_0x002b
        L_0x0029:
            r5 = r14
            goto L_0x005b
        L_0x002b:
            int r16 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            r17 = 4633641066610819072(0x404e000000000000, double:60.0)
            if (r16 != 0) goto L_0x003f
            double r5 = r5 - r8
            double r5 = r5 * r17
            double r2 = r12 - r10
            double r5 = r5 / r2
            r2 = 4645040803167600640(0x4076800000000000, double:360.0)
            double r5 = r5 + r2
            double r5 = r5 % r2
            goto L_0x005b
        L_0x003f:
            int r16 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r16 != 0) goto L_0x004e
            double r8 = r8 - r2
            double r8 = r8 * r17
            double r2 = r12 - r10
            double r8 = r8 / r2
            r2 = 4638144666238189568(0x405e000000000000, double:120.0)
            double r5 = r8 + r2
            goto L_0x005b
        L_0x004e:
            int r8 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r8 != 0) goto L_0x0029
            double r2 = r2 - r5
            double r2 = r2 * r17
            double r5 = r12 - r10
            double r2 = r2 / r5
            r5 = 4642648265865560064(0x406e000000000000, double:240.0)
            double r5 = r5 + r2
        L_0x005b:
            double r2 = r12 + r10
            r8 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r16 = r2 / r8
            if (r0 != 0) goto L_0x0064
            goto L_0x0074
        L_0x0064:
            r14 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r0 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r0 > 0) goto L_0x006e
            double r12 = r12 - r10
            double r14 = r12 / r2
            goto L_0x0074
        L_0x006e:
            double r2 = r12 - r10
            double r8 = r8 - r12
            double r8 = r8 - r10
            double r14 = r2 / r8
        L_0x0074:
            r0 = 3
            double[] r0 = new double[r0]
            r0[r1] = r5
            r1 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r14 = r14 * r1
            r0[r4] = r14
            double r16 = r16 * r1
            r0[r7] = r16
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawPaint.RGB2HSL(java.awt.Color):double[]");
    }

    public static double[] RGB2SCRGB(Color color) {
        float[] colorComponents = color.getColorComponents((float[]) null);
        double[] dArr = new double[3];
        for (int i = 0; i < 3; i++) {
            float f = colorComponents[i];
            if (f < 0.0f) {
                dArr[i] = 0.0d;
            } else if (((double) f) <= 0.04045d) {
                dArr[i] = ((double) f) / 12.92d;
            } else if (f <= 1.0f) {
                dArr[i] = Math.pow((((double) f) + 0.055d) / 1.055d, 2.4d);
            } else {
                dArr[i] = 1.0d;
            }
        }
        return dArr;
    }

    public static Color SCRGB2RGB(double... dArr) {
        double[] dArr2 = new double[3];
        for (int i = 0; i < 3; i++) {
            double d = dArr[i];
            if (d < 0.0d) {
                dArr2[i] = 0.0d;
            } else if (d <= 0.0031308d) {
                dArr2[i] = d * 12.92d;
            } else if (d < 1.0d) {
                dArr2[i] = (Math.pow(d, 0.4166666666666667d) * 1.055d) - 0.055d;
            } else {
                dArr2[i] = 1.0d;
            }
        }
        return new Color((float) dArr2[0], (float) dArr2[1], (float) dArr2[2]);
    }

    static void fillPaintWorkaround(Graphics2D graphics2D, Shape shape2) {
        try {
            graphics2D.fill(shape2);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOG.atWarn().withThrowable(e).log("IBM JDK failed with TexturePaintContext AIOOBE - try adding the following to the VM parameter:\n-Xjit:exclude={sun/java2d/pipe/AlphaPaintPipe.renderPathTile(Ljava/lang/Object;[BIIIIII)V} and search for 'JIT Problem Determination for IBM SDK using -Xjit' (http://www-01.ibm.com/support/docview.wss?uid=swg21294023) for how to add/determine further excludes");
        }
    }
}
