package org.apache.poi.ss.usermodel;

public interface Shape {
    ChildAnchor getAnchor();

    Shape getParent();

    String getShapeName();

    boolean isNoFill();

    void setFillColor(int i, int i2, int i3);

    void setLineStyleColor(int i, int i2, int i3);

    void setNoFill(boolean z);
}
