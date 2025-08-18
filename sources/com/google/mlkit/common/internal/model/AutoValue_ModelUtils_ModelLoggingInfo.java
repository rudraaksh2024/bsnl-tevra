package com.google.mlkit.common.internal.model;

import com.google.mlkit.common.internal.model.ModelUtils;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class AutoValue_ModelUtils_ModelLoggingInfo extends ModelUtils.ModelLoggingInfo {
    private final long zza;
    private final String zzb;
    private final boolean zzc;

    AutoValue_ModelUtils_ModelLoggingInfo(long j, String str, boolean z) {
        this.zza = j;
        this.zzb = str;
        this.zzc = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ModelUtils.ModelLoggingInfo) {
            ModelUtils.ModelLoggingInfo modelLoggingInfo = (ModelUtils.ModelLoggingInfo) obj;
            return this.zza == modelLoggingInfo.getSize() && this.zzb.equals(modelLoggingInfo.getHash()) && this.zzc == modelLoggingInfo.isManifestModel();
        }
    }

    public String getHash() {
        return this.zzb;
    }

    public long getSize() {
        return this.zza;
    }

    public final int hashCode() {
        long j = this.zza;
        return (true != this.zzc ? 1237 : 1231) ^ ((((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003);
    }

    public boolean isManifestModel() {
        return this.zzc;
    }

    public final String toString() {
        long j = this.zza;
        String str = this.zzb;
        boolean z = this.zzc;
        return "ModelLoggingInfo{size=" + j + ", hash=" + str + ", manifestModel=" + z + VectorFormat.DEFAULT_SUFFIX;
    }
}
