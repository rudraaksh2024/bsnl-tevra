package androidx.camera.camera2.internal;

import android.content.Context;
import android.media.CamcorderProfile;
import android.util.Size;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Camera2DeviceSurfaceManager implements CameraDeviceSurfaceManager {
    private static final String TAG = "Camera2DeviceSurfaceManager";
    private final CamcorderProfileHelper mCamcorderProfileHelper;
    private final Map<String, SupportedSurfaceCombination> mCameraSupportedSurfaceCombinationMap;

    public Camera2DeviceSurfaceManager(Context context, Object obj, Set<String> set) throws CameraUnavailableException {
        this(context, new CamcorderProfileHelper() {
            public boolean hasProfile(int i, int i2) {
                return CamcorderProfile.hasProfile(i, i2);
            }

            public CamcorderProfile get(int i, int i2) {
                return CamcorderProfile.get(i, i2);
            }
        }, obj, set);
    }

    Camera2DeviceSurfaceManager(Context context, CamcorderProfileHelper camcorderProfileHelper, Object obj, Set<String> set) throws CameraUnavailableException {
        CameraManagerCompat cameraManagerCompat;
        this.mCameraSupportedSurfaceCombinationMap = new HashMap();
        Preconditions.checkNotNull(camcorderProfileHelper);
        this.mCamcorderProfileHelper = camcorderProfileHelper;
        if (obj instanceof CameraManagerCompat) {
            cameraManagerCompat = (CameraManagerCompat) obj;
        } else {
            cameraManagerCompat = CameraManagerCompat.from(context);
        }
        init(context, cameraManagerCompat, set);
    }

    private void init(Context context, CameraManagerCompat cameraManagerCompat, Set<String> set) throws CameraUnavailableException {
        Preconditions.checkNotNull(context);
        for (String next : set) {
            this.mCameraSupportedSurfaceCombinationMap.put(next, new SupportedSurfaceCombination(context, next, cameraManagerCompat, this.mCamcorderProfileHelper));
        }
    }

    public boolean checkSupported(String str, List<SurfaceConfig> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        SupportedSurfaceCombination supportedSurfaceCombination = this.mCameraSupportedSurfaceCombinationMap.get(str);
        if (supportedSurfaceCombination != null) {
            return supportedSurfaceCombination.checkSupported(list);
        }
        return false;
    }

    public SurfaceConfig transformSurfaceConfig(String str, int i, Size size) {
        SupportedSurfaceCombination supportedSurfaceCombination = this.mCameraSupportedSurfaceCombinationMap.get(str);
        if (supportedSurfaceCombination != null) {
            return supportedSurfaceCombination.transformSurfaceConfig(i, size);
        }
        return null;
    }

    public Map<UseCaseConfig<?>, Size> getSuggestedResolutions(String str, List<AttachedSurfaceInfo> list, List<UseCaseConfig<?>> list2) {
        Preconditions.checkArgument(!list2.isEmpty(), "No new use cases to be bound.");
        SupportedSurfaceCombination supportedSurfaceCombination = this.mCameraSupportedSurfaceCombinationMap.get(str);
        if (supportedSurfaceCombination != null) {
            return supportedSurfaceCombination.getSuggestedResolutions(list, list2);
        }
        throw new IllegalArgumentException("No such camera id in supported combination list: " + str);
    }
}
