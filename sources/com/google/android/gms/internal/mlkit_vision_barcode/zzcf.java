package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzcf extends AbstractSet {
    final /* synthetic */ zzcl zza;

    zzcf(zzcl zzcl) {
        this.zza = zzcl;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean contains(@CheckForNull Object obj) {
        Map zzj = this.zza.zzj();
        if (zzj != null) {
            return zzj.entrySet().contains(obj);
        }
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            int zzd = this.zza.zzq(entry.getKey());
            if (zzd != -1) {
                Object[] objArr = this.zza.zzc;
                objArr.getClass();
                if (zzay.zza(objArr[zzd], entry.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final Iterator iterator() {
        zzcl zzcl = this.zza;
        Map zzj = zzcl.zzj();
        if (zzj != null) {
            return zzj.entrySet().iterator();
        }
        return new zzcd(zzcl);
    }

    public final boolean remove(@CheckForNull Object obj) {
        Map zzj = this.zza.zzj();
        if (zzj != null) {
            return zzj.entrySet().remove(obj);
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        zzcl zzcl = this.zza;
        if (zzcl.zzo()) {
            return false;
        }
        int zzc = zzcl.zzp();
        Object key = entry.getKey();
        Object value = entry.getValue();
        Object zzh = zzcl.zzh(this.zza);
        zzcl zzcl2 = this.zza;
        int[] iArr = zzcl2.zza;
        iArr.getClass();
        Object[] objArr = zzcl2.zzb;
        objArr.getClass();
        Object[] objArr2 = zzcl2.zzc;
        objArr2.getClass();
        int zzb = zzcm.zzb(key, value, zzc, zzh, iArr, objArr, objArr2);
        if (zzb == -1) {
            return false;
        }
        this.zza.zzn(zzb, zzc);
        zzcl zzcl3 = this.zza;
        zzcl3.zzg = zzcl3.zzg - 1;
        this.zza.zzl();
        return true;
    }

    public final int size() {
        return this.zza.size();
    }
}
