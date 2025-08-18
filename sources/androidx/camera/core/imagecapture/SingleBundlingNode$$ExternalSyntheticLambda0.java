package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageProxy;
import androidx.core.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SingleBundlingNode$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ SingleBundlingNode f$0;

    public /* synthetic */ SingleBundlingNode$$ExternalSyntheticLambda0(SingleBundlingNode singleBundlingNode) {
        this.f$0 = singleBundlingNode;
    }

    public final void accept(Object obj) {
        this.f$0.matchImageWithRequest((ImageProxy) obj);
    }
}
