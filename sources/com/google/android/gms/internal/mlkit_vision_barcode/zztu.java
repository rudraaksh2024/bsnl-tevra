package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.vision.barcode.internal.zzj;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final /* synthetic */ class zztu implements Runnable {
    public final /* synthetic */ zztx zza;
    public final /* synthetic */ zzpk zzb;
    public final /* synthetic */ Object zzc;
    public final /* synthetic */ long zzd;
    public final /* synthetic */ zzj zze;

    public /* synthetic */ zztu(zztx zztx, zzpk zzpk, Object obj, long j, zzj zzj) {
        this.zza = zztx;
        this.zzb = zzpk;
        this.zzc = obj;
        this.zzd = j;
        this.zze = zzj;
    }

    public final void run() {
        this.zza.zzh(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
