package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.NetworkObserver;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.PlaybackResumer;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000e\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\rJ\u000e\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\"J\u0010\u0010'\u001a\u00020(2\b\b\u0001\u0010)\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\u00132\u0006\u0010*\u001a\u00020+J\u0016\u0010\u0011\u001a\u00020\u00132\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\rJ\u001e\u0010\u0011\u001a\u00020\u00132\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020.J(\u0010\u0011\u001a\u00020\u00132\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u000100J\r\u00101\u001a\u00020\rH\u0000¢\u0006\u0002\b2J\r\u00103\u001a\u00020\u0013H\u0000¢\u0006\u0002\b4J\r\u00105\u001a\u00020\u0013H\u0000¢\u0006\u0002\b6J\u0006\u00107\u001a\u00020\u0013J\u000e\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020(R\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/LegacyYouTubePlayerView;", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/SixteenByNineFrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "listener", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/FullscreenListener;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/FullscreenListener;Landroid/util/AttributeSet;I)V", "<set-?>", "", "canPlay", "getCanPlay$core_release", "()Z", "initialize", "Lkotlin/Function0;", "", "isYouTubePlayerReady", "isYouTubePlayerReady$core_release", "setYouTubePlayerReady$core_release", "(Z)V", "networkObserver", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/NetworkObserver;", "playbackResumer", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/PlaybackResumer;", "webViewYouTubePlayer", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/WebViewYouTubePlayer;", "getWebViewYouTubePlayer$core_release", "()Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/WebViewYouTubePlayer;", "youTubePlayerCallbacks", "", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/YouTubePlayerCallback;", "enableBackgroundPlayback", "enable", "getYouTubePlayerWhenReady", "youTubePlayerCallback", "inflateCustomPlayerUi", "Landroid/view/View;", "layoutId", "youTubePlayerListener", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/YouTubePlayerListener;", "handleNetworkEvents", "playerOptions", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/options/IFramePlayerOptions;", "videoId", "", "isEligibleForPlayback", "isEligibleForPlayback$core_release", "onResume", "onResume$core_release", "onStop", "onStop$core_release", "release", "setCustomPlayerUi", "view", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LegacyYouTubePlayerView.kt */
public final class LegacyYouTubePlayerView extends SixteenByNineFrameLayout {
    private boolean canPlay;
    /* access modifiers changed from: private */
    public Function0<Unit> initialize;
    private boolean isYouTubePlayerReady;
    private final NetworkObserver networkObserver;
    /* access modifiers changed from: private */
    public final PlaybackResumer playbackResumer;
    private final WebViewYouTubePlayer webViewYouTubePlayer;
    /* access modifiers changed from: private */
    public final Set<YouTubePlayerCallback> youTubePlayerCallbacks;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LegacyYouTubePlayerView(Context context, FullscreenListener fullscreenListener, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, fullscreenListener, (i2 & 4) != 0 ? null : attributeSet, (i2 & 8) != 0 ? 0 : i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LegacyYouTubePlayerView(Context context, FullscreenListener fullscreenListener, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fullscreenListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        WebViewYouTubePlayer webViewYouTubePlayer2 = new WebViewYouTubePlayer(context, fullscreenListener, (AttributeSet) null, 0, 12, (DefaultConstructorMarker) null);
        this.webViewYouTubePlayer = webViewYouTubePlayer2;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
        NetworkObserver networkObserver2 = new NetworkObserver(applicationContext);
        this.networkObserver = networkObserver2;
        PlaybackResumer playbackResumer2 = new PlaybackResumer();
        this.playbackResumer = playbackResumer2;
        this.initialize = LegacyYouTubePlayerView$initialize$1.INSTANCE;
        this.youTubePlayerCallbacks = new LinkedHashSet();
        this.canPlay = true;
        addView(webViewYouTubePlayer2, new FrameLayout.LayoutParams(-1, -1));
        webViewYouTubePlayer2.addListener(playbackResumer2);
        webViewYouTubePlayer2.addListener(new AbstractYouTubePlayerListener(this) {
            final /* synthetic */ LegacyYouTubePlayerView this$0;

            {
                this.this$0 = r1;
            }

            public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState playerState) {
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                Intrinsics.checkNotNullParameter(playerState, "state");
                if (playerState == PlayerConstants.PlayerState.PLAYING && !this.this$0.isEligibleForPlayback$core_release()) {
                    youTubePlayer.pause();
                }
            }
        });
        webViewYouTubePlayer2.addListener(new AbstractYouTubePlayerListener(this) {
            final /* synthetic */ LegacyYouTubePlayerView this$0;

            {
                this.this$0 = r1;
            }

            public void onReady(YouTubePlayer youTubePlayer) {
                Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
                this.this$0.setYouTubePlayerReady$core_release(true);
                for (YouTubePlayerCallback onYouTubePlayer : this.this$0.youTubePlayerCallbacks) {
                    onYouTubePlayer.onYouTubePlayer(youTubePlayer);
                }
                this.this$0.youTubePlayerCallbacks.clear();
                youTubePlayer.removeListener(this);
            }
        });
        networkObserver2.getListeners().add(new NetworkObserver.Listener(this) {
            final /* synthetic */ LegacyYouTubePlayerView this$0;

            public void onNetworkUnavailable() {
            }

            {
                this.this$0 = r1;
            }

            public void onNetworkAvailable() {
                if (!this.this$0.isYouTubePlayerReady$core_release()) {
                    this.this$0.initialize.invoke();
                } else {
                    this.this$0.playbackResumer.resume(this.this$0.getWebViewYouTubePlayer$core_release().getYoutubePlayer$core_release());
                }
            }
        });
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LegacyYouTubePlayerView(Context context) {
        this(context, FakeWebViewYouTubeListener.INSTANCE, (AttributeSet) null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final WebViewYouTubePlayer getWebViewYouTubePlayer$core_release() {
        return this.webViewYouTubePlayer;
    }

    public final boolean isYouTubePlayerReady$core_release() {
        return this.isYouTubePlayerReady;
    }

    public final void setYouTubePlayerReady$core_release(boolean z) {
        this.isYouTubePlayerReady = z;
    }

    public final boolean getCanPlay$core_release() {
        return this.canPlay;
    }

    public final void initialize(YouTubePlayerListener youTubePlayerListener, boolean z, IFramePlayerOptions iFramePlayerOptions, String str) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        Intrinsics.checkNotNullParameter(iFramePlayerOptions, "playerOptions");
        if (!this.isYouTubePlayerReady) {
            if (z) {
                this.networkObserver.observeNetwork();
            }
            Function0<Unit> legacyYouTubePlayerView$initialize$2 = new LegacyYouTubePlayerView$initialize$2(this, iFramePlayerOptions, str, youTubePlayerListener);
            this.initialize = legacyYouTubePlayerView$initialize$2;
            if (!z) {
                legacyYouTubePlayerView$initialize$2.invoke();
                return;
            }
            return;
        }
        throw new IllegalStateException("This YouTubePlayerView has already been initialized.");
    }

    public final void initialize(YouTubePlayerListener youTubePlayerListener, boolean z, IFramePlayerOptions iFramePlayerOptions) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        Intrinsics.checkNotNullParameter(iFramePlayerOptions, "playerOptions");
        initialize(youTubePlayerListener, z, iFramePlayerOptions, (String) null);
    }

    public final void initialize(YouTubePlayerListener youTubePlayerListener, boolean z) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        initialize(youTubePlayerListener, z, IFramePlayerOptions.Companion.getDefault());
    }

