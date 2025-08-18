package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzdb implements RemoteCall {
    private final /* synthetic */ LocationSettingsRequest zza;

    /* synthetic */ zzdb(LocationSettingsRequest locationSettingsRequest) {
        this.zza = locationSettingsRequest;
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        zzdz zzdz = (zzdz) obj;
        int i = zzda.zza;
        LocationSettingsRequest locationSettingsRequest = this.zza;
        Preconditions.checkArgument(locationSettingsRequest != null, "locationSettingsRequest can't be null");
        ((zzv) zzdz.getService()).zzD(locationSettingsRequest, new zzde(taskCompletionSource), (String) null);
    }
}
