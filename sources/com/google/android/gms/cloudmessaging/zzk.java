package com.google.android.gms.cloudmessaging;

import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.1.0 */
public final /* synthetic */ class zzk implements Handler.Callback {
    public final /* synthetic */ zzn zza;

    public /* synthetic */ zzk(zzn zzn) {
        this.zza = zzn;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0049, code lost:
        r4 = r5.getData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0054, code lost:
        if (r4.getBoolean("unsupported", false) == false) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        r1.zzc(new com.google.android.gms.cloudmessaging.zzs(4, "Not supported by GmsCore", (java.lang.Throwable) null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0063, code lost:
        r1.zza(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r5) {
        /*
            r4 = this;
            java.lang.String r0 = "Received response for unknown request: "
            java.lang.String r1 = "MessengerIpcClient"
            int r2 = r5.arg1
            r3 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r3)
            if (r1 == 0) goto L_0x0020
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Received response to request: "
            r1.<init>(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "MessengerIpcClient"
            android.util.Log.d(r3, r1)
        L_0x0020:
            com.google.android.gms.cloudmessaging.zzn r4 = r4.zza
            monitor-enter(r4)
            android.util.SparseArray r1 = r4.zze     // Catch:{ all -> 0x0068 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0068 }
            com.google.android.gms.cloudmessaging.zzr r1 = (com.google.android.gms.cloudmessaging.zzr) r1     // Catch:{ all -> 0x0068 }
            if (r1 != 0) goto L_0x0040
            java.lang.String r5 = "MessengerIpcClient"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0068 }
            r1.<init>(r0)     // Catch:{ all -> 0x0068 }
            r1.append(r2)     // Catch:{ all -> 0x0068 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x0068 }
            android.util.Log.w(r5, r0)     // Catch:{ all -> 0x0068 }
            monitor-exit(r4)     // Catch:{ all -> 0x0068 }
            goto L_0x0066
        L_0x0040:
            android.util.SparseArray r0 = r4.zze     // Catch:{ all -> 0x0068 }
            r0.remove(r2)     // Catch:{ all -> 0x0068 }
            r4.zzf()     // Catch:{ all -> 0x0068 }
            monitor-exit(r4)     // Catch:{ all -> 0x0068 }
            android.os.Bundle r4 = r5.getData()
            java.lang.String r5 = "unsupported"
            r0 = 0
            boolean r5 = r4.getBoolean(r5, r0)
            if (r5 == 0) goto L_0x0063
            java.lang.String r4 = "Not supported by GmsCore"
            com.google.android.gms.cloudmessaging.zzs r5 = new com.google.android.gms.cloudmessaging.zzs
            r0 = 4
            r2 = 0
            r5.<init>(r0, r4, r2)
            r1.zzc(r5)
            goto L_0x0066
        L_0x0063:
            r1.zza(r4)
        L_0x0066:
            r4 = 1
            return r4
        L_0x0068:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0068 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cloudmessaging.zzk.handleMessage(android.os.Message):boolean");
    }
}
