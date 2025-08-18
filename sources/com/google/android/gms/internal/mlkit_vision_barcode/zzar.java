package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.SystemClock;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzar {
    private static final zzbf zza;

    static {
        zzbf zzbf;
        try {
            SystemClock.elapsedRealtimeNanos();
            zzbf = new zzap();
        } catch (Throwable unused) {
            SystemClock.elapsedRealtime();
            zzbf = new zzaq();
        }
        zza = zzbf;
    }

    public static zzbf zza() {
        return zza;
    }
}
