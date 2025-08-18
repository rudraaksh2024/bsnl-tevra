package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.location.zzbb;
import com.google.android.gms.internal.location.zzbi;
import com.google.android.gms.internal.location.zzci;
import com.google.android.gms.internal.location.zzcr;
import com.google.android.gms.internal.location.zzct;
import com.google.android.gms.internal.location.zzcz;
import com.google.android.gms.internal.location.zzda;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class LocationServices {
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API = zzbi.zzb;
    @Deprecated
    public static final FusedLocationProviderApi FusedLocationApi = new zzbb();
    @Deprecated
    public static final GeofencingApi GeofencingApi = new zzcr();
    @Deprecated
    public static final SettingsApi SettingsApi = new zzcz();

    private LocationServices() {
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Activity activity) {
        return new zzbi(activity);
    }

    public static FusedOrientationProviderClient getFusedOrientationProviderClient(Activity activity) {
        return new zzci(activity);
    }

    public static GeofencingClient getGeofencingClient(Activity activity) {
        return new zzct(activity);
    }

    public static SettingsClient getSettingsClient(Activity activity) {
        return new zzda(activity);
    }

    public static FusedLocationProviderClient getFusedLocationProviderClient(Context context) {
        return new zzbi(context);
    }

    public static FusedOrientationProviderClient getFusedOrientationProviderClient(Context context) {
        return new zzci(context);
    }

    public static GeofencingClient getGeofencingClient(Context context) {
        return new zzct(context);
    }

    public static SettingsClient getSettingsClient(Context context) {
        return new zzda(context);
    }
}
