package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public enum zzpi implements zzfe {
    TYPE_UNKNOWN(0),
    TYPE_THIN(1),
    TYPE_THICK(2),
    TYPE_GMV(3);
    
    private final int zzf;

    private zzpi(int i) {
        this.zzf = i;
    }

    public final int zza() {
        return this.zzf;
    }
}
