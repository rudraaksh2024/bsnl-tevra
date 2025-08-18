package org.apache.poi.xslf.util;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.PrintStream;
import java.text.AttributedCharacterIterator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class DummyGraphics2d extends Graphics2D {
    private static final Object[] ATTRS = {TextAttribute.FAMILY, "TextAttribute.FAMILY", TextAttribute.WEIGHT, "TextAttribute.WEIGHT", TextAttribute.WEIGHT_EXTRA_LIGHT, "TextAttribute.WEIGHT_EXTRA_LIGHT", TextAttribute.WEIGHT_LIGHT, "TextAttribute.WEIGHT_LIGHT", TextAttribute.WEIGHT_DEMILIGHT, "TextAttribute.WEIGHT_DEMILIGHT", TextAttribute.WEIGHT_REGULAR, "TextAttribute.WEIGHT_REGULAR", TextAttribute.WEIGHT_SEMIBOLD, "TextAttribute.WEIGHT_SEMIBOLD", TextAttribute.WEIGHT_MEDIUM, "TextAttribute.WEIGHT_MEDIUM", TextAttribute.WEIGHT_DEMIBOLD, "TextAttribute.WEIGHT_DEMIBOLD", TextAttribute.WEIGHT_BOLD, "TextAttribute.WEIGHT_BOLD", TextAttribute.WEIGHT_HEAVY, "TextAttribute.WEIGHT_HEAVY", TextAttribute.WEIGHT_EXTRABOLD, "TextAttribute.WEIGHT_EXTRABOLD", TextAttribute.WEIGHT_ULTRABOLD, "TextAttribute.WEIGHT_ULTRABOLD", TextAttribute.WIDTH, "TextAttribute.WIDTH", TextAttribute.WIDTH_CONDENSED, "TextAttribute.WIDTH_CONDENSED", TextAttribute.WIDTH_SEMI_CONDENSED, "TextAttribute.WIDTH_SEMI_CONDENSED", TextAttribute.WIDTH_REGULAR, "TextAttribute.WIDTH_REGULAR", TextAttribute.WIDTH_SEMI_EXTENDED, "TextAttribute.WIDTH_SEMI_EXTENDED", TextAttribute.WIDTH_EXTENDED, "TextAttribute.WIDTH_EXTENDED", TextAttribute.POSTURE, "TextAttribute.POSTURE", TextAttribute.POSTURE_REGULAR, "TextAttribute.POSTURE_REGULAR", TextAttribute.POSTURE_OBLIQUE, "TextAttribute.POSTURE_OBLIQUE", TextAttribute.SIZE, "TextAttribute.SIZE", TextAttribute.TRANSFORM, "TextAttribute.TRANSFORM", TextAttribute.SUPERSCRIPT, "TextAttribute.SUPERSCRIPT", TextAttribute.SUPERSCRIPT_SUPER, "TextAttribute.SUPERSCRIPT_SUPER", TextAttribute.SUPERSCRIPT_SUB, "TextAttribute.SUPERSCRIPT_SUB", TextAttribute.FONT, "TextAttribute.FONT", TextAttribute.CHAR_REPLACEMENT, "TextAttribute.CHAR_REPLACEMENT", TextAttribute.FOREGROUND, "TextAttribute.FOREGROUND", TextAttribute.BACKGROUND, "TextAttribute.BACKGROUND", TextAttribute.UNDERLINE, "TextAttribute.UNDERLINE", TextAttribute.UNDERLINE_ON, "TextAttribute.UNDERLINE_ON", TextAttribute.STRIKETHROUGH, "TextAttribute.STRIKETHROUGH", TextAttribute.STRIKETHROUGH_ON, "TextAttribute.STRIKETHROUGH_ON", TextAttribute.RUN_DIRECTION, "TextAttribute.RUN_DIRECTION", TextAttribute.RUN_DIRECTION_LTR, "TextAttribute.RUN_DIRECTION_LTR", TextAttribute.RUN_DIRECTION_RTL, "TextAttribute.RUN_DIRECTION_RTL", TextAttribute.BIDI_EMBEDDING, "TextAttribute.BIDI_EMBEDDING", TextAttribute.JUSTIFICATION, "TextAttribute.JUSTIFICATION", TextAttribute.JUSTIFICATION_FULL, "TextAttribute.JUSTIFICATION_FULL", TextAttribute.JUSTIFICATION_NONE, "TextAttribute.JUSTIFICATION_NONE", TextAttribute.INPUT_METHOD_HIGHLIGHT, "TextAttribute.INPUT_METHOD_HIGHLIGHT", TextAttribute.INPUT_METHOD_UNDERLINE, "TextAttribute.INPUT_METHOD_UNDERLINE", TextAttribute.UNDERLINE_LOW_ONE_PIXEL, "TextAttribute.UNDERLINE_LOW_ONE_PIXEL", TextAttribute.UNDERLINE_LOW_TWO_PIXEL, "TextAttribute.UNDERLINE_LOW_TWO_PIXEL", TextAttribute.UNDERLINE_LOW_DOTTED, "TextAttribute.UNDERLINE_LOW_DOTTED", TextAttribute.UNDERLINE_LOW_GRAY, "TextAttribute.UNDERLINE_LOW_GRAY", TextAttribute.UNDERLINE_LOW_DASHED, "TextAttribute.UNDERLINE_LOW_DASHED", TextAttribute.SWAP_COLORS, "TextAttribute.SWAP_COLORS", TextAttribute.SWAP_COLORS_ON, "TextAttribute.SWAP_COLORS_ON", TextAttribute.NUMERIC_SHAPING, "TextAttribute.NUMERIC_SHAPING", TextAttribute.KERNING, "TextAttribute.KERNING", TextAttribute.KERNING_ON, "TextAttribute.KERNING_ON", TextAttribute.LIGATURES, "TextAttribute.LIGATURES", TextAttribute.LIGATURES_ON, "TextAttribute.LIGATURES_ON", TextAttribute.TRACKING, "TextAttribute.TRACKING", TextAttribute.TRACKING_TIGHT, "TextAttribute.TRACKING_TIGHT", TextAttribute.TRACKING_LOOSE, "TextAttribute.TRACKING_LOOSE"};
    private static final String[] COMPOSITE_RULES = {"CLEAR", "SRC", "SRC_OVER", "DST_OVER", "SRC_IN", "DST_IN", "SRC_OUT", "DST_OUT", "DST", "SRC_ATOP", "DST_ATOP", "XOR"};
    private static final Object[] HINTS = {RenderingHints.KEY_ANTIALIASING, "RenderingHints.KEY_ANTIALIASING", RenderingHints.VALUE_ANTIALIAS_ON, "RenderingHints.VALUE_ANTIALIAS_ON", RenderingHints.VALUE_ANTIALIAS_OFF, "RenderingHints.VALUE_ANTIALIAS_OFF", RenderingHints.VALUE_ANTIALIAS_DEFAULT, "RenderingHints.VALUE_ANTIALIAS_DEFAULT", RenderingHints.KEY_RENDERING, "RenderingHints.KEY_RENDERING", RenderingHints.VALUE_RENDER_SPEED, "RenderingHints.VALUE_RENDER_SPEED", RenderingHints.VALUE_RENDER_QUALITY, "RenderingHints.VALUE_RENDER_QUALITY", RenderingHints.VALUE_RENDER_DEFAULT, "RenderingHints.VALUE_RENDER_DEFAULT", RenderingHints.KEY_DITHERING, "RenderingHints.KEY_DITHERING", RenderingHints.VALUE_DITHER_DISABLE, "RenderingHints.VALUE_DITHER_DISABLE", RenderingHints.VALUE_DITHER_ENABLE, "RenderingHints.VALUE_DITHER_ENABLE", RenderingHints.VALUE_DITHER_DEFAULT, "RenderingHints.VALUE_DITHER_DEFAULT", RenderingHints.KEY_TEXT_ANTIALIASING, "RenderingHints.KEY_TEXT_ANTIALIASING", RenderingHints.VALUE_TEXT_ANTIALIAS_ON, "RenderingHints.VALUE_TEXT_ANTIALIAS_ON", RenderingHints.VALUE_TEXT_ANTIALIAS_OFF, "RenderingHints.VALUE_TEXT_ANTIALIAS_OFF", RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT, "RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT", RenderingHints.VALUE_TEXT_ANTIALIAS_GASP, "RenderingHints.VALUE_TEXT_ANTIALIAS_GASP", RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB, "RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB", RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR, "RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR", RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB, "RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB", RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR, "RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR", RenderingHints.KEY_TEXT_LCD_CONTRAST, "RenderingHints.KEY_TEXT_LCD_CONTRAST", RenderingHints.KEY_FRACTIONALMETRICS, "RenderingHints.KEY_FRACTIONALMETRICS", RenderingHints.VALUE_FRACTIONALMETRICS_OFF, "RenderingHints.VALUE_FRACTIONALMETRICS_OFF", RenderingHints.VALUE_FRACTIONALMETRICS_ON, "RenderingHints.VALUE_FRACTIONALMETRICS_ON", RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT, "RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT", RenderingHints.KEY_INTERPOLATION, "RenderingHints.KEY_INTERPOLATION", RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR, "RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR", RenderingHints.VALUE_INTERPOLATION_BILINEAR, "RenderingHints.VALUE_INTERPOLATION_BILINEAR", RenderingHints.VALUE_INTERPOLATION_BICUBIC, "RenderingHints.VALUE_INTERPOLATION_BICUBIC", RenderingHints.KEY_ALPHA_INTERPOLATION, "RenderingHints.KEY_ALPHA_INTERPOLATION", RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED, "RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED", RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY, "RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY", RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT, "RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT", RenderingHints.KEY_COLOR_RENDERING, "RenderingHints.KEY_COLOR_RENDERING", RenderingHints.VALUE_COLOR_RENDER_SPEED, "RenderingHints.VALUE_COLOR_RENDER_SPEED", RenderingHints.VALUE_COLOR_RENDER_QUALITY, "RenderingHints.VALUE_COLOR_RENDER_QUALITY", RenderingHints.VALUE_COLOR_RENDER_DEFAULT, "RenderingHints.VALUE_COLOR_RENDER_DEFAULT", RenderingHints.KEY_STROKE_CONTROL, "RenderingHints.KEY_STROKE_CONTROL", RenderingHints.VALUE_STROKE_DEFAULT, "RenderingHints.VALUE_STROKE_DEFAULT", RenderingHints.VALUE_STROKE_NORMALIZE, "RenderingHints.VALUE_STROKE_NORMALIZE", RenderingHints.VALUE_STROKE_PURE, "RenderingHints.VALUE_STROKE_PURE"};
    private BufferedImage bufimg;
    private final Graphics2D g2D;
    private final PrintStream log;

    public DummyGraphics2d() {
        this(System.out);
    }

    public DummyGraphics2d(PrintStream printStream) {
        BufferedImage bufferedImage = new BufferedImage(1000, 1000, 2);
        this.bufimg = bufferedImage;
        this.g2D = bufferedImage.getGraphics();
        this.log = printStream;
    }

    public DummyGraphics2d(PrintStream printStream, Graphics2D graphics2D) {
        this.g2D = graphics2D;
        this.log = printStream;
    }

    public void addRenderingHints(Map<?, ?> map) {
        this.log.println("addRenderingHinds(Map):\n  hints = " + map);
        this.g2D.addRenderingHints(map);
    }

    public void clip(Shape shape) {
        this.log.println("clip(Shape):\n  s = " + shape);
        this.g2D.clip(shape);
    }

    private void pathToString(StringBuilder sb, Path2D path2D) {
        sb.append("Path2D p = new Path2D.Double(").append(path2D.getWindingRule()).append(");\n");
        double[] dArr = new double[6];
        PathIterator pathIterator = path2D.getPathIterator((AffineTransform) null);
        while (!pathIterator.isDone()) {
            int currentSegment = pathIterator.currentSegment(dArr);
            if (currentSegment == 0) {
                sb.append("p.moveTo(").append(dArr[0]).append(",").append(dArr[1]).append(");\n");
            } else if (currentSegment == 1) {
                sb.append("p.lineTo(").append(dArr[0]).append(",").append(dArr[1]).append(");\n");
            } else if (currentSegment == 2) {
                sb.append("p.quadTo(").append(dArr[0]).append(",").append(dArr[1]).append(",").append(dArr[2]).append(",").append(dArr[3]).append(");\n");
            } else if (currentSegment == 3) {
                sb.append("p.curveTo(").append(dArr[0]).append(",").append(dArr[1]).append(",").append(dArr[2]).append(",").append(dArr[3]).append(",").append(dArr[4]).append(",").append(dArr[5]).append(");\n");
            } else if (currentSegment == 4) {
                sb.append("p.closePath();\n");
            }
            pathIterator.next();
        }
    }

    public void draw(Shape shape) {
        if (shape instanceof Path2D) {
            StringBuilder sb = new StringBuilder();
            pathToString(sb, (Path2D) shape);
            sb.append("g.draw(p);");
            this.log.println(sb);
        } else {
            this.log.println("g.draw(" + shape + ")");
        }
        this.g2D.draw(shape);
    }

    public void drawGlyphVector(GlyphVector glyphVector, float f, float f2) {
        this.log.println("drawGlyphVector(GlyphVector, float, float):\n  g = " + glyphVector + "\n  x = " + f + "\n  y = " + f2);
        this.g2D.drawGlyphVector(glyphVector, f, f2);
    }

    public void drawImage(BufferedImage bufferedImage, BufferedImageOp bufferedImageOp, int i, int i2) {
        this.log.println("drawImage(BufferedImage, BufferedImageOp, x, y):\n  img = " + bufferedImage + "\n  op = " + bufferedImageOp + "\n  x = " + i + "\n  y = " + i2);
        this.g2D.drawImage(bufferedImage, bufferedImageOp, i, i2);
    }

    public boolean drawImage(Image image, AffineTransform affineTransform, ImageObserver imageObserver) {
        this.log.println("drawImage(Image,AfflineTransform,ImageObserver):\n  img = " + image + "\n  xform = " + affineTransform + "\n  obs = " + imageObserver);
        return this.g2D.drawImage(image, affineTransform, imageObserver);
    }

    public void drawRenderableImage(RenderableImage renderableImage, AffineTransform affineTransform) {
        this.log.println("drawRenderableImage(RenderableImage, AfflineTransform):\n  img = " + renderableImage + "\n  xform = " + affineTransform);
        this.g2D.drawRenderableImage(renderableImage, affineTransform);
    }

    public void drawRenderedImage(RenderedImage renderedImage, AffineTransform affineTransform) {
        this.log.println("drawRenderedImage(RenderedImage, AffineTransform):\n  img = " + renderedImage + "\n  xform = " + affineTransform);
        this.g2D.drawRenderedImage(renderedImage, affineTransform);
    }

    public void drawString(String str, float f, float f2) {
        this.log.println("drawString(s,x,y):\n  s = " + str + "\n  x = " + f + "\n  y = " + f2);
        this.g2D.drawString(str, f, f2);
    }

    public void fill(Shape shape) {
        if (shape instanceof Path2D) {
            StringBuilder sb = new StringBuilder();
            pathToString(sb, (Path2D) shape);
            sb.append("g.fill(p);");
            this.log.println(sb);
        } else {
            this.log.println("g.fill(" + shape + ")");
        }
        this.g2D.fill(shape);
    }

    public Color getBackground() {
        this.log.println("getBackground():");
        return this.g2D.getBackground();
    }

    public Composite getComposite() {
        this.log.println("getComposite():");
        return this.g2D.getComposite();
    }

    public GraphicsConfiguration getDeviceConfiguration() {
        this.log.println("getDeviceConfiguration():");
        return this.g2D.getDeviceConfiguration();
    }

    public FontRenderContext getFontRenderContext() {
        this.log.println("getFontRenderContext():");
        return this.g2D.getFontRenderContext();
    }

    public Paint getPaint() {
        this.log.println("getPaint():");
        return this.g2D.getPaint();
    }

    public Object getRenderingHint(RenderingHints.Key key) {
        this.log.println("getRenderingHint(\"" + key + "\")");
        return this.g2D.getRenderingHint(key);
    }

    public RenderingHints getRenderingHints() {
        this.log.println("getRenderingHints():");
        return this.g2D.getRenderingHints();
    }

    public Stroke getStroke() {
        this.log.println("getStroke():");
        return this.g2D.getStroke();
    }

    public AffineTransform getTransform() {
        this.log.println("getTransform():");
        return this.g2D.getTransform();
    }

    public boolean hit(Rectangle rectangle, Shape shape, boolean z) {
        this.log.println("hit(Rectangle, Shape, onStroke):\n  rect = " + rectangle + "\n  s = " + shape + "\n  onStroke = " + z);
        return this.g2D.hit(rectangle, shape, z);
    }

    public void rotate(double d) {
        this.log.println("rotate(theta):\n  theta = " + d);
        this.g2D.rotate(d);
    }

    public void rotate(double d, double d2, double d3) {
        this.log.println("rotate(double,double,double):\n  theta = " + d + "\n  x = " + d2 + "\n  y = " + d3);
        this.g2D.rotate(d, d2, d3);
    }

    public void scale(double d, double d2) {
        this.log.println("g.scale(" + d + "," + d2 + ");");
        this.g2D.scale(d, d2);
    }

    public void setBackground(Color color) {
        this.log.printf(Locale.ROOT, "setBackground(new Color(0x%08X))%n", new Object[]{Integer.valueOf(color.getRGB())});
        this.g2D.setBackground(color);
    }

    public void setComposite(Composite composite) {
        String str;
        if (composite instanceof AlphaComposite) {
            AlphaComposite alphaComposite = (AlphaComposite) composite;
            StringBuilder sb = new StringBuilder("g.setComposite(AlphaComposite.getInstance(AlphaComposite.");
            String[] strArr = COMPOSITE_RULES;
            str = sb.append(strArr[Math.max(0, Math.min(strArr.length - 1, alphaComposite.getRule()))]).append(", ").append(alphaComposite.getAlpha()).append("f));").toString();
        } else {
            str = "g.setComposite(" + composite.toString() + ");";
        }
        this.log.println(str);
        this.g2D.setComposite(composite);
    }

    public void setPaint(Paint paint) {
        String str;
        if (paint instanceof Color) {
            str = "g.setPaint(" + String.format(Locale.ROOT, "new Color(0x%08X));", new Object[]{Integer.valueOf(((Color) paint).getRGB())});
        } else {
            str = "g.setPaint(" + paint.toString() + ");";
        }
        this.log.println(str);
        this.g2D.setPaint(paint);
    }

    public void setRenderingHint(RenderingHints.Key key, Object obj) {
        this.log.println("g.setRenderingHint(" + mapHint(key) + ", " + mapHint(obj) + ");");
        this.g2D.setRenderingHint(key, obj);
    }

    private static String mapHint(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof AffineTransform) {
            return mapTransform((AffineTransform) obj);
        }
        int i = 0;
        while (true) {
            Object[] objArr = HINTS;
            if (i >= objArr.length) {
                return "\"" + obj + "\"";
            }
            if (obj == objArr[i]) {
                return (String) objArr[i + 1];
            }
            i += 2;
        }
    }

    public void setRenderingHints(Map<?, ?> map) {
        this.log.println("setRenderingHints(Map):\n  hints = " + map);
        this.g2D.setRenderingHints(map);
    }

    public void setStroke(Stroke stroke) {
        String str;
        if (stroke instanceof BasicStroke) {
            BasicStroke basicStroke = (BasicStroke) stroke;
            str = "g.setStroke(new BasicStroke(" + basicStroke.getLineWidth() + "f, BasicStroke.CAP_" + new String[]{"BUTT", "ROUND", "SQUARE"}[basicStroke.getEndCap()] + ", BasicStroke.JOIN_" + new String[]{"MITER", "ROUND", "BEVEL"}[basicStroke.getLineJoin()] + ", " + basicStroke.getMiterLimit() + "f, " + Arrays.toString(basicStroke.getDashArray()) + ", " + basicStroke.getDashPhase() + "f));";
        } else {
            str = "g.setStroke(" + stroke + ");";
        }
        this.log.println(str);
        this.g2D.setStroke(stroke);
    }

    private static String mapTransform(AffineTransform affineTransform) {
        if (affineTransform.isIdentity()) {
            return "new AffineTransform()";
        }
        return "new AffineTransform(" + affineTransform.getScaleX() + "f," + affineTransform.getShearY() + "f," + affineTransform.getShearX() + "f," + affineTransform.getScaleY() + "f," + affineTransform.getTranslateX() + "f," + affineTransform.getTranslateY() + "f)";
    }

    public void setTransform(AffineTransform affineTransform) {
        this.log.println("g.setTransform(" + mapTransform(affineTransform) + ");");
        this.g2D.setTransform(affineTransform);
    }

    public void shear(double d, double d2) {
        this.log.println("shear(shx, dhy):\n  shx = " + d + "\n  shy = " + d2);
        this.g2D.shear(d, d2);
    }

    public void transform(AffineTransform affineTransform) {
        this.log.println("transform(AffineTransform):\n  Tx = " + affineTransform);
        this.g2D.transform(affineTransform);
    }

    public void translate(double d, double d2) {
        this.log.println("translate(double, double):\n  tx = " + d + "\n  ty = " + d2);
        this.g2D.translate(d, d2);
    }

    public void clearRect(int i, int i2, int i3, int i4) {
        this.log.println("clearRect(int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4);
        this.g2D.clearRect(i, i2, i3, i4);
    }

    public void clipRect(int i, int i2, int i3, int i4) {
        this.log.println("clipRect(int, int, int, int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "height = " + i4);
        this.g2D.clipRect(i, i2, i3, i4);
    }

    public void copyArea(int i, int i2, int i3, int i4, int i5, int i6) {
        this.log.println("copyArea(int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4);
        this.g2D.copyArea(i, i2, i3, i4, i5, i6);
    }

    public Graphics create() {
        this.log.println("create():");
        return this.g2D.create();
    }

    public Graphics create(int i, int i2, int i3, int i4) {
        this.log.println("create(int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4);
        return this.g2D.create(i, i2, i3, i4);
    }

    public void dispose() {
        this.log.println("dispose():");
        this.g2D.dispose();
    }

    public void draw3DRect(int i, int i2, int i3, int i4, boolean z) {
        this.log.println("draw3DRect(int,int,int,int,boolean):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4 + "\n  raised = " + z);
        this.g2D.draw3DRect(i, i2, i3, i4, z);
    }

    public void drawArc(int i, int i2, int i3, int i4, int i5, int i6) {
        this.log.println("drawArc(int,int,int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4 + "\n  startAngle = " + i5 + "\n  arcAngle = " + i6);
        this.g2D.drawArc(i, i2, i3, i4, i5, i6);
    }

    public void drawBytes(byte[] bArr, int i, int i2, int i3, int i4) {
        this.log.println("drawBytes(byte[],int,int,int,int):\n  data = " + Arrays.toString(bArr) + "\n  offset = " + i + "\n  length = " + i2 + "\n  x = " + i3 + "\n  y = " + i4);
        this.g2D.drawBytes(bArr, i, i2, i3, i4);
    }

    public void drawChars(char[] cArr, int i, int i2, int i3, int i4) {
        this.log.println("drawChars(data,int,int,int,int):\n  data = " + Arrays.toString(cArr) + "\n  offset = " + i + "\n  length = " + i2 + "\n  x = " + i3 + "\n  y = " + i4);
        this.g2D.drawChars(cArr, i, i2, i3, i4);
    }

    public boolean drawImage(Image image, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, ImageObserver imageObserver) {
        Image image2 = image;
        int i9 = i;
        int i10 = i2;
        int i11 = i3;
        int i12 = i4;
        int i13 = i5;
        int i14 = i6;
        int i15 = i7;
        int i16 = i8;
        ImageObserver imageObserver2 = imageObserver;
        this.log.println("drawImage(Image,int,int,int,int,int,int,int,int,ImageObserver):\n  img = " + image + "\n  dx1 = " + i9 + "\n  dy1 = " + i10 + "\n  dx2 = " + i11 + "\n  dy2 = " + i12 + "\n  sx1 = " + i13 + "\n  sy1 = " + i14 + "\n  sx2 = " + i15 + "\n  sy2 = " + i16 + "\n  observer = " + imageObserver2);
        return this.g2D.drawImage(image, i9, i10, i11, i12, i13, i14, i15, i16, imageObserver2);
    }

    public boolean drawImage(Image image, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Color color, ImageObserver imageObserver) {
        int i9 = i;
        int i10 = i2;
        int i11 = i3;
        int i12 = i4;
        int i13 = i5;
        int i14 = i6;
        int i15 = i7;
        int i16 = i8;
        Color color2 = color;
        ImageObserver imageObserver2 = imageObserver;
        this.log.println("drawImage(Image,int,int,int,int,int,int,int,int,Color,ImageObserver):\n  img = " + image + "\n  dx1 = " + i9 + "\n  dy1 = " + i10 + "\n  dx2 = " + i11 + "\n  dy2 = " + i12 + "\n  sx1 = " + i13 + "\n  sy1 = " + i14 + "\n  sx2 = " + i15 + "\n  sy2 = " + i16 + "\n  bgcolor = " + color2 + "\n  observer = " + imageObserver2);
        return this.g2D.drawImage(image, i9, i10, i11, i12, i13, i14, i15, i16, color2, imageObserver2);
    }

    public boolean drawImage(Image image, int i, int i2, Color color, ImageObserver imageObserver) {
        this.log.println("drawImage(Image,int,int,Color,ImageObserver):\n  img = " + image + "\n  x = " + i + "\n  y = " + i2 + "\n  bgcolor = " + color + "\n  observer = " + imageObserver);
        return this.g2D.drawImage(image, i, i2, color, imageObserver);
    }

    public boolean drawImage(Image image, int i, int i2, ImageObserver imageObserver) {
        this.log.println("drawImage(Image,int,int,observer):\n  img = " + image + "\n  x = " + i + "\n  y = " + i2 + "\n  observer = " + imageObserver);
        return this.g2D.drawImage(image, i, i2, imageObserver);
    }

    public boolean drawImage(Image image, int i, int i2, int i3, int i4, Color color, ImageObserver imageObserver) {
        Image image2 = image;
        int i5 = i4;
        Color color2 = color;
        ImageObserver imageObserver2 = imageObserver;
        this.log.println("drawImage(Image,int,int,int,int,Color,ImageObserver):\n  img = " + image + "\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i5 + "\n  bgcolor = " + color2 + "\n  observer = " + imageObserver2);
        return this.g2D.drawImage(image, i, i2, i3, i5, color2, imageObserver2);
    }

    public boolean drawImage(Image image, int i, int i2, int i3, int i4, ImageObserver imageObserver) {
        this.log.println("drawImage(Image,int,int,width,height,observer):\n  img = " + image + "\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4 + "\n  observer = " + imageObserver);
        return this.g2D.drawImage(image, i, i2, i3, i4, imageObserver);
    }

    public void drawLine(int i, int i2, int i3, int i4) {
        this.log.println("drawLine(int,int,int,int):\n  x1 = " + i + "\n  y1 = " + i2 + "\n  x2 = " + i3 + "\n  y2 = " + i4);
        this.g2D.drawLine(i, i2, i3, i4);
    }

    public void drawOval(int i, int i2, int i3, int i4) {
        this.log.println("drawOval(int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4);
        this.g2D.drawOval(i, i2, i3, i4);
    }

    public void drawPolygon(Polygon polygon) {
        this.log.println("drawPolygon(Polygon):\n  p = " + polygon);
        this.g2D.drawPolygon(polygon);
    }

    public void drawPolygon(int[] iArr, int[] iArr2, int i) {
        this.log.println("drawPolygon(int[],int[],int):\n  xPoints = " + Arrays.toString(iArr) + "\n  yPoints = " + Arrays.toString(iArr2) + "\n  nPoints = " + i);
        this.g2D.drawPolygon(iArr, iArr2, i);
    }

    public void drawPolyline(int[] iArr, int[] iArr2, int i) {
        this.log.println("drawPolyline(int[],int[],int):\n  xPoints = " + Arrays.toString(iArr) + "\n  yPoints = " + Arrays.toString(iArr2) + "\n  nPoints = " + i);
        this.g2D.drawPolyline(iArr, iArr2, i);
    }

    public void drawRect(int i, int i2, int i3, int i4) {
        this.log.println("drawRect(int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4);
        this.g2D.drawRect(i, i2, i3, i4);
    }

    public void drawRoundRect(int i, int i2, int i3, int i4, int i5, int i6) {
        this.log.println("drawRoundRect(int,int,int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4 + "\n  arcWidth = " + i5 + "\n  arcHeight = " + i6);
        this.g2D.drawRoundRect(i, i2, i3, i4, i5, i6);
    }

    private static String mapAttribute(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof Font) {
            Font font = (Font) obj;
            return "new Font(\"" + font.getFamily(Locale.ROOT) + "\"," + new String[]{"Font.PLAIN", "Font.BOLD", "Font.ITALIC", "Font.BOLD | Font.ITALIC"}[font.getStyle()] + "," + font.getSize() + ")";
        }
        int i = 0;
        if (obj instanceof Color) {
            return String.format(Locale.ROOT, "new Color(0x%08X)", new Object[]{Integer.valueOf(((Color) obj).getRGB())});
        }
        while (true) {
            Object[] objArr = ATTRS;
            if (i >= objArr.length) {
                return "\"" + obj + "\"";
            }
            if (obj == objArr[i]) {
                return (String) objArr[i + 1];
            }
            i += 2;
        }
    }

    public void drawString(AttributedCharacterIterator attributedCharacterIterator, float f, float f2) {
        AttributedCharacterIterator attributedCharacterIterator2 = attributedCharacterIterator;
        float f3 = f;
        float f4 = f2;
        int index = attributedCharacterIterator.getIndex();
        HashMap hashMap = new HashMap();
        StringBuilder sb = new StringBuilder();
        char current = attributedCharacterIterator.current();
        while (current != 65535) {
            sb.append(current);
            attributedCharacterIterator.getAttributes().forEach(new DummyGraphics2d$$ExternalSyntheticLambda1(hashMap, attributedCharacterIterator2));
            current = attributedCharacterIterator.next();
        }
        sb.setLength(0);
        sb.append("AttributedString as = new AttributedString(\"" + sb + "\");\n");
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            AttributedCharacterIterator.Attribute attribute = (AttributedCharacterIterator.Attribute) entry.getKey();
            int i = -2;
            Object obj = null;
            int i2 = -2;
            for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                int intValue = ((Integer) entry2.getKey()).intValue();
                Object value = entry2.getValue();
                Iterator it2 = it;
                if (i < intValue - 1 || value != obj) {
                    if (i2 >= 0) {
                        sb.append("as.addAttribute(").append(mapAttribute(attribute)).append(",").append(mapAttribute(obj)).append(",").append(i2).append(",").append(i + 1).append(");\n");
                    }
                    i2 = intValue;
                }
                obj = value;
                i = intValue;
                it = it2;
            }
            Iterator it3 = it;
            if (obj != null) {
                sb.append("as.addAttribute(").append(mapAttribute(attribute)).append(",").append(mapAttribute(obj)).append(",").append(i2).append(",").append(i + 1).append(");\n");
            }
            it = it3;
        }
        sb.append("g.drawString(as.getIterator(),").append(f3).append("f,").append(f4).append("f);");
        this.log.println(sb);
        attributedCharacterIterator2.setIndex(index);
        this.g2D.drawString(attributedCharacterIterator2, f3, f4);
    }

    static /* synthetic */ Map lambda$null$0(AttributedCharacterIterator.Attribute attribute) {
        return new LinkedHashMap();
    }

    public void drawString(AttributedCharacterIterator attributedCharacterIterator, int i, int i2) {
        drawString(attributedCharacterIterator, (float) i, (float) i2);
    }

    public void drawString(String str, int i, int i2) {
        this.log.println("drawString(str,int,int):\n  str = " + str + "\n  x = " + i + "\n  y = " + i2);
        this.g2D.drawString(str, i, i2);
    }

    public void fill3DRect(int i, int i2, int i3, int i4, boolean z) {
        this.log.println("fill3DRect(int,int,int,int,boolean):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4 + "\n  raised = " + z);
        this.g2D.fill3DRect(i, i2, i3, i4, z);
    }

    public void fillArc(int i, int i2, int i3, int i4, int i5, int i6) {
        this.log.println("fillArc(int,int,int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4 + "\n  startAngle = " + i5 + "\n  arcAngle = " + i6);
        this.g2D.fillArc(i, i2, i3, i4, i5, i6);
    }

    public void fillOval(int i, int i2, int i3, int i4) {
        this.log.println("fillOval(int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4);
        this.g2D.fillOval(i, i2, i3, i4);
    }

    public void fillPolygon(Polygon polygon) {
        this.log.println("fillPolygon(Polygon):\n  p = " + polygon);
        this.g2D.fillPolygon(polygon);
    }

    public void fillPolygon(int[] iArr, int[] iArr2, int i) {
        this.log.println("fillPolygon(int[],int[],int):\n  xPoints = " + Arrays.toString(iArr) + "\n  yPoints = " + Arrays.toString(iArr2) + "\n  nPoints = " + i);
        this.g2D.fillPolygon(iArr, iArr2, i);
    }

    public void fillRect(int i, int i2, int i3, int i4) {
        this.log.println("g.fillRect(" + i + "," + i2 + "," + i3 + "," + i4 + ");");
        this.g2D.fillRect(i, i2, i3, i4);
    }

    public void fillRoundRect(int i, int i2, int i3, int i4, int i5, int i6) {
        this.log.println("fillRoundRect(" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5 + "," + i6 + ")");
        this.g2D.fillRoundRect(i, i2, i3, i4, i5, i6);
    }

    public Shape getClip() {
        this.log.println("getClip():");
        return this.g2D.getClip();
    }

    public Rectangle getClipBounds() {
        this.log.println("getClipBounds():");
        return this.g2D.getClipBounds();
    }

    public Rectangle getClipBounds(Rectangle rectangle) {
        this.log.println("getClipBounds(Rectangle):\n  r = " + rectangle);
        return this.g2D.getClipBounds(rectangle);
    }

    public Color getColor() {
        this.log.println("getColor():");
        return this.g2D.getColor();
    }

    public Font getFont() {
        this.log.println("getFont():");
        return this.g2D.getFont();
    }

    public FontMetrics getFontMetrics() {
        this.log.println("getFontMetrics():");
        return this.g2D.getFontMetrics();
    }

    public FontMetrics getFontMetrics(Font font) {
        this.log.println("getFontMetrics():");
        return this.g2D.getFontMetrics(font);
    }

    public boolean hitClip(int i, int i2, int i3, int i4) {
        this.log.println("hitClip(int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4);
        return this.g2D.hitClip(i, i2, i3, i4);
    }

    public void setClip(Shape shape) {
        this.log.println("setClip(Shape):\n  clip = " + shape);
        this.g2D.setClip(shape);
    }

    public void setClip(int i, int i2, int i3, int i4) {
        this.log.println("setClip(int,int,int,int):\n  x = " + i + "\n  y = " + i2 + "\n  width = " + i3 + "\n  height = " + i4);
        this.g2D.setClip(i, i2, i3, i4);
    }

    public void setColor(Color color) {
        this.log.printf(Locale.ROOT, "g.setColor(new Color(0x%08X));%n", new Object[]{Integer.valueOf(color.getRGB())});
        this.g2D.setColor(color);
    }

    public void setFont(Font font) {
        this.log.println("setFont(Font):\n  font = " + font);
        this.g2D.setFont(font);
    }

    public void setPaintMode() {
        this.log.println("setPaintMode():");
        this.g2D.setPaintMode();
    }

    public void setXORMode(Color color) {
        this.log.println("setXORMode(Color):\n  c1 = " + color);
        this.g2D.setXORMode(color);
    }

    public String toString() {
        this.log.println("toString():");
        return this.g2D.toString();
    }

    public void translate(int i, int i2) {
        this.log.println("translate(int,int):\n  x = " + i + "\n  y = " + i2);
        this.g2D.translate(i, i2);
    }
}
