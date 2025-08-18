package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzpu implements zzpr {
    final List zza;

    public zzpu(Context context, zzpt zzpt) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzpt.zzc()) {
            arrayList.add(new zzqh(context, zzpt));
        }
    }

    public final void zza(zzpq zzpq) {
        for (zzpr zza2 : this.zza) {
            zza2.zza(zzpq);
        }
    }
}
