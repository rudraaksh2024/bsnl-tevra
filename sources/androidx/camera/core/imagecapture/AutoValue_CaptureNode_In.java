package androidx.camera.core.imagecapture;

import android.util.Size;
import androidx.camera.core.imagecapture.CaptureNode;
import androidx.camera.core.processing.Edge;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_CaptureNode_In extends CaptureNode.In {
    private final int format;
    private final Edge<ProcessingRequest> requestEdge;
    private final Size size;

    AutoValue_CaptureNode_In(Size size2, int i, Edge<ProcessingRequest> edge) {
        if (size2 != null) {
            this.size = size2;
            this.format = i;
            if (edge != null) {
                this.requestEdge = edge;
                return;
            }
            throw new NullPointerException("Null requestEdge");
        }
        throw new NullPointerException("Null size");
    }

    /* access modifiers changed from: package-private */
    public Size getSize() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public int getFormat() {
        return this.format;
    }

    /* access modifiers changed from: package-private */
    public Edge<ProcessingRequest> getRequestEdge() {
        return this.requestEdge;
    }

    public String toString() {
        return "In{size=" + this.size + ", format=" + this.format + ", requestEdge=" + this.requestEdge + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CaptureNode.In)) {
            return false;
        }
        CaptureNode.In in = (CaptureNode.In) obj;
        if (!this.size.equals(in.getSize()) || this.format != in.getFormat() || !this.requestEdge.equals(in.getRequestEdge())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.requestEdge.hashCode() ^ ((((this.size.hashCode() ^ 1000003) * 1000003) ^ this.format) * 1000003);
    }
}
