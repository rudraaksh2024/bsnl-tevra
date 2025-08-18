package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public abstract class zzuv {
    public static zzuv zzg(float f, float f2, float f3, float f4, float f5) {
        return new zzuo(f, f2, f3, f4, 0.0f);
    }

    /* access modifiers changed from: package-private */
    public abstract float zza();

    /* access modifiers changed from: package-private */
    public abstract float zzb();

    /* access modifiers changed from: package-private */
    public abstract float zzc();

    /* access modifiers changed from: package-private */
    public abstract float zzd();

    /* access modifiers changed from: package-private */
    public abstract float zze();

    /* access modifiers changed from: package-private */
    public final float zzf() {
        if (zzh()) {
            return (zzb() - zzc()) * (zzd() - zze());
        }
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzh() {
        return zzc() >= 0.0f && zzc() < zzb() && zzb() <= 1.0f && zze() >= 0.0f && zze() < zzd() && zzd() <= 1.0f;
    }
}
