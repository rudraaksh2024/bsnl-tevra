package com.pierfrancescosoffritti.androidyoutubeplayer.core.player;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class YouTubePlayerBridge$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ YouTubePlayerBridge f$0;
    public final /* synthetic */ PlayerConstants.PlayerState f$1;

    public /* synthetic */ YouTubePlayerBridge$$ExternalSyntheticLambda10(YouTubePlayerBridge youTubePlayerBridge, PlayerConstants.PlayerState playerState) {
        this.f$0 = youTubePlayerBridge;
        this.f$1 = playerState;
    }

    public final void run() {
        YouTubePlayerBridge.sendStateChange$lambda$4(this.f$0, this.f$1);
    }
}
