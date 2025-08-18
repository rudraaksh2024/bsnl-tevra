package androidx.camera.core.internal;

import android.graphics.Rect;
import android.media.ImageWriter;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.compat.ImageWriterCompat;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;

public class YuvToJpegProcessor implements CaptureProcessor {
    private static final String TAG = "YuvToJpegProcessor";
    private static final Rect UNINITIALIZED_RECT = new Rect(0, 0, 0, 0);
    CallbackToFutureAdapter.Completer<Void> mCloseCompleter;
    private ListenableFuture<Void> mCloseFuture;
    private boolean mClosed = false;
    private Rect mImageRect = UNINITIALIZED_RECT;
    private ImageWriter mImageWriter;
    private final Object mLock = new Object();
    private final int mMaxImages;
    private int mProcessingImages = 0;
    private int mQuality;
    private int mRotationDegrees = 0;

    public YuvToJpegProcessor(int i, int i2) {
        this.mQuality = i;
        this.mMaxImages = i2;
    }

    public void setJpegQuality(int i) {
        synchronized (this.mLock) {
            this.mQuality = i;
        }
    }

    public void setRotationDegrees(int i) {
        synchronized (this.mLock) {
            this.mRotationDegrees = i;
        }
    }

    public void onOutputSurface(Surface surface, int i) {
        Preconditions.checkState(i == 256, "YuvToJpegProcessor only supports JPEG output format.");
        synchronized (this.mLock) {
            if (this.mClosed) {
                Logger.w(TAG, "Cannot set output surface. Processor is closed.");
            } else if (this.mImageWriter == null) {
                this.mImageWriter = ImageWriterCompat.newInstance(surface, this.mMaxImages, i);
            } else {
                throw new IllegalStateException("Output surface already set.");
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: androidx.camera.core.ImageProxy} */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0141  */
    public void process(androidx.camera.core.impl.ImageProxyBundle r20) {
        /*
            r19 = this;
            r1 = r19
            java.util.List r0 = r20.getCaptureIds()
            int r2 = r0.size()
            r3 = 0
            r4 = 1
            if (r2 != r4) goto L_0x0010
            r2 = r4
            goto L_0x0011
        L_0x0010:
            r2 = r3
        L_0x0011:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Processing image bundle have single capture id, but found "
            r5.<init>(r6)
            int r6 = r0.size()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            androidx.core.util.Preconditions.checkArgument(r2, r5)
            java.lang.Object r0 = r0.get(r3)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r2 = r20
            com.google.common.util.concurrent.ListenableFuture r0 = r2.getImageProxy(r0)
            boolean r2 = r0.isDone()
            androidx.core.util.Preconditions.checkArgument(r2)
            java.lang.Object r2 = r1.mLock
            monitor-enter(r2)
            android.media.ImageWriter r5 = r1.mImageWriter     // Catch:{ all -> 0x01cc }
            boolean r6 = r1.mClosed     // Catch:{ all -> 0x01cc }
            if (r6 != 0) goto L_0x0049
            r6 = r4
            goto L_0x004a
        L_0x0049:
            r6 = r3
        L_0x004a:
            android.graphics.Rect r7 = r1.mImageRect     // Catch:{ all -> 0x01cc }
            if (r6 == 0) goto L_0x0053
            int r8 = r1.mProcessingImages     // Catch:{ all -> 0x01cc }
            int r8 = r8 + r4
            r1.mProcessingImages = r8     // Catch:{ all -> 0x01cc }
        L_0x0053:
            int r8 = r1.mQuality     // Catch:{ all -> 0x01cc }
            int r9 = r1.mRotationDegrees     // Catch:{ all -> 0x01cc }
            monitor-exit(r2)     // Catch:{ all -> 0x01cc }
            r2 = 0
            java.lang.Object r10 = r0.get()     // Catch:{ Exception -> 0x013c, all -> 0x0138 }
            androidx.camera.core.ImageProxy r10 = (androidx.camera.core.ImageProxy) r10     // Catch:{ Exception -> 0x013c, all -> 0x0138 }
            if (r6 != 0) goto L_0x0097
            java.lang.String r0 = "YuvToJpegProcessor"
            java.lang.String r7 = "Image enqueued for processing on closed processor."
            androidx.camera.core.Logger.w(r0, r7)     // Catch:{ Exception -> 0x0135, all -> 0x0132 }
            r10.close()     // Catch:{ Exception -> 0x0135, all -> 0x0132 }
            java.lang.Object r7 = r1.mLock
            monitor-enter(r7)
            if (r6 == 0) goto L_0x0080
            int r0 = r1.mProcessingImages     // Catch:{ all -> 0x007e }
            int r6 = r0 + -1
            r1.mProcessingImages = r6     // Catch:{ all -> 0x007e }
            if (r0 != 0) goto L_0x0080
            boolean r0 = r1.mClosed     // Catch:{ all -> 0x007e }
            if (r0 == 0) goto L_0x0080
            r3 = r4
            goto L_0x0080
        L_0x007e:
            r0 = move-exception
            goto L_0x0095
        L_0x0080:
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r0 = r1.mCloseCompleter     // Catch:{ all -> 0x007e }
            monitor-exit(r7)     // Catch:{ all -> 0x007e }
            if (r3 == 0) goto L_0x0094
            r5.close()
            java.lang.String r1 = "YuvToJpegProcessor"
            java.lang.String r3 = "Closed after completion of last image processed."
            androidx.camera.core.Logger.d(r1, r3)
            if (r0 == 0) goto L_0x0094
            r0.set(r2)
        L_0x0094:
            return
        L_0x0095:
            monitor-exit(r7)     // Catch:{ all -> 0x007e }
            throw r0
        L_0x0097:
            android.media.Image r11 = r5.dequeueInputImage()     // Catch:{ Exception -> 0x0135, all -> 0x0132 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x0130 }
            r12 = r0
            androidx.camera.core.ImageProxy r12 = (androidx.camera.core.ImageProxy) r12     // Catch:{ Exception -> 0x0130 }
            int r0 = r12.getFormat()     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            r10 = 35
            if (r0 != r10) goto L_0x00ac
            r0 = r4
            goto L_0x00ad
        L_0x00ac:
            r0 = r3
        L_0x00ad:
            java.lang.String r10 = "Input image is not expected YUV_420_888 image format"
            androidx.core.util.Preconditions.checkState(r0, r10)     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            byte[] r14 = androidx.camera.core.internal.utils.ImageUtil.yuv_420_888toNv21(r12)     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            android.graphics.YuvImage r0 = new android.graphics.YuvImage     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            r15 = 17
            int r16 = r12.getWidth()     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            int r17 = r12.getHeight()     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            r18 = 0
            r13 = r0
            r13.<init>(r14, r15, r16, r17, r18)     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            android.media.Image$Plane[] r10 = r11.getPlanes()     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            r10 = r10[r3]     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            java.nio.ByteBuffer r10 = r10.getBuffer()     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            int r13 = r10.position()     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            androidx.camera.core.impl.utils.ExifOutputStream r14 = new androidx.camera.core.impl.utils.ExifOutputStream     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            androidx.camera.core.internal.ByteBufferOutputStream r15 = new androidx.camera.core.internal.ByteBufferOutputStream     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            r15.<init>(r10)     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            androidx.camera.core.impl.utils.ExifData r9 = androidx.camera.core.impl.utils.ExifData.create(r12, r9)     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            r14.<init>(r15, r9)     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            r0.compressToJpeg(r7, r8, r14)     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            r12.close()     // Catch:{ Exception -> 0x012d, all -> 0x012a }
            int r0 = r10.position()     // Catch:{ Exception -> 0x0127, all -> 0x0124 }
            r10.limit(r0)     // Catch:{ Exception -> 0x0127, all -> 0x0124 }
            r10.position(r13)     // Catch:{ Exception -> 0x0127, all -> 0x0124 }
            r5.queueInputImage(r11)     // Catch:{ Exception -> 0x0127, all -> 0x0124 }
            java.lang.Object r7 = r1.mLock
            monitor-enter(r7)
            if (r6 == 0) goto L_0x010c
            int r0 = r1.mProcessingImages     // Catch:{ all -> 0x010a }
            int r6 = r0 + -1
            r1.mProcessingImages = r6     // Catch:{ all -> 0x010a }
            if (r0 != 0) goto L_0x010c
            boolean r0 = r1.mClosed     // Catch:{ all -> 0x010a }
            if (r0 == 0) goto L_0x010c
            r3 = r4
            goto L_0x010c
        L_0x010a:
            r0 = move-exception
            goto L_0x0122
        L_0x010c:
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r0 = r1.mCloseCompleter     // Catch:{ all -> 0x010a }
            monitor-exit(r7)     // Catch:{ all -> 0x010a }
            if (r3 == 0) goto L_0x01c9
            r5.close()
            java.lang.String r1 = "YuvToJpegProcessor"
            java.lang.String r3 = "Closed after completion of last image processed."
            androidx.camera.core.Logger.d(r1, r3)
            if (r0 == 0) goto L_0x01c9
        L_0x011d:
            r0.set(r2)
            goto L_0x01c9
        L_0x0122:
            monitor-exit(r7)     // Catch:{ all -> 0x010a }
            throw r0
        L_0x0124:
            r0 = move-exception
            r10 = r2
            goto L_0x0161
        L_0x0127:
            r0 = move-exception
            r10 = r2
            goto L_0x013f
        L_0x012a:
            r0 = move-exception
            r10 = r12
            goto L_0x0161
        L_0x012d:
            r0 = move-exception
            r10 = r12
            goto L_0x013f
        L_0x0130:
            r0 = move-exception
            goto L_0x013f
        L_0x0132:
            r0 = move-exception
            r11 = r2
            goto L_0x0161
        L_0x0135:
            r0 = move-exception
            r11 = r2
            goto L_0x013f
        L_0x0138:
            r0 = move-exception
            r10 = r2
            r11 = r10
            goto L_0x0161
        L_0x013c:
            r0 = move-exception
            r10 = r2
            r11 = r10
        L_0x013f:
            if (r6 == 0) goto L_0x0197
            java.lang.String r7 = "YuvToJpegProcessor"
            java.lang.String r8 = "Failed to process YUV -> JPEG"
            androidx.camera.core.Logger.e(r7, r8, r0)     // Catch:{ all -> 0x0160 }
            android.media.Image r11 = r5.dequeueInputImage()     // Catch:{ all -> 0x0160 }
            android.media.Image$Plane[] r0 = r11.getPlanes()     // Catch:{ all -> 0x0160 }
            r0 = r0[r3]     // Catch:{ all -> 0x0160 }
            java.nio.ByteBuffer r0 = r0.getBuffer()     // Catch:{ all -> 0x0160 }
            r0.rewind()     // Catch:{ all -> 0x0160 }
            r0.limit(r3)     // Catch:{ all -> 0x0160 }
            r5.queueInputImage(r11)     // Catch:{ all -> 0x0160 }
            goto L_0x0197
        L_0x0160:
            r0 = move-exception
        L_0x0161:
            java.lang.Object r7 = r1.mLock
            monitor-enter(r7)
            if (r6 == 0) goto L_0x0176
            int r6 = r1.mProcessingImages     // Catch:{ all -> 0x0174 }
            int r8 = r6 + -1
            r1.mProcessingImages = r8     // Catch:{ all -> 0x0174 }
            if (r6 != 0) goto L_0x0176
            boolean r6 = r1.mClosed     // Catch:{ all -> 0x0174 }
            if (r6 == 0) goto L_0x0176
            r3 = r4
            goto L_0x0176
        L_0x0174:
            r0 = move-exception
            goto L_0x0195
        L_0x0176:
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r1 = r1.mCloseCompleter     // Catch:{ all -> 0x0174 }
            monitor-exit(r7)     // Catch:{ all -> 0x0174 }
            if (r11 == 0) goto L_0x017e
            r11.close()
        L_0x017e:
            if (r10 == 0) goto L_0x0183
            r10.close()
        L_0x0183:
            if (r3 == 0) goto L_0x0194
            r5.close()
            java.lang.String r3 = "YuvToJpegProcessor"
            java.lang.String r4 = "Closed after completion of last image processed."
            androidx.camera.core.Logger.d(r3, r4)
            if (r1 == 0) goto L_0x0194
            r1.set(r2)
        L_0x0194:
            throw r0
        L_0x0195:
            monitor-exit(r7)     // Catch:{ all -> 0x0174 }
            throw r0
        L_0x0197:
            java.lang.Object r7 = r1.mLock
            monitor-enter(r7)
            if (r6 == 0) goto L_0x01ac
            int r0 = r1.mProcessingImages     // Catch:{ all -> 0x01aa }
            int r6 = r0 + -1
            r1.mProcessingImages = r6     // Catch:{ all -> 0x01aa }
            if (r0 != 0) goto L_0x01ac
            boolean r0 = r1.mClosed     // Catch:{ all -> 0x01aa }
            if (r0 == 0) goto L_0x01ac
            r3 = r4
            goto L_0x01ac
        L_0x01aa:
            r0 = move-exception
            goto L_0x01ca
        L_0x01ac:
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r0 = r1.mCloseCompleter     // Catch:{ all -> 0x01aa }
            monitor-exit(r7)     // Catch:{ all -> 0x01aa }
            if (r11 == 0) goto L_0x01b4
            r11.close()
        L_0x01b4:
            if (r10 == 0) goto L_0x01b9
            r10.close()
        L_0x01b9:
            if (r3 == 0) goto L_0x01c9
            r5.close()
            java.lang.String r1 = "YuvToJpegProcessor"
            java.lang.String r3 = "Closed after completion of last image processed."
            androidx.camera.core.Logger.d(r1, r3)
            if (r0 == 0) goto L_0x01c9
            goto L_0x011d
        L_0x01c9:
            return
        L_0x01ca:
            monitor-exit(r7)     // Catch:{ all -> 0x01aa }
            throw r0
        L_0x01cc:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x01cc }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.YuvToJpegProcessor.process(androidx.camera.core.impl.ImageProxyBundle):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        if (r4 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002f, code lost:
        r4.set(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            boolean r1 = r4.mClosed     // Catch:{ all -> 0x0033 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return
        L_0x0009:
            r1 = 1
            r4.mClosed = r1     // Catch:{ all -> 0x0033 }
            int r1 = r4.mProcessingImages     // Catch:{ all -> 0x0033 }
            r2 = 0
            if (r1 != 0) goto L_0x0024
            android.media.ImageWriter r1 = r4.mImageWriter     // Catch:{ all -> 0x0033 }
            if (r1 == 0) goto L_0x0024
            java.lang.String r1 = "YuvToJpegProcessor"
            java.lang.String r3 = "No processing in progress. Closing immediately."
            androidx.camera.core.Logger.d(r1, r3)     // Catch:{ all -> 0x0033 }
            android.media.ImageWriter r1 = r4.mImageWriter     // Catch:{ all -> 0x0033 }
            r1.close()     // Catch:{ all -> 0x0033 }
            androidx.concurrent.futures.CallbackToFutureAdapter$Completer<java.lang.Void> r4 = r4.mCloseCompleter     // Catch:{ all -> 0x0033 }
            goto L_0x002c
        L_0x0024:
            java.lang.String r4 = "YuvToJpegProcessor"
            java.lang.String r1 = "close() called while processing. Will close after completion."
            androidx.camera.core.Logger.d(r4, r1)     // Catch:{ all -> 0x0033 }
            r4 = r2
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            if (r4 == 0) goto L_0x0032
            r4.set(r2)
        L_0x0032:
            return
        L_0x0033:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.YuvToJpegProcessor.close():void");
    }

    public ListenableFuture<Void> getCloseFuture() {
        ListenableFuture<Void> listenableFuture;
        synchronized (this.mLock) {
            if (!this.mClosed || this.mProcessingImages != 0) {
                if (this.mCloseFuture == null) {
                    this.mCloseFuture = CallbackToFutureAdapter.getFuture(new YuvToJpegProcessor$$ExternalSyntheticLambda0(this));
                }
                listenableFuture = Futures.nonCancellationPropagating(this.mCloseFuture);
            } else {
                listenableFuture = Futures.immediateFuture(null);
            }
        }
        return listenableFuture;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getCloseFuture$0$androidx-camera-core-internal-YuvToJpegProcessor  reason: not valid java name */
    public /* synthetic */ Object m166lambda$getCloseFuture$0$androidxcameracoreinternalYuvToJpegProcessor(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.mLock) {
            this.mCloseCompleter = completer;
        }
        return "YuvToJpegProcessor-close";
    }

    public void onResolutionUpdate(Size size) {
        synchronized (this.mLock) {
            this.mImageRect = new Rect(0, 0, size.getWidth(), size.getHeight());
        }
    }
}
