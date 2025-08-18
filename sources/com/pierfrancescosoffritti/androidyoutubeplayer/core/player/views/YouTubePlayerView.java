package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.google.firebase.messaging.Constants;
import com.pierfrancescosoffritti.androidyoutubeplayer.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b*\u0001\u0018\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0014J\u000e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\rJ\u000e\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$J\u0010\u0010%\u001a\u00020&2\b\b\u0001\u0010'\u001a\u00020\nJ\u000e\u0010(\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010(\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*J\u0016\u0010(\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\rJ\u001e\u0010(\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\r2\u0006\u0010)\u001a\u00020*J(\u0010(\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\r2\u0006\u0010)\u001a\u00020*2\b\u0010,\u001a\u0004\u0018\u00010-J\u0006\u0010.\u001a\u00020 J\b\u0010/\u001a\u00020 H\u0002J\u0018\u00100\u001a\u00020 2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204H\u0016J\b\u00105\u001a\u00020 H\u0002J\u0006\u00106\u001a\u00020 J\u000e\u00107\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0014J\u000e\u00108\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020&J\u0018\u0010;\u001a\u00020 2\u0006\u0010<\u001a\u00020\n2\u0006\u0010=\u001a\u00020\nH\u0002J\u0006\u0010>\u001a\u00020 R\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0004\n\u0002\u0010\u0019¨\u0006?"}, d2 = {"Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/YouTubePlayerView;", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/SixteenByNineFrameLayout;", "Landroidx/lifecycle/LifecycleEventObserver;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "enableAutomaticInitialization", "", "getEnableAutomaticInitialization", "()Z", "setEnableAutomaticInitialization", "(Z)V", "fullscreenListeners", "", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/FullscreenListener;", "legacyTubePlayerView", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/LegacyYouTubePlayerView;", "webViewFullscreenListener", "com/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/YouTubePlayerView$webViewFullscreenListener$1", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/YouTubePlayerView$webViewFullscreenListener$1;", "addFullscreenListener", "fullscreenListener", "addYouTubePlayerListener", "youTubePlayerListener", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/YouTubePlayerListener;", "enableBackgroundPlayback", "", "enable", "getYouTubePlayerWhenReady", "youTubePlayerCallback", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/listeners/YouTubePlayerCallback;", "inflateCustomPlayerUi", "Landroid/view/View;", "layoutId", "initialize", "playerOptions", "Lcom/pierfrancescosoffritti/androidyoutubeplayer/core/player/options/IFramePlayerOptions;", "handleNetworkEvents", "videoId", "", "matchParent", "onResume", "onStateChanged", "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "onStop", "release", "removeFullscreenListener", "removeYouTubePlayerListener", "setCustomPlayerUi", "view", "setLayoutParams", "targetWidth", "targetHeight", "wrapContent", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: YouTubePlayerView.kt */
public final class YouTubePlayerView extends SixteenByNineFrameLayout implements LifecycleEventObserver {
    private boolean enableAutomaticInitialization;
    /* access modifiers changed from: private */
    public final List<FullscreenListener> fullscreenListeners;
    /* access modifiers changed from: private */
    public final LegacyYouTubePlayerView legacyTubePlayerView;
    private final YouTubePlayerView$webViewFullscreenListener$1 webViewFullscreenListener;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: YouTubePlayerView.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|17) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                androidx.lifecycle.Lifecycle$Event[] r0 = androidx.lifecycle.Lifecycle.Event.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_RESUME     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_STOP     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_DESTROY     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_CREATE     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_START     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_PAUSE     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                androidx.lifecycle.Lifecycle$Event r1 = androidx.lifecycle.Lifecycle.Event.ON_ANY     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ YouTubePlayerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public YouTubePlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.fullscreenListeners = new ArrayList();
        YouTubePlayerView$webViewFullscreenListener$1 youTubePlayerView$webViewFullscreenListener$1 = new YouTubePlayerView$webViewFullscreenListener$1(this);
        this.webViewFullscreenListener = youTubePlayerView$webViewFullscreenListener$1;
        LegacyYouTubePlayerView legacyYouTubePlayerView = new LegacyYouTubePlayerView(context, youTubePlayerView$webViewFullscreenListener$1, (AttributeSet) null, 0, 12, (DefaultConstructorMarker) null);
        this.legacyTubePlayerView = legacyYouTubePlayerView;
        addView(legacyYouTubePlayerView, YouTubePlayerViewKt.getMatchParent());
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.YouTubePlayerView, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme.obtainStyl….YouTubePlayerView, 0, 0)");
        this.enableAutomaticInitialization = obtainStyledAttributes.getBoolean(R.styleable.YouTubePlayerView_enableAutomaticInitialization, true);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.YouTubePlayerView_autoPlay, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.YouTubePlayerView_handleNetworkEvents, true);
        String string = obtainStyledAttributes.getString(R.styleable.YouTubePlayerView_videoId);
        obtainStyledAttributes.recycle();
        if (!z || string != null) {
            YouTubePlayerView$youTubePlayerListener$1 youTubePlayerView$youTubePlayerListener$1 = new YouTubePlayerView$youTubePlayerListener$1(string, this, z);
            if (this.enableAutomaticInitialization) {
                legacyYouTubePlayerView.initialize(youTubePlayerView$youTubePlayerListener$1, z2, IFramePlayerOptions.Companion.getDefault(), string);
                return;
            }
            return;
        }
        throw new IllegalStateException("YouTubePlayerView: videoId is not set but autoPlay is set to true. This combination is not allowed.");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public YouTubePlayerView(Context context) {
        this(context, (AttributeSet) null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public YouTubePlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ YouTubePlayerView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final boolean getEnableAutomaticInitialization() {
        return this.enableAutomaticInitialization;
    }

    public final void setEnableAutomaticInitialization(boolean z) {
        this.enableAutomaticInitialization = z;
    }

    public final void initialize(YouTubePlayerListener youTubePlayerListener, boolean z, IFramePlayerOptions iFramePlayerOptions, String str) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        Intrinsics.checkNotNullParameter(iFramePlayerOptions, "playerOptions");
        if (!this.enableAutomaticInitialization) {
            this.legacyTubePlayerView.initialize(youTubePlayerListener, z, iFramePlayerOptions, str);
            return;
        }
        throw new IllegalStateException("YouTubePlayerView: If you want to initialize this view manually, you need to set 'enableAutomaticInitialization' to false.");
    }

    public final void initialize(YouTubePlayerListener youTubePlayerListener, boolean z, IFramePlayerOptions iFramePlayerOptions) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        Intrinsics.checkNotNullParameter(iFramePlayerOptions, "playerOptions");
        if (!this.enableAutomaticInitialization) {
            this.legacyTubePlayerView.initialize(youTubePlayerListener, z, iFramePlayerOptions, (String) null);
            return;
        }
        throw new IllegalStateException("YouTubePlayerView: If you want to initialize this view manually, you need to set 'enableAutomaticInitialization' to false.");
    }

    public final void initialize(YouTubePlayerListener youTubePlayerListener, boolean z) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        if (!this.enableAutomaticInitialization) {
            this.legacyTubePlayerView.initialize(youTubePlayerListener, z, IFramePlayerOptions.Companion.getDefault());
            return;
        }
        throw new IllegalStateException("YouTubePlayerView: If you want to initialize this view manually, you need to set 'enableAutomaticInitialization' to false.");
    }

    public final void initialize(YouTubePlayerListener youTubePlayerListener, IFramePlayerOptions iFramePlayerOptions) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        Intrinsics.checkNotNullParameter(iFramePlayerOptions, "playerOptions");
        if (!this.enableAutomaticInitialization) {
            this.legacyTubePlayerView.initialize(youTubePlayerListener, true, iFramePlayerOptions);
            return;
        }
        throw new IllegalStateException("YouTubePlayerView: If you want to initialize this view manually, you need to set 'enableAutomaticInitialization' to false.");
    }

    public final void initialize(YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        if (!this.enableAutomaticInitialization) {
            this.legacyTubePlayerView.initialize(youTubePlayerListener, true);
            return;
        }
        throw new IllegalStateException("YouTubePlayerView: If you want to initialize this view manually, you need to set 'enableAutomaticInitialization' to false.");
    }

    public final void getYouTubePlayerWhenReady(YouTubePlayerCallback youTubePlayerCallback) {
        Intrinsics.checkNotNullParameter(youTubePlayerCallback, "youTubePlayerCallback");
        this.legacyTubePlayerView.getYouTubePlayerWhenReady(youTubePlayerCallback);
    }

    public final View inflateCustomPlayerUi(int i) {
        return this.legacyTubePlayerView.inflateCustomPlayerUi(i);
    }

    public final void setCustomPlayerUi(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.legacyTubePlayerView.setCustomPlayerUi(view);
    }

    public final void enableBackgroundPlayback(boolean z) {
        this.legacyTubePlayerView.enableBackgroundPlayback(z);
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, Constants.ScionAnalytics.PARAM_SOURCE);
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i == 1) {
            onResume();
        } else if (i == 2) {
            onStop();
        } else if (i == 3) {
            release();
        }
    }

    public final void release() {
        this.legacyTubePlayerView.release();
    }

    private final void onResume() {
        this.legacyTubePlayerView.onResume$core_release();
    }

    private final void onStop() {
        this.legacyTubePlayerView.onStop$core_release();
    }

    public final boolean addYouTubePlayerListener(YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        return this.legacyTubePlayerView.getWebViewYouTubePlayer$core_release().addListener(youTubePlayerListener);
    }

    public final boolean removeYouTubePlayerListener(YouTubePlayerListener youTubePlayerListener) {
        Intrinsics.checkNotNullParameter(youTubePlayerListener, "youTubePlayerListener");
        return this.legacyTubePlayerView.getWebViewYouTubePlayer$core_release().removeListener(youTubePlayerListener);
    }

    public final boolean addFullscreenListener(FullscreenListener fullscreenListener) {
        Intrinsics.checkNotNullParameter(fullscreenListener, "fullscreenListener");
        return this.fullscreenListeners.add(fullscreenListener);
    }

    public final boolean removeFullscreenListener(FullscreenListener fullscreenListener) {
        Intrinsics.checkNotNullParameter(fullscreenListener, "fullscreenListener");
        return this.fullscreenListeners.remove(fullscreenListener);
    }

    public final void matchParent() {
        setLayoutParams(-1, -1);
    }

    public final void wrapContent() {
        setLayoutParams(-1, -2);
    }

    private final void setLayoutParams(int i, int i2) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        setLayoutParams(layoutParams);
    }
}
