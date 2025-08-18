package org.apache.poi.sl.usermodel;

import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface TextBox<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends AutoShape<S, P> {
}
