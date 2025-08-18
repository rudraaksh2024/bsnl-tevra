package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.workaround.OverrideAeModeForStillCapture;
import androidx.camera.camera2.internal.compat.workaround.UseTorchAsFlash;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureMetaData;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

class Camera2CapturePipeline {
    private static final Set<CameraCaptureMetaData.AeState> AE_CONVERGED_STATE_SET;
    private static final Set<CameraCaptureMetaData.AeState> AE_TORCH_AS_FLASH_CONVERGED_STATE_SET;
    private static final Set<CameraCaptureMetaData.AfState> AF_CONVERGED_STATE_SET = Collections.unmodifiableSet(EnumSet.of(CameraCaptureMetaData.AfState.PASSIVE_FOCUSED, CameraCaptureMetaData.AfState.PASSIVE_NOT_FOCUSED, CameraCaptureMetaData.AfState.LOCKED_FOCUSED, CameraCaptureMetaData.AfState.LOCKED_NOT_FOCUSED));
    private static final Set<CameraCaptureMetaData.AwbState> AWB_CONVERGED_STATE_SET = Collections.unmodifiableSet(EnumSet.of(CameraCaptureMetaData.AwbState.CONVERGED, CameraCaptureMetaData.AwbState.UNKNOWN));
    private static final String TAG = "Camera2CapturePipeline";
    private final Camera2CameraControlImpl mCameraControl;
    private final Quirks mCameraQuirk;
    private final Executor mExecutor;
    private final boolean mIsLegacyDevice;
    private int mTemplate = 1;
    private final UseTorchAsFlash mUseTorchAsFlash;

    interface PipelineTask {
        boolean isCaptureResultNeeded();

