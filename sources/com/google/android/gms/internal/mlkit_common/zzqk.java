package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzqk {
    private static zzqj zza;

    public static synchronized zzpz zza(zzpt zzpt) {
        zzpz zzpz;
        synchronized (zzqk.class) {
            if (zza == null) {
                zza = new zzqj((zzqi) null);
            }
            zzpz = (zzpz) zza.get(zzpt);
        }
        return zzpz;
    }

    public static synchronized zzpz zzb(String str) {
        zzpz zza2;
        synchronized (zzqk.class) {
            zza2 = zza(zzpt.zzd("common").zzd());
        }
        return zza2;
    }
}
