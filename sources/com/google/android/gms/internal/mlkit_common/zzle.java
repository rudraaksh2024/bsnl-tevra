package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public enum zzle implements zzbm {
    UNKNOWN(0),
    TRANSLATE(1);
    
    private final int zzd;

    private zzle(int i) {
        this.zzd = i;
    }

    public static zzle zzb(int i) {
        for (zzle zzle : values()) {
            if (zzle.zzd == i) {
                return zzle;
            }
        }
        return UNKNOWN;
    }

    public final int zza() {
        return this.zzd;
    }
}
