package androidx.camera.core.imagecapture;

import androidx.camera.core.imagecapture.ProcessingNode;
import androidx.camera.core.processing.Edge;
import org.apache.commons.math3.geometry.VectorFormat;

final class AutoValue_ProcessingNode_In extends ProcessingNode.In {
    private final Edge<ProcessingNode.InputPacket> edge;
    private final int format;

    AutoValue_ProcessingNode_In(Edge<ProcessingNode.InputPacket> edge2, int i) {
        if (edge2 != null) {
            this.edge = edge2;
            this.format = i;
            return;
        }
        throw new NullPointerException("Null edge");
    }

    /* access modifiers changed from: package-private */
    public Edge<ProcessingNode.InputPacket> getEdge() {
        return this.edge;
    }

    /* access modifiers changed from: package-private */
    public int getFormat() {
        return this.format;
    }

    public String toString() {
        return "In{edge=" + this.edge + ", format=" + this.format + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProcessingNode.In)) {
            return false;
        }
        ProcessingNode.In in = (ProcessingNode.In) obj;
        if (!this.edge.equals(in.getEdge()) || this.format != in.getFormat()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.format ^ ((this.edge.hashCode() ^ 1000003) * 1000003);
    }
}
