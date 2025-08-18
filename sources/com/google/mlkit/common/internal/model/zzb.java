package com.google.mlkit.common.internal.model;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzb implements SuccessContinuation {
    public final /* synthetic */ RemoteModelDownloadManager zza;

    public /* synthetic */ zzb(RemoteModelDownloadManager remoteModelDownloadManager) {
        this.zza = remoteModelDownloadManager;
    }

    public final Task then(Object obj) {
        return this.zza.ensureModelDownloaded();
    }
}
