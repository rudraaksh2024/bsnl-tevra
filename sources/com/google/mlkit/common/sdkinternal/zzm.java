package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzm implements Executor {
    public final /* synthetic */ Executor zza;
    public final /* synthetic */ CancellationToken zzb;
    public final /* synthetic */ CancellationTokenSource zzc;
    public final /* synthetic */ TaskCompletionSource zzd;

    public /* synthetic */ zzm(Executor executor, CancellationToken cancellationToken, CancellationTokenSource cancellationTokenSource, TaskCompletionSource taskCompletionSource) {
        this.zza = executor;
        this.zzb = cancellationToken;
        this.zzc = cancellationTokenSource;
        this.zzd = taskCompletionSource;
    }

    public final void execute(Runnable runnable) {
        Executor executor = this.zza;
        CancellationToken cancellationToken = this.zzb;
        CancellationTokenSource cancellationTokenSource = this.zzc;
        TaskCompletionSource taskCompletionSource = this.zzd;
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            if (cancellationToken.isCancellationRequested()) {
                cancellationTokenSource.cancel();
            } else {
                taskCompletionSource.setException(e);
            }
            throw e;
        }
    }
}
