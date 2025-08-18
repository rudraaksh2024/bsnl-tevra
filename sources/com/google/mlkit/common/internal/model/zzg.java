package com.google.mlkit.common.internal.model;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_common.zzki;
import com.google.android.gms.internal.mlkit_common.zzkz;
import com.google.android.gms.internal.mlkit_common.zzln;
import com.google.android.gms.internal.mlkit_common.zzlo;
import com.google.android.gms.internal.mlkit_common.zzlw;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqc;
import com.google.android.gms.internal.mlkit_common.zzqk;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.model.CustomRemoteModel;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.model.ModelFileHelper;
import com.google.mlkit.common.sdkinternal.model.ModelInfoRetrieverInterop;
import com.google.mlkit.common.sdkinternal.model.ModelValidator;
import com.google.mlkit.common.sdkinternal.model.RemoteModelDownloadManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelFileManager;
import com.google.mlkit.common.sdkinternal.model.RemoteModelManagerInterface;
import java.util.Set;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public final class zzg implements RemoteModelManagerInterface {
    private final MlKitContext zza;
    private final zzpz zzb;

    public zzg(MlKitContext mlKitContext) {
        zzpz zzb2 = zzqk.zzb("common");
        this.zza = mlKitContext;
        this.zzb = zzb2;
    }

    private final RemoteModelDownloadManager zze(CustomRemoteModel customRemoteModel) {
        RemoteModelFileManager remoteModelFileManager = new RemoteModelFileManager(this.zza, customRemoteModel, (ModelValidator) null, new ModelFileHelper(this.zza), new zza(this.zza, customRemoteModel.getUniqueModelNameForPersist()));
        MlKitContext mlKitContext = this.zza;
        return RemoteModelDownloadManager.getInstance(mlKitContext, customRemoteModel, new ModelFileHelper(mlKitContext), remoteModelFileManager, (ModelInfoRetrieverInterop) mlKitContext.get(ModelInfoRetrieverInterop.class));
    }

    public final /* bridge */ /* synthetic */ Task deleteDownloadedModel(RemoteModel remoteModel) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        MLTaskExecutor.workerThreadExecutor().execute(new zzc(this, (CustomRemoteModel) remoteModel, taskCompletionSource));
        return taskCompletionSource.getTask().addOnCompleteListener(new zzd(this));
    }

    public final /* bridge */ /* synthetic */ Task download(RemoteModel remoteModel, DownloadConditions downloadConditions) {
        RemoteModelDownloadManager zze = zze((CustomRemoteModel) remoteModel);
        zze.setDownloadConditions(downloadConditions);
        return Tasks.forResult(null).onSuccessTask(MLTaskExecutor.workerThreadExecutor(), new zzb(zze));
    }

    public final Task<Set<CustomRemoteModel>> getDownloadedModels() {
        return Tasks.forException(new MlKitException("Custom Remote model does not support listing downloaded models", 12));
    }

    public final /* bridge */ /* synthetic */ Task isModelDownloaded(RemoteModel remoteModel) {
        return MLTaskExecutor.getInstance().scheduleCallable(new zze(this, (CustomRemoteModel) remoteModel)).addOnCompleteListener(new zzf(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Boolean zza(CustomRemoteModel customRemoteModel) throws Exception {
        return Boolean.valueOf(zze(customRemoteModel).isModelDownloadedAndValid());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(CustomRemoteModel customRemoteModel, TaskCompletionSource taskCompletionSource) {
        try {
            new ModelFileHelper(this.zza).deleteAllModels(ModelType.CUSTOM, (String) Preconditions.checkNotNull(customRemoteModel.getModelName()));
            taskCompletionSource.setResult(null);
        } catch (RuntimeException e) {
            taskCompletionSource.setException(new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Task task) {
        boolean isSuccessful = task.isSuccessful();
        zzpz zzpz = this.zzb;
        zzlo zzlo = new zzlo();
        zzki zzki = new zzki();
        zzki.zzb(zzlw.CUSTOM);
        zzki.zza(Boolean.valueOf(isSuccessful));
        zzlo.zze(zzki.zzc());
        zzpz.zzd(zzqc.zzf(zzlo), zzln.REMOTE_MODEL_DELETE_ON_DEVICE);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(Task task) {
        boolean booleanValue = ((Boolean) task.getResult()).booleanValue();
        zzpz zzpz = this.zzb;
        zzlo zzlo = new zzlo();
        zzkz zzkz = new zzkz();
        zzkz.zzb(zzlw.CUSTOM);
        zzkz.zza(Boolean.valueOf(booleanValue));
        zzlo.zzg(zzkz.zzc());
        zzpz.zzd(zzqc.zzf(zzlo), zzln.REMOTE_MODEL_IS_DOWNLOADED);
    }
}
