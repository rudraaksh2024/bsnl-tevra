package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class BitmapLoadTask extends AsyncTask<Void, Void, BitmapWorkerResult> {
    private static final int MAX_BITMAP_SIZE = 104857600;
    private static final String TAG = "BitmapWorkerTask";
    private final BitmapLoadCallback mBitmapLoadCallback;
    private final WeakReference<Context> mContext;
    private Uri mInputUri;
    private Uri mOutputUri;
    private final int mRequiredHeight;
    private final int mRequiredWidth;

    public static class BitmapWorkerResult {
        Bitmap mBitmapResult;
        Exception mBitmapWorkerException;
        ExifInfo mExifInfo;

        public BitmapWorkerResult(Bitmap bitmap, ExifInfo exifInfo) {
            this.mBitmapResult = bitmap;
            this.mExifInfo = exifInfo;
        }

        public BitmapWorkerResult(Exception exc) {
            this.mBitmapWorkerException = exc;
        }
    }

    public BitmapLoadTask(Context context, Uri uri, Uri uri2, int i, int i2, BitmapLoadCallback bitmapLoadCallback) {
        this.mContext = new WeakReference<>(context);
        this.mInputUri = uri;
        this.mOutputUri = uri2;
        this.mRequiredWidth = i;
        this.mRequiredHeight = i2;
        this.mBitmapLoadCallback = bitmapLoadCallback;
    }

    /* access modifiers changed from: protected */
    public BitmapWorkerResult doInBackground(Void... voidArr) {
        InputStream openInputStream;
        Context context = (Context) this.mContext.get();
        if (context == null) {
            return new BitmapWorkerResult(new NullPointerException("context is null"));
        }
        if (this.mInputUri == null) {
            return new BitmapWorkerResult(new NullPointerException("Input Uri cannot be null"));
        }
        try {
            processInputUri();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = BitmapLoadUtils.calculateInSampleSize(options, this.mRequiredWidth, this.mRequiredHeight);
            boolean z = false;
            options.inJustDecodeBounds = false;
            Bitmap bitmap = null;
            while (!z) {
                try {
                    openInputStream = context.getContentResolver().openInputStream(this.mInputUri);
                    bitmap = BitmapFactory.decodeStream(openInputStream, (Rect) null, options);
                    if (options.outWidth == -1 || options.outHeight == -1) {
                        BitmapWorkerResult bitmapWorkerResult = new BitmapWorkerResult(new IllegalArgumentException("Bounds for bitmap could not be retrieved from the Uri: [" + this.mInputUri + "]"));
                        BitmapLoadUtils.close(openInputStream);
                        return bitmapWorkerResult;
                    }
                    BitmapLoadUtils.close(openInputStream);
                    if (!checkSize(bitmap, options)) {
                        z = true;
                    }
                } catch (OutOfMemoryError e) {
                    Log.e(TAG, "doInBackground: BitmapFactory.decodeFileDescriptor: ", e);
                    options.inSampleSize *= 2;
                } catch (IOException e2) {
                    Log.e(TAG, "doInBackground: ImageDecoder.createSource: ", e2);
                    return new BitmapWorkerResult(new IllegalArgumentException("Bitmap could not be decoded from the Uri: [" + this.mInputUri + "]", e2));
                } catch (Throwable th) {
                    BitmapLoadUtils.close(openInputStream);
                    throw th;
                }
            }
            if (bitmap == null) {
                return new BitmapWorkerResult(new IllegalArgumentException("Bitmap could not be decoded from the Uri: [" + this.mInputUri + "]"));
            }
            int exifOrientation = BitmapLoadUtils.getExifOrientation(context, this.mInputUri);
            int exifToDegrees = BitmapLoadUtils.exifToDegrees(exifOrientation);
            int exifToTranslation = BitmapLoadUtils.exifToTranslation(exifOrientation);
            ExifInfo exifInfo = new ExifInfo(exifOrientation, exifToDegrees, exifToTranslation);
            Matrix matrix = new Matrix();
            if (exifToDegrees != 0) {
                matrix.preRotate((float) exifToDegrees);
            }
            if (exifToTranslation != 1) {
                matrix.postScale((float) exifToTranslation, 1.0f);
            }
            if (!matrix.isIdentity()) {
                return new BitmapWorkerResult(BitmapLoadUtils.transformBitmap(bitmap, matrix), exifInfo);
            }
            return new BitmapWorkerResult(bitmap, exifInfo);
        } catch (IOException | NullPointerException e3) {
            return new BitmapWorkerResult(e3);
        }
    }

    private void processInputUri() throws NullPointerException, IOException {
        String scheme = this.mInputUri.getScheme();
        Log.d(TAG, "Uri scheme: " + scheme);
        if ("http".equals(scheme) || "https".equals(scheme)) {
            try {
                downloadFile(this.mInputUri, this.mOutputUri);
            } catch (IOException | NullPointerException e) {
                Log.e(TAG, "Downloading failed", e);
                throw e;
            }
        } else if (!"file".equals(scheme) && !"content".equals(scheme)) {
            Log.e(TAG, "Invalid Uri scheme " + scheme);
            throw new IllegalArgumentException("Invalid Uri scheme" + scheme);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void downloadFile(android.net.Uri r5, android.net.Uri r6) throws java.lang.NullPointerException, java.io.IOException {
        /*
            r4 = this;
            java.lang.String r0 = "BitmapWorkerTask"
            java.lang.String r1 = "downloadFile"
            android.util.Log.d(r0, r1)
            if (r6 == 0) goto L_0x009e
            java.lang.ref.WeakReference<android.content.Context> r0 = r4.mContext
            java.lang.Object r0 = r0.get()
            android.content.Context r0 = (android.content.Context) r0
            if (r0 == 0) goto L_0x0096
            com.yalantis.ucrop.OkHttpClientStore r1 = com.yalantis.ucrop.OkHttpClientStore.INSTANCE
            okhttp3.OkHttpClient r1 = r1.getClient()
            r2 = 0
            okhttp3.Request$Builder r3 = new okhttp3.Request$Builder     // Catch:{ all -> 0x0078 }
            r3.<init>()     // Catch:{ all -> 0x0078 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0078 }
            okhttp3.Request$Builder r5 = r3.url((java.lang.String) r5)     // Catch:{ all -> 0x0078 }
            okhttp3.Request r5 = r5.build()     // Catch:{ all -> 0x0078 }
            okhttp3.Call r5 = r1.newCall(r5)     // Catch:{ all -> 0x0078 }
            okhttp3.Response r5 = r5.execute()     // Catch:{ all -> 0x0078 }
            okhttp3.ResponseBody r3 = r5.body()     // Catch:{ all -> 0x0074 }
            okio.BufferedSource r3 = r3.source()     // Catch:{ all -> 0x0074 }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ all -> 0x006f }
            java.io.OutputStream r6 = r0.openOutputStream(r6)     // Catch:{ all -> 0x006f }
            if (r6 == 0) goto L_0x0067
            okio.Sink r2 = okio.Okio.sink((java.io.OutputStream) r6)     // Catch:{ all -> 0x006f }
            r3.readAll(r2)     // Catch:{ all -> 0x006f }
            com.yalantis.ucrop.util.BitmapLoadUtils.close(r3)
            com.yalantis.ucrop.util.BitmapLoadUtils.close(r2)
            if (r5 == 0) goto L_0x005b
            okhttp3.ResponseBody r5 = r5.body()
            com.yalantis.ucrop.util.BitmapLoadUtils.close(r5)
        L_0x005b:
            okhttp3.Dispatcher r5 = r1.dispatcher()
            r5.cancelAll()
            android.net.Uri r5 = r4.mOutputUri
            r4.mInputUri = r5
            return
        L_0x0067:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException     // Catch:{ all -> 0x006f }
            java.lang.String r0 = "OutputStream for given output Uri is null"
            r6.<init>(r0)     // Catch:{ all -> 0x006f }
            throw r6     // Catch:{ all -> 0x006f }
        L_0x006f:
            r6 = move-exception
            r0 = r5
            r5 = r2
            r2 = r3
            goto L_0x007b
        L_0x0074:
            r6 = move-exception
            r0 = r5
            r5 = r2
            goto L_0x007b
        L_0x0078:
            r6 = move-exception
            r5 = r2
            r0 = r5
        L_0x007b:
            com.yalantis.ucrop.util.BitmapLoadUtils.close(r2)
            com.yalantis.ucrop.util.BitmapLoadUtils.close(r5)
            if (r0 == 0) goto L_0x008a
            okhttp3.ResponseBody r5 = r0.body()
            com.yalantis.ucrop.util.BitmapLoadUtils.close(r5)
        L_0x008a:
            okhttp3.Dispatcher r5 = r1.dispatcher()
            r5.cancelAll()
            android.net.Uri r5 = r4.mOutputUri
            r4.mInputUri = r5
            throw r6
        L_0x0096:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "Context is null"
            r4.<init>(r5)
            throw r4
        L_0x009e:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "Output Uri is null - cannot download image"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yalantis.ucrop.task.BitmapLoadTask.downloadFile(android.net.Uri, android.net.Uri):void");
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(BitmapWorkerResult bitmapWorkerResult) {
        if (bitmapWorkerResult.mBitmapWorkerException == null) {
            this.mBitmapLoadCallback.onBitmapLoaded(bitmapWorkerResult.mBitmapResult, bitmapWorkerResult.mExifInfo, this.mInputUri, this.mOutputUri);
        } else {
            this.mBitmapLoadCallback.onFailure(bitmapWorkerResult.mBitmapWorkerException);
        }
    }

    private boolean checkSize(Bitmap bitmap, BitmapFactory.Options options) {
        if ((bitmap != null ? bitmap.getByteCount() : 0) <= MAX_BITMAP_SIZE) {
            return false;
        }
        options.inSampleSize *= 2;
        return true;
    }
}
