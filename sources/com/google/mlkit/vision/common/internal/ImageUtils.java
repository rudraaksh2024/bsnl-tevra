package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public class ImageUtils {
    private static final GmsLogger zza = new GmsLogger("MLKitImageUtils", "");
    private static final ImageUtils zzb = new ImageUtils();

    private ImageUtils() {
    }

    public static ImageUtils getInstance() {
        return zzb;
    }

    public IObjectWrapper getImageDataWrapper(InputImage inputImage) throws MlKitException {
        int format = inputImage.getFormat();
        if (format == -1) {
            return ObjectWrapper.wrap((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal()));
        }
        if (format != 17) {
            if (format == 35) {
                return ObjectWrapper.wrap(inputImage.getMediaImage());
            }
            if (format != 842094169) {
                int format2 = inputImage.getFormat();
                throw new MlKitException("Unsupported image format: " + format2, 3);
            }
        }
        return ObjectWrapper.wrap((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer()));
    }

    public int getMobileVisionImageFormat(InputImage inputImage) {
        return inputImage.getFormat();
    }

    public int getMobileVisionImageSize(InputImage inputImage) {
        if (inputImage.getFormat() == -1) {
            return ((Bitmap) Preconditions.checkNotNull(inputImage.getBitmapInternal())).getAllocationByteCount();
        }
        if (inputImage.getFormat() == 17 || inputImage.getFormat() == 842094169) {
            return ((ByteBuffer) Preconditions.checkNotNull(inputImage.getByteBuffer())).limit();
        }
        if (inputImage.getFormat() != 35) {
            return 0;
        }
        return (((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()))[0].getBuffer().limit() * 3) / 2;
    }

    public Matrix getUprightRotationMatrix(int i, int i2, int i3) {
        if (i3 == 0) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postTranslate(((float) (-i)) / 2.0f, ((float) (-i2)) / 2.0f);
        matrix.postRotate((float) (i3 * 90));
        int i4 = i3 % 2;
        int i5 = i4 != 0 ? i2 : i;
        if (i4 == 0) {
            i = i2;
        }
        matrix.postTranslate(((float) i5) / 2.0f, ((float) i) / 2.0f);
        return matrix;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        throw r3;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004b */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006c A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006d A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008b A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x008d A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0091 A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0098 A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009c A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a3 A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a7 A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ae A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c3 A[Catch:{ FileNotFoundException -> 0x00d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[Catch:{ FileNotFoundException -> 0x00d0 }, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Bitmap zza(android.content.ContentResolver r10, android.net.Uri r11) throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r9 = "MLKitImageUtils"
            android.graphics.Bitmap r7 = android.provider.MediaStore.Images.Media.getBitmap(r10, r11)     // Catch:{ FileNotFoundException -> 0x00d0 }
            if (r7 == 0) goto L_0x00c8
            java.lang.String r0 = "content"
            java.lang.String r1 = r11.getScheme()     // Catch:{ FileNotFoundException -> 0x00d0 }
            boolean r0 = r0.equals(r1)     // Catch:{ FileNotFoundException -> 0x00d0 }
            r1 = 0
            r2 = 0
            if (r0 != 0) goto L_0x0023
            java.lang.String r0 = "file"
            java.lang.String r3 = r11.getScheme()     // Catch:{ FileNotFoundException -> 0x00d0 }
            boolean r0 = r0.equals(r3)     // Catch:{ FileNotFoundException -> 0x00d0 }
            if (r0 != 0) goto L_0x0023
            goto L_0x0073
        L_0x0023:
            r0 = 1
            java.io.InputStream r10 = r10.openInputStream(r11)     // Catch:{ IOException -> 0x0055 }
            if (r10 == 0) goto L_0x004c
            androidx.exifinterface.media.ExifInterface r3 = new androidx.exifinterface.media.ExifInterface     // Catch:{ all -> 0x0030 }
            r3.<init>((java.io.InputStream) r10)     // Catch:{ all -> 0x0030 }
            goto L_0x004d
        L_0x0030:
            r3 = move-exception
            r10.close()     // Catch:{ all -> 0x0035 }
            goto L_0x004b
        L_0x0035:
            r10 = move-exception
            java.lang.Class<java.lang.Throwable> r4 = java.lang.Throwable.class
            java.lang.String r5 = "addSuppressed"
            java.lang.Class[] r6 = new java.lang.Class[r0]     // Catch:{ Exception -> 0x004b }
            java.lang.Class<java.lang.Throwable> r8 = java.lang.Throwable.class
            r6[r2] = r8     // Catch:{ Exception -> 0x004b }
            java.lang.reflect.Method r4 = r4.getDeclaredMethod(r5, r6)     // Catch:{ Exception -> 0x004b }
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x004b }
            r5[r2] = r10     // Catch:{ Exception -> 0x004b }
            r4.invoke(r3, r5)     // Catch:{ Exception -> 0x004b }
        L_0x004b:
            throw r3     // Catch:{ IOException -> 0x0055 }
        L_0x004c:
            r3 = r1
        L_0x004d:
            if (r10 == 0) goto L_0x006a
            r10.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x006a
        L_0x0053:
            r10 = move-exception
            goto L_0x0057
        L_0x0055:
            r10 = move-exception
            r3 = r1
        L_0x0057:
            com.google.android.gms.common.internal.GmsLogger r4 = zza     // Catch:{ FileNotFoundException -> 0x00d0 }
            java.lang.String r5 = java.lang.String.valueOf(r11)     // Catch:{ FileNotFoundException -> 0x00d0 }
            java.lang.String r6 = "failed to open file to read rotation meta data: "
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ FileNotFoundException -> 0x00d0 }
            java.lang.String r5 = r6.concat(r5)     // Catch:{ FileNotFoundException -> 0x00d0 }
            r4.e(r9, r5, r10)     // Catch:{ FileNotFoundException -> 0x00d0 }
        L_0x006a:
            if (r3 != 0) goto L_0x006d
            goto L_0x0073
        L_0x006d:
            java.lang.String r10 = "Orientation"
            int r2 = r3.getAttributeInt(r10, r0)     // Catch:{ FileNotFoundException -> 0x00d0 }
        L_0x0073:
            android.graphics.Matrix r10 = new android.graphics.Matrix     // Catch:{ FileNotFoundException -> 0x00d0 }
            r10.<init>()     // Catch:{ FileNotFoundException -> 0x00d0 }
            int r3 = r7.getWidth()     // Catch:{ FileNotFoundException -> 0x00d0 }
            int r4 = r7.getHeight()     // Catch:{ FileNotFoundException -> 0x00d0 }
            r0 = -1028390912(0xffffffffc2b40000, float:-90.0)
            r5 = 1119092736(0x42b40000, float:90.0)
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r8 = 1065353216(0x3f800000, float:1.0)
            switch(r2) {
                case 2: goto L_0x00ae;
                case 3: goto L_0x00a7;
                case 4: goto L_0x00a3;
                case 5: goto L_0x009c;
                case 6: goto L_0x0098;
                case 7: goto L_0x0091;
                case 8: goto L_0x008d;
                default: goto L_0x008b;
            }     // Catch:{ FileNotFoundException -> 0x00d0 }
        L_0x008b:
            r5 = r1
            goto L_0x00b7
        L_0x008d:
            r10.postRotate(r0)     // Catch:{ FileNotFoundException -> 0x00d0 }
            goto L_0x00ac
        L_0x0091:
            r10.postRotate(r0)     // Catch:{ FileNotFoundException -> 0x00d0 }
            r10.postScale(r6, r8)     // Catch:{ FileNotFoundException -> 0x00d0 }
            goto L_0x00ac
        L_0x0098:
            r10.postRotate(r5)     // Catch:{ FileNotFoundException -> 0x00d0 }
            goto L_0x00ac
        L_0x009c:
            r10.postRotate(r5)     // Catch:{ FileNotFoundException -> 0x00d0 }
            r10.postScale(r6, r8)     // Catch:{ FileNotFoundException -> 0x00d0 }
            goto L_0x00ac
        L_0x00a3:
            r10.postScale(r8, r6)     // Catch:{ FileNotFoundException -> 0x00d0 }
            goto L_0x00ac
        L_0x00a7:
            r0 = 1127481344(0x43340000, float:180.0)
            r10.postRotate(r0)     // Catch:{ FileNotFoundException -> 0x00d0 }
        L_0x00ac:
            r5 = r10
            goto L_0x00b7
        L_0x00ae:
            android.graphics.Matrix r10 = new android.graphics.Matrix     // Catch:{ FileNotFoundException -> 0x00d0 }
            r10.<init>()     // Catch:{ FileNotFoundException -> 0x00d0 }
            r10.postScale(r6, r8)     // Catch:{ FileNotFoundException -> 0x00d0 }
            goto L_0x00ac
        L_0x00b7:
            if (r5 == 0) goto L_0x00c7
            r1 = 0
            r2 = 0
            r6 = 1
            r0 = r7
            android.graphics.Bitmap r10 = android.graphics.Bitmap.createBitmap(r0, r1, r2, r3, r4, r5, r6)     // Catch:{ FileNotFoundException -> 0x00d0 }
            if (r7 == r10) goto L_0x00c7
            r7.recycle()     // Catch:{ FileNotFoundException -> 0x00d0 }
            r7 = r10
        L_0x00c7:
            return r7
        L_0x00c8:
            java.io.IOException r10 = new java.io.IOException     // Catch:{ FileNotFoundException -> 0x00d0 }
            java.lang.String r0 = "The image Uri could not be resolved."
            r10.<init>(r0)     // Catch:{ FileNotFoundException -> 0x00d0 }
            throw r10     // Catch:{ FileNotFoundException -> 0x00d0 }
        L_0x00d0:
            r10 = move-exception
            com.google.android.gms.common.internal.GmsLogger r0 = zza
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.lang.String r1 = "Could not open file: "
            java.lang.String r11 = r1.concat(r11)
            r0.e(r9, r11, r10)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.common.internal.ImageUtils.zza(android.content.ContentResolver, android.net.Uri):android.graphics.Bitmap");
    }
}
