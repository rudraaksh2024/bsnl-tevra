package org.apache.poi.sl.usermodel;

import java.util.List;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface ShapeContainer<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends Iterable<S> {
    void addShape(S s);

    AutoShape<S, P> createAutoShape();

    ConnectorShape<S, P> createConnector();

    FreeformShape<S, P> createFreeform();

    GroupShape<S, P> createGroup();

    ObjectShape<?, ?> createOleShape(PictureData pictureData);

    PictureShape<S, P> createPicture(PictureData pictureData);

    TableShape<S, P> createTable(int i, int i2);

    TextBox<S, P> createTextBox();

    List<S> getShapes();

    boolean removeShape(S s);
}
