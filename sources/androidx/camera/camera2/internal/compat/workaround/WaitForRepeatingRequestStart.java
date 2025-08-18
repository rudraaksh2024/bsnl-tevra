package androidx.camera.camera2.internal.compat.workaround;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.internal.Camera2CaptureCallbacks;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.camera2.internal.compat.quirk.CaptureSessionStuckQuirk;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;

public class WaitForRepeatingRequestStart {
    private final CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() {
        public void onCaptureStarted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, long j, long j2) {
            if (WaitForRepeatingRequestStart.this.mStartStreamingCompleter != null) {
                WaitForRepeatingRequestStart.this.mStartStreamingCompleter.set(null);
                WaitForRepeatingRequestStart.this.mStartStreamingCompleter = null;
            }
        }

        public void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
            if (WaitForRepeatingRequestStart.this.mStartStreamingCompleter != null) {
                WaitForRepeatingRequestStart.this.mStartStreamingCompleter.setCancelled();
                WaitForRepeatingRequestStart.this.mStartStreamingCompleter = null;
            }
        }
    };
    private final boolean mHasCaptureSessionStuckQuirk;
    private boolean mHasSubmittedRepeating;
    private final Object mLock = new Object();
    CallbackToFutureAdapter.Completer<Void> mStartStreamingCompleter;
    private final ListenableFuture<Void> mStartStreamingFuture;

    @FunctionalInterface
    public interface OpenCaptureSession {
        ListenableFuture<Void> run(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List<DeferrableSurface> list);
    }

    @FunctionalInterface
    public interface SingleRepeatingRequest {
        int run(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException;
    }

    public WaitForRepeatingRequestStart(Quirks quirks) {
        this.mHasCaptureSessionStuckQuirk = quirks.contains(CaptureSessionStuckQuirk.class);
        if (shouldWaitRepeatingSubmit()) {
            this.mStartStreamingFuture = CallbackToFutureAdapter.getFuture(new WaitForRepeatingRequestStart$$ExternalSyntheticLambda0(this));
        } else {
            this.mStartStreamingFuture = Futures.immediateFuture(null);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-camera2-internal-compat-workaround-WaitForRepeatingRequestStart  reason: not valid java name */
    public /* synthetic */ Object m94lambda$new$0$androidxcameracamera2internalcompatworkaroundWaitForRepeatingRequestStart(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mStartStreamingCompleter = completer;
        return "WaitForRepeatingRequestStart[" + this + "]";
    }

    public boolean shouldWaitRepeatingSubmit() {
        return this.mHasCaptureSessionStuckQuirk;
    }

    public ListenableFuture<Void> getStartStreamFuture() {
        return Futures.nonCancellationPropagating(this.mStartStreamingFuture);
    }

    public ListenableFuture<Void> openCaptureSession(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List<DeferrableSurface> list, List<SynchronizedCaptureSession> list2, OpenCaptureSession openCaptureSession) {
        ArrayList arrayList = new ArrayList();
        for (SynchronizedCaptureSession openingBlocker : list2) {
            arrayList.add(openingBlocker.getOpeningBlocker());
        }
        return FutureChain.from(Futures.successfulAsList(arrayList)).transformAsync(new WaitForRepeatingRequestStart$$ExternalSyntheticLambda1(openCaptureSession, cameraDevice, sessionConfigurationCompat, list), CameraXExecutors.directExecutor());
    }

    public int setSingleRepeatingRequest(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback, SingleRepeatingRequest singleRepeatingRequest) throws CameraAccessException {
        int run;
        synchronized (this.mLock) {
            if (shouldWaitRepeatingSubmit()) {
                captureCallback = Camera2CaptureCallbacks.createComboCallback(this.mCaptureCallback, captureCallback);
                this.mHasSubmittedRepeating = true;
            }
            run = singleRepeatingRequest.run(captureRequest, captureCallback);
        }
        return run;
    }

    public void onSessionEnd() {
        synchronized (this.mLock) {
            if (shouldWaitRepeatingSubmit() && !this.mHasSubmittedRepeating) {
                this.mStartStreamingFuture.cancel(true);
            }
        }
    }
}
