package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzem extends zzeo {
    public static zzev zza(Object obj) {
        return new zzep(obj);
    }

    public static void zzb(zzev zzev, zzek zzek, Executor executor) {
        zzev.zzj(new zzel(zzev, zzek), executor);
    }

    public static zzev zzc(zzup zzup, Executor executor) {
        zzey zzey = new zzey(zzup);
        zzey.run();
        return zzey;
    }
}
