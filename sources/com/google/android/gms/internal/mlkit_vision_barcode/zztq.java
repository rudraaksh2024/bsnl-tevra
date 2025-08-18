package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zztq implements zztn {
    final List zza;

    public zztq(Context context, zztp zztp) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zztp.zzc()) {
            arrayList.add(new zzuf(context, zztp));
        }
    }

    public final void zza(zztm zztm) {
        for (zztn zza2 : this.zza) {
            zza2.zza(zztm);
        }
    }
}
