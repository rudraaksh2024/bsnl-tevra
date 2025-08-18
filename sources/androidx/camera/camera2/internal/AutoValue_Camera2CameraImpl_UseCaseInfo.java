package androidx.camera.camera2.internal;

import android.util.Size;
import androidx.camera.camera2.internal.Camera2CameraImpl;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_Camera2CameraImpl_UseCaseInfo extends Camera2CameraImpl.UseCaseInfo {
    private final SessionConfig sessionConfig;
    private final Size surfaceResolution;
    private final UseCaseConfig<?> useCaseConfig;
    private final String useCaseId;
    private final Class<?> useCaseType;

    AutoValue_Camera2CameraImpl_UseCaseInfo(String str, Class<?> cls, SessionConfig sessionConfig2, UseCaseConfig<?> useCaseConfig2, Size size) {
        if (str != null) {
            this.useCaseId = str;
            if (cls != null) {
                this.useCaseType = cls;
                if (sessionConfig2 != null) {
                    this.sessionConfig = sessionConfig2;
                    if (useCaseConfig2 != null) {
                        this.useCaseConfig = useCaseConfig2;
                        this.surfaceResolution = size;
                        return;
                    }
                    throw new NullPointerException("Null useCaseConfig");
                }
                throw new NullPointerException("Null sessionConfig");
            }
            throw new NullPointerException("Null useCaseType");
        }
        throw new NullPointerException("Null useCaseId");
    }

    /* access modifiers changed from: package-private */
    public String getUseCaseId() {
        return this.useCaseId;
    }

    /* access modifiers changed from: package-private */
    public Class<?> getUseCaseType() {
        return this.useCaseType;
    }

    /* access modifiers changed from: package-private */
    public SessionConfig getSessionConfig() {
        return this.sessionConfig;
    }

    /* access modifiers changed from: package-private */
    public UseCaseConfig<?> getUseCaseConfig() {
        return this.useCaseConfig;
    }

    /* access modifiers changed from: package-private */
    public Size getSurfaceResolution() {
        return this.surfaceResolution;
    }

    public String toString() {
        return "UseCaseInfo{useCaseId=" + this.useCaseId + ", useCaseType=" + this.useCaseType + ", sessionConfig=" + this.sessionConfig + ", useCaseConfig=" + this.useCaseConfig + ", surfaceResolution=" + this.surfaceResolution + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Camera2CameraImpl.UseCaseInfo)) {
            return false;
        }
        Camera2CameraImpl.UseCaseInfo useCaseInfo = (Camera2CameraImpl.UseCaseInfo) obj;
        if (this.useCaseId.equals(useCaseInfo.getUseCaseId()) && this.useCaseType.equals(useCaseInfo.getUseCaseType()) && this.sessionConfig.equals(useCaseInfo.getSessionConfig()) && this.useCaseConfig.equals(useCaseInfo.getUseCaseConfig())) {
            Size size = this.surfaceResolution;
            if (size == null) {
                if (useCaseInfo.getSurfaceResolution() == null) {
                    return true;
                }
            } else if (size.equals(useCaseInfo.getSurfaceResolution())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((this.useCaseId.hashCode() ^ 1000003) * 1000003) ^ this.useCaseType.hashCode()) * 1000003) ^ this.sessionConfig.hashCode()) * 1000003) ^ this.useCaseConfig.hashCode()) * 1000003;
        Size size = this.surfaceResolution;
        return (size == null ? 0 : size.hashCode()) ^ hashCode;
    }
}
