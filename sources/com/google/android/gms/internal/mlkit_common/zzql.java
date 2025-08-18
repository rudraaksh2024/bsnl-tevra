package com.google.android.gms.internal.mlkit_common;

import android.os.SystemClock;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzql {
    private static final GmsLogger zza = new GmsLogger("RemoteModelUtils", "");

    public static zzlu zza(RemoteModel remoteModel, SharedPrefManager sharedPrefManager, zzqb zzqb) {
        zzlw zzlw;
        ModelType zzb = zzqb.zzb();
        String modelHash = remoteModel.getModelHash();
        zzma zzma = new zzma();
        zzlv zzlv = new zzlv();
        zzlv.zzc(remoteModel.getModelNameForBackend());
        zzlv.zzd(zzlx.CLOUD);
        zzlv.zza(zzag.zzb(modelHash));
        int ordinal = zzb.ordinal();
        if (ordinal == 2) {
            zzlw = zzlw.BASE_TRANSLATE;
        } else if (ordinal == 4) {
            zzlw = zzlw.CUSTOM;
        } else if (ordinal != 5) {
            zzlw = zzlw.TYPE_UNKNOWN;
        } else {
            zzlw = zzlw.BASE_DIGITAL_INK;
        }
        zzlv.zzb(zzlw);
        zzma.zzb(zzlv.zzg());
        zzmd zzc = zzma.zzc();
        zzlr zzlr = new zzlr();
        zzlr.zzd(zzqb.zzc());
        zzlr.zzc(zzqb.zzd());
        zzlr.zzb(Long.valueOf((long) zzqb.zza()));
        zzlr.zzf(zzc);
        if (zzqb.zzg()) {
            long modelDownloadBeginTimeMs = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs == 0) {
                zza.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                long modelFirstUseTimeMs = sharedPrefManager.getModelFirstUseTimeMs(remoteModel);
                if (modelFirstUseTimeMs == 0) {
                    modelFirstUseTimeMs = SystemClock.elapsedRealtime();
                    sharedPrefManager.setModelFirstUseTimeMs(remoteModel, modelFirstUseTimeMs);
                }
                zzlr.zzg(Long.valueOf(modelFirstUseTimeMs - modelDownloadBeginTimeMs));
            }
        }
        if (zzqb.zzf()) {
            long modelDownloadBeginTimeMs2 = sharedPrefManager.getModelDownloadBeginTimeMs(remoteModel);
            if (modelDownloadBeginTimeMs2 == 0) {
                zza.w("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                zzlr.zze(Long.valueOf(SystemClock.elapsedRealtime() - modelDownloadBeginTimeMs2));
            }
        }
        return zzlr.zzi();
    }
}
