package com.graphbuilder.curve;

public class Polyline extends Curve {
    public Polyline(ControlPath controlPath, GroupIterator groupIterator) {
        super(controlPath, groupIterator);
    }

    public void appendTo(MultiPath multiPath) {
        if (this.gi.isInRange(0, this.cp.numPoints())) {
            this.gi.set(0, 0);
            if (this.connect) {
                multiPath.lineTo(this.cp.getPoint(this.gi.next()).getLocation());
            } else {
                multiPath.moveTo(this.cp.getPoint(this.gi.next()).getLocation());
            }
            while (this.gi.hasNext()) {
                multiPath.lineTo(this.cp.getPoint(this.gi.next()).getLocation());
            }
            return;
        }
        throw new IllegalArgumentException("Group iterator not in range");
    }
}
