package androidx.camera.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Rational;
import android.util.Size;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.Logger;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.ViewPort;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.view.internal.compat.quirk.DeviceQuirks;
import androidx.camera.view.internal.compat.quirk.SurfaceViewNotCroppedByParentQuirk;
import androidx.camera.view.internal.compat.quirk.SurfaceViewStretchedQuirk;
import androidx.camera.view.transform.OutputTransform;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.messaging.Constants;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public final class PreviewView extends FrameLayout {
    static final int DEFAULT_BACKGROUND_COLOR = 17170444;
    private static final ImplementationMode DEFAULT_IMPL_MODE = ImplementationMode.PERFORMANCE;
    private static final String TAG = "PreviewView";
    final AtomicReference<PreviewStreamStateObserver> mActiveStreamStateObserver;
    CameraController mCameraController;
    CameraInfoInternal mCameraInfoInternal;
    private final DisplayRotationListener mDisplayRotationListener;
    PreviewViewImplementation mImplementation;
    ImplementationMode mImplementationMode;
    OnFrameUpdateListener mOnFrameUpdateListener;
    Executor mOnFrameUpdateListenerExecutor;
    private final View.OnLayoutChangeListener mOnLayoutChangeListener;
    final MutableLiveData<StreamState> mPreviewStreamStateLiveData;
    final PreviewTransformation mPreviewTransform;
    PreviewViewMeteringPointFactory mPreviewViewMeteringPointFactory;
    private final ScaleGestureDetector mScaleGestureDetector;
    final Preview.SurfaceProvider mSurfaceProvider;
    private MotionEvent mTouchUpEvent;
    boolean mUseDisplayRotation;

    public interface OnFrameUpdateListener {
        void onFrameUpdate(long j);
    }

    public enum StreamState {
        IDLE,
        STREAMING
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-view-PreviewView  reason: not valid java name */
    public /* synthetic */ void m192lambda$new$0$androidxcameraviewPreviewView(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if ((i3 - i == i7 - i5 && i4 - i2 == i8 - i6) ? false : true) {
            redrawPreview();
            attachToControllerIfReady(true);
        }
    }

    public PreviewView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PreviewView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PreviewView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    /* JADX INFO: finally extract failed */
    public PreviewView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        ImplementationMode implementationMode = DEFAULT_IMPL_MODE;
        this.mImplementationMode = implementationMode;
        PreviewTransformation previewTransformation = new PreviewTransformation();
        this.mPreviewTransform = previewTransformation;
        this.mUseDisplayRotation = true;
        this.mPreviewStreamStateLiveData = new MutableLiveData<>(StreamState.IDLE);
        this.mActiveStreamStateObserver = new AtomicReference<>();
        this.mPreviewViewMeteringPointFactory = new PreviewViewMeteringPointFactory(previewTransformation);
        this.mDisplayRotationListener = new DisplayRotationListener();
        this.mOnLayoutChangeListener = new PreviewView$$ExternalSyntheticLambda0(this);
        this.mSurfaceProvider = new Preview.SurfaceProvider() {
            public void onSurfaceRequested(SurfaceRequest surfaceRequest) {
                PreviewViewImplementation previewViewImplementation;
                if (!Threads.isMainThread()) {
                    ContextCompat.getMainExecutor(PreviewView.this.getContext()).execute(new PreviewView$1$$ExternalSyntheticLambda1(this, surfaceRequest));
                    return;
                }
                Logger.d(PreviewView.TAG, "Surface requested by Preview.");
                CameraInternal camera = surfaceRequest.getCamera();
                PreviewView.this.mCameraInfoInternal = camera.getCameraInfoInternal();
                surfaceRequest.setTransformationInfoListener(ContextCompat.getMainExecutor(PreviewView.this.getContext()), new PreviewView$1$$ExternalSyntheticLambda2(this, camera, surfaceRequest));
                PreviewView previewView = PreviewView.this;
                if (PreviewView.shouldUseTextureView(surfaceRequest, previewView.mImplementationMode)) {
                    PreviewView previewView2 = PreviewView.this;
                    previewViewImplementation = new TextureViewImplementation(previewView2, previewView2.mPreviewTransform);
                } else {
                    PreviewView previewView3 = PreviewView.this;
                    previewViewImplementation = new SurfaceViewImplementation(previewView3, previewView3.mPreviewTransform);
                }
                previewView.mImplementation = previewViewImplementation;
                PreviewStreamStateObserver previewStreamStateObserver = new PreviewStreamStateObserver(camera.getCameraInfoInternal(), PreviewView.this.mPreviewStreamStateLiveData, PreviewView.this.mImplementation);
                PreviewView.this.mActiveStreamStateObserver.set(previewStreamStateObserver);
                camera.getCameraState().addObserver(ContextCompat.getMainExecutor(PreviewView.this.getContext()), previewStreamStateObserver);
                PreviewView.this.mImplementation.onSurfaceRequested(surfaceRequest, new PreviewView$1$$ExternalSyntheticLambda3(this, previewStreamStateObserver, camera));
                if (PreviewView.this.mOnFrameUpdateListener != null && PreviewView.this.mOnFrameUpdateListenerExecutor != null) {
                    PreviewView.this.mImplementation.setFrameUpdateListener(PreviewView.this.mOnFrameUpdateListenerExecutor, PreviewView.this.mOnFrameUpdateListener);
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onSurfaceRequested$0$androidx-camera-view-PreviewView$1  reason: not valid java name */
            public /* synthetic */ void m193lambda$onSurfaceRequested$0$androidxcameraviewPreviewView$1(SurfaceRequest surfaceRequest) {
                PreviewView.this.mSurfaceProvider.onSurfaceRequested(surfaceRequest);
            }

            /* access modifiers changed from: package-private */
            /* JADX WARNING: Removed duplicated region for block: B:13:0x0050  */
            /* JADX WARNING: Removed duplicated region for block: B:14:0x0055  */
            /* renamed from: lambda$onSurfaceRequested$1$androidx-camera-view-PreviewView$1  reason: not valid java name */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public /* synthetic */ void m194lambda$onSurfaceRequested$1$androidxcameraviewPreviewView$1(androidx.camera.core.impl.CameraInternal r4, androidx.camera.core.SurfaceRequest r5, androidx.camera.core.SurfaceRequest.TransformationInfo r6) {
                /*
                    r3 = this;
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    java.lang.String r1 = "Preview transformation info updated. "
                    r0.<init>(r1)
                    java.lang.StringBuilder r0 = r0.append(r6)
                    java.lang.String r0 = r0.toString()
                    java.lang.String r1 = "PreviewView"
                    androidx.camera.core.Logger.d(r1, r0)
                    androidx.camera.core.impl.CameraInfoInternal r4 = r4.getCameraInfoInternal()
                    java.lang.Integer r4 = r4.getLensFacing()
                    r0 = 0
                    r2 = 1
                    if (r4 != 0) goto L_0x0027
                    java.lang.String r4 = "The lens facing is null, probably an external."
                    androidx.camera.core.Logger.w(r1, r4)
                L_0x0025:
                    r4 = r2
                    goto L_0x002f
                L_0x0027:
                    int r4 = r4.intValue()
                    if (r4 != 0) goto L_0x002e
                    goto L_0x0025
                L_0x002e:
                    r4 = r0
                L_0x002f:
                    androidx.camera.view.PreviewView r1 = androidx.camera.view.PreviewView.this
                    androidx.camera.view.PreviewTransformation r1 = r1.mPreviewTransform
                    android.util.Size r5 = r5.getResolution()
                    r1.setTransformationInfo(r6, r5, r4)
                    int r4 = r6.getTargetRotation()
                    r5 = -1
                    if (r4 == r5) goto L_0x0055
                    androidx.camera.view.PreviewView r4 = androidx.camera.view.PreviewView.this
                    androidx.camera.view.PreviewViewImplementation r4 = r4.mImplementation
                    if (r4 == 0) goto L_0x0050
                    androidx.camera.view.PreviewView r4 = androidx.camera.view.PreviewView.this
                    androidx.camera.view.PreviewViewImplementation r4 = r4.mImplementation
                    boolean r4 = r4 instanceof androidx.camera.view.SurfaceViewImplementation
                    if (r4 == 0) goto L_0x0050
                    goto L_0x0055
                L_0x0050:
                    androidx.camera.view.PreviewView r4 = androidx.camera.view.PreviewView.this
                    r4.mUseDisplayRotation = r0
                    goto L_0x0059
                L_0x0055:
                    androidx.camera.view.PreviewView r4 = androidx.camera.view.PreviewView.this
                    r4.mUseDisplayRotation = r2
                L_0x0059:
                    androidx.camera.view.PreviewView r4 = androidx.camera.view.PreviewView.this
                    r4.updateDisplayRotationIfNeeded()
                    androidx.camera.view.PreviewView r3 = androidx.camera.view.PreviewView.this
                    r3.redrawPreview()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.camera.view.PreviewView.AnonymousClass1.m194lambda$onSurfaceRequested$1$androidxcameraviewPreviewView$1(androidx.camera.core.impl.CameraInternal, androidx.camera.core.SurfaceRequest, androidx.camera.core.SurfaceRequest$TransformationInfo):void");
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onSurfaceRequested$2$androidx-camera-view-PreviewView$1  reason: not valid java name */
            public /* synthetic */ void m195lambda$onSurfaceRequested$2$androidxcameraviewPreviewView$1(PreviewStreamStateObserver previewStreamStateObserver, CameraInternal cameraInternal) {
                if (PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(PreviewView.this.mActiveStreamStateObserver, previewStreamStateObserver, (Object) null)) {
                    previewStreamStateObserver.updatePreviewStreamState(StreamState.IDLE);
                }
                previewStreamStateObserver.clear();
                cameraInternal.getCameraState().removeObserver(previewStreamStateObserver);
            }
        };
        Threads.checkMainThread();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PreviewView, i, i2);
        ViewCompat.saveAttributeDataForStyleable(this, context, R.styleable.PreviewView, attributeSet, obtainStyledAttributes, i, i2);
        try {
            setScaleType(ScaleType.fromId(obtainStyledAttributes.getInteger(R.styleable.PreviewView_scaleType, previewTransformation.getScaleType().getId())));
            setImplementationMode(ImplementationMode.fromId(obtainStyledAttributes.getInteger(R.styleable.PreviewView_implementationMode, implementationMode.getId())));
            obtainStyledAttributes.recycle();
            this.mScaleGestureDetector = new ScaleGestureDetector(context, new PinchToZoomOnScaleGestureListener());
            if (getBackground() == null) {
                setBackgroundColor(ContextCompat.getColor(getContext(), DEFAULT_BACKGROUND_COLOR));
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateDisplayRotationIfNeeded();
        startListeningToDisplayChange();
        addOnLayoutChangeListener(this.mOnLayoutChangeListener);
        PreviewViewImplementation previewViewImplementation = this.mImplementation;
        if (previewViewImplementation != null) {
            previewViewImplementation.onAttachedToWindow();
        }
        attachToControllerIfReady(true);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeOnLayoutChangeListener(this.mOnLayoutChangeListener);
        PreviewViewImplementation previewViewImplementation = this.mImplementation;
        if (previewViewImplementation != null) {
            previewViewImplementation.onDetachedFromWindow();
        }
        CameraController cameraController = this.mCameraController;
        if (cameraController != null) {
            cameraController.clearPreviewSurface();
        }
        stopListeningToDisplayChange();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mCameraController == null) {
            return super.onTouchEvent(motionEvent);
        }
        boolean z = motionEvent.getPointerCount() == 1;
        boolean z2 = motionEvent.getAction() == 1;
        boolean z3 = motionEvent.getEventTime() - motionEvent.getDownTime() < ((long) ViewConfiguration.getLongPressTimeout());
        if (z && z2 && z3) {
            this.mTouchUpEvent = motionEvent;
            performClick();
            return true;
        } else if (this.mScaleGestureDetector.onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean performClick() {
        if (this.mCameraController != null) {
            MotionEvent motionEvent = this.mTouchUpEvent;
            float x = motionEvent != null ? motionEvent.getX() : ((float) getWidth()) / 2.0f;
            MotionEvent motionEvent2 = this.mTouchUpEvent;
            this.mCameraController.onTapToFocus(this.mPreviewViewMeteringPointFactory, x, motionEvent2 != null ? motionEvent2.getY() : ((float) getHeight()) / 2.0f);
        }
        this.mTouchUpEvent = null;
        return super.performClick();
    }

    public void setImplementationMode(ImplementationMode implementationMode) {
        Threads.checkMainThread();
        this.mImplementationMode = implementationMode;
        if (implementationMode == ImplementationMode.PERFORMANCE && this.mOnFrameUpdateListener != null) {
            throw new IllegalArgumentException("PERFORMANCE mode doesn't support frame update listener");
        }
    }

    public ImplementationMode getImplementationMode() {
        Threads.checkMainThread();
        return this.mImplementationMode;
    }

    public Preview.SurfaceProvider getSurfaceProvider() {
        Threads.checkMainThread();
        return this.mSurfaceProvider;
    }

    public void setScaleType(ScaleType scaleType) {
        Threads.checkMainThread();
        this.mPreviewTransform.setScaleType(scaleType);
        redrawPreview();
        attachToControllerIfReady(false);
    }

    public ScaleType getScaleType() {
        Threads.checkMainThread();
        return this.mPreviewTransform.getScaleType();
    }

    public MeteringPointFactory getMeteringPointFactory() {
        Threads.checkMainThread();
        return this.mPreviewViewMeteringPointFactory;
    }

    public LiveData<StreamState> getPreviewStreamState() {
        return this.mPreviewStreamStateLiveData;
    }

    public Bitmap getBitmap() {
        Threads.checkMainThread();
        PreviewViewImplementation previewViewImplementation = this.mImplementation;
        if (previewViewImplementation == null) {
            return null;
        }
        return previewViewImplementation.getBitmap();
    }

    public ViewPort getViewPort() {
        Threads.checkMainThread();
        if (getDisplay() == null) {
            return null;
        }
        return getViewPort(getDisplay().getRotation());
    }

    public ViewPort getViewPort(int i) {
        Threads.checkMainThread();
        if (getWidth() == 0 || getHeight() == 0) {
            return null;
        }
        return new ViewPort.Builder(new Rational(getWidth(), getHeight()), i).setScaleType(getViewPortScaleType()).setLayoutDirection(getLayoutDirection()).build();
    }

    private int getViewPortScaleType() {
        switch (AnonymousClass2.$SwitchMap$androidx$camera$view$PreviewView$ScaleType[getScaleType().ordinal()]) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 0;
            case 4:
            case 5:
            case 6:
                return 3;
            default:
                throw new IllegalStateException("Unexpected scale type: " + getScaleType());
        }
    }

    /* access modifiers changed from: package-private */
    public void redrawPreview() {
        Threads.checkMainThread();
        PreviewViewImplementation previewViewImplementation = this.mImplementation;
        if (previewViewImplementation != null) {
            previewViewImplementation.redrawPreview();
        }
        this.mPreviewViewMeteringPointFactory.recalculate(new Size(getWidth(), getHeight()), getLayoutDirection());
        CameraController cameraController = this.mCameraController;
        if (cameraController != null) {
            cameraController.updatePreviewViewTransform(getOutputTransform());
        }
    }

    static boolean shouldUseTextureView(SurfaceRequest surfaceRequest, ImplementationMode implementationMode) {
        int i;
        boolean equals = surfaceRequest.getCamera().getCameraInfoInternal().getImplementationType().equals(CameraInfo.IMPLEMENTATION_TYPE_CAMERA2_LEGACY);
        boolean z = (DeviceQuirks.get(SurfaceViewStretchedQuirk.class) == null && DeviceQuirks.get(SurfaceViewNotCroppedByParentQuirk.class) == null) ? false : true;
        if (surfaceRequest.isRGBA8888Required() || equals || z || (i = AnonymousClass2.$SwitchMap$androidx$camera$view$PreviewView$ImplementationMode[implementationMode.ordinal()]) == 1) {
            return true;
        }
        if (i == 2) {
            return false;
        }
        throw new IllegalArgumentException("Invalid implementation mode: " + implementationMode);
    }

    /* renamed from: androidx.camera.view.PreviewView$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$view$PreviewView$ImplementationMode;
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$view$PreviewView$ScaleType;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0059 */
        static {
            /*
                androidx.camera.view.PreviewView$ImplementationMode[] r0 = androidx.camera.view.PreviewView.ImplementationMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$camera$view$PreviewView$ImplementationMode = r0
                r1 = 1
                androidx.camera.view.PreviewView$ImplementationMode r2 = androidx.camera.view.PreviewView.ImplementationMode.COMPATIBLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$androidx$camera$view$PreviewView$ImplementationMode     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.camera.view.PreviewView$ImplementationMode r3 = androidx.camera.view.PreviewView.ImplementationMode.PERFORMANCE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                androidx.camera.view.PreviewView$ScaleType[] r2 = androidx.camera.view.PreviewView.ScaleType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$androidx$camera$view$PreviewView$ScaleType = r2
                androidx.camera.view.PreviewView$ScaleType r3 = androidx.camera.view.PreviewView.ScaleType.FILL_END     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$androidx$camera$view$PreviewView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0038 }
                androidx.camera.view.PreviewView$ScaleType r2 = androidx.camera.view.PreviewView.ScaleType.FILL_CENTER     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r0 = $SwitchMap$androidx$camera$view$PreviewView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0043 }
                androidx.camera.view.PreviewView$ScaleType r1 = androidx.camera.view.PreviewView.ScaleType.FILL_START     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$androidx$camera$view$PreviewView$ScaleType     // Catch:{ NoSuchFieldError -> 0x004e }
                androidx.camera.view.PreviewView$ScaleType r1 = androidx.camera.view.PreviewView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x004e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$androidx$camera$view$PreviewView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0059 }
                androidx.camera.view.PreviewView$ScaleType r1 = androidx.camera.view.PreviewView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x0059 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0059 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0059 }
            L_0x0059:
                int[] r0 = $SwitchMap$androidx$camera$view$PreviewView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0064 }
                androidx.camera.view.PreviewView$ScaleType r1 = androidx.camera.view.PreviewView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.view.PreviewView.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void updateDisplayRotationIfNeeded() {
        Display display;
        CameraInfoInternal cameraInfoInternal;
        if (this.mUseDisplayRotation && (display = getDisplay()) != null && (cameraInfoInternal = this.mCameraInfoInternal) != null) {
            this.mPreviewTransform.overrideWithDisplayRotation(cameraInfoInternal.getSensorRotationDegrees(display.getRotation()), display.getRotation());
        }
    }

    public void setFrameUpdateListener(Executor executor, OnFrameUpdateListener onFrameUpdateListener) {
        if (this.mImplementationMode != ImplementationMode.PERFORMANCE) {
            this.mOnFrameUpdateListener = onFrameUpdateListener;
            this.mOnFrameUpdateListenerExecutor = executor;
            PreviewViewImplementation previewViewImplementation = this.mImplementation;
            if (previewViewImplementation != null) {
                previewViewImplementation.setFrameUpdateListener(executor, onFrameUpdateListener);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("PERFORMANCE mode doesn't support frame update listener");
    }

    public enum ImplementationMode {
        PERFORMANCE(0),
        COMPATIBLE(1);
        
        private final int mId;

        private ImplementationMode(int i) {
            this.mId = i;
        }

        /* access modifiers changed from: package-private */
        public int getId() {
            return this.mId;
        }

        static ImplementationMode fromId(int i) {
            for (ImplementationMode implementationMode : values()) {
                if (implementationMode.mId == i) {
                    return implementationMode;
                }
            }
            throw new IllegalArgumentException("Unknown implementation mode id " + i);
        }
    }

    public enum ScaleType {
        FILL_START(0),
        FILL_CENTER(1),
        FILL_END(2),
        FIT_START(3),
        FIT_CENTER(4),
        FIT_END(5);
        
        private final int mId;

        private ScaleType(int i) {
            this.mId = i;
        }

        /* access modifiers changed from: package-private */
        public int getId() {
            return this.mId;
        }

        static ScaleType fromId(int i) {
            for (ScaleType scaleType : values()) {
                if (scaleType.mId == i) {
                    return scaleType;
                }
            }
            throw new IllegalArgumentException("Unknown scale type id " + i);
        }
    }

    class PinchToZoomOnScaleGestureListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        PinchToZoomOnScaleGestureListener() {
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (PreviewView.this.mCameraController == null) {
                return true;
            }
            PreviewView.this.mCameraController.onPinchToZoom(scaleGestureDetector.getScaleFactor());
            return true;
        }
    }

    public void setController(CameraController cameraController) {
        Threads.checkMainThread();
        CameraController cameraController2 = this.mCameraController;
        if (!(cameraController2 == null || cameraController2 == cameraController)) {
            cameraController2.clearPreviewSurface();
        }
        this.mCameraController = cameraController;
        attachToControllerIfReady(false);
    }

    public CameraController getController() {
        Threads.checkMainThread();
        return this.mCameraController;
    }

    public OutputTransform getOutputTransform() {
        Matrix matrix;
        Threads.checkMainThread();
        try {
            matrix = this.mPreviewTransform.getSurfaceToPreviewViewMatrix(new Size(getWidth(), getHeight()), getLayoutDirection());
        } catch (IllegalStateException unused) {
            matrix = null;
        }
        Rect surfaceCropRect = this.mPreviewTransform.getSurfaceCropRect();
        if (matrix == null || surfaceCropRect == null) {
            Logger.d(TAG, "Transform info is not ready");
            return null;
        }
        matrix.preConcat(TransformUtils.getNormalizedToBuffer(surfaceCropRect));
        if (this.mImplementation instanceof TextureViewImplementation) {
            matrix.postConcat(getMatrix());
        } else {
            Logger.w(TAG, "PreviewView needs to be in COMPATIBLE mode for the transform to work correctly.");
        }
        return new OutputTransform(matrix, new Size(surfaceCropRect.width(), surfaceCropRect.height()));
    }

    private void attachToControllerIfReady(boolean z) {
        Threads.checkMainThread();
        Display display = getDisplay();
        ViewPort viewPort = getViewPort();
        if (this.mCameraController != null && viewPort != null && isAttachedToWindow() && display != null) {
            try {
                this.mCameraController.attachPreviewSurface(getSurfaceProvider(), viewPort, display);
            } catch (IllegalStateException e) {
                if (z) {
                    Logger.e(TAG, e.toString(), e);
                    return;
                }
                throw e;
            }
        }
    }

    private void startListeningToDisplayChange() {
        DisplayManager displayManager = getDisplayManager();
        if (displayManager != null) {
            displayManager.registerDisplayListener(this.mDisplayRotationListener, new Handler(Looper.getMainLooper()));
        }
    }

    private void stopListeningToDisplayChange() {
        DisplayManager displayManager = getDisplayManager();
        if (displayManager != null) {
            displayManager.unregisterDisplayListener(this.mDisplayRotationListener);
        }
    }

    private DisplayManager getDisplayManager() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        return (DisplayManager) context.getApplicationContext().getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
    }

    class DisplayRotationListener implements DisplayManager.DisplayListener {
        public void onDisplayAdded(int i) {
        }

        public void onDisplayRemoved(int i) {
        }

        DisplayRotationListener() {
        }

        public void onDisplayChanged(int i) {
            Display display = PreviewView.this.getDisplay();
            if (display != null && display.getDisplayId() == i) {
                PreviewView.this.updateDisplayRotationIfNeeded();
                PreviewView.this.redrawPreview();
            }
        }
    }
}
