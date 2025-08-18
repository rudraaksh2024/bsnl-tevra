package androidx.camera.core.processing;

import android.opengl.EGLSurface;
import androidx.camera.core.processing.OpenGlRenderer;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_OpenGlRenderer_OutputSurface extends OpenGlRenderer.OutputSurface {
    private final EGLSurface eglSurface;
    private final int height;
    private final int width;

    AutoValue_OpenGlRenderer_OutputSurface(EGLSurface eGLSurface, int i, int i2) {
        if (eGLSurface != null) {
            this.eglSurface = eGLSurface;
            this.width = i;
            this.height = i2;
            return;
        }
        throw new NullPointerException("Null eglSurface");
    }

    /* access modifiers changed from: package-private */
    public EGLSurface getEglSurface() {
        return this.eglSurface;
    }

    /* access modifiers changed from: package-private */
    public int getWidth() {
        return this.width;
    }

    /* access modifiers changed from: package-private */
    public int getHeight() {
        return this.height;
    }

    public String toString() {
        return "OutputSurface{eglSurface=" + this.eglSurface + ", width=" + this.width + ", height=" + this.height + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OpenGlRenderer.OutputSurface)) {
            return false;
        }
        OpenGlRenderer.OutputSurface outputSurface = (OpenGlRenderer.OutputSurface) obj;
        if (this.eglSurface.equals(outputSurface.getEglSurface()) && this.width == outputSurface.getWidth() && this.height == outputSurface.getHeight()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.height ^ ((((this.eglSurface.hashCode() ^ 1000003) * 1000003) ^ this.width) * 1000003);
    }
}
