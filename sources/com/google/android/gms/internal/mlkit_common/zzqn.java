package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzqn {
    private static zzqn zza;

    private zzqn() {
    }

    public static synchronized zzqn zza() {
        zzqn zzqn;
        synchronized (zzqn.class) {
            if (zza == null) {
                zza = new zzqn();
            }
            zzqn = zza;
        }
        return zzqn;
    }

    public static void zzb() {
        zzqm.zza();
    }
}
