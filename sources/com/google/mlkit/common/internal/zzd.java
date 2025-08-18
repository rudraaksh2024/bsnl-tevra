package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitThreadPool;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzd implements ComponentFactory {
    public static final /* synthetic */ zzd zza = new zzd();

    private /* synthetic */ zzd() {
    }

    public final Object create(ComponentContainer componentContainer) {
        return new ExecutorSelector(componentContainer.getProvider(MlKitThreadPool.class));
    }
}
