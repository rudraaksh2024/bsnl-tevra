package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzbl extends zzdh {
    final transient Map zza;
    final /* synthetic */ zzbu zzb;

    zzbl(zzbu zzbu, Map map) {
        this.zzb = zzbu;
        this.zza = map;
    }

    public final void clear() {
        Map map = this.zza;
        zzbu zzbu = this.zzb;
        if (map == zzbu.zza) {
            zzbu.zzs();
        } else {
            zzda.zza(new zzbk(this));
        }
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        return zzdi.zzb(this.zza, obj);
    }

    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.zza.equals(obj);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Set keySet() {
        return this.zzb.zzw();
    }

    @CheckForNull
    public final /* bridge */ /* synthetic */ Object remove(@CheckForNull Object obj) {
        Collection collection = (Collection) this.zza.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection zza2 = this.zzb.zza();
        zza2.addAll(collection);
        zzbu zzbu = this.zzb;
        zzbu.zzb = zzbu.zzb - collection.size();
        collection.clear();
        return zza2;
    }

    public final int size() {
        return this.zza.size();
    }

    public final String toString() {
        return this.zza.toString();
    }

    @CheckForNull
    /* renamed from: zza */
    public final Collection get(@CheckForNull Object obj) {
        Collection collection = (Collection) zzdi.zza(this.zza, obj);
        if (collection == null) {
            return null;
        }
        return this.zzb.zzd(obj, collection);
    }

    /* access modifiers changed from: protected */
    public final Set zzb() {
        return new zzbj(this);
    }
}
