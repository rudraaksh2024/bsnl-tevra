package org.apache.poi.sl.draw.geom;

import java.awt.Shape;

public class Outline {
    private final PathIf path;
    private final Shape shape;

    public Outline(Shape shape2, PathIf pathIf) {
        this.shape = shape2;
        this.path = pathIf;
    }

    public PathIf getPath() {
        return this.path;
    }

    public Shape getOutline() {
        return this.shape;
    }
}
