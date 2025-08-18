package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzaw {
    private final String zza;
    private final zzau zzb;
    private zzau zzc;

    /* synthetic */ zzaw(String str, zzav zzav) {
        zzau zzau = new zzau((zzat) null);
        this.zzb = zzau;
        this.zzc = zzau;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzau zzau = this.zzb.zzb;
        String str = "";
        while (zzau != null) {
            Object obj = zzau.zza;
            sb.append(str);
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append(deepToString, 1, deepToString.length() - 1);
            }
            zzau = zzau.zzb;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzaw zza(@CheckForNull Object obj) {
        zzau zzau = new zzau((zzat) null);
        this.zzc.zzb = zzau;
        this.zzc = zzau;
        zzau.zza = obj;
        return this;
    }
}
