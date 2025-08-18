package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzpw implements Callable {
    public final /* synthetic */ SharedPrefManager zza;

    public /* synthetic */ zzpw(SharedPrefManager sharedPrefManager) {
        this.zza = sharedPrefManager;
    }

    public final Object call() {
        return this.zza.getMlSdkInstanceId();
    }
}
