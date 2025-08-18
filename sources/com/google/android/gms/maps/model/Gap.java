package com.google.android.gms.maps.model;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class Gap extends PatternItem {
    public final float length;

    public Gap(float f) {
        super(2, Float.valueOf(Math.max(f, 0.0f)));
        this.length = Math.max(f, 0.0f);
    }

    public String toString() {
        float f = this.length;
        return "[Gap: length=" + f + "]";
    }
}
