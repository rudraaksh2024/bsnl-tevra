package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzaq extends zzbf {
    zzaq() {
    }

    public final long zza() {
        return TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
    }
}
