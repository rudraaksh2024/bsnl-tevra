package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzuo extends zzuv {
    private final float zza;
    private final float zzb;
    private final float zzc;
    private final float zzd;

    zzuo(float f, float f2, float f3, float f4, float f5) {
        this.zza = f;
        this.zzb = f2;
        this.zzc = f3;
        this.zzd = f4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzuv) {
            zzuv zzuv = (zzuv) obj;
            if (Float.floatToIntBits(this.zza) == Float.floatToIntBits(zzuv.zzc()) && Float.floatToIntBits(this.zzb) == Float.floatToIntBits(zzuv.zze()) && Float.floatToIntBits(this.zzc) == Float.floatToIntBits(zzuv.zzb()) && Float.floatToIntBits(this.zzd) == Float.floatToIntBits(zzuv.zzd())) {
                int floatToIntBits = Float.floatToIntBits(0.0f);
                zzuv.zza();
                if (floatToIntBits == Float.floatToIntBits(0.0f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((Float.floatToIntBits(this.zzd) ^ ((((((Float.floatToIntBits(this.zza) ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.zzb)) * 1000003) ^ Float.floatToIntBits(this.zzc)) * 1000003)) * 1000003) ^ Float.floatToIntBits(0.0f);
    }

    public final String toString() {
        float f = this.zza;
        float f2 = this.zzb;
        float f3 = this.zzc;
        float f4 = this.zzd;
        return "PredictedArea{xMin=" + f + ", yMin=" + f2 + ", xMax=" + f3 + ", yMax=" + f4 + ", confidenceScore=0.0}";
    }

    /* access modifiers changed from: package-private */
    public final float zza() {
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public final float zzb() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final float zzc() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final float zzd() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final float zze() {
        return this.zzb;
    }
}
