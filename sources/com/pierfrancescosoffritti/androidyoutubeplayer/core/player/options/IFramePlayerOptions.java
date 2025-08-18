package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \n2\u00020\u0001:\u0002\t\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007J\b\u0010\b\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/options/IFramePlayerOptions;", "", "playerOptions", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getOrigin", "", "getOrigin$core_release", "toString", "Builder", "Companion", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: IFramePlayerOptions.kt */
public final class IFramePlayerOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */

    /* renamed from: default  reason: not valid java name */
    public static final IFramePlayerOptions f0default = new Builder().controls(1).build();
    private final JSONObject playerOptions;

    public /* synthetic */ IFramePlayerOptions(JSONObject jSONObject, DefaultConstructorMarker defaultConstructorMarker) {
        this(jSONObject);
    }

    private IFramePlayerOptions(JSONObject jSONObject) {
        this.playerOptions = jSONObject;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/options/IFramePlayerOptions$Companion;", "", "()V", "default", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/options/IFramePlayerOptions;", "getDefault", "()Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/options/IFramePlayerOptions;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: IFramePlayerOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final IFramePlayerOptions getDefault() {
            return IFramePlayerOptions.f0default;
        }
    }

    public String toString() {
        String jSONObject = this.playerOptions.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "playerOptions.toString()");
        return jSONObject;
    }

    public final String getOrigin$core_release() {
        String string = this.playerOptions.getString(Builder.ORIGIN);
        Intrinsics.checkNotNullExpressionValue(string, "playerOptions.getString(Builder.ORIGIN)");
        return string;
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\nJ\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\nJ\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\nJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\bJ\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\bJ\u0010\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\nH\u0007J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\bJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\nJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/options/IFramePlayerOptions$Builder;", "", "()V", "builderOptions", "Lorg/json/JSONObject;", "addInt", "", "key", "", "value", "", "addString", "autoplay", "controls", "build", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/options/IFramePlayerOptions;", "ccLoadPolicy", "end", "endSeconds", "fullscreen", "fs", "ivLoadPolicy", "langPref", "languageCode", "list", "listType", "modestBranding", "mute", "origin", "rel", "start", "startSeconds", "Companion", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: IFramePlayerOptions.kt */
    public static final class Builder {
        private static final String AUTO_PLAY = "autoplay";
        private static final String CC_LANG_PREF = "cc_lang_pref";
        private static final String CC_LOAD_POLICY = "cc_load_policy";
        private static final String CONTROLS = "controls";
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private static final String ENABLE_JS_API = "enablejsapi";
        private static final String END = "end";
        private static final String FS = "fs";
        private static final String IV_LOAD_POLICY = "iv_load_policy";
        private static final String LIST = "list";
        private static final String LIST_TYPE = "listType";
        private static final String MUTE = "mute";
        public static final String ORIGIN = "origin";
        private static final String REL = "rel";
        private static final String START = "start";
        private final JSONObject builderOptions = new JSONObject();

        @Deprecated(message = "Deprecated and will have no effect")
        public final Builder modestBranding(int i) {
            return this;
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/options/IFramePlayerOptions$Builder$Companion;", "", "()V", "AUTO_PLAY", "", "CC_LANG_PREF", "CC_LOAD_POLICY", "CONTROLS", "ENABLE_JS_API", "END", "FS", "IV_LOAD_POLICY", "LIST", "LIST_TYPE", "MUTE", "ORIGIN", "REL", "START", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: IFramePlayerOptions.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }

        public Builder() {
            addInt(AUTO_PLAY, 0);
            addInt(MUTE, 0);
            addInt(CONTROLS, 0);
            addInt(ENABLE_JS_API, 1);
            addInt(FS, 0);
            addString(ORIGIN, "https://www.youtube.com");
            addInt(REL, 0);
            addInt(IV_LOAD_POLICY, 3);
            addInt(CC_LOAD_POLICY, 0);
        }

        public final IFramePlayerOptions build() {
            return new IFramePlayerOptions(this.builderOptions, (DefaultConstructorMarker) null);
        }

        public final Builder controls(int i) {
            addInt(CONTROLS, i);
            return this;
        }

        public final Builder autoplay(int i) {
            addInt(AUTO_PLAY, i);
            return this;
        }

        public final Builder mute(int i) {
            addInt(MUTE, i);
            return this;
        }

        public final Builder rel(int i) {
            addInt(REL, i);
            return this;
        }

        public final Builder ivLoadPolicy(int i) {
            addInt(IV_LOAD_POLICY, i);
            return this;
        }

        public final Builder langPref(String str) {
            Intrinsics.checkNotNullParameter(str, "languageCode");
            addString(CC_LANG_PREF, str);
            return this;
        }

        public final Builder ccLoadPolicy(int i) {
            addInt(CC_LOAD_POLICY, i);
            return this;
        }

        public final Builder origin(String str) {
            Intrinsics.checkNotNullParameter(str, ORIGIN);
            addString(ORIGIN, str);
            return this;
        }

        public final Builder list(String str) {
            Intrinsics.checkNotNullParameter(str, "list");
            addString("list", str);
            return this;
        }

        public final Builder listType(String str) {
            Intrinsics.checkNotNullParameter(str, LIST_TYPE);
            addString(LIST_TYPE, str);
            return this;
        }

        public final Builder fullscreen(int i) {
            addInt(FS, i);
            return this;
        }

        public final Builder start(int i) {
            addInt(START, i);
            return this;
        }

        public final Builder end(int i) {
            addInt(END, i);
            return this;
        }

        private final void addString(String str, String str2) {
            try {
                this.builderOptions.put(str, str2);
            } catch (JSONException unused) {
                throw new RuntimeException("Illegal JSON value " + str + ": " + str2);
            }
        }

        private final void addInt(String str, int i) {
            try {
                this.builderOptions.put(str, i);
            } catch (JSONException unused) {
                throw new RuntimeException("Illegal JSON value " + str + ": " + i);
            }
        }
    }
}
