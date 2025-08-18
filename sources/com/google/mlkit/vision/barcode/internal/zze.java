package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final /* synthetic */ class zze implements SuccessContinuation {
    public final /* synthetic */ BarcodeScannerImpl zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ int zzc;

    public /* synthetic */ zze(BarcodeScannerImpl barcodeScannerImpl, int i, int i2) {
        this.zza = barcodeScannerImpl;
        this.zzb = i;
        this.zzc = i2;
    }

    public final Task then(Object obj) {
        return this.zza.zzd(this.zzb, this.zzc, (List) obj);
    }
}
