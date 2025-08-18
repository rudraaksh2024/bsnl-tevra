package androidx.camera.core.impl;

import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public interface CaptureProcessor {
    void close() {
    }

    void onOutputSurface(Surface surface, int i);

    void onResolutionUpdate(Size size);

    void process(ImageProxyBundle imageProxyBundle);

    ListenableFuture<Void> getCloseFuture() {
        return Futures.immediateFuture(null);
    }
}
