package org.apache.poi.sl.draw.geom;

import java.util.Objects;

public final class ConnectionSite implements ConnectionSiteIf {
    private String ang;
    private final AdjustPoint pos = new AdjustPoint();

    public AdjustPoint getPos() {
        return this.pos;
    }

    public void setPos(AdjustPointIf adjustPointIf) {
        if (adjustPointIf != null) {
            this.pos.setX(adjustPointIf.getX());
            this.pos.setY(adjustPointIf.getY());
        }
    }

    public String getAng() {
        return this.ang;
    }

    public void setAng(String str) {
        this.ang = str;
    }

    public boolean isSetAng() {
        return this.ang != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConnectionSite)) {
            return false;
        }
        ConnectionSite connectionSite = (ConnectionSite) obj;
        if (!Objects.equals(this.pos, connectionSite.pos) || !Objects.equals(this.ang, connectionSite.ang)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.pos, this.ang});
    }
}
