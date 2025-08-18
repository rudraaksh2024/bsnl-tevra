package com.google.android.gms.internal.mlkit_common;

import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class zzaq extends zzar {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzar zzc;

    zzaq(zzar zzar, int i, int i2) {
        this.zzc = zzar;
        this.zza = i;
        this.zzb = i2;
    }

    public final Object get(int i) {
        zzaf.zza(i, this.zzb, "index");
        return this.zzc.get(i + this.zza);
    }

    public final int size() {
        return this.zzb;
    }

    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public final Object[] zze() {
        return this.zzc.zze();
    }

    public final zzar zzf(int i, int i2) {
        zzaf.zzd(i, i2, this.zzb);
        zzar zzar = this.zzc;
        int i3 = this.zza;
        return zzar.subList(i + i3, i2 + i3);
    }
}
