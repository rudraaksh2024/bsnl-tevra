package androidx.camera.core.imagecapture;

import android.util.Log;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.core.util.Pair;
import androidx.core.util.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class TakePictureManager implements ForwardingImageProxy.OnImageCloseListener {
    private static final String TAG = "TakePictureManager";
    final ImageCaptureControl mImageCaptureControl;
    final ImagePipeline mImagePipeline;
    RequestWithCallback mInFlightRequest;
    final Deque<TakePictureRequest> mNewRequests = new ArrayDeque();
    boolean mPaused = false;

    public TakePictureManager(ImageCaptureControl imageCaptureControl, ImagePipeline imagePipeline) {
        Threads.checkMainThread();
        this.mImageCaptureControl = imageCaptureControl;
        this.mImagePipeline = imagePipeline;
        imagePipeline.setOnImageCloseListener(this);
    }

    public void offerRequest(TakePictureRequest takePictureRequest) {
        Threads.checkMainThread();
        this.mNewRequests.offer(takePictureRequest);
        issueNextRequest();
    }

    public void pause() {
        Threads.checkMainThread();
        this.mPaused = true;
    }

    public void resume() {
        Threads.checkMainThread();
        this.mPaused = false;
        issueNextRequest();
    }

    public void abortRequests() {
        Threads.checkMainThread();
        ImageCaptureException imageCaptureException = new ImageCaptureException(3, "Camera is closed.", (Throwable) null);
        for (TakePictureRequest onError : this.mNewRequests) {
            onError.onError(imageCaptureException);
        }
        this.mNewRequests.clear();
        RequestWithCallback requestWithCallback = this.mInFlightRequest;
        if (requestWithCallback != null) {
            requestWithCallback.abort(imageCaptureException);
        }
    }

    /* access modifiers changed from: package-private */
    public void issueNextRequest() {
        Threads.checkMainThread();
        Log.d(TAG, "Issue the next TakePictureRequest.");
        if (hasInFlightRequest()) {
            Log.d(TAG, "There is already a request in-flight.");
        } else if (this.mPaused) {
            Log.d(TAG, "The class is paused.");
        } else if (this.mImagePipeline.getCapacity() == 0) {
            Log.d(TAG, "Too many acquire images. Close image to be able to process next.");
        } else {
            TakePictureRequest poll = this.mNewRequests.poll();
            if (poll == null) {
                Log.d(TAG, "No new request.");
                return;
            }
            RequestWithCallback requestWithCallback = new RequestWithCallback(poll);
            trackCurrentRequest(requestWithCallback);
            Pair<CameraRequest, ProcessingRequest> createRequests = this.mImagePipeline.createRequests(poll, requestWithCallback);
            submitCameraRequest((CameraRequest) Objects.requireNonNull((CameraRequest) createRequests.first), new TakePictureManager$$ExternalSyntheticLambda0(this, (ProcessingRequest) Objects.requireNonNull((ProcessingRequest) createRequests.second)));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$issueNextRequest$0$androidx-camera-core-imagecapture-TakePictureManager  reason: not valid java name */
    public /* synthetic */ void m151lambda$issueNextRequest$0$androidxcameracoreimagecaptureTakePictureManager(ProcessingRequest processingRequest) {
        this.mImagePipeline.postProcess(processingRequest);
    }

    private void trackCurrentRequest(RequestWithCallback requestWithCallback) {
        Preconditions.checkState(!hasInFlightRequest());
        this.mInFlightRequest = requestWithCallback;
        requestWithCallback.getCaptureFuture().addListener(new TakePictureManager$$ExternalSyntheticLambda1(this), CameraXExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$trackCurrentRequest$1$androidx-camera-core-imagecapture-TakePictureManager  reason: not valid java name */
    public /* synthetic */ void m152lambda$trackCurrentRequest$1$androidxcameracoreimagecaptureTakePictureManager() {
        this.mInFlightRequest = null;
        issueNextRequest();
    }

    private void submitCameraRequest(final CameraRequest cameraRequest, final Runnable runnable) {
        Threads.checkMainThread();
        this.mImageCaptureControl.lockFlashMode();
        Futures.addCallback(this.mImageCaptureControl.submitStillCaptureRequests(cameraRequest.getCaptureConfigs()), new FutureCallback<Void>() {
            public void onSuccess(Void voidR) {
                runnable.run();
                TakePictureManager.this.mImageCaptureControl.unlockFlashMode();
            }

            public void onFailure(Throwable th) {
                if (th instanceof ImageCaptureException) {
                    cameraRequest.onCaptureFailure((ImageCaptureException) th);
                } else {
                    cameraRequest.onCaptureFailure(new ImageCaptureException(2, "Failed to submit capture request", th));
                }
                TakePictureManager.this.mImageCaptureControl.unlockFlashMode();
            }
        }, CameraXExecutors.mainThreadExecutor());
    }

    /* access modifiers changed from: package-private */
    public boolean hasInFlightRequest() {
        return this.mInFlightRequest != null;
    }

    public void onImageClose(ImageProxy imageProxy) {
        CameraXExecutors.mainThreadExecutor().execute(new TakePictureManager$$ExternalSyntheticLambda2(this));
    }
}
