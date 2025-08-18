package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class zzbj implements zzbo {
    private final int zza;
    private final zzbn zzb;

    zzbj(int i, zzbn zzbn) {
        this.zza = i;
        this.zzb = zzbn;
    }

    public final Class annotationType() {
        return zzbo.class;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbo)) {
            return false;
        }
        zzbo zzbo = (zzbo) obj;
        return this.zza == zzbo.zza() && this.zzb.equals(zzbo.zzb());
    }

    public final int hashCode() {
        return (this.zza ^ 14552422) + (this.zzb.hashCode() ^ 2041407134);
    }

    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    public final int zza() {
        return this.zza;
    }

    public final zzbn zzb() {
        return this.zzb;
    }
}
