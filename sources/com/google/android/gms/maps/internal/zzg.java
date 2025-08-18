package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzad;
import com.google.android.gms.internal.maps.zzaf;
import com.google.android.gms.internal.maps.zzag;
import com.google.android.gms.internal.maps.zzai;
import com.google.android.gms.internal.maps.zzaj;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzo;
import com.google.android.gms.internal.maps.zzq;
import com.google.android.gms.internal.maps.zzr;
import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class zzg extends zza implements IGoogleMapDelegate {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public final zzl addCircle(CircleOptions circleOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, circleOptions);
        Parcel zzH = zzH(35, zza);
        zzl zzb = zzk.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final zzo addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, groundOverlayOptions);
        Parcel zzH = zzH(12, zza);
        zzo zzb = zzn.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final zzaa addMarker(MarkerOptions markerOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, markerOptions);
        Parcel zzH = zzH(11, zza);
        zzaa zzb = zzz.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final void addOnMapCapabilitiesChangedListener(zzak zzak) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzak);
        zzc(110, zza);
    }

    public final zzad addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, polygonOptions);
        Parcel zzH = zzH(10, zza);
        zzad zzb = zzac.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final zzag addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, polylineOptions);
        Parcel zzH = zzH(9, zza);
        zzag zzb = zzaf.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final zzaj addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, tileOverlayOptions);
        Parcel zzH = zzH(13, zza);
        zzaj zzb = zzai.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(5, zza);
    }

    public final void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzd zzd) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc.zzg(zza, zzd);
        zzc(6, zza);
    }

    public final void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzd zzd) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zza.writeInt(i);
        zzc.zzg(zza, zzd);
        zzc(7, zza);
    }

    public final void clear() throws RemoteException {
        zzc(14, zza());
    }

    public final CameraPosition getCameraPosition() throws RemoteException {
        Parcel zzH = zzH(1, zza());
        CameraPosition cameraPosition = (CameraPosition) zzc.zza(zzH, CameraPosition.CREATOR);
        zzH.recycle();
        return cameraPosition;
    }

    public final zzr getFocusedBuilding() throws RemoteException {
        Parcel zzH = zzH(44, zza());
        zzr zzb = zzq.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final void getMapAsync(zzas zzas) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzas);
        zzc(53, zza);
    }

    public final zzx getMapCapabilities() throws RemoteException {
        Parcel zzH = zzH(109, zza());
        zzx zzb = zzw.zzb(zzH.readStrongBinder());
        zzH.recycle();
        return zzb;
    }

    public final int getMapType() throws RemoteException {
        Parcel zzH = zzH(15, zza());
        int readInt = zzH.readInt();
        zzH.recycle();
        return readInt;
    }

    public final float getMaxZoomLevel() throws RemoteException {
        Parcel zzH = zzH(2, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    public final float getMinZoomLevel() throws RemoteException {
        Parcel zzH = zzH(3, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    public final Location getMyLocation() throws RemoteException {
        Parcel zzH = zzH(23, zza());
        Location location = (Location) zzc.zza(zzH, Location.CREATOR);
        zzH.recycle();
        return location;
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IProjectionDelegate getProjection() throws android.os.RemoteException {
        /*
            r3 = this;
            r0 = 26
            android.os.Parcel r1 = r3.zza()
            android.os.Parcel r3 = r3.zzH(r0, r1)
            android.os.IBinder r0 = r3.readStrongBinder()
            if (r0 != 0) goto L_0x0012
            r0 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r1 = "com.google.android.gms.maps.internal.IProjectionDelegate"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.maps.internal.IProjectionDelegate
            if (r2 == 0) goto L_0x0020
            r0 = r1
            com.google.android.gms.maps.internal.IProjectionDelegate r0 = (com.google.android.gms.maps.internal.IProjectionDelegate) r0
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.maps.internal.zzbt r1 = new com.google.android.gms.maps.internal.zzbt
            r1.<init>(r0)
            r0 = r1
        L_0x0026:
            r3.recycle()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getProjection():com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IUiSettingsDelegate getUiSettings() throws android.os.RemoteException {
        /*
            r3 = this;
            r0 = 25
            android.os.Parcel r1 = r3.zza()
            android.os.Parcel r3 = r3.zzH(r0, r1)
            android.os.IBinder r0 = r3.readStrongBinder()
            if (r0 != 0) goto L_0x0012
            r0 = 0
            goto L_0x0026
        L_0x0012:
            java.lang.String r1 = "com.google.android.gms.maps.internal.IUiSettingsDelegate"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.maps.internal.IUiSettingsDelegate
            if (r2 == 0) goto L_0x0020
            r0 = r1
            com.google.android.gms.maps.internal.IUiSettingsDelegate r0 = (com.google.android.gms.maps.internal.IUiSettingsDelegate) r0
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.maps.internal.zzbz r1 = new com.google.android.gms.maps.internal.zzbz
            r1.<init>(r0)
            r0 = r1
        L_0x0026:
            r3.recycle()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzg.getUiSettings():com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    public final boolean isBuildingsEnabled() throws RemoteException {
        Parcel zzH = zzH(40, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    public final boolean isIndoorEnabled() throws RemoteException {
        Parcel zzH = zzH(19, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    public final boolean isMyLocationEnabled() throws RemoteException {
        Parcel zzH = zzH(21, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    public final boolean isTrafficEnabled() throws RemoteException {
        Parcel zzH = zzH(17, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    public final void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iObjectWrapper);
        zzc(4, zza);
    }

    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        zzc(54, zza);
    }

    public final void onDestroy() throws RemoteException {
        zzc(57, zza());
    }

    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        zzc(81, zza);
    }

    public final void onExitAmbient() throws RemoteException {
        zzc(82, zza());
    }

    public final void onLowMemory() throws RemoteException {
        zzc(58, zza());
    }

    public final void onPause() throws RemoteException {
        zzc(56, zza());
    }

    public final void onResume() throws RemoteException {
        zzc(55, zza());
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, bundle);
        Parcel zzH = zzH(60, zza);
        if (zzH.readInt() != 0) {
            bundle.readFromParcel(zzH);
        }
        zzH.recycle();
    }

    public final void onStart() throws RemoteException {
        zzc(101, zza());
    }

    public final void onStop() throws RemoteException {
        zzc(102, zza());
    }

    public final void removeOnMapCapabilitiesChangedListener(zzak zzak) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzak);
        zzc(111, zza);
    }

    public final void resetMinMaxZoomPreference() throws RemoteException {
        zzc(94, zza());
    }

    public final void setBuildingsEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(41, zza);
    }

    public final void setContentDescription(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzc(61, zza);
    }

    public final boolean setIndoorEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        Parcel zzH = zzH(20, zza);
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    public final void setInfoWindowAdapter(zzi zzi) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzi);
        zzc(33, zza);
    }

    public final void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, latLngBounds);
        zzc(95, zza);
    }

    public final void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, iLocationSourceDelegate);
        zzc(24, zza);
    }

    public final boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException {
        Parcel zza = zza();
        zzc.zze(zza, mapStyleOptions);
        Parcel zzH = zzH(91, zza);
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }

    public final void setMapType(int i) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i);
        zzc(16, zza);
    }

    public final void setMaxZoomPreference(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(93, zza);
    }

    public final void setMinZoomPreference(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(92, zza);
    }

    public final void setMyLocationEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(22, zza);
    }

    public final void setOnCameraChangeListener(zzn zzn) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzn);
        zzc(27, zza);
    }

    public final void setOnCameraIdleListener(zzp zzp) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzp);
        zzc(99, zza);
    }

    public final void setOnCameraMoveCanceledListener(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzr);
        zzc(98, zza);
    }

    public final void setOnCameraMoveListener(zzt zzt) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzt);
        zzc(97, zza);
    }

    public final void setOnCameraMoveStartedListener(zzv zzv) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzv);
        zzc(96, zza);
    }

    public final void setOnCircleClickListener(zzx zzx) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzx);
        zzc(89, zza);
    }

    public final void setOnGroundOverlayClickListener(zzz zzz) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzz);
        zzc(83, zza);
    }

    public final void setOnIndoorStateChangeListener(zzab zzab) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzab);
        zzc(45, zza);
    }

    public final void setOnInfoWindowClickListener(zzad zzad) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzad);
        zzc(32, zza);
    }

    public final void setOnInfoWindowCloseListener(zzaf zzaf) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzaf);
        zzc(86, zza);
    }

    public final void setOnInfoWindowLongClickListener(zzah zzah) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzah);
        zzc(84, zza);
    }

    public final void setOnMapClickListener(zzam zzam) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzam);
        zzc(28, zza);
    }

    public final void setOnMapLoadedCallback(zzao zzao) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzao);
        zzc(42, zza);
    }

    public final void setOnMapLongClickListener(zzaq zzaq) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzaq);
        zzc(29, zza);
    }

    public final void setOnMarkerClickListener(zzau zzau) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzau);
        zzc(30, zza);
    }

    public final void setOnMarkerDragListener(zzaw zzaw) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzaw);
        zzc(31, zza);
    }

    public final void setOnMyLocationButtonClickListener(zzay zzay) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzay);
        zzc(37, zza);
    }

    public final void setOnMyLocationChangeListener(zzba zzba) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzba);
        zzc(36, zza);
    }

    public final void setOnMyLocationClickListener(zzbc zzbc) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbc);
        zzc(107, zza);
    }

    public final void setOnPoiClickListener(zzbe zzbe) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbe);
        zzc(80, zza);
    }

    public final void setOnPolygonClickListener(zzbg zzbg) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbg);
        zzc(85, zza);
    }

    public final void setOnPolylineClickListener(zzbi zzbi) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbi);
        zzc(87, zza);
    }

    public final void setPadding(int i, int i2, int i3, int i4) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i);
        zza.writeInt(i2);
        zza.writeInt(i3);
        zza.writeInt(i4);
        zzc(39, zza);
    }

    public final void setTrafficEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(18, zza);
    }

    public final void setWatermarkEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, z);
        zzc(51, zza);
    }

    public final void snapshot(zzbv zzbv, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbv);
        zzc.zzg(zza, iObjectWrapper);
        zzc(38, zza);
    }

    public final void snapshotForTest(zzbv zzbv) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzbv);
        zzc(71, zza);
    }

    public final void stopAnimation() throws RemoteException {
        zzc(8, zza());
    }

    public final boolean useViewLifecycleWhenInFragment() throws RemoteException {
        Parcel zzH = zzH(59, zza());
        boolean zzh = zzc.zzh(zzH);
        zzH.recycle();
        return zzh;
    }
}
