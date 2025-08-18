package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.vision.barcode.internal.zzj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.0 */
public final class zztx {
    private static zzcv zza;
    private static final zzcx zzb = zzcx.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zztn zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zztx(Context context, SharedPrefManager sharedPrefManager, zztn zztn, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zztn;
        zzuj.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new zztr(this));
        MLTaskExecutor instance = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzh = instance.scheduleCallable(new zzts(sharedPrefManager));
        zzcx zzcx = zzb;
        this.zzj = zzcx.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzcx.get(str)) : -1;
    }

    static long zza(List list, double d) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d / 100.0d) * ((double) list.size()))) - 1, 0))).longValue();
    }

    private static synchronized zzcv zzi() {
        synchronized (zztx.class) {
            zzcv zzcv = zza;
            if (zzcv != null) {
                return zzcv;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzcs zzcs = new zzcs();
            for (int i = 0; i < locales.size(); i++) {
                zzcs.zzd(CommonUtils.languageTagFromLocale(locales.get(i)));
            }
            zzcv zzf2 = zzcs.zzf();
            zza = zzf2;
            return zzf2;
        }
    }

    private final String zzj() {
        if (this.zzg.isSuccessful()) {
            return (String) this.zzg.getResult();
        }
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    private final boolean zzk(zzpk zzpk, long j, long j2) {
        if (this.zzk.get(zzpk) != null && j - ((Long) this.zzk.get(zzpk)).longValue() <= TimeUnit.SECONDS.toMillis(30)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzb() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zztm zztm, zzpk zzpk, String str) {
        String str2;
        zztm.zzb(zzpk);
        String zzd2 = zztm.zzd();
        zzsj zzsj = new zzsj();
        zzsj.zzb(this.zzc);
        zzsj.zzc(this.zzd);
        zzsj.zzh(zzi());
        zzsj.zzg(true);
        zzsj.zzl(zzd2);
        zzsj.zzj(str);
        if (this.zzh.isSuccessful()) {
            str2 = (String) this.zzh.getResult();
        } else {
            str2 = this.zzf.getMlSdkInstanceId();
        }
        zzsj.zzi(str2);
        zzsj.zzd(10);
        zzsj.zzk(Integer.valueOf(this.zzj));
        zztm.zzc(zzsj);
        this.zze.zza(zztm);
    }

    public final void zzd(zztm zztm, zzpk zzpk) {
        zze(zztm, zzpk, zzj());
    }

    public final void zze(zztm zztm, zzpk zzpk, String str) {
        MLTaskExecutor.workerThreadExecutor().execute(new zztt(this, zztm, zzpk, str));
    }

    public final void zzf(zztw zztw, zzpk zzpk) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (zzk(zzpk, elapsedRealtime, 30)) {
            this.zzk.put(zzpk, Long.valueOf(elapsedRealtime));
            zze(zztw.zza(), zzpk, zzj());
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(zzpk zzpk, zzj zzj2) {
        zzdb zzdb = (zzdb) this.zzl.get(zzpk);
        if (zzdb != null) {
            for (Object next : zzdb.zzw()) {
                ArrayList<Long> arrayList = new ArrayList<>(zzdb.zze(next));
                Collections.sort(arrayList);
                zzoj zzoj = new zzoj();
                long j = 0;
                for (Long longValue : arrayList) {
                    j += longValue.longValue();
                }
                zzoj.zza(Long.valueOf(j / ((long) arrayList.size())));
                zzoj.zzc(Long.valueOf(zza(arrayList, 100.0d)));
                zzoj.zzf(Long.valueOf(zza(arrayList, 75.0d)));
                zzoj.zzd(Long.valueOf(zza(arrayList, 50.0d)));
                zzoj.zzb(Long.valueOf(zza(arrayList, 25.0d)));
                zzoj.zze(Long.valueOf(zza(arrayList, 0.0d)));
                zze(zzj2.zza(next, arrayList.size(), zzoj.zzg()), zzpk, zzj());
            }
            this.zzl.remove(zzpk);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(zzpk zzpk, Object obj, long j, zzj zzj2) {
        if (!this.zzl.containsKey(zzpk)) {
            this.zzl.put(zzpk, zzbz.zzz());
        }
        ((zzdb) this.zzl.get(zzpk)).zzt(obj, Long.valueOf(j));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (zzk(zzpk, elapsedRealtime, 30)) {
            this.zzk.put(zzpk, Long.valueOf(elapsedRealtime));
            MLTaskExecutor.workerThreadExecutor().execute(new zztv(this, zzpk, zzj2));
        }
    }
}
