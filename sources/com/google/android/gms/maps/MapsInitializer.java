package com.google.android.gms.maps;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class MapsInitializer {
    private static final String zza = "MapsInitializer";
    private static boolean zzb = false;
    private static Renderer zzc = Renderer.LEGACY;

    /* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
    public enum Renderer {
        LEGACY,
        LATEST
    }

    private MapsInitializer() {
    }

    public static synchronized int initialize(Context context) {
        int initialize;
        synchronized (MapsInitializer.class) {
            initialize = initialize(context, (Renderer) null, (OnMapsSdkInitializedCallback) null);
        }
        return initialize;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0084, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0028, code lost:
        return 0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0053 A[Catch:{ RemoteException -> 0x0085, RemoteException -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007e A[Catch:{ RemoteException -> 0x0085, RemoteException -> 0x005f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized int initialize(android.content.Context r5, com.google.android.gms.maps.MapsInitializer.Renderer r6, com.google.android.gms.maps.OnMapsSdkInitializedCallback r7) {
        /*
            java.lang.Class<com.google.android.gms.maps.MapsInitializer> r0 = com.google.android.gms.maps.MapsInitializer.class
            monitor-enter(r0)
            java.lang.String r1 = "Context is null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, r1)     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = zza     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = "preferredRenderer: "
            java.lang.String r3 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0091 }
            java.lang.String r2 = r2.concat(r3)     // Catch:{ all -> 0x0091 }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x0091 }
            boolean r1 = zzb     // Catch:{ all -> 0x0091 }
            r2 = 0
            if (r1 == 0) goto L_0x0029
            if (r7 == 0) goto L_0x0027
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = zzc     // Catch:{ all -> 0x0091 }
            r7.onMapsSdkInitialized(r5)     // Catch:{ all -> 0x0091 }
        L_0x0027:
            monitor-exit(r0)
            return r2
        L_0x0029:
            com.google.android.gms.maps.internal.zzf r1 = com.google.android.gms.maps.internal.zzcb.zza(r5, r6)     // Catch:{ GooglePlayServicesNotAvailableException -> 0x008c }
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r3 = r1.zze()     // Catch:{ RemoteException -> 0x0085 }
            com.google.android.gms.maps.CameraUpdateFactory.zza(r3)     // Catch:{ RemoteException -> 0x0085 }
            com.google.android.gms.internal.maps.zzi r3 = r1.zzj()     // Catch:{ RemoteException -> 0x0085 }
            com.google.android.gms.maps.model.BitmapDescriptorFactory.zza(r3)     // Catch:{ RemoteException -> 0x0085 }
            r3 = 1
            zzb = r3     // Catch:{ all -> 0x0091 }
            r4 = 2
            if (r6 == 0) goto L_0x004c
            int r6 = r6.ordinal()     // Catch:{ all -> 0x0091 }
            if (r6 == 0) goto L_0x004d
            if (r6 == r3) goto L_0x004a
            goto L_0x004c
        L_0x004a:
            r3 = r4
            goto L_0x004d
        L_0x004c:
            r3 = r2
        L_0x004d:
            int r6 = r1.zzd()     // Catch:{ RemoteException -> 0x005f }
            if (r6 != r4) goto L_0x0057
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.Renderer.LATEST     // Catch:{ RemoteException -> 0x005f }
            zzc = r6     // Catch:{ RemoteException -> 0x005f }
        L_0x0057:
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch:{ RemoteException -> 0x005f }
            r1.zzl(r5, r3)     // Catch:{ RemoteException -> 0x005f }
            goto L_0x0067
        L_0x005f:
            r5 = move-exception
            java.lang.String r6 = zza     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = "Failed to retrieve renderer type or log initialization."
            android.util.Log.e(r6, r1, r5)     // Catch:{ all -> 0x0091 }
        L_0x0067:
            java.lang.String r5 = zza     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = "loadedRenderer: "
            com.google.android.gms.maps.MapsInitializer$Renderer r1 = zzc     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0091 }
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = r6.concat(r1)     // Catch:{ all -> 0x0091 }
            android.util.Log.d(r5, r6)     // Catch:{ all -> 0x0091 }
            if (r7 == 0) goto L_0x0083
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = zzc     // Catch:{ all -> 0x0091 }
            r7.onMapsSdkInitialized(r5)     // Catch:{ all -> 0x0091 }
        L_0x0083:
            monitor-exit(r0)
            return r2
        L_0x0085:
            r5 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r6 = new com.google.android.gms.maps.model.RuntimeRemoteException     // Catch:{ all -> 0x0091 }
            r6.<init>(r5)     // Catch:{ all -> 0x0091 }
            throw r6     // Catch:{ all -> 0x0091 }
        L_0x008c:
            r5 = move-exception
            int r5 = r5.errorCode     // Catch:{ all -> 0x0091 }
            monitor-exit(r0)
            return r5
        L_0x0091:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapsInitializer.initialize(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer, com.google.android.gms.maps.OnMapsSdkInitializedCallback):int");
    }
}
