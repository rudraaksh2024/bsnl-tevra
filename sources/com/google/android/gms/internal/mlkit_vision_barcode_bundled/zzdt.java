package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzdt {
    private static final zzdt zzb = new zzdt(true);
    final zzgu zza = new zzgk(16);
    private boolean zzc;
    private boolean zzd;

    private zzdt() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x011e, code lost:
        r1 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0120, code lost:
        return r4 + r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds r4, java.lang.Object r5) {
        /*
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r0 = r4.zzd()
            int r1 = r4.zza()
            r4.zzg()
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzb
            int r4 = r1 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho.GROUP
            if (r0 != r1) goto L_0x0026
            r1 = r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r1 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo) r1
            byte[] r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            boolean r2 = r1 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcl
            if (r2 != 0) goto L_0x0022
            int r4 = r4 + r4
            goto L_0x0026
        L_0x0022:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcl r1 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcl) r1
            r4 = 0
            throw r4
        L_0x0026:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhp r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhp.INT
            int r0 = r0.ordinal()
            r1 = 4
            r2 = 8
            switch(r0) {
                case 0: goto L_0x0119;
                case 1: goto L_0x0113;
                case 2: goto L_0x0108;
                case 3: goto L_0x00fd;
                case 4: goto L_0x00f2;
                case 5: goto L_0x00ec;
                case 6: goto L_0x00e6;
                case 7: goto L_0x00df;
                case 8: goto L_0x00c7;
                case 9: goto L_0x00c0;
                case 10: goto L_0x00aa;
                case 11: goto L_0x0093;
                case 12: goto L_0x0087;
                case 13: goto L_0x006b;
                case 14: goto L_0x0064;
                case 15: goto L_0x005d;
                case 16: goto L_0x004c;
                case 17: goto L_0x003a;
                default: goto L_0x0032;
            }
        L_0x0032:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.String r5 = "There is no way to get here, but the compiler thinks otherwise."
            r4.<init>(r5)
            throw r4
        L_0x003a:
            java.lang.Long r5 = (java.lang.Long) r5
            long r0 = r5.longValue()
            long r2 = r0 + r0
            r5 = 63
            long r0 = r0 >> r5
            long r0 = r0 ^ r2
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r0)
            goto L_0x011f
        L_0x004c:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r0 = r5 + r5
            int r5 = r5 >> 31
            r5 = r5 ^ r0
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x011f
        L_0x005d:
            java.lang.Long r5 = (java.lang.Long) r5
            r5.longValue()
            goto L_0x011e
        L_0x0064:
            java.lang.Integer r5 = (java.lang.Integer) r5
            r5.intValue()
            goto L_0x011f
        L_0x006b:
            boolean r0 = r5 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzef
            if (r0 == 0) goto L_0x007b
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzef r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzef) r5
            int r5 = r5.zza()
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r5)
            goto L_0x011f
        L_0x007b:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r5)
            goto L_0x011f
        L_0x0087:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x011f
        L_0x0093:
            boolean r0 = r5 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
            if (r0 == 0) goto L_0x00a2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r5
            int r5 = r5.zzd()
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x00d5
        L_0x00a2:
            byte[] r5 = (byte[]) r5
            int r5 = r5.length
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x00d5
        L_0x00aa:
            boolean r0 = r5 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzet
            if (r0 == 0) goto L_0x00b9
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzet r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzet) r5
            int r5 = r5.zza()
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x00d5
        L_0x00b9:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo) r5
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzv(r5)
            goto L_0x011f
        L_0x00c0:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo) r5
            int r1 = r5.zzE()
            goto L_0x011f
        L_0x00c7:
            boolean r0 = r5 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
            if (r0 == 0) goto L_0x00d8
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r5
            int r5 = r5.zzd()
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
        L_0x00d5:
            int r1 = r0 + r5
            goto L_0x011f
        L_0x00d8:
            java.lang.String r5 = (java.lang.String) r5
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzx(r5)
            goto L_0x011f
        L_0x00df:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            r5.booleanValue()
            r1 = 1
            goto L_0x011f
        L_0x00e6:
            java.lang.Integer r5 = (java.lang.Integer) r5
            r5.intValue()
            goto L_0x011f
        L_0x00ec:
            java.lang.Long r5 = (java.lang.Long) r5
            r5.longValue()
            goto L_0x011e
        L_0x00f2:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r5)
            goto L_0x011f
        L_0x00fd:
            java.lang.Long r5 = (java.lang.Long) r5
            long r0 = r5.longValue()
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r0)
            goto L_0x011f
        L_0x0108:
            java.lang.Long r5 = (java.lang.Long) r5
            long r0 = r5.longValue()
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r0)
            goto L_0x011f
        L_0x0113:
            java.lang.Float r5 = (java.lang.Float) r5
            r5.floatValue()
            goto L_0x011f
        L_0x0119:
            java.lang.Double r5 = (java.lang.Double) r5
            r5.doubleValue()
        L_0x011e:
            r1 = r2
        L_0x011f:
            int r4 = r4 + r1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt.zza(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds, java.lang.Object):int");
    }

    public static zzdt zzd() {
        return zzb;
    }

    private static Object zzl(Object obj) {
        if (obj instanceof zzft) {
            return ((zzft) obj).zzc();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private final void zzm(Map.Entry entry) {
        Object obj;
        zzds zzds = (zzds) entry.getKey();
        Object value = entry.getValue();
        if (!(value instanceof zzet)) {
            zzds.zzg();
            if (zzds.zze() == zzhp.MESSAGE) {
                Object zze = zze(zzds);
                if (zze == null) {
                    this.zza.put(zzds, zzl(value));
                    return;
                }
                if (zze instanceof zzft) {
                    obj = zzds.zzc((zzft) zze, (zzft) value);
                } else {
                    zzfn zzZ = ((zzfo) zze).zzZ();
                    zzds.zzb(zzZ, (zzfo) value);
                    obj = zzZ.zzj();
                }
                this.zza.put(zzds, obj);
                return;
            }
            this.zza.put(zzds, zzl(value));
            return;
        }
        zzet zzet = (zzet) value;
        throw null;
    }

    private static boolean zzn(Map.Entry entry) {
        zzds zzds = (zzds) entry.getKey();
        if (zzds.zze() != zzhp.MESSAGE) {
            return true;
        }
        zzds.zzg();
        Object value = entry.getValue();
        if (value instanceof zzfp) {
            return ((zzfp) value).zzac();
        }
        if (value instanceof zzet) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzo(Map.Entry entry) {
        zzds zzds = (zzds) entry.getKey();
        Object value = entry.getValue();
        if (zzds.zze() != zzhp.MESSAGE) {
            return zza(zzds, value);
        }
        zzds.zzg();
        zzds.zzf();
        if (value instanceof zzet) {
            int zzy = zzdj.zzy(((zzds) entry.getKey()).zza());
            int zza2 = ((zzet) value).zza();
            int zzy2 = zzdj.zzy(zza2) + zza2;
            int zzy3 = zzdj.zzy(24);
            int zzy4 = zzdj.zzy(16);
            int zzy5 = zzdj.zzy(8);
            return zzy5 + zzy5 + zzy4 + zzy + zzy3 + zzy2;
        }
        int zzy6 = zzdj.zzy(((zzds) entry.getKey()).zza());
        int zzy7 = zzdj.zzy(24) + zzdj.zzv((zzfo) value);
        int zzy8 = zzdj.zzy(16);
        int zzy9 = zzdj.zzy(8);
        return zzy9 + zzy9 + zzy8 + zzy6 + zzy7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdt)) {
            return false;
        }
        return this.zza.equals(((zzdt) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzb() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zzb(); i2++) {
            i += zzo(this.zza.zzg(i2));
        }
        for (Map.Entry zzo : this.zza.zzc()) {
            i += zzo(zzo);
        }
        return i;
    }

    /* renamed from: zzc */
    public final zzdt clone() {
        zzdt zzdt = new zzdt();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzdt.zzi((zzds) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzdt.zzi((zzds) entry.getKey(), entry.getValue());
        }
        zzdt.zzd = this.zzd;
        return zzdt;
    }

    public final Object zze(zzds zzds) {
        Object obj = this.zza.get(zzds);
        if (!(obj instanceof zzet)) {
            return obj;
        }
        zzet zzet = (zzet) obj;
        throw null;
    }

    public final Iterator zzf() {
        if (this.zzd) {
            return new zzes(this.zza.entrySet().iterator());
        }
        return this.zza.entrySet().iterator();
    }

    public final void zzg() {
        if (!this.zzc) {
            for (int i = 0; i < this.zza.zzb(); i++) {
                Map.Entry zzg = this.zza.zzg(i);
                if (zzg.getValue() instanceof zzed) {
                    ((zzed) zzg.getValue()).zzS();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzh(zzdt zzdt) {
        for (int i = 0; i < zzdt.zza.zzb(); i++) {
            zzm(zzdt.zza.zzg(i));
        }
        for (Map.Entry zzm : zzdt.zza.zzc()) {
            zzm(zzm);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        if ((r6 instanceof byte[]) == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        if (r0 != false) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        if ((r6 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzet) == false) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004f, code lost:
        r4.zzd = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        r4.zza.zze(r5, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0085, code lost:
        throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new java.lang.Object[]{java.lang.Integer.valueOf(r5.zza()), r5.zzd().zza(), r6.getClass().getName()}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0023, code lost:
        if ((r6 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzet) == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002c, code lost:
        if ((r6 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzef) == false) goto L_0x0057;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzi(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds r5, java.lang.Object r6) {
        /*
            r4 = this;
            r5.zzg()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r0 = r5.zzd()
            byte[] r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            r6.getClass()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho.DOUBLE
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhp r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhp.INT
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhp r0 = r0.zza()
            int r0 = r0.ordinal()
            r1 = 1
            switch(r0) {
                case 0: goto L_0x0047;
                case 1: goto L_0x0044;
                case 2: goto L_0x0041;
                case 3: goto L_0x003e;
                case 4: goto L_0x003b;
                case 5: goto L_0x0038;
                case 6: goto L_0x002f;
                case 7: goto L_0x0026;
                case 8: goto L_0x001d;
                default: goto L_0x001c;
            }
        L_0x001c:
            goto L_0x0057
        L_0x001d:
            boolean r0 = r6 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo
            if (r0 != 0) goto L_0x004b
            boolean r0 = r6 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzet
            if (r0 == 0) goto L_0x0057
            goto L_0x004b
        L_0x0026:
            boolean r0 = r6 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x004b
            boolean r0 = r6 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzef
            if (r0 == 0) goto L_0x0057
            goto L_0x004b
        L_0x002f:
            boolean r0 = r6 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
            if (r0 != 0) goto L_0x004b
            boolean r0 = r6 instanceof byte[]
            if (r0 == 0) goto L_0x0057
            goto L_0x004b
        L_0x0038:
            boolean r0 = r6 instanceof java.lang.String
            goto L_0x0049
        L_0x003b:
            boolean r0 = r6 instanceof java.lang.Boolean
            goto L_0x0049
        L_0x003e:
            boolean r0 = r6 instanceof java.lang.Double
            goto L_0x0049
        L_0x0041:
            boolean r0 = r6 instanceof java.lang.Float
            goto L_0x0049
        L_0x0044:
            boolean r0 = r6 instanceof java.lang.Long
            goto L_0x0049
        L_0x0047:
            boolean r0 = r6 instanceof java.lang.Integer
        L_0x0049:
            if (r0 == 0) goto L_0x0057
        L_0x004b:
            boolean r0 = r6 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzet
            if (r0 == 0) goto L_0x0051
            r4.zzd = r1
        L_0x0051:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgu r4 = r4.zza
            r4.put(r5, r6)
            return
        L_0x0057:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r0 = 3
            java.lang.Object[] r0 = new java.lang.Object[r0]
            int r2 = r5.zza()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3 = 0
            r0[r3] = r2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r5 = r5.zzd()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhp r5 = r5.zza()
            r0[r1] = r5
            java.lang.Class r5 = r6.getClass()
            java.lang.String r5 = r5.getName()
            r6 = 2
            r0[r6] = r5
            java.lang.String r5 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r5 = java.lang.String.format(r5, r0)
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt.zzi(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds, java.lang.Object):void");
    }

    public final boolean zzj() {
        return this.zzc;
    }

    public final boolean zzk() {
        for (int i = 0; i < this.zza.zzb(); i++) {
            if (!zzn(this.zza.zzg(i))) {
                return false;
            }
        }
        for (Map.Entry zzn : this.zza.zzc()) {
            if (!zzn(zzn)) {
                return false;
            }
        }
        return true;
    }

    private zzdt(boolean z) {
        zzg();
        zzg();
    }
}
