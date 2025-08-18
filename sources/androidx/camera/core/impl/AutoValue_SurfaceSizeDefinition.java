package androidx.camera.core.impl;

import android.util.Size;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_SurfaceSizeDefinition extends SurfaceSizeDefinition {
    private final Size analysisSize;
    private final Size previewSize;
    private final Size recordSize;

    AutoValue_SurfaceSizeDefinition(Size size, Size size2, Size size3) {
        if (size != null) {
            this.analysisSize = size;
            if (size2 != null) {
                this.previewSize = size2;
                if (size3 != null) {
                    this.recordSize = size3;
                    return;
                }
                throw new NullPointerException("Null recordSize");
            }
            throw new NullPointerException("Null previewSize");
        }
        throw new NullPointerException("Null analysisSize");
    }

    public Size getAnalysisSize() {
        return this.analysisSize;
    }

    public Size getPreviewSize() {
        return this.previewSize;
    }

    public Size getRecordSize() {
        return this.recordSize;
    }

    public String toString() {
        return "SurfaceSizeDefinition{analysisSize=" + this.analysisSize + ", previewSize=" + this.previewSize + ", recordSize=" + this.recordSize + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SurfaceSizeDefinition)) {
            return false;
        }
        SurfaceSizeDefinition surfaceSizeDefinition = (SurfaceSizeDefinition) obj;
        if (!this.analysisSize.equals(surfaceSizeDefinition.getAnalysisSize()) || !this.previewSize.equals(surfaceSizeDefinition.getPreviewSize()) || !this.recordSize.equals(surfaceSizeDefinition.getRecordSize())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.recordSize.hashCode() ^ ((((this.analysisSize.hashCode() ^ 1000003) * 1000003) ^ this.previewSize.hashCode()) * 1000003);
    }
}
