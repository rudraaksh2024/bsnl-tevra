package androidx.camera.core.internal;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.SurfaceProcessor;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.UseCase;
import androidx.camera.core.ViewPort;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.processing.SurfaceProcessorInternal;
import androidx.camera.core.processing.SurfaceProcessorWithExecutor;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class CameraUseCaseAdapter implements Camera {
    private static final String TAG = "CameraUseCaseAdapter";
    private boolean mAttached = true;
    private CameraConfig mCameraConfig = CameraConfigs.emptyConfig();
    private final CameraDeviceSurfaceManager mCameraDeviceSurfaceManager;
    private CameraInternal mCameraInternal;
    private final LinkedHashSet<CameraInternal> mCameraInternals;
    private List<CameraEffect> mEffects = Collections.emptyList();
    private List<UseCase> mExtraUseCases = new ArrayList();
    private final CameraId mId;
    private Config mInteropConfig = null;
    private final Object mLock = new Object();
    private final UseCaseConfigFactory mUseCaseConfigFactory;
    private final List<UseCase> mUseCases = new ArrayList();
    private ViewPort mViewPort;

    public CameraUseCaseAdapter(LinkedHashSet<CameraInternal> linkedHashSet, CameraDeviceSurfaceManager cameraDeviceSurfaceManager, UseCaseConfigFactory useCaseConfigFactory) {
        this.mCameraInternal = (CameraInternal) linkedHashSet.iterator().next();
        LinkedHashSet<CameraInternal> linkedHashSet2 = new LinkedHashSet<>(linkedHashSet);
        this.mCameraInternals = linkedHashSet2;
        this.mId = new CameraId(linkedHashSet2);
        this.mCameraDeviceSurfaceManager = cameraDeviceSurfaceManager;
        this.mUseCaseConfigFactory = useCaseConfigFactory;
    }

    public static CameraId generateCameraId(LinkedHashSet<CameraInternal> linkedHashSet) {
        return new CameraId(linkedHashSet);
    }

    public CameraId getCameraId() {
        return this.mId;
    }

    public boolean isEquivalent(CameraUseCaseAdapter cameraUseCaseAdapter) {
        return this.mId.equals(cameraUseCaseAdapter.getCameraId());
    }

    public void setViewPort(ViewPort viewPort) {
        synchronized (this.mLock) {
            this.mViewPort = viewPort;
        }
    }

    public void setEffects(List<CameraEffect> list) {
        synchronized (this.mLock) {
            this.mEffects = list;
        }
    }

    public void addUseCases(Collection<UseCase> collection) throws CameraException {
        synchronized (this.mLock) {
            ArrayList<UseCase> arrayList = new ArrayList<>();
            for (UseCase next : collection) {
                if (this.mUseCases.contains(next)) {
                    Logger.d(TAG, "Attempting to attach already attached UseCase");
                } else {
                    arrayList.add(next);
                }
            }
            ArrayList arrayList2 = new ArrayList(this.mUseCases);
            List<UseCase> emptyList = Collections.emptyList();
            List emptyList2 = Collections.emptyList();
            if (isCoexistingPreviewImageCaptureRequired()) {
                arrayList2.removeAll(this.mExtraUseCases);
                arrayList2.addAll(arrayList);
                emptyList = calculateRequiredExtraUseCases(arrayList2, new ArrayList(this.mExtraUseCases));
                ArrayList arrayList3 = new ArrayList(emptyList);
                arrayList3.removeAll(this.mExtraUseCases);
                arrayList.addAll(arrayList3);
                emptyList2 = new ArrayList(this.mExtraUseCases);
                emptyList2.removeAll(emptyList);
            }
            Map<UseCase, ConfigPair> configs = getConfigs(arrayList, this.mCameraConfig.getUseCaseConfigFactory(), this.mUseCaseConfigFactory);
            try {
                ArrayList arrayList4 = new ArrayList(this.mUseCases);
                arrayList4.removeAll(emptyList2);
                Map<UseCase, Size> calculateSuggestedResolutions = calculateSuggestedResolutions(this.mCameraInternal.getCameraInfoInternal(), arrayList, arrayList4, configs);
                updateViewPort(calculateSuggestedResolutions, collection);
                updateEffects(this.mEffects, collection);
                this.mExtraUseCases = emptyList;
                detachUnnecessaryUseCases(emptyList2);
                for (UseCase useCase : arrayList) {
                    ConfigPair configPair = configs.get(useCase);
                    useCase.onAttach(this.mCameraInternal, configPair.mExtendedConfig, configPair.mCameraConfig);
                    useCase.updateSuggestedResolution((Size) Preconditions.checkNotNull(calculateSuggestedResolutions.get(useCase)));
                }
                this.mUseCases.addAll(arrayList);
                if (this.mAttached) {
                    this.mCameraInternal.attachUseCases(arrayList);
                }
                for (UseCase notifyState : arrayList) {
                    notifyState.notifyState();
                }
            } catch (IllegalArgumentException e) {
                throw new CameraException(e.getMessage());
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:5|6|7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeUseCases(java.util.Collection<androidx.camera.core.UseCase> r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0028 }
            r1.<init>(r3)     // Catch:{ all -> 0x0028 }
            r2.detachUnnecessaryUseCases(r1)     // Catch:{ all -> 0x0028 }
            boolean r1 = r2.isCoexistingPreviewImageCaptureRequired()     // Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0026
            java.util.List<androidx.camera.core.UseCase> r1 = r2.mExtraUseCases     // Catch:{ all -> 0x0028 }
            r1.removeAll(r3)     // Catch:{ all -> 0x0028 }
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ CameraException -> 0x001e }
            r2.addUseCases(r3)     // Catch:{ CameraException -> 0x001e }
            goto L_0x0026
        L_0x001e:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0028 }
            java.lang.String r3 = "Failed to add extra fake Preview or ImageCapture use case!"
            r2.<init>(r3)     // Catch:{ all -> 0x0028 }
            throw r2     // Catch:{ all -> 0x0028 }
        L_0x0026:
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            return
        L_0x0028:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.CameraUseCaseAdapter.removeUseCases(java.util.Collection):void");
    }

    public List<UseCase> getUseCases() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mUseCases);
        }
        return arrayList;
    }

    public void attachUseCases() {
        synchronized (this.mLock) {
            if (!this.mAttached) {
                this.mCameraInternal.attachUseCases(this.mUseCases);
                restoreInteropConfig();
                for (UseCase notifyState : this.mUseCases) {
                    notifyState.notifyState();
                }
                this.mAttached = true;
            }
        }
    }

    public void setActiveResumingMode(boolean z) {
        this.mCameraInternal.setActiveResumingMode(z);
    }

    public void detachUseCases() {
        synchronized (this.mLock) {
            if (this.mAttached) {
                this.mCameraInternal.detachUseCases(new ArrayList(this.mUseCases));
                cacheInteropConfig();
                this.mAttached = false;
            }
        }
    }

    private void restoreInteropConfig() {
        synchronized (this.mLock) {
            if (this.mInteropConfig != null) {
                this.mCameraInternal.getCameraControlInternal().addInteropConfig(this.mInteropConfig);
            }
        }
    }

    private void cacheInteropConfig() {
        synchronized (this.mLock) {
            CameraControlInternal cameraControlInternal = this.mCameraInternal.getCameraControlInternal();
            this.mInteropConfig = cameraControlInternal.getInteropConfig();
            cameraControlInternal.clearInteropConfig();
        }
    }

    private Map<UseCase, Size> calculateSuggestedResolutions(CameraInfoInternal cameraInfoInternal, List<UseCase> list, List<UseCase> list2, Map<UseCase, ConfigPair> map) {
        ArrayList arrayList = new ArrayList();
        String cameraId = cameraInfoInternal.getCameraId();
        HashMap hashMap = new HashMap();
        for (UseCase next : list2) {
            arrayList.add(AttachedSurfaceInfo.create(this.mCameraDeviceSurfaceManager.transformSurfaceConfig(cameraId, next.getImageFormat(), next.getAttachedSurfaceResolution()), next.getImageFormat(), next.getAttachedSurfaceResolution(), next.getCurrentConfig().getTargetFramerate((Range<Integer>) null)));
            hashMap.put(next, next.getAttachedSurfaceResolution());
        }
        if (!list.isEmpty()) {
            HashMap hashMap2 = new HashMap();
            for (UseCase next2 : list) {
                ConfigPair configPair = map.get(next2);
                hashMap2.put(next2.mergeConfigs(cameraInfoInternal, configPair.mExtendedConfig, configPair.mCameraConfig), next2);
            }
            Map<UseCaseConfig<?>, Size> suggestedResolutions = this.mCameraDeviceSurfaceManager.getSuggestedResolutions(cameraId, arrayList, new ArrayList(hashMap2.keySet()));
            for (Map.Entry entry : hashMap2.entrySet()) {
                hashMap.put((UseCase) entry.getValue(), suggestedResolutions.get(entry.getKey()));
            }
        }
        return hashMap;
    }

    static void updateEffects(List<CameraEffect> list, Collection<UseCase> collection) {
        HashMap hashMap = new HashMap();
        for (CameraEffect next : list) {
            hashMap.put(Integer.valueOf(next.getTargets()), next);
        }
        for (UseCase next2 : collection) {
            if (next2 instanceof Preview) {
                Preview preview = (Preview) next2;
                CameraEffect cameraEffect = (CameraEffect) hashMap.get(1);
                if (cameraEffect == null) {
                    preview.setProcessor((SurfaceProcessorInternal) null);
                } else {
                    preview.setProcessor(new SurfaceProcessorWithExecutor((SurfaceProcessor) Objects.requireNonNull(cameraEffect.getSurfaceProcessor()), cameraEffect.getProcessorExecutor()));
                }
            }
        }
    }

    private void updateViewPort(Map<UseCase, Size> map, Collection<UseCase> collection) {
        synchronized (this.mLock) {
            if (this.mViewPort != null) {
                Integer lensFacing = this.mCameraInternal.getCameraInfoInternal().getLensFacing();
                boolean z = true;
                if (lensFacing == null) {
                    Logger.w(TAG, "The lens facing is null, probably an external.");
                } else if (lensFacing.intValue() != 0) {
                    z = false;
                }
                Map<UseCase, Rect> calculateViewPortRects = ViewPorts.calculateViewPortRects(this.mCameraInternal.getCameraControlInternal().getSensorRect(), z, this.mViewPort.getAspectRatio(), this.mCameraInternal.getCameraInfoInternal().getSensorRotationDegrees(this.mViewPort.getRotation()), this.mViewPort.getScaleType(), this.mViewPort.getLayoutDirection(), map);
                for (UseCase next : collection) {
                    next.setViewPortCropRect((Rect) Preconditions.checkNotNull(calculateViewPortRects.get(next)));
                    next.setSensorToBufferTransformMatrix(calculateSensorToBufferTransformMatrix(this.mCameraInternal.getCameraControlInternal().getSensorRect(), map.get(next)));
                }
            }
        }
    }

    private static Matrix calculateSensorToBufferTransformMatrix(Rect rect, Size size) {
        Preconditions.checkArgument(rect.width() > 0 && rect.height() > 0, "Cannot compute viewport crop rects zero sized sensor rect.");
        RectF rectF = new RectF(rect);
        Matrix matrix = new Matrix();
        matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) size.getWidth(), (float) size.getHeight()), rectF, Matrix.ScaleToFit.CENTER);
        matrix.invert(matrix);
        return matrix;
    }

    private static class ConfigPair {
        UseCaseConfig<?> mCameraConfig;
        UseCaseConfig<?> mExtendedConfig;

        ConfigPair(UseCaseConfig<?> useCaseConfig, UseCaseConfig<?> useCaseConfig2) {
            this.mExtendedConfig = useCaseConfig;
            this.mCameraConfig = useCaseConfig2;
        }
    }

    private Map<UseCase, ConfigPair> getConfigs(List<UseCase> list, UseCaseConfigFactory useCaseConfigFactory, UseCaseConfigFactory useCaseConfigFactory2) {
        HashMap hashMap = new HashMap();
        for (UseCase next : list) {
            hashMap.put(next, new ConfigPair(next.getDefaultConfig(false, useCaseConfigFactory), next.getDefaultConfig(true, useCaseConfigFactory2)));
        }
        return hashMap;
    }

    public static final class CameraId {
        private final List<String> mIds = new ArrayList();

        CameraId(LinkedHashSet<CameraInternal> linkedHashSet) {
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                this.mIds.add(((CameraInternal) it.next()).getCameraInfoInternal().getCameraId());
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof CameraId) {
                return this.mIds.equals(((CameraId) obj).mIds);
            }
            return false;
        }

        public int hashCode() {
            return this.mIds.hashCode() * 53;
        }
    }

    public static final class CameraException extends Exception {
        public CameraException() {
        }

        public CameraException(String str) {
            super(str);
        }

        public CameraException(Throwable th) {
            super(th);
        }
    }

    public CameraControl getCameraControl() {
        return this.mCameraInternal.getCameraControlInternal();
    }

    public CameraInfo getCameraInfo() {
        return this.mCameraInternal.getCameraInfoInternal();
    }

    public LinkedHashSet<CameraInternal> getCameraInternals() {
        return this.mCameraInternals;
    }

    public CameraConfig getExtendedConfig() {
        CameraConfig cameraConfig;
        synchronized (this.mLock) {
            cameraConfig = this.mCameraConfig;
        }
        return cameraConfig;
    }

    public void setExtendedConfig(CameraConfig cameraConfig) {
        synchronized (this.mLock) {
            if (cameraConfig == null) {
                cameraConfig = CameraConfigs.emptyConfig();
            }
            if (!this.mUseCases.isEmpty()) {
                if (!this.mCameraConfig.getCompatibilityId().equals(cameraConfig.getCompatibilityId())) {
                    throw new IllegalStateException("Need to unbind all use cases before binding with extension enabled");
                }
            }
            this.mCameraConfig = cameraConfig;
            this.mCameraInternal.setExtendedConfig(cameraConfig);
        }
    }

    public boolean isUseCasesCombinationSupported(UseCase... useCaseArr) {
        synchronized (this.mLock) {
            try {
                calculateSuggestedResolutions(this.mCameraInternal.getCameraInfoInternal(), Arrays.asList(useCaseArr), Collections.emptyList(), getConfigs(Arrays.asList(useCaseArr), this.mCameraConfig.getUseCaseConfigFactory(), this.mUseCaseConfigFactory));
            } catch (IllegalArgumentException unused) {
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    private List<UseCase> calculateRequiredExtraUseCases(List<UseCase> list, List<UseCase> list2) {
        ArrayList arrayList = new ArrayList(list2);
        boolean isExtraPreviewRequired = isExtraPreviewRequired(list);
        boolean isExtraImageCaptureRequired = isExtraImageCaptureRequired(list);
        UseCase useCase = null;
        UseCase useCase2 = null;
        for (UseCase next : list2) {
            if (isPreview(next)) {
                useCase = next;
            } else if (isImageCapture(next)) {
                useCase2 = next;
            }
        }
        if (isExtraPreviewRequired && useCase == null) {
            arrayList.add(createExtraPreview());
        } else if (!isExtraPreviewRequired && useCase != null) {
            arrayList.remove(useCase);
        }
        if (isExtraImageCaptureRequired && useCase2 == null) {
            arrayList.add(createExtraImageCapture());
        } else if (!isExtraImageCaptureRequired && useCase2 != null) {
            arrayList.remove(useCase2);
        }
        return arrayList;
    }

    private void detachUnnecessaryUseCases(List<UseCase> list) {
        synchronized (this.mLock) {
            if (!list.isEmpty()) {
                this.mCameraInternal.detachUseCases(list);
                for (UseCase next : list) {
                    if (this.mUseCases.contains(next)) {
                        next.onDetach(this.mCameraInternal);
                    } else {
                        Logger.e(TAG, "Attempting to detach non-attached UseCase: " + next);
                    }
                }
                this.mUseCases.removeAll(list);
            }
        }
    }

    private boolean isCoexistingPreviewImageCaptureRequired() {
        boolean z;
        synchronized (this.mLock) {
            z = true;
            if (this.mCameraConfig.getUseCaseCombinationRequiredRule() != 1) {
                z = false;
            }
        }
        return z;
    }

    private boolean isExtraPreviewRequired(List<UseCase> list) {
        boolean z = false;
        boolean z2 = false;
        for (UseCase next : list) {
            if (isPreview(next)) {
                z2 = true;
            } else if (isImageCapture(next)) {
                z = true;
            }
        }
        if (!z || z2) {
            return false;
        }
        return true;
    }

    private boolean isExtraImageCaptureRequired(List<UseCase> list) {
        boolean z = false;
        boolean z2 = false;
        for (UseCase next : list) {
            if (isPreview(next)) {
                z = true;
            } else if (isImageCapture(next)) {
                z2 = true;
            }
        }
        if (!z || z2) {
            return false;
        }
        return true;
    }

    private boolean isPreview(UseCase useCase) {
        return useCase instanceof Preview;
    }

    private boolean isImageCapture(UseCase useCase) {
        return useCase instanceof ImageCapture;
    }

    private Preview createExtraPreview() {
        Preview build = new Preview.Builder().setTargetName("Preview-Extra").build();
        build.setSurfaceProvider(new CameraUseCaseAdapter$$ExternalSyntheticLambda1());
        return build;
    }

    static /* synthetic */ void lambda$createExtraPreview$1(SurfaceRequest surfaceRequest) {
        SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(surfaceRequest.getResolution().getWidth(), surfaceRequest.getResolution().getHeight());
        surfaceTexture.detachFromGLContext();
        Surface surface = new Surface(surfaceTexture);
        surfaceRequest.provideSurface(surface, CameraXExecutors.directExecutor(), new CameraUseCaseAdapter$$ExternalSyntheticLambda0(surface, surfaceTexture));
    }

    static /* synthetic */ void lambda$createExtraPreview$0(Surface surface, SurfaceTexture surfaceTexture, SurfaceRequest.Result result) {
        surface.release();
        surfaceTexture.release();
    }

    private ImageCapture createExtraImageCapture() {
        return new ImageCapture.Builder().setTargetName("ImageCapture-Extra").build();
    }
}
