package com.pierfrancescosoffritti.androidyoutubeplayer.core.player;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.exifinterface.media.ExifInterface;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 $2\u00020\u0001:\u0002$%B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\nH\u0007J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\nH\u0007J\b\u0010\u001a\u001a\u00020\u0015H\u0007J\u0010\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\nH\u0007J\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\nH\u0007J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\nH\u0007J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\nH\u0007J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\nH\u0007J\b\u0010#\u001a\u00020\u0015H\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayerBridge;", "", "youTubePlayerOwner", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayerBridge$YouTubePlayerBridgeCallbacks;", "(Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayerBridge$YouTubePlayerBridgeCallbacks;)V", "mainThreadHandler", "Landroid/os/Handler;", "parsePlaybackQuality", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlaybackQuality;", "quality", "", "parsePlaybackRate", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlaybackRate;", "rate", "parsePlayerError", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlayerError;", "error", "parsePlayerState", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlayerState;", "state", "sendApiChange", "", "sendError", "", "sendPlaybackQualityChange", "sendPlaybackRateChange", "sendReady", "sendStateChange", "sendVideoCurrentTime", "seconds", "sendVideoDuration", "sendVideoId", "videoId", "sendVideoLoadedFraction", "fraction", "sendYouTubeIFrameAPIReady", "Companion", "YouTubePlayerBridgeCallbacks", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: YouTubePlayerBridge.kt */
public final class YouTubePlayerBridge {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String ERROR_HTML_5_PLAYER = "5";
    private static final String ERROR_INVALID_PARAMETER_IN_REQUEST = "2";
    private static final String ERROR_VIDEO_NOT_FOUND = "100";
    private static final String ERROR_VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER1 = "101";
    private static final String ERROR_VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER2 = "150";
    private static final String QUALITY_DEFAULT = "default";
    private static final String QUALITY_HD1080 = "hd1080";
    private static final String QUALITY_HD720 = "hd720";
    private static final String QUALITY_HIGH_RES = "highres";
    private static final String QUALITY_LARGE = "large";
    private static final String QUALITY_MEDIUM = "medium";
    private static final String QUALITY_SMALL = "small";
    private static final String RATE_0_25 = "0.25";
    private static final String RATE_0_5 = "0.5";
    private static final String RATE_0_75 = "0.75";
    private static final String RATE_1 = "1";
    private static final String RATE_1_25 = "1.25";
    private static final String RATE_1_5 = "1.5";
    private static final String RATE_1_75 = "1.75";
    private static final String RATE_2 = "2";
    private static final String STATE_BUFFERING = "BUFFERING";
    private static final String STATE_CUED = "CUED";
    private static final String STATE_ENDED = "ENDED";
    private static final String STATE_PAUSED = "PAUSED";
    private static final String STATE_PLAYING = "PLAYING";
    private static final String STATE_UNSTARTED = "UNSTARTED";
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private final YouTubePlayerBridgeCallbacks youTubePlayerOwner;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayerBridge$YouTubePlayerBridgeCallbacks;", "", "listeners", "", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/YouTubePlayerListener;", "getListeners", "()Ljava/util/Collection;", "getInstance", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayer;", "onYouTubeIFrameAPIReady", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: YouTubePlayerBridge.kt */
    public interface YouTubePlayerBridgeCallbacks {
        YouTubePlayer getInstance();

        Collection<YouTubePlayerListener> getListeners();

        void onYouTubeIFrameAPIReady();
    }

