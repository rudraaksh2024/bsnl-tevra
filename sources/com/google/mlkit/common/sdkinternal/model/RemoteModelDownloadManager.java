package com.google.mlkit.common.sdkinternal.model;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.ParcelFileDescriptor;
import android.util.LongSparseArray;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzlm;
import com.google.android.gms.internal.mlkit_common.zzls;
import com.google.android.gms.internal.mlkit_common.zzpq;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqc;
import com.google.android.gms.internal.mlkit_common.zzqk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelInfo;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public class RemoteModelDownloadManager {
    /* access modifiers changed from: private */
    public static final GmsLogger zza = new GmsLogger("ModelDownloadManager", "");
    private static final Map zzb = new HashMap();
    /* access modifiers changed from: private */
    public final LongSparseArray zzc = new LongSparseArray();
    /* access modifiers changed from: private */
    public final LongSparseArray zzd = new LongSparseArray();
    /* access modifiers changed from: private */
    public final MlKitContext zze;
    private final DownloadManager zzf;
    /* access modifiers changed from: private */
    public final RemoteModel zzg;
    private final ModelType zzh;
    /* access modifiers changed from: private */
    public final zzpz zzi;
    private final SharedPrefManager zzj;
    private final ModelFileHelper zzk;
    private final ModelInfoRetrieverInterop zzl;
    private final RemoteModelFileManager zzm;
    private DownloadConditions zzn;

    RemoteModelDownloadManager(MlKitContext mlKitContext, RemoteModel remoteModel, ModelFileHelper modelFileHelper, RemoteModelFileManager remoteModelFileManager, ModelInfoRetrieverInterop modelInfoRetrieverInterop, zzpz zzpz) {
        this.zze = mlKitContext;
        this.zzh = remoteModel.getModelType();
        this.zzg = remoteModel;
        DownloadManager downloadManager = (DownloadManager) mlKitContext.getApplicationContext().getSystemService("download");
        this.zzf = downloadManager;
        this.zzi = zzpz;
        if (downloadManager == null) {
            zza.d("ModelDownloadManager", "Download manager service is not available in the service.");
        }
        this.zzk = modelFileHelper;
        this.zzj = SharedPrefManager.getInstance(mlKitContext);
        this.zzl = modelInfoRetrieverInterop;
        this.zzm = remoteModelFileManager;
    }

    public static synchronized RemoteModelDownloadManager getInstance(MlKitContext mlKitContext, RemoteModel remoteModel, ModelFileHelper modelFileHelper, RemoteModelFileManager remoteModelFileManager, ModelInfoRetrieverInterop modelInfoRetrieverInterop) {
        RemoteModelDownloadManager remoteModelDownloadManager;
        synchronized (RemoteModelDownloadManager.class) {
            Map map = zzb;
            if (!map.containsKey(remoteModel)) {
                map.put(remoteModel, new RemoteModelDownloadManager(mlKitContext, remoteModel, modelFileHelper, remoteModelFileManager, modelInfoRetrieverInterop, zzqk.zzb("common")));
            }
            remoteModelDownloadManager = (RemoteModelDownloadManager) map.get(remoteModel);
        }
        return remoteModelDownloadManager;
    }

    private final Task zzj(long j) {
        this.zze.getApplicationContext().registerReceiver(zzm(j), new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), (String) null, MLTaskExecutor.getInstance().getHandler());
        return zzk(j).getTask();
    }

    private final synchronized TaskCompletionSource zzk(long j) {
        TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.zzd.get(j);
        if (taskCompletionSource != null) {
            return taskCompletionSource;
        }
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        this.zzd.put(j, taskCompletionSource2);
        return taskCompletionSource2;
    }

    private final synchronized zzd zzm(long j) {
        zzd zzd2 = (zzd) this.zzc.get(j);
        if (zzd2 != null) {
            return zzd2;
        }
        zzd zzd3 = new zzd(this, j, zzk(j), (zzc) null);
        this.zzc.put(j, zzd3);
        return zzd3;
    }

    private final synchronized Long zzn(DownloadManager.Request request, ModelInfo modelInfo) {
        DownloadManager downloadManager = this.zzf;
        if (downloadManager == null) {
            return null;
        }
        long enqueue = downloadManager.enqueue(request);
        GmsLogger gmsLogger = zza;
        gmsLogger.d("ModelDownloadManager", "Schedule a new downloading task: " + enqueue);
        this.zzj.setDownloadingModelInfo(enqueue, modelInfo);
        this.zzi.zzf(zzqc.zzg(), this.zzg, zzlm.NO_ERROR, false, modelInfo.getModelType(), zzls.SCHEDULED);
        return Long.valueOf(enqueue);
    }

    private final synchronized Long zzo(ModelInfo modelInfo, DownloadConditions downloadConditions) throws MlKitException {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        String downloadingModelHash = this.zzj.getDownloadingModelHash(this.zzg);
        Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
        if (downloadingModelHash == null || !downloadingModelHash.equals(modelInfo.getModelHash()) || downloadingModelStatusCode == null) {
            GmsLogger gmsLogger = zza;
            gmsLogger.d("ModelDownloadManager", "Need to download a new model.");
            removeOrCancelDownload();
            DownloadManager.Request request = new DownloadManager.Request(modelInfo.getModelUri());
            if (this.zzk.modelExistsLocally(modelInfo.getModelNameForPersist(), modelInfo.getModelType())) {
                gmsLogger.d("ModelDownloadManager", "Model update is enabled and have a previous downloaded model, use download condition");
                this.zzi.zzf(zzqc.zzg(), this.zzg, zzlm.NO_ERROR, false, modelInfo.getModelType(), zzls.UPDATE_AVAILABLE);
            }
            request.setRequiresCharging(downloadConditions.isChargingRequired());
            if (downloadConditions.isWifiRequired()) {
                request.setAllowedNetworkTypes(2);
            }
            return zzn(request, modelInfo);
        }
        Integer downloadingModelStatusCode2 = getDownloadingModelStatusCode();
        if (downloadingModelStatusCode2 == null || !(downloadingModelStatusCode2.intValue() == 8 || downloadingModelStatusCode2.intValue() == 16)) {
            this.zzi.zzf(zzqc.zzg(), this.zzg, zzlm.NO_ERROR, false, this.zzg.getModelType(), zzls.DOWNLOADING);
        }
        zza.d("ModelDownloadManager", "New model is already in downloading, do nothing.");
        return null;
    }

    public Task<Void> ensureModelDownloaded() {
        MlKitException mlKitException;
        ModelInfo modelInfo;
        this.zzi.zzf(zzqc.zzg(), this.zzg, zzlm.NO_ERROR, false, ModelType.UNKNOWN, zzls.EXPLICITLY_REQUESTED);
        Long l = null;
        try {
            modelInfo = zzg();
            mlKitException = null;
        } catch (MlKitException e) {
            mlKitException = e;
            modelInfo = null;
        }
        try {
            Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
            Long downloadingId = getDownloadingId();
            if (!modelExistsLocally()) {
                if (downloadingModelStatusCode == null || downloadingModelStatusCode.intValue() != 8) {
                    if (downloadingModelStatusCode != null && downloadingModelStatusCode.intValue() == 16) {
                        MlKitException zzl2 = zzl(downloadingId);
                        removeOrCancelDownload();
                        return Tasks.forException(zzl2);
                    } else if (downloadingModelStatusCode == null || (!(downloadingModelStatusCode.intValue() == 4 || downloadingModelStatusCode.intValue() == 2 || downloadingModelStatusCode.intValue() == 1) || downloadingId == null || getDownloadingModelHash() == null)) {
                        if (modelInfo != null) {
                            l = zzo(modelInfo, this.zzn);
                        }
                        if (l == null) {
                            return Tasks.forException(new MlKitException("Failed to schedule the download task", 13, mlKitException));
                        }
                        return zzj(l.longValue());
                    } else {
                        zzpz zzpz = this.zzi;
                        zzpq zzg2 = zzqc.zzg();
                        RemoteModel remoteModel = this.zzg;
                        zzpz.zzf(zzg2, remoteModel, zzlm.NO_ERROR, false, remoteModel.getModelType(), zzls.DOWNLOADING);
                        return zzj(downloadingId.longValue());
                    }
                }
            }
            if (modelInfo != null) {
                Long zzo = zzo(modelInfo, this.zzn);
                if (zzo != null) {
                    return zzj(zzo.longValue());
                }
                zza.i("ModelDownloadManager", "Didn't schedule download for the updated model");
            }
            return Tasks.forResult(null);
        } catch (MlKitException e2) {
            return Tasks.forException(new MlKitException("Failed to ensure the model is downloaded.", 13, e2));
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        zza.e("ModelDownloadManager", "Downloaded file is not found");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.os.ParcelFileDescriptor getDownloadedFile() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.Long r0 = r5.getDownloadingId()     // Catch:{ all -> 0x0022 }
            android.app.DownloadManager r1 = r5.zzf     // Catch:{ all -> 0x0022 }
            r2 = 0
            if (r1 == 0) goto L_0x0020
            if (r0 == 0) goto L_0x0020
            long r3 = r0.longValue()     // Catch:{ FileNotFoundException -> 0x0015 }
            android.os.ParcelFileDescriptor r2 = r1.openDownloadedFile(r3)     // Catch:{ FileNotFoundException -> 0x0015 }
            goto L_0x001e
        L_0x0015:
            com.google.android.gms.common.internal.GmsLogger r0 = zza     // Catch:{ all -> 0x0022 }
            java.lang.String r1 = "ModelDownloadManager"
            java.lang.String r3 = "Downloaded file is not found"
            r0.e(r1, r3)     // Catch:{ all -> 0x0022 }
        L_0x001e:
            monitor-exit(r5)
            return r2
        L_0x0020:
            monitor-exit(r5)
            return r2
        L_0x0022:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.getDownloadedFile():android.os.ParcelFileDescriptor");
    }

    public synchronized Long getDownloadingId() {
        return this.zzj.getDownloadingModelId(this.zzg);
    }

    public synchronized String getDownloadingModelHash() {
        return this.zzj.getDownloadingModelHash(this.zzg);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:43|44) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0047, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008f, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x008d */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0048 A[SYNTHETIC, Splitter:B:20:0x0048] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:43:0x008d=Splitter:B:43:0x008d, B:33:0x006e=Splitter:B:33:0x006e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Integer getDownloadingModelStatusCode() {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.Long r0 = r8.getDownloadingId()     // Catch:{ all -> 0x0090 }
            android.app.DownloadManager r1 = r8.zzf     // Catch:{ all -> 0x0090 }
            r2 = 0
            if (r1 == 0) goto L_0x008e
            if (r0 != 0) goto L_0x000e
            goto L_0x008e
        L_0x000e:
            android.app.DownloadManager$Query r3 = new android.app.DownloadManager$Query     // Catch:{ all -> 0x0090 }
            r3.<init>()     // Catch:{ all -> 0x0090 }
            r4 = 1
            long[] r5 = new long[r4]     // Catch:{ all -> 0x0090 }
            long r6 = r0.longValue()     // Catch:{ all -> 0x0090 }
            r0 = 0
            r5[r0] = r6     // Catch:{ all -> 0x0090 }
            android.app.DownloadManager$Query r3 = r3.setFilterById(r5)     // Catch:{ all -> 0x0090 }
            android.database.Cursor r1 = r1.query(r3)     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x003e
            boolean r3 = r1.moveToFirst()     // Catch:{ all -> 0x003c }
            if (r3 == 0) goto L_0x003e
            java.lang.String r3 = "status"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ all -> 0x003c }
            int r3 = r1.getInt(r3)     // Catch:{ all -> 0x003c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x003c }
            goto L_0x003f
        L_0x003c:
            r2 = move-exception
            goto L_0x0073
        L_0x003e:
            r3 = r2
        L_0x003f:
            if (r3 != 0) goto L_0x0048
            if (r1 == 0) goto L_0x0046
            r1.close()     // Catch:{ all -> 0x0090 }
        L_0x0046:
            monitor-exit(r8)
            return r2
        L_0x0048:
            int r5 = r3.intValue()     // Catch:{ all -> 0x003c }
            r6 = 2
            if (r5 == r6) goto L_0x006d
            int r5 = r3.intValue()     // Catch:{ all -> 0x003c }
            r6 = 4
            if (r5 == r6) goto L_0x006d
            int r5 = r3.intValue()     // Catch:{ all -> 0x003c }
            if (r5 == r4) goto L_0x006d
            int r5 = r3.intValue()     // Catch:{ all -> 0x003c }
            r6 = 8
            if (r5 == r6) goto L_0x006d
            int r0 = r3.intValue()     // Catch:{ all -> 0x003c }
            r4 = 16
            if (r0 == r4) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            r2 = r3
        L_0x006e:
            r1.close()     // Catch:{ all -> 0x0090 }
            monitor-exit(r8)
            return r2
        L_0x0073:
            r1.close()     // Catch:{ all -> 0x0077 }
            goto L_0x008d
        L_0x0077:
            r1 = move-exception
            java.lang.Class<java.lang.Throwable> r3 = java.lang.Throwable.class
            java.lang.String r5 = "addSuppressed"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x008d }
            java.lang.Class<java.lang.Throwable> r7 = java.lang.Throwable.class
            r6[r0] = r7     // Catch:{ Exception -> 0x008d }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r5, r6)     // Catch:{ Exception -> 0x008d }
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x008d }
            r4[r0] = r1     // Catch:{ Exception -> 0x008d }
            r3.invoke(r2, r4)     // Catch:{ Exception -> 0x008d }
        L_0x008d:
            throw r2     // Catch:{ all -> 0x0090 }
        L_0x008e:
            monitor-exit(r8)
            return r2
        L_0x0090:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.getDownloadingModelStatusCode():java.lang.Integer");
    }

    public boolean isModelDownloadedAndValid() throws MlKitException {
        try {
            if (modelExistsLocally()) {
                return true;
            }
        } catch (MlKitException unused) {
            zza.d("ModelDownloadManager", "Failed to check if the model exist locally.");
        }
        Long downloadingId = getDownloadingId();
        String downloadingModelHash = getDownloadingModelHash();
        if (downloadingId == null || downloadingModelHash == null) {
            zza.d("ModelDownloadManager", "No new model is downloading.");
            removeOrCancelDownload();
            return false;
        }
        Integer downloadingModelStatusCode = getDownloadingModelStatusCode();
        GmsLogger gmsLogger = zza;
        new StringBuilder("Download Status code: ").append(downloadingModelStatusCode);
        gmsLogger.d("ModelDownloadManager", "Download Status code: ".concat(String.valueOf(downloadingModelStatusCode)));
        if (downloadingModelStatusCode == null) {
            removeOrCancelDownload();
            return false;
        } else if (!Objects.equal(downloadingModelStatusCode, 8) || zzi(downloadingModelHash) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean modelExistsLocally() throws MlKitException {
        return this.zzk.modelExistsLocally(this.zzg.getUniqueModelNameForPersist(), this.zzh);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeOrCancelDownload() throws com.google.mlkit.common.MlKitException {
        /*
            r5 = this;
            java.lang.String r0 = "Cancel or remove existing downloading task: "
            monitor-enter(r5)
            java.lang.Long r1 = r5.getDownloadingId()     // Catch:{ all -> 0x005e }
            android.app.DownloadManager r2 = r5.zzf     // Catch:{ all -> 0x005e }
            if (r2 == 0) goto L_0x005c
            if (r1 != 0) goto L_0x000e
            goto L_0x005c
        L_0x000e:
            com.google.android.gms.common.internal.GmsLogger r2 = zza     // Catch:{ all -> 0x005e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x005e }
            r3.<init>(r0)     // Catch:{ all -> 0x005e }
            java.lang.String r0 = "Cancel or remove existing downloading task: "
            r3.append(r1)     // Catch:{ all -> 0x005e }
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x005e }
            java.lang.String r0 = r0.concat(r3)     // Catch:{ all -> 0x005e }
            java.lang.String r3 = "ModelDownloadManager"
            r2.d(r3, r0)     // Catch:{ all -> 0x005e }
            android.app.DownloadManager r0 = r5.zzf     // Catch:{ all -> 0x005e }
            r2 = 1
            long[] r2 = new long[r2]     // Catch:{ all -> 0x005e }
            long r3 = r1.longValue()     // Catch:{ all -> 0x005e }
            r1 = 0
            r2[r1] = r3     // Catch:{ all -> 0x005e }
            int r0 = r0.remove(r2)     // Catch:{ all -> 0x005e }
            if (r0 > 0) goto L_0x0042
            java.lang.Integer r0 = r5.getDownloadingModelStatusCode()     // Catch:{ all -> 0x005e }
            if (r0 != 0) goto L_0x0040
            goto L_0x0042
        L_0x0040:
            monitor-exit(r5)
            return
        L_0x0042:
            com.google.mlkit.common.sdkinternal.model.ModelFileHelper r0 = r5.zzk     // Catch:{ all -> 0x005e }
            com.google.mlkit.common.model.RemoteModel r1 = r5.zzg     // Catch:{ all -> 0x005e }
            java.lang.String r1 = r1.getUniqueModelNameForPersist()     // Catch:{ all -> 0x005e }
            com.google.mlkit.common.model.RemoteModel r2 = r5.zzg     // Catch:{ all -> 0x005e }
            com.google.mlkit.common.sdkinternal.ModelType r2 = r2.getModelType()     // Catch:{ all -> 0x005e }
            r0.deleteTempFilesInPrivateFolder(r1, r2)     // Catch:{ all -> 0x005e }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r0 = r5.zzj     // Catch:{ all -> 0x005e }
            com.google.mlkit.common.model.RemoteModel r1 = r5.zzg     // Catch:{ all -> 0x005e }
            r0.clearDownloadingModelInfo(r1)     // Catch:{ all -> 0x005e }
            monitor-exit(r5)
            return
        L_0x005c:
            monitor-exit(r5)
            return
        L_0x005e:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.removeOrCancelDownload():void");
    }

    public void setDownloadConditions(DownloadConditions downloadConditions) {
        Preconditions.checkNotNull(downloadConditions, "DownloadConditions can not be null");
        this.zzn = downloadConditions;
    }

    public synchronized void updateLatestModelHashAndType(String str) throws MlKitException {
        this.zzj.setLatestModelHash(this.zzg, str);
        removeOrCancelDownload();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008a, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.mlkit.common.sdkinternal.ModelInfo zzg() throws com.google.mlkit.common.MlKitException {
        /*
            r10 = this;
            java.lang.String r0 = "The model "
            monitor-enter(r10)
            boolean r1 = r10.modelExistsLocally()     // Catch:{ all -> 0x00bd }
            if (r1 == 0) goto L_0x001f
            com.google.android.gms.internal.mlkit_common.zzpz r2 = r10.zzi     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.mlkit_common.zzpq r3 = com.google.android.gms.internal.mlkit_common.zzqc.zzg()     // Catch:{ all -> 0x00bd }
            com.google.mlkit.common.model.RemoteModel r4 = r10.zzg     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.mlkit_common.zzlm r5 = com.google.android.gms.internal.mlkit_common.zzlm.NO_ERROR     // Catch:{ all -> 0x00bd }
            r6 = 0
            com.google.mlkit.common.model.RemoteModel r7 = r10.zzg     // Catch:{ all -> 0x00bd }
            com.google.mlkit.common.sdkinternal.ModelType r7 = r7.getModelType()     // Catch:{ all -> 0x00bd }
            com.google.android.gms.internal.mlkit_common.zzls r8 = com.google.android.gms.internal.mlkit_common.zzls.LIVE     // Catch:{ all -> 0x00bd }
            r2.zzf(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00bd }
        L_0x001f:
            com.google.mlkit.common.sdkinternal.model.ModelInfoRetrieverInterop r2 = r10.zzl     // Catch:{ all -> 0x00bd }
            if (r2 == 0) goto L_0x00b3
            com.google.mlkit.common.model.RemoteModel r3 = r10.zzg     // Catch:{ all -> 0x00bd }
            com.google.mlkit.common.sdkinternal.ModelInfo r2 = r2.retrieveRemoteModelInfo(r3)     // Catch:{ all -> 0x00bd }
            r3 = 0
            if (r2 != 0) goto L_0x002e
            monitor-exit(r10)
            return r3
        L_0x002e:
            com.google.mlkit.common.sdkinternal.MlKitContext r4 = r10.zze     // Catch:{ all -> 0x00bd }
            com.google.mlkit.common.model.RemoteModel r5 = r10.zzg     // Catch:{ all -> 0x00bd }
            java.lang.String r6 = r2.getModelHash()     // Catch:{ all -> 0x00bd }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r7 = com.google.mlkit.common.sdkinternal.SharedPrefManager.getInstance(r4)     // Catch:{ all -> 0x00bd }
            java.lang.String r5 = r7.getIncompatibleModelHash(r5)     // Catch:{ all -> 0x00bd }
            boolean r5 = r6.equals(r5)     // Catch:{ all -> 0x00bd }
            r6 = 0
            r8 = 1
            if (r5 == 0) goto L_0x0063
            android.content.Context r4 = r4.getApplicationContext()     // Catch:{ all -> 0x00bd }
            java.lang.String r4 = com.google.mlkit.common.sdkinternal.CommonUtils.getAppVersion(r4)     // Catch:{ all -> 0x00bd }
            java.lang.String r5 = r7.getPreviousAppVersion()     // Catch:{ all -> 0x00bd }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00bd }
            if (r4 == 0) goto L_0x0063
            com.google.android.gms.common.internal.GmsLogger r4 = zza     // Catch:{ all -> 0x00bd }
            java.lang.String r5 = "ModelDownloadManager"
            java.lang.String r7 = "The model is incompatible with TFLite and the app is not upgraded, do not download"
            r4.e(r5, r7)     // Catch:{ all -> 0x00bd }
            r4 = r6
            goto L_0x0064
        L_0x0063:
            r4 = r8
        L_0x0064:
            if (r1 != 0) goto L_0x006d
            com.google.mlkit.common.sdkinternal.SharedPrefManager r5 = r10.zzj     // Catch:{ all -> 0x00bd }
            com.google.mlkit.common.model.RemoteModel r7 = r10.zzg     // Catch:{ all -> 0x00bd }
            r5.clearLatestModelHash(r7)     // Catch:{ all -> 0x00bd }
        L_0x006d:
            com.google.mlkit.common.sdkinternal.MlKitContext r5 = r10.zze     // Catch:{ all -> 0x00bd }
            com.google.mlkit.common.model.RemoteModel r7 = r10.zzg     // Catch:{ all -> 0x00bd }
            java.lang.String r9 = r2.getModelHash()     // Catch:{ all -> 0x00bd }
            com.google.mlkit.common.sdkinternal.SharedPrefManager r5 = com.google.mlkit.common.sdkinternal.SharedPrefManager.getInstance(r5)     // Catch:{ all -> 0x00bd }
            java.lang.String r5 = r5.getLatestModelHash(r7)     // Catch:{ all -> 0x00bd }
            boolean r5 = r9.equals(r5)     // Catch:{ all -> 0x00bd }
            r5 = r5 ^ r8
            if (r4 == 0) goto L_0x008b
            if (r1 == 0) goto L_0x0089
            if (r5 != 0) goto L_0x0089
            goto L_0x008c
        L_0x0089:
            monitor-exit(r10)
            return r2
        L_0x008b:
            r6 = r5
        L_0x008c:
            if (r1 == 0) goto L_0x0094
            r1 = r6 ^ r4
            if (r1 == 0) goto L_0x0094
            monitor-exit(r10)
            return r3
        L_0x0094:
            com.google.mlkit.common.MlKitException r1 = new com.google.mlkit.common.MlKitException     // Catch:{ all -> 0x00bd }
            com.google.mlkit.common.model.RemoteModel r2 = r10.zzg     // Catch:{ all -> 0x00bd }
            java.lang.String r2 = r2.getModelName()     // Catch:{ all -> 0x00bd }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bd }
            r3.<init>(r0)     // Catch:{ all -> 0x00bd }
            r3.append(r2)     // Catch:{ all -> 0x00bd }
            java.lang.String r0 = " is incompatible with TFLite runtime"
            r3.append(r0)     // Catch:{ all -> 0x00bd }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x00bd }
            r2 = 100
            r1.<init>(r0, r2)     // Catch:{ all -> 0x00bd }
            throw r1     // Catch:{ all -> 0x00bd }
        L_0x00b3:
            com.google.mlkit.common.MlKitException r0 = new com.google.mlkit.common.MlKitException     // Catch:{ all -> 0x00bd }
            java.lang.String r1 = "Please include com.google.mlkit:linkfirebase sdk as your dependency when you try to download from Firebase."
            r2 = 14
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00bd }
            throw r0     // Catch:{ all -> 0x00bd }
        L_0x00bd:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager.zzg():com.google.mlkit.common.sdkinternal.ModelInfo");
    }

    public final File zzi(String str) throws MlKitException {
        GmsLogger gmsLogger = zza;
        gmsLogger.d("ModelDownloadManager", "Model downloaded successfully");
        this.zzi.zzf(zzqc.zzg(), this.zzg, zzlm.NO_ERROR, true, this.zzh, zzls.SUCCEEDED);
        ParcelFileDescriptor downloadedFile = getDownloadedFile();
        if (downloadedFile == null) {
            removeOrCancelDownload();
            return null;
        }
        gmsLogger.d("ModelDownloadManager", "moving downloaded model from external storage to private folder.");
        try {
            return this.zzm.moveModelToPrivateFolder(downloadedFile, str, this.zzg);
        } finally {
            removeOrCancelDownload();
        }
    }

    /* access modifiers changed from: private */
    public final MlKitException zzl(Long l) {
        DownloadManager downloadManager = this.zzf;
        Cursor cursor = null;
        if (!(downloadManager == null || l == null)) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(new long[]{l.longValue()}));
        }
        int i = 13;
        String str = "Model downloading failed";
        if (cursor != null && cursor.moveToFirst()) {
            int i2 = cursor.getInt(cursor.getColumnIndex("reason"));
            if (i2 == 1006) {
                str = "Model downloading failed due to insufficient space on the device.";
                i = 101;
            } else {
                str = "Model downloading failed due to error code: " + i2 + " from Android DownloadManager";
            }
        }
        return new MlKitException(str, i);
    }

    public int getFailureReason(Long l) {
        int columnIndex;
        DownloadManager downloadManager = this.zzf;
        Cursor cursor = null;
        if (!(downloadManager == null || l == null)) {
            cursor = downloadManager.query(new DownloadManager.Query().setFilterById(new long[]{l.longValue()}));
        }
        if (cursor == null || !cursor.moveToFirst() || (columnIndex = cursor.getColumnIndex("reason")) == -1) {
            return 0;
        }
        return cursor.getInt(columnIndex);
    }
}
