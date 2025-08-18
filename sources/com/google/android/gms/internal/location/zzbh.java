package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
final class zzbh implements RemoteCall, zzdr {
    final /* synthetic */ zzbi zza;
    private final zzbg zzb;
    private ListenerHolder zzc;
    private boolean zzd = true;

    zzbh(zzbi zzbi, ListenerHolder listenerHolder, zzbg zzbg) {
        this.zza = zzbi;
        this.zzc = listenerHolder;
        this.zzb = zzbg;
    }

    public final /* bridge */ /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
        ListenerHolder.ListenerKey listenerKey;
        boolean z;
        zzdz zzdz = (zzdz) obj;
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
        synchronized (this) {
            listenerKey = this.zzc.getListenerKey();
            z = this.zzd;
            this.zzc.clear();
        }
        if (listenerKey == null) {
            taskCompletionSource.setResult(false);
        } else {
            this.zzb.zza(zzdz, listenerKey, z, taskCompletionSource);
        }
    }

    public final synchronized ListenerHolder zza() {
        return this.zzc;
    }

    public final synchronized void zzb(ListenerHolder listenerHolder) {
        ListenerHolder listenerHolder2 = this.zzc;
        if (listenerHolder2 != listenerHolder) {
            listenerHolder2.clear();
            this.zzc = listenerHolder;
        }
    }

    public final void zzc() {
        ListenerHolder.ListenerKey listenerKey;
        synchronized (this) {
            this.zzd = false;
            listenerKey = this.zzc.getListenerKey();
        }
        if (listenerKey != null) {
            this.zza.doUnregisterEventListener(listenerKey, 2441);
        }
    }
}
