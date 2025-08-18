package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstantsKt;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.logging.log4j.util.Chars;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\b\u0010\u0018\u001a\u00020\u0010H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\u0006\u0010\u001e\u001a\u00020\u0010J\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\u0016J\u0010\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0014H\u0016J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\rH\u0016J\u0010\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\rH\u0016J\u0010\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u001cH\u0016J\b\u0010+\u001a\u00020\u0010H\u0016J\b\u0010,\u001a\u00020\u0010H\u0016J-\u0010-\u001a\u00020\u0010*\u00020\u00032\u0006\u0010.\u001a\u00020\u00122\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020100\"\u000201H\u0002¢\u0006\u0002\u00102R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/YouTubePlayerImpl;", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayer;", "webView", "Landroid/webkit/WebView;", "(Landroid/webkit/WebView;)V", "listeners", "", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/YouTubePlayerListener;", "getListeners", "()Ljava/util/Set;", "mainThread", "Landroid/os/Handler;", "addListener", "", "listener", "cueVideo", "", "videoId", "", "startSeconds", "", "loadVideo", "mute", "nextVideo", "pause", "play", "playVideoAt", "index", "", "previousVideo", "release", "removeListener", "seekTo", "time", "setLoop", "loop", "setPlaybackRate", "playbackRate", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlaybackRate;", "setShuffle", "shuffle", "setVolume", "volumePercent", "toggleFullscreen", "unMute", "invoke", "function", "args", "", "", "(Landroid/webkit/WebView;Ljava/lang/String;[Ljava/lang/Object;)V", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebViewYouTubePlayer.kt */
final class YouTubePlayerImpl implements YouTubePlayer {
    private final Set<YouTubePlayerListener> listeners = new LinkedHashSet();
    private final Handler mainThread = new Handler(Looper.getMainLooper());
    private final WebView webView;

    public YouTubePlayerImpl(WebView webView2) {
        Intrinsics.checkNotNullParameter(webView2, "webView");
        this.webView = webView2;
    }

    public final Set<YouTubePlayerListener> getListeners() {
        return this.listeners;
    }

    public void loadVideo(String str, float f) {
        Intrinsics.checkNotNullParameter(str, "videoId");
        invoke(this.webView, "loadVideo", str, Float.valueOf(f));
    }

    public void cueVideo(String str, float f) {
        Intrinsics.checkNotNullParameter(str, "videoId");
        invoke(this.webView, "cueVideo", str, Float.valueOf(f));
    }

    public void play() {
        invoke(this.webView, "playVideo", new Object[0]);
    }

    public void pause() {
        invoke(this.webView, "pauseVideo", new Object[0]);
    }

    public void nextVideo() {
        invoke(this.webView, "nextVideo", new Object[0]);
    }

    public void previousVideo() {
        invoke(this.webView, "previousVideo", new Object[0]);
    }

    public void playVideoAt(int i) {
        invoke(this.webView, "playVideoAt", Integer.valueOf(i));
    }

    public void setLoop(boolean z) {
        invoke(this.webView, "setLoop", Boolean.valueOf(z));
    }

    public void setShuffle(boolean z) {
        invoke(this.webView, "setShuffle", Boolean.valueOf(z));
    }

    public void mute() {
        invoke(this.webView, "mute", new Object[0]);
    }

    public void unMute() {
        invoke(this.webView, "unMute", new Object[0]);
    }

    public void setVolume(int i) {
        if (i >= 0 && i < 101) {
            invoke(this.webView, "setVolume", Integer.valueOf(i));
            return;
        }
        throw new IllegalArgumentException("Volume must be between 0 and 100".toString());
    }

    public void seekTo(float f) {
        invoke(this.webView, "seekTo", Float.valueOf(f));
    }

    public void setPlaybackRate(PlayerConstants.PlaybackRate playbackRate) {
        Intrinsics.checkNotNullParameter(playbackRate, "playbackRate");
        invoke(this.webView, "setPlaybackRate", Float.valueOf(PlayerConstantsKt.toFloat(playbackRate)));
    }

    public void toggleFullscreen() {
        invoke(this.webView, "toggleFullscreen", new Object[0]);
    }

    public boolean addListener(YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return this.listeners.add(youTubePlayerListener);
    }

    public boolean removeListener(YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return this.listeners.remove(youTubePlayerListener);
    }

    public final void release() {
        this.listeners.clear();
        this.mainThread.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$2(WebView webView2, String str, List list) {
        Intrinsics.checkNotNullParameter(webView2, "$this_invoke");
        Intrinsics.checkNotNullParameter(str, "$function");
        Intrinsics.checkNotNullParameter(list, "$stringArgs");
        webView2.loadUrl("javascript:" + str + '(' + CollectionsKt.joinToString$default(list, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + ')');
    }

    private final void invoke(WebView webView2, String str, Object... objArr) {
        String str2;
        Collection arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            if (obj instanceof String) {
                str2 = "'" + obj + Chars.QUOTE;
            } else {
                str2 = obj.toString();
            }
            arrayList.add(str2);
        }
        this.mainThread.post(new YouTubePlayerImpl$$ExternalSyntheticLambda0(webView2, str, (List) arrayList));
    }
}
