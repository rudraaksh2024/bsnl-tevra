package org.apache.poi.sl.usermodel;

import java.awt.Color;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface TableCell<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends TextShape<S, P> {

    public enum BorderEdge {
        bottom,
        left,
        top,
        right
    }

    StrokeStyle getBorderStyle(BorderEdge borderEdge);

    int getGridSpan();

    int getRowSpan();

    boolean isMerged();

    void removeBorder(BorderEdge borderEdge);

    void setBorderColor(BorderEdge borderEdge, Color color);

    void setBorderCompound(BorderEdge borderEdge, StrokeStyle.LineCompound lineCompound);

    void setBorderDash(BorderEdge borderEdge, StrokeStyle.LineDash lineDash);

    void setBorderStyle(BorderEdge borderEdge, StrokeStyle strokeStyle);

    void setBorderWidth(BorderEdge borderEdge, double d);
}
