package androidx.camera.core.impl;

import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.UseCase;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

public interface CameraInternal extends Camera, UseCase.StateChangeCallback {
    void attachUseCases(Collection<UseCase> collection);

    void close();

    void detachUseCases(Collection<UseCase> collection);

    CameraControlInternal getCameraControlInternal();

    CameraInfoInternal getCameraInfoInternal();

    Observable<State> getCameraState();

    void open();

    ListenableFuture<Void> release();

    void setActiveResumingMode(boolean z) {
    }

    void setExtendedConfig(CameraConfig cameraConfig) {
    }

    public enum State {
        PENDING_OPEN(false),
        OPENING(true),
        OPEN(true),
        CLOSING(true),
        CLOSED(false),
        RELEASING(true),
        RELEASED(false);
        
        private final boolean mHoldsCameraSlot;

        private State(boolean z) {
            this.mHoldsCameraSlot = z;
        }

        /* access modifiers changed from: package-private */
        public boolean holdsCameraSlot() {
            return this.mHoldsCameraSlot;
        }
    }

    CameraControl getCameraControl() {
        return getCameraControlInternal();
    }

    CameraInfo getCameraInfo() {
        return getCameraInfoInternal();
    }

    LinkedHashSet<CameraInternal> getCameraInternals() {
        return new LinkedHashSet<>(Collections.singleton(this));
    }

    CameraConfig getExtendedConfig() {
        return CameraConfigs.emptyConfig();
    }
}
