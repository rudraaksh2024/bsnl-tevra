package androidx.camera.core.imagecapture;

import android.util.Size;
import android.view.Surface;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.MetadataImageReader;
import androidx.camera.core.SafeCloseImageReaderProxy;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.processing.Edge;
import androidx.camera.core.processing.Node;
import androidx.core.util.Preconditions;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class CaptureNode implements Node<In, Out> {
    static final int MAX_IMAGES = 4;
    private ProcessingRequest mCurrentRequest = null;
    private In mInputEdge;
    private Out mOutputEdge;
    private final Set<ImageProxy> mPendingImages = new HashSet();
    private final Set<Integer> mPendingStageIds = new HashSet();
    SafeCloseImageReaderProxy mSafeCloseImageReaderProxy;

    CaptureNode() {
    }

    public Out transform(In in) {
        this.mInputEdge = in;
        Size size = in.getSize();
        MetadataImageReader metadataImageReader = new MetadataImageReader(size.getWidth(), size.getHeight(), in.getFormat(), 4);
        this.mSafeCloseImageReaderProxy = new SafeCloseImageReaderProxy(metadataImageReader);
        in.setCameraCaptureCallback(metadataImageReader.getCameraCaptureCallback());
        in.setSurface((Surface) Objects.requireNonNull(metadataImageReader.getSurface()));
        metadataImageReader.setOnImageAvailableListener(new CaptureNode$$ExternalSyntheticLambda0(this), CameraXExecutors.mainThreadExecutor());
        in.getRequestEdge().setListener(new CaptureNode$$ExternalSyntheticLambda1(this));
        Out of = Out.of(in.getFormat());
        this.mOutputEdge = of;
        return of;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$transform$0$androidx-camera-core-imagecapture-CaptureNode  reason: not valid java name */
    public /* synthetic */ void m147lambda$transform$0$androidxcameracoreimagecaptureCaptureNode(ImageReaderProxy imageReaderProxy) {
        onImageProxyAvailable((ImageProxy) Objects.requireNonNull(imageReaderProxy.acquireNextImage()));
    }

    /* access modifiers changed from: package-private */
    public void onImageProxyAvailable(ImageProxy imageProxy) {
        Threads.checkMainThread();
        if (this.mCurrentRequest == null) {
            this.mPendingImages.add(imageProxy);
        } else {
            matchAndPropagateImage(imageProxy);
        }
    }

    private void matchAndPropagateImage(ImageProxy imageProxy) {
        int intValue = ((Integer) Objects.requireNonNull(imageProxy.getImageInfo().getTagBundle().getTag(this.mCurrentRequest.getTagBundleKey()))).intValue();
        Preconditions.checkState(this.mPendingStageIds.contains(Integer.valueOf(intValue)), "Received an unexpected stage id" + intValue);
        this.mPendingStageIds.remove(Integer.valueOf(intValue));
        if (this.mPendingStageIds.isEmpty()) {
            this.mCurrentRequest.onImageCaptured();
            this.mCurrentRequest = null;
        }
        this.mOutputEdge.getImageEdge().accept(imageProxy);
    }

    /* access modifiers changed from: package-private */
    public void onRequestAvailable(ProcessingRequest processingRequest) {
        Threads.checkMainThread();
        boolean z = true;
        Preconditions.checkState(getCapacity() > 0, "Too many acquire images. Close image to be able to process next.");
        if (this.mCurrentRequest != null && !this.mPendingStageIds.isEmpty()) {
            z = false;
        }
        Preconditions.checkState(z, "The previous request is not complete");
        this.mCurrentRequest = processingRequest;
        this.mPendingStageIds.addAll(processingRequest.getStageIds());
        this.mOutputEdge.getRequestEdge().accept(processingRequest);
        for (ImageProxy matchAndPropagateImage : this.mPendingImages) {
            matchAndPropagateImage(matchAndPropagateImage);
        }
        this.mPendingImages.clear();
    }

    public void release() {
        Threads.checkMainThread();
        SafeCloseImageReaderProxy safeCloseImageReaderProxy = this.mSafeCloseImageReaderProxy;
        if (safeCloseImageReaderProxy != null) {
            safeCloseImageReaderProxy.safeClose();
        }
        In in = this.mInputEdge;
        if (in != null) {
            in.closeSurface();
        }
    }

    /* access modifiers changed from: package-private */
    public In getInputEdge() {
        return this.mInputEdge;
    }

    public int getCapacity() {
        Threads.checkMainThread();
        Preconditions.checkState(this.mSafeCloseImageReaderProxy != null, "The ImageReader is not initialized.");
        return this.mSafeCloseImageReaderProxy.getCapacity();
    }

    public void setOnImageCloseListener(ForwardingImageProxy.OnImageCloseListener onImageCloseListener) {
        Threads.checkMainThread();
        Preconditions.checkState(this.mSafeCloseImageReaderProxy != null, "The ImageReader is not initialized.");
        this.mSafeCloseImageReaderProxy.setOnImageCloseListener(onImageCloseListener);
    }

    static abstract class In {
        private CameraCaptureCallback mCameraCaptureCallback;
        private DeferrableSurface mSurface;

        /* access modifiers changed from: package-private */
        public abstract int getFormat();

        /* access modifiers changed from: package-private */
        public abstract Edge<ProcessingRequest> getRequestEdge();

        /* access modifiers changed from: package-private */
        public abstract Size getSize();

        In() {
        }

        /* access modifiers changed from: package-private */
        public DeferrableSurface getSurface() {
            return this.mSurface;
        }

        /* access modifiers changed from: package-private */
        public void setSurface(Surface surface) {
            Preconditions.checkState(this.mSurface == null, "The surface is already set.");
            this.mSurface = new ImmediateSurface(surface);
        }

        /* access modifiers changed from: package-private */
        public void closeSurface() {
            this.mSurface.close();
        }

        /* access modifiers changed from: package-private */
        public CameraCaptureCallback getCameraCaptureCallback() {
            return this.mCameraCaptureCallback;
        }

        /* access modifiers changed from: package-private */
        public void setCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            this.mCameraCaptureCallback = cameraCaptureCallback;
        }

        static In of(Size size, int i) {
            return new AutoValue_CaptureNode_In(size, i, new Edge());
        }
    }

    static abstract class Out {
        /* access modifiers changed from: package-private */
        public abstract int getFormat();

        /* access modifiers changed from: package-private */
        public abstract Edge<ImageProxy> getImageEdge();

        /* access modifiers changed from: package-private */
        public abstract Edge<ProcessingRequest> getRequestEdge();

        Out() {
        }

        static Out of(int i) {
            return new AutoValue_CaptureNode_Out(new Edge(), new Edge(), i);
        }
    }
}
