package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzlr {
    /* access modifiers changed from: private */
    public zzmd zza;
    /* access modifiers changed from: private */
    public Long zzb;
    /* access modifiers changed from: private */
    public zzlm zzc;
    /* access modifiers changed from: private */
    public Long zzd;
    /* access modifiers changed from: private */
    public zzls zze;
    /* access modifiers changed from: private */
    public Long zzf;

    public final zzlr zzb(Long l) {
        this.zzf = l;
        return this;
    }

    public final zzlr zzc(zzls zzls) {
        this.zze = zzls;
        return this;
    }

    public final zzlr zzd(zzlm zzlm) {
        this.zzc = zzlm;
        return this;
    }

    public final zzlr zze(Long l) {
        this.zzd = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzlr zzf(zzmd zzmd) {
        this.zza = zzmd;
        return this;
    }

    public final zzlr zzg(Long l) {
        this.zzb = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzlu zzi() {
        return new zzlu(this, (zzlt) null);
    }
}
