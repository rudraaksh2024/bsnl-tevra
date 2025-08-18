package com.pierfrancescosoffritti.androidyoutubeplayer.core.player;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0007"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants;", "", "()V", "PlaybackQuality", "PlaybackRate", "PlayerError", "PlayerState", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PlayerConstants.kt */
public final class PlayerConstants {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlaybackQuality;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "SMALL", "MEDIUM", "LARGE", "HD720", "HD1080", "HIGH_RES", "DEFAULT", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PlayerConstants.kt */
    public enum PlaybackQuality {
        UNKNOWN,
        SMALL,
        MEDIUM,
        LARGE,
        HD720,
        HD1080,
        HIGH_RES,
        DEFAULT
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlaybackRate;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "RATE_0_25", "RATE_0_5", "RATE_0_75", "RATE_1", "RATE_1_25", "RATE_1_5", "RATE_1_75", "RATE_2", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PlayerConstants.kt */
    public enum PlaybackRate {
        UNKNOWN,
        RATE_0_25,
        RATE_0_5,
        RATE_0_75,
        RATE_1,
        RATE_1_25,
        RATE_1_5,
        RATE_1_75,
        RATE_2
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlayerError;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "INVALID_PARAMETER_IN_REQUEST", "HTML_5_PLAYER", "VIDEO_NOT_FOUND", "VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PlayerConstants.kt */
    public enum PlayerError {
        UNKNOWN,
        INVALID_PARAMETER_IN_REQUEST,
        HTML_5_PLAYER,
        VIDEO_NOT_FOUND,
        VIDEO_NOT_PLAYABLE_IN_EMBEDDED_PLAYER
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/PlayerConstants$PlayerState;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "UNSTARTED", "ENDED", "PLAYING", "PAUSED", "BUFFERING", "VIDEO_CUED", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PlayerConstants.kt */
    public enum PlayerState {
        UNKNOWN,
        UNSTARTED,
        ENDED,
        PLAYING,
        PAUSED,
        BUFFERING,
        VIDEO_CUED
    }
}
