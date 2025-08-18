package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzqc implements zzpq {
    private final zzlo zza;
    private zzol zzb = new zzol();

    private zzqc(zzlo zzlo, int i) {
        this.zza = zzlo;
        zzqn.zza();
    }

    public static zzpq zzf(zzlo zzlo) {
        return new zzqc(zzlo, 0);
    }

    public static zzpq zzg() {
        return new zzqc(new zzlo(), 0);
    }

    public final zzpq zza(zzln zzln) {
        this.zza.zzf(zzln);
        return this;
    }

    public final zzpq zzb(zzlu zzlu) {
        this.zza.zzi(zzlu);
        return this;
    }

    public final zzpq zzc(zzol zzol) {
        this.zzb = zzol;
        return this;
    }

    public final String zzd() {
        zzon zzf = this.zza.zzk().zzf();
        return (zzf == null || zzag.zzc(zzf.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzf.zzk());
    }

    public final byte[] zze(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zzj(this.zzb.zzm());
        try {
            zzqn.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzjn.zza).ignoreNullValues(true).build().encode(this.zza.zzk()).getBytes("utf-8");
            }
            zzlq zzk = this.zza.zzk();
            zzbs zzbs = new zzbs();
            zzjn.zza.configure(zzbs);
            return zzbs.zza().zza(zzk);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
