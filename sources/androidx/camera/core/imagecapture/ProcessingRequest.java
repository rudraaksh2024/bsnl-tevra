package androidx.camera.core.imagecapture;

import android.graphics.Matrix;
import android.graphics.Rect;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureStage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class ProcessingRequest {
    private final TakePictureCallback mCallback;
    private final Rect mCropRect;
    private final int mJpegQuality;
    private final ImageCapture.OutputFileOptions mOutputFileOptions;
    private final int mRotationDegrees;
    private final Matrix mSensorToBufferTransform;
    private final List<Integer> mStageIds = new ArrayList();
    private final String mTagBundleKey;

    ProcessingRequest(CaptureBundle captureBundle, ImageCapture.OutputFileOptions outputFileOptions, Rect rect, int i, int i2, Matrix matrix, TakePictureCallback takePictureCallback) {
        this.mOutputFileOptions = outputFileOptions;
        this.mJpegQuality = i2;
        this.mRotationDegrees = i;
        this.mCropRect = rect;
        this.mSensorToBufferTransform = matrix;
        this.mCallback = takePictureCallback;
        this.mTagBundleKey = String.valueOf(captureBundle.hashCode());
        for (CaptureStage id : (List) Objects.requireNonNull(captureBundle.getCaptureStages())) {
            this.mStageIds.add(Integer.valueOf(id.getId()));
        }
    }

    /* access modifiers changed from: package-private */
    public String getTagBundleKey() {
        return this.mTagBundleKey;
    }

    /* access modifiers changed from: package-private */
    public List<Integer> getStageIds() {
        return this.mStageIds;
    }

    /* access modifiers changed from: package-private */
    public ImageCapture.OutputFileOptions getOutputFileOptions() {
        return this.mOutputFileOptions;
    }

    /* access modifiers changed from: package-private */
    public Rect getCropRect() {
        return this.mCropRect;
    }

    /* access modifiers changed from: package-private */
    public int getRotationDegrees() {
        return this.mRotationDegrees;
    }

    /* access modifiers changed from: package-private */
    public int getJpegQuality() {
        return this.mJpegQuality;
    }

    /* access modifiers changed from: package-private */
    public Matrix getSensorToBufferTransform() {
        return this.mSensorToBufferTransform;
    }

    /* access modifiers changed from: package-private */
    public boolean isInMemoryCapture() {
        return getOutputFileOptions() == null;
    }

    /* access modifiers changed from: package-private */
    public void onImageCaptured() {
        this.mCallback.onImageCaptured();
    }

    /* access modifiers changed from: package-private */
    public void onFinalResult(ImageCapture.OutputFileResults outputFileResults) {
        this.mCallback.onFinalResult(outputFileResults);
    }

    /* access modifiers changed from: package-private */
    public void onFinalResult(ImageProxy imageProxy) {
        this.mCallback.onFinalResult(imageProxy);
    }

    /* access modifiers changed from: package-private */
    public void onProcessFailure(ImageCaptureException imageCaptureException) {
        this.mCallback.onProcessFailure(imageCaptureException);
    }

    /* access modifiers changed from: package-private */
    public boolean isAborted() {
        return this.mCallback.isAborted();
    }
}
