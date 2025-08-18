package org.apache.poi.hssf.usermodel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.NotImplemented;

public class EscherGraphics extends Graphics {
    private static final Logger LOG = LogManager.getLogger((Class<?>) EscherGraphics.class);
    private Color background = Color.white;
    private final HSSFShapeGroup escherGroup;
    private Font font;
    private Color foreground;
    private final float verticalPixelsPerPoint;
    private final float verticalPointsPerPixel;
    private final HSSFWorkbook workbook;

    public void dispose() {
    }

    public Rectangle getClipBounds() {
        return null;
    }

    public EscherGraphics(HSSFShapeGroup hSSFShapeGroup, HSSFWorkbook hSSFWorkbook, Color color, float f) {
        this.escherGroup = hSSFShapeGroup;
        this.workbook = hSSFWorkbook;
        this.verticalPointsPerPixel = f;
        this.verticalPixelsPerPoint = 1.0f / f;
        this.font = new Font(HSSFFont.FONT_ARIAL, 0, 10);
        this.foreground = color;
    }

    EscherGraphics(HSSFShapeGroup hSSFShapeGroup, HSSFWorkbook hSSFWorkbook, Color color, Font font2, float f) {
        this.escherGroup = hSSFShapeGroup;
        this.workbook = hSSFWorkbook;
        this.foreground = color;
        this.font = font2;
        this.verticalPointsPerPixel = f;
        this.verticalPixelsPerPoint = 1.0f / f;
    }

    public void clearRect(int i, int i2, int i3, int i4) {
        Color color = this.foreground;
        setColor(this.background);
        fillRect(i, i2, i3, i4);
        setColor(color);
    }

    @NotImplemented
    public void clipRect(int i, int i2, int i3, int i4) {
        LOG.atWarn().log("clipRect not supported");
    }

    @NotImplemented
    public void copyArea(int i, int i2, int i3, int i4, int i5, int i6) {
        LOG.atWarn().log("copyArea not supported");
    }

    public Graphics create() {
        return new EscherGraphics(this.escherGroup, this.workbook, this.foreground, this.font, this.verticalPointsPerPixel);
    }

    @NotImplemented
    public void drawArc(int i, int i2, int i3, int i4, int i5, int i6) {
        LOG.atWarn().log("drawArc not supported");
    }

    @NotImplemented
    public boolean drawImage(Image image, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Color color, ImageObserver imageObserver) {
        LOG.atWarn().log("drawImage not supported");
        return true;
    }

    @NotImplemented
    public boolean drawImage(Image image, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, ImageObserver imageObserver) {
        LOG.atWarn().log("drawImage not supported");
        return true;
    }

    public boolean drawImage(Image image, int i, int i2, int i3, int i4, Color color, ImageObserver imageObserver) {
        ImageObserver imageObserver2 = imageObserver;
        return drawImage(image, i, i2, i + i3, i2 + i4, 0, 0, image.getWidth(imageObserver2), image.getHeight(imageObserver2), color, imageObserver2);
    }

    public boolean drawImage(Image image, int i, int i2, int i3, int i4, ImageObserver imageObserver) {
        ImageObserver imageObserver2 = imageObserver;
        return drawImage(image, i, i2, i + i3, i2 + i4, 0, 0, image.getWidth(imageObserver2), image.getHeight(imageObserver2), imageObserver2);
    }

    public boolean drawImage(Image image, int i, int i2, Color color, ImageObserver imageObserver) {
        return drawImage(image, i, i2, image.getWidth(imageObserver), image.getHeight(imageObserver), color, imageObserver);
    }

    public boolean drawImage(Image image, int i, int i2, ImageObserver imageObserver) {
        return drawImage(image, i, i2, image.getWidth(imageObserver), image.getHeight(imageObserver), imageObserver);
    }

    public void drawLine(int i, int i2, int i3, int i4) {
        drawLine(i, i2, i3, i4, 0);
    }

    public void drawLine(int i, int i2, int i3, int i4, int i5) {
        HSSFSimpleShape createShape = this.escherGroup.createShape(new HSSFChildAnchor(i, i2, i3, i4));
        createShape.setShapeType(20);
        createShape.setLineWidth(i5);
        createShape.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
    }

