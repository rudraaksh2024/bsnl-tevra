package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
class zzco extends zzcp {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    zzco(int i) {
    }

    private final void zzd(int i) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length < i) {
            int i2 = length + (length >> 1) + 1;
            if (i2 < i) {
                int highestOneBit = Integer.highestOneBit(i - 1);
                i2 = highestOneBit + highestOneBit;
            }
            if (i2 < 0) {
                i2 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i2);
            this.zzc = false;
        } else if (this.zzc) {
            this.zza = (Object[]) objArr.clone();
            this.zzc = false;
        }
    }

    public /* bridge */ /* synthetic */ zzcp zzb(Object obj) {
        throw null;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.util.Collection, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.mlkit_vision_barcode.zzcp zzc(java.lang.Iterable r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof java.util.Collection
            if (r0 == 0) goto L_0x0020
            int r0 = r2.zzb
            int r1 = r3.size()
            int r0 = r0 + r1
            r2.zzd(r0)
            boolean r0 = r3 instanceof com.google.android.gms.internal.mlkit_vision_barcode.zzcq
            if (r0 != 0) goto L_0x0013
            goto L_0x0020
        L_0x0013:
            com.google.android.gms.internal.mlkit_vision_barcode.zzcq r3 = (com.google.android.gms.internal.mlkit_vision_barcode.zzcq) r3
            java.lang.Object[] r0 = r2.zza
            int r1 = r2.zzb
            int r3 = r3.zza(r0, r1)
            r2.zzb = r3
            return r2
        L_0x0020:
            java.util.Iterator r3 = r3.iterator()
        L_0x0024:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0032
            java.lang.Object r0 = r3.next()
            r2.zzb(r0)
            goto L_0x0024
        L_0x0032:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzco.zzc(java.lang.Iterable):com.google.android.gms.internal.mlkit_vision_barcode.zzcp");
    }

    public final zzco zza(Object obj) {
        obj.getClass();
        zzd(this.zzb + 1);
        Object[] objArr = this.zza;
        int i = this.zzb;
        this.zzb = i + 1;
        objArr[i] = obj;
        return this;
    }
}
