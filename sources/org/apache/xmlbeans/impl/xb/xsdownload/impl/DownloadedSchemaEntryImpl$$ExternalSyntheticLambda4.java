package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.function.BiConsumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DownloadedSchemaEntryImpl$$ExternalSyntheticLambda4 implements BiConsumer {
    public final /* synthetic */ DownloadedSchemaEntryImpl f$0;

    public /* synthetic */ DownloadedSchemaEntryImpl$$ExternalSyntheticLambda4(DownloadedSchemaEntryImpl downloadedSchemaEntryImpl) {
        this.f$0 = downloadedSchemaEntryImpl;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.insertSchemaLocation(((Integer) obj).intValue(), (String) obj2);
    }
}
