package org.apache.poi.sl.draw.geom;

import java.util.Objects;

public final class XYAdjustHandle implements AdjustHandle {
    private String gdRefX;
    private String gdRefY;
    private String maxX;
    private String maxY;
    private String minX;
    private String minY;
    private AdjustPoint pos;

    public AdjustPoint getPos() {
        return this.pos;
    }

    public void setPos(AdjustPoint adjustPoint) {
        this.pos = adjustPoint;
    }

    public boolean isSetPos() {
        return this.pos != null;
    }

    public String getGdRefX() {
        return this.gdRefX;
    }

    public void setGdRefX(String str) {
        this.gdRefX = str;
    }

    public boolean isSetGdRefX() {
        return this.gdRefX != null;
    }

    public String getMinX() {
        return this.minX;
    }

    public void setMinX(String str) {
        this.minX = str;
    }

    public boolean isSetMinX() {
        return this.minX != null;
    }

    public String getMaxX() {
        return this.maxX;
    }

    public void setMaxX(String str) {
        this.maxX = str;
    }

    public boolean isSetMaxX() {
        return this.maxX != null;
    }

    public String getGdRefY() {
        return this.gdRefY;
    }

    public void setGdRefY(String str) {
        this.gdRefY = str;
    }

    public boolean isSetGdRefY() {
        return this.gdRefY != null;
    }

    public String getMinY() {
        return this.minY;
    }

    public void setMinY(String str) {
        this.minY = str;
    }

    public boolean isSetMinY() {
        return this.minY != null;
    }

    public String getMaxY() {
        return this.maxY;
    }

    public void setMaxY(String str) {
        this.maxY = str;
    }

    public boolean isSetMaxY() {
        return this.maxY != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof XYAdjustHandle)) {
            return false;
        }
        XYAdjustHandle xYAdjustHandle = (XYAdjustHandle) obj;
        if (!Objects.equals(this.pos, xYAdjustHandle.pos) || !Objects.equals(this.gdRefX, xYAdjustHandle.gdRefX) || !Objects.equals(this.minX, xYAdjustHandle.minX) || !Objects.equals(this.maxX, xYAdjustHandle.maxX) || !Objects.equals(this.gdRefY, xYAdjustHandle.gdRefY) || !Objects.equals(this.minY, xYAdjustHandle.minY) || !Objects.equals(this.maxY, xYAdjustHandle.maxY)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.pos, this.gdRefX, this.minX, this.maxX, this.gdRefY, this.minY, this.maxY});
    }
}
