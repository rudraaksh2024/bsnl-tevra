package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzpx implements Runnable {
    public final /* synthetic */ zzpz zza;
    public final /* synthetic */ zzpq zzb;
    public final /* synthetic */ zzln zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zzpx(zzpz zzpz, zzpq zzpq, zzln zzln, String str) {
        this.zza = zzpz;
        this.zzb = zzpq;
        this.zzc = zzln;
        this.zzd = str;
    }

    public final void run() {
        this.zza.zzb(this.zzb, this.zzc, this.zzd);
    }
}
