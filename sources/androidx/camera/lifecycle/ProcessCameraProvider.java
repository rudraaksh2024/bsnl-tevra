package androidx.camera.lifecycle;

import android.content.Context;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraEffect;
import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraX;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.ViewPort;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.ExtendedCameraConfigProviderStore;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public final class ProcessCameraProvider implements LifecycleCameraProvider {
    private static final ProcessCameraProvider sAppInstance = new ProcessCameraProvider();
    private CameraX mCameraX;
    private CameraXConfig.Provider mCameraXConfigProvider = null;
    private ListenableFuture<CameraX> mCameraXInitializeFuture;
    private ListenableFuture<Void> mCameraXShutdownFuture = Futures.immediateFuture(null);
    private Context mContext;
    private final LifecycleCameraRepository mLifecycleCameraRepository = new LifecycleCameraRepository();
    private final Object mLock = new Object();

    static /* synthetic */ CameraXConfig lambda$configureInstanceInternal$3(CameraXConfig cameraXConfig) {
        return cameraXConfig;
    }

    public static ListenableFuture<ProcessCameraProvider> getInstance(Context context) {
        Preconditions.checkNotNull(context);
        return Futures.transform(sAppInstance.getOrCreateCameraXInstance(context), new ProcessCameraProvider$$ExternalSyntheticLambda2(context), CameraXExecutors.directExecutor());
    }

    static /* synthetic */ ProcessCameraProvider lambda$getInstance$0(Context context, CameraX cameraX) {
        ProcessCameraProvider processCameraProvider = sAppInstance;
        processCameraProvider.setCameraX(cameraX);
        processCameraProvider.setContext(ContextUtil.getApplicationContext(context));
        return processCameraProvider;
    }

    private ListenableFuture<CameraX> getOrCreateCameraXInstance(Context context) {
        synchronized (this.mLock) {
            ListenableFuture<CameraX> listenableFuture = this.mCameraXInitializeFuture;
            if (listenableFuture != null) {
                return listenableFuture;
            }
            ListenableFuture<CameraX> future = CallbackToFutureAdapter.getFuture(new ProcessCameraProvider$$ExternalSyntheticLambda3(this, new CameraX(context, this.mCameraXConfigProvider)));
            this.mCameraXInitializeFuture = future;
            return future;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getOrCreateCameraXInstance$2$androidx-camera-lifecycle-ProcessCameraProvider  reason: not valid java name */
    public /* synthetic */ Object m184lambda$getOrCreateCameraXInstance$2$androidxcameralifecycleProcessCameraProvider(final CameraX cameraX, final CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.mLock) {
            Futures.addCallback(FutureChain.from(this.mCameraXShutdownFuture).transformAsync(new ProcessCameraProvider$$ExternalSyntheticLambda0(cameraX), CameraXExecutors.directExecutor()), new FutureCallback<Void>() {
                public void onSuccess(Void voidR) {
                    completer.set(cameraX);
                }

                public void onFailure(Throwable th) {
                    completer.setException(th);
                }
            }, CameraXExecutors.directExecutor());
        }
        return "ProcessCameraProvider-initializeCameraX";
    }

    public static void configureInstance(CameraXConfig cameraXConfig) {
        sAppInstance.configureInstanceInternal(cameraXConfig);
    }

    private void configureInstanceInternal(CameraXConfig cameraXConfig) {
        synchronized (this.mLock) {
            Preconditions.checkNotNull(cameraXConfig);
            Preconditions.checkState(this.mCameraXConfigProvider == null, "CameraX has already been configured. To use a different configuration, shutdown() must be called.");
            this.mCameraXConfigProvider = new ProcessCameraProvider$$ExternalSyntheticLambda1(cameraXConfig);
        }
    }

    public ListenableFuture<Void> shutdown() {
        ListenableFuture<Void> listenableFuture;
        this.mLifecycleCameraRepository.clear();
        CameraX cameraX = this.mCameraX;
        if (cameraX != null) {
            listenableFuture = cameraX.shutdown();
        } else {
            listenableFuture = Futures.immediateFuture(null);
        }
        synchronized (this.mLock) {
            this.mCameraXConfigProvider = null;
            this.mCameraXInitializeFuture = null;
            this.mCameraXShutdownFuture = listenableFuture;
        }
        this.mCameraX = null;
        this.mContext = null;
        return listenableFuture;
    }

    private void setCameraX(CameraX cameraX) {
        this.mCameraX = cameraX;
    }

    private void setContext(Context context) {
        this.mContext = context;
    }

    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCase... useCaseArr) {
        return bindToLifecycle(lifecycleOwner, cameraSelector, (ViewPort) null, Collections.emptyList(), useCaseArr);
    }

    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, UseCaseGroup useCaseGroup) {
        return bindToLifecycle(lifecycleOwner, cameraSelector, useCaseGroup.getViewPort(), useCaseGroup.getEffects(), (UseCase[]) useCaseGroup.getUseCases().toArray(new UseCase[0]));
    }

    /* access modifiers changed from: package-private */
    public Camera bindToLifecycle(LifecycleOwner lifecycleOwner, CameraSelector cameraSelector, ViewPort viewPort, List<CameraEffect> list, UseCase... useCaseArr) {
        CameraConfig cameraConfig;
        CameraConfig config;
        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
        UseCase[] useCaseArr2 = useCaseArr;
        Threads.checkMainThread();
        CameraSelector.Builder fromSelector = CameraSelector.Builder.fromSelector(cameraSelector);
        int length = useCaseArr2.length;
        int i = 0;
        while (true) {
            cameraConfig = null;
            if (i >= length) {
                break;
            }
            CameraSelector cameraSelector2 = useCaseArr2[i].getCurrentConfig().getCameraSelector((CameraSelector) null);
            if (cameraSelector2 != null) {
                Iterator it = cameraSelector2.getCameraFilterSet().iterator();
                while (it.hasNext()) {
                    fromSelector.addCameraFilter((CameraFilter) it.next());
                }
            }
            i++;
        }
        LinkedHashSet<CameraInternal> filter = fromSelector.build().filter(this.mCameraX.getCameraRepository().getCameras());
        if (!filter.isEmpty()) {
            LifecycleCamera lifecycleCamera = this.mLifecycleCameraRepository.getLifecycleCamera(lifecycleOwner, CameraUseCaseAdapter.generateCameraId(filter));
            Collection<LifecycleCamera> lifecycleCameras = this.mLifecycleCameraRepository.getLifecycleCameras();
            for (UseCase useCase : useCaseArr2) {
                for (LifecycleCamera next : lifecycleCameras) {
                    if (next.isBound(useCase) && next != lifecycleCamera) {
                        throw new IllegalStateException(String.format("Use case %s already bound to a different lifecycle.", new Object[]{useCase}));
                    }
                }
            }
            if (lifecycleCamera == null) {
                lifecycleCamera = this.mLifecycleCameraRepository.createLifecycleCamera(lifecycleOwner, new CameraUseCaseAdapter(filter, this.mCameraX.getCameraDeviceSurfaceManager(), this.mCameraX.getDefaultConfigFactory()));
            }
            Iterator it2 = cameraSelector.getCameraFilterSet().iterator();
            while (it2.hasNext()) {
                CameraFilter cameraFilter = (CameraFilter) it2.next();
                if (!(cameraFilter.getIdentifier() == CameraFilter.DEFAULT_ID || (config = ExtendedCameraConfigProviderStore.getConfigProvider(cameraFilter.getIdentifier()).getConfig(lifecycleCamera.getCameraInfo(), this.mContext)) == null)) {
                    if (cameraConfig == null) {
                        cameraConfig = config;
                    } else {
                        throw new IllegalArgumentException("Cannot apply multiple extended camera configs at the same time.");
                    }
                }
            }
            lifecycleCamera.setExtendedConfig(cameraConfig);
            if (useCaseArr2.length == 0) {
                return lifecycleCamera;
            }
            this.mLifecycleCameraRepository.bindToLifecycleCamera(lifecycleCamera, viewPort, list, Arrays.asList(useCaseArr));
            return lifecycleCamera;
        }
        throw new IllegalArgumentException("Provided camera selector unable to resolve a camera for the given use case");
    }

    public boolean isBound(UseCase useCase) {
        for (LifecycleCamera isBound : this.mLifecycleCameraRepository.getLifecycleCameras()) {
            if (isBound.isBound(useCase)) {
                return true;
            }
        }
        return false;
    }

    public void unbind(UseCase... useCaseArr) {
        Threads.checkMainThread();
        this.mLifecycleCameraRepository.unbind(Arrays.asList(useCaseArr));
    }

    public void unbindAll() {
        Threads.checkMainThread();
        this.mLifecycleCameraRepository.unbindAll();
    }

    public boolean hasCamera(CameraSelector cameraSelector) throws CameraInfoUnavailableException {
        try {
            cameraSelector.select(this.mCameraX.getCameraRepository().getCameras());
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public List<CameraInfo> getAvailableCameraInfos() {
        ArrayList arrayList = new ArrayList();
        for (CameraInternal cameraInfo : this.mCameraX.getCameraRepository().getCameras()) {
            arrayList.add(cameraInfo.getCameraInfo());
        }
        return arrayList;
    }

    private ProcessCameraProvider() {
    }
}
