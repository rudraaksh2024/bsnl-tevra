package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.StyleSpan;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class zzae extends zza implements zzag {
    zzae(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
    }

    public final void zzA(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(11, zza);
    }

    public final void zzB(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(5, zza);
    }

    public final void zzC(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(9, zza);
    }

    public final boolean zzD(zzag zzag) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzag);
        Parcel zzH = zzH(15, zza);
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    public final boolean zzE() throws RemoteException {
        Parcel zzH = zzH(18, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    public final boolean zzF() throws RemoteException {
        Parcel zzH = zzH(14, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    public final boolean zzG() throws RemoteException {
        Parcel zzH = zzH(12, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    public final float zzd() throws RemoteException {
        Parcel zzH = zzH(6, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    public final float zze() throws RemoteException {
        Parcel zzH = zzH(10, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    public final int zzf() throws RemoteException {
        Parcel zzH = zzH(8, zza());
        int readInt = zzH.readInt();
        zzH.recycle();
        return readInt;
    }

    public final int zzg() throws RemoteException {
        Parcel zzH = zzH(24, zza());
        int readInt = zzH.readInt();
        zzH.recycle();
        return readInt;
    }

    public final int zzh() throws RemoteException {
        Parcel zzH = zzH(16, zza());
        int readInt = zzH.readInt();
        zzH.recycle();
        return readInt;
    }

    public final IObjectWrapper zzi() throws RemoteException {
        Parcel zzH = zzH(28, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzH.readStrongBinder());
        zzH.recycle();
        return asInterface;
    }

    public final Cap zzj() throws RemoteException {
        Parcel zzH = zzH(22, zza());
        Cap cap = (Cap) zzc.zza(zzH, Cap.CREATOR);
        zzH.recycle();
        return cap;
    }

    public final Cap zzk() throws RemoteException {
        Parcel zzH = zzH(20, zza());
        Cap cap = (Cap) zzc.zza(zzH, Cap.CREATOR);
        zzH.recycle();
        return cap;
    }

    public final String zzl() throws RemoteException {
        Parcel zzH = zzH(2, zza());
        String readString = zzH.readString();
        zzH.recycle();
        return readString;
    }

    public final List zzm() throws RemoteException {
        Parcel zzH = zzH(26, zza());
        ArrayList<PatternItem> createTypedArrayList = zzH.createTypedArrayList(PatternItem.CREATOR);
        zzH.recycle();
        return createTypedArrayList;
    }

    public final List zzn() throws RemoteException {
        Parcel zzH = zzH(4, zza());
        ArrayList<LatLng> createTypedArrayList = zzH.createTypedArrayList(LatLng.CREATOR);
        zzH.recycle();
        return createTypedArrayList;
    }

    public final List zzo() throws RemoteException {
        Parcel zzH = zzH(30, zza());
        ArrayList<StyleSpan> createTypedArrayList = zzH.createTypedArrayList(StyleSpan.CREATOR);
        zzH.recycle();
        return createTypedArrayList;
    }

    public final void zzp() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzq(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(17, zza);
    }

    public final void zzr(int i) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i);
        zzc(7, zza);
    }

    public final void zzs(Cap cap) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, cap);
        zzc(21, zza);
    }

    public final void zzt(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(13, zza);
    }

    public final void zzu(int i) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i);
        zzc(23, zza);
    }

    public final void zzv(List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzc(25, zza);
    }

    public final void zzw(List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzc(3, zza);
    }

    public final void zzx(List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzc(29, zza);
    }

    public final void zzy(Cap cap) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, cap);
        zzc(19, zza);
    }

    public final void zzz(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(27, zza);
    }
}
