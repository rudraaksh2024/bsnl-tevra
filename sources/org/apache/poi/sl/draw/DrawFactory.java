package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.ConnectorShape;
import org.apache.poi.sl.usermodel.FreeformShape;
import org.apache.poi.sl.usermodel.GraphicalFrame;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextShape;

public class DrawFactory {
    private static final ThreadLocal<DrawFactory> defaultFactory = new ThreadLocal<>();

    public static void setDefaultFactory(DrawFactory drawFactory) {
        if (drawFactory == null) {
            defaultFactory.remove();
        } else {
            defaultFactory.set(drawFactory);
        }
    }

    public static DrawFactory getInstance(Graphics2D graphics2D) {
        DrawFactory drawFactory;
        boolean z = false;
        if (graphics2D != null) {
            drawFactory = (DrawFactory) graphics2D.getRenderingHint(Drawable.DRAW_FACTORY);
            if (drawFactory != null) {
                z = true;
            }
        } else {
            drawFactory = null;
        }
        if (drawFactory == null) {
            drawFactory = defaultFactory.get();
        }
        if (drawFactory == null) {
            drawFactory = new DrawFactory();
        }
        if (graphics2D != null && !z) {
            graphics2D.setRenderingHint(Drawable.DRAW_FACTORY, drawFactory);
        }
        return drawFactory;
    }

    public Drawable getDrawable(Shape<?, ?> shape) {
        if (shape instanceof TextBox) {
            return getDrawable((TextBox<?, ?>) (TextBox) shape);
        }
        if (shape instanceof FreeformShape) {
            return getDrawable((FreeformShape<?, ?>) (FreeformShape) shape);
        }
        if (shape instanceof TextShape) {
            return getDrawable((TextShape<?, ?>) (TextShape) shape);
        }
        if (shape instanceof TableShape) {
            return getDrawable((TableShape<?, ?>) (TableShape) shape);
        }
        if (shape instanceof GroupShape) {
            return getDrawable((GroupShape<?, ?>) (GroupShape) shape);
        }
        if (shape instanceof PictureShape) {
            return getDrawable((PictureShape<?, ?>) (PictureShape) shape);
        }
        if (shape instanceof GraphicalFrame) {
            return getDrawable((GraphicalFrame<?, ?>) (GraphicalFrame) shape);
        }
        if (shape instanceof Background) {
            return getDrawable((Background<?, ?>) (Background) shape);
        }
        if (shape instanceof ConnectorShape) {
            return getDrawable((ConnectorShape<?, ?>) (ConnectorShape) shape);
        }
        if (shape instanceof Slide) {
            return getDrawable((Slide<?, ?>) (Slide) shape);
        }
        if (shape instanceof MasterSheet) {
            return getDrawable((MasterSheet<?, ?>) (MasterSheet) shape);
        }
        if (shape instanceof Sheet) {
            return getDrawable((Sheet<?, ?>) (Sheet) shape);
        }
        if (shape.getClass().isAnnotationPresent(DrawNotImplemented.class)) {
            return new DrawNothing(shape);
        }
        throw new IllegalArgumentException("Unsupported shape type: " + shape.getClass());
    }

    public DrawSlide getDrawable(Slide<?, ?> slide) {
        return new DrawSlide(slide);
    }

    public DrawSheet getDrawable(Sheet<?, ?> sheet) {
        return new DrawSheet(sheet);
    }

    public DrawMasterSheet getDrawable(MasterSheet<?, ?> masterSheet) {
        return new DrawMasterSheet(masterSheet);
    }

    public DrawTextBox getDrawable(TextBox<?, ?> textBox) {
        return new DrawTextBox(textBox);
    }

    public DrawFreeformShape getDrawable(FreeformShape<?, ?> freeformShape) {
        return new DrawFreeformShape(freeformShape);
    }

    public DrawConnectorShape getDrawable(ConnectorShape<?, ?> connectorShape) {
        return new DrawConnectorShape(connectorShape);
    }

    public DrawTableShape getDrawable(TableShape<?, ?> tableShape) {
        return new DrawTableShape(tableShape);
    }

    public DrawTextShape getDrawable(TextShape<?, ?> textShape) {
        return new DrawTextShape(textShape);
    }

    public DrawGroupShape getDrawable(GroupShape<?, ?> groupShape) {
        return new DrawGroupShape(groupShape);
    }

    public DrawPictureShape getDrawable(PictureShape<?, ?> pictureShape) {
        return new DrawPictureShape(pictureShape);
    }

    public DrawGraphicalFrame getDrawable(GraphicalFrame<?, ?> graphicalFrame) {
        return new DrawGraphicalFrame(graphicalFrame);
    }

    public DrawTextParagraph getDrawable(TextParagraph<?, ?, ?> textParagraph) {
        return new DrawTextParagraph(textParagraph);
    }

    public DrawBackground getDrawable(Background<?, ?> background) {
        return new DrawBackground(background);
    }

    public DrawTextFragment getTextFragment(TextLayout textLayout, AttributedString attributedString) {
        return new DrawTextFragment(textLayout, attributedString);
    }

    public DrawPaint getPaint(PlaceableShape<?, ?> placeableShape) {
        return new DrawPaint(placeableShape);
    }

    public void drawShape(Graphics2D graphics2D, Shape<?, ?> shape, Rectangle2D rectangle2D) {
        Rectangle2D anchor = shape.getAnchor();
        if (anchor.isEmpty()) {
            return;
        }
        if (rectangle2D == null || !rectangle2D.isEmpty()) {
            AffineTransform affineTransform = (AffineTransform) graphics2D.getRenderingHint(Drawable.GROUP_TRANSFORM);
            AffineTransform affineTransform2 = new AffineTransform();
            if (rectangle2D != null) {
                try {
                    double width = rectangle2D.getWidth() / anchor.getWidth();
                    double height = rectangle2D.getHeight() / anchor.getHeight();
                    affineTransform2.translate(rectangle2D.getCenterX(), rectangle2D.getCenterY());
                    affineTransform2.scale(width, height);
                    affineTransform2.translate(-anchor.getCenterX(), -anchor.getCenterY());
                } catch (Throwable th) {
                    graphics2D.setRenderingHint(Drawable.GROUP_TRANSFORM, affineTransform);
                    throw th;
                }
            }
            graphics2D.setRenderingHint(Drawable.GROUP_TRANSFORM, affineTransform2);
            Drawable drawable = getDrawable(shape);
            drawable.applyTransform(graphics2D);
            drawable.draw(graphics2D);
            graphics2D.setRenderingHint(Drawable.GROUP_TRANSFORM, affineTransform);
        }
    }

    public DrawFontManager getFontManager(Graphics2D graphics2D) {
        DrawFontManager drawFontManager = (DrawFontManager) graphics2D.getRenderingHint(Drawable.FONT_HANDLER);
        return drawFontManager != null ? drawFontManager : new DrawFontManagerDefault();
    }
}
