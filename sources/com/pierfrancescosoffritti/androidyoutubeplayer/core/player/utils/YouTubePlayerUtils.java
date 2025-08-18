package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils;

import androidx.lifecycle.Lifecycle;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000¨\u0006\u000b"}, d2 = {"loadOrCueVideo", "", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/YouTubePlayer;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "videoId", "", "startSeconds", "", "canLoad", "", "core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: YouTubePlayerUtils.kt */
public final class YouTubePlayerUtils {
    public static final void loadOrCueVideo(YouTubePlayer youTubePlayer, Lifecycle lifecycle, String str, float f) {
        Intrinsics.checkNotNullParameter(youTubePlayer, "<this>");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(str, "videoId");
        loadOrCueVideo(youTubePlayer, lifecycle.getCurrentState() == Lifecycle.State.RESUMED, str, f);
    }

    public static final /* synthetic */ void loadOrCueVideo(YouTubePlayer youTubePlayer, boolean z, String str, float f) {
        Intrinsics.checkNotNullParameter(youTubePlayer, "<this>");
        Intrinsics.checkNotNullParameter(str, "videoId");
        if (z) {
            youTubePlayer.loadVideo(str, f);
        } else {
            youTubePlayer.cueVideo(str, f);
        }
    }
}
