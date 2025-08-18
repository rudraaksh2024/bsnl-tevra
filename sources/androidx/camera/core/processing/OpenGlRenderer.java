package androidx.camera.core.processing;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.logging.log4j.message.ParameterizedMessage;

public final class OpenGlRenderer {
    private static final String DEFAULT_FRAGMENT_SHADER = String.format(Locale.US, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 %s;\nuniform samplerExternalOES %s;\nvoid main() {\n    gl_FragColor = texture2D(%s, %s);\n}\n", new Object[]{VAR_TEXTURE_COORD, VAR_TEXTURE, VAR_TEXTURE, VAR_TEXTURE_COORD});
    private static final String DEFAULT_VERTEX_SHADER = String.format(Locale.US, "uniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 %s;\nvoid main() {\n    gl_Position = aPosition;\n    %s = (uTexMatrix * aTextureCoord).xy;\n}\n", new Object[]{VAR_TEXTURE_COORD, VAR_TEXTURE_COORD});
    private static final int SIZEOF_FLOAT = 4;
    private static final String TAG = "OpenGlRenderer";
    private static final FloatBuffer TEX_BUF;
    private static final float[] TEX_COORDS;
    private static final int TEX_TARGET = 36197;
    private static final String VAR_TEXTURE = "sTexture";
    private static final String VAR_TEXTURE_COORD = "vTextureCoord";
    private static final FloatBuffer VERTEX_BUF;
    private static final float[] VERTEX_COORDS;
    private OutputSurface mCurrentOutputSurface;
    private EGLConfig mEglConfig;
    private EGLContext mEglContext = EGL14.EGL_NO_CONTEXT;
    private EGLDisplay mEglDisplay = EGL14.EGL_NO_DISPLAY;
    private Thread mGlThread;
    private final AtomicBoolean mInitialized = new AtomicBoolean(false);
    private final Map<Surface, OutputSurface> mOutputSurfaceMap = new HashMap();
    private int mPositionLoc = -1;
    private int mProgramHandle = -1;
    private EGLSurface mTempSurface = EGL14.EGL_NO_SURFACE;
    private int mTexCoordLoc = -1;
    private int mTexId = -1;
    private int mTexMatrixLoc = -1;

    static {
        float[] fArr = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
        VERTEX_COORDS = fArr;
        VERTEX_BUF = createFloatBuffer(fArr);
        float[] fArr2 = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
        TEX_COORDS = fArr2;
        TEX_BUF = createFloatBuffer(fArr2);
    }

    public void init(ShaderProvider shaderProvider) {
        checkInitializedOrThrow(false);
        try {
            createEglContext();
            createTempSurface();
            makeCurrent(this.mTempSurface);
            createProgram(shaderProvider);
            loadLocations();
            createTexture();
            this.mGlThread = Thread.currentThread();
            this.mInitialized.set(true);
        } catch (IllegalArgumentException | IllegalStateException e) {
            releaseInternal();
            throw e;
        }
    }

    public void release() {
        if (this.mInitialized.getAndSet(false)) {
            checkGlThreadOrThrow();
            releaseInternal();
        }
    }

    public void setOutputSurface(Surface surface) {
        checkInitializedOrThrow(true);
        checkGlThreadOrThrow();
        if (!this.mOutputSurfaceMap.containsKey(surface)) {
            EGLSurface createWindowSurface = createWindowSurface(this.mEglDisplay, (EGLConfig) Objects.requireNonNull(this.mEglConfig), surface);
            Size surfaceSize = getSurfaceSize(createWindowSurface);
            this.mOutputSurfaceMap.put(surface, OutputSurface.of(createWindowSurface, surfaceSize.getWidth(), surfaceSize.getHeight()));
        }
        OutputSurface outputSurface = (OutputSurface) Objects.requireNonNull(this.mOutputSurfaceMap.get(surface));
        this.mCurrentOutputSurface = outputSurface;
        makeCurrent(outputSurface.getEglSurface());
        GLES20.glViewport(0, 0, this.mCurrentOutputSurface.getWidth(), this.mCurrentOutputSurface.getHeight());
        GLES20.glScissor(0, 0, this.mCurrentOutputSurface.getWidth(), this.mCurrentOutputSurface.getHeight());
    }

    public int getTextureName() {
        checkInitializedOrThrow(true);
        checkGlThreadOrThrow();
        return this.mTexId;
    }

    public void render(long j, float[] fArr) {
        checkInitializedOrThrow(true);
        checkGlThreadOrThrow();
        if (this.mCurrentOutputSurface != null) {
            GLES20.glUseProgram(this.mProgramHandle);
            checkGlErrorOrThrow("glUseProgram");
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(TEX_TARGET, this.mTexId);
            GLES20.glUniformMatrix4fv(this.mTexMatrixLoc, 1, false, fArr, 0);
            checkGlErrorOrThrow("glUniformMatrix4fv");
            GLES20.glEnableVertexAttribArray(this.mPositionLoc);
            checkGlErrorOrThrow("glEnableVertexAttribArray");
            GLES20.glVertexAttribPointer(this.mPositionLoc, 2, 5126, false, 0, VERTEX_BUF);
            checkGlErrorOrThrow("glVertexAttribPointer");
            GLES20.glEnableVertexAttribArray(this.mTexCoordLoc);
            checkGlErrorOrThrow("glEnableVertexAttribArray");
            GLES20.glVertexAttribPointer(this.mTexCoordLoc, 2, 5126, false, 0, TEX_BUF);
            checkGlErrorOrThrow("glVertexAttribPointer");
            GLES20.glDrawArrays(5, 0, 4);
            checkGlErrorOrThrow("glDrawArrays");
            GLES20.glDisableVertexAttribArray(this.mPositionLoc);
            GLES20.glDisableVertexAttribArray(this.mTexCoordLoc);
            GLES20.glUseProgram(0);
            GLES20.glBindTexture(TEX_TARGET, 0);
            EGLExt.eglPresentationTimeANDROID(this.mEglDisplay, this.mCurrentOutputSurface.getEglSurface(), j);
            if (!EGL14.eglSwapBuffers(this.mEglDisplay, this.mCurrentOutputSurface.getEglSurface())) {
                Logger.w(TAG, "Failed to swap buffers with EGL error: 0x" + Integer.toHexString(EGL14.eglGetError()));
            }
        }
    }

    private void createEglContext() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        this.mEglDisplay = eglGetDisplay;
        if (!Objects.equals(eglGetDisplay, EGL14.EGL_NO_DISPLAY)) {
            int[] iArr = new int[2];
            if (EGL14.eglInitialize(this.mEglDisplay, iArr, 0, iArr, 1)) {
                EGLConfig[] eGLConfigArr = new EGLConfig[1];
                if (EGL14.eglChooseConfig(this.mEglDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12610, 1, 12339, 5, 12344}, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
                    EGLConfig eGLConfig = eGLConfigArr[0];
                    EGLContext eglCreateContext = EGL14.eglCreateContext(this.mEglDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
                    checkEglErrorOrThrow("eglCreateContext");
                    this.mEglConfig = eGLConfig;
                    this.mEglContext = eglCreateContext;
                    int[] iArr2 = new int[1];
                    EGL14.eglQueryContext(this.mEglDisplay, eglCreateContext, 12440, iArr2, 0);
                    Log.d(TAG, "EGLContext created, client version " + iArr2[0]);
                    return;
                }
                throw new IllegalStateException("Unable to find a suitable EGLConfig");
            }
            this.mEglDisplay = EGL14.EGL_NO_DISPLAY;
            throw new IllegalStateException("Unable to initialize EGL14");
        }
        throw new IllegalStateException("Unable to get EGL14 display");
    }

    private void createTempSurface() {
        this.mTempSurface = createPBufferSurface(this.mEglDisplay, (EGLConfig) Objects.requireNonNull(this.mEglConfig), 1, 1);
    }

    private void makeCurrent(EGLSurface eGLSurface) {
        Preconditions.checkNotNull(this.mEglDisplay);
        Preconditions.checkNotNull(this.mEglContext);
        if (!EGL14.eglMakeCurrent(this.mEglDisplay, eGLSurface, eGLSurface, this.mEglContext)) {
            throw new IllegalStateException("eglMakeCurrent failed");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createProgram(androidx.camera.core.processing.ShaderProvider r9) {
        /*
            r8 = this;
            java.lang.String r0 = "glAttachShader"
            java.lang.String r1 = "Could not link program: "
            r2 = -1
            java.lang.String r3 = DEFAULT_VERTEX_SHADER     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x005b }
            r4 = 35633(0x8b31, float:4.9932E-41)
            int r3 = loadShader(r4, r3)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x005b }
            int r9 = r8.loadFragmentShader(r9)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0057 }
            int r4 = android.opengl.GLES20.glCreateProgram()     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0054 }
            java.lang.String r5 = "glCreateProgram"
            checkGlErrorOrThrow(r5)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            android.opengl.GLES20.glAttachShader(r4, r3)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            checkGlErrorOrThrow(r0)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            android.opengl.GLES20.glAttachShader(r4, r9)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            checkGlErrorOrThrow(r0)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            android.opengl.GLES20.glLinkProgram(r4)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            r0 = 1
            int[] r5 = new int[r0]     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            r6 = 35714(0x8b82, float:5.0046E-41)
            r7 = 0
            android.opengl.GLES20.glGetProgramiv(r4, r6, r5, r7)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            r5 = r5[r7]     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            if (r5 != r0) goto L_0x003b
            r8.mProgramHandle = r4     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            return
        L_0x003b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            r0.<init>(r1)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            java.lang.String r1 = android.opengl.GLES20.glGetProgramInfoLog(r4)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            java.lang.String r0 = r0.toString()     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            r8.<init>(r0)     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
            throw r8     // Catch:{ IllegalArgumentException | IllegalStateException -> 0x0052 }
        L_0x0052:
            r8 = move-exception
            goto L_0x005f
        L_0x0054:
            r8 = move-exception
            r4 = r2
            goto L_0x005f
        L_0x0057:
            r8 = move-exception
            r9 = r2
            r4 = r9
            goto L_0x005f
        L_0x005b:
            r8 = move-exception
            r9 = r2
            r3 = r9
            r4 = r3
        L_0x005f:
            if (r3 == r2) goto L_0x0064
            android.opengl.GLES20.glDeleteShader(r3)
        L_0x0064:
            if (r9 == r2) goto L_0x0069
            android.opengl.GLES20.glDeleteShader(r9)
        L_0x0069:
            if (r4 == r2) goto L_0x006e
            android.opengl.GLES20.glDeleteProgram(r4)
        L_0x006e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.processing.OpenGlRenderer.createProgram(androidx.camera.core.processing.ShaderProvider):void");
    }

    private void loadLocations() {
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.mProgramHandle, "aPosition");
        this.mPositionLoc = glGetAttribLocation;
        checkLocationOrThrow(glGetAttribLocation, "aPosition");
        int glGetAttribLocation2 = GLES20.glGetAttribLocation(this.mProgramHandle, "aTextureCoord");
        this.mTexCoordLoc = glGetAttribLocation2;
        checkLocationOrThrow(glGetAttribLocation2, "aTextureCoord");
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.mProgramHandle, "uTexMatrix");
        this.mTexMatrixLoc = glGetUniformLocation;
        checkLocationOrThrow(glGetUniformLocation, "uTexMatrix");
    }

    private void createTexture() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        checkGlErrorOrThrow("glGenTextures");
        int i = iArr[0];
        GLES20.glBindTexture(TEX_TARGET, i);
        checkGlErrorOrThrow("glBindTexture " + i);
        GLES20.glTexParameterf(TEX_TARGET, 10241, 9728.0f);
        GLES20.glTexParameterf(TEX_TARGET, TarConstants.DEFAULT_BLKSIZE, 9729.0f);
        GLES20.glTexParameteri(TEX_TARGET, 10242, 33071);
        GLES20.glTexParameteri(TEX_TARGET, 10243, 33071);
        checkGlErrorOrThrow("glTexParameter");
        this.mTexId = i;
    }

    private int loadFragmentShader(ShaderProvider shaderProvider) {
        if (shaderProvider == ShaderProvider.DEFAULT) {
            return loadShader(35632, DEFAULT_FRAGMENT_SHADER);
        }
        try {
            String createFragmentShader = shaderProvider.createFragmentShader(VAR_TEXTURE, VAR_TEXTURE_COORD);
            if (createFragmentShader != null && createFragmentShader.contains(VAR_TEXTURE_COORD) && createFragmentShader.contains(VAR_TEXTURE)) {
                return loadShader(35632, createFragmentShader);
            }
            throw new IllegalArgumentException("Invalid fragment shader");
        } catch (Throwable th) {
            if (th instanceof IllegalArgumentException) {
                throw th;
            }
            throw new IllegalArgumentException("Unable to compile fragment shader", th);
        }
    }

    private Size getSurfaceSize(EGLSurface eGLSurface) {
        return new Size(querySurface(this.mEglDisplay, eGLSurface, 12375), querySurface(this.mEglDisplay, eGLSurface, 12374));
    }

    private void releaseInternal() {
        int i = this.mProgramHandle;
        if (i != -1) {
            GLES20.glDeleteProgram(i);
            this.mProgramHandle = -1;
        }
        for (OutputSurface eglSurface : this.mOutputSurfaceMap.values()) {
            EGL14.eglDestroySurface(this.mEglDisplay, eglSurface.getEglSurface());
        }
        this.mOutputSurfaceMap.clear();
        if (!Objects.equals(this.mTempSurface, EGL14.EGL_NO_SURFACE)) {
            EGL14.eglDestroySurface(this.mEglDisplay, this.mTempSurface);
            this.mTempSurface = EGL14.EGL_NO_SURFACE;
        }
        if (!Objects.equals(this.mEglDisplay, EGL14.EGL_NO_DISPLAY)) {
            if (!Objects.equals(this.mEglContext, EGL14.EGL_NO_CONTEXT)) {
                EGL14.eglMakeCurrent(this.mEglDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, this.mEglContext);
                EGL14.eglDestroyContext(this.mEglDisplay, this.mEglContext);
                this.mEglContext = EGL14.EGL_NO_CONTEXT;
            }
            EGL14.eglTerminate(this.mEglDisplay);
            this.mEglDisplay = EGL14.EGL_NO_DISPLAY;
        }
        this.mEglConfig = null;
        this.mProgramHandle = -1;
        this.mTexMatrixLoc = -1;
        this.mPositionLoc = -1;
        this.mTexCoordLoc = -1;
        this.mTexId = -1;
        this.mCurrentOutputSurface = null;
        this.mGlThread = null;
    }

    private void checkInitializedOrThrow(boolean z) {
        Preconditions.checkState(z == this.mInitialized.get(), z ? "OpenGlRenderer is not initialized" : "OpenGlRenderer is already initialized");
    }

    private void checkGlThreadOrThrow() {
        Preconditions.checkState(this.mGlThread == Thread.currentThread(), "Method call must be called on the GL thread.");
    }

    private static EGLSurface createPBufferSurface(EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
        EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, eGLConfig, new int[]{12375, i, 12374, i2, 12344}, 0);
        checkEglErrorOrThrow("eglCreatePbufferSurface");
        if (eglCreatePbufferSurface != null) {
            return eglCreatePbufferSurface;
        }
        throw new IllegalStateException("surface was null");
    }

    private static EGLSurface createWindowSurface(EGLDisplay eGLDisplay, EGLConfig eGLConfig, Surface surface) {
        EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(eGLDisplay, eGLConfig, surface, new int[]{12344}, 0);
        checkEglErrorOrThrow("eglCreateWindowSurface");
        if (eglCreateWindowSurface != null) {
            return eglCreateWindowSurface;
        }
        throw new IllegalStateException("surface was null");
    }

    private static int loadShader(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        checkGlErrorOrThrow("glCreateShader type=" + i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        Logger.w(TAG, "Could not compile shader: " + str);
        GLES20.glDeleteShader(glCreateShader);
        throw new IllegalStateException("Could not compile shader type " + i + ParameterizedMessage.ERROR_MSG_SEPARATOR + GLES20.glGetShaderInfoLog(glCreateShader));
    }

    private static int querySurface(EGLDisplay eGLDisplay, EGLSurface eGLSurface, int i) {
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(eGLDisplay, eGLSurface, i, iArr, 0);
        return iArr[0];
    }

    public static FloatBuffer createFloatBuffer(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    private static void checkLocationOrThrow(int i, String str) {
        if (i < 0) {
            throw new IllegalStateException("Unable to locate '" + str + "' in program");
        }
    }

    private static void checkEglErrorOrThrow(String str) {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError != 12288) {
            throw new IllegalStateException(str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
        }
    }

    private static void checkGlErrorOrThrow(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            throw new IllegalStateException(str + ": GL error 0x" + Integer.toHexString(glGetError));
        }
    }

    static abstract class OutputSurface {
        /* access modifiers changed from: package-private */
        public abstract EGLSurface getEglSurface();

        /* access modifiers changed from: package-private */
        public abstract int getHeight();

        /* access modifiers changed from: package-private */
        public abstract int getWidth();

        OutputSurface() {
        }

        static OutputSurface of(EGLSurface eGLSurface, int i, int i2) {
            return new AutoValue_OpenGlRenderer_OutputSurface(eGLSurface, i, i2);
        }
    }
}
