package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzos {
    private final zzoq zza;
    private final Integer zzb;
    private final Integer zzc = null;
    private final Boolean zzd = null;

    /* synthetic */ zzos(zzop zzop, zzor zzor) {
        this.zza = zzop.zza;
        this.zzb = zzop.zzb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzos)) {
            return false;
        }
        zzos zzos = (zzos) obj;
        if (Objects.equal(this.zza, zzos.zza) && Objects.equal(this.zzb, zzos.zzb)) {
            Integer num = zzos.zzc;
            if (Objects.equal((Object) null, (Object) null)) {
                Boolean bool = zzos.zzd;
                if (Objects.equal((Object) null, (Object) null)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, null, null);
    }

    public final zzoq zza() {
        return this.zza;
    }

    public final Integer zzb() {
        return this.zzb;
    }
}