    public YouTubePlayerBridge(YouTubePlayerBridgeCallbacks youTubePlayerBridgeCallbacks) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridgeCallbacks, "youTubePlayerOwner");
        this.youTubePlayerOwner = youTubePlayerBridgeCallbacks;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001a\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayerBridge$Companion;", "", "()V", "ERROR_HTML_5_PLAYER", "", "ERROR_INVALID_PARAMETER_IN_REQUEST", "ERROR_VIDEO_NOT_FOUND", "ERROR_VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER1", "ERROR_VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER2", "QUALITY_DEFAULT", "QUALITY_HD1080", "QUALITY_HD720", "QUALITY_HIGH_RES", "QUALITY_LARGE", "QUALITY_MEDIUM", "QUALITY_SMALL", "RATE_0_25", "RATE_0_5", "RATE_0_75", "RATE_1", "RATE_1_25", "RATE_1_5", "RATE_1_75", "RATE_2", "STATE_BUFFERING", "STATE_CUED", "STATE_ENDED", "STATE_PAUSED", "STATE_PLAYING", "STATE_UNSTARTED", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: YouTubePlayerBridge.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: private */
    public static final void sendYouTubeIFrameAPIReady$lambda$0(YouTubePlayerBridge youTubePlayerBridge) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        youTubePlayerBridge.youTubePlayerOwner.onYouTubeIFrameAPIReady();
    }

    @JavascriptInterface
    public final boolean sendYouTubeIFrameAPIReady() {
        return this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda7(this));
    }

    @JavascriptInterface
    public final boolean sendReady() {
        return this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: private */
    public static final void sendReady$lambda$2(YouTubePlayerBridge youTubePlayerBridge) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        for (YouTubePlayerListener onReady : youTubePlayerBridge.youTubePlayerOwner.getListeners()) {
            onReady.onReady(youTubePlayerBridge.youTubePlayerOwner.getInstance());
        }
    }

    @JavascriptInterface
    public final void sendStateChange(String str) {
        Intrinsics.checkNotNullParameter(str, "state");
        this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda10(this, parsePlayerState(str)));
    }

    /* access modifiers changed from: private */
    public static final void sendStateChange$lambda$4(YouTubePlayerBridge youTubePlayerBridge, PlayerConstants.PlayerState playerState) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        Intrinsics.checkNotNullParameter(playerState, "$playerState");
        for (YouTubePlayerListener onStateChange : youTubePlayerBridge.youTubePlayerOwner.getListeners()) {
            onStateChange.onStateChange(youTubePlayerBridge.youTubePlayerOwner.getInstance(), playerState);
        }
    }

    @JavascriptInterface
    public final void sendPlaybackQualityChange(String str) {
        Intrinsics.checkNotNullParameter(str, "quality");
        this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda2(this, parsePlaybackQuality(str)));
    }

    /* access modifiers changed from: private */
    public static final void sendPlaybackQualityChange$lambda$6(YouTubePlayerBridge youTubePlayerBridge, PlayerConstants.PlaybackQuality playbackQuality) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        Intrinsics.checkNotNullParameter(playbackQuality, "$playbackQuality");
        for (YouTubePlayerListener onPlaybackQualityChange : youTubePlayerBridge.youTubePlayerOwner.getListeners()) {
            onPlaybackQualityChange.onPlaybackQualityChange(youTubePlayerBridge.youTubePlayerOwner.getInstance(), playbackQuality);
        }
    }

    @JavascriptInterface
    public final void sendPlaybackRateChange(String str) {
        Intrinsics.checkNotNullParameter(str, "rate");
        this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda8(this, parsePlaybackRate(str)));
    }

    /* access modifiers changed from: private */
    public static final void sendPlaybackRateChange$lambda$8(YouTubePlayerBridge youTubePlayerBridge, PlayerConstants.PlaybackRate playbackRate) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        Intrinsics.checkNotNullParameter(playbackRate, "$playbackRate");
        for (YouTubePlayerListener onPlaybackRateChange : youTubePlayerBridge.youTubePlayerOwner.getListeners()) {
            onPlaybackRateChange.onPlaybackRateChange(youTubePlayerBridge.youTubePlayerOwner.getInstance(), playbackRate);
        }
    }

    @JavascriptInterface
    public final void sendError(String str) {
        Intrinsics.checkNotNullParameter(str, "error");
        this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda1(this, parsePlayerError(str)));
    }

    /* access modifiers changed from: private */
    public static final void sendError$lambda$10(YouTubePlayerBridge youTubePlayerBridge, PlayerConstants.PlayerError playerError) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        Intrinsics.checkNotNullParameter(playerError, "$playerError");
        for (YouTubePlayerListener onError : youTubePlayerBridge.youTubePlayerOwner.getListeners()) {
            onError.onError(youTubePlayerBridge.youTubePlayerOwner.getInstance(), playerError);
        }
    }

    @JavascriptInterface
    public final boolean sendApiChange() {
        return this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: private */
    public static final void sendApiChange$lambda$12(YouTubePlayerBridge youTubePlayerBridge) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        for (YouTubePlayerListener onApiChange : youTubePlayerBridge.youTubePlayerOwner.getListeners()) {
            onApiChange.onApiChange(youTubePlayerBridge.youTubePlayerOwner.getInstance());
        }
    }

    @JavascriptInterface
    public final void sendVideoCurrentTime(String str) {
        Intrinsics.checkNotNullParameter(str, "seconds");
        try {
            this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda5(this, Float.parseFloat(str)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static final void sendVideoCurrentTime$lambda$14(YouTubePlayerBridge youTubePlayerBridge, float f) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        for (YouTubePlayerListener onCurrentSecond : youTubePlayerBridge.youTubePlayerOwner.getListeners()) {
            onCurrentSecond.onCurrentSecond(youTubePlayerBridge.youTubePlayerOwner.getInstance(), f);
        }
    }

    @JavascriptInterface
    public final void sendVideoDuration(String str) {
        Intrinsics.checkNotNullParameter(str, "seconds");
        try {
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda9(this, Float.parseFloat(str)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static final void sendVideoDuration$lambda$16(YouTubePlayerBridge youTubePlayerBridge, float f) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        for (YouTubePlayerListener onVideoDuration : youTubePlayerBridge.youTubePlayerOwner.getListeners()) {
            onVideoDuration.onVideoDuration(youTubePlayerBridge.youTubePlayerOwner.getInstance(), f);
        }
    }

    @JavascriptInterface
    public final void sendVideoLoadedFraction(String str) {
        Intrinsics.checkNotNullParameter(str, "fraction");
        try {
            this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda0(this, Float.parseFloat(str)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static final void sendVideoLoadedFraction$lambda$18(YouTubePlayerBridge youTubePlayerBridge, float f) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        for (YouTubePlayerListener onVideoLoadedFraction : youTubePlayerBridge.youTubePlayerOwner.getListeners()) {
            onVideoLoadedFraction.onVideoLoadedFraction(youTubePlayerBridge.youTubePlayerOwner.getInstance(), f);
        }
    }

    @JavascriptInterface
    public final boolean sendVideoId(String str) {
        Intrinsics.checkNotNullParameter(str, "videoId");
        return this.mainThreadHandler.post(new YouTubePlayerBridge$$ExternalSyntheticLambda6(this, str));
    }

    /* access modifiers changed from: private */
    public static final void sendVideoId$lambda$20(YouTubePlayerBridge youTubePlayerBridge, String str) {
        Intrinsics.checkNotNullParameter(youTubePlayerBridge, "this$0");
        Intrinsics.checkNotNullParameter(str, "$videoId");
        for (YouTubePlayerListener onVideoId : youTubePlayerBridge.youTubePlayerOwner.getListeners()) {
            onVideoId.onVideoId(youTubePlayerBridge.youTubePlayerOwner.getInstance(), str);
        }
    }

    private final PlayerConstants.PlayerState parsePlayerState(String str) {
        if (StringsKt.equals(str, STATE_UNSTARTED, true)) {
            return PlayerConstants.PlayerState.UNSTARTED;
        }
        if (StringsKt.equals(str, STATE_ENDED, true)) {
            return PlayerConstants.PlayerState.ENDED;
        }
        if (StringsKt.equals(str, STATE_PLAYING, true)) {
            return PlayerConstants.PlayerState.PLAYING;
        }
        if (StringsKt.equals(str, STATE_PAUSED, true)) {
            return PlayerConstants.PlayerState.PAUSED;
        }
        if (StringsKt.equals(str, STATE_BUFFERING, true)) {
            return PlayerConstants.PlayerState.BUFFERING;
        }
        if (StringsKt.equals(str, STATE_CUED, true)) {
            return PlayerConstants.PlayerState.VIDEO_CUED;
        }
        return PlayerConstants.PlayerState.UNKNOWN;
    }

    private final PlayerConstants.PlaybackQuality parsePlaybackQuality(String str) {
        if (StringsKt.equals(str, QUALITY_SMALL, true)) {
            return PlayerConstants.PlaybackQuality.SMALL;
        }
        if (StringsKt.equals(str, "medium", true)) {
            return PlayerConstants.PlaybackQuality.MEDIUM;
        }
        if (StringsKt.equals(str, QUALITY_LARGE, true)) {
            return PlayerConstants.PlaybackQuality.LARGE;
        }
        if (StringsKt.equals(str, QUALITY_HD720, true)) {
            return PlayerConstants.PlaybackQuality.HD720;
        }
        if (StringsKt.equals(str, QUALITY_HD1080, true)) {
            return PlayerConstants.PlaybackQuality.HD1080;
        }
        if (StringsKt.equals(str, QUALITY_HIGH_RES, true)) {
            return PlayerConstants.PlaybackQuality.HIGH_RES;
        }
        if (StringsKt.equals(str, QUALITY_DEFAULT, true)) {
            return PlayerConstants.PlaybackQuality.DEFAULT;
        }
        return PlayerConstants.PlaybackQuality.UNKNOWN;
    }

    private final PlayerConstants.PlaybackRate parsePlaybackRate(String str) {
        if (StringsKt.equals(str, RATE_0_25, true)) {
            return PlayerConstants.PlaybackRate.RATE_0_25;
        }
        if (StringsKt.equals(str, RATE_0_5, true)) {
            return PlayerConstants.PlaybackRate.RATE_0_5;
        }
        if (StringsKt.equals(str, RATE_0_75, true)) {
            return PlayerConstants.PlaybackRate.RATE_0_75;
        }
        if (StringsKt.equals(str, RATE_1, true)) {
            return PlayerConstants.PlaybackRate.RATE_1;
        }
        if (StringsKt.equals(str, RATE_1_25, true)) {
            return PlayerConstants.PlaybackRate.RATE_1_25;
        }
        if (StringsKt.equals(str, RATE_1_5, true)) {
            return PlayerConstants.PlaybackRate.RATE_1_5;
        }
        if (StringsKt.equals(str, RATE_1_75, true)) {
            return PlayerConstants.PlaybackRate.RATE_1_75;
        }
        if (StringsKt.equals(str, ExifInterface.GPS_MEASUREMENT_2D, true)) {
            return PlayerConstants.PlaybackRate.RATE_2;
        }
        return PlayerConstants.PlaybackRate.UNKNOWN;
    }

    private final PlayerConstants.PlayerError parsePlayerError(String str) {
        if (StringsKt.equals(str, ExifInterface.GPS_MEASUREMENT_2D, true)) {
            return PlayerConstants.PlayerError.INVALID_PARAMETER_IN_REQUEST;
        }
        if (StringsKt.equals(str, ERROR_HTML_5_PLAYER, true)) {
            return PlayerConstants.PlayerError.HTML_5_PLAYER;
        }
        if (StringsKt.equals(str, ERROR_VIDEO_NOT_FOUND, true)) {
            return PlayerConstants.PlayerError.VIDEO_NOT_FOUND;
        }
        if (StringsKt.equals(str, ERROR_VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER1, true)) {
            return PlayerConstants.PlayerError.VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER;
        }
        if (StringsKt.equals(str, ERROR_VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER2, true)) {
            return PlayerConstants.PlayerError.VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER;
        }
        return PlayerConstants.PlayerError.UNKNOWN;
    }
}
