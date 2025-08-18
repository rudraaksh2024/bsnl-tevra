package androidx.camera.core.impl;

import androidx.camera.core.Camera;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInternal;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public final class CameraStateRegistry {
    private static final String TAG = "CameraStateRegistry";
    private int mAvailableCameras;
    private final Map<Camera, CameraRegistration> mCameraStates = new HashMap();
    private final StringBuilder mDebugString = new StringBuilder();
    private final Object mLock = new Object();
    private final int mMaxAllowedOpenedCameras;

    public interface OnOpenAvailableListener {
        void onOpenAvailable();
    }

    public CameraStateRegistry(int i) {
        this.mMaxAllowedOpenedCameras = i;
        synchronized ("mLock") {
            this.mAvailableCameras = i;
        }
    }

    public void registerCamera(Camera camera, Executor executor, OnOpenAvailableListener onOpenAvailableListener) {
        synchronized (this.mLock) {
            Preconditions.checkState(!this.mCameraStates.containsKey(camera), "Camera is already registered: " + camera);
            this.mCameraStates.put(camera, new CameraRegistration((CameraInternal.State) null, executor, onOpenAvailableListener));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean tryOpenCamera(androidx.camera.core.Camera r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.mLock
            monitor-enter(r0)
            java.util.Map<androidx.camera.core.Camera, androidx.camera.core.impl.CameraStateRegistry$CameraRegistration> r1 = r9.mCameraStates     // Catch:{ all -> 0x009b }
            java.lang.Object r1 = r1.get(r10)     // Catch:{ all -> 0x009b }
            androidx.camera.core.impl.CameraStateRegistry$CameraRegistration r1 = (androidx.camera.core.impl.CameraStateRegistry.CameraRegistration) r1     // Catch:{ all -> 0x009b }
            java.lang.String r2 = "Camera must first be registered with registerCamera()"
            java.lang.Object r1 = androidx.core.util.Preconditions.checkNotNull(r1, r2)     // Catch:{ all -> 0x009b }
            androidx.camera.core.impl.CameraStateRegistry$CameraRegistration r1 = (androidx.camera.core.impl.CameraStateRegistry.CameraRegistration) r1     // Catch:{ all -> 0x009b }
            java.lang.String r2 = "CameraStateRegistry"
            boolean r2 = androidx.camera.core.Logger.isDebugEnabled(r2)     // Catch:{ all -> 0x009b }
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0052
            java.lang.StringBuilder r2 = r9.mDebugString     // Catch:{ all -> 0x009b }
            r2.setLength(r4)     // Catch:{ all -> 0x009b }
            java.lang.StringBuilder r2 = r9.mDebugString     // Catch:{ all -> 0x009b }
            java.util.Locale r5 = java.util.Locale.US     // Catch:{ all -> 0x009b }
            java.lang.String r6 = "tryOpenCamera(%s) [Available Cameras: %d, Already Open: %b (Previous state: %s)]"
            r7 = 4
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x009b }
            r7[r4] = r10     // Catch:{ all -> 0x009b }
            int r10 = r9.mAvailableCameras     // Catch:{ all -> 0x009b }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x009b }
            r7[r3] = r10     // Catch:{ all -> 0x009b }
            androidx.camera.core.impl.CameraInternal$State r10 = r1.getState()     // Catch:{ all -> 0x009b }
            boolean r10 = isOpen(r10)     // Catch:{ all -> 0x009b }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x009b }
            r8 = 2
            r7[r8] = r10     // Catch:{ all -> 0x009b }
            androidx.camera.core.impl.CameraInternal$State r10 = r1.getState()     // Catch:{ all -> 0x009b }
            r8 = 3
            r7[r8] = r10     // Catch:{ all -> 0x009b }
            java.lang.String r10 = java.lang.String.format(r5, r6, r7)     // Catch:{ all -> 0x009b }
            r2.append(r10)     // Catch:{ all -> 0x009b }
        L_0x0052:
            int r10 = r9.mAvailableCameras     // Catch:{ all -> 0x009b }
            if (r10 > 0) goto L_0x0063
            androidx.camera.core.impl.CameraInternal$State r10 = r1.getState()     // Catch:{ all -> 0x009b }
            boolean r10 = isOpen(r10)     // Catch:{ all -> 0x009b }
            if (r10 == 0) goto L_0x0061
            goto L_0x0063
        L_0x0061:
            r10 = r4
            goto L_0x0069
        L_0x0063:
            androidx.camera.core.impl.CameraInternal$State r10 = androidx.camera.core.impl.CameraInternal.State.OPENING     // Catch:{ all -> 0x009b }
            r1.setState(r10)     // Catch:{ all -> 0x009b }
            r10 = r3
        L_0x0069:
            java.lang.String r1 = "CameraStateRegistry"
            boolean r1 = androidx.camera.core.Logger.isDebugEnabled(r1)     // Catch:{ all -> 0x009b }
            if (r1 == 0) goto L_0x0094
            java.lang.StringBuilder r1 = r9.mDebugString     // Catch:{ all -> 0x009b }
            java.util.Locale r2 = java.util.Locale.US     // Catch:{ all -> 0x009b }
            java.lang.String r5 = " --> %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x009b }
            if (r10 == 0) goto L_0x007e
            java.lang.String r6 = "SUCCESS"
            goto L_0x0080
        L_0x007e:
            java.lang.String r6 = "FAIL"
        L_0x0080:
            r3[r4] = r6     // Catch:{ all -> 0x009b }
            java.lang.String r2 = java.lang.String.format(r2, r5, r3)     // Catch:{ all -> 0x009b }
            r1.append(r2)     // Catch:{ all -> 0x009b }
            java.lang.String r1 = "CameraStateRegistry"
            java.lang.StringBuilder r2 = r9.mDebugString     // Catch:{ all -> 0x009b }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x009b }
            androidx.camera.core.Logger.d(r1, r2)     // Catch:{ all -> 0x009b }
        L_0x0094:
            if (r10 == 0) goto L_0x0099
            r9.recalculateAvailableCameras()     // Catch:{ all -> 0x009b }
        L_0x0099:
            monitor-exit(r0)     // Catch:{ all -> 0x009b }
            return r10
        L_0x009b:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x009b }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.CameraStateRegistry.tryOpenCamera(androidx.camera.core.Camera):boolean");
    }

    public void markCameraState(Camera camera, CameraInternal.State state) {
        markCameraState(camera, state, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0078, code lost:
        if (r6 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007a, code lost:
        r4 = r6.values().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0086, code lost:
        if (r4.hasNext() == false) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0088, code lost:
        ((androidx.camera.core.impl.CameraStateRegistry.CameraRegistration) r4.next()).notifyListener();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void markCameraState(androidx.camera.core.Camera r5, androidx.camera.core.impl.CameraInternal.State r6, boolean r7) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            int r1 = r4.mAvailableCameras     // Catch:{ all -> 0x0093 }
            androidx.camera.core.impl.CameraInternal$State r2 = androidx.camera.core.impl.CameraInternal.State.RELEASED     // Catch:{ all -> 0x0093 }
            if (r6 != r2) goto L_0x000e
            androidx.camera.core.impl.CameraInternal$State r2 = r4.unregisterCamera(r5)     // Catch:{ all -> 0x0093 }
            goto L_0x0012
        L_0x000e:
            androidx.camera.core.impl.CameraInternal$State r2 = r4.updateAndVerifyState(r5, r6)     // Catch:{ all -> 0x0093 }
        L_0x0012:
            if (r2 != r6) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x0093 }
            return
        L_0x0016:
            r2 = 1
            if (r1 >= r2) goto L_0x0056
            int r1 = r4.mAvailableCameras     // Catch:{ all -> 0x0093 }
            if (r1 <= 0) goto L_0x0056
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ all -> 0x0093 }
            r6.<init>()     // Catch:{ all -> 0x0093 }
            java.util.Map<androidx.camera.core.Camera, androidx.camera.core.impl.CameraStateRegistry$CameraRegistration> r4 = r4.mCameraStates     // Catch:{ all -> 0x0093 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ all -> 0x0093 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0093 }
        L_0x002c:
            boolean r1 = r4.hasNext()     // Catch:{ all -> 0x0093 }
            if (r1 == 0) goto L_0x0070
            java.lang.Object r1 = r4.next()     // Catch:{ all -> 0x0093 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x0093 }
            java.lang.Object r2 = r1.getValue()     // Catch:{ all -> 0x0093 }
            androidx.camera.core.impl.CameraStateRegistry$CameraRegistration r2 = (androidx.camera.core.impl.CameraStateRegistry.CameraRegistration) r2     // Catch:{ all -> 0x0093 }
            androidx.camera.core.impl.CameraInternal$State r2 = r2.getState()     // Catch:{ all -> 0x0093 }
            androidx.camera.core.impl.CameraInternal$State r3 = androidx.camera.core.impl.CameraInternal.State.PENDING_OPEN     // Catch:{ all -> 0x0093 }
            if (r2 != r3) goto L_0x002c
            java.lang.Object r2 = r1.getKey()     // Catch:{ all -> 0x0093 }
            androidx.camera.core.Camera r2 = (androidx.camera.core.Camera) r2     // Catch:{ all -> 0x0093 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0093 }
            androidx.camera.core.impl.CameraStateRegistry$CameraRegistration r1 = (androidx.camera.core.impl.CameraStateRegistry.CameraRegistration) r1     // Catch:{ all -> 0x0093 }
            r6.put(r2, r1)     // Catch:{ all -> 0x0093 }
            goto L_0x002c
        L_0x0056:
            androidx.camera.core.impl.CameraInternal$State r1 = androidx.camera.core.impl.CameraInternal.State.PENDING_OPEN     // Catch:{ all -> 0x0093 }
            if (r6 != r1) goto L_0x006f
            int r6 = r4.mAvailableCameras     // Catch:{ all -> 0x0093 }
            if (r6 <= 0) goto L_0x006f
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ all -> 0x0093 }
            r6.<init>()     // Catch:{ all -> 0x0093 }
            java.util.Map<androidx.camera.core.Camera, androidx.camera.core.impl.CameraStateRegistry$CameraRegistration> r4 = r4.mCameraStates     // Catch:{ all -> 0x0093 }
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x0093 }
            androidx.camera.core.impl.CameraStateRegistry$CameraRegistration r4 = (androidx.camera.core.impl.CameraStateRegistry.CameraRegistration) r4     // Catch:{ all -> 0x0093 }
            r6.put(r5, r4)     // Catch:{ all -> 0x0093 }
            goto L_0x0070
        L_0x006f:
            r6 = 0
        L_0x0070:
            if (r6 == 0) goto L_0x0077
            if (r7 != 0) goto L_0x0077
            r6.remove(r5)     // Catch:{ all -> 0x0093 }
        L_0x0077:
            monitor-exit(r0)     // Catch:{ all -> 0x0093 }
            if (r6 == 0) goto L_0x0092
            java.util.Collection r4 = r6.values()
            java.util.Iterator r4 = r4.iterator()
        L_0x0082:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0092
            java.lang.Object r5 = r4.next()
            androidx.camera.core.impl.CameraStateRegistry$CameraRegistration r5 = (androidx.camera.core.impl.CameraStateRegistry.CameraRegistration) r5
            r5.notifyListener()
            goto L_0x0082
        L_0x0092:
            return
        L_0x0093:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0093 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.CameraStateRegistry.markCameraState(androidx.camera.core.Camera, androidx.camera.core.impl.CameraInternal$State, boolean):void");
    }

    private CameraInternal.State unregisterCamera(Camera camera) {
        CameraRegistration remove = this.mCameraStates.remove(camera);
        if (remove == null) {
            return null;
        }
        recalculateAvailableCameras();
        return remove.getState();
    }

    private CameraInternal.State updateAndVerifyState(Camera camera, CameraInternal.State state) {
        CameraInternal.State state2 = ((CameraRegistration) Preconditions.checkNotNull(this.mCameraStates.get(camera), "Cannot update state of camera which has not yet been registered. Register with CameraStateRegistry.registerCamera()")).setState(state);
        if (state == CameraInternal.State.OPENING) {
            Preconditions.checkState(isOpen(state) || state2 == CameraInternal.State.OPENING, "Cannot mark camera as opening until camera was successful at calling CameraStateRegistry.tryOpenCamera()");
        }
        if (state2 != state) {
            recalculateAvailableCameras();
        }
        return state2;
    }

    private static boolean isOpen(CameraInternal.State state) {
        return state != null && state.holdsCameraSlot();
    }

    private void recalculateAvailableCameras() {
        if (Logger.isDebugEnabled(TAG)) {
            this.mDebugString.setLength(0);
            this.mDebugString.append("Recalculating open cameras:\n");
            this.mDebugString.append(String.format(Locale.US, "%-45s%-22s\n", new Object[]{"Camera", "State"}));
            this.mDebugString.append("-------------------------------------------------------------------\n");
        }
        int i = 0;
        for (Map.Entry next : this.mCameraStates.entrySet()) {
            if (Logger.isDebugEnabled(TAG)) {
                this.mDebugString.append(String.format(Locale.US, "%-45s%-22s\n", new Object[]{((Camera) next.getKey()).toString(), ((CameraRegistration) next.getValue()).getState() != null ? ((CameraRegistration) next.getValue()).getState().toString() : "UNKNOWN"}));
            }
            if (isOpen(((CameraRegistration) next.getValue()).getState())) {
                i++;
            }
        }
        if (Logger.isDebugEnabled(TAG)) {
            this.mDebugString.append("-------------------------------------------------------------------\n");
            this.mDebugString.append(String.format(Locale.US, "Open count: %d (Max allowed: %d)", new Object[]{Integer.valueOf(i), Integer.valueOf(this.mMaxAllowedOpenedCameras)}));
            Logger.d(TAG, this.mDebugString.toString());
        }
        this.mAvailableCameras = Math.max(this.mMaxAllowedOpenedCameras - i, 0);
    }

    public boolean isCameraClosing() {
        synchronized (this.mLock) {
            for (Map.Entry<Camera, CameraRegistration> value : this.mCameraStates.entrySet()) {
                if (((CameraRegistration) value.getValue()).getState() == CameraInternal.State.CLOSING) {
                    return true;
                }
            }
            return false;
        }
    }

    private static class CameraRegistration {
        private final OnOpenAvailableListener mCameraAvailableListener;
        private final Executor mNotifyExecutor;
        private CameraInternal.State mState;

        CameraRegistration(CameraInternal.State state, Executor executor, OnOpenAvailableListener onOpenAvailableListener) {
            this.mState = state;
            this.mNotifyExecutor = executor;
            this.mCameraAvailableListener = onOpenAvailableListener;
        }

        /* access modifiers changed from: package-private */
        public CameraInternal.State setState(CameraInternal.State state) {
            CameraInternal.State state2 = this.mState;
            this.mState = state;
            return state2;
        }

        /* access modifiers changed from: package-private */
        public CameraInternal.State getState() {
            return this.mState;
        }

        /* access modifiers changed from: package-private */
        public void notifyListener() {
            try {
                Executor executor = this.mNotifyExecutor;
                OnOpenAvailableListener onOpenAvailableListener = this.mCameraAvailableListener;
                Objects.requireNonNull(onOpenAvailableListener);
                executor.execute(new CameraStateRegistry$CameraRegistration$$ExternalSyntheticLambda0(onOpenAvailableListener));
            } catch (RejectedExecutionException e) {
                Logger.e(CameraStateRegistry.TAG, "Unable to notify camera.", e);
            }
        }
    }
}
