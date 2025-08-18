package com.google.android.gms.maps.model;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class CustomCap extends Cap {
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    public CustomCap(BitmapDescriptor bitmapDescriptor2) {
        this(bitmapDescriptor2, 10.0f);
    }

    public String toString() {
        String valueOf = String.valueOf(this.bitmapDescriptor);
        float f = this.refWidth;
        return "[CustomCap: bitmapDescriptor=" + valueOf + " refWidth=" + f + "]";
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CustomCap(com.google.android.gms.maps.model.BitmapDescriptor r3, float r4) {
        /*
            r2 = this;
            java.lang.String r0 = "bitmapDescriptor must not be null"
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r3, r0)
            com.google.android.gms.maps.model.BitmapDescriptor r0 = (com.google.android.gms.maps.model.BitmapDescriptor) r0
            r1 = 0
            int r1 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x0015
            r2.<init>(r0, r4)
            r2.bitmapDescriptor = r3
            r2.refWidth = r4
            return
        L_0x0015:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "refWidth must be positive"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.CustomCap.<init>(com.google.android.gms.maps.model.BitmapDescriptor, float):void");
    }
}
