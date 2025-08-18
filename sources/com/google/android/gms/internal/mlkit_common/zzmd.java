package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzmd {
    private final zzlz zza;
    private final zzmb zzb = null;
    private final zzmb zzc = null;
    private final Boolean zzd = null;

    /* synthetic */ zzmd(zzma zzma, zzmc zzmc) {
        this.zza = zzma.zza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzmd)) {
            return false;
        }
        zzmd zzmd = (zzmd) obj;
        if (Objects.equal(this.zza, zzmd.zza)) {
            zzmb zzmb = zzmd.zzb;
            if (Objects.equal((Object) null, (Object) null)) {
                zzmb zzmb2 = zzmd.zzc;
                if (Objects.equal((Object) null, (Object) null)) {
                    Boolean bool = zzmd.zzd;
                    if (Objects.equal((Object) null, (Object) null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, null, null);
    }

    public final zzlz zza() {
        return this.zza;
    }
}
