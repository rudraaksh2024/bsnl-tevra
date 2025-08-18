package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
final class zztj extends zzto {
    private String zza;
    private boolean zzb;
    private int zzc;
    private byte zzd;

    zztj() {
    }

    public final zzto zza(boolean z) {
        this.zzb = true;
        this.zzd = (byte) (1 | this.zzd);
        return this;
    }

    public final zzto zzb(int i) {
        this.zzc = 1;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzto zzc(String str) {
        this.zza = str;
        return this;
    }

    public final zztp zzd() {
        String str;
        if (this.zzd == 3 && (str = this.zza) != null) {
            return new zztl(str, this.zzb, this.zzc, (zztk) null);
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
