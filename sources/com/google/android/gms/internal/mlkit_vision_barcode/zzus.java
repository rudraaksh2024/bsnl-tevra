package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions;
import com.google.mlkit.vision.barcode.internal.BarcodeScannerImpl;
import com.google.mlkit.vision.barcode.internal.zzf;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zzus {
    /* access modifiers changed from: private */
    public static final GmsLogger zzf = new GmsLogger("AutoZoom");
    final zzuu zza;
    final zzbz zzb;
    @Nullable
    ScheduledFuture zzc;
    @Nullable
    String zzd;
    int zze;
    /* access modifiers changed from: private */
    public final AtomicBoolean zzg;
    private final Object zzh = new Object();
    private final ScheduledExecutorService zzi;
    private final zzbf zzj;
    private final zztx zzk;
    private final String zzl;
    private Executor zzm;
    private float zzn;
    private float zzo;
    private long zzp;
    private long zzq;
    private boolean zzr;
    private zzf zzs;

    private zzus(Context context, zzuu zzuu, String str) {
        zzg.zza();
        ScheduledExecutorService unconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(2));
        zzbf zza2 = zzar.zza();
        zztx zztx = new zztx(context, new SharedPrefManager(context), new zztq(context, zztp.zzd("scanner-auto-zoom").zzd()), "scanner-auto-zoom");
        this.zza = zzuu;
        this.zzg = new AtomicBoolean(false);
        this.zzb = zzbz.zzz();
        this.zzi = unconfigurableScheduledExecutorService;
        this.zzj = zza2;
        this.zzk = zztx;
        this.zzl = str;
        this.zze = 1;
        this.zzn = 1.0f;
        this.zzo = -1.0f;
        this.zzp = zza2.zza();
    }

    public static zzus zzd(Context context, String str) {
        return new zzus(context, zzuu.zzb, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void zzf(com.google.android.gms.internal.mlkit_vision_barcode.zzus r7) {
        /*
            java.lang.Object r0 = r7.zzh
            monitor-enter(r0)
            int r1 = r7.zze     // Catch:{ all -> 0x0044 }
            r2 = 2
            if (r1 != r2) goto L_0x0042
            java.util.concurrent.atomic.AtomicBoolean r1 = r7.zzg     // Catch:{ all -> 0x0044 }
            boolean r1 = r1.get()     // Catch:{ all -> 0x0044 }
            if (r1 != 0) goto L_0x0042
            java.util.concurrent.ScheduledFuture r1 = r7.zzc     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0042
            boolean r1 = r1.isCancelled()     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x001b
            goto L_0x0042
        L_0x001b:
            float r1 = r7.zzn     // Catch:{ all -> 0x0044 }
            r2 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0040
            long r3 = r7.zza()     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r1 = r7.zza     // Catch:{ all -> 0x0044 }
            long r5 = r1.zzi()     // Catch:{ all -> 0x0044 }
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x0040
            com.google.android.gms.common.internal.GmsLogger r1 = zzf     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = "AutoZoom"
            java.lang.String r4 = "Reset zoom = 1"
            r1.i(r3, r4)     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzpk r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzpk.SCANNER_AUTO_ZOOM_AUTO_RESET     // Catch:{ all -> 0x0044 }
            r3 = 0
            r7.zzl(r2, r1, r3)     // Catch:{ all -> 0x0044 }
        L_0x0040:
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            return
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            return
        L_0x0044:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzus.zzf(com.google.android.gms.internal.mlkit_vision_barcode.zzus):void");
    }

    static /* bridge */ /* synthetic */ void zzg(zzus zzus, float f) {
        synchronized (zzus.zzh) {
            zzus.zzn = f;
            zzus.zzr(false);
        }
    }

    private final float zzp(float f) {
        int i = (f > 1.0f ? 1 : (f == 1.0f ? 0 : -1));
        float f2 = this.zzo;
        if (i < 0) {
            f = 1.0f;
        }
        return (f2 <= 0.0f || f <= f2) ? f : f2;
    }

    /* access modifiers changed from: private */
    public final void zzq(zzpk zzpk, float f, float f2, @Nullable zzuv zzuv) {
        long convert;
        if (this.zzd != null) {
            zzsb zzsb = new zzsb();
            zzsb.zza(this.zzl);
            String str = this.zzd;
            str.getClass();
            zzsb.zze(str);
            zzsb.zzf(Float.valueOf(f));
            zzsb.zzc(Float.valueOf(f2));
            synchronized (this.zzh) {
                convert = TimeUnit.MILLISECONDS.convert(this.zzj.zza() - this.zzq, TimeUnit.NANOSECONDS);
            }
            zzsb.zzb(Long.valueOf(convert));
            if (zzuv != null) {
                zzsc zzsc = new zzsc();
                zzsc.zzc(Float.valueOf(zzuv.zzc()));
                zzsc.zze(Float.valueOf(zzuv.zze()));
                zzsc.zzb(Float.valueOf(zzuv.zzb()));
                zzsc.zzd(Float.valueOf(zzuv.zzd()));
                zzsc.zza(Float.valueOf(0.0f));
                zzsb.zzd(zzsc.zzf());
            }
            zztx zztx = this.zzk;
            zzpl zzpl = new zzpl();
            zzpl.zzi(zzsb.zzh());
            zztx.zzd(zzua.zzf(zzpl), zzpk);
        }
    }

    private final void zzr(boolean z) {
        ScheduledFuture scheduledFuture;
        synchronized (this.zzh) {
            this.zzb.zzs();
            this.zzp = this.zzj.zza();
            if (z && (scheduledFuture = this.zzc) != null) {
                scheduledFuture.cancel(false);
                this.zzc = null;
            }
        }
    }

    public final long zza() {
        long convert;
        synchronized (this.zzh) {
            convert = TimeUnit.MILLISECONDS.convert(this.zzj.zza() - this.zzp, TimeUnit.NANOSECONDS);
        }
        return convert;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzev zzc(float f) throws Exception {
        zzf zzf2 = this.zzs;
        float zzp2 = zzp(f);
        ZoomSuggestionOptions zoomSuggestionOptions = zzf2.zza;
        int i = BarcodeScannerImpl.zzc;
        if (true != zoomSuggestionOptions.zzb().setZoom(zzp2)) {
            zzp2 = 0.0f;
        }
        return zzem.zza(Float.valueOf(zzp2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0268, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00df A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzi(int r13, com.google.android.gms.internal.mlkit_vision_barcode.zzuv r14) {
        /*
            r12 = this;
            java.lang.Object r0 = r12.zzh
            monitor-enter(r0)
            int r1 = r12.zze     // Catch:{ all -> 0x0269 }
            r2 = 2
            if (r1 == r2) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x0269 }
            return
        L_0x000a:
            boolean r1 = r14.zzh()     // Catch:{ all -> 0x0269 }
            if (r1 == 0) goto L_0x0267
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r1 = r12.zza     // Catch:{ all -> 0x0269 }
            boolean r1 = r1.zzl()     // Catch:{ all -> 0x0269 }
            r3 = 0
            if (r1 == 0) goto L_0x0023
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r1 = r12.zza     // Catch:{ all -> 0x0269 }
            float r1 = r1.zzb()     // Catch:{ all -> 0x0269 }
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x0267
        L_0x0023:
            boolean r1 = r12.zzr     // Catch:{ all -> 0x0269 }
            r4 = 1
            if (r1 != 0) goto L_0x0031
            com.google.android.gms.internal.mlkit_vision_barcode.zzpk r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzpk.SCANNER_AUTO_ZOOM_FIRST_ATTEMPT     // Catch:{ all -> 0x0269 }
            float r5 = r12.zzn     // Catch:{ all -> 0x0269 }
            r12.zzq(r1, r5, r5, r14)     // Catch:{ all -> 0x0269 }
            r12.zzr = r4     // Catch:{ all -> 0x0269 }
        L_0x0031:
            com.google.android.gms.common.internal.GmsLogger r1 = zzf     // Catch:{ all -> 0x0269 }
            java.lang.String r5 = "AutoZoom"
            java.util.Locale r6 = java.util.Locale.getDefault()     // Catch:{ all -> 0x0269 }
            java.lang.String r7 = "Process PredictedArea: [%.2f, %.2f, %.2f, %.2f, %.2f], frameIndex = %d"
            r8 = 6
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x0269 }
            float r9 = r14.zzc()     // Catch:{ all -> 0x0269 }
            java.lang.Float r9 = java.lang.Float.valueOf(r9)     // Catch:{ all -> 0x0269 }
            r10 = 0
            r8[r10] = r9     // Catch:{ all -> 0x0269 }
            float r9 = r14.zze()     // Catch:{ all -> 0x0269 }
            java.lang.Float r9 = java.lang.Float.valueOf(r9)     // Catch:{ all -> 0x0269 }
            r8[r4] = r9     // Catch:{ all -> 0x0269 }
            float r4 = r14.zzb()     // Catch:{ all -> 0x0269 }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ all -> 0x0269 }
            r8[r2] = r4     // Catch:{ all -> 0x0269 }
            float r2 = r14.zzd()     // Catch:{ all -> 0x0269 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ all -> 0x0269 }
            r4 = 3
            r8[r4] = r2     // Catch:{ all -> 0x0269 }
            java.lang.Float r2 = java.lang.Float.valueOf(r3)     // Catch:{ all -> 0x0269 }
            r4 = 4
            r8[r4] = r2     // Catch:{ all -> 0x0269 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x0269 }
            r4 = 5
            r8[r4] = r2     // Catch:{ all -> 0x0269 }
            java.lang.String r4 = java.lang.String.format(r6, r7, r8)     // Catch:{ all -> 0x0269 }
            r1.i(r5, r4)     // Catch:{ all -> 0x0269 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbz r1 = r12.zzb     // Catch:{ all -> 0x0269 }
            r1.zzt(r2, r14)     // Catch:{ all -> 0x0269 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbz r1 = r12.zzb     // Catch:{ all -> 0x0269 }
            java.util.Set r1 = r1.zzw()     // Catch:{ all -> 0x0269 }
            int r2 = r1.size()     // Catch:{ all -> 0x0269 }
            int r2 = r2 + -1
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r4 = r12.zza     // Catch:{ all -> 0x0269 }
            int r4 = r4.zzh()     // Catch:{ all -> 0x0269 }
            if (r2 <= r4) goto L_0x00d0
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0269 }
            r2 = r13
        L_0x009b:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x0269 }
            if (r4 == 0) goto L_0x00af
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x0269 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ all -> 0x0269 }
            int r4 = r4.intValue()     // Catch:{ all -> 0x0269 }
            if (r2 <= r4) goto L_0x009b
            r2 = r4
            goto L_0x009b
        L_0x00af:
            com.google.android.gms.common.internal.GmsLogger r1 = zzf     // Catch:{ all -> 0x0269 }
            java.lang.String r4 = "AutoZoom"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0269 }
            r5.<init>()     // Catch:{ all -> 0x0269 }
            java.lang.String r6 = "Removing recent frameIndex = "
            r5.append(r6)     // Catch:{ all -> 0x0269 }
            r5.append(r2)     // Catch:{ all -> 0x0269 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0269 }
            r1.i(r4, r5)     // Catch:{ all -> 0x0269 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbz r1 = r12.zzb     // Catch:{ all -> 0x0269 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0269 }
            r1.zzf(r2)     // Catch:{ all -> 0x0269 }
        L_0x00d0:
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ all -> 0x0269 }
            r1.<init>()     // Catch:{ all -> 0x0269 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbz r2 = r12.zzb     // Catch:{ all -> 0x0269 }
            java.util.Collection r2 = r2.zzu()     // Catch:{ all -> 0x0269 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0269 }
        L_0x00df:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x0269 }
            if (r4 == 0) goto L_0x0168
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x0269 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x0269 }
            java.lang.Object r5 = r4.getKey()     // Catch:{ all -> 0x0269 }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ all -> 0x0269 }
            int r5 = r5.intValue()     // Catch:{ all -> 0x0269 }
            if (r5 == r13) goto L_0x00df
            java.lang.Object r5 = r4.getValue()     // Catch:{ all -> 0x0269 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzuv r5 = (com.google.android.gms.internal.mlkit_vision_barcode.zzuv) r5     // Catch:{ all -> 0x0269 }
            boolean r6 = r5.zzh()     // Catch:{ all -> 0x0269 }
            if (r6 == 0) goto L_0x0152
            boolean r6 = r14.zzh()     // Catch:{ all -> 0x0269 }
            if (r6 != 0) goto L_0x010a
            goto L_0x0152
        L_0x010a:
            float r6 = r5.zzc()     // Catch:{ all -> 0x0269 }
            float r7 = r14.zzc()     // Catch:{ all -> 0x0269 }
            float r6 = java.lang.Math.max(r6, r7)     // Catch:{ all -> 0x0269 }
            float r7 = r5.zze()     // Catch:{ all -> 0x0269 }
            float r8 = r14.zze()     // Catch:{ all -> 0x0269 }
            float r7 = java.lang.Math.max(r7, r8)     // Catch:{ all -> 0x0269 }
            float r8 = r5.zzb()     // Catch:{ all -> 0x0269 }
            float r9 = r14.zzb()     // Catch:{ all -> 0x0269 }
            float r8 = java.lang.Math.min(r8, r9)     // Catch:{ all -> 0x0269 }
            float r9 = r5.zzd()     // Catch:{ all -> 0x0269 }
            float r11 = r14.zzd()     // Catch:{ all -> 0x0269 }
            float r9 = java.lang.Math.min(r9, r11)     // Catch:{ all -> 0x0269 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzuv r6 = com.google.android.gms.internal.mlkit_vision_barcode.zzuv.zzg(r6, r7, r8, r9, r3)     // Catch:{ all -> 0x0269 }
            float r7 = r6.zzf()     // Catch:{ all -> 0x0269 }
            float r5 = r5.zzf()     // Catch:{ all -> 0x0269 }
            float r8 = r14.zzf()     // Catch:{ all -> 0x0269 }
            float r5 = r5 + r8
            float r6 = r6.zzf()     // Catch:{ all -> 0x0269 }
            float r5 = r5 - r6
            float r7 = r7 / r5
            goto L_0x0153
        L_0x0152:
            r7 = r3
        L_0x0153:
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r5 = r12.zza     // Catch:{ all -> 0x0269 }
            float r5 = r5.zzd()     // Catch:{ all -> 0x0269 }
            int r5 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r5 < 0) goto L_0x00df
            java.lang.Object r4 = r4.getKey()     // Catch:{ all -> 0x0269 }
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ all -> 0x0269 }
            r1.add(r4)     // Catch:{ all -> 0x0269 }
            goto L_0x00df
        L_0x0168:
            int r13 = r1.size()     // Catch:{ all -> 0x0269 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r1 = r12.zza     // Catch:{ all -> 0x0269 }
            int r1 = r1.zzg()     // Catch:{ all -> 0x0269 }
            if (r13 >= r1) goto L_0x0186
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r13 = r12.zza     // Catch:{ all -> 0x0269 }
            boolean r13 = r13.zzl()     // Catch:{ all -> 0x0269 }
            if (r13 == 0) goto L_0x0262
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r13 = r12.zza     // Catch:{ all -> 0x0269 }
            float r13 = r13.zza()     // Catch:{ all -> 0x0269 }
            int r13 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r13 > 0) goto L_0x0262
        L_0x0186:
            java.lang.Object r13 = r12.zzh     // Catch:{ all -> 0x0269 }
            monitor-enter(r13)     // Catch:{ all -> 0x0269 }
            long r1 = r12.zza()     // Catch:{ all -> 0x0264 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r3 = r12.zza     // Catch:{ all -> 0x0264 }
            long r3 = r3.zzj()     // Catch:{ all -> 0x0264 }
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x019a
            monitor-exit(r13)     // Catch:{ all -> 0x0264 }
            goto L_0x0262
        L_0x019a:
            float r1 = r14.zzc()     // Catch:{ all -> 0x0264 }
            java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch:{ all -> 0x0264 }
            float r2 = r14.zze()     // Catch:{ all -> 0x0264 }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ all -> 0x0264 }
            float r3 = r14.zzb()     // Catch:{ all -> 0x0264 }
            java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{ all -> 0x0264 }
            float r4 = r14.zzd()     // Catch:{ all -> 0x0264 }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ all -> 0x0264 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzcv r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzcv.zzi(r1, r2, r3, r4)     // Catch:{ all -> 0x0264 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzdy r1 = r1.listIterator(r10)     // Catch:{ all -> 0x0264 }
            r2 = 1315859240(0x4e6e6b28, float:1.0E9)
        L_0x01c5:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0264 }
            if (r3 == 0) goto L_0x01f4
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0264 }
            java.lang.Float r3 = (java.lang.Float) r3     // Catch:{ all -> 0x0264 }
            float r3 = r3.floatValue()     // Catch:{ all -> 0x0264 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r4 = r12.zza     // Catch:{ all -> 0x0264 }
            float r4 = r4.zzc()     // Catch:{ all -> 0x0264 }
            r5 = 1073741824(0x40000000, float:2.0)
            float r4 = r4 / r5
            r5 = -1090519040(0xffffffffbf000000, float:-0.5)
            float r3 = r3 + r5
            float r3 = java.lang.Math.abs(r3)     // Catch:{ all -> 0x0264 }
            r5 = 981668463(0x3a83126f, float:0.001)
            float r3 = java.lang.Math.max(r3, r5)     // Catch:{ all -> 0x0264 }
            float r3 = r4 / r3
            int r4 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r4 <= 0) goto L_0x01c5
            r2 = r3
            goto L_0x01c5
        L_0x01f4:
            float r1 = r12.zzn     // Catch:{ all -> 0x0264 }
            float r1 = r1 * r2
            float r1 = r12.zzp(r1)     // Catch:{ all -> 0x0264 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r2 = r12.zza     // Catch:{ all -> 0x0264 }
            boolean r2 = r2.zzk()     // Catch:{ all -> 0x0264 }
            if (r2 == 0) goto L_0x0244
            float r2 = r12.zzn     // Catch:{ all -> 0x0264 }
            float r3 = r1 - r2
            float r3 = r3 / r2
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r2 = r12.zza     // Catch:{ all -> 0x0264 }
            float r2 = r2.zze()     // Catch:{ all -> 0x0264 }
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x0244
            com.google.android.gms.internal.mlkit_vision_barcode.zzuu r2 = r12.zza     // Catch:{ all -> 0x0264 }
            float r2 = r2.zzf()     // Catch:{ all -> 0x0264 }
            float r2 = -r2
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x0244
            com.google.android.gms.common.internal.GmsLogger r14 = zzf     // Catch:{ all -> 0x0264 }
            java.lang.String r2 = "AutoZoom"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0264 }
            r3.<init>()     // Catch:{ all -> 0x0264 }
            java.lang.String r4 = "Auto zoom to "
            r3.append(r4)     // Catch:{ all -> 0x0264 }
            r3.append(r1)     // Catch:{ all -> 0x0264 }
            java.lang.String r1 = " is filtered by threshold"
            r3.append(r1)     // Catch:{ all -> 0x0264 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0264 }
            r14.i(r2, r1)     // Catch:{ all -> 0x0264 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbf r14 = r12.zzj     // Catch:{ all -> 0x0264 }
            long r1 = r14.zza()     // Catch:{ all -> 0x0264 }
            r12.zzp = r1     // Catch:{ all -> 0x0264 }
            monitor-exit(r13)     // Catch:{ all -> 0x0264 }
            goto L_0x0262
        L_0x0244:
            com.google.android.gms.common.internal.GmsLogger r2 = zzf     // Catch:{ all -> 0x0264 }
            java.lang.String r3 = "AutoZoom"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0264 }
            r4.<init>()     // Catch:{ all -> 0x0264 }
            java.lang.String r5 = "Going to set zoom = "
            r4.append(r5)     // Catch:{ all -> 0x0264 }
            r4.append(r1)     // Catch:{ all -> 0x0264 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0264 }
            r2.i(r3, r4)     // Catch:{ all -> 0x0264 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzpk r2 = com.google.android.gms.internal.mlkit_vision_barcode.zzpk.SCANNER_AUTO_ZOOM_AUTO_ZOOM     // Catch:{ all -> 0x0264 }
            r12.zzl(r1, r2, r14)     // Catch:{ all -> 0x0264 }
            monitor-exit(r13)     // Catch:{ all -> 0x0264 }
        L_0x0262:
            monitor-exit(r0)     // Catch:{ all -> 0x0269 }
            return
        L_0x0264:
            r12 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x0264 }
            throw r12     // Catch:{ all -> 0x0269 }
        L_0x0267:
            monitor-exit(r0)     // Catch:{ all -> 0x0269 }
            return
        L_0x0269:
            r12 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0269 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzus.zzi(int, com.google.android.gms.internal.mlkit_vision_barcode.zzuv):void");
    }

    public final void zzj() {
        synchronized (this.zzh) {
            if (this.zze != 4) {
                zzn(false);
                this.zzi.shutdown();
                this.zze = 4;
            }
        }
    }

    public final void zzk(float f) {
        synchronized (this.zzh) {
            zzbc.zzc(f >= 1.0f);
            this.zzo = f;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzl(float r10, com.google.android.gms.internal.mlkit_vision_barcode.zzpk r11, @javax.annotation.Nullable com.google.android.gms.internal.mlkit_vision_barcode.zzuv r12) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.zzh
            monitor-enter(r0)
            java.util.concurrent.Executor r1 = r9.zzm     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003d
            com.google.mlkit.vision.barcode.internal.zzf r1 = r9.zzs     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003d
            int r1 = r9.zze     // Catch:{ all -> 0x003f }
            r2 = 2
            if (r1 == r2) goto L_0x0011
            goto L_0x003d
        L_0x0011:
            java.util.concurrent.atomic.AtomicBoolean r1 = r9.zzg     // Catch:{ all -> 0x003f }
            r2 = 0
            r3 = 1
            boolean r1 = r1.compareAndSet(r2, r3)     // Catch:{ all -> 0x003f }
            if (r1 != 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x001d:
            float r4 = r9.zzn     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.mlkit_vision_barcode.zzup r1 = new com.google.android.gms.internal.mlkit_vision_barcode.zzup     // Catch:{ all -> 0x003f }
            r1.<init>(r9, r10)     // Catch:{ all -> 0x003f }
            java.util.concurrent.Executor r2 = r9.zzm     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.mlkit_vision_barcode.zzev r7 = com.google.android.gms.internal.mlkit_vision_barcode.zzem.zzc(r1, r2)     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.mlkit_vision_barcode.zzur r8 = new com.google.android.gms.internal.mlkit_vision_barcode.zzur     // Catch:{ all -> 0x003f }
            r1 = r8
            r2 = r9
            r3 = r11
            r5 = r12
            r6 = r10
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x003f }
            java.util.concurrent.Executor r9 = com.google.android.gms.internal.mlkit_vision_barcode.zzew.zza()     // Catch:{ all -> 0x003f }
            com.google.android.gms.internal.mlkit_vision_barcode.zzem.zzb(r7, r8, r9)     // Catch:{ all -> 0x003f }
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzus.zzl(float, com.google.android.gms.internal.mlkit_vision_barcode.zzpk, com.google.android.gms.internal.mlkit_vision_barcode.zzuv):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzm() {
        /*
            r10 = this;
            java.lang.Object r0 = r10.zzh
            monitor-enter(r0)
            int r1 = r10.zze     // Catch:{ all -> 0x0051 }
            r2 = 2
            if (r1 == r2) goto L_0x004f
            r3 = 4
            if (r1 != r3) goto L_0x000c
            goto L_0x004f
        L_0x000c:
            r1 = 1
            r10.zzr(r1)     // Catch:{ all -> 0x0051 }
            java.util.concurrent.ScheduledExecutorService r3 = r10.zzi     // Catch:{ all -> 0x0051 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzuq r4 = new com.google.android.gms.internal.mlkit_vision_barcode.zzuq     // Catch:{ all -> 0x0051 }
            r4.<init>(r10)     // Catch:{ all -> 0x0051 }
            r7 = 500(0x1f4, double:2.47E-321)
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0051 }
            r5 = r7
            java.util.concurrent.ScheduledFuture r3 = r3.scheduleWithFixedDelay(r4, r5, r7, r9)     // Catch:{ all -> 0x0051 }
            r10.zzc = r3     // Catch:{ all -> 0x0051 }
            int r3 = r10.zze     // Catch:{ all -> 0x0051 }
            r4 = 0
            if (r3 != r1) goto L_0x0044
            java.util.UUID r1 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x0051 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0051 }
            r10.zzd = r1     // Catch:{ all -> 0x0051 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbf r1 = r10.zzj     // Catch:{ all -> 0x0051 }
            long r5 = r1.zza()     // Catch:{ all -> 0x0051 }
            r10.zzq = r5     // Catch:{ all -> 0x0051 }
            r1 = 0
            r10.zzr = r1     // Catch:{ all -> 0x0051 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzpk r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzpk.SCANNER_AUTO_ZOOM_START     // Catch:{ all -> 0x0051 }
            float r3 = r10.zzn     // Catch:{ all -> 0x0051 }
            r10.zzq(r1, r3, r3, r4)     // Catch:{ all -> 0x0051 }
            goto L_0x004b
        L_0x0044:
            com.google.android.gms.internal.mlkit_vision_barcode.zzpk r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzpk.SCANNER_AUTO_ZOOM_RESUME     // Catch:{ all -> 0x0051 }
            float r3 = r10.zzn     // Catch:{ all -> 0x0051 }
            r10.zzq(r1, r3, r3, r4)     // Catch:{ all -> 0x0051 }
        L_0x004b:
            r10.zze = r2     // Catch:{ all -> 0x0051 }
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            return
        L_0x004f:
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            return
        L_0x0051:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzus.zzm():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzn(boolean r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzh
            monitor-enter(r0)
            int r1 = r4.zze     // Catch:{ all -> 0x0037 }
            r2 = 1
            if (r1 == r2) goto L_0x0035
            r3 = 4
            if (r1 != r3) goto L_0x000c
            goto L_0x0035
        L_0x000c:
            r4.zzr(r2)     // Catch:{ all -> 0x0037 }
            r1 = 0
            if (r5 == 0) goto L_0x0025
            boolean r5 = r4.zzr     // Catch:{ all -> 0x0037 }
            if (r5 != 0) goto L_0x001d
            com.google.android.gms.internal.mlkit_vision_barcode.zzpk r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzpk.SCANNER_AUTO_ZOOM_FIRST_ATTEMPT     // Catch:{ all -> 0x0037 }
            float r3 = r4.zzn     // Catch:{ all -> 0x0037 }
            r4.zzq(r5, r3, r3, r1)     // Catch:{ all -> 0x0037 }
        L_0x001d:
            com.google.android.gms.internal.mlkit_vision_barcode.zzpk r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzpk.SCANNER_AUTO_ZOOM_SCAN_SUCCESS     // Catch:{ all -> 0x0037 }
            float r3 = r4.zzn     // Catch:{ all -> 0x0037 }
            r4.zzq(r5, r3, r3, r1)     // Catch:{ all -> 0x0037 }
            goto L_0x002c
        L_0x0025:
            com.google.android.gms.internal.mlkit_vision_barcode.zzpk r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzpk.SCANNER_AUTO_ZOOM_SCAN_FAILED     // Catch:{ all -> 0x0037 }
            float r3 = r4.zzn     // Catch:{ all -> 0x0037 }
            r4.zzq(r5, r3, r3, r1)     // Catch:{ all -> 0x0037 }
        L_0x002c:
            r5 = 0
            r4.zzr = r5     // Catch:{ all -> 0x0037 }
            r4.zze = r2     // Catch:{ all -> 0x0037 }
            r4.zzd = r1     // Catch:{ all -> 0x0037 }
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            return
        L_0x0035:
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            return
        L_0x0037:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzus.zzn(boolean):void");
    }

    public final void zzo(zzf zzf2, Executor executor) {
        this.zzs = zzf2;
        this.zzm = executor;
    }
}
