package androidx.camera.core.impl;

import androidx.camera.core.impl.SessionConfig;
import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_SessionConfig_OutputConfig extends SessionConfig.OutputConfig {
    private final String physicalCameraId;
    private final List<DeferrableSurface> sharedSurfaces;
    private final DeferrableSurface surface;
    private final int surfaceGroupId;

    private AutoValue_SessionConfig_OutputConfig(DeferrableSurface deferrableSurface, List<DeferrableSurface> list, String str, int i) {
        this.surface = deferrableSurface;
        this.sharedSurfaces = list;
        this.physicalCameraId = str;
        this.surfaceGroupId = i;
    }

    public DeferrableSurface getSurface() {
        return this.surface;
    }

    public List<DeferrableSurface> getSharedSurfaces() {
        return this.sharedSurfaces;
    }

    public String getPhysicalCameraId() {
        return this.physicalCameraId;
    }

    public int getSurfaceGroupId() {
        return this.surfaceGroupId;
    }

    public String toString() {
        return "OutputConfig{surface=" + this.surface + ", sharedSurfaces=" + this.sharedSurfaces + ", physicalCameraId=" + this.physicalCameraId + ", surfaceGroupId=" + this.surfaceGroupId + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SessionConfig.OutputConfig)) {
            return false;
        }
        SessionConfig.OutputConfig outputConfig = (SessionConfig.OutputConfig) obj;
        if (!this.surface.equals(outputConfig.getSurface()) || !this.sharedSurfaces.equals(outputConfig.getSharedSurfaces()) || ((str = this.physicalCameraId) != null ? !str.equals(outputConfig.getPhysicalCameraId()) : outputConfig.getPhysicalCameraId() != null) || this.surfaceGroupId != outputConfig.getSurfaceGroupId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((this.surface.hashCode() ^ 1000003) * 1000003) ^ this.sharedSurfaces.hashCode()) * 1000003;
        String str = this.physicalCameraId;
        return this.surfaceGroupId ^ ((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003);
    }

    static final class Builder extends SessionConfig.OutputConfig.Builder {
        private String physicalCameraId;
        private List<DeferrableSurface> sharedSurfaces;
        private DeferrableSurface surface;
        private Integer surfaceGroupId;

        Builder() {
        }

        public SessionConfig.OutputConfig.Builder setSurface(DeferrableSurface deferrableSurface) {
            if (deferrableSurface != null) {
                this.surface = deferrableSurface;
                return this;
            }
            throw new NullPointerException("Null surface");
        }

        public SessionConfig.OutputConfig.Builder setSharedSurfaces(List<DeferrableSurface> list) {
            if (list != null) {
                this.sharedSurfaces = list;
                return this;
            }
            throw new NullPointerException("Null sharedSurfaces");
        }

        public SessionConfig.OutputConfig.Builder setPhysicalCameraId(String str) {
            this.physicalCameraId = str;
            return this;
        }

        public SessionConfig.OutputConfig.Builder setSurfaceGroupId(int i) {
            this.surfaceGroupId = Integer.valueOf(i);
            return this;
        }

        public SessionConfig.OutputConfig build() {
            String str = this.surface == null ? " surface" : "";
            if (this.sharedSurfaces == null) {
                str = str + " sharedSurfaces";
            }
            if (this.surfaceGroupId == null) {
                str = str + " surfaceGroupId";
            }
            if (str.isEmpty()) {
                return new AutoValue_SessionConfig_OutputConfig(this.surface, this.sharedSurfaces, this.physicalCameraId, this.surfaceGroupId.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
