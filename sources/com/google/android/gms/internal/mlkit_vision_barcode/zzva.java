package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzva extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzva> CREATOR = new zzvp();
    private final zzve zza;
    private final String zzb;
    private final String zzc;
    private final zzvf[] zzd;
    private final zzvc[] zze;
    private final String[] zzf;
    private final zzux[] zzg;

    public zzva(zzve zzve, String str, String str2, zzvf[] zzvfArr, zzvc[] zzvcArr, String[] strArr, zzux[] zzuxArr) {
        this.zza = zzve;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzvfArr;
        this.zze = zzvcArr;
        this.zzf = strArr;
        this.zzg = zzuxArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeTypedArray(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeTypedArray(parcel, 7, this.zzg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzve zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final zzux[] zzd() {
        return this.zzg;
    }

    public final zzvc[] zze() {
        return this.zze;
    }

    public final zzvf[] zzf() {
        return this.zzd;
    }

    public final String[] zzg() {
        return this.zzf;
    }
}
