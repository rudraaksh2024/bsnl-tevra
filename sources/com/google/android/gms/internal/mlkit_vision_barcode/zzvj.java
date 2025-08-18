package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzvj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvj> CREATOR = new zzvk();
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final byte[] zzd;
    private final Point[] zze;
    private final int zzf;
    private final zzvc zzg;
    private final zzvf zzh;
    private final zzvg zzi;
    private final zzvi zzj;
    private final zzvh zzk;
    private final zzvd zzl;
    private final zzuz zzm;
    private final zzva zzn;
    private final zzvb zzo;

    public zzvj(int i, String str, String str2, byte[] bArr, Point[] pointArr, int i2, zzvc zzvc, zzvf zzvf, zzvg zzvg, zzvi zzvi, zzvh zzvh, zzvd zzvd, zzuz zzuz, zzva zzva, zzvb zzvb) {
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bArr;
        this.zze = pointArr;
        this.zzf = i2;
        this.zzg = zzvc;
        this.zzh = zzvf;
        this.zzi = zzvg;
        this.zzj = zzvi;
        this.zzk = zzvh;
        this.zzl = zzvd;
        this.zzm = zzuz;
        this.zzn = zzva;
        this.zzo = zzvb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzf);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzh, i, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzi, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzj, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i, false);
        SafeParcelWriter.writeParcelable(parcel, 13, this.zzm, i, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzn, i, false);
        SafeParcelWriter.writeParcelable(parcel, 15, this.zzo, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        return this.zzf;
    }

    public final zzuz zzc() {
        return this.zzm;
    }

    public final zzva zzd() {
        return this.zzn;
    }

    public final zzvb zze() {
        return this.zzo;
    }

    public final zzvc zzf() {
        return this.zzg;
    }

    public final zzvd zzg() {
        return this.zzl;
    }

    public final zzvf zzh() {
        return this.zzh;
    }

    public final zzvg zzi() {
        return this.zzi;
    }

    public final zzvh zzj() {
        return this.zzk;
    }

    public final zzvi zzk() {
        return this.zzj;
    }

    public final String zzl() {
        return this.zzb;
    }

    public final String zzm() {
        return this.zzc;
    }

    public final byte[] zzn() {
        return this.zzd;
    }

    public final Point[] zzo() {
        return this.zze;
    }
}
