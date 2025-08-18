package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.9.0 */
final class zzpk extends zzps {
    private String zza;
    private boolean zzb;
    private int zzc;
    private byte zzd;

    zzpk() {
    }

    public final zzps zza(boolean z) {
        this.zzb = true;
        this.zzd = (byte) (1 | this.zzd);
        return this;
    }

    public final zzps zzb(int i) {
        this.zzc = 1;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzps zzc(String str) {
        this.zza = "common";
        return this;
    }

    public final zzpt zzd() {
        String str;
        if (this.zzd == 3 && (str = this.zza) != null) {
            return new zzpm(str, this.zzb, this.zzc, (zzpl) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" libraryName");
        }
        if ((this.zzd & 1) == 0) {
            sb.append(" enableFirelog");
        }
        if ((this.zzd & 2) == 0) {
            sb.append(" firelogEventType");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
