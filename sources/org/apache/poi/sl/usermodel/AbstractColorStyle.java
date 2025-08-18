package org.apache.poi.sl.usermodel;

import java.util.Objects;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.util.Internal;

@Internal
public abstract class AbstractColorStyle implements ColorStyle {
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ColorStyle)) {
            return false;
        }
        return Objects.equals(DrawPaint.applyColorTransform(this), DrawPaint.applyColorTransform((ColorStyle) obj));
    }

    public int hashCode() {
        return DrawPaint.applyColorTransform(this).hashCode();
    }
}
