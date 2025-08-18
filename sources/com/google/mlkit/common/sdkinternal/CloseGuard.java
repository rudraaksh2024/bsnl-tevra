package com.google.mlkit.common.sdkinternal;

import android.util.Log;
import com.google.android.gms.internal.mlkit_common.zzle;
import com.google.android.gms.internal.mlkit_common.zzlf;
import com.google.android.gms.internal.mlkit_common.zzln;
import com.google.android.gms.internal.mlkit_common.zzlo;
import com.google.android.gms.internal.mlkit_common.zzpz;
import com.google.android.gms.internal.mlkit_common.zzqc;
import com.google.android.gms.internal.mlkit_common.zzqk;
import com.google.mlkit.common.sdkinternal.Cleaner;
import java.io.Closeable;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.mlkit:common@@18.9.0 */
public class CloseGuard implements Closeable {
    public static final int API_TRANSLATE = 1;
    private final AtomicBoolean zza = new AtomicBoolean();
    private final String zzb;
    private final Cleaner.Cleanable zzc;

    /* compiled from: com.google.mlkit:common@@18.9.0 */
    public static class Factory {
        private final Cleaner zza;

        public Factory(Cleaner cleaner) {
            this.zza = cleaner;
        }

        public CloseGuard create(Object obj, int i, Runnable runnable) {
            return new CloseGuard(obj, i, this.zza, runnable, zzqk.zzb("common"));
        }
    }

    CloseGuard(Object obj, int i, Cleaner cleaner, Runnable runnable, zzpz zzpz) {
        this.zzb = obj.toString();
        this.zzc = cleaner.register(obj, new zze(this, i, zzpz, runnable));
    }

    public final void close() {
        this.zza.set(true);
        this.zzc.clean();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, zzpz zzpz, Runnable runnable) {
        if (!this.zza.get()) {
            Log.e("MlKitCloseGuard", String.format(Locale.ENGLISH, "%s has not been closed", new Object[]{this.zzb}));
            zzlo zzlo = new zzlo();
            zzlf zzlf = new zzlf();
            zzlf.zzb(zzle.zzb(i));
            zzlo.zzh(zzlf.zzc());
            zzpz.zzd(zzqc.zzf(zzlo), zzln.HANDLE_LEAKED);
        }
        runnable.run();
    }
}
