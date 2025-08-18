package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.internal.model.zzg;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.RemoteModelManager;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzh implements ComponentFactory {
    public static final /* synthetic */ zzh zza = new zzh();

    private /* synthetic */ zzh() {
    }

    public final Object create(ComponentContainer componentContainer) {
        return new RemoteModelManager.RemoteModelManagerRegistration(CustomRemoteModel.class, componentContainer.getProvider(zzg.class));
    }
}
