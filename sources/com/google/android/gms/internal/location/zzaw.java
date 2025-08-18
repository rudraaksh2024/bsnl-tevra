package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzaw extends zzba {
    final /* synthetic */ LocationCallback zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaw(zzbb zzbb, GoogleApiClient googleApiClient, LocationCallback locationCallback) {
        super(googleApiClient);
        this.zza = locationCallback;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        Class<LocationCallback> cls = LocationCallback.class;
        ((zzdz) anyClient).zzw(ListenerHolders.createListenerKey(this.zza, "LocationCallback"), true, zzbb.zza(this));
    }
}
