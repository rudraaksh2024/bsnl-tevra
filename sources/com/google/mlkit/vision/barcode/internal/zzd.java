package com.google.mlkit.vision.barcode.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final /* synthetic */ class zzd implements ComponentFactory {
    public static final /* synthetic */ zzd zza = new zzd();

    private /* synthetic */ zzd() {
    }

    public final Object create(ComponentContainer componentContainer) {
        return new zzg((zzh) componentContainer.get(zzh.class), (ExecutorSelector) componentContainer.get(ExecutorSelector.class), (MlKitContext) componentContainer.get(MlKitContext.class));
    }
}
