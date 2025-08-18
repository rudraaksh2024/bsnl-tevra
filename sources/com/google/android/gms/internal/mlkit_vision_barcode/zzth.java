package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzth {
    private final zzcv zza;

    /* synthetic */ zzth(zztf zztf, zztg zztg) {
        this.zza = zztf.zza;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzth)) {
            return false;
        }
        return Objects.equal(this.zza, ((zzth) obj).zza);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public final zzcv zza() {
        return this.zza;
    }
}
