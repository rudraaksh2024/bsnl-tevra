package com.pierfrancescosoffritti.androidyoutubeplayer.core.player;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class YouTubePlayerBridge$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ YouTubePlayerBridge f$0;
    public final /* synthetic */ PlayerConstants.PlaybackQuality f$1;

    public /* synthetic */ YouTubePlayerBridge$$ExternalSyntheticLambda2(YouTubePlayerBridge youTubePlayerBridge, PlayerConstants.PlaybackQuality playbackQuality) {
        this.f$0 = youTubePlayerBridge;
        this.f$1 = playbackQuality;
    }

    public final void run() {
        YouTubePlayerBridge.sendPlaybackQualityChange$lambda$6(this.f$0, this.f$1);
    }
}
