package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_ResolutionInfo extends ResolutionInfo {
    private final Rect cropRect;
    private final Size resolution;
    private final int rotationDegrees;

    AutoValue_ResolutionInfo(Size size, Rect rect, int i) {
        if (size != null) {
            this.resolution = size;
            if (rect != null) {
                this.cropRect = rect;
                this.rotationDegrees = i;
                return;
            }
            throw new NullPointerException("Null cropRect");
        }
        throw new NullPointerException("Null resolution");
    }

    public Size getResolution() {
        return this.resolution;
    }

    public Rect getCropRect() {
        return this.cropRect;
    }

    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    public String toString() {
        return "ResolutionInfo{resolution=" + this.resolution + ", cropRect=" + this.cropRect + ", rotationDegrees=" + this.rotationDegrees + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResolutionInfo)) {
            return false;
        }
        ResolutionInfo resolutionInfo = (ResolutionInfo) obj;
        if (!this.resolution.equals(resolutionInfo.getResolution()) || !this.cropRect.equals(resolutionInfo.getCropRect()) || this.rotationDegrees != resolutionInfo.getRotationDegrees()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.rotationDegrees ^ ((((this.resolution.hashCode() ^ 1000003) * 1000003) ^ this.cropRect.hashCode()) * 1000003);
    }
}
