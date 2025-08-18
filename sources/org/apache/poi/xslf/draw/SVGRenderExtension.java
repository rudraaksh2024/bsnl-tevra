package org.apache.poi.xslf.draw;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.apache.batik.svggen.DefaultExtensionHandler;
import org.apache.batik.svggen.SVGColor;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGPaintDescriptor;
import org.apache.batik.svggen.SVGTexturePaint;
import org.apache.poi.sl.draw.DrawTexturePaint;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.draw.PathGradientPaint;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.Internal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Internal
public class SVGRenderExtension extends DefaultExtensionHandler {
    private static final int LINE_LENGTH = 65;
    private static final String XLINK_NS = "http://www.w3.org/1999/xlink";
    private final Map<Long, String> imageMap = new HashMap();
    private WeakReference<SVGGraphics2D> svgGraphics2D = null;

    public SVGGraphics2D getSvgGraphics2D() {
        WeakReference<SVGGraphics2D> weakReference = this.svgGraphics2D;
        if (weakReference != null) {
            return (SVGGraphics2D) weakReference.get();
        }
        return null;
    }

    public void setSvgGraphics2D(SVGGraphics2D sVGGraphics2D) {
        this.svgGraphics2D = new WeakReference<>(sVGGraphics2D);
    }

    public SVGPaintDescriptor handlePaint(Paint paint, SVGGeneratorContext sVGGeneratorContext) {
        if (paint instanceof LinearGradientPaint) {
            return getLgpDescriptor((LinearGradientPaint) paint, sVGGeneratorContext);
        }
        if (paint instanceof RadialGradientPaint) {
            return getRgpDescriptor((RadialGradientPaint) paint, sVGGeneratorContext);
        }
        if (paint instanceof PathGradientPaint) {
            return getPathDescriptor((PathGradientPaint) paint, sVGGeneratorContext);
        }
        if (paint instanceof DrawTexturePaint) {
            return getDtpDescriptor((DrawTexturePaint) paint, sVGGeneratorContext);
        }
        return SVGRenderExtension.super.handlePaint(paint, sVGGeneratorContext);
    }

    private SVGPaintDescriptor getPathDescriptor(PathGradientPaint pathGradientPaint, SVGGeneratorContext sVGGeneratorContext) {
        RenderingHints renderingHints = sVGGeneratorContext.getGraphicContextDefaults().getRenderingHints();
        Shape shape = (Shape) renderingHints.get(Drawable.GRADIENT_SHAPE);
        if (shape == null) {
            return null;
        }
        PathGradientPaint.PathGradientContext createContext = pathGradientPaint.createContext(ColorModel.getRGBdefault(), shape.getBounds(), shape.getBounds2D(), new AffineTransform(), renderingHints);
        return new SVGTexturePaint(sVGGeneratorContext).toSVG(new TexturePaint(new BufferedImage(createContext.getColorModel(), createContext.createRaster(), false, (Hashtable) null), shape.getBounds2D()));
    }