    public void drawOval(int i, int i2, int i3, int i4) {
        HSSFSimpleShape createShape = this.escherGroup.createShape(new HSSFChildAnchor(i, i2, i3 + i, i4 + i2));
        createShape.setShapeType(3);
        createShape.setLineWidth(0);
        createShape.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        createShape.setNoFill(true);
    }

    public void drawPolygon(int[] iArr, int[] iArr2, int i) {
        int findBiggest = findBiggest(iArr);
        int findBiggest2 = findBiggest(iArr2);
        int findSmallest = findSmallest(iArr);
        int findSmallest2 = findSmallest(iArr2);
        HSSFPolygon createPolygon = this.escherGroup.createPolygon(new HSSFChildAnchor(findSmallest, findSmallest2, findBiggest, findBiggest2));
        createPolygon.setPolygonDrawArea(findBiggest - findSmallest, findBiggest2 - findSmallest2);
        createPolygon.setPoints(addToAll(iArr, -findSmallest), addToAll(iArr2, -findSmallest2));
        createPolygon.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        createPolygon.setLineWidth(0);
        createPolygon.setNoFill(true);
    }

    private int[] addToAll(int[] iArr, int i) {
        int[] iArr2 = new int[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr2[i2] = iArr[i2] + i;
        }
        return iArr2;
    }

    @NotImplemented
    public void drawPolyline(int[] iArr, int[] iArr2, int i) {
        LOG.atWarn().log("drawPolyline not supported");
    }

    @NotImplemented
    public void drawRect(int i, int i2, int i3, int i4) {
        LOG.atWarn().log("drawRect not supported");
    }

    @NotImplemented
    public void drawRoundRect(int i, int i2, int i3, int i4, int i5, int i6) {
        LOG.atWarn().log("drawRoundRect not supported");
    }

    public void drawString(String str, int i, int i2) {
        Font font2;
        if (str != null && !str.isEmpty()) {
            if (this.font.getName().equals("SansSerif")) {
                font2 = new Font(HSSFFont.FONT_ARIAL, this.font.getStyle(), (int) (((float) this.font.getSize()) / this.verticalPixelsPerPoint));
            } else {
                font2 = new Font(this.font.getName(), this.font.getStyle(), (int) (((float) this.font.getSize()) / this.verticalPixelsPerPoint));
            }
            float f = this.verticalPixelsPerPoint;
            int size = (int) (((float) i2) - ((((float) this.font.getSize()) / f) + (f * 2.0f)));
            HSSFTextbox createTextbox = this.escherGroup.createTextbox(new HSSFChildAnchor(i, size, (StaticFontMetrics.getFontDetails(font2).getStringWidth(str) * 8) + 12 + i, (((int) ((((float) this.font.getSize()) / this.verticalPixelsPerPoint) + 6.0f)) * 2) + size));
            createTextbox.setNoFill(true);
            createTextbox.setLineStyle(-1);
            HSSFRichTextString hSSFRichTextString = new HSSFRichTextString(str);
            hSSFRichTextString.applyFont((org.apache.poi.ss.usermodel.Font) matchFont(font2));
            createTextbox.setString(hSSFRichTextString);
        }
    }

    private HSSFFont matchFont(Font font2) {
        HSSFColor findColor = this.workbook.getCustomPalette().findColor((byte) this.foreground.getRed(), (byte) this.foreground.getGreen(), (byte) this.foreground.getBlue());
        if (findColor == null) {
            findColor = this.workbook.getCustomPalette().findSimilarColor((byte) this.foreground.getRed(), (byte) this.foreground.getGreen(), (byte) this.foreground.getBlue());
        }
        boolean z = true;
        boolean z2 = (font2.getStyle() & 1) != 0;
        if ((font2.getStyle() & 2) == 0) {
            z = false;
        }
        HSSFFont findFont = this.workbook.findFont(z2, findColor.getIndex(), (short) (font2.getSize() * 20), font2.getName(), z, false, 0, (byte) 0);
        if (findFont != null) {
            return findFont;
        }
        HSSFFont createFont = this.workbook.createFont();
        createFont.setBold(z2);
        createFont.setColor(findColor.getIndex());
        createFont.setFontHeight((short) (font2.getSize() * 20));
        createFont.setFontName(font2.getName());
        createFont.setItalic(z);
        createFont.setStrikeout(false);
        createFont.setTypeOffset(0);
        createFont.setUnderline((byte) 0);
        return createFont;
    }

