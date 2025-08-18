package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzuj {
    private static zzuj zza;

    private zzuj() {
    }

    public static synchronized zzuj zza() {
        zzuj zzuj;
        synchronized (zzuj.class) {
            if (zza == null) {
                zza = new zzuj();
            }
            zzuj = zza;
        }
        return zzuj;
    }
}
