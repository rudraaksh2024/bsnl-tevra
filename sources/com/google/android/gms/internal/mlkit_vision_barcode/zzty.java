package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final /* synthetic */ class zzty implements OnFailureListener {
    public final /* synthetic */ zztz zza;
    public final /* synthetic */ long zzb;

    public /* synthetic */ zzty(zztz zztz, long j) {
        this.zza = zztz;
        this.zzb = j;
    }

    public final void onFailure(Exception exc) {
        this.zza.zzb(this.zzb, exc);
    }
}
