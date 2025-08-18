package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractMap;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzdo extends zzcv {
    final /* synthetic */ zzdp zza;

    zzdo(zzdp zzdp) {
        this.zza = zzdp;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzbc.zza(i, this.zza.zzc, "index");
        zzdp zzdp = this.zza;
        int i2 = i + i;
        Object obj = zzdp.zzb[i2];
        obj.getClass();
        Object obj2 = zzdp.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    public final int size() {
        return this.zza.zzc;
    }
}
