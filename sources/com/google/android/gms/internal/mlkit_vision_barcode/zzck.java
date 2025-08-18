package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzck extends AbstractCollection {
    final /* synthetic */ zzcl zza;

    zzck(zzcl zzcl) {
        this.zza = zzcl;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final Iterator iterator() {
        zzcl zzcl = this.zza;
        Map zzj = zzcl.zzj();
        if (zzj != null) {
            return zzj.values().iterator();
        }
        return new zzce(zzcl);
    }

    public final int size() {
        return this.zza.size();
    }
}
