package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzpz {
    private static zzar zza;
    private static final zzau zzb = zzau.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzpr zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zzpz(Context context, SharedPrefManager sharedPrefManager, zzpr zzpr, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzpr;
        zzqn.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new zzpv(this));
        MLTaskExecutor instance = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzh = instance.scheduleCallable(new zzpw(sharedPrefManager));
        zzau zzau = zzb;
        this.zzj = zzau.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzau.get(str)) : -1;
    }

    private static synchronized zzar zzh() {
        synchronized (zzpz.class) {
            zzar zzar = zza;
            if (zzar != null) {
                return zzar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzao zzao = new zzao();
            for (int i = 0; i < locales.size(); i++) {
                zzao.zzb(CommonUtils.languageTagFromLocale(locales.get(i)));
            }
            zzar zzc2 = zzao.zzc();
            zza = zzc2;
            return zzc2;
        }
    }

    private final zzol zzi(String str, String str2) {
        String str3;
        zzol zzol = new zzol();
        zzol.zzb(this.zzc);
        zzol.zzc(this.zzd);
        zzol.zzh(zzh());
        zzol.zzg(true);
        zzol.zzl(str);
        zzol.zzj(str2);
        if (this.zzh.isSuccessful()) {
            str3 = (String) this.zzh.getResult();
        } else {
            str3 = this.zzf.getMlSdkInstanceId();
        }
        zzol.zzi(str3);
        zzol.zzd(10);
        zzol.zzk(Integer.valueOf(this.zzj));
        return zzol;
    }

    private final String zzj() {
        if (this.zzg.isSuccessful()) {
            return (String) this.zzg.getResult();
        }
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zza() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzpq zzpq, zzln zzln, String str) {
        zzpq.zza(zzln);
        zzpq.zzc(zzi(zzpq.zzd(), str));
        this.zze.zza(zzpq);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzpq zzpq, zzqb zzqb, RemoteModel remoteModel) {
        zzpq.zza(zzln.MODEL_DOWNLOAD);
        zzpq.zzc(zzi(zzqb.zze(), zzj()));
        zzpq.zzb(zzql.zza(remoteModel, this.zzf, zzqb));
        this.zze.zza(zzpq);
    }

    public final void zzd(zzpq zzpq, zzln zzln) {
        MLTaskExecutor.workerThreadExecutor().execute(new zzpx(this, zzpq, zzln, zzj()));
    }

    public final void zze(zzpq zzpq, RemoteModel remoteModel, boolean z, int i) {
        zzqa zzh2 = zzqb.zzh();
        zzh2.zzf(false);
        zzh2.zzd(remoteModel.getModelType());
        zzh2.zza(zzls.FAILED);
        zzh2.zzb(zzlm.DOWNLOAD_FAILED);
        zzh2.zzc(i);
        zzg(zzpq, remoteModel, zzh2.zzh());
    }

    public final void zzf(zzpq zzpq, RemoteModel remoteModel, zzlm zzlm, boolean z, ModelType modelType, zzls zzls) {
        zzqa zzh2 = zzqb.zzh();
        zzh2.zzf(z);
        zzh2.zzd(modelType);
        zzh2.zzb(zzlm);
        zzh2.zza(zzls);
        zzg(zzpq, remoteModel, zzh2.zzh());
    }

    public final void zzg(zzpq zzpq, RemoteModel remoteModel, zzqb zzqb) {
        MLTaskExecutor.workerThreadExecutor().execute(new zzpy(this, zzpq, zzqb, remoteModel));
    }
}
