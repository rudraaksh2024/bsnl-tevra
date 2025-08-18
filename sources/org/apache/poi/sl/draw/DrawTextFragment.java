package org.apache.poi.sl.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public class DrawTextFragment implements Drawable {
    final TextLayout layout;
    final AttributedString str;
    double x;
    double y;

    public void applyTransform(Graphics2D graphics2D) {
    }

    public void drawContent(Graphics2D graphics2D) {
    }

    public DrawTextFragment(TextLayout textLayout, AttributedString attributedString) {
        this.layout = textLayout;
        this.str = attributedString;
    }

    public void setPosition(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public void draw(Graphics2D graphics2D) {
        if (this.str != null) {
            double ascent = this.y + ((double) this.layout.getAscent());
            Integer num = (Integer) graphics2D.getRenderingHint(Drawable.TEXT_RENDERING_MODE);
            if (num == null || num.intValue() != 2) {
                try {
                    graphics2D.drawString(this.str.getIterator(), (float) this.x, (float) ascent);
                } catch (ClassCastException unused) {
                    replaceForgroundPaintWithBlack(this.str);
                    graphics2D.drawString(this.str.getIterator(), (float) this.x, (float) ascent);
                }
            } else {
                this.layout.draw(graphics2D, (float) this.x, (float) ascent);
            }
        }
    }

    private void replaceForgroundPaintWithBlack(AttributedString attributedString) {
        AttributedCharacterIterator iterator = attributedString.getIterator(new TextAttribute[]{TextAttribute.FOREGROUND});
        for (char first = iterator.first(); first != 65535; first = iterator.next()) {
            attributedString.addAttribute(TextAttribute.FOREGROUND, Color.BLACK, iterator.getBeginIndex(), iterator.getEndIndex());
        }
    }

    public TextLayout getLayout() {
        return this.layout;
    }

    public AttributedString getAttributedString() {
        return this.str;
    }

    public float getHeight() {
        return (float) ((double) (this.layout.getAscent() + this.layout.getDescent()));
    }

    public float getLeading() {
        double leading = (double) this.layout.getLeading();
        if (leading == 0.0d) {
            leading = ((double) (this.layout.getAscent() + this.layout.getDescent())) * 0.15d;
        }
        return (float) leading;
    }

    public float getWidth() {
        return this.layout.getAdvance();
    }

    public String getString() {
        AttributedString attributedString = this.str;
        if (attributedString == null) {
            return "";
        }
        AttributedCharacterIterator iterator = attributedString.getIterator();
        StringBuilder sb = new StringBuilder();
        for (char first = iterator.first(); first != 65535; first = iterator.next()) {
            sb.append(first);
        }
        return sb.toString();
    }

    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + getString();
    }
}
