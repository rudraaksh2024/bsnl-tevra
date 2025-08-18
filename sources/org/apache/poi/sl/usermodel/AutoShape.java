package org.apache.poi.sl.usermodel;

import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface AutoShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends TextShape<S, P> {
}
