package org.apache.poi.sl.usermodel;

import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface GroupShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends Shape<S, P>, ShapeContainer<S, P>, PlaceableShape<S, P> {
    Rectangle2D getInteriorAnchor();

    void setInteriorAnchor(Rectangle2D rectangle2D);
}
