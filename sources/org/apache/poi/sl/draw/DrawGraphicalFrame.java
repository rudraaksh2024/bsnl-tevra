package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import org.apache.poi.sl.usermodel.GraphicalFrame;
import org.apache.poi.sl.usermodel.PictureShape;

public class DrawGraphicalFrame extends DrawShape {
    public DrawGraphicalFrame(GraphicalFrame<?, ?> graphicalFrame) {
        super(graphicalFrame);
    }

    public void draw(Graphics2D graphics2D) {
        PictureShape fallbackPicture = ((GraphicalFrame) getShape()).getFallbackPicture();
        if (fallbackPicture != null) {
            DrawFactory.getInstance(graphics2D).getDrawable((PictureShape<?, ?>) fallbackPicture).draw(graphics2D);
        }
    }
}
