package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.internal.mlkit_common.zzpz;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zze implements Runnable {
    public final /* synthetic */ CloseGuard zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ zzpz zzc;
    public final /* synthetic */ Runnable zzd;

    public /* synthetic */ zze(CloseGuard closeGuard, int i, zzpz zzpz, Runnable runnable) {
        this.zza = closeGuard;
        this.zzb = i;
        this.zzc = zzpz;
        this.zzd = runnable;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd);
    }
}
