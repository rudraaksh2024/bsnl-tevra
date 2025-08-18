package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class zzem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzem> CREATOR = new zzen();
    private final List zza;
    private final PendingIntent zzb;
    private final String zzc;

    zzem(List list, PendingIntent pendingIntent, String str) {
        zzex zzex;
        if (list == null) {
            zzex = zzex.zzi();
        } else {
            zzex = zzex.zzj(list);
        }
        this.zza = zzex;
        this.zzb = pendingIntent;
        this.zzc = str;
    }

    public static zzem zza(List list) {
        Preconditions.checkNotNull(list, "geofence can't be null.");
        Preconditions.checkArgument(!list.isEmpty(), "Geofences must contains at least one id.");
        return new zzem(list, (PendingIntent) null, "");
    }

    public static zzem zzb(PendingIntent pendingIntent) {
        Preconditions.checkNotNull(pendingIntent, "PendingIntent can not be null.");
        return new zzem((List) null, pendingIntent, "");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        List list = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 1, list, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
