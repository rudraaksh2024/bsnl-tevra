package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzel implements Runnable {
    final Future zza;
    final zzek zzb;

    zzel(Future future, zzek zzek) {
        this.zza = future;
        this.zzb = zzek;
    }

    public final void run() {
        boolean z;
        Object obj;
        Throwable zza2 = zzfa.zza((zzez) this.zza);
        if (zza2 == null) {
            try {
                Future future = this.zza;
                z = false;
                if (future.isDone()) {
                    while (true) {
                        obj = future.get();
                        break;
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    this.zzb.zzb(obj);
                    return;
                }
                throw new IllegalStateException(zzbd.zzb("Future was expected to be done: %s", future));
            } catch (InterruptedException unused) {
                z = true;
            } catch (ExecutionException e) {
                this.zzb.zza(e.getCause());
            } catch (Error | RuntimeException e2) {
                this.zzb.zza(e2);
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        } else {
            this.zzb.zza(zza2);
        }
    }

    public final String toString() {
        zzaw zza2 = zzax.zza(this);
        zza2.zza(this.zzb);
        return zza2.toString();
    }
}
