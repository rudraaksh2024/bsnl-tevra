package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;

public class DrawTextShape extends DrawSimpleShape {
    public DrawTextShape(TextShape<?, ?> textShape) {
        super(textShape);
    }

    public void drawContent(Graphics2D graphics2D) {
        double d;
        double height;
        double height2;
        Graphics2D graphics2D2 = graphics2D;
        TextShape shape = getShape();
        Rectangle2D anchor = DrawShape.getAnchor(graphics2D2, (PlaceableShape<?, ?>) shape);
        if (anchor != null) {
            Insets2D insets = shape.getInsets();
            double x = anchor.getX() + insets.left;
            double y = anchor.getY();
            AffineTransform transform = graphics2D.getTransform();
            boolean flipVertical = shape.getFlipVertical();
            boolean flipHorizontal = shape.getFlipHorizontal();
            ShapeContainer parent = shape.getParent();
            while (parent instanceof PlaceableShape) {
                PlaceableShape placeableShape = (PlaceableShape) parent;
                flipVertical ^= placeableShape.getFlipVertical();
                flipHorizontal ^= placeableShape.getFlipHorizontal();
                parent = placeableShape.getParent();
            }
            if (flipVertical ^ flipHorizontal) {
                double x2 = anchor.getX();
                double y2 = anchor.getY();
                graphics2D2.translate(anchor.getWidth() + x2, y2);
                d = x;
                graphics2D2.scale(-1.0d, 1.0d);
                graphics2D2.translate(-x2, -y2);
            } else {
                d = x;
            }
            Double textRotation = shape.getTextRotation();
            if (!(textRotation == null || textRotation.doubleValue() == 0.0d)) {
                double centerX = anchor.getCenterX();
                double centerY = anchor.getCenterY();
                graphics2D2.translate(centerX, centerY);
                graphics2D2.rotate(Math.toRadians(textRotation.doubleValue()));
                graphics2D2.translate(-centerX, -centerY);
            }
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$sl$usermodel$VerticalAlignment[shape.getVerticalAlignment().ordinal()];
            if (i != 2) {
                if (i != 3) {
                    height2 = insets.top;
                } else {
                    height2 = insets.top + ((((anchor.getHeight() - getTextHeight(graphics2D)) - insets.top) - insets.bottom) / 2.0d);
                }
                height = y + height2;
            } else {
                height = y + ((anchor.getHeight() - getTextHeight(graphics2D)) - insets.bottom);
            }
            double d2 = height;
            TextShape.TextDirection textDirection = shape.getTextDirection();
            if (textDirection == TextShape.TextDirection.VERTICAL || textDirection == TextShape.TextDirection.VERTICAL_270) {
                double d3 = textDirection == TextShape.TextDirection.VERTICAL ? 90.0d : 270.0d;
                double centerX2 = anchor.getCenterX();
                double centerY2 = anchor.getCenterY();
                graphics2D2.translate(centerX2, centerY2);
                graphics2D2.rotate(Math.toRadians(d3));
                graphics2D2.translate(-centerX2, -centerY2);
                double width = (anchor.getWidth() - anchor.getHeight()) / 2.0d;
                graphics2D2.translate(width, -width);
            }
            drawParagraphs(graphics2D, d, d2);
            graphics2D2.setTransform(transform);
        }
    }

    /* renamed from: org.apache.poi.sl.draw.DrawTextShape$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$sl$usermodel$VerticalAlignment;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.sl.usermodel.VerticalAlignment[] r0 = org.apache.poi.sl.usermodel.VerticalAlignment.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$sl$usermodel$VerticalAlignment = r0
                org.apache.poi.sl.usermodel.VerticalAlignment r1 = org.apache.poi.sl.usermodel.VerticalAlignment.TOP     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$VerticalAlignment     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.sl.usermodel.VerticalAlignment r1 = org.apache.poi.sl.usermodel.VerticalAlignment.BOTTOM     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$sl$usermodel$VerticalAlignment     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.sl.usermodel.VerticalAlignment r1 = org.apache.poi.sl.usermodel.VerticalAlignment.MIDDLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawTextShape.AnonymousClass1.<clinit>():void");
        }
    }

    public double drawParagraphs(Graphics2D graphics2D, double d, double d2) {
        double d3;
        double d4;
        Graphics2D graphics2D2 = graphics2D;
        DrawFactory instance = DrawFactory.getInstance(graphics2D);
        Iterator it = getShape().iterator();
        int i = 1;
        double d5 = d2;
        boolean z = true;
        int i2 = 0;
        while (it.hasNext()) {
            TextParagraph textParagraph = (TextParagraph) it.next();
            DrawTextParagraph drawable = instance.getDrawable((TextParagraph<?, ?, ?>) textParagraph);
            TextParagraph.BulletStyle bulletStyle = textParagraph.getBulletStyle();
            if (bulletStyle == null || bulletStyle.getAutoNumberingScheme() == null) {
                i2 = -1;
            } else {
                Integer autoNumberingStartAt = bulletStyle.getAutoNumberingStartAt();
                if (autoNumberingStartAt == null) {
                    autoNumberingStartAt = Integer.valueOf(i);
                }
                if (autoNumberingStartAt.intValue() > i2) {
                    i2 = autoNumberingStartAt.intValue();
                }
            }
            drawable.setAutoNumberingIdx(i2);
            drawable.breakText(graphics2D2);
            if (z) {
                d3 = (double) drawable.getFirstLineLeading();
            } else {
                Double spaceBefore = textParagraph.getSpaceBefore();
                if (spaceBefore == null) {
                    spaceBefore = Double.valueOf(0.0d);
                }
                if (spaceBefore.doubleValue() > 0.0d) {
                    d3 = spaceBefore.doubleValue() * 0.01d * ((double) drawable.getFirstLineHeight());
                } else {
                    d3 = -spaceBefore.doubleValue();
                }
            }
            double d6 = d5 + d3;
            drawable.setPosition(d, d6);
            drawable.setFirstParagraph(z);
            drawable.draw(graphics2D2);
            d5 = d6 + drawable.getY();
            if (it.hasNext()) {
                Double spaceAfter = textParagraph.getSpaceAfter();
                if (spaceAfter == null) {
                    spaceAfter = Double.valueOf(0.0d);
                }
                if (spaceAfter.doubleValue() > 0.0d) {
                    d4 = spaceAfter.doubleValue() * 0.01d * ((double) drawable.getLastLineHeight());
                } else {
                    d4 = -spaceAfter.doubleValue();
                }
                d5 += d4;
            }
            i2++;
            i = 1;
            z = false;
        }
        return d5 - d2;
    }

    public double getTextHeight() {
        return getTextHeight((Graphics2D) null);
    }

    public double getTextHeight(Graphics2D graphics2D) {
        Graphics2D createGraphics = new BufferedImage(1, 1, 1).createGraphics();
        if (graphics2D != null) {
            createGraphics.addRenderingHints(graphics2D.getRenderingHints());
            createGraphics.setTransform(graphics2D.getTransform());
        }
        return drawParagraphs(createGraphics, 0.0d, 0.0d);
    }

    /* access modifiers changed from: protected */
    public TextShape<?, ? extends TextParagraph<?, ?, ? extends TextRun>> getShape() {
        return (TextShape) this.shape;
    }
}
