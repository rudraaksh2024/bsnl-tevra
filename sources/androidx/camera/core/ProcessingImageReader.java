package androidx.camera.core;

import android.media.ImageReader;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class ProcessingImageReader implements ImageReaderProxy {
    private static final int EXIF_MAX_SIZE_BYTES = 64000;
    private static final String TAG = "ProcessingImageReader";
    private final List<Integer> mCaptureIdList = new ArrayList();
    final CaptureProcessor mCaptureProcessor;
    private FutureCallback<List<ImageProxy>> mCaptureStageReadyCallback = new FutureCallback<List<ImageProxy>>() {
        public void onFailure(Throwable th) {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
            r4.this$0.mCaptureProcessor.process(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
            r5 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
            monitor-enter(r4.this$0.mLock);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            r4.this$0.mSettableImageProxyBundle.reset();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0038, code lost:
            r2.execute(new androidx.camera.core.ProcessingImageReader$3$$ExternalSyntheticLambda0(r1, r5));
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onSuccess(java.util.List<androidx.camera.core.ImageProxy> r5) {
            /*
                r4 = this;
                androidx.camera.core.ProcessingImageReader r5 = androidx.camera.core.ProcessingImageReader.this
                java.lang.Object r5 = r5.mLock
                monitor-enter(r5)
                androidx.camera.core.ProcessingImageReader r0 = androidx.camera.core.ProcessingImageReader.this     // Catch:{ all -> 0x0058 }
                boolean r0 = r0.mClosed     // Catch:{ all -> 0x0058 }
                if (r0 == 0) goto L_0x000d
                monitor-exit(r5)     // Catch:{ all -> 0x0058 }
                return
            L_0x000d:
                androidx.camera.core.ProcessingImageReader r0 = androidx.camera.core.ProcessingImageReader.this     // Catch:{ all -> 0x0058 }
                r1 = 1
                r0.mProcessing = r1     // Catch:{ all -> 0x0058 }
                androidx.camera.core.ProcessingImageReader r0 = androidx.camera.core.ProcessingImageReader.this     // Catch:{ all -> 0x0058 }
                androidx.camera.core.SettableImageProxyBundle r0 = r0.mSettableImageProxyBundle     // Catch:{ all -> 0x0058 }
                androidx.camera.core.ProcessingImageReader r1 = androidx.camera.core.ProcessingImageReader.this     // Catch:{ all -> 0x0058 }
                androidx.camera.core.ProcessingImageReader$OnProcessingErrorCallback r1 = r1.mOnProcessingErrorCallback     // Catch:{ all -> 0x0058 }
                androidx.camera.core.ProcessingImageReader r2 = androidx.camera.core.ProcessingImageReader.this     // Catch:{ all -> 0x0058 }
                java.util.concurrent.Executor r2 = r2.mErrorCallbackExecutor     // Catch:{ all -> 0x0058 }
                monitor-exit(r5)     // Catch:{ all -> 0x0058 }
                androidx.camera.core.ProcessingImageReader r5 = androidx.camera.core.ProcessingImageReader.this     // Catch:{ Exception -> 0x0027 }
                androidx.camera.core.impl.CaptureProcessor r5 = r5.mCaptureProcessor     // Catch:{ Exception -> 0x0027 }
                r5.process(r0)     // Catch:{ Exception -> 0x0027 }
                goto L_0x0041
            L_0x0027:
                r5 = move-exception
                androidx.camera.core.ProcessingImageReader r0 = androidx.camera.core.ProcessingImageReader.this
                java.lang.Object r0 = r0.mLock
                monitor-enter(r0)
                androidx.camera.core.ProcessingImageReader r3 = androidx.camera.core.ProcessingImageReader.this     // Catch:{ all -> 0x0055 }
                androidx.camera.core.SettableImageProxyBundle r3 = r3.mSettableImageProxyBundle     // Catch:{ all -> 0x0055 }
                r3.reset()     // Catch:{ all -> 0x0055 }
                if (r1 == 0) goto L_0x0040
                if (r2 == 0) goto L_0x0040
                androidx.camera.core.ProcessingImageReader$3$$ExternalSyntheticLambda0 r3 = new androidx.camera.core.ProcessingImageReader$3$$ExternalSyntheticLambda0     // Catch:{ all -> 0x0055 }
                r3.<init>(r1, r5)     // Catch:{ all -> 0x0055 }
                r2.execute(r3)     // Catch:{ all -> 0x0055 }
            L_0x0040:
                monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            L_0x0041:
                androidx.camera.core.ProcessingImageReader r5 = androidx.camera.core.ProcessingImageReader.this
                java.lang.Object r5 = r5.mLock
                monitor-enter(r5)
                androidx.camera.core.ProcessingImageReader r0 = androidx.camera.core.ProcessingImageReader.this     // Catch:{ all -> 0x0052 }
                r1 = 0
                r0.mProcessing = r1     // Catch:{ all -> 0x0052 }
                monitor-exit(r5)     // Catch:{ all -> 0x0052 }
                androidx.camera.core.ProcessingImageReader r4 = androidx.camera.core.ProcessingImageReader.this
                r4.closeAndCompleteFutureIfNecessary()
                return
            L_0x0052:
                r4 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0052 }
                throw r4
            L_0x0055:
                r4 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0055 }
                throw r4
            L_0x0058:
                r4 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x0058 }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.ProcessingImageReader.AnonymousClass3.onSuccess(java.util.List):void");
        }
    };
    CallbackToFutureAdapter.Completer<Void> mCloseCompleter;
    private ListenableFuture<Void> mCloseFuture;
    boolean mClosed = false;
    Executor mErrorCallbackExecutor;
    Executor mExecutor;
    private ImageReaderProxy.OnImageAvailableListener mImageProcessedListener = new ImageReaderProxy.OnImageAvailableListener() {
        public void onImageAvailable(ImageReaderProxy imageReaderProxy) {
            ImageReaderProxy.OnImageAvailableListener onImageAvailableListener;
            Executor executor;
            synchronized (ProcessingImageReader.this.mLock) {
                onImageAvailableListener = ProcessingImageReader.this.mListener;
                executor = ProcessingImageReader.this.mExecutor;
                ProcessingImageReader.this.mSettableImageProxyBundle.reset();
                ProcessingImageReader.this.setupSettableImageProxyBundleCallbacks();
            }
            if (onImageAvailableListener == null) {
                return;
            }
            if (executor != null) {
                executor.execute(new ProcessingImageReader$2$$ExternalSyntheticLambda0(this, onImageAvailableListener));
            } else {
                onImageAvailableListener.onImageAvailable(ProcessingImageReader.this);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onImageAvailable$0$androidx-camera-core-ProcessingImageReader$2  reason: not valid java name */
        public /* synthetic */ void m133lambda$onImageAvailable$0$androidxcameracoreProcessingImageReader$2(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
            onImageAvailableListener.onImageAvailable(ProcessingImageReader.this);
        }
    };
    final ImageReaderProxy mInputImageReader;
    ImageReaderProxy.OnImageAvailableListener mListener;
    final Object mLock = new Object();
    OnProcessingErrorCallback mOnProcessingErrorCallback;
    final ImageReaderProxy mOutputImageReader;
    final Executor mPostProcessExecutor;
    boolean mProcessing = false;
    SettableImageProxyBundle mSettableImageProxyBundle = new SettableImageProxyBundle(Collections.emptyList(), this.mTagBundleKey);
    private ListenableFuture<List<ImageProxy>> mSettableImageProxyFutureList = Futures.immediateFuture(new ArrayList());
    private String mTagBundleKey = new String();
    private ImageReaderProxy.OnImageAvailableListener mTransformedListener = new ImageReaderProxy.OnImageAvailableListener() {
        public void onImageAvailable(ImageReaderProxy imageReaderProxy) {
            ProcessingImageReader.this.imageIncoming(imageReaderProxy);
        }
    };
    private final ListenableFuture<Void> mUnderlyingCaptureProcessorCloseFuture;

    interface OnProcessingErrorCallback {
        void notifyProcessingError(String str, Throwable th);
    }

    static /* synthetic */ Void lambda$getCloseFuture$1(Void voidR) {
        return null;
    }

    ProcessingImageReader(Builder builder) {
        if (builder.mInputImageReader.getMaxImages() >= builder.mCaptureBundle.getCaptureStages().size()) {
            ImageReaderProxy imageReaderProxy = builder.mInputImageReader;
            this.mInputImageReader = imageReaderProxy;
            int width = imageReaderProxy.getWidth();
            int height = imageReaderProxy.getHeight();
            if (builder.mOutputFormat == 256) {
                width = ((int) (((float) (width * height)) * 1.5f)) + EXIF_MAX_SIZE_BYTES;
                height = 1;
            }
            AndroidImageReaderProxy androidImageReaderProxy = new AndroidImageReaderProxy(ImageReader.newInstance(width, height, builder.mOutputFormat, imageReaderProxy.getMaxImages()));
            this.mOutputImageReader = androidImageReaderProxy;
            this.mPostProcessExecutor = builder.mPostProcessExecutor;
            CaptureProcessor captureProcessor = builder.mCaptureProcessor;
            this.mCaptureProcessor = captureProcessor;
            captureProcessor.onOutputSurface(androidImageReaderProxy.getSurface(), builder.mOutputFormat);
            captureProcessor.onResolutionUpdate(new Size(imageReaderProxy.getWidth(), imageReaderProxy.getHeight()));
            this.mUnderlyingCaptureProcessorCloseFuture = captureProcessor.getCloseFuture();
            setCaptureBundle(builder.mCaptureBundle);
            return;
        }
        throw new IllegalArgumentException("MetadataImageReader is smaller than CaptureBundle.");
    }

    public ImageProxy acquireLatestImage() {
        ImageProxy acquireLatestImage;
        synchronized (this.mLock) {
            acquireLatestImage = this.mOutputImageReader.acquireLatestImage();
        }
        return acquireLatestImage;
    }

    public ImageProxy acquireNextImage() {
        ImageProxy acquireNextImage;
        synchronized (this.mLock) {
            acquireNextImage = this.mOutputImageReader.acquireNextImage();
        }
        return acquireNextImage;
    }

    public void close() {
        synchronized (this.mLock) {
            if (!this.mClosed) {
                this.mInputImageReader.clearOnImageAvailableListener();
                this.mOutputImageReader.clearOnImageAvailableListener();
                this.mClosed = true;
                this.mCaptureProcessor.close();
                closeAndCompleteFutureIfNecessary();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void closeAndCompleteFutureIfNecessary() {
        boolean z;
        boolean z2;
        CallbackToFutureAdapter.Completer<Void> completer;
        synchronized (this.mLock) {
            z = this.mClosed;
            z2 = this.mProcessing;
            completer = this.mCloseCompleter;
            if (z && !z2) {
                this.mInputImageReader.close();
                this.mSettableImageProxyBundle.close();
                this.mOutputImageReader.close();
            }
        }
        if (z && !z2) {
            this.mUnderlyingCaptureProcessorCloseFuture.addListener(new ProcessingImageReader$$ExternalSyntheticLambda2(this, completer), CameraXExecutors.directExecutor());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$closeAndCompleteFutureIfNecessary$0$androidx-camera-core-ProcessingImageReader  reason: not valid java name */
    public /* synthetic */ void m131lambda$closeAndCompleteFutureIfNecessary$0$androidxcameracoreProcessingImageReader(CallbackToFutureAdapter.Completer completer) {
        cancelSettableImageProxyBundleFutureList();
        if (completer != null) {
            completer.set(null);
        }
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> getCloseFuture() {
        ListenableFuture<Void> listenableFuture;
        synchronized (this.mLock) {
            if (!this.mClosed || this.mProcessing) {
                if (this.mCloseFuture == null) {
                    this.mCloseFuture = CallbackToFutureAdapter.getFuture(new ProcessingImageReader$$ExternalSyntheticLambda1(this));
                }
                listenableFuture = Futures.nonCancellationPropagating(this.mCloseFuture);
            } else {
                listenableFuture = Futures.transform(this.mUnderlyingCaptureProcessorCloseFuture, new ProcessingImageReader$$ExternalSyntheticLambda0(), CameraXExecutors.directExecutor());
            }
        }
        return listenableFuture;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getCloseFuture$2$androidx-camera-core-ProcessingImageReader  reason: not valid java name */
    public /* synthetic */ Object m132lambda$getCloseFuture$2$androidxcameracoreProcessingImageReader(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.mLock) {
            this.mCloseCompleter = completer;
        }
        return "ProcessingImageReader-close";
    }

    public int getHeight() {
        int height;
        synchronized (this.mLock) {
            height = this.mInputImageReader.getHeight();
        }
        return height;
    }

    public int getWidth() {
        int width;
        synchronized (this.mLock) {
            width = this.mInputImageReader.getWidth();
        }
        return width;
    }

    public int getImageFormat() {
        int imageFormat;
        synchronized (this.mLock) {
            imageFormat = this.mOutputImageReader.getImageFormat();
        }
        return imageFormat;
    }

    public int getMaxImages() {
        int maxImages;
        synchronized (this.mLock) {
            maxImages = this.mInputImageReader.getMaxImages();
        }
        return maxImages;
    }

    public Surface getSurface() {
        Surface surface;
        synchronized (this.mLock) {
            surface = this.mInputImageReader.getSurface();
        }
        return surface;
    }

    public void setOnImageAvailableListener(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, Executor executor) {
        synchronized (this.mLock) {
            this.mListener = (ImageReaderProxy.OnImageAvailableListener) Preconditions.checkNotNull(onImageAvailableListener);
            this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
            this.mInputImageReader.setOnImageAvailableListener(this.mTransformedListener, executor);
            this.mOutputImageReader.setOnImageAvailableListener(this.mImageProcessedListener, executor);
        }
    }

    public void clearOnImageAvailableListener() {
        synchronized (this.mLock) {
            this.mListener = null;
            this.mExecutor = null;
            this.mInputImageReader.clearOnImageAvailableListener();
            this.mOutputImageReader.clearOnImageAvailableListener();
            if (!this.mProcessing) {
                this.mSettableImageProxyBundle.close();
            }
        }
    }

    public void setCaptureBundle(CaptureBundle captureBundle) {
        synchronized (this.mLock) {
            if (!this.mClosed) {
                cancelSettableImageProxyBundleFutureList();
                if (captureBundle.getCaptureStages() != null) {
                    if (this.mInputImageReader.getMaxImages() >= captureBundle.getCaptureStages().size()) {
                        this.mCaptureIdList.clear();
                        for (CaptureStage next : captureBundle.getCaptureStages()) {
                            if (next != null) {
                                this.mCaptureIdList.add(Integer.valueOf(next.getId()));
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("CaptureBundle is larger than InputImageReader.");
                    }
                }
                this.mTagBundleKey = Integer.toString(captureBundle.hashCode());
                this.mSettableImageProxyBundle = new SettableImageProxyBundle(this.mCaptureIdList, this.mTagBundleKey);
                setupSettableImageProxyBundleCallbacks();
            }
        }
    }

    private void cancelSettableImageProxyBundleFutureList() {
        synchronized (this.mLock) {
            if (!this.mSettableImageProxyFutureList.isDone()) {
                this.mSettableImageProxyFutureList.cancel(true);
            }
            this.mSettableImageProxyBundle.reset();
        }
    }

    public String getTagBundleKey() {
        return this.mTagBundleKey;
    }

    /* access modifiers changed from: package-private */
    public CameraCaptureCallback getCameraCaptureCallback() {
        synchronized (this.mLock) {
            ImageReaderProxy imageReaderProxy = this.mInputImageReader;
            if (imageReaderProxy instanceof MetadataImageReader) {
                CameraCaptureCallback cameraCaptureCallback = ((MetadataImageReader) imageReaderProxy).getCameraCaptureCallback();
                return cameraCaptureCallback;
            }
            AnonymousClass4 r1 = new CameraCaptureCallback() {
            };
            return r1;
        }
    }

    public void setOnProcessingErrorCallback(Executor executor, OnProcessingErrorCallback onProcessingErrorCallback) {
        synchronized (this.mLock) {
            this.mErrorCallbackExecutor = executor;
            this.mOnProcessingErrorCallback = onProcessingErrorCallback;
        }
    }

    /* access modifiers changed from: package-private */
    public void setupSettableImageProxyBundleCallbacks() {
        ArrayList arrayList = new ArrayList();
        for (Integer intValue : this.mCaptureIdList) {
            arrayList.add(this.mSettableImageProxyBundle.getImageProxy(intValue.intValue()));
        }
        this.mSettableImageProxyFutureList = Futures.allAsList(arrayList);
        Futures.addCallback(Futures.allAsList(arrayList), this.mCaptureStageReadyCallback, this.mPostProcessExecutor);
    }

    /* access modifiers changed from: package-private */
    public void imageIncoming(ImageReaderProxy imageReaderProxy) {
        synchronized (this.mLock) {
            if (!this.mClosed) {
                try {
                    ImageProxy acquireNextImage = imageReaderProxy.acquireNextImage();
                    if (acquireNextImage != null) {
                        Integer num = (Integer) acquireNextImage.getImageInfo().getTagBundle().getTag(this.mTagBundleKey);
                        if (!this.mCaptureIdList.contains(num)) {
                            Logger.w(TAG, "ImageProxyBundle does not contain this id: " + num);
                            acquireNextImage.close();
                        } else {
                            this.mSettableImageProxyBundle.addImageProxy(acquireNextImage);
                        }
                    }
                } catch (IllegalStateException e) {
                    Logger.e(TAG, "Failed to acquire latest image.", e);
                }
                return;
            }
            return;
        }
    }

    static final class Builder {
        protected final CaptureBundle mCaptureBundle;
        protected final CaptureProcessor mCaptureProcessor;
        protected final ImageReaderProxy mInputImageReader;
        protected int mOutputFormat;
        protected Executor mPostProcessExecutor;

        Builder(ImageReaderProxy imageReaderProxy, CaptureBundle captureBundle, CaptureProcessor captureProcessor) {
            this.mPostProcessExecutor = Executors.newSingleThreadExecutor();
            this.mInputImageReader = imageReaderProxy;
            this.mCaptureBundle = captureBundle;
            this.mCaptureProcessor = captureProcessor;
            this.mOutputFormat = imageReaderProxy.getImageFormat();
        }

        Builder(int i, int i2, int i3, int i4, CaptureBundle captureBundle, CaptureProcessor captureProcessor) {
            this(new MetadataImageReader(i, i2, i3, i4), captureBundle, captureProcessor);
        }

        /* access modifiers changed from: package-private */
        public Builder setPostProcessExecutor(Executor executor) {
            this.mPostProcessExecutor = executor;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setOutputFormat(int i) {
            this.mOutputFormat = i;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ProcessingImageReader build() {
            return new ProcessingImageReader(this);
        }
    }
}
