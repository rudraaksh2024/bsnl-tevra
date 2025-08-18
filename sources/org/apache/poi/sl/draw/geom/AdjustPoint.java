package org.apache.poi.sl.draw.geom;

import java.util.Objects;

public class AdjustPoint implements AdjustPointIf {
    private String x;
    private String y;

    public String getX() {
        return this.x;
    }

    public void setX(String str) {
        this.x = str;
    }

    public boolean isSetX() {
        return this.x != null;
    }

    public String getY() {
        return this.y;
    }

    public void setY(String str) {
        this.y = str;
    }

    public boolean isSetY() {
        return this.y != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdjustPoint)) {
            return false;
        }
        AdjustPoint adjustPoint = (AdjustPoint) obj;
        if (!Objects.equals(this.x, adjustPoint.x) || !Objects.equals(this.y, adjustPoint.y)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.x, this.y});
    }
}
