package com.google.android.gms.internal.mlkit_common;

import android.content.Context;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzi {
    public static final zzi zza;
    public static final zzi zzb;
    public static final zzi zzc;
    /* access modifiers changed from: private */
    public final boolean zzd;
    /* access modifiers changed from: private */
    public final boolean zze = false;
    private final zzar zzf;

    static {
        zzg zzg = new zzg((zzf) null);
        zzg.zzb();
        zza = zzg.zzd();
        zzg zzg2 = new zzg((zzf) null);
        zzg2.zzb();
        zzg2.zza(new zze());
        zzb = zzg2.zzd();
        zzg zzg3 = new zzg((zzf) null);
        zzg3.zzc();
        zzc = zzg3.zzd();
    }

    /* synthetic */ zzi(boolean z, boolean z2, zzar zzar, zzh zzh) {
        this.zzd = z;
        this.zzf = zzar;
    }

    static /* bridge */ /* synthetic */ int zzc(zzi zzi, Context context, zzr zzr) {
        zzar zzar = zzi.zzf;
        int size = zzar.size();
        int i = 0;
        while (i < size) {
            int zza2 = ((zzs) zzar.get(i)).zza(context, zzr, zzi.zzd) - 1;
            i++;
            if (zza2 == 1) {
                return 2;
            }
        }
        return 3;
    }
}
