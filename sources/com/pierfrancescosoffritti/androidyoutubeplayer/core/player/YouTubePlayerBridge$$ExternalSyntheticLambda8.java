package com.pierfrancescosoffritti.androidyoutubeplayer.core.player;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class YouTubePlayerBridge$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ YouTubePlayerBridge f$0;
    public final /* synthetic */ PlayerConstants.PlaybackRate f$1;

    public /* synthetic */ YouTubePlayerBridge$$ExternalSyntheticLambda8(YouTubePlayerBridge youTubePlayerBridge, PlayerConstants.PlaybackRate playbackRate) {
        this.f$0 = youTubePlayerBridge;
        this.f$1 = playbackRate;
    }

    public final void run() {
        YouTubePlayerBridge.sendPlaybackRateChange$lambda$8(this.f$0, this.f$1);
    }
}
