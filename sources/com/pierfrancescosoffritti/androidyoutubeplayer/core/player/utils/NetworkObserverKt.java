package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0003¨\u0006\u0005"}, d2 = {"isConnectedToInternet", "", "context", "Landroid/content/Context;", "Landroid/net/NetworkCapabilities;", "core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: NetworkObserver.kt */
public final class NetworkObserverKt {
    /* access modifiers changed from: private */
    public static final boolean isConnectedToInternet(Context context) {
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null) {
            return false;
        }
        return isConnectedToInternet(networkCapabilities);
    }

    private static final boolean isConnectedToInternet(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(3)) {
            return true;
        }
        return false;
    }
}
