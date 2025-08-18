package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.Slide;

public class DrawSlide extends DrawSheet {
    public DrawSlide(Slide<?, ?> slide) {
        super(slide);
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(Drawable.CURRENT_SLIDE, this.sheet);
        Background background = this.sheet.getBackground();
        if (background != null) {
            DrawFactory.getInstance(graphics2D).getDrawable((Background<?, ?>) background).draw(graphics2D);
        }
        super.draw(graphics2D);
        graphics2D.setRenderingHint(Drawable.CURRENT_SLIDE, (Object) null);
    }
}
