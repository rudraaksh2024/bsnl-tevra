package androidx.camera.core.imagecapture;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.ProcessingNode;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.internal.CameraCaptureResultImageInfo;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import androidx.core.util.Preconditions;
import java.io.IOException;

final class ProcessingInput2Packet implements Operation<ProcessingNode.InputPacket, Packet<ImageProxy>> {
    ProcessingInput2Packet() {
    }

    public Packet<ImageProxy> apply(ProcessingNode.InputPacket inputPacket) throws ImageCaptureException {
        Exif exif;
        Matrix matrix;
        Rect rect;
        ImageProxy imageProxy = inputPacket.getImageProxy();
        ProcessingRequest processingRequest = inputPacket.getProcessingRequest();
        if (imageProxy.getFormat() == 256) {
            try {
                exif = Exif.createFromImageProxy(imageProxy);
                imageProxy.getPlanes()[0].getBuffer().rewind();
            } catch (IOException e) {
                throw new ImageCaptureException(1, "Failed to extract EXIF data.", e);
            }
        } else {
            exif = null;
        }
        Exif exif2 = exif;
        CameraCaptureResult cameraCaptureResult = ((CameraCaptureResultImageInfo) imageProxy.getImageInfo()).getCameraCaptureResult();
        Rect cropRect = processingRequest.getCropRect();
        Matrix sensorToBufferTransform = processingRequest.getSensorToBufferTransform();
        int rotationDegrees = processingRequest.getRotationDegrees();
        if (ImagePipeline.EXIF_ROTATION_AVAILABILITY.shouldUseExifOrientation(imageProxy)) {
            Preconditions.checkNotNull(exif2, "The image must have JPEG exif.");
            Preconditions.checkState(isSizeMatch(exif2, imageProxy), "Exif size does not match image size.");
            Matrix halTransform = getHalTransform(processingRequest.getRotationDegrees(), new Size(exif2.getWidth(), exif2.getHeight()), exif2.getRotation());
            rect = getUpdatedCropRect(processingRequest.getCropRect(), halTransform);
            matrix = getUpdatedTransform(processingRequest.getSensorToBufferTransform(), halTransform);
            rotationDegrees = exif2.getRotation();
        } else {
            matrix = sensorToBufferTransform;
            rect = cropRect;
        }
        return Packet.of(imageProxy, exif2, rect, rotationDegrees, matrix, cameraCaptureResult);
    }

    private static boolean isSizeMatch(Exif exif, ImageProxy imageProxy) {
        return exif.getWidth() == imageProxy.getWidth() && exif.getHeight() == imageProxy.getHeight();
    }

    private static Matrix getUpdatedTransform(Matrix matrix, Matrix matrix2) {
        Matrix matrix3 = new Matrix(matrix);
        matrix3.postConcat(matrix2);
        return matrix3;
    }

    private static Rect getUpdatedCropRect(Rect rect, Matrix matrix) {
        RectF rectF = new RectF(rect);
        matrix.mapRect(rectF);
        Rect rect2 = new Rect();
        rectF.round(rect2);
        return rect2;
    }

    private static Matrix getHalTransform(int i, Size size, int i2) {
        int i3 = i - i2;
        Size size2 = TransformUtils.is90or270(TransformUtils.within360(i3)) ? new Size(size.getHeight(), size.getWidth()) : size;
        return TransformUtils.getRectToRect(new RectF(0.0f, 0.0f, (float) size2.getWidth(), (float) size2.getHeight()), new RectF(0.0f, 0.0f, (float) size.getWidth(), (float) size.getHeight()), i3);
    }
}
