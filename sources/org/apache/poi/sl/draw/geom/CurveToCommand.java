package org.apache.poi.sl.draw.geom;

import java.util.Objects;

public final class CurveToCommand implements CurveToCommandIf {
    private final AdjustPoint pt1 = new AdjustPoint();
    private final AdjustPoint pt2 = new AdjustPoint();
    private final AdjustPoint pt3 = new AdjustPoint();

    public AdjustPoint getPt1() {
        return this.pt1;
    }

    public void setPt1(AdjustPointIf adjustPointIf) {
        if (adjustPointIf != null) {
            this.pt1.setX(adjustPointIf.getX());
            this.pt1.setY(adjustPointIf.getY());
        }
    }

    public AdjustPoint getPt2() {
        return this.pt2;
    }

    public void setPt2(AdjustPointIf adjustPointIf) {
        if (adjustPointIf != null) {
            this.pt2.setX(adjustPointIf.getX());
            this.pt2.setY(adjustPointIf.getY());
        }
    }

    public AdjustPoint getPt3() {
        return this.pt3;
    }

    public void setPt3(AdjustPointIf adjustPointIf) {
        if (adjustPointIf != null) {
            this.pt3.setX(adjustPointIf.getX());
            this.pt3.setY(adjustPointIf.getY());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CurveToCommand)) {
            return false;
        }
        CurveToCommand curveToCommand = (CurveToCommand) obj;
        if (!Objects.equals(this.pt1, curveToCommand.pt1) || !Objects.equals(this.pt2, curveToCommand.pt2) || !Objects.equals(this.pt3, curveToCommand.pt3)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.pt1, this.pt2, this.pt3});
    }
}
