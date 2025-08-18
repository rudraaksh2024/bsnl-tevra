package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.GmsLogger;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzur implements zzek {
    final /* synthetic */ zzpk zza;
    final /* synthetic */ float zzb;
    final /* synthetic */ zzuv zzc;
    final /* synthetic */ float zzd;
    final /* synthetic */ zzus zze;

    zzur(zzus zzus, zzpk zzpk, float f, zzuv zzuv, float f2) {
        this.zze = zzus;
        this.zza = zzpk;
        this.zzb = f;
        this.zzc = zzuv;
        this.zzd = f2;
    }

    public final void zza(Throwable th) {
        GmsLogger zzb2 = zzus.zzf;
        float f = this.zzd;
        zzb2.w("AutoZoom", "Unable to set zoom to " + f, th);
        this.zze.zzg.set(false);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        Float f = (Float) obj;
        if (f.floatValue() >= 1.0f) {
            zzus.zzg(this.zze, f.floatValue());
            this.zze.zzq(this.zza, this.zzb, f.floatValue(), this.zzc);
        }
        this.zze.zzg.set(false);
    }
}
