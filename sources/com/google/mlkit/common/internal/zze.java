package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.Cleaner;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zze implements ComponentFactory {
    public static final /* synthetic */ zze zza = new zze();

    private /* synthetic */ zze() {
    }

    public final Object create(ComponentContainer componentContainer) {
        return Cleaner.create();
    }
}
