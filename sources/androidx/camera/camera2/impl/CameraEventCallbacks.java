package androidx.camera.camera2.impl;

import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.MultiValueSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CameraEventCallbacks extends MultiValueSet<CameraEventCallback> {
    public CameraEventCallbacks(CameraEventCallback... cameraEventCallbackArr) {
        addAll(Arrays.asList(cameraEventCallbackArr));
    }

    public ComboCameraEventCallback createComboCallback() {
        return new ComboCameraEventCallback(getAllItems());
    }

    public static CameraEventCallbacks createEmptyCallback() {
        return new CameraEventCallbacks(new CameraEventCallback[0]);
    }

    public MultiValueSet<CameraEventCallback> clone() {
        CameraEventCallbacks createEmptyCallback = createEmptyCallback();
        createEmptyCallback.addAll(getAllItems());
        return createEmptyCallback;
    }

    public static final class ComboCameraEventCallback {
        private final List<CameraEventCallback> mCallbacks = new ArrayList();

        ComboCameraEventCallback(List<CameraEventCallback> list) {
            for (CameraEventCallback add : list) {
                this.mCallbacks.add(add);
            }
        }

        public List<CaptureConfig> onInitSession() {
            ArrayList arrayList = new ArrayList();
            for (CameraEventCallback onInitSession : this.mCallbacks) {
                CaptureConfig onInitSession2 = onInitSession.onInitSession();
                if (onInitSession2 != null) {
                    arrayList.add(onInitSession2);
                }
            }
            return arrayList;
        }

        public List<CaptureConfig> onEnableSession() {
            ArrayList arrayList = new ArrayList();
            for (CameraEventCallback onEnableSession : this.mCallbacks) {
                CaptureConfig onEnableSession2 = onEnableSession.onEnableSession();
                if (onEnableSession2 != null) {
                    arrayList.add(onEnableSession2);
                }
            }
            return arrayList;
        }

        public List<CaptureConfig> onRepeating() {
            ArrayList arrayList = new ArrayList();
            for (CameraEventCallback onRepeating : this.mCallbacks) {
                CaptureConfig onRepeating2 = onRepeating.onRepeating();
                if (onRepeating2 != null) {
                    arrayList.add(onRepeating2);
                }
            }
            return arrayList;
        }

        public List<CaptureConfig> onDisableSession() {
            ArrayList arrayList = new ArrayList();
            for (CameraEventCallback onDisableSession : this.mCallbacks) {
                CaptureConfig onDisableSession2 = onDisableSession.onDisableSession();
                if (onDisableSession2 != null) {
                    arrayList.add(onDisableSession2);
                }
            }
            return arrayList;
        }

        public void onDeInitSession() {
            for (CameraEventCallback onDeInitSession : this.mCallbacks) {
                onDeInitSession.onDeInitSession();
            }
        }

        public List<CameraEventCallback> getCallbacks() {
            return this.mCallbacks;
        }
    }
}
