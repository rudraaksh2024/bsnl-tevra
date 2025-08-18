package androidx.camera.core;

import android.graphics.Matrix;
import android.media.ImageReader;
import androidx.camera.core.impl.TagBundle;

class ModifiableImageReaderProxy extends AndroidImageReaderProxy {
    private volatile Integer mRotationDegrees = null;
    private volatile Matrix mSensorToBufferTransformMatrix = null;
    private volatile TagBundle mTagBundle = null;
    private volatile Long mTimestamp = null;

    ModifiableImageReaderProxy(ImageReader imageReader) {
        super(imageReader);
    }

    /* access modifiers changed from: package-private */
    public void setImageTagBundle(TagBundle tagBundle) {
        this.mTagBundle = tagBundle;
    }

    /* access modifiers changed from: package-private */
    public void setImageTimeStamp(long j) {
        this.mTimestamp = Long.valueOf(j);
    }

    /* access modifiers changed from: package-private */
    public void setImageRotationDegrees(int i) {
        this.mRotationDegrees = Integer.valueOf(i);
    }

    /* access modifiers changed from: package-private */
    public void setImageSensorToBufferTransformaMatrix(Matrix matrix) {
        this.mSensorToBufferTransformMatrix = matrix;
    }

    public ImageProxy acquireLatestImage() {
        return modifyImage(super.acquireNextImage());
    }

    public ImageProxy acquireNextImage() {
        return modifyImage(super.acquireNextImage());
    }

    private ImageProxy modifyImage(ImageProxy imageProxy) {
        int i;
        Matrix matrix;
        ImageInfo imageInfo = imageProxy.getImageInfo();
        TagBundle tagBundle = this.mTagBundle != null ? this.mTagBundle : imageInfo.getTagBundle();
        long longValue = this.mTimestamp != null ? this.mTimestamp.longValue() : imageInfo.getTimestamp();
        if (this.mRotationDegrees != null) {
            i = this.mRotationDegrees.intValue();
        } else {
            i = imageInfo.getRotationDegrees();
        }
        if (this.mSensorToBufferTransformMatrix != null) {
            matrix = this.mSensorToBufferTransformMatrix;
        } else {
            matrix = imageInfo.getSensorToBufferTransformMatrix();
        }
        return new SettableImageProxy(imageProxy, ImmutableImageInfo.create(tagBundle, longValue, i, matrix));
    }
}
