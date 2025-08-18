package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0003J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0006\u0010\u0012\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "listeners", "", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver$Listener;", "getListeners", "()Ljava/util/List;", "networkBroadcastReceiver", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkBroadcastReceiver;", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "destroy", "", "doObserveNetwork", "doObserveNetworkLegacy", "observeNetwork", "Listener", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NetworkObserver.kt */
public final class NetworkObserver {
    private final Context context;
    private final List<Listener> listeners = new ArrayList();
    private NetworkBroadcastReceiver networkBroadcastReceiver;
    private ConnectivityManager.NetworkCallback networkCallback;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver$Listener;", "", "onNetworkAvailable", "", "onNetworkUnavailable", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NetworkObserver.kt */
    public interface Listener {
        void onNetworkAvailable();

        void onNetworkUnavailable();
    }

    public NetworkObserver(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final List<Listener> getListeners() {
        return this.listeners;
    }

    public final void observeNetwork() {
        doObserveNetwork(this.context);
    }

    public final void destroy() {
        ConnectivityManager.NetworkCallback networkCallback2 = this.networkCallback;
        if (networkCallback2 != null) {
            Object systemService = this.context.getSystemService("connectivity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            ((ConnectivityManager) systemService).unregisterNetworkCallback(networkCallback2);
            this.listeners.clear();
            this.networkCallback = null;
            this.networkBroadcastReceiver = null;
        }
    }

    private final void doObserveNetwork(Context context2) {
        ConnectivityManager.NetworkCallback networkObserver$doObserveNetwork$callback$1 = new NetworkObserver$doObserveNetwork$callback$1(this);
        this.networkCallback = networkObserver$doObserveNetwork$callback$1;
        Object systemService = context2.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ((ConnectivityManager) systemService).registerDefaultNetworkCallback(networkObserver$doObserveNetwork$callback$1);
    }

    private final void doObserveNetworkLegacy(Context context2) {
        NetworkBroadcastReceiver networkBroadcastReceiver2 = new NetworkBroadcastReceiver(new NetworkObserver$doObserveNetworkLegacy$1(this), new NetworkObserver$doObserveNetworkLegacy$2(this));
        this.networkBroadcastReceiver = networkBroadcastReceiver2;
        context2.registerReceiver(networkBroadcastReceiver2, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
