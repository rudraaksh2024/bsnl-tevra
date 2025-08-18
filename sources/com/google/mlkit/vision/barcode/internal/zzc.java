package com.google.mlkit.vision.barcode.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.MlKitContext;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final /* synthetic */ class zzc implements ComponentFactory {
    public static final /* synthetic */ zzc zza = new zzc();

    private /* synthetic */ zzc() {
    }

    public final Object create(ComponentContainer componentContainer) {
        int i = BarcodeRegistrar.zza;
        return new zzh((MlKitContext) componentContainer.get(MlKitContext.class));
    }
}
