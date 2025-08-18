package com.google.mlkit.common.sdkinternal;

import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final /* synthetic */ class zzr implements OnFailureListener {
    public static final /* synthetic */ zzr zza = new zzr();

    private /* synthetic */ zzr() {
    }

    public final void onFailure(Exception exc) {
        Log.e("OptionalModuleUtils", "Failed to request modules install request", exc);
    }
}
