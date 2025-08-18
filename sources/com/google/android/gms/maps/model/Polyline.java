package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzag;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class Polyline {
    private final zzag zza;

    public Polyline(zzag zzag) {
        this.zza = (zzag) Preconditions.checkNotNull(zzag);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Polyline)) {
            return false;
        }
        try {
            return this.zza.zzD(((Polyline) obj).zza);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getColor() {
        try {
            return this.zza.zzf();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public Cap getEndCap() {
        try {
            return this.zza.zzj().zza();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getId() {
        try {
            return this.zza.zzl();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getJointType() {
        try {
            return this.zza.zzg();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public List<PatternItem> getPattern() {
        try {
            return PatternItem.zza(this.zza.zzm());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public List<LatLng> getPoints() {
        try {
            return this.zza.zzn();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public List<StyleSpan> getSpans() {
        try {
            return this.zza.zzo();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public Cap getStartCap() {
        try {
            return this.zza.zzk().zza();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.zza.zzi());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getWidth() {
        try {
            return this.zza.zzd();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getZIndex() {
        try {
            return this.zza.zze();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.zza.zzh();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isClickable() {
        try {
            return this.zza.zzE();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isGeodesic() {
        try {
            return this.zza.zzF();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        try {
            return this.zza.zzG();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setEndCap(Cap cap) {
        Preconditions.checkNotNull(cap, "endCap must not be null");
        try {
            this.zza.zzs(cap);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPoints(List<LatLng> list) {
        Preconditions.checkNotNull(list, "points must not be null");
        try {
            this.zza.zzw(list);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setStartCap(Cap cap) {
        Preconditions.checkNotNull(cap, "startCap must not be null");
        try {
            this.zza.zzy(cap);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void remove() {
        try {
            this.zza.zzp();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setClickable(boolean z) {
        try {
            this.zza.zzq(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setColor(int i) {
        try {
            this.zza.zzr(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setGeodesic(boolean z) {
        try {
            this.zza.zzt(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setJointType(int i) {
        try {
            this.zza.zzu(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPattern(List<PatternItem> list) {
        try {
            this.zza.zzv(list);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setSpans(List<StyleSpan> list) {
        try {
            this.zza.zzx(list);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTag(Object obj) {
        try {
            this.zza.zzz(ObjectWrapper.wrap(obj));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setVisible(boolean z) {
        try {
            this.zza.zzA(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setWidth(float f) {
        try {
            this.zza.zzB(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZIndex(float f) {
        try {
            this.zza.zzC(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
