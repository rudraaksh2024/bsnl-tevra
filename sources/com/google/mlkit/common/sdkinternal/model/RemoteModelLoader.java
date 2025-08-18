package com.google.mlkit.common.sdkinternal.model;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqk;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.io.File;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public class RemoteModelLoader {
    private static final GmsLogger zza = new GmsLogger("RemoteModelLoader", "");
    private static final Map zzb = new HashMap();
    private final MlKitContext zzc;
    private final RemoteModel zzd;
    private final RemoteModelDownloadManager zze;
    private final RemoteModelFileManager zzf;
    private final RemoteModelLoaderHelper zzg;
    private final zzpz zzh;
    private boolean zzi = true;

    private RemoteModelLoader(MlKitContext mlKitContext, RemoteModel remoteModel, ModelValidator modelValidator, RemoteModelLoaderHelper remoteModelLoaderHelper, RemoteModelFileMover remoteModelFileMover) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(mlKitContext, remoteModel, modelValidator, new ModelFileHelper(mlKitContext), remoteModelFileMover);
        this.zzf = remoteModelFileManager;
        this.zze = RemoteModelDownloadManager.getInstance(mlKitContext, remoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
        this.zzg = remoteModelLoaderHelper;
        this.zzc = mlKitContext;
        this.zzd = remoteModel;
        this.zzh = zzqk.zzb("common");
    }

    public static synchronized RemoteModelLoader getInstance(MlKitContext mlKitContext, RemoteModel remoteModel, ModelValidator modelValidator, RemoteModelLoaderHelper remoteModelLoaderHelper, RemoteModelFileMover remoteModelFileMover) {
        RemoteModelLoader remoteModelLoader;
        synchronized (RemoteModelLoader.class) {
            String uniqueModelNameForPersist = remoteModel.getUniqueModelNameForPersist();
            Map map = zzb;
            if (!map.containsKey(uniqueModelNameForPersist)) {
                map.put(uniqueModelNameForPersist, new RemoteModelLoader(mlKitContext, remoteModel, modelValidator, remoteModelLoaderHelper, remoteModelFileMover));
            }
            remoteModelLoader = (RemoteModelLoader) map.get(uniqueModelNameForPersist);
        }
        return remoteModelLoader;
    }

    private final MappedByteBuffer zza(String str) throws MlKitException {
        return this.zzg.loadModelAtPath(str);
    }

    private final MappedByteBuffer zzb(File file) throws MlKitException {
        try {
            return zza(file.getAbsolutePath());
        } catch (Exception e) {
            this.zzf.zzc(file);
            throw new MlKitException("Failed to load newly downloaded model.", 14, e);
        }
    }

    public RemoteModel getRemoteModel() {
        return this.zzd;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00bf A[Catch:{ Exception -> 0x00db }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00fb A[Catch:{ Exception -> 0x00db }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.nio.MappedByteBuffer load() throws com.google.mlkit.common.MlKitException {
        /*
            r10 = this;
            java.lang.String r0 = "Download Status code: "
            monitor-enter(r10)
            com.google.android.gms.common.internal.GmsLogger r1 = zza     // Catch:{ all -> 0x0100 }
            java.lang.String r2 = "RemoteModelLoader"
            java.lang.String r3 = "Try to load newly downloaded model file."
            r1.d(r2, r3)     // Catch:{ all -> 0x0100 }
            boolean r2 = r10.zzi     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r3 = r10.zze     // Catch:{ all -> 0x0100 }
            java.lang.Long r3 = r3.getDownloadingId()     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r4 = r10.zze     // Catch:{ all -> 0x0100 }
            java.lang.String r4 = r4.getDownloadingModelHash()     // Catch:{ all -> 0x0100 }
            r5 = 0
            r6 = 0
            if (r3 == 0) goto L_0x00b0
            if (r4 != 0) goto L_0x0022
            goto L_0x00b0
        L_0x0022:
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r7 = r10.zze     // Catch:{ all -> 0x0100 }
            java.lang.Integer r7 = r7.getDownloadingModelStatusCode()     // Catch:{ all -> 0x0100 }
            if (r7 != 0) goto L_0x0031
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = r10.zze     // Catch:{ all -> 0x0100 }
            r0.removeOrCancelDownload()     // Catch:{ all -> 0x0100 }
            goto L_0x00bc
        L_0x0031:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0100 }
            r8.<init>(r0)     // Catch:{ all -> 0x0100 }
            java.lang.String r0 = "Download Status code: "
            r8.append(r7)     // Catch:{ all -> 0x0100 }
            java.lang.String r8 = r7.toString()     // Catch:{ all -> 0x0100 }
            java.lang.String r0 = r0.concat(r8)     // Catch:{ all -> 0x0100 }
            java.lang.String r8 = "RemoteModelLoader"
            r1.d(r8, r0)     // Catch:{ all -> 0x0100 }
            int r0 = r7.intValue()     // Catch:{ all -> 0x0100 }
            r8 = 8
            if (r0 != r8) goto L_0x0091
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = r10.zze     // Catch:{ all -> 0x0100 }
            java.io.File r0 = r0.zzi(r4)     // Catch:{ all -> 0x0100 }
            if (r0 != 0) goto L_0x0059
            goto L_0x00bc
        L_0x0059:
            java.nio.MappedByteBuffer r3 = r10.zzb(r0)     // Catch:{ all -> 0x0100 }
            java.lang.String r7 = r0.getParent()     // Catch:{ all -> 0x0100 }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x0100 }
            java.lang.String r8 = "Moved the downloaded model to private folder successfully: "
            java.lang.String r9 = "RemoteModelLoader"
            java.lang.String r7 = r8.concat(r7)     // Catch:{ all -> 0x0100 }
            r1.d(r9, r7)     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r7 = r10.zze     // Catch:{ all -> 0x0100 }
            r7.updateLatestModelHashAndType(r4)     // Catch:{ all -> 0x0100 }
            if (r2 == 0) goto L_0x00bd
            com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager r2 = r10.zzf     // Catch:{ all -> 0x0100 }
            boolean r2 = r2.zzd(r0)     // Catch:{ all -> 0x0100 }
            if (r2 == 0) goto L_0x00bd
            java.lang.String r2 = "RemoteModelLoader"
            java.lang.String r3 = "All old models are deleted."
            r1.d(r2, r3)     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager r2 = r10.zzf     // Catch:{ all -> 0x0100 }
            java.io.File r0 = r2.zza(r0)     // Catch:{ all -> 0x0100 }
            java.nio.MappedByteBuffer r3 = r10.zzb(r0)     // Catch:{ all -> 0x0100 }
            goto L_0x00bd
        L_0x0091:
            int r0 = r7.intValue()     // Catch:{ all -> 0x0100 }
            r2 = 16
            if (r0 != r2) goto L_0x00bc
            com.google.android.gms.internal.mlkit_common.zzpz r0 = r10.zzh     // Catch:{ all -> 0x0100 }
            com.google.android.gms.internal.mlkit_common.zzpq r2 = com.google.android.gms.internal.mlkit_common.zzqc.zzg()     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.model.RemoteModel r4 = r10.zzd     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r7 = r10.zze     // Catch:{ all -> 0x0100 }
            int r3 = r7.getFailureReason(r3)     // Catch:{ all -> 0x0100 }
            r0.zze(r2, r4, r5, r3)     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = r10.zze     // Catch:{ all -> 0x0100 }
            r0.removeOrCancelDownload()     // Catch:{ all -> 0x0100 }
            goto L_0x00bc
        L_0x00b0:
            java.lang.String r0 = "RemoteModelLoader"
            java.lang.String r2 = "No new model is downloading."
            r1.d(r0, r2)     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager r0 = r10.zze     // Catch:{ all -> 0x0100 }
            r0.removeOrCancelDownload()     // Catch:{ all -> 0x0100 }
        L_0x00bc:
            r3 = r6
        L_0x00bd:
            if (r3 != 0) goto L_0x00fb
            java.lang.String r0 = "RemoteModelLoader"
            java.lang.String r2 = "Loading existing model file."
            r1.d(r0, r2)     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager r0 = r10.zzf     // Catch:{ all -> 0x0100 }
            java.lang.String r0 = r0.zzb()     // Catch:{ all -> 0x0100 }
            if (r0 != 0) goto L_0x00d6
            java.lang.String r0 = "RemoteModelLoader"
            java.lang.String r2 = "No existing model file"
            r1.d(r0, r2)     // Catch:{ all -> 0x0100 }
            goto L_0x00fe
        L_0x00d6:
            java.nio.MappedByteBuffer r6 = r10.zza(r0)     // Catch:{ Exception -> 0x00db }
            goto L_0x00fe
        L_0x00db:
            r1 = move-exception
            com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager r2 = r10.zzf     // Catch:{ all -> 0x0100 }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x0100 }
            r3.<init>(r0)     // Catch:{ all -> 0x0100 }
            r2.zzc(r3)     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.sdkinternal.MlKitContext r0 = r10.zzc     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r0 = com.google.mlkit.common.sdkinternal.SharedPrefManager.getInstance(r0)     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.model.RemoteModel r2 = r10.zzd     // Catch:{ all -> 0x0100 }
            r0.clearLatestModelHash(r2)     // Catch:{ all -> 0x0100 }
            com.google.mlkit.common.MlKitException r0 = new com.google.mlkit.common.MlKitException     // Catch:{ all -> 0x0100 }
            java.lang.String r2 = "Failed to load an already downloaded model."
            r3 = 14
            r0.<init>(r2, r3, r1)     // Catch:{ all -> 0x0100 }
            throw r0     // Catch:{ all -> 0x0100 }
        L_0x00fb:
            r10.zzi = r5     // Catch:{ all -> 0x0100 }
            r6 = r3
        L_0x00fe:
            monitor-exit(r10)
            return r6
        L_0x0100:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelLoader.load():java.nio.MappedByteBuffer");
    }
}
