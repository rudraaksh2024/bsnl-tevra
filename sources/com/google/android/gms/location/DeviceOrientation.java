package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.location.zzer;
import java.util.Arrays;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public class DeviceOrientation extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DeviceOrientation> CREATOR = new zzm();
    private final float[] zza;
    private final float zzb;
    private final float zzc;
    private final long zzd;
    private final byte zze;
    private final float zzf;
    private final float zzg;

    DeviceOrientation(float[] fArr, float f, float f2, long j, byte b, float f3, float f4) {
        zzj(fArr);
        boolean z = true;
        zzer.zza(f >= 0.0f && f < 360.0f);
        zzer.zza(f2 >= 0.0f && f2 <= 180.0f);
        zzer.zza(f4 >= 0.0f && f4 <= 180.0f);
        zzer.zza(j < 0 ? false : z);
        this.zza = fArr;
        this.zzb = f;
        this.zzc = f2;
        this.zzf = f3;
        this.zzg = f4;
        this.zzd = j;
        this.zze = (byte) (((byte) (((byte) (b | UnionPtg.sid)) | 4)) | 8);
    }

    /* access modifiers changed from: private */
    public static void zzj(float[] fArr) {
        boolean z = true;
        zzer.zzb(fArr != null && fArr.length == 4, "Input attitude array should be of length 4.");
        if (Float.isNaN(fArr[0]) || Float.isNaN(fArr[1]) || Float.isNaN(fArr[2]) || Float.isNaN(fArr[3])) {
            z = false;
        }
        zzer.zzb(z, "Input attitude cannot contain NaNs.");
    }

    @Pure
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceOrientation)) {
            return false;
        }
        DeviceOrientation deviceOrientation = (DeviceOrientation) obj;
        return Float.compare(this.zzb, deviceOrientation.zzb) == 0 && Float.compare(this.zzc, deviceOrientation.zzc) == 0 && (zza() == deviceOrientation.zza() && (!zza() || Float.compare(this.zzf, deviceOrientation.zzf) == 0)) && (hasConservativeHeadingErrorDegrees() == deviceOrientation.hasConservativeHeadingErrorDegrees() && (!hasConservativeHeadingErrorDegrees() || Float.compare(getConservativeHeadingErrorDegrees(), deviceOrientation.getConservativeHeadingErrorDegrees()) == 0)) && this.zzd == deviceOrientation.zzd && Arrays.equals(this.zza, deviceOrientation.zza);
    }

    @Pure
    public float[] getAttitude() {
        return (float[]) this.zza.clone();
    }

    @Pure
    public float getConservativeHeadingErrorDegrees() {
        return this.zzg;
    }

    @Pure
    public long getElapsedRealtimeNs() {
        return this.zzd;
    }

    @Pure
    public float getHeadingDegrees() {
        return this.zzb;
    }

    @Pure
    public float getHeadingErrorDegrees() {
        return this.zzc;
    }

    @Pure
    public boolean hasConservativeHeadingErrorDegrees() {
        return (this.zze & Ptg.CLASS_ARRAY) != 0;
    }

    @Pure
    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zzb), Float.valueOf(this.zzc), Float.valueOf(this.zzg), Long.valueOf(this.zzd), this.zza, Byte.valueOf(this.zze));
    }

    @Pure
    public String toString() {
        StringBuilder sb = new StringBuilder("DeviceOrientation[attitude=");
        sb.append(Arrays.toString(this.zza));
        sb.append(", headingDegrees=");
        sb.append(this.zzb);
        sb.append(", headingErrorDegrees=");
        sb.append(this.zzc);
        if (hasConservativeHeadingErrorDegrees()) {
            sb.append(", conservativeHeadingErrorDegrees=");
            sb.append(this.zzg);
        }
        sb.append(", elapsedRealtimeNs=");
        sb.append(this.zzd);
        sb.append(']');
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloatArray(parcel, 1, getAttitude(), false);
        SafeParcelWriter.writeFloat(parcel, 4, getHeadingDegrees());
        SafeParcelWriter.writeFloat(parcel, 5, getHeadingErrorDegrees());
        SafeParcelWriter.writeLong(parcel, 6, getElapsedRealtimeNs());
        SafeParcelWriter.writeByte(parcel, 7, this.zze);
        SafeParcelWriter.writeFloat(parcel, 8, this.zzf);
        SafeParcelWriter.writeFloat(parcel, 9, getConservativeHeadingErrorDegrees());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Pure
    public final boolean zza() {
        return (this.zze & 32) != 0;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ float[] zzc() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ float zzd() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ float zze() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ long zzf() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ byte zzg() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ float zzh() {
        return this.zzf;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ float zzi() {
        return this.zzg;
    }

    /* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
    public static final class Builder {
        private final float[] zza;
        private float zzb;
        private float zzc;
        private long zzd;
        private byte zze = 0;
        private float zzf;
        private float zzg;

        public Builder(DeviceOrientation deviceOrientation) {
            DeviceOrientation.zzj(deviceOrientation.zzc());
            this.zza = Arrays.copyOf(deviceOrientation.zzc(), deviceOrientation.zzc().length);
            setHeadingDegrees(deviceOrientation.zzd());
            setHeadingErrorDegrees(deviceOrientation.zze());
            setConservativeHeadingErrorDegrees(deviceOrientation.zzi());
            setElapsedRealtimeNs(deviceOrientation.zzf());
            this.zzf = deviceOrientation.zzh();
            this.zze = deviceOrientation.zzg();
        }

        public DeviceOrientation build() {
            return new DeviceOrientation(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
        }

        public Builder clearConservativeHeadingErrorDegrees() {
            this.zzg = 180.0f;
            this.zzf = 0.0f;
            this.zze = (byte) (((byte) (this.zze & -65)) & -33);
            return this;
        }

        public Builder setAttitude(float[] fArr) {
            DeviceOrientation.zzj(fArr);
            System.arraycopy(fArr, 0, this.zza, 0, fArr.length);
            return this;
        }

        public Builder setConservativeHeadingErrorDegrees(float f) {
            float f2 = 0.0f;
            boolean z = false;
            if (f >= 0.0f && f <= 180.0f) {
                z = true;
            }
            zzer.zzb(z, "conservativeHeadingErrorDegrees should be between 0 and 180.");
            this.zzg = f;
            this.zze = (byte) (this.zze | Ptg.CLASS_ARRAY);
            Parcelable.Creator<DeviceOrientation> creator = DeviceOrientation.CREATOR;
            if (f < 180.0f) {
                f2 = (float) (2.0d / (1.0d - Math.cos(Math.toRadians((double) f))));
            }
            this.zzf = f2;
            this.zze = (byte) (this.zze | 32);
            return this;
        }

        public Builder setElapsedRealtimeNs(long j) {
            zzer.zzb(j >= 0, "elapsedRealtimeNs should be greater than or equal to 0.");
            this.zzd = j;
            return this;
        }

        public Builder setHeadingDegrees(float f) {
            boolean z = false;
            if (f >= 0.0f && f < 360.0f) {
                z = true;
            }
            zzer.zzb(z, "headingDegrees should be greater than or equal to 0 and less than 360.");
            this.zzb = f;
            return this;
        }

        public Builder setHeadingErrorDegrees(float f) {
            boolean z = false;
            if (f >= 0.0f && f <= 180.0f) {
                z = true;
            }
            zzer.zzb(z, "headingErrorDegrees should be between 0 and 180.");
            this.zzc = f;
            return this;
        }

        public Builder(float[] fArr, float f, float f2, long j) {
            DeviceOrientation.zzj(fArr);
            this.zza = Arrays.copyOf(fArr, fArr.length);
            setHeadingDegrees(f);
            setHeadingErrorDegrees(f2);
            setElapsedRealtimeNs(j);
            this.zzf = 0.0f;
            this.zzg = 180.0f;
            this.zze = 0;
        }
    }
}
