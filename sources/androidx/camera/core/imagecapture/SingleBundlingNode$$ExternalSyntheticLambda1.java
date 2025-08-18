package androidx.camera.core.imagecapture;

import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SingleBundlingNode$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ SingleBundlingNode f$0;

    public /* synthetic */ SingleBundlingNode$$ExternalSyntheticLambda1(SingleBundlingNode singleBundlingNode) {
        this.f$0 = singleBundlingNode;
    }

    public final void accept(Object obj) {
        this.f$0.trackIncomingRequest((ProcessingRequest) obj);
    }
}
