package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
import javax.annotation.CheckForNull;
import org.apache.logging.log4j.util.Chars;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzz {
    private final String zza;
    private final zzx zzb;
    private zzx zzc;

    /* synthetic */ zzz(String str, zzy zzy) {
        zzx zzx = new zzx((zzw) null);
        this.zzb = zzx;
        this.zzc = zzx;
        str.getClass();
        this.zza = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.zza);
        sb.append('{');
        zzx zzx = this.zzb.zzc;
        String str = "";
        while (zzx != null) {
            Object obj = zzx.zzb;
            sb.append(str);
            String str2 = zzx.zza;
            if (str2 != null) {
                sb.append(str2);
                sb.append(Chars.EQ);
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append(deepToString, 1, deepToString.length() - 1);
            }
            zzx = zzx.zzc;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }

    public final zzz zza(String str, @CheckForNull Object obj) {
        zzx zzx = new zzx((zzw) null);
        this.zzc.zzc = zzx;
        this.zzc = zzx;
        zzx.zzb = obj;
        zzx.zza = str;
        return this;
    }

    public final zzz zzb(String str, boolean z) {
        String valueOf = String.valueOf(z);
        zzv zzv = new zzv((zzu) null);
        this.zzc.zzc = zzv;
        this.zzc = zzv;
        zzv.zzb = valueOf;
        zzv.zza = "isManifestFile";
        return this;
    }
}
