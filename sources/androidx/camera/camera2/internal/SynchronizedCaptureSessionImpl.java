package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.camera2.internal.compat.workaround.ForceCloseCaptureSession;
import androidx.camera.camera2.internal.compat.workaround.ForceCloseDeferrableSurface;
import androidx.camera.camera2.internal.compat.workaround.WaitForRepeatingRequestStart;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

class SynchronizedCaptureSessionImpl extends SynchronizedCaptureSessionBaseImpl {
    private static final String TAG = "SyncCaptureSessionImpl";
    private final ForceCloseDeferrableSurface mCloseSurfaceQuirk;
    private List<DeferrableSurface> mDeferrableSurfaces;
    private final ForceCloseCaptureSession mForceCloseSessionQuirk;
    private final Object mObjectLock = new Object();
    ListenableFuture<Void> mOpeningCaptureSession;
    private final WaitForRepeatingRequestStart mWaitForOtherSessionCompleteQuirk;

    SynchronizedCaptureSessionImpl(Quirks quirks, Quirks quirks2, CaptureSessionRepository captureSessionRepository, Executor executor, ScheduledExecutorService scheduledExecutorService, Handler handler) {
        super(captureSessionRepository, executor, scheduledExecutorService, handler);
        this.mCloseSurfaceQuirk = new ForceCloseDeferrableSurface(quirks, quirks2);
        this.mWaitForOtherSessionCompleteQuirk = new WaitForRepeatingRequestStart(quirks);
        this.mForceCloseSessionQuirk = new ForceCloseCaptureSession(quirks2);
    }

    public ListenableFuture<Void> openCaptureSession(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List<DeferrableSurface> list) {
        ListenableFuture<Void> nonCancellationPropagating;
        synchronized (this.mObjectLock) {
            ListenableFuture<Void> openCaptureSession = this.mWaitForOtherSessionCompleteQuirk.openCaptureSession(cameraDevice, sessionConfigurationCompat, list, this.mCaptureSessionRepository.getClosingCaptureSession(), new SynchronizedCaptureSessionImpl$$ExternalSyntheticLambda1(this));
            this.mOpeningCaptureSession = openCaptureSession;
            nonCancellationPropagating = Futures.nonCancellationPropagating(openCaptureSession);
        }
        return nonCancellationPropagating;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$openCaptureSession$0$androidx-camera-camera2-internal-SynchronizedCaptureSessionImpl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m63lambda$openCaptureSession$0$androidxcameracamera2internalSynchronizedCaptureSessionImpl(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List list) {
        return super.openCaptureSession(cameraDevice, sessionConfigurationCompat, list);
    }

    public ListenableFuture<Void> getOpeningBlocker() {
        return this.mWaitForOtherSessionCompleteQuirk.getStartStreamFuture();
    }

    public ListenableFuture<List<Surface>> startWithDeferrableSurface(List<DeferrableSurface> list, long j) {
        ListenableFuture<List<Surface>> startWithDeferrableSurface;
        synchronized (this.mObjectLock) {
            this.mDeferrableSurfaces = list;
            startWithDeferrableSurface = super.startWithDeferrableSurface(list, j);
        }
        return startWithDeferrableSurface;
    }

    public boolean stop() {
        boolean stop;
        synchronized (this.mObjectLock) {
            if (isCameraCaptureSessionOpen()) {
                this.mCloseSurfaceQuirk.onSessionEnd(this.mDeferrableSurfaces);
            } else {
                ListenableFuture<Void> listenableFuture = this.mOpeningCaptureSession;
                if (listenableFuture != null) {
                    listenableFuture.cancel(true);
                }
            }
            stop = super.stop();
        }
        return stop;
    }

    public int setSingleRepeatingRequest(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return this.mWaitForOtherSessionCompleteQuirk.setSingleRepeatingRequest(captureRequest, captureCallback, new SynchronizedCaptureSessionImpl$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setSingleRepeatingRequest$1$androidx-camera-camera2-internal-SynchronizedCaptureSessionImpl  reason: not valid java name */
    public /* synthetic */ int m64lambda$setSingleRepeatingRequest$1$androidxcameracamera2internalSynchronizedCaptureSessionImpl(CaptureRequest captureRequest, CameraCaptureSession.CaptureCallback captureCallback) throws CameraAccessException {
        return super.setSingleRepeatingRequest(captureRequest, captureCallback);
    }

    public void onConfigured(SynchronizedCaptureSession synchronizedCaptureSession) {
        debugLog("Session onConfigured()");
        this.mForceCloseSessionQuirk.onSessionConfigured(synchronizedCaptureSession, this.mCaptureSessionRepository.getCreatingCaptureSessions(), this.mCaptureSessionRepository.getCaptureSessions(), new SynchronizedCaptureSessionImpl$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onConfigured$2$androidx-camera-camera2-internal-SynchronizedCaptureSessionImpl  reason: not valid java name */
    public /* synthetic */ void m62lambda$onConfigured$2$androidxcameracamera2internalSynchronizedCaptureSessionImpl(SynchronizedCaptureSession synchronizedCaptureSession) {
        super.onConfigured(synchronizedCaptureSession);
    }

    public void close() {
        debugLog("Session call close()");
        this.mWaitForOtherSessionCompleteQuirk.onSessionEnd();
        this.mWaitForOtherSessionCompleteQuirk.getStartStreamFuture().addListener(new SynchronizedCaptureSessionImpl$$ExternalSyntheticLambda0(this), getExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$close$3$androidx-camera-camera2-internal-SynchronizedCaptureSessionImpl  reason: not valid java name */
    public /* synthetic */ void m61lambda$close$3$androidxcameracamera2internalSynchronizedCaptureSessionImpl() {
        debugLog("Session call super.close()");
        super.close();
    }

    public void onClosed(SynchronizedCaptureSession synchronizedCaptureSession) {
        synchronized (this.mObjectLock) {
            this.mCloseSurfaceQuirk.onSessionEnd(this.mDeferrableSurfaces);
        }
        debugLog("onClosed()");
        super.onClosed(synchronizedCaptureSession);
    }

    /* access modifiers changed from: package-private */
    public void debugLog(String str) {
        Logger.d(TAG, "[" + this + "] " + str);
    }
}
