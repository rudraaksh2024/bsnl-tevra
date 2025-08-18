package com.google.android.gms.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public interface FusedOrientationProviderClient extends HasApiKey<Api.ApiOptions.NoOptions> {
    Task<Void> removeOrientationUpdates(DeviceOrientationListener deviceOrientationListener);

    Task<Void> requestOrientationUpdates(DeviceOrientationRequest deviceOrientationRequest, Executor executor, DeviceOrientationListener deviceOrientationListener);
}
