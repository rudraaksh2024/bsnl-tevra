package androidx.camera.core;

import android.media.ImageReader;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.ImageProxyBundle;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

class CaptureProcessorPipeline implements CaptureProcessor {
    private static final String TAG = "CaptureProcessorPipeline";
    CallbackToFutureAdapter.Completer<Void> mCloseCompleter;
    private ListenableFuture<Void> mCloseFuture;
    private boolean mClosed = false;
    final Executor mExecutor;
    private ImageReaderProxy mIntermediateImageReader = null;
    private final Object mLock = new Object();
    private final int mMaxImages;
    private final CaptureProcessor mPostCaptureProcessor;
    private final CaptureProcessor mPreCaptureProcessor;
    private boolean mProcessing = false;
    private ImageInfo mSourceImageInfo = null;
    private final ListenableFuture<List<Void>> mUnderlyingCaptureProcessorsCloseFuture;

    static /* synthetic */ Void lambda$getCloseFuture$3(List list) {
        return null;
    }

    CaptureProcessorPipeline(CaptureProcessor captureProcessor, int i, CaptureProcessor captureProcessor2, Executor executor) {
        this.mPreCaptureProcessor = captureProcessor;
        this.mPostCaptureProcessor = captureProcessor2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(captureProcessor.getCloseFuture());
        arrayList.add(captureProcessor2.getCloseFuture());
        this.mUnderlyingCaptureProcessorsCloseFuture = Futures.allAsList(arrayList);
        this.mExecutor = executor;
        this.mMaxImages = i;
    }

    public void onOutputSurface(Surface surface, int i) {
        this.mPostCaptureProcessor.onOutputSurface(surface, i);
    }

    public void process(ImageProxyBundle imageProxyBundle) {
        synchronized (this.mLock) {
            if (!this.mClosed) {
                this.mProcessing = true;
                ListenableFuture<ImageProxy> imageProxy = imageProxyBundle.getImageProxy(imageProxyBundle.getCaptureIds().get(0).intValue());
                Preconditions.checkArgument(imageProxy.isDone());
                try {
                    this.mSourceImageInfo = ((ImageProxy) imageProxy.get()).getImageInfo();
                    this.mPreCaptureProcessor.process(imageProxyBundle);
                } catch (InterruptedException | ExecutionException unused) {
                    throw new IllegalArgumentException("Can not successfully extract ImageProxy from the ImageProxyBundle.");
                }
            }
        }
    }

    public void onResolutionUpdate(Size size) {
        AndroidImageReaderProxy androidImageReaderProxy = new AndroidImageReaderProxy(ImageReader.newInstance(size.getWidth(), size.getHeight(), 35, this.mMaxImages));
        this.mIntermediateImageReader = androidImageReaderProxy;
        this.mPreCaptureProcessor.onOutputSurface(androidImageReaderProxy.getSurface(), 35);
        this.mPreCaptureProcessor.onResolutionUpdate(size);
        this.mPostCaptureProcessor.onResolutionUpdate(size);
        this.mIntermediateImageReader.setOnImageAvailableListener(new CaptureProcessorPipeline$$ExternalSyntheticLambda0(this), CameraXExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onResolutionUpdate$1$androidx-camera-core-CaptureProcessorPipeline  reason: not valid java name */
    public /* synthetic */ void m112lambda$onResolutionUpdate$1$androidxcameracoreCaptureProcessorPipeline(ImageReaderProxy imageReaderProxy) {
        ImageProxy acquireNextImage = imageReaderProxy.acquireNextImage();
        try {
            this.mExecutor.execute(new CaptureProcessorPipeline$$ExternalSyntheticLambda3(this, acquireNextImage));
        } catch (RejectedExecutionException unused) {
            Logger.e(TAG, "The executor for post-processing might have been shutting down or terminated!");
            acquireNextImage.close();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: postProcess */
    public void m111lambda$onResolutionUpdate$0$androidxcameracoreCaptureProcessorPipeline(ImageProxy imageProxy) {
        boolean z;
        synchronized (this.mLock) {
            z = this.mClosed;
        }
        if (!z) {
            Size size = new Size(imageProxy.getWidth(), imageProxy.getHeight());
            Preconditions.checkNotNull(this.mSourceImageInfo);
            String next = this.mSourceImageInfo.getTagBundle().listKeys().iterator().next();
            int intValue = ((Integer) this.mSourceImageInfo.getTagBundle().getTag(next)).intValue();
            SettableImageProxy settableImageProxy = new SettableImageProxy(imageProxy, size, this.mSourceImageInfo);
            this.mSourceImageInfo = null;
            SettableImageProxyBundle settableImageProxyBundle = new SettableImageProxyBundle(Collections.singletonList(Integer.valueOf(intValue)), next);
            settableImageProxyBundle.addImageProxy(settableImageProxy);
            try {
                this.mPostCaptureProcessor.process(settableImageProxyBundle);
            } catch (Exception e) {
                Logger.e(TAG, "Post processing image failed! " + e.getMessage());
            }
        }
        synchronized (this.mLock) {
            this.mProcessing = false;
        }
        closeAndCompleteFutureIfNecessary();
    }

    public void close() {
        synchronized (this.mLock) {
            if (!this.mClosed) {
                this.mClosed = true;
                this.mPreCaptureProcessor.close();
                this.mPostCaptureProcessor.close();
                closeAndCompleteFutureIfNecessary();
            }
        }
    }

    private void closeAndCompleteFutureIfNecessary() {
        boolean z;
        boolean z2;
        CallbackToFutureAdapter.Completer<Void> completer;
        synchronized (this.mLock) {
            z = this.mClosed;
            z2 = this.mProcessing;
            completer = this.mCloseCompleter;
            if (z && !z2) {
                this.mIntermediateImageReader.close();
            }
        }
        if (z && !z2 && completer != null) {
            this.mUnderlyingCaptureProcessorsCloseFuture.addListener(new CaptureProcessorPipeline$$ExternalSyntheticLambda4(completer), CameraXExecutors.directExecutor());
        }
    }

    public ListenableFuture<Void> getCloseFuture() {
        ListenableFuture<Void> listenableFuture;
        synchronized (this.mLock) {
            if (!this.mClosed || this.mProcessing) {
                if (this.mCloseFuture == null) {
                    this.mCloseFuture = CallbackToFutureAdapter.getFuture(new CaptureProcessorPipeline$$ExternalSyntheticLambda2(this));
                }
                listenableFuture = Futures.nonCancellationPropagating(this.mCloseFuture);
            } else {
                listenableFuture = Futures.transform(this.mUnderlyingCaptureProcessorsCloseFuture, new CaptureProcessorPipeline$$ExternalSyntheticLambda1(), CameraXExecutors.directExecutor());
            }
        }
        return listenableFuture;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getCloseFuture$4$androidx-camera-core-CaptureProcessorPipeline  reason: not valid java name */
    public /* synthetic */ Object m110lambda$getCloseFuture$4$androidxcameracoreCaptureProcessorPipeline(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.mLock) {
            this.mCloseCompleter = completer;
        }
        return "CaptureProcessorPipeline-close";
    }
}
