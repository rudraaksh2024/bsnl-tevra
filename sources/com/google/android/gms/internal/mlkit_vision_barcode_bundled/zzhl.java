package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzhl extends zzhk {
    zzhl() {
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0047, code lost:
        if (r12[r13] <= -65) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0083, code lost:
        if (r12[r13] <= -65) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r12[r13] <= -65) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(int r11, byte[] r12, int r13, int r14) {
        /*
            r10 = this;
            r10 = -19
            r0 = -62
            r1 = -16
            r2 = 0
            r3 = -96
            r4 = -32
            r5 = -65
            r6 = -1
            if (r11 == 0) goto L_0x0087
            if (r13 < r14) goto L_0x0013
            return r11
        L_0x0013:
            byte r7 = (byte) r11
            if (r7 >= r4) goto L_0x0022
            if (r7 < r0) goto L_0x0021
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r13 > r5) goto L_0x0021
        L_0x001e:
            r13 = r11
            goto L_0x0087
        L_0x0021:
            return r6
        L_0x0022:
            if (r7 >= r1) goto L_0x004b
            int r11 = r11 >> 8
            int r11 = ~r11
            byte r11 = (byte) r11
            if (r11 != 0) goto L_0x0039
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r11 >= r14) goto L_0x0034
            r9 = r13
            r13 = r11
            r11 = r9
            goto L_0x0039
        L_0x0034:
            int r10 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhn.zzi(r7, r13)
            return r10
        L_0x0039:
            if (r11 > r5) goto L_0x004a
            if (r7 != r4) goto L_0x003f
            if (r11 < r3) goto L_0x004a
        L_0x003f:
            if (r7 != r10) goto L_0x0043
            if (r11 >= r3) goto L_0x004a
        L_0x0043:
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r13 > r5) goto L_0x004a
            goto L_0x001e
        L_0x004a:
            return r6
        L_0x004b:
            int r8 = r11 >> 8
            int r8 = ~r8
            byte r8 = (byte) r8
            if (r8 != 0) goto L_0x005f
            int r11 = r13 + 1
            byte r8 = r12[r13]
            if (r11 >= r14) goto L_0x005a
            r13 = r11
            r11 = r2
            goto L_0x0061
        L_0x005a:
            int r10 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhn.zzi(r7, r8)
            return r10
        L_0x005f:
            int r11 = r11 >> 16
        L_0x0061:
            if (r11 != 0) goto L_0x0072
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r11 >= r14) goto L_0x006d
            r9 = r13
            r13 = r11
            r11 = r9
            goto L_0x0072
        L_0x006d:
            int r10 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhn.zzj(r7, r8, r13)
            return r10
        L_0x0072:
            if (r8 > r5) goto L_0x0086
            int r7 = r7 << 28
            int r8 = r8 + 112
            int r7 = r7 + r8
            int r7 = r7 >> 30
            if (r7 != 0) goto L_0x0086
            if (r11 > r5) goto L_0x0086
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r13 > r5) goto L_0x0086
            goto L_0x001e
        L_0x0086:
            return r6
        L_0x0087:
            if (r13 >= r14) goto L_0x0090
            byte r11 = r12[r13]
            if (r11 < 0) goto L_0x0090
            int r13 = r13 + 1
            goto L_0x0087
        L_0x0090:
            if (r13 < r14) goto L_0x0094
            goto L_0x00f3
        L_0x0094:
            if (r13 < r14) goto L_0x0098
            goto L_0x00f3
        L_0x0098:
            int r11 = r13 + 1
            byte r13 = r12[r13]
            if (r13 >= 0) goto L_0x00f4
            if (r13 >= r4) goto L_0x00ae
            if (r11 < r14) goto L_0x00a4
            r2 = r13
            goto L_0x00f3
        L_0x00a4:
            if (r13 < r0) goto L_0x00ac
            int r13 = r11 + 1
            byte r11 = r12[r11]
            if (r11 <= r5) goto L_0x0094
        L_0x00ac:
            r2 = r6
            goto L_0x00f3
        L_0x00ae:
            if (r13 >= r1) goto L_0x00ce
            int r7 = r14 + -1
            if (r11 < r7) goto L_0x00b9
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhn.zzc(r12, r11, r14)
            goto L_0x00f3
        L_0x00b9:
            int r7 = r11 + 1
            byte r11 = r12[r11]
            if (r11 > r5) goto L_0x00ac
            if (r13 != r4) goto L_0x00c3
            if (r11 < r3) goto L_0x00ac
        L_0x00c3:
            if (r13 != r10) goto L_0x00c7
            if (r11 >= r3) goto L_0x00ac
        L_0x00c7:
            int r13 = r7 + 1
            byte r11 = r12[r7]
            if (r11 <= r5) goto L_0x0094
            goto L_0x00ac
        L_0x00ce:
            int r7 = r14 + -2
            if (r11 < r7) goto L_0x00d7
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhn.zzc(r12, r11, r14)
            goto L_0x00f3
        L_0x00d7:
            int r7 = r11 + 1
            byte r11 = r12[r11]
            if (r11 > r5) goto L_0x00ac
            int r13 = r13 << 28
            int r11 = r11 + 112
            int r13 = r13 + r11
            int r11 = r13 >> 30
            if (r11 != 0) goto L_0x00ac
            int r11 = r7 + 1
            byte r13 = r12[r7]
            if (r13 > r5) goto L_0x00ac
            int r13 = r11 + 1
            byte r11 = r12[r11]
            if (r11 <= r5) goto L_0x0094
            goto L_0x00ac
        L_0x00f3:
            return r2
        L_0x00f4:
            r13 = r11
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhl.zza(int, byte[], int, int):int");
    }
}
