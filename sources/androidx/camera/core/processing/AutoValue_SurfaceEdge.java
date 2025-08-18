package androidx.camera.core.processing;

import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_SurfaceEdge extends SurfaceEdge {
    private final List<SettableSurface> surfaces;

    AutoValue_SurfaceEdge(List<SettableSurface> list) {
        if (list != null) {
            this.surfaces = list;
            return;
        }
        throw new NullPointerException("Null surfaces");
    }

    public List<SettableSurface> getSurfaces() {
        return this.surfaces;
    }

    public String toString() {
        return "SurfaceEdge{surfaces=" + this.surfaces + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SurfaceEdge) {
            return this.surfaces.equals(((SurfaceEdge) obj).getSurfaces());
        }
        return false;
    }

    public int hashCode() {
        return this.surfaces.hashCode() ^ 1000003;
    }
}
