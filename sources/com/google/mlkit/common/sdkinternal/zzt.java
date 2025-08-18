package com.google.mlkit.common.sdkinternal;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzt implements Runnable {
    public final /* synthetic */ TaskQueue zza;
    public final /* synthetic */ Runnable zzb;

    public /* synthetic */ zzt(TaskQueue taskQueue, Runnable runnable) {
        this.zza = taskQueue;
        this.zzb = runnable;
    }

    public final void run() {
        TaskQueue taskQueue = this.zza;
        Runnable runnable = this.zzb;
        zzx zzx = new zzx(taskQueue, (zzw) null);
        try {
            runnable.run();
            zzx.close();
            return;
        } catch (Throwable th) {
            try {
                Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class}).invoke(th, new Object[]{th});
            } catch (Exception unused) {
            }
        }
        throw th;
    }
}
