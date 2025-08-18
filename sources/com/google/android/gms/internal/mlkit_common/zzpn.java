package com.google.android.gms.internal.mlkit_common;

import com.google.mlkit.common.sdkinternal.ModelType;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class zzpn extends zzqa {
    private zzlm zza;
    private String zzb;
    private boolean zzc;
    private boolean zzd;
    private ModelType zze;
    private zzls zzf;
    private int zzg;
    private byte zzh;

    zzpn() {
    }

    public final zzqa zza(zzls zzls) {
        if (zzls != null) {
            this.zzf = zzls;
            return this;
        }
        throw new NullPointerException("Null downloadStatus");
    }

    public final zzqa zzb(zzlm zzlm) {
        if (zzlm != null) {
            this.zza = zzlm;
            return this;
        }
        throw new NullPointerException("Null errorCode");
    }

    public final zzqa zzc(int i) {
        this.zzg = i;
        this.zzh = (byte) (this.zzh | 4);
        return this;
    }

    public final zzqa zzd(ModelType modelType) {
        if (modelType != null) {
            this.zze = modelType;
            return this;
        }
        throw new NullPointerException("Null modelType");
    }

    public final zzqa zze(boolean z) {
        this.zzd = z;
        this.zzh = (byte) (this.zzh | 2);
        return this;
    }

    public final zzqa zzf(boolean z) {
        this.zzc = z;
        this.zzh = (byte) (this.zzh | 1);
        return this;
    }

    public final zzqa zzg(String str) {
        this.zzb = "NA";
        return this;
    }

    public final zzqb zzh() {
        zzlm zzlm;
        String str;
        ModelType modelType;
        zzls zzls;
        if (this.zzh == 7 && (zzlm = this.zza) != null && (str = this.zzb) != null && (modelType = this.zze) != null && (zzls = this.zzf) != null) {
            return new zzpp(zzlm, str, this.zzc, this.zzd, modelType, zzls, this.zzg, (zzpo) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" errorCode");
        }
        if (this.zzb == null) {
            sb.append(" tfliteSchemaVersion");
        }
        if ((this.zzh & 1) == 0) {
            sb.append(" shouldLogRoughDownloadTime");
        }
        if ((this.zzh & 2) == 0) {
            sb.append(" shouldLogExactDownloadTime");
        }
        if (this.zze == null) {
            sb.append(" modelType");
        }
        if (this.zzf == null) {
            sb.append(" downloadStatus");
        }
        if ((this.zzh & 4) == 0) {
            sb.append(" failureStatusCode");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