        void postCapture();

        ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult);
    }

    static {
        Set<CameraCaptureMetaData.AeState> unmodifiableSet = Collections.unmodifiableSet(EnumSet.of(CameraCaptureMetaData.AeState.CONVERGED, CameraCaptureMetaData.AeState.FLASH_REQUIRED, CameraCaptureMetaData.AeState.UNKNOWN));
        AE_CONVERGED_STATE_SET = unmodifiableSet;
        EnumSet<CameraCaptureMetaData.AeState> copyOf = EnumSet.copyOf(unmodifiableSet);
        copyOf.remove(CameraCaptureMetaData.AeState.FLASH_REQUIRED);
        copyOf.remove(CameraCaptureMetaData.AeState.UNKNOWN);
        AE_TORCH_AS_FLASH_CONVERGED_STATE_SET = Collections.unmodifiableSet(copyOf);
    }

    Camera2CapturePipeline(Camera2CameraControlImpl camera2CameraControlImpl, CameraCharacteristicsCompat cameraCharacteristicsCompat, Quirks quirks, Executor executor) {
        boolean z = true;
        this.mCameraControl = camera2CameraControlImpl;
        Integer num = (Integer) cameraCharacteristicsCompat.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
        this.mIsLegacyDevice = (num == null || num.intValue() != 2) ? false : z;
        this.mExecutor = executor;
        this.mCameraQuirk = quirks;
        this.mUseTorchAsFlash = new UseTorchAsFlash(quirks);
    }

    public void setTemplate(int i) {
        this.mTemplate = i;
    }

    public ListenableFuture<List<Void>> submitStillCaptures(List<CaptureConfig> list, int i, int i2, int i3) {
        OverrideAeModeForStillCapture overrideAeModeForStillCapture = new OverrideAeModeForStillCapture(this.mCameraQuirk);
        Pipeline pipeline = new Pipeline(this.mTemplate, this.mExecutor, this.mCameraControl, this.mIsLegacyDevice, overrideAeModeForStillCapture);
        if (i == 0) {
            pipeline.addTask(new AfTask(this.mCameraControl));
        }
        if (isTorchAsFlash(i3)) {
            pipeline.addTask(new TorchTask(this.mCameraControl, i2, this.mExecutor));
        } else {
            pipeline.addTask(new AePreCaptureTask(this.mCameraControl, i2, overrideAeModeForStillCapture));
        }
        return Futures.nonCancellationPropagating(pipeline.executeCapture(list, i2));
    }

    static class Pipeline {
        private static final long CHECK_3A_TIMEOUT_IN_NS = TimeUnit.SECONDS.toNanos(1);
        private static final long CHECK_3A_WITH_FLASH_TIMEOUT_IN_NS = TimeUnit.SECONDS.toNanos(5);
        private final Camera2CameraControlImpl mCameraControl;
        private final Executor mExecutor;
        private final boolean mIsLegacyDevice;
        private final OverrideAeModeForStillCapture mOverrideAeModeForStillCapture;
        private final PipelineTask mPipelineSubTask = new PipelineTask() {
            public ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult) {
                ArrayList arrayList = new ArrayList();
                for (PipelineTask preCapture : Pipeline.this.mTasks) {
                    arrayList.add(preCapture.preCapture(totalCaptureResult));
                }
                return Futures.transform(Futures.allAsList(arrayList), new Camera2CapturePipeline$Pipeline$1$$ExternalSyntheticLambda0(), CameraXExecutors.directExecutor());
            }

            public boolean isCaptureResultNeeded() {
                for (PipelineTask isCaptureResultNeeded : Pipeline.this.mTasks) {
                    if (isCaptureResultNeeded.isCaptureResultNeeded()) {
                        return true;
                    }
                }
                return false;
            }

            public void postCapture() {
                for (PipelineTask postCapture : Pipeline.this.mTasks) {
                    postCapture.postCapture();
                }
            }
        };
        final List<PipelineTask> mTasks = new ArrayList();
        private final int mTemplate;
        private long mTimeout3A = CHECK_3A_TIMEOUT_IN_NS;

        Pipeline(int i, Executor executor, Camera2CameraControlImpl camera2CameraControlImpl, boolean z, OverrideAeModeForStillCapture overrideAeModeForStillCapture) {
            this.mTemplate = i;
            this.mExecutor = executor;
            this.mCameraControl = camera2CameraControlImpl;
            this.mIsLegacyDevice = z;
            this.mOverrideAeModeForStillCapture = overrideAeModeForStillCapture;
        }

        /* access modifiers changed from: package-private */
        public void addTask(PipelineTask pipelineTask) {
            this.mTasks.add(pipelineTask);
        }

        private void setTimeout3A(long j) {
            this.mTimeout3A = j;
        }

        /* access modifiers changed from: package-private */
        public ListenableFuture<List<Void>> executeCapture(List<CaptureConfig> list, int i) {
            ListenableFuture listenableFuture;
            ListenableFuture immediateFuture = Futures.immediateFuture(null);
            if (!this.mTasks.isEmpty()) {
                if (this.mPipelineSubTask.isCaptureResultNeeded()) {
                    listenableFuture = Camera2CapturePipeline.waitForResult(0, this.mCameraControl, (ResultListener.Checker) null);
                } else {
                    listenableFuture = Futures.immediateFuture(null);
                }
                immediateFuture = FutureChain.from(listenableFuture).transformAsync(new Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda2(this, i), this.mExecutor).transformAsync(new Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda3(this), this.mExecutor);
            }
            FutureChain transformAsync = FutureChain.from(immediateFuture).transformAsync(new Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda4(this, list, i), this.mExecutor);
            PipelineTask pipelineTask = this.mPipelineSubTask;
            Objects.requireNonNull(pipelineTask);
            transformAsync.addListener(new Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda5(pipelineTask), this.mExecutor);
            return transformAsync;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$executeCapture$0$androidx-camera-camera2-internal-Camera2CapturePipeline$Pipeline  reason: not valid java name */
        public /* synthetic */ ListenableFuture m29lambda$executeCapture$0$androidxcameracamera2internalCamera2CapturePipeline$Pipeline(int i, TotalCaptureResult totalCaptureResult) throws Exception {
            if (Camera2CapturePipeline.isFlashRequired(i, totalCaptureResult)) {
                setTimeout3A(CHECK_3A_WITH_FLASH_TIMEOUT_IN_NS);
            }
            return this.mPipelineSubTask.preCapture(totalCaptureResult);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$executeCapture$2$androidx-camera-camera2-internal-Camera2CapturePipeline$Pipeline  reason: not valid java name */
        public /* synthetic */ ListenableFuture m30lambda$executeCapture$2$androidxcameracamera2internalCamera2CapturePipeline$Pipeline(Boolean bool) throws Exception {
            if (Boolean.TRUE.equals(bool)) {
                return Camera2CapturePipeline.waitForResult(this.mTimeout3A, this.mCameraControl, new Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda1());
            }
            return Futures.immediateFuture(null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$executeCapture$3$androidx-camera-camera2-internal-Camera2CapturePipeline$Pipeline  reason: not valid java name */
        public /* synthetic */ ListenableFuture m31lambda$executeCapture$3$androidxcameracamera2internalCamera2CapturePipeline$Pipeline(List list, int i, TotalCaptureResult totalCaptureResult) throws Exception {
            return submitConfigsInternal(list, i);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0066  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x006a  */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0075  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0078 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.common.util.concurrent.ListenableFuture<java.util.List<java.lang.Void>> submitConfigsInternal(java.util.List<androidx.camera.core.impl.CaptureConfig> r7, int r8) {
            /*
                r6 = this;
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Iterator r7 = r7.iterator()
            L_0x000e:
                boolean r2 = r7.hasNext()
                if (r2 == 0) goto L_0x008c
                java.lang.Object r2 = r7.next()
                androidx.camera.core.impl.CaptureConfig r2 = (androidx.camera.core.impl.CaptureConfig) r2
                androidx.camera.core.impl.CaptureConfig$Builder r3 = androidx.camera.core.impl.CaptureConfig.Builder.from(r2)
                int r4 = r2.getTemplateType()
                r5 = 5
                if (r4 != r5) goto L_0x0063
                androidx.camera.camera2.internal.Camera2CameraControlImpl r4 = r6.mCameraControl
                androidx.camera.camera2.internal.ZslControl r4 = r4.getZslControl()
                boolean r4 = r4.isZslDisabledByFlashMode()
                if (r4 != 0) goto L_0x0063
                androidx.camera.camera2.internal.Camera2CameraControlImpl r4 = r6.mCameraControl
                androidx.camera.camera2.internal.ZslControl r4 = r4.getZslControl()
                boolean r4 = r4.isZslDisabledByUserCaseConfig()
                if (r4 != 0) goto L_0x0063
                androidx.camera.camera2.internal.Camera2CameraControlImpl r4 = r6.mCameraControl
                androidx.camera.camera2.internal.ZslControl r4 = r4.getZslControl()
                androidx.camera.core.ImageProxy r4 = r4.dequeueImageFromBuffer()
                if (r4 == 0) goto L_0x0057
                androidx.camera.camera2.internal.Camera2CameraControlImpl r5 = r6.mCameraControl
                androidx.camera.camera2.internal.ZslControl r5 = r5.getZslControl()
                boolean r5 = r5.enqueueImageToImageWriter(r4)
                if (r5 == 0) goto L_0x0057
                r5 = 1
                goto L_0x0058
            L_0x0057:
                r5 = 0
            L_0x0058:
                if (r5 == 0) goto L_0x0063
                androidx.camera.core.ImageInfo r4 = r4.getImageInfo()
                androidx.camera.core.impl.CameraCaptureResult r4 = androidx.camera.core.impl.CameraCaptureResults.retrieveCameraCaptureResult(r4)
                goto L_0x0064
            L_0x0063:
                r4 = 0
            L_0x0064:
                if (r4 == 0) goto L_0x006a
                r3.setCameraCaptureResult(r4)
                goto L_0x006d
            L_0x006a:
                r6.applyStillCaptureTemplate(r3, r2)
            L_0x006d:
                androidx.camera.camera2.internal.compat.workaround.OverrideAeModeForStillCapture r2 = r6.mOverrideAeModeForStillCapture
                boolean r2 = r2.shouldSetAeModeAlwaysFlash(r8)
                if (r2 == 0) goto L_0x0078
                r6.applyAeModeQuirk(r3)
            L_0x0078:
                androidx.camera.camera2.internal.Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda0 r2 = new androidx.camera.camera2.internal.Camera2CapturePipeline$Pipeline$$ExternalSyntheticLambda0
                r2.<init>(r6, r3)
                com.google.common.util.concurrent.ListenableFuture r2 = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(r2)
                r0.add(r2)
                androidx.camera.core.impl.CaptureConfig r2 = r3.build()
                r1.add(r2)
                goto L_0x000e
            L_0x008c:
                androidx.camera.camera2.internal.Camera2CameraControlImpl r6 = r6.mCameraControl
                r6.submitCaptureRequestsInternal(r1)
                com.google.common.util.concurrent.ListenableFuture r6 = androidx.camera.core.impl.utils.futures.Futures.allAsList(r0)
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.Camera2CapturePipeline.Pipeline.submitConfigsInternal(java.util.List, int):com.google.common.util.concurrent.ListenableFuture");
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$submitConfigsInternal$4$androidx-camera-camera2-internal-Camera2CapturePipeline$Pipeline  reason: not valid java name */
        public /* synthetic */ Object m32lambda$submitConfigsInternal$4$androidxcameracamera2internalCamera2CapturePipeline$Pipeline(CaptureConfig.Builder builder, final CallbackToFutureAdapter.Completer completer) throws Exception {
            builder.addCameraCaptureCallback(new CameraCaptureCallback() {
                public void onCaptureCompleted(CameraCaptureResult cameraCaptureResult) {
                    completer.set(null);
                }

                public void onCaptureFailed(CameraCaptureFailure cameraCaptureFailure) {
                    completer.setException(new ImageCaptureException(2, "Capture request failed with reason " + cameraCaptureFailure.getReason(), (Throwable) null));
                }

                public void onCaptureCancelled() {
                    completer.setException(new ImageCaptureException(3, "Capture request is cancelled because camera is closed", (Throwable) null));
                }
            });
            return "submitStillCapture";
        }

        private void applyStillCaptureTemplate(CaptureConfig.Builder builder, CaptureConfig captureConfig) {
            int i;
            if (this.mTemplate != 3 || this.mIsLegacyDevice) {
                i = (captureConfig.getTemplateType() == -1 || captureConfig.getTemplateType() == 5) ? 2 : -1;
            } else {
                i = 4;
            }
            if (i != -1) {
                builder.setTemplateType(i);
            }
        }

        private void applyAeModeQuirk(CaptureConfig.Builder builder) {
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, 3);
            builder.addImplementationOptions(builder2.build());
        }
    }

    static ListenableFuture<TotalCaptureResult> waitForResult(long j, Camera2CameraControlImpl camera2CameraControlImpl, ResultListener.Checker checker) {
        ResultListener resultListener = new ResultListener(j, checker);
        camera2CameraControlImpl.addCaptureResultListener(resultListener);
        return resultListener.getFuture();
    }

    /* access modifiers changed from: package-private */
    public static boolean is3AConverged(TotalCaptureResult totalCaptureResult, boolean z) {
        if (totalCaptureResult == null) {
            return false;
        }
        Camera2CameraCaptureResult camera2CameraCaptureResult = new Camera2CameraCaptureResult(totalCaptureResult);
        boolean z2 = camera2CameraCaptureResult.getAfMode() == CameraCaptureMetaData.AfMode.OFF || camera2CameraCaptureResult.getAfMode() == CameraCaptureMetaData.AfMode.UNKNOWN || AF_CONVERGED_STATE_SET.contains(camera2CameraCaptureResult.getAfState());
        boolean z3 = ((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_MODE)).intValue() == 0;
        boolean z4 = !z ? z3 || AE_CONVERGED_STATE_SET.contains(camera2CameraCaptureResult.getAeState()) : z3 || AE_TORCH_AS_FLASH_CONVERGED_STATE_SET.contains(camera2CameraCaptureResult.getAeState());
        boolean z5 = (((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AWB_MODE)).intValue() == 0) || AWB_CONVERGED_STATE_SET.contains(camera2CameraCaptureResult.getAwbState());
        Logger.d(TAG, "checkCaptureResult, AE=" + camera2CameraCaptureResult.getAeState() + " AF =" + camera2CameraCaptureResult.getAfState() + " AWB=" + camera2CameraCaptureResult.getAwbState());
        if (!z2 || !z4 || !z5) {
            return false;
        }
        return true;
    }

    static class AfTask implements PipelineTask {
        private final Camera2CameraControlImpl mCameraControl;
        private boolean mIsExecuted = false;

        public boolean isCaptureResultNeeded() {
            return true;
        }

        AfTask(Camera2CameraControlImpl camera2CameraControlImpl) {
            this.mCameraControl = camera2CameraControlImpl;
        }

        public ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult) {
            Integer num;
            ListenableFuture<Boolean> immediateFuture = Futures.immediateFuture(true);
            if (totalCaptureResult == null || (num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_MODE)) == null) {
                return immediateFuture;
            }
            int intValue = num.intValue();
            if (intValue == 1 || intValue == 2) {
                Logger.d(Camera2CapturePipeline.TAG, "TriggerAf? AF mode auto");
                Integer num2 = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
                if (num2 != null && num2.intValue() == 0) {
                    Logger.d(Camera2CapturePipeline.TAG, "Trigger AF");
                    this.mIsExecuted = true;
                    this.mCameraControl.getFocusMeteringControl().triggerAf((CallbackToFutureAdapter.Completer<CameraCaptureResult>) null, false);
                }
            }
            return immediateFuture;
        }

        public void postCapture() {
            if (this.mIsExecuted) {
                Logger.d(Camera2CapturePipeline.TAG, "cancel TriggerAF");
                this.mCameraControl.getFocusMeteringControl().cancelAfAeTrigger(true, false);
            }
        }
    }

    static class TorchTask implements PipelineTask {
        private static final long CHECK_3A_WITH_TORCH_TIMEOUT_IN_NS = TimeUnit.SECONDS.toNanos(2);
        private final Camera2CameraControlImpl mCameraControl;
        private final Executor mExecutor;
        private final int mFlashMode;
        private boolean mIsExecuted = false;

        TorchTask(Camera2CameraControlImpl camera2CameraControlImpl, int i, Executor executor) {
            this.mCameraControl = camera2CameraControlImpl;
            this.mFlashMode = i;
            this.mExecutor = executor;
        }

        public ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult) {
            if (Camera2CapturePipeline.isFlashRequired(this.mFlashMode, totalCaptureResult)) {
                if (this.mCameraControl.isTorchOn()) {
                    Logger.d(Camera2CapturePipeline.TAG, "Torch already on, not turn on");
                } else {
                    Logger.d(Camera2CapturePipeline.TAG, "Turn on torch");
                    this.mIsExecuted = true;
                    return FutureChain.from(CallbackToFutureAdapter.getFuture(new Camera2CapturePipeline$TorchTask$$ExternalSyntheticLambda1(this))).transformAsync(new Camera2CapturePipeline$TorchTask$$ExternalSyntheticLambda2(this), this.mExecutor).transform(new Camera2CapturePipeline$TorchTask$$ExternalSyntheticLambda3(), CameraXExecutors.directExecutor());
                }
            }
            return Futures.immediateFuture(false);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$0$androidx-camera-camera2-internal-Camera2CapturePipeline$TorchTask  reason: not valid java name */
        public /* synthetic */ Object m34lambda$preCapture$0$androidxcameracamera2internalCamera2CapturePipeline$TorchTask(CallbackToFutureAdapter.Completer completer) throws Exception {
            this.mCameraControl.getTorchControl().m65lambda$enableTorch$1$androidxcameracamera2internalTorchControl(completer, true);
            return "TorchOn";
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$2$androidx-camera-camera2-internal-Camera2CapturePipeline$TorchTask  reason: not valid java name */
        public /* synthetic */ ListenableFuture m35lambda$preCapture$2$androidxcameracamera2internalCamera2CapturePipeline$TorchTask(Void voidR) throws Exception {
            return Camera2CapturePipeline.waitForResult(CHECK_3A_WITH_TORCH_TIMEOUT_IN_NS, this.mCameraControl, new Camera2CapturePipeline$TorchTask$$ExternalSyntheticLambda0());
        }

        static /* synthetic */ Boolean lambda$preCapture$3(TotalCaptureResult totalCaptureResult) {
            return false;
        }

        public boolean isCaptureResultNeeded() {
            return this.mFlashMode == 0;
        }

        public void postCapture() {
            if (this.mIsExecuted) {
                this.mCameraControl.getTorchControl().m65lambda$enableTorch$1$androidxcameracamera2internalTorchControl((CallbackToFutureAdapter.Completer<Void>) null, false);
                Logger.d(Camera2CapturePipeline.TAG, "Turn off torch");
            }
        }
    }

    static class AePreCaptureTask implements PipelineTask {
        private final Camera2CameraControlImpl mCameraControl;
        private final int mFlashMode;
        private boolean mIsExecuted = false;
        private final OverrideAeModeForStillCapture mOverrideAeModeForStillCapture;

        AePreCaptureTask(Camera2CameraControlImpl camera2CameraControlImpl, int i, OverrideAeModeForStillCapture overrideAeModeForStillCapture) {
            this.mCameraControl = camera2CameraControlImpl;
            this.mFlashMode = i;
            this.mOverrideAeModeForStillCapture = overrideAeModeForStillCapture;
        }

        public ListenableFuture<Boolean> preCapture(TotalCaptureResult totalCaptureResult) {
            if (!Camera2CapturePipeline.isFlashRequired(this.mFlashMode, totalCaptureResult)) {
                return Futures.immediateFuture(false);
            }
            Logger.d(Camera2CapturePipeline.TAG, "Trigger AE");
            this.mIsExecuted = true;
            return FutureChain.from(CallbackToFutureAdapter.getFuture(new Camera2CapturePipeline$AePreCaptureTask$$ExternalSyntheticLambda0(this))).transform(new Camera2CapturePipeline$AePreCaptureTask$$ExternalSyntheticLambda1(), CameraXExecutors.directExecutor());
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$preCapture$0$androidx-camera-camera2-internal-Camera2CapturePipeline$AePreCaptureTask  reason: not valid java name */
        public /* synthetic */ Object m28lambda$preCapture$0$androidxcameracamera2internalCamera2CapturePipeline$AePreCaptureTask(CallbackToFutureAdapter.Completer completer) throws Exception {
            this.mCameraControl.getFocusMeteringControl().triggerAePrecapture(completer);
            this.mOverrideAeModeForStillCapture.onAePrecaptureStarted();
            return "AePreCapture";
        }

        static /* synthetic */ Boolean lambda$preCapture$1(Void voidR) {
            return true;
        }

        public boolean isCaptureResultNeeded() {
            return this.mFlashMode == 0;
        }

        public void postCapture() {
            if (this.mIsExecuted) {
                Logger.d(Camera2CapturePipeline.TAG, "cancel TriggerAePreCapture");
                this.mCameraControl.getFocusMeteringControl().cancelAfAeTrigger(false, true);
                this.mOverrideAeModeForStillCapture.onAePrecaptureFinished();
            }
        }
    }

    static boolean isFlashRequired(int i, TotalCaptureResult totalCaptureResult) {
        if (i == 0) {
            Integer num = totalCaptureResult != null ? (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE) : null;
            if (num == null || num.intValue() != 4) {
                return false;
            }
            return true;
        } else if (i == 1) {
            return true;
        } else {
            if (i == 2) {
                return false;
            }
            throw new AssertionError(i);
        }
    }

    static class ResultListener implements Camera2CameraControlImpl.CaptureResultListener {
        static final long NO_TIMEOUT = 0;
        private final Checker mChecker;
        private CallbackToFutureAdapter.Completer<TotalCaptureResult> mCompleter;
        private final ListenableFuture<TotalCaptureResult> mFuture = CallbackToFutureAdapter.getFuture(new Camera2CapturePipeline$ResultListener$$ExternalSyntheticLambda0(this));
        private final long mTimeLimitNs;
        private volatile Long mTimestampOfFirstUpdateNs = null;

        interface Checker {
            boolean check(TotalCaptureResult totalCaptureResult);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$androidx-camera-camera2-internal-Camera2CapturePipeline$ResultListener  reason: not valid java name */
        public /* synthetic */ Object m33lambda$new$0$androidxcameracamera2internalCamera2CapturePipeline$ResultListener(CallbackToFutureAdapter.Completer completer) throws Exception {
            this.mCompleter = completer;
            return "waitFor3AResult";
        }

        ResultListener(long j, Checker checker) {
            this.mTimeLimitNs = j;
            this.mChecker = checker;
        }

        public ListenableFuture<TotalCaptureResult> getFuture() {
            return this.mFuture;
        }

        public boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
            Long l = (Long) totalCaptureResult.get(CaptureResult.SENSOR_TIMESTAMP);
            if (l != null && this.mTimestampOfFirstUpdateNs == null) {
                this.mTimestampOfFirstUpdateNs = l;
            }
            Long l2 = this.mTimestampOfFirstUpdateNs;
            if (0 == this.mTimeLimitNs || l2 == null || l == null || l.longValue() - l2.longValue() <= this.mTimeLimitNs) {
                Checker checker = this.mChecker;
                if (checker != null && !checker.check(totalCaptureResult)) {
                    return false;
                }
                this.mCompleter.set(totalCaptureResult);
                return true;
            }
            this.mCompleter.set(null);
            Logger.d(Camera2CapturePipeline.TAG, "Wait for capture result timeout, current:" + l + " first: " + l2);
            return true;
        }
    }

    private boolean isTorchAsFlash(int i) {
        return this.mUseTorchAsFlash.shouldUseTorchAsFlash() || this.mTemplate == 3 || i == 1;
    }
}
