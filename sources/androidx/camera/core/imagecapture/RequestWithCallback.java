package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.utils.Threads;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;

class RequestWithCallback implements TakePictureCallback {
    private CallbackToFutureAdapter.Completer<Void> mCaptureCompleter;
    private final ListenableFuture<Void> mCaptureFuture;
    private boolean mIsAborted = false;
    private boolean mIsComplete = false;
    private final TakePictureRequest mTakePictureRequest;

    RequestWithCallback(TakePictureRequest takePictureRequest) {
        this.mTakePictureRequest = takePictureRequest;
        this.mCaptureFuture = CallbackToFutureAdapter.getFuture(new RequestWithCallback$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-core-imagecapture-RequestWithCallback  reason: not valid java name */
    public /* synthetic */ Object m150lambda$new$0$androidxcameracoreimagecaptureRequestWithCallback(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mCaptureCompleter = completer;
        return "CaptureCompleteFuture";
    }

    public void onImageCaptured() {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            this.mCaptureCompleter.set(null);
        }
    }

    public void onFinalResult(ImageCapture.OutputFileResults outputFileResults) {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            checkOnImageCaptured();
            markComplete();
            this.mTakePictureRequest.onResult(outputFileResults);
        }
    }

    public void onFinalResult(ImageProxy imageProxy) {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            checkOnImageCaptured();
            markComplete();
            this.mTakePictureRequest.onResult(imageProxy);
        }
    }

    public void onProcessFailure(ImageCaptureException imageCaptureException) {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            checkOnImageCaptured();
            markComplete();
            onFailure(imageCaptureException);
        }
    }

    public boolean isAborted() {
        return this.mIsAborted;
    }

    public void onCaptureFailure(ImageCaptureException imageCaptureException) {
        Threads.checkMainThread();
        if (!this.mIsAborted) {
            markComplete();
            this.mCaptureCompleter.set(null);
            onFailure(imageCaptureException);
        }
    }

    /* access modifiers changed from: package-private */
    public void abort(ImageCaptureException imageCaptureException) {
        Threads.checkMainThread();
        this.mIsAborted = true;
        this.mCaptureCompleter.set(null);
        onFailure(imageCaptureException);
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> getCaptureFuture() {
        Threads.checkMainThread();
        return this.mCaptureFuture;
    }

    private void checkOnImageCaptured() {
        Preconditions.checkState(this.mCaptureFuture.isDone(), "onImageCaptured() must be called before onFinalResult()");
    }

    private void markComplete() {
        Preconditions.checkState(!this.mIsComplete, "The callback can only complete once.");
        this.mIsComplete = true;
    }

    private void onFailure(ImageCaptureException imageCaptureException) {
        Threads.checkMainThread();
        this.mTakePictureRequest.onError(imageCaptureException);
    }
}
