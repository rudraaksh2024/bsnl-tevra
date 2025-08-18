package com.google.mlkit.common.sdkinternal;

import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzj implements ThreadFactory {
    public final /* synthetic */ ThreadFactory zza;

    public /* synthetic */ zzj(ThreadFactory threadFactory) {
        this.zza = threadFactory;
    }

    public final Thread newThread(Runnable runnable) {
        return this.zza.newThread(new zzi(runnable));
    }
}
