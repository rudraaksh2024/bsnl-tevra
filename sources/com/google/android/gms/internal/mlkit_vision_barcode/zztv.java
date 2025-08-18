package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.vision.barcode.internal.zzj;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final /* synthetic */ class zztv implements Runnable {
    public final /* synthetic */ zztx zza;
    public final /* synthetic */ zzpk zzb;
    public final /* synthetic */ zzj zzc;

    public /* synthetic */ zztv(zztx zztx, zzpk zzpk, zzj zzj) {
        this.zza = zztx;
        this.zzb = zzpk;
        this.zzc = zzj;
    }

    public final void run() {
        this.zza.zzg(this.zzb, this.zzc);
    }
}
