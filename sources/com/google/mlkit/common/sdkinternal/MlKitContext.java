package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentDiscovery;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.ComponentRuntime;
import com.google.mlkit.common.internal.MlKitComponentDiscoveryService;
import java.util.List;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public class MlKitContext {
    private static final Object zza = new Object();
    private static MlKitContext zzb;
    private ComponentRuntime zzc;

    private MlKitContext() {
    }

    public static MlKitContext getInstance() {
        MlKitContext mlKitContext;
        synchronized (zza) {
            Preconditions.checkState(zzb != null, "MlKitContext has not been initialized");
            mlKitContext = (MlKitContext) Preconditions.checkNotNull(zzb);
        }
        return mlKitContext;
    }

    public static MlKitContext initialize(Context context, List<ComponentRegistrar> list) {
        MlKitContext mlKitContext;
        synchronized (zza) {
            Preconditions.checkState(zzb == null, "MlKitContext is already initialized");
            MlKitContext mlKitContext2 = new MlKitContext();
            zzb = mlKitContext2;
            ComponentRuntime componentRuntime = new ComponentRuntime(TaskExecutors.MAIN_THREAD, list, Component.of(zzb(context), Context.class, (Class<? super T>[]) new Class[0]), Component.of(mlKitContext2, MlKitContext.class, (Class<? super T>[]) new Class[0]));
            mlKitContext2.zzc = componentRuntime;
            componentRuntime.initializeEagerComponents(true);
            mlKitContext = zzb;
        }
        return mlKitContext;
    }

    public static MlKitContext initializeIfNeeded(Context context) {
        MlKitContext mlKitContext;
        synchronized (zza) {
            mlKitContext = zzb;
            if (mlKitContext == null) {
                mlKitContext = zza(context);
            }
        }
        return mlKitContext;
    }

    public static MlKitContext zza(Context context) {
        MlKitContext mlKitContext;
        synchronized (zza) {
            Preconditions.checkState(zzb == null, "MlKitContext is already initialized");
            MlKitContext mlKitContext2 = new MlKitContext();
            zzb = mlKitContext2;
            Context zzb2 = zzb(context);
            ComponentRuntime build = ComponentRuntime.builder(TaskExecutors.MAIN_THREAD).addLazyComponentRegistrars(ComponentDiscovery.forContext(zzb2, MlKitComponentDiscoveryService.class).discoverLazy()).addComponent(Component.of(zzb2, Context.class, (Class<? super T>[]) new Class[0])).addComponent(Component.of(mlKitContext2, MlKitContext.class, (Class<? super T>[]) new Class[0])).build();
            mlKitContext2.zzc = build;
            build.initializeEagerComponents(true);
            mlKitContext = zzb;
        }
        return mlKitContext;
    }

    private static Context zzb(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    public <T> T get(Class<T> cls) {
        Preconditions.checkState(zzb == this, "MlKitContext has been deleted");
        Preconditions.checkNotNull(this.zzc);
        return this.zzc.get(cls);
    }

    public Context getApplicationContext() {
        return (Context) get(Context.class);
    }

    public static MlKitContext initializeIfNeeded(Context context, List<ComponentRegistrar> list) {
        MlKitContext mlKitContext;
        synchronized (zza) {
            mlKitContext = zzb;
            if (mlKitContext == null) {
                mlKitContext = initialize(context, list);
            }
        }
        return mlKitContext;
    }
}
