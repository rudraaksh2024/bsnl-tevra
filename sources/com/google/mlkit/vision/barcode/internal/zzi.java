package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.internal.mlkit_vision_barcode.zzcs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpj;
import com.google.android.gms.internal.mlkit_vision_barcode.zztm;
import com.google.android.gms.internal.mlkit_vision_barcode.zztw;
import com.google.mlkit.vision.common.InputImage;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final /* synthetic */ class zzi implements zztw {
    public final /* synthetic */ zzk zza;
    public final /* synthetic */ long zzb;
    public final /* synthetic */ zzpj zzc;
    public final /* synthetic */ zzcs zzd;
    public final /* synthetic */ zzcs zze;
    public final /* synthetic */ InputImage zzf;

    public /* synthetic */ zzi(zzk zzk, long j, zzpj zzpj, zzcs zzcs, zzcs zzcs2, InputImage inputImage) {
        this.zza = zzk;
        this.zzb = j;
        this.zzc = zzpj;
        this.zzd = zzcs;
        this.zze = zzcs2;
        this.zzf = inputImage;
    }

    public final zztm zza() {
        return this.zza.zzc(this.zzb, this.zzc, this.zzd, this.zze, this.zzf);
    }
}
