package androidx.camera.core.processing;

import java.util.List;

public abstract class SurfaceEdge {
    public abstract List<SettableSurface> getSurfaces();

    public static SurfaceEdge create(List<SettableSurface> list) {
        return new AutoValue_SurfaceEdge(list);
    }
}
