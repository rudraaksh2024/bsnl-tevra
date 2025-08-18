package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.model.RemoteModel;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzpy implements Runnable {
    public final /* synthetic */ zzpz zza;
    public final /* synthetic */ zzpq zzb;
    public final /* synthetic */ zzqb zzc;
    public final /* synthetic */ RemoteModel zzd;

    public /* synthetic */ zzpy(zzpz zzpz, zzpq zzpq, zzqb zzqb, RemoteModel remoteModel) {
        this.zza = zzpz;
        this.zzb = zzpq;
        this.zzc = zzqb;
        this.zzd = remoteModel;
    }

    public final void run() {
        this.zza.zzc(this.zzb, this.zzc, this.zzd);
    }
}
