package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.pierfrancescosoffritti.androidyoutubeplayer.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayerBridge;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import java.io.InputStream;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.logging.log4j.util.Chars;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010!\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0017J\b\u0010\"\u001a\u00020\u001dH\u0016J\b\u0010#\u001a\u00020\u001cH\u0016J\u001a\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(H\u0003J5\u0010)\u001a\u00020\u001d2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001b2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010(H\u0000¢\u0006\u0002\b+J\u0010\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020\u000bH\u0014J\b\u0010.\u001a\u00020\u001dH\u0016J\u000e\u0010/\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0017R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168VX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bX.¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u001c8@X\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u00060"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/WebViewYouTubePlayer;", "Landroid/webkit/WebView;", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayerBridge$YouTubePlayerBridgeCallbacks;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "listener", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/FullscreenListener;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/FullscreenListener;Landroid/util/AttributeSet;I)V", "_youTubePlayer", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/YouTubePlayerImpl;", "isBackgroundPlaybackEnabled", "", "isBackgroundPlaybackEnabled$core_release", "()Z", "setBackgroundPlaybackEnabled$core_release", "(Z)V", "listeners", "", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/YouTubePlayerListener;", "getListeners", "()Ljava/util/Collection;", "youTubePlayerInitListener", "Lkotlin/Function1;", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayer;", "", "youtubePlayer", "getYoutubePlayer$core_release", "()Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayer;", "addListener", "destroy", "getInstance", "initWebView", "playerOptions", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/options/IFramePlayerOptions;", "videoId", "", "initialize", "initListener", "initialize$core_release", "onWindowVisibilityChanged", "visibility", "onYouTubeIFrameAPIReady", "removeListener", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebViewYouTubePlayer.kt */
public final class WebViewYouTubePlayer extends WebView implements YouTubePlayerBridge.YouTubePlayerBridgeCallbacks {
    private final YouTubePlayerImpl _youTubePlayer;
    private boolean isBackgroundPlaybackEnabled;
    /* access modifiers changed from: private */
    public final FullscreenListener listener;
    private Function1<? super YouTubePlayer, Unit> youTubePlayerInitListener;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WebViewYouTubePlayer(Context context, FullscreenListener fullscreenListener, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, fullscreenListener, (i2 & 4) != 0 ? null : attributeSet, (i2 & 8) != 0 ? 0 : i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebViewYouTubePlayer(Context context, FullscreenListener fullscreenListener, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fullscreenListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = fullscreenListener;
        this._youTubePlayer = new YouTubePlayerImpl(this);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WebViewYouTubePlayer(Context context) {
        this(context, FakeWebViewYouTubeListener.INSTANCE, (AttributeSet) null, 0, 12, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final YouTubePlayer getYoutubePlayer$core_release() {
        return this._youTubePlayer;
    }

    public final boolean isBackgroundPlaybackEnabled$core_release() {
        return this.isBackgroundPlaybackEnabled;
    }

    public final void setBackgroundPlaybackEnabled$core_release(boolean z) {
        this.isBackgroundPlaybackEnabled = z;
    }

    public final void initialize$core_release(Function1<? super YouTubePlayer, Unit> function1, IFramePlayerOptions iFramePlayerOptions, String str) {
        Intrinsics.checkNotNullParameter(function1, "initListener");
        this.youTubePlayerInitListener = function1;
        if (iFramePlayerOptions == null) {
            iFramePlayerOptions = IFramePlayerOptions.Companion.getDefault();
        }
        initWebView(iFramePlayerOptions, str);
    }

    public Collection<YouTubePlayerListener> getListeners() {
        return CollectionsKt.toSet(this._youTubePlayer.getListeners());
    }

    public YouTubePlayer getInstance() {
        return this._youTubePlayer;
    }

    public void onYouTubeIFrameAPIReady() {
        Function1<? super YouTubePlayer, Unit> function1 = this.youTubePlayerInitListener;
        if (function1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("youTubePlayerInitListener");
            function1 = null;
        }
        function1.invoke(this._youTubePlayer);
    }

    public final boolean addListener(YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return this._youTubePlayer.getListeners().add(youTubePlayerListener);
    }

    public final boolean removeListener(YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return this._youTubePlayer.getListeners().remove(youTubePlayerListener);
    }

    public void destroy() {
        this._youTubePlayer.release();
        super.destroy();
    }

    private final void initWebView(IFramePlayerOptions iFramePlayerOptions, String str) {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setCacheMode(-1);
        addJavascriptInterface(new YouTubePlayerBridge(this), "YouTubePlayerBridge");
        InputStream openRawResource = getResources().openRawResource(R.raw.ayp_youtube_player);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "resources.openRawResourc…R.raw.ayp_youtube_player)");
        loadDataWithBaseURL(iFramePlayerOptions.getOrigin$core_release(), StringsKt.replace$default(StringsKt.replace$default(WebViewYouTubePlayerKt.readHTMLFromUTF8File(openRawResource), "<<injectedVideoId>>", str != null ? "'" + str + Chars.QUOTE : "undefined", false, 4, (Object) null), "<<injectedPlayerVars>>", iFramePlayerOptions.toString(), false, 4, (Object) null), "text/html", "utf-8", (String) null);
        setWebChromeClient(new WebViewYouTubePlayer$initWebView$2(this));
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        if (!this.isBackgroundPlaybackEnabled || !(i == 8 || i == 4)) {
            super.onWindowVisibilityChanged(i);
        }
    }
}
