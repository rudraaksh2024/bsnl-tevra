package org.apache.poi.sl.draw.geom;

public interface ConnectionSiteIf {
    String getAng();

    AdjustPointIf getPos();

    boolean isSetAng();

    void setAng(String str);

    void setPos(AdjustPointIf adjustPointIf);
}
