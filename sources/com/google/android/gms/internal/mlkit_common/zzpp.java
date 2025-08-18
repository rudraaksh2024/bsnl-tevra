package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class zzpp extends zzqb {
    private final zzlm zza;
    private final String zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final ModelType zze;
    private final zzls zzf;
    private final int zzg;

    /* synthetic */ zzpp(zzlm zzlm, String str, boolean z, boolean z2, ModelType modelType, zzls zzls, int i, zzpo zzpo) {
        this.zza = zzlm;
        this.zzb = str;
        this.zzc = z;
        this.zzd = z2;
        this.zze = modelType;
        this.zzf = zzls;
        this.zzg = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzqb) {
            zzqb zzqb = (zzqb) obj;
            return this.zza.equals(zzqb.zzc()) && this.zzb.equals(zzqb.zze()) && this.zzc == zzqb.zzg() && this.zzd == zzqb.zzf() && this.zze.equals(zzqb.zzb()) && this.zzf.equals(zzqb.zzd()) && this.zzg == zzqb.zza();
        }
    }

    public final int hashCode() {
        int hashCode = ((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode();
        int i = 1237;
        int i2 = true != this.zzc ? 1237 : 1231;
        if (true == this.zzd) {
            i = 1231;
        }
        return this.zzg ^ (((((((((hashCode * 1000003) ^ i2) * 1000003) ^ i) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode()) * 1000003);
    }

    public final String toString() {
        String obj = this.zza.toString();
        String str = this.zzb;
        boolean z = this.zzc;
        boolean z2 = this.zzd;
        String obj2 = this.zze.toString();
        String obj3 = this.zzf.toString();
        int i = this.zzg;
        return "RemoteModelLoggingOptions{errorCode=" + obj + ", tfliteSchemaVersion=" + str + ", shouldLogRoughDownloadTime=" + z + ", shouldLogExactDownloadTime=" + z2 + ", modelType=" + obj2 + ", downloadStatus=" + obj3 + ", failureStatusCode=" + i + VectorFormat.DEFAULT_SUFFIX;
    }

    public final int zza() {
        return this.zzg;
    }

    public final ModelType zzb() {
        return this.zze;
    }

    public final zzlm zzc() {
        return this.zza;
    }

    public final zzls zzd() {
        return this.zzf;
    }

    public final String zze() {
        return this.zzb;
    }

    public final boolean zzf() {
        return this.zzd;
    }

    public final boolean zzg() {
        return this.zzc;
    }
}
