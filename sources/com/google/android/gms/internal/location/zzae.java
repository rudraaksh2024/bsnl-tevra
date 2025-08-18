package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.ActivityRecognition;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
abstract class zzae extends BaseImplementation.ApiMethodImpl {
    public zzae(GoogleApiClient googleApiClient) {
        super((Api<?>) ActivityRecognition.API, googleApiClient);
    }

    public final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }
}
