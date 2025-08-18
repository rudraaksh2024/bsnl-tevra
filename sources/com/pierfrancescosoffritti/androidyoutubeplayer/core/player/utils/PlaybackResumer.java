package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0006\u0010\u0012\u001a\u00020\rJ\u0006\u0010\u0013\u001a\u00020\rJ\u0018\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\bH\u0016J\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/utils/PlaybackResumer;", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/AbstractYouTubePlayerListener;", "()V", "canLoad", "", "currentSecond", "", "currentVideoId", "", "error", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlayerError;", "isPlaying", "onCurrentSecond", "", "youTubePlayer", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayer;", "second", "onError", "onLifecycleResume", "onLifecycleStop", "onStateChange", "state", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlayerState;", "onVideoId", "videoId", "resume", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PlaybackResumer.kt */
public final class PlaybackResumer extends AbstractYouTubePlayerListener {
    private boolean canLoad;
    private float currentSecond;
    private String currentVideoId;
    private PlayerConstants.PlayerError error;
    private boolean isPlaying;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PlaybackResumer.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants$PlayerState[] r0 = com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants$PlayerState r1 = com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerState.ENDED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants$PlayerState r1 = com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerState.PAUSED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants$PlayerState r1 = com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerState.PLAYING     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.PlaybackResumer.WhenMappings.<clinit>():void");
        }
    }

    public final void resume(YouTubePlayer youTubePlayer) {
        Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
        String str = this.currentVideoId;
        if (str != null) {
            if (this.isPlaying && this.error == PlayerConstants.PlayerError.HTML_5_PLAYER) {
                YouTubePlayerUtils.loadOrCueVideo(youTubePlayer, this.canLoad, str, this.currentSecond);
            } else if (!this.isPlaying && this.error == PlayerConstants.PlayerError.HTML_5_PLAYER) {
                youTubePlayer.cueVideo(str, this.currentSecond);
            }
            this.error = null;
        }
    }

    public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState playerState) {
        Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
        Intrinsics.checkNotNullParameter(playerState, "state");
        int i = WhenMappings.$EnumSwitchMapping$0[playerState.ordinal()];
        if (i == 1 || i == 2) {
            this.isPlaying = false;
        } else if (i == 3) {
            this.isPlaying = true;
        }
    }

    public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError playerError) {
        Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
        Intrinsics.checkNotNullParameter(playerError, "error");
        if (playerError == PlayerConstants.PlayerError.HTML_5_PLAYER) {
            this.error = playerError;
        }
    }

    public void onCurrentSecond(YouTubePlayer youTubePlayer, float f) {
        Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
        this.currentSecond = f;
    }

    public void onVideoId(YouTubePlayer youTubePlayer, String str) {
        Intrinsics.checkNotNullParameter(youTubePlayer, "youTubePlayer");
        Intrinsics.checkNotNullParameter(str, "videoId");
        this.currentVideoId = str;
    }

    public final void onLifecycleResume() {
        this.canLoad = true;
    }

    public final void onLifecycleStop() {
        this.canLoad = false;
    }
}
