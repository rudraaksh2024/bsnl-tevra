package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzfv {
    private final zzpj zza;
    private final Boolean zzb = null;
    private final Boolean zzc;
    private final zzos zzd;
    private final zzth zze;
    private final zzcv zzf;
    private final zzcv zzg;

    /* synthetic */ zzfv(zzft zzft, zzfu zzfu) {
        this.zza = zzft.zza;
        this.zzc = zzft.zzb;
        this.zzd = null;
        this.zze = zzft.zzc;
        this.zzf = zzft.zzd;
        this.zzg = zzft.zze;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfv)) {
            return false;
        }
        zzfv zzfv = (zzfv) obj;
        if (Objects.equal(this.zza, zzfv.zza)) {
            Boolean bool = zzfv.zzb;
            if (Objects.equal((Object) null, (Object) null) && Objects.equal(this.zzc, zzfv.zzc)) {
                zzos zzos = zzfv.zzd;
                return Objects.equal((Object) null, (Object) null) && Objects.equal(this.zze, zzfv.zze) && Objects.equal(this.zzf, zzfv.zzf) && Objects.equal(this.zzg, zzfv.zzg);
            }
        }
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, this.zzg);
    }

    public final zzcv zza() {
        return this.zzf;
    }

    public final zzcv zzb() {
        return this.zzg;
    }

    public final zzpj zzc() {
        return this.zza;
    }

    public final zzth zzd() {
        return this.zze;
    }

    public final Boolean zze() {
        return this.zzc;
    }
}
