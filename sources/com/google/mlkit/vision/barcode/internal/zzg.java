package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.internal.mlkit_vision_barcode.zzui;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzg {
    private final zzh zza;
    private final ExecutorSelector zzb;
    private final MlKitContext zzc;

    zzg(zzh zzh, ExecutorSelector executorSelector, MlKitContext mlKitContext) {
        this.zza = zzh;
        this.zzb = executorSelector;
        this.zzc = mlKitContext;
    }

    public final BarcodeScannerImpl zza() {
        return zzb(BarcodeScannerImpl.zzd);
    }

    public final BarcodeScannerImpl zzb(BarcodeScannerOptions barcodeScannerOptions) {
        return new BarcodeScannerImpl(barcodeScannerOptions, (zzk) this.zza.get(barcodeScannerOptions), this.zzb.getExecutorToUse(barcodeScannerOptions.zzc()), zzui.zzb(zzb.zzd()), this.zzc);
    }
}
