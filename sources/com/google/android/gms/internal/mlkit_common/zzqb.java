package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public abstract class zzqb {
    public static zzqa zzh() {
        zzpn zzpn = new zzpn();
        zzpn.zzg("NA");
        zzpn.zzf(false);
        zzpn.zze(false);
        zzpn.zzd(ModelType.UNKNOWN);
        zzpn.zzb(zzlm.NO_ERROR);
        zzpn.zza(zzls.UNKNOWN_STATUS);
        zzpn.zzc(0);
        return zzpn;
    }

    public abstract int zza();

    public abstract ModelType zzb();

    public abstract zzlm zzc();

    public abstract zzls zzd();

    public abstract String zze();

    public abstract boolean zzf();

    public abstract boolean zzg();
}
