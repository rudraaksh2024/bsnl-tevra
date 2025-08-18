package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzow {
    /* access modifiers changed from: private */
    public Long zza;
    /* access modifiers changed from: private */
    public zzpj zzb;
    /* access modifiers changed from: private */
    public Boolean zzc;
    /* access modifiers changed from: private */
    public Boolean zzd;
    /* access modifiers changed from: private */
    public Boolean zze;

    public final zzow zza(Boolean bool) {
        this.zzd = bool;
        return this;
    }

    public final zzow zzb(Boolean bool) {
        this.zze = bool;
        return this;
    }

    public final zzow zzc(Long l) {
        this.zza = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzow zzd(zzpj zzpj) {
        this.zzb = zzpj;
        return this;
    }

    public final zzow zze(Boolean bool) {
        this.zzc = bool;
        return this;
    }

    public final zzoy zzf() {
        return new zzoy(this, (zzox) null);
    }
}
