package org.apache.poi.sl.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.sl.usermodel.FreeformShape;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.NotImplemented;

public class SLGraphics extends Graphics2D implements Cloneable {
    private static final Logger LOG = LogManager.getLogger((Class<?>) SLGraphics.class);
    private Color _background = Color.black;
    private Font _font = new Font(HSSFFont.FONT_ARIAL, 0, 12);
    private Color _foreground = Color.white;
    private GroupShape<?, ?> _group;
    private RenderingHints _hints = new RenderingHints((Map) null);
    private Paint _paint = Color.black;
    private Stroke _stroke = new BasicStroke();
    private AffineTransform _transform = new AffineTransform();

    public void copyArea(int i, int i2, int i3, int i4, int i5, int i6) {
    }

    public void dispose() {
    }

    public SLGraphics(GroupShape<?, ?> groupShape) {
        this._group = groupShape;
    }

    public GroupShape<?, ?> getShapeGroup() {
        return this._group;
    }

    public Font getFont() {
        return this._font;
    }

    public void setFont(Font font) {
        this._font = font;
    }

    public Color getColor() {
        return this._foreground;
    }

    public void setColor(Color color) {
        setPaint(color);
    }

    public Stroke getStroke() {
        return this._stroke;
    }

    public void setStroke(Stroke stroke) {
        this._stroke = stroke;
    }

    public Paint getPaint() {
        return this._paint;
    }

    public void setPaint(Paint paint) {
        if (paint != null) {
            this._paint = paint;
            if (paint instanceof Color) {
                this._foreground = (Color) paint;
            }
        }
    }

    public AffineTransform getTransform() {
        return new AffineTransform(this._transform);
    }

    public void setTransform(AffineTransform affineTransform) {
        this._transform = new AffineTransform(affineTransform);
    }

    public void draw(Shape shape) {
        Path2D.Double doubleR = new Path2D.Double(this._transform.createTransformedShape(shape));
        FreeformShape<?, ?> createFreeform = this._group.createFreeform();
        createFreeform.setPath(doubleR);
        createFreeform.setFillColor((Color) null);
        applyStroke(createFreeform);
        Color color = this._paint;
        if (color instanceof Color) {
            createFreeform.setStrokeStyle(color);
        }
    }

    public void drawString(String str, float f, float f2) {
        String str2 = str;
        TextBox<?, ?> createTextBox = this._group.createTextBox();
        TextRun textRun = (TextRun) ((TextParagraph) createTextBox.getTextParagraphs().get(0)).getTextRuns().get(0);
        textRun.setFontSize(Double.valueOf((double) this._font.getSize()));
        textRun.setFontFamily(this._font.getFamily());
        if (getColor() != null) {
            textRun.setFontColor((PaintStyle) DrawPaint.createSolidPaint(getColor()));
        }
        if (this._font.isBold()) {
            textRun.setBold(true);
        }
        if (this._font.isItalic()) {
            textRun.setItalic(true);
        }
        createTextBox.setText(str2);
        createTextBox.setInsets(new Insets2D(0.0d, 0.0d, 0.0d, 0.0d));
        createTextBox.setWordWrap(false);
        createTextBox.setHorizontalCentered(false);
        createTextBox.setVerticalAlignment(VerticalAlignment.MIDDLE);
        TextLayout textLayout = new TextLayout(str2, this._font, getFontRenderContext());
        float ascent = textLayout.getAscent();
        float f3 = ascent * 2.0f;
        createTextBox.setAnchor(new Rectangle((int) f, (int) (f2 - ((f3 / 2.0f) + (ascent / 2.0f))), (int) ((float) Math.floor((double) textLayout.getAdvance())), (int) f3));
    }

    public void fill(Shape shape) {
        Path2D.Double doubleR = new Path2D.Double(this._transform.createTransformedShape(shape));
        FreeformShape<?, ?> createFreeform = this._group.createFreeform();
        createFreeform.setPath(doubleR);
        applyPaint(createFreeform);
        createFreeform.setStrokeStyle(new Object[0]);
    }

    public void translate(int i, int i2) {
        this._transform.translate((double) i, (double) i2);
    }

    @NotImplemented
    public void clip(Shape shape) {
        logNotImplemented();
    }

    @NotImplemented
    public Shape getClip() {
        logNotImplemented();
        return null;
    }

