package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzbk implements Iterator {
    final Iterator zza;
    @CheckForNull
    Collection zzb;
    final /* synthetic */ zzbl zzc;

    zzbk(zzbl zzbl) {
        this.zzc = zzbl;
        this.zza = zzbl.zza.entrySet().iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.zza.next();
        this.zzb = (Collection) entry.getValue();
        zzbl zzbl = this.zzc;
        Object key = entry.getKey();
        return new zzcr(key, zzbl.zzb.zzd(key, (Collection) entry.getValue()));
    }

    public final void remove() {
        zzbc.zze(this.zzb != null, "no calls to next() since the last call to remove()");
        this.zza.remove();
        zzbu zzbu = this.zzc.zzb;
        zzbu.zzb = zzbu.zzb - this.zzb.size();
        this.zzb.clear();
        this.zzb = null;
    }
}
