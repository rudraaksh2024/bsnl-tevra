package org.apache.poi.sl.usermodel;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface TextShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends SimpleShape<S, P>, Iterable<P> {

    public enum TextAutofit {
        NONE,
        NORMAL,
        SHAPE
    }

    public enum TextDirection {
        HORIZONTAL,
        VERTICAL,
        VERTICAL_270,
        STACKED
    }

    TextRun appendText(String str, boolean z);

    Insets2D getInsets();

    String getText();

    TextDirection getTextDirection();

    double getTextHeight();

    double getTextHeight(Graphics2D graphics2D);

    List<P> getTextParagraphs();

    TextPlaceholder getTextPlaceholder();

    Double getTextRotation();

    VerticalAlignment getVerticalAlignment();

    boolean getWordWrap();

    boolean isHorizontalCentered();

    Rectangle2D resizeToFitText();

    Rectangle2D resizeToFitText(Graphics2D graphics2D);

    void setHorizontalCentered(Boolean bool);

    void setInsets(Insets2D insets2D);

    TextRun setText(String str);

    void setTextDirection(TextDirection textDirection);

    void setTextPlaceholder(TextPlaceholder textPlaceholder);

    void setTextRotation(Double d);

    void setVerticalAlignment(VerticalAlignment verticalAlignment);

    void setWordWrap(boolean z);

    public enum TextPlaceholder {
        TITLE(0),
        BODY(1),
        CENTER_TITLE(6),
        CENTER_BODY(5),
        HALF_BODY(7),
        QUARTER_BODY(8),
        NOTES(2),
        OTHER(4);
        
        public final int nativeId;

        private TextPlaceholder(int i) {
            this.nativeId = i;
        }

        public static TextPlaceholder fromNativeId(int i) {
            for (TextPlaceholder textPlaceholder : values()) {
                if (textPlaceholder.nativeId == i) {
                    return textPlaceholder;
                }
            }
            return null;
        }

        public static boolean isTitle(int i) {
            return i == TITLE.nativeId || i == CENTER_TITLE.nativeId;
        }
    }
}
