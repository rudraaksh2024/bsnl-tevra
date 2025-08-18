package androidx.camera.core.impl.utils;

import com.google.android.gms.location.DeviceOrientationRequest;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

final class LongRational {
    private final long mDenominator;
    private final long mNumerator;

    LongRational(long j, long j2) {
        this.mNumerator = j;
        this.mDenominator = j2;
    }

    LongRational(double d) {
        this((long) (d * 10000.0d), DeviceOrientationRequest.OUTPUT_PERIOD_MEDIUM);
    }

    /* access modifiers changed from: package-private */
    public long getNumerator() {
        return this.mNumerator;
    }

    /* access modifiers changed from: package-private */
    public long getDenominator() {
        return this.mDenominator;
    }

    /* access modifiers changed from: package-private */
    public double toDouble() {
        return ((double) this.mNumerator) / ((double) this.mDenominator);
    }

    public String toString() {
        return this.mNumerator + PackagingURIHelper.FORWARD_SLASH_STRING + this.mDenominator;
    }
}
