package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/pierfrancescosoffritti/androidyoutubeplayer/core/player/views/WebViewYouTubePlayer$initWebView$2", "Landroid/webkit/WebChromeClient;", "getDefaultVideoPoster", "Landroid/graphics/Bitmap;", "onHideCustomView", "", "onShowCustomView", "view", "Landroid/view/View;", "callback", "Landroid/webkit/WebChromeClient$CustomViewCallback;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebViewYouTubePlayer.kt */
public final class WebViewYouTubePlayer$initWebView$2 extends WebChromeClient {
    final /* synthetic */ WebViewYouTubePlayer this$0;

    WebViewYouTubePlayer$initWebView$2(WebViewYouTubePlayer webViewYouTubePlayer) {
        this.this$0 = webViewYouTubePlayer;
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(customViewCallback, "callback");
        super.onShowCustomView(view, customViewCallback);
        this.this$0.listener.onEnterFullscreen(view, new WebViewYouTubePlayer$initWebView$2$onShowCustomView$1(customViewCallback));
    }

    public void onHideCustomView() {
        super.onHideCustomView();
        this.this$0.listener.onExitFullscreen();
    }

    public Bitmap getDefaultVideoPoster() {
        Bitmap defaultVideoPoster = super.getDefaultVideoPoster();
        return defaultVideoPoster == null ? Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565) : defaultVideoPoster;
    }
}
