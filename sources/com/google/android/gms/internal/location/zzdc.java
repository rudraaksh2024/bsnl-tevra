package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final /* synthetic */ class zzdc implements RemoteCall {
    static final /* synthetic */ zzdc zza = new zzdc();

    private /* synthetic */ zzdc() {
    }

    public final /* synthetic */ void accept(Object obj, Object obj2) {
        ((zzv) ((zzdz) obj).getService()).zzE(new zzdm((TaskCompletionSource) obj2));
    }
}
