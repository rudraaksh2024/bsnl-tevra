package androidx.camera.core.imagecapture;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

class JpegBytes2Disk implements Operation<In, ImageCapture.OutputFileResults> {
    private static final int COPY_BUFFER_SIZE = 1024;
    private static final int NOT_PENDING = 0;
    private static final int PENDING = 1;
    private static final String TEMP_FILE_PREFIX = "CameraX";
    private static final String TEMP_FILE_SUFFIX = ".tmp";

    JpegBytes2Disk() {
    }

    public ImageCapture.OutputFileResults apply(In in) throws ImageCaptureException {
        Packet<byte[]> packet = in.getPacket();
        ImageCapture.OutputFileOptions outputFileOptions = in.getOutputFileOptions();
        File createTempFile = createTempFile(outputFileOptions);
        writeBytesToFile(createTempFile, packet.getData());
        updateFileExif(createTempFile, (Exif) Objects.requireNonNull(packet.getExif()), outputFileOptions, packet.getRotationDegrees());
        return new ImageCapture.OutputFileResults(copyFileToTarget(createTempFile, outputFileOptions));
    }

    private static File createTempFile(ImageCapture.OutputFileOptions outputFileOptions) throws ImageCaptureException {
        try {
            File file = outputFileOptions.getFile();
            if (file != null) {
                return new File(file.getParent(), TEMP_FILE_PREFIX + UUID.randomUUID().toString() + TEMP_FILE_SUFFIX);
            }
            return File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX);
        } catch (IOException e) {
            throw new ImageCaptureException(1, "Failed to create temp file.", e);
        }
    }

    private static void writeBytesToFile(File file, byte[] bArr) throws ImageCaptureException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            return;
        } catch (IOException e) {
            throw new ImageCaptureException(1, "Failed to write to temp file", e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static void updateFileExif(File file, Exif exif, ImageCapture.OutputFileOptions outputFileOptions, int i) throws ImageCaptureException {
        try {
            Exif createFromFile = Exif.createFromFile(file);
            exif.copyToCroppedImage(createFromFile);
            if (createFromFile.getRotation() == 0 && i != 0) {
                createFromFile.rotate(i);
            }
            ImageCapture.Metadata metadata = outputFileOptions.getMetadata();
            if (metadata.isReversedHorizontal()) {
                createFromFile.flipHorizontally();
            }
            if (metadata.isReversedVertical()) {
                createFromFile.flipVertically();
            }
            if (metadata.getLocation() != null) {
                createFromFile.attachLocation(metadata.getLocation());
            }
            createFromFile.save();
        } catch (IOException e) {
            throw new ImageCaptureException(1, "Failed to update Exif data", e);
        }
    }

    private static Uri copyFileToTarget(File file, ImageCapture.OutputFileOptions outputFileOptions) throws ImageCaptureException {
        if (isSaveToMediaStore(outputFileOptions)) {
            return copyFileToMediaStore(file, outputFileOptions);
        }
        if (isSaveToOutputStream(outputFileOptions)) {
            try {
                copyFileToOutputStream(file, (OutputStream) Objects.requireNonNull(outputFileOptions.getOutputStream()));
                return null;
            } catch (IOException unused) {
                throw new ImageCaptureException(1, "Failed to write to OutputStream.", (Throwable) null);
            }
        } else if (isSaveToFile(outputFileOptions)) {
            return copyFileToFile(file, (File) Objects.requireNonNull(outputFileOptions.getFile()));
        } else {
            throw new ImageCaptureException(0, "Invalid OutputFileOptions", (Throwable) null);
        }
    }

    private static Uri copyFileToMediaStore(File file, ImageCapture.OutputFileOptions outputFileOptions) throws ImageCaptureException {
        ContentValues contentValues;
        ContentResolver contentResolver = (ContentResolver) Objects.requireNonNull(outputFileOptions.getContentResolver());
        if (outputFileOptions.getContentValues() != null) {
            contentValues = new ContentValues(outputFileOptions.getContentValues());
        } else {
            contentValues = new ContentValues();
        }
        setContentValuePendingFlag(contentValues, 1);
        Uri insert = contentResolver.insert(outputFileOptions.getSaveCollection(), contentValues);
        if (insert != null) {
            try {
                copyTempFileToUri(file, insert, contentResolver);
                updateUriPendingStatus(insert, contentResolver, 0);
                return insert;
            } catch (IOException e) {
                throw new ImageCaptureException(1, "Failed to write to MediaStore URI: " + insert, e);
            } catch (Throwable th) {
                updateUriPendingStatus(insert, contentResolver, 0);
                throw th;
            }
        } else {
            throw new ImageCaptureException(1, "Failed to insert a MediaStore URI.", (Throwable) null);
        }
    }

    private static Uri copyFileToFile(File file, File file2) throws ImageCaptureException {
        if (file2.exists()) {
            file2.delete();
        }
        if (file.renameTo(file2)) {
            return Uri.fromFile(file2);
        }
        throw new ImageCaptureException(1, "Failed to overwrite the file: " + file2.getAbsolutePath(), (Throwable) null);
    }

    private static void copyTempFileToUri(File file, Uri uri, ContentResolver contentResolver) throws IOException {
        OutputStream openOutputStream = contentResolver.openOutputStream(uri);
        if (openOutputStream != null) {
            try {
                copyFileToOutputStream(file, openOutputStream);
                if (openOutputStream != null) {
                    openOutputStream.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            throw new FileNotFoundException(uri + " cannot be resolved.");
        }
        throw th;
    }

    private static void copyFileToOutputStream(File file, OutputStream outputStream) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    outputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return;
                }
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static void updateUriPendingStatus(Uri uri, ContentResolver contentResolver, int i) {
        ContentValues contentValues = new ContentValues();
        setContentValuePendingFlag(contentValues, i);
        contentResolver.update(uri, contentValues, (String) null, (String[]) null);
    }

    private static void setContentValuePendingFlag(ContentValues contentValues, int i) {
        contentValues.put("is_pending", Integer.valueOf(i));
    }

    private static boolean isSaveToMediaStore(ImageCapture.OutputFileOptions outputFileOptions) {
        return (outputFileOptions.getSaveCollection() == null || outputFileOptions.getContentResolver() == null || outputFileOptions.getContentValues() == null) ? false : true;
    }

    private static boolean isSaveToFile(ImageCapture.OutputFileOptions outputFileOptions) {
        return outputFileOptions.getFile() != null;
    }

    private static boolean isSaveToOutputStream(ImageCapture.OutputFileOptions outputFileOptions) {
        return outputFileOptions.getOutputStream() != null;
    }

    static abstract class In {
        /* access modifiers changed from: package-private */
        public abstract ImageCapture.OutputFileOptions getOutputFileOptions();

        /* access modifiers changed from: package-private */
        public abstract Packet<byte[]> getPacket();

        In() {
        }

        static In of(Packet<byte[]> packet, ImageCapture.OutputFileOptions outputFileOptions) {
            return new AutoValue_JpegBytes2Disk_In(packet, outputFileOptions);
        }
    }
}
