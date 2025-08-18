package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzuh extends LazyInstanceMap {
    private zzuh() {
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zztp zztp = (zztp) obj;
        MlKitContext instance = MlKitContext.getInstance();
        return new zztx(instance.getApplicationContext(), (SharedPrefManager) instance.get(SharedPrefManager.class), new zztq(MlKitContext.getInstance().getApplicationContext(), zztp), zztp.zzb());
    }

    /* synthetic */ zzuh(zzug zzug) {
    }
}
