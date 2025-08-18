package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.zzw;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzdd extends zzq {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ zzw zzb;

    zzdd(TaskCompletionSource taskCompletionSource, zzw zzw) {
        this.zza = taskCompletionSource;
        this.zzb = zzw;
    }

    public final void zzd(zzl zzl) {
        TaskUtil.setResultOrApiException(zzl.getStatus(), this.zza);
    }

    public final void zze() throws RemoteException {
        this.zzb.zzf();
    }
}
