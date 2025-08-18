package com.google.android.gms.internal.mlkit_vision_barcode;

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

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzuf implements zztn {
    private Provider zza;
    private final Provider zzb;
    private final zztp zzc;

    public zzuf(Context context, zztp zztp) {
        this.zzc = zztp;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        TransportFactory newFactory = TransportRuntime.getInstance().newFactory((Destination) cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.zza = new Lazy(new zzuc(newFactory));
        }
        this.zzb = new Lazy(new zzud(newFactory));
    }

    static Event zzb(zztp zztp, zztm zztm) {
        int zza2 = zztp.zza();
        if (zztm.zza() != 0) {
            return Event.ofData(zztm.zze(zza2, false));
        }
        return Event.ofTelemetry(zztm.zze(zza2, false));
    }

    public final void zza(zztm zztm) {
        if (this.zzc.zza() == 0) {
            Provider provider = this.zza;
            if (provider != null) {
                ((Transport) provider.get()).send(zzb(this.zzc, zztm));
                return;
            }
            return;
        }
        ((Transport) this.zzb.get()).send(zzb(this.zzc, zztm));
    }
}
