package androidx.camera.camera2.internal;

import android.os.Build;
import androidx.camera.core.impl.ImageAnalysisConfig;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.PreviewConfig;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.VideoCaptureConfig;
import java.util.Collection;

public final class StreamUseCaseUtil {
    private StreamUseCaseUtil() {
    }

    public static long getStreamUseCaseFromUseCaseConfigs(Collection<UseCaseConfig<?>> collection, Collection<SessionConfig> collection2) {
        if (Build.VERSION.SDK_INT < 33) {
            return -1;
        }
        if (collection.isEmpty()) {
            return 0;
        }
        for (SessionConfig templateType : collection2) {
            if (templateType.getTemplateType() == 5) {
                return 0;
            }
        }
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (UseCaseConfig next : collection) {
            if (next instanceof ImageAnalysisConfig) {
                return 0;
            }
            if (next instanceof PreviewConfig) {
                z3 = true;
            } else if (next instanceof ImageCaptureConfig) {
                if (z2) {
                    return 4;
                }
                z = true;
            } else if (!(next instanceof VideoCaptureConfig)) {
                continue;
            } else if (z) {
                return 4;
            } else {
                z2 = true;
            }
        }
        if (z) {
            return 2;
        }
        if (z2) {
            return 3;
        }
        if (!z3) {
            return 0;
        }
        return 1;
    }
}
