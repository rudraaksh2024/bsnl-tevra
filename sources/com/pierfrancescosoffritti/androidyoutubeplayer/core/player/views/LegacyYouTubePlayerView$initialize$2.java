package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: LegacyYouTubePlayerView.kt */
final class LegacyYouTubePlayerView$initialize$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ IFramePlayerOptions $playerOptions;
    final /* synthetic */ String $videoId;
    final /* synthetic */ YouTubePlayerListener $youTubePlayerListener;
    final /* synthetic */ LegacyYouTubePlayerView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LegacyYouTubePlayerView$initialize$2(LegacyYouTubePlayerView legacyYouTubePlayerView, IFramePlayerOptions iFramePlayerOptions, String str, YouTubePlayerListener youTubePlayerListener) {
        super(0);
        this.this$0 = legacyYouTubePlayerView;
        this.$playerOptions = iFramePlayerOptions;
        this.$videoId = str;
        this.$youTubePlayerListener = youTubePlayerListener;
    }

    public final void invoke() {
        WebViewYouTubePlayer webViewYouTubePlayer$core_release = this.this$0.getWebViewYouTubePlayer$core_release();
        final YouTubePlayerListener youTubePlayerListener = this.$youTubePlayerListener;
        webViewYouTubePlayer$core_release.initialize$core_release(new Function1<YouTubePlayer, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((YouTubePlayer) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(YouTubePlayer youTubePlayer) {
                Intrinsics.checkNotNullParameter(youTubePlayer, "it");
                youTubePlayer.addListener(youTubePlayerListener);
            }
        }, this.$playerOptions, this.$videoId);
    }
}
