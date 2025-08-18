package org.apache.poi.poifs.crypt.dsig.services;

import java.net.HttpURLConnection;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TimeStampSimpleHttpClient$$ExternalSyntheticLambda0 implements TimeStampSimpleHttpClient.MethodHandler {
    public final /* synthetic */ byte[] f$0;

    public /* synthetic */ TimeStampSimpleHttpClient$$ExternalSyntheticLambda0(byte[] bArr) {
        this.f$0 = bArr;
    }

    public final void handle(HttpURLConnection httpURLConnection) {
        TimeStampSimpleHttpClient.lambda$post$0(this.f$0, httpURLConnection);
    }
}
