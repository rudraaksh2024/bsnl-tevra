package com.google.android.gms.internal.mlkit_common;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public abstract class zzav extends zzan implements Set {
    @CheckForNull
    private transient zzar zza;

    zzav() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size() && containsAll(set)) {
                    return true;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public final int hashCode() {
        return zzbd.zza(this);
    }

    /* renamed from: zzd */
    public abstract zzbe iterator();

    public final zzar zzf() {
        zzar zzar = this.zza;
        if (zzar != null) {
            return zzar;
        }
        zzar zzg = zzg();
        this.zza = zzg;
        return zzg;
    }

    /* access modifiers changed from: package-private */
    public zzar zzg() {
        Object[] array = toArray();
        int i = zzar.zzd;
        return zzar.zzg(array, array.length);
    }
}
