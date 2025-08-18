package org.apache.poi.sl.usermodel;

import java.awt.geom.Path2D;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface FreeformShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends AutoShape<S, P> {
    Path2D getPath();

    int setPath(Path2D path2D);
}
