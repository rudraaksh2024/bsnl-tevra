package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
class zzbt extends zzbr implements List {
    final /* synthetic */ zzbu zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbt(zzbu zzbu, Object obj, @CheckForNull List list, zzbr zzbr) {
        super(zzbu, obj, list, zzbr);
        this.zzf = zzbu;
    }

    public final void add(int i, Object obj) {
        zzb();
        boolean isEmpty = this.zzb.isEmpty();
        ((List) this.zzb).add(i, obj);
        zzbu zzbu = this.zzf;
        zzbu.zzb = zzbu.zzb + 1;
        if (isEmpty) {
            zza();
        }
    }

    public final boolean addAll(int i, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.zzb).addAll(i, collection);
        if (addAll) {
            int size2 = this.zzb.size();
            zzbu zzbu = this.zzf;
            zzbu.zzb = zzbu.zzb + (size2 - size);
            if (size == 0) {
                zza();
                return true;
            }
        }
        return addAll;
    }

    public final Object get(int i) {
        zzb();
        return ((List) this.zzb).get(i);
    }

    public final int indexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.zzb).indexOf(obj);
    }

    public final int lastIndexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.zzb).lastIndexOf(obj);
    }

    public final ListIterator listIterator() {
        zzb();
        return new zzbs(this);
    }

    public final Object remove(int i) {
        zzb();
        Object remove = ((List) this.zzb).remove(i);
        zzbu zzbu = this.zzf;
        zzbu.zzb = zzbu.zzb - 1;
        zzc();
        return remove;
    }

    public final Object set(int i, Object obj) {
        zzb();
        return ((List) this.zzb).set(i, obj);
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [com.google.android.gms.internal.mlkit_vision_barcode.zzbr] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List subList(int r4, int r5) {
        /*
            r3 = this;
            r3.zzb()
            com.google.android.gms.internal.mlkit_vision_barcode.zzbu r0 = r3.zzf
            java.lang.Object r1 = r3.zza
            java.util.Collection r2 = r3.zzb
            java.util.List r2 = (java.util.List) r2
            java.util.List r4 = r2.subList(r4, r5)
            com.google.android.gms.internal.mlkit_vision_barcode.zzbr r5 = r3.zzc
            if (r5 == 0) goto L_0x0014
            r3 = r5
        L_0x0014:
            java.util.List r3 = r0.zzm(r1, r4, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzbt.subList(int, int):java.util.List");
    }

    public final ListIterator listIterator(int i) {
        zzb();
        return new zzbs(this, i);
    }
}
