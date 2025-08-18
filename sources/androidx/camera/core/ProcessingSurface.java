package androidx.camera.core;

import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.SingleImageProxyBundle;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledExecutorService;

final class ProcessingSurface extends DeferrableSurface {
    private static final int MAX_IMAGES = 2;
    private static final String TAG = "ProcessingSurfaceTextur";
    private final CameraCaptureCallback mCameraCaptureCallback;
    final CaptureProcessor mCaptureProcessor;
    final CaptureStage mCaptureStage;
    private final Handler mImageReaderHandler;
    private final MetadataImageReader mInputImageReader;
    private final Surface mInputSurface;
    final Object mLock = new Object();
    private final DeferrableSurface mOutputDeferrableSurface;
    boolean mReleased;
    private final Size mResolution;
    private String mTagBundleKey;
    private final ImageReaderProxy.OnImageAvailableListener mTransformedListener;

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-core-ProcessingSurface  reason: not valid java name */
    public /* synthetic */ void m134lambda$new$0$androidxcameracoreProcessingSurface(ImageReaderProxy imageReaderProxy) {
        synchronized (this.mLock) {
            imageIncoming(imageReaderProxy);
        }
    }

    ProcessingSurface(int i, int i2, int i3, Handler handler, CaptureStage captureStage, CaptureProcessor captureProcessor, DeferrableSurface deferrableSurface, String str) {
        super(new Size(i, i2), i3);
        ProcessingSurface$$ExternalSyntheticLambda1 processingSurface$$ExternalSyntheticLambda1 = new ProcessingSurface$$ExternalSyntheticLambda1(this);
        this.mTransformedListener = processingSurface$$ExternalSyntheticLambda1;
        this.mReleased = false;
        Size size = new Size(i, i2);
        this.mResolution = size;
        if (handler != null) {
            this.mImageReaderHandler = handler;
        } else {
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                this.mImageReaderHandler = new Handler(myLooper);
            } else {
                throw new IllegalStateException("Creating a ProcessingSurface requires a non-null Handler, or be created  on a thread with a Looper.");
            }
        }
        ScheduledExecutorService newHandlerExecutor = CameraXExecutors.newHandlerExecutor(this.mImageReaderHandler);
        MetadataImageReader metadataImageReader = new MetadataImageReader(i, i2, i3, 2);
        this.mInputImageReader = metadataImageReader;
        metadataImageReader.setOnImageAvailableListener(processingSurface$$ExternalSyntheticLambda1, newHandlerExecutor);
        this.mInputSurface = metadataImageReader.getSurface();
        this.mCameraCaptureCallback = metadataImageReader.getCameraCaptureCallback();
        this.mCaptureProcessor = captureProcessor;
        captureProcessor.onResolutionUpdate(size);
        this.mCaptureStage = captureStage;
        this.mOutputDeferrableSurface = deferrableSurface;
        this.mTagBundleKey = str;
        Futures.addCallback(deferrableSurface.getSurface(), new FutureCallback<Surface>() {
            public void onSuccess(Surface surface) {
                synchronized (ProcessingSurface.this.mLock) {
                    ProcessingSurface.this.mCaptureProcessor.onOutputSurface(surface, 1);
                }
            }

            public void onFailure(Throwable th) {
                Logger.e(ProcessingSurface.TAG, "Failed to extract Listenable<Surface>.", th);
            }
        }, CameraXExecutors.directExecutor());
        getTerminationFuture().addListener(new ProcessingSurface$$ExternalSyntheticLambda2(this), CameraXExecutors.directExecutor());
    }

    public ListenableFuture<Surface> provideSurface() {
        return FutureChain.from(this.mOutputDeferrableSurface.getSurface()).transform(new ProcessingSurface$$ExternalSyntheticLambda0(this), CameraXExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$provideSurface$1$androidx-camera-core-ProcessingSurface  reason: not valid java name */
    public /* synthetic */ Surface m135lambda$provideSurface$1$androidxcameracoreProcessingSurface(Surface surface) {
        return this.mInputSurface;
    }

    /* access modifiers changed from: package-private */
    public CameraCaptureCallback getCameraCaptureCallback() {
        CameraCaptureCallback cameraCaptureCallback;
        synchronized (this.mLock) {
            if (!this.mReleased) {
                cameraCaptureCallback = this.mCameraCaptureCallback;
            } else {
                throw new IllegalStateException("ProcessingSurface already released!");
            }
        }
        return cameraCaptureCallback;
    }

    /* access modifiers changed from: private */
    public void release() {
        synchronized (this.mLock) {
            if (!this.mReleased) {
                this.mInputImageReader.clearOnImageAvailableListener();
                this.mInputImageReader.close();
                this.mInputSurface.release();
                this.mOutputDeferrableSurface.close();
                this.mReleased = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void imageIncoming(ImageReaderProxy imageReaderProxy) {
        ImageProxy imageProxy;
        if (!this.mReleased) {
            try {
                imageProxy = imageReaderProxy.acquireNextImage();
            } catch (IllegalStateException e) {
                Logger.e(TAG, "Failed to acquire next image.", e);
                imageProxy = null;
            }
            if (imageProxy != null) {
                ImageInfo imageInfo = imageProxy.getImageInfo();
                if (imageInfo == null) {
                    imageProxy.close();
                    return;
                }
                Integer num = (Integer) imageInfo.getTagBundle().getTag(this.mTagBundleKey);
                if (num == null) {
                    imageProxy.close();
                } else if (this.mCaptureStage.getId() != num.intValue()) {
                    Logger.w(TAG, "ImageProxyBundle does not contain this id: " + num);
                    imageProxy.close();
                } else {
                    SingleImageProxyBundle singleImageProxyBundle = new SingleImageProxyBundle(imageProxy, this.mTagBundleKey);
                    try {
                        incrementUseCount();
                        this.mCaptureProcessor.process(singleImageProxyBundle);
                        singleImageProxyBundle.close();
                        decrementUseCount();
                    } catch (DeferrableSurface.SurfaceClosedException unused) {
                        Logger.d(TAG, "The ProcessingSurface has been closed. Don't process the incoming image.");
                        singleImageProxyBundle.close();
                    }
                }
            }
        }
    }
}
