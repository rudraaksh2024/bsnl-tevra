package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzb;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzz;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public abstract class zzat extends zzb implements zzau {
    public zzat() {
        super("com.google.android.gms.maps.internal.IOnMarkerClickListener");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzaa zzb = zzz.zzb(parcel.readStrongBinder());
        zzc.zzc(parcel);
        boolean zzb2 = zzb(zzb);
        parcel2.writeNoException();
        zzc.zzd(parcel2, zzb2);
        return true;
    }
}
