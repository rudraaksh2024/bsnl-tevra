package com.google.mlkit.common.sdkinternal.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.mlkit_common.zzlm;
import com.google.android.gms.internal.mlkit_common.zzls;
import com.google.android.gms.internal.mlkit_common.zzpq;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqa;
import com.google.android.gms.internal.mlkit_common.zzqb;
import com.google.android.gms.internal.mlkit_common.zzqc;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class zzd extends BroadcastReceiver {
    final /* synthetic */ RemoteModelDownloadManager zza;
    private final long zzb;
    private final TaskCompletionSource zzc;

    /* synthetic */ zzd(RemoteModelDownloadManager remoteModelDownloadManager, long j, TaskCompletionSource taskCompletionSource, zzc zzc2) {
        this.zza = remoteModelDownloadManager;
        this.zzb = j;
        this.zzc = taskCompletionSource;
    }

    public final void onReceive(Context context, Intent intent) {
        long longExtra = intent.getLongExtra("extra_download_id", -1);
        if (longExtra == this.zzb) {
            Integer downloadingModelStatusCode = this.zza.getDownloadingModelStatusCode();
            synchronized (this.zza) {
                try {
                    this.zza.zze.getApplicationContext().unregisterReceiver(this);
                } catch (IllegalArgumentException e) {
                    RemoteModelDownloadManager.zza.w("ModelDownloadManager", "Exception thrown while trying to unregister the broadcast receiver for the download", e);
                }
                this.zza.zzc.remove(this.zzb);
                this.zza.zzd.remove(this.zzb);
            }
            if (downloadingModelStatusCode != null) {
                if (downloadingModelStatusCode.intValue() == 16) {
                    zzpz zzh = this.zza.zzi;
                    zzpq zzg = zzqc.zzg();
                    RemoteModelDownloadManager remoteModelDownloadManager = this.zza;
                    RemoteModel zze = remoteModelDownloadManager.zzg;
                    Long valueOf = Long.valueOf(longExtra);
                    zzh.zze(zzg, zze, false, remoteModelDownloadManager.getFailureReason(valueOf));
                    this.zzc.setException(this.zza.zzl(valueOf));
                    return;
                } else if (downloadingModelStatusCode.intValue() == 8) {
                    zzpz zzh2 = this.zza.zzi;
                    zzpq zzg2 = zzqc.zzg();
                    RemoteModel zze2 = this.zza.zzg;
                    zzqa zzh3 = zzqb.zzh();
                    zzh3.zzb(zzlm.NO_ERROR);
                    zzh3.zze(true);
                    zzh3.zzd(this.zza.zzg.getModelType());
                    zzh3.zza(zzls.SUCCEEDED);
                    zzh2.zzg(zzg2, zze2, zzh3.zzh());
                    this.zzc.setResult(null);
                    return;
                }
            }
            this.zza.zzi.zze(zzqc.zzg(), this.zza.zzg, false, 0);
            this.zzc.setException(new MlKitException("Model downloading failed", 13));
        }
    }
}