    private SVGPaintDescriptor getRgpDescriptor(RadialGradientPaint radialGradientPaint, SVGGeneratorContext sVGGeneratorContext) {
        Element createElementNS = sVGGeneratorContext.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "radialGradient");
        String generateID = sVGGeneratorContext.getIDGenerator().generateID("gradient");
        createElementNS.setAttribute("id", generateID);
        setPoint(createElementNS, radialGradientPaint.getCenterPoint(), "cx", "cy");
        setPoint(createElementNS, radialGradientPaint.getFocusPoint(), "fx", "fy");
        createElementNS.setAttribute("r", String.valueOf(radialGradientPaint.getRadius()));
        addMgpAttributes(createElementNS, sVGGeneratorContext, radialGradientPaint);
        return new SVGPaintDescriptor("url(#" + generateID + ")", "1", createElementNS);
    }

    private SVGPaintDescriptor getLgpDescriptor(LinearGradientPaint linearGradientPaint, SVGGeneratorContext sVGGeneratorContext) {
        Element createElementNS = sVGGeneratorContext.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "linearGradient");
        String generateID = sVGGeneratorContext.getIDGenerator().generateID("gradient");
        createElementNS.setAttribute("id", generateID);
        setPoint(createElementNS, linearGradientPaint.getStartPoint(), "x1", "y1");
        setPoint(createElementNS, linearGradientPaint.getEndPoint(), "x2", "y2");
        addMgpAttributes(createElementNS, sVGGeneratorContext, linearGradientPaint);
        return new SVGPaintDescriptor("url(#" + generateID + ")", "1", createElementNS);
    }

    private void addMgpAttributes(Element element, SVGGeneratorContext sVGGeneratorContext, MultipleGradientPaint multipleGradientPaint) {
        element.setAttribute("gradientUnits", "userSpaceOnUse");
        int i = AnonymousClass1.$SwitchMap$java$awt$MultipleGradientPaint$CycleMethod[multipleGradientPaint.getCycleMethod().ordinal()];
        element.setAttribute("spreadMethod", i != 1 ? i != 2 ? "pad" : "repeat" : "reflect");
        element.setAttribute("color-interpolation", multipleGradientPaint.getColorSpace() == MultipleGradientPaint.ColorSpaceType.LINEAR_RGB ? "linearRGB" : "sRGB");
        AffineTransform transform = multipleGradientPaint.getTransform();
        if (!transform.isIdentity()) {
            element.setAttribute("gradientTransform", "matrix(" + transform.getScaleX() + " " + transform.getShearY() + " " + transform.getShearX() + " " + transform.getScaleY() + " " + transform.getTranslateX() + " " + transform.getTranslateY() + ")");
        }
        Color[] colors = multipleGradientPaint.getColors();
        float[] fractions = multipleGradientPaint.getFractions();
        for (int i2 = 0; i2 < colors.length; i2++) {
            Element createElementNS = sVGGeneratorContext.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "stop");
            SVGPaintDescriptor svg = SVGColor.toSVG(colors[i2], sVGGeneratorContext);
            createElementNS.setAttribute(TypedValues.CycleType.S_WAVE_OFFSET, ((int) (fractions[i2] * 100.0f)) + "%");
            createElementNS.setAttribute("stop-color", svg.getPaintValue());
            if (colors[i2].getAlpha() != 255) {
                createElementNS.setAttribute("stop-opacity", svg.getOpacityValue());
            }
            element.appendChild(createElementNS);
        }
    }

    /* renamed from: org.apache.poi.xslf.draw.SVGRenderExtension$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$awt$MultipleGradientPaint$CycleMethod;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                java.awt.MultipleGradientPaint$CycleMethod[] r0 = java.awt.MultipleGradientPaint.CycleMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$awt$MultipleGradientPaint$CycleMethod = r0
                java.awt.MultipleGradientPaint$CycleMethod r1 = java.awt.MultipleGradientPaint.CycleMethod.REFLECT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$java$awt$MultipleGradientPaint$CycleMethod     // Catch:{ NoSuchFieldError -> 0x001d }
                java.awt.MultipleGradientPaint$CycleMethod r1 = java.awt.MultipleGradientPaint.CycleMethod.REPEAT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$java$awt$MultipleGradientPaint$CycleMethod     // Catch:{ NoSuchFieldError -> 0x0028 }
                java.awt.MultipleGradientPaint$CycleMethod r1 = java.awt.MultipleGradientPaint.CycleMethod.NO_CYCLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.draw.SVGRenderExtension.AnonymousClass1.<clinit>():void");
        }
    }

    private static void setPoint(Element element, Point2D point2D, String str, String str2) {
        element.setAttribute(str, Double.toString(point2D.getX()));
        element.setAttribute(str2, Double.toString(point2D.getY()));
    }

    private SVGPaintDescriptor getDtpDescriptor(DrawTexturePaint drawTexturePaint, SVGGeneratorContext sVGGeneratorContext) {
        SVGGeneratorContext sVGGeneratorContext2 = sVGGeneratorContext;
        String imageID = getImageID(drawTexturePaint, sVGGeneratorContext);
        Document dOMFactory = sVGGeneratorContext.getDOMFactory();
        Element createElementNS = dOMFactory.createElementNS("http://www.w3.org/2000/svg", "pattern");
        String generateID = sVGGeneratorContext.getIDGenerator().generateID("pattern");
        PaintStyle.TexturePaint fill = drawTexturePaint.getFill();
        Insets2D stretch = fill.getStretch();
        if (stretch == null) {
            stretch = new Insets2D(0.0d, 0.0d, 0.0d, 0.0d);
        }
        Rectangle2D anchorRect = drawTexturePaint.getAnchorRect();
        String doubleString = sVGGeneratorContext2.doubleString(((-stretch.left) / 100000.0d) * anchorRect.getWidth());
        String doubleString2 = sVGGeneratorContext2.doubleString(((-stretch.top) / 100000.0d) * anchorRect.getHeight());
        String str = imageID;
        Document document = dOMFactory;
        String doubleString3 = sVGGeneratorContext2.doubleString((((stretch.left + 100000.0d) + stretch.right) / 100000.0d) * anchorRect.getWidth());
        String str2 = "http://www.w3.org/2000/svg";
        String doubleString4 = sVGGeneratorContext2.doubleString((((stretch.top + 100000.0d) + stretch.bottom) / 100000.0d) * anchorRect.getHeight());
        Dimension2DDouble scale = fill.getScale();
        if (scale == null) {
            scale = new Dimension2DDouble(1.0d, 1.0d);
        }
        Point2D.Double offset = fill.getOffset();
        if (offset == null) {
            offset = new Point2D.Double(0.0d, 0.0d);
        }
        if (fill.getFlipMode() == null) {
            PaintStyle.FlipMode flipMode = PaintStyle.FlipMode.NONE;
        }
        setAttribute(sVGGeneratorContext2, createElementNS, null, "patternUnits", "objectBoundingBox", null, "id", generateID, null, "x", Double.valueOf(offset.getX()), null, "y", Double.valueOf(offset.getY()), null, "width", sVGGeneratorContext2.doubleString(scale.getWidth() * 100.0d) + "%", null, "height", sVGGeneratorContext2.doubleString(scale.getHeight() * 100.0d) + "%", null, "preserveAspectRatio", "none", null, "viewBox", doubleString + " " + doubleString2 + " " + doubleString3 + " " + doubleString4);
        org.apache.poi.sl.usermodel.Shape shape = fill.getShape();
        if (!fill.isRotatedWithShape() && (shape instanceof SimpleShape)) {
            double rotation = ((SimpleShape) shape).getRotation();
            if (rotation != 0.0d) {
                setAttribute(sVGGeneratorContext2, createElementNS, null, "patternTransform", "rotate(" + sVGGeneratorContext2.doubleString(-rotation) + ")");
            }
        }
        Element createElementNS2 = document.createElementNS(str2, "use");
        createElementNS2.setAttributeNS((String) null, "href", "#" + str);
        createElementNS.appendChild(createElementNS2);
        return new SVGPaintDescriptor("url(#" + generateID + ")", "1", createElementNS);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getImageID(org.apache.poi.sl.draw.DrawTexturePaint r11, org.apache.batik.svggen.SVGGeneratorContext r12) {
        /*
            r10 = this;
            org.apache.poi.sl.draw.ImageRenderer r0 = r11.getImageRenderer()
            boolean r1 = r0 instanceof org.apache.poi.sl.draw.BitmapImageRenderer
            r2 = 0
            if (r1 == 0) goto L_0x0033
            r1 = r0
            org.apache.poi.sl.draw.BitmapImageRenderer r1 = (org.apache.poi.sl.draw.BitmapImageRenderer) r1
            java.lang.String r3 = r1.getCachedContentType()
            org.apache.poi.sl.usermodel.PictureData$PictureType r4 = org.apache.poi.sl.usermodel.PictureData.PictureType.PNG
            java.lang.String r4 = r4.contentType
            boolean r4 = r4.equals(r3)
            if (r4 != 0) goto L_0x002e
            org.apache.poi.sl.usermodel.PictureData$PictureType r4 = org.apache.poi.sl.usermodel.PictureData.PictureType.JPEG
            java.lang.String r4 = r4.contentType
            boolean r4 = r4.equals(r3)
            if (r4 != 0) goto L_0x002e
            org.apache.poi.sl.usermodel.PictureData$PictureType r4 = org.apache.poi.sl.usermodel.PictureData.PictureType.GIF
            java.lang.String r4 = r4.contentType
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x0033
        L_0x002e:
            byte[] r1 = r1.getCachedImage()
            goto L_0x0035
        L_0x0033:
            r1 = r2
            r3 = r1
        L_0x0035:
            if (r1 != 0) goto L_0x004f
            java.awt.image.BufferedImage r0 = r0.getImage()
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r1 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
            r1.<init>()
            java.lang.String r3 = "PNG"
            javax.imageio.ImageIO.write(r0, r3, r1)     // Catch:{ IOException -> 0x004e }
            byte[] r1 = r1.toByteArray()
            org.apache.poi.sl.usermodel.PictureData$PictureType r0 = org.apache.poi.sl.usermodel.PictureData.PictureType.PNG
            java.lang.String r3 = r0.contentType
            goto L_0x004f
        L_0x004e:
            return r2
        L_0x004f:
            java.util.zip.CRC32 r0 = new java.util.zip.CRC32
            r0.<init>()
            r0.update(r1)
            long r4 = r0.getValue()
            java.lang.Long r0 = java.lang.Long.valueOf(r4)
            java.util.Map<java.lang.Long, java.lang.String> r4 = r10.imageMap
            java.lang.Object r4 = r4.get(r0)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L_0x006a
            return r4
        L_0x006a:
            org.w3c.dom.Document r4 = r12.getDOMFactory()
            java.awt.geom.Rectangle2D r11 = r11.getAnchorRect()
            org.apache.batik.svggen.SVGIDGenerator r5 = r12.getIDGenerator()
            java.lang.String r6 = "image"
            java.lang.String r5 = r5.generateID(r6)
            java.util.Map<java.lang.Long, java.lang.String> r7 = r10.imageMap
            r7.put(r0, r5)
            int r0 = r1.length
            r7 = 4
            int r0 = r0 * r7
            r8 = 3
            int r0 = r0 / r8
            int r0 = r0 + r8
            r0 = r0 & -4
            int r9 = r0 / 65
            int r9 = r9 + 30
            int r0 = r0 + r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r0)
            java.lang.String r0 = "data:"
            r9.append(r0)
            r9.append(r3)
            java.lang.String r0 = ";base64,\n"
            r9.append(r0)
            java.lang.String r0 = "\n"
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.US_ASCII
            byte[] r0 = r0.getBytes(r3)
            r3 = 65
            java.util.Base64$Encoder r0 = java.util.Base64.getMimeEncoder(r3, r0)
            java.lang.String r0 = r0.encodeToString(r1)
            r9.append(r0)
            java.lang.String r0 = "http://www.w3.org/2000/svg"
            org.w3c.dom.Element r0 = r4.createElementNS(r0, r6)
            r1 = 21
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r3 = 0
            r1[r3] = r2
            r3 = 1
            java.lang.String r4 = "id"
            r1[r3] = r4
            r3 = 2
            r1[r3] = r5
            r1[r8] = r2
            java.lang.String r3 = "preserveAspectRatio"
            r1[r7] = r3
            r3 = 5
            java.lang.String r4 = "none"
            r1[r3] = r4
            r3 = 6
            r1[r3] = r2
            r3 = 7
            java.lang.String r4 = "x"
            r1[r3] = r4
            double r3 = r11.getX()
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            r4 = 8
            r1[r4] = r3
            r3 = 9
            r1[r3] = r2
            r3 = 10
            java.lang.String r4 = "y"
            r1[r3] = r4
            double r3 = r11.getY()
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            r4 = 11
            r1[r4] = r3
            r3 = 12
            r1[r3] = r2
            r3 = 13
            java.lang.String r4 = "width"
            r1[r3] = r4
            double r3 = r11.getWidth()
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            r4 = 14
            r1[r4] = r3
            r3 = 15
            r1[r3] = r2
            r2 = 16
            java.lang.String r3 = "height"
            r1[r2] = r3
            double r2 = r11.getHeight()
            java.lang.Double r11 = java.lang.Double.valueOf(r2)
            r2 = 17
            r1[r2] = r11
            r11 = 18
            java.lang.String r2 = "http://www.w3.org/1999/xlink"
            r1[r11] = r2
            r11 = 19
            java.lang.String r2 = "xlink:href"
            r1[r11] = r2
            r11 = 20
            java.lang.String r2 = r9.toString()
            r1[r11] = r2
            setAttribute(r12, r0, r1)
            org.apache.batik.svggen.SVGGraphics2D r10 = r10.getSvgGraphics2D()
            org.apache.batik.svggen.DOMTreeManager r10 = r10.getDOMTreeManager()
            r10.addOtherDef(r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.draw.SVGRenderExtension.getImageID(org.apache.poi.sl.draw.DrawTexturePaint, org.apache.batik.svggen.SVGGeneratorContext):java.lang.String");
    }

    private static void setAttribute(SVGGeneratorContext sVGGeneratorContext, Element element, Object... objArr) {
        String str;
        for (int i = 0; i < objArr.length; i += 3) {
            String str2 = objArr[i];
            String str3 = objArr[i + 1];
            Number number = objArr[i + 2];
            if (number instanceof String) {
                str = (String) number;
            } else {
                str = number instanceof Number ? sVGGeneratorContext.doubleString(number.doubleValue()) : number == null ? "" : number.toString();
            }
            element.setAttributeNS(str2, str3, str);
        }
    }
}
