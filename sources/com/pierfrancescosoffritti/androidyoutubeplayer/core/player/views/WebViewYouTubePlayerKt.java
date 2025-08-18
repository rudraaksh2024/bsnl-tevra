package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¨\u0006\u0004"}, d2 = {"readHTMLFromUTF8File", "", "inputStream", "Ljava/io/InputStream;", "core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebViewYouTubePlayer.kt */
public final class WebViewYouTubePlayerKt {
    /* JADX WARNING: Can't wrap try/catch for region: R(3:7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003f, code lost:
        throw new java.lang.RuntimeException("Can't parse HTML file.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0038 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String readHTMLFromUTF8File(java.io.InputStream r10) {
        /*
            java.lang.String r0 = "inputStream"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            r0 = r10
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = r0
            java.io.InputStream r1 = (java.io.InputStream) r1     // Catch:{ all -> 0x0040 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0038 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0038 }
            java.lang.String r3 = "utf-8"
            r2.<init>(r10, r3)     // Catch:{ Exception -> 0x0038 }
            java.io.Reader r2 = (java.io.Reader) r2     // Catch:{ Exception -> 0x0038 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0038 }
            java.io.Reader r1 = (java.io.Reader) r1     // Catch:{ Exception -> 0x0038 }
            java.util.List r10 = kotlin.io.TextStreamsKt.readLines(r1)     // Catch:{ Exception -> 0x0038 }
            r1 = r10
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch:{ Exception -> 0x0038 }
            java.lang.String r10 = "\n"
            r2 = r10
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ Exception -> 0x0038 }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 62
            r9 = 0
            java.lang.String r10 = kotlin.collections.CollectionsKt.joinToString$default(r1, r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0038 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            return r10
        L_0x0038:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException     // Catch:{ all -> 0x0040 }
            java.lang.String r1 = "Can't parse HTML file."
            r10.<init>(r1)     // Catch:{ all -> 0x0040 }
            throw r10     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r10)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.WebViewYouTubePlayerKt.readHTMLFromUTF8File(java.io.InputStream):java.lang.String");
    }
}
