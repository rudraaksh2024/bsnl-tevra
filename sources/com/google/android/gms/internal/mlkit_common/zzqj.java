package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class zzqj extends LazyInstanceMap {
    private zzqj() {
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzpt zzpt = (zzpt) obj;
        MlKitContext instance = MlKitContext.getInstance();
        return new zzpz(instance.getApplicationContext(), (SharedPrefManager) instance.get(SharedPrefManager.class), new zzpu(MlKitContext.getInstance().getApplicationContext(), zzpt), zzpt.zzb());
    }

    /* synthetic */ zzqj(zzqi zzqi) {
    }
}
