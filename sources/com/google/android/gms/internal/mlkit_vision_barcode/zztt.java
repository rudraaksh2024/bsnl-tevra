package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final /* synthetic */ class zztt implements Runnable {
    public final /* synthetic */ zztx zza;
    public final /* synthetic */ zztm zzb;
    public final /* synthetic */ zzpk zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zztt(zztx zztx, zztm zztm, zzpk zzpk, String str) {
        this.zza = zztx;
        this.zzb = zztm;
        this.zzc = zzpk;
        this.zzd = str;
    }

    public final void run() {
        this.zza.zzc(this.zzb, this.zzc, this.zzd);
    }
}
