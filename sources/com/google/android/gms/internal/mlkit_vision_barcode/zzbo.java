package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzbo extends zzdf {
    final /* synthetic */ zzbu zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbo(zzbu zzbu, Map map) {
        super(map);
        this.zza = zzbu;
    }

    public final void clear() {
        zzda.zza(iterator());
    }

    public final boolean containsAll(Collection collection) {
        return this.zzb.keySet().containsAll(collection);
    }

    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.zzb.keySet().equals(obj);
    }

    public final int hashCode() {
        return this.zzb.keySet().hashCode();
    }

    public final Iterator iterator() {
        return new zzbn(this, this.zzb.entrySet().iterator());
    }

    public final boolean remove(@CheckForNull Object obj) {
        Collection collection = (Collection) this.zzb.remove(obj);
        if (collection == null) {
            return false;
        }
        int size = collection.size();
        collection.clear();
        zzbu zzbu = this.zza;
        zzbu.zzb = zzbu.zzb - size;
        return size > 0;
    }
}
