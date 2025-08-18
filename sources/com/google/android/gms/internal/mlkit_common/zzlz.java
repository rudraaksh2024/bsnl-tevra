package com.google.android.gms.internal.mlkit_common;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzlz {
    private final String zza;
    private final String zzb = null;
    private final zzlx zzc;
    private final String zzd;
    private final String zze;
    private final zzlw zzf;
    private final Long zzg;
    private final Boolean zzh;
    private final Boolean zzi;

    /* synthetic */ zzlz(zzlv zzlv, zzly zzly) {
        this.zza = zzlv.zza;
        this.zzc = zzlv.zzb;
        this.zzd = null;
        this.zze = zzlv.zzc;
        this.zzf = zzlv.zzd;
        this.zzg = null;
        this.zzh = null;
        this.zzi = null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzlz)) {
            return false;
        }
        zzlz zzlz = (zzlz) obj;
        if (Objects.equal(this.zza, zzlz.zza)) {
            String str = zzlz.zzb;
            if (Objects.equal((Object) null, (Object) null) && Objects.equal(this.zzc, zzlz.zzc)) {
                String str2 = zzlz.zzd;
                if (Objects.equal((Object) null, (Object) null) && Objects.equal(this.zze, zzlz.zze) && Objects.equal(this.zzf, zzlz.zzf)) {
                    Long l = zzlz.zzg;
                    if (Objects.equal((Object) null, (Object) null)) {
                        Boolean bool = zzlz.zzh;
                        if (Objects.equal((Object) null, (Object) null)) {
                            Boolean bool2 = zzlz.zzi;
                            if (Objects.equal((Object) null, (Object) null)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, null, this.zzc, null, this.zze, this.zzf, null, null, null);
    }

    public final zzlw zza() {
        return this.zzf;
    }

    public final zzlx zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zza;
    }
}
