package org.apache.poi.sl.usermodel;

import java.util.List;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface Notes<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends Sheet<S, P> {
    List<? extends List<P>> getTextParagraphs();
}
