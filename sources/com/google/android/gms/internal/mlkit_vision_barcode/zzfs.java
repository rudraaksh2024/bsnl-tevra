package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzfs {
    /* access modifiers changed from: private */
    public zzfv zza;
    /* access modifiers changed from: private */
    public Integer zzb;
    /* access modifiers changed from: private */
    public zzol zzc;

    public final zzfs zza(Integer num) {
        this.zzb = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzfs zzb(zzol zzol) {
        this.zzc = zzol;
        return this;
    }

    public final zzfs zzc(zzfv zzfv) {
        this.zza = zzfv;
        return this;
    }

    public final zzfx zze() {
        return new zzfx(this, (zzfw) null);
    }
}
