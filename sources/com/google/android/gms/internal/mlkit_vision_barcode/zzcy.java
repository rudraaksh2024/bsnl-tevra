package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public abstract class zzcy extends zzcq implements Set {
    @CheckForNull
    private transient zzcv zza;

    zzcy() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        return zzdv.zzb(this, obj);
    }

    public final int hashCode() {
        return zzdv.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzdx iterator();

    public final zzcv zzf() {
        zzcv zzcv = this.zza;
        if (zzcv != null) {
            return zzcv;
        }
        zzcv zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    /* access modifiers changed from: package-private */
    public zzcv zzg() {
        Object[] array = toArray();
        int i = zzcv.zzd;
        return zzcv.zzg(array, array.length);
    }
}
