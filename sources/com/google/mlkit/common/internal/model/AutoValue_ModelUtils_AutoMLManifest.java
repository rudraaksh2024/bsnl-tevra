package com.google.mlkit.common.internal.model;

import com.google.mlkit.common.internal.model.ModelUtils;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class AutoValue_ModelUtils_AutoMLManifest extends ModelUtils.AutoMLManifest {
    private final String zza;
    private final String zzb;
    private final String zzc;

    AutoValue_ModelUtils_AutoMLManifest(String str, String str2, String str3) {
        if (str != null) {
            this.zza = str;
            if (str2 != null) {
                this.zzb = str2;
                if (str3 != null) {
                    this.zzc = str3;
                    return;
                }
                throw new NullPointerException("Null labelsFile");
            }
            throw new NullPointerException("Null modelFile");
        }
        throw new NullPointerException("Null modelType");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ModelUtils.AutoMLManifest) {
            ModelUtils.AutoMLManifest autoMLManifest = (ModelUtils.AutoMLManifest) obj;
            return this.zza.equals(autoMLManifest.getModelType()) && this.zzb.equals(autoMLManifest.getModelFile()) && this.zzc.equals(autoMLManifest.getLabelsFile());
        }
    }

    public String getLabelsFile() {
        return this.zzc;
    }

    public String getModelFile() {
        return this.zzb;
    }

    public String getModelType() {
        return this.zza;
    }

    public final int hashCode() {
        return this.zzc.hashCode() ^ ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003);
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        String str3 = this.zzc;
        return "AutoMLManifest{modelType=" + str + ", modelFile=" + str2 + ", labelsFile=" + str3 + VectorFormat.DEFAULT_SUFFIX;
    }
}
