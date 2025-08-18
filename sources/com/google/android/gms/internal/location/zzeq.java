package com.google.android.gms.internal.location;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzeq extends zzep {
    public static boolean zza(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }
}