    public void drawString(AttributedCharacterIterator attributedCharacterIterator, int i, int i2) {
        LOG.atWarn().log("drawString not supported");
    }

    public void fillArc(int i, int i2, int i3, int i4, int i5, int i6) {
        LOG.atWarn().log("fillArc not supported");
    }

    public void fillOval(int i, int i2, int i3, int i4) {
        HSSFSimpleShape createShape = this.escherGroup.createShape(new HSSFChildAnchor(i, i2, i3 + i, i4 + i2));
        createShape.setShapeType(3);
        createShape.setLineStyle(-1);
        createShape.setFillColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        createShape.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        createShape.setNoFill(false);
    }

    public void fillPolygon(int[] iArr, int[] iArr2, int i) {
        int findBiggest = findBiggest(iArr);
        int findBiggest2 = findBiggest(iArr2);
        int findSmallest = findSmallest(iArr);
        int findSmallest2 = findSmallest(iArr2);
        HSSFPolygon createPolygon = this.escherGroup.createPolygon(new HSSFChildAnchor(findSmallest, findSmallest2, findBiggest, findBiggest2));
        createPolygon.setPolygonDrawArea(findBiggest - findSmallest, findBiggest2 - findSmallest2);
        createPolygon.setPoints(addToAll(iArr, -findSmallest), addToAll(iArr2, -findSmallest2));
        createPolygon.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        createPolygon.setFillColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
    }

    private int findBiggest(int[] iArr) {
        int i = Integer.MIN_VALUE;
        for (int i2 : iArr) {
            if (i2 > i) {
                i = i2;
            }
        }
        return i;
    }

    private int findSmallest(int[] iArr) {
        int i = Integer.MAX_VALUE;
        for (int i2 : iArr) {
            if (i2 < i) {
                i = i2;
            }
        }
        return i;
    }

    public void fillRect(int i, int i2, int i3, int i4) {
        HSSFSimpleShape createShape = this.escherGroup.createShape(new HSSFChildAnchor(i, i2, i3 + i, i4 + i2));
        createShape.setShapeType(1);
        createShape.setLineStyle(-1);
        createShape.setFillColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
        createShape.setLineStyleColor(this.foreground.getRed(), this.foreground.getGreen(), this.foreground.getBlue());
    }

    public void fillRoundRect(int i, int i2, int i3, int i4, int i5, int i6) {
        LOG.atWarn().log("fillRoundRect not supported");
    }

    public Shape getClip() {
        return getClipBounds();
    }

    public Color getColor() {
        return this.foreground;
    }

    public Font getFont() {
        return this.font;
    }

    public FontMetrics getFontMetrics(Font font2) {
        return Toolkit.getDefaultToolkit().getFontMetrics(font2);
    }

    public void setClip(int i, int i2, int i3, int i4) {
        setClip(new Rectangle(i, i2, i3, i4));
    }

    @NotImplemented
    public void setClip(Shape shape) {
        LOG.atWarn().log("setClip not supported");
    }

    public void setColor(Color color) {
        this.foreground = color;
    }

    public void setFont(Font font2) {
        this.font = font2;
    }

    @NotImplemented
    public void setPaintMode() {
        LOG.atWarn().log("setPaintMode not supported");
    }

    @NotImplemented
    public void setXORMode(Color color) {
        LOG.atWarn().log("setXORMode not supported");
    }

    @NotImplemented
    public void translate(int i, int i2) {
        LOG.atWarn().log("translate not supported");
    }

    public Color getBackground() {
        return this.background;
    }

    public void setBackground(Color color) {
        this.background = color;
    }

    /* access modifiers changed from: package-private */
    public HSSFShapeGroup getEscherGraphics() {
        return this.escherGroup;
    }
}
