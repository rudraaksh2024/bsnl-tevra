package com.graphbuilder.curve;

public abstract class Curve {
    protected boolean connect = false;
    protected ControlPath cp;
    protected GroupIterator gi;

    public abstract void appendTo(MultiPath multiPath);

    public void resetMemory() {
    }

    public Curve(ControlPath controlPath, GroupIterator groupIterator) {
        setControlPath(controlPath);
        setGroupIterator(groupIterator);
    }

    public ControlPath getControlPath() {
        return this.cp;
    }

    public void setControlPath(ControlPath controlPath) {
        if (controlPath != null) {
            this.cp = controlPath;
            return;
        }
        throw new IllegalArgumentException("ControlPath cannot be null.");
    }

    public GroupIterator getGroupIterator() {
        return this.gi;
    }

    public void setGroupIterator(GroupIterator groupIterator) {
        if (groupIterator != null) {
            this.gi = groupIterator;
            return;
        }
        throw new IllegalArgumentException("GroupIterator cannot be null.");
    }

    public boolean getConnect() {
        return this.connect;
    }

    public void setConnect(boolean z) {
        this.connect = z;
    }
}
