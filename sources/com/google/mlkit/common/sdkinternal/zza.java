package com.google.mlkit.common.sdkinternal;

import java.lang.ref.ReferenceQueue;
import java.util.Set;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zza implements Runnable {
    public final /* synthetic */ ReferenceQueue zza;
    public final /* synthetic */ Set zzb;

    public /* synthetic */ zza(ReferenceQueue referenceQueue, Set set) {
        this.zza = referenceQueue;
        this.zzb = set;
    }

    public final void run() {
        ReferenceQueue referenceQueue = this.zza;
        Set set = this.zzb;
        while (!set.isEmpty()) {
            try {
                ((zzd) referenceQueue.remove()).clean();
            } catch (InterruptedException unused) {
            }
        }
    }
}
