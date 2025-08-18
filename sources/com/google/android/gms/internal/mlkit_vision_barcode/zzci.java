package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzci extends AbstractSet {
    final /* synthetic */ zzcl zza;

    zzci(zzcl zzcl) {
        this.zza = zzcl;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.containsKey(obj);
    }

    public final Iterator iterator() {
        zzcl zzcl = this.zza;
        Map zzj = zzcl.zzj();
        if (zzj != null) {
            return zzj.keySet().iterator();
        }
        return new zzcc(zzcl);
    }

    public final boolean remove(@CheckForNull Object obj) {
        Map zzj = this.zza.zzj();
        if (zzj != null) {
            return zzj.keySet().remove(obj);
        }
        return this.zza.zzs(obj) != zzcl.zzd;
    }

    public final int size() {
        return this.zza.size();
    }
}