    public void scale(double d, double d2) {
        this._transform.scale(d, d2);
    }

    public void drawRoundRect(int i, int i2, int i3, int i4, int i5, int i6) {
        draw(new RoundRectangle2D.Double((double) i, (double) i2, (double) i3, (double) i4, (double) i5, (double) i6));
    }

    public void drawString(String str, int i, int i2) {
        drawString(str, (float) i, (float) i2);
    }

    public void fillOval(int i, int i2, int i3, int i4) {
        fill(new Ellipse2D.Double((double) i, (double) i2, (double) i3, (double) i4));
    }

    public void fillRoundRect(int i, int i2, int i3, int i4, int i5, int i6) {
        fill(new RoundRectangle2D.Double((double) i, (double) i2, (double) i3, (double) i4, (double) i5, (double) i6));
    }

    public void fillArc(int i, int i2, int i3, int i4, int i5, int i6) {
        fill(new Arc2D.Double((double) i, (double) i2, (double) i3, (double) i4, (double) i5, (double) i6, 2));
    }

    public void drawArc(int i, int i2, int i3, int i4, int i5, int i6) {
        draw(new Arc2D.Double((double) i, (double) i2, (double) i3, (double) i4, (double) i5, (double) i6, 0));
    }

    public void drawPolyline(int[] iArr, int[] iArr2, int i) {
        if (i > 0) {
            GeneralPath generalPath = new GeneralPath();
            generalPath.moveTo((float) iArr[0], (float) iArr2[0]);
            for (int i2 = 1; i2 < i; i2++) {
                generalPath.lineTo((float) iArr[i2], (float) iArr2[i2]);
            }
            draw(generalPath);
        }
    }

    public void drawOval(int i, int i2, int i3, int i4) {
        draw(new Ellipse2D.Double((double) i, (double) i2, (double) i3, (double) i4));
    }

    @NotImplemented
    public boolean drawImage(Image image, int i, int i2, Color color, ImageObserver imageObserver) {
        logNotImplemented();
        return false;
    }

    @NotImplemented
    public boolean drawImage(Image image, int i, int i2, int i3, int i4, Color color, ImageObserver imageObserver) {
        logNotImplemented();
        return false;
    }

    @NotImplemented
    public boolean drawImage(Image image, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, ImageObserver imageObserver) {
        logNotImplemented();
        return false;
    }

    @NotImplemented
    public boolean drawImage(Image image, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Color color, ImageObserver imageObserver) {
        logNotImplemented();
        return false;
    }

    @NotImplemented
    public boolean drawImage(Image image, int i, int i2, ImageObserver imageObserver) {
        logNotImplemented();
        return false;
    }

    public void drawLine(int i, int i2, int i3, int i4) {
        draw(new Line2D.Double((double) i, (double) i2, (double) i3, (double) i4));
    }

    public void fillPolygon(int[] iArr, int[] iArr2, int i) {
        fill(new Polygon(iArr, iArr2, i));
    }

    public void fillRect(int i, int i2, int i3, int i4) {
        fill(new Rectangle(i, i2, i3, i4));
    }

    public void drawRect(int i, int i2, int i3, int i4) {
        draw(new Rectangle(i, i2, i3, i4));
    }

    public void drawPolygon(int[] iArr, int[] iArr2, int i) {
        draw(new Polygon(iArr, iArr2, i));
    }

    public void clipRect(int i, int i2, int i3, int i4) {
        clip(new Rectangle(i, i2, i3, i4));
    }

    @NotImplemented
    public void setClip(Shape shape) {
        logNotImplemented();
    }

    public Rectangle getClipBounds() {
        logNotImplemented();
        return null;
    }

    public void drawString(AttributedCharacterIterator attributedCharacterIterator, int i, int i2) {
        drawString(attributedCharacterIterator, (float) i, (float) i2);
    }

    public void clearRect(int i, int i2, int i3, int i4) {
        Paint paint = getPaint();
        setColor(getBackground());
        fillRect(i, i2, i3, i4);
        setPaint(paint);
    }

    public void setClip(int i, int i2, int i3, int i4) {
        setClip(new Rectangle(i, i2, i3, i4));
    }

    public void rotate(double d) {
        this._transform.rotate(d);
    }

