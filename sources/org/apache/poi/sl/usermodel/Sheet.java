package org.apache.poi.sl.usermodel;

import java.awt.Graphics2D;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;

public interface Sheet<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> extends ShapeContainer<S, P> {
    void draw(Graphics2D graphics2D);

    Background<S, P> getBackground();

    boolean getFollowMasterGraphics();

    MasterSheet<S, P> getMasterSheet();

    PlaceholderDetails getPlaceholderDetails(Placeholder placeholder);

    SlideShow<S, P> getSlideShow();
}
