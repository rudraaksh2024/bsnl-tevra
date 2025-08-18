package com.google.android.gms.internal.mlkit_common;

import java.util.AbstractMap;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class zzay extends zzar {
    final /* synthetic */ zzaz zza;

    zzay(zzaz zzaz) {
        this.zza = zzaz;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzaf.zza(i, this.zza.zzc, "index");
        zzaz zzaz = this.zza;
        int i2 = i + i;
        Object obj = zzaz.zzb[i2];
        obj.getClass();
        Object obj2 = zzaz.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public final int size() {
        return this.zza.zzc;
    }
}
