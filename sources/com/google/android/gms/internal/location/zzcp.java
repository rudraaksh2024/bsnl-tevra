package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzcp extends zzcq {
    final /* synthetic */ List zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcp(zzcr zzcr, GoogleApiClient googleApiClient, List list) {
        super(googleApiClient);
        this.zza = list;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzdz) anyClient).zzF(zzem.zza(this.zza), zzcr.zza(this));
    }
}
