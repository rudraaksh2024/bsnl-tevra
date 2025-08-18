package bsnl.bsnl_teevra.wizards;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import bsnl.bsnl_teevra.R;

public class Ont_Configure_web_Activity extends AppCompatActivity {
    private WebView webView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.ont_configure_web_activity);
        this.webView = (WebView) findViewById(R.id.webView);
        String stringExtra = getIntent().getStringExtra("URL");
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setLoadWithOverviewMode(true);
        this.webView.getSettings().setUseWideViewPort(true);
        this.webView.getSettings().setSupportZoom(true);
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.getSettings().setDisplayZoomControls(false);
        this.webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        this.webView.clearCache(true);
        this.webView.clearFormData();
        this.webView.clearHistory();
        this.webView.clearSslPreferences();
        this.webView.setInitialScale(150);
        this.webView.setWebViewClient(new WebViewClient());
        this.webView.loadUrl(stringExtra);
        this.webView.setScrollBarStyle(33554432);
        this.webView.setScrollbarFadingEnabled(true);
    }
}
