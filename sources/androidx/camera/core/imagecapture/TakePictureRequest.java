package androidx.camera.core.imagecapture;

import android.graphics.Matrix;
import android.graphics.Rect;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.core.util.Preconditions;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

public abstract class TakePictureRequest {
    /* access modifiers changed from: package-private */
    public abstract Executor getAppExecutor();

    /* access modifiers changed from: package-private */
    public abstract int getCaptureMode();

    /* access modifiers changed from: package-private */
    public abstract Rect getCropRect();

    /* access modifiers changed from: package-private */
    public abstract ImageCapture.OnImageCapturedCallback getInMemoryCallback();

    /* access modifiers changed from: package-private */
    public abstract int getJpegQuality();

    /* access modifiers changed from: package-private */
    public abstract ImageCapture.OnImageSavedCallback getOnDiskCallback();

    /* access modifiers changed from: package-private */
    public abstract ImageCapture.OutputFileOptions getOutputFileOptions();

    /* access modifiers changed from: package-private */
    public abstract int getRotationDegrees();

    /* access modifiers changed from: package-private */
    public abstract Matrix getSensorToBufferTransform();

    /* access modifiers changed from: package-private */
    public abstract List<CameraCaptureCallback> getSessionConfigCameraCaptureCallbacks();

    /* access modifiers changed from: package-private */
    public void onError(ImageCaptureException imageCaptureException) {
        getAppExecutor().execute(new TakePictureRequest$$ExternalSyntheticLambda1(this, imageCaptureException));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$0$androidx-camera-core-imagecapture-TakePictureRequest  reason: not valid java name */
    public /* synthetic */ void m153lambda$onError$0$androidxcameracoreimagecaptureTakePictureRequest(ImageCaptureException imageCaptureException) {
        boolean z = true;
        boolean z2 = getInMemoryCallback() != null;
        if (getOnDiskCallback() == null) {
            z = false;
        }
        if (z2 && !z) {
            ((ImageCapture.OnImageCapturedCallback) Objects.requireNonNull(getInMemoryCallback())).onError(imageCaptureException);
        } else if (!z || z2) {
            throw new IllegalStateException("One and only one callback is allowed.");
        } else {
            ((ImageCapture.OnImageSavedCallback) Objects.requireNonNull(getOnDiskCallback())).onError(imageCaptureException);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onResult$1$androidx-camera-core-imagecapture-TakePictureRequest  reason: not valid java name */
    public /* synthetic */ void m154lambda$onResult$1$androidxcameracoreimagecaptureTakePictureRequest(ImageCapture.OutputFileResults outputFileResults) {
        ((ImageCapture.OnImageSavedCallback) Objects.requireNonNull(getOnDiskCallback())).onImageSaved((ImageCapture.OutputFileResults) Objects.requireNonNull(outputFileResults));
    }

    /* access modifiers changed from: package-private */
    public void onResult(ImageCapture.OutputFileResults outputFileResults) {
        getAppExecutor().execute(new TakePictureRequest$$ExternalSyntheticLambda0(this, outputFileResults));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onResult$2$androidx-camera-core-imagecapture-TakePictureRequest  reason: not valid java name */
    public /* synthetic */ void m155lambda$onResult$2$androidxcameracoreimagecaptureTakePictureRequest(ImageProxy imageProxy) {
        ((ImageCapture.OnImageCapturedCallback) Objects.requireNonNull(getInMemoryCallback())).onCaptureSuccess((ImageProxy) Objects.requireNonNull(imageProxy));
    }

    /* access modifiers changed from: package-private */
    public void onResult(ImageProxy imageProxy) {
        getAppExecutor().execute(new TakePictureRequest$$ExternalSyntheticLambda2(this, imageProxy));
    }

    public static TakePictureRequest of(Executor executor, ImageCapture.OnImageCapturedCallback onImageCapturedCallback, ImageCapture.OnImageSavedCallback onImageSavedCallback, ImageCapture.OutputFileOptions outputFileOptions, Rect rect, Matrix matrix, int i, int i2, int i3, List<CameraCaptureCallback> list) {
        boolean z = true;
        Preconditions.checkArgument((onImageSavedCallback == null) == (outputFileOptions == null), "onDiskCallback and outputFileOptions should be both null or both non-null.");
        boolean z2 = onImageSavedCallback == null;
        if (onImageCapturedCallback != null) {
            z = false;
        }
        Preconditions.checkArgument(z ^ z2, "One and only one on-disk or in-memory callback should be present.");
        return new AutoValue_TakePictureRequest(executor, onImageCapturedCallback, onImageSavedCallback, outputFileOptions, rect, matrix, i, i2, i3, list);
    }
}
