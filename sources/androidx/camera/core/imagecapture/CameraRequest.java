package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.utils.Threads;
import java.util.List;

public final class CameraRequest {
    private final TakePictureCallback mCallback;
    private final List<CaptureConfig> mCaptureConfigs;

    public CameraRequest(List<CaptureConfig> list, TakePictureCallback takePictureCallback) {
        this.mCaptureConfigs = list;
        this.mCallback = takePictureCallback;
    }

    /* access modifiers changed from: package-private */
    public List<CaptureConfig> getCaptureConfigs() {
        return this.mCaptureConfigs;
    }

    /* access modifiers changed from: package-private */
    public void onCaptureFailure(ImageCaptureException imageCaptureException) {
        Threads.checkMainThread();
        this.mCallback.onCaptureFailure(imageCaptureException);
    }
}
