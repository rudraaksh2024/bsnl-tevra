package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzey extends zzfc {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzey() {
        super((zzfb) null);
    }

    /* synthetic */ zzey(zzex zzex) {
        super((zzfb) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        Object obj2;
        List list = (List) zzhi.zzf(obj, j);
        if (list instanceof zzew) {
            obj2 = ((zzew) list).zze();
        } else if (!zza.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzfw) || !(list instanceof zzel)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzel zzel = (zzel) list;
                if (zzel.zzc()) {
                    zzel.zzb();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzhi.zzs(obj, j, obj2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.Object r4, java.lang.Object r5, long r6) {
        /*
            r3 = this;
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r5, r6)
            java.util.List r3 = (java.util.List) r3
            int r5 = r3.size()
            java.lang.Object r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r4, r6)
            java.util.List r0 = (java.util.List) r0
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x0039
            boolean r1 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew
            if (r1 == 0) goto L_0x0020
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev r0 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev
            r0.<init>((int) r5)
            goto L_0x0035
        L_0x0020:
            boolean r1 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfw
            if (r1 == 0) goto L_0x0030
            boolean r1 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel
            if (r1 == 0) goto L_0x0030
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel) r0
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel r5 = r0.zzd(r5)
            r0 = r5
            goto L_0x0035
        L_0x0030:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r5)
        L_0x0035:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzs(r4, r6, r0)
            goto L_0x008f
        L_0x0039:
            java.lang.Class r1 = zza
            java.lang.Class r2 = r0.getClass()
            boolean r1 = r1.isAssignableFrom(r2)
            if (r1 == 0) goto L_0x0057
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r0.size()
            int r2 = r2 + r5
            r1.<init>(r2)
            r1.addAll(r0)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzs(r4, r6, r1)
        L_0x0055:
            r0 = r1
            goto L_0x008f
        L_0x0057:
            boolean r1 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhd
            if (r1 == 0) goto L_0x0072
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev r1 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzev
            int r2 = r0.size()
            int r2 = r2 + r5
            r1.<init>((int) r2)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhd r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhd) r0
            int r5 = r1.size()
            r1.addAll(r5, r0)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzs(r4, r6, r1)
            goto L_0x0055
        L_0x0072:
            boolean r1 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfw
            if (r1 == 0) goto L_0x008f
            boolean r1 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel
            if (r1 == 0) goto L_0x008f
            r1 = r0
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel r1 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel) r1
            boolean r2 = r1.zzc()
            if (r2 != 0) goto L_0x008f
            int r0 = r0.size()
            int r0 = r0 + r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel r0 = r1.zzd(r0)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzs(r4, r6, r0)
        L_0x008f:
            int r5 = r0.size()
            int r1 = r3.size()
            if (r5 <= 0) goto L_0x009e
            if (r1 <= 0) goto L_0x009e
            r0.addAll(r3)
        L_0x009e:
            if (r5 > 0) goto L_0x00a1
            goto L_0x00a2
        L_0x00a1:
            r3 = r0
        L_0x00a2:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzs(r4, r6, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzey.zzb(java.lang.Object, java.lang.Object, long):void");
    }
}
