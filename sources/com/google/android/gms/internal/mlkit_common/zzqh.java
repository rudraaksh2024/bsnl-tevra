package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.Destination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzqh implements zzpr {
    private Provider zza;
    private final Provider zzb;
    private final zzpt zzc;

    public zzqh(Context context, zzpt zzpt) {
        this.zzc = zzpt;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        TransportFactory newFactory = TransportRuntime.getInstance().newFactory((Destination) cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.zza = new Lazy(new zzqe(newFactory));
        }
        this.zzb = new Lazy(new zzqf(newFactory));
    }

    static Event zzb(zzpt zzpt, zzpq zzpq) {
        return Event.ofTelemetry(zzpq.zze(zzpt.zza(), false));
    }

    public final void zza(zzpq zzpq) {
        if (this.zzc.zza() == 0) {
            Provider provider = this.zza;
            if (provider != null) {
                ((Transport) provider.get()).send(zzb(this.zzc, zzpq));
                return;
            }
            return;
        }
        ((Transport) this.zzb.get()).send(zzb(this.zzc, zzpq));
    }
}
