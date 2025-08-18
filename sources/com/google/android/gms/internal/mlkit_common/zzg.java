package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzg {
    private final zzao zza = new zzao();
    private Boolean zzb;

    private zzg() {
    }

    public final zzg zza(zzs zzs) {
        zzaf.zzc(this.zzb, "Must call internal() or external() before appending rules.");
        this.zza.zzb(zzs);
        return this;
    }

    public final zzg zzb() {
        zzaf.zze(this.zzb == null, "A SourcePolicy can only set internal() or external() once.");
        this.zzb = false;
        return this;
    }

    public final zzg zzc() {
        zzaf.zze(this.zzb == null, "A SourcePolicy can only set internal() or external() once.");
        this.zzb = true;
        return this;
    }

    public final zzi zzd() {
        zzaf.zzc(this.zzb, "Must call internal() or external() when building a SourcePolicy.");
        return new zzi(this.zzb.booleanValue(), false, this.zza.zzc(), (zzh) null);
    }

    /* synthetic */ zzg(zzf zzf) {
    }
}
