package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.CaptureNode;
import androidx.camera.core.processing.Edge;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_CaptureNode_Out extends CaptureNode.Out {
    private final int format;
    private final Edge<ImageProxy> imageEdge;
    private final Edge<ProcessingRequest> requestEdge;

    AutoValue_CaptureNode_Out(Edge<ImageProxy> edge, Edge<ProcessingRequest> edge2, int i) {
        if (edge != null) {
            this.imageEdge = edge;
            if (edge2 != null) {
                this.requestEdge = edge2;
                this.format = i;
                return;
            }
            throw new NullPointerException("Null requestEdge");
        }
        throw new NullPointerException("Null imageEdge");
    }

    /* access modifiers changed from: package-private */
    public Edge<ImageProxy> getImageEdge() {
        return this.imageEdge;
    }

    /* access modifiers changed from: package-private */
    public Edge<ProcessingRequest> getRequestEdge() {
        return this.requestEdge;
    }

    /* access modifiers changed from: package-private */
    public int getFormat() {
        return this.format;
    }

    public String toString() {
        return "Out{imageEdge=" + this.imageEdge + ", requestEdge=" + this.requestEdge + ", format=" + this.format + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CaptureNode.Out)) {
            return false;
        }
        CaptureNode.Out out = (CaptureNode.Out) obj;
        if (!this.imageEdge.equals(out.getImageEdge()) || !this.requestEdge.equals(out.getRequestEdge()) || this.format != out.getFormat()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.format ^ ((((this.imageEdge.hashCode() ^ 1000003) * 1000003) ^ this.requestEdge.hashCode()) * 1000003);
    }
}
