package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.core.content.ContextCompat;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzd implements Callable {
    public final /* synthetic */ Context zza;

    public /* synthetic */ zzd(Context context) {
        this.zza = context;
    }

    public final Object call() {
        Context context = this.zza;
        int i = zzj.zza;
        return ContextCompat.getExternalCacheDirs(context);
    }
}
