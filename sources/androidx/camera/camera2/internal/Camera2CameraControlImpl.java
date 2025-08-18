package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.util.ArrayMap;
import android.util.Rational;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.workaround.AeFpsRange;
import androidx.camera.camera2.internal.compat.workaround.AutoFlashAEModeDisabler;
import androidx.camera.camera2.interop.Camera2CameraControl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class Camera2CameraControlImpl implements CameraControlInternal {
    private static final int DEFAULT_TEMPLATE = 1;
    private static final String TAG = "Camera2CameraControlImp";
    static final String TAG_SESSION_UPDATE_ID = "CameraControlSessionUpdateId";
    private final AeFpsRange mAeFpsRange;
    private final AutoFlashAEModeDisabler mAutoFlashAEModeDisabler;
    private final Camera2CameraControl mCamera2CameraControl;
    private final Camera2CapturePipeline mCamera2CapturePipeline;
    private final CameraCaptureCallbackSet mCameraCaptureCallbackSet;
    private final CameraCharacteristicsCompat mCameraCharacteristics;
    private final CameraControlInternal.ControlUpdateCallback mControlUpdateCallback;
    private long mCurrentSessionUpdateId;
    final Executor mExecutor;
    private final ExposureControl mExposureControl;
    private volatile int mFlashMode;
    private volatile ListenableFuture<Void> mFlashModeChangeSessionUpdateFuture;
    private final FocusMeteringControl mFocusMeteringControl;
    private volatile boolean mIsTorchOn;
    private final Object mLock;
    private final AtomicLong mNextSessionUpdateId;
    final CameraControlSessionCallback mSessionCallback;
    private final SessionConfig.Builder mSessionConfigBuilder;
    private int mTemplate;
    private final TorchControl mTorchControl;
    private int mUseCount;
    private final ZoomControl mZoomControl;
    ZslControl mZslControl;

    public interface CaptureResultListener {
        boolean onCaptureResult(TotalCaptureResult totalCaptureResult);
    }

    static /* synthetic */ void lambda$addInteropConfig$1() {
    }

    static /* synthetic */ void lambda$clearInteropConfig$2() {
    }

    Camera2CameraControlImpl(CameraCharacteristicsCompat cameraCharacteristicsCompat, ScheduledExecutorService scheduledExecutorService, Executor executor, CameraControlInternal.ControlUpdateCallback controlUpdateCallback) {
        this(cameraCharacteristicsCompat, scheduledExecutorService, executor, controlUpdateCallback, new Quirks(new ArrayList()));
    }

    Camera2CameraControlImpl(CameraCharacteristicsCompat cameraCharacteristicsCompat, ScheduledExecutorService scheduledExecutorService, Executor executor, CameraControlInternal.ControlUpdateCallback controlUpdateCallback, Quirks quirks) {
        this.mLock = new Object();
        SessionConfig.Builder builder = new SessionConfig.Builder();
        this.mSessionConfigBuilder = builder;
        this.mUseCount = 0;
        this.mIsTorchOn = false;
        this.mFlashMode = 2;
        this.mNextSessionUpdateId = new AtomicLong(0);
        this.mFlashModeChangeSessionUpdateFuture = Futures.immediateFuture(null);
        this.mTemplate = 1;
        this.mCurrentSessionUpdateId = 0;
        CameraCaptureCallbackSet cameraCaptureCallbackSet = new CameraCaptureCallbackSet();
        this.mCameraCaptureCallbackSet = cameraCaptureCallbackSet;
        this.mCameraCharacteristics = cameraCharacteristicsCompat;
        this.mControlUpdateCallback = controlUpdateCallback;
        this.mExecutor = executor;
        CameraControlSessionCallback cameraControlSessionCallback = new CameraControlSessionCallback(executor);
        this.mSessionCallback = cameraControlSessionCallback;
        builder.setTemplateType(this.mTemplate);
        builder.addRepeatingCameraCaptureCallback(CaptureCallbackContainer.create(cameraControlSessionCallback));
        builder.addRepeatingCameraCaptureCallback(cameraCaptureCallbackSet);
        this.mExposureControl = new ExposureControl(this, cameraCharacteristicsCompat, executor);
        this.mFocusMeteringControl = new FocusMeteringControl(this, scheduledExecutorService, executor, quirks);
        this.mZoomControl = new ZoomControl(this, cameraCharacteristicsCompat, executor);
        this.mTorchControl = new TorchControl(this, cameraCharacteristicsCompat, executor);
        this.mZslControl = new ZslControlImpl(cameraCharacteristicsCompat);
        this.mAeFpsRange = new AeFpsRange(quirks);
        this.mAutoFlashAEModeDisabler = new AutoFlashAEModeDisabler(quirks);
        this.mCamera2CameraControl = new Camera2CameraControl(this, executor);
        this.mCamera2CapturePipeline = new Camera2CapturePipeline(this, cameraCharacteristicsCompat, quirks, executor);
        executor.execute(new Camera2CameraControlImpl$$ExternalSyntheticLambda10(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ void m5lambda$new$0$androidxcameracamera2internalCamera2CameraControlImpl() {
        addCaptureResultListener(this.mCamera2CameraControl.getCaptureRequestListener());
    }

    /* access modifiers changed from: package-private */
    public void incrementUseCount() {
        synchronized (this.mLock) {
            this.mUseCount++;
        }
    }

    /* access modifiers changed from: package-private */
    public void decrementUseCount() {
        synchronized (this.mLock) {
            int i = this.mUseCount;
            if (i != 0) {
                this.mUseCount = i - 1;
            } else {
                throw new IllegalStateException("Decrementing use count occurs more times than incrementing");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getUseCount() {
        int i;
        synchronized (this.mLock) {
            i = this.mUseCount;
        }
        return i;
    }

    public ZoomControl getZoomControl() {
        return this.mZoomControl;
    }

    public FocusMeteringControl getFocusMeteringControl() {
        return this.mFocusMeteringControl;
    }

    public TorchControl getTorchControl() {
        return this.mTorchControl;
    }

    public ExposureControl getExposureControl() {
        return this.mExposureControl;
    }

    public ZslControl getZslControl() {
        return this.mZslControl;
    }

    public Camera2CameraControl getCamera2CameraControl() {
        return this.mCamera2CameraControl;
    }

    public void addInteropConfig(Config config) {
        this.mCamera2CameraControl.addCaptureRequestOptions(CaptureRequestOptions.Builder.from(config).build()).addListener(new Camera2CameraControlImpl$$ExternalSyntheticLambda1(), CameraXExecutors.directExecutor());
    }

    public void clearInteropConfig() {
        this.mCamera2CameraControl.clearCaptureRequestOptions().addListener(new Camera2CameraControlImpl$$ExternalSyntheticLambda4(), CameraXExecutors.directExecutor());
    }

    public Config getInteropConfig() {
        return this.mCamera2CameraControl.getCamera2ImplConfig();
    }

    /* access modifiers changed from: package-private */
    public void setActive(boolean z) {
        this.mFocusMeteringControl.setActive(z);
        this.mZoomControl.setActive(z);
        this.mTorchControl.setActive(z);
        this.mExposureControl.setActive(z);
        this.mCamera2CameraControl.setActive(z);
    }

    public void setPreviewAspectRatio(Rational rational) {
        this.mFocusMeteringControl.setPreviewAspectRatio(rational);
    }

    public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction focusMeteringAction) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mFocusMeteringControl.startFocusAndMetering(focusMeteringAction));
    }

    public ListenableFuture<Void> cancelFocusAndMetering() {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mFocusMeteringControl.cancelFocusAndMetering());
    }

    public ListenableFuture<Void> setZoomRatio(float f) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mZoomControl.setZoomRatio(f));
    }

    public ListenableFuture<Void> setLinearZoom(float f) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mZoomControl.setLinearZoom(f));
    }

    public int getFlashMode() {
        return this.mFlashMode;
    }

    public void setFlashMode(int i) {
        if (!isControlInUse()) {
            Logger.w(TAG, "Camera is not active.");
            return;
        }
        this.mFlashMode = i;
        ZslControl zslControl = this.mZslControl;
        boolean z = true;
        if (!(this.mFlashMode == 1 || this.mFlashMode == 0)) {
            z = false;
        }
        zslControl.setZslDisabledByFlashMode(z);
        this.mFlashModeChangeSessionUpdateFuture = updateSessionConfigAsync();
    }

    public void addZslConfig(SessionConfig.Builder builder) {
        this.mZslControl.addZslConfig(builder);
    }

    public void setZslDisabledByUserCaseConfig(boolean z) {
        this.mZslControl.setZslDisabledByUserCaseConfig(z);
    }

    public boolean isZslDisabledByByUserCaseConfig() {
        return this.mZslControl.isZslDisabledByUserCaseConfig();
    }

    public ListenableFuture<Void> enableTorch(boolean z) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mTorchControl.enableTorch(z));
    }

    private ListenableFuture<Void> waitForSessionUpdateId(long j) {
        return CallbackToFutureAdapter.getFuture(new Camera2CameraControlImpl$$ExternalSyntheticLambda6(this, j));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$waitForSessionUpdateId$4$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ Object m10lambda$waitForSessionUpdateId$4$androidxcameracamera2internalCamera2CameraControlImpl(long j, CallbackToFutureAdapter.Completer completer) throws Exception {
        addCaptureResultListener(new Camera2CameraControlImpl$$ExternalSyntheticLambda7(j, completer));
        return "waitForSessionUpdateId:" + j;
    }

    static /* synthetic */ boolean lambda$waitForSessionUpdateId$3(long j, CallbackToFutureAdapter.Completer completer, TotalCaptureResult totalCaptureResult) {
        if (!isSessionUpdated(totalCaptureResult, j)) {
            return false;
        }
        completer.set(null);
        return true;
    }

    static boolean isSessionUpdated(TotalCaptureResult totalCaptureResult, long j) {
        Long l;
        if (totalCaptureResult.getRequest() == null) {
            return false;
        }
        Object tag = totalCaptureResult.getRequest().getTag();
        if (!(tag instanceof TagBundle) || (l = (Long) ((TagBundle) tag).getTag(TAG_SESSION_UPDATE_ID)) == null || l.longValue() < j) {
            return false;
        }
        return true;
    }

    public ListenableFuture<Integer> setExposureCompensationIndex(int i) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return this.mExposureControl.setExposureCompensationIndex(i);
    }

    public ListenableFuture<List<Void>> submitStillCaptureRequests(List<CaptureConfig> list, int i, int i2) {
        if (!isControlInUse()) {
            Logger.w(TAG, "Camera is not active.");
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return FutureChain.from(Futures.nonCancellationPropagating(this.mFlashModeChangeSessionUpdateFuture)).transformAsync(new Camera2CameraControlImpl$$ExternalSyntheticLambda8(this, list, i, getFlashMode(), i2), this.mExecutor);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$submitStillCaptureRequests$5$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m7lambda$submitStillCaptureRequests$5$androidxcameracamera2internalCamera2CameraControlImpl(List list, int i, int i2, int i3, Void voidR) throws Exception {
        return this.mCamera2CapturePipeline.submitStillCaptures(list, i, i2, i3);
    }

    public SessionConfig getSessionConfig() {
        this.mSessionConfigBuilder.setTemplateType(this.mTemplate);
        this.mSessionConfigBuilder.setImplementationOptions(getSessionOptions());
        Object captureRequestTag = this.mCamera2CameraControl.getCamera2ImplConfig().getCaptureRequestTag((Object) null);
        if (captureRequestTag != null && (captureRequestTag instanceof Integer)) {
            this.mSessionConfigBuilder.addTag(Camera2CameraControl.TAG_KEY, captureRequestTag);
        }
        this.mSessionConfigBuilder.addTag(TAG_SESSION_UPDATE_ID, Long.valueOf(this.mCurrentSessionUpdateId));
        return this.mSessionConfigBuilder.build();
    }

    /* access modifiers changed from: package-private */
    public void setTemplate(int i) {
        this.mTemplate = i;
        this.mFocusMeteringControl.setTemplate(i);
        this.mCamera2CapturePipeline.setTemplate(this.mTemplate);
    }

    /* access modifiers changed from: package-private */
    public void resetTemplate() {
        setTemplate(1);
    }

    private boolean isControlInUse() {
        return getUseCount() > 0;
    }

    public void updateSessionConfig() {
        this.mExecutor.execute(new Camera2CameraControlImpl$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> updateSessionConfigAsync() {
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new Camera2CameraControlImpl$$ExternalSyntheticLambda3(this)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateSessionConfigAsync$7$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ Object m9lambda$updateSessionConfigAsync$7$androidxcameracamera2internalCamera2CameraControlImpl(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Camera2CameraControlImpl$$ExternalSyntheticLambda9(this, completer));
        return "updateSessionConfigAsync";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateSessionConfigAsync$6$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ void m8lambda$updateSessionConfigAsync$6$androidxcameracamera2internalCamera2CameraControlImpl(CallbackToFutureAdapter.Completer completer) {
        Futures.propagate(waitForSessionUpdateId(updateSessionConfigSynchronous()), completer);
    }

    /* access modifiers changed from: package-private */
    public long updateSessionConfigSynchronous() {
        this.mCurrentSessionUpdateId = this.mNextSessionUpdateId.getAndIncrement();
        this.mControlUpdateCallback.onCameraControlUpdateSessionConfig();
        return this.mCurrentSessionUpdateId;
    }

    /* access modifiers changed from: package-private */
    public Rect getCropSensorRegion() {
        return this.mZoomControl.getCropSensorRegion();
    }

    public Rect getSensorRect() {
        return (Rect) Preconditions.checkNotNull((Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE));
    }

    /* access modifiers changed from: package-private */
    public void removeCaptureResultListener(CaptureResultListener captureResultListener) {
        this.mSessionCallback.removeListener(captureResultListener);
    }

    /* access modifiers changed from: package-private */
    public void addCaptureResultListener(CaptureResultListener captureResultListener) {
        this.mSessionCallback.addListener(captureResultListener);
    }

    /* access modifiers changed from: package-private */
    public void addSessionCameraCaptureCallback(Executor executor, CameraCaptureCallback cameraCaptureCallback) {
        this.mExecutor.execute(new Camera2CameraControlImpl$$ExternalSyntheticLambda5(this, executor, cameraCaptureCallback));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addSessionCameraCaptureCallback$8$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ void m4lambda$addSessionCameraCaptureCallback$8$androidxcameracamera2internalCamera2CameraControlImpl(Executor executor, CameraCaptureCallback cameraCaptureCallback) {
        this.mCameraCaptureCallbackSet.addCaptureCallback(executor, cameraCaptureCallback);
    }

    /* access modifiers changed from: package-private */
    public void removeSessionCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
        this.mExecutor.execute(new Camera2CameraControlImpl$$ExternalSyntheticLambda0(this, cameraCaptureCallback));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeSessionCameraCaptureCallback$9$androidx-camera-camera2-internal-Camera2CameraControlImpl  reason: not valid java name */
    public /* synthetic */ void m6lambda$removeSessionCameraCaptureCallback$9$androidxcameracamera2internalCamera2CameraControlImpl(CameraCaptureCallback cameraCaptureCallback) {
        this.mCameraCaptureCallbackSet.removeCaptureCallback(cameraCaptureCallback);
    }

    /* access modifiers changed from: package-private */
    public void enableTorchInternal(boolean z) {
        this.mIsTorchOn = z;
        if (!z) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setTemplateType(this.mTemplate);
            builder.setUseRepeatingSurface(true);
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(getSupportedAeMode(1)));
            builder2.setCaptureRequestOption(CaptureRequest.FLASH_MODE, 0);
            builder.addImplementationOptions(builder2.build());
            submitCaptureRequestsInternal(Collections.singletonList(builder.build()));
        }
        updateSessionConfigSynchronous();
    }

    /* access modifiers changed from: package-private */
    public boolean isTorchOn() {
        return this.mIsTorchOn;
    }

    /* access modifiers changed from: package-private */
    public void submitCaptureRequestsInternal(List<CaptureConfig> list) {
        this.mControlUpdateCallback.onCameraControlCaptureRequests(list);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0070 A[LOOP:0: B:10:0x006a->B:12:0x0070, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.camera.core.impl.Config getSessionOptions() {
        /*
            r6 = this;
            androidx.camera.camera2.impl.Camera2ImplConfig$Builder r0 = new androidx.camera.camera2.impl.Camera2ImplConfig$Builder
            r0.<init>()
            android.hardware.camera2.CaptureRequest$Key r1 = android.hardware.camera2.CaptureRequest.CONTROL_MODE
            r2 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r0.setCaptureRequestOption(r1, r3)
            androidx.camera.camera2.internal.FocusMeteringControl r1 = r6.mFocusMeteringControl
            r1.addFocusMeteringOptions(r0)
            androidx.camera.camera2.internal.compat.workaround.AeFpsRange r1 = r6.mAeFpsRange
            r1.addAeFpsRangeOptions(r0)
            androidx.camera.camera2.internal.ZoomControl r1 = r6.mZoomControl
            r1.addZoomOption(r0)
            boolean r1 = r6.mIsTorchOn
            r3 = 2
            if (r1 == 0) goto L_0x002d
            android.hardware.camera2.CaptureRequest$Key r1 = android.hardware.camera2.CaptureRequest.FLASH_MODE
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r0.setCaptureRequestOption(r1, r3)
            goto L_0x0033
        L_0x002d:
            int r1 = r6.mFlashMode
            if (r1 == 0) goto L_0x0037
            if (r1 == r2) goto L_0x0035
        L_0x0033:
            r1 = r2
            goto L_0x003d
        L_0x0035:
            r1 = 3
            goto L_0x003d
        L_0x0037:
            androidx.camera.camera2.internal.compat.workaround.AutoFlashAEModeDisabler r1 = r6.mAutoFlashAEModeDisabler
            int r1 = r1.getCorrectedAeMode(r3)
        L_0x003d:
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE
            int r1 = r6.getSupportedAeMode(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.setCaptureRequestOption(r3, r1)
            android.hardware.camera2.CaptureRequest$Key r1 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_MODE
            int r2 = r6.getSupportedAwbMode(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.setCaptureRequestOption(r1, r2)
            androidx.camera.camera2.internal.ExposureControl r1 = r6.mExposureControl
            r1.setCaptureRequestOption(r0)
            androidx.camera.camera2.interop.Camera2CameraControl r6 = r6.mCamera2CameraControl
            androidx.camera.camera2.impl.Camera2ImplConfig r6 = r6.getCamera2ImplConfig()
            java.util.Set r1 = r6.listOptions()
            java.util.Iterator r1 = r1.iterator()
        L_0x006a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0084
            java.lang.Object r2 = r1.next()
            androidx.camera.core.impl.Config$Option r2 = (androidx.camera.core.impl.Config.Option) r2
            androidx.camera.core.impl.MutableConfig r3 = r0.getMutableConfig()
            androidx.camera.core.impl.Config$OptionPriority r4 = androidx.camera.core.impl.Config.OptionPriority.ALWAYS_OVERRIDE
            java.lang.Object r5 = r6.retrieveOption(r2)
            r3.insertOption(r2, r4, r5)
            goto L_0x006a
        L_0x0084:
            androidx.camera.camera2.impl.Camera2ImplConfig r6 = r0.build()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.Camera2CameraControlImpl.getSessionOptions():androidx.camera.core.impl.Config");
    }

    /* access modifiers changed from: package-private */
    public int getSupportedAfMode(int i) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        if (isModeInList(i, iArr)) {
            return i;
        }
        if (isModeInList(4, iArr)) {
            return 4;
        }
        if (isModeInList(1, iArr)) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getSupportedAeMode(int i) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        if (isModeInList(i, iArr)) {
            return i;
        }
        if (isModeInList(1, iArr)) {
            return 1;
        }
        return 0;
    }

    private int getSupportedAwbMode(int i) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        if (isModeInList(i, iArr)) {
            return i;
        }
        if (isModeInList(1, iArr)) {
            return 1;
        }
        return 0;
    }

    private boolean isModeInList(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getMaxAfRegionCount() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* access modifiers changed from: package-private */
    public int getMaxAeRegionCount() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* access modifiers changed from: package-private */
    public int getMaxAwbRegionCount() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* access modifiers changed from: package-private */
    public long getCurrentSessionUpdateId() {
        return this.mCurrentSessionUpdateId;
    }

    static final class CameraControlSessionCallback extends CameraCaptureSession.CaptureCallback {
        private final Executor mExecutor;
        final Set<CaptureResultListener> mResultListeners = new HashSet();

        CameraControlSessionCallback(Executor executor) {
            this.mExecutor = executor;
        }

        /* access modifiers changed from: package-private */
        public void addListener(CaptureResultListener captureResultListener) {
            this.mResultListeners.add(captureResultListener);
        }

        /* access modifiers changed from: package-private */
        public void removeListener(CaptureResultListener captureResultListener) {
            this.mResultListeners.remove(captureResultListener);
        }

        public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            this.mExecutor.execute(new Camera2CameraControlImpl$CameraControlSessionCallback$$ExternalSyntheticLambda0(this, totalCaptureResult));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCaptureCompleted$0$androidx-camera-camera2-internal-Camera2CameraControlImpl$CameraControlSessionCallback  reason: not valid java name */
        public /* synthetic */ void m11lambda$onCaptureCompleted$0$androidxcameracamera2internalCamera2CameraControlImpl$CameraControlSessionCallback(TotalCaptureResult totalCaptureResult) {
            HashSet hashSet = new HashSet();
            for (CaptureResultListener next : this.mResultListeners) {
                if (next.onCaptureResult(totalCaptureResult)) {
                    hashSet.add(next);
                }
            }
            if (!hashSet.isEmpty()) {
                this.mResultListeners.removeAll(hashSet);
            }
        }
    }

    static final class CameraCaptureCallbackSet extends CameraCaptureCallback {
        Map<CameraCaptureCallback, Executor> mCallbackExecutors = new ArrayMap();
        Set<CameraCaptureCallback> mCallbacks = new HashSet();

        CameraCaptureCallbackSet() {
        }

        /* access modifiers changed from: package-private */
        public void addCaptureCallback(Executor executor, CameraCaptureCallback cameraCaptureCallback) {
            this.mCallbacks.add(cameraCaptureCallback);
            this.mCallbackExecutors.put(cameraCaptureCallback, executor);
        }

        /* access modifiers changed from: package-private */
        public void removeCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            this.mCallbacks.remove(cameraCaptureCallback);
            this.mCallbackExecutors.remove(cameraCaptureCallback);
        }

        public void onCaptureCompleted(CameraCaptureResult cameraCaptureResult) {
            for (CameraCaptureCallback next : this.mCallbacks) {
                try {
                    this.mCallbackExecutors.get(next).execute(new Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda2(next, cameraCaptureResult));
                } catch (RejectedExecutionException e) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureCompleted.", e);
                }
            }
        }

        public void onCaptureFailed(CameraCaptureFailure cameraCaptureFailure) {
            for (CameraCaptureCallback next : this.mCallbacks) {
                try {
                    this.mCallbackExecutors.get(next).execute(new Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda0(next, cameraCaptureFailure));
                } catch (RejectedExecutionException e) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureFailed.", e);
                }
            }
        }

        public void onCaptureCancelled() {
            for (CameraCaptureCallback next : this.mCallbacks) {
                try {
                    this.mCallbackExecutors.get(next).execute(new Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda1(next));
                } catch (RejectedExecutionException e) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureCancelled.", e);
                }
            }
        }
    }
}
