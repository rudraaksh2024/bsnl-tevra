package com.google.android.gms.cloudmessaging;

import android.os.IBinder;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.1.0 */
public final /* synthetic */ class zzg implements Runnable {
    public final /* synthetic */ zzn zza;
    public final /* synthetic */ IBinder zzb;

    public /* synthetic */ zzg(zzn zzn, IBinder iBinder) {
        this.zza = zzn;
        this.zzb = iBinder;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0020, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0021, code lost:
        r0.zza(0, r3.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0029, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002b, code lost:
        throw r3;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:5:0x000a, B:9:0x000f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r3 = this;
            com.google.android.gms.cloudmessaging.zzn r0 = r3.zza
            android.os.IBinder r3 = r3.zzb
            monitor-enter(r0)
            r1 = 0
            if (r3 != 0) goto L_0x000f
            java.lang.String r3 = "Null service connection"
            r0.zza(r1, r3)     // Catch:{ all -> 0x001e }
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x000f:
            com.google.android.gms.cloudmessaging.zzp r2 = new com.google.android.gms.cloudmessaging.zzp     // Catch:{ RemoteException -> 0x0020 }
            r2.<init>(r3)     // Catch:{ RemoteException -> 0x0020 }
            r0.zzc = r2     // Catch:{ RemoteException -> 0x0020 }
            r3 = 2
            r0.zza = r3     // Catch:{ all -> 0x001e }
            r0.zzc()     // Catch:{ all -> 0x001e }
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x001e:
            r3 = move-exception
            goto L_0x002a
        L_0x0020:
            r3 = move-exception
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x001e }
            r0.zza(r1, r3)     // Catch:{ all -> 0x001e }
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cloudmessaging.zzg.run():void");
    }
}
