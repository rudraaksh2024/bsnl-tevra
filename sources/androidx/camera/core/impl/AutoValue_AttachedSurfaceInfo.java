package androidx.camera.core.impl;

import android.util.Range;
import android.util.Size;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_AttachedSurfaceInfo extends AttachedSurfaceInfo {
    private final int imageFormat;
    private final Size size;
    private final SurfaceConfig surfaceConfig;
    private final Range<Integer> targetFrameRate;

    AutoValue_AttachedSurfaceInfo(SurfaceConfig surfaceConfig2, int i, Size size2, Range<Integer> range) {
        if (surfaceConfig2 != null) {
            this.surfaceConfig = surfaceConfig2;
            this.imageFormat = i;
            if (size2 != null) {
                this.size = size2;
                this.targetFrameRate = range;
                return;
            }
            throw new NullPointerException("Null size");
        }
        throw new NullPointerException("Null surfaceConfig");
    }

    public SurfaceConfig getSurfaceConfig() {
        return this.surfaceConfig;
    }

    public int getImageFormat() {
        return this.imageFormat;
    }

    public Size getSize() {
        return this.size;
    }

    public Range<Integer> getTargetFrameRate() {
        return this.targetFrameRate;
    }

    public String toString() {
        return "AttachedSurfaceInfo{surfaceConfig=" + this.surfaceConfig + ", imageFormat=" + this.imageFormat + ", size=" + this.size + ", targetFrameRate=" + this.targetFrameRate + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AttachedSurfaceInfo)) {
            return false;
        }
        AttachedSurfaceInfo attachedSurfaceInfo = (AttachedSurfaceInfo) obj;
        if (this.surfaceConfig.equals(attachedSurfaceInfo.getSurfaceConfig()) && this.imageFormat == attachedSurfaceInfo.getImageFormat() && this.size.equals(attachedSurfaceInfo.getSize())) {
            Range<Integer> range = this.targetFrameRate;
            if (range == null) {
                if (attachedSurfaceInfo.getTargetFrameRate() == null) {
                    return true;
                }
            } else if (range.equals(attachedSurfaceInfo.getTargetFrameRate())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((this.surfaceConfig.hashCode() ^ 1000003) * 1000003) ^ this.imageFormat) * 1000003) ^ this.size.hashCode()) * 1000003;
        Range<Integer> range = this.targetFrameRate;
        return (range == null ? 0 : range.hashCode()) ^ hashCode;
    }
}
