package com.google.android.gms.internal.mlkit_vision_barcode;

import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zzun extends zzuu {
    private final int zzc;
    private final int zzd;
    private final float zze;
    private final float zzf;
    private final boolean zzg;
    private final float zzh;
    private final float zzi;
    private final long zzj;
    private final long zzk;
    private final boolean zzl;
    private final float zzm;
    private final float zzn;

    /* synthetic */ zzun(int i, int i2, float f, float f2, boolean z, float f3, float f4, long j, long j2, boolean z2, float f5, float f6, zzum zzum) {
        this.zzc = i;
        this.zzd = i2;
        this.zze = f;
        this.zzf = f2;
        this.zzg = z;
        this.zzh = f3;
        this.zzi = f4;
        this.zzj = j;
        this.zzk = j2;
        this.zzl = z2;
        this.zzm = f5;
        this.zzn = f6;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzuu) {
            zzuu zzuu = (zzuu) obj;
            return this.zzc == zzuu.zzh() && this.zzd == zzuu.zzg() && Float.floatToIntBits(this.zze) == Float.floatToIntBits(zzuu.zzd()) && Float.floatToIntBits(this.zzf) == Float.floatToIntBits(zzuu.zzc()) && this.zzg == zzuu.zzl() && Float.floatToIntBits(this.zzh) == Float.floatToIntBits(zzuu.zzb()) && Float.floatToIntBits(this.zzi) == Float.floatToIntBits(zzuu.zza()) && this.zzj == zzuu.zzj() && this.zzk == zzuu.zzi() && this.zzl == zzuu.zzk() && Float.floatToIntBits(this.zzm) == Float.floatToIntBits(zzuu.zze()) && Float.floatToIntBits(this.zzn) == Float.floatToIntBits(zzuu.zzf());
        }
    }

    public final int hashCode() {
        int i = 1237;
        int floatToIntBits = ((((((((((((this.zzc ^ 1000003) * 1000003) ^ this.zzd) * 1000003) ^ Float.floatToIntBits(this.zze)) * 1000003) ^ Float.floatToIntBits(this.zzf)) * 1000003) ^ (true != this.zzg ? 1237 : 1231)) * 1000003) ^ Float.floatToIntBits(this.zzh)) * 1000003) ^ Float.floatToIntBits(this.zzi);
        int i2 = (int) this.zzj;
        int i3 = (int) this.zzk;
        if (true == this.zzl) {
            i = 1231;
        }
        return Float.floatToIntBits(this.zzn) ^ (((((((((floatToIntBits * 1000003) ^ i2) * 1000003) ^ i3) * 1000003) ^ i) * 1000003) ^ Float.floatToIntBits(this.zzm)) * 1000003);
    }

    public final String toString() {
        int i = this.zzc;
        int i2 = this.zzd;
        float f = this.zze;
        float f2 = this.zzf;
        boolean z = this.zzg;
        float f3 = this.zzh;
        float f4 = this.zzi;
        long j = this.zzj;
        long j2 = this.zzk;
        boolean z2 = this.zzl;
        float f5 = this.zzm;
        float f6 = this.zzn;
        return "AutoZoomOptions{recentFramesToCheck=" + i + ", recentFramesContainingPredictedArea=" + i2 + ", recentFramesIou=" + f + ", maxCoverage=" + f2 + ", useConfidenceScore=" + z + ", lowerConfidenceScore=" + f3 + ", higherConfidenceScore=" + f4 + ", zoomIntervalInMillis=" + j + ", resetIntervalInMillis=" + j2 + ", enableZoomThreshold=" + z2 + ", zoomInThreshold=" + f5 + ", zoomOutThreshold=" + f6 + VectorFormat.DEFAULT_SUFFIX;
    }

    /* access modifiers changed from: package-private */
    public final float zza() {
        return this.zzi;
    }

    /* access modifiers changed from: package-private */
    public final float zzb() {
        return this.zzh;
    }

    /* access modifiers changed from: package-private */
    public final float zzc() {
        return this.zzf;
    }

    /* access modifiers changed from: package-private */
    public final float zzd() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final float zze() {
        return this.zzm;
    }

    /* access modifiers changed from: package-private */
    public final float zzf() {
        return this.zzn;
    }

    /* access modifiers changed from: package-private */
    public final int zzg() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final int zzh() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final long zzi() {
        return this.zzk;
    }

    /* access modifiers changed from: package-private */
    public final long zzj() {
        return this.zzj;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzk() {
        return this.zzl;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzl() {
        return this.zzg;
    }
}
