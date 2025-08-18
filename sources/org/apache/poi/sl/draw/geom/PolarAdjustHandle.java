package org.apache.poi.sl.draw.geom;

import java.util.Objects;

public final class PolarAdjustHandle implements AdjustHandle {
    private String gdRefAng;
    private String gdRefR;
    private String maxAng;
    private String maxR;
    private String minAng;
    private String minR;
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

    public String getGdRefR() {
        return this.gdRefR;
    }

    public void setGdRefR(String str) {
        this.gdRefR = str;
    }

    public boolean isSetGdRefR() {
        return this.gdRefR != null;
    }

    public String getMinR() {
        return this.minR;
    }

    public void setMinR(String str) {
        this.minR = str;
    }

    public boolean isSetMinR() {
        return this.minR != null;
    }

    public String getMaxR() {
        return this.maxR;
    }

    public void setMaxR(String str) {
        this.maxR = str;
    }

    public boolean isSetMaxR() {
        return this.maxR != null;
    }

    public String getGdRefAng() {
        return this.gdRefAng;
    }

    public void setGdRefAng(String str) {
        this.gdRefAng = str;
    }

    public boolean isSetGdRefAng() {
        return this.gdRefAng != null;
    }

    public String getMinAng() {
        return this.minAng;
    }

    public void setMinAng(String str) {
        this.minAng = str;
    }

    public boolean isSetMinAng() {
        return this.minAng != null;
    }

    public String getMaxAng() {
        return this.maxAng;
    }

    public void setMaxAng(String str) {
        this.maxAng = str;
    }

    public boolean isSetMaxAng() {
        return this.maxAng != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PolarAdjustHandle)) {
            return false;
        }
        PolarAdjustHandle polarAdjustHandle = (PolarAdjustHandle) obj;
        if (!Objects.equals(this.pos, polarAdjustHandle.pos) || !Objects.equals(this.gdRefR, polarAdjustHandle.gdRefR) || !Objects.equals(this.minR, polarAdjustHandle.minR) || !Objects.equals(this.maxR, polarAdjustHandle.maxR) || !Objects.equals(this.gdRefAng, polarAdjustHandle.gdRefAng) || !Objects.equals(this.minAng, polarAdjustHandle.minAng) || !Objects.equals(this.maxAng, polarAdjustHandle.maxAng)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.pos, this.gdRefR, this.minR, this.maxR, this.gdRefAng, this.minAng, this.maxAng});
    }
}
