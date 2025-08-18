package com.pierfrancescosoffritti.androidyoutubeplayer.core.player;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class YouTubePlayerBridge$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ YouTubePlayerBridge f$0;
    public final /* synthetic */ float f$1;

    public /* synthetic */ YouTubePlayerBridge$$ExternalSyntheticLambda0(YouTubePlayerBridge youTubePlayerBridge, float f) {
        this.f$0 = youTubePlayerBridge;
        this.f$1 = f;
    }

    public final void run() {
        YouTubePlayerBridge.sendVideoLoadedFraction$lambda$18(this.f$0, this.f$1);
    }
}
