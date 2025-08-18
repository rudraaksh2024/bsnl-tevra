package androidx.camera.core.processing;

import androidx.camera.core.CameraEffect;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProcessor;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class InternalImageProcessor {
    private final Executor mExecutor;
    private final ImageProcessor mImageProcessor;

    public InternalImageProcessor(CameraEffect cameraEffect) {
        Preconditions.checkArgument(cameraEffect.getTargets() == 4);
        this.mExecutor = cameraEffect.getProcessorExecutor();
        this.mImageProcessor = (ImageProcessor) Objects.requireNonNull(cameraEffect.getImageProcessor());
    }

    public ImageProcessor.Response safeProcess(ImageProcessor.Request request) throws ImageCaptureException {
        try {
            return (ImageProcessor.Response) CallbackToFutureAdapter.getFuture(new InternalImageProcessor$$ExternalSyntheticLambda1(this, request)).get();
        } catch (InterruptedException | ExecutionException e) {
            Throwable cause = e.getCause();
            Exception exc = e;
            if (cause != null) {
                exc = e.getCause();
            }
            throw new ImageCaptureException(0, "Failed to invoke ImageProcessor.", exc);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$safeProcess$1$androidx-camera-core-processing-InternalImageProcessor  reason: not valid java name */
    public /* synthetic */ Object m175lambda$safeProcess$1$androidxcameracoreprocessingInternalImageProcessor(ImageProcessor.Request request, CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new InternalImageProcessor$$ExternalSyntheticLambda0(this, completer, request));
        return "InternalImageProcessor#process " + request.hashCode();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$safeProcess$0$androidx-camera-core-processing-InternalImageProcessor  reason: not valid java name */
    public /* synthetic */ void m174lambda$safeProcess$0$androidxcameracoreprocessingInternalImageProcessor(CallbackToFutureAdapter.Completer completer, ImageProcessor.Request request) {
        try {
            completer.set(this.mImageProcessor.process(request));
        } catch (Exception e) {
            completer.setException(e);
        }
    }
}
