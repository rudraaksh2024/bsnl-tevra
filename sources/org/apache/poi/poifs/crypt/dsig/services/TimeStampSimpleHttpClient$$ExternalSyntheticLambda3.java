package org.apache.poi.poifs.crypt.dsig.services;

import java.net.HttpURLConnection;
import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TimeStampSimpleHttpClient$$ExternalSyntheticLambda3 implements BiConsumer {
    public final /* synthetic */ HttpURLConnection f$0;

    public /* synthetic */ TimeStampSimpleHttpClient$$ExternalSyntheticLambda3(HttpURLConnection httpURLConnection) {
        this.f$0 = httpURLConnection;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.setRequestProperty((String) obj, (String) obj2);
    }
}
