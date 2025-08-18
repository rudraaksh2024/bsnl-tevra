package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzua implements zztm {
    private final zzpl zza;
    private zzsj zzb = new zzsj();
    private final int zzc;

    private zzua(zzpl zzpl, int i) {
        this.zza = zzpl;
        zzuj.zza();
        this.zzc = i;
    }

    public static zztm zzf(zzpl zzpl) {
        return new zzua(zzpl, 0);
    }

    public static zztm zzg(zzpl zzpl, int i) {
        return new zzua(zzpl, 1);
    }

    public final int zza() {
        return this.zzc;
    }

    public final zztm zzb(zzpk zzpk) {
        this.zza.zzf(zzpk);
        return this;
    }

    public final zztm zzc(zzsj zzsj) {
        this.zzb = zzsj;
        return this;
    }

    public final String zzd() {
        zzsl zzg = this.zza.zzk().zzg();
        return (zzg == null || zzbd.zzc(zzg.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzg.zzk());
    }

    public final byte[] zze(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zzj(this.zzb.zzm());
        try {
            zzuj.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zznj.zza).ignoreNullValues(true).build().encode(this.zza.zzk()).getBytes("utf-8");
            }
            zzpn zzk = this.zza.zzk();
            zzfk zzfk = new zzfk();
            zznj.zza.configure(zzfk);
            return zzfk.zza().zza(zzk);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
