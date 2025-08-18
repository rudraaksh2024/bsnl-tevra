package org.apache.poi.sl.draw.geom;

import java.util.Objects;

public final class MoveToCommand implements MoveToCommandIf {
    private final AdjustPoint pt = new AdjustPoint();

    public AdjustPoint getPt() {
        return this.pt;
    }

    public void setPt(AdjustPointIf adjustPointIf) {
        if (adjustPointIf != null) {
            this.pt.setX(adjustPointIf.getX());
            this.pt.setY(adjustPointIf.getY());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MoveToCommand)) {
            return false;
        }
        return Objects.equals(this.pt, ((MoveToCommand) obj).pt);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.pt});
    }
}