    public final void initialize(YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        initialize(youTubePlayerListener, true);
    }

    public final void getYouTubePlayerWhenReady(YouTubePlayerCallback youTubePlayerCallback) {
        Intrinsics.checkNotNullParameter(youTubePlayerCallback, "youTubePlayerCallback");
        if (this.isYouTubePlayerReady) {
            youTubePlayerCallback.onYouTubePlayer(this.webViewYouTubePlayer.getYoutubePlayer$core_release());
        } else {
            this.youTubePlayerCallbacks.add(youTubePlayerCallback);
        }
    }

    public final View inflateCustomPlayerUi(int i) {
        removeViews(1, getChildCount() - 1);
        View inflate = View.inflate(getContext(), i, this);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(context, layoutId, this)");
        return inflate;
    }

    public final void setCustomPlayerUi(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        removeViews(1, getChildCount() - 1);
        addView(view);
    }

    public final void release() {
        this.networkObserver.destroy();
        removeView(this.webViewYouTubePlayer);
        this.webViewYouTubePlayer.removeAllViews();
        this.webViewYouTubePlayer.destroy();
    }

    public final void onResume$core_release() {
        this.playbackResumer.onLifecycleResume();
        this.canPlay = true;
    }

    public final void onStop$core_release() {
        this.webViewYouTubePlayer.getYoutubePlayer$core_release().pause();
        this.playbackResumer.onLifecycleStop();
        this.canPlay = false;
    }

    public final boolean isEligibleForPlayback$core_release() {
        return this.canPlay || this.webViewYouTubePlayer.isBackgroundPlaybackEnabled$core_release();
    }

    public final void enableBackgroundPlayback(boolean z) {
        this.webViewYouTubePlayer.setBackgroundPlaybackEnabled$core_release(z);
    }
}
