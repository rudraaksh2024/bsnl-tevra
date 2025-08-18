package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.imagecapture.CaptureNode;
import androidx.camera.core.imagecapture.ProcessingNode;
import androidx.camera.core.impl.utils.Threads;
import androidx.core.util.Preconditions;
import java.util.Objects;

class SingleBundlingNode implements BundlingNode {
    private ProcessingNode.In mOutputEdge;
    private ProcessingRequest mPendingRequest;

    public void release() {
    }

    SingleBundlingNode() {
    }

    public ProcessingNode.In transform(CaptureNode.Out out) {
        out.getImageEdge().setListener(new SingleBundlingNode$$ExternalSyntheticLambda0(this));
        out.getRequestEdge().setListener(new SingleBundlingNode$$ExternalSyntheticLambda1(this));
        ProcessingNode.In of = ProcessingNode.In.of(out.getFormat());
        this.mOutputEdge = of;
        return of;
    }

    /* access modifiers changed from: private */
    public void trackIncomingRequest(ProcessingRequest processingRequest) {
        Threads.checkMainThread();
        boolean z = false;
        Preconditions.checkState(processingRequest.getStageIds().size() == 1, "Cannot handle multi-image capture.");
        if (this.mPendingRequest == null) {
            z = true;
        }
        Preconditions.checkState(z, "Already has an existing request.");
        this.mPendingRequest = processingRequest;
    }

    /* access modifiers changed from: private */
    public void matchImageWithRequest(ImageProxy imageProxy) {
        Threads.checkMainThread();
        boolean z = true;
        Preconditions.checkState(this.mPendingRequest != null);
        if (((Integer) Objects.requireNonNull(imageProxy.getImageInfo().getTagBundle().getTag(this.mPendingRequest.getTagBundleKey()))).intValue() != this.mPendingRequest.getStageIds().get(0).intValue()) {
            z = false;
        }
        Preconditions.checkState(z);
        this.mOutputEdge.getEdge().accept(ProcessingNode.InputPacket.of(this.mPendingRequest, imageProxy));
        this.mPendingRequest = null;
    }
}
