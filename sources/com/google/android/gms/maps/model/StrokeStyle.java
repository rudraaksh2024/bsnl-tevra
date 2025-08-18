package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class StrokeStyle extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StrokeStyle> CREATOR = new zzv();
    private final float zza;
    private final int zzb;
    private final int zzc;
    private final boolean zzd;
    private final StampStyle zze;

    /* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
    public static final class Builder {
        private float zza;
        private int zzb;
        private int zzc;
        private boolean zzd;
        private StampStyle zze;

        private Builder() {
        }

        public Builder(StrokeStyle strokeStyle) {
            this.zza = strokeStyle.zza();
            Pair zzb2 = strokeStyle.zzb();
            this.zzb = ((Integer) zzb2.first).intValue();
            this.zzc = ((Integer) zzb2.second).intValue();
            this.zzd = strokeStyle.isVisible();
            this.zze = strokeStyle.getStamp();
        }

        /* synthetic */ Builder(zzu zzu) {
        }

        public StrokeStyle build() {
            return new StrokeStyle(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }

        public Builder stamp(StampStyle stampStyle) {
            this.zze = stampStyle;
            return this;
        }

        public final Builder zza(int i) {
            this.zzb = i;
            this.zzc = i;
            return this;
        }

        public final Builder zzb(int i, int i2) {
            this.zzb = i;
            this.zzc = i2;
            return this;
        }

        public final Builder zzc(boolean z) {
            this.zzd = z;
            return this;
        }

        public final Builder zzd(float f) {
            this.zza = f;
            return this;
        }
    }

    StrokeStyle(float f, int i, int i2, boolean z, StampStyle stampStyle) {
        this.zza = f;
        this.zzb = i;
        this.zzc = i2;
        this.zzd = z;
        this.zze = stampStyle;
    }

    public static Builder colorBuilder(int i) {
        Builder builder = new Builder((zzu) null);
        builder.zza(i);
        return builder;
    }

    public static Builder gradientBuilder(int i, int i2) {
        Builder builder = new Builder((zzu) null);
        builder.zzb(i, i2);
        return builder;
    }

    public static Builder transparentColorBuilder() {
        Builder builder = new Builder((zzu) null);
        builder.zza(0);
        return builder;
    }

    public StampStyle getStamp() {
        return this.zze;
    }

    public boolean isVisible() {
        return this.zzd;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, this.zza);
        SafeParcelWriter.writeInt(parcel, 3, this.zzb);
        SafeParcelWriter.writeInt(parcel, 4, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 5, isVisible());
        SafeParcelWriter.writeParcelable(parcel, 6, getStamp(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final float zza() {
        return this.zza;
    }

    public final Pair zzb() {
        return new Pair(Integer.valueOf(this.zzb), Integer.valueOf(this.zzc));
    }
}