    public void rotate(double d, double d2, double d3) {
        this._transform.rotate(d, d2, d3);
    }

    public void shear(double d, double d2) {
        this._transform.shear(d, d2);
    }

    public FontRenderContext getFontRenderContext() {
        return new FontRenderContext(new AffineTransform(), RenderingHints.VALUE_TEXT_ANTIALIAS_ON.equals(getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING)), RenderingHints.VALUE_FRACTIONALMETRICS_ON.equals(getRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS)));
    }

    public void transform(AffineTransform affineTransform) {
        this._transform.concatenate(affineTransform);
    }

    public void drawImage(BufferedImage bufferedImage, BufferedImageOp bufferedImageOp, int i, int i2) {
        drawImage((Image) bufferedImageOp.filter(bufferedImage, (BufferedImage) null), i, i2, (ImageObserver) null);
    }

    public void setBackground(Color color) {
        if (color != null) {
            this._background = color;
        }
    }

    public Color getBackground() {
        return this._background;
    }

    @NotImplemented
    public void setComposite(Composite composite) {
        logNotImplemented();
    }

    @NotImplemented
    public Composite getComposite() {
        logNotImplemented();
        return null;
    }

    public Object getRenderingHint(RenderingHints.Key key) {
        return this._hints.get(key);
    }

    public void setRenderingHint(RenderingHints.Key key, Object obj) {
        this._hints.put(key, obj);
    }

    public void drawGlyphVector(GlyphVector glyphVector, float f, float f2) {
        fill(glyphVector.getOutline(f, f2));
    }

    public GraphicsConfiguration getDeviceConfiguration() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }

    public void addRenderingHints(Map<?, ?> map) {
        this._hints.putAll(map);
    }

    public void translate(double d, double d2) {
        this._transform.translate(d, d2);
    }

    @NotImplemented
    public void drawString(AttributedCharacterIterator attributedCharacterIterator, float f, float f2) {
        logNotImplemented();
    }

    public boolean hit(Rectangle rectangle, Shape shape, boolean z) {
        if (z) {
            shape = getStroke().createStrokedShape(shape);
        }
        return getTransform().createTransformedShape(shape).intersects(rectangle);
    }

    public RenderingHints getRenderingHints() {
        return this._hints;
    }

    public void setRenderingHints(Map<?, ?> map) {
        RenderingHints renderingHints = new RenderingHints((Map) null);
        this._hints = renderingHints;
        renderingHints.putAll(map);
    }

    @NotImplemented
    public boolean drawImage(Image image, AffineTransform affineTransform, ImageObserver imageObserver) {
        logNotImplemented();
        return false;
    }

    @NotImplemented
    public boolean drawImage(Image image, int i, int i2, int i3, int i4, ImageObserver imageObserver) {
        logNotImplemented();
        return false;
    }

    public Graphics create() {
        try {
            return (Graphics) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public FontMetrics getFontMetrics(Font font) {
        return Toolkit.getDefaultToolkit().getFontMetrics(font);
    }

    @NotImplemented
    public void setXORMode(Color color) {
        logNotImplemented();
    }

    @NotImplemented
    public void setPaintMode() {
        logNotImplemented();
    }

    @NotImplemented
    public void drawRenderedImage(RenderedImage renderedImage, AffineTransform affineTransform) {
        logNotImplemented();
    }

    @NotImplemented
    public void drawRenderableImage(RenderableImage renderableImage, AffineTransform affineTransform) {
        logNotImplemented();
    }

    /* access modifiers changed from: protected */
    public void applyStroke(SimpleShape<?, ?> simpleShape) {
        BasicStroke basicStroke = this._stroke;
        if (basicStroke instanceof BasicStroke) {
            BasicStroke basicStroke2 = basicStroke;
            simpleShape.setStrokeStyle(Double.valueOf((double) basicStroke2.getLineWidth()));
            if (basicStroke2.getDashArray() != null) {
                simpleShape.setStrokeStyle(StrokeStyle.LineDash.DASH);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void applyPaint(SimpleShape<?, ?> simpleShape) {
        Color color = this._paint;
        if (color instanceof Color) {
            simpleShape.setFillColor(color);
        }
    }

    private void logNotImplemented() {
        LOG.atWarn().log("Not implemented");
    }
}
