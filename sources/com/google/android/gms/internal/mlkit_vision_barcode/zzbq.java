package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
class zzbq implements Iterator {
    final Iterator zza;
    final Collection zzb;
    final /* synthetic */ zzbr zzc;

    zzbq(zzbr zzbr) {
        Iterator it;
        this.zzc = zzbr;
        this.zzb = zzbr.zzb;
        Collection collection = zzbr.zzb;
        if (collection instanceof List) {
            it = ((List) collection).listIterator();
        } else {
            it = collection.iterator();
        }
        this.zza = it;
    }

    zzbq(zzbr zzbr, Iterator it) {
        this.zzc = zzbr;
        this.zzb = zzbr.zzb;
        this.zza = it;
    }

    public final boolean hasNext() {
        zza();
        return this.zza.hasNext();
    }

    public final Object next() {
        zza();
        return this.zza.next();
    }

    public final void remove() {
        this.zza.remove();
        zzbu zzbu = this.zzc.zze;
        zzbu.zzb = zzbu.zzb - 1;
        this.zzc.zzc();
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zzc.zzb();
        if (this.zzc.zzb != this.zzb) {
            throw new ConcurrentModificationException();
        }
    }
}
