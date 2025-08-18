package com.google.android.gms.maps.model;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class Dash extends PatternItem {
    public final float length;

    public Dash(float f) {
        super(0, Float.valueOf(Math.max(f, 0.0f)));
        this.length = Math.max(f, 0.0f);
    }

    public String toString() {
        float f = this.length;
        return "[Dash: length=" + f + "]";
    }
}
