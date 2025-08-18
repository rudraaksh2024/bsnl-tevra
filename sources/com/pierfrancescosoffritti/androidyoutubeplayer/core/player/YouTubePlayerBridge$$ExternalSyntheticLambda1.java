package com.pierfrancescosoffritti.androidyoutubeplayer.core.player;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class YouTubePlayerBridge$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ YouTubePlayerBridge f$0;
    public final /* synthetic */ PlayerConstants.PlayerError f$1;

    public /* synthetic */ YouTubePlayerBridge$$ExternalSyntheticLambda1(YouTubePlayerBridge youTubePlayerBridge, PlayerConstants.PlayerError playerError) {
        this.f$0 = youTubePlayerBridge;
        this.f$1 = playerError;
    }

    public final void run() {
        YouTubePlayerBridge.sendError$lambda$10(this.f$0, this.f$1);
    }
}
