package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
abstract class zzcy extends BaseImplementation.ApiMethodImpl {
    public zzcy(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbi.zzb, googleApiClient);
    }

    public final /* synthetic */ Result createFailedResult(Status status) {
        return new LocationSettingsResult(status, (LocationSettingsStates) null);
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }
}
