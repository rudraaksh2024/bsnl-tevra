package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.List;
import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzbs extends zzbq implements ListIterator {
    final /* synthetic */ zzbt zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbs(zzbt zzbt) {
        super(zzbt);
        this.zzd = zzbt;
    }

    public final void add(Object obj) {
        boolean isEmpty = this.zzd.isEmpty();
        zza();
        ((ListIterator) this.zza).add(obj);
        zzbu zzbu = this.zzd.zzf;
        zzbu.zzb = zzbu.zzb + 1;
        if (isEmpty) {
            this.zzd.zza();
        }
    }

    public final boolean hasPrevious() {
        zza();
        return ((ListIterator) this.zza).hasPrevious();
    }

    public final int nextIndex() {
        zza();
        return ((ListIterator) this.zza).nextIndex();
    }

    public final Object previous() {
        zza();
        return ((ListIterator) this.zza).previous();
    }

    public final int previousIndex() {
        zza();
        return ((ListIterator) this.zza).previousIndex();
    }

    public final void set(Object obj) {
        zza();
        ((ListIterator) this.zza).set(obj);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbs(zzbt zzbt, int i) {
        super(zzbt, ((List) zzbt.zzb).listIterator(i));
        this.zzd = zzbt;
    }
}
