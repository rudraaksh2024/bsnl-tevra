package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
abstract class zzch implements Iterator {
    int zzb;
    int zzc;
    int zzd = -1;
    final /* synthetic */ zzcl zze;

    /* synthetic */ zzch(zzcl zzcl, zzcg zzcg) {
        this.zze = zzcl;
        this.zzb = zzcl.zzf;
        this.zzc = zzcl.zze();
    }

    private final void zzb() {
        if (this.zze.zzf != this.zzb) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        return this.zzc >= 0;
    }

    public final Object next() {
        zzb();
        if (hasNext()) {
            int i = this.zzc;
            this.zzd = i;
            Object zza = zza(i);
            this.zzc = this.zze.zzf(this.zzc);
            return zza;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        zzb();
        zzbc.zze(this.zzd >= 0, "no calls to next() since the last call to remove()");
        this.zzb += 32;
        zzcl zzcl = this.zze;
        int i = this.zzd;
        Object[] objArr = zzcl.zzb;
        objArr.getClass();
        zzcl.remove(objArr[i]);
        this.zzc--;
        this.zzd = -1;
    }

    /* access modifiers changed from: package-private */
    public abstract Object zza(int i);
}
