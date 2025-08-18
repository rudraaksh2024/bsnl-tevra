package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzfb implements zzfg {
    private final int zza;
    private final zzff zzb;

    zzfb(int i, zzff zzff) {
        this.zza = i;
        this.zzb = zzff;
    }

    public final Class annotationType() {
        return zzfg.class;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfg)) {
            return false;
        }
        zzfg zzfg = (zzfg) obj;
        return this.zza == zzfg.zza() && this.zzb.equals(zzfg.zzb());
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

    public final zzff zzb() {
        return this.zzb;
    }
}
