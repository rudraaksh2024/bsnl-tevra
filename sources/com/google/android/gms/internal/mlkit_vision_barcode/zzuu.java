package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public abstract class zzuu {
    public static final zzuu zza = zzm().zzm();
    public static final zzuu zzb;

    static {
        zzut zzm = zzm();
        zzm.zzi(false);
        zzb = zzm.zzm();
    }

    public static zzut zzm() {
        zzul zzul = new zzul();
        zzul.zzg(10);
        zzul.zze(5);
        zzul.zzf(0.25f);
        zzul.zzd(0.8f);
        zzul.zzi(true);
        zzul.zzc(0.5f);
        zzul.zzb(0.8f);
        zzul.zzk(1500);
        zzul.zzh(3000);
        zzul.zza(true);
        zzul.zzj(0.1f);
        zzul.zzl(0.05f);
        return zzul;
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
    public abstract float zzf();

    /* access modifiers changed from: package-private */
    public abstract int zzg();

    /* access modifiers changed from: package-private */
    public abstract int zzh();

    /* access modifiers changed from: package-private */
    public abstract long zzi();

    /* access modifiers changed from: package-private */
    public abstract long zzj();

    /* access modifiers changed from: package-private */
    public abstract boolean zzk();

    /* access modifiers changed from: package-private */
    public abstract boolean zzl();
}
