package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.internal.mlkit_vision_barcode.zzpi;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzqa;
import com.google.android.gms.internal.mlkit_vision_barcode.zztm;
import com.google.android.gms.internal.mlkit_vision_barcode.zztw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzua;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final /* synthetic */ class zza implements zztw {
    public final /* synthetic */ zzpj zza;

    public /* synthetic */ zza(zzpj zzpj) {
        this.zza = zzpj;
    }

    public final zztm zza() {
        zzpj zzpj = this.zza;
        zzpl zzpl = new zzpl();
        zzpl.zze(zzb.zzf() ? zzpi.TYPE_THICK : zzpi.TYPE_THIN);
        zzqa zzqa = new zzqa();
        zzqa.zzb(zzpj);
        zzpl.zzh(zzqa.zzc());
        return zzua.zzf(zzpl);
    }
}
