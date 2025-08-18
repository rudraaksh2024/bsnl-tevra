package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.poi.sl.usermodel.PaintStyle;

public final class Path implements PathIf {
    private final List<PathCommand> commands = new ArrayList();
    private boolean extrusionOk = false;
    private PaintStyle.PaintModifier fill = PaintStyle.PaintModifier.NORM;
    private long h = -1;
    private boolean stroke = true;
    private long w = -1;

    public void addCommand(PathCommand pathCommand) {
        this.commands.add(pathCommand);
    }

    public Path2D.Double getPath(Context context) {
        Path2D.Double doubleR = new Path2D.Double();
        for (PathCommand execute : this.commands) {
            execute.execute(doubleR, context);
        }
        return doubleR;
    }

    public boolean isStroked() {
        return this.stroke;
    }

    public void setStroke(boolean z) {
        this.stroke = z;
    }

    public boolean isFilled() {
        return this.fill != PaintStyle.PaintModifier.NONE;
    }

    public PaintStyle.PaintModifier getFill() {
        return this.fill;
    }

    public void setFill(PaintStyle.PaintModifier paintModifier) {
        this.fill = paintModifier;
    }

    public long getW() {
        return this.w;
    }

    public void setW(long j) {
        this.w = j;
    }

    public long getH() {
        return this.h;
    }

    public void setH(long j) {
        this.h = j;
    }

    public boolean isExtrusionOk() {
        return this.extrusionOk;
    }

    public void setExtrusionOk(boolean z) {
        this.extrusionOk = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Path)) {
            return false;
        }
        Path path = (Path) obj;
        if (!Objects.equals(this.commands, path.commands) || !Objects.equals(Long.valueOf(this.w), Long.valueOf(path.w)) || !Objects.equals(Long.valueOf(this.h), Long.valueOf(path.h)) || this.fill != path.fill || !Objects.equals(Boolean.valueOf(this.stroke), Boolean.valueOf(path.stroke)) || !Objects.equals(Boolean.valueOf(this.extrusionOk), Boolean.valueOf(path.extrusionOk))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.commands, Long.valueOf(this.w), Long.valueOf(this.h), Integer.valueOf(this.fill.ordinal()), Boolean.valueOf(this.stroke), Boolean.valueOf(this.extrusionOk)});
    }
}
