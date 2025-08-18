package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.Bitmap2JpegBytes;
import androidx.camera.core.imagecapture.Image2JpegBytes;
import androidx.camera.core.imagecapture.JpegBytes2Disk;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.processing.Edge;
import androidx.camera.core.processing.Node;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import java.util.Objects;
import java.util.concurrent.Executor;

public class ProcessingNode implements Node<In, Void> {
    private Operation<Bitmap2JpegBytes.In, Packet<byte[]>> mBitmap2JpegBytes;
    private final Executor mBlockingExecutor;
    private Operation<Image2JpegBytes.In, Packet<byte[]>> mImage2JpegBytes;
    private Operation<InputPacket, Packet<ImageProxy>> mInput2Packet;
    private Operation<Packet<byte[]>, Packet<Bitmap>> mJpegBytes2CroppedBitmap;
    private Operation<JpegBytes2Disk.In, ImageCapture.OutputFileResults> mJpegBytes2Disk;
    private Operation<Packet<byte[]>, Packet<ImageProxy>> mJpegBytes2Image;
    private Operation<Packet<ImageProxy>, ImageProxy> mJpegImage2Result;

    public void release() {
    }

    ProcessingNode(Executor executor) {
        this.mBlockingExecutor = executor;
    }

    public Void transform(In in) {
        in.getEdge().setListener(new ProcessingNode$$ExternalSyntheticLambda0(this));
        this.mInput2Packet = new ProcessingInput2Packet();
        this.mImage2JpegBytes = new Image2JpegBytes();
        this.mJpegBytes2CroppedBitmap = new JpegBytes2CroppedBitmap();
        this.mBitmap2JpegBytes = new Bitmap2JpegBytes();
        this.mJpegBytes2Disk = new JpegBytes2Disk();
        this.mJpegImage2Result = new JpegImage2Result();
        if (in.getFormat() != 35) {
            return null;
        }
        this.mJpegBytes2Image = new JpegBytes2Image();
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$transform$1$androidx-camera-core-imagecapture-ProcessingNode  reason: not valid java name */
    public /* synthetic */ void m149lambda$transform$1$androidxcameracoreimagecaptureProcessingNode(InputPacket inputPacket) {
        if (!inputPacket.getProcessingRequest().isAborted()) {
            this.mBlockingExecutor.execute(new ProcessingNode$$ExternalSyntheticLambda2(this, inputPacket));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: processInputPacket */
    public void m148lambda$transform$0$androidxcameracoreimagecaptureProcessingNode(InputPacket inputPacket) {
        ProcessingRequest processingRequest = inputPacket.getProcessingRequest();
        try {
            if (inputPacket.getProcessingRequest().isInMemoryCapture()) {
                CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda3(processingRequest, processInMemoryCapture(inputPacket)));
                return;
            }
            CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda4(processingRequest, processOnDiskCapture(inputPacket)));
        } catch (ImageCaptureException e) {
            sendError(processingRequest, e);
        } catch (RuntimeException e2) {
            sendError(processingRequest, new ImageCaptureException(0, "Processing failed.", e2));
        }
    }

    /* access modifiers changed from: package-private */
    public ImageCapture.OutputFileResults processOnDiskCapture(InputPacket inputPacket) throws ImageCaptureException {
        ProcessingRequest processingRequest = inputPacket.getProcessingRequest();
        Packet apply = this.mImage2JpegBytes.apply(Image2JpegBytes.In.of(this.mInput2Packet.apply(inputPacket), processingRequest.getJpegQuality()));
        if (apply.hasCropping()) {
            apply = this.mBitmap2JpegBytes.apply(Bitmap2JpegBytes.In.of(this.mJpegBytes2CroppedBitmap.apply(apply), processingRequest.getJpegQuality()));
        }
        return this.mJpegBytes2Disk.apply(JpegBytes2Disk.In.of(apply, (ImageCapture.OutputFileOptions) Objects.requireNonNull(processingRequest.getOutputFileOptions())));
    }

    /* access modifiers changed from: package-private */
    public ImageProxy processInMemoryCapture(InputPacket inputPacket) throws ImageCaptureException {
        ProcessingRequest processingRequest = inputPacket.getProcessingRequest();
        Packet apply = this.mInput2Packet.apply(inputPacket);
        if (apply.getFormat() == 35) {
            apply = this.mJpegBytes2Image.apply(this.mImage2JpegBytes.apply(Image2JpegBytes.In.of(apply, processingRequest.getJpegQuality())));
        }
        return this.mJpegImage2Result.apply(apply);
    }

    private static void sendError(ProcessingRequest processingRequest, ImageCaptureException imageCaptureException) {
        CameraXExecutors.mainThreadExecutor().execute(new ProcessingNode$$ExternalSyntheticLambda1(processingRequest, imageCaptureException));
    }

    static abstract class InputPacket {
        /* access modifiers changed from: package-private */
        public abstract ImageProxy getImageProxy();

        /* access modifiers changed from: package-private */
        public abstract ProcessingRequest getProcessingRequest();

        InputPacket() {
        }

        static InputPacket of(ProcessingRequest processingRequest, ImageProxy imageProxy) {
            return new AutoValue_ProcessingNode_InputPacket(processingRequest, imageProxy);
        }
    }

    static abstract class In {
        /* access modifiers changed from: package-private */
        public abstract Edge<InputPacket> getEdge();

        /* access modifiers changed from: package-private */
        public abstract int getFormat();

        In() {
        }

        static In of(int i) {
            return new AutoValue_ProcessingNode_In(new Edge(), i);
        }
    }

    /* access modifiers changed from: package-private */
    public void injectJpegBytes2CroppedBitmapForTesting(Operation<Packet<byte[]>, Packet<Bitmap>> operation) {
        this.mJpegBytes2CroppedBitmap = operation;
    }
}
